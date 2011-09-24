package br.edu.unifei.cct730.trabalho06.compressao.model;

import br.edu.unifei.cct730.trabalho03.utils.MatrizCelula;
import br.edu.unifei.cct730.trabalho06.utils.Conversao;

/**
 * Classe responsavel por realizar a compressao
 * Run Length Encoding
 * 
 * @author fknappe
 *
 */
public class CompressaoRLE {

	/**
	 * Metodo responsavel por retornar uma matriz
	 * contendo os valores da matriz compactados
	 * em binario
	 * 
	 * @return String[]
	 */
	public String[] getMatrizCompactada(MatrizCelula matriz) {

		// Declaracao de variaveis locais
		String[] matrizCompactada = new String[matriz.getNumLinhas()];
		String linha = "", atual = "", anterior = "", convertida = "";
		int quantidade = 0;

		for (int i = 0; i < matriz.getNumLinhas(); i++) {
			linha = "";
			for (int j = 0; j < matriz.getNumColunas(); j++) {
				linha += Conversao.converteDecimalEmBinario(
						(matriz.getValorCelula(i, j)
						));
			}
			// Obtendo o primeiro valor
			convertida = linha.substring(0, 1);

			//Obtendo o valor anterior
			anterior = linha.substring(0, 1);			
			quantidade = 1;

			for (int j = 1; j < linha.length(); j++) {

				//Obtendo o valor do proximo caracter
				atual = linha.substring(j, j + 1);

				//Caso os valores sejam iguais
				if (atual.equals(anterior)) {
					quantidade++;

				//Caso contrario
				} else {
					// Verifica se quantidade >= 9, coloca entre parenteses
					if (quantidade > 9) convertida += "(" + quantidade + ")";
					else convertida += "" + quantidade;

					// Reinicia a contagem
					anterior = atual;
					quantidade = 1;
				}
			}

			// Grava o ultimo valor
			if (quantidade > 9) convertida += "(" + quantidade + ")";
			else convertida += "" + quantidade;

			// Salva a linha convertida
			matrizCompactada[i] = convertida;
		}

		return matrizCompactada;
	}

	/**
	 * Metodo responsavel por retornar uma matriz
	 * com os valores descompactados do arquivo
	 * 
	 * @param int linhas
	 * @param int colunas
	 * @param String[] matrizCompactada
	 *  
	 * @return MatrizCelula
	 */
	public MatrizCelula getMatrizDescompactada(int linhas, int colunas, String[] matrizCompactada) {

		// Declaracao de variaveis de instancia
		MatrizCelula matriz = null;
		String caracter, atual, numero = "";
		String[] matrizDescompactada = null;
		int posicao = 0;
		
		matrizDescompactada = new String[linhas];
		
		for(String linha : matrizCompactada) {
			atual = linha.substring(0, 1);
			
			// Inicio a linha da matriz descompactada
			matrizDescompactada[posicao] = "";
			
			for (int i = 1; i < linha.length(); i++) {
				caracter = linha.substring(i, i + 1);
				if (caracter.equals("(")) {
					i++;
					caracter = linha.substring(i, i + 1);
					while (!caracter.equals(")")) {
						numero += caracter;
						i++;
						caracter = linha.substring(i, i + 1);
					}
					escreveNaMatrizDescompactada(matrizDescompactada, atual, numero, posicao);
				} else {
					numero += caracter;
					escreveNaMatrizDescompactada(matrizDescompactada, atual, numero, posicao);
				}

				if (atual.equals("1")) {
					atual = "0";
				} else {
					atual = "1";
				}
				numero = "";
			}
			posicao++;
		}
		
		// Inicializacao da matriz descompactada
		matriz = new MatrizCelula(linhas, colunas);
		
		// Preenche a matriz
		for (int i = 0, k = 0; i < matrizDescompactada.length; i++, k++) {
			for (int j = 0, l = 0; j < matrizDescompactada[i].length(); j += 8, l++) {
				matriz.add(Conversao.converteBinarioEmDecimal(matrizDescompactada[i].substring(j, j + 8)), k, l);
			}
		}
		return matriz;
	}

	/**
	 * Metodo responsavel 
	 * 
	 * @param MatrizCelula matriz
	 * @param String atual
	 * @param String valor
	 * @param int posicao
	 */
	private void escreveNaMatrizDescompactada(String[] matriz, String atual, String valor, int posicao) {
		for (int j = 0; j < Integer.parseInt(valor); j++) {
			matriz[posicao] += atual;
		}
	}
}
