package br.edu.unifei.cct730.trabalho03.utils;

/**
 * Classe responsavel por realizar operacoes matematicas sobre
 * matrizes
 * 
 * @author fknappe
 *
 */
public class OperacoesMatematicas {	
	
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
		 * Realizando a multiplicacao
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
}
