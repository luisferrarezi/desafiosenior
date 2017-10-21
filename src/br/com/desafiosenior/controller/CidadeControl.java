package br.com.desafiosenior.controller;

import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import br.com.desafiosenior.model.Cidade;

import br.com.desafiosenior.dao.Consultas;

public class CidadeControl {
	public CidadeControl() {}
	
	public void getListaCapitais(JList lista) {
		Consultas consultas = new Consultas();		
		setListaCidades(lista, consultas.getListaCapitais(), "", "");
	}
	
	public void getListaCidades(JList lista, String codigoIbge, String estado) {
		Consultas consultas = new Consultas();		
		setListaCidades(lista, consultas.getListaCidades(codigoIbge, estado), codigoIbge, estado);
	}	
	
	private void setListaCidades(JList lista, List<Cidade> listaCapitais, String codigoIbge, String estado) {
		DefaultListModel modelo = new DefaultListModel();		
		lista.setModel(modelo);
		String linha ="";
		
		for (Cidade cidade : listaCapitais) {
			if ("".equals(estado)) {
				linha = cidade.getIbgeId() + " , " + cidade.getUf() + " , " + cidade.getNome() + " TRUE , " +					       
				        cidade.getLon() + " , " + cidade.getLat() + " , " + cidade.getNoAccents() + " , " +
					    cidade.getAlternativeNames() + " , " + 
					    cidade.getMicroRegion() + " , " + cidade.getMesoRegion();
			} else {
				linha = cidade.getNome();
			}
			
			modelo.addElement(linha);
		}		
		lista.setModel(modelo);
	}
}
