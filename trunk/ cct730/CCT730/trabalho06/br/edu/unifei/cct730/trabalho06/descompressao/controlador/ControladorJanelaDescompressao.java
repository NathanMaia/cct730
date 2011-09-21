package br.edu.unifei.cct730.trabalho06.descompressao.controlador;

import br.edu.unifei.cct730.trabalho05.gui.componentes.MyFileChooser;
import br.edu.unifei.cct730.trabalho06.descompressao.gui.JanelaDescompressao;
import br.edu.unifei.cct730.trabalho06.eventos.MyActionListener;
import br.edu.unifei.cct730.trabalho06.padroes.Controlador;

/**
 * Classe responsavel por intermediar as solicitacoes do usuario
 * com o restante da aplicacao
 * 
 * @author fknappe
 *
 */
public class ControladorJanelaDescompressao extends Controlador {

	/**
	 * Construtor
	 * 
	 * @param JanelaDescompressao j
	 */
	public ControladorJanelaDescompressao(JanelaDescompressao j) {
		super(j);
	}

	@Override
	public void registraEventos() {
		
		// Instanciando um novo listener
		MyActionListener myListener = new MyActionListener();
		
		// Adicionando tratamento as acoes dos botoes do menu prinicipal
		((JanelaDescompressao)this.frame).getBtnAbrirArquivo().addActionListener(myListener);
		((JanelaDescompressao)this.frame).getBtnDescomprimir().addActionListener(myListener);
		((JanelaDescompressao)this.frame).getBtnSobre().addActionListener(myListener);
		((JanelaDescompressao)this.frame).getBtnSair().addActionListener(myListener);
	}
	
	/**
	 * Metodo responsavel por tratar as acoes
	 * de interacao com arquivos
	 * 
	 * @return void
	 */
	public void abrirArquivo() {
		
	}
	
	/**
	 * Metodo responsavel por realizar a descompressao
	 * da imagem sintetica comprimida
	 * 
	 * @return void
	 */
	public void descomprimirImagem() {
		
	}
	
	/**
	 * Metodo responsavel pela abertura da janela para escolha
	 * do arquivo de imagem
	 * 
	 * @return MyFileChooser
	 */
	private MyFileChooser abrirFileChooser() {

		// Declaracao das variaveis locais
		MyFileChooser fileChooser = null;

		// Inicializando o ficheiro de arquivos
		fileChooser = new MyFileChooser("Abrir arquivo de imagem");

		/*
		 *  Filtrando os arquivos a serem abertos somente para 
		 *  imagens em nivel de cinza
		 */
		fileChooser.filtro(".img", "Arquivos em nivel de cinza");
		return fileChooser;
	}
}
