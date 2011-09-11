package br.edu.unifei.cct730.trabalho04.principal.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JInternalFrame;
import javax.swing.plaf.FileChooserUI;

import br.edu.unifei.cct730.trabalho04.utils.Mensagem;
import br.edu.unifei.cct730.trabalho04.eventos.MyActionListener;
import br.edu.unifei.cct730.trabalho04.gui.componentes.MyFileChooser;
import br.edu.unifei.cct730.trabalho04.gui.janelas.JanelaHistograma;
import br.edu.unifei.cct730.trabalho04.gui.janelas.JanelaImagemBinaria;
import br.edu.unifei.cct730.trabalho04.gui.janelas.JanelaParametrosZoom;
import br.edu.unifei.cct730.trabalho04.gui.painel.PainelImagemBinaria;
import br.edu.unifei.cct730.trabalho04.padroes.Controlador;
import br.edu.unifei.cct730.trabalho04.utils.arquivo.ArquivoCabecalho;
import br.edu.unifei.cct730.trabalho04.utils.arquivo.ArquivoImagem;
import br.edu.unifei.cct730.trabalho04.utils.histograma.Descritor;
import br.edu.unifei.cct730.trabalho04.utils.histograma.Histograma;
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
	private Descritor descritor = null;
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
		janela.getBtnBinarizar().addActionListener(listener);
		janela.getBtnHistograma().addActionListener(listener);
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
			// Finalizando todas as acoes anteriores
			for(JInternalFrame j : janela.getDesktop().getAllFrames()) {
				if(j instanceof JanelaImagemBinaria)
					j.dispose();
			}

			/* 
			 * Inicializando a interface para selecionamento do arquivo desejado
			 */
			MyFileChooser fileChooser = new MyFileChooser("Selecione a imagem");
			fileChooser.defineDiretorioInicial(caminhoUltimoArquivo);
			fileChooser.filtro(".img", "Arquivos em nivel de cinza");
			
			int op = fileChooser.lancarOpenDialog(janela);
			String retorno = fileChooser.getArquivoSelecionado(op);
			
			if(!(retorno.equals(MyFileChooser.OPERACAO_CANCELADA) || 
			     retorno.equals(MyFileChooser.OPERACAO_ERRO))
			 ) {
				arquivoImagem = new ArquivoImagem(fileChooser.getCaminhoArquivo());
				arquivoCabecalho = new ArquivoCabecalho(
						arquivoImagem.getAbsolutePath().replace("IMG", "CAB")
				);

				// Leitura das dimensões da figura do arquivo
				int numeroLinhas = arquivoCabecalho.getNumeroLinhas();
				int numeroColunas = arquivoCabecalho.getNumeroColunas();

				// Inicializando o descritor
				this.descritor = new Descritor(
					numeroLinhas,
					numeroColunas
				);

				// Leitura dos tons de cinza do arquivo 
				Short[][] tonsCinza = arquivoImagem.getTonsCinza(
					numeroLinhas, 
					numeroColunas
				);

				/* 
				 * Adicionando os tons de cinza presentes no arquivo
				 */
				for(int i = 0; i < numeroLinhas; i++) {
					for(int j = 0; j < numeroColunas; j++) {
						this.descritor.adicionar(i, j, tonsCinza[i][j]);
					}
				}
				
				// Definindo o caminho do arquivo para aberturas posteriores
				this.caminhoUltimoArquivo = arquivoImagem.getAbsolutePath();

				/*
				 * Finalizando os arquivos da imagem
				 */
				arquivoImagem.fecharArquivo();
				arquivoCabecalho.fecharArquivo();
				
				/*
				 * Habilitando as acoes do menu 
				 */
				janela.getBtnHistograma().setEnabled(true);
				janela.getBtnBinarizar().setEnabled(false);
				janela.getBtnZoom().setEnabled(false);
			}

		} catch(Exception e) {
			e.printStackTrace();
			Mensagem.mostraErro(
				janela, 
				"Falha ao abrir o arquivo!"
			);
		}
	}

	/**
	 * Metodo responsavel por tratar as acoes do botao de montar o histograma
	 * da imagem
	 * 
	 * @return void
	 */
	public void histograma() {
		try {
			/*
			 * Construcao do histograma da imagem, de acordo com o numero de faixas
			 * especificado 
			 */
			this.histograma = descritor.controiHistograma(
					Mensagem.entradaDeDados(
							"Insira o numero de faixas do histograma: "
					)
			);
			// Inicializando a janela que apresenta os dados do histograma
			final JanelaHistograma jHistograma = new JanelaHistograma(this.histograma);
			lancarFrame(jHistograma);

		} catch(NumberFormatException e) {
			e.printStackTrace();
			Mensagem.mostraErro(
					janela,
					"O valor de entrada deve ser um numero inteiro"
			);
		} finally {
			janela.getBtnHistograma().setEnabled(false);
			janela.getBtnBinarizar().setEnabled(true);
		}
	}

	/**
	 * Metodo responsavel por realizar a equalizacao da imagem binaria
	 * 
	 * @return void
	 */
	public void equalizacao() {
		// Declaracao das variaveis locais
		PainelImagemBinaria imagem = null;
		
		// Verificando se a imagem binarizada existe
		if(existeImagemBinaria()) {
			for(JInternalFrame j : janela.getDesktop().getAllFrames()) {
				if(j instanceof JanelaImagemBinaria) {
					imagem = ((JanelaImagemBinaria)j).getPainelImagem();
				}
			}
			
			// Inicializando a janela com a imagem equalizada
			final JanelaImagemBinaria janelaImagemBinaria = new JanelaImagemBinaria(
					new PainelImagemBinaria(
							this.descritor.getDescritorEqualizado(this.histograma),
							imagem.getLimiar()
				    )
			);
			lancarFrame(janelaImagemBinaria);
			
			// Tratamento da ação de movimento da janela 
			janelaImagemBinaria.getPainelImagem().addMouseListener(new MouseAdapter() {
				public void mouseReleased(MouseEvent e) {

					// Declaracao de variaveis
					int x, y = 0;

					x = e.getX();
					y = e.getY();

					if (x < janelaImagemBinaria.getPainelImagem().getNumeroColunas() && 
							y < janelaImagemBinaria.getPainelImagem().getNumeroLinhas()) {
						janelaImagemBinaria.getPainelImagem().trocaEstadoPosicao(x, y);
						janelaImagemBinaria.getPainelImagem().repaint();
					}
				}
			});	
		} else {
			Mensagem.mostraErro(
					janela, 
					"Imagem binaria inexistente!"
			);
		}
	}

	/**
	 * Metodo responsavel por tratar as acoes do botao de binarizar a imagem
	 * 
	 * @return void
	 */
	public void binarizar() {
		try {
			// Finalizando todas as acoes anteriores
			for(JInternalFrame j : janela.getDesktop().getAllFrames()) {
				if(j instanceof JanelaImagemBinaria)
					j.dispose();
			}

			// Inicializando uma nova imagem binaria
			final JanelaImagemBinaria jImagemBinaria = new JanelaImagemBinaria(
					descritor, 
					(short)Mensagem.entradaDeDados(
							"Insira o valor do limiar de binarizacao: "
					)
			);
			janela.getDesktop().add(jImagemBinaria);
			try {
				jImagemBinaria.setSelected(true);
			} catch (java.beans.PropertyVetoException e){}

			// Tratamento da ação de movimento da janela 
			jImagemBinaria.getPainelImagem().addMouseListener(new MouseAdapter() {
				public void mouseReleased(MouseEvent e) {

					// Declaracao de variaveis
					int x, y = 0;

					/* 
					 * Caputrando os valores do posicionamento
					 * da imagem
					 */
					x = e.getX();
					y = e.getY();

					if (x < jImagemBinaria.getPainelImagem().getNumeroColunas() && 
							y < jImagemBinaria.getPainelImagem().getNumeroLinhas()) {
						jImagemBinaria.getPainelImagem().trocaEstadoPosicao(x, y);
						jImagemBinaria.getPainelImagem().repaint();
					}
				}
			});

		} catch(NumberFormatException e) {
			e.printStackTrace();
			Mensagem.mostraErro(
					janela, 
			"O valor de entrada deve ser um numero inteiro");
		} finally {
			janela.getBtnEqualizar().setEnabled(true);
			janela.getBtnZoom().setEnabled(true);
		}
	}

	/**
	 * Metodo responsavel por tratar as acoes do botao de dimensionamento do
	 * imagem binaria
	 * 
	 * @return void
	 */
	public void zoom() {

		// Verificando se a imagem binarizada existe
		if(existeImagemBinaria()) {
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
							PainelImagemBinaria imagem = ((JanelaImagemBinaria)j).getPainelImagem();
							Transformacao transformacao = (Transformacao)jParamZoom.getPanelEscalamento().getBean();

							imagem = transformacao.realizarTransformacao(imagem);
							constroiJanelaImagemBinarizada(imagem);
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
	 * Metodo responsavel por inicialiar uma nova imagem binaria
	 * escalonada
	 * 
	 * @param PainelImagemBinaria panel
	 * 
	 * @return void
	 */
	private void constroiJanelaImagemBinarizada(PainelImagemBinaria panel) {
		final JanelaImagemBinaria jImagemBinaria = new JanelaImagemBinaria(panel);
		janela.getDesktop().add(jImagemBinaria);
		try {
			jImagemBinaria.setSelected(true);
		} catch (java.beans.PropertyVetoException e){}

		// Tratamento da ação de movimento da janela 
		jImagemBinaria.getPainelImagem().addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent e) {

				// Declaracao de variaveis
				int x, y = 0;

				/*
				 * Capturando o posicionamento
				 * da imagem
				 */
				x = e.getX();
				y = e.getY();

				if (x < jImagemBinaria.getPainelImagem().getNumeroColunas() && 
						y < jImagemBinaria.getPainelImagem().getNumeroLinhas()) {
					jImagemBinaria.getPainelImagem().trocaEstadoPosicao(x, y);
					jImagemBinaria.getPainelImagem().repaint();
				}
			}
		});
	}
	
	/**
	 * Metodo responsavel por verificar a existencia da imagem binaria
	 * 
	 * @return boolean
	 */
	private boolean existeImagemBinaria() {
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
