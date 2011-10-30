package br.edu.unifei.cct730.trabalho08.principal.controlador;

import java.io.IOException;

import javax.swing.JInternalFrame;

import br.edu.unifei.cct730.trabalho07.utils.Mensagem;
import br.edu.unifei.cct730.trabalho08.gui.componentes.MyFileChooser;
import br.edu.unifei.cct730.trabalho08.gui.janelas.JanelaImagemDigitalizada;
import br.edu.unifei.cct730.trabalho08.gui.painel.PainelImagem;
import br.edu.unifei.cct730.trabalho08.model.imagem.ImagemDigitalizada;
import br.edu.unifei.cct730.trabalho08.utils.arquivo.Arquivo;
import br.edu.unifei.cct730.trabalho08.utils.arquivo.FactoryArquivo;
import br.edu.unifei.cct730.trabalho08.eventos.MyActionListener;
import br.edu.unifei.cct730.trabalho08.padroes.Controlador;
import br.edu.unifei.cct730.trabalho08.principal.gui.JanelaPrincipal;
import br.edu.unifei.cct730.trabalho08.utils.arquivo.ArquivoCabecalho;
import br.edu.unifei.cct730.trabalho08.utils.arquivo.ArquivoImagem;

public class ControladorPrincipal extends Controlador {

	// Declaracao das variaveis de instancia
	private String caminhoUltimoArquivo = "";
	private ArquivoCabecalho arquivoCabecalho;
	private ArquivoImagem arquivoImagem;
	
	/**
	 * Construtor
	 * 
	 * @param JanelaPrincipal j
	 */
	public ControladorPrincipal(JanelaPrincipal j) {
		super(j);
	}

	@Override
	public void registraEventos() {

		MyActionListener myListener = new MyActionListener();

		((JanelaPrincipal)this.frame).getBtnAbrirArquivo().addActionListener(myListener);
		((JanelaPrincipal)this.frame).getBtnSobre().addActionListener(myListener);
		((JanelaPrincipal)this.frame).getBtnSair().addActionListener(myListener);
	}

	/**
	 * Metodo responsavel por tratar as acoes
	 * do botao de abertura do arquivo da
	 * imagem
	 * 
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
			((JanelaPrincipal)this.frame).habilitarMenu();
		}
	}

	/*
	 **********************************************************************************************************************************************************************
	 *                                                            
	 *                                                            Metodos auxiliares (todos privados)
	 *                                                            
	 **********************************************************************************************************************************************************************/                                                       	

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
		for(JInternalFrame j : ((JanelaPrincipal)this.frame).getDesktop().getAllFrames()) {
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
		((JanelaPrincipal)this.frame).desabilitarMenu();
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
}
