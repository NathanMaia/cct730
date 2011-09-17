package br.edu.unifei.cct730.trabalho05.controlador.principal;

import java.io.IOException;

import javax.swing.JInternalFrame;

import br.edu.unifei.cct730.trabalho05.gui.componentes.MyFileChooser;
import br.edu.unifei.cct730.trabalho05.eventos.MyActionListener;
import br.edu.unifei.cct730.trabalho05.model.arquivo.ArquivoCabecalho;
import br.edu.unifei.cct730.trabalho05.model.arquivo.ArquivoImagem;
import br.edu.unifei.cct730.trabalho05.padroes.Controlador;
import br.edu.unifei.cct730.trabalho05.utils.constantes.Mensagem;
import br.edu.unifei.cct730.trabalho05.gui.principal.JanelaPrincipal;

/**
 * Classe responsavel por responder a todas as solicitacoes do usuario
 * 
 * @author fknappe
 *
 */
public class ControladorPrincipal extends Controlador {

	// Declaracao das variaveis de instancia
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
		janela.getBtnFiltrar().addActionListener(listener);
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

				/*
				 * Realizando a abertura do arquivo
				 * da imagem 
				 */
				this.abrirArquivoImagem();

				/*
				 *  Garantindo que o arquivo da imagem foi finalizado
				 */
				this.fecharArquivoImagem();
			}
		} catch(IOException e) {
			e.printStackTrace();
			Mensagem.mostraErro(
					janela, 
					"Falha na abertura do arquivo!"
			);
		} catch(NumberFormatException e) {
			e.printStackTrace();
			Mensagem.mostraErro(
					janela, 
					"Numero deve ser inteiro"
			);
		} catch(NullPointerException e) {
			e.printStackTrace();
			Mensagem.mostraErro(
					janela, 
					"Selecione o tipo de ruido antes de filtra-lo"
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
	/*public void recarregarArquivo() {
		try {
			// Desfazendo todas as acoes previas do usuario
			this.desfazerAcoesAnteriores();

		} catch(IOException e) {
			e.printStackTrace();
			Mensagem.mostraErro(
					janela, 
					"Falha na abertura do arquivo!"
			);
		} catch(NumberFormatException e) {
			e.printStackTrace();
			Mensagem.mostraErro(
					janela, 
					"Numero deve ser inteiro"
			);
		} catch(NullPointerException e) {
			e.printStackTrace();
			Mensagem.mostraErro(
					janela, 
					"Selecione o tipo de ruido antes de filtra-lo"
			);

		} finally { 
			//Habilitando as acoes do menu 
			janela.habilitarMenu();
		}
	}*/

	/**
	 * Metodo responsavel por aplicar o filtro
	 * selecionado no combo sobre a imagem
	 * digitalizada
	 * 
	 * @return void
	 */
	/*public void filtrar() {
		try {
			// Selecionando o tipo de filtro
			String filtro = (String)janela.getCmbFiltros().getSelectedItem();

			// Aplicando o filtro sobre a imagem
			for(JInternalFrame j : janela.getDesktop().getAllFrames()) {
				((JanelaImagemFiltrada)j).
				getPainelImagemDigitalizada().
				constroiImagem(
						this.filtroPassaBaixa(filtro).getTabelaPontos()
				);
			}

		} catch(IOException e) {
			e.printStackTrace();
			Mensagem.mostraErro(
					janela, 
					"Falha na abertura do arquivo!"
			);
		} catch(NumberFormatException e) {
			e.printStackTrace();
			Mensagem.mostraErro(
					janela, 
					"Numero deve ser inteiro"
			);
		} catch(NullPointerException e) {
			e.printStackTrace();
			Mensagem.mostraErro(
					janela, 
					"Selecione o tipo de ruido antes de filtra-lo"
			);
		}
	}*/

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
	 * Metodo responsavel por desfazer as acoes anteriores
	 * na reabertura do arquivo
	 * 
	 * @return void
	 */
	private void desfazerAcoesAnteriores() {
		// Finalizando todas as acoes anteriores
		for(JInternalFrame j : janela.getDesktop().getAllFrames()) {
			j.dispose();
		}
	}

	/**
	 * Metodo que retorna a porcentagem
	 * de ruido para realizar a filtragem
	 * 
	 * @param tipo
	 * @return int
	 * @throws NumberFormatException
	 */
	private int porcentagemRuido(String tipo) throws NumberFormatException {
		// Declaracao de variaveis locais
		int porcentagem = 0;

		// Entrada de dados
		porcentagem = Mensagem.entradaDeDados(
				"Determine a porcentagem do " + tipo + ": "
		);

		if((porcentagem <=  0) && (porcentagem >= 100))
			throw new NumberFormatException(
					"Entre com um valor entre 0 - 100."
			);

		return porcentagem; 
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
		fileChooser = new MyFileChooser("Selecione a imagem");

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
	 * Metodo responsavel por realizar a abertura dos arquivos
	 * da imagem
	 * 
	 * @throws IOException
	 */
	private void abrirArquivoImagem() throws IOException {
		// Abertura do arquivo
		if(this.arquivoImagem == null) {
			arquivoImagem = new ArquivoImagem(caminhoUltimoArquivo);
			arquivoCabecalho = new ArquivoCabecalho(
					arquivoImagem.getAbsolutePath().replace("IMG", "CAB")
			);
		}
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
		if(!this.arquivoImagem.isFechado()) {
			arquivoImagem.fecharArquivo();
			arquivoCabecalho.fecharArquivo();
		}
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
	private Short[][] getTonsCinzaArquivo() throws IOException, NullPointerException {
		return arquivoImagem.getTonsCinza(
				this.getNumeroLinhasImagem(), 
				this.getNumeroColunasImagem()
		);
	}
}
