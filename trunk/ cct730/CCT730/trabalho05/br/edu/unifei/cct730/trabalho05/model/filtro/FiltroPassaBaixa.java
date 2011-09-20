package br.edu.unifei.cct730.trabalho05.model.filtro;

import br.edu.unifei.cct730.trabalho05.model.imagem.Imagem;
import br.edu.unifei.cct730.trabalho05.model.imagem.ImagemDigitalizada;
import br.edu.unifei.cct730.trabalho05.model.imagem.ImagemFiltrada;
import br.edu.unifei.cct730.trabalho05.utils.operadores.OperacaoConvolucao;
import br.edu.unifei.cct730.trabalho05.utils.operadores.OperacaoMatematica;

/**
 * Classe responsavel pelas acoes dos filtros
 * do tipo passa baixa
 * 
 * @author fknappe
 *
 */
public class FiltroPassaBaixa extends Filtro {

	// Constantes
	public static final int PASSABAIXA_MEDIA = 0;
	public static final int PASSABAIXA_MEDIANA = 1;
	
	// Declaracao das variaveis de instancia
	Short[][] matrizConvolucao = null;
	
	/**
	 * Construtor
	 * 
	 * @param ImagemFiltrada im
	 */
	public FiltroPassaBaixa(Imagem im) {
		super(im);
	}

	/**
	 * Metodo responsavel por escolher o tipo
	 * da filtragem da imagem digitalizada
	 * 
	 * @param int tipoFiltro
	 * 
	 * @return Imagem
	 */
	@Override
	public ImagemFiltrada filtrar(int tipoFiltro) throws IllegalArgumentException {

		// Escolha do tipo de filtragem
		switch(tipoFiltro) {
			case PASSABAIXA_MEDIA:
				return this.filtroMedia();
				
			case PASSABAIXA_MEDIANA:
				return this.filtroMediana();
				
			default:
				throw new IllegalArgumentException("Tipo de filtro inexistente!");
		}
	}
	
	/**
	 * Metodo responsavel por aplicar o filtro
	 * passa baixa pela media
	 * 
	 * @return Imagem
	 */
	private ImagemFiltrada filtroMedia() {
		
		// Declaracao de variaveis locais
		ImagemFiltrada imFiltrada = null;
		int soma = 0;
		int meioTemplateX, meioTemplateY = 0;
		int numLinhas, numColunas = 0;
		int linhasFiltro, colunasFiltro = 0;
		double multiplicacao, valorForaDaMatriz = 0.0;
		Short[][] tonsDeCinza = null;
		
		// Linhas e colunas da imagem
		numLinhas = this.imagem.getNumeroLinhas();
		numColunas = this.imagem.getNumeroColunas();
		
		// Obtendo a matriz de tons de cinza da imagem
		tonsDeCinza = this.imagem.getTonsDeCinzaImagem();
		
		// Tamanho da matriz de convolucao
		linhasFiltro = matrizConvolucao[0].length;
		colunasFiltro = matrizConvolucao.length;
		
		// Meio do filtro
		meioTemplateX = matrizConvolucao[0].length / 2;
		meioTemplateY = matrizConvolucao.length / 2;
		
		// Instancia a nova imagem filtrada
		imFiltrada = new ImagemFiltrada(numLinhas, numColunas);
		
		// Varre a matriz imagem
		for (int i = 0; i < this.imagem.getNumeroLinhas(); i++) {
			for (int j = 0; j < this.imagem.getNumeroColunas(); j++) {
				soma = 0;
				
				// Posiciona o filtro
				for (int k = i - meioTemplateX, s = 0; k <= i + meioTemplateX; k++, s++) {
					for (int m = j - meioTemplateY, t = 0; m <= j + meioTemplateY; m++, t++) {
						
						// Realiza a multiplicacao
						if (k < 0 || m < 0 || k > numLinhas - 1 || m > numColunas - 1) {
							multiplicacao = OperacaoMatematica.multiplicar((int) valorForaDaMatriz, (int) matrizConvolucao[s][t]);
						} else {
							multiplicacao = OperacaoMatematica.multiplicar((int)tonsDeCinza[k][m], (int) matrizConvolucao[s][t]);
						}
						
						// Soma os valores multiplicados
						soma += multiplicacao;
					}
				}
				// Insere o novo valor na imagem filtrada
				imFiltrada.criarImagem(i, j, new Integer(soma / (colunasFiltro * linhasFiltro)).shortValue());
			}
		}
		
		return imFiltrada;
	}

	/**
	 * Metodo responsavel por aplicar o filtro
	 * passa baixa pela mediana
	 * 
	 * @return Imagem
	 */
	private ImagemFiltrada filtroMediana() {
		
		// Declaracao de variaveis locais
		ImagemFiltrada imFiltrada = null;
		int[] vetorVizinho = null;
		Short[][] tonsDeCinza = null;
		int colunasFiltro, linhasFiltro = 0;
		double valorForaDaMatriz = 0.0;
		int count = 0;
		
		// Obtendo a matriz de niveis de cinza da imagem
		tonsDeCinza = this.imagem.getTonsDeCinzaImagem();
		
		// Tamanho da matriz de convolucao
		linhasFiltro = matrizConvolucao[0].length;
		colunasFiltro = matrizConvolucao.length;
		
		// Vetor que guarda os vizinhos do ponto de analise
		vetorVizinho = new int[colunasFiltro * linhasFiltro];
		
		// Inicializando a imagem filtrada
		imFiltrada = new ImagemFiltrada(
				this.imagem.getNumeroLinhas(), 
				this.imagem.getNumeroColunas()
		);
		
		// Varredura da imagem
		for (int i = 0; i < this.imagem.getNumeroLinhas(); i++) {
			for (int j = 0; j < this.imagem.getNumeroColunas(); j++) {
				count = 0;
				// Obtendo todos os vizinhos do ponto
				for (int a = -(colunasFiltro / 2); a <= (colunasFiltro / 2); a++) {
					for (int b = -(colunasFiltro / 2); b <= (colunasFiltro / 2); b++) {
						if (i + a >= 0 && j + b >= 0 && i + a < this.imagem.getNumeroLinhas() && j + b < this.imagem.getNumeroColunas()) {
							vetorVizinho[count] = tonsDeCinza[i + a][j + b];
						} else {
							vetorVizinho[count] = (int) valorForaDaMatriz;
						}
						count++;
					}
				}
				// Ordena os vizinhos
				vetorVizinho = OperacaoConvolucao.ordenadaVizinhos(vetorVizinho);
				
				// Pega o meio do vetor de vizinhos
				imFiltrada.criarImagem(i, j, new Integer(vetorVizinho[vetorVizinho.length / 2]).shortValue());
			}
		}
		return imFiltrada;
	}

	// Metodos getters e setters
	public Short[][] getMatrizConvolucao() {
		return matrizConvolucao;
	}

	public void setMatrizConvolucao(Short[][] matrizConvolucao) {
		this.matrizConvolucao = matrizConvolucao;
	}
	
}
