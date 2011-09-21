package br.edu.unifei.cct730.trabalho06.compressao.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import br.edu.unifei.cct730.trabalho03.gui.janelas.JanelaImagemSintetica;
import br.edu.unifei.cct730.trabalho03.gui.painel.PainelDesenho;
import br.edu.unifei.cct730.trabalho03.utils.Mensagem;
import br.edu.unifei.cct730.trabalho06.compressao.gui.JanelaCompressao;
import br.edu.unifei.cct730.trabalho06.eventos.MyActionListener;
import br.edu.unifei.cct730.trabalho06.padroes.Controlador;

/**
 * 
 * @author fknappe
 *
 */
public class ControladorJanelaCompressao extends Controlador {

	// Declaracao das variaveis de instancia
	private JanelaImagemSintetica janelaImagem;
	
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
		
		// Inicializando um novo listener
		MyActionListener myListener = new MyActionListener();
		
		// Tratamento das acoes dos botoes do menu principal
		((JanelaCompressao)this.frame).getBtnAbrirPanel().addActionListener(myListener);
		((JanelaCompressao)this.frame).getBtnComprimir().addActionListener(myListener);
		((JanelaCompressao)this.frame).getBtnSobre().addActionListener(myListener);
		((JanelaCompressao)this.frame).getBtnSair().addActionListener(myListener);
	}
	
	/**
	 * Metodo responsavel por instanciar um painel para criacao
	 * das imagens sinteticas
	 * 
	 * @param void
	 * @return void
	 */
	public void abrirPanel() {
		// Verificando se ja existe uma imagem sintetica em aberto
		if(janelaImagem != null) {
			janelaImagem.dispose();
		}

		try {
			// Inicializando uma nova imagem sintetica
			janelaImagem = new JanelaImagemSintetica();
			PainelDesenho panelDesenho = new PainelDesenho(
					this.retornaValorAltura(), 
					this.retornaValorLargura()
			);
			janelaImagem.setPanelDesenho(panelDesenho);
			lancarFrame(janelaImagem);

			//Tratamento das acoes dos botoes da janela que contem a imagem sintetica
			janelaImagem.getBtnSair().addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					janelaImagem.dispose();
					janelaImagem = null;
					((JanelaCompressao)frame).desabilitaFuncoesMenu();
				}
			});
		} catch(NumberFormatException e) {
			e.printStackTrace();
			Mensagem.mostraErro(
					((JanelaCompressao)this.frame), 
					"Numero deve ser um valor inteiro"
			);
		} finally {
			((JanelaCompressao)this.frame).habilitaFuncoesMenu();
		}
	}
	
	/**
	 * 
	 */
	public void comprimirImagem() {
		
	}
	
	/**
	 * Metodo responsavel por receber o valor da altura
	 * das celulas do editor de imagem sintetica
	 * 
	 * @param void
	 * @return int
	 */
	private int retornaValorAltura() throws NumberFormatException {
		int altura = 0;

		altura = Mensagem.entradaDeDados("Determine o valor da altura: ");
		return altura;
	}

	/**
	 * Metodo responsavel por receber o valor da largura
	 * das celulas do editor de imagem sintetica
	 * 
	 * @param void
	 * @return int
	 */
	private int retornaValorLargura() throws NumberFormatException {
		int largura = 0;

		largura = Mensagem.entradaDeDados("Determine o valor da largura: ");
		return largura;
	}
}
