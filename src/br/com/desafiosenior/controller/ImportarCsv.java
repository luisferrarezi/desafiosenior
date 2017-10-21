package br.com.desafiosenior.controller;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.util.Arrays;
import java.util.HashMap;

import javax.swing.JOptionPane;

import br.com.desafiosenior.dao.*;

public class ImportarCsv {

	private static final String VIRGULA = ",";
	
	
	public void ImportarCsv() {
		
	}
	
	public boolean processaImportacao(String caminhoArquivo) {
		try {
			Conexao conexao = new Conexao();
			Connection con = conexao.ObterConexao();			
			
			BufferedReader reader = new BufferedReader(new FileReader(caminhoArquivo));
	        String linha = null;	        
	        reader.readLine();
	        while ((linha = reader.readLine()) != null) {
	            String[] dadosCidade = linha.split(VIRGULA);
	            
	            HashMap parametros = new HashMap();
	            parametros.put("IBGE_ID", dadosCidade[0]);
	            parametros.put("UF", dadosCidade[1]);
	            parametros.put("NAME", dadosCidade[2]);
	            parametros.put("CAPITAL", dadosCidade[3]);
	            parametros.put("LON", dadosCidade[4]);
	            parametros.put("LAT", dadosCidade[5]);
	            parametros.put("NO_ACCENTS", dadosCidade[6]);
	            parametros.put("ALTERNATIVE_NAMES", dadosCidade[7]);
	            parametros.put("MICROREGION", dadosCidade[8]);
	            parametros.put("MESOREGION", dadosCidade[9]);
	            
	            Importacao importacao = new Importacao();
	            importacao.importarCidades(parametros, con);
	        }
	        reader.close();
	        
	        JOptionPane.showMessageDialog(null,"Importação realizada com sucesso!","Importação",JOptionPane.INFORMATION_MESSAGE);
	        
	        return true;	        
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,"Falhar na importacao! Erro: " + e.getMessage(),"Importação",JOptionPane.ERROR_MESSAGE);
			return false;
		}
	}
}
