package br.edu.unifei.cct730.trabalho03.principal.mediator;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import br.edu.unifei.cct730.trabalho03.eventos.MyActionListener;
import br.edu.unifei.cct730.trabalho03.eventos.PanelDesenhoListener;
import br.edu.unifei.cct730.trabalho03.gui.janelas.JanelaImagemSintetica;
import br.edu.unifei.cct730.trabalho03.gui.painel.PanelDesenho;
import br.edu.unifei.cct730.trabalho03.principal.gui.JanelaPrincipal;
import br.edu.unifei.cct730.trabalho03.utils.MatrizCelula;
import br.edu.unifei.cct730.trabalho03.utils.Transformacao;
import br.edu.unifei.cct730.trabalho03.utils.Utils;
import br.edu.unifei.cct730.trabalho03.padroes.Mediator;

/**
 * Classe responsavel por intermediar as a��es
 * da JanelaPrincipal com o restante da aplicacao
 * 
 * @author fknappe
 *
 */
public class MediatorPrincipal extends Mediator {
	
	//Declaracao das variaveis de instancia
	private JanelaPrincipal janela = null;
	private JanelaImagemSintetica janelaImagem = null;
	
	/**
	 * Construtor 
	 * @param JanelaPrincipal j
	 */
	public MediatorPrincipal(JanelaPrincipal j) {
		super(j);
		this.janela = j;
	}
	
	/**
	 * Metodo responsavel por registrar todas as acoes da GUI
	 * 
	 * @return void
	 */
	@Override
	public void registraEventos() {
		
		// Inicializando o listener para captar os eventos da janela
		MyActionListener myListener = new MyActionListener();
		
		// Registrando as a��es dos boto�es da GUI
		janela.getBtnAbrirPanel().addActionListener(myListener);
		janela.getBtnEscalamento().addActionListener(myListener);
		janela.getBtnEspelhamentoH().addActionListener(myListener);
		janela.getBtnEspelhamentoV().addActionListener(myListener);
		janela.getBtnRotacao().addActionListener(myListener);
		janela.getBtnTranslacao().addActionListener(myListener);
		janela.getBtnFinalizar().addActionListener(myListener);
	}
	
	/**
	 * Metodo responsavel por criar um painel para criacao
	 * das imagens sinteticas
	 * 
	 * @param void
	 * @return void
	 */
	public void abrirPanel() {
		if(janelaImagem != null) {
			janelaImagem.dispose();
		}
		janelaImagem = new JanelaImagemSintetica();
		PanelDesenho panelDesenho = new PanelDesenho(
				this.retornaValorAltura(), 
				this.retornaValorLargura()
		);
		janelaImagem.setPanelDesenho(panelDesenho);
		lancarFrame(janelaImagem);
		
		janelaImagem.getBtnSair().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				janelaImagem.dispose();
				janelaImagem = null;
			}
		});
	}
	
	/**
	 * Metodo responsavel por tratar as operacoes de rotacao
	 * sobre a imagem sintetica
	 * 
	 * @param void
	 * @return void
	 */
	public void rotacao() {
		if(validaTransformacao()){
			this.atualizaImagemSintetica(
				this.getTransformacao(janelaImagem.getPanelDesenho().getMatriz()).rotaciona(
					this.retornaValorTransformacao("Determine o angulo de rota��o: "), 5, 5
				)
			);
		}
	}
	
	/**
	 * Metodo responsavel por tratar as operacoes de translacao
	 * sobre a imagem sintetica
	 * 
	 * @param void
	 * @return void
	 */
	public void translacao() {
		if(validaTransformacao()){
			this.atualizaImagemSintetica(
				this.getTransformacao(janelaImagem.getPanelDesenho().getMatriz()).translada(
					this.retornaValorTransformacao("Determine o valor do deslocamento em X: "), 
					this.retornaValorTransformacao("Determine o valor do deslocamento em Y: ")
				)
			);
		}
	}
	
	/**
	 * Metodo responsavel por tratar as operacoes de escala
	 * sobre a imagem sintetica
	 * 
	 * @param void
	 * @return void
	 */
	public void escalamento() {
		if(validaTransformacao()){
			this.atualizaImagemSintetica(
				this.getTransformacao(janelaImagem.getPanelDesenho().getMatriz()).escala(
					this.retornaValorTransformacao("Determine o fator de escala: ")
				)
			);
		}
	}
	
	/**
	 * Metodo responsavel por tratar as operacoes de
	 * espelhamento horizontal
	 * 
	 * @param void
	 * @return void
	 */
	public void espelhamentoHorizontal() {
		if(validaTransformacao()) {
			this.atualizaImagemSintetica(
				this.getTransformacao(janelaImagem.getPanelDesenho().getMatriz()).espelhamentoHorizontal()
			);
		}
	}
	
	/**
	 * Metodo responsavel por tratar as operacoes de
	 * espelhamento vertical
	 * 
	 * @param void
	 * @return void
	 */
	public void espelhamentoVertical() {
		if(validaTransformacao()) {
			this.atualizaImagemSintetica(
				this.getTransformacao(janelaImagem.getPanelDesenho().getMatriz()).espelhamentoVertical()
			);
		}
	}
	
	/**
	 * M�todo respons�vel por finalizar o aplicativo
	 * 
	 * @param  void
	 * @return void
	 */
	public void sair() {
		if(Utils.confirmaMensagem(janela, "Deseja realmente sair do programa?")) {
			janela.dispose();
			System.exit(0);
		}
	}
	
	/**
	 * Metodo responsavel por receber o valor da altura
	 * das celulas do editor de imagem sintetica
	 * 
	 * @param void
	 * @return int
	 */
	private int retornaValorAltura() {
		int altura = 0;
		try{
			altura = Utils.entradaDeDados("Determine o valor da altura: ");
		} catch(NumberFormatException e) {
			e.printStackTrace();
			Utils.mostraErro(
					this.janela, 
					"O valor de entrada deve ser um numero inteiro");
		}
		
		return altura;
	}
	
	/**
	 * Metodo responsavel por receber o valor da largura
	 * das celulas do editor de imagem sintetica
	 * 
	 * @param void
	 * @return int
	 */
	private int retornaValorLargura() {
		int largura = 0;
		try{
			largura = Utils.entradaDeDados("Determine o valor da largura: ");
		} catch(NumberFormatException e) {
			e.printStackTrace();
			Utils.mostraErro(
					this.janela, 
					"O valor de entrada deve ser um numero inteiro");
		}
		
		return largura;
	}
	
	/**
	 * Metodo responsavel por receber o valor do deslocamento
	 * das celulas do editor de imagem sintetica
	 * 
	 * @param mensagem
	 * @return int
	 */
	private int retornaValorTransformacao(String mensagem) {
		int transformacao = 0;
		try{
			transformacao = Utils.entradaDeDados(mensagem);
		} catch(NumberFormatException e) {
			e.printStackTrace();
			Utils.mostraErro(
					this.janela, 
					"O valor de entrada deve ser um numero inteiro");
		}
		
		return transformacao;
	}
	
	/**
	 * Metodo responsavel por atualizar o posicionamento da
	 * imagem sintetica no painel
	 * 
	 * @param void
	 * @return void
	 */
	private void atualizaImagemSintetica(int[][] matrizTransformacao) {
		int altura = janelaImagem.getPanelDesenho().getMatriz().getNumLinhas();
		int largura = janelaImagem.getPanelDesenho().getMatriz().getNumColunas();
		PanelDesenho panelDesenho = new PanelDesenho(
				altura, 
				largura,
				matrizTransformacao
		);
		janelaImagem.dispose();
		janelaImagem = null;
		janelaImagem = new JanelaImagemSintetica();
		janelaImagem.setPanelDesenho(panelDesenho);
		lancarFrame(janelaImagem);
		
		janelaImagem.getBtnSair().addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				janelaImagem.dispose();
				janelaImagem = null;
			}
		});
		janela.getDesktop().repaint();
	}
	
	/**
	 * Metodo responsavel por criar a transformacao sobre a matriz
	 * de celulas da imagem sintetica
	 * 
	 * @param MatrizCelula matriz
	 * @return Transformacao
	 */
	private Transformacao getTransformacao(MatrizCelula matriz) {
		Transformacao transformacao = new Transformacao(matriz);
		return transformacao;
	}
	
	/**
	 * Metodo responsavel por validar a existencia de uma imagem
	 * sintetica para realizar as transformacoes
	 * 
	 * @param void
	 * @return boolean
	 */
	
	private boolean validaTransformacao() {
		if(janelaImagem == null) {
			Utils.mostraErro(janela, "N�o � poss�vel realizar a trasforma��o (nenhuma imagem sint�tica foi selecionada).");
			return false;
		}
		return true;
	}
}
