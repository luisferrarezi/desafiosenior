package br.com.desafiosenior.controller;

import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JList;

import br.com.desafiosenior.dao.Consultas;
import br.com.desafiosenior.model.Estado;

public class EstadoControl {
	public EstadoControl() {}
	
	public void getListaEstados(JList lista, Boolean somenteMaiorMenorEstado) {
		Consultas consultas = new Consultas();
		setListaEstados(lista, consultas.getListaEstados(somenteMaiorMenorEstado));
	}	
	
	private void setListaEstados(JList lista, List<Estado> listaEstados) {
		DefaultListModel modelo = new DefaultListModel();		
		lista.setModel(modelo);		
		
		for (Estado estado : listaEstados) {
			String linha = estado.getNome() + " , " + estado.getQtdCidades();
			
			modelo.addElement(linha);
		}
		
		lista.setModel(modelo);
	}
}
