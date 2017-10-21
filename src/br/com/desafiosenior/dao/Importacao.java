package br.com.desafiosenior.dao;

import java.sql.*;
import java.util.HashMap;

public class Importacao {	
	
	public Importacao() {		
    }
	
	public void importarCidades(HashMap parametros, Connection con) throws Exception{		
		CallableStatement cs = con.prepareCall("{call SP_IMPORTAR_CIDADES(?,?,?,?,?,?,?,?,?,?)}");
		
		cs.setString(1, (String) parametros.get("IBGE_ID"));
		cs.setString(2, parametros.get("UF").toString().trim());
		cs.setString(3, (String) parametros.get("NAME"));
		cs.setString(4, parametros.get("CAPITAL").toString().trim());
		cs.setString(5, (String) parametros.get("LON"));
		cs.setString(6, (String) parametros.get("LAT"));
		cs.setString(7, (String) parametros.get("NO_ACCENTS"));
		cs.setString(8, (String) parametros.get("ALTERNATIVE_NAMES"));
		cs.setString(9, (String) parametros.get("MICROREGION"));
		cs.setString(10, (String) parametros.get("MESOREGION"));
		
		cs.execute();
		cs.close();
	}
}
