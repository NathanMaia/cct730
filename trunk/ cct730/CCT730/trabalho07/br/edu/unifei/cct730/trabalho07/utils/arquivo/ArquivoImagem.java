package br.edu.unifei.cct730.trabalho07.utils.arquivo;

import java.io.IOException;

/**
 * Classe responsavel por interagir com o arquivo
 * de dados da imagem
 * 
 * @author fknappe
 *
 */
public class ArquivoImagem extends Arquivo {

	private Short[][] tonsDeCinza = null;

	/**
	 * Construtor
	 * @param a
	 */
	public ArquivoImagem(String a) {
		super(a);
	}

	/**
	 * Metodo responsavel por pegar todos os tons de cinza
	 * presentes na imagem
	 * 
	 * @param int nl
	 * @param int nc
	 * 
	 * @return Short[][]
	 * @throws IOException
	 */
	public Short[][] getTonsCinza(int nl, int nc) throws IOException {
		if(this.tonsDeCinza == null) {
			this.tonsDeCinza = new Short[nl][nc];
			String linha = "";
			String linhaRepartida[] = null;
			if(this.isReadable()) {
				for (int i = 0; ((linha = this.streamReader.readLine()) != null) && (i < nl); i++) {
					linhaRepartida = linha.split(" ");
					for (int j = 0; j < (linhaRepartida.length) && (j < nc); j++) {
						tonsDeCinza[i][j] = Short.parseShort(linhaRepartida[j]);	
					}
				}
			}
		}
		return tonsDeCinza;
	}
	
	public long getTamanhoImagem() {
		return this.length();
	}
}
