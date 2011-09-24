package br.edu.unifei.cct730.trabalho06.compressao.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import br.edu.unifei.cct730.trabalho06.gui.janelas.JanelaImagemSintetica;
import br.edu.unifei.cct730.trabalho06.gui.painel.PainelDesenho;
import br.edu.unifei.cct730.trabalho06.utils.Mensagem;
import br.edu.unifei.cct730.trabalho06.utils.arquivo.ArquivoCompressao;
import br.edu.unifei.cct730.trabalho06.compressao.gui.JanelaCompressao;
import br.edu.unifei.cct730.trabalho06.compressao.model.CompressaoRLE;
import br.edu.unifei.cct730.trabalho06.eventos.MyActionListener;
import br.edu.unifei.cct730.trabalho06.gui.componentes.MyFileChooser;
import br.edu.unifei.cct730.trabalho06.padroes.Controlador;

/**
 * Classe responsavel por intermediar as acoes do usuario
 * com o restante da aplicacao
 * 
 * @author fknappe
 *
 */
public class ControladorJanelaCompressao extends Controlador {

	// Declaracao das variaveis de instancia
	private JanelaImagemSintetica janelaImagem;

	/**
	 * Construtor 
	 * 
	 * @param JanelaCompressao j
	 */
	public ControladorJanelaCompressao(JanelaCompressao j) {
		super(j);
	}

	@Override
	public void registraEventos() {

		// Inicializando um novo listener
		MyActionListener myListener = new MyActionListener();

		// Tratamento das acoes dos botoes do menu principal
		((JanelaCompressao)this.frame).getBtnAbrirPanel().addActionListener(myListener);
		((JanelaCompressao)this.frame).getBtnSobre().addActionListener(myListener);
		((JanelaCompressao)this.frame).getBtnSair().addActionListener(myListener);
	}

	/**
	 * Metodo responsavel por instanciar um painel para criacao
	 * das imagens sinteticas
	 * 
	 * @param void
	 * @return void
	 */
	public void abrirPanel() {
		// Verificando se ja existe uma imagem sintetica em aberto
		if(janelaImagem != null) {
			janelaImagem.dispose();
		}

		try {
			// Inicializando uma nova imagem sintetica
			janelaImagem = new JanelaImagemSintetica("Imagem para compressao");
			PainelDesenho panelDesenho = new PainelDesenho(
					this.retornaValorAltura(), 
					this.retornaValorLargura()
			);
			janelaImagem.setPanelDesenho(panelDesenho, true);
			lancarFrame(janelaImagem);

			//Tratamento das acoes dos botoes da janela que contem a imagem sintetica
			janelaImagem.getBtnSair().addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					janelaImagem.dispose();
					janelaImagem = null;
				}
			});

			// Tratamento das acoes do botao comprimir imagem
			janelaImagem.getBtnComprimir().addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent arg0) {
					try {
						
						// Acoes do usuario com o fileChooser
						MyFileChooser myFileChooser = abrirFileChooser();
						int retorno = myFileChooser.lancarSaveDialog(frame);
						String caminhoArquivoImagem = myFileChooser.getArquivoSelecionado(retorno);	
						
						// Inicializando uma novo arquivo de compressao
						ArquivoCompressao arquivoImagem = new ArquivoCompressao(caminhoArquivoImagem);
						CompressaoRLE compressaoRLE = new CompressaoRLE();
						
						// Salvando os dados da compressao no arquivo
						if(arquivoImagem.salvarArquivoCompressao(
								janelaImagem.getPanelDesenho().getMatriz().getNumLinhas(),
								janelaImagem.getPanelDesenho().getMatriz().getNumColunas(),
								compressaoRLE.getMatrizCompactada(janelaImagem.getPanelDesenho().getMatriz()))
						) {
							arquivoImagem.fecharArquivoEscrita();
							Mensagem.mostraMensagem(frame, "Imagem comprimida com sucesso!");
						}
						
					} catch(IOException e) {
						e.printStackTrace();
						Mensagem.mostraErro(
								frame, 
								"Erro ao comprimir a imagem!"
						);
						
					} 
				}
			});

		} catch(NumberFormatException e) {
			e.printStackTrace();
			Mensagem.mostraErro(
					((JanelaCompressao)this.frame), 
					"Numero deve ser um valor inteiro"
			);
		} 
	}

	/**
	 * Metodo responsavel por receber o valor da altura
	 * das celulas do editor de imagem sintetica
	 * 
	 * @param void
	 * @return int
	 */
	private int retornaValorAltura() throws NumberFormatException {
		int altura = 0;

		altura = Mensagem.entradaDeDados("Determine o valor da altura: ");
		return altura;
	}

	/**
	 * Metodo responsavel por receber o valor da largura
	 * das celulas do editor de imagem sintetica
	 * 
	 * @param void
	 * @return int
	 */
	private int retornaValorLargura() throws NumberFormatException {
		int largura = 0;

		largura = Mensagem.entradaDeDados("Determine o valor da largura: ");
		return largura;
	}
	
	/**
	 * Metodo responsavel por instanciar o file chooser
	 * para escolha do nome e local que o arquivo da
	 * imagem comprimida sera salvo
	 * 
	 * @return MyFileChooser
	 */
	private MyFileChooser abrirFileChooser() {
		// Declaracao de variaveis locais
		MyFileChooser fileChooser = null;
		
		fileChooser = new MyFileChooser("Salvar arquivo compressao");
		fileChooser.setFileSelectionMode(MyFileChooser.FILES_ONLY);
		fileChooser.filtro(".rle", "Arquivos de compressao RLE");
		fileChooser.formatoPadrao("imagem.rle");
		return fileChooser;
	}
}
