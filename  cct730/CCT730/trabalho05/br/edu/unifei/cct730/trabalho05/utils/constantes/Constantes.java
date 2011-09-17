package br.edu.unifei.cct730.trabalho05.utils.constantes;

public class Constantes {

	// Filtros de ruido
	public static final String RUIDO_SAL = "Ruido Sal";
	public static final String RUIDO_PIMENTA = "Ruido Pimenta";
	public static final String RUIDO_SAL_COM_PIMENTA = "Rudio de sal e pimenta";
	
	// Filtros de passa baixa
	public static final String PASSABAIXA_MEDIA = "Media";
	public static final String PASSABAIXA_MEDIANA = "Mediana";
		
	/*
	 * Array que seleciona os filtros do 
	 * ruido 
	 */
	public static final String[] FILTROS_RUIDOS = { 
		RUIDO_SAL,
		RUIDO_PIMENTA,
		RUIDO_SAL_COM_PIMENTA
	};
	
	/*
	 * Array que seleciona os filtros do
	 * tipo passa baixa
	 */
	public static final String[] FILTROS_PASSABAIXA = {
		PASSABAIXA_MEDIA,
		PASSABAIXA_MEDIANA
	};
}
