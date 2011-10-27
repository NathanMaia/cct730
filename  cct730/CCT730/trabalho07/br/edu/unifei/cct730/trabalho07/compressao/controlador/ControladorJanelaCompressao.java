package br.edu.unifei.cct730.trabalho07.compressao.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JInternalFrame;

import br.edu.unifei.cct730.trabalho07.gui.janelas.JanelaImagemDigitalizada;
import br.edu.unifei.cct730.trabalho07.gui.painel.PainelImagem;
import br.edu.unifei.cct730.trabalho07.model.histograma.Histograma;
import br.edu.unifei.cct730.trabalho07.model.histograma.OperacoesImagem;
import br.edu.unifei.cct730.trabalho07.model.imagem.ImagemDigitalizada;
import br.edu.unifei.cct730.trabalho07.eventos.MyActionListener;
import br.edu.unifei.cct730.trabalho07.utils.arquivo.Arquivo;
import br.edu.unifei.cct730.trabalho07.utils.arquivo.ArquivoCabecalho;
import br.edu.unifei.cct730.trabalho07.utils.arquivo.ArquivoHDados;
import br.edu.unifei.cct730.trabalho07.utils.arquivo.ArquivoHHeader;
import br.edu.unifei.cct730.trabalho07.utils.arquivo.ArquivoImagem;
import br.edu.unifei.cct730.trabalho07.utils.arquivo.FactoryArquivo;
import br.edu.unifei.cct730.trabalho07.utils.Mensagem;
import br.edu.unifei.cct730.trabalho07.gui.componentes.MyFileChooser;
import br.edu.unifei.cct730.trabalho07.compressao.gui.JanelaCompressao;
import br.edu.unifei.cct730.trabalho07.compressao.model.ArvoreHuffman;
import br.edu.unifei.cct730.trabalho07.compressao.model.FrequenciaSimbolo;
import br.edu.unifei.cct730.trabalho07.gui.janelas.JanelaRelatorio;
import br.edu.unifei.cct730.trabalho07.padroes.Controlador;

/**
 * Classe responsavel por intermediar as acoes do usuario
 * com o restante da aplicacao
 * 
 * @author fknappe
 *
 */
public class ControladorJanelaCompressao extends Controlador {

	// Declaracao das variaveis de instancia
	private String caminhoUltimoArquivo = "";
	private ArquivoImagem arquivoImagem = null;
	private ArquivoCabecalho arquivoCabecalho = null;
	private ArquivoHHeader arquivoHHeader = null;
	private ArquivoHDados arquivoHDados = null;

	/**
	 * Construtor
	 * 
	 * @param JanelaCompressao j
	 */
	public ControladorJanelaCompressao(JanelaCompressao j) {
		super(j);
	}

	@Override
	public void registraEventos() {
		MyActionListener myListener = new MyActionListener();

		((JanelaCompressao)this.frame).getBtnAbrirArquivo().addActionListener(myListener);
		((JanelaCompressao)this.frame).getBtnComprimirHuffman().addActionListener(myListener);
		((JanelaCompressao)this.frame).getBtnSair().addActionListener(myListener);
		((JanelaCompressao)this.frame).getBtnSobre().addActionListener(myListener);
	}

	/**
	 * Metodo responsavel por instanciar um painel para criacao
	 * das imagens sinteticas
	 * 
	 * @param void
	 * @return void
	 */
	public void abrirArquivo() {
		// Declaracao de variaveis locais
		MyFileChooser fileChooser = null;
		String retorno = "";
		int op = 0;

		try {
			// Desabilitando todas as acoes ja existentes
			this.desfazerAcoesAnteriores();

			// Escolha do arquivo
			fileChooser = this.abrirFileChooser(
					"Abrir arquivo de imagem", 
					".img", 
					"Arquivos em nivel de cinza",
					"IMAGEM.img"
			);

			op = fileChooser.lancarOpenDialog(this.frame);
			retorno = fileChooser.getArquivoSelecionado(op);

			if(!(retorno.equals(MyFileChooser.OPERACAO_CANCELADA) || 
					retorno.equals(MyFileChooser.OPERACAO_ERRO))
			) {				
				// Definindo o caminho do arquivo para aberturas posteriores
				this.caminhoUltimoArquivo = fileChooser.getSelectedFile().getAbsolutePath();

				/*
				 * Realizando a abertura do arquivo
				 * da imagem 
				 */
				this.abrirArquivoImagem();

				/*
				 * Inicializando as imagens 
				 */
				this.inicializarImagemDigitalizada();

				/*
				 *  Garantindo que o arquivo da imagem foi finalizado
				 */
				this.fecharArquivoImagem();
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
			((JanelaCompressao)this.frame).habilitarMenu();
		}
	}

	/**
	 * Metodo responsavel por tratar as acoes
	 * do botao de compressao
	 * 
	 * @return void
	 */
	public void comprimirHuffman() {

		// Inicializando uma nova janela relatorio
		final JanelaRelatorio jRelatorio = new JanelaRelatorio();
		lancarFrame(jRelatorio);

		jRelatorio.getBtnFinalizar().addActionListener(new MyActionListener());
		jRelatorio.getBtnCompressao().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				try{
					// Declaracao de variaveis locais
					MyFileChooser fileChooser = null;
					ArvoreHuffman arvore = null;
					int retorno = 0;
					int numLinhas = 0, numColunas = 0;

					// Abertura para escolha do arquivo
					fileChooser = abrirFileChooser(
							"Salvar arquivo compressao", 
							".huff", 
							"Arvore de Huffman", 
							"imagem.huff"
					);

					retorno = fileChooser.lancarSaveDialog(frame);
					caminhoUltimoArquivo = fileChooser.getArquivoSelecionado(retorno);	

					// Abertura do arquivo de compressao
					abrirArquivoHuffman();

					// Construcao da estrutura de dados 
					arvore = constroiArvoreHuffman();

					/*
					 * Obtendo as dimensoes do arquivo 
					 */
					numLinhas = arquivoCabecalho.getNumeroLinhas();
					numColunas = arquivoCabecalho.getNumeroColunas();

					/*
					 * Chamada do metodo para salvar os dados 
					 * no arquivo de cabecalho 
					 */
					arquivoHHeader.salvarHuffmanHeader(
							arvore,
							numLinhas,
							numColunas
					);

					// Definindo o tamanho do header do arquivo de compactacao
					arquivoHDados.setHuffmanTamanhoHeader(arquivoHHeader.getHuffmanTamanhoHeader());

					/*
					 * Chamada do metodo para salvar os 
					 * dados comprimidos pelo algoritmo de huffman
					 */
					arquivoHDados.salvarHuffmanDados(
							arquivoImagem.getTonsCinza(
									numLinhas, 
									numColunas
							),
							arvore,
							numLinhas,
							numColunas		
					);

					// Gerando o relatorio de compressao
					jRelatorio.getTxtAreaRelatorio().append(geraRelatorioHuffman(arvore));

					// Fechando a escrita no arquivo
					fecharArquivoHuffman();

				} catch(IOException e) {
					e.printStackTrace();
					Mensagem.mostraErro(
							frame, 
							"Erro ao comprimir a imagem!"
					);
				} catch(IllegalArgumentException e) {  
					e.printStackTrace();
					Mensagem.mostraErro(
							frame, 
							e.getMessage()
					); 
				}
			}
		});
	}

	/*
	 **********************************************************************************************************************************************************************
	 *                                                            
	 *                                                            Metodos auxiliares (todos privados)
	 *                                                            
	 **********************************************************************************************************************************************************************/                                                       	

	/**
	 * Metodo responsavel por construir a
	 * estrutura de dados representativa
	 * da arvore de huffman
	 * 
	 * @return ArvoreHuffman
	 */
	private ArvoreHuffman constroiArvoreHuffman() throws IOException, IllegalArgumentException {

		// Declaracao das variaveis locais
		ArvoreHuffman arvore = null;
		List<FrequenciaSimbolo> frequenciaSimbolo = new ArrayList<FrequenciaSimbolo>();

		// Construcao do histograma da imagem
		Histograma histograma = OperacoesImagem.constroiHistograma(this.constroiImagemDigitalizada());

		// Inicializando a lista de frequencia dos simbolos da imagem
		for (int i = 0; i < histograma.getHistograma().length; i++) {
			if (histograma.getHistograma()[i] != 0) {
				frequenciaSimbolo.add(new FrequenciaSimbolo(histograma.getHistograma()[i], i + ""));
			}
		}

		// Inicializando a estrutura de dados da arvore de huffman
		arvore = new ArvoreHuffman(frequenciaSimbolo);

		return arvore;
	}

	/**
	 * Metodo responsavel por gerar o relatorio sobre
	 * o processo de compactacao da imagem pelo 
	 * algoritrmo da arvore de huffman
	 * 
	 * @param ArvoreHuffman arv
	 * 
	 * @return String
	 */
	private String geraRelatorioHuffman(ArvoreHuffman arv) {

		// Declaracao de variaveis locais
		String relatorio = "";

		try { 
			relatorio += arv.listaSimbolos();
			relatorio += "\n\nGrau de compactacao: \n" + "Imagem original : " + arquivoCabecalho.getTamanho() + " bytes\nImagem compactada: " + arquivoHDados.getHuffmanTamanho();
			relatorio += " bytes\nPorcentagem de reducao: " + (100 - 100 * (arquivoHDados.getHuffmanTamanho()) / (arquivoCabecalho.getTamanho())) + "%";

		} catch(IOException e) {
			e.printStackTrace();
			Mensagem.mostraErro(
					frame, 
					"Erro na leitura da dimensao da imagem"
			);
		}

		return relatorio;
	}

	/**
	 * Metodo responsavel por instanciar o file chooser
	 * para escolha do nome e local que o arquivo da
	 * imagem comprimida sera salvo
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
		Short[][] tonsDeCinza = null;
		ImagemDigitalizada imagem = null;

		/*
		 * Inicializando as variaveis locais
		 */
		numeroLinhas = this.arquivoCabecalho.getNumeroLinhas();
		numeroColunas = this.arquivoCabecalho.getNumeroColunas();
		tonsDeCinza = arquivoImagem.getTonsCinza(numeroLinhas, numeroColunas);

		// Construindo a imagem
		imagem = (ImagemDigitalizada) new ImagemDigitalizada(numeroLinhas, numeroColunas).constroiImagem(tonsDeCinza);

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
		for(JInternalFrame j : ((JanelaCompressao)this.frame).getDesktop().getAllFrames()) {
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
		((JanelaCompressao)this.frame).desabilitarMenu();
	}

	/**
	 * Metodo responsavel por realizar a abertura dos arquivos
	 * da imagem
	 * 
	 * @return void
	 * @throws IOException
	 */
	private void abrirArquivoImagem() throws IOException, IllegalArgumentException {

		/*
		 * Abertura do arquivo para leitura da imagem
		 */
		if(this.arquivoImagem == null) {
			arquivoImagem = (ArquivoImagem)FactoryArquivo.create(
					Arquivo.ARQUIVO_IMAGEM,
					caminhoUltimoArquivo
			);

			arquivoCabecalho = (ArquivoCabecalho)FactoryArquivo.create(
					Arquivo.ARQUIVO_CABECALHO,
					arquivoImagem.getAbsolutePath().replace("IMG", "CAB")
			);
		}
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
	 * Metodo responsavel por fechar o stream de leitura para o arquivo
	 * da imagem
	 * 
	 * @return void
	 * @throws IOException
	 * @throws NullPointerException
	 */
	private void fecharArquivoImagem() throws IOException, NullPointerException {

		/*
		 * Finalizando o stream com os arquivos da imagem
		 */
		if(!this.arquivoImagem.isFechadoLeitura()) {
			arquivoImagem.fecharArquivoLeitura();
			arquivoCabecalho.fecharArquivoLeitura();
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
