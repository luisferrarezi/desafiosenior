package br.com.desafiosenior.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.desafiosenior.model.Cidade;
import br.com.desafiosenior.model.Estado;

public class Consultas {	
	
	public Consultas() {
		
	}
	
	public List<Cidade> getListaCapitais(){
		try {
		     Conexao conexao = new Conexao();
			 Connection con = conexao.ObterConexao();			
			
	         List<Cidade> cidades = new ArrayList<Cidade>();
	         PreparedStatement stmt = con.prepareStatement("select c.ibge_id, c.nome, c.lon, c.lat, c.no_accents, c.alternative_names, e.uf, mr.microregion, me.mesoregion\r\n" + 
	         		"  from cidade c\r\n" + 
	         		" inner join estado e on e.id = estado_id_fk \r\n" + 
	         		" inner join microregion mr on mr.id = microregion_id_fk \r\n" + 
	         		" inner join mesoregion me on me.id = mesoregion_id_fk \r\n" + 
	         		" where capital = 'S'\r\n" + 
	         		"order by c.nome");
	         ResultSet rs = stmt.executeQuery();
	 
	         while (rs.next()) {
	             Cidade cidade = new Cidade();
	             cidade.setIbgeId(rs.getString("ibge_id"));
	             cidade.setNome(rs.getString("nome"));	             
	             cidade.setLon(rs.getString("lon"));
	             cidade.setLat(rs.getString("lat"));
	             cidade.setNoAccents(rs.getString("no_accents"));
	             cidade.setAlternativeNames(rs.getString("alternative_names"));
	             cidade.setUf(rs.getString("uf"));
	             cidade.setMicroRegion(rs.getString("microregion"));
	             cidade.setMesoRegion(rs.getString("mesoregion"));
	 
	             cidades.add(cidade);
	         }
	         rs.close();
	         stmt.close();
	         return cidades;
	     } catch (SQLException e) {
	         throw new RuntimeException(e);
	     }
	}
	
	public List<Cidade> getListaCidades(String codigoIbge, String estado){
		try {
		     Conexao conexao = new Conexao();
			 Connection con = conexao.ObterConexao();
			 String sql = "";
			 
			 if (!"".equals(codigoIbge)) {
				 sql = "select c.ibge_id, c.nome, c.lon, c.lat, c.no_accents, c.alternative_names, e.uf, mr.microregion, me.mesoregion\r\n" + 
			         		"  from cidade c\r\n" + 
			         		" inner join estado e on e.id = estado_id_fk \r\n" + 
			         		" inner join microregion mr on mr.id = microregion_id_fk \r\n" + 
			         		" inner join mesoregion me on me.id = mesoregion_id_fk \r\n" + 
			         		" where c.ibge_id = ?";
			 } else if (!"".equals(estado)) {
				 sql = "select c.nome\r\n" + 
			         		"  from cidade c\r\n" + 
			         		" inner join estado e on e.id = estado_id_fk \r\n" +
			         		" where e.uf = ?";				 
			 }
			
	         List<Cidade> cidades = new ArrayList<Cidade>();
	         PreparedStatement stmt = con.prepareStatement(sql);
	         
	         if (!"".equals(codigoIbge)) {
	        	 stmt.setString(1, codigoIbge);
	         } else if (!"".equals(estado)) {
	        	 stmt.setString(1, estado);
	         }
	         
	         ResultSet rs = stmt.executeQuery();
	 
	         while (rs.next()) {
	             Cidade cidade = new Cidade();
	             
	             if (!"".equals(codigoIbge)) {
	            	 cidade.setIbgeId(rs.getString("ibge_id"));
	            	 cidade.setNome(rs.getString("nome"));	             
	            	 cidade.setLon(rs.getString("lon"));
	            	 cidade.setLat(rs.getString("lat"));
	            	 cidade.setNoAccents(rs.getString("no_accents"));
	            	 cidade.setAlternativeNames(rs.getString("alternative_names"));
	            	 cidade.setUf(rs.getString("uf"));
	            	 cidade.setMicroRegion(rs.getString("microregion"));
	            	 cidade.setMesoRegion(rs.getString("mesoregion"));
	             } else if(!"".equals(estado)) {
	            	 cidade.setNome(rs.getString("nome"));
	             }
	 
	             cidades.add(cidade);
	         }
	         rs.close();
	         stmt.close();
	         return cidades;
	     } catch (SQLException e) {
	         throw new RuntimeException(e);
	     }
	}	
	
	public List<Estado> getListaEstados(Boolean estadosMaiorMenor){
		try {
		     Conexao conexao = new Conexao();
			 Connection con = conexao.ObterConexao();			
			
			 String sql = "";
			 if (estadosMaiorMenor) {
				 sql = "select * \r\n" + 
			         		"from (select e.UF, (select count(*) from cidade where estado_id_fk = e.id) qtdCidades\r\n" + 
			         		"        from Estado e \r\n" + 
			         		"        order by 2 desc)\r\n" + 
			         		"where ROWNUM = 1\r\n" + 
			         		"union \r\n" + 
			         		"select * \r\n" + 
			         		"from (select e.UF, (select count(*) from cidade where estado_id_fk = e.id) qtdCidades\r\n" + 
			         		"        from Estado e \r\n" + 
			         		"        order by 2 )\r\n" + 
			         		"where ROWNUM = 1";
			 } else {
				 sql = "select e.UF, (select count(*) from cidade where estado_id_fk = e.id) qtdCidades\r\n" + 
			         		"  from Estado e\r\n" + 
			         		"order by e.uf";
			 }
			 
	         List<Estado> estados = new ArrayList<Estado>();
	         PreparedStatement stmt = con.prepareStatement(sql);
	         ResultSet rs = stmt.executeQuery();
	 
	         while (rs.next()) {
	             Estado estado = new Estado();
	             estado.setNome(rs.getString("uf"));	             
	             estado.setQtdCidades(rs.getString("qtdcidades"));
	 
	             estados.add(estado);
	         }
	         rs.close();
	         stmt.close();
	         return estados;
	     } catch (SQLException e) {
	         throw new RuntimeException(e);
	     }
	}	
}
