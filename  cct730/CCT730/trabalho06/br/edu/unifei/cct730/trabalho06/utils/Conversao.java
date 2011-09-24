package br.edu.unifei.cct730.trabalho06.utils;

public class Conversao {

	/**
	 * Metodo responsavel por converter um valor decimal
	 * para uma string que representa o valor em base
	 * binaria
	 * 
	 * @param int decimal
	 * 
	 * @return String
	 */
	public static String converteDecimalEmBinario(int decimal) {
		
		// Declaracao das variaveis locais
		String binario = "";
		int base = 0;
		
		// Conversao para base binaria
		for (int i = 0; i < 8; i++) {
			base = decimal & 0x80;
			if (base == 0x80) binario += '1';
			else binario += '0';
			decimal = decimal << 1;
		}
		return binario;
	}
	
	/**
	 * Metodo responsavel por uma string que representa um valor
	 * em base binaria para um valor decimal
	 * 
	 * @param String string
	 * 
	 * @return int
	 */
	public static int converteBinarioEmDecimal(String string) {
		int soma = 0;
		for (int i = 0, j = 7; i < 8; i++, j--) {
			soma += (Integer.parseInt(string.substring(i, i + 1)) << j);
		}
		return soma;
	}
}
