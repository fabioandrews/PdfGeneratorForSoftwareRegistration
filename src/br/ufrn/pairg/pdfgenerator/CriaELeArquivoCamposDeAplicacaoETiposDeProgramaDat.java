package br.ufrn.pairg.pdfgenerator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.LinkedList;

public class CriaELeArquivoCamposDeAplicacaoETiposDeProgramaDat 
{
	private String[] opcoesTiposDeAplicacaoDefault = { "AD01 - Administr", "AD02 - Fun��o Adm", "AD03 - Modern Adm", "AD04 - Adm Publ", "AD05 - Adm Empres","AD06 - Adm Prod","AD07 - Adm Pes","AD08 - Adm Materl","AD09 - Adm Patrim","AD10 - Marketing","AD11 - Adm Escrit",
			"AG01 - Agricultur","AG02 - Ci�nc Agrl","AG03 - Adm Agricl","AG04 - Econom Agr�c","AG05 - Sist agr�c","AG06 - Eng agr�cl","AG07 - Edafologia","AG08 - Fitopatol","AG09 - Prod Veget","AG10 - Prod Animl","AG11 - Ci�nc Flor","AG12 - Aquacultur","AG13 - Extr Veget","AG14 - Extr Animl",
			"AN01 - Sociedade","AN02 - Desenv soc","AN03 - Grupos soc","AN04 - Cultura","AN05 - Religi�o","AN06 - Antropolog","AN07 - Sociologia",
			"AH01 - Assen Hum","AH02 - Cidade","AH03 - Org Territ","AH04 - Pol As Hum","AH05 - Popula��o","AH06 - Discip Aux",
			"BL01 - Biologia","BL02 - Gen�tica","BL03 - Citologia","BL04 - Microbiolg","BL05 - Anatomia","BL06 - Fisiologia","BL07 - Bioqu�mica","BL08 - Biof�sica",
			"BT01 - Bot�nica","BT02 - Fitogeograf","BT03 - Bot�n Econ","BT04 - Bot�n Sist",
			"CO01 - Filosofia","CO02 - Ci�ncia","CO03 - Ci�nc Ling","CO04 - Comunic","CO05 - Arte","CO06 - Hist�ria",
			"CC01 - Constru��o","CC02 - Proc Const","CC03 - Org Constr","CC04 - Obra P�bl","CC05 - Estrutura","CC06 - Edifica��o","CC07 - Tecn Const","CC08 - Hig Const","CC09 - Eng Hidrl","CC10 - Solo",
			"DI01 - Legisla��o","DI02 - Dir Constl","DI03 - Disc Dr.",
			"EL01 - Ecologia","EL02 - Ecofisiol","EL03 - Ecol Human","EL04 - EcVeg/Anm","EL05 - Etologia",
			"EC01 - Economia","EC02 - AnMicroec","EC03 - TeoMicroe","EC04 - AtivEconm","EC05 - Contab Nac","EC06 - Econ Monet","EC07 - Mercado","EC08 - Bens Econom","EC09 - Eng/Din Ec","EC10 - Econ Espec","EC11 - Propriedad","EC12 - Ec Internac","EC13 - Pol�t Econ","EC14 - Empresa",
			"ED01  -  Ensin Regl","ED02 - Ensin - Supl","ED03 - Adm/Pr Ens","ED04 - Formas Ens","ED05 - Curr�culo","ED06 - Educa��o",
			"EN01  -  Energia","EN02 - Rec Energ","EN03 - Combust�vl","EN04 - Tecn Energ","EN05 - Eng Eltr�n","EN06 - Eng Nucle",
			"FN01  -  Finan P�bl","FN02 - Finan Priv","FN03 - Sist Finan","FN04 - Rec/Instrum","FN05 - Adm Finan","FN06 - Contabilid",
			"FQ01  -  F�s Part�c","FQ02 - Ac�st/�tic","FQ03 - Onda","FQ04 - Metrologia","FQ05 - Mec�nica","FQ06 - Fis Solid","FQ07 - Termodin�m","FQ08 - Eletr�nica","FQ09 - Magn/Elmag","FQ10 - F�s SupDis","FQ11 - Radia��o","FQ12 - Espectrosc","FQ13 - F�s Molecl","FQ14 - Qu�mica","FQ15 - Qu�m An/Po","FQ16 - F�s - Qu�m","FQ17 - Qu�m Org�n","FQ18 - Qu�m Inorg",
			"GC01  -  Geog F�sic","GC02 - Geog Humna","GC03 - Geog Regio","GC04 - Orient Geo","GC05 - Geodesia","GC06 - Topografia","GC07 - Fotogramet","GC08 - Mapeamento","GC09 - Met Cartog","GC10 - Plan Carto",
			"GL01  -  Geol F�sic","GL02 - Glaciolog","GL03 - Geotectonc","GL04 - Geol Marin","GL05 - Geol Hist","GL06 - Geol Econ","GL07 - GeoQuiFiTe",
			"HB01  -  Habita��o","HB02 - Tipol Habt",
			"HD01  -  Hidrologia","HD02 - Hidrograf","HD03 - Hidrometr","HD04 - Oceanograf",
			"IN01  -  Ind�stria","IN02 - Tecnologia","IN03 - Engenharia","IN04 - Ind Ext Mi","IN05 - Ind Transf",
			"IF01  -  Informa��o","IF02 - Documenta�","IF03 - Reprograf","IF04 - Documento","IF05 - Biblioteco","IF06 - Arquivolog","IF07 - Ci�nc Info","IF08 - Serv Info","IF09 - Uso Inform","IF10 - Gen�rico",
			"MT01  -  L�gica Mat","MT02 - �lgebra","MT03 - Geometria","MT04 - An�lis Mat","MT05 - C�lculo","MT06 - Mat Aplic",
			"MA01  -  Meio Amb","MA02 - Recurs Nat","MA03 - Polui��o","MA04 - Qualid Amb",
			"ME01  -  Metodolg","ME02 - Atmosfera","ME03 - Climatolog",
			"PD01  -  Pedologia","PD02 - Pedog�nese","PD03 - Tipos de Solo",
			"PL01  -  Ci�nc Pol","PL02 - Pol�tica",
			"PR01  -  Previd�nc","PR02 - Benef Prev","PR03 - Assist Soc",
			"PS01  -  Psicologia","PS02 - Comportamt","PS03 - Teor Psic",
			"SM01  -  Saneamento","SM02 - Res�duo","SM03 - Limpeza","SM04 - Abast �gua","SM05 - Esgoto",
			"SD01  -  Sa�de","SD02 - Adm Sanit","SD03 - Doen�a","SD04 - Defic F�s","SD05 - Assist M�d","SD06 - Terap Diag","SD07 - Medicina","SD08 - Espec Med","SD09 - Eng Biomed","SD10 - Farmacolog","SD11 - Odontolog",
			"SV01  -  Servi�os","SV02 - Seguro","SV03 - Com�rcio","SV04 - Turismo",
			"TC01  -  Telecom","TC02 - Sist Telec","TC03 - Eng Telec","TC04 - Serv/Redes",
			"TB01  -  Trabalho","TB02 - Rec Human","TB03 - Merc Trab","TB04 - Cond Trab","TB05 - Estr Ocup","TB06 - Lazer",
			"TP01  -  Transporte","TP02  - Sist Trans","TP03 - Serv Trans","TP04 - Eng Transp","TP05 - Mod Transp",
			"UB01  -  Urbanismo","UB02 - Solo urban","UB03 - �rea urban","UB04 - Circ Urban","UB05 - Arquitetur"};//serah colocado na combobox

	private String[] opcoesTipoDeProgramaDefault = {"AP01 - Aplicativo","AP02 - Planejament","AP03 - Controle","AP04 - Auditoria",
			"AP05 - Contabiliz","AT01 - Automa��o","AT02 - Atm Escrt","AT03 - Atm Comerc","AT04 - Atm Bancar",
			"AT05 - Atm Indust","AT06 - Contr Proc","AT07 - Atm Manuf","AT08 - Elet Autom","AV01 - Aval Desemp",
			"AV02 - Cont Recurs","CD01 - Com Dados","CD02 - Emul Termnl","CD03 - Monitor TP","CD04 - Ger Dispost",
			"CD05 - Ger de Rede","CD06 - Rede Local","CT01 - Comuta��o","CT02 - Impl Fun Ad","CT03 - Ger Op&Man",
			"CT04 - Term Op&Man","DS01 - Ferrm Desnv","DS02 - Gerd Aplic.","DS03 - CASE","DS04 - Desv c/Metd",
			"DS05 - Bib Rotinas","DS06 - Apoio Progm","DS07 - Sup Documt","DS08 - Convers Sis","ET01 - Entrtmnto",
			"ET02 - Jogos Anim","ET03 - Gerad Desen","ET04 - Simuladores","FA01 - Ferrm Apoio","FA02 - Proc Texto",
			"FA03 - Planil Elet","FA04 - Gerad Gr�fc","GI01 - Gerenc Info","GI02 - Gerenc BD","GI03 - Gerad Telas",
			"GI04 - Gerad Relat","GI05 - Dicion Dad","GI06 - Ent Val Dad","GI07 - Org Man Arq","GI08 - Recup Dados",
			"IA01 - Intlg Artf","IA02 - Sist Especl","IA03 - Proc Lng Nt","IT01 - Instrument","IT02 - Inst T&M",
			"IT03 - Inst Biomd","IT04 - Inst Analt","LG01 - Linguagem","LG02 - Compilador","LG03 - Montador",
			"LG04 - Pr� - Compld","LG05 - Comp Cruz","LG06 - Pr� - Proces","LG07 - Interptd","LG08 - Ling Procd",
			"LG09 - Ling N Prcd","PD01 - Seg Prot Dd","PD02 - Senha","PD03 - Criptograf","PD04 - Man Intg Dd",
			"PD05 - Cont Acess","SM01 - Simul & Mod","SM02 - Simulador","SM03 - Sim Amb Op","SM04 - CAE/CAD/CAM",
			"SO01 - Sist Operac","SO02 - Interf E&S","SO03 - Interf Disc","SO04 - Interf Com","SO05 - Geren Usuar",
			"SO06 - Adm Dispost","SO07 - Cont Proces","SO08 - Cont Redes","SO09 - Proc Comand","TC01 - Aplc Tcn Ct",
			"TC02 - Pesq Operac","TC03 - Recnh Padr","TC04 - Proc Imagem","TI01 - Teleinform","TI02 - Terminais",
			"TI03 - Transm Dados","TI04 - Comut Dados","UT01 - Utilit�rios","UT02 - Compress Dd","UT03 - Conv Arq",
			"UT04 - Class/Inter","UT05 - Cont Spool","UT06 - Transf Arq"};

	
	private String[] tooltipsTiposDeAplicacaoDefault = {"desenvolv. organizacional,desburocratiza��o", 
			"Planejamento governamental:estrat�gico, operacional, t�cnica de planej.,organiza��o administr.,organiza��o funcional, organograma, estrutura organizacional, controle administr. -  an�lise de desempenho, avalia��o de desempenho", 
	        "an�lise organizacional, O&M",
	        "Administr. Federal, Estadual, Municipal, direito administr., reforma administr., interven��o do Estado na economia, controle da administr. p�blica",
	        "administr., de neg�cios, privada, organiza��o de empresas",
	        "planejamento da f�brica, engenharia do produto, prot�tipo, planejamento da produ��o, controle de qualidade",
	        "planejamento de pessoal - recrutamento, sele��o, admiss�o, avalia��o, promo��o, etc",
	        "planejamento de material, aquisi��o, armazenamento, almoxarifado, aliena��o, controle de material, de estoque, invent�rio, requisi��o de material",
	        "invent�rio patrimonial, fiscaliza��o, conserva��o, manuten��o do patrim�nio",
	        "mercadologia, administr. de marketing ou mercadol�gica, an�lise, e pesquisa de mercado, estrat�gia de marketing, composto do produtomarca-embalagem administr. de vendas - planejamento de vendas - controle de vendas",
	        "servi�os de escrit�rio - comunica��o administr., arquivo de escrit�rio, etc",
	        "agropecu�ria, desenvolvimento rural, extens�o rural, planejamento e pol�tica agr�cola, zoneamento agr�cola",
	        "agrologia, agronomia, agrostologia, edafologia, pomologia",
	        "im�vel rural: fazenda - granja empresa rural",
	        "economia agr�cola",
	        "agricultura extensiva, intensiva, itinerante, monocultura, policultura",
	        "constru��o rural: a�ude - barragem, estufa, habita��o rural, drenagem, irriga��o",
	        "conserva��o de solo, controle da eros�o, melhoramento, recupera��o,tratamento, manejo do solo: aduba��o, fertiliza��o",
	        "doen�as e pragas vegetais, defensivo agr�cola",
	        "produ��o agr�cola, fitotecnia: cultura agr�cola, lavoura, cultivo - t�cnica agr�cola",
	        "produto animal, zootecnia: tipos de cria��o, veterin�ria ou medicina veterin�ria, zoopatologia, produto veterin�rio, veterin�ria preventiva",
	        "Ci�ncias Florestais (dasonomia, economia florestal, pol�tica florestal, produ��o vegetal, silvicultura, arboricultura-florestamento, reflorestamento, terra marginal)",
	        "aquacultura ou aquicultura animal, vegetal",
	        "Extrativismo vegetal (produto extrativo vegetal: celulose, cera, fibra, goma natural, madeira, l�tex)",
	        "Extrativismo Animal (ca�a, pesca, prospec��o produto extrativo animal: couro-pele-pescado)",
	        "sistema social, estrutura, situa��o, mobiliza��o, controle, mudan�a e reforma social",
	        "planejamento social, pol�tica social, a��o social, bem-estar social, n�vel ou padr�o de vida",
	        "tribo, bando, etnia, grupo local, desenvolvimento comunit�rio, na��o, indiv�duo",
	        "civiliza��o, cultura popular: folclore - uso e costumes",
	        "doutrina, teologia, pr�tica religiosa, etc.",
	        "antropologia f�sica: antropometria - paleantropologia, enologia: etnografia - ernologia, etnografia, antropologia: economia - urbana - pol�tica",
	        "sistem�tica, comparada aplicada: urbana - rural - pol�tica - econ�mica - do trabalho - da educa��o - do direito, sociografia, pesquisa social, processo social",
	        "povoamento, n�cleo populacional, invas�o, assentamento rural, urbano, cintur�o verde",
	        "metr�pole, regi�o ou �rea metropolitana, rur�polis",
	        "Organiza��o Territorial (organiza��o do espa�o, rede urbana, conurba��o)",
	        "Pol�ticas de Assentamento Humanos (pol�tica demogr�fica, migrat�ria, planejamento familiar, pol�tica de coloniza��o, de desenvolvimento urbano ou pol�tica urbana)",
	        "distribui��o da popula��o, mobilidade ou movimento da popula��o, migra��o, din�mica populacional",
	        "Disciplinas Auxiliares (demografia, geografia urbana, agr�ria, teoria dos limiares ou localiza��o, teoria da polariza��o)",
	        "ser vivo, subst�ncia org�nica, leis biol�gicas, biotipologia, biometria, bioclimatologia, parasitologia, filogenia ou evolu��o, geobiologia, histologia, limnologia",
	        "citogen�tica, engenharia gen�tica, genotipo, hereditariedade, melhoramento gen�tico, gen, gen�tica das popula��es",
	        "ou biologia celular, c�lula, meiose, etc",
	        "bacteriologia, virologia, biogeografia",
	        "sistemas: cardiovascular - digestivo - tegumentar, etc, embriologia, secre��o, excre��o, �rg�os dos sentidos",
	        "nascimento, digest�o, reprodu��o, sexualidade, nemofisiologia, metabolismo",
	        "amino�cido, prote�na, horm�nio, fen�meno bioqu�micos: bioss�ntese - fermenta��o - osmose, etc",
	        "bioenerg�tica, biomec�nica, eletrofisiologia",
	        "fitologia, vegetal, vegeta��o, morfologia, fisiologia vegetal, quimioss�ntese, gen�tica vegetal, fitossociologia, biologia floral",
	        "geografia bot�nica ou bot�nica geogr�fica, caatinga, cerrado, campo, mangue, etc",
	        "planta condiment�cia, daninha ou nociva, arom�tica, feculenta, t�xtil, cereal, legume, hortali�a, gr�o aliment�cio",
	        "taxonomia vegetal",
	        "metaf�sica, est�tica, �tica, filosofia social, teoria do conhecimento, hermen�utica, l�gica, dial�tica, doutrina filos�fica",
	        "ci�ncias humanas e sociais, naturais, biol�gicas, geoci�ncia, pol�tica cient�fica, desenvolvimento cient�fico, hist�ria da ci�ncia, filosofia da ci�ncia, metodologia cient�fica, metodologia, pesquisa ou investiga��o, pesquisa aplicada - indicar a �rea espec�fica com outro c�digo, institui��o de pesquisa",
	        "lingu�stica, geolingu�stica, sociolingu�stica e linguagem popular, linguagem: natural, artificial)",
	        "comunica��o humana, escrita, visual, social: comunica��o de massa, propaganda, rela��es p�blicas, meios de comunica��o: radiocomunica��o, imprensa, pesquisa de opini�o, arte gr�fica: editora��o, editora��o, impress�o, edi��o",
	        "cria��o art�stica, patrim�nio art�stico, industrial, fotografia, aerofotografia, cinema, m�sica, literatura",
	        "pol�tica, econ�mica, social, pesquisa hist�rica: arqueologia, numism�tica, genealogia, filatelia, epigrafia, patrim�nio hist�rico",
	        "constru��o civil: habitacional, comercial, industrial: constru��o industrializada ou pr�-fabricada",
	        "Processo Construtivo (tradicional, convencional, misto, evolu�do, cantaria, adobe, alvenaria, concreto, m�quina de constru��o, equipamento para constru��o)",
	        "Organiza��o da constru��o (licita��o de obra, custa da constru��o, memorial descritivo de obra, ger�ncia de projeto de constru��o, execu��o da obra, fiscaliza��o de obra, racionaliza��o da constru��o, coordena��o dimensional, coordena��o modular, suprimento de obra)",
	        "engenharia civil, engenharia de avalia��es, contrato de obra p�blica, licita��o de obra p�blica, obra de grande porte, obra de arte, como engenharia civil",
	        "c�lculo estrutural, an�lise de estrutura, mec�nica das estruturas: es�stica, plana, retocila, etc, tipo de estrutura: concreto, a�o, met�lico, infl�vel, etc, armadura: estrutural, armadura para concreto armado",
	        "pr�dio, edif�cio, elemento construtivo: funda��o, pilar, viga, componente construtivo: painel, instala��es, manuten��o da constru��o, obra: de acabamento, melhoria, demoli��o",
	        "ancoragem, apiloagem, caleamento estrutural, cimbramento, concretagem, escoramento, terraplanagem, pavimenta��o",
	        "Higiene das constru��es (ventila��o, ilumina��o, conforto t�rmico, isolamento: ac�stico, t�rmico, e higrosc�pico)",
	        "obra hidr�ulica ou estrutura hidr�ulica, conduto hidr�ulico, tubula��o, canal, reservat�rio: lago artificial, piscina, a�ude eclusa, dispositivos de controle de �gua: comporta, polder, reguladora de n�vel, barragem, drenagem, hidr�ulica do solo",
	        "mec�nica das rochas, mec�nicas dos solos, aterro, escava��o, talude, movimento de terra, obra de terra, nivelamento de terra, obra de conten��o: estrutura de arrimo, conten��o de encosta",
	        "federal, estadual, municipal, hier�rquica das leis, constitui��o, lei ordin�ria, etc, prote��o da lei ou prote��o legal, hermen�utica jur�dica ou interpreta��o das leis",
	        "Direito Constitucional (poder constituinte, organiza��o nacional: uni�o, estado, munic�pio, distrito federal, territ�rio federal, poderes do estado, legislativo, executivo, judici�rio, declara��o de direitos: nacionalidade, direitos pol�ticos, etc, direito eleitoral)",
	        "Outras Disciplinas do Direito (disciplinar, previdenci�rio, ecol�gico, urban�stico, econ�mico, financeiro, tribut�rio: c�lculo do tributo, evas�o tribut�ria, infra��o tribut�ria, etc, direito processual civil, direito penal, direito processual penal, direito internacional p�blico, direitos do homem ou humanos, lit�gio internacional, direito privado, direito civil, bens: propriedade p�blica, privada, patrim�nio, semoventes, im�veis, p�blicos, direito de fam�lia, direito das coisas: direito autoral, enfiteuse ou aforamento, laudemio, registro imobili�rio, direito sucess�rio: heran�a, sucess�o, invent�rio, direito das obriga��es: acordo, conv�nio, contrato, loca ��o, arrendamento, fian�a, direito agr�rio, direito do trabalho, direito comercial, direito industrial, direito mar�timo, direito aeron�utico, direito internacional privado",
	        "biosfera, rela��o bi�tica, rela��o abi�tica, ecologia agr�cola, aqu�tica, florestal, equil�brio/desequil�brio ecol�gico, fen�meno ecol�gico",
	        "ecofisiologia animal, vegetal, distr�fico, digotr�fico, eutr�fico, etc.",
	        "ecodesenvolvimento, ecologia social, ecologia urbana",
	        "Ecologia Vegetal/Ecologia Animal (autoecologia, sinecologia, habitat, vida selvagem)",
	        "migra��o, anodromo,catadromo, piracema, hiberna��o, comportamento animal, comportamento vegetal",
	        "teoria econ�mica, metodologia da economia: modelos e econometria, an�lise econ�mica, sistema econ�mico",
	        "microeconomia, teoria da oferta, teoria da produ��o, fun��o da produ��o, economias de escala, teoria dos custos, elasticidade da oferta: pre�o e renda, teoria da demanda ou teoria do consumidor, teoria da utilidade ou an�lise cardinal, teoria dos mercados, teoria do pre�o ou do valor, teoria do equil�brio econ�mico, teoria do bem-estar, ou economia social ou teoria dadistribui��o da renda, �timo de pareto curva de Lorenz, custo social",
	        "ou microeconomia ou teoria microecon�mica, demanda agregada, oferta agregada, venda, n�vel de emprego",
	        "setor econ�mico ou setor de produ��o, setores: prim�rio, secund�rio, terci�rio, p�blico, privado, informal ou economia silenciosa ou invis�vel ou mercado informal, fator de produ��o, distribui��o da renda, produtividade, superprodu��o, consumo, poupan�a, interna, externa, entesouramento, poupan�a for�ada, investimento, forma��o de capital, recursos econ�micos ou riqueza, indicador econ., indexa��o, desindexa��o, desenvolv. econ. local, regional, nacional, setorial, integrado, crescimento econ., desempenho econ., disparidade econ., acumula��o de capital",
	        "ou contabilidade social ou conta nacional, agregado econ�mico: PIB, PNB, PNL, PIL, renda nacional, an�lise de insumo - produto ou input - output ou de rela��es intersetoriais, ou an�lise de Leontief, ou insumo-produto",
	        "moeda: cria��o, circula��o, flutua��o, sist. monet�rio: tipos de moeda e meios de pagto ou meio circulante, base monet�ria, unidade monet�ria, moeda division�ria, reforma monet�ria",
	        "demanda, oferta, mercado consumidor, mercado externo ou externo ou exterior, mercado interno, internacional, produtor, paralelo, a termo, pre�o",
	        "bens de consumo, de capital, insumo, bens: dur�veis, n�o dur�veis, tang�veis, intang�veis, inferiores, normais, de Giffen",
	        "Engenharia econ�mica/din�mica econ�mica (an�lise custo/benef�cio ou custo benef�cio, pay-out ou prazo de refluxo, ciclo econ�mico ou flutua��o econ�mica, n�vel dos pre�os: infla��o, defla��o, conjuntura econ�mica)",
	        "ou ci�ncia regional ou economia regional, economia local, urbana, regionaliza��o",
	        "propriedade do capital, da terra ou propriedade fundi�ria, estrutura agr�ria, loteamento",
	        "ou rela��es econ�micas, balan�o de pagamentos: balan�a comercial, balan�a de servi�os, movimento de capitais internacionais, protecionismo, livre com�rcio, c�mbio: conversibilidade da moeda, controle cambial, c�mbio livre, taxa de c�mbio, estatiza��o monet�ria, valoriza��o da moeda, minidesvaloriza��o, maxidesvaloriza��o, mercado cambial, divisas, reservas monet�rias, d�vida externa, integra��o econ�mica internacional, zona monet�ria, coopera��o econ�mica, bloqueio econ�mico",
	        "pol�tica fiscal, monet�ria, de cr�dito, econ�mica internacional, de com�rcio exterior, de desenvolvimento econ�mico, de desenvolvimento nacional, de distribui��o da renda, agr�ria, de pre�os, estatiza��o, privatiza��o, planejamento econ�mico",
	        "total, m�dia, marginal, custo ou custo operacional: total, m�dio, etc, tipos de empresa: p�blica, privada, multinacional, estrangeira, microempresa, de pequeno, m�dio e grande porte, nacional, cooperativa, concentra��o econ�mica: holding, conglomerado de empresas, combina��o de empresas, cons�rcio de empresas, truste, joint-venture",
	        "Ensino regular (pr�-escolar, 1� grau, 2� grau, superior, p�s-gradua��o, orienta��o profissional)",
	        "Ensino supletivo (alfabetiza��o, aprendizagem, comercial, industrial, agr�cola, suprimento: curso de atualiza��o, de aperfei�oamento, treinamento)",
	        "Institui��o/Administra��o/Processo de ensino (jardim escolar, escola maternal, jardim de inf�ncia, escola: de 1� grau, 2� grau, centro de ensino, de estudo supletivo, universidade, faculdade ou instituto superior de ensino, evas�o escolar, servi�os educacionais, equipamento escolar, m�todo de ensino, did�tica: t�cnica de ensino, pr�tica de ensino, ensino integrado, processo formal de ensino, processo n�o formal de ensino)",
	        "Formas de ensino/material instrucional (ensino direto, teleduca��o, por correspond�ncia, radioeduca��o, ensino semi-indireto, m�dulo instrucional, equipamento did�tico, material audio-visual aprendizagem cognitiva, psicomotora, afetiva, autodidatismo)",
	        "curr�culo ou programa de ensino, reforma de ensino, curr�culo m�nimo, etc, corpo docente, corpo discente, graus e diplomas",
	        "pedagogia, ensino, sistema educacional, rede de ensino, educa��o de adulto, educa��o de base, de massa, etc, pol�tica educacional, educa��o ectra-escolar: educa��o comunit�ria, recuperadora",
	        "pol�tica energ�tica, economia energ�tica: consumo de energia, empresa de energia",
	        "Recursos/servi�os/formas de energia (recursos hidrel�tricos ou hidroel�tricos, carbon�feros, petrol�feros, uran �feros, servi�os de energia el�trica, de g�s canalizado, formas: energia el�trica, mec �nica, qu�mica, radiante, luminosa, sonora, t�rmica, etc)",
	        "f�ssil, de origem vegetal, biomassa, nuclear, s�lido, l�quido, gasoso",
	        "Tecnologia e Energia (fonte de energia: convencional, alternativa, gera��o de energia, usina de energia, convers�o de energia, armazenamento de energia, transporte de energia, distribui��o de energia: eletrifica��o, engenharia el�trica, medi��o de energia)",
	        "microeletr�nica, circuito eletr�nico, eletr�nica industrial, semicondutor",
	        "tecnologia de reatores, reator nuclear",
	        "receita p�blica, or�amento p�blico, sistema tribut�rio, despesa p�blica, cr�dito p�blico, administra��o fiscal",
	        "financiamento privado",
	        "institui��o financeira, opera��es financeiras: opera��o de cr�dito, banc�ria, de fian�a, de c�mbio, de sero, open market, hedge, overnight, cobran�a, mercado de capitais",
	        "Recursos/Or�amento/Instrumentos (aplica��o de recursos, capital, recursos or�ament�rios, fundos, or�amento: anal�tico, de custeio de capital, empresarial ou privado, p�blico, de aplica��o, de caixa, de receita e despesa, familiar, t�tulo de cr�dito, a��o, cart�o de cr�dito, caderneta de poupan�a, financiamento)",
	        "administra��o financeira, juro, cr�dito, d�bito, loteria (planejamento financeiro, pol�tica financeira, controle financeiro, an�lise financeira, assist�ncia financeira, juro de mora, taxa de juro, spread, cr�dito: especializa��o geral, p�blico, internacional, tribut�rio, d�bito, d�bito fiscal, loteria esportiva, loto)",
	        "contabilidade, financeira, gerencial, t�cnicas cont �beis, demonstra��o de resultado: receita/despesa, resultado cont�bil, balancete, demonstra��o de lucros e preju�zos acumulados, demonstra��o de origens e aplica��es de recursos, deprecia��o, exaust�o",
	        "Mat�ria/F�sica das Part�culas/e dos �ons (antimat�ria, val�ncia composi��o de mat�ria, estados da mat�ria, part�cula �tica, part�cula elementar, part�cula carregada, ioniza��o)",
	        "onda sonora, som, luz, �tica geom�trica, microsc�pica, f�sica, alidade, microscopia, solametria",
	        "amplitude, difra��o, frequ�ncia, modula��o, demodula��o, reflex�o, refra��o, propaga��o, resson�ncia e tipos de onda",
	        "unidade de medida, dimens�o, an�lise dimensional, equa��o dimensional, sistema de medida, medi��o: macro e micro-medi��o",
	        "est�tica, din�mica, cinem�tica, cin�tica, espa�o, tempo, movimento, momento, for�a, densidade, massa, volume, resist�ncia dos materiais, trabalho (pot�ncia)",
	        "F�sica dos S�lidos/ dos Flu�dos/ dos Plasmas (mec�nica dos s�lidos, propriedade dos s�lidos, estrutura dos s�lidos, mec�nica dos flu�dos, din�mica dos flu�dos, est�tica dos flu�dos, cinem�tica dos flu�dos, mec�nica dos gases, hidromec�nica, viscosidade, plasma-f�sica)",
	        "calor, calorimetria, temperatura, radia��o t�rmica, tratamento t�rm., termologia, propried. termodin�mica",
	        "qu�ntica, linear, n�o linear",
	        "Magnetismo/Eletromagnetismo (campo, polo, circuito e propriedade magn�tica, interfer�ncia eletromagn�tica, propriedade eletromagn�tica, onda eletromagn�tica, radia��o monocrom�tica, micro-onda, polariza��o espont�nea, onda hertziana)",
	        "F�sica de Superf�cie/de Dispers�o (tens�o superficial, capilaridade, f�sica coloidal)",
	        "efeito da radia��o, radia��o atmosf�rica, radia��o ionizante",
	        "espectrografia, espectrometria, espectroscopia at�mica, molecular e �tica, espectrofotometria",
	        "F�sica Molecular (ou f�sica at�mica, rea��o nuclear, estrutura molecular, radiatividade, radiometria)",
	        "composto qu�mico, subst�ncia combust�vel, subst�ncia qu�mica, propriedade qu�mica, lega��o qu�mica, radical qu�mico, rea��o qu�mica, composi��o qu�mica, pol�mero inorg�nico",
	        "Qu�mica Anal�tica / dos pol�meros (an�lise qu�micas calorimetria, condumetria, cromatografia, pol�mero org�nico, pol�mero inorg�nico)",
	        "an�lise f�sico-qu�mica, processos f�sicos-qu�micos",
	        "composto org�nico, �cido, sal",
	        "elemento qu�mico, metal, gases raros, terras raras, composto inorg�nico, nucl�deo",
	        "ou fisiografia, paleografia, geomorfologia, acidente geogr�fico, morfologia gen�tica, morfologia fisiol�gica",
	        "ou antopogeografia, geografia econ�mica, pol�tica, da popula��o",
	        "regi�o: homog�nea, elementar, zona geogr�fica: t�rrida, subtropical",
	        "pontos cardeais, colaterais, hemisf�rio",
	        "astron�mica, espacial, gravim�trica, geom�trica, levantamento geod�sico",
	        "topometria, planimetria, altimetria, acidente topogr�fico, sensoramento remoto ou monitoreamento remoto",
	        "fotogrametria terrestre, aerofotogrametria, etc",
	        "fotogramia, mapa, carta, fotocarta, mosaico, etc",
	        "M�todos e Processos de Cartografia (processo astrogeod�sico, m�todo das dire��es, m�todo de Schreiber, de Sterneck, etc.)",
	        "Plano Cartogr�fico (azimute / posi��o / ponto meridiano, paralelo, c�rculo hor�rio, etc, azimute de Laplace, geod�sico, da carta, etc, tri�ngulo de posi��o, polar, culmina��o, etc, polo geogr�fico, celeste, elevado, etc)",
	        "din�mica externa: intemperismo, eluvia��o, iluvia ��o, eros�o, din�mica interna: tectonismo, magma, etc, geologia estrutural: anticlinal, sinclinal, dobra, junta, folia��o",
	        "ou criologia, glacia��o, moraina",
	        "Geotect�nica (tect�nica, geodin�mica, sismologia)",
	        "Geologia Marinha (fotogeologia: mapeamento geol�gico)",
	        "paleontologia, arcabou�o tect�nico da terra, sedimentologia, estratigrafia",
	        "petrologia, petrografia, g�nese de jazida: metalgenia, mineraliza��o, jazida mineral, prospec��o, mineralogia f�sica, qu�mica, etc",
	        "Geoqu�mica / Hidrogeologia / Geof�sica / Geot�cnica (geoqu�mica dos solos, das rochas, �gua subterr�nea, geof�sica marinha, terrestre, s�smica, gravimetria, ensaio geot�cnico)",
	        "moradia, fun��o habitacional, mercado habitacional, pol�tica habitacional",
	        "Tipologia Habitacional (habita��o unifamiliar, multifamiliar, funcional especial: para velhos, para estudantes, habita��o provis�ria: alojamento, acampamento, tugurio, habita��o m�vel, espont�nea, flutuante, etc)",
	        "�gua, ciclo hidrol�gico",
	        "bacia hidrogr�fica, representativa, �rea de inunda��o, curso de �gua, bacia lacustre, etc",
	        "fluviometria, pluviometria, evapometria, sedimentometria, esta��o hidrom�trica, fluviom�trica, etc",
	        "ou oceanologia, ou talassografia, oceano, mar, tipos de oceanografia: f�sica, qu�mica, biol�gica, geol�gica, batimetria",
	        "pol�tica industrial, concentra��o industrial, produ��o industrial, pesquisa industrial, empresa industrial",
	        "pol�tica tecnol�gica, coopera��o t�cnica, pesquisa tecnol�gica, inova��o tecnol�gica, tecnologia apropriada, qu�mica tecnol�gica",
	        "desenho t�cnico, engenharia metal�rgica, engenharia qu�mica, mec �nica, automotiva, aeron�utica, naval, de produ��o, de teste",
	        "Ind�stria Extrativa Mineral (pol�tica mineral, pesquisa mineral, engenharia e minas, minera��o, extrativismo mineral, ou explora��o mineral)",
	        "Ind�stria de Transforma��o (ind�stria manufatureira, produto, industrializa��o, processo industrial, g�nero da ind �stria: metal�rgica, de material el�trico, eletr�nico, qu�mica, mec�nica, de componentes, de armamento, t�xtil, etc, estabelecimento industrial, produto industrializado, aliment�cio, etc, materiais e equipamentos)",
	        "cient�fica, tecnol�gica, bibliogr�fica, estrat�gica, dados, etc",
	        "an�lise da informa��o, processamento de informa��o armazenamento, recupera��o, dissemina��o, interc�mbio, bibliofilia, bibliologia, bibliometria",
	        "fotoc�pia, microfotografia, microfilmagem, micrografia",
	        "informa��o, registrada, ou material de informa��o, documento cient�fico, confidencial, prim�rio, secund�rio, n�o convencional, obra de refer�ncia, multimeio, material leg�vel por m�quina",
	        "administra��o de biblioteca, processos t�cnicos",
	        "ou arquiv�stica, administra��o de arquivos",
	        "sistema de informa��o, rede de informa��o, teoria da informa��o, fluxo de informa��o",
	        "biblioteca, centro de documenta��o, arquivo, centro referencial, museu, etc",
	        "usu�rio, estudo e perfil do usu�rio",
	        "processamento de dados",
	        "metamatem�tica, m�todo matem�tico, processo matem�tico, teoria l�gica",
	        "teoria dos conjuntos, teoria dos n�meros, �lgebra elementar, estrutura alg�brica, tipos de �lgebra",
	        "geometria plana, geometria s�lida, geometria anal�tica, trigonometria, geometria descritiva, geometria diferencial, etc",
	        "topologia, an�lise real, an�lise num�rica, an�lise complexa, vetorial, matricial, tensorial, funcional, transforma��o integral, equa��o",
	        "c�lculo diferencial, integral, operacional, vetorial, matricial, tensorial, num�rico, variacional",
	        "modelo matem�tico. Especificar a aplica��o: estat�stica, gr�fico, c�lculo de probabilidade, an�lise estat�stica, pesquisa operacional, matem�tica financeira atuarial",
	        "artificial, natural, pol�tica do meio ambiente",
	        "natureza: conserva��o, recursos naturais renov�veis, n�o renov�veis, �rea protegida",
	        "tipos de polui��o: atmosf�rica, bacteriol�gica, f�sica, do solo, �gua, qu�mica, radioativa, sonora, etc, controle preven��o, n�vel de polui��o, poluente",
	        "qualidade da vida, da �gua, do ar, monitoramento ambiental, engenharia ambiental, de defesa civil",
	        "f�sica, din�mica, aplicada",
	        "ar, atmosfera inferior, superior, circula��o e press�o atmosf�rica, previs�o e esta��o metereol�gica, vento, tempestade, massa de ar, temperatura do ar, radia��o solar, umidade do ar",
	        "clima, aclimata��o, agroclimatologia, esta��o climatol�gica, tipos de clima",
	        "= ci�ncia do solo, terra, solo: mineral ou org�nico",
	        "fatores de forma��o do solo, processo pedogen�tico, perfil do solo: morfopedologia, f�sica do solo: morfopedologia, f�sica do solo, qu�mica do solo mineralogia do solo, biologia do solo, horizonte",
	        "tipos do solo",
	        "teoria pol�tica, metodologia pol�tica",
	        "sistema pol�tico, estrutura pol�tica, Estado (na��o) soberania, formas de estado, governo, regime pol�tico, poder p�blico, organiza��o do poder, a��o pol�tica, pol�tica de governo, doutrina pol�tica",
	        "seguridade social, pol�tica de previd�ncia social, previd�ncia social, previd�ncia privada",
	        "aposentadoria, aux�lio ou assist�ncia previdenci�ria, pec�lio, abono, etc",
	        "m�dica, odontol�gica, alimentar, reeducativa, assist�ncia habitacional, organiza��es de assist�ncia social, servi�o social",
	        "= ci�ncia do comportamento, psicologia do desenvolvimento, psicologia social, aplicada - cl�nica, psicoterapia, educacional, processos: sensorial, intelig�ncia, congnitivo, reluxo",
	        "= conduta ou comportamento humano, motiva��o",
	        "sistema e teoria de psicologia, parapsicologia (associacionismo), behaviorismo, psicologia existencialista, do refor�o, etc",
	        "engenharia sanit�ria, saneamento b�sico",
	        "detrito, dejeto ou efluente, lixo, res�duo: gasoso, l�quido, org�nico, qu�mico, t�rmico, t�xico",
	        "limpeza p�blica, drenagem urbana (limpeza urbana, de logradouro, coleta de lixo, destina��o do lixo, etc, rede de drenagem urbana)",
	        "= sistema de abastecimento de �gua, servi�os de �gua, capta��o de �gua, adu��o de �gua, tratamento de �gua, reservat�rio de �gua, distribui��o de �gua, medi��o de �gua",
	        "servi�o de esgoto, esgoto sanit�rio, tratamento: preliminar, prim�rio, secund�rio, terci�rio, remo��o de s�lidos, lodo, emiss�rio, etc, esgoto industrial",
	        "pol�tica de sa�de, higiene, sa�de f�sica, mental, p�blica",
	        "= administra��o de sa�de, servi�os b�sicos de sa�de, servi�os de sa�de: hospital, centro de sa�de, posto de sa�de, de socorro, etc, sistema de sa�de, levantamento sanit�rio, educa��o sanit�ria, campanha de sa�de p�blica, equipamento m�dico",
	        "cong�nita, infecciosa, do sistema reprodutor, do sistema glandular, etc",
	        "f�sica, mental, inv�lido",
	        "hospitalar, m�dico-domiciliar, ambulatorial, m�dico-sanit�ria",
	        "terapia, diagn�stico m�dico (terap�utica, fisioterapia, hemoterapia, dieta, etc, diagn�stico: laboratorial, radiol�gico, s�ndrome, sintoma",
	        "alop�tica, hemeop�tica, preventiva, tropical, nuclear, medicina do trabalho, legal, de urg�ncia",
	        "Especialidades M�dicas (cardiologia, endocrinologia, epidemiologia, ginecologia, oftalmologia, psiquiatria, patologia, dermatologia, radiologia, etc, medicina n�o-convencional: naturop�tica, caseira, acupuntura, do-in, etc)",
	        "Engenharia Biom�dica, ci�ncias param�dicas (bioengenharia, biotecnologia, enfermagem, optometria, fonoaudiologia)",
	        "assist�ncia farmac�utica, toxicologia, farmacopeia, farmacognosia, medicamento",
	        "sa�de oral, periodontias, pr�tese dent�ria, assist�ncia odontol�gica",
	        "p�blicos especificar conforme o tipo: telefonia, telegrafia, etc: correio, servi�os de energia el�trica, seguran�a p�blica, de �gua, de esgoto, etc - servi�os privados: alojamento e alimenta��o, de reparo e manuten��o, pessoais, de vigil�ncia e guarda, etc, tarifa de servi�os",
	        "social, privado, quanto ao objeto, pessoal, patrimonial, de responsabilidade, contrato de seguro, seguradora, resseguro, co-seguro, corretora de seguro",
	        "interno, exterior, il�cito, comercializa��o, corretagem ou servi�os de corretagem, ensilagem, entrepostagem, interc�mbio comercial, especula ��o, mercadoria, zona franca, porte livre, pol�tica comercial",
	        "pol�tica de turismo, turismo interno, externo, interc�mbio tur�stico, infraestrutura tur�stica: ag�ncia de turismo, rede hoteleira",
	        "pol�tica de telecomunica��es, modelo de telecomunica��es",
	        "radiocomunica��o, sistema de televis�o, telefonia, telegrafia, sistema de radar, telemetria, transmiss�o de dados, comunica��o por fio, teoria de telecomunica��es",
	        "linha de comunica��o, recep��o, transmiss�o",
	        "servi�os, redes esta��es e material de telecomunica��es",
	        "intelectual, t�cnico, manual, mecanizado, rural, dom�stico, eventual, em condom�nio, m�o-de-obra, teoria do trabalho: m�todo de trabalho, controle do trabalho, organiza��o do trabalho",
	        "desenvolvimento de recursos humanos, pessoal trabalhador = oper�rio, classe trabalhadora: trabalhador rural, aut�nomo, n�o qualificado, etc",
	        "Mercado de Trabalho (pol�tica empregat�cia, salarial, pleno emprego, desemprego, subemprego, for�a de trabalho, emprego c�clico, fiscaliza��o do trabalho, racionaliza��o do trabalho)",
	        "Condi��es de Trabalho (ergonomia ou engenharia humana, ambiente de trabalho)",
	        "Estrutura Ocupacional (ocupa��o, profiss�o liberal, sindicato, associa��o de empregos, conselho profissional, empresariado, emprego, cargo)",
	        "renova��o, col�nia de f�rias, etc",
	        "pol�tica de transporte, planejamento de transporte",
	        "dom�stico, regional, interregional, rural, urbano, integrado, etc, infraestrutura transporte = rede de transporte, sistema vi�rio, rede: aerovi�rio, dutovi�rio, hidrovi�ria, corredor de transporte, via de transporte, terminal de transporte, equipamento de transporte, material de transporte",
	        "transporte de carga, de passageiro, linha de transporte, empresa de transporte",
	        "de tr�fego, aeron�utica, ferrovi�ria, rodovi�ria, naval, automotiva",
	        "Modalidades de Transporte (a�reo, terrestre, hidrovi�rio, especial: dutovi�rio, vertical)",
	        "= arte urbana, organiza��o do espa�o urbano, projeto urban�stico, forma urbana, planejamento urbano, hist�ria do urbanismo",
	        "im�vel urbano, terreno urbano, parcelamento do solo, cadastro imobili�rio, avalia��o imobili�ria, tributa��o urbana, renda imobili�ria, especula��o imobili�ria",
	        "= zona urbana, s�tio urbano, estrutura urbana, urbaniza��o, uso do solo = apropria��o do espa�o, zoneamento urbano, renda imobil., especula��o imobil.",
	        "Circula��o Urbana (via de circula��o, terminal de transporte, tr�fego urbano, infra-estrutura urbana = equipamento urbano, servi�os p�blicos urbanos, equipamento comunit�rio)",
	        "projeto de arquitetura, reconvers�o de uso, arquitetura: dom�stica, industrial, de com�rcio, de administra��o, institucional, militar, tradicional, de interiores = decora��o elemento formal, elemento funcional, elemento decorativo"};

	
	private String[] tooltipsTipoDeProgramaDefault = {"Aplicativos","Planejamento","Controle","Auditoria","Contabiliz",
			                                          "Automa��o","Automa��o de Escrit�rio","Automa��o Comercial","Automa��o Banc�ria","Automa��o Industrial","Controle de Processos","Automa��o da Manufatura (Controle Num�rico Computadorizado, Rob�tica, etc)","Eletr�nica Automotiva (computador de bordo, sistema de inje��o e/ou igni��o eletr�nica, etc)",
													  "Avalia��o de Desempenho","Contabiliza��o de Recursos",
													  "Comunica��o de Dados","Emuladores de Terminais","Monitores de Teleprocessamento","Gerenc. Disposit. e Perif�ricos","Gerenciador de Rede de Comunica��o de Dados","Rede Local",
													  "Comuta��o Telef�nica e Telegr�fica","Implementador de Fun��es Adicionais","Gerenciador Opera��o e Manuten��o","Terminal de Opera��o e Manuten��o de Central",
													  "Ferramentas de Suporte ao Desenvolv. de Sistemas","Gerador de Aplica��es","Computer Aided Softw Engineering","Aplicativos Desenvolv. Sist. de acordo com determ. Metodologia","Bibliotecas de Rotinas ('Libraries')","Apoio � Programa��o","Suporte � Documenta��o","Conversor de Sistemas",
													  "Entretenimento","Jogos Animados ('arcade games')","Geradores de Desenhos","Simuladores Destinados ao Lazer",
													  "Ferramenta de Apoio","Processadores de Texto","Planilhas Eletr�nicas","Geradores de Gr�ficos",
													  "Gerenciador de Informa��es","Gerenciador de Banco de Dados","Gerador de Telas","Gerador de Relat�rios","Dicion�rio de Dados","Entrada e Valida��o da Dados","Organiza��o, Tratamento, Manuten��o de Arquivo","Recupera��o de Dados",
													  "Intelig�ncia Artificial","Sistemas Especialistas","Sistemas de Processamento de Linguagem Natural",
													  "Instrumenta��o","Instrumenta��o de Teste e Medi��o", "Instrumenta��o Biom�dica", "Instrumenta��o Anal�tica",
													  "Linguagens","Compilador","Montador","Pr�-compilador","Compilador cruzado","Pr�-processador","Interpretador","Linguagem Procedural","Linguagem N�o Procedural",
													  "Seguran�a e Prote��o de Dados","Senha","Criptografia","Manuten��o da Integridade dos Dados","Controle de Acessos",
													  "Simula��o e Modelagem","Simulador V�o/Carro/Submarino/...","Simuladores de Ambiente Operacional","CAE/CAD/CAM/CAL/CBT/...",
													  "Sistema Operacional","Interface de Entrada e Sa�da","Interface B�sica de Disco","Interface de Comunica��o","Gerenciador de Usu�rios","Administrador de Dispositivos","Controlador de Processos","Controlador de Redes","Processador de Comandos",
													  "Aplica��es T�cnico-Cient�ficas","Pesquisa Operacional","Reconhecimento de Padr�es","Processamento de Imagem",
													  "Teleinform�tica","Terminais","Transmiss�o de Dados","Comuta��o de Dados",
													  "Utilit�rios","Compressor de Dados","Conversor Meios de Armazenamento","Classificador / Intercalador","Controlador de Spool","Transfer�ncia de Arquivos"};
													  
	
	private void criarArquivoCamposDeAplicacao()
	{
		PrintWriter writer;
		try {
			writer = new PrintWriter("config/aplicacao.dat", "UTF-8");
			
			for(int i = 0; i < this.opcoesTiposDeAplicacaoDefault.length; i++)
			{
				String umCampoDeAplicacao = this.opcoesTiposDeAplicacaoDefault[i];
				String tooltipUmCampoDeAplicacao = this.tooltipsTiposDeAplicacaoDefault[i];
				String oQueEscrever = umCampoDeAplicacao + "/" + tooltipUmCampoDeAplicacao;
				
				if(i < this.opcoesTiposDeAplicacaoDefault.length - 1)
				{
					oQueEscrever = oQueEscrever + ";";
				}
				
				writer.println(oQueEscrever);
			}
			writer.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void criarArquivoTiposDePrograma()
	{
		PrintWriter writer;
		try {
			writer = new PrintWriter("config/programa.dat", "UTF-8");
			
			for(int i = 0; i < this.opcoesTipoDeProgramaDefault.length; i++)
			{
				String umTipoDePrograma = this.opcoesTipoDeProgramaDefault[i];
				String toolTipUmTipoDePrograma = tooltipsTipoDeProgramaDefault[i];
				String oQueEscrever = umTipoDePrograma + "/" + toolTipUmTipoDePrograma;
				
				
				if(i < this.opcoesTipoDeProgramaDefault.length - 1)
				{
					oQueEscrever = oQueEscrever + ";";
				}
				
				writer.println(oQueEscrever);
			}
			writer.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//serah que o arquivo ja existe ou nao?
	private boolean existeArquivoaplicacaodatECriaPastaConfigSeNaoExistir()
	{
		//primeiro vamos checar se a pasta config existe. Se nao, vamos cria-la antes de criar o arquivo config
		boolean existePastaConfig = existePastaConfig();
		if(existePastaConfig == false)
		{
			File directory = new File(String.valueOf("config"));
			directory.mkdir();
			return false;
		}
		else
		{
			File f = new File("config/aplicacao.dat");
			if(f.exists() && !f.isDirectory()) 
			{
				return true;
			}
			else
			{
				return false;
			}
		}
	}
	
	//sera que a pasta config existe ou nao?
	private boolean existePastaConfig()
	{
		File directory = new File(String.valueOf("config"));
		return directory.exists();
	}
	
		
	//serah que o arquivo ja existe ou nao?
	private boolean existeArquivoprogramadatECriaPastaConfigSeNaoExistir()
	{
		//primeiro vamos checar se a pasta config existe. Se nao, vamos cria-la antes de criar o arquivo config
		boolean existePastaConfig = existePastaConfig();
		if(existePastaConfig == false)
		{
			File directory = new File(String.valueOf("config"));
			directory.mkdir();
			return false;
		}
		else
		{
			File f = new File("config/programa.dat");
			if(f.exists() && !f.isDirectory()) 
			{
				return true;
			}
			else
			{
				return false;
			}
		}
	}
		
	
	public String[] pegarCamposDeAplicacao()
	{
		LinkedList<String> retorno = new LinkedList<String>();
		
		//serah que o arquivo ja existe?
		boolean existeArquivo = this.existeArquivoaplicacaodatECriaPastaConfigSeNaoExistir();
		if(existeArquivo == false)
		{
			//nao existe. Vamos cria-lo e retornar os valores default!
			this.criarArquivoCamposDeAplicacao();
			
			for(int i = 0; i < this.opcoesTiposDeAplicacaoDefault.length; i++)
			{
				String umElemento = this.opcoesTiposDeAplicacaoDefault[i];
				retorno.add(umElemento);
			}
		}
		else
		{
			//existe! Vamos ler do arquivo
			String textoNoArquivo = 
					LeitorArquivoTexto.lerArquivoQualquerDeTexto("config/aplicacao.dat");
			
			String textoNoArquivoSemEspacos = textoNoArquivo.replaceAll("\\s+",""); //remove todos os espacos em branco
			String[] elementosNoArquivo = textoNoArquivoSemEspacos.split(";");
			
			for(int i = 0; i < elementosNoArquivo.length; i++)
			{
				if(elementosNoArquivo[i].length() > 0)
				{
					String umElemento = elementosNoArquivo[i];
					String parteImportanteUmElemento = umElemento.substring(0, umElemento.indexOf("/")); //vamos tirar td que estiver depois da /, que eh o tooltip
					
					//ele pode ser um nome composto que nos tiramos o espaco em branco! Vamos ver
					String umElementoComEspacoEmBranco = "";
					boolean passouOTravessao = false; //so considera depois do travessao
					for(int ii = 0; ii < parteImportanteUmElemento.length(); ii++)
					{
						String umaLetra = String.valueOf(parteImportanteUmElemento.charAt(ii));
						if(umaLetra.compareTo("-") == 0)
						{
							passouOTravessao = true;
						}
						
						if(passouOTravessao == true && Character.isUpperCase(parteImportanteUmElemento.charAt(ii)) == true)
						{
							umElementoComEspacoEmBranco = umElementoComEspacoEmBranco + " ";
						}
						
						umElementoComEspacoEmBranco = umElementoComEspacoEmBranco + umaLetra;
					}
					
					retorno.add(umElementoComEspacoEmBranco);
				}
			}
		}
		
		//falta transformar essa linkedlist em array
		String[] arrayRetorno = new String[retorno.size()];
		for(int j = 0; j < retorno.size(); j++)
		{
			arrayRetorno[j] = retorno.get(j);
		}
		return arrayRetorno;
	}
	
	public String[] pegarTiposDePrograma()
	{
		LinkedList<String> retorno = new LinkedList<String>();
		
		//serah que o arquivo ja existe?
		boolean existeArquivo = this.existeArquivoprogramadatECriaPastaConfigSeNaoExistir();
		if(existeArquivo == false)
		{
			//nao existe. Vamos cria-lo e retornar os valores default!
			this.criarArquivoTiposDePrograma();
			
			for(int i = 0; i < this.opcoesTipoDeProgramaDefault.length; i++)
			{
				String umElemento = this.opcoesTipoDeProgramaDefault[i];
				retorno.add(umElemento);
			}
		}
		else
		{
			//existe! Vamos ler do arquivo
			String textoNoArquivo = 
					LeitorArquivoTexto.lerArquivoQualquerDeTexto("config/programa.dat");
			
			String textoNoArquivoSemEspacos = textoNoArquivo.replaceAll("\\s+",""); //remove todos os espacos em branco
			String[] elementosNoArquivo = textoNoArquivoSemEspacos.split(";");
			
			for(int i = 0; i < elementosNoArquivo.length; i++)
			{
				if(elementosNoArquivo[i].length() > 0)
				{
					String umElemento = elementosNoArquivo[i];
					String umElementoQueImporta = umElemento.substring(0, umElemento.indexOf("/")); //vamos tirar td que estiver depois da /, que eh o tooltip
					
					//ele pode ser um nome composto que nos tiramos o espaco em branco! Vamos ver
					String umElementoComEspacoEmBranco = "";
					boolean passouOTravessao = false; //so considera depois do travessao
					for(int ii = 0; ii < umElementoQueImporta.length(); ii++)
					{
						String umaLetra = String.valueOf(umElementoQueImporta.charAt(ii));
						if(umaLetra.compareTo("-") == 0)
						{
							passouOTravessao = true;
						}
						
						if(passouOTravessao == true && Character.isUpperCase(umElementoQueImporta.charAt(ii)) == true)
						{
							umElementoComEspacoEmBranco = umElementoComEspacoEmBranco + " ";
						}
						
						umElementoComEspacoEmBranco = umElementoComEspacoEmBranco + umaLetra;
					}
					
					retorno.add(umElementoComEspacoEmBranco);
				}
			}
		}
		
		//falta transformar essa linkedlist em array
		String[] arrayRetorno = new String[retorno.size()];
		for(int j = 0; j < retorno.size(); j++)
		{
			arrayRetorno[j] = retorno.get(j);
		}
		return arrayRetorno;
	}
	
	public String[] pegarTooltipCamposDeAplicacao()
	{
		LinkedList<String> retorno = new LinkedList<String>();
		
		//serah que o arquivo ja existe?
		boolean existeArquivo = this.existeArquivoaplicacaodatECriaPastaConfigSeNaoExistir();
		if(existeArquivo == false)
		{
			//nao existe. Vamos cria-lo e retornar os valores default!
			this.criarArquivoCamposDeAplicacao();
			
			for(int i = 0; i < this.tooltipsTiposDeAplicacaoDefault.length; i++)
			{
				String umElemento = this.tooltipsTiposDeAplicacaoDefault[i];
				retorno.add(umElemento);
			}
		}
		else
		{
			//existe! Vamos ler do arquivo
			String textoNoArquivo = 
					LeitorArquivoTexto.lerArquivoQualquerDeTexto("config/aplicacao.dat");
			
			String[] elementosNoArquivo = textoNoArquivo.split(";");
			
			for(int i = 0; i < elementosNoArquivo.length; i++)
			{
				if(elementosNoArquivo[i].length() > 0)
				{
					String umElemento = elementosNoArquivo[i];
					String umElementoQueImporta = umElemento.substring(umElemento.indexOf("/")+1); //vamos tirar td que estiver antes da /, queremos soh o tooltip
					
					//ele pode ser um nome composto que nos tiramos o espaco em branco! Vamos ver
					String umElementoComEspacoEmBranco = "";
					boolean passouOTravessao = false; //so considera depois do travessao
					for(int ii = 0; ii < umElementoQueImporta.length(); ii++)
					{
						String umaLetra = String.valueOf(umElementoQueImporta.charAt(ii));
						if(umaLetra.compareTo("-") == 0)
						{
							passouOTravessao = true;
						}
						
						if(passouOTravessao == true && Character.isUpperCase(umElementoQueImporta.charAt(ii)) == true)
						{
							umElementoComEspacoEmBranco = umElementoComEspacoEmBranco + " ";
						}
						
						umElementoComEspacoEmBranco = umElementoComEspacoEmBranco + umaLetra;
					}
					
					retorno.add(umElementoComEspacoEmBranco);
				}
			}
		}
		
		//falta transformar essa linkedlist em array
		String[] arrayRetorno = new String[retorno.size()];
		for(int j = 0; j < retorno.size(); j++)
		{
			arrayRetorno[j] = retorno.get(j);
		}
		return arrayRetorno;
	}
	
	public String[] pegarTooltipTiposDePrograma()
	{
		LinkedList<String> retorno = new LinkedList<String>();
		
		//serah que o arquivo ja existe?
		boolean existeArquivo = this.existeArquivoprogramadatECriaPastaConfigSeNaoExistir();
		if(existeArquivo == false)
		{
			//nao existe. Vamos cria-lo e retornar os valores default!
			this.criarArquivoTiposDePrograma();
			
			for(int i = 0; i < this.tooltipsTipoDeProgramaDefault.length; i++)
			{
				String umElemento = this.tooltipsTipoDeProgramaDefault[i];
				retorno.add(umElemento);
			}
		}
		else
		{
			//existe! Vamos ler do arquivo
			String textoNoArquivo = 
					LeitorArquivoTexto.lerArquivoQualquerDeTexto("config/programa.dat");
			
			String[] elementosNoArquivo = textoNoArquivo.split(";");
			
			for(int i = 0; i < elementosNoArquivo.length; i++)
			{
				if(elementosNoArquivo[i].length() > 0)
				{
					String umElemento = elementosNoArquivo[i];
					String umElementoQueImporta = umElemento.substring(umElemento.indexOf("/")+1); //vamos tirar td que estiver antes da /, queremos soh o tooltip
					
					//ele pode ser um nome composto que nos tiramos o espaco em branco! Vamos ver
					String umElementoComEspacoEmBranco = "";
					boolean passouOTravessao = false; //so considera depois do travessao
					for(int ii = 0; ii < umElementoQueImporta.length(); ii++)
					{
						String umaLetra = String.valueOf(umElementoQueImporta.charAt(ii));
						if(umaLetra.compareTo("-") == 0)
						{
							passouOTravessao = true;
						}
						
						if(passouOTravessao == true && Character.isUpperCase(umElementoQueImporta.charAt(ii)) == true)
						{
							umElementoComEspacoEmBranco = umElementoComEspacoEmBranco + " ";
						}
						
						umElementoComEspacoEmBranco = umElementoComEspacoEmBranco + umaLetra;
					}
					
					retorno.add(umElementoComEspacoEmBranco);
				}
			}
		}
		
		//falta transformar essa linkedlist em array
		String[] arrayRetorno = new String[retorno.size()];
		for(int j = 0; j < retorno.size(); j++)
		{
			arrayRetorno[j] = retorno.get(j);
		}
		return arrayRetorno;
	}

}
