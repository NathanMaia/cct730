package br.edu.unifei.cct730.trabalho05.principal.controlador;

import java.io.IOException;

import javax.swing.JInternalFrame;

import br.edu.unifei.cct730.trabalho05.gui.componentes.MyFileChooser;
import br.edu.unifei.cct730.trabalho05.eventos.MyActionListener;
import br.edu.unifei.cct730.trabalho05.padroes.Controlador;
import br.edu.unifei.cct730.trabalho05.principal.gui.JanelaPrincipal;
import br.edu.unifei.cct730.trabalho05.utils.arquivo.ArquivoCabecalho;
import br.edu.unifei.cct730.trabalho05.utils.arquivo.ArquivoImagem;
import br.edu.unifei.cct730.trabalho05.utils.constantes.Mensagem;
import br.edu.unifei.cct730.trabalho05.utils.imagem.ImagemDigitalizada;
import br.edu.unifei.cct730.trabalho05.gui.janelas.JanelaImagemDigitalizada;
import br.edu.unifei.cct730.trabalho05.gui.painel.PainelImagem;

/**
 * Classe responsavel por responder a todas as solicitacoes do usuario
 * 
 * @author fknappe
 *
 */
public class ControladorPrincipal extends Controlador {

	// Declara��o das vari�veis de inst�ncia
	private JanelaPrincipal janela = null;
	private ArquivoImagem arquivoImagem = null;
	private ArquivoCabecalho arquivoCabecalho = null;
	private String caminhoUltimoArquivo = "";

	/**
	 * Construtor
	 * 
	 * @param JanelaPrincipal j
	 */
	public ControladorPrincipal(JanelaPrincipal j) {
		super(j);
		this.janela = j;
	}

	/**
	 * Metodo responsavel por inicializar todas as funcoes do menu
	 * da interface principal
	 * 
	 * @retun void
	 */
	@Override
	public void registraEventos() {
		MyActionListener listener = new MyActionListener();

		// Adicionando as acoes a todos os botoes do menu
		janela.getBtnAbrirArquivo().addActionListener(listener);
		janela.getBtnRecarregarArquivo().addActionListener(listener);
		janela.getBtnSobre().addActionListener(listener);
		janela.getBtnSair().addActionListener(listener);
	}

	/**
	 * Metodo responsavel por tratar as acoes do botao de abrir
	 * o arquivo da imagem
	 * 
	 * @return void
	 */
	public void abrirArquivo() {
		try {
			// Desabilitando todas as acoes ja existentes
			this.desfazerAcoesAnteriores();

			// Escolha do arquivo
			MyFileChooser fileChooser = this.abrirFileChooser();
			int op = fileChooser.lancarOpenDialog(janela);
			String retorno = fileChooser.getArquivoSelecionado(op);

			if(!(retorno.equals(MyFileChooser.OPERACAO_CANCELADA) || 
					retorno.equals(MyFileChooser.OPERACAO_ERRO))
			) {				
				// Definindo o caminho do arquivo para aberturas posteriores
				this.caminhoUltimoArquivo = fileChooser.getSelectedFile().getAbsolutePath();

				// Apresentando a imagem ao usuario
				constroiJanelaImagemDigitalizada();
			}
		} catch(Exception e) {
			e.printStackTrace();
			Mensagem.mostraErro(
					janela, 
					"Falha ao abrir o arquivo!"
			);
		} finally { 
			//Habilitando as acoes do menu 
			janela.habilitarMenu();
		}
	}

	/**
	 * Metodo responsavel por reabrir o arquivo
	 * de imagem
	 * 
	 * @return void
	 */
	public void recarregarArquivo() {
		try {
			// Desfazendo todas as acoes previas do usuario
			this.desfazerAcoesAnteriores();

			// Apresentando a imagem ao usuario
			constroiJanelaImagemDigitalizada();

		} catch(Exception e) {
			e.printStackTrace();
			Mensagem.mostraErro(
					janela, 
					"Falha ao abrir o arquivo!"
			);
		} finally {
			janela.habilitarMenu();
		}
	}

	/**
	 * Metodo responsavel por tratar as acoes do botao que mostra as informacoes
	 * sobre o(s) autor(es) deste projeto
	 * 
	 * @return void
	 */
	public void sobre() {
		Mensagem.mostraMensagemSobre(
				janela,
				"Universidade Federal de Itajuba\n" + 
				"PDI - Processamento de Imagens \n" +
				"Professor: Dr. Edison Oliveira de Jesus\n" +
				"Aluno: Felipe Agostini Knappe - 12623"
		);
	}

	/**
	 * Metodo responsavel por tratar as acoes do botao de finalizar o aplicativo
	 * 
	 * @return void
	 */
	public void sair() {
		if(Mensagem.confirmaMensagem(
				janela, 
				"Deseja realmente sair do programa?"
		)
		) {
			janela.dispose();
			System.exit(0);
		}
	}

/* ****************************************************************************************************************************************************************** 
* 															Inicio dos metodos auxiliares (todos privados)
*********************************************************************************************************************************************************************/

	/**
	 * Metodo responsavel por inicializar a janela que 
	 * contem a imagem digitalizada
	 * 
	 * @param PanelImagem panel
	 * 
	 * @return void
	 */
	private JanelaImagemDigitalizada constroiJanelaImagemDigitalizada() throws IOException {
		/*
		 * Inicializando a janela que contem a
		 * imagem digitalizada 
		 */
		JanelaImagemDigitalizada jImagemDigitalizada = 
			new JanelaImagemDigitalizada(
					new PainelImagem(
							this.criarImagemDigitalizada()
					)
			);
		lancarJanelaImagem(jImagemDigitalizada);
		return jImagemDigitalizada;
	}

	/**
	 * Metodo responsavel pela abertura da janela para escolha
	 * do arquivo de imagem
	 * 
	 * @return MyFileChooser
	 */
	private MyFileChooser abrirFileChooser() {
		MyFileChooser fileChooser = new MyFileChooser("Selecione a imagem");
		// Setando o diretorio local como inicial
		fileChooser.defineDiretorioInicial(caminhoUltimoArquivo);
		/*
		 *  Filtrando os arquivos a serem abertos somente para 
		 *  imagens em nivel de cinza
		 */
		fileChooser.filtro(".img", "Arquivos em nivel de cinza");
		return fileChooser;
	}

	/**
	 * Metodo responsavel por inicializar uma instancia
	 * da imagem digitalizada
	 * 
	 * @return ImagemDigitalizada
	 * @throws IOException
	 */
	private ImagemDigitalizada criarImagemDigitalizada() throws IOException {
		// Abertura do arquivo
		this.abrirArquivoImagem();

		// Leitura das dimensoes da figura do arquivo
		int numeroLinhas = this.getNumeroLinhasImagem();
		int numeroColunas = this.getNumeroColunasImagem();

		// Inicializando a imagem digitalizada
		ImagemDigitalizada imagem = new ImagemDigitalizada(
				numeroLinhas,
				numeroColunas
		);

		// Leitura dos tons de cinza do arquivo 
		Short[][] tonsCinza = 
			this.getTonsCinzaImagem(
					numeroLinhas,
					numeroColunas
			);

		/* 
		 * Adicionando os tons de cinza presentes no arquivo
		 * a imagem digitalizada
		 */
		for(int i = 0; i < numeroLinhas; i++) {
			for(int j = 0; j < numeroColunas; j++) {
				imagem.criarImagem(i, j, tonsCinza[i][j]);
			}
		}

		/*
		 * Finalizando os arquivos da imagem
		 */
		this.fecharArquivoImagem();

		return imagem;
	}

	/**
	 * Metodo responsavel por desfazer as acoes anteriores
	 * na reabertura do arquivo
	 * 
	 * @return void
	 */
	private void desfazerAcoesAnteriores() {
		// Finalizando todas as acoes anteriores
		for(JInternalFrame j : janela.getDesktop().getAllFrames()) {
			if(j instanceof JanelaImagemDigitalizada)
				j.dispose();
		}
	}

	/**
	 * Metodo responsavel por realizar a abertura dos arquivos
	 * da imagem
	 * 
	 * @throws IOException
	 */
	private void abrirArquivoImagem() throws IOException {
		// Abertura do arquivo
		arquivoImagem = new ArquivoImagem(caminhoUltimoArquivo);
		arquivoCabecalho = new ArquivoCabecalho(
				arquivoImagem.getAbsolutePath().replace("IMG", "CAB")
		);
	}

	/**
	 * Metodo responsavel por fechar o stream para o arquivo
	 * da imagem
	 * 
	 * @return void
	 * @throws IOException
	 * @throws NullPointerException
	 */
	private void fecharArquivoImagem() throws IOException, NullPointerException {
		// Finalizando o stream com os arquivos
		arquivoImagem.fecharArquivo();
		arquivoCabecalho.fecharArquivo();
	}

	/**
	 * Metodo que retorna o numero de linhas presentes
	 * no arquivo da imagem
	 * 
	 * @return int
	 * @throws IOException
	 * @throws NullPointerException
	 */
	private int getNumeroLinhasImagem() throws IOException, NullPointerException {
		return arquivoCabecalho.getNumeroLinhas();
	}

	/**
	 * Metodo que retorna o numero de colunas presentes
	 * no arquivo da imagem
	 * 
	 * @return int
	 * @throws IOException
	 * @throws NullPointerException
	 */
	private int getNumeroColunasImagem() throws IOException, NullPointerException {
		return arquivoCabecalho.getNumeroColunas();
	}

	/**
	 * Metodo que retorna todos os tons de cinza presentes
	 * no arquivo da imagem
	 * 
	 * @return Short[][]
	 * @throws IOException
	 */
	private Short[][] getTonsCinzaImagem(int numLinhas, int numColunas) throws IOException, NullPointerException {
		return arquivoImagem.getTonsCinza(
				numLinhas, 
				numColunas
		);
	}
}
