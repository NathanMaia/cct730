package br.edu.unifei.cct730.trabalho04.gui.painel;

import java.awt.Graphics;
import java.util.List;

import br.edu.unifei.cct730.trabalho04.ponto.Ponto;
import br.edu.unifei.cct730.trabalho04.utils.Constantes;
import br.edu.unifei.cct730.trabalho04.utils.formas.Reta;
import br.edu.unifei.cct730.trabalho04.utils.histograma.Histograma;

/**
 * Classe responsavel por implementar a inteface que apresenta
 * o grafico do hsitograma da imagem
 * 
 * @author fknappe
 *
 */
public class PainelHistograma extends javax.swing.JPanel {
	
	// Declaracao das variaveis de instancia 
	private Histograma histograma = null;
	private Ponto origem = null;
	private List<Reta> retas = null;
	private Reta retaLimiar = null;

	/**
	 * Construtor
	 * 
	 * @param Histograma histograma
	 * @param boolean limiar
	 */
	public PainelHistograma(Histograma histograma, boolean limiar) {
		this.histograma = histograma;
		this.origem = new Ponto(18, 210);
		desenharHistograma();
		if (limiar) {
			retaLimiar = new Reta(
					new Ponto(origem.getX() + 128, origem.getY()), 
					new Ponto(origem.getX() + 128, origem.getY() - 200), 
					Constantes.COR_LIMIAR
			);
		}
	}

	/**
	 * Metodo responsavel por desenhar o 
	 * grafico representativo do histograma
	 * 
	 * @return void
	 */
	private void desenharHistograma() {
		// Declaracao das variaveis locais
		int altura = 0;
		
		// Eixo horizontal
		this.retas.add(new Reta(new Ponto(18, 210), new Ponto(273, 210), Constantes.COR_EIXO));
		
		// Eixo vertical
		this.retas.add(
				new Reta(
						new Ponto(18, 10), 
						new Ponto(18, 210), 
						Constantes.COR_EIXO
				)
		);

		// Grafico do histograma
		for (int i = 0; i < histograma.getHistograma().length; i++) {
			altura = (int) Math.round((double) (200 * (double) histograma.getNivelCinza(i) / (double) histograma.getMaiorValor()));
			this.retas.add(
					new Reta(
							new Ponto(origem.getX() + i, origem.getY()), 
							new Ponto(origem.getX() + i, origem.getY() - altura), 
							Constantes.COR_HISTOGRAMA
					)
			);
		}
	}

	/**
	 * Metodo responsavel por plotar o grafico do histograma
	 * 
	 * @return void
	 */
	public void paint(Graphics g) {
		super.paint(g);

		for (Reta reta : retas) {
			for (Ponto p : reta.getPontos()) {
				p.setCor(reta.getCor());
				p.plota(g);
			}
		}

		if (retaLimiar != null) {
			for (Ponto p : retaLimiar.getPontos()) {
				p.setCor(Constantes.COR_LIMIAR);
				p.plota(g);
			}
		}
	}

	/**
	 * Metodo que atualiza a posicao do limiar da imagem
	 * 
	 * @param int limiar
	 * 
	 * @return void
	 */
	public void atualizarLimiar(int limiar) {
		retaLimiar = new Reta(
				new Ponto(origem.getX() + limiar, origem.getY()), 
				new Ponto(origem.getX() + limiar, origem.getY() - 200), 
				Constantes.COR_LIMIAR
		);
		this.repaint();
	}
}
