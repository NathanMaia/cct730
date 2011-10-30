package br.edu.unifei.cct730.trabalho07.descompressao.controlador;

import java.io.IOException;

import javax.swing.JInternalFrame;

import br.edu.unifei.cct730.trabalho07.compressao.model.ArvoreHuffman;
import br.edu.unifei.cct730.trabalho07.descompressao.gui.JanelaDescompressao;
import br.edu.unifei.cct730.trabalho07.eventos.MyActionListener;
import br.edu.unifei.cct730.trabalho07.gui.componentes.MyFileChooser;
import br.edu.unifei.cct730.trabalho07.gui.janelas.JanelaImagemDigitalizada;
import br.edu.unifei.cct730.trabalho07.gui.painel.PainelImagem;
import br.edu.unifei.cct730.trabalho07.model.imagem.ImagemDigitalizada;
import br.edu.unifei.cct730.trabalho07.padroes.Controlador;
import br.edu.unifei.cct730.trabalho07.utils.Mensagem;
import br.edu.unifei.cct730.trabalho07.utils.arquivo.Arquivo;
import br.edu.unifei.cct730.trabalho07.utils.arquivo.ArquivoHDados;
import br.edu.unifei.cct730.trabalho07.utils.arquivo.ArquivoHHeader;
import br.edu.unifei.cct730.trabalho07.utils.arquivo.FactoryArquivo;

/**
 * Classe responsavel por intermediar as acoes do usuario
 * com o restante da aplicacao
 * 
 * @author fknappe
 *
 */
public class ControladorJanelaDescompressao extends Controlador {

	// Declaracao de variaveis de instancia
	private String caminhoUltimoArquivo = "";
	private ArquivoHHeader arquivoHHeader;
	private ArquivoHDados arquivoHDados;
	private Short[][] tonsCinza = null;

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
		MyActionListener myListener = new MyActionListener();

		((JanelaDescompressao)this.frame).getBtnAbrirArquivo().addActionListener(myListener);
		((JanelaDescompressao)this.frame).getBtnDescomprimirHuffman().addActionListener(myListener);
		((JanelaDescompressao)this.frame).getBtnSobre().addActionListener(myListener);
		((JanelaDescompressao)this.frame).getBtnSair().addActionListener(myListener);
	}

	/**
	 * Metodo responsavel por tratar a acao
	 * do botao de abertura do arquivo 
	 * de compressao
	 * 
	 * @return void
	 */
	public void abrirArquivo() {

		// Declaracao de variaveis locais
		MyFileChooser fileChooser = null;
		int op = 0;
		String retorno = "";

		try {

			// Desabilitando todas as acoes ja existentes
			this.desfazerAcoesAnteriores();

			// Escolha do arquivo
			fileChooser = this.abrirFileChooser(
					"Abrir arquivo compressao", 
					".huff", 
					"Arvore de Huffman", 
					"imagem.huff"
			);

			op = fileChooser.lancarOpenDialog(this.frame);
			retorno = fileChooser.getArquivoSelecionado(op);

			if(!(retorno.equals(MyFileChooser.OPERACAO_CANCELADA) || 
					retorno.equals(MyFileChooser.OPERACAO_ERRO))
			) {				
				// Definindo o caminho do arquivo para aberturas posteriores
				this.caminhoUltimoArquivo = fileChooser.getSelectedFile().getAbsolutePath();

				/*
				 * Realizando a abertura do 
				 * arquivo de compressao
				 */
				this.abrirArquivoHuffman();
			}

		} catch(IOException e) {
			e.printStackTrace();
			Mensagem.mostraErro(
					this.frame, 
					"Falha na abertura do arquivo!"
			);

		} catch(IllegalArgumentException e) {  
			e.printStackTrace();
			Mensagem.mostraErro(
					this.frame, 
					e.getMessage()
			);

		} finally {
			//Habilitando as acoes do menu 
			((JanelaDescompressao)this.frame).habilitarMenu();
			Mensagem.mostraMensagem(
					this.frame, 
					"Arquivo aberto com sucesso!"
			);
		}
	}

	/**
	 * Metodo responsavel por tratar a acao
	 * do botao de descompressao dos dados
	 * do arquivo
	 * 
	 * @return void
	 */
	public void descomprimirHuffman() {

		// Declaracao de variaveis locais
		ArvoreHuffman arvore = null;

		try {

			arvore = this.arquivoHHeader.lerHuffmanHeader();

			this.tonsCinza = this.arquivoHDados.lerHuffmanDados(
					arvore, 
					this.arquivoHHeader.getQuantidadeBits(), 
					this.arquivoHHeader.getNumeroLinhas(), 
					this.arquivoHHeader.getNumeroColunas()
			);

			this.inicializarImagemDigitalizada();

			this.fecharArquivoHuffman();

		} catch(IOException e) {
			e.printStackTrace();
			Mensagem.mostraErro(
					this.frame, 
					"Erro na leitura do arquivo!"
			);

		} catch(NumberFormatException e) {
			e.printStackTrace();
			Mensagem.mostraErro(
					this.frame, 
					"Falha na leitura dos bits da compressao"
			);
		}
	}

	/*
	 **********************************************************************************************************************************************************************
	 *
	 *                                                             Metodos auxiliares (todos privados)
	 *
	 *********************************************************************************************************************************************************************/

	/**
	 * Metodo responsavel por instanciar o file chooser
	 * para escolha do nome e local que o arquivo da
	 * imagem de dados da compressao
	 * 
	 * @return MyFileChooser
	 */
	private MyFileChooser abrirFileChooser(
			String title, 
			String fileType, 
			String fileDescription, 
			String format
	) {

		// Declaracao de variaveis locais
		MyFileChooser fileChooser = null;

		/*
		 * Inicializando o filechooser
		 */
		fileChooser = new MyFileChooser(title);
		fileChooser.setFileSelectionMode(MyFileChooser.FILES_ONLY);
		fileChooser.defineDiretorioInicial(caminhoUltimoArquivo);
		fileChooser.filtro(fileType, fileDescription);
		fileChooser.formatoPadrao(format);

		return fileChooser;
	}

	/**
	 * Metodo responsavel por inicializar a
	 * imagem digitalizada do projeto
	 * 
	 * @return void
	 */
	private void inicializarImagemDigitalizada() throws IOException, IllegalArgumentException {

		/*
		 * Inicializando a imagem digitalizada
		 */
		JanelaImagemDigitalizada j = new JanelaImagemDigitalizada(
				new PainelImagem(
						this.constroiImagemDigitalizada()	
				)
		); 

		/*
		 * Adicionando a janela interna que contem
		 * a imagem digitalizada no desktop e tornand-a
		 * visivel
		 */
		lancarJanelaImagem(j);
	}

	/**
	 * Metodo responsavel por criar uma instancia da imagem digitalizada
	 * 
	 * @return ImagemDigitalizada
	 * @throws IOException
	 * @throws IllegalArgumentException
	 */
	private ImagemDigitalizada constroiImagemDigitalizada() throws IOException, IllegalArgumentException {

		// Declaracao das variaveis locais
		int numeroLinhas, numeroColunas = 0;
		ImagemDigitalizada imagem = null;

		/*
		 * Inicializando as variaveis locais
		 */
		numeroLinhas = this.arquivoHHeader.getNumeroLinhas();
		numeroColunas = this.arquivoHHeader.getNumeroColunas();

		// Construindo a imagem
		imagem = (ImagemDigitalizada) new ImagemDigitalizada(numeroLinhas, numeroColunas).constroiImagem(this.tonsCinza);

		return imagem;	
	}

	/**
	 * Metodo responsavel por desfazer as acoes anteriores
	 * na reabertura do arquivo
	 * 
	 * @return void
	 */
	private void desfazerAcoesAnteriores() {

		/*
		 * Finalizando todas as acoes anteriores
		 */
		for(JInternalFrame j : ((JanelaDescompressao)this.frame).getDesktop().getAllFrames()) {
			j.dispose();
		}

		/*
		 *  Limpando o cache do caminho para o ultimo
		 *  manipulado
		 */
		caminhoUltimoArquivo = "";

		/*
		 * Restaurando o menu para a acao inicial
		 */
		((JanelaDescompressao)this.frame).desabilitarMenu();
	}

	/**
	 * Metodo responsavel por realizar a abertura do arquivo
	 * de huffman para compactacao dos dados da imagem
	 * 
	 * @return void
	 * @throws IOException
	 * @throws IllegalArgumentException
	 */
	private void abrirArquivoHuffman() throws IOException, IllegalArgumentException {

		/*
		 *  Abertura do arquivo para
		 *  escrita da compactacao
		 */
		if(this.arquivoHHeader == null) {
			arquivoHHeader = (ArquivoHHeader)FactoryArquivo.create(
					Arquivo.ARQUIVO_HUFFMAN_HEADER,
					caminhoUltimoArquivo
			);

			arquivoHDados = new ArquivoHDados(
					arquivoHHeader.getAbsolutePath().concat(".dados")
			);
		}
	}

	/**
	 * Metodo responsavel por fechar o stream de escrita para o
	 * arquivo de compactacao
	 *
	 * @return void
	 * @throws IOException
	 * @throws NullPointerException
	 */
	private void fecharArquivoHuffman() throws IOException, NullPointerException {

		/* 
		 * Finalizando o stream de escrita do arquivo de compactacao
		 */
		if(!this.arquivoHHeader.isFechadoEscrita()) {
			arquivoHHeader.fecharArquivoEscrita();
			arquivoHDados.fecharArquivoEscrita();
		}
	}
}
