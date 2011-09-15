package br.edu.unifei.cct730.trabalho04.utils.histograma;

import java.util.List;
import java.util.Map;

import br.edu.unifei.cct730.trabalho04.utils.ponto.Ponto;
import br.edu.unifei.cct730.trabalho04.utils.imagem.ImagemDigitalizada;

/**
 * Classe responsavel por realizar operacoes
 * sobre as imagens digitalizadas
 * 
 * @author fknappe
 *
 */
public class OperacoesImagem {
	
	/**
	 * Metodo responsavel pela construcao do histograma da 
	 * imagem
	 * 
	 * @param int numeroFaixas
	 * 
	 * @return Histograma
	 */
	public static Histograma constroiHistograma(ImagemDigitalizada imagem) {
		
		// Declaracao das variaveis locais
		Histograma histograma = null;
		int nivelCinza = 0;
		
		histograma = new Histograma();
		
		for (Map.Entry<Short, List<Ponto>> entrada: imagem.getTabelaPontos().entrySet()) {
			nivelCinza = entrada.getKey();
			for (int i = 0; i < entrada.getValue().size(); i++) 
				histograma.acrescentar(nivelCinza);
		}
		
		histograma.calcularPorcentagens();
		return histograma;
	}
	
	/**
	 * Metodo responsavel por realizar a equalizacao da imagem
	 * digitalizada e retornar o mapeamento
	 * 
	 * @param Histograma h
	 * 
	 * @return int[]
	 */
	public static int[] constroiMapeamentoEqualizacao(
			Histograma h
		) {
		
		// Declaracao das variaveis locais
		double[] funcaoDeTransformacao = new double[255];
		double[] faixaDeValores = new double[255];
		int[] mapeamento = new int[255];
		int fatorDeEqualizacao = 0;
		double diferencaInicio = 0.0;
		double diferencaProx = 0.0;
		
		//Calculo das tranformacoes
		funcaoDeTransformacao[0] = h.getPorcentagens()[0];
		for (int i = 1; i < h.getPorcentagens().length; i++) {
			funcaoDeTransformacao[i] = funcaoDeTransformacao[i - 1] + h.getPorcentagens()[i];
		}
		
		//Calcula das novas faixas de valores do histograma
		for (int i = 0; i < 255; i++) {
			faixaDeValores[i] = ((double) i / (double) 255.0) * 100.0;
		}
		
		//Mapeia os niveis de cinza entre as novas faixas de valores
		for (int i = 0; i < 255; i++) {
			diferencaInicio = Math.abs(funcaoDeTransformacao[i] - faixaDeValores[0]);
			fatorDeEqualizacao = 0;
			for (int j = 0; j < faixaDeValores.length; j++) {
				//Subtrai o valor da transformacao da faixa de valor
				diferencaProx = Math.abs(funcaoDeTransformacao[i] - faixaDeValores[j]);
				if (diferencaProx < diferencaInicio) {					
					fatorDeEqualizacao = j;
					diferencaInicio = diferencaProx;
				}
			}
			
			//Novo mapeamento
			mapeamento[i] = fatorDeEqualizacao;			
		}
		
		return mapeamento;
	}
}
