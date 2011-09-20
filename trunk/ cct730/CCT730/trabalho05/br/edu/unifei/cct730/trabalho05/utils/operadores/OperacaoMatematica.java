package br.edu.unifei.cct730.trabalho05.utils.operadores;

/**
 * Classe responsavel por realizar operacoes matematicas sobre
 * matrizes
 * 
 * @author fknappe
 *
 */
public class OperacaoMatematica {	
	
	/**
	 * Metodo responsavel por realizar a soma dos
	 * elementos da matriz
	 * 
	 * @param int t1
	 * @param int t2
	 * @return int
	 */
	public static int somar(int t1, int t2) {
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
	 * elementos da matriz, atraves do deslocamento de bits
	 * 
	 * @param int termo1
	 * @param int termo2
	 * 
	 * @return double
	 */
	public static double multiplicar(int t1, int t2) {
		// Declaracao de variaveis locais
		double resultado = 0;
		
		while (t2 != 0) {
			if ((t2 & 01) == 1)
				resultado += t1;
			t1 <<= 1;
			t2 >>= 1;
		}
		return resultado;
	}
}
