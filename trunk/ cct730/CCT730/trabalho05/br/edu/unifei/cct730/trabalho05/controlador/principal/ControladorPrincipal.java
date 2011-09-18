package br.edu.unifei.cct730.trabalho05.controlador.principal;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JInternalFrame;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import br.edu.unifei.cct730.trabalho05.gui.componentes.MyFileChooser;
import br.edu.unifei.cct730.trabalho05.eventos.MyActionListener;
import br.edu.unifei.cct730.trabalho05.model.arquivo.Arquivo;
import br.edu.unifei.cct730.trabalho05.model.arquivo.ArquivoCabecalho;
import br.edu.unifei.cct730.trabalho05.model.arquivo.ArquivoImagem;
import br.edu.unifei.cct730.trabalho05.model.arquivo.FactoryArquivo;
import br.edu.unifei.cct730.trabalho05.model.filtro.FactoryFiltro;
import br.edu.unifei.cct730.trabalho05.model.filtro.Filtro;
import br.edu.unifei.cct730.trabalho05.model.filtro.FiltroPassaBaixa;
import br.edu.unifei.cct730.trabalho05.model.filtro.FiltroRuido;
import br.edu.unifei.cct730.trabalho05.model.imagem.FactoryImagem;
import br.edu.unifei.cct730.trabalho05.model.imagem.Imagem;
import br.edu.unifei.cct730.trabalho05.model.imagem.ImagemDigitalizada;
import br.edu.unifei.cct730.trabalho05.model.imagem.ImagemFiltrada;
import br.edu.unifei.cct730.trabalho05.padroes.Controlador;
import br.edu.unifei.cct730.trabalho05.utils.constantes.Constantes;
import br.edu.unifei.cct730.trabalho05.utils.constantes.Mensagem;
import br.edu.unifei.cct730.trabalho05.gui.janelas.FactoryJanelaImagem;
import br.edu.unifei.cct730.trabalho05.gui.janelas.JanelaFiltroPassaBaixa;
import br.edu.unifei.cct730.trabalho05.gui.janelas.JanelaImagem;
import br.edu.unifei.cct730.trabalho05.gui.janelas.JanelaImagemDigitalizada;
import br.edu.unifei.cct730.trabalho05.gui.janelas.JanelaImagemFiltrada;
import br.edu.unifei.cct730.trabalho05.gui.painel.FactoryPainelImagem;
import br.edu.unifei.cct730.trabalho05.gui.painel.PainelImagem;
import br.edu.unifei.cct730.trabalho05.gui.painel.PainelImagemFiltrada;
import br.edu.unifei.cct730.trabalho05.gui.principal.JanelaPrincipal;

/**
 * Classe responsavel por responder a todas as solicitacoes do usuario
 * 
 * @author fknappe
 *
 */
public class ControladorPrincipal extends Controlador {

	// Declaracao das variaveis de instancia
	private JanelaPrincipal janela = null;
	private ArquivoImagem arquivoImagem = null;
	private ArquivoCabecalho arquivoCabecalho = null;
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

		// Adicionando as acoes a todos os botoes do menu
		janela.getBtnAbrirArquivo().addActionListener(listener);
		janela.getBtnRecarregarArquivo().addActionListener(listener);
		janela.getBtnSobre().addActionListener(listener);
		janela.getBtnSair().addActionListener(listener);
		janela.getBtnFiltrar().addActionListener(listener);
	}

	/**
	 * Metodo responsavel por tratar as acoes do botao de abrir
	 * o arquivo da imagem
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
			fileChooser = this.abrirFileChooser();
			op = fileChooser.lancarOpenDialog(janela);
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
				 * Incializando a imagem com o filtro de 
				 * ruido de sal 
				 */
				this.inicializarImagemFiltradaPorRuido(
						FiltroRuido.RUIDO_SAL, 
						this.getImagemDigitalizada()
				);

				/*
				 * Inicializando a imagem com o filtro de
				 * ruido de pimenta 
				 */
				this.inicializarImagemFiltradaPorRuido(
						FiltroRuido.RUIDO_PIMENTA,
						this.getImagemDigitalizada()
				);

				/*
				 * Incializando a imagem com os filtros de
				 * ruido de sal e pimenta simultaneamente
				 */
				this.inicializarImagemFiltradaPorRuido(
						FiltroRuido.RUIDO_SAL_PIMENTA, 
						this.getImagemDigitalizada()	
				);

				/*
				 *  Garantindo que o arquivo da imagem foi finalizado
				 */
				this.fecharArquivoImagem();
			}
		} catch(IOException e) {
			e.printStackTrace();
			Mensagem.mostraErro(
					janela, 
					"Falha na abertura do arquivo!"
			);
		} catch(NumberFormatException e) {
			e.printStackTrace();
			Mensagem.mostraErro(
					janela, 
					"Numero deve ser inteiro"
			);
		} catch(NullPointerException e) {
			e.printStackTrace();
			Mensagem.mostraErro(
					janela, 
					"Selecione o tipo de ruido antes de filtra-lo"
			);

		} catch(IllegalArgumentException e) {  
			e.printStackTrace();
			Mensagem.mostraErro(
					janela, 
					e.getMessage()
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

		} catch(NumberFormatException e) {
			e.printStackTrace();
			Mensagem.mostraErro(
					janela, 
					"Numero deve ser inteiro"
			);

		} catch(IllegalArgumentException e) {
			e.printStackTrace();
			Mensagem.mostraErro(
					janela, 
					e.getMessage()
			);

		} finally { 
			//Habilitando as acoes do menu 
			janela.habilitarMenu();
		}
	}

	/**
	 * Metodo responsavel por aplicar o filtro
	 * selecionado no combo sobre a imagem
	 * digitalizada
	 * 
	 * @return void
	 */
	public void filtrar() {

		try {
			
			// Inicializacao da janela que define o tamanho da matriz de convolucao
			final JanelaFiltroPassaBaixa jPassaBaixa = 
				new JanelaFiltroPassaBaixa("Dimensao da matriz de convolucao");
			lancarFrame(jPassaBaixa);

			// Tratamento para os eventos do botao Cancelar
			jPassaBaixa.getBtnCancelar().addActionListener(new MyActionListener());
			
			// Tratamento para os eventos do botao OK
			jPassaBaixa.getBtnOk().addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					//Declaracao das variaveis
					ImagemFiltrada imFiltrada = null;
					
					// Selecionando o tipo de filtro
					int filtro = (int)janela.getCmbFiltros().getSelectedIndex();

					/*
					 *  Aplicando o filtro sobre a imagem
					 */
					for(JInternalFrame j : janela.getDesktop().getAllFrames()) {
						ImagemDigitalizada im = 
							((ImagemDigitalizada)((JanelaImagem)j).getPainelImagem().getImagem());
						FiltroPassaBaixa passaBaixa = 
							(FiltroPassaBaixa)FactoryFiltro.create(Filtro.FILTRO_PASSABAIXA, im);
						imFiltrada = passaBaixa.filtrar(filtro);
						((JanelaImagem)j).getPainelImagem().constroiImagem(imFiltrada.getTabelaPontos());
					}
				}
			});
			
			// Tratamento dos eventos do slider
			jPassaBaixa.getSliderMatriz().addChangeListener(new ChangeListener() {
				
				@Override
				public void stateChanged(ChangeEvent e) {
					JSlider slider = (JSlider) e.getSource();
					jPassaBaixa.getLblTamanhoMatriz().setText((((int) slider.getValue()) * 2 + 3) + "x" + (((int) slider.getValue()) * 2 + 3));
				}
			});

		} catch(IllegalArgumentException e) {
			e.printStackTrace();
			Mensagem.mostraErro(
					janela, 
					e.getMessage()
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
	 * Metodo responsavel por tratar as acoes do botao 
	 * de finalizar o aplicativo
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

	/* ****************************************************************************************************************************************************************** 
	 * 															Inicio dos metodos auxiliares (todos privados)
	 *********************************************************************************************************************************************************************/

	/**
	 * Metodo responsavel por inicializar todas as 
	 * imagens digitalizadas do projeto
	 * 
	 * @return void
	 */
	private void inicializarImagemDigitalizada() throws IOException, IllegalArgumentException {

		// Declaracao das variaveis locais
		int numeroLinhas, numeroColunas = 0;
		Short[][] tonsDeCinza = null;

		/*
		 * Inicializando as variaveis locais
		 */
		numeroLinhas = this.arquivoCabecalho.getNumeroLinhas();
		numeroColunas = this.arquivoCabecalho.getNumeroColunas();
		tonsDeCinza = arquivoImagem.getTonsCinza(numeroLinhas, numeroColunas);

		/*
		 * Inicializando a imagem digitalizada
		 */
		JanelaImagemDigitalizada j = 
			(JanelaImagemDigitalizada)FactoryJanelaImagem.create(
					JanelaImagem.IMAGEM_DIGITALIZADA,
					FactoryPainelImagem.create(
							PainelImagem.IMAGEM_DIGITALIZADA, 
							FactoryImagem.create(
									Imagem.IMAGEM_DIGITALIZADA, 
									numeroLinhas, 
									numeroColunas
							).constroiImagem(tonsDeCinza)
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
	 * Metodo responsavel por inicializar uma imagem filtrada,
	 * de acordo com o tipo de filtro especificado
	 * 
	 * @param int tipo
	 * @param ImagemDigitalizada imagem
	 * 
	 * @return void
	 * @throws IOException
	 * @throws NumberFormatException
	 * @throws IllegalArgumentException
	 */
	private void inicializarImagemFiltradaPorRuido(int tipo, ImagemDigitalizada imagem) throws IOException, NumberFormatException, IllegalArgumentException {

		// Declaracao de variaveis locais
		FiltroRuido filtro = null;

		// Inicializando o filtro
		filtro = (FiltroRuido)FactoryFiltro.create(
				Filtro.FILTRO_RUIDO, 
				imagem
		);

		/*
		 *  Definindo a porcentagem de incidencia do filtro
		 */
		if(tipo == FiltroRuido.RUIDO_SAL) { 
			filtro.setPorcentagemSal(this.porcentagemRuido(Constantes.FILTROS_RUIDOS[0]));
		} else if(tipo == FiltroRuido.RUIDO_PIMENTA) {
			filtro.setPorcentagemPimenta(this.porcentagemRuido(Constantes.FILTROS_RUIDOS[1]));
		} else if(tipo == FiltroRuido.RUIDO_SAL_PIMENTA) {
			filtro.setPorcentagemSal(this.porcentagemRuido(Constantes.FILTROS_RUIDOS[0]));
			filtro.setPorcentagemPimenta(this.porcentagemRuido(Constantes.FILTROS_RUIDOS[1]));
		}

		/*
		 *  Inicializando o painel com a imagem filtrada
		 */
		PainelImagemFiltrada pImagemFiltrada = 
			(PainelImagemFiltrada)FactoryPainelImagem.create(
					PainelImagem.IMAGEM_FILTRADA,
					filtro.filtrar(
							tipo
					)
			);

		// Inicializando a janela interna que contem a imagem
		JanelaImagemFiltrada j = 
			(JanelaImagemFiltrada)FactoryJanelaImagem.create(
					JanelaImagem.IMAGEM_FILTRADA, 
					pImagemFiltrada
			);

		/*
		 *  Adicionando a janela interna ao desktop
		 *  e tornando-a visivel
		 */
		lancarJanelaImagem(j);
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
			j.dispose();
		}
	}

	/**
	 * Metodo responsavel por obter a imagem digitalizada
	 * contida no desktop do aplicativo
	 * 
	 * @return ImagemDigitalizada
	 */
	private ImagemDigitalizada getImagemDigitalizada() {

		// Declaracao das variaveis locais
		ImagemDigitalizada imDigitalizada = null;

		// Obtendo a imagem original dentre todas contidas no desktop
		for(JInternalFrame j : janela.getDesktop().getAllFrames()) {
			if(j instanceof JanelaImagemDigitalizada) {
				imDigitalizada = (ImagemDigitalizada)((JanelaImagemDigitalizada)j).getPainelImagem().getImagem();
				break;
			}
		}

		return imDigitalizada;
	}

	/**
	 * Metodo que retorna a porcentagem
	 * de ruido para realizar a filtragem
	 * 
	 * @param tipo
	 * @return int
	 * @throws NumberFormatException
	 */
	private int porcentagemRuido(String tipo) throws NumberFormatException {

		// Declaracao de variaveis locais
		int porcentagem = 0;

		// Entrada de dados
		porcentagem = Mensagem.entradaDeDados(
				"Determine a porcentagem do " + tipo + ": "
		);

		if((porcentagem <=  0) && (porcentagem >= 100))
			throw new NumberFormatException(
					"Entre com um valor entre 0 - 100."
			);

		return porcentagem; 
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
		fileChooser = new MyFileChooser("Selecione a imagem");

		// Setando o diretorio local como inicial
		fileChooser.defineDiretorioInicial(caminhoUltimoArquivo);

		/*
		 *  Filtrando os arquivos a serem abertos somente para 
		 *  imagens em nivel de cinza
		 */
		fileChooser.filtro(".img", "Arquivos em nivel de cinza");
		return fileChooser;
	}

	/**
	 * Metodo responsavel por realizar a abertura dos arquivos
	 * da imagem
	 * 
	 * @throws IOException
	 */
	private void abrirArquivoImagem() throws IOException, IllegalArgumentException {

		// Abertura do arquivo
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
	 * Metodo responsavel por fechar o stream para o arquivo
	 * da imagem
	 * 
	 * @return void
	 * @throws IOException
	 * @throws NullPointerException
	 */
	private void fecharArquivoImagem() throws IOException, NullPointerException {
		// Finalizando o stream com os arquivos
		if(!this.arquivoImagem.isFechado()) {
			arquivoImagem.fecharArquivo();
			arquivoCabecalho.fecharArquivo();
		}
	}
}
