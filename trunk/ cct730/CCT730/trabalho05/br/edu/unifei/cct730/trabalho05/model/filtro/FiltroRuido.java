package br.edu.unifei.cct730.trabalho05.model.filtro;

import java.util.Random;

import br.edu.unifei.cct730.trabalho05.model.imagem.Imagem;
import br.edu.unifei.cct730.trabalho05.model.imagem.ImagemDigitalizada;
import br.edu.unifei.cct730.trabalho05.model.imagem.ImagemFiltrada;

/**
 * Classe responsavel pelas acoes dos filtro
 * do tipo ruido
 * 
 * @author fknappe
 *
 */
public class FiltroRuido extends Filtro {

	// Constantes
	public static final int RUIDO_SAL = 0;
	public static final int RUIDO_PIMENTA = 1;
	public static final int RUIDO_SAL_PIMENTA = 2;
	
	// Declaracao das variaveis locais
	private int porcentagemSal;
	private int porcentagemPimenta;
	
	/**
	 * Construtor
	 * 
	 * @param ImagemFiltrada im
	 * @param int ps
	 * @param int pp
	 *
	 */
	public FiltroRuido(Imagem im) {
		super(im);
	}
	
	/**
	 * Metodo responsavel por selecionar o tipo de filtro
	 * que sera aplicado sobre a imagem
	 * 
	 * @return ImagemFiltrada
	 */
	@Override
	public ImagemFiltrada filtrar(int tipoFiltro) {
		switch(tipoFiltro) {
			case FiltroRuido.RUIDO_SAL:
				return filtroSal();
			case FiltroRuido.RUIDO_PIMENTA:
				return filtroPimenta();
			case FiltroRuido.RUIDO_SAL_PIMENTA:
				return filtroSalComPimenta();
			default:
				throw new NullPointerException("Filtro inexistente!");
		}	
	}

	/**
	 * Metodo responsavel por aplicar o filtro 
	 * com ruido de sal sobre a imagem digitalizada
	 *
	 * @return ImagemFiltrada
	 */
	private ImagemFiltrada filtroSal() {

		// Declaracao das variaveis locais
		int quantidade = 0;
		ImagemFiltrada imFiltrada = null;
		
		Short[][] tonsDeCinza = this.imagem.getTonsDeCinzaImagem();

		// Calcula a porcentagem de incidencia do ruido
		quantidade = (imagem.getNumeroLinhas() * imagem.getNumeroColunas() * porcentagemSal) / 100;

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

		imFiltrada = new ImagemFiltrada(
			this.imagem.getNumeroLinhas(), 
			this.imagem.getNumeroColunas()
		);
		
		return (ImagemFiltrada)imFiltrada.constroiImagem(tonsDeCinza);
	}

	/**
	 * Metodo responsavel por aplicar o filtro 
	 * com ruido de pimenta sobre a imagem digitalizada
	 * 
	 * @return ImagemFiltrada
	 */
	private ImagemFiltrada filtroPimenta() {

		// Declaracao das variaveis locais
		int quantidade = 0;
		ImagemFiltrada imFiltrada = null;
		
		Short[][] tonsDeCinza = this.imagem.getTonsDeCinzaImagem();

		// Calcula a porcentagem de incidencia do ruido
		quantidade = (this.imagem.getNumeroLinhas() * this.imagem.getNumeroColunas() * porcentagemPimenta) / 100;

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

		imFiltrada = new ImagemFiltrada(
				this.imagem.getNumeroLinhas(),
				this.imagem.getNumeroColunas()
		);
		
		return (ImagemFiltrada)imFiltrada.constroiImagem(tonsDeCinza); 
	}
	
	/**
	 * Metodo responsavel por aplicar o filtro com ruido
	 * de pimenta sobre a imagem digitalizada
	 * 
	 * @param Short[][] tonsDeCinza
	 * 
	 * @return ImagemFiltrada
	 */
	private ImagemFiltrada filtroPimenta(Short[][] tonsDeCinza) {
		// Declaracao das variaveis locais
		int quantidade = 0;
		ImagemFiltrada imFiltrada = null;
		
		// Calcula a porcentagem de incidencia do ruido
		quantidade = (this.imagem.getNumeroLinhas() * this.imagem.getNumeroColunas() * porcentagemPimenta) / 100;

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

		imFiltrada = new ImagemFiltrada(
				this.imagem.getNumeroLinhas(),
				this.imagem.getNumeroColunas()
		);
		
		return (ImagemFiltrada)imFiltrada.constroiImagem(tonsDeCinza); 
	}

	/**
	 * Metodo responsavel por aplicar o filtro 
	 * com ruido de sal juntamente com o filtro 
	 * de pimenta sobre a imagem digitalizada
	 * 
	 * @return ImagemFiltrada
	 */
	private ImagemFiltrada filtroSalComPimenta() {
		ImagemFiltrada imFiltrada = this.filtroSal();
		imFiltrada = this.filtroPimenta(imFiltrada.getTonsDeCinzaImagem());
		return imFiltrada;
	}

	// Metodos getters e setters
	public int getPorcentagemSal() {
		return porcentagemSal;
	}

	public void setPorcentagemSal(int porcentagemSal) {
		this.porcentagemSal = porcentagemSal;
	}

	public int getPorcentagemPimenta() {
		return porcentagemPimenta;
	}

	public void setPorcentagemPimenta(int porcentagemPimenta) {
		this.porcentagemPimenta = porcentagemPimenta;
	}
}
