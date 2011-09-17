package br.edu.unifei.cct730.trabalho05.model.filtros;

import java.io.IOException;
import java.util.Random;

import br.edu.unifei.cct730.trabalho05.model.imagem.ImagemFiltrada;
import br.edu.unifei.cct730.trabalho05.utils.constantes.Constantes;

public class FiltroRuido extends Filtro {

	// Constantes
	private static final int RUIDO_SAL = 0;
	private static final int RUIDO_PIMENTA = 1;
	private static final int RUIDO_SAL_PIMENTA = 2;
	
	/**
	 * Construtor
	 * 
	 * @param ImagemFiltrada im
	 */
	public FiltroRuido(ImagemFiltrada im) {
		super(im);
	}
	
	@Override
	public ImagemFiltrada filtrar(int tipoFiltro) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Metodo responsavel por aplicar o filtro 
	 * com ruido de sal sobre a imagem digitalizada
	 *
	 * @param PainelImagem pImagem
	 * 
	 * @return PainelImagem
	 */
	private ImagemFiltrada filtroSal(int porcentagem, Short[][] tonsDeCinza) {

		// Declaracao das variaveis locais
		int quantidade = 0;

		// Calcula a porcentagem de incidencia do ruido
		quantidade = (imagem.getNumeroLinhas() * imagem.getNumeroColunas() * porcentagem) / 100;

		/* 
		 * Calculo de um valor aleatorio para 
		 * auxiliar no calculo da incidencia
		 * do ruido sobre os niveis de cinza
		 */
		Random r = new Random();

		// Troca randomica dos niveis de cinza
		for (int i = 0; i < quantidade; i++) {
			tonsDeCinza
			[r.nextInt(imagem.getNumeroLinhas() - 1)]
			[r.nextInt(imagem.getNumeroColunas() - 1)] = 255;
		}

		return (ImagemFiltrada)this.imagem.constroiImagem(tonsDeCinza);
	}

	/**
	 * Metodo responsavel por aplicar o filtro 
	 * com ruido de pimenta sobre a imagem digitalizada
	 * 
	 * @param PainelImagem pImagem
	 * 
	 * @return PainelImagem
	 */
	private ImagemFiltrada filtroPimenta(int porcentagem, Short[][] tonsDeCinza) {

		// Declaracao das variaveis locais
		int quantidade = 0;

		// Calcula a porcentagem de incidencia do ruido
		quantidade = (this.imagem.getNumeroLinhas() * this.imagem.getNumeroColunas() * porcentagem) / 100;

		/* 
		 * Calculo de um valor aleatorio para 
		 * auxiliar no calculo da incidencia
		 * do ruido sobre os niveis de cinza
		 */
		Random r = new Random();

		// Troca randomica dos niveis de cinza
		for (int i = 0; i < quantidade; i++) {
			tonsDeCinza
			[r.nextInt(this.imagem.getNumeroLinhas() - 1)]
			[r.nextInt(this.imagem.getNumeroColunas() - 1)] = 0; 
		}

		return (ImagemFiltrada)this.imagem.constroiImagem(tonsDeCinza); 
	}

	/**
	 * Metodo responsavel por aplicar o filtro 
	 * com ruido de sal juntamente com o filtro 
	 * de pimenta sobre a imagem digitalizada
	 * 
	 * @param int porcentagemSal
	 * @param int orcentagemPimenta
	 * @param Short[][] tonsDeCinza
	 * 
	 * @return ImagemFiltrada
	 */
	private ImagemFiltrada filtroSalComPimenta(int porcentagemSal, int porcentagemPimenta, Short[][] tonsDeCinza) {
		this.imagem = this.filtroSal(porcentagemSal, tonsDeCinza);
		this.imagem = this.filtroPimenta(porcentagemPimenta, this.imagem.getTonsDeCinzaImagem());
		return this.imagem;
	}
}
