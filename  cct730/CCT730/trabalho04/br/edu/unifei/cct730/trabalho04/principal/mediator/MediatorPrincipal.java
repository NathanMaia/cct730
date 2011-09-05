package br.edu.unifei.cct730.trabalho04.principal.mediator;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JInternalFrame;

import br.edu.unifei.cct730.trabalho04.utils.Utils;
import br.edu.unifei.cct730.trabalho04.eventos.MyActionListener;
import br.edu.unifei.cct730.trabalho04.gui.janelas.JanelaHistograma;
import br.edu.unifei.cct730.trabalho04.gui.janelas.JanelaImagemBinaria;
import br.edu.unifei.cct730.trabalho04.gui.janelas.JanelaParametrosZoom;
import br.edu.unifei.cct730.trabalho04.gui.painel.PainelImagemBinaria;
import br.edu.unifei.cct730.trabalho04.padroes.Mediator;
import br.edu.unifei.cct730.trabalho04.utils.ArquivoCabecalho;
import br.edu.unifei.cct730.trabalho04.utils.ArquivoImagem;
import br.edu.unifei.cct730.trabalho04.utils.Descritor;
import br.edu.unifei.cct730.trabalho04.utils.Histograma;
import br.edu.unifei.cct730.trabalho04.utils.Transformacao;
import br.unifei.edu.cct730.trabalho04.principal.gui.JanelaPrincipal;

/**
 * Classe responsavel por responder a todas as solicitacoes do usuario
 * 
 * @author fknappe
 *
 */
public class MediatorPrincipal extends Mediator {

	// Declaração das variáveis de instância
	private JanelaPrincipal janela = null;
	private ArquivoImagem arquivoImagem = null;
	private ArquivoCabecalho arquivoCabecalho = null;
	private Descritor descritor = null;
	private Histograma histograma = null;

	/**
	 * Construtor
	 * 
	 * @param JanelaPrincipal j
	 */
	public MediatorPrincipal(JanelaPrincipal j) {
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
		janela.getBtnSair().addActionListener(listener);
	}

	/**
	 * Metodo responsavel por tratar as acoes do botao de abrir
	 * o arquivo da imagem
	 * 
	 * @return void
	 */
	public void abrirArquivo() {
		try {
			arquivoImagem = new ArquivoImagem("IMAGEM.IMG");
			arquivoCabecalho = new ArquivoCabecalho("IMAGEM.CAB");

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


			for(int i = 0; i < numeroLinhas; i++) {
				for(int j = 0; j < numeroColunas; j++) {
					this.descritor.adicionar(i, j, tonsCinza[i][j]);
				}
			}

			arquivoImagem.fecharArquivo();
			arquivoCabecalho.fecharArquivo();

		} catch(Exception e) {
			e.printStackTrace();
			Utils.mostraErro(
					janela, 
					"Falha ao abrir o arquivo!"
			);
		} finally {
			janela.getBtnHistograma().setEnabled(true);
			Utils.mostraMensagem(
					janela, 
					"Arquivo lido com sucesso!"
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
			this.histograma = descritor.controiHistograma(
					Utils.entradaDeDados(
							"Determine o numero de faixas do histograma: "
					)
			);
			final JanelaHistograma jHistograma = new JanelaHistograma(histograma);
			lancarFrame(jHistograma);

			jHistograma.getBtnFinalizar().addActionListener(new MyActionListener());

		} catch(NumberFormatException e) {
			e.printStackTrace();
			Utils.mostraErro(
					janela,
					"O valor de entrada deve ser um numero inteiro"
			);
		} finally {
			janela.getBtnHistograma().setEnabled(false);
			janela.getBtnBinarizar().setEnabled(true);
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
					(short)Utils.entradaDeDados(
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
			Utils.mostraErro(
					janela, 
			"O valor de entrada deve ser um numero inteiro");
		} finally {
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
		final JanelaParametrosZoom jParamZoom = new JanelaParametrosZoom();
		lancarFrame(jParamZoom);

		jParamZoom.getBtnCancelar().addActionListener(new MyActionListener());
		jParamZoom.getBtnOk().addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				for(JInternalFrame j : janela.getDesktop().getAllFrames()) {
					if(j instanceof JanelaImagemBinaria) {
						PainelImagemBinaria imagem = ((JanelaImagemBinaria)j).getPainelImagem();
						Transformacao transformacao = (Transformacao)jParamZoom.getPanelEscalamento().getBean();

						imagem = transformacao.realizarTransformacao(imagem);
						constroiJanelaImagemBinarizadaEscalonada(imagem);
						jParamZoom.dispose();
						return;
					}
				}	
			}
		});
	}

	/**
	 * Metodo responsavel por tratar as acoes do botao de finalizar o aplicativo
	 * 
	 * @return void
	 */
	public void sair() {
		if(Utils.confirmaMensagem(janela, "Deseja realmente sair do programa?")) {
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
	private void constroiJanelaImagemBinarizadaEscalonada(PainelImagemBinaria panel) {
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
}
