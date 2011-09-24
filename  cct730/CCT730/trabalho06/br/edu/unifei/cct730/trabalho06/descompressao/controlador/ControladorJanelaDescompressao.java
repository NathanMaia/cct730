package br.edu.unifei.cct730.trabalho06.descompressao.controlador;

import java.io.FileNotFoundException;
import java.io.IOException;

import br.edu.unifei.cct730.trabalho06.gui.janelas.JanelaImagemSintetica;
import br.edu.unifei.cct730.trabalho06.gui.painel.PainelDesenho;
import br.edu.unifei.cct730.trabalho06.utils.Mensagem;
import br.edu.unifei.cct730.trabalho06.gui.componentes.MyFileChooser;
import br.edu.unifei.cct730.trabalho06.compressao.model.CompressaoRLE;
import br.edu.unifei.cct730.trabalho06.descompressao.gui.JanelaDescompressao;
import br.edu.unifei.cct730.trabalho06.eventos.MyActionListener;
import br.edu.unifei.cct730.trabalho06.padroes.Controlador;
import br.edu.unifei.cct730.trabalho06.utils.arquivo.ArquivoCompressao;

/**
 * Classe responsavel por intermediar as solicitacoes do usuario
 * com o restante da aplicacao
 * 
 * @author fknappe
 *
 */
public class ControladorJanelaDescompressao extends Controlador {

	// Declaracao das variaveis de instancia
	private ArquivoCompressao arquivo = null;

	/**
	 * Construtor
	 * 
	 * @param JanelaDescompressao j
	 */
	public ControladorJanelaDescompressao(JanelaDescompressao j) {
		super(j);
	}

	@Override
	public void registraEventos() {

		// Instanciando um novo listener
		MyActionListener myListener = new MyActionListener();

		// Adicionando tratamento as acoes dos botoes do menu prinicipal
		((JanelaDescompressao)this.frame).getBtnAbrirArquivo().addActionListener(myListener);
		((JanelaDescompressao)this.frame).getBtnDescomprimir().addActionListener(myListener);
		((JanelaDescompressao)this.frame).getBtnSobre().addActionListener(myListener);
		((JanelaDescompressao)this.frame).getBtnSair().addActionListener(myListener);
	}

	/**
	 * Metodo responsavel por tratar as acoes
	 * de interacao com arquivos
	 * 
	 * @return void
	 */
	public void abrirArquivo() {

		try {
			// Declaracao das variaveis locais
			MyFileChooser myFileChooser = this.abrirFileChooser();
			int retorno = myFileChooser.lancarOpenDialog(frame);
			String caminhoArquivoImagem = myFileChooser.getArquivoSelecionado(retorno);	

			// Inicializando uma novo arquivo de compressao
			arquivo = new ArquivoCompressao(caminhoArquivoImagem);

			if(arquivo.exists()) {
				Mensagem.mostraMensagem(
						frame, 
						"Arquivo carregado com sucesso!"
				);
			}

		} catch(IOException e) {
			e.printStackTrace();
			Mensagem.mostraErro(
					frame, 
					"Erro ao carregar o arquivo!"
			);
			((JanelaDescompressao)this.frame).desabilitaMenu();

		} finally {
			((JanelaDescompressao)this.frame).habilitaMenu();
		}
	}

	/**
	 * Metodo responsavel por realizar a descompressao
	 * da imagem sintetica comprimida
	 * 
	 * @return void
	 */
	public void descomprimirImagem() {

		try{ 
			// Declaracao das variaveis locais
			CompressaoRLE compressao = null;

			// Inicializando a descompressao
			compressao = new CompressaoRLE();

			// Inicializando a imagem sintetica
			JanelaImagemSintetica jImagem = new JanelaImagemSintetica("Imagem descomprimida por RLE");
			jImagem.setPanelDesenho(
					new PainelDesenho(
						compressao.getMatrizDescompactada(
							this.getNumeroLinhasMatriz(), 
							this.getNumeroColunasMatriz(), 
							this.getMatrizCompactada())
					), 
					false
			);
			lancarFrame(jImagem);
			
			// Tratamento do evento de finalizar a janela com a imagem descomprimida
			jImagem.getBtnSair().addActionListener(new MyActionListener());
			
		} catch(IOException e) {
			e.printStackTrace();
			Mensagem.mostraErro(
					this.frame, 
					"Erro na leitura do arquivo!"
			);
		} catch(NullPointerException e) {
			e.printStackTrace();
			Mensagem.mostraErro(
					this.frame, 
					"Voce deve inicializar o arquivo primeiramente!"
			);
		}
	}

	/**
	 * Metodo responsavel pela abertura da janela para escolha
	 * do arquivo de imagem
	 * 
	 * @return MyFileChooser
	 */
	private MyFileChooser abrirFileChooser() {

		// Declaracao das variaveis locais
		MyFileChooser fileChooser = null;

		// Inicializando o ficheiro de arquivos
		fileChooser = new MyFileChooser("Abrir arquivo de compressao");

		/*
		 *  Filtrando os arquivos a serem abertos somente para 
		 *  imagens em nivel de cinza
		 */
		fileChooser.filtro(".rle", "Arquivos em compressao RLE");
		return fileChooser;
	}

	/**
	 * Metodo responsavel por retornar o numero de linhas
	 * da matriz do arquivo de compressao
	 * 
	 * @return Integer
	 * @throws IOException, NullPointerException
	 */
	private Integer getNumeroLinhasMatriz() throws IOException, NullPointerException {
		return this.arquivo.getNumeroLinhas();
	}

	/**
	 * Metodo responsavel por retornar o numero de colunas
	 * da matriz do arquivo de compressao
	 * 
	 * @return Integer
	 * @throws IOException, NullPointerException
	 */
	private Integer getNumeroColunasMatriz() throws IOException, NullPointerException {
		return this.arquivo.getNumeroColunas();
	}

	/**
	 * 
	 * @return
	 * @throws IOException, NullPointerException
	 */
	private String[] getMatrizCompactada() throws IOException, NullPointerException {

		// Declaracao de variaveis locais
		String[] matrizCompactada = null;

		// Inicializando
		matrizCompactada = this.arquivo.lerArquivoCompressao();

		return matrizCompactada;
	}
}
