package br.edu.unifei.cct730.trabalho04.principal.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

import javax.swing.JInternalFrame;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.plaf.FileChooserUI;

import br.edu.unifei.cct730.trabalho04.utils.Mensagem;
import br.edu.unifei.cct730.trabalho04.eventos.MyActionListener;
import br.edu.unifei.cct730.trabalho04.eventos.PainelImagemListener;
import br.edu.unifei.cct730.trabalho04.gui.componentes.MyFileChooser;
import br.edu.unifei.cct730.trabalho04.gui.janelas.JanelaImagemDigitalizada;
import br.edu.unifei.cct730.trabalho04.gui.janelas.JanelaImagemEqualizada;
import br.edu.unifei.cct730.trabalho04.gui.janelas.JanelaHistograma;
import br.edu.unifei.cct730.trabalho04.gui.janelas.JanelaImagemBinaria;
import br.edu.unifei.cct730.trabalho04.gui.janelas.JanelaParametrosZoom;
import br.edu.unifei.cct730.trabalho04.gui.painel.PainelImagem;
import br.edu.unifei.cct730.trabalho04.gui.painel.PainelImagemBinaria;
import br.edu.unifei.cct730.trabalho04.padroes.Controlador;
import br.edu.unifei.cct730.trabalho04.utils.arquivo.ArquivoCabecalho;
import br.edu.unifei.cct730.trabalho04.utils.arquivo.ArquivoImagem;
import br.edu.unifei.cct730.trabalho04.utils.histograma.Descritor;
import br.edu.unifei.cct730.trabalho04.utils.histograma.Histograma;
import br.edu.unifei.cct730.trabalho04.utils.imagem.ImagemBinarizada;
import br.edu.unifei.cct730.trabalho04.utils.imagem.ImagemDigitalizada;
import br.edu.unifei.cct730.trabalho04.utils.transformacao.Transformacao;
import br.unifei.edu.cct730.trabalho04.principal.gui.JanelaPrincipal;

/**
 * Classe responsavel por responder a todas as solicitacoes do usuario
 * 
 * @author fknappe
 *
 */
public class ControladorPrincipal extends Controlador {

	// Declaração das variáveis de instância
	private JanelaPrincipal janela = null;
	private ArquivoImagem arquivoImagem = null;
	private ArquivoCabecalho arquivoCabecalho = null;
	private Histograma histograma = null;
	private String caminhoUltimoArquivo = "";

	/**
	 * Construtor
	 * 
	 * @param JanelaPrincipal j
	 */
	public ControladorPrincipal(JanelaPrincipal j) {
		super(j);
		this.janela = j;
	}

	/**
	 * Metodo responsavel por inicializar todas as funcoes do menu
	 * da interface principal
	 * 
	 * @retun void
	 */
	@Override
	public void registraEventos() {
		MyActionListener listener = new MyActionListener();

		janela.getBtnAbrirArquivo().addActionListener(listener);
		janela.getBtnRecarregarArquivo().addActionListener(listener);
		janela.getBtnBinarizar().addActionListener(listener);
		janela.getBtnZoom().addActionListener(listener);
		janela.getBtnSobre().addActionListener(listener);
		janela.getBtnSair().addActionListener(listener);
		janela.getBtnEqualizar().addActionListener(listener);
	}

	/**
	 * Metodo responsavel por tratar as acoes do botao de abrir
	 * o arquivo da imagem
	 * 
	 * @return void
	 */
	public void abrirArquivo() {
		try {
			// Desabilitando todas as acoes ja existentes
			this.desfazerAcoesAnteriores();

			// Escolha do arquivo
			MyFileChooser fileChooser = this.abrirFileChooser();
			int op = fileChooser.lancarOpenDialog(janela);
			String retorno = fileChooser.getArquivoSelecionado(op);

			if(!(retorno.equals(MyFileChooser.OPERACAO_CANCELADA) || 
					retorno.equals(MyFileChooser.OPERACAO_ERRO))
			) {				
				// Definindo o caminho do arquivo para aberturas posteriores
				this.caminhoUltimoArquivo = fileChooser.getSelectedFile().getAbsolutePath();

				// Apresentando a imagem ao usuario
				constroiJanelaImagemDigitalizada();
			}
		} catch(Exception e) {
			e.printStackTrace();
			Mensagem.mostraErro(
					janela, 
					"Falha ao abrir o arquivo!"
			);
		} finally { 
			//Habilitando as acoes do menu 
			janela.habilitarMenu();
		}
	}

	/**
	 * Metodo responsavel por reabrir o arquivo
	 * de imagem
	 * 
	 * @return void
	 */
	public void recarregarArquivo() {
		try {
			// Desfazendo todas as acoes previas do usuario
			this.desfazerAcoesAnteriores();

			// Apresentando a imagem ao usuario
			constroiJanelaImagemDigitalizada();

		} catch(Exception e) {
			e.printStackTrace();
			Mensagem.mostraErro(
					janela, 
					"Falha ao abrir o arquivo!"
			);
		} finally {
			janela.habilitarMenu();
		}
	}

	/**
	 * Metodo responsavel por tratar as acoes do botao de binarizar a imagem
	 * 
	 * @return void
	 */
	public void binarizacao() {
		try {
			// Construir imagem binarizada
			ImagemBinarizada imagem = this.criarImagemBinarizada();
			
			// Apresenta a imagem binarizada
			this.constroiJanelaImagemBinarizada(imagem);

			// Apresenta o histograma para ajustes na imagem
			this.constroiJanelaHistograma(imagem);
			
		} catch(Exception e) {
			e.printStackTrace();
			Mensagem.mostraErro(
					janela, 
			"Falha na leitura do arquivo!");
		} finally {
			janela.getBtnEqualizar().setEnabled(true);
			janela.getBtnZoom().setEnabled(true);
		}
	}

	/**
	 * Metodo responsavel por realizar a equalizacao da imagem binaria
	 * 
	 * @return void
	 */
	/*public void equalizacao() {

		// Verificando se a imagem binarizada existe
		if(existeImagem()) {

			// Inicializando a janela com a imagem equalizada
			final JanelaImagemEqualizada janelaEqualizacao = new JanelaImagemEqualizada(
					new PainelImagem(
							this.descritor.criarImagemEqualizada(this.histograma)
				    )
			);
			lancarFrame(janelaEqualizacao);

		} else {
			Mensagem.mostraErro(
					janela, 
					"Imagem binaria inexistente!"
			);
		}
	}*/

	/**
	 * Metodo responsavel por tratar as acoes do botao de dimensionamento do
	 * imagem binaria
	 * 
	 * @return void
	 */
	public void zoom() {

		// Verificando se a imagem binarizada existe
		if(existeImagem()) {
			final JanelaParametrosZoom jParamZoom = new JanelaParametrosZoom();
			lancarFrame(jParamZoom);

			// Adicionando tratamento as acoes dos botoes da GUI
			jParamZoom.getBtnCancelar().addActionListener(new MyActionListener());
			jParamZoom.getBtnOk().addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent arg0) {

					//Tratamento que realiza o escalamento da imagem
					for(JInternalFrame j : janela.getDesktop().getAllFrames()) {
						if(j instanceof JanelaImagemBinaria) {
							PainelImagem imagem = ((JanelaImagemDigitalizada)j).getPainelImagemDigitalizada();
							Transformacao transformacao = (Transformacao)jParamZoom.getPanelEscalamento().getBean();

							imagem = transformacao.realizarTransformacao(imagem);
							//constroiJanelaImagemDigitalizada(imagem);
							jParamZoom.dispose();
							return;
						}
					}	
				}
			});
		} else {
			Mensagem.mostraErro(
					this.frame, 
					"Imagem binaria inexistente!"
			);
		}
	}

	/**
	 * Metodo responsavel por tratar as acoes do botao que mostra as informacoes
	 * sobre o(s) autor(es) deste projeto
	 * 
	 * @return void
	 */
	public void sobre() {
		Mensagem.mostraMensagemSobre(
				janela,
				"Universidade Federal de Itajuba\n" + 
				"PDI - Processamento de Imagens \n" +
				"Professor: Dr. Edison Oliveira de Jesus\n" +
				"Aluno: Felipe Agostini Knappe - 12623"
		);
	}

	/**
	 * Metodo responsavel por tratar as acoes do botao de finalizar o aplicativo
	 * 
	 * @return void
	 */
	public void sair() {
		if(Mensagem.confirmaMensagem(
				janela, 
				"Deseja realmente sair do programa?"
		)
		) {
			janela.dispose();
			System.exit(0);
		}
	}

	/**
	 * Metodo responsavel por inicializar a janela que 
	 * contem a imagem digitalizada
	 * 
	 * @param PanelImagem panel
	 * 
	 * @return void
	 */
	private JanelaImagemDigitalizada constroiJanelaImagemDigitalizada() throws IOException {
		JanelaImagemDigitalizada jImagemDigitalizada = 
			new JanelaImagemDigitalizada(
					new PainelImagem(
							this.criarImagemDigitalizada()
					)
			);
		janela.getDesktop().add(jImagemDigitalizada);
		try {
			jImagemDigitalizada.setSelected(true);
		} catch (java.beans.PropertyVetoException e){}

		return jImagemDigitalizada;
	}
	
	/**
	 * 
	 * @param ImagemBinarizada im
	 */
	private void constroiJanelaHistograma(ImagemBinarizada im) {
		JanelaHistograma jHistograma = 
			new JanelaHistograma(
				Descritor.controiHistograma(im)
		);
		janela.getDesktop().add(jHistograma);
		try {
			jHistograma.setSelected(true);
		} catch (java.beans.PropertyVetoException e){}
		
		// Tratamento dos eventos do slider
		jHistograma.getSliderLimiar().addChangeListener(new ChangeListener(){

			@Override
			public void stateChanged(ChangeEvent arg0) {}
		});
		
		// Tratamento dos eventos do botao reiniciar
		jHistograma.getBtnReiniciar().addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {}
			
		});
		
		// Tratamento do botão ok
		jHistograma.getBtnOk().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
	}

	/**
	 * Metodo responsavel por inicializar a janela que
	 * contem a imagem binarizada
	 * 
	 * @return void
	 */
	private void constroiJanelaImagemBinarizada(ImagemBinarizada im) throws IOException {
		JanelaImagemBinaria jImagemBinaria = 
			new JanelaImagemBinaria(
					new PainelImagemBinaria(im)
			);
		janela.getDesktop().add(jImagemBinaria);
		try {
			jImagemBinaria.setSelected(true);
		} catch (java.beans.PropertyVetoException e){}
	}

	/**
	 * Metodo responsavel por inicializar a janela que
	 * contem a imagem equalizada
	 *
	 * @return void
	 */
	private JanelaImagemEqualizada constroiJanelaImagemEscalonada() {
		return null;
	}

	/**
	 * Metodo responsavel pela abertura da janela para escolha
	 * do arquivo de imagem
	 * 
	 * @return MyFileChooser
	 */
	private MyFileChooser abrirFileChooser() {
		MyFileChooser fileChooser = new MyFileChooser("Selecione a imagem");
		fileChooser.defineDiretorioInicial(caminhoUltimoArquivo);
		fileChooser.filtro(".img", "Arquivos em nivel de cinza");
		return fileChooser;
	}

	/**
	 * Metodo responsavel por inicializar uma instancia
	 * da imagem digitalizada
	 * 
	 * @return ImagemDigitalizada
	 * @throws IOException
	 */
	private ImagemDigitalizada criarImagemDigitalizada() throws IOException {
		// Abertura do arquivo
		this.abrirArquivoImagem();

		// Leitura das dimensoes da figura do arquivo
		int numeroLinhas = this.getNumeroLinhasImagem();
		int numeroColunas = this.getNumeroColunasImagem();

		// Inicializando a imagem digitalizada
		ImagemDigitalizada imagem = new ImagemDigitalizada(
				numeroLinhas,
				numeroColunas
		);

		// Leitura dos tons de cinza do arquivo 
		Short[][] tonsCinza = 
			this.getTonsCinzaImagem(
					numeroLinhas,
					numeroColunas
			);

		/* 
		 * Adicionando os tons de cinza presentes no arquivo
		 * a imagem digitalizada
		 */
		for(int i = 0; i < numeroLinhas; i++) {
			for(int j = 0; j < numeroColunas; j++) {
				imagem.criarImagem(i, j, tonsCinza[i][j]);
			}
		}

		/*
		 * Finalizando os arquivos da imagem
		 */
		this.fecharArquivoImagem();
		
		return imagem;
	}

	/**
	 * Metodo responsavel por inicializar uma instancia
	 * da imagem binarizada
	 * 
	 * @return ImagemBinarizada
	 * @throws IOException
	 */
	private ImagemBinarizada criarImagemBinarizada() throws IOException {

		// Abertura do arquivo da imagem
		this.abrirArquivoImagem();
		
		// Leitura das dimensoes da figura da imagem
		int numeroLinhas = this.getNumeroLinhasImagem();
		int numeroColunas = this.getNumeroColunasImagem();
		
		// Inicializando a imagem binarizada
		ImagemBinarizada imagem = new ImagemBinarizada(
				numeroLinhas, 
				numeroColunas
		);

		// Obtem todos os tons de cinza presentes na imagem
		Short[][] tonsCinza = 
			this.getTonsCinzaImagem(
					numeroLinhas,
					numeroColunas
			);

		/* 
		 * Adicionando os tons de cinza presentes no arquivo
		 * a imagem binarizada
		 */
		for(int i = 0; i < numeroLinhas; i++) {
			for(int j = 0; j < numeroColunas; j++) {
				imagem.criarImagem(i, j, tonsCinza[i][j]);
			}
		}

		/*
		 * Finalizando os arquivos da imagem 
		 */
		this.fecharArquivoImagem();
		
		return imagem;
	}

	/**
	 * Metodo responsavel por desfazer as acoes anteriores
	 * na reabertura do arquivo
	 * 
	 * @return void
	 */
	private void desfazerAcoesAnteriores() {
		// Finalizando todas as acoes anteriores
		for(JInternalFrame j : janela.getDesktop().getAllFrames()) {
			if(j instanceof JanelaImagemDigitalizada)
				j.dispose();
		}
	}

	/**
	 * Metodo responsavel por realizar a abertura dos arquivos
	 * da imagem
	 * 
	 * @throws IOException
	 */
	private void abrirArquivoImagem() throws IOException {
		// Abertura do arquivo
		arquivoImagem = new ArquivoImagem(caminhoUltimoArquivo);
		arquivoCabecalho = new ArquivoCabecalho(
				arquivoImagem.getAbsolutePath().replace("IMG", "CAB")
		);
	}

	/**
	 * Metodo responsavel por fechar o stream para o arquivo
	 * da imagem
	 * 
	 * @return void
	 * @throws IOException
	 * @throws NullPointerException
	 */
	private void fecharArquivoImagem() throws IOException, NullPointerException {
		arquivoImagem.fecharArquivo();
		arquivoCabecalho.fecharArquivo();
	}

	/**
	 * Metodo que retorna o numero de linhas presentes
	 * no arquivo da imagem
	 * 
	 * @return int
	 * @throws IOException
	 * @throws NullPointerException
	 */
	private int getNumeroLinhasImagem() throws IOException, NullPointerException {
		return arquivoCabecalho.getNumeroLinhas();
	}

	/**
	 * Metodo que retorna o numero de colunas presentes
	 * no arquivo da imagem
	 * 
	 * @return int
	 * @throws IOException
	 * @throws NullPointerException
	 */
	private int getNumeroColunasImagem() throws IOException, NullPointerException {
		return arquivoCabecalho.getNumeroColunas();
	}

	/**
	 * Metodo que retorna todos os tons de cinza presentes
	 * no arquivo da imagem
	 * 
	 * @return Short[][]
	 * @throws IOException
	 */
	private Short[][] getTonsCinzaImagem(int numLinhas, int numColunas) throws IOException, NullPointerException {
		return arquivoImagem.getTonsCinza(
				numLinhas, 
				numColunas
		);
	}

	/**
	 * Metodo responsavel por verificar a existencia da imagem binaria
	 * 
	 * @return boolean
	 */
	private boolean existeImagem() {
		boolean achei = false;

		// Verificando se existe a instancia imagem binaria
		for(JInternalFrame j : janela.getDesktop().getAllFrames()) {
			if(j instanceof JanelaImagemBinaria) {
				achei = true;
			}
		}
		return achei;
	}
}
