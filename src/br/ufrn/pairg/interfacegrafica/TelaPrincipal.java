package br.ufrn.pairg.interfacegrafica;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileFilter;
import javax.swing.JLabel;

import br.ufrn.pairg.pdfgenerator.CriaeLeArquivoConfiguracoesdat;
import br.ufrn.pairg.pdfgenerator.Main;
import br.ufrn.pairg.pdfgenerator.SingletonPDFGeradoComSucessoDeveSerMostrado;

import com.jgoodies.forms.factories.DefaultComponentFactory;

import javax.swing.SwingConstants;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Label;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JEditorPane;
import javax.swing.JFileChooser;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;
import java.util.LinkedList;

import javax.swing.AbstractAction;
import javax.swing.Action;






import java.io.File;

import javax.swing.*;
import javax.swing.filechooser.*;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileFilter;
import javax.swing.JLabel;

import br.ufrn.pairg.pdfgenerator.CriaeLeArquivoConfiguracoesdat;
import br.ufrn.pairg.pdfgenerator.Main;

import com.jgoodies.forms.factories.DefaultComponentFactory;

import javax.swing.SwingConstants;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Label;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JComboBox;
import javax.swing.JEditorPane;
import javax.swing.JFileChooser;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;
import java.util.LinkedList;

import javax.swing.AbstractAction;
import javax.swing.Action;








import java.io.File;

import javax.swing.*;
import javax.swing.filechooser.*;

public class TelaPrincipal extends JFrame implements ActionListener
{

	private JPanel contentPane;
	private JTextField campo_preencher_diretorio;
	private JTextField campo_preencher_autor;
	private JTextField campo_preencher_versao;
	private JTextField campo_preencher_output;
	private final Action acaoBotaoEspecificarPastasArquivosProjeto = new AcaoEspecificarPastasEArquivosProjeto();
	private final Action acaoSelecionarPastaProjeto = new AcaoSelecionarProjeto();
	private static JFileChooser escolhedorPastaProjeto;
	private static JFileChooser escolhedorOutputProjeto;
	private LinkedList<String> extensoes;
	//variáveis referentes a selecionar 
	private JTextField textFieldAdicionarExtensoes;
	private JList<String> listaExtensoes;
	private JButton buttonAdicionarExtensoes;
	private JButton buttonRemoverExtensoes;
	private DefaultListModel<String> listModel;
	private Label label;
	private final Action acaoGerarPdf = new AcaoGerarPdf();
	private final Action acaoEspecificarOutput = new AcaoEspecificarOutput();
	private JTextField campo_nome_projeto;
	
	private int tamanhoMaximoDaBarraDeProgresso; //necessario  para mostrar ao usuario o progresso na hora de carregar as pastas e subpastas do projeto
	private JLabel textoBarraDeProgresso;
	
	
	private JTextField textFieldLinguagens;
	private String linguagens;
	
	private JList<String> listaTiposDeAplicacao;
	private JList<String> listaTiposDePrograma;
	private DefaultListModel<String> listModelListaTiposDeAplicacao;
	private DefaultListModel<String> listModelListaTiposDePrograma;
	private JButton buttonAdicionarTipoDeAplicacao;
	private JButton buttonRemoverTipoDeAplicacao;
	private JButton buttonAdicionarTipoDePrograma;
	private JButton buttonRemoverTipoDePrograma;
	private LinkedList<String> tiposDeAplicacao;
	private LinkedList<String> tiposDePrograma;
	private JComboBox comboBoxAdicionarTipoDeAplicacao;
	private JComboBox comboBoxAdicionarTipoDePrograma;
	private String[] opcoesTiposDeAplicacao = { "AD01-Administr", "AD02-Função Adm", "AD03-Modern Adm", "AD04-Adm Publ", "AD05-Adm Empres","AD06-Adm Prod","AD07-Adm Pes","AD08-Adm Materl","AD09-Adm Patrim","AD10-Marketing","AD11-Adm Escrit",
												"AG01-Agricultur","AG02-Ciênc Agrl","AG03-Adm Agricl","AG04-Econom Agríc","AG05-Sist agríc","AG06-Eng agrícl","AG07-Edafologia","AG08-Fitopatol","AG09-Prod Veget","AG10-Prod Animl","AG11-Ciênc Flor","AG12-Aquacultur","AG13-Extr Veget","AG14-Extr Animl",
												"AN01-Sociedade","AN02-Desenv soc","AN03-Grupos soc","AN04-Cultura","AN05-Religião","AN06-Antropolog","AN07-Sociologia",
												"AH01-Assen Hum","AH02-Cidade","AH03-Org Territ","AH04-Pol As Hum","AH05-População","AH06-Discip Aux",
												"BL01-Biologia","BL02-Genética","BL03-Citologia","BL04-Microbiolg","BL05-Anatomia","BL06-Fisiologia","BL07-Bioquímica","BL08-Biofísica",
												"BT01-Botânica","BT02-Fitogeograf","BT03-Botân Econ","BT04-Botân Sist",
												"CO01-Filosofia","CO02-Ciência","CO03-Ciênc Ling","CO04-Comunic","CO05-Arte","CO06-História",
												"CC01-Construção","CC02-Proc Const","CC03-Org Constr","CC04-Obra Públ","CC05-Estrutura","CC06-Edificação","CC07-Tecn Const","CC08-Hig Const","CC09-Eng Hidrl","CC10-Solo",
												"DI01-Legislação","DI02-Dir Constl","DI03-Disc Dr.",
												"EL01-Ecologia","EL02-Ecofisiol","EL03-Ecol Human","EL04-EcVeg/Anm","EL05-Etologia",
												"EC01-Economia","EC02-AnMicroec","EC03-TeoMicroe","EC04-AtivEconm","EC05-Contab Nac","EC06-Econ Monet","EC07-Mercado","EC08-Bens Econom","EC09-Eng/Din Ec","EC10-Econ Espec","EC11-Propriedad","EC12-Ec Internac","EC13-Polít Econ","EC14-Empresa",
												"ED01-Ensin Regl","ED02-Ensin-Supl","ED03-Adm/Pr Ens","ED04-Formas Ens","ED05-Currículo","ED06-Educação",
												"EN01-Energia","EN02-Rec Energ","EN03-Combustívl","EN04-Tecn Energ","EN05-Eng Eltrôn","EN06-Eng Nucle",
												"FN01-Finan Públ","FN02-Finan Priv","FN03-Sist Finan","FN04-Rec/Instrum","FN05-Adm Finan","FN06-Contabilid",
												"FQ01-Fís Partíc","FQ02-Acúst/Ótic","FQ03-Onda","FQ04-Metrologia","FQ05-Mecânica","FQ06-Fis Solid","FQ07-Termodinâm","FQ08-Eletrônica","FQ09-Magn/Elmag","FQ10-Fís SupDis","FQ11-Radiação","FQ12-Espectrosc","FQ13-Fís Molecl","FQ14-Química","FQ15-Quím An/Po","FQ16-Fís-Quím","FQ17-Quím Orgân","FQ18-Quím Inorg",
												"GC01-Geog Físic","GC02-Geog Humna","GC03-Geog Regio","GC04-Orient Geo","GC05-Geodesia","GC06-Topografia","GC07-Fotogramet","GC08-Mapeamento","GC09-Met Cartog","GC10-Plan Carto",
												"GL01-Geol Físic","GL02-Glaciolog","GL03-Geotectonc","GL04-Geol Marin","GL05-Geol Hist","GL06-Geol Econ","GL07-GeoQuiFiTe",
												"HB01-Habitação","HB02-Tipol Habt",
												"HD01-Hidrologia","HD02-Hidrograf","HD03-Hidrometr","HD04-Oceanograf",
												"IN01-Indústria","IN02-Tecnologia","IN03-Engenharia","IN04-Ind Ext Mi","IN05-Ind Transf",
												"IF01-Informação","IF02-Documentaç","IF03-Reprograf","IF04-Documento","IF05-Biblioteco","IF06-Arquivolog","IF07-Ciênc Info","IF08-Serv Info","IF09-Uso Inform","IF10-Genérico",
												"MT01-Lógica Mat","MT02-Álgebra","MT03-Geometria","MT04-Anális Mat","MT05-Cálculo","MT06-Mat Aplic",
												"MA01-Meio Amb","MA02-Recurs Nat","MA03-Poluição","MA04-Qualid Amb",
												"ME01-Metodolg","ME02-Atmosfera","ME03-Climatolog",
												"PD01-Pedologia","PD02-Pedogênese","PD03-Tipos de Solo",
												"PL01-Ciênc Pol","PL02-Política",
												"PR01-Previdênc","PR02-Benef Prev","PR03-Assist Soc",
												"PS01-Psicologia","PS02-Comportamt","PS03-Teor Psic",
												"SM01-Saneamento","SM02-Resíduo","SM03-Limpeza","SM04-Abast água","SM05-Esgoto",
												"SD01-Saúde","SD02-Adm Sanit","SD03-Doença","SD04-Defic Fís","SD05-Assist Méd","SD06-Terap Diag","SD07-Medicina","SD08-Espec Med","SD09-Eng Biomed","SD10-Farmacolog","SD11-Odontolog",
												"SV01-Serviços","SV02-Seguro","SV03-Comércio","SV04-Turismo",
												"TC01-Telecom","TC02-Sist Telec","TC03-Eng Telec","TC04-Serv/Redes",
												"TB01-Trabalho","TB02-Rec Human","TB03-Merc Trab","TB04-Cond Trab","TB05-Estr Ocup","TB06-Lazer",
												"TP01-Transporte","TP02 -Sist Trans","TP03-Serv Trans","TP04-Eng Transp","TP05-Mod Transp",
												"UB01-Urbanismo","UB02-Solo urban","UB03-Área urban","UB04-Circ Urban","UB05-Arquitetur"};//serah colocado na combobox
	
	String[] opcoesTipoDePrograma = {"AP01-Aplicativo","AP02-Planejament","AP03-Controle","AP04-Auditoria",
			"AP05-Contabiliz","AT01-Automação","AT02-Atm Escrt","AT03-Atm Comerc","AT04-Atm Bancar",
			"AT05-Atm Indust","AT06-Contr Proc","AT07-Atm Manuf","AT08-Elet Autom","AV01-Aval Desemp",
			"AV02-Cont Recurs","CD01-Com Dados","CD02-Emul Termnl","CD03-Monitor TP","CD04-Ger Dispost",
			"CD05-Ger de Rede","CD06-Rede Local","CT01-Comutação","CT02-Impl Fun Ad","CT03-Ger Op&Man",
			"CT04-Term Op&Man","DS01-Ferrm Desnv","DS02-Gerd Aplic.","DS03-CASE","DS04-Desv c/Metd",
			"DS05-Bib Rotinas","DS06-Apoio Progm","DS07-Sup Documt","DS08-Convers Sis","ET01-Entrtmnto",
			"ET02-Jogos Anim","ET03-Gerad Desen","ET04-Simuladores","FA01-Ferrm Apoio","FA02-Proc Texto",
			"FA03-Planil Elet","FA04-Gerad Gráfc","GI01-Gerenc Info","GI02-Gerenc BD","GI03-Gerad Telas",
			"GI04-Gerad Relat","GI05-Dicion Dad","GI06-Ent Val Dad","GI07-Org Man Arq","GI08-Recup Dados",
			"IA01-Intlg Artf","IA02-Sist Especl","IA03-Proc Lng Nt","IT01-Instrument","IT02-Inst T&M",
			"IT03-Inst Biomd","IT04-Inst Analt","LG01-Linguagem","LG02-Compilador","LG03-Montador",
			"LG04-Pré-Compld","LG05-Comp Cruz","LG06-Pré-Proces","LG07-Interptd","LG08-Ling Procd",
			"LG09-Ling N Prcd","PD01-Seg Prot Dd","PD02-Senha","PD03-Criptograf","PD04-Man Intg Dd",
			"PD05-Cont Acess","SM01-Simul & Mod","SM02-Simulador","SM03-Sim Amb Op","SM04-CAE/CAD/CAM",
			"SO01-Sist Operac","SO02-Interf E&S","SO03-Interf Disc","SO04-Interf Com","SO05-Geren Usuar",
			"SO06-Adm Dispost","SO07-Cont Proces","SO08-Cont Redes","SO09-Proc Comand","TC01-Aplc Tcn Ct",
			"TC02-Pesq Operac","TC03-Recnh Padr","TC04-Proc Imagem","TI01-Teleinform","TI02-Terminais",
			"TI03-Transm Dados","TI04-Comut Dados","UT01-Utilitários","UT02-Compress Dd","UT03-Conv Arq",
			"UT04-Class/Inter","UT05-Cont Spool","UT06-Transf Arq"};
	
	private JDialog dialogParaBarraDeProgresso;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SingletonGuardaProjetoPastasEArquivosSelecionados.getInstance().limparListaSelecionados();
					TelaPrincipal frame = new TelaPrincipal();
					frame.setVisible(true);
					//SingletonBarraDeProgresso.getInstance().tornarBarraDeProgressoInvisivel();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TelaPrincipal() {
		setTitle("code2inpi");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 650, 637);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{12, 92, 42, 42, 92};
		gbl_contentPane.rowHeights = new int[]{19, 19, 19, 19, 19, 19, 19, 19, 19, 19, 19, 0, 19, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{1.0, 1.0, 0.1, 0.1, 0.6};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 1.0, 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
		contentPane.setLayout(gbl_contentPane);
		
		JLabel TituloTela = DefaultComponentFactory.getInstance().createTitle("code2inpi");
		TituloTela.setFont(new Font("Tahoma", Font.BOLD, 15));
		TituloTela.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_TituloTela = new GridBagConstraints();
		gbc_TituloTela.gridwidth = 7;
		gbc_TituloTela.gridheight = 1;
		gbc_TituloTela.insets = new Insets(0, 0, 5, 5);
		gbc_TituloTela.anchor = GridBagConstraints.NORTH;
		gbc_TituloTela.fill = GridBagConstraints.HORIZONTAL;
		gbc_TituloTela.gridx = 0;
		gbc_TituloTela.gridy = 0;
		contentPane.add(TituloTela, gbc_TituloTela);
		
		JLabel descricao_software = DefaultComponentFactory.getInstance().createLabel("Bem vindo ao code2inpi! Escolha seu projeto que converteremos");
		GridBagConstraints gbc_descricao_software = new GridBagConstraints();
		gbc_descricao_software.insets = new Insets(0, 0, 5, 0);
		gbc_descricao_software.gridx = 0;
		gbc_descricao_software.gridy = 1;
		gbc_descricao_software.gridwidth = 8;
		gbc_descricao_software.gridheight = 1;
		contentPane.add(descricao_software, gbc_descricao_software);
		
		JLabel descricao_software2 = DefaultComponentFactory.getInstance().createLabel("o c\u00F3digo fonte para um \u00FAnico PDF!");
		descricao_software2.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_descricao_software2 = new GridBagConstraints();
		gbc_descricao_software2.gridwidth = 8;
		gbc_descricao_software2.gridheight = 1;
		gbc_descricao_software2.insets = new Insets(0, 0, 5, 0);
		gbc_descricao_software2.gridx = 0;
		gbc_descricao_software2.gridy = 2;
		contentPane.add(descricao_software2, gbc_descricao_software2);
		gbc_descricao_software.gridwidth = 1;
		
		//PARTE REFERENTE A ADICIONAR EXTENSÃO(ANDREWS)
		JPanel painel_adicionar_extensao = new JPanel();
		TitledBorder tituloPainelExtensoes;
		tituloPainelExtensoes = BorderFactory.createTitledBorder("Extensões");
		
		JPanel painel_opcoes_projeto = new JPanel();
		TitledBorder tituloPainelProjeto;
		tituloPainelProjeto = BorderFactory.createTitledBorder("Projeto");
		painel_opcoes_projeto.setBorder(tituloPainelProjeto);
		GridBagConstraints gbc_painel_opcoes_projeto = new GridBagConstraints();
		gbc_painel_opcoes_projeto.insets = new Insets(0, 0, 5, 5);
		gbc_painel_opcoes_projeto.fill = GridBagConstraints.BOTH;
		gbc_painel_opcoes_projeto.gridx = 0;
		gbc_painel_opcoes_projeto.gridy = 3;
		gbc_painel_opcoes_projeto.gridwidth = 4;
		gbc_painel_opcoes_projeto.gridheight = 4;
		
		contentPane.add(painel_opcoes_projeto, gbc_painel_opcoes_projeto);
		GridBagLayout gbl_painel_opcoes_projeto = new GridBagLayout();
		gbl_painel_opcoes_projeto.columnWidths = new int[]{0, 0, 0};
		gbl_painel_opcoes_projeto.rowHeights = new int[]{0, 0, 0};
		gbl_painel_opcoes_projeto.columnWeights = new double[]{Double.MIN_VALUE, 1.0};
		gbl_painel_opcoes_projeto.rowWeights = new double[]{0.0, 0.0, 0.0};
		painel_opcoes_projeto.setLayout(gbl_painel_opcoes_projeto);
		
		JLabel lblTtulo = DefaultComponentFactory.getInstance().createLabel("T\u00EDtulo:");
		GridBagConstraints gbc_lblTtulo = new GridBagConstraints();
		gbc_lblTtulo.anchor = GridBagConstraints.EAST;
		gbc_lblTtulo.insets = new Insets(0, 0, 5, 5);
		gbc_lblTtulo.gridx = 0;
		gbc_lblTtulo.gridy = 0;
		painel_opcoes_projeto.add(lblTtulo, gbc_lblTtulo);
		
		campo_nome_projeto = new JTextField();
		GridBagConstraints gbc_campo_preencher_titulo_projeto = new GridBagConstraints();
		gbc_campo_preencher_titulo_projeto.insets = new Insets(0, 0, 5, 5);
		gbc_campo_preencher_titulo_projeto.fill = GridBagConstraints.HORIZONTAL;
		gbc_campo_preencher_titulo_projeto.gridx = 1;
		gbc_campo_preencher_titulo_projeto.gridy = 0;
		painel_opcoes_projeto.add(campo_nome_projeto, gbc_campo_preencher_titulo_projeto);
		campo_nome_projeto.setColumns(10);
		
		JButton botaoHintNomeProjeto = new JButton("?");
		botaoHintNomeProjeto.setToolTipText("Nome do seu projeto");
		GridBagConstraints gbc_explicacao_nome_projeto = new GridBagConstraints();
		gbc_explicacao_nome_projeto.insets = new Insets(0, 0, 5, 0);
		gbc_explicacao_nome_projeto.gridx = 2;
		gbc_explicacao_nome_projeto.gridy = 0;
		painel_opcoes_projeto.add(botaoHintNomeProjeto, gbc_explicacao_nome_projeto);
		
		JLabel lblAutor = DefaultComponentFactory.getInstance().createLabel("Autor:");
		GridBagConstraints gbc_lblAutor = new GridBagConstraints();
		gbc_lblAutor.anchor = GridBagConstraints.EAST;
		gbc_lblAutor.insets = new Insets(0, 0, 5, 5);
		gbc_lblAutor.gridx = 0;
		gbc_lblAutor.gridy = 1;
		painel_opcoes_projeto.add(lblAutor, gbc_lblAutor);
		
		campo_preencher_autor = new JTextField();
		GridBagConstraints gbc_campo_preencher_autor_projeto = new GridBagConstraints();
		gbc_campo_preencher_autor_projeto.insets = new Insets(0, 0, 5, 5);
		gbc_campo_preencher_autor_projeto.fill = GridBagConstraints.HORIZONTAL;
		gbc_campo_preencher_autor_projeto.gridx = 1;
		gbc_campo_preencher_autor_projeto.gridy = 1;
		painel_opcoes_projeto.add(campo_preencher_autor, gbc_campo_preencher_autor_projeto);
		campo_preencher_autor.setColumns(10);
		
		JLabel lblVerso = DefaultComponentFactory.getInstance().createLabel("Vers\u00E3o:");
		GridBagConstraints gbc_lblVerso = new GridBagConstraints();
		gbc_lblVerso.insets = new Insets(0, 0, 0, 5);
		gbc_lblVerso.anchor = GridBagConstraints.EAST;
		gbc_lblVerso.gridx = 0;
		gbc_lblVerso.gridy = 2;
		painel_opcoes_projeto.add(lblVerso, gbc_lblVerso);
		
		campo_preencher_versao = new JTextField();
		GridBagConstraints gbc_campo_preencher_versao_projeto = new GridBagConstraints();
		gbc_campo_preencher_versao_projeto.insets = new Insets(0, 0, 0, 5);
		gbc_campo_preencher_versao_projeto.fill = GridBagConstraints.HORIZONTAL;
		gbc_campo_preencher_versao_projeto.gridx = 1;
		gbc_campo_preencher_versao_projeto.gridy = 2;
		painel_opcoes_projeto.add(campo_preencher_versao, gbc_campo_preencher_versao_projeto);
		campo_preencher_versao.setColumns(10);
		painel_adicionar_extensao.setBorder(tituloPainelExtensoes);
		GridBagConstraints gbc_painel_adicionar_extensao = new GridBagConstraints();
		gbc_painel_adicionar_extensao.insets = new Insets(0, 0, 5, 0);
		gbc_painel_adicionar_extensao.anchor = GridBagConstraints.NORTH;
		gbc_painel_adicionar_extensao.gridheight = 8;
		gbc_painel_adicionar_extensao.gridx = 4;
		gbc_painel_adicionar_extensao.gridy = 3;
		gbc_painel_adicionar_extensao.gridwidth = 4;
		contentPane.add(painel_adicionar_extensao, gbc_painel_adicionar_extensao);
		GridBagLayout gbl_painel_adicionar_extensao = new GridBagLayout();
		gbl_painel_adicionar_extensao.columnWidths = new int[]{20, 20, 20, 20};
		gbl_painel_adicionar_extensao.rowHeights = new int[]{20, 20, 20, 20};
		gbl_painel_adicionar_extensao.columnWeights = new double[]{0.4, 0.4, 0.4, 0.4};
		gbl_painel_adicionar_extensao.rowWeights = new double[]{0.4, 0.4, 0.4, 0.4};
		painel_adicionar_extensao.setLayout(gbl_painel_adicionar_extensao);
		
		
		
		textFieldAdicionarExtensoes = new JTextField();
		GridBagConstraints gbc_textFieldAdicionarExtensoes = new GridBagConstraints();
		gbc_textFieldAdicionarExtensoes.gridwidth = 3;
		gbc_textFieldAdicionarExtensoes.insets = new Insets(0, 0, 0, 5);
		gbc_textFieldAdicionarExtensoes.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldAdicionarExtensoes.gridx = 0;
		gbc_textFieldAdicionarExtensoes.gridy = 3;
		painel_adicionar_extensao.add(textFieldAdicionarExtensoes, gbc_textFieldAdicionarExtensoes);
		textFieldAdicionarExtensoes.setColumns(10);
		
		buttonRemoverExtensoes = new JButton("-");
		GridBagConstraints gbc_buttonRemoverExtensoes = new GridBagConstraints();
		gbc_buttonRemoverExtensoes.insets = new Insets(0, 0, 5, 0);
		gbc_buttonRemoverExtensoes.gridx = 3;
		gbc_buttonRemoverExtensoes.gridy = 0;
		painel_adicionar_extensao.add(buttonRemoverExtensoes, gbc_buttonRemoverExtensoes);
		buttonRemoverExtensoes.addActionListener(this);
		
		
		this.listModel = new DefaultListModel<String>();
		this.listaExtensoes = new JList<String>(listModel);
		
		GridBagConstraints gbc_listaExtensoes = new GridBagConstraints();
		gbc_listaExtensoes.gridheight = 3;
		gbc_listaExtensoes.gridwidth = 4;
		gbc_listaExtensoes.insets = new Insets(0, 0, 5, 25);
		gbc_listaExtensoes.gridx = 0;
		gbc_listaExtensoes.gridy = 0;
		listaExtensoes.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		listaExtensoes.setLayoutOrientation(JList.VERTICAL);
		listaExtensoes.setVisibleRowCount(-1);
		JScrollPane scrollPane = new JScrollPane(listaExtensoes);
		scrollPane.setPreferredSize(new Dimension(80, 120));
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		ListSelectionModel listSelectionModel = listaExtensoes.getSelectionModel();
	    listSelectionModel.addListSelectionListener(
	                            new ListenerListaExtensoes(buttonRemoverExtensoes,listaExtensoes));
		
	    painel_adicionar_extensao.add(scrollPane, gbc_listaExtensoes);
	    
	    buttonAdicionarExtensoes = new JButton("+");
	    GridBagConstraints gbc_buttonAdicionarExtensoes = new GridBagConstraints();
	    gbc_buttonAdicionarExtensoes.gridx = 3;
	    gbc_buttonAdicionarExtensoes.gridy = 3;
	    painel_adicionar_extensao.add(buttonAdicionarExtensoes, gbc_buttonAdicionarExtensoes);
	    buttonAdicionarExtensoes.addActionListener(this);
	    extensoes= new LinkedList<String>();
		TitledBorder tituloPainelArquivos;
		tituloPainelArquivos = BorderFactory.createTitledBorder("Arquivos");
		
		
		
	    extensoes= new LinkedList<String>();

	    //vamos verificar se n jah existem extensoes no arquivo .txt que podemos usar
	    this.verificarSeJaExistemExtensoesNoTxtParaJaPovoarAGuiComEstasExtensoes();
		
	    //PARTE REFERENTE A LINGUAGENS
	    JLabel lblLinguagens = DefaultComponentFactory.getInstance().createLabel("Linguagens:");
		GridBagConstraints gbc_lblLinguagens = new GridBagConstraints();
		gbc_lblLinguagens.insets = new Insets(5, 0, 0, 5);
		gbc_lblLinguagens.anchor = GridBagConstraints.EAST;
		gbc_lblLinguagens.gridx = 0;
		gbc_lblLinguagens.gridy = 3;
		painel_opcoes_projeto.add(lblLinguagens, gbc_lblLinguagens);
		
		textFieldLinguagens = new JTextField();
		GridBagConstraints gbc_textFieldLinguagens = new GridBagConstraints();
		gbc_textFieldLinguagens.insets = new Insets(5, 0, 0, 5);
		gbc_textFieldLinguagens.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldLinguagens.gridx = 1;
		gbc_textFieldLinguagens.gridy = 3;
		painel_opcoes_projeto.add(textFieldLinguagens, gbc_textFieldLinguagens);
		textFieldLinguagens.setColumns(10);
	    
	    //PARTE REFERENTE ao tipo de aplicacao e programa
		JPanel panel_tipo_de_aplicacao_e_programa =  new JPanel();
		GridBagConstraints gbc_panel_tipo_de_aplicacao_e_programa = new GridBagConstraints();
		gbc_panel_tipo_de_aplicacao_e_programa.fill = GridBagConstraints.BOTH;
		gbc_panel_tipo_de_aplicacao_e_programa.gridx = 0;
		gbc_panel_tipo_de_aplicacao_e_programa.gridy = 7;
		gbc_panel_tipo_de_aplicacao_e_programa.insets = new Insets(0, 0, 5, 0);
		gbc_panel_tipo_de_aplicacao_e_programa.anchor = GridBagConstraints.NORTH;
		gbc_panel_tipo_de_aplicacao_e_programa.gridheight = 7;
		gbc_panel_tipo_de_aplicacao_e_programa.gridwidth = 4;
		contentPane.add(panel_tipo_de_aplicacao_e_programa, gbc_panel_tipo_de_aplicacao_e_programa);
		GridBagLayout gbl_panel_tipo_de_aplicacao_e_programa = new GridBagLayout();
		gbl_panel_tipo_de_aplicacao_e_programa.columnWidths = new int[]{20, 20, 20, 20};
		gbl_panel_tipo_de_aplicacao_e_programa.rowHeights = new int[]{20, 20, 20, 20};
		gbl_panel_tipo_de_aplicacao_e_programa.columnWeights = new double[]{0.4, 0.4, 0.4, 0.4};
		gbl_panel_tipo_de_aplicacao_e_programa.rowWeights = new double[]{0.4, 0.4, 0.4, 0.4};
		panel_tipo_de_aplicacao_e_programa.setLayout(gbl_panel_tipo_de_aplicacao_e_programa);
		
		JPanel panel_tipo_de_aplicacao =  new JPanel();
		TitledBorder titulopanel_tipo_de_aplicacao;
		titulopanel_tipo_de_aplicacao = BorderFactory.createTitledBorder("Campos de aplicação");
		panel_tipo_de_aplicacao.setBorder(titulopanel_tipo_de_aplicacao);
		
		GridBagConstraints gbc_panel_tipo_de_aplicacao = new GridBagConstraints();
		gbc_panel_tipo_de_aplicacao.fill = GridBagConstraints.BOTH;
		gbc_panel_tipo_de_aplicacao.gridx = 0;
		gbc_panel_tipo_de_aplicacao.gridy = 0;
		gbc_panel_tipo_de_aplicacao.insets = new Insets(0, 0, 5, 0);
		gbc_panel_tipo_de_aplicacao.anchor = GridBagConstraints.NORTH;
		gbc_panel_tipo_de_aplicacao.gridheight = 7;
		gbc_panel_tipo_de_aplicacao.gridwidth = 2;
		
		GridBagLayout gbl_panel_tipo_de_aplicacao = new GridBagLayout();
		gbl_panel_tipo_de_aplicacao.columnWidths = new int[]{20, 20, 20, 20};
		gbl_panel_tipo_de_aplicacao.rowHeights = new int[]{20, 20, 20, 20};
		gbl_panel_tipo_de_aplicacao.columnWeights = new double[]{0.4, 0.4, 0.4, 0.4};
		gbl_panel_tipo_de_aplicacao.rowWeights = new double[]{0.4, 0.4, 0.4, 0.4};
		panel_tipo_de_aplicacao.setLayout(gbl_panel_tipo_de_aplicacao);
		
		panel_tipo_de_aplicacao_e_programa.add(panel_tipo_de_aplicacao,gbc_panel_tipo_de_aplicacao);
		
		JPanel panel_tipo_de_programa =  new JPanel();
		TitledBorder titulopanel_tipo_de_programa;
		titulopanel_tipo_de_programa = BorderFactory.createTitledBorder("Tipos de programa");
		panel_tipo_de_programa.setBorder(titulopanel_tipo_de_programa);
		GridBagConstraints gbc_panel_tipo_de_programa = new GridBagConstraints();
		gbc_panel_tipo_de_programa.fill = GridBagConstraints.BOTH;
		gbc_panel_tipo_de_programa.gridx = 2;
		gbc_panel_tipo_de_programa.gridy = 0;
		gbc_panel_tipo_de_programa.insets = new Insets(0, 0, 5, 0);
		gbc_panel_tipo_de_programa.anchor = GridBagConstraints.NORTH;
		gbc_panel_tipo_de_programa.gridheight = 7;
		gbc_panel_tipo_de_programa.gridwidth = 2;
		
		GridBagLayout gbl_panel_tipo_de_programa = new GridBagLayout();
		gbl_panel_tipo_de_programa.columnWidths = new int[]{20, 20, 20, 20};
		gbl_panel_tipo_de_programa.rowHeights = new int[]{20, 20, 20, 20};
		gbl_panel_tipo_de_programa.columnWeights = new double[]{0.4, 0.4, 0.4, 0.4};
		gbl_panel_tipo_de_programa.rowWeights = new double[]{0.4, 0.4, 0.4, 0.4};
		panel_tipo_de_programa.setLayout(gbl_panel_tipo_de_programa);
		
		panel_tipo_de_aplicacao_e_programa.add(panel_tipo_de_programa,gbc_panel_tipo_de_programa);
		
		
		this.listModelListaTiposDeAplicacao = new DefaultListModel<String>();
		this.listModelListaTiposDePrograma = new DefaultListModel<String>();
		this.listaTiposDeAplicacao = new JList<String>(listModelListaTiposDeAplicacao);
		this.listaTiposDePrograma = new JList<String>(listModelListaTiposDePrograma);
		
		
		GridBagConstraints gbc_listaTiposDeAplicacao = new GridBagConstraints();
		gbc_listaTiposDeAplicacao.gridheight = 5;
		gbc_listaTiposDeAplicacao.gridwidth = 2;
		gbc_listaTiposDeAplicacao.insets = new Insets(0, 0, 15, 35);
		gbc_listaTiposDeAplicacao.gridx = 0;
		gbc_listaTiposDeAplicacao.gridy = 0;
		listaTiposDeAplicacao.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		listaTiposDeAplicacao.setLayoutOrientation(JList.VERTICAL);
		listaTiposDeAplicacao.setVisibleRowCount(-1);
		JScrollPane scrollPaneListaTiposDeAplicacao = new JScrollPane(listaTiposDeAplicacao);
		scrollPaneListaTiposDeAplicacao.setPreferredSize(new Dimension(120, 100));
		scrollPaneListaTiposDeAplicacao.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollPaneListaTiposDeAplicacao.setHorizontalScrollBarPolicy(
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		ListSelectionModel listSelectionModelListaTiposDeAplicacao = listaTiposDeAplicacao.getSelectionModel();
		
		panel_tipo_de_aplicacao.add(scrollPaneListaTiposDeAplicacao, gbc_listaTiposDeAplicacao);
	    
	    
		buttonRemoverTipoDeAplicacao = new JButton("-");
		GridBagConstraints gbc_buttonRemoverTipoDeAplicacao = new GridBagConstraints();
		gbc_buttonRemoverTipoDeAplicacao.insets = new Insets(15, 0, 0, -50);
		gbc_buttonRemoverTipoDeAplicacao.gridx = 1;
		gbc_buttonRemoverTipoDeAplicacao.gridy = 0;
		panel_tipo_de_aplicacao.add(buttonRemoverTipoDeAplicacao, gbc_buttonRemoverTipoDeAplicacao);
		buttonRemoverTipoDeAplicacao.addActionListener(this);
		
		listSelectionModelListaTiposDeAplicacao.addListSelectionListener(
                new ListenerListaTiposDeAplicacao(buttonRemoverTipoDeAplicacao,listaTiposDeAplicacao));
		
		
	    comboBoxAdicionarTipoDeAplicacao = new JComboBox<String>(this.opcoesTiposDeAplicacao);
	    comboBoxAdicionarTipoDeAplicacao.setSelectedIndex(-1);
	    comboBoxAdicionarTipoDeAplicacao.addActionListener(this);
		GridBagConstraints gbc_textFieldAdicionarTipoDeAplicacao = new GridBagConstraints();
		gbc_textFieldAdicionarTipoDeAplicacao.gridwidth = 2;
		gbc_textFieldAdicionarTipoDeAplicacao.gridx = 0;
		gbc_textFieldAdicionarTipoDeAplicacao.gridy = 5;
		panel_tipo_de_aplicacao.add(comboBoxAdicionarTipoDeAplicacao, gbc_textFieldAdicionarTipoDeAplicacao);
		
		
		buttonAdicionarTipoDeAplicacao = new JButton("+");
	    GridBagConstraints gbc_buttonAdicionarTipoDeAplicacao = new GridBagConstraints();
	    gbc_buttonAdicionarTipoDeAplicacao.gridx = 2;
	    gbc_buttonAdicionarTipoDeAplicacao.gridy = 5;
	    panel_tipo_de_aplicacao.add(buttonAdicionarTipoDeAplicacao, gbc_buttonAdicionarTipoDeAplicacao);
	    buttonAdicionarTipoDeAplicacao.addActionListener(this);
	    tiposDeAplicacao= new LinkedList<String>();
		
		
	    GridBagConstraints gbc_listaTiposDePrograma = new GridBagConstraints();
	    gbc_listaTiposDePrograma.gridheight = 5;
	    gbc_listaTiposDePrograma.gridwidth = 2;
	    gbc_listaTiposDePrograma.insets = new Insets(0, 0, 15, 35);
	    gbc_listaTiposDePrograma.gridx = 0;
	    gbc_listaTiposDePrograma.gridy = 0;
		listaTiposDePrograma.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		listaTiposDePrograma.setLayoutOrientation(JList.VERTICAL);
		listaTiposDePrograma.setVisibleRowCount(-1);
		JScrollPane scrollPaneListaTiposDePrograma = new JScrollPane(listaTiposDePrograma);
		scrollPaneListaTiposDePrograma.setPreferredSize(new Dimension(120, 100));
		scrollPaneListaTiposDePrograma.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollPaneListaTiposDePrograma.setHorizontalScrollBarPolicy(
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		ListSelectionModel listSelectionModelListaTiposDePrograma = listaTiposDePrograma.getSelectionModel();
		
		panel_tipo_de_programa.add(scrollPaneListaTiposDePrograma, gbc_listaTiposDePrograma);
	    
	    
		buttonRemoverTipoDePrograma = new JButton("-");
		GridBagConstraints gbc_buttonRemoverTipoDePrograma = new GridBagConstraints();
		gbc_buttonRemoverTipoDePrograma.insets = new Insets(15, 0, 0, -50);
		gbc_buttonRemoverTipoDePrograma.gridx = 1;
		gbc_buttonRemoverTipoDePrograma.gridy = 0;
		panel_tipo_de_programa.add(buttonRemoverTipoDePrograma, gbc_buttonRemoverTipoDePrograma);
		buttonRemoverTipoDePrograma.addActionListener(this);
		
		listSelectionModelListaTiposDePrograma.addListSelectionListener(
                new ListenerListaTiposDePrograma(buttonRemoverTipoDePrograma,listaTiposDePrograma));
		
		comboBoxAdicionarTipoDePrograma = new JComboBox<String>(this.opcoesTipoDePrograma);
		comboBoxAdicionarTipoDePrograma.setSelectedIndex(-1);
		comboBoxAdicionarTipoDePrograma.addActionListener(this);
		GridBagConstraints gbc_textFieldAdicionarTipoDePrograma = new GridBagConstraints();
		gbc_textFieldAdicionarTipoDePrograma.gridwidth = 2;
		gbc_textFieldAdicionarTipoDePrograma.gridx = 0;
		gbc_textFieldAdicionarTipoDePrograma.gridy = 5;
		panel_tipo_de_programa.add(comboBoxAdicionarTipoDePrograma, gbc_textFieldAdicionarTipoDePrograma);
		
		buttonAdicionarTipoDePrograma = new JButton("+");
	    GridBagConstraints gbc_buttonAdicionarTipoDePrograma = new GridBagConstraints();
	    gbc_buttonAdicionarTipoDePrograma.gridx = 2;
	    gbc_buttonAdicionarTipoDePrograma.gridy = 5;
	    panel_tipo_de_programa.add(buttonAdicionarTipoDePrograma, gbc_buttonAdicionarTipoDePrograma);
	    buttonAdicionarTipoDePrograma.addActionListener(this);
	    tiposDePrograma= new LinkedList<String>();
		
		
		
		
		//FIM da parte referente ao tipo de aplicacao e programa
		
		JPanel painel_arquivos = new JPanel();
		painel_arquivos.setBorder(tituloPainelArquivos);
		GridBagConstraints gbc_painel_arquivos = new GridBagConstraints();
		gbc_painel_arquivos.gridheight = 3;
		gbc_painel_arquivos.gridwidth = 4;
		gbc_painel_arquivos.insets = new Insets(0, 0, 5, 5);
		gbc_painel_arquivos.fill = GridBagConstraints.BOTH;
		gbc_painel_arquivos.gridx = 0;
		gbc_painel_arquivos.gridy = 16;
		contentPane.add(painel_arquivos, gbc_painel_arquivos);
		GridBagLayout gbl_painel_arquivos = new GridBagLayout();
		gbl_painel_arquivos.columnWidths = new int[]{0, 0, 0, 0, 0};
		gbl_painel_arquivos.rowHeights = new int[]{0, 0, 0, 0};
		gbl_painel_arquivos.columnWeights = new double[]{0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_painel_arquivos.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		painel_arquivos.setLayout(gbl_painel_arquivos);
		
		JLabel lblDiretrio = DefaultComponentFactory.getInstance().createLabel("Diret\u00F3rio:");
		GridBagConstraints gbc_lblDiretrio = new GridBagConstraints();
		gbc_lblDiretrio.insets = new Insets(0, 0, 5, 5);
		gbc_lblDiretrio.anchor = GridBagConstraints.EAST;
		gbc_lblDiretrio.gridx = 0;
		gbc_lblDiretrio.gridy = 0;
		painel_arquivos.add(lblDiretrio, gbc_lblDiretrio);
		
		campo_preencher_diretorio = new JTextField();
		GridBagConstraints gbc_campo_preencher_diretorio = new GridBagConstraints();
		gbc_campo_preencher_diretorio.insets = new Insets(0, 0, 5, 5);
		gbc_campo_preencher_diretorio.fill = GridBagConstraints.HORIZONTAL;
		gbc_campo_preencher_diretorio.gridx = 1;
		gbc_campo_preencher_diretorio.gridy = 0;
		painel_arquivos.add(campo_preencher_diretorio, gbc_campo_preencher_diretorio);
		campo_preencher_diretorio.setColumns(10);
		
		JButton botao_selecionar_pasta_projeto = new JButton("...");
		botao_selecionar_pasta_projeto.setAction(acaoSelecionarPastaProjeto);
		GridBagConstraints gbc_botao_escolher_diretorio_projeto = new GridBagConstraints();
		gbc_botao_escolher_diretorio_projeto.insets = new Insets(0, 0, 5, 5);
		gbc_botao_escolher_diretorio_projeto.gridx = 2;
		gbc_botao_escolher_diretorio_projeto.gridy = 0;
		painel_arquivos.add(botao_selecionar_pasta_projeto, gbc_botao_escolher_diretorio_projeto);
		
		JButton botao_explicacao_selecione_diretorio = new JButton("?");
		botao_explicacao_selecione_diretorio.setToolTipText("escolha a pasta raiz do seu projeto");
		GridBagConstraints gbc_botao_instrucao_escolher_diretorio = new GridBagConstraints();
		gbc_botao_instrucao_escolher_diretorio.insets = new Insets(0, 0, 5, 0);
		gbc_botao_instrucao_escolher_diretorio.gridx = 3;
		gbc_botao_instrucao_escolher_diretorio.gridy = 0;
		painel_arquivos.add(botao_explicacao_selecione_diretorio, gbc_botao_instrucao_escolher_diretorio);
		
		JLabel lblOutput = DefaultComponentFactory.getInstance().createLabel("Output:");
		GridBagConstraints gbc_lblOutput = new GridBagConstraints();
		gbc_lblOutput.anchor = GridBagConstraints.EAST;
		gbc_lblOutput.insets = new Insets(0, 0, 5, 5);
		gbc_lblOutput.gridx = 0;
		gbc_lblOutput.gridy = 1;
		painel_arquivos.add(lblOutput, gbc_lblOutput);
		
		campo_preencher_output = new JTextField();
		GridBagConstraints gbc_campo_preencher_output = new GridBagConstraints();
		gbc_campo_preencher_output.insets = new Insets(0, 0, 5, 5);
		gbc_campo_preencher_output.fill = GridBagConstraints.HORIZONTAL;
		gbc_campo_preencher_output.gridx = 1;
		gbc_campo_preencher_output.gridy = 1;
		painel_arquivos.add(campo_preencher_output, gbc_campo_preencher_output);
		campo_preencher_output.setColumns(10);
		
		JButton botao_especificar_arquivo_output = new JButton("...");
		botao_especificar_arquivo_output.setAction(acaoEspecificarOutput);
		GridBagConstraints gbc_botao_especificar_output = new GridBagConstraints();
		gbc_botao_especificar_output.insets = new Insets(0, 0, 5, 5);
		gbc_botao_especificar_output.gridx = 2;
		gbc_botao_especificar_output.gridy = 1;
		painel_arquivos.add(botao_especificar_arquivo_output, gbc_botao_especificar_output);
		
		JButton botaoEspecificarPastas = new JButton("Avan\u00E7ado...");
		botaoEspecificarPastas.setAction(acaoBotaoEspecificarPastasArquivosProjeto);
		GridBagConstraints gbc_botao_especificar_pastas = new GridBagConstraints();
		gbc_botao_especificar_pastas.insets = new Insets(0, 0, 0, 5);
		gbc_botao_especificar_pastas.gridx = 1;
		gbc_botao_especificar_pastas.gridy = 2;
		painel_arquivos.add(botaoEspecificarPastas, gbc_botao_especificar_pastas);
		
		JButton botao_instrucoes_avancado = new JButton("?");
		botao_instrucoes_avancado.setToolTipText("especificar que pastas/arquivos de seu projeto você quer no PDF");
		GridBagConstraints gbc_botao_instrucoes_avancado = new GridBagConstraints();
		gbc_botao_instrucoes_avancado.insets = new Insets(0, 0, 0, 5);
		gbc_botao_instrucoes_avancado.gridx = 2;
		gbc_botao_instrucoes_avancado.gridy = 2;
		painel_arquivos.add(botao_instrucoes_avancado, gbc_botao_instrucoes_avancado);
		
		JButton botaoGerarPDF = new JButton("Gerar PDF");
		botaoGerarPDF.setAction(acaoGerarPdf);
		GridBagConstraints gbc_botaoGerarPDF = new GridBagConstraints();
		gbc_botaoGerarPDF.gridheight = 2;
		gbc_botaoGerarPDF.gridwidth = 2;
		gbc_botaoGerarPDF.insets = new Insets(0, 0, 5, 5);
		gbc_botaoGerarPDF.gridx = 1;
		gbc_botaoGerarPDF.gridy = 20;
		contentPane.add(botaoGerarPDF, gbc_botaoGerarPDF);
		
		JProgressBar barraDeProgresso = new JProgressBar();
		/*GridBagConstraints gbc_JProgressBarUsuarioClicouNoBotaoAvancado = new GridBagConstraints();
		gbc_JProgressBarUsuarioClicouNoBotaoAvancado.insets = new Insets(0, 0, 5, 5);
		gbc_JProgressBarUsuarioClicouNoBotaoAvancado.gridheight = 1;
		gbc_JProgressBarUsuarioClicouNoBotaoAvancado.gridwidth = 2;
		gbc_JProgressBarUsuarioClicouNoBotaoAvancado.gridx = 1;
		gbc_JProgressBarUsuarioClicouNoBotaoAvancado.gridy = 22;*/

		
		//contentPane.add(barraDeProgresso, gbc_JProgressBarUsuarioClicouNoBotaoAvancado);
		SingletonBarraDeProgresso.getInstance().setBarraDeProgresso(barraDeProgresso);
		
		textoBarraDeProgresso = new JLabel("                                                                                 ",SwingConstants.CENTER);
		/*GridBagConstraints gbc_textoBarraDeProgresso = new GridBagConstraints();
		gbc_textoBarraDeProgresso.insets = new Insets(0, 0, 0, 5);
		gbc_textoBarraDeProgresso.gridheight = 1;
		gbc_textoBarraDeProgresso.gridwidth = 2;
		gbc_textoBarraDeProgresso.gridx = 1;
		gbc_textoBarraDeProgresso.gridy = 23;
		contentPane.add(textoBarraDeProgresso, gbc_textoBarraDeProgresso);*/
		textoBarraDeProgresso.setText("                                                                                        ");
		textoBarraDeProgresso.setVisible(true);
		SingletonBarraDeProgresso.getInstance().setTextoBarraDeProgresso(textoBarraDeProgresso);
		
		this.dialogParaBarraDeProgresso = new JDialog();     
		this.dialogParaBarraDeProgresso.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.dialogParaBarraDeProgresso.setTitle("Carregando...");
		JPanel painelProDialog = new JPanel();
		painelProDialog.setPreferredSize(new Dimension(300,100));
		
		GridBagLayout gbl_painelProDialog = new GridBagLayout();
		painelProDialog.setLayout(gbl_painelProDialog);
		
		
		GridBagConstraints gbc_barra_progresso = new GridBagConstraints();
		gbc_barra_progresso.gridx = 0;
		gbc_barra_progresso.gridy = 0;
		gbc_barra_progresso.gridheight = 2;
		gbc_barra_progresso.gridwidth = 3;
		
		painelProDialog.add(barraDeProgresso, gbc_barra_progresso);
		
		GridBagConstraints gbc_texto_progresso = new GridBagConstraints();
		gbc_texto_progresso.gridx = 0;
		gbc_texto_progresso.gridy = 2;
		gbc_texto_progresso.gridheight = 1;
		gbc_texto_progresso.gridwidth = 1;
		
		
		painelProDialog.add(textoBarraDeProgresso,gbc_texto_progresso);
		
		this.dialogParaBarraDeProgresso.add(painelProDialog);

		this.dialogParaBarraDeProgresso.pack();
		//this.dialogParaBarraDeProgresso.setLocationByPlatform(true);
		this.dialogParaBarraDeProgresso.setVisible(true);
		this.dialogParaBarraDeProgresso.setLocationRelativeTo(null);
		
		SingletonBarraDeProgresso.getInstance().setDialogParaBarraDeProgresso(dialogParaBarraDeProgresso);
	}
	
	public void actionPerformed(ActionEvent e) 
	{
		if (e.getSource() == this.buttonRemoverExtensoes)
		{
			int index = listaExtensoes.getSelectedIndex();
		    this.listModel.remove(index);
		    this.extensoes.remove(index);

		    int size = this.listModel.getSize();

		    if (size == 0) { //Nao tem nenhuma extensao. Desabilitar remover.
		        buttonRemoverExtensoes.setEnabled(false);

		    } else { //Select an index.
		        if (index == this.listModel.getSize()) {
		            //removed item in last position
		            index--;
		        }

		        listaExtensoes.setSelectedIndex(index);
		        listaExtensoes.ensureIndexIsVisible(index);
		    }
		}
		else if (e.getSource() == this.buttonAdicionarExtensoes)
		{
			//antes de adicionar, serah que a linkedlist existe?
			if(this.extensoes == null)
			{
				this.extensoes = new LinkedList<String>();
			}
			
			String novaExtensao = textFieldAdicionarExtensoes.getText();

		    //Usuario n digitou uma extensao valida...
		    if (novaExtensao.equals("") || jaExisteEstaExtensao(novaExtensao)) 
		    {
		    	JOptionPane.showMessageDialog(this, "Digite uma extensão válida e que não já esteja adicionada");
		        return;
		    }
		    
		    //usuario pode ter usado uma extensao com . tipo .java. Vamos tirar os pontos
		    String novaExtensaoSemPonto = novaExtensao.replace(".", "");

		    //coloca no fim da lista
		    listModel.insertElementAt(novaExtensaoSemPonto, this.listModel.getSize());
		    this.extensoes.add(novaExtensaoSemPonto);
		    

		    //Reset the text field.
		    textFieldAdicionarExtensoes.requestFocusInWindow();
		    textFieldAdicionarExtensoes.setText("");

		}
		else if(e.getSource() == this.buttonAdicionarTipoDeAplicacao)
		{
			String novoTipo = (String)comboBoxAdicionarTipoDeAplicacao.getSelectedItem();
			
			if(novoTipo.length() > 0)
			{
				boolean jaExisteEsteItemNaLista = false;
				for(int i = 0; i < this.tiposDeAplicacao.size(); i++)
				{
					String umItem = this.tiposDeAplicacao.get(i);
					if(umItem.compareTo(novoTipo) == 0)
					{
						jaExisteEsteItemNaLista = true;
						break;
					}
				}
				
				if(jaExisteEsteItemNaLista == false)
				{
					this.tiposDeAplicacao.add(novoTipo);
					//coloca no fim da lista
				    listModelListaTiposDeAplicacao.insertElementAt(novoTipo, this.listModelListaTiposDeAplicacao.getSize());
				}
			}
		}
		else if(e.getSource() == this.buttonAdicionarTipoDePrograma)
		{
			String novoTipo = (String)comboBoxAdicionarTipoDePrograma.getSelectedItem();
			
			if(novoTipo.length() > 0)
			{
				boolean jaExisteEsteItemNaLista = false;
				for(int i = 0; i < this.tiposDePrograma.size(); i++)
				{
					String umItem = this.tiposDePrograma.get(i);
					if(umItem.compareTo(novoTipo) == 0)
					{
						jaExisteEsteItemNaLista = true;
						break;
					}
				}
				
				if(jaExisteEsteItemNaLista == false)
				{
					this.tiposDePrograma.add(novoTipo);
					//coloca no fim da lista
				    listModelListaTiposDePrograma.insertElementAt(novoTipo, this.listModelListaTiposDePrograma.getSize());
				}
			}
		}
		else if(e.getSource() == this.buttonRemoverTipoDeAplicacao)
		{
			int index = listaTiposDeAplicacao.getSelectedIndex();
		    this.listModelListaTiposDeAplicacao.remove(index);
		    this.tiposDeAplicacao.remove(index);

		    int size = this.listModelListaTiposDeAplicacao.getSize();

		    if (size == 0) { //Nao tem nenhuma aplicacao. Desabilitar remover.
		        buttonRemoverTipoDeAplicacao.setEnabled(false);

		    } else { //Select an index.
		        if (index == this.listModelListaTiposDeAplicacao.getSize()) {
		            //removed item in last position
		            index--;
		        }

		        listaTiposDeAplicacao.setSelectedIndex(index);
		        listaTiposDeAplicacao.ensureIndexIsVisible(index);
		    }
		}
		else if(e.getSource() == this.buttonRemoverTipoDePrograma)
		{
			int index = listaTiposDePrograma.getSelectedIndex();
		    this.listModelListaTiposDePrograma.remove(index);
		    this.tiposDePrograma.remove(index);

		    int size = this.listModelListaTiposDePrograma.getSize();

		    if (size == 0) { //Nao tem nenhuma aplicacao. Desabilitar remover.
		        buttonRemoverTipoDePrograma.setEnabled(false);

		    } else { //Select an index.
		        if (index == this.listModelListaTiposDePrograma.getSize()) {
		            //removed item in last position
		            index--;
		        }

		        listaTiposDePrograma.setSelectedIndex(index);
		        listaTiposDePrograma.ensureIndexIsVisible(index);
		    }
		}
			
	    
	}
	
	private boolean jaExisteEstaExtensao(String extensao)
	{
		for(int i = 0; i < this.extensoes.size(); i++)
		{
			String umaExtensao = extensoes.get(i);
			if(umaExtensao.compareTo(extensao) == 0)
			{
				return true;
			}
		}
		
		return false;
	}
	
	//vamos verificar se existe um arquivo extensoes.txt. Se sim, verificaremos se existem extensoes nele e mostrar ao usuario 
		private void verificarSeJaExistemExtensoesNoTxtParaJaPovoarAGuiComEstasExtensoes()
		{
			CriaeLeArquivoConfiguracoesdat conheceOArquivoComAsExtensoes = new CriaeLeArquivoConfiguracoesdat();
			LinkedList<String> extensoesDoArquivo = conheceOArquivoComAsExtensoes.pegarExtensoesNoTxtExtensoes();
			
			if(extensoesDoArquivo.size() > 0)
			{
				//ja existia alguma extensao. Vamos usa-las
				
				if(this.extensoes == null)
				{
					this.extensoes = new LinkedList<String>();
				}
				
				for(int j = 0; j < extensoesDoArquivo.size(); j++)
				{
					String umaExtensao = extensoesDoArquivo.get(j);
					if (jaExisteEstaExtensao(umaExtensao) == false) 
				    {
						this.extensoes.add(umaExtensao);
						listModel.insertElementAt(umaExtensao, this.listModel.getSize());
				    }
				}
				
			}
		}
	

	private class AcaoEspecificarPastasEArquivosProjeto extends AbstractAction {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		public AcaoEspecificarPastasEArquivosProjeto() {
			putValue(NAME, "Avançado...");
		}
		public void actionPerformed(ActionEvent e) {
			//mudar o cursor para loading
			TelaPrincipal.this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
			String [] arrayComURlDoProjeto = new String [2];
			arrayComURlDoProjeto[0] = campo_preencher_diretorio.getText();
			if(arrayComURlDoProjeto[0].length() > 0 )
			{
				File pastaDoProjeto = SingletonGuardaProjetoPastasEArquivosSelecionados.getInstance().getPastaDoProjeto();
				arrayComURlDoProjeto[1] = pastaDoProjeto.getName();
				
				//antes de chamar o main, vamos checar quantos arquivos tem na pasta p dar nocao de tamanho ao usuario
				tamanhoMaximoDaBarraDeProgresso = 0;
				pegaQuantosArquivosTemNoDiretorio(pastaDoProjeto.getPath());
				String textoBarraDeProgresso = "Carregando arquivos da pasta selecionada...";
				SingletonBarraDeProgresso.getInstance().inicializarBarraDeProgresso(tamanhoMaximoDaBarraDeProgresso,textoBarraDeProgresso);
				SeletorPastasProjeto seletorPastasProjeto = new SeletorPastasProjeto();
				seletorPastasProjeto.setExtensoesEspecificadas(extensoes);
				seletorPastasProjeto.main(arrayComURlDoProjeto);
			}
			else
			{
				JOptionPane.showMessageDialog(TelaPrincipal.this, "Especifique uma pasta do projeto primeiro.");
			}
			//voltar o cursor ao normal
			TelaPrincipal.this.setCursor(Cursor.getDefaultCursor());
			SingletonBarraDeProgresso.getInstance().tornarBarraDeProgressoInvisivel();
			
		}
	}
	private class AcaoSelecionarProjeto extends AbstractAction {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		public AcaoSelecionarProjeto() {
			escolhedorPastaProjeto = new JFileChooser();
			putValue(NAME, "...");
		}
		public void actionPerformed(ActionEvent e) {
			//mudar o cursor para loading
			TelaPrincipal.this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
			//In response to a button click:
			SingletonGuardaProjetoPastasEArquivosSelecionados.getInstance().limparListaSelecionados();
			escolhedorPastaProjeto.setFileSelectionMode( JFileChooser.DIRECTORIES_ONLY);
			int returnVal = escolhedorPastaProjeto.showOpenDialog(TelaPrincipal.this);
			 if (returnVal == JFileChooser.APPROVE_OPTION) {
		            File pastaDoProjeto = escolhedorPastaProjeto.getSelectedFile();
		            //This is where a real application would open the file.
		            System.out.println("Opening: " + pastaDoProjeto.getPath());
		            SingletonGuardaProjetoPastasEArquivosSelecionados.getInstance().setPastaDoProjeto(pastaDoProjeto);
		            campo_preencher_diretorio.setText(pastaDoProjeto.getPath());
		        } else {
		            System.out.println("Open command cancelled by user.");
		            
		        }
			//voltar o cursor ao normal
			TelaPrincipal.this.setCursor(Cursor.getDefaultCursor());
		}
	}
	
	
	
	private class AcaoGerarPdf extends AbstractAction {
		public AcaoGerarPdf() {
			putValue(NAME, "Gerar PDF");
		}
		public void actionPerformed(ActionEvent e) {
			//mudar o cursor para loading
			TelaPrincipal.this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
			
			//antes de tudo, vamos fazer o valor do singletonpdfgeradocomsucesso ser true
			SingletonPDFGeradoComSucessoDeveSerMostrado.getInstance().setDeveSerMostrado(true);
			
			//primeiro checar se usuário preencheu todos os campos
			LinkedList<String> camposNaoPreenchidos = usuarioEsqueceuDePreencherQueCampos();
			if(camposNaoPreenchidos.size() > 0)
			{
				//usuario esqueceu de preencher algguns campos
				String errorMessage = "Erro- Falta preencher os seguintes campos: ";
				for(int i = 0; i < camposNaoPreenchidos.size(); i++)
				{
					String umCampoNaoPreenchido = camposNaoPreenchidos.get(i);
					errorMessage = errorMessage + umCampoNaoPreenchido;
					if(i + 1 < camposNaoPreenchidos.size())
					{
						errorMessage = errorMessage + " , ";
					}
				}
				JOptionPane.showMessageDialog(TelaPrincipal.this, errorMessage);
				TelaPrincipal.this.setCursor(Cursor.getDefaultCursor());
				
			}
			else
			{
				//usuário preencheu todos os campos!
				Main main = new Main();

				SingletonGuardaProjetoPastasEArquivosSelecionados guardaDadosProjetoGerarPdf = SingletonGuardaProjetoPastasEArquivosSelecionados.getInstance();
				File pastaRaizDoProjeto = guardaDadosProjetoGerarPdf.getPastaDoProjeto();
				String nomeDiretorioRaizProjeto = pastaRaizDoProjeto.getName();
				String nomeDosAutoresSeparadosPorVirgula = campo_preencher_autor.getText();
				String versaoDoProjeto = campo_preencher_versao.getText();
				String urlOutputProjeto = campo_preencher_output.getText();
				String linguagens = textFieldLinguagens.getText();
				Main.outputFILE2 = urlOutputProjeto;
				Main.outputFILE = urlOutputProjeto;
				String tituloProjeto = campo_nome_projeto.getText();
				main.gerarPDFParaRegistroDeSoftware(extensoes, tituloProjeto,nomeDiretorioRaizProjeto, versaoDoProjeto, nomeDosAutoresSeparadosPorVirgula, linguagens,tiposDeAplicacao,tiposDePrograma);
				//voltar o cursor ao normal
				TelaPrincipal.this.setCursor(Cursor.getDefaultCursor());
				
				if(SingletonPDFGeradoComSucessoDeveSerMostrado.getInstance().getDeveSerMostrado() == true)
				{
					JOptionPane.showMessageDialog(TelaPrincipal.this, "Arquivo PDF gerado com sucesso!");
					//faltou soh colocar num arquivo de configuracoes as extensoes que usamos, isso se o usuario quiser
					CriaeLeArquivoConfiguracoesdat criaExtensoesdat = new CriaeLeArquivoConfiguracoesdat();
					criaExtensoesdat.criarArquivoConfiguracoesdat(extensoes);
				}
				else
				{
					//tinha algum id q tornou o projeto incapaz de gerar numero de paginas e o usuario decidiun gerar o pdf
				}
			}

			
		}
		
		public LinkedList<String> usuarioEsqueceuDePreencherQueCampos()
		{
			LinkedList<String> camposNaoPreenchidos = new LinkedList<String>();
			if(campo_nome_projeto.getText().length() <= 0)
			{
				camposNaoPreenchidos.add("título");
			}
			SingletonGuardaProjetoPastasEArquivosSelecionados guardaDadosProjetoGerarPdf = SingletonGuardaProjetoPastasEArquivosSelecionados.getInstance();
			File pastaRaizDoProjeto = guardaDadosProjetoGerarPdf.getPastaDoProjeto();
			if(pastaRaizDoProjeto == null)
			{
				camposNaoPreenchidos.add("diretório");
			}
			if(campo_preencher_autor.getText().length() <= 0)
			{
				camposNaoPreenchidos.add("autor");
			}
			if(campo_preencher_versao.getText().length() <= 0)
			{
				camposNaoPreenchidos.add("versão");
			}
			if(campo_preencher_output.getText().length() <= 0)
			{
				camposNaoPreenchidos.add("output");
			}
			if(extensoes.size() <=0)
			{
				camposNaoPreenchidos.add("extensões");
			}
			
			if(textFieldLinguagens.getText().length() <= 0)
			{
				camposNaoPreenchidos.add("linguagens");
			}
			
			if(tiposDeAplicacao == null || tiposDeAplicacao.size() == 0)
			{
				camposNaoPreenchidos.add("campos de aplicação");
			}
			
			if(tiposDePrograma == null || tiposDePrograma.size() == 0)
			{
				camposNaoPreenchidos.add("tipos de programa");
			}
			
			
			return camposNaoPreenchidos;
			
		}
	}
	private class AcaoEspecificarOutput extends AbstractAction {
		public AcaoEspecificarOutput() {
			escolhedorOutputProjeto= new JFileChooser();
			putValue(NAME, "...");
		}
		public void actionPerformed(ActionEvent e) {
			//mudar o cursor para loading
			TelaPrincipal.this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
			escolhedorOutputProjeto.setSelectedFile(new File("meuProjeto.pdf"));
			//In response to a button click:
			escolhedorOutputProjeto.addChoosableFileFilter(new FileFilter() {
				 
			    public String getDescription() {
			        return "Arquivos PDF (*.pdf)";
			    }
			 
			    public boolean accept(File f) {
			        if (f.isDirectory()) {
			            return true;
			        } else {
			            return f.getName().toLowerCase().endsWith(".pdf");
			        }
			    }
			});
			escolhedorOutputProjeto.setFileSelectionMode( JFileChooser.FILES_ONLY);
			int returnVal = escolhedorOutputProjeto.showSaveDialog(TelaPrincipal.this);
			 if (returnVal == JFileChooser.APPROVE_OPTION) {
		            File pdfDoProjeto = escolhedorOutputProjeto.getSelectedFile();
		            //This is where a real application would open the file.
		            System.out.println("output: " + pdfDoProjeto.getPath());
		            SingletonGuardaProjetoPastasEArquivosSelecionados.getInstance().setOutputSelecionado(pdfDoProjeto);
		            campo_preencher_output.setText(pdfDoProjeto.getPath());
		        } else {
		            System.out.println("Select output command cancelled by user.");
		            
		        }
			//voltar o cursor ao normal
			TelaPrincipal.this.setCursor(Cursor.getDefaultCursor());
		}
	}
	
	
	private int pegaQuantosArquivosTemNoDiretorio(String dirPath) {
	    File f = new File(dirPath);
	    File[] files = f.listFiles();

	    if (files != null)
	    for (int i = 0; i < files.length; i++) {
	    	tamanhoMaximoDaBarraDeProgresso++;
	        File file = files[i];

	        if (file.isDirectory()) {   
	        	pegaQuantosArquivosTemNoDiretorio(file.getAbsolutePath()); 
	        }
	    }
	    
	    return tamanhoMaximoDaBarraDeProgresso;
	}
	
	public void alterarTextoBarraProgresso(String texto)
	{
		this.textoBarraDeProgresso.setText(texto);
	}
	
	public void setVisibletextoBarraDeProgresso()
	{
		this.textoBarraDeProgresso.setVisible(true);
	}
}
