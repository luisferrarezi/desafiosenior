package br.com.desafiosenior.view;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;

import java.awt.event.*;
import java.io.File;

import br.com.desafiosenior.controller.CidadeControl;
import br.com.desafiosenior.controller.EstadoControl;
import br.com.desafiosenior.controller.ImportarCsv;

public class Principal extends JFrame {

    private JButton botaoBuscar;
    private JButton botaoImportar;
    private JButton botaoBuscarCapitais;
    private JButton botaoBuscarEstados;
    private JButton botaoBuscarPorIDIBGE;
    private JButton botaoBuscarPorEstado;
    private JCheckBox buscaMaiorMenorEstado;
    private JTextField campo;    
    private JFileChooser dlg;
    private JLabel tituloCampo;
    private JList lista;

    public Principal() {
        this.setTitle("Desafio S�nior");
        this.setBounds(0, 0, 800, 600);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.getContentPane().setLayout(null);

        dlg = new JFileChooser();
        
        tituloCampo = new JLabel();
        tituloCampo.setText("Selecione o arquivo que deseja importar:");
        tituloCampo.setBounds(10, 20, 300, 30);
        this.add(tituloCampo);        
        
        campo = new JTextField();
        campo.setBounds(10, 50, 500, 30);
        campo.setEditable(false);
        this.add(campo);
        
        botaoBuscar = new JButton();
        botaoBuscar.setText("...");
        botaoBuscar.setBounds(510, 50, 30, 29);
        this.add(botaoBuscar);        

        botaoBuscar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                JFileChooser abrir = new JFileChooser();
                abrir.setFileFilter(new FileFilter() {
					
					@Override
					public String getDescription() {
						return "*.csv";
					}
					
					@Override
					public boolean accept(File f) {						
						return (f.getName().endsWith(".csv")) || f.isDirectory();
					}
				});
                int retorno = abrir.showOpenDialog(null);  
                           if (retorno==JFileChooser.APPROVE_OPTION)  
                        	   campo.setText(abrir.getSelectedFile().getAbsolutePath());                
            }
        });
        
        botaoImportar = new JButton();
        botaoImportar.setText("Importar arquivo");
        botaoImportar.setBounds(10, 90, 130, 30);
        this.add(botaoImportar);        

        botaoImportar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
            	boolean importado = false;
            	
            	botaoBuscar.setEnabled(importado);
            	botaoImportar.setEnabled(importado);
            	
            	ImportarCsv importarCsv = new ImportarCsv();
            	importado = importarCsv.processaImportacao(campo.getText());
            	
            	botaoBuscar.setEnabled(importado);
            	botaoImportar.setEnabled(importado);
            }
        });
        
        botaoBuscarCapitais = new JButton();
        botaoBuscarCapitais.setText("Buscar Capitais");
        botaoBuscarCapitais.setBounds(140, 90, 130, 30);
        this.add(botaoBuscarCapitais);        

        botaoBuscarCapitais.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {            	            	
            	CidadeControl cidadeControl = new CidadeControl();
            	cidadeControl.getListaCapitais(lista);
            }
        });
        
        botaoBuscarEstados = new JButton();
        botaoBuscarEstados.setText("Buscar Estados");
        botaoBuscarEstados.setBounds(270, 90, 130, 30);
        this.add(botaoBuscarEstados);        

        botaoBuscarEstados.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {            	            	
            	EstadoControl estadoControl = new EstadoControl();
            	estadoControl.getListaEstados(lista, buscaMaiorMenorEstado.isSelected());
            }
        });
       
        botaoBuscarPorIDIBGE = new JButton();
        botaoBuscarPorIDIBGE.setText("Buscar Cidades por ID IBGE");
        botaoBuscarPorIDIBGE.setBounds(400, 90, 190, 30);
        this.add(botaoBuscarPorIDIBGE);        

        botaoBuscarPorIDIBGE.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {            	            	
            	CidadeControl cidadeControl = new CidadeControl();
            	
            	String codigoIbge = JOptionPane.showInputDialog("Digite o C�digo do IBGE da cidade");
            	
            	cidadeControl.getListaCidades(lista, codigoIbge, "");
            }
        });
        
        botaoBuscarPorEstado = new JButton();
        botaoBuscarPorEstado.setText("Buscar Cidades por Estado");
        botaoBuscarPorEstado.setBounds(590, 90, 190, 30);
        this.add(botaoBuscarPorEstado);        

        botaoBuscarPorEstado.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {            	            	
            	CidadeControl cidadeControl = new CidadeControl();
            	
            	String estado = JOptionPane.showInputDialog("Digite a sigla do estado ");
            	
            	cidadeControl.getListaCidades(lista, "", estado);
            }
        });        

        
        buscaMaiorMenorEstado = new JCheckBox();
        buscaMaiorMenorEstado.setText("Buscar o maior e menor estado");
        buscaMaiorMenorEstado.setBounds(270, 120, 250, 30);
        this.add(buscaMaiorMenorEstado);              
                
        lista = new JList();
        JScrollPane painelLista = new JScrollPane(lista);
        painelLista.setBounds(10, 150, 770, 400);
        this.add(painelLista);
    } 

    public static void main(String[] args) {
        Principal exemplo = new Principal();
        exemplo.setVisible(true);
    } 

} 