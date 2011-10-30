package br.edu.unifei.cct730.trabalho08.gui.componentes;

import java.io.File;
import java.io.FileNotFoundException;

import javax.swing.JFileChooser;

/**
 * Classe responsavel por implementar o comportamento de um
 * ficheiro de arquivos do projeto
 * 
 * @author fknappe
 *
 */
public class MyFileChooser extends javax.swing.JFileChooser {

	// Declaracao das constantes da classe
	public static final String OPERACAO_ERRO = "Erro na abertura do arquivo";
	public static final String OPERACAO_CANCELADA = "Operacao Cancelada";

	/**
	 * Construtor 
	 * 
	 * @param String title
	 */
	public MyFileChooser(String title) {
		super(".");
		this.setDialogTitle(title);
	}
	
	/**
	 * Metodo responsavel por lancar o dialog responsavel
	 * pela abertura do arquivo
	 * 
	 * @param JFrame j
	 * @return int
	 */
	public int lancarOpenDialog(javax.swing.JFrame j) {
		int retorno = this.showOpenDialog(j);
		return retorno;
	}
	
	/**
	 * Metodo responsavel por lancar o dialog responsavel
	 * por salvar o arquivo
	 * 
	 * @param JFrame j
	 * 
	 * @return int
	 */
	public int lancarSaveDialog(javax.swing.JFrame j) {
		int retorno = this.showSaveDialog(j);
		return retorno;
	}
	
	/**
	 * Retorna o caminho do arquivo selecionado pelo
	 * filechooser
	 * 
	 * @param String op
	 * 
	 * @return String
	 */
	public String getArquivoSelecionado(int op) throws FileNotFoundException {
		
		// Declaracao de variaveis locais
		String caminhoArquivo = "";
		
		switch(op) {
			case JFileChooser.APPROVE_OPTION:
				caminhoArquivo = this.getSelectedFile().getAbsolutePath();
				break;
				
			case JFileChooser.CANCEL_OPTION:
				caminhoArquivo = OPERACAO_CANCELADA;
				break;
				
			case JFileChooser.ERROR_OPTION:
				caminhoArquivo = OPERACAO_ERRO;
				break;
			
			default:
				throw new FileNotFoundException();
		}
		return caminhoArquivo;
	}

	/**
	 * Define um filtro de extens›es dos arquivos que ser‹o
	 * manipulados pelo FileChooser
	 * 
	 * @param String type
	 * 
	 * @return void
	 */
	public void filtro(String fileType, String fileDescription) {
		final String type = fileType;
		final String description = fileDescription;
		
		this.setFileFilter(new javax.swing.filechooser.FileFilter() {
			@Override
			public boolean accept(File f){
				return f.getName().toLowerCase().endsWith(type) || f.isDirectory();
			}

			@Override
			public String getDescription() {
				return description;
			}
		});
	}
	
	/**
	 * Metodo responsavel por definir um filtro default
	 * para o formato dos arquivos a serem salvos
	 * 
	 * @param String fileName
	 * 
	 * @return void
	 */
	public void formatoPadrao(String fileName) {
		this.setSelectedFile(new File(fileName));
	}
	
	/**
	 * Metodo que define o diretorio inicial que o file chooser
	 * se posicionara
	 * 
	 * @param String f
	 * 
	 * @return void
	 */
	public void defineDiretorioInicial(String path) {
		if(!path.equals("")) {
			this.setCurrentDirectory(new File(path));
		}
	}
}