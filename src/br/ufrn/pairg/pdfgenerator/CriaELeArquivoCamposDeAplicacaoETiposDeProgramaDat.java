package br.ufrn.pairg.pdfgenerator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.LinkedList;

public class CriaELeArquivoCamposDeAplicacaoETiposDeProgramaDat 
{
	private String[] opcoesTiposDeAplicacaoDefault = { "AD01 - Administr", "AD02 - Função Adm", "AD03 - Modern Adm", "AD04 - Adm Publ", "AD05 - Adm Empres","AD06 - Adm Prod","AD07 - Adm Pes","AD08 - Adm Materl","AD09 - Adm Patrim","AD10 - Marketing","AD11 - Adm Escrit",
			"AG01 - Agricultur","AG02 - Ciênc Agrl","AG03 - Adm Agricl","AG04 - Econom Agríc","AG05 - Sist agríc","AG06 - Eng agrícl","AG07 - Edafologia","AG08 - Fitopatol","AG09 - Prod Veget","AG10 - Prod Animl","AG11 - Ciênc Flor","AG12 - Aquacultur","AG13 - Extr Veget","AG14 - Extr Animl",
			"AN01 - Sociedade","AN02 - Desenv soc","AN03 - Grupos soc","AN04 - Cultura","AN05 - Religião","AN06 - Antropolog","AN07 - Sociologia",
			"AH01 - Assen Hum","AH02 - Cidade","AH03 - Org Territ","AH04 - Pol As Hum","AH05 - População","AH06 - Discip Aux",
			"BL01 - Biologia","BL02 - Genética","BL03 - Citologia","BL04 - Microbiolg","BL05 - Anatomia","BL06 - Fisiologia","BL07 - Bioquímica","BL08 - Biofísica",
			"BT01 - Botânica","BT02 - Fitogeograf","BT03 - Botân Econ","BT04 - Botân Sist",
			"CO01 - Filosofia","CO02 - Ciência","CO03 - Ciênc Ling","CO04 - Comunic","CO05 - Arte","CO06 - História",
			"CC01 - Construção","CC02 - Proc Const","CC03 - Org Constr","CC04 - Obra Públ","CC05 - Estrutura","CC06 - Edificação","CC07 - Tecn Const","CC08 - Hig Const","CC09 - Eng Hidrl","CC10 - Solo",
			"DI01 - Legislação","DI02 - Dir Constl","DI03 - Disc Dr.",
			"EL01 - Ecologia","EL02 - Ecofisiol","EL03 - Ecol Human","EL04 - EcVeg/Anm","EL05 - Etologia",
			"EC01 - Economia","EC02 - AnMicroec","EC03 - TeoMicroe","EC04 - AtivEconm","EC05 - Contab Nac","EC06 - Econ Monet","EC07 - Mercado","EC08 - Bens Econom","EC09 - Eng/Din Ec","EC10 - Econ Espec","EC11 - Propriedad","EC12 - Ec Internac","EC13 - Polít Econ","EC14 - Empresa",
			"ED01  -  Ensin Regl","ED02 - Ensin - Supl","ED03 - Adm/Pr Ens","ED04 - Formas Ens","ED05 - Currículo","ED06 - Educação",
			"EN01  -  Energia","EN02 - Rec Energ","EN03 - Combustívl","EN04 - Tecn Energ","EN05 - Eng Eltrôn","EN06 - Eng Nucle",
			"FN01  -  Finan Públ","FN02 - Finan Priv","FN03 - Sist Finan","FN04 - Rec/Instrum","FN05 - Adm Finan","FN06 - Contabilid",
			"FQ01  -  Fís Partíc","FQ02 - Acúst/Ótic","FQ03 - Onda","FQ04 - Metrologia","FQ05 - Mecânica","FQ06 - Fis Solid","FQ07 - Termodinâm","FQ08 - Eletrônica","FQ09 - Magn/Elmag","FQ10 - Fís SupDis","FQ11 - Radiação","FQ12 - Espectrosc","FQ13 - Fís Molecl","FQ14 - Química","FQ15 - Quím An/Po","FQ16 - Fís - Quím","FQ17 - Quím Orgân","FQ18 - Quím Inorg",
			"GC01  -  Geog Físic","GC02 - Geog Humna","GC03 - Geog Regio","GC04 - Orient Geo","GC05 - Geodesia","GC06 - Topografia","GC07 - Fotogramet","GC08 - Mapeamento","GC09 - Met Cartog","GC10 - Plan Carto",
			"GL01  -  Geol Físic","GL02 - Glaciolog","GL03 - Geotectonc","GL04 - Geol Marin","GL05 - Geol Hist","GL06 - Geol Econ","GL07 - GeoQuiFiTe",
			"HB01  -  Habitação","HB02 - Tipol Habt",
			"HD01  -  Hidrologia","HD02 - Hidrograf","HD03 - Hidrometr","HD04 - Oceanograf",
			"IN01  -  Indústria","IN02 - Tecnologia","IN03 - Engenharia","IN04 - Ind Ext Mi","IN05 - Ind Transf",
			"IF01  -  Informação","IF02 - Documentaç","IF03 - Reprograf","IF04 - Documento","IF05 - Biblioteco","IF06 - Arquivolog","IF07 - Ciênc Info","IF08 - Serv Info","IF09 - Uso Inform","IF10 - Genérico",
			"MT01  -  Lógica Mat","MT02 - Álgebra","MT03 - Geometria","MT04 - Anális Mat","MT05 - Cálculo","MT06 - Mat Aplic",
			"MA01  -  Meio Amb","MA02 - Recurs Nat","MA03 - Poluição","MA04 - Qualid Amb",
			"ME01  -  Metodolg","ME02 - Atmosfera","ME03 - Climatolog",
			"PD01  -  Pedologia","PD02 - Pedogênese","PD03 - Tipos de Solo",
			"PL01  -  Ciênc Pol","PL02 - Política",
			"PR01  -  Previdênc","PR02 - Benef Prev","PR03 - Assist Soc",
			"PS01  -  Psicologia","PS02 - Comportamt","PS03 - Teor Psic",
			"SM01  -  Saneamento","SM02 - Resíduo","SM03 - Limpeza","SM04 - Abast água","SM05 - Esgoto",
			"SD01  -  Saúde","SD02 - Adm Sanit","SD03 - Doença","SD04 - Defic Fís","SD05 - Assist Méd","SD06 - Terap Diag","SD07 - Medicina","SD08 - Espec Med","SD09 - Eng Biomed","SD10 - Farmacolog","SD11 - Odontolog",
			"SV01  -  Serviços","SV02 - Seguro","SV03 - Comércio","SV04 - Turismo",
			"TC01  -  Telecom","TC02 - Sist Telec","TC03 - Eng Telec","TC04 - Serv/Redes",
			"TB01  -  Trabalho","TB02 - Rec Human","TB03 - Merc Trab","TB04 - Cond Trab","TB05 - Estr Ocup","TB06 - Lazer",
			"TP01  -  Transporte","TP02  - Sist Trans","TP03 - Serv Trans","TP04 - Eng Transp","TP05 - Mod Transp",
			"UB01  -  Urbanismo","UB02 - Solo urban","UB03 - Área urban","UB04 - Circ Urban","UB05 - Arquitetur"};//serah colocado na combobox

	private String[] opcoesTipoDeProgramaDefault = {"AP01 - Aplicativo","AP02 - Planejament","AP03 - Controle","AP04 - Auditoria",
			"AP05 - Contabiliz","AT01 - Automação","AT02 - Atm Escrt","AT03 - Atm Comerc","AT04 - Atm Bancar",
			"AT05 - Atm Indust","AT06 - Contr Proc","AT07 - Atm Manuf","AT08 - Elet Autom","AV01 - Aval Desemp",
			"AV02 - Cont Recurs","CD01 - Com Dados","CD02 - Emul Termnl","CD03 - Monitor TP","CD04 - Ger Dispost",
			"CD05 - Ger de Rede","CD06 - Rede Local","CT01 - Comutação","CT02 - Impl Fun Ad","CT03 - Ger Op&Man",
			"CT04 - Term Op&Man","DS01 - Ferrm Desnv","DS02 - Gerd Aplic.","DS03 - CASE","DS04 - Desv c/Metd",
			"DS05 - Bib Rotinas","DS06 - Apoio Progm","DS07 - Sup Documt","DS08 - Convers Sis","ET01 - Entrtmnto",
			"ET02 - Jogos Anim","ET03 - Gerad Desen","ET04 - Simuladores","FA01 - Ferrm Apoio","FA02 - Proc Texto",
			"FA03 - Planil Elet","FA04 - Gerad Gráfc","GI01 - Gerenc Info","GI02 - Gerenc BD","GI03 - Gerad Telas",
			"GI04 - Gerad Relat","GI05 - Dicion Dad","GI06 - Ent Val Dad","GI07 - Org Man Arq","GI08 - Recup Dados",
			"IA01 - Intlg Artf","IA02 - Sist Especl","IA03 - Proc Lng Nt","IT01 - Instrument","IT02 - Inst T&M",
			"IT03 - Inst Biomd","IT04 - Inst Analt","LG01 - Linguagem","LG02 - Compilador","LG03 - Montador",
			"LG04 - Pré - Compld","LG05 - Comp Cruz","LG06 - Pré - Proces","LG07 - Interptd","LG08 - Ling Procd",
			"LG09 - Ling N Prcd","PD01 - Seg Prot Dd","PD02 - Senha","PD03 - Criptograf","PD04 - Man Intg Dd",
			"PD05 - Cont Acess","SM01 - Simul & Mod","SM02 - Simulador","SM03 - Sim Amb Op","SM04 - CAE/CAD/CAM",
			"SO01 - Sist Operac","SO02 - Interf E&S","SO03 - Interf Disc","SO04 - Interf Com","SO05 - Geren Usuar",
			"SO06 - Adm Dispost","SO07 - Cont Proces","SO08 - Cont Redes","SO09 - Proc Comand","TC01 - Aplc Tcn Ct",
			"TC02 - Pesq Operac","TC03 - Recnh Padr","TC04 - Proc Imagem","TI01 - Teleinform","TI02 - Terminais",
			"TI03 - Transm Dados","TI04 - Comut Dados","UT01 - Utilitários","UT02 - Compress Dd","UT03 - Conv Arq",
			"UT04 - Class/Inter","UT05 - Cont Spool","UT06 - Transf Arq"};

	
	private String[] tooltipsTiposDeAplicacaoDefault = {"desenvolv. organizacional,desburocratização", 
			"Planejamento governamental:estratégico, operacional, técnica de planej.,organização administr.,organização funcional, organograma, estrutura organizacional, controle administr. -  análise de desempenho, avaliação de desempenho", 
	        "análise organizacional, O&M",
	        "Administr. Federal, Estadual, Municipal, direito administr., reforma administr., intervenção do Estado na economia, controle da administr. pública",
	        "administr., de negócios, privada, organização de empresas",
	        "planejamento da fábrica, engenharia do produto, protótipo, planejamento da produção, controle de qualidade",
	        "planejamento de pessoal - recrutamento, seleção, admissão, avaliação, promoção, etc",
	        "planejamento de material, aquisição, armazenamento, almoxarifado, alienação, controle de material, de estoque, inventário, requisição de material",
	        "inventário patrimonial, fiscalização, conservação, manutenção do patrimônio",
	        "mercadologia, administr. de marketing ou mercadológica, análise, e pesquisa de mercado, estratégia de marketing, composto do produtomarca-embalagem administr. de vendas - planejamento de vendas - controle de vendas",
	        "serviços de escritório - comunicação administr., arquivo de escritório, etc",
	        "agropecuária, desenvolvimento rural, extensão rural, planejamento e política agrícola, zoneamento agrícola",
	        "agrologia, agronomia, agrostologia, edafologia, pomologia",
	        "imóvel rural: fazenda - granja empresa rural",
	        "economia agrícola",
	        "agricultura extensiva, intensiva, itinerante, monocultura, policultura",
	        "construção rural: açude - barragem, estufa, habitação rural, drenagem, irrigação",
	        "conservação de solo, controle da erosão, melhoramento, recuperação,tratamento, manejo do solo: adubação, fertilização",
	        "doenças e pragas vegetais, defensivo agrícola",
	        "produção agrícola, fitotecnia: cultura agrícola, lavoura, cultivo - técnica agrícola",
	        "produto animal, zootecnia: tipos de criação, veterinária ou medicina veterinária, zoopatologia, produto veterinário, veterinária preventiva",
	        "Ciências Florestais (dasonomia, economia florestal, política florestal, produção vegetal, silvicultura, arboricultura-florestamento, reflorestamento, terra marginal)",
	        "aquacultura ou aquicultura animal, vegetal",
	        "Extrativismo vegetal (produto extrativo vegetal: celulose, cera, fibra, goma natural, madeira, látex)",
	        "Extrativismo Animal (caça, pesca, prospecção produto extrativo animal: couro-pele-pescado)",
	        "sistema social, estrutura, situação, mobilização, controle, mudança e reforma social",
	        "planejamento social, política social, ação social, bem-estar social, nível ou padrão de vida",
	        "tribo, bando, etnia, grupo local, desenvolvimento comunitário, nação, indivíduo",
	        "civilização, cultura popular: folclore - uso e costumes",
	        "doutrina, teologia, prática religiosa, etc.",
	        "antropologia física: antropometria - paleantropologia, enologia: etnografia - ernologia, etnografia, antropologia: economia - urbana - política",
	        "sistemática, comparada aplicada: urbana - rural - política - econômica - do trabalho - da educação - do direito, sociografia, pesquisa social, processo social",
	        "povoamento, núcleo populacional, invasão, assentamento rural, urbano, cinturão verde",
	        "metrópole, região ou área metropolitana, rurópolis",
	        "Organização Territorial (organização do espaço, rede urbana, conurbação)",
	        "Políticas de Assentamento Humanos (política demográfica, migratória, planejamento familiar, política de colonização, de desenvolvimento urbano ou política urbana)",
	        "distribuição da população, mobilidade ou movimento da população, migração, dinâmica populacional",
	        "Disciplinas Auxiliares (demografia, geografia urbana, agrária, teoria dos limiares ou localização, teoria da polarização)",
	        "ser vivo, substância orgânica, leis biológicas, biotipologia, biometria, bioclimatologia, parasitologia, filogenia ou evolução, geobiologia, histologia, limnologia",
	        "citogenética, engenharia genética, genotipo, hereditariedade, melhoramento genético, gen, genética das populações",
	        "ou biologia celular, célula, meiose, etc",
	        "bacteriologia, virologia, biogeografia",
	        "sistemas: cardiovascular - digestivo - tegumentar, etc, embriologia, secreção, excreção, órgãos dos sentidos",
	        "nascimento, digestão, reprodução, sexualidade, nemofisiologia, metabolismo",
	        "aminoácido, proteína, hormônio, fenômeno bioquímicos: biossíntese - fermentação - osmose, etc",
	        "bioenergética, biomecânica, eletrofisiologia",
	        "fitologia, vegetal, vegetação, morfologia, fisiologia vegetal, quimiossíntese, genética vegetal, fitossociologia, biologia floral",
	        "geografia botânica ou botânica geográfica, caatinga, cerrado, campo, mangue, etc",
	        "planta condimentícia, daninha ou nociva, aromática, feculenta, têxtil, cereal, legume, hortaliça, grão alimentício",
	        "taxonomia vegetal",
	        "metafísica, estética, ética, filosofia social, teoria do conhecimento, hermenêutica, lógica, dialética, doutrina filosófica",
	        "ciências humanas e sociais, naturais, biológicas, geociência, política científica, desenvolvimento científico, história da ciência, filosofia da ciência, metodologia científica, metodologia, pesquisa ou investigação, pesquisa aplicada - indicar a área específica com outro código, instituição de pesquisa",
	        "linguística, geolinguística, sociolinguística e linguagem popular, linguagem: natural, artificial)",
	        "comunicação humana, escrita, visual, social: comunicação de massa, propaganda, relações públicas, meios de comunicação: radiocomunicação, imprensa, pesquisa de opinião, arte gráfica: editoração, editoração, impressão, edição",
	        "criação artística, patrimônio artístico, industrial, fotografia, aerofotografia, cinema, música, literatura",
	        "política, econômica, social, pesquisa histórica: arqueologia, numismática, genealogia, filatelia, epigrafia, patrimônio histórico",
	        "construção civil: habitacional, comercial, industrial: construção industrializada ou pré-fabricada",
	        "Processo Construtivo (tradicional, convencional, misto, evoluído, cantaria, adobe, alvenaria, concreto, máquina de construção, equipamento para construção)",
	        "Organização da construção (licitação de obra, custa da construção, memorial descritivo de obra, gerência de projeto de construção, execução da obra, fiscalização de obra, racionalização da construção, coordenação dimensional, coordenação modular, suprimento de obra)",
	        "engenharia civil, engenharia de avaliações, contrato de obra pública, licitação de obra pública, obra de grande porte, obra de arte, como engenharia civil",
	        "cálculo estrutural, análise de estrutura, mecânica das estruturas: esóstica, plana, retocila, etc, tipo de estrutura: concreto, aço, metálico, inflável, etc, armadura: estrutural, armadura para concreto armado",
	        "prédio, edifício, elemento construtivo: fundação, pilar, viga, componente construtivo: painel, instalações, manutenção da construção, obra: de acabamento, melhoria, demolição",
	        "ancoragem, apiloagem, caleamento estrutural, cimbramento, concretagem, escoramento, terraplanagem, pavimentação",
	        "Higiene das construções (ventilação, iluminação, conforto térmico, isolamento: acústico, térmico, e higroscópico)",
	        "obra hidráulica ou estrutura hidráulica, conduto hidráulico, tubulação, canal, reservatório: lago artificial, piscina, açude eclusa, dispositivos de controle de água: comporta, polder, reguladora de nível, barragem, drenagem, hidráulica do solo",
	        "mecânica das rochas, mecânicas dos solos, aterro, escavação, talude, movimento de terra, obra de terra, nivelamento de terra, obra de contenção: estrutura de arrimo, contenção de encosta",
	        "federal, estadual, municipal, hierárquica das leis, constituição, lei ordinária, etc, proteção da lei ou proteção legal, hermenêutica jurídica ou interpretação das leis",
	        "Direito Constitucional (poder constituinte, organização nacional: união, estado, município, distrito federal, território federal, poderes do estado, legislativo, executivo, judiciário, declaração de direitos: nacionalidade, direitos políticos, etc, direito eleitoral)",
	        "Outras Disciplinas do Direito (disciplinar, previdenciário, ecológico, urbanístico, econômico, financeiro, tributário: cálculo do tributo, evasão tributária, infração tributária, etc, direito processual civil, direito penal, direito processual penal, direito internacional público, direitos do homem ou humanos, litígio internacional, direito privado, direito civil, bens: propriedade pública, privada, patrimônio, semoventes, imóveis, públicos, direito de família, direito das coisas: direito autoral, enfiteuse ou aforamento, laudemio, registro imobiliário, direito sucessório: herança, sucessão, inventário, direito das obrigações: acordo, convênio, contrato, loca ção, arrendamento, fiança, direito agrário, direito do trabalho, direito comercial, direito industrial, direito marítimo, direito aeronáutico, direito internacional privado",
	        "biosfera, relação biótica, relação abiótica, ecologia agrícola, aquática, florestal, equilíbrio/desequilíbrio ecológico, fenômeno ecológico",
	        "ecofisiologia animal, vegetal, distrófico, digotrófico, eutrófico, etc.",
	        "ecodesenvolvimento, ecologia social, ecologia urbana",
	        "Ecologia Vegetal/Ecologia Animal (autoecologia, sinecologia, habitat, vida selvagem)",
	        "migração, anodromo,catadromo, piracema, hibernação, comportamento animal, comportamento vegetal",
	        "teoria econômica, metodologia da economia: modelos e econometria, análise econômica, sistema econômico",
	        "microeconomia, teoria da oferta, teoria da produção, função da produção, economias de escala, teoria dos custos, elasticidade da oferta: preço e renda, teoria da demanda ou teoria do consumidor, teoria da utilidade ou análise cardinal, teoria dos mercados, teoria do preço ou do valor, teoria do equilíbrio econômico, teoria do bem-estar, ou economia social ou teoria dadistribuição da renda, ótimo de pareto curva de Lorenz, custo social",
	        "ou microeconomia ou teoria microeconômica, demanda agregada, oferta agregada, venda, nível de emprego",
	        "setor econômico ou setor de produção, setores: primário, secundário, terciário, público, privado, informal ou economia silenciosa ou invisível ou mercado informal, fator de produção, distribuição da renda, produtividade, superprodução, consumo, poupança, interna, externa, entesouramento, poupança forçada, investimento, formação de capital, recursos econômicos ou riqueza, indicador econ., indexação, desindexação, desenvolv. econ. local, regional, nacional, setorial, integrado, crescimento econ., desempenho econ., disparidade econ., acumulação de capital",
	        "ou contabilidade social ou conta nacional, agregado econômico: PIB, PNB, PNL, PIL, renda nacional, análise de insumo - produto ou input - output ou de relações intersetoriais, ou análise de Leontief, ou insumo-produto",
	        "moeda: criação, circulação, flutuação, sist. monetário: tipos de moeda e meios de pagto ou meio circulante, base monetária, unidade monetária, moeda divisionária, reforma monetária",
	        "demanda, oferta, mercado consumidor, mercado externo ou externo ou exterior, mercado interno, internacional, produtor, paralelo, a termo, preço",
	        "bens de consumo, de capital, insumo, bens: duráveis, não duráveis, tangíveis, intangíveis, inferiores, normais, de Giffen",
	        "Engenharia econômica/dinâmica econômica (análise custo/benefício ou custo benefício, pay-out ou prazo de refluxo, ciclo econômico ou flutuação econômica, nível dos preços: inflação, deflação, conjuntura econômica)",
	        "ou ciência regional ou economia regional, economia local, urbana, regionalização",
	        "propriedade do capital, da terra ou propriedade fundiária, estrutura agrária, loteamento",
	        "ou relações econômicas, balanço de pagamentos: balança comercial, balança de serviços, movimento de capitais internacionais, protecionismo, livre comércio, câmbio: conversibilidade da moeda, controle cambial, câmbio livre, taxa de câmbio, estatização monetária, valorização da moeda, minidesvalorização, maxidesvalorização, mercado cambial, divisas, reservas monetárias, dívida externa, integração econômica internacional, zona monetária, cooperação econômica, bloqueio econômico",
	        "política fiscal, monetária, de crédito, econômica internacional, de comércio exterior, de desenvolvimento econômico, de desenvolvimento nacional, de distribuição da renda, agrária, de preços, estatização, privatização, planejamento econômico",
	        "total, média, marginal, custo ou custo operacional: total, médio, etc, tipos de empresa: pública, privada, multinacional, estrangeira, microempresa, de pequeno, médio e grande porte, nacional, cooperativa, concentração econômica: holding, conglomerado de empresas, combinação de empresas, consórcio de empresas, truste, joint-venture",
	        "Ensino regular (pré-escolar, 1º grau, 2º grau, superior, pós-graduação, orientação profissional)",
	        "Ensino supletivo (alfabetização, aprendizagem, comercial, industrial, agrícola, suprimento: curso de atualização, de aperfeiçoamento, treinamento)",
	        "Instituição/Administração/Processo de ensino (jardim escolar, escola maternal, jardim de infância, escola: de 1º grau, 2º grau, centro de ensino, de estudo supletivo, universidade, faculdade ou instituto superior de ensino, evasão escolar, serviços educacionais, equipamento escolar, método de ensino, didática: técnica de ensino, prática de ensino, ensino integrado, processo formal de ensino, processo não formal de ensino)",
	        "Formas de ensino/material instrucional (ensino direto, teleducação, por correspondência, radioeducação, ensino semi-indireto, módulo instrucional, equipamento didático, material audio-visual aprendizagem cognitiva, psicomotora, afetiva, autodidatismo)",
	        "currículo ou programa de ensino, reforma de ensino, currículo mínimo, etc, corpo docente, corpo discente, graus e diplomas",
	        "pedagogia, ensino, sistema educacional, rede de ensino, educação de adulto, educação de base, de massa, etc, política educacional, educação ectra-escolar: educação comunitária, recuperadora",
	        "política energética, economia energética: consumo de energia, empresa de energia",
	        "Recursos/serviços/formas de energia (recursos hidrelétricos ou hidroelétricos, carboníferos, petrolíferos, uran íferos, serviços de energia elétrica, de gás canalizado, formas: energia elétrica, mec ânica, química, radiante, luminosa, sonora, térmica, etc)",
	        "fóssil, de origem vegetal, biomassa, nuclear, sólido, líquido, gasoso",
	        "Tecnologia e Energia (fonte de energia: convencional, alternativa, geração de energia, usina de energia, conversão de energia, armazenamento de energia, transporte de energia, distribuição de energia: eletrificação, engenharia elétrica, medição de energia)",
	        "microeletrônica, circuito eletrônico, eletrônica industrial, semicondutor",
	        "tecnologia de reatores, reator nuclear",
	        "receita pública, orçamento público, sistema tributário, despesa pública, crédito público, administração fiscal",
	        "financiamento privado",
	        "instituição financeira, operações financeiras: operação de crédito, bancária, de fiança, de câmbio, de sero, open market, hedge, overnight, cobrança, mercado de capitais",
	        "Recursos/Orçamento/Instrumentos (aplicação de recursos, capital, recursos orçamentários, fundos, orçamento: analítico, de custeio de capital, empresarial ou privado, público, de aplicação, de caixa, de receita e despesa, familiar, título de crédito, ação, cartão de crédito, caderneta de poupança, financiamento)",
	        "administração financeira, juro, crédito, débito, loteria (planejamento financeiro, política financeira, controle financeiro, análise financeira, assistência financeira, juro de mora, taxa de juro, spread, crédito: especialização geral, público, internacional, tributário, débito, débito fiscal, loteria esportiva, loto)",
	        "contabilidade, financeira, gerencial, técnicas cont ábeis, demonstração de resultado: receita/despesa, resultado contábil, balancete, demonstração de lucros e prejuízos acumulados, demonstração de origens e aplicações de recursos, depreciação, exaustão",
	        "Matéria/Física das Partículas/e dos íons (antimatéria, valência composição de matéria, estados da matéria, partícula ótica, partícula elementar, partícula carregada, ionização)",
	        "onda sonora, som, luz, ótica geométrica, microscópica, física, alidade, microscopia, solametria",
	        "amplitude, difração, frequência, modulação, demodulação, reflexão, refração, propagação, ressonância e tipos de onda",
	        "unidade de medida, dimensão, análise dimensional, equação dimensional, sistema de medida, medição: macro e micro-medição",
	        "estática, dinâmica, cinemática, cinética, espaço, tempo, movimento, momento, força, densidade, massa, volume, resistência dos materiais, trabalho (potência)",
	        "Física dos Sólidos/ dos Fluídos/ dos Plasmas (mecânica dos sólidos, propriedade dos sólidos, estrutura dos sólidos, mecânica dos fluídos, dinâmica dos fluídos, estática dos fluídos, cinemática dos fluídos, mecânica dos gases, hidromecânica, viscosidade, plasma-física)",
	        "calor, calorimetria, temperatura, radiação térmica, tratamento térm., termologia, propried. termodinâmica",
	        "quântica, linear, não linear",
	        "Magnetismo/Eletromagnetismo (campo, polo, circuito e propriedade magnética, interferência eletromagnética, propriedade eletromagnética, onda eletromagnética, radiação monocromática, micro-onda, polarização espontânea, onda hertziana)",
	        "Física de Superfície/de Dispersão (tensão superficial, capilaridade, física coloidal)",
	        "efeito da radiação, radiação atmosférica, radiação ionizante",
	        "espectrografia, espectrometria, espectroscopia atômica, molecular e ótica, espectrofotometria",
	        "Física Molecular (ou física atômica, reação nuclear, estrutura molecular, radiatividade, radiometria)",
	        "composto químico, substância combustível, substância química, propriedade química, legação química, radical químico, reação química, composição química, polímero inorgânico",
	        "Química Analítica / dos polímeros (análise químicas calorimetria, condumetria, cromatografia, polímero orgânico, polímero inorgânico)",
	        "análise físico-química, processos físicos-químicos",
	        "composto orgânico, ácido, sal",
	        "elemento químico, metal, gases raros, terras raras, composto inorgânico, nuclídeo",
	        "ou fisiografia, paleografia, geomorfologia, acidente geográfico, morfologia genética, morfologia fisiológica",
	        "ou antopogeografia, geografia econômica, política, da população",
	        "região: homogênea, elementar, zona geográfica: tórrida, subtropical",
	        "pontos cardeais, colaterais, hemisfério",
	        "astronômica, espacial, gravimétrica, geométrica, levantamento geodésico",
	        "topometria, planimetria, altimetria, acidente topográfico, sensoramento remoto ou monitoreamento remoto",
	        "fotogrametria terrestre, aerofotogrametria, etc",
	        "fotogramia, mapa, carta, fotocarta, mosaico, etc",
	        "Métodos e Processos de Cartografia (processo astrogeodésico, método das direções, método de Schreiber, de Sterneck, etc.)",
	        "Plano Cartográfico (azimute / posição / ponto meridiano, paralelo, círculo horário, etc, azimute de Laplace, geodésico, da carta, etc, triângulo de posição, polar, culminação, etc, polo geográfico, celeste, elevado, etc)",
	        "dinâmica externa: intemperismo, eluviação, iluvia ção, erosão, dinâmica interna: tectonismo, magma, etc, geologia estrutural: anticlinal, sinclinal, dobra, junta, foliação",
	        "ou criologia, glaciação, moraina",
	        "Geotectônica (tectônica, geodinâmica, sismologia)",
	        "Geologia Marinha (fotogeologia: mapeamento geológico)",
	        "paleontologia, arcabouço tectônico da terra, sedimentologia, estratigrafia",
	        "petrologia, petrografia, gênese de jazida: metalgenia, mineralização, jazida mineral, prospecção, mineralogia física, química, etc",
	        "Geoquímica / Hidrogeologia / Geofísica / Geotécnica (geoquímica dos solos, das rochas, água subterrânea, geofísica marinha, terrestre, sísmica, gravimetria, ensaio geotécnico)",
	        "moradia, função habitacional, mercado habitacional, política habitacional",
	        "Tipologia Habitacional (habitação unifamiliar, multifamiliar, funcional especial: para velhos, para estudantes, habitação provisória: alojamento, acampamento, tugurio, habitação móvel, espontânea, flutuante, etc)",
	        "água, ciclo hidrológico",
	        "bacia hidrográfica, representativa, área de inundação, curso de água, bacia lacustre, etc",
	        "fluviometria, pluviometria, evapometria, sedimentometria, estação hidrométrica, fluviométrica, etc",
	        "ou oceanologia, ou talassografia, oceano, mar, tipos de oceanografia: física, química, biológica, geológica, batimetria",
	        "política industrial, concentração industrial, produção industrial, pesquisa industrial, empresa industrial",
	        "política tecnológica, cooperação técnica, pesquisa tecnológica, inovação tecnológica, tecnologia apropriada, química tecnológica",
	        "desenho técnico, engenharia metalúrgica, engenharia química, mec ânica, automotiva, aeronáutica, naval, de produção, de teste",
	        "Indústria Extrativa Mineral (política mineral, pesquisa mineral, engenharia e minas, mineração, extrativismo mineral, ou exploração mineral)",
	        "Indústria de Transformação (indústria manufatureira, produto, industrialização, processo industrial, gênero da ind ústria: metalúrgica, de material elétrico, eletrônico, química, mecânica, de componentes, de armamento, têxtil, etc, estabelecimento industrial, produto industrializado, alimentício, etc, materiais e equipamentos)",
	        "científica, tecnológica, bibliográfica, estratégica, dados, etc",
	        "análise da informação, processamento de informação armazenamento, recuperação, disseminação, intercâmbio, bibliofilia, bibliologia, bibliometria",
	        "fotocópia, microfotografia, microfilmagem, micrografia",
	        "informação, registrada, ou material de informação, documento científico, confidencial, primário, secundário, não convencional, obra de referência, multimeio, material legível por máquina",
	        "administração de biblioteca, processos técnicos",
	        "ou arquivística, administração de arquivos",
	        "sistema de informação, rede de informação, teoria da informação, fluxo de informação",
	        "biblioteca, centro de documentação, arquivo, centro referencial, museu, etc",
	        "usuário, estudo e perfil do usuário",
	        "processamento de dados",
	        "metamatemática, método matemático, processo matemático, teoria lógica",
	        "teoria dos conjuntos, teoria dos números, álgebra elementar, estrutura algébrica, tipos de álgebra",
	        "geometria plana, geometria sólida, geometria analítica, trigonometria, geometria descritiva, geometria diferencial, etc",
	        "topologia, análise real, análise numérica, análise complexa, vetorial, matricial, tensorial, funcional, transformação integral, equação",
	        "cálculo diferencial, integral, operacional, vetorial, matricial, tensorial, numérico, variacional",
	        "modelo matemático. Especificar a aplicação: estatística, gráfico, cálculo de probabilidade, análise estatística, pesquisa operacional, matemática financeira atuarial",
	        "artificial, natural, política do meio ambiente",
	        "natureza: conservação, recursos naturais renováveis, não renováveis, área protegida",
	        "tipos de poluição: atmosférica, bacteriológica, física, do solo, água, química, radioativa, sonora, etc, controle prevenção, nível de poluição, poluente",
	        "qualidade da vida, da água, do ar, monitoramento ambiental, engenharia ambiental, de defesa civil",
	        "física, dinâmica, aplicada",
	        "ar, atmosfera inferior, superior, circulação e pressão atmosférica, previsão e estação metereológica, vento, tempestade, massa de ar, temperatura do ar, radiação solar, umidade do ar",
	        "clima, aclimatação, agroclimatologia, estação climatológica, tipos de clima",
	        "= ciência do solo, terra, solo: mineral ou orgânico",
	        "fatores de formação do solo, processo pedogenético, perfil do solo: morfopedologia, física do solo: morfopedologia, física do solo, química do solo mineralogia do solo, biologia do solo, horizonte",
	        "tipos do solo",
	        "teoria política, metodologia política",
	        "sistema político, estrutura política, Estado (nação) soberania, formas de estado, governo, regime político, poder público, organização do poder, ação política, política de governo, doutrina política",
	        "seguridade social, política de previdência social, previdência social, previdência privada",
	        "aposentadoria, auxílio ou assistência previdenciária, pecúlio, abono, etc",
	        "médica, odontológica, alimentar, reeducativa, assistência habitacional, organizações de assistência social, serviço social",
	        "= ciência do comportamento, psicologia do desenvolvimento, psicologia social, aplicada - clínica, psicoterapia, educacional, processos: sensorial, inteligência, congnitivo, reluxo",
	        "= conduta ou comportamento humano, motivação",
	        "sistema e teoria de psicologia, parapsicologia (associacionismo), behaviorismo, psicologia existencialista, do reforço, etc",
	        "engenharia sanitária, saneamento básico",
	        "detrito, dejeto ou efluente, lixo, resíduo: gasoso, líquido, orgânico, químico, térmico, tóxico",
	        "limpeza pública, drenagem urbana (limpeza urbana, de logradouro, coleta de lixo, destinação do lixo, etc, rede de drenagem urbana)",
	        "= sistema de abastecimento de água, serviços de água, captação de água, adução de água, tratamento de água, reservatório de água, distribuição de água, medição de água",
	        "serviço de esgoto, esgoto sanitário, tratamento: preliminar, primário, secundário, terciário, remoção de sólidos, lodo, emissário, etc, esgoto industrial",
	        "política de saúde, higiene, saúde física, mental, pública",
	        "= administração de saúde, serviços básicos de saúde, serviços de saúde: hospital, centro de saúde, posto de saúde, de socorro, etc, sistema de saúde, levantamento sanitário, educação sanitária, campanha de saúde pública, equipamento médico",
	        "congênita, infecciosa, do sistema reprodutor, do sistema glandular, etc",
	        "física, mental, inválido",
	        "hospitalar, médico-domiciliar, ambulatorial, médico-sanitária",
	        "terapia, diagnóstico médico (terapêutica, fisioterapia, hemoterapia, dieta, etc, diagnóstico: laboratorial, radiológico, síndrome, sintoma",
	        "alopática, hemeopática, preventiva, tropical, nuclear, medicina do trabalho, legal, de urgência",
	        "Especialidades Médicas (cardiologia, endocrinologia, epidemiologia, ginecologia, oftalmologia, psiquiatria, patologia, dermatologia, radiologia, etc, medicina não-convencional: naturopática, caseira, acupuntura, do-in, etc)",
	        "Engenharia Biomédica, ciências paramédicas (bioengenharia, biotecnologia, enfermagem, optometria, fonoaudiologia)",
	        "assistência farmacêutica, toxicologia, farmacopeia, farmacognosia, medicamento",
	        "saúde oral, periodontias, prótese dentária, assistência odontológica",
	        "públicos especificar conforme o tipo: telefonia, telegrafia, etc: correio, serviços de energia elétrica, segurança pública, de água, de esgoto, etc - serviços privados: alojamento e alimentação, de reparo e manutenção, pessoais, de vigilância e guarda, etc, tarifa de serviços",
	        "social, privado, quanto ao objeto, pessoal, patrimonial, de responsabilidade, contrato de seguro, seguradora, resseguro, co-seguro, corretora de seguro",
	        "interno, exterior, ilícito, comercialização, corretagem ou serviços de corretagem, ensilagem, entrepostagem, intercâmbio comercial, especula ção, mercadoria, zona franca, porte livre, política comercial",
	        "política de turismo, turismo interno, externo, intercâmbio turístico, infraestrutura turística: agência de turismo, rede hoteleira",
	        "política de telecomunicações, modelo de telecomunicações",
	        "radiocomunicação, sistema de televisão, telefonia, telegrafia, sistema de radar, telemetria, transmissão de dados, comunicação por fio, teoria de telecomunicações",
	        "linha de comunicação, recepção, transmissão",
	        "serviços, redes estações e material de telecomunicações",
	        "intelectual, técnico, manual, mecanizado, rural, doméstico, eventual, em condomínio, mão-de-obra, teoria do trabalho: método de trabalho, controle do trabalho, organização do trabalho",
	        "desenvolvimento de recursos humanos, pessoal trabalhador = operário, classe trabalhadora: trabalhador rural, autônomo, não qualificado, etc",
	        "Mercado de Trabalho (política empregatícia, salarial, pleno emprego, desemprego, subemprego, força de trabalho, emprego cíclico, fiscalização do trabalho, racionalização do trabalho)",
	        "Condições de Trabalho (ergonomia ou engenharia humana, ambiente de trabalho)",
	        "Estrutura Ocupacional (ocupação, profissão liberal, sindicato, associação de empregos, conselho profissional, empresariado, emprego, cargo)",
	        "renovação, colônia de férias, etc",
	        "política de transporte, planejamento de transporte",
	        "doméstico, regional, interregional, rural, urbano, integrado, etc, infraestrutura transporte = rede de transporte, sistema viário, rede: aeroviário, dutoviário, hidroviária, corredor de transporte, via de transporte, terminal de transporte, equipamento de transporte, material de transporte",
	        "transporte de carga, de passageiro, linha de transporte, empresa de transporte",
	        "de tráfego, aeronáutica, ferroviária, rodoviária, naval, automotiva",
	        "Modalidades de Transporte (aéreo, terrestre, hidroviário, especial: dutoviário, vertical)",
	        "= arte urbana, organização do espaço urbano, projeto urbanístico, forma urbana, planejamento urbano, história do urbanismo",
	        "imóvel urbano, terreno urbano, parcelamento do solo, cadastro imobiliário, avaliação imobiliária, tributação urbana, renda imobiliária, especulação imobiliária",
	        "= zona urbana, sítio urbano, estrutura urbana, urbanização, uso do solo = apropriação do espaço, zoneamento urbano, renda imobil., especulação imobil.",
	        "Circulação Urbana (via de circulação, terminal de transporte, tráfego urbano, infra-estrutura urbana = equipamento urbano, serviços públicos urbanos, equipamento comunitário)",
	        "projeto de arquitetura, reconversão de uso, arquitetura: doméstica, industrial, de comércio, de administração, institucional, militar, tradicional, de interiores = decoração elemento formal, elemento funcional, elemento decorativo"};

	
	private String[] tooltipsTipoDeProgramaDefault = {"Aplicativos","Planejamento","Controle","Auditoria","Contabiliz",
			                                          "Automação","Automação de Escritório","Automação Comercial","Automação Bancária","Automação Industrial","Controle de Processos","Automação da Manufatura (Controle Numérico Computadorizado, Robótica, etc)","Eletrônica Automotiva (computador de bordo, sistema de injeção e/ou ignição eletrônica, etc)",
													  "Avaliação de Desempenho","Contabilização de Recursos",
													  "Comunicação de Dados","Emuladores de Terminais","Monitores de Teleprocessamento","Gerenc. Disposit. e Periféricos","Gerenciador de Rede de Comunicação de Dados","Rede Local",
													  "Comutação Telefônica e Telegráfica","Implementador de Funções Adicionais","Gerenciador Operação e Manutenção","Terminal de Operação e Manutenção de Central",
													  "Ferramentas de Suporte ao Desenvolv. de Sistemas","Gerador de Aplicações","Computer Aided Softw Engineering","Aplicativos Desenvolv. Sist. de acordo com determ. Metodologia","Bibliotecas de Rotinas ('Libraries')","Apoio à Programação","Suporte à Documentação","Conversor de Sistemas",
													  "Entretenimento","Jogos Animados ('arcade games')","Geradores de Desenhos","Simuladores Destinados ao Lazer",
													  "Ferramenta de Apoio","Processadores de Texto","Planilhas Eletrônicas","Geradores de Gráficos",
													  "Gerenciador de Informações","Gerenciador de Banco de Dados","Gerador de Telas","Gerador de Relatórios","Dicionário de Dados","Entrada e Validação da Dados","Organização, Tratamento, Manutenção de Arquivo","Recuperação de Dados",
													  "Inteligência Artificial","Sistemas Especialistas","Sistemas de Processamento de Linguagem Natural",
													  "Instrumentação","Instrumentação de Teste e Medição", "Instrumentação Biomédica", "Instrumentação Analítica",
													  "Linguagens","Compilador","Montador","Pré-compilador","Compilador cruzado","Pré-processador","Interpretador","Linguagem Procedural","Linguagem Não Procedural",
													  "Segurança e Proteção de Dados","Senha","Criptografia","Manutenção da Integridade dos Dados","Controle de Acessos",
													  "Simulação e Modelagem","Simulador Vôo/Carro/Submarino/...","Simuladores de Ambiente Operacional","CAE/CAD/CAM/CAL/CBT/...",
													  "Sistema Operacional","Interface de Entrada e Saída","Interface Básica de Disco","Interface de Comunicação","Gerenciador de Usuários","Administrador de Dispositivos","Controlador de Processos","Controlador de Redes","Processador de Comandos",
													  "Aplicações Técnico-Científicas","Pesquisa Operacional","Reconhecimento de Padrões","Processamento de Imagem",
													  "Teleinformática","Terminais","Transmissão de Dados","Comutação de Dados",
													  "Utilitários","Compressor de Dados","Conversor Meios de Armazenamento","Classificador / Intercalador","Controlador de Spool","Transferência de Arquivos"};
													  
	
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
