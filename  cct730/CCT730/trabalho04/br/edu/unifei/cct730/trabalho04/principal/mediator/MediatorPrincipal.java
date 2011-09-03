package br.edu.unifei.cct730.trabalho04.principal.mediator;

import br.edu.unifei.cct730.trabalho03.utils.Utils;
import br.edu.unifei.cct730.trabalho04.eventos.MyActionListener;
import br.edu.unifei.cct730.trabalho04.gui.janelas.JanelaHistograma;
import br.edu.unifei.cct730.trabalho04.gui.janelas.JanelaImagemBinaria;
import br.edu.unifei.cct730.trabalho04.padroes.Mediator;
import br.edu.unifei.cct730.trabalho04.utils.ArquivoCabecalho;
import br.edu.unifei.cct730.trabalho04.utils.ArquivoImagem;
import br.edu.unifei.cct730.trabalho04.utils.Descritor;
import br.edu.unifei.cct730.trabalho04.utils.Histograma;
import br.unifei.edu.cct730.trabalho04.principal.gui.JanelaPrincipal;

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
	 * @param j
	 */
	public MediatorPrincipal(JanelaPrincipal j) {
		super(j);
		this.janela = j;
	}

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
	 * 
	 */
	public void abrirArquivo() {
		try {
			arquivoImagem = new ArquivoImagem("IMAGEM.IMG");
			arquivoCabecalho = new ArquivoCabecalho("IMAGEM.CAB");
			
			int numeroLinhas = arquivoCabecalho.getNumeroLinhas();
			int numeroColunas = arquivoCabecalho.getNumeroColunas();
			
			this.descritor = new Descritor(
					numeroLinhas,
					numeroColunas
			);
			
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
		}
	}

	/**
	 * 
	 */
	public void histograma() {
		try {
			descritor.controiHistograma(
					Utils.entradaDeDados(
							"Determine o numero de faixas do histograma: "
					)
			);
			final JanelaHistograma jHistograma = new JanelaHistograma(histograma);
			lancarFrame(jHistograma);
		} catch(NumberFormatException e) {
			e.printStackTrace();
			Utils.mostraErro(
					janela,
					"O valor de entrada deve ser um numero inteiro"
			);
		}
	}

	/**
	 * 
	 */
	public void binarizar() {
		try {
			final JanelaImagemBinaria jImagemBinaria = new JanelaImagemBinaria(
					descritor, 
					histograma, 
					(short)Utils.entradaDeDados(
							"Insira o valor do limiar de binarizacao: "
					)
			);
			lancarFrame(jImagemBinaria);

		} catch(NumberFormatException e) {
			e.printStackTrace();
			Utils.mostraErro(
					janela, 
			"O valor de entrada deve ser um numero inteiro");
		}
	}

	/**
	 * 
	 */
	public void zoom() {

	}

	/**
	 * 
	 */
	public void sair() {
		if(Utils.confirmaMensagem(janela, "Deseja realmente sair do programa?")) {
			janela.dispose();
			System.exit(0);
		}
	}
}
