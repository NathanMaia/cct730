package principal;



import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import util.PosicaoMatriz;

/**
 * Classe principal do aplicativo
 * @author fknappe
 */
public class Principal {
	
	private static final String MATRIZ_A = "matrizA.dat";
	private static final String MATRIZ_B = "matrizB.dat";	

	/**
	 * Metodo principal da aplicacao (respons�vel pela execu��o do programa)
	 * @param args
	 * @throws Exception
	 * 
	 * @return void
	 */
	public static void main(String[] args) throws Exception {
		
		// Declaracao de variaveis
		int[][] matrizA, matrizB, matrizConvolucao;
		int i, j = 0;
		
		// Obtencao das matrizes (leitura de arquivos)
		matrizA = getMatriz(MATRIZ_A);
		matrizB = getMatriz(MATRIZ_B);		
		
		matrizConvolucao = new int[matrizA.length][matrizA[0].length];
		
		// Execucao das convolucoes
		for (i=0; i < matrizA.length; i++) {
			for (j = 0; j < matrizA[i].length; j++) {
				matrizConvolucao[i][j] = getConvolucao(matrizA, matrizB, new PosicaoMatriz(i, j));
			}
		}
		
		// Impressao da matriz
		imprimeMatriz(matrizConvolucao);		
	}

	/**
	 * Metodo que retorna o valor da convolucao
	 * 
	 * @param int[][] matrizA
	 * @param int[][] matrizB
	 * @param PosicaoMatriz posicaoCentral
	 * @return int
	 */
	private static int getConvolucao(int[][] matrizA, int[][] matrizB, PosicaoMatriz posicaoCentral) {
		
		// Declaracao das variaveis
		int iA, iB, jA, jB, z, m = 0;
		
		// Inicializando
		z = 0;		
		m = matrizB.length/2;
		
	   /*
		* Iterando pelas duas matrizes, elemento a elemento e multiplicando
		* seus valores
		*/		
		for(iA = posicaoCentral.getLinha() - m, iB = 0;
			iB < matrizB.length; 
			iA++, iB++) {
			for (jA = posicaoCentral.getColuna() - m, jB = 0;
				 jB < matrizB[iB].length; 
				 jA++, jB++) {
				 if (iA < 0 || iA >= matrizA.length ||
				     jA < 0 || jA >= matrizA[iA].length )
					 continue;					
				 z += multiplicar(matrizA[iA][jA], matrizB[iB][jB]);
			}
		}		
		return z;
	}

	/**
	 * Metodo responsavel por realizar a multiplicao dos
	 * elementos da matriz
	 * 
	 * @param int termo1
	 * @param int termo2
	 * @return int
	 */
	private static int multiplicar(int t1, int t2) {
		// Declaracao de variaveis
		int i, resultado = 0;
		
		/* 
		 * Inicializando a variavel que armazena o resultado 
		 * da multiplicacao dos elementos da matriz 
		 */
		resultado = 0;
		
		/*
		 * 
		 */
		for (i = 0; i < 32; i++) {
			if (t2 % 2 == 1) {
				resultado += t1;
			}
			t2 = t2 >> 1;
			t1 = t1 << 1;
		}		
		return resultado;
	}
	
	/**
	 * Metodo responsavel por realizar a soma dos
	 * elementos da matriz
	 * 
	 * @param int t1
	 * @param int t2
	 * @return int
	 */
	private static int somar(int t1, int t2) {
		// Declaracao de variaveis
        int c = 0, r = 0;
        int t = ~0;
        
        for (t= ~0; t != 0; t>>= 1)
        {
        	r <<= 1;
            r |= (t1 ^ t2 ^ c) & 1;
            c = ((t1|t2) & c|t1 & t2) & 1;
            t1 >>= 1;
            t2 >>= 1;
        }
        
        for (t = ~0, c = ~t; t != 0; t>>= 1) {
        	c <<= 1;
            c |= r&1;
            r >>= 1;
        }
        return c;
	}

	/**
	 * Metodo responsavel pela impressao da matriz
	 * 
	 * @param int[][] matriz
	 * @return void
	 */
	private static void imprimeMatriz(int[][] matriz) {
		int i, j;
		
		for (i=0; i < matriz.length; i++) {
			for (j = 0; j < matriz[i].length; j++) {
				System.out.print(matriz[i][j] + "  ");
			}
			System.out.println();
		}		
	}

	/**
	 * Retorna o valor central da matriz
	 * 
	 * @param int[][] matriz
	 * @return PosicaoMatriz
	 */
	private static PosicaoMatriz getPosicaoCentral(int[][] matriz) {
		// Declaracao de variaveis
		PosicaoMatriz posicaoCentral = null;
		int m = 0;
				
		m = matriz.length/2;
		posicaoCentral = new PosicaoMatriz(m, m);
		
		return posicaoCentral;
	}

	/**
	 * Metodo responsavel por inicializar a matriz os dados do arquivo
	 * 
	 * @param String arquivo nome do arquivo
	 * @return int[][]
	 * @throws IOException
	 */
	private static int[][] getMatriz(String nomeArquvivo) throws IOException {
		
		//Declaracao de variaveis
		int linhas, colunas, i, j = 0;
		int[][] matriz;
		String[] linha = null;
		BufferedReader fluxo = null;
		
		//Abrindo arquivo para leitura dos dados
		fluxo = getArquivoMatriz(nomeArquvivo);
		
		//Obtendo a primeira linha do arquivo (que cont�m a dimens�o da matriz)
		linha = fluxo.readLine().split(" ");
		
		//Definindo o n�mero de linhas e colunas da matriz
		linhas = Integer.parseInt(linha[0]);
		colunas = Integer.parseInt(linha[1]);
		
		//Inicializando a matriz com os dados do arquivo
		matriz = new int[linhas][colunas];
		for (i = 0; i < linhas; i++) {
			matriz[i] = new int[colunas];
			linha = fluxo.readLine().split(" ");
			
			for (j=0; j<colunas; j++) {
				matriz[i][j] = Integer.parseInt(linha[j]);
			}
		}		
		
		//Fechando o arquivo
		fluxo.close();
		
		return matriz;
	}
	
	/**
	 * Metodo responsavel por abrir um buffer com o arquivo
	 * 
	 * @param String nomeArquivo
	 * @return BufferedReader
	 * @throws IOException
	 */
	private static BufferedReader getArquivoMatriz(String nomeArquivo) throws IOException {
		return new BufferedReader(new FileReader(nomeArquivo));
	}
}
