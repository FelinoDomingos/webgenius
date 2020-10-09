package tecShine.com.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import tecShine.com.Erros.Validar;
import tecShine.com.JDBC.BD_Criar_SQL;
import tecShine.com.JDBC.Controle_ID_SQL;
import tecShine.com.JDBC.EliminarLinha_SQL;
import tecShine.com.JDBC.Pesquisar_SQL;
import tecShine.com.JDBC.Salvar_SQL;
import tecShine.com.JDBC.Tabela_Actualizar_SQL;
import tecShine.com.JDBC.Tabelas_Criar_SQL;
import tecShine.com.dao.Escola_Integrantes;
import tecShine.com.dao.Login;
import tecShine.com.dao.Verificar_Login;
import tecShine.com.model.Agenda;
import tecShine.com.model.Aluno;
import tecShine.com.model.AlunoNotas;
import tecShine.com.model.Coordenador;
import tecShine.com.model.Curso;
import tecShine.com.model.Desciplina;
import tecShine.com.model.Documentos;
import tecShine.com.model.FaltaEEstagio;
import tecShine.com.model.Fase1;
import tecShine.com.model.Fase2;
import tecShine.com.model.Fase4;
import tecShine.com.model.Financa;
import tecShine.com.model.Funcionario;
import tecShine.com.model.LoginIntegrantes;
import tecShine.com.model.MatricuEConfirmacao;
import tecShine.com.model.Minhas_Financas;
import tecShine.com.model.Pagamento;
import tecShine.com.model.Pagar;
import tecShine.com.model.Professor;
import tecShine.com.model.Propina;
import tecShine.com.model.Propina_E_Multa;
import tecShine.com.model.Recuperando_Dados;
import tecShine.com.model.RecuperarSenha;
import tecShine.com.model.Relatorio;
import tecShine.com.model.Salario;
import tecShine.com.model.Turma;
import tecShine.com.model.Validacoes;
import tecShine.com.model.Venda;
import tecShine.com.model.WG.Escola;
import tecShine.com.model.WG.PCA_WG;
import tecShine.com.relatorios.ImprimirRelatorio;

@Controller

/**
 * 
 * @author Jose Euclides Pedro Pereira dos Santos Esta é classe Que vai
 *         controlat todas as requisições do projecto
 *
 */

public class TechShineController {

	// ************************
	// Site da WebGenius
	// ***********************

	private String senha = " ";
	private String pagina = null;
	private Login entrarNoSistema = new Login();
	private int estado = 0;
	private int estado2 = 0;
	private Salario salario;
	private Propina_E_Multa pM;
	private MatricuEConfirmacao mc;
	private Documentos doc;
	private Venda venda;
	private Propina propina;
	private Escola_Integrantes acesso = new Escola_Integrantes();
	private String request = null;
	private int contador = 0;
	private String mensagem;
	private Verificar_Login aceder = new Verificar_Login();

	private PCA_WG pca = new PCA_WG();
	private Aluno aluno = new Aluno();
	private Fase2 admin = new Fase2();
	private Funcionario prof = new Funcionario();
	private Funcionario coord = new Funcionario();
	private Funcionario secret = new Funcionario();
	private Funcionario tesou = new Funcionario();
	public String configurarNome;
	public String configurarEscola;

	private String usuario;
	public String bi;
	private Fase1 fase1;
	public String BD;
	public int id;
	private String nomeDoCurso;

	private ArrayList<String> documentos = new ArrayList<>();
	private ArrayList<String> materias = new ArrayList<>();
	private Turma turma;
	private Funcionario func = new Funcionario();
	private String quem_E_O_func;
	private Professor prof2;
	private Minhas_Financas alunoFinanca;
	private String cursoAluno;
	private String curso2;
	private String turmaAluno;
	private String disciplina;
	private String nivel;
	private ArrayList<String> conteudos2;
	private ArrayList<String> conteudos3 = new ArrayList<>();
	private String nomeCompleto;
	private String turnoAluno;
	private String retorno;

	@GetMapping({ "/login", "/" })
	public String login() {

		System.out.println("Estado: " + this.estado);
		if (estado == 0) {

			this.pagina = "TechShine/login";
			return this.pagina;
		} else {
			return this.pagina;
		}
		

	}

	@PostMapping("/login")
	public String login_post(Model model, LoginIntegrantes login, HttpServletRequest request) {

		String pagina;
		String senha2;
		String usuario;

		senha2 = login.getSenha();
		usuario = login.getUsuario();
		this.usuario = usuario;

		System.out.println("Usuario: " + usuario);
		System.out.println("senha: " + senha2);

		Pesquisar_SQL p = new Pesquisar_SQL();

		boolean podeEntrar = false;

		

		
		if((usuario.equalsIgnoreCase("1996"))&&
				(senha2.equalsIgnoreCase("1996"))) {
			
			
			    BD_Criar_SQL bd = new BD_Criar_SQL();
			    Salvar_SQL s = new Salvar_SQL();
			   // bd.criarBaseDeDados("wg");
			    
			    Tabelas_Criar_SQL c= new Tabelas_Criar_SQL();
			   // c.criarTabelaPCA_WG("wg", "PCA");
			     
			    
			    
			   // c.criarTabelaEscolas_WG();
			
			podeEntrar = true;
		}else {
			
			ArrayList<String> retorno = aceder.existeNaWG(senha2, usuario);
			
			if (retorno.size() == 0) {

			} else {

				if (retorno.size() > 3) {

					this.id = Integer.parseInt(retorno.get(0));
					this.bi = retorno.get(1);
					this.BD = retorno.get(2);
					this.turmaAluno = retorno.get(3);

					System.out.println("BI Admin: " + this.bi);
					System.out.println("id: " + this.id);
					System.out.println("BD : " + this.BD);
					System.out.println("Turma Aluno : " + turmaAluno);
				} else {

					this.id = Integer.parseInt(retorno.get(0));
					this.bi = retorno.get(1);
					this.BD = retorno.get(2);

					System.out.println("BI Admin: " + this.bi);
					System.out.println("id: " + this.id);
					System.out.println("BD : " + this.BD);

				}

				podeEntrar = true;

			}
			
			
		}
		

		if (podeEntrar) {

			Verificar_Login v = new Verificar_Login();

			this.senha = senha2;

			System.out.println("Senha Geral: " + senha);
			if (usuario.equalsIgnoreCase("PCA")||
					(usuario.endsWith("PCA"))) {

				estado = 1;

				String sistema_Cadastrado = p.pesquisarUmConteudo_Numa_Linha_String("wg", "escolas", "sisCadatrado",
						"bi", this.bi, 0);

				if (sistema_Cadastrado.equalsIgnoreCase("S")) {

					configurarNome = p.pesquisarUmConteudo_Numa_Linha_String(this.BD, "pca_" + this.BD, "nome", "bi",
							this.bi, 0);
                    this.nomeCompleto = configurarNome;
					
					if(configurarNome.contains(" ")) {
						
						configurarNome = v.usuarioAcesso(v.acessoAWG(configurarNome));
					}
					

					configurarEscola = p.pesquisarTudoEmString(this.BD, "pca_" + this.BD, "instituicao").get(0);

					int qalunos = p.pesquisarUmConteudo_Numa_Linha_Int(this.BD, "infoescola", "qalunos", "id", "", 1);
					int qfunc = p.pesquisarUmConteudo_Numa_Linha_Int(this.BD, "infoescola", "qfunc", "id", "", 1);
					int QCurso = p.pesquisarUmConteudo_Numa_Linha_Int(this.BD, "infoescola", "QCurso", "id", "", 1);
					int renda = p.pesquisarUmConteudo_Numa_Linha_Int(this.BD, "adminfinanca", "renda", "bi_admin",
							this.bi, 0);

					model.addAttribute("qalunos", qalunos);
					model.addAttribute("qfunc", qfunc);
					model.addAttribute("qCurso", QCurso);
					model.addAttribute("renda", renda + ",00 Kz");

					System.out.println("Nome: " + configurarNome);
					System.out.println("Escola: " + configurarEscola);

					model.addAttribute("nome", configurarNome);
					model.addAttribute("escola", configurarEscola);

					this.quem_E_O_func = "pca";
					return "TechShine/Admin/inicio";

				} else {

					
					this.quem_E_O_func="pca";
					System.out.println("this.quem_E_O_func: "+this.quem_E_O_func);
					pagina = entrarNoSistema.quemEstaAFazerLogin(senha, usuario);
					this.pagina = pagina;

				}

				System.out.println("Entrou No Admin");
			} else if (usuario.endsWith("AL")) {

				pagina = "redirect:Aluno";
				this.pagina = pagina;
				estado = 1;

				configurarNome = p.pesquisarUmConteudo_Numa_Linha_String(this.BD, this.turmaAluno, "Alunos", "id", "",
						this.id);
				
				this.nomeCompleto = configurarNome;

				if(configurarNome.contains(" ")) {
					
					configurarNome = v.usuarioAcesso(v.acessoAWG(configurarNome));
				}

				configurarEscola = p.pesquisarTudoEmString(this.BD, "pca_" + this.BD, "instituicao").get(0);

				model.addAttribute("nome", configurarNome);
				model.addAttribute("escola", configurarEscola);

				// this.model.addAttribute("nome", this.Admin);
				this.quem_E_O_func = this.turmaAluno;
				System.out.println("Entrou No Aluno");

			} else if (usuario.contains("Coord")) {

				pagina = "redirect:coord";
				this.pagina = pagina;
				estado = 1;

				configurarNome = p.pesquisarUmConteudo_Numa_Linha_String(this.BD, "Coord_DadosPessoais", "nomes", "bi",
						this.bi, 0);
				
				this.nomeCompleto = configurarNome;
				if(configurarNome.contains(" ")) {
					
					configurarNome = v.usuarioAcesso(v.acessoAWG(configurarNome));
				}

				configurarEscola = p.pesquisarTudoEmString(this.BD, "pca_" + this.BD, "instituicao").get(0);

				model.addAttribute("nome", configurarNome);
				model.addAttribute("escola", configurarEscola);
				this.quem_E_O_func = "coord";

				System.out.println("Entrou No Coord");
			} else if (usuario.contains("Secretaria")) {

				pagina = "redirect:Secretaria";
				this.pagina = pagina;
				estado = 1;

				configurarNome = p.pesquisarUmConteudo_Numa_Linha_String(this.BD, "Secretaria_DadosPessoais", "nomes",
						"bi", this.bi, 0);
				
				this.nomeCompleto = configurarNome;
				if(configurarNome.contains(" ")) {
					
					configurarNome = v.usuarioAcesso(v.acessoAWG(configurarNome));
				}

				configurarEscola = p.pesquisarTudoEmString(this.BD, "pca_" + this.BD, "instituicao").get(0);

				model.addAttribute("nome", configurarNome);
				model.addAttribute("escola", configurarEscola);

				System.out.println("Entrou No Secretaria");
				this.quem_E_O_func = "secretaria";

			} else if (usuario.contains("Tesouraria")) {

				pagina = "redirect:Tesouraria";
				this.pagina = pagina;
				estado = 1;

				configurarNome = p.pesquisarUmConteudo_Numa_Linha_String(this.BD, "Tesouraria_DadosPessoais", "nomes",
						"bi", this.bi, 0);
				
				this.nomeCompleto = configurarNome;

				if(configurarNome.contains(" ")) {
					
					configurarNome = v.usuarioAcesso(v.acessoAWG(configurarNome));
				}

				configurarEscola = p.pesquisarTudoEmString(this.BD, "pca_" + this.BD, "instituicao").get(0);

				model.addAttribute("nome", configurarNome);
				model.addAttribute("escola", configurarEscola);

				this.quem_E_O_func = "tesouraria";
				System.out.println("Entrou No Tesouraria");
			} else if (usuario.contains("Professor")) {

				pagina = "redirect:Professor";
				this.pagina = pagina;
				estado = 1;

				configurarNome = p.pesquisarUmConteudo_Numa_Linha_String(this.BD, "Professor_DadosPessoais", "nomes",
						"bi", this.bi, 0);
				
				
				this.nomeCompleto = configurarNome;

				if(configurarNome.contains(" ")) {
					
					configurarNome = v.usuarioAcesso(v.acessoAWG(configurarNome));
				}

				configurarEscola = p.pesquisarTudoEmString(this.BD, "pca_" + this.BD, "instituicao").get(0);

				model.addAttribute("nome", configurarNome);
				model.addAttribute("escola", configurarEscola);

				this.quem_E_O_func = "professor";
				System.out.println("Entrou No Professor");
			} else if (usuario.contains("DP")) {

				pagina = entrarNoSistema.quemEstaAFazerLogin(senha, this.usuario);
				this.pagina = pagina;
				estado = 1;

				configurarNome = p.pesquisarUmConteudo_Numa_Linha_String(this.BD, "DP_DadosPessoais", "nomes", "bi",
						this.bi, 0);
				
				this.nomeCompleto = configurarNome;

				if(configurarNome.contains(" ")) {
					
					configurarNome = v.usuarioAcesso(v.acessoAWG(configurarNome));
				}

				configurarEscola = p.pesquisarTudoEmString(this.BD, "pca_" + this.BD, "instituicao").get(0);

				model.addAttribute("nome", configurarNome);
				model.addAttribute("escola", configurarEscola);

				this.quem_E_O_func = "DP";

				this.pagina = "redirect:admin";
				System.out.println("Entrou No DP");
			} else if (usuario.contains("DA")) {

				pagina = entrarNoSistema.quemEstaAFazerLogin(senha, this.usuario);
				this.pagina = pagina;
				estado = 1;

				configurarNome = p.pesquisarUmConteudo_Numa_Linha_String(this.BD, "DA_DadosPessoais", "nomes", "bi",
						this.bi, 0);
				
				this.nomeCompleto = configurarNome;

				if(configurarNome.contains(" ")) {
					
					configurarNome = v.usuarioAcesso(v.acessoAWG(configurarNome));
				}

				configurarEscola = p.pesquisarTudoEmString(this.BD, "pca_" + this.BD, "instituicao").get(0);

				model.addAttribute("nome", configurarNome);
				model.addAttribute("escola", configurarEscola);

				this.quem_E_O_func = "DA";

				this.pagina = "redirect:admin";
				System.out.println("Entrou No DA");
			} else if (usuario.contains("DG")) {

				// pagina= entrarNoSistema.quemEstaAFazerLogin(senha,this.usuario);

				estado = 1;

				configurarNome = p.pesquisarUmConteudo_Numa_Linha_String(this.BD, "DG_DadosPessoais", "nomes", "bi",
						this.bi, 0);
				
				this.nomeCompleto = configurarNome;

				if(configurarNome.contains(" ")) {
					
					configurarNome = v.usuarioAcesso(v.acessoAWG(configurarNome));
				}

				configurarEscola = p.pesquisarTudoEmString(this.BD, "pca_" + this.BD, "instituicao").get(0);

				model.addAttribute("nome", configurarNome);
				model.addAttribute("escola", configurarEscola);

				this.quem_E_O_func = "DG";
				System.out.println("Entrou No DG");
				this.pagina = "redirect:admin";
			} else if ((usuario.contains("clide")) || (usuario.contains("elino")) || (usuario.contains("ander"))) {

				System.out.println("Entrou No WebGenius");
				
				pagina="WebGnius/WG/inicio";
				this.pagina = pagina;

				estado = 1;

				configurarNome = p.pesquisarUmConteudo_Numa_Linha_String("wg", "pca", "usuario", "bi", this.bi, 0);

				if(configurarNome.contains(" ")) {
					
					configurarNome = v.usuarioAcesso(v.acessoAWG(configurarNome));
				}
				model.addAttribute("pca", configurarNome);
				model.addAttribute("escola", "WebGenius");

			}else if((usuario.equalsIgnoreCase("1996"))&&
					(senha2.equalsIgnoreCase("1996"))) {

				System.out.println("Entrou No WebGenius");
				pagina="WebGnius/WG/inicio";
				this.pagina = pagina;

				estado = 1;


				model.addAttribute("pca", "Euclides");
				model.addAttribute("escola", "WebGenius");

			}

		}  else {	
			

			pagina = "TechShine/login";
			this.pagina = pagina;
			senha = " ";
			request = null;
			
			if(p.mensagem2!=null) {
				
				this.mensagem = p.mensagem2;
			}else {
				
				this.mensagem = "* Coloque Seus Dados De Acesso Correctamente! ";
			}

			
			model.addAttribute("mensagem", this.mensagem);
			System.out.println("Entrou Em Nenhum Lado");
		}

		return this.pagina;
	}

	@GetMapping("/Recuperar-Dados")
	public String recuperar_Dados() {

		return "TechShine/recuperarDados";

	}

	@GetMapping("/Recuperando-Dados")
	public String Recuperando_Dados() {

		return "TechShine/recuperarDados";

	}

	@PostMapping("/Recuperando-Dados")
	public String Recuperando_Dados(Recuperando_Dados recuperar) {

		String pagina = null;

		if ((recuperar.getRecuperacao().equalsIgnoreCase("Euclides"))
				|| recuperar.getRecuperacao().equalsIgnoreCase("Felino")
				|| (recuperar.getRecuperacao().equalsIgnoreCase("Fander"))) {

			pagina = "WebGnius/cadastrar_Empresa/acesso";

		} else {
			pagina = "redirect:Recuperando-Dados";
		}

		return pagina;

	}

	/*
	 * ===================================== PRINCIPIO DOS MÉTODOS DA WEBGENIUS
	 * =====================================
	 */

	// Cadastrando Empresas na WebGenius

	@GetMapping({ "/Cadastrar-Sistema-1", "/Cadastrar-Sistema-2", "/Cadastrar-Sistema-3", "/Cadastrar-Sistema-4",
			"/Cadastrar-Sistema-5a", "/Cadastrar-Sistema-5b", "/Cadastrar-Sistema-6", "/Cadastrar-Sistema-7",
			"/Cadastrar-Sistema-8", "/Cadastrar-Sistema-9", "/Cadastrar-Sistema-9a", "/Cadastrar-Sistema-9b",
			"/Cadastrar-Sistema-9c", "/Cadastrar-Sistema-9d", "/Cadastrar-Sistema-9e", "/Cadastrar-Sistema-9f",
			"/Cadastrar-Sistema-10", "/WgContacts", "/Admin-Minha-Seguranca", "/Admin-Alterar-Salario",
			"/Admin-Alterar-Propina", "Admin-Alterar-MC", "/Admin-Alterar-Doc", "/Admin-Alterar-Vendas",
			"/Admin-Alterar-FE", "/Admin-Ver-PM", "/Admin-Ver-MC", "/Admin-Ver-Salario", "/Admin-Ver-Doc",
			"/Admin-Ver-Materias", "/Admin-Ver-FE", "/Admin-Cadastro", "/Cadastrar-Sistema-6-1" })

	public String CadasdrarEmpresa(HttpServletRequest request, Model model) {
		String nomeDaPagina = null;
		String requisicao;

		requisicao = request.getRequestURI();
		Salvar_SQL s = new Salvar_SQL();
		Pesquisar_SQL p = new Pesquisar_SQL();
		ArrayList<Curso> cursos = p.Listar_Cursos(this.BD);
		ArrayList<String> tCursos = new ArrayList<>();
		ArrayList<String> funcionarios = p.listarFuncionarios();

		for (Curso c : cursos) {

			tCursos.add(c.getNome());
		}
		ArrayList<String> niveis = p.pesquisarTudoEmString(this.BD, "infoescola", "niveis");
		if (requisicao.equalsIgnoreCase("/Cadastrar-Sistema-1")) {
			
			

			if (estado < 0) {

				nomeDaPagina = this.pagina;
				this.pagina = nomeDaPagina;
			} else {

				++estado;
				if (estado > 1) {
					estado = estado - (estado - 1);

				}
				if (entrarNoSistema.podeAbrirAPagina(senha)) {
					
					this.quem_E_O_func="pca";

					// if(acesso.bloqueandoRequisicoes(TechShine_Controller.request, request)) {

					System.out.println("Estado: " + estado);
					System.out.println("Padina Actual: " + this.pagina);
					if (estado == 1) {
						nomeDaPagina = "WebGnius/cadastrar_Empresa/sistema_fase1";
						this.pagina = nomeDaPagina;
						++estado;
					} else {

					}

					// }

				} else {

					estado = 0;
					nomeDaPagina = "redirect:login";
					this.pagina = nomeDaPagina;
				}
			}

		} else if (requisicao.equalsIgnoreCase("/Cadastrar-Sistema-2")) {

			if (estado < 0) {

				nomeDaPagina = this.pagina;
				this.pagina = nomeDaPagina;
			} else {

				System.out.println("");
				++estado;
				if (estado > 2) {
					estado = estado - (estado - 1);
					++estado;
				}
				if (entrarNoSistema.podeAbrirAPagina(senha)) {

					// if(acesso.bloqueandoRequisicoes(TechShine_Controller.request, request)) {

					if (estado == 2) {
						nomeDaPagina = "WebGnius/cadastrar_Empresa/sistema_fase2";
						this.pagina = nomeDaPagina;
						++estado;
					} else {

					}

					// }

				} else {
					estado = 0;
					nomeDaPagina = "redirect:login";
					this.pagina = nomeDaPagina;
				}

			}

		} else if (requisicao.equalsIgnoreCase("/Cadastrar-Sistema-3")) {

			if (estado < 0) {

				nomeDaPagina = this.pagina;
				this.pagina = nomeDaPagina;
			} else {

				System.out.println("Entro no 3");
				++estado;
				if (estado > 2) {
					estado = estado - (estado - 1);
					estado = estado + 2;
				}
				if (entrarNoSistema.podeAbrirAPagina(senha)) {

					// if(acesso.bloqueandoRequisicoes(TechShine_Controller.request, request)) {

					if (estado == 3) {

						model.addAttribute("nome", configurarNome);
						model.addAttribute("escola", configurarEscola);

						this.quem_E_O_func="pca";
						nomeDaPagina = "WebGnius/cadastrar_Empresa/sistema_fase3";
						this.pagina = nomeDaPagina;
						++estado;
					}
					// }

				} else {

					estado = 0;
					nomeDaPagina = "redirect:login";
					this.pagina = nomeDaPagina;
				}

			}

		} else if (requisicao.equalsIgnoreCase("/Cadastrar-Sistema-5a")) {
			estado = -5;
			if (entrarNoSistema.podeAbrirAPagina(senha)) {

				// if(acesso.bloqueandoRequisicoes(TechShine_Controller.request, request)) {

				int qalunos = p.pesquisarUmConteudo_Numa_Linha_Int(this.BD, "infoescola", "qalunos", "id", "", 1);
				int qfunc = p.pesquisarUmConteudo_Numa_Linha_Int(this.BD, "infoescola", "qfunc", "id", "", 1);
				int QCurso = p.pesquisarUmConteudo_Numa_Linha_Int(this.BD, "infoescola", "QCurso", "id", "", 1);
				int renda;
				int despeza = 0;
				String mes = s.mesActual(this.BD);

				if (this.quem_E_O_func.equalsIgnoreCase("pca")) {
					renda = p.pesquisarUmConteudo_Numa_Linha_Int(this.BD, "adminfinanca", "renda", "bi_admin", this.bi,
							0);

					ArrayList<Integer> despezas = p.pesquisarTudoEmInt(this.BD, "adminfinanca", "despeza");

					if (despezas.size() > 0) {

						despeza = p.pesquisarTudoEmInt(this.BD, "adminfinanca", "despeza").get(0);
					}
					model.addAttribute("renda", renda + ",00 Kz");

				} else {

					renda = p.pesquisarUmConteudo_Numa_Linha_Int(this.BD, this.quem_E_O_func + "_financa", mes, "id",
							"", this.id);

					if (renda == 0) {
						model.addAttribute("renda", "Irregular");
					} else {
						model.addAttribute("renda", "Regular");
					}

				}

				model.addAttribute("qalunos", qalunos);
				model.addAttribute("qfunc", qfunc);
				model.addAttribute("qCurso", QCurso);

				model.addAttribute("despeza", despeza);

				model.addAttribute("nome", configurarNome);
				model.addAttribute("escola", configurarEscola);

				nomeDaPagina = "WebGnius/cadastrar_Empresa/sistema_fase5a";
				this.pagina = nomeDaPagina;
				// }

			} else {
				estado = 0;
				nomeDaPagina = "redirect:login";
				this.pagina = nomeDaPagina;
			}

		} else if (requisicao.equalsIgnoreCase("/Cadastrar-Sistema-5b")) {
			estado = -5;
			if (entrarNoSistema.podeAbrirAPagina(senha)) {

				// if(acesso.bloqueandoRequisicoes(TechShine_Controller.request, request)) {

				int qalunos = p.pesquisarUmConteudo_Numa_Linha_Int(this.BD, "infoescola", "qalunos", "id", "", 1);
				int qfunc = p.pesquisarUmConteudo_Numa_Linha_Int(this.BD, "infoescola", "qfunc", "id", "", 1);
				int QCurso = p.pesquisarUmConteudo_Numa_Linha_Int(this.BD, "infoescola", "QCurso", "id", "", 1);

				int renda;
				int despeza = 0;
				String mes = s.mesActual(this.BD);

				if (this.quem_E_O_func.equalsIgnoreCase("pca")) {
					renda = p.pesquisarUmConteudo_Numa_Linha_Int(this.BD, "adminfinanca", "renda", "bi_admin", this.bi,
							0);

					ArrayList<Integer> despezas = p.pesquisarTudoEmInt(this.BD, "adminfinanca", "despeza");

					if (despezas.size() > 0) {

						despeza = p.pesquisarTudoEmInt(this.BD, "adminfinanca", "despeza").get(0);
					}

					model.addAttribute("renda", renda + ",00 Kz");
				} else {

					renda = p.pesquisarUmConteudo_Numa_Linha_Int(this.BD, this.quem_E_O_func + "_financa", mes, "id",
							"", this.id);

					if (renda == 0) {
						model.addAttribute("renda", "Irregular");
					} else {
						model.addAttribute("renda", "Regular");
					}

				}

				model.addAttribute("qalunos", qalunos);
				model.addAttribute("qfunc", qfunc);
				model.addAttribute("qCurso", QCurso);

				model.addAttribute("despeza", despeza);

				model.addAttribute("niveis", niveis);
				model.addAttribute("cursos", tCursos);

				if ((this.quem_E_O_func.equalsIgnoreCase("DA")) || (this.quem_E_O_func.equalsIgnoreCase("DP"))) {
					funcionarios.remove("DG");
					funcionarios.remove("DA");
					funcionarios.remove("DP");

				} else if (this.quem_E_O_func.equalsIgnoreCase("DG")) {
					funcionarios.remove("DG");

				}

				model.addAttribute("funcionarios", funcionarios);
				model.addAttribute("nome", configurarNome);
				model.addAttribute("escola", configurarEscola);

				nomeDaPagina = "WebGnius/cadastrar_Empresa/sistema_fase5b";
				this.pagina = nomeDaPagina;

				// }

			} else {
				estado = 0;
				nomeDaPagina = "redirect:login";
				this.pagina = nomeDaPagina;
			}

		} else if (requisicao.equalsIgnoreCase("/Cadastrar-Sistema-6")) {

			estado = -5;
			if (entrarNoSistema.podeAbrirAPagina(senha)) {

				// if(acesso.bloqueandoRequisicoes(TechShine_Controller.request, request)) {

				int qalunos = p.pesquisarUmConteudo_Numa_Linha_Int(this.BD, "infoescola", "qalunos", "id", "", 1);
				int qfunc = p.pesquisarUmConteudo_Numa_Linha_Int(this.BD, "infoescola", "qfunc", "id", "", 1);
				int QCurso = p.pesquisarUmConteudo_Numa_Linha_Int(this.BD, "infoescola", "QCurso", "id", "", 1);

				int renda;
				int despeza = 0;
				String mes = s.mesActual(this.BD);

				if (this.quem_E_O_func.equalsIgnoreCase("pca")) {
					renda = p.pesquisarUmConteudo_Numa_Linha_Int(this.BD, "adminfinanca", "renda", "bi_admin", this.bi,
							0);

					ArrayList<Integer> despezas = p.pesquisarTudoEmInt(this.BD, "adminfinanca", "despeza");

					if (despezas.size() > 0) {

						despeza = p.pesquisarTudoEmInt(this.BD, "adminfinanca", "despeza").get(0);
					}

					model.addAttribute("renda", renda + ",00 Kz");
				} else {

					renda = p.pesquisarUmConteudo_Numa_Linha_Int(this.BD, this.quem_E_O_func + "_financa", mes, "id",
							"", this.id);

					if (renda == 0) {
						model.addAttribute("renda", "Irregular");
					} else {
						model.addAttribute("renda", "Regular");
					}

				}
				
				ArrayList<String> cursosTt = p.pesquisarTudoEmString(this.BD, "cursos", "nome");

				model.addAttribute("qalunos", qalunos);
				model.addAttribute("qfunc", qfunc);
				model.addAttribute("qCurso", QCurso);
				model.addAttribute("despeza", despeza);

				model.addAttribute("niveis", niveis);
				model.addAttribute("cursos", cursosTt);
				model.addAttribute("nome", configurarNome);
				model.addAttribute("escola", configurarEscola);

				nomeDaPagina = "WebGnius/cadastrar_Empresa/sistema_fase6";
				this.pagina = nomeDaPagina;
				// }

			} else {

				estado = 0;
				nomeDaPagina = "redirect:login";
				this.pagina = nomeDaPagina;
			}
		} else if (requisicao.equalsIgnoreCase("/Cadastrar-Sistema-6-1")) {

			estado = -5;
			if (entrarNoSistema.podeAbrirAPagina(senha)) {

				// if(acesso.bloqueandoRequisicoes(TechShine_Controller.request, request)) {

				int qalunos = p.pesquisarUmConteudo_Numa_Linha_Int(this.BD, "infoescola", "qalunos", "id", "", 1);
				int qfunc = p.pesquisarUmConteudo_Numa_Linha_Int(this.BD, "infoescola", "qfunc", "id", "", 1);
				int QCurso = p.pesquisarUmConteudo_Numa_Linha_Int(this.BD, "infoescola", "QCurso", "id", "", 1);

				int renda;
				int despeza = 0;
				String mes = s.mesActual(this.BD);

				if (this.quem_E_O_func.equalsIgnoreCase("pca")) {
					renda = p.pesquisarUmConteudo_Numa_Linha_Int(this.BD, "adminfinanca", "renda", "bi_admin", this.bi,
							0);

					ArrayList<Integer> despezas = p.pesquisarTudoEmInt(this.BD, "adminfinanca", "despeza");

					if (despezas.size() > 0) {

						despeza = p.pesquisarTudoEmInt(this.BD, "adminfinanca", "despeza").get(0);
					}
					model.addAttribute("renda", renda + ",00 Kz");

				} else {

					renda = p.pesquisarUmConteudo_Numa_Linha_Int(this.BD, this.quem_E_O_func + "_financa", mes, "id",
							"", this.id);

					if (renda == 0) {
						model.addAttribute("renda", "Irregular");
					} else {
						model.addAttribute("renda", "Regular");
					}

				}

				model.addAttribute("qalunos", qalunos);
				model.addAttribute("qfunc", qfunc);
				model.addAttribute("qCurso", QCurso);
				model.addAttribute("despeza", despeza);

				model.addAttribute("niveis", niveis);
				model.addAttribute("curso", this.nomeDoCurso);
				model.addAttribute("nome", configurarNome);
				model.addAttribute("escola", configurarEscola);

				nomeDaPagina = "WebGnius/cadastrar_Empresa/sistema_fase6.1";
				this.pagina = nomeDaPagina;
				// }

			} else {

				estado = 0;
				nomeDaPagina = "redirect:login";
				this.pagina = nomeDaPagina;
			}
		}

		else if (requisicao.equalsIgnoreCase("/Cadastrar-Sistema-7")) {

			estado = -5;
			if (entrarNoSistema.podeAbrirAPagina(senha)) {

				// if(acesso.bloqueandoRequisicoes(TechShine_Controller.request, request)) {

				int qalunos = p.pesquisarUmConteudo_Numa_Linha_Int(this.BD, "infoescola", "qalunos", "id", "", 1);
				int qfunc = p.pesquisarUmConteudo_Numa_Linha_Int(this.BD, "infoescola", "qfunc", "id", "", 1);
				int QCurso = p.pesquisarUmConteudo_Numa_Linha_Int(this.BD, "infoescola", "QCurso", "id", "", 1);

				int renda;
				int despeza = 0;
				String mes = s.mesActual(this.BD);

				if (this.quem_E_O_func.equalsIgnoreCase("pca")) {
					renda = p.pesquisarUmConteudo_Numa_Linha_Int(this.BD, "adminfinanca", "renda", "bi_admin", this.bi,
							0);

					ArrayList<Integer> despezas = p.pesquisarTudoEmInt(this.BD, "adminfinanca", "despeza");

					if (despezas.size() > 0) {

						despeza = p.pesquisarTudoEmInt(this.BD, "adminfinanca", "despeza").get(0);
					}

					model.addAttribute("renda", renda + ",00 Kz");
				} else {

					renda = p.pesquisarUmConteudo_Numa_Linha_Int(this.BD, this.quem_E_O_func + "_financa", mes, "id",
							"", this.id);

					if (renda == 0) {
						model.addAttribute("renda", "Irregular");
					} else {
						model.addAttribute("renda", "Regular");
					}

				}

				model.addAttribute("qalunos", qalunos);
				model.addAttribute("qfunc", qfunc);
				model.addAttribute("qCurso", QCurso);
				model.addAttribute("despeza", despeza);

				model.addAttribute("niveis", niveis);
				model.addAttribute("cursos", tCursos);
				model.addAttribute("nome", configurarNome);
				model.addAttribute("escola", configurarEscola);

				nomeDaPagina = "WebGnius/cadastrar_Empresa/sistema_fase7";
				this.pagina = nomeDaPagina;
				// }

			} else {

				estado = 0;
				nomeDaPagina = "redirect:login";
				this.pagina = nomeDaPagina;
			}
		}

		else if (requisicao.equalsIgnoreCase("/WgContacts")) {

			estado = -5;
			if (entrarNoSistema.podeAbrirAPagina(senha)) {

				// if(acesso.bloqueandoRequisicoes(TechShine_Controller.request, request)) {

				
				int qalunos = p.pesquisarUmConteudo_Numa_Linha_Int(this.BD, "infoescola", "qalunos", "id", "", 1);
				int qfunc = p.pesquisarUmConteudo_Numa_Linha_Int(this.BD, "infoescola", "qfunc", "id", "", 1);
				int QCurso = p.pesquisarUmConteudo_Numa_Linha_Int(this.BD, "infoescola", "QCurso", "id", "", 1);

				
				int renda;
				String mes = s.mesActual(this.BD);

				if (this.quem_E_O_func.equalsIgnoreCase("pca")) {
					renda = p.pesquisarUmConteudo_Numa_Linha_Int(this.BD, "adminfinanca", "renda", "bi_admin", this.bi, 0);

					model.addAttribute("renda", renda + ",00 Kz");
				} else {

					renda = p.pesquisarUmConteudo_Numa_Linha_Int(this.BD, this.quem_E_O_func + "_financa", mes, "id", "",
							this.id);

					if (renda == 0) {
						model.addAttribute("renda", "Irregular");
					} else {
						model.addAttribute("renda", "Regular");
					}

				}

				model.addAttribute("qalunos", qalunos);
				model.addAttribute("qfunc", qfunc);
				model.addAttribute("qCurso", QCurso);

				
				model.addAttribute("nome", configurarNome);
				model.addAttribute("escola", configurarEscola);
			
				  
				
				nomeDaPagina = "WebGnius/WG/contactos";
				this.pagina = nomeDaPagina;
				// }

			} else {

				estado = 0;
				nomeDaPagina = "redirect:login";
				this.pagina = nomeDaPagina;
			}

		} else if (requisicao.equalsIgnoreCase("/Cadastrar-Sistema-9")) {

			estado = -5;
			if (entrarNoSistema.podeAbrirAPagina(senha)) {

				// if(acesso.bloqueandoRequisicoes(TechShine_Controller.request, request)) {

				estado2 = 0;
				ArrayList<Integer> financaCadastrada = p.pesquisarTudoEmInt(this.BD, "adminfinanca", "finCadastrada");
				if (financaCadastrada.size() > 0) {
					estado2 = p.pesquisarTudoEmInt(this.BD, "adminfinanca", "finCadastrada").get(0);
				}

				if (estado2 == 0) {

					for (Curso c : cursos) {

						tCursos.add(c.getNome());
					}

					int qalunos = p.pesquisarUmConteudo_Numa_Linha_Int(this.BD, "infoescola", "qalunos", "id", "", 1);
					int qfunc = p.pesquisarUmConteudo_Numa_Linha_Int(this.BD, "infoescola", "qfunc", "id", "", 1);
					int QCurso = p.pesquisarUmConteudo_Numa_Linha_Int(this.BD, "infoescola", "QCurso", "id", "", 1);
					int renda = p.pesquisarUmConteudo_Numa_Linha_Int(this.BD, "adminfinanca", "renda", "bi_admin",
							this.bi, 0);

					int despeza = 0;
					ArrayList<Integer> despezas = p.pesquisarTudoEmInt(this.BD, "adminfinanca", "despeza");

					if (despezas.size() > 0) {

					}

					model.addAttribute("qalunos", qalunos);
					model.addAttribute("qfunc", qfunc);
					model.addAttribute("qCurso", QCurso);
					model.addAttribute("renda", renda);
					model.addAttribute("despeza", despeza);

					model.addAttribute("cursos", tCursos);
					model.addAttribute("resultado", "Curso Inserido Com Sucesso !");
					model.addAttribute("nome", configurarNome);
					model.addAttribute("escola", configurarEscola);

					nomeDaPagina = "WebGnius/cadastrar_Empresa/sistema_fase9";
				} else {

					for (Curso c : cursos) {

						tCursos.add(c.getNome());
					}

					int qalunos = p.pesquisarUmConteudo_Numa_Linha_Int(this.BD, "infoescola", "qalunos", "id", "", 1);
					int qfunc = p.pesquisarUmConteudo_Numa_Linha_Int(this.BD, "infoescola", "qfunc", "id", "", 1);
					int QCurso = p.pesquisarUmConteudo_Numa_Linha_Int(this.BD, "infoescola", "QCurso", "id", "", 1);

					int renda;
					int despeza = 0;
					String mes = s.mesActual(this.BD);

					if (this.quem_E_O_func.equalsIgnoreCase("pca")) {
						renda = p.pesquisarUmConteudo_Numa_Linha_Int(this.BD, "adminfinanca", "renda", "bi_admin",
								this.bi, 0);

						ArrayList<Integer> despezas = p.pesquisarTudoEmInt(this.BD, "adminfinanca", "despeza");

						if (despezas.size() > 0) {

							despeza = p.pesquisarTudoEmInt(this.BD, "adminfinanca", "despeza").get(0);
						}
						nomeDaPagina = "WebGnius/cadastrar_Empresa/sistema_fase10";

					} else {

						renda = p.pesquisarUmConteudo_Numa_Linha_Int(this.BD, this.quem_E_O_func + "_financa", mes,
								"id", "", this.id);

						if (renda == 0) {
							model.addAttribute("renda", "Irregular");
						} else {
							model.addAttribute("renda", "Regular");
						}

						nomeDaPagina = "WebGnius/cadastrar_Empresa/sistema_fase11";
					}

					model.addAttribute("qalunos", qalunos);
					model.addAttribute("qfunc", qfunc);
					model.addAttribute("qCurso", QCurso);
					model.addAttribute("renda", renda);
					model.addAttribute("despeza", despeza);

					model.addAttribute("cursos", tCursos);
					model.addAttribute("resultado", "Curso Inserido Com Sucesso !");
					model.addAttribute("nome", configurarNome);
					model.addAttribute("escola", configurarEscola);

					// nomeDaPagina="WebGnius/cadastrar_Empresa/tudo_Cadastrado";

				}

				this.pagina = nomeDaPagina;
				// }

			} else {

				estado = 0;
				nomeDaPagina = "redirect:login";
				this.pagina = nomeDaPagina;
			}

		} else if (requisicao.equalsIgnoreCase("/Cadastrar-Sistema-9a")) {

			estado = -5;
			if (entrarNoSistema.podeAbrirAPagina(senha)) {

				// if(acesso.bloqueandoRequisicoes(TechShine_Controller.request, request)) {

				nomeDaPagina = "WebGnius/cadastrar_Empresa/sistema_fase9a";
				this.pagina = nomeDaPagina;
				System.out.println("Entrou Aqui");
				// }

			} else {

				estado = 0;
				nomeDaPagina = "redirect:login";
				this.pagina = nomeDaPagina;
			}
		} else if (requisicao.equalsIgnoreCase("/Cadastrar-Sistema-9b")) {

			estado = -5;
			if (entrarNoSistema.podeAbrirAPagina(senha)) {

				// if(acesso.bloqueandoRequisicoes(TechShine_Controller.request, request)) {

				for (Curso c : cursos) {

					tCursos.add(c.getNome());
				}

				int qalunos = p.pesquisarUmConteudo_Numa_Linha_Int(this.BD, "infoescola", "qalunos", "id", "", 1);
				int qfunc = p.pesquisarUmConteudo_Numa_Linha_Int(this.BD, "infoescola", "qfunc", "id", "", 1);
				int QCurso = p.pesquisarUmConteudo_Numa_Linha_Int(this.BD, "infoescola", "QCurso", "id", "", 1);
				int renda = p.pesquisarUmConteudo_Numa_Linha_Int(this.BD, "adminfinanca", "renda", "bi_admin", this.bi,
						0);

				model.addAttribute("qalunos", qalunos);
				model.addAttribute("qfunc", qfunc);
				model.addAttribute("qCurso", QCurso);
				model.addAttribute("renda", renda);

				ArrayList<String> tCursos2= new ArrayList<>();

				for(int i=0;i<tCursos.size();i++) {

					tCursos2.add(tCursos.get(i));
				}

				model.addAttribute("cursos", tCursos2);
				model.addAttribute("resultado", "Curso Inserido Com Sucesso !");
				model.addAttribute("nome", configurarNome);
				model.addAttribute("escola", configurarEscola);
				nomeDaPagina = "WebGnius/cadastrar_Empresa/sistema_fase9b";
				this.pagina = nomeDaPagina;
				// }

			} else {

				estado = 0;
				nomeDaPagina = "redirect:login";
				this.pagina = nomeDaPagina;
			}

		} else if (requisicao.equalsIgnoreCase("/Cadastrar-Sistema-9c")) {

			estado = -5;
			if (entrarNoSistema.podeAbrirAPagina(senha)) {

				// if(acesso.bloqueandoRequisicoes(TechShine_Controller.request, request)) {

				for (Curso c : cursos) {

					tCursos.add(c.getNome());
				}

				int qalunos = p.pesquisarUmConteudo_Numa_Linha_Int(this.BD, "infoescola", "qalunos", "id", "", 1);
				int qfunc = p.pesquisarUmConteudo_Numa_Linha_Int(this.BD, "infoescola", "qfunc", "id", "", 1);
				int QCurso = p.pesquisarUmConteudo_Numa_Linha_Int(this.BD, "infoescola", "QCurso", "id", "", 1);
				int renda = p.pesquisarUmConteudo_Numa_Linha_Int(this.BD, "adminfinanca", "renda", "bi_admin", this.bi,
						0);

				model.addAttribute("qalunos", qalunos);
				model.addAttribute("qfunc", qfunc);
				model.addAttribute("qCurso", QCurso);
				model.addAttribute("renda", renda);
				
				ArrayList<String> tCursos2= new ArrayList<>();
				
				for(int i=0;i<tCursos.size();i++) {
					
					tCursos2.add(tCursos.get(i));
				}

				model.addAttribute("cursos", tCursos2);
				model.addAttribute("resultado", "Curso Inserido Com Sucesso !");
				model.addAttribute("nome", configurarNome);
				model.addAttribute("escola", configurarEscola);

				nomeDaPagina = "WebGnius/cadastrar_Empresa/sistema_fase9c";
				this.pagina = nomeDaPagina;
				// }

			} else {

				estado = 0;
				nomeDaPagina = "redirect:login";
				this.pagina = nomeDaPagina;
			}

		} else if (requisicao.equalsIgnoreCase("/Cadastrar-Sistema-9d")) {

			estado = -5;
			if (entrarNoSistema.podeAbrirAPagina(senha)) {

				// if(acesso.bloqueandoRequisicoes(TechShine_Controller.request, request)) {

				for (Curso c : cursos) {

					tCursos.add(c.getNome());
				}

				int qalunos = p.pesquisarUmConteudo_Numa_Linha_Int(this.BD, "infoescola", "qalunos", "id", "", 1);
				int qfunc = p.pesquisarUmConteudo_Numa_Linha_Int(this.BD, "infoescola", "qfunc", "id", "", 1);
				int QCurso = p.pesquisarUmConteudo_Numa_Linha_Int(this.BD, "infoescola", "QCurso", "id", "", 1);
				int renda = p.pesquisarUmConteudo_Numa_Linha_Int(this.BD, "adminfinanca", "renda", "bi_admin", this.bi,
						0);

				ArrayList<String> docs = p.pesquisarTudoEmString(this.BD, "infoescola", "Documentos");
				model.addAttribute("qalunos", qalunos);
				model.addAttribute("qfunc", qfunc);
				model.addAttribute("qCurso", QCurso);
				model.addAttribute("renda", renda);

				model.addAttribute("documentos", docs);

				model.addAttribute("resultado", "Curso Inserido Com Sucesso !");
				model.addAttribute("nome", configurarNome);
				model.addAttribute("escola", configurarEscola);
				nomeDaPagina = "WebGnius/cadastrar_Empresa/sistema_fase9d";
				this.pagina = nomeDaPagina;
				// }

			} else {

				estado = 0;
				nomeDaPagina = "redirect:login";
				this.pagina = nomeDaPagina;
			}

		} else if (requisicao.equalsIgnoreCase("/Cadastrar-Sistema-9e")) {

			estado = -5;
			if (entrarNoSistema.podeAbrirAPagina(senha)) {

				// if(acesso.bloqueandoRequisicoes(TechShine_Controller.request, request)) {

				for (Curso c : cursos) {

					tCursos.add(c.getNome());
				}

				int qalunos = p.pesquisarUmConteudo_Numa_Linha_Int(this.BD, "infoescola", "qalunos", "id", "", 1);
				int qfunc = p.pesquisarUmConteudo_Numa_Linha_Int(this.BD, "infoescola", "qfunc", "id", "", 1);
				int QCurso = p.pesquisarUmConteudo_Numa_Linha_Int(this.BD, "infoescola", "QCurso", "id", "", 1);
				int renda = p.pesquisarUmConteudo_Numa_Linha_Int(this.BD, "adminfinanca", "renda", "bi_admin", this.bi,
						0);

				ArrayList<String> materiais = p.pesquisarTudoEmString(this.BD, "infoescola", "Materias");

				model.addAttribute("qalunos", qalunos);
				model.addAttribute("qfunc", qfunc);
				model.addAttribute("qCurso", QCurso);
				model.addAttribute("renda", renda);
				model.addAttribute("materiais", materiais);

				model.addAttribute("cursos", tCursos);
				model.addAttribute("resultado", "Curso Inserido Com Sucesso !");
				model.addAttribute("nome", configurarNome);
				model.addAttribute("escola", configurarEscola);

				nomeDaPagina = "WebGnius/cadastrar_Empresa/sistema_fase9e";
				this.pagina = nomeDaPagina;
				// }

			} else {

				estado = 0;
				nomeDaPagina = "redirect:login";
				this.pagina = nomeDaPagina;
			}

		} else if (requisicao.equalsIgnoreCase("/Cadastrar-Sistema-9f")) {

			estado = -5;
			if (entrarNoSistema.podeAbrirAPagina(senha)) {

				// if(acesso.bloqueandoRequisicoes(TechShine_Controller.request, request)) {

				for (Curso c : cursos) {

					tCursos.add(c.getNome());
				}

				int qalunos = p.pesquisarUmConteudo_Numa_Linha_Int(this.BD, "infoescola", "qalunos", "id", "", 1);
				int qfunc = p.pesquisarUmConteudo_Numa_Linha_Int(this.BD, "infoescola", "qfunc", "id", "", 1);
				int QCurso = p.pesquisarUmConteudo_Numa_Linha_Int(this.BD, "infoescola", "QCurso", "id", "", 1);
				int renda = p.pesquisarUmConteudo_Numa_Linha_Int(this.BD, "adminfinanca", "renda", "bi_admin", this.bi,
						0);

				ArrayList<String> faltas = new ArrayList<>();
				faltas.add("Vermelha");
				faltas.add("Azul");

				model.addAttribute("qalunos", qalunos);
				model.addAttribute("qfunc", qfunc);
				model.addAttribute("qCurso", QCurso);
				model.addAttribute("renda", renda);
				model.addAttribute("faltas", faltas);

				model.addAttribute("cursos", tCursos);
				model.addAttribute("resultado", "Curso Inserido Com Sucesso !");
				model.addAttribute("nome", configurarNome);
				model.addAttribute("escola", configurarEscola);

				nomeDaPagina = "WebGnius/cadastrar_Empresa/sistema_fase9f";
				this.pagina = nomeDaPagina;
				// }

			} else {

				estado = 0;
				nomeDaPagina = "redirect:login";
				this.pagina = nomeDaPagina;
			}

		} else if (requisicao.equalsIgnoreCase("/Cadastrar-Sistema-10")) {

			estado = -5;
			if (entrarNoSistema.podeAbrirAPagina(senha)) {

				// if(acesso.bloqueandoRequisicoes(TechShine_Controller.request, request)) {

				for (Curso c : cursos) {

					tCursos.add(c.getNome());
				}

				int qalunos = p.pesquisarUmConteudo_Numa_Linha_Int(this.BD, "infoescola", "qalunos", "id", "", 1);
				int qfunc = p.pesquisarUmConteudo_Numa_Linha_Int(this.BD, "infoescola", "qfunc", "id", "", 1);
				int QCurso = p.pesquisarUmConteudo_Numa_Linha_Int(this.BD, "infoescola", "QCurso", "id", "", 1);

				int despeza = 0;
				ArrayList<Integer> despezas = p.pesquisarTudoEmInt(this.BD, "adminfinanca", "despeza");

				if (despezas.size() > 0) {

					despeza = p.pesquisarTudoEmInt(this.BD, "adminfinanca", "despeza").get(0);
				}

				int renda = 0;
				String mes = s.mesActual(this.BD);
				if (this.quem_E_O_func.equalsIgnoreCase("pca")) {

					renda = p.pesquisarUmConteudo_Numa_Linha_Int(this.BD, "adminfinanca", "renda", "bi_admin", this.bi,
							0);
					nomeDaPagina = "WebGnius/cadastrar_Empresa/sistema_fase10";
				} else {

					renda = p.pesquisarUmConteudo_Numa_Linha_Int(this.BD, this.quem_E_O_func + "_financa", mes, "id",
							"", this.id);

					if (renda == 0) {
						model.addAttribute("renda", "Irregular");
					} else {
						model.addAttribute("renda", "Regular");
					}

					nomeDaPagina = "WebGnius/cadastrar_Empresa/sistema_fase11";

				}

				model.addAttribute("qalunos", qalunos);
				model.addAttribute("qfunc", qfunc);
				model.addAttribute("qCurso", QCurso);
				model.addAttribute("renda", renda);
				model.addAttribute("despeza", despeza);
				model.addAttribute("financa", this.quem_E_O_func + " | Finança");

				model.addAttribute("cursos", tCursos);
				model.addAttribute("resultado", "Curso Inserido Com Sucesso !");
				model.addAttribute("nome", configurarNome);
				model.addAttribute("escola", configurarEscola);

				this.pagina = nomeDaPagina;

				// }

			} else {

				estado = 0;
				nomeDaPagina = "redirect:login";
				this.pagina = nomeDaPagina;
			}

		} else if (requisicao.equalsIgnoreCase("/Admin-Cadastro")) {

			estado = -5;
			if (entrarNoSistema.podeAbrirAPagina(senha)) {

				// if(acesso.bloqueandoRequisicoes(TechShine_Controller.request, request)) {

				for (Curso c : cursos) {

					tCursos.add(c.getNome());
				}

				int qalunos = p.pesquisarUmConteudo_Numa_Linha_Int(this.BD, "infoescola", "qalunos", "id", "", 1);
				int qfunc = p.pesquisarUmConteudo_Numa_Linha_Int(this.BD, "infoescola", "qfunc", "id", "", 1);
				int QCurso = p.pesquisarUmConteudo_Numa_Linha_Int(this.BD, "infoescola", "QCurso", "id", "", 1);
				int renda = p.pesquisarUmConteudo_Numa_Linha_Int(this.BD, "adminfinanca", "renda", "bi_admin", this.bi,
						0);

				int despeza = 0;
				ArrayList<Integer> despezas = p.pesquisarTudoEmInt(this.BD, "adminfinanca", "despeza");

				if (despezas.size() > 0) {

					despeza = p.pesquisarTudoEmInt(this.BD, "adminfinanca", "despeza").get(0);
				}

				model.addAttribute("qalunos", qalunos);
				model.addAttribute("qfunc", qfunc);
				model.addAttribute("qCurso", QCurso);
				model.addAttribute("renda", renda + ",00 Kz");
				model.addAttribute("despeza", despeza);

				model.addAttribute("cursos", tCursos);
				model.addAttribute("resultado", "Curso Inserido Com Sucesso !");
				model.addAttribute("nome", configurarNome);
				model.addAttribute("escola", configurarEscola);

				nomeDaPagina = "WebGnius/cadastrar_Empresa/tudo_Cadastrado";
				this.pagina = nomeDaPagina;
				estado2 = 1;

				// }

			} else {

				estado = 0;
				nomeDaPagina = "redirect:login";
				this.pagina = nomeDaPagina;
			}

			// Alterando O Salario Dos Funcionários

		} else if (requisicao.equalsIgnoreCase("/Admin-Alterar-Salario")) {

			estado = -5;
			if (entrarNoSistema.podeAbrirAPagina(senha)) {

				// if(acesso.bloqueandoRequisicoes(TechShine_Controller.request, request)) {

				for (Curso c : cursos) {

					tCursos.add(c.getNome());
				}

				int qalunos = p.pesquisarUmConteudo_Numa_Linha_Int(this.BD, "infoescola", "qalunos", "id", "", 1);
				int qfunc = p.pesquisarUmConteudo_Numa_Linha_Int(this.BD, "infoescola", "qfunc", "id", "", 1);
				int QCurso = p.pesquisarUmConteudo_Numa_Linha_Int(this.BD, "infoescola", "QCurso", "id", "", 1);
				int renda = p.pesquisarUmConteudo_Numa_Linha_Int(this.BD, "adminfinanca", "renda", "bi_admin", this.bi,
						0);

				int despeza = 0;
				ArrayList<Integer> despezas = p.pesquisarTudoEmInt(this.BD, "adminfinanca", "despeza");

				if (despezas.size() > 0) {

					despeza = p.pesquisarTudoEmInt(this.BD, "adminfinanca", "despeza").get(0);
				}

				model.addAttribute("qalunos", qalunos);
				model.addAttribute("qfunc", qfunc);
				model.addAttribute("qCurso", QCurso);
				model.addAttribute("renda", renda + ",00 Kz");
				model.addAttribute("despeza", despeza);

				model.addAttribute("cursos", tCursos);
				model.addAttribute("resultado", "Curso Inserido Com Sucesso !");
				model.addAttribute("nome", configurarNome);
				model.addAttribute("escola", configurarEscola);

				nomeDaPagina = "WebGnius/cadastrar_Empresa/sistema_fase9a2";
				this.pagina = nomeDaPagina;
				// }

			} else {

				estado = 0;
				nomeDaPagina = "redirect:login";
				this.pagina = nomeDaPagina;
			}

		} else if (requisicao.equalsIgnoreCase("/Admin-Alterar-Propina")) {

			estado = -5;
			if (entrarNoSistema.podeAbrirAPagina(senha)) {

				// if(acesso.bloqueandoRequisicoes(TechShine_Controller.request, request)) {

				for (Curso c : cursos) {

					tCursos.add(c.getNome());
				}

				int qalunos = p.pesquisarUmConteudo_Numa_Linha_Int(this.BD, "infoescola", "qalunos", "id", "", 1);
				int qfunc = p.pesquisarUmConteudo_Numa_Linha_Int(this.BD, "infoescola", "qfunc", "id", "", 1);
				int QCurso = p.pesquisarUmConteudo_Numa_Linha_Int(this.BD, "infoescola", "QCurso", "id", "", 1);
				int renda = p.pesquisarUmConteudo_Numa_Linha_Int(this.BD, "adminfinanca", "renda", "bi_admin", this.bi,
						0);

				int despeza = 0;
				ArrayList<Integer> despezas = p.pesquisarTudoEmInt(this.BD, "adminfinanca", "despeza");

				if (despezas.size() > 0) {

					despeza = p.pesquisarTudoEmInt(this.BD, "adminfinanca", "despeza").get(0);
				}

				model.addAttribute("qalunos", qalunos);
				model.addAttribute("qfunc", qfunc);
				model.addAttribute("qCurso", QCurso);
				model.addAttribute("renda", renda + ",00 Kz");
				model.addAttribute("despeza", despeza);

				model.addAttribute("cursos", tCursos);
				model.addAttribute("resultado", "Curso Inserido Com Sucesso !");
				model.addAttribute("nome", configurarNome);
				model.addAttribute("escola", configurarEscola);
				nomeDaPagina = "WebGnius/cadastrar_Empresa/sistema_fase9b2";
				this.pagina = nomeDaPagina;
				// }

			} else {

				estado = 0;
				nomeDaPagina = "redirect:login";
				this.pagina = nomeDaPagina;
			}

		} else if (requisicao.equalsIgnoreCase("/Admin-Alterar-MC")) {

			estado = -5;
			if (entrarNoSistema.podeAbrirAPagina(senha)) {

				// if(acesso.bloqueandoRequisicoes(TechShine_Controller.request, request)) {

				for (Curso c : cursos) {

					tCursos.add(c.getNome());
				}

				int qalunos = p.pesquisarUmConteudo_Numa_Linha_Int(this.BD, "infoescola", "qalunos", "id", "", 1);
				int qfunc = p.pesquisarUmConteudo_Numa_Linha_Int(this.BD, "infoescola", "qfunc", "id", "", 1);
				int QCurso = p.pesquisarUmConteudo_Numa_Linha_Int(this.BD, "infoescola", "QCurso", "id", "", 1);
				int renda = p.pesquisarUmConteudo_Numa_Linha_Int(this.BD, "adminfinanca", "renda", "bi_admin", this.bi,
						0);

				int despeza = 0;
				ArrayList<Integer> despezas = p.pesquisarTudoEmInt(this.BD, "adminfinanca", "despeza");

				if (despezas.size() > 0) {

					despeza = p.pesquisarTudoEmInt(this.BD, "adminfinanca", "despeza").get(0);
				}

				model.addAttribute("qalunos", qalunos);
				model.addAttribute("qfunc", qfunc);
				model.addAttribute("qCurso", QCurso);
				model.addAttribute("renda", renda + ",00 Kz");
				model.addAttribute("despeza", despeza);

				model.addAttribute("cursos", tCursos);
				model.addAttribute("resultado", "Curso Inserido Com Sucesso !");
				model.addAttribute("nome", configurarNome);
				model.addAttribute("escola", configurarEscola);
				nomeDaPagina = "WebGnius/cadastrar_Empresa/sistema_fase9c2";
				this.pagina = nomeDaPagina;
				// }

			} else {

				estado = 0;
				nomeDaPagina = "redirect:login";
				this.pagina = nomeDaPagina;
			}

		} else if (requisicao.equalsIgnoreCase("/Admin-Alterar-Doc")) {

			estado = -5;
			if (entrarNoSistema.podeAbrirAPagina(senha)) {

				// if(acesso.bloqueandoRequisicoes(TechShine_Controller.request, request)) {

				for (Curso c : cursos) {

					tCursos.add(c.getNome());
				}

				int qalunos = p.pesquisarUmConteudo_Numa_Linha_Int(this.BD, "infoescola", "qalunos", "id", "", 1);
				int qfunc = p.pesquisarUmConteudo_Numa_Linha_Int(this.BD, "infoescola", "qfunc", "id", "", 1);
				int QCurso = p.pesquisarUmConteudo_Numa_Linha_Int(this.BD, "infoescola", "QCurso", "id", "", 1);
				int renda = p.pesquisarUmConteudo_Numa_Linha_Int(this.BD, "adminfinanca", "renda", "bi_admin", this.bi,
						0);

				int despeza = 0;
				ArrayList<Integer> despezas = p.pesquisarTudoEmInt(this.BD, "adminfinanca", "despeza");

				if (despezas.size() > 0) {

					despeza = p.pesquisarTudoEmInt(this.BD, "adminfinanca", "despeza").get(0);
				}

				model.addAttribute("qalunos", qalunos);
				model.addAttribute("qfunc", qfunc);
				model.addAttribute("qCurso", QCurso);
				model.addAttribute("renda", renda + ",00 Kz");
				model.addAttribute("despeza", despeza);

				model.addAttribute("cursos", tCursos);
				model.addAttribute("resultado", "Curso Inserido Com Sucesso !");
				model.addAttribute("nome", configurarNome);
				model.addAttribute("escola", configurarEscola);
				nomeDaPagina = "WebGnius/cadastrar_Empresa/sistema_fase9d2";
				this.pagina = nomeDaPagina;
				// }

			} else {

				estado = 0;
				nomeDaPagina = "redirect:login";
				this.pagina = nomeDaPagina;
			}

		} else if (requisicao.equalsIgnoreCase("/Admin-Alterar-Vendas")) {

			estado = -5;
			if (entrarNoSistema.podeAbrirAPagina(senha)) {

				// if(acesso.bloqueandoRequisicoes(TechShine_Controller.request, request)) {

				for (Curso c : cursos) {

					tCursos.add(c.getNome());
				}

				int qalunos = p.pesquisarUmConteudo_Numa_Linha_Int(this.BD, "infoescola", "qalunos", "id", "", 1);
				int qfunc = p.pesquisarUmConteudo_Numa_Linha_Int(this.BD, "infoescola", "qfunc", "id", "", 1);
				int QCurso = p.pesquisarUmConteudo_Numa_Linha_Int(this.BD, "infoescola", "QCurso", "id", "", 1);
				int renda = p.pesquisarUmConteudo_Numa_Linha_Int(this.BD, "adminfinanca", "renda", "bi_admin", this.bi,
						0);

				int despeza = 0;
				ArrayList<Integer> despezas = p.pesquisarTudoEmInt(this.BD, "adminfinanca", "despeza");

				if (despezas.size() > 0) {

					despeza = p.pesquisarTudoEmInt(this.BD, "adminfinanca", "despeza").get(0);
				}

				model.addAttribute("qalunos", qalunos);
				model.addAttribute("qfunc", qfunc);
				model.addAttribute("qCurso", QCurso);
				model.addAttribute("renda", renda);
				model.addAttribute("despeza", despeza);

				model.addAttribute("cursos", tCursos);
				model.addAttribute("resultado", "Curso Inserido Com Sucesso !");
				model.addAttribute("nome", configurarNome);
				model.addAttribute("escola", configurarEscola);

				nomeDaPagina = "WebGnius/cadastrar_Empresa/sistema_fase9e2";
				this.pagina = nomeDaPagina;
				// }

			} else {

				estado = 0;
				nomeDaPagina = "redirect:login";
				this.pagina = nomeDaPagina;
			}

		} else if (requisicao.equalsIgnoreCase("/Admin-Alterar-FE")) {

			estado = -5;
			if (entrarNoSistema.podeAbrirAPagina(senha)) {

				// if(acesso.bloqueandoRequisicoes(TechShine_Controller.request, request)) {

				for (Curso c : cursos) {

					tCursos.add(c.getNome());
				}

				int qalunos = p.pesquisarUmConteudo_Numa_Linha_Int(this.BD, "infoescola", "qalunos", "id", "", 1);
				int qfunc = p.pesquisarUmConteudo_Numa_Linha_Int(this.BD, "infoescola", "qfunc", "id", "", 1);
				int QCurso = p.pesquisarUmConteudo_Numa_Linha_Int(this.BD, "infoescola", "QCurso", "id", "", 1);
				int renda = p.pesquisarUmConteudo_Numa_Linha_Int(this.BD, "adminfinanca", "renda", "bi_admin", this.bi,
						0);

				int despeza = 0;
				ArrayList<Integer> despezas = p.pesquisarTudoEmInt(this.BD, "adminfinanca", "despeza");

				if (despezas.size() > 0) {

					despeza = p.pesquisarTudoEmInt(this.BD, "adminfinanca", "despeza").get(0);
				}

				model.addAttribute("qalunos", qalunos);
				model.addAttribute("qfunc", qfunc);
				model.addAttribute("qCurso", QCurso);
				model.addAttribute("renda", renda);
				model.addAttribute("despeza", despeza);

				model.addAttribute("cursos", tCursos);
				model.addAttribute("resultado", "Curso Inserido Com Sucesso !");
				model.addAttribute("nome", configurarNome);
				model.addAttribute("escola", configurarEscola);

				nomeDaPagina = "WebGnius/cadastrar_Empresa/sistema_fase9f2";
				this.pagina = nomeDaPagina;
				// }

			} else {

				estado = 0;
				nomeDaPagina = "redirect:login";
				this.pagina = nomeDaPagina;
			}

		} else if (requisicao.equalsIgnoreCase("/Admin-Ver-PM")) {

			estado = -5;
			if (entrarNoSistema.podeAbrirAPagina(senha)) {

				// if(acesso.bloqueandoRequisicoes(TechShine_Controller.request, request)) {

				nomeDaPagina = "WebGnius/cadastrar_Empresa/verPropinaEMulta";
				this.pagina = nomeDaPagina;
				// }

			} else {

				estado = 0;
				nomeDaPagina = "redirect:login";
				this.pagina = nomeDaPagina;
			}

		} else if (requisicao.equalsIgnoreCase("/Admin-Ver-Salario")) {

			estado = -5;
			if (entrarNoSistema.podeAbrirAPagina(senha)) {

				// if(acesso.bloqueandoRequisicoes(TechShine_Controller.request, request)) {

				nomeDaPagina = "WebGnius/cadastrar_Empresa/verSalario";
				this.pagina = nomeDaPagina;
				// }

			} else {

				estado = 0;
				nomeDaPagina = "redirect:login";
				this.pagina = nomeDaPagina;
			}

		} else if (requisicao.equalsIgnoreCase("/Admin-Ver-MC")) {

			estado = -5;
			if (entrarNoSistema.podeAbrirAPagina(senha)) {

				// if(acesso.bloqueandoRequisicoes(TechShine_Controller.request, request)) {

				nomeDaPagina = "WebGnius/cadastrar_Empresa/verMatriConfirmacao";
				this.pagina = nomeDaPagina;
				// }

			} else {

				estado = 0;
				nomeDaPagina = "redirect:login";
				this.pagina = nomeDaPagina;
			}

		} else if (requisicao.equalsIgnoreCase("/Admin-Ver-Doc")) {

			estado = -5;
			if (entrarNoSistema.podeAbrirAPagina(senha)) {

				// if(acesso.bloqueandoRequisicoes(TechShine_Controller.request, request)) {

				nomeDaPagina = "WebGnius/cadastrar_Empresa/verDocumento";
				this.pagina = nomeDaPagina;
				// }

			} else {

				estado = 0;
				nomeDaPagina = "redirect:login";
				this.pagina = nomeDaPagina;
			}

		} else if (requisicao.equalsIgnoreCase("/Admin-Ver-Materias")) {

			estado = -5;
			if (entrarNoSistema.podeAbrirAPagina(senha)) {

				// if(acesso.bloqueandoRequisicoes(TechShine_Controller.request, request)) {

				nomeDaPagina = "WebGnius/cadastrar_Empresa/verMaterias";
				this.pagina = nomeDaPagina;
				// }

			} else {

				estado = 0;
				nomeDaPagina = "redirect:login";
				this.pagina = nomeDaPagina;
			}

		} else if (requisicao.equalsIgnoreCase("/Admin-Ver-FE")) {

			estado = -5;
			if (entrarNoSistema.podeAbrirAPagina(senha)) {

				// if(acesso.bloqueandoRequisicoes(TechShine_Controller.request, request)) {

				nomeDaPagina = "WebGnius/cadastrar_Empresa/verFalta";
				this.pagina = nomeDaPagina;
				// }

			} else {

				estado = 0;
				nomeDaPagina = "redirect:login";
				this.pagina = nomeDaPagina;
			}

		}

		else if (requisicao.equalsIgnoreCase("/Admin-Minha-Seguranca")) {

			estado = -5;
			if (entrarNoSistema.podeAbrirAPagina(senha)) {

				// if(acesso.bloqueandoRequisicoes(TechShine_Controller.request, request)) {

				int qalunos = p.pesquisarUmConteudo_Numa_Linha_Int(this.BD, "infoescola", "qalunos", "id", "", 1);
				int qfunc = p.pesquisarUmConteudo_Numa_Linha_Int(this.BD, "infoescola", "qfunc", "id", "", 1);
				int QCurso = p.pesquisarUmConteudo_Numa_Linha_Int(this.BD, "infoescola", "QCurso", "id", "", 1);
				int renda = p.pesquisarUmConteudo_Numa_Linha_Int(this.BD, "adminfinanca", "renda", "bi_admin", this.bi,
						0);

				model.addAttribute("qalunos", qalunos);
				model.addAttribute("qfunc", qfunc);
				model.addAttribute("qCurso", QCurso);
				model.addAttribute("renda", renda + ",00 Kz");

				System.out.println("Nome: " + configurarNome);
				System.out.println("Escola: " + configurarEscola);

				model.addAttribute("nome", configurarNome);
				model.addAttribute("escola", configurarEscola);

				nomeDaPagina = "TechShine/Admin/security";
				this.pagina = nomeDaPagina;
				// }

			} else {

				estado = 0;
				nomeDaPagina = "redirect:login";
				this.pagina = nomeDaPagina;
			}

		}

		return this.pagina;
	}

	@GetMapping("/Cadastrar-Documentos")
	public String documentos(Model model) {

		Salvar_SQL s = new Salvar_SQL();
		String nomeDaPagina;
		if (estado < 0) {

			nomeDaPagina = this.pagina;
			this.pagina = nomeDaPagina;
		} else {

			++estado;
			if (estado > 0) {
				estado = estado - (estado - 1);
				estado = estado + 3;
			}
			if (entrarNoSistema.podeAbrirAPagina(senha)) {

				// if(acesso.bloqueandoRequisicoes(TechShine_Controller.request, request)) {

				if (estado == 4) {

					s.sistema_Actualizar_OndeParou_O_Cadastro(bi, 4);

					model.addAttribute("nome", configurarNome);
					model.addAttribute("escola", configurarEscola);
					this.pagina = "WebGnius/cadastrar_Empresa/Fase4_Doc";

				}
				// }

			} else {

				estado = 0;
				nomeDaPagina = "redirect:login";
				this.pagina = nomeDaPagina;
			}

		}

		return this.pagina;

	}

	@GetMapping("/Cadastrar-Materias")
	public String materias(Model model) {

		Salvar_SQL s = new Salvar_SQL();
		String nomeDaPagina;
		if (estado < 0) {

			nomeDaPagina = this.pagina;
			this.pagina = nomeDaPagina;
		} else {

			++estado;
			if (estado > 0) {
				estado = estado - (estado - 1);
				estado = estado + 3;
			}
			if (entrarNoSistema.podeAbrirAPagina(senha)) {

				// if(acesso.bloqueandoRequisicoes(TechShine_Controller.request, request)) {

				if (estado == 4) {

					s.sistema_Actualizar_OndeParou_O_Cadastro(bi, 4);

					model.addAttribute("nome", configurarNome);
					model.addAttribute("escola", configurarEscola);
					this.pagina = "WebGnius/cadastrar_Empresa/Fase4_Materias";

					// }

				}

			} else {

				estado = 0;
				nomeDaPagina = "redirect:login";
				this.pagina = nomeDaPagina;
			}

		}
		return this.pagina;

	}

	@GetMapping("/Entrar")
	public String entrar(Model model) {

		Salvar_SQL s = new Salvar_SQL();
		String nomeDaPagina;
		if (estado < 0) {

			nomeDaPagina = this.pagina;
			this.pagina = nomeDaPagina;
		} else {

			++estado;
			if (estado > 0) {
				estado = estado - (estado - 1);
				estado = estado + 3;
			}
			if (entrarNoSistema.podeAbrirAPagina(senha)) {

				// if(acesso.bloqueandoRequisicoes(TechShine_Controller.request, request)) {

				if (estado == 4) {

					model.addAttribute("nome", configurarNome);
					model.addAttribute("escola", configurarEscola);

					this.pagina = "WebGnius/cadastrar_Empresa/admin";

				}
				// }

			} else {

				estado = 0;
				nomeDaPagina = "redirect:login";
				this.pagina = nomeDaPagina;
			}

		}

		return this.pagina;

	}

	// =============================================
	// MÉTODOS POST

	@PostMapping({ "/Cadastrar-Sistema-1" })
	public String fase1(Model model, Fase1 fase1) {
		boolean primario;
		boolean secundario;
		boolean medio;

		this.fase1 = fase1;

		primario = fase1.isPrimario();
		secundario = fase1.isSecundario();
		medio = fase1.isMedio();

		System.out.println("Primario: " + primario);
		System.out.println("Secundario: " + secundario);
		System.out.println("Medio: " + medio);
		if ((primario) || (secundario) || (medio)) {

			pagina = entrarNoSistema.quemEstaAFazerLogin(senha, this.usuario);
			System.out.println("this pagina: " + this.pagina);

		} else {
			pagina = "WebGnius/cadastrar_Empresa/sistema_fase1";
		}

		return this.pagina;
	}

	@PostMapping({ "/Cadastrar-Sistema-2" })
	public String fase2(Model model, Fase2 admin) {

		this.configurarNome = admin.getPca();
		this.configurarEscola = admin.getInstituicao();

		admin.setPca(this.configurarNome);
		admin.setInstituicao(this.configurarEscola);

		Salvar_SQL s = new Salvar_SQL();
		Tabela_Actualizar_SQL ta = new Tabela_Actualizar_SQL();

		String nomeDaEscola2 = s.inserirTabelaAdmin(admin, this.fase1);
		ta.actualizarColuna_Qualquer_Linha("wg", "Escolas", "escola", 0, nomeDaEscola2, "bi", bi);
		this.BD = s.retornarBaseDeDados();

		model.addAttribute("nome", configurarNome);
		model.addAttribute("escola", configurarEscola);

		pagina = entrarNoSistema.quemEstaAFazerLogin(senha, this.usuario);
		return this.pagina;
	}

	@PostMapping({ "/Cadastrar-Documentos" })
	public String doc(Fase4 fase4, Model model) {

		this.documentos = fase4.getDocumentos();
		model.addAttribute("nome", configurarNome);
		model.addAttribute("escola", configurarEscola);

		this.pagina = "WebGnius/cadastrar_Empresa/Fase4_Materias";
		return this.pagina;
	}

	@PostMapping({ "/Cadastrar-Materias" })
	public String mat(Fase4 fase4, Model model) {

		Salvar_SQL s = new Salvar_SQL();
		this.materias = fase4.getMaterias();
		model.addAttribute("nome", configurarNome);
		model.addAttribute("escola", configurarEscola);

		s.inserir_Materias(this.BD, this.materias);
		s.inserir_Documentos(this.BD, this.documentos);

		// Definindo Que Parou O Cadastro na Fase 6
		s.sistema_Actualizar_OndeParou_O_Cadastro(bi, 5);

		// Definindo Que Terminou o Cadastro Do Sistema
		s.sistema_Cadastrado_Com_Sucesso(bi);

		this.pagina = "WebGnius/cadastrar_Empresa/Fase4_Entrar";
		return this.pagina;
	}

	@PostMapping({ "/Cadastrar-Sistema-5a" })
	public String CadasdrarCursos(@Validated Curso curso, Model model, BindingResult erros) {

		Salvar_SQL s = new Salvar_SQL();
		Pesquisar_SQL p = new Pesquisar_SQL();
		ArrayList<Curso> cursos = p.Listar_Cursos(this.BD);
		ArrayList<String> tCursos = new ArrayList<>();
		for (Curso c : cursos) {

			tCursos.add(c.getNome());
		}
		ArrayList<String> niveis = p.pesquisarTudoEmString(this.BD, "infoescola", "niveis");

		boolean cursoInserido;

		if (curso.isInserir_Disciplinas()) {
			this.nomeDoCurso = curso.getNome();
			curso.setNome(this.nomeDoCurso);

			cursoInserido = s.inserirCurso(this.BD, curso);
			if (cursoInserido) {

				model.addAttribute("niveis", niveis);
				model.addAttribute("curso", this.nomeDoCurso);
				model.addAttribute("nome", configurarNome);
				model.addAttribute("escola", configurarEscola);

				model.addAttribute("resultado", "Curso Inserido, Crie as Desciplinas desse Curso");

				this.pagina = "WebGnius/cadastrar_Empresa/sistema_fase6.1";

			} else {
				model.addAttribute("resultado", " * Falha, Este Curso Ja Existe Ou Defina-o sem Pontos");
				this.pagina = "redirect:Cadastrar-Sistema-5a";
			}
		} else {
			cursoInserido = s.inserirCurso(this.BD, curso);
			if (cursoInserido) {

				if (cursos != null) {

					model.addAttribute("cursos", tCursos);
				}

				model.addAttribute("resultado", "Curso Inserido Com Sucesso !");
				model.addAttribute("nome", configurarNome);
				model.addAttribute("escola", configurarEscola);

				this.pagina = "redirect:Cadastrar-Sistema-5a";

			} else {
				model.addAttribute("resultado", " * Falha, Este Curso Ja Existe Ou Defina-o sem Pontos");
				this.pagina = "redirect:Cadastrar-Sistema-5a";
			}

		}

		// nomeDaPagina= entrarNoSistema.quemEstaAFazerLogin(senha, model);
		return this.pagina;
	}

	@PostMapping("/Cadastrar-Sistema-5b")
	public String CadasdrarFuns(Funcionario func, Model model) {

		Salvar_SQL s = new Salvar_SQL();
		boolean retorno;
		String cargo = func.getCargo();

		func.setCargo(cargo);

		this.func = func;

		if (cargo.equalsIgnoreCase("professor")) {

			// Colocar a ideia de selecionar as
			// Disciplinas do Professor

			model.addAttribute("nome", configurarNome);
			model.addAttribute("escola", configurarEscola);

			this.pagina = "redirect:Admin-Cadastrar_Prof";

		} else {

			ArrayList<Funcionario> funcs = new ArrayList<>();
			funcs.add(this.func);

			model.addAttribute("funcionario", funcs);
			model.addAttribute("nome", configurarNome);
			model.addAttribute("escola", configurarEscola);

			retorno = s.inserir_Integrante(this.BD, func);
			if (retorno) {

				model.addAttribute("resultado", "Funcionário Cadastrado com Sucesso");
			} else {

				model.addAttribute("resultado", "Falha, Volte A Inserir O " + "Funcionário");
			}
			System.out.println("Entrou nesse LOCAL ");
			this.pagina = "redirect:Cadastrar-Sistema-5b";
			System.out.println("Agora está Aqui ");

		}

		// nomeDaPagina= entrarNoSistema.quemEstaAFazerLogin(senha, model);
		return this.pagina;
	}

	@PostMapping({ "/Cadastrar-Sistema-6" })
	public String CadasdrarDesciplinas(Model model, Desciplina desciplina) {

		Pesquisar_SQL p = new Pesquisar_SQL();
		ArrayList<Boolean> chaves = new ArrayList<>();

		chaves.add(desciplina.isChave1());
		chaves.add(desciplina.isChave2());
		chaves.add(desciplina.isChave3());
		chaves.add(desciplina.isChave4());
		chaves.add(desciplina.isChave5());
		chaves.add(desciplina.isChave6());
		chaves.add(desciplina.isChave7());
		chaves.add(desciplina.isChave8());
		chaves.add(desciplina.isChave9());
		chaves.add(desciplina.isChave10());
		chaves.add(desciplina.isChave11());
		chaves.add(desciplina.isChave12());
		chaves.add(desciplina.isChave13());
		chaves.add(desciplina.isChave14());
		chaves.add(desciplina.isChave15());

		ArrayList<String> desciplinas = new ArrayList<>();
		desciplinas.add(desciplina.getDesciplinas1());
		desciplinas.add(desciplina.getDesciplinas2());
		desciplinas.add(desciplina.getDesciplinas3());
		desciplinas.add(desciplina.getDesciplinas4());
		desciplinas.add(desciplina.getDesciplinas5());
		desciplinas.add(desciplina.getDesciplinas6());
		desciplinas.add(desciplina.getDesciplinas7());
		desciplinas.add(desciplina.getDesciplinas8());
		desciplinas.add(desciplina.getDesciplinas9());
		desciplinas.add(desciplina.getDesciplinas10());
		desciplinas.add(desciplina.getDesciplinas11());
		desciplinas.add(desciplina.getDesciplinas12());
		desciplinas.add(desciplina.getDesciplinas13());
		desciplinas.add(desciplina.getDesciplinas14());
		desciplinas.add(desciplina.getDesciplinas15());
		
		System.out.println("Todas Disciplinas");

         for(String c : desciplinas) {
        	 
        	 System.out.println(" Disciplina: "+c );
         }
         
         System.out.println("Todas Chaves");

         for(Boolean c : chaves) {
        	 
        	 System.out.println(" Chaves: "+c );
         }


		// nomeDaPagina= entrarNoSistema.quemEstaAFazerLogin(senha, model);

		Salvar_SQL s = new Salvar_SQL();

		s.inserir_Varias_Disciplinas(this.BD, chaves, desciplina.getNivel(), desciplinas, desciplina.getCurso());

		ArrayList<Curso> cursos = p.Listar_Cursos(this.BD);
		ArrayList<String> tCursos = new ArrayList<>();

		for (Curso c : cursos) {

			tCursos.add(c.getNome());
		}
		model.addAttribute("cursos", tCursos);
		model.addAttribute("resultado", "Curso Inserido Com Sucesso !");
		model.addAttribute("nome", configurarNome);
		model.addAttribute("escola", configurarEscola);

		// nomeDaPagina= entrarNoSistema.quemEstaAFazerLogin(senha, model);
		return "redirect:Cadastrar-Sistema-6";
	}

	@PostMapping({ "/Cadastrar-Sistema-6-1" })
	public String CadasdrarDesciplinas2(Model model, Desciplina desciplina) {

		Pesquisar_SQL p = new Pesquisar_SQL();
		ArrayList<Boolean> chaves = new ArrayList<>();

		chaves.add(desciplina.isChave1());
		chaves.add(desciplina.isChave2());
		chaves.add(desciplina.isChave3());
		chaves.add(desciplina.isChave4());
		chaves.add(desciplina.isChave5());
		chaves.add(desciplina.isChave6());
		chaves.add(desciplina.isChave7());
		chaves.add(desciplina.isChave8());
		chaves.add(desciplina.isChave9());
		chaves.add(desciplina.isChave10());
		chaves.add(desciplina.isChave11());
		chaves.add(desciplina.isChave12());
		chaves.add(desciplina.isChave13());
		chaves.add(desciplina.isChave14());
		chaves.add(desciplina.isChave15());

		ArrayList<String> desciplinas = new ArrayList<>();
		desciplinas.add(desciplina.getDesciplinas1());
		desciplinas.add(desciplina.getDesciplinas2());
		desciplinas.add(desciplina.getDesciplinas3());
		desciplinas.add(desciplina.getDesciplinas4());
		desciplinas.add(desciplina.getDesciplinas5());
		desciplinas.add(desciplina.getDesciplinas6());
		desciplinas.add(desciplina.getDesciplinas7());
		desciplinas.add(desciplina.getDesciplinas8());
		desciplinas.add(desciplina.getDesciplinas9());
		desciplinas.add(desciplina.getDesciplinas10());
		desciplinas.add(desciplina.getDesciplinas11());
		desciplinas.add(desciplina.getDesciplinas12());
		desciplinas.add(desciplina.getDesciplinas13());
		desciplinas.add(desciplina.getDesciplinas14());
		desciplinas.add(desciplina.getDesciplinas15());

		// nomeDaPagina= entrarNoSistema.quemEstaAFazerLogin(senha, model);

		Salvar_SQL s = new Salvar_SQL();

		s.inserir_Varias_Disciplinas(this.BD, chaves, desciplina.getNivel(), desciplinas, this.nomeDoCurso);

		ArrayList<Curso> cursos = p.Listar_Cursos(this.BD);
		ArrayList<String> tCursos = new ArrayList<>();

		for (Curso c : cursos) {

			tCursos.add(c.getNome());
		}
		model.addAttribute("cursos", tCursos);
		model.addAttribute("resultado", "Curso Inserido Com Sucesso !");
		model.addAttribute("nome", configurarNome);
		model.addAttribute("escola", configurarEscola);

		this.pagina = "redirect:Cadastrar-Sistema-6";
		return this.pagina;
	}

	@PostMapping({ "/Cadastrar-Sistema-7" })
	public String CadasdrarTurmas(Model model, Turma turma) {

		Pesquisar_SQL p = new Pesquisar_SQL();
		Tabelas_Criar_SQL c = new Tabelas_Criar_SQL();

		String turno = turma.getTurno();
		String nivel = turma.getNivel();
		String curso = turma.getCurso();
		String sigla = turma.getSigla();

		turma.setTurno(turno);
		turma.setNivel(nivel);
		turma.setCurso(curso);

		boolean continuar = true;

		String nomeTurma;

		String turno2 = (String) turno.subSequence(0, 1);
		String nivel2 = (String) turma.getNivel().subSequence(0, 2);

		String abeviaturaCurso_Ou_Disciplina = curso;
		abeviaturaCurso_Ou_Disciplina = abeviaturaCurso_Ou_Disciplina.substring(0, 3);
		ArrayList<String> turmas = p.pesquisarTudoEmString(BD, "cursos_turmas", curso);

		if ((sigla.equalsIgnoreCase("")) || (sigla == null)) {

			c.criarTabelaTurma(this.BD, turma, configurarEscola);

			model.addAttribute("resultado", "Turma Criada Com Sucesso!");
			this.pagina = "redirect:Cadastrar-Sistema-7";
		} else {

			nomeTurma = "Turma" + turno2 + "" + abeviaturaCurso_Ou_Disciplina + "" + nivel2 + "__" + sigla;

			sair: for (String turm : turmas) {

				if (turm.equalsIgnoreCase(nomeTurma)) {

					continuar = false;
					break sair;
				}
			}

			if (continuar) {

				c.criarTabelaTurma(this.BD, turma, configurarEscola);
				model.addAttribute("resultado", "Turma Criada Com Sucesso!");
				this.pagina = "redirect:Cadastrar-Sistema-7";
			} else {

				model.addAttribute("resultado", "Ja Existe Uma Turma com A Sigla Inserida!");
				this.pagina = "redirect:Cadastrar-Sistema-7";
			}
		}

		// nomeDaPagina= entrarNoSistema.quemEstaAFazerLogin(senha, model);

		return this.pagina;
	}

	@PostMapping({ "/Cadastrar-Sistema-9b" })
	public String CadasdrarSalario(@Validated Salario salario, Model model, BindingResult erros) {

		if (erros.hasErrors()) {

			return "redirect:Cadastrar-Sistema-9b";
		}

		this.salario = salario;

		System.out.println("salario Do Professor: " + salario.getProfSalario());
		// nomeDaPagina= entrarNoSistema.quemEstaAFazerLogin(senha, model);
		return "redirect:Cadastrar-Sistema-9b";
	}

	@PostMapping({ "/Cadastrar-Sistema-9c" })
	public String CadasdrarPropinasEMulta(@Validated Propina_E_Multa pM, Model model, BindingResult erros) {

		this.pM = pM;
		// nomeDaPagina= entrarNoSistema.quemEstaAFazerLogin(senha, model);
		return "redirect:Cadastrar-Sistema-9c";
	}

	@PostMapping({ "/Cadastrar-Sistema-9d" })
	public String CadasdrarMatrEConfir(@Validated MatricuEConfirmacao mc, Model model, BindingResult erros) {

		if (erros.hasErrors()) {

			return "redirect:Cadastrar-Sistema-9d";
		}

		this.mc = mc;
		// nomeDaPagina= entrarNoSistema.quemEstaAFazerLogin(senha, model);
		return "redirect:Cadastrar-Sistema-9d";
	}

	@PostMapping("/Admin-Cadastrar_Prof")
	public String Admin_Cadastrar__Prof(Funcionario func, Model model) {

		String pagina = null;
		int valor;

		Pesquisar_SQL p = new Pesquisar_SQL();

		ArrayList<Integer> tempos = func.getTempoPorDesciplinas();

		valor = tempos.get(0);
		tempos.add(valor);
		func.setTempoPorDesciplinas(tempos);

		if (valor == 0) {

			model.addAttribute("resultado", "Falha, Informe o Tempo Por Desciplinas");
			this.pagina = "redirect:Cadastrar-Sistema-5b";
		} else {

			this.func.setDisciplinas(func.getDisciplinas());
			this.func.setTempoPorDesciplinas(func.getTempoPorDesciplinas());

			String curso = this.func.getCurso();
			String turno = this.func.getTurno();
			String nivel = this.func.getNivel();

			this.func.setCurso(curso);
			this.func.setTurno(turno);
			this.func.setNivel(nivel);

			Salvar_SQL s = new Salvar_SQL();
			ArrayList<String> turmas = p.Listar_TurmasDoCurso2(this.BD, turno, nivel, curso);

			ArrayList<String> tTurmas = new ArrayList<>();

			sair: for (int i = 0; i < turmas.size(); i++) {

				if (turmas.get(i).contains(func.getTurmas().get(0))) {
					tTurmas.add(turmas.get(i));

					this.func.setTurmas(tTurmas);
					s.inserir_Integrante(this.BD, this.func);
					model.addAttribute("resultado", "Professor Cadastrado Com Sucesso !");
					break sair;
				}
			}

			imprimirFuncionario();

			this.pagina = "redirect:Cadastrar-Sistema-5b";
		}

		return this.pagina;

	}

	@PostMapping({ "/Cadastrar-Sistema-9e" })
	public String CadasdrarDoc(@Validated Documentos doc, Model model, BindingResult erros) {

		Pesquisar_SQL p = new Pesquisar_SQL();
		ArrayList<String> materiais = p.pesquisarTudoEmString(this.BD, "infoescola", "Materias");

		this.doc = doc;

		if (materiais.size() > 0) {
			this.pagina = "redirect:Cadastrar-Sistema-9e";
		} else {

			this.pagina = "redirect:Cadastrar-Sistema-9f";
		}
		// nomeDaPagina= entrarNoSistema.quemEstaAFazerLogin(senha, model);
		return this.pagina;
	}

	@PostMapping({ "/Cadastrar-Sistema-9f" })
	public String CadasdrarVenda(@Validated Venda venda, Model model, BindingResult erros) {

		if (erros.hasErrors()) {

			return "redirect:Cadastrar-Sistema-9f";
		}

		this.venda = venda;
		// nomeDaPagina= entrarNoSistema.quemEstaAFazerLogin(senha, model);
		return "redirect:Cadastrar-Sistema-9f";
	}

	@PostMapping({ "/Cadastrar-Sistema-10" })
	public String CadasdrarFaltas(@Validated FaltaEEstagio fe, Model model, BindingResult erros) {

		// =======================================
		// Apartir Daqui todos Os Dados Irão Na Base De Dados
		// De Salario,Documentos,matriculaEConfirmação,vendaDeMaterias
		// e Falta E Estagio para serem salvadas
		/*
		 * 
		 * this.salario=null; this.doc; this.mc;
		 * 
		 * if(this.venda==null){
		 * 
		 * }else{
		 * 
		 * colocar a venda de materias } this.venda; fe; this.pM;
		 * 
		 */

		// ===========================================

		Salvar_SQL s = new Salvar_SQL();
		Pesquisar_SQL p = new Pesquisar_SQL();

		ArrayList<String> materias = p.pesquisarTudoEmString(this.BD, "infoescola", "Materias");
		ArrayList<String> documentos = p.pesquisarTudoEmString(this.BD, "infoescola", "Documentos");

		boolean continuar = false;
		if (materias.size() > 0) {

			continuar = true;
		}

		ArrayList<String> cursos = p.pesquisarTudoEmString(this.BD, "cursos", "nome");

		for (int i = 0; i < cursos.size(); i++) {

			s.inserir_Preco_Da_Confirmacao_curso(this.BD, cursos.get(i), this.mc.getConfirmacoes().get(i));

			s.inserir_Preco_Da_matricula_curso(this.BD, cursos.get(i), this.mc.getMatriculas().get(i));


		}

		if (continuar) {

			for (int i = 0; i < materias.size(); i++) {

				s.inserir_Preco_Do_Material(this.BD, materias.get(i), this.venda.getMateriais().get(i));
			}
		}

		for (int i = 0; i < documentos.size(); i++) {
			s.inserir_Preco_Do_Documento(this.BD, documentos.get(i), this.doc.getDocumentos().get(i));
		}

		s.inserir_Preco_Tempo_de_PagamentoPropina(this.BD, this.pM.getPrazo());

		s.inserir_Precos_Das_Faltas(this.BD, "Vermelha", fe.getFaltas().get(0));
		s.inserir_Precos_Das_Faltas(this.BD, "Azul", fe.getFaltas().get(1));
		s.inserir_Preco_Do_Estagio(this.BD, fe.getEstagio());

		Tabela_Actualizar_SQL ta = new Tabela_Actualizar_SQL();
		ta.actualizarColuna_Qualquer_Linha(this.BD, "adminfinanca", "finCadastrada", 1, "", "bi_admin", this.bi);

		// nomeDaPagina= entrarNoSistema.quemEstaAFazerLogin(senha, model);
		return "redirect:Cadastrar-Sistema-10";
	}

	@PostMapping({ "/Admin-Alterar-Salario" })
	public String alterarSalario(@Validated Salario salario, Model model, BindingResult erros) {

		if (erros.hasErrors()) {

			return "redirect:Admin-Alterar-Salario";
		}

		System.out.println("salario Do Professor: " + salario.getProfSalario());
		// nomeDaPagina= entrarNoSistema.quemEstaAFazerLogin(senha, model);
		return "redirect:Admin-Alterar-Salario";
	}

	@PostMapping({ "/Admin-Alterar-Propina" })
	public String alterarPropinasEMulta(@Validated Propina_E_Multa pM, Model model, BindingResult erros) {

		if (erros.hasErrors()) {

			return "redirect:Admin-Alterar-Propina";
		}

		// nomeDaPagina= entrarNoSistema.quemEstaAFazerLogin(senha, model);
		return "redirect:Admin-Alterar-Propina";
	}

	@PostMapping({ "/Admin-Alterar-MC" })
	public String alterarMatrEConfir(@Validated MatricuEConfirmacao mc, Model model, BindingResult erros) {

		if (erros.hasErrors()) {

			return "redirect:Admin-Alterar-MC";
		}

		// nomeDaPagina= entrarNoSistema.quemEstaAFazerLogin(senha, model);
		return "redirect:Admin-Alterar-MC";
	}

	@PostMapping({ "/Admin-Alterar-Doc" })
	public String AlterarDoc(@Validated Documentos doc, Model model, BindingResult erros) {

		if (erros.hasErrors()) {

			return "redirect:Admin-Alterar-Doc";
		}
		return "redirect:Admin-Alterar-Doc";
	}

	@PostMapping({ "/Admin-Alterar-Vendas" })
	public String alterarVenda(@Validated Venda venda, Model model, BindingResult erros) {

		if (erros.hasErrors()) {

			return "redirect:Admin-Alterar-Vendas";
		}

		// nomeDaPagina= entrarNoSistema.quemEstaAFazerLogin(senha, model);
		return "redirect:Admin-Alterar-Vendas";
	}

	@PostMapping({ "/Admin-Alterar-FE" })
	public String alterarFaltas(@Validated FaltaEEstagio fe, Model model, BindingResult erros) {

		if (erros.hasErrors()) {

			return "redirect:Admin-Alterar-FE";
		}

		// nomeDaPagina= entrarNoSistema.quemEstaAFazerLogin(senha, model);
		return "redirect:Admin-Alterar-FE";
	}

	// FIM DO CADASTOR DA EMPRESA NA WG
	// ============================

	/**
	 * Metodos Pertencentes aos Administrador da Escola
	 * 
	 * @return as devidas paginas
	 * 
	 *         Feito por
	 * @author Jose Euclides Pedro Pereira dos Santos
	 */

	@GetMapping("/admin")
	public String admin(Model model) {

		Pesquisar_SQL p = new Pesquisar_SQL();
		String nomeDaPagina = null;
		estado = -5;

		if (entrarNoSistema.podeAbrirAPagina(senha)) {

			// if(acesso.bloqueandoRequisicoes(TechShine_Controller.request, request)) {

			int qalunos = p.pesquisarUmConteudo_Numa_Linha_Int(this.BD, "infoescola", "qalunos", "id", "", 1);
			int qfunc = p.pesquisarUmConteudo_Numa_Linha_Int(this.BD, "infoescola", "qfunc", "id", "", 1);
			int QCurso = p.pesquisarUmConteudo_Numa_Linha_Int(this.BD, "infoescola", "QCurso", "id", "", 1);
			int renda = 0;

			Salvar_SQL s = new Salvar_SQL();
			String mes = s.mesActual(this.BD);

			if (this.quem_E_O_func.equalsIgnoreCase("pca")) {
				renda = p.pesquisarUmConteudo_Numa_Linha_Int(this.BD, "adminfinanca", "renda", "bi_admin", this.bi, 0);
				nomeDaPagina = "TechShine/Admin/inicio";
				model.addAttribute("renda", renda + ",00 Kz");
			} else if (this.quem_E_O_func.equalsIgnoreCase("DG")) {

				renda = p.pesquisarUmConteudo_Numa_Linha_Int(this.BD, "dg_financa", mes, "id", "", this.id);

				if (renda == 0) {
					model.addAttribute("renda", "Irregular");
				} else {
					model.addAttribute("renda", "Regular");
				}

				nomeDaPagina = "TechShine/DG/inicio";
			} else if (this.quem_E_O_func.equalsIgnoreCase("DA")) {

				renda = p.pesquisarUmConteudo_Numa_Linha_Int(this.BD, "da_financa", mes, "id", "", this.id);

				if (renda == 0) {
					model.addAttribute("renda", "Irregular");
				} else {
					model.addAttribute("renda", "Regular");
				}

				nomeDaPagina = "TechShine/DA/inicio";
			} else if (this.quem_E_O_func.equalsIgnoreCase("DP")) {

				renda = p.pesquisarUmConteudo_Numa_Linha_Int(this.BD, "dp_financa", mes, "id", "", this.id);

				if (renda == 0) {
					model.addAttribute("renda", "Irregular");
				} else {
					model.addAttribute("renda", "Regular");
				}

				nomeDaPagina = "TechShine/DP/inicio";
			}

			model.addAttribute("qalunos", qalunos);
			model.addAttribute("qfunc", qfunc);
			model.addAttribute("qCurso", QCurso);

			model.addAttribute("nome", configurarNome);
			model.addAttribute("escola", configurarEscola);

			this.pagina = nomeDaPagina;
			// this.model.addAttribute("nome", this.Admin);
			// }

		} else {
			estado = 0;
			this.pagina = "redirect:login";
		}

		return this.pagina;

	}
	@GetMapping("/Admin-Todos-Cursos")
	public String lista_De_Cursos(Model model) {

		String nomeDaPagina = null;
		estado = -5;
		if (entrarNoSistema.podeAbrirAPagina(senha)) {

			// if(acesso.bloqueandoRequisicoes(TechShine_Controller.request, request)) {

			Pesquisar_SQL p = new Pesquisar_SQL();

			ArrayList<String> cursos = p.pesquisarTudoEmString(this.BD, "cursos", "nome");
			ArrayList<Integer> precos = p.pesquisarTudoEmInt(this.BD, "cursos", "preco");
			ArrayList<String> coords = p.pesquisarTudoEmString(this.BD, "cursos", "coord");
			ArrayList<Integer> descima = p.pesquisarTudoEmInt(this.BD, "cursos_Niveis", "Decima");
			ArrayList<Integer> DecimaPrimeira = p.pesquisarTudoEmInt(this.BD, "cursos_Niveis", "DecimaPrimeira");
			ArrayList<Integer> DecimaSegunda = p.pesquisarTudoEmInt(this.BD, "cursos_Niveis", "DecimaSegunda");
			ArrayList<Integer> DecimaTerceira = p.pesquisarTudoEmInt(this.BD, "cursos_Niveis", "DecimaTerceira");
			
			ArrayList<Curso> funcs = new ArrayList<>();
			for (int i = 0; i < cursos.size(); i++) {

				Curso curso = new Curso();

				if (cursos.size() > 0) {
					curso.setNome(cursos.get(i));
				}

				if (coords.size() > 0) {
					curso.setCoord(coords.get(i));
				} else {
					curso.setCoord("Nenhum");
				}

				if (descima.size() > 0) {
					curso.setPreco1(descima.get(i));
				}
				if (DecimaPrimeira.size() > 0) {
					curso.setPreco2(DecimaPrimeira.get(i));
				}
				if (DecimaSegunda.size() > 0) {
					curso.setPreco3(DecimaSegunda.get(i));
				}
				if (DecimaTerceira.size() > 0) {
					curso.setPreco4(DecimaTerceira.get(i));
				}

				funcs.add(curso);

			}

			int qalunos = p.pesquisarUmConteudo_Numa_Linha_Int(this.BD, "infoescola", "qalunos", "id", "", 1);
			int qfunc = p.pesquisarUmConteudo_Numa_Linha_Int(this.BD, "infoescola", "qfunc", "id", "", 1);
			int QCurso = p.pesquisarUmConteudo_Numa_Linha_Int(this.BD, "infoescola", "QCurso", "id", "", 1);
			Salvar_SQL s = new Salvar_SQL();
			int renda;
			int despeza = 0;
			String mes = s.mesActual(this.BD);

			if (this.quem_E_O_func.equalsIgnoreCase("pca")) {
				renda = p.pesquisarUmConteudo_Numa_Linha_Int(this.BD, "adminfinanca", "renda", "bi_admin", this.bi, 0);

				ArrayList<Integer> despezas = p.pesquisarTudoEmInt(this.BD, "adminfinanca", "despeza");

				if (despezas.size() > 0) {

					despeza = p.pesquisarTudoEmInt(this.BD, "adminfinanca", "despeza").get(0);
				}
				model.addAttribute("renda", renda + ",00 Kz");

			} else {

				renda = p.pesquisarUmConteudo_Numa_Linha_Int(this.BD, this.quem_E_O_func + "_financa", mes, "id", "",
						this.id);

				if (renda == 0) {
					model.addAttribute("renda", "Irregular");
				} else {
					model.addAttribute("renda", "Regular");
				}

			} 

			model.addAttribute("qalunos", qalunos);
			model.addAttribute("qfunc", qfunc);
			model.addAttribute("qCurso", QCurso);

			model.addAttribute("despeza", despeza);

			model.addAttribute("cursos", funcs);

			model.addAttribute("nome", configurarNome);
			model.addAttribute("escola", configurarEscola);

			nomeDaPagina = "TechShine/Admin/listaDeCursos";
			this.pagina = nomeDaPagina;
			// }

		} else {

			estado = 0;
			nomeDaPagina = "redirect:login";
			this.pagina = nomeDaPagina;
		}

		return this.pagina;
	}

	@GetMapping("/Admin-curso-info")
	public String Cursos(HttpServletRequest request) {

		String nomeDaPagina = null;
		estado = -5;
		if (entrarNoSistema.podeAbrirAPagina(senha)) {

			// if(acesso.bloqueandoRequisicoes(TechShine_Controller.request, request)) {

			nomeDaPagina = "TechShine/Admin/cursoDados";
			this.pagina = nomeDaPagina;
			// }

		} else {

			estado = 0;
			nomeDaPagina = "redirect:login";
			this.pagina = nomeDaPagina;
		}

		return this.pagina;
	}

	@GetMapping("/Admin-Alunos")
	public String matricularAluno(Model model) {
		String nomeDaPagina = null;

		Pesquisar_SQL p = new Pesquisar_SQL();
		estado = -5;
		if (entrarNoSistema.podeAbrirAPagina(senha)) {

			// if(acesso.bloqueandoRequisicoes(TechShine_Controller.request, request)) {

			ArrayList<Curso> cursos = p.Listar_Cursos(this.BD);
			ArrayList<String> tCursos = new ArrayList<>();

			for (Curso c : cursos) {

				tCursos.add(c.getNome());
			}
			ArrayList<String> niveis = p.pesquisarTudoEmString(this.BD, "infoescola", "niveis");

			int qalunos = p.pesquisarUmConteudo_Numa_Linha_Int(this.BD, "infoescola", "qalunos", "id", "", 1);
			int qfunc = p.pesquisarUmConteudo_Numa_Linha_Int(this.BD, "infoescola", "qfunc", "id", "", 1);
			int QCurso = p.pesquisarUmConteudo_Numa_Linha_Int(this.BD, "infoescola", "QCurso", "id", "", 1);

			Salvar_SQL s = new Salvar_SQL();
			int renda;
			int despeza = 0;
			String mes = s.mesActual(this.BD);

			if (this.quem_E_O_func.equalsIgnoreCase("pca")) {
				renda = p.pesquisarUmConteudo_Numa_Linha_Int(this.BD, "adminfinanca", "renda", "bi_admin", this.bi, 0);

				ArrayList<Integer> despezas = p.pesquisarTudoEmInt(this.BD, "adminfinanca", "despeza");

				if (despezas.size() > 0) {

					despeza = p.pesquisarTudoEmInt(this.BD, "adminfinanca", "despeza").get(0);
				}

				model.addAttribute("renda", renda + ",00 Kz");
			} else {

				renda = p.pesquisarUmConteudo_Numa_Linha_Int(this.BD, this.quem_E_O_func + "_financa", mes, "id", "",
						this.id);

				if (renda == 0) {
					model.addAttribute("renda", "Irregular");
				} else {
					model.addAttribute("renda", "Regular");
				}

			}

			model.addAttribute("qalunos", qalunos);
			model.addAttribute("qfunc", qfunc);
			model.addAttribute("qCurso", QCurso);
			model.addAttribute("despeza", despeza);

			model.addAttribute("cursos", tCursos);
			model.addAttribute("resultado", "Curso Inserido Com Sucesso !");
			model.addAttribute("nome", configurarNome);
			model.addAttribute("escola", configurarEscola);
			model.addAttribute("niveis", niveis);

			nomeDaPagina = "TechShine/Admin/cadastrarAluno";
			this.pagina = nomeDaPagina;

			// }

		} else {

			estado = 0;
			nomeDaPagina = "redirect:login";
			this.pagina = nomeDaPagina;
		}
		return this.pagina;
	}

	@PostMapping("/Admin-Alunos")
	public String matricularAluno(Aluno aluno, Model model) {

		
		String nomeDoAluno = aluno.getNome();
		
		System.out.println("nomeDoAluno: "+nomeDoAluno);
		this.aluno.setNome(nomeDoAluno);
		
		Salvar_SQL s = new Salvar_SQL();
		Turma dadosAluno = s.inserirAluno(this.BD, aluno);

		String turma2 = dadosAluno.getTurma();
		dadosAluno.setTurma(turma2);

		String[] a = turma2.split("_");

		turma2 = this.configurarEscola + " " + a[0] + " AL";
		dadosAluno.setUsuario(turma2);

		this.turma = dadosAluno;

		String curso = aluno.getCurso();
		String nivel = aluno.getNivel();
		String turno = aluno.getTurno();
		

		aluno.setCurso(curso);
		aluno.setNivel(nivel);
		aluno.setTurno(turno);
		aluno.setNome(nomeDoAluno);

		
		this.aluno.setCurso(curso);
		this.aluno.setNivel(nivel);
		this.aluno.setTurno(turno);

		if (dadosAluno.isAlunoInserido()) {

			model.addAttribute("resultado", "Aluno Inserido Com Sucesso !");
			model.addAttribute("nome", configurarNome);
			model.addAttribute("escola", configurarEscola);
			model.addAttribute("aluno", dadosAluno);

			this.pagina = "redirect:Admin-Info";
		} else {

			model.addAttribute("resultado", "Aluno Inserido Com Sucesso !");
			model.addAttribute("nome", configurarNome);
			model.addAttribute("escola", configurarEscola);

			ArrayList<Turma> alunoDaTurma = new ArrayList<>();
			alunoDaTurma.add(dadosAluno);

			model.addAttribute("aluno", alunoDaTurma);

			this.pagina = "redirect:Admin-Alunos";
		}
		return this.pagina;
	}

	@GetMapping({ "/Admin-Ver-Funcionarios" })
	public String cad_O_Func(Model model) {

		String nomeDaPagina = null;
		estado = -5;
		if (entrarNoSistema.podeAbrirAPagina(senha)) {

			// if(acesso.bloqueandoRequisicoes(TechShine_Controller.request, request)) {

			Pesquisar_SQL p = new Pesquisar_SQL();

			int qalunos = p.pesquisarUmConteudo_Numa_Linha_Int(this.BD, "infoescola", "qalunos", "id", "", 1);
			int qfunc = p.pesquisarUmConteudo_Numa_Linha_Int(this.BD, "infoescola", "qfunc", "id", "", 1);
			int QCurso = p.pesquisarUmConteudo_Numa_Linha_Int(this.BD, "infoescola", "QCurso", "id", "", 1);
			int renda = p.pesquisarUmConteudo_Numa_Linha_Int(this.BD, "adminfinanca", "renda", "bi_admin", this.bi, 0);

			Salvar_SQL s = new Salvar_SQL();
			int despeza = 0;
			String mes = s.mesActual(this.BD);

			if (this.quem_E_O_func.equalsIgnoreCase("pca")) {
				renda = p.pesquisarUmConteudo_Numa_Linha_Int(this.BD, "adminfinanca", "renda", "bi_admin", this.bi, 0);

				ArrayList<Integer> despezas = p.pesquisarTudoEmInt(this.BD, "adminfinanca", "despeza");

				if (despezas.size() > 0) {

					despeza = p.pesquisarTudoEmInt(this.BD, "adminfinanca", "despeza").get(0);
				}

				model.addAttribute("renda", renda + ",00 Kz");
			} else {

				renda = p.pesquisarUmConteudo_Numa_Linha_Int(this.BD, this.quem_E_O_func + "_financa", mes, "id", "",
						this.id);

				if (renda == 0) {
					model.addAttribute("renda", "Irregular");
				} else {
					model.addAttribute("renda", "Regular");
				}

			}

			model.addAttribute("qalunos", qalunos);
			model.addAttribute("qfunc", qfunc);
			model.addAttribute("qCurso", QCurso);
			model.addAttribute("despeza", despeza);

			model.addAttribute("nome", configurarNome);
			model.addAttribute("escola", configurarEscola);

			nomeDaPagina = "TechShine/Admin/ver_Funcionarios";
			this.pagina = nomeDaPagina;
			// }

		} else {

			estado = 0;
			nomeDaPagina = "redirect:login";
			this.pagina = nomeDaPagina;
		}

		return this.pagina;
	}

	/*
	 * Mostrar Funcionários,Finança,Alunos e Cursos Cadastrados Pelo Admin
	 * 
	 */

	@GetMapping({ "/Admin-Ver-Func-Cord", "/Admin-Ver-Func-Prof", "/Admin-Ver-Func-Sec", "/Admin-Ver-Func-Limp",
			"/Admin-Ver-Func-Segur", "/Admin-Ver-Func-Tesou", "/Admin-Func-info", "/Admin-Pagar",
			"/Admin-Cadastrar_Prof", "/Admin-Ver-Func-DP", "/Admin-Ver-Func-DG", "/Admin-Ver-Func-DA" })
	public String admin_Funcionarios(Model model, HttpServletRequest request) {

		Pesquisar_SQL p = new Pesquisar_SQL();
		ArrayList<String> funcionarios = p.listarFuncionarios();
		ArrayList<String> niveis = p.pesquisarTudoEmString(this.BD, "infoescola", "niveis");
		ArrayList<Curso> cursos = p.Listar_Cursos(this.BD);
		Tabela_Actualizar_SQL ta = new Tabela_Actualizar_SQL();

		String requisicao;
		String nomeDaPagina = null;
		estado = -5;
		requisicao = request.getRequestURI();

		if (requisicao.equalsIgnoreCase("/Admin-Ver-Func-Cord")) {

			if (entrarNoSistema.podeAbrirAPagina(senha)) {

				// if(acesso.bloqueandoRequisicoes(TechShine_Controller.request, request)) {

				this.retorno ="Admin-Ver-Func-Cord";
				ArrayList<String> profs = p.pesquisarTudoEmString(this.BD, "coord_dadospessoais", "nomes");
				ArrayList<String> bis = p.pesquisarTudoEmString(this.BD, "coord_dadospessoais", "bi");
				ArrayList<Integer> telefones = p.pesquisarTudoEmInt(this.BD, "coord_dadospessoais", "telefone");
				ArrayList<Integer> ids = p.pesquisarTudoEmInt(this.BD, "coord_dadospessoais", "id");
				ArrayList<String> contratos = p.pesquisarTudoEmString(this.BD, "coord_acesso", "contrato");

				int qalunos = p.pesquisarUmConteudo_Numa_Linha_Int(this.BD, "infoescola", "qalunos", "id", "", 1);
				int qfunc = p.pesquisarUmConteudo_Numa_Linha_Int(this.BD, "infoescola", "qfunc", "id", "", 1);
				int QCurso = p.pesquisarUmConteudo_Numa_Linha_Int(this.BD, "infoescola", "QCurso", "id", "", 1);
				int renda = p.pesquisarUmConteudo_Numa_Linha_Int(this.BD, "adminfinanca", "renda", "bi_admin", this.bi,
						0);

				ArrayList<Funcionario> funcs = new ArrayList<>();
				for (int i = 0; i < profs.size(); i++) {

					Funcionario func = new Funcionario();
					func.setNome(profs.get(i));
					func.setTelefone(telefones.get(i));
					func.setId(ids.get(i));
					func.setBi(bis.get(i));

					if ((contratos.size() > 0) && (i < contratos.size() - 1)) {

						func.setContrato2(contratos.get(i));
					} else {

						func.setContrato2("Nenhum");
					}
					funcs.add(func);

				}

				model.addAttribute("coords", funcs);
				model.addAttribute("qalunos", qalunos);
				model.addAttribute("qfunc", qfunc);
				model.addAttribute("qCurso", QCurso);
				model.addAttribute("renda", renda);

				model.addAttribute("nome", configurarNome);
				model.addAttribute("escola", configurarEscola);
				nomeDaPagina = "TechShine/Admin/verCord";
				this.pagina = nomeDaPagina;
				// }

			} else {

				estado = 0;
				nomeDaPagina = "redirect:login";
				this.pagina = nomeDaPagina;
			}
			return this.pagina;

		} else if (requisicao.equalsIgnoreCase("/Admin-Ver-Func-Prof")) {

			if (entrarNoSistema.podeAbrirAPagina(senha)) {

				// if(acesso.bloqueandoRequisicoes(TechShine_Controller.request, request)) {

				this.retorno ="Admin-Ver-Func-Prof";
				
				ArrayList<String> profs = p.pesquisarTudoEmString(this.BD, "professor_dadospessoais", "nomes");
				ArrayList<Integer> telefones = p.pesquisarTudoEmInt(this.BD, "professor_dadospessoais", "telefone");
				ArrayList<Integer> ids = p.pesquisarTudoEmInt(this.BD, "professor_dadospessoais", "id");
				ArrayList<String> contratos = p.pesquisarTudoEmString(this.BD, "professor_acesso", "contrato");

				ArrayList<String> bis = p.pesquisarTudoEmString(this.BD, "secretaria_dadospessoais", "bi");
				ArrayList<Funcionario> funcs = new ArrayList<>();
				for (int i = 0; i < profs.size(); i++) {

					Funcionario func = new Funcionario();
					func.setNome(profs.get(i));
					func.setTelefone(telefones.get(i));
					func.setId(ids.get(i));
					func.setBi(bis.get(i));
					

					if ((contratos.size() > 0) && (i < contratos.size() - 1)) {

						func.setContrato2(contratos.get(i));
					} else {

						func.setContrato2("Nenhum");
					}

					funcs.add(func);

				}

				int qalunos = p.pesquisarUmConteudo_Numa_Linha_Int(this.BD, "infoescola", "qalunos", "id", "", 1);
				int qfunc = p.pesquisarUmConteudo_Numa_Linha_Int(this.BD, "infoescola", "qfunc", "id", "", 1);
				int QCurso = p.pesquisarUmConteudo_Numa_Linha_Int(this.BD, "infoescola", "QCurso", "id", "", 1);
				int renda = p.pesquisarUmConteudo_Numa_Linha_Int(this.BD, "adminfinanca", "renda", "bi_admin", this.bi,
						0);

				model.addAttribute("qalunos", qalunos);
				model.addAttribute("qfunc", qfunc);
				model.addAttribute("qCurso", QCurso);

				Salvar_SQL s = new Salvar_SQL();
				int despeza = 0;
				String mes = s.mesActual(this.BD);

				if (this.quem_E_O_func.equalsIgnoreCase("pca")) {
					renda = p.pesquisarUmConteudo_Numa_Linha_Int(this.BD, "adminfinanca", "renda", "bi_admin", this.bi,
							0);

					ArrayList<Integer> despezas = p.pesquisarTudoEmInt(this.BD, "adminfinanca", "despeza");

					if (despezas.size() > 0) {

						despeza = p.pesquisarTudoEmInt(this.BD, "adminfinanca", "despeza").get(0);
					}

					model.addAttribute("renda", renda + ",00 Kz");
				} else {

					renda = p.pesquisarUmConteudo_Numa_Linha_Int(this.BD, this.quem_E_O_func + "_financa", mes, "id",
							"", this.id);

					if (renda == 0) {
						model.addAttribute("renda", "Irregular");
					} else {
						model.addAttribute("renda", "Regular");
					}

				}

				model.addAttribute("profs", funcs);

				model.addAttribute("nome", configurarNome);
				model.addAttribute("escola", configurarEscola);

				nomeDaPagina = "TechShine/Admin/verProfs";
				this.pagina = nomeDaPagina;

				// }

			} else {

				estado = 0;
				nomeDaPagina = "redirect:login";
				this.pagina = nomeDaPagina;
			}
			return this.pagina;

		} else if (requisicao.equalsIgnoreCase("/Admin-Ver-Func-Sec")) {

			if (entrarNoSistema.podeAbrirAPagina(senha)) {

				// if(acesso.bloqueandoRequisicoes(TechShine_Controller.request, request)) {

				
				this.retorno ="Admin-Ver-Func-Sec";
				
				ArrayList<String> profs = p.pesquisarTudoEmString(this.BD, "secretaria_dadospessoais", "nomes");
				ArrayList<Integer> telefones = p.pesquisarTudoEmInt(this.BD, "secretaria_dadospessoais", "telefone");
				ArrayList<Integer> ids = p.pesquisarTudoEmInt(this.BD, "secretaria_dadospessoais", "id");
				ArrayList<String> contratos = p.pesquisarTudoEmString(this.BD, "secretaria_acesso", "contrato");

				ArrayList<String> bis = p.pesquisarTudoEmString(this.BD, "secretaria_dadospessoais", "bi");
				int qalunos = p.pesquisarUmConteudo_Numa_Linha_Int(this.BD, "infoescola", "qalunos", "id", "", 1);
				int qfunc = p.pesquisarUmConteudo_Numa_Linha_Int(this.BD, "infoescola", "qfunc", "id", "", 1);
				int QCurso = p.pesquisarUmConteudo_Numa_Linha_Int(this.BD, "infoescola", "QCurso", "id", "", 1);
				int renda = p.pesquisarUmConteudo_Numa_Linha_Int(this.BD, "adminfinanca", "renda", "bi_admin", this.bi,
						0);

				model.addAttribute("qalunos", qalunos);
				model.addAttribute("qfunc", qfunc);
				model.addAttribute("qCurso", QCurso);

				Salvar_SQL s = new Salvar_SQL();
				int despeza = 0;
				String mes = s.mesActual(this.BD);

				if (this.quem_E_O_func.equalsIgnoreCase("pca")) {
					renda = p.pesquisarUmConteudo_Numa_Linha_Int(this.BD, "adminfinanca", "renda", "bi_admin", this.bi,
							0);

					ArrayList<Integer> despezas = p.pesquisarTudoEmInt(this.BD, "adminfinanca", "despeza");

					if (despezas.size() > 0) {

						despeza = p.pesquisarTudoEmInt(this.BD, "adminfinanca", "despeza").get(0);
					}

					model.addAttribute("renda", renda + ",00 Kz");
				} else {

					renda = p.pesquisarUmConteudo_Numa_Linha_Int(this.BD, this.quem_E_O_func + "_financa", mes, "id",
							"", this.id);

					if (renda == 0) {
						model.addAttribute("renda", "Irregular");
					} else {
						model.addAttribute("renda", "Regular");
					}

				}

				ArrayList<Funcionario> funcs = new ArrayList<>();
				for (int i = 0; i < profs.size(); i++) {

					Funcionario func = new Funcionario();
					func.setNome(profs.get(i));
					func.setTelefone(telefones.get(i));
					func.setId(ids.get(i));
					func.setBi(bis.get(i));

					if ((contratos.size() > 0) && (i < contratos.size() - 1)) {

						func.setContrato2(contratos.get(i));
					} else {

						func.setContrato2("Nenhum");
					}

					funcs.add(func);

				}

				model.addAttribute("secretarias", funcs);

				model.addAttribute("nome", configurarNome);
				model.addAttribute("escola", configurarEscola);

				nomeDaPagina = "TechShine/Admin/verSec";
				this.pagina = nomeDaPagina;

				// }

			} else {

				estado = 0;
				nomeDaPagina = "redirect:login";
				this.pagina = nomeDaPagina;
			}
			return this.pagina;

		} else if (requisicao.equalsIgnoreCase("/Admin-Ver-Func-DP")) {

			if (entrarNoSistema.podeAbrirAPagina(senha)) {

				// if(acesso.bloqueandoRequisicoes(TechShine_Controller.request, request)) {

				this.retorno ="Admin-Ver-Func-DP";
				
				ArrayList<String> profs = p.pesquisarTudoEmString(this.BD, "dp_dadospessoais", "nomes");
				ArrayList<Integer> telefones = p.pesquisarTudoEmInt(this.BD, "dp_dadospessoais", "telefone");
				ArrayList<Integer> ids = p.pesquisarTudoEmInt(this.BD, "dp_dadospessoais", "id");
				ArrayList<String> contratos = p.pesquisarTudoEmString(this.BD, "dp_acesso", "contrato");

				ArrayList<String> bis = p.pesquisarTudoEmString(this.BD, "secretaria_dadospessoais", "bi");
				int qalunos = p.pesquisarUmConteudo_Numa_Linha_Int(this.BD, "infoescola", "qalunos", "id", "", 1);
				int qfunc = p.pesquisarUmConteudo_Numa_Linha_Int(this.BD, "infoescola", "qfunc", "id", "", 1);
				int QCurso = p.pesquisarUmConteudo_Numa_Linha_Int(this.BD, "infoescola", "QCurso", "id", "", 1);
				int renda = p.pesquisarUmConteudo_Numa_Linha_Int(this.BD, "adminfinanca", "renda", "bi_admin", this.bi,
						0);

				model.addAttribute("qalunos", qalunos);
				model.addAttribute("qfunc", qfunc);
				model.addAttribute("qCurso", QCurso);

				Salvar_SQL s = new Salvar_SQL();
				int despeza = 0;
				String mes = s.mesActual(this.BD);

				if (this.quem_E_O_func.equalsIgnoreCase("pca")) {
					renda = p.pesquisarUmConteudo_Numa_Linha_Int(this.BD, "adminfinanca", "renda", "bi_admin", this.bi,
							0);

					ArrayList<Integer> despezas = p.pesquisarTudoEmInt(this.BD, "adminfinanca", "despeza");

					if (despezas.size() > 0) {

						despeza = p.pesquisarTudoEmInt(this.BD, "adminfinanca", "despeza").get(0);
					}

					model.addAttribute("renda", renda + ",00 Kz");
				} else {

					renda = p.pesquisarUmConteudo_Numa_Linha_Int(this.BD, this.quem_E_O_func + "_financa", mes, "id",
							"", this.id);

					if (renda == 0) {
						model.addAttribute("renda", "Irregular");
					} else {
						model.addAttribute("renda", "Regular");
					}

				}

				ArrayList<Funcionario> funcs = new ArrayList<>();
				for (int i = 0; i < profs.size(); i++) {

					Funcionario func = new Funcionario();
					func.setNome(profs.get(i));
					func.setTelefone(telefones.get(i));
					func.setId(ids.get(i));
					func.setBi(bis.get(i));

					if ((contratos.size() > 0) && (i < contratos.size() - 1)) {

						func.setContrato2(contratos.get(i));
					} else {

						func.setContrato2("Nenhum");
					}
					funcs.add(func);

				}

				model.addAttribute("dps", funcs);

				model.addAttribute("nome", configurarNome);
				model.addAttribute("escola", configurarEscola);

				nomeDaPagina = "TechShine/Admin/verDP";
				this.pagina = nomeDaPagina;

				// }

			} else {

				estado = 0;
				nomeDaPagina = "redirect:login";
				this.pagina = nomeDaPagina;
			}
			return this.pagina;

		} else if (requisicao.equalsIgnoreCase("/Admin-Ver-Func-DG")) {

			if (entrarNoSistema.podeAbrirAPagina(senha)) {

				// if(acesso.bloqueandoRequisicoes(TechShine_Controller.request, request)) {

				this.retorno ="Admin-Ver-Func-DG";
				
				
				ArrayList<String> profs = p.pesquisarTudoEmString(this.BD, "dg_dadospessoais", "nomes");
				ArrayList<Integer> telefones = p.pesquisarTudoEmInt(this.BD, "dg_dadospessoais", "telefone");
				ArrayList<Integer> ids = p.pesquisarTudoEmInt(this.BD, "dg_dadospessoais", "id");
				ArrayList<String> contratos = p.pesquisarTudoEmString(this.BD, "dg_acesso", "contrato");
 
				ArrayList<String> bis = p.pesquisarTudoEmString(this.BD, "dg_dadospessoais", "bi");
				int qalunos = p.pesquisarUmConteudo_Numa_Linha_Int(this.BD, "infoescola", "qalunos", "id", "", 1);
				int qfunc = p.pesquisarUmConteudo_Numa_Linha_Int(this.BD, "infoescola", "qfunc", "id", "", 1);
				int QCurso = p.pesquisarUmConteudo_Numa_Linha_Int(this.BD, "infoescola", "QCurso", "id", "", 1);
				int renda = p.pesquisarUmConteudo_Numa_Linha_Int(this.BD, "adminfinanca", "renda", "bi_admin", this.bi,
						0);

				model.addAttribute("qalunos", qalunos);
				model.addAttribute("qfunc", qfunc);
				model.addAttribute("qCurso", QCurso);

				Salvar_SQL s = new Salvar_SQL();
				int despeza = 0;
				String mes = s.mesActual(this.BD);

				if (this.quem_E_O_func.equalsIgnoreCase("pca")) {
					renda = p.pesquisarUmConteudo_Numa_Linha_Int(this.BD, "adminfinanca", "renda", "bi_admin", this.bi,
							0);

					ArrayList<Integer> despezas = p.pesquisarTudoEmInt(this.BD, "adminfinanca", "despeza");

					if (despezas.size() > 0) {

						despeza = p.pesquisarTudoEmInt(this.BD, "adminfinanca", "despeza").get(0);
					}

					model.addAttribute("renda", renda + ",00 Kz");
				} else {

					renda = p.pesquisarUmConteudo_Numa_Linha_Int(this.BD, this.quem_E_O_func + "_financa", mes, "id",
							"", this.id);

					if (renda == 0) {
						model.addAttribute("renda", "Irregular");
					} else {
						model.addAttribute("renda", "Regular");
					}

				}

				ArrayList<Funcionario> funcs = new ArrayList<>();
				for (int i = 0; i < profs.size(); i++) {

					Funcionario func = new Funcionario();
					func.setNome(profs.get(i));
					func.setTelefone(telefones.get(i));
					func.setId(ids.get(i));
					func.setBi(bis.get(i));

					if ((contratos.size() > 0) && (i < contratos.size() - 1)) {

						func.setContrato2(contratos.get(i));
					} else {

						func.setContrato2("Nenhum");
					}
					funcs.add(func);

				}

				model.addAttribute("dgs", funcs);

				model.addAttribute("nome", configurarNome);
				model.addAttribute("escola", configurarEscola);

				nomeDaPagina = "TechShine/Admin/verDG";
				this.pagina = nomeDaPagina;

				// }

			} else {

				estado = 0;
				nomeDaPagina = "redirect:login";
				this.pagina = nomeDaPagina;
			}
			return this.pagina;

		} else if (requisicao.equalsIgnoreCase("/Admin-Ver-Func-DA")) {

			if (entrarNoSistema.podeAbrirAPagina(senha)) {

				// if(acesso.bloqueandoRequisicoes(TechShine_Controller.request, request)) {

				this.retorno ="Admin-Ver-Func-DA";
				
				ArrayList<String> profs = p.pesquisarTudoEmString(this.BD, "da_dadospessoais", "nomes");
				ArrayList<Integer> telefones = p.pesquisarTudoEmInt(this.BD, "da_dadospessoais", "telefone");
				ArrayList<Integer> ids = p.pesquisarTudoEmInt(this.BD, "da_dadospessoais", "id");
				ArrayList<String> contratos = p.pesquisarTudoEmString(this.BD, "da_acesso", "contrato");

				ArrayList<String> bis = p.pesquisarTudoEmString(this.BD, "secretaria_dadospessoais", "bi");
				int qalunos = p.pesquisarUmConteudo_Numa_Linha_Int(this.BD, "infoescola", "qalunos", "id", "", 1);
				int qfunc = p.pesquisarUmConteudo_Numa_Linha_Int(this.BD, "infoescola", "qfunc", "id", "", 1);
				int QCurso = p.pesquisarUmConteudo_Numa_Linha_Int(this.BD, "infoescola", "QCurso", "id", "", 1);
				int renda = p.pesquisarUmConteudo_Numa_Linha_Int(this.BD, "adminfinanca", "renda", "bi_admin", this.bi,
						0);

				model.addAttribute("qalunos", qalunos);
				model.addAttribute("qfunc", qfunc);
				model.addAttribute("qCurso", QCurso);

				Salvar_SQL s = new Salvar_SQL();
				int despeza = 0;
				String mes = s.mesActual(this.BD);

				if (this.quem_E_O_func.equalsIgnoreCase("pca")) {
					renda = p.pesquisarUmConteudo_Numa_Linha_Int(this.BD, "adminfinanca", "renda", "bi_admin", this.bi,
							0);

					ArrayList<Integer> despezas = p.pesquisarTudoEmInt(this.BD, "adminfinanca", "despeza");

					if (despezas.size() > 0) {

						despeza = p.pesquisarTudoEmInt(this.BD, "adminfinanca", "despeza").get(0);
					}

					model.addAttribute("renda", renda + ",00 Kz");
				} else {

					renda = p.pesquisarUmConteudo_Numa_Linha_Int(this.BD, this.quem_E_O_func + "_financa", mes, "id",
							"", this.id);

					if (renda == 0) {
						model.addAttribute("renda", "Irregular");
					} else {
						model.addAttribute("renda", "Regular");
					}

				}

				ArrayList<Funcionario> funcs = new ArrayList<>();
				for (int i = 0; i < profs.size(); i++) {

					Funcionario func = new Funcionario();
					func.setNome(profs.get(i));
					func.setTelefone(telefones.get(i));
					func.setId(ids.get(i));
					func.setBi(bis.get(i));

					if ((contratos.size() > 0) && (i < contratos.size() - 1)) {

						func.setContrato2(contratos.get(i));
					} else {

						func.setContrato2("Nenhum");
					}
					funcs.add(func);

				}

				model.addAttribute("das", funcs);

				model.addAttribute("nome", configurarNome);
				model.addAttribute("escola", configurarEscola);

				nomeDaPagina = "TechShine/Admin/verDA";
				this.pagina = nomeDaPagina;

				// }

			} else {

				estado = 0;
				nomeDaPagina = "redirect:login";
				this.pagina = nomeDaPagina;
			}
			return this.pagina;

		}

		else if (requisicao.equalsIgnoreCase("/Admin-Ver-Func-Limp")) {

			if (entrarNoSistema.podeAbrirAPagina(senha)) {

				// if(acesso.bloqueandoRequisicoes(TechShine_Controller.request, request)) {

				
				this.retorno ="Admin-Ver-Func-Limp";
				
				
				ArrayList<String> profs = p.pesquisarTudoEmString(this.BD, "limpeza_dadospessoais", "nomes");
				ArrayList<Integer> telefones = p.pesquisarTudoEmInt(this.BD, "limpeza_dadospessoais", "telefone");
				ArrayList<Integer> ids = p.pesquisarTudoEmInt(this.BD, "limpeza_dadospessoais", "id");

				ArrayList<String> bis = p.pesquisarTudoEmString(this.BD, "secretaria_dadospessoais", "bi");
				int qalunos = p.pesquisarUmConteudo_Numa_Linha_Int(this.BD, "infoescola", "qalunos", "id", "", 1);
				int qfunc = p.pesquisarUmConteudo_Numa_Linha_Int(this.BD, "infoescola", "qfunc", "id", "", 1);
				int QCurso = p.pesquisarUmConteudo_Numa_Linha_Int(this.BD, "infoescola", "QCurso", "id", "", 1);
				int renda = p.pesquisarUmConteudo_Numa_Linha_Int(this.BD, "adminfinanca", "renda", "bi_admin", this.bi,
						0);

				model.addAttribute("qalunos", qalunos);
				model.addAttribute("qfunc", qfunc);
				model.addAttribute("qCurso", QCurso);

				Salvar_SQL s = new Salvar_SQL();
				int despeza = 0;
				String mes = s.mesActual(this.BD);

				if (this.quem_E_O_func.equalsIgnoreCase("pca")) {
					renda = p.pesquisarUmConteudo_Numa_Linha_Int(this.BD, "adminfinanca", "renda", "bi_admin", this.bi,
							0);

					ArrayList<Integer> despezas = p.pesquisarTudoEmInt(this.BD, "adminfinanca", "despeza");

					if (despezas.size() > 0) {

						despeza = p.pesquisarTudoEmInt(this.BD, "adminfinanca", "despeza").get(0);
					}

					model.addAttribute("renda", renda + ",00 Kz");
				} else {

					renda = p.pesquisarUmConteudo_Numa_Linha_Int(this.BD, this.quem_E_O_func + "_financa", mes, "id",
							"", this.id);

					if (renda == 0) {
						model.addAttribute("renda", "Irregular");
					} else {
						model.addAttribute("renda", "Regular");
					}

				}

				ArrayList<Funcionario> funcs = new ArrayList<>();
				for (int i = 0; i < profs.size(); i++) {

					Funcionario func = new Funcionario();
					func.setNome(profs.get(i));
					func.setTelefone(telefones.get(i));
					func.setId(ids.get(i));
					func.setBi(bis.get(i));

					funcs.add(func);

				}

				model.addAttribute("limpezas", funcs);

				model.addAttribute("nome", configurarNome);
				model.addAttribute("escola", configurarEscola);
				nomeDaPagina = "TechShine/Admin/verLimp";
				this.pagina = nomeDaPagina;
				// }

			} else {

				estado = 0;
				nomeDaPagina = "redirect:login";
				this.pagina = nomeDaPagina;
			}
			return this.pagina;
		} else if (requisicao.equalsIgnoreCase("/Admin-Ver-Func-Segur")) {

			if (entrarNoSistema.podeAbrirAPagina(senha)) {

				// if(acesso.bloqueandoRequisicoes(TechShine_Controller.request, request)) {

				
				this.retorno ="Admin-Ver-Func-Segur";
				
				
				ArrayList<String> profs = p.pesquisarTudoEmString(this.BD, "seguranca_dadospessoais", "nomes");
				ArrayList<Integer> telefones = p.pesquisarTudoEmInt(this.BD, "seguranca_dadospessoais", "telefone");
				ArrayList<Integer> ids = p.pesquisarTudoEmInt(this.BD, "seguranca_dadospessoais", "id");

				ArrayList<String> bis = p.pesquisarTudoEmString(this.BD, "secretaria_dadospessoais", "bi");
				int qalunos = p.pesquisarUmConteudo_Numa_Linha_Int(this.BD, "infoescola", "qalunos", "id", "", 1);
				int qfunc = p.pesquisarUmConteudo_Numa_Linha_Int(this.BD, "infoescola", "qfunc", "id", "", 1);
				int QCurso = p.pesquisarUmConteudo_Numa_Linha_Int(this.BD, "infoescola", "QCurso", "id", "", 1);
				int renda = p.pesquisarUmConteudo_Numa_Linha_Int(this.BD, "adminfinanca", "renda", "bi_admin", this.bi,
						0);

				model.addAttribute("qalunos", qalunos);
				model.addAttribute("qfunc", qfunc);
				model.addAttribute("qCurso", QCurso);

				Salvar_SQL s = new Salvar_SQL();
				int despeza = 0;
				String mes = s.mesActual(this.BD);

				if (this.quem_E_O_func.equalsIgnoreCase("pca")) {
					renda = p.pesquisarUmConteudo_Numa_Linha_Int(this.BD, "adminfinanca", "renda", "bi_admin", this.bi,
							0);

					ArrayList<Integer> despezas = p.pesquisarTudoEmInt(this.BD, "adminfinanca", "despeza");

					if (despezas.size() > 0) {

						despeza = p.pesquisarTudoEmInt(this.BD, "adminfinanca", "despeza").get(0);
					}

					model.addAttribute("renda", renda + ",00 Kz");
				} else {

					renda = p.pesquisarUmConteudo_Numa_Linha_Int(this.BD, this.quem_E_O_func + "_financa", mes, "id",
							"", this.id);

					if (renda == 0) {
						model.addAttribute("renda", "Irregular");
					} else {
						model.addAttribute("renda", "Regular");
					}

				}

				ArrayList<Funcionario> funcs = new ArrayList<>();
				for (int i = 0; i < profs.size(); i++) {

					Funcionario func = new Funcionario();
					func.setNome(profs.get(i));
					func.setTelefone(telefones.get(i));
					func.setId(ids.get(i));
					func.setBi(bis.get(i));

					funcs.add(func);

				}

				model.addAttribute("segurancas", funcs);

				model.addAttribute("nome", configurarNome);
				model.addAttribute("escola", configurarEscola);
				nomeDaPagina = "TechShine/Admin/verSeguranca";
				this.pagina = nomeDaPagina;
				// }

			} else {

				estado = 0;
				nomeDaPagina = "redirect:login";
				this.pagina = nomeDaPagina;
			}
			return this.pagina;

		} else if (requisicao.equalsIgnoreCase("/Admin-Ver-Func-Tesou")) {

			if (entrarNoSistema.podeAbrirAPagina(senha)) {

				// if(acesso.bloqueandoRequisicoes(TechShine_Controller.request, request)) {

				
				this.retorno ="Admin-Ver-Func-Tesou";
				
				
				ArrayList<String> profs = p.pesquisarTudoEmString(this.BD, "tesouraria_dadospessoais", "nomes");
				ArrayList<Integer> telefones = p.pesquisarTudoEmInt(this.BD, "tesouraria_dadospessoais", "telefone");
				ArrayList<Integer> ids = p.pesquisarTudoEmInt(this.BD, "tesouraria_dadospessoais", "id");
				ArrayList<String> contratos = p.pesquisarTudoEmString(this.BD, "tesouraria_acesso", "contrato");
				ArrayList<String> bis = p.pesquisarTudoEmString(this.BD, "secretaria_dadospessoais", "bi");

				int qalunos = p.pesquisarUmConteudo_Numa_Linha_Int(this.BD, "infoescola", "qalunos", "id", "", 1);
				int qfunc = p.pesquisarUmConteudo_Numa_Linha_Int(this.BD, "infoescola", "qfunc", "id", "", 1);
				int QCurso = p.pesquisarUmConteudo_Numa_Linha_Int(this.BD, "infoescola", "QCurso", "id", "", 1);
				int renda = p.pesquisarUmConteudo_Numa_Linha_Int(this.BD, "adminfinanca", "renda", "bi_admin", this.bi,
						0);

				model.addAttribute("qalunos", qalunos);
				model.addAttribute("qfunc", qfunc);
				model.addAttribute("qCurso", QCurso);

				Salvar_SQL s = new Salvar_SQL();
				int despeza = 0;
				String mes = s.mesActual(this.BD);

				if (this.quem_E_O_func.equalsIgnoreCase("pca")) {
					renda = p.pesquisarUmConteudo_Numa_Linha_Int(this.BD, "adminfinanca", "renda", "bi_admin", this.bi,
							0);

					ArrayList<Integer> despezas = p.pesquisarTudoEmInt(this.BD, "adminfinanca", "despeza");

					if (despezas.size() > 0) {

						despeza = p.pesquisarTudoEmInt(this.BD, "adminfinanca", "despeza").get(0);
					}

					model.addAttribute("renda", renda + ",00 Kz");
				} else {

					renda = p.pesquisarUmConteudo_Numa_Linha_Int(this.BD, this.quem_E_O_func + "_financa", mes, "id",
							"", this.id);

					if (renda == 0) {
						model.addAttribute("renda", "Irregular");
					} else {
						model.addAttribute("renda", "Regular");
					}

				}

				ArrayList<Funcionario> funcs = new ArrayList<>();
				for (int i = 0; i < profs.size(); i++) {

					Funcionario func = new Funcionario();
					func.setNome(profs.get(i));
					func.setTelefone(telefones.get(i));
					func.setId(ids.get(i));
					func.setBi(bis.get(i));

					if ((contratos.size() > 0) && (i < contratos.size() - 1)) {

						func.setContrato2(contratos.get(i));
					} else {

						func.setContrato2("Nenhum");
					}
					funcs.add(func);

				}

				model.addAttribute("tesourarias", funcs);

				model.addAttribute("nome", configurarNome);
				model.addAttribute("escola", configurarEscola);
				nomeDaPagina = "TechShine/Admin/verSeguranca";
				this.pagina = nomeDaPagina;
				// }

			} else {

				estado = 0;
				nomeDaPagina = "redirect:login";
				this.pagina = nomeDaPagina;
			}
			return this.pagina;
		} else if (requisicao.equalsIgnoreCase("/Admin-Func-info")) {

			if (entrarNoSistema.podeAbrirAPagina(senha)) {

				// if(acesso.bloqueandoRequisicoes(TechShine_Controller.request, request)) {

				ArrayList<String> cursosAUsar = new ArrayList<>();
				String conteudo;

				ArrayList<ArrayList<String>> todasDesciplinas = new ArrayList<>();

				for (Curso c : cursos) {

					conteudo = c.getNome();
					cursosAUsar.add(conteudo);
					c.setNome(conteudo);
				}

				for (String cur : cursosAUsar) {
					ArrayList<String> desciplinas = p.pesquisarTudoEmString(this.BD, "cursos_disciplinas", cur);
					todasDesciplinas.add(desciplinas);
				}

				model.addAttribute("resultado", "Funcionario Inserido Com Sucesso !");

				ArrayList<Funcionario> funcs = new ArrayList<>();
				funcs.add(this.func);

				int qalunos = p.pesquisarUmConteudo_Numa_Linha_Int(this.BD, "infoescola", "qalunos", "id", "", 1);
				int qfunc = p.pesquisarUmConteudo_Numa_Linha_Int(this.BD, "infoescola", "qfunc", "id", "", 1);
				int QCurso = p.pesquisarUmConteudo_Numa_Linha_Int(this.BD, "infoescola", "QCurso", "id", "", 1);
				int renda = p.pesquisarUmConteudo_Numa_Linha_Int(this.BD, "adminfinanca", "renda", "bi_admin", this.bi,
						0);

				model.addAttribute("qalunos", qalunos);
				model.addAttribute("qfunc", qfunc);
				model.addAttribute("qCurso", QCurso);

				Salvar_SQL s = new Salvar_SQL();
				int despeza = 0;
				String mes = s.mesActual(this.BD);

				if (this.quem_E_O_func.equalsIgnoreCase("pca")) {
					renda = p.pesquisarUmConteudo_Numa_Linha_Int(this.BD, "adminfinanca", "renda", "bi_admin", this.bi,
							0);

					ArrayList<Integer> despezas = p.pesquisarTudoEmInt(this.BD, "adminfinanca", "despeza");

					if (despezas.size() > 0) {

						despeza = p.pesquisarTudoEmInt(this.BD, "adminfinanca", "despeza").get(0);
					}

					model.addAttribute("renda", renda + ",00 Kz");
				} else {

					renda = p.pesquisarUmConteudo_Numa_Linha_Int(this.BD, this.quem_E_O_func + "_financa", mes, "id",
							"", this.id);

					if (renda == 0) {
						model.addAttribute("renda", "Irregular");
					} else {
						model.addAttribute("renda", "Regular");
					}

				}

				model.addAttribute("funcionario", funcs);
				model.addAttribute("funcionarios", funcionarios);
				model.addAttribute("niveis", niveis);
				model.addAttribute("desciplinas", todasDesciplinas);

				model.addAttribute("nome", configurarNome);
				model.addAttribute("escola", configurarEscola);
				model.addAttribute("cursos", cursos);

				nomeDaPagina = "TechShine/Admin/informacaoDoFunc";
				this.pagina = nomeDaPagina;

				// }

			} else {

				estado = 0;
				nomeDaPagina = "redirect:login";
				this.pagina = nomeDaPagina;
			}
			return this.pagina;
		} else if (requisicao.equalsIgnoreCase("/Admin-Cadastrar_Prof")) {

			if (entrarNoSistema.podeAbrirAPagina(senha)) {

				// if(acesso.bloqueandoRequisicoes(TechShine_Controller.request, request)) {

				String curso = this.func.getCurso();
				this.func.setCurso(curso);
				String desc;

				ArrayList<String> desciplinas = p.pesquisarTudoEmString(this.BD, "cursos_disciplinas", curso);
				ArrayList<String> desciplinasCertas = new ArrayList<>();
				for (String desciplina : desciplinas) {

					desc = ta.tirarCaracteres_RetornandoA_A_Lista(desciplina).get(2);
					desciplinasCertas.add(desc);
				}

				String turno = this.func.getTurno();
				this.func.setTurno(turno);

				String nivel = this.func.getNivel();
				this.func.setNivel(nivel);

				ArrayList<String> turmas = p.Listar_TurmasDoCurso(this.BD, turno, nivel, curso);

				if (turmas == null) {

					model.addAttribute("resultado",
							"Não Existe Turmas Correspondente ao curso, nivel e Turno Selecionado !");
					this.pagina = "redirect:Cadastrar-Sistema-5b";
				} else {

					for (String c : turmas) {

						System.out.println("Turma: " + c);
					}

					model.addAttribute("resultado", "Funcionario Inserido Com Sucesso !");

					ArrayList<Funcionario> funcs = new ArrayList<>();
					funcs.add(this.func);

					int renda = 0;

					Salvar_SQL s = new Salvar_SQL();
					int despeza = 0;
					String mes = s.mesActual(this.BD);

					if (this.quem_E_O_func.equalsIgnoreCase("pca")) {
						renda = p.pesquisarUmConteudo_Numa_Linha_Int(this.BD, "adminfinanca", "renda", "bi_admin",
								this.bi, 0);

						ArrayList<Integer> despezas = p.pesquisarTudoEmInt(this.BD, "adminfinanca", "despeza");

						if (despezas.size() > 0) {

							despeza = p.pesquisarTudoEmInt(this.BD, "adminfinanca", "despeza").get(0);
						}

						model.addAttribute("renda", renda + ",00 Kz");
					} else {

						renda = p.pesquisarUmConteudo_Numa_Linha_Int(this.BD, this.quem_E_O_func + "_financa", mes,
								"id", "", this.id);

						if (renda == 0) {
							model.addAttribute("renda", "Irregular");
						} else {
							model.addAttribute("renda", "Regular");
						}

					}

					model.addAttribute("curso", curso);
					model.addAttribute("turmas", turmas);
					model.addAttribute("desciplinas", desciplinasCertas);
					model.addAttribute("funcionario", funcs);
					model.addAttribute("nome", configurarNome);
					model.addAttribute("escola", configurarEscola);

					nomeDaPagina = "TechShine/Admin/cadastrar_Prof";
					this.pagina = nomeDaPagina;

				}

				// }

			} else {

				estado = 0;
				nomeDaPagina = "redirect:login";
				this.pagina = nomeDaPagina;
			}
			return this.pagina;
		}

		else if (requisicao.equalsIgnoreCase("/Admin-Pagar")) {

			if (entrarNoSistema.podeAbrirAPagina(senha)) {

				// if(acesso.bloqueandoRequisicoes(TechShine_Controller.request, request)) {

				int qalunos = p.pesquisarUmConteudo_Numa_Linha_Int(this.BD, "infoescola", "qalunos", "id", "", 1);
				int qfunc = p.pesquisarUmConteudo_Numa_Linha_Int(this.BD, "infoescola", "qfunc", "id", "", 1);
				int QCurso = p.pesquisarUmConteudo_Numa_Linha_Int(this.BD, "infoescola", "QCurso", "id", "", 1);
				int renda = p.pesquisarUmConteudo_Numa_Linha_Int(this.BD, "adminfinanca", "renda", "bi_admin", this.bi,
						0);

				model.addAttribute("qalunos", qalunos);
				model.addAttribute("qfunc", qfunc);
				model.addAttribute("qCurso", QCurso);
				model.addAttribute("renda", renda);

				model.addAttribute("nome", configurarNome);
				model.addAttribute("escola", configurarEscola);

				nomeDaPagina = "WebGnius/cadastrar_Empresa/pagar";
				this.pagina = nomeDaPagina;
				// }

			} else {

				estado = 0;
				nomeDaPagina = "redirect:login";
				this.pagina = nomeDaPagina;
			}
			return this.pagina;
		}

		return nomeDaPagina;
	}

	@PostMapping({ "/Admin-Pagar" })
	public String pagar(Pagar pagar, Model model) {

		Salvar_SQL s = new Salvar_SQL();
		s.adminPagar(this.BD, this.bi, pagar.getPagar());

		model.addAttribute("resultado", "Todos Funcionários foram Pagos !");
		// nomeDaPagina= entrarNoSistema.quemEstaAFazerLogin(senha, model);
		return "redirect:Admin-Pagar";
	}

	/*
	 * 
	 * @GetMapping({"/Admin-Turma-A","/Admin-Turma-B","/Admin-Turma-C",
	 * "/Admin-Turma-D"}) public String admin_Turma(){
	 * 
	 * return "TechShine/Admin/turma"; }
	 * 
	 */

	@GetMapping({ "/Admin-Info", "/Secretaria-Info" })
	public String admin_Info(Model model) {

		Pesquisar_SQL p = new Pesquisar_SQL();
		Salvar_SQL s = new Salvar_SQL();
		String nomeDaPagina = null;
		estado = -5;

		if (entrarNoSistema.podeAbrirAPagina(senha)) {

			// if(acesso.bloqueandoRequisicoes(TechShine_Controller.request, request)) {

			if (this.quem_E_O_func.equalsIgnoreCase("secretaria")) {

				String mes = s.mesActual(this.BD);

				int renda = p.pesquisarUmConteudo_Numa_Linha_Int(this.BD, this.quem_E_O_func + "_financa", mes, "id",
						"", this.id);
				int p2 = p.pesquisarUmConteudo_Numa_Linha_Int(this.BD, "func_diario", "Prop", "bi", this.bi, 0);
				int mc = p.pesquisarUmConteudo_Numa_Linha_Int(this.BD, "func_diario", "MatEConf", "bi", this.bi, 0);
				int os = p.pesquisarUmConteudo_Numa_Linha_Int(this.BD, "func_diario", "servicos", "bi", this.bi, 0);

				if (renda == 0) {
					model.addAttribute("renda", "Irregular");
				} else {
					model.addAttribute("renda", "Regular");

				}

				
				Date d = new Date();
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				
				model.addAttribute("data", sdf.format(d));
				
				
				model.addAttribute("p", p2);
				model.addAttribute("mc", mc);
				model.addAttribute("os", os);

				nomeDaPagina = "WebGnius/secretaria/informacaoDoAluno";

			} else if (this.quem_E_O_func.equalsIgnoreCase("PCA")) {

				int qalunos = p.pesquisarUmConteudo_Numa_Linha_Int(this.BD, "infoescola", "qalunos", "id", "", 1);
				int qfunc = p.pesquisarUmConteudo_Numa_Linha_Int(this.BD, "infoescola", "qfunc", "id", "", 1);
				int QCurso = p.pesquisarUmConteudo_Numa_Linha_Int(this.BD, "infoescola", "QCurso", "id", "", 1);
				int renda = p.pesquisarUmConteudo_Numa_Linha_Int(this.BD, "adminfinanca", "renda", "bi_admin", this.bi,
						0);

				model.addAttribute("qalunos", qalunos);
				model.addAttribute("qfunc", qfunc);
				model.addAttribute("qCurso", QCurso);
				model.addAttribute("renda", renda + ",00 Kz");

				nomeDaPagina = "TechShine/Admin/informacaoDoAluno";

			} else {

				int qalunos = p.pesquisarUmConteudo_Numa_Linha_Int(this.BD, "infoescola", "qalunos", "id", "", 1);
				int qfunc = p.pesquisarUmConteudo_Numa_Linha_Int(this.BD, "infoescola", "qfunc", "id", "", 1);
				int QCurso = p.pesquisarUmConteudo_Numa_Linha_Int(this.BD, "infoescola", "QCurso", "id", "", 1);

				String mes = s.mesActual(this.BD);

				int renda = p.pesquisarUmConteudo_Numa_Linha_Int(this.BD, this.quem_E_O_func + "_financa", mes, "id",
						"", this.id);

				if (renda == 0) {
					model.addAttribute("renda", "Irregular");
				} else {
					model.addAttribute("renda", "Regular");

				}

				model.addAttribute("qalunos", qalunos);
				model.addAttribute("qfunc", qfunc);
				model.addAttribute("qCurso", QCurso);

				nomeDaPagina = "TechShine/Admin/informacaoDoAluno";

			}

			model.addAttribute("resultado", "Aluno Inserido Com Sucesso !");
			model.addAttribute("nome", configurarNome);
			model.addAttribute("escola", configurarEscola);

			ArrayList<Turma> alunoDaTurma = new ArrayList<>();
			alunoDaTurma.add(this.turma);

			model.addAttribute("aluno", alunoDaTurma);

			this.pagina = nomeDaPagina;
			// }

		} else {

			estado = 0;
			nomeDaPagina = "redirect:login";
			this.pagina = nomeDaPagina;
		}
		return this.pagina;

	}

	@GetMapping("/Admin-Info-Curso")
	public String admin_Info_Curso(HttpServletRequest request) {

		estado = -5;
		String nomeDaPagina = null;

		if (entrarNoSistema.podeAbrirAPagina(senha)) {

			// if(acesso.bloqueandoRequisicoes(TechShine_Controller.request, request)) {

			nomeDaPagina = "TechShine/Admin/informacaoDoAluno";
			this.pagina = nomeDaPagina;
			// }

		} else {

			estado = 0;
			nomeDaPagina = "redirect:login";
			this.pagina = nomeDaPagina;
		}
		return this.pagina;
	}

	// FIM DOS MÉTODOS DO ADMIN
	// ================================

	/**
	 * Metodos Pertencentes aos Alunos da Escola
	 * 
	 * @return as devidas paginas
	 * 
	 *         Feito por
	 * @author Jose Euclides Pedro Pereira dos Santos
	 */
	@GetMapping("/Aluno-Notas")
	public String boletimDeNotas(Model model) {

		String nomeDaPagina = null;
		estado = -5;

		if (entrarNoSistema.podeAbrirAPagina(senha)) {

			// if(acesso.bloqueandoRequisicoes(TechShine_Controller.request, request)) {

			Salvar_SQL s = new Salvar_SQL();
			Pesquisar_SQL p = new Pesquisar_SQL();
			Tabela_Actualizar_SQL ta = new Tabela_Actualizar_SQL();

			String mes = s.mesActual(this.BD);
			Date d = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			int renda = p.pesquisarUmConteudo_Numa_Linha_Int(this.BD, this.quem_E_O_func + "_financa", mes, "id", "",
					this.id);
			int nDeEstudante = p.pesquisarUmConteudo_Numa_Linha_Int(this.BD, this.quem_E_O_func, "NProc", "id", "",
					this.id);

			if (renda == 0) {

				nomeDaPagina = "TechShine/Aluno/info1";
			} else {

				String turma = this.turmaAluno;
				String nivel = "";

				ArrayList<String> niveis = new ArrayList<>();

				niveis.add("1");
				niveis.add("2");
				niveis.add("3");
				niveis.add("4");
				niveis.add("5");
				niveis.add("6");
				niveis.add("7");
				niveis.add("8");
				niveis.add("9");
				niveis.add("10");
				niveis.add("11");
				niveis.add("12");
				niveis.add("13");

				String[] b = turma.split("_");

				turma = b[1];
				System.out.println("Turma Aqui: " + turma);

				for (String cadaC : niveis) {

					System.out.println("cadaC: " + cadaC);
					if (turma.endsWith(cadaC)) {

						System.out.println("Niveis Iguais");
						nivel = cadaC;
					}
				}

				System.out.println("Nivel " + nivel);
				String curso = p.pesquisarUmConteudo_Numa_Linha_String(this.BD, this.quem_E_O_func, "curso", "id", "",
						1);
				ArrayList<String> desciplinas = p.pesquisarTudoEmString(BD, "cursos_disciplinas", curso);
				String situacao = p.pesquisarUmConteudo_Numa_Linha_String(this.BD, "cursos", "Situacao", "nome", curso,
						0);
				curso = curso.substring(0, 3) + "" + nivel;

				ArrayList<String> desc = new ArrayList<>();
				ArrayList<String> dessciplinasFiltrada = new ArrayList<>();
				ArrayList<String> dessciplinasFiltrada2 = new ArrayList<>();

				String desc2;

				if ((turma.endsWith(curso))||
						(turma.endsWith(curso.toLowerCase()))) {

					for (String desciplina : desciplinas) {
						System.out.println("Disci: " + desciplina);

						if (desciplina.contains(nivel)) {

							desc = ta.tirarCaracteres_RetornandoA_A_Lista(desciplina);
							desc2 = desc.get(desc.size() - 1);

							System.out.println("Desc2: " + desc2);

							dessciplinasFiltrada.add(ta.tirarCaracteres(desc2));
							dessciplinasFiltrada2.add(desc2);
						}
					}
				}

				for (String dd : dessciplinasFiltrada2) {

					System.out.println("Desciplina: " + dd);
				}
				ArrayList<Aluno> dados = new ArrayList<>();

				ArrayList<String> situacao2 = new ArrayList<>();

				situacao2.add("A");
				situacao2.add("P");
				situacao2.add("E");

				for (int i = 0; i < dessciplinasFiltrada.size(); i++) {

					sair: for (String cadaCC : situacao2) {

						if (situacao.equalsIgnoreCase("A")) {

							Aluno aluno = new Aluno();

							aluno.setNota(p.pesquisarUmConteudo_Numa_Linha_Int(this.BD,
									this.quem_E_O_func + "_Avaliacao", dessciplinasFiltrada.get(i), "id", "", this.id));
							aluno.setDisciplina(dessciplinasFiltrada2.get(i));
							aluno.setSituacao("");

							dados.add(aluno);

							break sair;
						} else if (situacao.equalsIgnoreCase("P")) {

							Aluno aluno = new Aluno();

							aluno.setNota(p.pesquisarUmConteudo_Numa_Linha_Int(this.BD, this.quem_E_O_func + "_Prova",
									dessciplinasFiltrada.get(i), "id", "", this.id));
							aluno.setDisciplina(dessciplinasFiltrada2.get(i));
							aluno.setSituacao("");

							dados.add(aluno);

							break sair;
						} else if (situacao.equalsIgnoreCase("E")) {

							Aluno aluno = new Aluno();

							aluno.setNota(p.pesquisarUmConteudo_Numa_Linha_Int(this.BD, this.quem_E_O_func + "_Meedia",
									dessciplinasFiltrada.get(i), "id", "", this.id));
							aluno.setDisciplina(dessciplinasFiltrada2.get(i));
							aluno.setSituacao(p.pesquisarUmConteudo_Numa_Linha_String(this.BD,
									this.quem_E_O_func + "_Meedia", "Situacao", "id", "", this.id));

							dados.add(aluno);

							break sair;
						}
					}

				}

				model.addAttribute("aluno", dados);
				nomeDaPagina = "TechShine/Aluno/boletim";
			}

			if (renda == 0) {
				model.addAttribute("renda", "Irregular");
				model.addAttribute("situacao1", "Indisponivel");
			} else {
				model.addAttribute("renda", "Regular");
				model.addAttribute("situacao1", "Disponivel");
			}

			model.addAttribute("numero", nDeEstudante);

			model.addAttribute("data", sdf.format(d));

			model.addAttribute("nome", configurarNome);
			model.addAttribute("escola", configurarEscola);

			this.pagina = nomeDaPagina;
			// }

		} else {

			estado = 0;
			nomeDaPagina = "redirect:login";
			this.pagina = nomeDaPagina;
		}
		return this.pagina;

	}

	@GetMapping("/Aluno-Diario")
	public String diario(HttpServletRequest request) {

		String nomeDaPagina = null;
		estado = -5;

		if (entrarNoSistema.podeAbrirAPagina(senha)) {

			// if(acesso.bloqueandoRequisicoes(TechShine_Controller.request, request)) {

			nomeDaPagina = "TechShine/Aluno/diario";
			this.pagina = nomeDaPagina;
			// }

		} else {

			estado = 0;
			nomeDaPagina = "redirect:login";
			this.pagina = nomeDaPagina;
		}
		return this.pagina;

	}

	@GetMapping("/Aluno-Horario")
	public String horario(HttpServletRequest request) {

		String nomeDaPagina = null;
		estado = -5;

		if (entrarNoSistema.podeAbrirAPagina(senha)) {

			// if(acesso.bloqueandoRequisicoes(TechShine_Controller.request, request)) {

			nomeDaPagina = "TechShine/Aluno/horario";
			this.pagina = nomeDaPagina;
			// }

		} else {

			estado = 0;
			nomeDaPagina = "redirect:login";
			this.pagina = nomeDaPagina;
		}
		return this.pagina;

	}

	@GetMapping("/Aluno")
	public String inicio(Model model) {

		String nomeDaPagina = null;
		estado = -5;

		if (entrarNoSistema.podeAbrirAPagina(senha)) {

			// if(acesso.bloqueandoRequisicoes(TechShine_Controller.request, request)) {

			Salvar_SQL s = new Salvar_SQL();
			Pesquisar_SQL p = new Pesquisar_SQL();

			String mes = s.mesActual(this.BD);
			Date d = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			int renda = p.pesquisarUmConteudo_Numa_Linha_Int(this.BD, this.quem_E_O_func + "_financa", mes, "id", "",
					this.id);
			int nDeEstudante = p.pesquisarUmConteudo_Numa_Linha_Int(this.BD, this.quem_E_O_func, "NProc", "id", "",
					this.id);

			if (renda == 0) {
				model.addAttribute("renda", "Irregular");
				model.addAttribute("situacao1", "Indisponivel");
			} else {
				model.addAttribute("renda", "Regular");
				model.addAttribute("situacao1", "Disponivel");
			}

			model.addAttribute("numero", nDeEstudante);
			model.addAttribute("data", sdf.format(d));

			model.addAttribute("nome", configurarNome);
			model.addAttribute("escola", configurarEscola);

			nomeDaPagina = "TechShine/Aluno/inicio";
			this.pagina = nomeDaPagina;
			// }

		} else {

			estado = 0;
			nomeDaPagina = "redirect:login";
			this.pagina = nomeDaPagina;
		}
		return this.pagina;

	}

	@GetMapping("/Aluno-Materias")
	public String material(Model model) {

		String nomeDaPagina = null;
		estado = -5;

		if (entrarNoSistema.podeAbrirAPagina(senha)) {

			// if(acesso.bloqueandoRequisicoes(TechShine_Controller.request, request)) {

			Salvar_SQL s = new Salvar_SQL();
			Pesquisar_SQL p = new Pesquisar_SQL();
			Tabela_Actualizar_SQL ta = new Tabela_Actualizar_SQL();

			String mes = s.mesActual(this.BD);
			Date d = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			int renda = p.pesquisarUmConteudo_Numa_Linha_Int(this.BD, this.quem_E_O_func + "_financa", mes, "id", "",
					this.id);
			int nDeEstudante = p.pesquisarUmConteudo_Numa_Linha_Int(this.BD, this.quem_E_O_func, "NProc", "id", "",
					this.id);

			String turma = this.turmaAluno;
			String nivel = "";

			ArrayList<String> niveis = new ArrayList<>();

			niveis.add("1");
			niveis.add("2");
			niveis.add("3");
			niveis.add("4");
			niveis.add("5");
			niveis.add("6");
			niveis.add("7");
			niveis.add("8");
			niveis.add("9");
			niveis.add("10");
			niveis.add("11");
			niveis.add("12");
			niveis.add("13");

			String[] b = turma.split("_");

			turma = b[1];
			System.out.println("Turma Aqui: " + turma);

			for (String cadaC : niveis) {

				System.out.println("cadaC: " + cadaC);
				if (turma.endsWith(cadaC)) {

					System.out.println("Niveis Iguais");
					nivel = cadaC;
				}
			}

			System.out.println("Nivel " + nivel);
			String curso = p.pesquisarUmConteudo_Numa_Linha_String(this.BD, this.quem_E_O_func, "curso", "id", "", 1);
			ArrayList<String> desciplinas = p.pesquisarTudoEmString(BD, "cursos_disciplinas", curso);
			curso = curso.substring(0, 3) + "" + nivel;

			ArrayList<String> desc = new ArrayList<>();
			ArrayList<String> dessciplinasFiltrada2 = new ArrayList<>();

			String desc2;
			
			if (turma.endsWith(curso)||(turma.endsWith(curso.toLowerCase()))) {

				for (String desciplina : desciplinas) {
					System.out.println("Disci: " + desciplina);

					if (desciplina.contains(nivel)) {

						desc = ta.tirarCaracteres_RetornandoA_A_Lista(desciplina);
						desc2 = desc.get(desc.size() - 1);

						System.out.println("Desc2: " + desc2);

						dessciplinasFiltrada2.add(desc2);
					}
				}
			}

			for (String dd : dessciplinasFiltrada2) {

				System.out.println("Desciplina: " + dd);
			}

			if (renda == 0) {
				model.addAttribute("renda", "Irregular");
				model.addAttribute("situacao1", "Indisponivel");
			} else {
				model.addAttribute("renda", "Regular");
				model.addAttribute("situacao1", "Disponivel");
			}

			model.addAttribute("desciplinas", dessciplinasFiltrada2);
			model.addAttribute("numero", nDeEstudante);
			model.addAttribute("data", sdf.format(d));

			model.addAttribute("nome", configurarNome);
			model.addAttribute("escola", configurarEscola);

			nomeDaPagina = "TechShine/Aluno/material";
			this.pagina = nomeDaPagina;
			// }

		} else {

			estado = 0;
			nomeDaPagina = "redirect:login";
			this.pagina = nomeDaPagina;
		}
		return this.pagina;

	}

	@PostMapping({ "/Aluno-Materias" })
	public String aluno3(Aluno aluno) {

		this.disciplina = aluno.getDisciplina();
		// nomeDaPagina= entrarNoSistema.quemEstaAFazerLogin(senha, model);
		Salvar_SQL s = new Salvar_SQL();
		Pesquisar_SQL p = new Pesquisar_SQL();
		
		
		String mes = s.mesActual(this.BD);
		
		int renda = p.pesquisarUmConteudo_Numa_Linha_Int(this.BD, this.quem_E_O_func + "_financa", mes, "id", "",
				this.id);
		int nDeEstudante = p.pesquisarUmConteudo_Numa_Linha_Int(this.BD, this.quem_E_O_func, "NProc", "id", "",
				this.id);

		if (renda == 0) {

			this.pagina = "TechShine/Aluno/info1";
		}else {
			this.pagina = "redirect:Aluno-Materias-2";
		}
		
		
		return this.pagina;
	}

	@GetMapping("/Aluno-Materias-2")
	public String aluno4(Model model, HttpServletResponse response) {

		String nomeDaPagina = null;
		estado = -5;

		if (entrarNoSistema.podeAbrirAPagina(senha)) {

			// if(acesso.bloqueandoRequisicoes(TechShine_Controller.request, request)) {

			Salvar_SQL s = new Salvar_SQL();
			Pesquisar_SQL p = new Pesquisar_SQL();
			Tabela_Actualizar_SQL ta = new Tabela_Actualizar_SQL();

			String mes = s.mesActual(this.BD);
			Date d = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			int renda = p.pesquisarUmConteudo_Numa_Linha_Int(this.BD, this.quem_E_O_func + "_financa", mes, "id", "",
					this.id);
			int nDeEstudante = p.pesquisarUmConteudo_Numa_Linha_Int(this.BD, this.quem_E_O_func, "NProc", "id", "",
					this.id);

			String desciplina = ta.tirarCaracteres(this.disciplina);

			ArrayList<String> minhasMaterias = new ArrayList<>();

			ArrayList<String> enderecos = p.pesquisarTudoEmString(this.BD, "Materias_Online", "descricao");

			boolean siglaIgual = false;
			ArrayList<String> siglas = p.pesquisarTudoEmString(this.BD, "Materias_Online", "sigla");

			sair: for (String c : siglas) {

				if ((this.turmaAluno.contains(c))||
						(this.turmaAluno.contains(c.toLowerCase()))) {

					siglaIgual = true;
					break sair;
				}
			}

			String ficheiro;
			String data;
			String endereco;

			ArrayList<String> conteudos = new ArrayList<>();
			for (String c : enderecos) {

				endereco = p.pesquisarUmConteudo_Numa_Linha_String(this.BD, "Materias_Online", "endereco", "Descricao",
						c, 0);

				System.out.println("Descricao: " + c);
				System.out.println("ficheiro: " + endereco);
				System.out.println("Desciplina: " + desciplina);
				if ((endereco.contains(desciplina)) && (siglaIgual) && (endereco.contains(this.turmaAluno))) {

					System.out.println("Iguais");

					data = p.pesquisarUmConteudo_Numa_Linha_String(this.BD, "Materias_Online", "Data", "Descricao", c,
							0);
					ficheiro = p.pesquisarUmConteudo_Numa_Linha_String(this.BD, "Materias_Online", "Ficheiro",
							"Descricao", c, 0);

					System.out.println("Data: " + data);
					System.out.println("ficheiro: " + ficheiro);
					System.out.println("Endereco: " + endereco);

					conteudos.add(data + " - " + c);
					this.conteudos3.add(data + " - " + c + ";" + endereco + ";" + ficheiro);
				}

			}

			for (int i = conteudos.size() - 1; i >= 0; i--) {

				minhasMaterias.add(conteudos.get(i));

			}

			// Aqui Vou Fazer Uma Pesquisa No Servidor PAra Ver
			// As materias de Uma Disciplina no Servidor
			// Sendo que os Conteudos Vão Ser Apresentados
			// Em Forma de Data, Apresenta-as de Baixo Para Cima, ou
			// Seja Das Ultimas Materias Lançadas até as mais antigas

			/*
			 * Ver Como Ler ou Recuperar Arquivo no Servidor e como Fazer download do
			 * Arquivo, tambem dar uma olha como usaar o thymeleaf com th:href th:src !!!
			 * 
			 */

			if (renda == 0) {
				model.addAttribute("renda", "Irregular");
				model.addAttribute("situacao1", "Indisponivel");
			} else {
				model.addAttribute("renda", "Regular");
				model.addAttribute("situacao1", "Disponivel");
			}

			model.addAttribute("desciplina", this.disciplina);
			model.addAttribute("materias", minhasMaterias);
			model.addAttribute("numero", nDeEstudante);
			model.addAttribute("data", sdf.format(d));

			model.addAttribute("nome", configurarNome);
			model.addAttribute("escola", configurarEscola);

			nomeDaPagina = "TechShine/Aluno/material2";
			this.pagina = nomeDaPagina;
			// }

		} else {

			estado = 0;
			nomeDaPagina = "redirect:login";
			this.pagina = nomeDaPagina;
		}
		return this.pagina;

	}

	@PostMapping("/Aluno-Materias-2")
	public String materias(HttpServletResponse response, Aluno aluno, Model model) {

		String materia = aluno.getMateria();
		System.out.println("Materia: " + materia);
		String arquivo = null;
		String[] a = null;

		sair: for (String c : this.conteudos3) {
			System.out.println("Materia: " + materia);
			System.out.println("Materia2: " + c);
			if (c.contains(materia)) {
				System.out.println("Igual 3: ");

				a = c.split(";");
				arquivo = a[1] + "" + a[2];

				break sair;
			}
		}

		for (String c : a) {
			System.out.println("Conteudo: " + c);
		}

		if (a.length > 0) {

			System.out.println("ARQUIVO ALUNO: " + arquivo);

			try {

				FileInputStream in = new FileInputStream(arquivo);
				ServletOutputStream out = response.getOutputStream();

				byte[] buf = new byte[1024];
				int cont = 0;
				while ((cont = in.read(buf)) >= 0) {
					out.write(buf, 0, cont);

					System.out.println("Disponibilizando o Arquivo");

				}

				in.close();
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return "redirect:Aluno-Materias-2";
	}

	@GetMapping("/Aluno-Matriz-Curricular")
	public String matriz(HttpServletRequest request) {

		String nomeDaPagina = null;
		estado = -5;

		if (entrarNoSistema.podeAbrirAPagina(senha)) {

			// if(acesso.bloqueandoRequisicoes(TechShine_Controller.request, request)) {

			nomeDaPagina = "TechShine/Aluno/matriz";
			this.pagina = nomeDaPagina;
			// }

		} else {

			estado = 0;
			nomeDaPagina = "redirect:login";
			this.pagina = nomeDaPagina;
		}
		return this.pagina;

	}

	@GetMapping("/Aluno-Financa")
	public String alunoFinanca(Model model) {

		String nomeDaPagina = null;
		estado = -5;

		if (entrarNoSistema.podeAbrirAPagina(senha)) {

			// if(acesso.bloqueandoRequisicoes(TechShine_Controller.request, request)) {

			Salvar_SQL s = new Salvar_SQL();
			Pesquisar_SQL p = new Pesquisar_SQL();

			String mes = s.mesActual(this.BD);
			Date d = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			int renda = p.pesquisarUmConteudo_Numa_Linha_Int(this.BD, this.quem_E_O_func + "_financa", mes, "id", "",
					this.id);
			int nDeEstudante = p.pesquisarUmConteudo_Numa_Linha_Int(this.BD, this.quem_E_O_func, "NProc", "id", "",
					this.id);

			ArrayList<String> meses = new ArrayList<>();

			meses.add("Janeiro");

			meses.add("Fevereiro");

			meses.add("Marco");

			meses.add("Abril");

			meses.add("Maio");

			meses.add("Junho");

			meses.add("Julho");

			meses.add("Agosto");

			meses.add("Setembro");

			meses.add("Outubro");

			meses.add("Novembro");

			meses.add("Dezembro");

			String curso = p.pesquisarUmConteudo_Numa_Linha_String(this.BD, this.quem_E_O_func, "curso", "id", "", 1);

			
			String n=this.quem_E_O_func;
			String turma=null;
			
			if(this.quem_E_O_func.contains("__")) {
				
				String[] a = n.split("__");
				 turma = a[0];
			}else {
				turma = this.quem_E_O_func;
			}
			
			
			String nivel="";
			
			
			if(turma.endsWith("10")) {
				System.out.println("Turma Da 10º Classe");
				nivel="10";
			}else if(turma.endsWith("11")) {
				System.out.println("Turma Da 11º Classe");
				nivel="11";
			}if(turma.endsWith("12")) {
				System.out.println("Turma Da 12º Classe");
				nivel="12";
			}if(turma.endsWith("13")) {
				System.out.println("Turma Da 13º Classe");
				nivel="13";
			}
			
            int precoDoCurso=0;		
			
			if(nivel.contains("10")) {
				precoDoCurso= p.pesquisarUmConteudo_Numa_Linha_Int(BD, "cursos_Niveis", "Decima", "cursos", curso, 0);	
			}else if(nivel.contains("11")) {
				precoDoCurso= p.pesquisarUmConteudo_Numa_Linha_Int(BD, "cursos_Niveis", "DecimaPrimeira", "cursos", curso, 0);	
			}else if(nivel.contains("12")) {
				precoDoCurso= p.pesquisarUmConteudo_Numa_Linha_Int(BD, "cursos_Niveis", "DecimaSegunda", "cursos", curso, 0);	
			}else if(nivel.contains("13")) {
				precoDoCurso= p.pesquisarUmConteudo_Numa_Linha_Int(BD, "cursos_Niveis", "DecimaTerceira", "cursos", curso, 0);	
			}
			
			
			
			
			
			int diferenca;
			int mes2 = 0;
			
			

			ArrayList<Aluno> dados = new ArrayList<>();
			for (String cadaC : meses) {

				Aluno aluno = new Aluno();
				mes2 = p.pesquisarUmConteudo_Numa_Linha_Int(this.BD, this.quem_E_O_func + "_Financa", cadaC, "id", "",
						this.id);

				if (mes2 > 0) {
					
					if(mes2==1) {
						
						aluno.setPagamento("Não Pago");
						aluno.setMulta("Nenhuma");
					}else {
						
						diferenca = mes2 - precoDoCurso;
						
						System.out.println("Diferenca: "+diferenca);
						System.out.println("mes2: "+mes2);
						System.out.println("precoDoCurso: "+precoDoCurso);
						
						if (diferenca == 0) {
							
							System.out.println("Diferenca: "+diferenca);
							System.out.println("mes2: "+mes2);

							aluno.setPagamento(mes2 + ",00 Kz");
							aluno.setMulta("0,00 Kz");
						} else {

							System.out.println("+*+++++++++++++++++++");
							
							System.out.println("Diferenca: "+diferenca);
							System.out.println("mes2: "+mes2);

							aluno.setPagamento(mes2 + ",00 Kz");
							aluno.setMulta(diferenca + ",00 Kz");

							mes2 = mes2 - diferenca;
						}
						
					}

					
				}

				if (cadaC.equalsIgnoreCase("Marco")) {

					aluno.setMes("Março");
				} else {

					aluno.setMes(cadaC);
				}

				dados.add(aluno);
			}

			if (renda == 0) {
				model.addAttribute("renda", "Irregular");
				model.addAttribute("situacao1", "Indisponivel");
			} else {
				model.addAttribute("renda", "Regular");
				model.addAttribute("situacao1", "Disponivel");
			}

			model.addAttribute("aluno", dados);

			model.addAttribute("numero", nDeEstudante);
			model.addAttribute("data", sdf.format(d));

			model.addAttribute("nome", configurarNome);
			model.addAttribute("escola", configurarEscola);

			nomeDaPagina = "TechShine/Aluno/financa";
			this.pagina = nomeDaPagina;
			// }

		} else {

			estado = 0;
			nomeDaPagina = "redirect:login";
			this.pagina = nomeDaPagina;
		}
		return this.pagina;

	}

	// ===================================

	/**
	 * Metodos Pertencentes ao Coordenador da Escola
	 * 
	 * @return as devidas paginas
	 * 
	 *         Feito por
	 * @author Jose Euclides Pedro Pereira dos Santos
	 */

	@GetMapping("/coord")
	public String cord_inicio(Model model) {

		Pesquisar_SQL p = new Pesquisar_SQL();
		Salvar_SQL s = new Salvar_SQL();

		String nomeDaPagina = null;
		estado = -5;
		if (entrarNoSistema.podeAbrirAPagina(senha)) {

			// if(acesso.bloqueandoRequisicoes(TechShine_Controller.request, request)) {

			int contador = 0;
			int contador2 = 0;
			ArrayList<String> cursos = p.pesquisarTudoEmString_Restrito(this.BD, "cursos", "nome", "bi", this.bi, 0);

			System.out.println("Entrou Nos Cursos");
			for (String cadaC : cursos) {
				++contador2;
				System.out.println("Contador2: " + contador2);
				ArrayList<String> profs = p.pesquisarTudoEmString_Restrito(this.BD, "disciplinas_dos_profs",
						"professor", "curso", cadaC, 0);
				for (String pr : profs) {

					++contador;
					System.out.println("Contador: " + contador);
				}

			}

			String mes = s.mesActual(this.BD);
			Date d = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			int renda = p.pesquisarUmConteudo_Numa_Linha_Int(this.BD, this.quem_E_O_func + "_financa", mes, "id", "",
					this.id);

			model.addAttribute("qCursos", contador2);
			model.addAttribute("qProfs", contador);
			model.addAttribute("data", sdf.format(d));

			if (renda == 0) {
				model.addAttribute("renda", "Irregular");
			} else {
				model.addAttribute("renda", "Regular");
			}

			model.addAttribute("nome", configurarNome);
			model.addAttribute("escola", configurarEscola);

			nomeDaPagina = "TechShine/Coordenador/inicio";
			this.pagina = nomeDaPagina;

			// }

		} else {

			estado = 0;
			nomeDaPagina = "redirect:login";
			this.pagina = nomeDaPagina;
		}
		return this.pagina;

	}

	@GetMapping("/Coord-Professores-2")
	public String cord_materias(Model model) {

		String nomeDaPagina = null;
		estado = -5;
		if (entrarNoSistema.podeAbrirAPagina(senha)) {

			// if(acesso.bloqueandoRequisicoes(TechShine_Controller.request, request)) {

			Pesquisar_SQL p = new Pesquisar_SQL();
			Salvar_SQL s = new Salvar_SQL();
			Controle_ID_SQL cID = new Controle_ID_SQL();

			int contador = 0;
			int contador2 = 0;
			int contador3 = 0;
			ArrayList<String> cursos = p.pesquisarTudoEmString_Restrito(this.BD, "cursos", "nome", "bi", this.bi, 0);

			ArrayList<Integer> qPros = new ArrayList<>();
			ArrayList<Integer> qAlunos = new ArrayList<>();

			System.out.println("Entrou Nos Cursos");
			for (String cadaC : cursos) {
				++contador2;
				System.out.println("Contador2: " + contador2);
				ArrayList<String> profs = p.pesquisarTudoEmString_Restrito(this.BD, "disciplinas_dos_profs",
						"professor", "curso", cadaC, 0);

				qPros.add(profs.size());
				ArrayList<String> turma = p.pesquisarTudoEmString_Restrito(this.BD, "disciplinas_dos_profs", "turma",
						"curso", cadaC, 0);

				for (String pr : profs) {

					++contador;
					System.out.println("Contador: " + contador);
				}

				for (String t : turma) {

					System.out.println("Turma: " + t);

					contador3 = contador3 + cID.recuperarCodigo(this.BD, t, "id");

				}

				qAlunos.add(contador3);
				contador3 = 0;

			}

			ArrayList<String> profs = p.pesquisarTudoEmString_Restrito(this.BD, "disciplinas_dos_profs", "professor",
					"curso", this.curso2, 0);
			ArrayList<String> bis = p.pesquisarTudoEmString_Restrito(this.BD, "disciplinas_dos_profs", "bi", "curso",
					this.curso2, 0);
			ArrayList<Integer> telefones = new ArrayList<>();

			for (String b : bis) {

				telefones.add(p.pesquisarUmConteudo_Numa_Linha_Int(this.BD, "professor_dadospessoais", "Telefone", "bi",
						b, 0));
			}

			ArrayList<Curso> conteudosDoCurso = new ArrayList<>();
			for (int i = 0; i < profs.size(); i++) {

				Curso curso = new Curso();

				if ((profs.size() > 0) && (i < profs.size())) {

					curso.setProfessor(profs.get(i));
					;
				}

				if ((telefones.size() > 0) && (i < telefones.size())) {

					curso.setTelefone(telefones.get(i));
					;
				}

				conteudosDoCurso.add(curso);
			}

			String mes = s.mesActual(this.BD);
			Date d = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			int renda = p.pesquisarUmConteudo_Numa_Linha_Int(this.BD, this.quem_E_O_func + "_financa", mes, "id", "",
					this.id);

			model.addAttribute("cursos", conteudosDoCurso);
			model.addAttribute("qCursos", contador2);
			model.addAttribute("qProfs", contador);
			model.addAttribute("nAlunos", contador3);
			model.addAttribute("data", sdf.format(d));

			if (renda == 0) {
				model.addAttribute("renda", "Irregular");
			} else {
				model.addAttribute("renda", "Regular");
			}

			model.addAttribute("nome", configurarNome);
			model.addAttribute("escola", configurarEscola);

			nomeDaPagina = "TechShine/Coordenador/CoordProfs";
			this.pagina = nomeDaPagina;
			// }

		} else {

			estado = 0;
			nomeDaPagina = "redirect:login";
			this.pagina = nomeDaPagina;
		}
		return this.pagina;

	}

	@GetMapping({ "/Aluno-Minha-Seguranca" })
	public String aluno(Model model) {

		String nomeDaPagina = null;
		estado = -5;

		if (entrarNoSistema.podeAbrirAPagina(senha)) {

			// if(acesso.bloqueandoRequisicoes(TechShine_Controller.request, request)) {

			
			Salvar_SQL s = new Salvar_SQL();
			Pesquisar_SQL p = new Pesquisar_SQL();

			String mes = s.mesActual(this.BD);
			Date d = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			int renda = p.pesquisarUmConteudo_Numa_Linha_Int(this.BD, this.quem_E_O_func + "_financa", mes, "id", "",
					this.id);
			int nDeEstudante = p.pesquisarUmConteudo_Numa_Linha_Int(this.BD, this.quem_E_O_func, "NProc", "id", "",
					this.id);

			if (renda == 0) {
				model.addAttribute("renda", "Irregular");
				model.addAttribute("situacao1", "Indisponivel");
			} else {
				model.addAttribute("renda", "Regular");
				model.addAttribute("situacao1", "Disponivel");
			}

			model.addAttribute("numero", nDeEstudante);
			
			
			model.addAttribute("data", sdf.format(d));

			model.addAttribute("nome", configurarNome);
			model.addAttribute("escola", configurarEscola);
			
			
			nomeDaPagina = "TechShine/Aluno/security";
			this.pagina = nomeDaPagina;
			// }

		} else {

			estado = 0;
			nomeDaPagina = "redirect:login";
			this.pagina = nomeDaPagina;
		}

		// nomeDaPagina= entrarNoSistema.quemEstaAFazerLogin(senha, model);
		return this.pagina;
	}

	@GetMapping("/Coord-Professores")
	public String cord_turmas(Model model) {

		String nomeDaPagina = null;
		estado = -5;
		if (entrarNoSistema.podeAbrirAPagina(senha)) {

			// if(acesso.bloqueandoRequisicoes(TechShine_Controller.request, request)) {

			Pesquisar_SQL p = new Pesquisar_SQL();
			Salvar_SQL s = new Salvar_SQL();
			Controle_ID_SQL cID = new Controle_ID_SQL();

			int contador = 0;
			int contador2 = 0;
			int contador3 = 0;
			

			ArrayList<String> cursos3 = p.pesquisarTudoEmString_Restrito(this.BD, "cursos", "nome", "bi", this.bi, 0);

			System.out.println("Entrou Nos Cursos");
			for (String cadaC : cursos3) {
				++contador2;
				System.out.println("Contador2: " + contador2);
				ArrayList<String> profs = p.pesquisarTudoEmString_Restrito(this.BD, "disciplinas_dos_profs",
						"professor", "curso", cadaC, 0);
				for (String pr : profs) {

					++contador;
					System.out.println("Contador: " + contador);
				}

			}

			System.out.println("Entrou Nos Cursos");

			String mes = s.mesActual(this.BD);
			Date d = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			int renda = p.pesquisarUmConteudo_Numa_Linha_Int(this.BD, this.quem_E_O_func + "_financa", mes, "id", "",
					this.id);

			model.addAttribute("cursos", cursos3);
			model.addAttribute("qCursos", contador2);
			model.addAttribute("qProfs", contador);
			model.addAttribute("nAlunos", contador3);
			model.addAttribute("data", sdf.format(d));

			if (renda == 0) {
				model.addAttribute("renda", "Irregular");
			} else {
				model.addAttribute("renda", "Regular");
			}

			model.addAttribute("nome", configurarNome);
			model.addAttribute("escola", configurarEscola);

			nomeDaPagina = "TechShine/Coordenador/curso";
			this.pagina = nomeDaPagina;
			// }

		} else {

			estado = 0;
			nomeDaPagina = "redirect:login";
			this.pagina = nomeDaPagina;
		}
		return this.pagina;

	}

	@GetMapping("/Coord-Permissoes")
	public String cord_Avaliacao(Model model) {

		String nomeDaPagina = null;
		estado = -5;
		if (entrarNoSistema.podeAbrirAPagina(senha)) {

			// if(acesso.bloqueandoRequisicoes(TechShine_Controller.request, request)) {

			Pesquisar_SQL p = new Pesquisar_SQL();
			Salvar_SQL s = new Salvar_SQL();

			int contador = 0;
			int contador2 = 0;
			ArrayList<String> cursos = p.pesquisarTudoEmString_Restrito(this.BD, "cursos", "nome", "bi", this.bi, 0);

			System.out.println("Entrou Nos Cursos");
			for (String cadaC : cursos) {
				++contador2;
				System.out.println("Contador2: " + contador2);
				ArrayList<String> profs = p.pesquisarTudoEmString_Restrito(this.BD, "disciplinas_dos_profs",
						"professor", "curso", cadaC, 0);
				for (String pr : profs) {

					++contador;
					System.out.println("Contador: " + contador);
				}

			}

			String mes = s.mesActual(this.BD);
			Date d = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			int renda = p.pesquisarUmConteudo_Numa_Linha_Int(this.BD, this.quem_E_O_func + "_financa", mes, "id", "",
					this.id);

			model.addAttribute("qCursos", contador2);
			model.addAttribute("qProfs", contador);
			model.addAttribute("data", sdf.format(d));

			if (renda == 0) {
				model.addAttribute("renda", "Irregular");
			} else {
				model.addAttribute("renda", "Regular");
			}

			model.addAttribute("nome", configurarNome);
			model.addAttribute("escola", configurarEscola);

			nomeDaPagina = "TechShine/Coordenador/CoordPermissoes";
			this.pagina = nomeDaPagina;
			// }

		} else {

			estado = 0;
			nomeDaPagina = "redirect:login";
			this.pagina = nomeDaPagina;
		}
		return this.pagina;
	}

	@GetMapping("/Coord-Minha-Seguranca")
	public String corod_Seguranca(Model model) {

		String nomeDaPagina = null;
		estado = -5;
		if (entrarNoSistema.podeAbrirAPagina(senha)) {

			// if(acesso.bloqueandoRequisicoes(TechShine_Controller.request, request)) {

			Pesquisar_SQL p = new Pesquisar_SQL();
			Salvar_SQL s = new Salvar_SQL();

			int contador = 0;
			int contador2 = 0;
			ArrayList<String> cursos = p.pesquisarTudoEmString_Restrito(this.BD, "cursos", "nome", "bi", this.bi, 0);

			System.out.println("Entrou Nos Cursos");
			for (String cadaC : cursos) {
				++contador2;
				System.out.println("Contador2: " + contador2);
				ArrayList<String> profs = p.pesquisarTudoEmString_Restrito(this.BD, "disciplinas_dos_profs",
						"professor", "curso", cadaC, 0);
				for (String pr : profs) {

					++contador;
					System.out.println("Contador: " + contador);
				}

			}

			String mes = s.mesActual(this.BD);
			Date d = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			int renda = p.pesquisarUmConteudo_Numa_Linha_Int(this.BD, this.quem_E_O_func + "_financa", mes, "id", "",
					this.id);

			model.addAttribute("qCursos", contador2);
			model.addAttribute("qProfs", contador);
			model.addAttribute("data", sdf.format(d));

			if (renda == 0) {
				model.addAttribute("renda", "Irregular");
			} else {
				model.addAttribute("renda", "Regular");
			}

			model.addAttribute("nome", configurarNome);
			model.addAttribute("escola", configurarEscola);

			nomeDaPagina = "TechShine/Coordenador/definicao";
			this.pagina = nomeDaPagina;
			// }

		} else {

			estado = 0;
			nomeDaPagina = "redirect:login";
			this.pagina = nomeDaPagina;
		}
		return this.pagina;
	}

	@GetMapping("/financa")
	public String financa(Model model) {

		String nomeDaPagina = null;
		estado = -5;
		if (entrarNoSistema.podeAbrirAPagina(senha)) {

			// if(acesso.bloqueandoRequisicoes(TechShine_Controller.request, request)) {

			Pesquisar_SQL p = new Pesquisar_SQL();
			Salvar_SQL s = new Salvar_SQL();

			int contador = 0;
			int contador2 = 0;
			ArrayList<String> cursos = p.pesquisarTudoEmString_Restrito(this.BD, "cursos", "nome", "bi", this.bi, 0);

			System.out.println("Entrou Nos Cursos");
			for (String cadaC : cursos) {
				++contador2;
				System.out.println("Contador2: " + contador2);
				ArrayList<String> profs = p.pesquisarTudoEmString_Restrito(this.BD, "disciplinas_dos_profs",
						"professor", "curso", cadaC, 0);
				for (String pr : profs) {

					++contador;
					System.out.println("Contador: " + contador);
				}

			}

			String mes = s.mesActual(this.BD);
			Date d = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			int renda = p.pesquisarUmConteudo_Numa_Linha_Int(this.BD, this.quem_E_O_func + "_financa", mes, "id", "",
					this.id);

			model.addAttribute("qCursos", contador2);
			model.addAttribute("qProfs", contador);
			model.addAttribute("data", sdf.format(d));

			if (renda == 0) {
				model.addAttribute("renda", "Irregular");
			} else {
				model.addAttribute("renda", "Regular");
			}

			model.addAttribute("nome", configurarNome);
			model.addAttribute("escola", configurarEscola);

			nomeDaPagina = "TechShine/Coordenador/financa";
			this.pagina = nomeDaPagina;
			// }

		} else {

			estado = 0;
			nomeDaPagina = "redirect:login";
			this.pagina = nomeDaPagina;
		}
		return this.pagina;

	}

	/**
	 * Metodos Pertencentes aos Professor da Escola
	 * 
	 * @return as devidas paginas
	 * 
	 *         Feito por
	 * @author Jose Euclides Pedro Pereira dos Santos
	 */

	@GetMapping("/Professor")
	public String professor(Model model) {

		Salvar_SQL s = new Salvar_SQL();
		Tabela_Actualizar_SQL ta = new Tabela_Actualizar_SQL();
		String nomeDaPagina = null;
		estado = -5;
		if (entrarNoSistema.podeAbrirAPagina(senha)) {

			// if(acesso.bloqueandoRequisicoes(TechShine_Controller.request, request)) {

			Pesquisar_SQL p = new Pesquisar_SQL();

			int renda;
			String mes = s.mesActual(this.BD);

			renda = p.pesquisarUmConteudo_Numa_Linha_Int(this.BD, this.quem_E_O_func + "_financa", mes, "id", "",
					this.id);

			if (renda == 0) {
				model.addAttribute("renda", "Irregular");
			} else {
				model.addAttribute("renda", "Regular");
			}

			p.pesquisarUmConteudo_Numa_Linha_String(this.BD, "disciplinas_dos_profs", "turma", "bi", this.bi, 0);
			ArrayList<String> turmas = p.pesquisarTudoEmString_Restrito(this.BD, "disciplinas_dos_profs", "turma", "bi",
					this.bi, 0);

			int qalunos = 0;
			int contador = 0;

			Controle_ID_SQL cID = new Controle_ID_SQL();
			for (String turma : turmas) {

				qalunos = qalunos + cID.recuperarCodigo(this.BD, turma, "id");
				System.out.println("qalunos: " + qalunos);
				++contador;
			}

			// ArrayList<String> desciplinas =p.pesquisarTudoEmString_Restrito(this.BD,
			// "disciplinas_dos_profs", "desciplina", "bi", this.bi, 0);
			// ArrayList<String> cursos =p.pesquisarTudoEmString_Restrito(this.BD,
			// "disciplinas_dos_profs", "curso", "bi", this.bi, 0);
			// ArrayList<String> turnos =p.pesquisarTudoEmString_Restrito(this.BD,
			// "disciplinas_dos_profs", "turno", "bi", this.bi, 0);
			// ArrayList<String> niveis =p.pesquisarTudoEmString_Restrito(this.BD,
			// "disciplinas_dos_profs", "nivel", "bi", this.bi, 0);

			Date d = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			String data = sdf.format(d);

			model.addAttribute("qalunos", qalunos);
			model.addAttribute("qturmas", contador);
			model.addAttribute("data", data);
			model.addAttribute("mes", mes);

			model.addAttribute("nome", configurarNome);
			model.addAttribute("escola", configurarEscola);

			nomeDaPagina = "TechShine/Professor/inicio";
			this.pagina = nomeDaPagina;
			// }

		} else {

			estado = 0;
			nomeDaPagina = "redirect:login";
			this.pagina = nomeDaPagina;
		}
		return this.pagina;
	}

	@GetMapping("/Professor-Material")
	public String professor_Material(Model model) {

		String nomeDaPagina = null;
		estado = -5;
		if (entrarNoSistema.podeAbrirAPagina(senha)) {

			// if(acesso.bloqueandoRequisicoes(TechShine_Controller.request, request)) {

			Pesquisar_SQL p = new Pesquisar_SQL();
			Salvar_SQL s = new Salvar_SQL();

			int renda;
			String mes = s.mesActual(this.BD);

			renda = p.pesquisarUmConteudo_Numa_Linha_Int(this.BD, this.quem_E_O_func + "_financa", mes, "id", "",
					this.id);

			if (renda == 0) {
				model.addAttribute("renda", "Irregular");
			} else {
				model.addAttribute("renda", "Regular");
			}

			ArrayList<String> turmas = p.pesquisarTudoEmString_Restrito(this.BD, "disciplinas_dos_profs", "turma", "bi",
					this.bi, 0);

			int qalunos = 0;
			int contador = 0;

			Controle_ID_SQL cID = new Controle_ID_SQL();
			for (String turma : turmas) {

				qalunos = qalunos + cID.recuperarCodigo(this.BD, turma, "id");
				System.out.println("qalunos: " + qalunos);
				++contador;
			}

			// ArrayList<String> desciplinas =p.pesquisarTudoEmString_Restrito(this.BD,
			// "disciplinas_dos_profs", "desciplina", "bi", this.bi, 0);
			// ArrayList<String> cursos =p.pesquisarTudoEmString_Restrito(this.BD,
			// "disciplinas_dos_profs", "curso", "bi", this.bi, 0);
			// ArrayList<String> turnos =p.pesquisarTudoEmString_Restrito(this.BD,
			// "disciplinas_dos_profs", "turno", "bi", this.bi, 0);
			// ArrayList<String> niveis =p.pesquisarTudoEmString_Restrito(this.BD,
			// "disciplinas_dos_profs", "nivel", "bi", this.bi, 0);

			Date d = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			String data = sdf.format(d);

			ArrayList<String> cursos = p.pesquisarTudoEmString_Restrito(this.BD, "disciplinas_dos_profs", "curso", "bi",
					this.bi, 0);
			ArrayList<String> niveis = p.pesquisarTudoEmString_Restrito(this.BD, "disciplinas_dos_profs", "nivel", "bi",
					this.bi, 0);
			ArrayList<String> desciplinas = p.pesquisarTudoEmString_Restrito(this.BD, "disciplinas_dos_profs",
					"desciplina", "bi", this.bi, 0);

			model.addAttribute("qalunos", qalunos);
			model.addAttribute("qturmas", contador);
			model.addAttribute("data", data);
			model.addAttribute("mes", mes);
			model.addAttribute("cursos", cursos);
			model.addAttribute("niveis", niveis);
			model.addAttribute("desciplinas", desciplinas);
			model.addAttribute("turmas", turmas);

			model.addAttribute("nome", configurarNome);
			model.addAttribute("escola", configurarEscola);

			nomeDaPagina = "TechShine/Professor/material-aula";
			this.pagina = nomeDaPagina;
			// }

		} else {

			estado = 0;
			nomeDaPagina = "redirect:login";
			this.pagina = nomeDaPagina;
		}
		return this.pagina;
	}

	@PostMapping("/Professor-Material")
	public String professor_Material(Model model, Professor prof, HttpServletRequest request,
			HttpServletResponse response) throws IOException {

		MultipartFile arquivo = prof.getFicheiro();

		this.disciplina = prof.getDesciplina();
		String descricao = prof.getDescricao();

		String sigla = prof.getCurso().subSequence(0, 3) + "" + prof.getNivel().substring(0, 2);
		System.out.println("Arquivo Nome: " + arquivo.getName());
		System.out.println("Arquivo Caminho Absoluto: " + arquivo.getOriginalFilename());

		inserirArquivoNoServido(request, response, arquivo, false, descricao, sigla,prof.getTurma());
		System.out.println("Arquivo Caminho Absoluto: " + arquivo.getContentType());

		return "redirect:Professor-Material";
	}

	@PostMapping("/Professor-Avaliacao")
	public String professor_Avaliacao(Professor prof) {

		this.prof2 = prof;

		return "redirect:Professor-Avaliacao-2";
	}

	@PostMapping("/Professor-Avaliacao-2")
	public String professor_Avaliacao3(Model model, Professor prof) {

		this.prof2.setNotasDosAlunos(prof.getNotasDosAlunos());

		Salvar_SQL s = new Salvar_SQL();
		s.pode_Lancar_Nota_Ou_Prova(this.BD, this.prof2.getTurma(), this.prof2.getDesciplina(),
				this.prof2.getNotasDosAlunos(), this.prof2.getCurso());

		model.addAttribute("resultado", " Avaliações Lançadas Com Sucesso");
		return "redirect:Professor-Avaliacao";
	}

	@PostMapping("/Professor-Provas-2")
	public String professor_Avaliacao5(Model model, Professor prof) {

		this.prof2.setNotasDosAlunos(prof.getNotasDosAlunos());

		Salvar_SQL s = new Salvar_SQL();
		s.pode_Lancar_Nota_Ou_Prova(this.BD, this.prof2.getTurma(), this.prof2.getDesciplina(),
				this.prof2.getNotasDosAlunos(), this.prof2.getCurso());

		model.addAttribute("resultado", " Provas Lançadas Com Sucesso");
		return "redirect:Professor-Provas";
	}

	@PostMapping("/Professor-exane")
	public String professor_exame(Professor prof) {

		this.prof2 = prof;

		return "redirect:Professor-exane-2";
	}

	@PostMapping("/Professor-Turmas")
	public String professor_T(Professor prof) {

		this.prof2 = prof;

		return "redirect:Professor-Turmas-2";
	}

	@PostMapping("/Professor-exane-2")
	public String professor_exame2(Model model, Professor prof) {

		this.prof2.setNotasDosAlunos(prof.getNotasDosAlunos());

		Salvar_SQL s = new Salvar_SQL();
		s.pode_Lancar_Nota_Ou_Prova(this.BD, this.prof2.getTurma(), this.prof2.getDesciplina(),
				this.prof2.getNotasDosAlunos(), this.prof2.getCurso());

		model.addAttribute("resultado", " Exames Lançados Com Sucesso");
		return "redirect:Professor-exane";
	}

	@PostMapping({ "/Professor-Minha-Seguranca" })
	public String professor(@Validated RecuperarSenha recuperar, Model model) {

		String pagina = null;

		Salvar_SQL s = new Salvar_SQL();

		String senha = recuperar.getSenha();
		String palavra = recuperar.getPalavra();

		if ((senha.equalsIgnoreCase("")) || (senha == null)) {

		} else {

			s.sistema_Mudar_A_Senha(this.BD, senha, "professor", this.id, "", false);
		}

		if ((palavra.equalsIgnoreCase("")) || (palavra == null)) {

		} else {

			s.sistema_Mudar_A_Senha(this.BD, palavra, "professor", this.id, "", false);
		}

		if ((senha.equalsIgnoreCase("")) || (senha == null)) {

		} else {

			model.addAttribute("resultado", "A Senha Foi Alterada !");
		}

		if ((palavra.equalsIgnoreCase("")) || (palavra == null)) {

		} else {

			model.addAttribute("resultado", "A palavra Foi Configurada !");
		}

		pagina = "redirect:Professor-Minha-Seguranca";
		this.pagina = pagina;

		// nomeDaPagina= entrarNoSistema.quemEstaAFazerLogin(senha, model);
		return this.pagina;
	}

	@PostMapping("/Professor-Provas")
	public String professor_Avaliacao4(Professor prof) {

		this.prof2 = prof;
		

		return "redirect:Professor-Provas-2";
	}

	@GetMapping("/Professor-Provas")
	public String professor_frequencia(Model model) {

		String nomeDaPagina = null;
		estado = -5;
		if (entrarNoSistema.podeAbrirAPagina(senha)) {

			// if(acesso.bloqueandoRequisicoes(TechShine_Controller.request, request)) {

			Pesquisar_SQL p = new Pesquisar_SQL();
			Salvar_SQL s = new Salvar_SQL();

			int renda;
			String mes = s.mesActual(this.BD);

			renda = p.pesquisarUmConteudo_Numa_Linha_Int(this.BD, this.quem_E_O_func + "_financa", mes, "id", "",
					this.id);

			if (renda == 0) {
				model.addAttribute("renda", "Irregular");
			} else {
				model.addAttribute("renda", "Regular");
			}

			ArrayList<String> turmas = p.pesquisarTudoEmString_Restrito(this.BD, "disciplinas_dos_profs", "turma", "bi",
					this.bi, 0);

			int qalunos = 0;
			int contador = 0;

			Controle_ID_SQL cID = new Controle_ID_SQL();
			for (String turma : turmas) {

				qalunos = qalunos + cID.recuperarCodigo(this.BD, turma, "id");
				System.out.println("qalunos: " + qalunos);
				++contador;
			}

			ArrayList<String> desciplinas = p.pesquisarTudoEmString_Restrito(this.BD, "disciplinas_dos_profs",
					"desciplina", "bi", this.bi, 0);
			ArrayList<String> cursos = p.pesquisarTudoEmString_Restrito(this.BD, "disciplinas_dos_profs", "curso", "bi",
					this.bi, 0);
			// ArrayList<String> turnos =p.pesquisarTudoEmString_Restrito(this.BD,
			// "disciplinas_dos_profs", "turno", "bi", this.bi, 0);
			// ArrayList<String> niveis =p.pesquisarTudoEmString_Restrito(this.BD,
			// "disciplinas_dos_profs", "nivel", "bi", this.bi, 0);

			Date d = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			String data = sdf.format(d);

			model.addAttribute("qalunos", qalunos);
			model.addAttribute("qturmas", contador);
			model.addAttribute("data", data);
			model.addAttribute("mes", mes);

			model.addAttribute("cursos", cursos);
			model.addAttribute("turmas", turmas);
			model.addAttribute("desciplinas", desciplinas);

			model.addAttribute("nome", configurarNome);
			model.addAttribute("escola", configurarEscola);

			nomeDaPagina = "TechShine/Professor/escolha2";
			this.pagina = nomeDaPagina;
			// }

		} else {

			estado = 0;
			nomeDaPagina = "redirect:login";
			this.pagina = nomeDaPagina;
		}
		return this.pagina;
	}

	@GetMapping("/Professor-Provas-2")
	public String professor_frequencia6(Model model) {

		String nomeDaPagina = null;
		estado = -5;
		if (entrarNoSistema.podeAbrirAPagina(senha)) {

			// if(acesso.bloqueandoRequisicoes(TechShine_Controller.request, request)) {

			Pesquisar_SQL p = new Pesquisar_SQL();
			Salvar_SQL s = new Salvar_SQL();

			int renda;
			String mes = s.mesActual(this.BD);

			renda = p.pesquisarUmConteudo_Numa_Linha_Int(this.BD, this.quem_E_O_func + "_financa", mes, "id", "",
					this.id);

			if (renda == 0) {
				model.addAttribute("renda", "Irregular");
			} else {
				model.addAttribute("renda", "Regular");
			}

			p.pesquisarUmConteudo_Numa_Linha_String(this.BD, "disciplinas_dos_profs", "turma", "bi", this.bi, 0);
			ArrayList<String> turmas = p.pesquisarTudoEmString_Restrito(this.BD, "disciplinas_dos_profs", "turma", "bi",
					this.bi, 0);

			int qalunos = 0;
			int contador = 0;

			Controle_ID_SQL cID = new Controle_ID_SQL();
			for (String turma : turmas) {

				qalunos = qalunos + cID.recuperarCodigo(this.BD, turma, "id");
				System.out.println("qalunos: " + qalunos);
				++contador;
			}

			// ArrayList<String> desciplinas =p.pesquisarTudoEmString_Restrito(this.BD,
			// "disciplinas_dos_profs", "desciplina", "bi", this.bi, 0);
			// ArrayList<String> cursos =p.pesquisarTudoEmString_Restrito(this.BD,
			// "disciplinas_dos_profs", "curso", "bi", this.bi, 0);
			// ArrayList<String> turnos =p.pesquisarTudoEmString_Restrito(this.BD,
			// "disciplinas_dos_profs", "turno", "bi", this.bi, 0);
			// ArrayList<String> niveis =p.pesquisarTudoEmString_Restrito(this.BD,
			// "disciplinas_dos_profs", "nivel", "bi", this.bi, 0);

			Date d = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			String data = sdf.format(d);

			model.addAttribute("qalunos", qalunos);
			model.addAttribute("qturmas", contador);
			model.addAttribute("data", data);
			model.addAttribute("mes", mes);

			String turma = this.prof2.getTurma();
			this.prof2.setTurma(turma);

			ArrayList<String> alunos1 = p.pesquisarTudoEmString(this.BD, turma, "Alunos");
			ArrayList<String> alunos = new ArrayList<>();

			Verificar_Login v = new Verificar_Login();
			for (String cadaC : alunos1) {

				alunos.add(v.usuarioAcesso(v.acessoAWG(cadaC)));
			}
			
			
			
			
			

			model.addAttribute("alunos", alunos);
			
			
			model.addAttribute("curso", this.prof2.getCurso());
			model.addAttribute("turma", this.prof2.getTurma());
			model.addAttribute("turma", this.prof2.getTurma());
			model.addAttribute("nome", configurarNome);
			model.addAttribute("escola", configurarEscola);

			nomeDaPagina = "TechShine/Professor/frequencia";
			this.pagina = nomeDaPagina;
			// }

		} else {

			estado = 0;
			nomeDaPagina = "redirect:login";
			this.pagina = nomeDaPagina;
		}
		return this.pagina;
	}

	@GetMapping("/Professor-Turmas-2")
	public String professor_tu(Model model) {

		String nomeDaPagina = null;
		estado = -5;
		if (entrarNoSistema.podeAbrirAPagina(senha)) {

			// if(acesso.bloqueandoRequisicoes(TechShine_Controller.request, request)) {

			Pesquisar_SQL p = new Pesquisar_SQL();
			Salvar_SQL s = new Salvar_SQL();
			Tabela_Actualizar_SQL ta = new Tabela_Actualizar_SQL();

			int renda;
			String mes = s.mesActual(this.BD);

			renda = p.pesquisarUmConteudo_Numa_Linha_Int(this.BD, this.quem_E_O_func + "_financa", mes, "id", "",
					this.id);

			if (renda == 0) {
				model.addAttribute("renda", "Irregular");
			} else {
				model.addAttribute("renda", "Regular");
			}

			p.pesquisarUmConteudo_Numa_Linha_String(this.BD, "disciplinas_dos_profs", "turma", "bi", this.bi, 0);
			ArrayList<String> turmas = p.pesquisarTudoEmString_Restrito(this.BD, "disciplinas_dos_profs", "turma", "bi",
					this.bi, 0);

			int qalunos = 0;
			int contador = 0;

			Controle_ID_SQL cID = new Controle_ID_SQL();
			for (String turma : turmas) {

				qalunos = qalunos + cID.recuperarCodigo(this.BD, turma, "id");
				System.out.println("qalunos: " + qalunos);
				++contador;
			}

			// ArrayList<String> desciplinas =p.pesquisarTudoEmString_Restrito(this.BD,
			// "disciplinas_dos_profs", "desciplina", "bi", this.bi, 0);
			// ArrayList<String> cursos =p.pesquisarTudoEmString_Restrito(this.BD,
			// "disciplinas_dos_profs", "curso", "bi", this.bi, 0);
			// ArrayList<String> turnos =p.pesquisarTudoEmString_Restrito(this.BD,
			// "disciplinas_dos_profs", "turno", "bi", this.bi, 0);
			// ArrayList<String> niveis =p.pesquisarTudoEmString_Restrito(this.BD,
			// "disciplinas_dos_profs", "nivel", "bi", this.bi, 0);

			Date d = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			String data = sdf.format(d);

			String turma = this.prof2.getTurma();
			String desciplina = this.prof2.getDesciplina();

			desciplina = ta.tirarCaracteres(desciplina);
			ArrayList<String> alunos = p.pesquisarTudoEmString(this.BD, turma, "Alunos");
			ArrayList<Integer> avaliacao = p.pesquisarTudoEmInt(this.BD, turma + "_Avaliacao", desciplina);
			ArrayList<Integer> prova = p.pesquisarTudoEmInt(this.BD, turma + "_Prova", desciplina);
			ArrayList<Integer> media = p.pesquisarTudoEmInt(this.BD, turma + "_Media", desciplina);
			ArrayList<String> situacao = p.pesquisarTudoEmString(this.BD, turma + "_Media", "Situacao");

			Verificar_Login v = new Verificar_Login();
			String n;
			ArrayList<Aluno> alunos2 = new ArrayList<>();

			for (int i = 0; i < alunos.size(); i++) {

				Aluno aluno = new Aluno();

				if ((alunos.size() > 0) && (i < alunos.size())) {

					n = v.usuarioAcesso(v.acessoAWG(alunos.get(i)));
					aluno.setNome(n);

				}

				if ((prova.size() > 0) && (i < prova.size())) {

					aluno.setProva(prova.get(i));

				}
				if ((avaliacao.size() > 0) && (i < avaliacao.size())) {

					aluno.setAvaliacao(avaliacao.get(i));

				}

				if ((media.size() > 0) && (i < media.size())) {

					aluno.setMedia(media.get(i));

				}

				if ((situacao.size() > 0) && (i < situacao.size())) {

					aluno.setSituacao(situacao.get(i));

				}

				alunos2.add(aluno);
			}

			model.addAttribute("qalunos", qalunos);
			model.addAttribute("qturmas", contador);
			model.addAttribute("data", data);
			model.addAttribute("mes", mes);
			model.addAttribute("alunos", alunos2);

			model.addAttribute("nome", configurarNome);
			model.addAttribute("escola", configurarEscola);

			nomeDaPagina = "TechShine/Professor/minhas-turmas";
			this.pagina = nomeDaPagina;
			// }

		} else {

			estado = 0;
			nomeDaPagina = "redirect:login";
			this.pagina = nomeDaPagina;
		}
		return this.pagina;
	}

	@GetMapping("/Professor-Turmas")
	public String professor_Turmas(Model model) {

		String nomeDaPagina = null;
		estado = -5;
		if (entrarNoSistema.podeAbrirAPagina(senha)) {

			// if(acesso.bloqueandoRequisicoes(TechShine_Controller.request, request)) {

			Pesquisar_SQL p = new Pesquisar_SQL();
			Salvar_SQL s = new Salvar_SQL();

			int renda;
			String mes = s.mesActual(this.BD);

			renda = p.pesquisarUmConteudo_Numa_Linha_Int(this.BD, this.quem_E_O_func + "_financa", mes, "id", "",
					this.id);

			if (renda == 0) {
				model.addAttribute("renda", "Irregular");
			} else {
				model.addAttribute("renda", "Regular");
			}

			p.pesquisarUmConteudo_Numa_Linha_String(this.BD, "disciplinas_dos_profs", "turma", "bi", this.bi, 0);
			ArrayList<String> turmas = p.pesquisarTudoEmString_Restrito(this.BD, "disciplinas_dos_profs", "turma", "bi",
					this.bi, 0);

			int qalunos = 0;
			int contador = 0;

			Controle_ID_SQL cID = new Controle_ID_SQL();
			for (String turma : turmas) {

				qalunos = qalunos + cID.recuperarCodigo(this.BD, turma, "id");
				System.out.println("qalunos: " + qalunos);
				++contador;
			}

			ArrayList<String> desciplinas = p.pesquisarTudoEmString_Restrito(this.BD, "disciplinas_dos_profs",
					"desciplina", "bi", this.bi, 0);
			ArrayList<String> cursos = p.pesquisarTudoEmString_Restrito(this.BD, "disciplinas_dos_profs", "curso", "bi",
					this.bi, 0);
			// ArrayList<String> turnos =p.pesquisarTudoEmString_Restrito(this.BD,
			// "disciplinas_dos_profs", "turno", "bi", this.bi, 0);
			// ArrayList<String> niveis =p.pesquisarTudoEmString_Restrito(this.BD,
			// "disciplinas_dos_profs", "nivel", "bi", this.bi, 0);

			Date d = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			String data = sdf.format(d);

			model.addAttribute("qalunos", qalunos);
			model.addAttribute("qturmas", contador);
			model.addAttribute("data", data);
			model.addAttribute("mes", mes);
			model.addAttribute("cursos", cursos);
			model.addAttribute("turmas", turmas);
			model.addAttribute("desciplinas", desciplinas);

			model.addAttribute("nome", configurarNome);
			model.addAttribute("escola", configurarEscola);

			nomeDaPagina = "TechShine/Professor/minhaTurma1";
			this.pagina = nomeDaPagina;

			// }

		} else {

			estado = 0;
			nomeDaPagina = "redirect:login";
			this.pagina = nomeDaPagina;
		}
		return this.pagina;

	}

	@GetMapping("/Professor-Avaliacao")
	public String Prof_nota_E_avaliacao(Model model) {

		String nomeDaPagina = null;
		estado = -5;
		if (entrarNoSistema.podeAbrirAPagina(senha)) {

			// if(acesso.bloqueandoRequisicoes(TechShine_Controller.request, request)) {

			Pesquisar_SQL p = new Pesquisar_SQL();
			Salvar_SQL s = new Salvar_SQL();

			int renda;
			String mes = s.mesActual(this.BD);

			renda = p.pesquisarUmConteudo_Numa_Linha_Int(this.BD, this.quem_E_O_func + "_financa", mes, "id", "",
					this.id);

			if (renda == 0) {
				model.addAttribute("renda", "Irregular");
			} else {
				model.addAttribute("renda", "Regular");
			}

			p.pesquisarUmConteudo_Numa_Linha_String(this.BD, "disciplinas_dos_profs", "turma", "bi", this.bi, 0);
			ArrayList<String> turmas = p.pesquisarTudoEmString_Restrito(this.BD, "disciplinas_dos_profs", "turma", "bi",
					this.bi, 0);

			int qalunos = 0;
			int contador = 0;

			Controle_ID_SQL cID = new Controle_ID_SQL();
			for (String turma : turmas) {

				qalunos = qalunos + cID.recuperarCodigo(this.BD, turma, "id");
				System.out.println("qalunos: " + qalunos);
				++contador;
			}

			ArrayList<String> desciplinas = p.pesquisarTudoEmString_Restrito(this.BD, "disciplinas_dos_profs",
					"desciplina", "bi", this.bi, 0);
			ArrayList<String> cursos = p.pesquisarTudoEmString_Restrito(this.BD, "disciplinas_dos_profs", "curso", "bi",
					this.bi, 0);
			// ArrayList<String> turnos =p.pesquisarTudoEmString_Restrito(this.BD,
			// "disciplinas_dos_profs", "turno", "bi", this.bi, 0);
			// ArrayList<String> niveis =p.pesquisarTudoEmString_Restrito(this.BD,
			// "disciplinas_dos_profs", "nivel", "bi", this.bi, 0);

			Date d = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			String data = sdf.format(d);

			model.addAttribute("qalunos", qalunos);
			model.addAttribute("qturmas", contador);
			model.addAttribute("data", data);
			model.addAttribute("mes", mes);
			model.addAttribute("cursos", cursos);
			model.addAttribute("turmas", turmas);
			model.addAttribute("desciplinas", desciplinas);

			model.addAttribute("nome", configurarNome);
			model.addAttribute("escola", configurarEscola);
			nomeDaPagina = "TechShine/Professor/escolha";
			this.pagina = nomeDaPagina;
			// }

		} else {

			estado = 0;
			nomeDaPagina = "redirect:login";
			this.pagina = nomeDaPagina;
		}
		return this.pagina;

	}

	@GetMapping("/Professor-exane")
	public String Prof_nota_E(Model model) {

		String nomeDaPagina = null;
		estado = -5;
		if (entrarNoSistema.podeAbrirAPagina(senha)) {

			// if(acesso.bloqueandoRequisicoes(TechShine_Controller.request, request)) {

			Pesquisar_SQL p = new Pesquisar_SQL();
			Salvar_SQL s = new Salvar_SQL();

			int renda;
			String mes = s.mesActual(this.BD);

			renda = p.pesquisarUmConteudo_Numa_Linha_Int(this.BD, this.quem_E_O_func + "_financa", mes, "id", "",
					this.id);

			if (renda == 0) {
				model.addAttribute("renda", "Irregular");
			} else {
				model.addAttribute("renda", "Regular");
			}

			p.pesquisarUmConteudo_Numa_Linha_String(this.BD, "disciplinas_dos_profs", "turma", "bi", this.bi, 0);
			ArrayList<String> turmas = p.pesquisarTudoEmString_Restrito(this.BD, "disciplinas_dos_profs", "turma", "bi",
					this.bi, 0);

			int qalunos = 0;
			int contador = 0;

			Controle_ID_SQL cID = new Controle_ID_SQL();
			for (String turma : turmas) {

				qalunos = qalunos + cID.recuperarCodigo(this.BD, turma, "id");
				System.out.println("qalunos: " + qalunos);
				++contador;
			}

			ArrayList<String> desciplinas = p.pesquisarTudoEmString_Restrito(this.BD, "disciplinas_dos_profs",
					"desciplina", "bi", this.bi, 0);
			ArrayList<String> cursos = p.pesquisarTudoEmString_Restrito(this.BD, "disciplinas_dos_profs", "curso", "bi",
					this.bi, 0);
			// ArrayList<String> turnos =p.pesquisarTudoEmString_Restrito(this.BD,
			// "disciplinas_dos_profs", "turno", "bi", this.bi, 0);
			// ArrayList<String> niveis =p.pesquisarTudoEmString_Restrito(this.BD,
			// "disciplinas_dos_profs", "nivel", "bi", this.bi, 0);

			Date d = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			String data = sdf.format(d);

			model.addAttribute("qalunos", qalunos);
			model.addAttribute("qturmas", contador);
			model.addAttribute("data", data);
			model.addAttribute("mes", mes);
			model.addAttribute("cursos", cursos);
			model.addAttribute("turmas", turmas);
			model.addAttribute("desciplinas", desciplinas);

			model.addAttribute("nome", configurarNome);
			model.addAttribute("escola", configurarEscola);
			nomeDaPagina = "TechShine/Professor/exame";
			this.pagina = nomeDaPagina;
			// }

		} else {

			estado = 0;
			nomeDaPagina = "redirect:login";
			this.pagina = nomeDaPagina;
		}
		return this.pagina;

	}

	@GetMapping("/Professor-exane-2")
	public String Prof_nota_E2(Model model) {

		String nomeDaPagina = null;
		estado = -5;
		if (entrarNoSistema.podeAbrirAPagina(senha)) {

			// if(acesso.bloqueandoRequisicoes(TechShine_Controller.request, request)) {

			Pesquisar_SQL p = new Pesquisar_SQL();
			Salvar_SQL s = new Salvar_SQL();

			int renda;
			String mes = s.mesActual(this.BD);

			renda = p.pesquisarUmConteudo_Numa_Linha_Int(this.BD, this.quem_E_O_func + "_financa", mes, "id", "",
					this.id);

			if (renda == 0) {
				model.addAttribute("renda", "Irregular");
			} else {
				model.addAttribute("renda", "Regular");
			}

			p.pesquisarUmConteudo_Numa_Linha_String(this.BD, "disciplinas_dos_profs", "turma", "bi", this.bi, 0);
			ArrayList<String> turmas = p.pesquisarTudoEmString_Restrito(this.BD, "disciplinas_dos_profs", "turma", "bi",
					this.bi, 0);

			int qalunos = 0;
			int contador = 0;

			Controle_ID_SQL cID = new Controle_ID_SQL();
			for (String turma : turmas) {

				qalunos = qalunos + cID.recuperarCodigo(this.BD, turma, "id");
				System.out.println("qalunos: " + qalunos);
				++contador;
			}

			// ArrayList<String> desciplinas =p.pesquisarTudoEmString_Restrito(this.BD,
			// "disciplinas_dos_profs", "desciplina", "bi", this.bi, 0);
			// ArrayList<String> cursos =p.pesquisarTudoEmString_Restrito(this.BD,
			// "disciplinas_dos_profs", "curso", "bi", this.bi, 0);
			// ArrayList<String> turnos =p.pesquisarTudoEmString_Restrito(this.BD,
			// "disciplinas_dos_profs", "turno", "bi", this.bi, 0);
			// ArrayList<String> niveis =p.pesquisarTudoEmString_Restrito(this.BD,
			// "disciplinas_dos_profs", "nivel", "bi", this.bi, 0);

			Date d = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			String data = sdf.format(d);

			model.addAttribute("qalunos", qalunos);
			model.addAttribute("qturmas", contador);
			model.addAttribute("data", data);
			model.addAttribute("mes", mes);

			String turma = this.prof2.getTurma();
			this.prof2.setTurma(turma);

			ArrayList<String> alunos1 = p.pesquisarTudoEmString(this.BD, turma, "Alunos");
			ArrayList<String> alunos = new ArrayList<>();

			Verificar_Login v = new Verificar_Login();
			for (String cadaC : alunos1) {

				alunos.add(v.usuarioAcesso(v.acessoAWG(cadaC)));
			}

			model.addAttribute("alunos", alunos);

			model.addAttribute("nome", configurarNome);
			model.addAttribute("escola", configurarEscola);
			nomeDaPagina = "TechShine/Professor/exame2";
			this.pagina = nomeDaPagina;
			// }

		} else {

			estado = 0;
			nomeDaPagina = "redirect:login";
			this.pagina = nomeDaPagina;
		}
		return this.pagina;

	}

	@GetMapping("/Professor-Avaliacao-2")
	public String Prof_nota_E_avaliacao2(Model model) {

		String nomeDaPagina = null;
		estado = -5;
		if (entrarNoSistema.podeAbrirAPagina(senha)) {

			// if(acesso.bloqueandoRequisicoes(TechShine_Controller.request, request)) {

			Pesquisar_SQL p = new Pesquisar_SQL();
			Salvar_SQL s = new Salvar_SQL();

			int renda;
			String mes = s.mesActual(this.BD);

			renda = p.pesquisarUmConteudo_Numa_Linha_Int(this.BD, this.quem_E_O_func + "_financa", mes, "id", "",
					this.id);

			if (renda == 0) {
				model.addAttribute("renda", "Irregular");
			} else {
				model.addAttribute("renda", "Regular");
			}

			p.pesquisarUmConteudo_Numa_Linha_String(this.BD, "disciplinas_dos_profs", "turma", "bi", this.bi, 0);
			ArrayList<String> turmas = p.pesquisarTudoEmString_Restrito(this.BD, "disciplinas_dos_profs", "turma", "bi",
					this.bi, 0);

			int qalunos = 0;
			int contador = 0;

			Controle_ID_SQL cID = new Controle_ID_SQL();
			for (String turma : turmas) {

				qalunos = qalunos + cID.recuperarCodigo(this.BD, turma, "id");
				System.out.println("qalunos: " + qalunos);
				++contador;
			}

			// ArrayList<String> desciplinas =p.pesquisarTudoEmString_Restrito(this.BD,
			// "disciplinas_dos_profs", "desciplina", "bi", this.bi, 0);
			// ArrayList<String> cursos =p.pesquisarTudoEmString_Restrito(this.BD,
			// "disciplinas_dos_profs", "curso", "bi", this.bi, 0);
			// ArrayList<String> turnos =p.pesquisarTudoEmString_Restrito(this.BD,
			// "disciplinas_dos_profs", "turno", "bi", this.bi, 0);
			// ArrayList<String> niveis =p.pesquisarTudoEmString_Restrito(this.BD,
			// "disciplinas_dos_profs", "nivel", "bi", this.bi, 0);

			Date d = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			String data = sdf.format(d);

			model.addAttribute("qalunos", qalunos);
			model.addAttribute("qturmas", contador);
			model.addAttribute("data", data);
			model.addAttribute("mes", mes);

			String turma = this.prof2.getTurma();
			this.prof2.setTurma(turma);

			ArrayList<String> alunos1 = p.pesquisarTudoEmString(this.BD, turma, "Alunos");
			ArrayList<String> alunos = new ArrayList<>();

			Verificar_Login v = new Verificar_Login();
			for (String cadaC : alunos1) {

				alunos.add(v.usuarioAcesso(v.acessoAWG(cadaC)));
			}

			model.addAttribute("alunos", alunos);

			model.addAttribute("nome", configurarNome);
			model.addAttribute("escola", configurarEscola);
			nomeDaPagina = "TechShine/Professor/nota-avaliacao";
			this.pagina = nomeDaPagina;
			// }

		} else {

			estado = 0;
			nomeDaPagina = "redirect:login";
			this.pagina = nomeDaPagina;
		}
		return this.pagina;

	}

	@GetMapping("/Professor-Minha-Seguranca")
	public String prof_Seguranca(Model model) {

		String nomeDaPagina = null;
		estado = -5;
		if (entrarNoSistema.podeAbrirAPagina(senha)) {

			// if(acesso.bloqueandoRequisicoes(TechShine_Controller.request, request)) {

			Pesquisar_SQL p = new Pesquisar_SQL();
			Salvar_SQL s = new Salvar_SQL();

			int renda;
			String mes = s.mesActual(this.BD);

			renda = p.pesquisarUmConteudo_Numa_Linha_Int(this.BD, this.quem_E_O_func + "_financa", mes, "id", "",
					this.id);

			if (renda == 0) {
				model.addAttribute("renda", "Irregular");
			} else {
				model.addAttribute("renda", "Regular");
			}

			p.pesquisarUmConteudo_Numa_Linha_String(this.BD, "disciplinas_dos_profs", "turma", "bi", this.bi, 0);
			ArrayList<String> turmas = p.pesquisarTudoEmString_Restrito(this.BD, "disciplinas_dos_profs", "turma", "bi",
					this.bi, 0);

			int qalunos = 0;
			int contador = 0;

			Controle_ID_SQL cID = new Controle_ID_SQL();
			for (String turma : turmas) {

				qalunos = qalunos + cID.recuperarCodigo(this.BD, turma, "id");
				System.out.println("qalunos: " + qalunos);
				++contador;
			}

			// ArrayList<String> desciplinas =p.pesquisarTudoEmString_Restrito(this.BD,
			// "disciplinas_dos_profs", "desciplina", "bi", this.bi, 0);
			// ArrayList<String> cursos =p.pesquisarTudoEmString_Restrito(this.BD,
			// "disciplinas_dos_profs", "curso", "bi", this.bi, 0);
			// ArrayList<String> turnos =p.pesquisarTudoEmString_Restrito(this.BD,
			// "disciplinas_dos_profs", "turno", "bi", this.bi, 0);
			// ArrayList<String> niveis =p.pesquisarTudoEmString_Restrito(this.BD,
			// "disciplinas_dos_profs", "nivel", "bi", this.bi, 0);

			Date d = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			String data = sdf.format(d);

			model.addAttribute("qalunos", qalunos);
			model.addAttribute("qturmas", contador);
			model.addAttribute("data", data);
			model.addAttribute("mes", mes);

			model.addAttribute("nome", configurarNome);
			model.addAttribute("escola", configurarEscola);

			nomeDaPagina = "TechShine/Professor/security";
			this.pagina = nomeDaPagina;
			// }

		} else {

			estado = 0;
			nomeDaPagina = "redirect:login";
			this.pagina = nomeDaPagina;
		}
		return this.pagina;

	}

	@GetMapping("/Professor-Minha-Financa")
	public String prof_Financa() {

		String nomeDaPagina = null;
		estado = -5;
		if (entrarNoSistema.podeAbrirAPagina(senha)) {

			// if(acesso.bloqueandoRequisicoes(TechShine_Controller.request, request)) {

			nomeDaPagina = "TechShine/Professor/financa";
			this.pagina = nomeDaPagina;
			// }

		} else {

			estado = 0;
			nomeDaPagina = "redirect:login";
			this.pagina = nomeDaPagina;
		}
		return this.pagina;

	}

	// Area Do DG

	// ================================
	// TERMINAR SESSAO, DEFINICAO E FINANCA NA WEBgENIUS

	// Area de Tesouraria

	@GetMapping({ "/Tesouraria", "/Tesouraria-Pagar-Propina", "/Tesouraria-Pagar-Matricula",
			"/Tesouraria-Pagar-Documento", "/Tesouraria-Pagar-Estagio", "/Tesouraria-Pagar-Material",
			"/Tesouraria-Pagar-Falta", "/Tesouraria-Minha-Financa", "/Tesouraria-Minha-Seguranca",
			"/Tesouraria-Pagar-Confirmacao", "/Propina" })
	public String tesouraria(HttpServletRequest request, Model model) {

		String requisicao;
		String nomeDaPagina = null;
		estado = -5;
		requisicao = request.getRequestURI();

		if (requisicao.equalsIgnoreCase("/Tesouraria")) {

			System.out.println("Request2: " + request.getRequestURI());
			System.out.println("Senha: " + senha);
			if (entrarNoSistema.podeAbrirAPagina(senha)) {

				// if(acesso.bloqueandoRequisicoes(TechShine_Controller.request, request)) {

				Pesquisar_SQL p = new Pesquisar_SQL();
				Salvar_SQL s = new Salvar_SQL();

				String mes = s.mesActual(this.BD);

				int renda = p.pesquisarUmConteudo_Numa_Linha_Int(this.BD, this.quem_E_O_func + "_financa", mes, "id",
						"", this.id);
				int p2 = p.pesquisarUmConteudo_Numa_Linha_Int(this.BD, "func_diario", "Prop", "bi", this.bi, 0);
				int mc = p.pesquisarUmConteudo_Numa_Linha_Int(this.BD, "func_diario", "MatEConf", "bi", this.bi, 0);
				int os = p.pesquisarUmConteudo_Numa_Linha_Int(this.BD, "func_diario", "servicos", "bi", this.bi, 0);

				if (renda == 0) {
					model.addAttribute("renda", "Irregular");
				} else {
					model.addAttribute("renda", "Regular");
				}

				model.addAttribute("p", p2);
				model.addAttribute("mc", mc);
				model.addAttribute("os", os);
				model.addAttribute("nome", configurarNome);
				model.addAttribute("escola", configurarEscola);

				nomeDaPagina = "WebGnius/tesouraria/inicio";
				this.pagina = nomeDaPagina;
				// }
			} else {

				estado = 0;
				nomeDaPagina = "redirect:login";
				this.pagina = nomeDaPagina;
			}

		} else if (requisicao.equalsIgnoreCase("/Tesouraria-Pagar-Propina")) {

			if (entrarNoSistema.podeAbrirAPagina(senha)) {

				Pesquisar_SQL p = new Pesquisar_SQL();
				Salvar_SQL s = new Salvar_SQL();

				String mes = s.mesActual(this.BD);

				int renda = p.pesquisarUmConteudo_Numa_Linha_Int(this.BD, this.quem_E_O_func + "_financa", mes, "id",
						"", this.id);
				int p2 = p.pesquisarUmConteudo_Numa_Linha_Int(this.BD, "func_diario", "Prop", "bi", this.bi, 0);
				int mc = p.pesquisarUmConteudo_Numa_Linha_Int(this.BD, "func_diario", "MatEConf", "bi", this.bi, 0);
				int os = p.pesquisarUmConteudo_Numa_Linha_Int(this.BD, "func_diario", "servicos", "bi", this.bi, 0);

				if (renda == 0) {
					model.addAttribute("renda", "Irregular");
				} else {
					model.addAttribute("renda", "Regular");
				}

				ArrayList<Curso> cursos = p.Listar_Cursos(this.BD);
				ArrayList<String> tCursos = new ArrayList<>();

				for (Curso c : cursos) {

					tCursos.add(c.getNome());
				}
				ArrayList<String> niveis = p.pesquisarTudoEmString(this.BD, "infoescola", "niveis");

				
				ArrayList<String> turmasManha = p.pesquisarTudoEmString(this.BD, "controle_turmas", "Manha");
				ArrayList<String> turmasTarde = p.pesquisarTudoEmString(this.BD, "controle_turmas", "Tarde");
				ArrayList<String> turmasNoite = p.pesquisarTudoEmString(this.BD, "controle_turmas", "Noite");

				ArrayList<ArrayList<String>> todasTurmas = new ArrayList<>();

				todasTurmas.add(turmasManha);
				todasTurmas.add(turmasTarde);
				todasTurmas.add(turmasNoite);

				ArrayList<String> alunosManha = new ArrayList<>();
				ArrayList<String> alunosTarde = new ArrayList<>();
				ArrayList<String> alunosNoite = new ArrayList<>();
				ArrayList<Integer> todos_Processos = new ArrayList<>();

				int contador = 0;
				for (ArrayList<String> cadaTurno : todasTurmas) {

					++contador;
					for (String turma : cadaTurno) {

						ArrayList<String> alunos = p.pesquisarTudoEmString(this.BD, turma, "Alunos");
						ArrayList<Integer> NProc = p.pesquisarTudoEmInt(this.BD, turma, "NProc");
						
						for (Integer nproc : NProc) {
							
							
							todos_Processos.add(nproc);
						}
						

						if (contador == 1) {
							System.out.println("Manha: ");
							for (String al : alunos) {

								System.out.println("Aluno: " + al);
								alunosManha.add(al);
							}
						} else if (contador == 2) {
							System.out.println("Tarde: ");
							for (String al : alunos) {

								System.out.println("Aluno: " + al);
								alunosTarde.add(al);
							}
						} else if (contador == 3) {

							System.out.println("Noite: ");
							for (String al : alunos) {

								System.out.println("Aluno: " + al);
								alunosNoite.add(al);
							}
						}
					}

				}
				
				
				
				
				model.addAttribute("processos", todos_Processos);
				model.addAttribute("manha", alunosManha);
				model.addAttribute("tarde", alunosTarde);
				model.addAttribute("noite", alunosNoite);

				model.addAttribute("p", p2);
				model.addAttribute("cursos", tCursos);
				model.addAttribute("niveis", niveis);
				model.addAttribute("mc", mc);
				model.addAttribute("os", os);
				model.addAttribute("nome", configurarNome);
				model.addAttribute("escola", configurarEscola);

				// if(acesso.bloqueandoRequisicoes(TechShine_Controller.request, request)) {
				nomeDaPagina = "WebGnius/tesouraria/propina";

				this.pagina = nomeDaPagina;
				// }

			} else {
				estado = 0;
				nomeDaPagina = "redirect:login";
				this.pagina = nomeDaPagina;
			}

		} else if (requisicao.equalsIgnoreCase("/Tesouraria-Pagar-Matricula")) {

			if (entrarNoSistema.podeAbrirAPagina(senha)) {

				// if(acesso.bloqueandoRequisicoes(TechShine_Controller.request, request)) {

				Pesquisar_SQL p = new Pesquisar_SQL();
				Salvar_SQL s = new Salvar_SQL();

				String mes = s.mesActual(this.BD);

				int renda = p.pesquisarUmConteudo_Numa_Linha_Int(this.BD, this.quem_E_O_func + "_financa", mes, "id",
						"", this.id);
				int p2 = p.pesquisarUmConteudo_Numa_Linha_Int(this.BD, "func_diario", "Prop", "bi", this.bi, 0);
				int mc = p.pesquisarUmConteudo_Numa_Linha_Int(this.BD, "func_diario", "MatEConf", "bi", this.bi, 0);
				int os = p.pesquisarUmConteudo_Numa_Linha_Int(this.BD, "func_diario", "servicos", "bi", this.bi, 0);

				if (renda == 0) {
					model.addAttribute("renda", "Irregular");
				} else {
					model.addAttribute("renda", "Regular");
				}

				ArrayList<String> docs = p.pesquisarTudoEmString(this.BD, "Escola_Financa", "Matricula");
				ArrayList<Integer> preco = p.pesquisarTudoEmInt(this.BD, "Escola_Financa", "precoMatricula");

				ArrayList<String> tCursos = new ArrayList<>();

				String conteudo = "";
				int preco2 = 0;
				for (int i = 0; i < docs.size(); i++) {

					if ((docs.size() > 0) && (i < docs.size())) {
						conteudo = docs.get(i);
					}

					if ((preco.size() > 0) && (i < preco.size())) {
						preco2 = preco.get(i);
					}

					tCursos.add(conteudo + " " + preco2 + ",00Kz");
				}

				model.addAttribute("matriculas", tCursos);
				model.addAttribute("p", p2);
				model.addAttribute("mc", mc);
				model.addAttribute("os", os);
				model.addAttribute("nome", configurarNome);
				model.addAttribute("escola", configurarEscola);

				nomeDaPagina = "WebGnius/tesouraria/matricula";

				this.pagina = nomeDaPagina;
				// }

			} else {

				estado = 0;
				nomeDaPagina = "redirect:login";
				this.pagina = nomeDaPagina;
			}

		} else if (requisicao.equalsIgnoreCase("/Tesouraria-Pagar-Documento")) {

			if (entrarNoSistema.podeAbrirAPagina(senha)) {

				// if(acesso.bloqueandoRequisicoes(TechShine_Controller.request, request)) {

				Pesquisar_SQL p = new Pesquisar_SQL();
				Salvar_SQL s = new Salvar_SQL();

				String mes = s.mesActual(this.BD);

				int renda = p.pesquisarUmConteudo_Numa_Linha_Int(this.BD, this.quem_E_O_func + "_financa", mes, "id",
						"", this.id);
				int p2 = p.pesquisarUmConteudo_Numa_Linha_Int(this.BD, "func_diario", "Prop", "bi", this.bi, 0);
				int mc = p.pesquisarUmConteudo_Numa_Linha_Int(this.BD, "func_diario", "MatEConf", "bi", this.bi, 0);
				int os = p.pesquisarUmConteudo_Numa_Linha_Int(this.BD, "func_diario", "servicos", "bi", this.bi, 0);

				if (renda == 0) {
					model.addAttribute("renda", "Irregular");
				} else {
					model.addAttribute("renda", "Regular");
				}

				ArrayList<String> docs = p.pesquisarTudoEmString(this.BD, "Escola_Financa", "Docs");
				ArrayList<Integer> preco = p.pesquisarTudoEmInt(this.BD, "Escola_Financa", "precoDoc");

				ArrayList<String> tCursos = new ArrayList<>();
				String conteudo = "";
				int preco2 = 0;
				for (int i = 0; i < docs.size(); i++) {

					if ((docs.size() > 0) && (i < docs.size())) {
						conteudo = docs.get(i);
					}

					if ((preco.size() > 0) && (i < preco.size())) {
						preco2 = preco.get(i);
					}

					tCursos.add(conteudo + " " + preco2 + ",00Kz");
				}

				model.addAttribute("documentos", tCursos);

				model.addAttribute("p", p2);
				model.addAttribute("mc", mc);
				model.addAttribute("os", os);
				model.addAttribute("nome", configurarNome);
				model.addAttribute("escola", configurarEscola);

				nomeDaPagina = "WebGnius/tesouraria/documentos";

				this.pagina = nomeDaPagina;
				// }

			} else {

				estado = 0;
				nomeDaPagina = "redirect:login";
				this.pagina = nomeDaPagina;
			}

		} else if (requisicao.equalsIgnoreCase("/Tesouraria-Pagar-Estagio")) {

			if (entrarNoSistema.podeAbrirAPagina(senha)) {

				// if(acesso.bloqueandoRequisicoes(TechShine_Controller.request, request)) {

				Pesquisar_SQL p = new Pesquisar_SQL();
				Salvar_SQL s = new Salvar_SQL();

				String mes = s.mesActual(this.BD);

				int renda = p.pesquisarUmConteudo_Numa_Linha_Int(this.BD, this.quem_E_O_func + "_financa", mes, "id",
						"", this.id);
				int p2 = p.pesquisarUmConteudo_Numa_Linha_Int(this.BD, "func_diario", "Prop", "bi", this.bi, 0);
				int mc = p.pesquisarUmConteudo_Numa_Linha_Int(this.BD, "func_diario", "MatEConf", "bi", this.bi, 0);
				int os = p.pesquisarUmConteudo_Numa_Linha_Int(this.BD, "func_diario", "servicos", "bi", this.bi, 0);

				if (renda == 0) {
					model.addAttribute("renda", "Irregular");
				} else {
					model.addAttribute("renda", "Regular");
				}

				ArrayList<Integer> estagio = p.pesquisarTudoEmInt(this.BD, "Escola_Financa", "Estagio");
				int estagio2 = 0;
				if (estagio.size() > 0) {

					estagio2 = estagio.get(0);
				}

				model.addAttribute("estagio", estagio2 + ",00 Kz");

				model.addAttribute("p", p2);
				model.addAttribute("mc", mc);
				model.addAttribute("os", os);
				model.addAttribute("nome", configurarNome);
				model.addAttribute("escola", configurarEscola);

				nomeDaPagina = "WebGnius/tesouraria/estagio";

				this.pagina = nomeDaPagina;
				// }

			} else {

				estado = 0;
				nomeDaPagina = "redirect:login";
				this.pagina = nomeDaPagina;
			}

		} else if (requisicao.equalsIgnoreCase("/Tesouraria-Pagar-Falta")) {

			if (entrarNoSistema.podeAbrirAPagina(senha)) {

				// if(acesso.bloqueandoRequisicoes(TechShine_Controller.request, request)) {

				Pesquisar_SQL p = new Pesquisar_SQL();
				Salvar_SQL s = new Salvar_SQL();

				String mes = s.mesActual(this.BD);

				int renda = p.pesquisarUmConteudo_Numa_Linha_Int(this.BD, this.quem_E_O_func + "_financa", mes, "id",
						"", this.id);
				int p2 = p.pesquisarUmConteudo_Numa_Linha_Int(this.BD, "func_diario", "Prop", "bi", this.bi, 0);
				int mc = p.pesquisarUmConteudo_Numa_Linha_Int(this.BD, "func_diario", "MatEConf", "bi", this.bi, 0);
				int os = p.pesquisarUmConteudo_Numa_Linha_Int(this.BD, "func_diario", "servicos", "bi", this.bi, 0);

				if (renda == 0) {
					model.addAttribute("renda", "Irregular");
				} else {
					model.addAttribute("renda", "Regular");
				}

				ArrayList<String> docs = p.pesquisarTudoEmString(this.BD, "Escola_Financa", "Faltas");
				ArrayList<Integer> preco = p.pesquisarTudoEmInt(this.BD, "Escola_Financa", "precoFalta");

				ArrayList<String> tCursos = new ArrayList<>();
				String conteudo = "";
				int preco2 = 0;
				for (int i = 0; i < docs.size(); i++) {

					if ((docs.size() > 0) && (i < docs.size())) {
						conteudo = docs.get(i);
					}

					if ((preco.size() > 0) && (i < preco.size())) {
						preco2 = preco.get(i);
					}

					tCursos.add(conteudo + " " + preco2 + ",00Kz");
				}

				model.addAttribute("faltas", tCursos);

				model.addAttribute("p", p2);
				model.addAttribute("mc", mc);
				model.addAttribute("os", os);
				model.addAttribute("nome", configurarNome);
				model.addAttribute("escola", configurarEscola);

				nomeDaPagina = "WebGnius/tesouraria/faltas";

				this.pagina = nomeDaPagina;
				// }

			} else {

				estado = 0;
				nomeDaPagina = "redirect:login";
				this.pagina = nomeDaPagina;
			}

		} else if (requisicao.equalsIgnoreCase("/Tesouraria-Minha-Financa")) {

			if (entrarNoSistema.podeAbrirAPagina(senha)) {

				// if(acesso.bloqueandoRequisicoes(TechShine_Controller.request, request)) {

				Pesquisar_SQL p = new Pesquisar_SQL();
				Salvar_SQL s = new Salvar_SQL();

				String mes = s.mesActual(this.BD);

				int renda = p.pesquisarUmConteudo_Numa_Linha_Int(this.BD, this.quem_E_O_func + "_financa", mes, "id",
						"", this.id);
				int p2 = p.pesquisarUmConteudo_Numa_Linha_Int(this.BD, "func_diario", "Prop", "bi", this.bi, 0);
				int mc = p.pesquisarUmConteudo_Numa_Linha_Int(this.BD, "func_diario", "MatEConf", "bi", this.bi, 0);
				int os = p.pesquisarUmConteudo_Numa_Linha_Int(this.BD, "func_diario", "servicos", "bi", this.bi, 0);

				if (renda == 0) {
					model.addAttribute("renda", "Irregular");
				} else {
					model.addAttribute("renda", "Regular");
				}

				model.addAttribute("p", p2);
				model.addAttribute("mc", mc);
				model.addAttribute("os", os);
				model.addAttribute("nome", configurarNome);
				model.addAttribute("escola", configurarEscola);

				nomeDaPagina = "WebGnius/tesouraria/financa";

				this.pagina = nomeDaPagina;
				// }

			} else {

				estado = 0;
				nomeDaPagina = "redirect:login";
				this.pagina = nomeDaPagina;
			}

		} else if (requisicao.equalsIgnoreCase("/Tesouraria-Minha-Seguranca")) {

			if (entrarNoSistema.podeAbrirAPagina(senha)) {

				// if(acesso.bloqueandoRequisicoes(TechShine_Controller.request, request)) {

				Pesquisar_SQL p = new Pesquisar_SQL();
				Salvar_SQL s = new Salvar_SQL();

				String mes = s.mesActual(this.BD);

				int renda = p.pesquisarUmConteudo_Numa_Linha_Int(this.BD, this.quem_E_O_func + "_financa", mes, "id",
						"", this.id);
				int p2 = p.pesquisarUmConteudo_Numa_Linha_Int(this.BD, "func_diario", "Prop", "bi", this.bi, 0);
				int mc = p.pesquisarUmConteudo_Numa_Linha_Int(this.BD, "func_diario", "MatEConf", "bi", this.bi, 0);
				int os = p.pesquisarUmConteudo_Numa_Linha_Int(this.BD, "func_diario", "servicos", "bi", this.bi, 0);

				if (renda == 0) {
					model.addAttribute("renda", "Irregular");
				} else {
					model.addAttribute("renda", "Regular");
				}

				model.addAttribute("p", p2);
				model.addAttribute("mc", mc);
				model.addAttribute("os", os);
				model.addAttribute("nome", configurarNome);
				model.addAttribute("escola", configurarEscola);

				nomeDaPagina = "WebGnius/tesouraria/definicao";

				this.pagina = nomeDaPagina;
				// }

			} else {

				estado = 0;
				nomeDaPagina = "redirect:login";
				this.pagina = nomeDaPagina;
			}

		}

		else if (requisicao.equalsIgnoreCase("/Tesouraria-Pagar-Material")) {

			if (entrarNoSistema.podeAbrirAPagina(senha)) {

				// if(acesso.bloqueandoRequisicoes(TechShine_Controller.request, request)) {

				Pesquisar_SQL p = new Pesquisar_SQL();
				Salvar_SQL s = new Salvar_SQL();

				String mes = s.mesActual(this.BD);

				int renda = p.pesquisarUmConteudo_Numa_Linha_Int(this.BD, this.quem_E_O_func + "_financa", mes, "id",
						"", this.id);
				int p2 = p.pesquisarUmConteudo_Numa_Linha_Int(this.BD, "func_diario", "Prop", "bi", this.bi, 0);
				int mc = p.pesquisarUmConteudo_Numa_Linha_Int(this.BD, "func_diario", "MatEConf", "bi", this.bi, 0);
				int os = p.pesquisarUmConteudo_Numa_Linha_Int(this.BD, "func_diario", "servicos", "bi", this.bi, 0);

				if (renda == 0) {
					model.addAttribute("renda", "Irregular");
				} else {
					model.addAttribute("renda", "Regular");
				}

				ArrayList<String> docs = p.pesquisarTudoEmString(this.BD, "Escola_Financa", "Mat");
				ArrayList<Integer> preco = p.pesquisarTudoEmInt(this.BD, "Escola_Financa", "precoMat");

				ArrayList<String> tCursos = new ArrayList<>();
				String conteudo = "";
				int preco2 = 0;
				for (int i = 0; i < docs.size(); i++) {

					if ((docs.size() > 0) && (i < docs.size())) {
						conteudo = docs.get(i);
					}

					if ((preco.size() > 0) && (i < preco.size())) {
						preco2 = preco.get(i);
					}

					tCursos.add(conteudo + " " + preco2 + ",00Kz");
				}

				model.addAttribute("materias", tCursos);

				model.addAttribute("p", p2);
				model.addAttribute("mc", mc);
				model.addAttribute("os", os);
				model.addAttribute("nome", configurarNome);
				model.addAttribute("escola", configurarEscola);

				nomeDaPagina = "WebGnius/tesouraria/material";

				this.pagina = nomeDaPagina;
				// }

			} else {

				estado = 0;
				nomeDaPagina = "redirect:login";
				this.pagina = nomeDaPagina;
			}

		} else if (requisicao.equalsIgnoreCase("/Tesouraria-Pagar-Confirmacao")) {

			if (entrarNoSistema.podeAbrirAPagina(senha)) {

				// if(acesso.bloqueandoRequisicoes(TechShine_Controller.request, request)) {

				Pesquisar_SQL p = new Pesquisar_SQL();
				Salvar_SQL s = new Salvar_SQL();

				String mes = s.mesActual(this.BD);

				int renda = p.pesquisarUmConteudo_Numa_Linha_Int(this.BD, this.quem_E_O_func + "_financa", mes, "id",
						"", this.id);
				int p2 = p.pesquisarUmConteudo_Numa_Linha_Int(this.BD, "func_diario", "Prop", "bi", this.bi, 0);
				int mc = p.pesquisarUmConteudo_Numa_Linha_Int(this.BD, "func_diario", "MatEConf", "bi", this.bi, 0);
				int os = p.pesquisarUmConteudo_Numa_Linha_Int(this.BD, "func_diario", "servicos", "bi", this.bi, 0);

				if (renda == 0) {
					model.addAttribute("renda", "Irregular");
				} else {
					model.addAttribute("renda", "Regular");
				}

				ArrayList<String> docs = p.pesquisarTudoEmString(this.BD, "Escola_Financa", "confirmacao");
				ArrayList<Integer> preco = p.pesquisarTudoEmInt(this.BD, "Escola_Financa", "precoConfir");

				ArrayList<String> tCursos = new ArrayList<>();
				String conteudo = "";
				int preco2 = 0;
				for (int i = 0; i < docs.size(); i++) {

					if ((docs.size() > 0) && (i < docs.size())) {
						conteudo = docs.get(i);
					}

					if ((preco.size() > 0) && (i < preco.size())) {
						preco2 = preco.get(i);
					}

					tCursos.add(conteudo + " " + preco2 + ",00Kz");
				}

				model.addAttribute("confirmacoes", tCursos);

				model.addAttribute("p", p2);
				model.addAttribute("mc", mc);
				model.addAttribute("os", os);
				model.addAttribute("nome", configurarNome);
				model.addAttribute("escola", configurarEscola);

				nomeDaPagina = "WebGnius/tesouraria/confirmacao";

				this.pagina = nomeDaPagina;
				// }

			} else {

				estado = 0;
				nomeDaPagina = "redirect:login";
				this.pagina = nomeDaPagina;
			}

		} else if (requisicao.equalsIgnoreCase("/Propina")) {

			if (entrarNoSistema.podeAbrirAPagina(senha)) {

				// if(acesso.bloqueandoRequisicoes(TechShine_Controller.request, request)) {

				Pesquisar_SQL p = new Pesquisar_SQL();
				Salvar_SQL s = new Salvar_SQL();

				String mes = s.mesActual(this.BD);

				int renda = p.pesquisarUmConteudo_Numa_Linha_Int(this.BD, this.quem_E_O_func + "_financa", mes, "id",
						"", this.id);
				int p2 = p.pesquisarUmConteudo_Numa_Linha_Int(this.BD, "func_diario", "Prop", "bi", this.bi, 0);
				int mc = p.pesquisarUmConteudo_Numa_Linha_Int(this.BD, "func_diario", "MatEConf", "bi", this.bi, 0);
				int os = p.pesquisarUmConteudo_Numa_Linha_Int(this.BD, "func_diario", "servicos", "bi", this.bi, 0);

				if (renda == 0) {
					model.addAttribute("renda", "Irregular");
				} else {
					model.addAttribute("renda", "Regular");
				}

				String turma = this.alunoFinanca.getTurmaDoAluno();
				int id = this.alunoFinanca.getId();

				ArrayList<String> mesePagos = s.tesouraria_AlunosMezesPagos(this.BD, turma, id);
				ArrayList<String> meseNPagos = s.tesouraria_AlunosMezes_Não_Pagos(this.BD, turma, id);
				ArrayList<String> precos = s.tesouraria_MsesQueVaiPagar(this.BD, this.cursoAluno,this.nivel);
				ArrayList<Pagamento> meses = new ArrayList<>();

				for (String c : mesePagos) {

					System.out.println("Mes  Pago: " + c);
				}

				for (String c : meseNPagos) {

					System.out.println("Mes Não Pago: " + c);
				}

				for (int i = 0; i < mesePagos.size(); i++) {

					Pagamento mes1 = new Pagamento();

					mes1.setPagos(mesePagos.get(i));
					mes1.setnPagos(meseNPagos.get(i));

					meses.add(mes1);
				}

				System.out.println("meses: " + meses.size());

				for (Pagamento c : meses) {

					System.out.println("Meses  Pago: " + c.getPagos());
					System.out.println("Meses Não Pago: " + c.getnPagos());
				}

				String aluno = this.alunoFinanca.getNome();

				String mes2 = s.mesActual(this.BD);
				Date dataActual = new Date();
				SimpleDateFormat sdf2 = new SimpleDateFormat("dd/MM/yyyy");
				String[] data = sdf2.format(dataActual).split("/");

				int diaActual = Integer.parseInt(data[0]);
				int tempoSemMulta = p.pesquisarUmConteudo_Numa_Linha_Int(BD, "Escola_Financa", "TempoPropina", "id", "",
						1);
				int multa = p.pesquisarTudoEmInt(BD, "adminfinanca", "multa").get(0);
				ArrayList<String> multas = new ArrayList<>();
				if ((diaActual > tempoSemMulta) && (multa > 0)) {
					multas = s.tesouraria_Multas(this.BD, this.cursoAluno,this.nivel);
				}

				System.out.println("Aluno: " + aluno);

				model.addAttribute("aluno", aluno);
				model.addAttribute("precos", precos);
				model.addAttribute("meses", meses);
				model.addAttribute("mesActual", mes2);
				model.addAttribute("diaActual", tempoSemMulta);
				model.addAttribute("multas", multas);
				model.addAttribute("turma", turma);

				model.addAttribute("p", p2);
				model.addAttribute("mc", mc);
				model.addAttribute("os", os);
				model.addAttribute("nome", configurarNome);
				model.addAttribute("escola", configurarEscola);

				nomeDaPagina = "WebGnius/tesouraria/historicoDoAluno";

				this.pagina = nomeDaPagina;
				// }

			} else {

				estado = 0;
				nomeDaPagina = "redirect:login";
				this.pagina = nomeDaPagina;
			}

		}

		return this.pagina;
	}

	@PostMapping({ "/Propina" })
	public String tesouraria_Pagar(HttpServletRequest request,HttpServletResponse response,Aluno aluno, Model model) {

		
		Salvar_SQL s = new Salvar_SQL();
		Validar v = new Validar();
		Pesquisar_SQL p = new Pesquisar_SQL();
		
		this.cursoAluno = aluno.getCurso();
		aluno.setCurso(this.cursoAluno);
		
		String nivel = aluno.getNivel();
		this.nivel= nivel;
		String turno = aluno.getTurno();
		String nome = aluno.getNome();
		
		String bi="";
		
		if((aluno.getBi().equalsIgnoreCase(""))||
				(aluno.getBi()==null)) {
			
		}else {
			
			bi = aluno.getBi();
		}
		
		aluno.setNivel(nivel);
		aluno.setTurno(turno);
		aluno.setNome(nome);
		
		
		
		Validacoes b = v.tesouraria_Propina_Validar(this.BD, this.cursoAluno, nivel, turno, nome,bi);
		//r.imprimirRelatorio(request,response);

		if(b.isTemErro()) {
			
			this.alunoFinanca = s.tesourariaPagar_Propina_FASE1(this.BD, aluno);
			this.pagina= "redirect:Propina";
			
		}else {
			
			this.pagina= "redirect:Tesouraria-Pagar-Propina";
			model.addAttribute("resultado", b.getDescricao());
		}
		
		
		
		
		
		

		// nomeDaPagina= entrarNoSistema.quemEstaAFazerLogin(senha, model);
		return this.pagina;
	}

	@PostMapping({ "/Tesouraria-pagar" })
	public void tesourariaPagar(HttpServletResponse response,Aluno aluno, Model model) {

		Salvar_SQL s = new Salvar_SQL();
		Pesquisar_SQL p = new Pesquisar_SQL();
		
		String mesesp =  aluno.getMesesAPagar();
		
		System.out.println(" mesesp: "+mesesp);
		 aluno.setMesesAPagar(mesesp);
		
		String[] a = mesesp.split(" ");
		
		System.out.println(" ***************************************************\n\n: ");
		System.out.println(" MMMM: "+aluno.getMesesAPagar());
		String mesApagar = a[0];
		System.out.println(mesApagar);
		int pagarQMeses= Integer.parseInt(mesApagar);
		
		int idAluno = aluno.getId();
		
		Pagamento pagamento = s.tesouraria_EfectuarOPagamento(this.BD, this.alunoFinanca.getTurmaDoAluno(),
				this.alunoFinanca.getId(), aluno.getMesesAPagar(), this.cursoAluno, idAluno, this.bi,this.nivel);

		if (pagamento.isPagamentoEfectuado()) {

			// 7 Aqui vai Imprimir a Propina
			// O Objecto Pagamento ja tem todo os dados que se precisa pa
			// Impressão
			
			
             String turma = pagamento.getTurma();
             ArrayList<String> mesesPagos = s.tesouraria_AlunosMezesPagos(this.BD, turma, idAluno);
			
             System.out.println("Meses Pagos");
             
             for(String c: mesesPagos) {
            	 
            	 System.out.println("Mes: "+c);
             }
			ImprimirRelatorio ir = new ImprimirRelatorio();
			
			int precoDoCurso=0;		
			
			if(this.nivel.contains("10")) {
				precoDoCurso= p.pesquisarUmConteudo_Numa_Linha_Int(BD, "cursos_Niveis", "Decima", "cursos", this.cursoAluno, 0);	
			}else if(this.nivel.contains("11")) {
				precoDoCurso= p.pesquisarUmConteudo_Numa_Linha_Int(BD, "cursos_Niveis", "DecimaPrimeira", "cursos", this.cursoAluno, 0);	
			}else if(this.nivel.contains("12")) {
				precoDoCurso= p.pesquisarUmConteudo_Numa_Linha_Int(BD, "cursos_Niveis", "DecimaSegunda", "cursos", this.cursoAluno, 0);	
			}else if(this.nivel.contains("13")) {
				precoDoCurso= p.pesquisarUmConteudo_Numa_Linha_Int(BD, "cursos_Niveis", "DecimaTerceira", "cursos", this.cursoAluno, 0);	
			}
			
			
			int valorPago = precoDoCurso;
			int numeroDeEstudante = p.pesquisarUmConteudo_Numa_Linha_Int(this.BD, turma,"NProc", "id", "", this.alunoFinanca.getId());
			  ir.relatorioPropina(this.BD,response, this.configurarEscola, pagamento.getCurso(), "Propina", valorPago, this.configurarNome, pagarQMeses, turma, mesesPagos, pagamento.getAluno(), numeroDeEstudante+"");


		}
		//return "redirect:Tesouraria-Pagar-Propina";

	}

	@PostMapping({ "/Tesouraria-Pagar-Matricula" })
	public String tesourariaPaga_matricula(HttpServletRequest resquest,HttpServletResponse response,Curso curso, Model model) {

		Salvar_SQL s = new Salvar_SQL();

		String nome = curso.getNome();
		String[] a = nome.split(" ");
		nome = a[0];
		s.tesouraria_Pagar_Matricula(BD, nome, this.bi);

		//r.imprimirRelatorio(resquest,response);

		// nomeDaPagina= entrarNoSistema.quemEstaAFazerLogin(senha, model);
		return "redirect:Tesouraria-Pagar-Matricula";
	}

	@PostMapping({ "/Tesouraria-Pagar-Confirmacao" })
	public String tesourariaPaga_Confirmacao(Curso curso, Model model) {

		Salvar_SQL s = new Salvar_SQL();

		String nome = curso.getNome();
		String[] a = nome.split(" ");
		nome = a[0];
		s.tesouraria_Pagar_Confirmacao(BD, nome, this.bi);

		imprimir(null, null, "Confirmacao");

		// nomeDaPagina= entrarNoSistema.quemEstaAFazerLogin(senha, model);
		return "redirect:Tesouraria-Pagar-Confirmacao";
	}

	@PostMapping({ "/Tesouraria-Pagar-Estagio" })
	public String tesourariaPaga_Estagio() {

		Salvar_SQL s = new Salvar_SQL();
		s.tesouraria_Pagar_Estagio(this.BD, this.bi);
		imprimir(null, null, "Estagio");

		// nomeDaPagina= entrarNoSistema.quemEstaAFazerLogin(senha, model);
		return "redirect:Tesouraria-Pagar-Estagio";
	}

	@PostMapping({ "/Tesouraria-Pagar-Material" })
	public String tesourariaPaga_Material(Financa financa, Model model) {

		Salvar_SQL s = new Salvar_SQL();

		String nome = financa.getMaterias();
		String[] a = nome.split(" ");
		nome = a[0];
		s.tesouraria_Pagar_Materias(this.BD, nome, this.bi);
		imprimir(null, null, "Material");
		// nomeDaPagina= entrarNoSistema.quemEstaAFazerLogin(senha, model);
		return "redirect:Tesouraria-Pagar-Material";
	}

	@PostMapping({ "/Tesouraria-Pagar-Documento" })
	public String tesourariaPaga_Documento(Financa financa, Model model) {

		Salvar_SQL s = new Salvar_SQL();

		String nome = financa.getDoc();
		String[] a = nome.split(" ");
		nome = a[0];
		s.tesouraria_Pagar_Documento(this.BD, nome, this.bi);

		imprimir(null, null, "Documento");
		// nomeDaPagina= entrarNoSistema.quemEstaAFazerLogin(senha, model);
		return "redirect:Tesouraria-Pagar-Documento";
	}

	@PostMapping({ "/Tesouraria-Pagar-Falta" })
	public String tesourariaPaga_Falta(Financa financa, Model model) {

		Salvar_SQL s = new Salvar_SQL();

		String nome = financa.getFalta();
		String[] a = nome.split(" ");
		nome = a[0];
		s.tesouraria_Pagar_Falta(this.BD, nome, this.bi);

		imprimir(null, null, "Falta");
		// nomeDaPagina= entrarNoSistema.quemEstaAFazerLogin(senha, model);
		return "redirect:Tesouraria-Pagar-Falta";
	}

	// Metodos Da Secretaria

	@GetMapping({ "/Secretaria", "/Secretaria-Agenda", "/Secretaria-Confirmacao", "/Secretaria-Financa",
			"/Secretaria-Matricula", "/Secretaria-Estagio", "/Secretaria-Materiais", "/Secretaria-Documentos",
			"/Secretaria-Faltas", "/Secretaria-Minha-Seguranca", "/Secretaria-Pautas", "/Secretaria-Minha-Financa",
			"/Secretaria-Lista"

	})
	public String secretaria(HttpServletRequest request, Model model) {

		String requisicao;
		String nomeDaPagina = null;
		estado = -5;
		requisicao = request.getRequestURI();

		if (requisicao.equalsIgnoreCase("/Secretaria")) {

			if (entrarNoSistema.podeAbrirAPagina(senha)) {

				// if(acesso.bloqueandoRequisicoes(TechShine_Controller.request, request)) {

				Pesquisar_SQL p = new Pesquisar_SQL();
				Salvar_SQL s = new Salvar_SQL();

				String mes = s.mesActual(this.BD);

				int renda = p.pesquisarUmConteudo_Numa_Linha_Int(this.BD, this.quem_E_O_func + "_financa", mes, "id",
						"", this.id);
				int p2 = p.pesquisarUmConteudo_Numa_Linha_Int(this.BD, "func_diario", "Prop", "bi", this.bi, 0);
				int mc = p.pesquisarUmConteudo_Numa_Linha_Int(this.BD, "func_diario", "MatEConf", "bi", this.bi, 0);
				int os = p.pesquisarUmConteudo_Numa_Linha_Int(this.BD, "func_diario", "servicos", "bi", this.bi, 0);

				if (renda == 0) {
					model.addAttribute("renda", "Irregular");
				} else {
					model.addAttribute("renda", "Regular");
				}

				/*
				 * p.pesquisarUmConteudo_Numa_Linha_String(this.BD, "disciplinas_dos_profs",
				 * "turma", "bi", this.bi, 0); ArrayList<String> turmas
				 * =p.pesquisarTudoEmString_Restrito(this.BD, "disciplinas_dos_profs", "turma",
				 * "bi", this.bi, 0);
				 * 
				 * int qalunos=0; int contador=0;
				 * 
				 * for (String turma : turmas) {
				 * 
				 * qalunos= qalunos + p.pesquisarTudoEmInt(this.BD, turma, "NDeAlunos").get(0);
				 * System.out.println("qalunos: "+qalunos); ++contador; }
				 * 
				 * //ArrayList<String> desciplinas =p.pesquisarTudoEmString_Restrito(this.BD,
				 * "disciplinas_dos_profs", "desciplina", "bi", this.bi, 0); //ArrayList<String>
				 * cursos =p.pesquisarTudoEmString_Restrito(this.BD, "disciplinas_dos_profs",
				 * "curso", "bi", this.bi, 0); //ArrayList<String> turnos
				 * =p.pesquisarTudoEmString_Restrito(this.BD, "disciplinas_dos_profs", "turno",
				 * "bi", this.bi, 0); //ArrayList<String> niveis
				 * =p.pesquisarTudoEmString_Restrito(this.BD, "disciplinas_dos_profs", "nivel",
				 * "bi", this.bi, 0);
				 * 
				 * Date d = new Date(); SimpleDateFormat sdf = new
				 * SimpleDateFormat("dd/MM/yyyy"); String data = sdf.format(d);
				 * 
				 */

				
				Date d = new Date();
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				
				model.addAttribute("data", sdf.format(d));
				model.addAttribute("p", p2);
				model.addAttribute("mc", mc);
				model.addAttribute("os", os);

				model.addAttribute("nome", configurarNome);
				model.addAttribute("escola", configurarEscola);

				nomeDaPagina = "WebGnius/secretaria/inicio";
				this.pagina = nomeDaPagina;
				// }

			} else {

				estado = 0;
				nomeDaPagina = "redirect:login";
				this.pagina = nomeDaPagina;
			}

		} else if (requisicao.equalsIgnoreCase("/Secretaria-Agenda")) {

			if (entrarNoSistema.podeAbrirAPagina(senha)) {

				// if(acesso.bloqueandoRequisicoes(TechShine_Controller.request, request)) {

				Pesquisar_SQL p = new Pesquisar_SQL();
				Salvar_SQL s = new Salvar_SQL();

				String mes = s.mesActual(this.BD);

				int renda = p.pesquisarUmConteudo_Numa_Linha_Int(this.BD, this.quem_E_O_func + "_financa", mes, "id",
						"", this.id);
				int p2 = p.pesquisarUmConteudo_Numa_Linha_Int(this.BD, "func_diario", "Prop", "bi", this.bi, 0);
				int mc = p.pesquisarUmConteudo_Numa_Linha_Int(this.BD, "func_diario", "MatEConf", "bi", this.bi, 0);
				int os = p.pesquisarUmConteudo_Numa_Linha_Int(this.BD, "func_diario", "servicos", "bi", this.bi, 0);

				if (renda == 0) {
					model.addAttribute("renda", "Irregular");
				} else {
					model.addAttribute("renda", "Regular");
				}

				/*
				 * p.pesquisarUmConteudo_Numa_Linha_String(this.BD, "disciplinas_dos_profs",
				 * "turma", "bi", this.bi, 0); ArrayList<String> turmas
				 * =p.pesquisarTudoEmString_Restrito(this.BD, "disciplinas_dos_profs", "turma",
				 * "bi", this.bi, 0);
				 * 
				 * int qalunos=0; int contador=0;
				 * 
				 * for (String turma : turmas) {
				 * 
				 * qalunos= qalunos + p.pesquisarTudoEmInt(this.BD, turma, "NDeAlunos").get(0);
				 * System.out.println("qalunos: "+qalunos); ++contador; }
				 * 
				 * //ArrayList<String> desciplinas =p.pesquisarTudoEmString_Restrito(this.BD,
				 * "disciplinas_dos_profs", "desciplina", "bi", this.bi, 0); //ArrayList<String>
				 * cursos =p.pesquisarTudoEmString_Restrito(this.BD, "disciplinas_dos_profs",
				 * "curso", "bi", this.bi, 0); //ArrayList<String> turnos
				 * =p.pesquisarTudoEmString_Restrito(this.BD, "disciplinas_dos_profs", "turno",
				 * "bi", this.bi, 0); //ArrayList<String> niveis
				 * =p.pesquisarTudoEmString_Restrito(this.BD, "disciplinas_dos_profs", "nivel",
				 * "bi", this.bi, 0);
				 * 
				 * Date d = new Date(); SimpleDateFormat sdf = new
				 * SimpleDateFormat("dd/MM/yyyy"); String data = sdf.format(d);
				 * 
				 */

				model.addAttribute("p", p2);
				model.addAttribute("mc", mc);
				model.addAttribute("os", os);

				model.addAttribute("nome", configurarNome);
				model.addAttribute("escola", configurarEscola);

				nomeDaPagina = "WebGnius/secretaria/agendaDeServico";
				this.pagina = nomeDaPagina;
				// }

			} else {

				estado = 0;
				nomeDaPagina = "redirect:login";
				this.pagina = nomeDaPagina;
			}

		} else if (requisicao.equalsIgnoreCase("/Secretaria-Confirmacao")) {

			if (entrarNoSistema.podeAbrirAPagina(senha)) {

				// if(acesso.bloqueandoRequisicoes(TechShine_Controller.request, request)) {

				Pesquisar_SQL p = new Pesquisar_SQL();
				Salvar_SQL s = new Salvar_SQL();

				String mes = s.mesActual(this.BD);

				int renda = p.pesquisarUmConteudo_Numa_Linha_Int(this.BD, this.quem_E_O_func + "_financa", mes, "id",
						"", this.id);
				int p2 = p.pesquisarUmConteudo_Numa_Linha_Int(this.BD, "func_diario", "Prop", "bi", this.bi, 0);
				int mc = p.pesquisarUmConteudo_Numa_Linha_Int(this.BD, "func_diario", "MatEConf", "bi", this.bi, 0);
				int os = p.pesquisarUmConteudo_Numa_Linha_Int(this.BD, "func_diario", "servicos", "bi", this.bi, 0);

				if (renda == 0) {
					model.addAttribute("renda", "Irregular");
				} else {
					model.addAttribute("renda", "Regular");
				}

				/*
				 * p.pesquisarUmConteudo_Numa_Linha_String(this.BD, "disciplinas_dos_profs",
				 * "turma", "bi", this.bi, 0); ArrayList<String> turmas
				 * =p.pesquisarTudoEmString_Restrito(this.BD, "disciplinas_dos_profs", "turma",
				 * "bi", this.bi, 0);
				 * 
				 * int qalunos=0; int contador=0;
				 * 
				 * for (String turma : turmas) {
				 * 
				 * qalunos= qalunos + p.pesquisarTudoEmInt(this.BD, turma, "NDeAlunos").get(0);
				 * System.out.println("qalunos: "+qalunos); ++contador; }
				 * 
				 * //ArrayList<String> desciplinas =p.pesquisarTudoEmString_Restrito(this.BD,
				 * "disciplinas_dos_profs", "desciplina", "bi", this.bi, 0); //ArrayList<String>
				 * cursos =p.pesquisarTudoEmString_Restrito(this.BD, "disciplinas_dos_profs",
				 * "curso", "bi", this.bi, 0); //ArrayList<String> turnos
				 * =p.pesquisarTudoEmString_Restrito(this.BD, "disciplinas_dos_profs", "turno",
				 * "bi", this.bi, 0); //ArrayList<String> niveis
				 * =p.pesquisarTudoEmString_Restrito(this.BD, "disciplinas_dos_profs", "nivel",
				 * "bi", this.bi, 0);
				 * 
				 * Date d = new Date(); SimpleDateFormat sdf = new
				 * SimpleDateFormat("dd/MM/yyyy"); String data = sdf.format(d);
				 * 
				 */

				ArrayList<Curso> cursos = p.Listar_Cursos(this.BD);
				ArrayList<String> tCursos = new ArrayList<>();

				for (Curso c : cursos) {

					tCursos.add(c.getNome());
				}
				ArrayList<String> niveis = p.pesquisarTudoEmString(this.BD, "infoescola", "niveis");

				
				
				
				
				Date d = new Date();
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				
				ArrayList<String> turmasManha = p.pesquisarTudoEmString(this.BD, "controle_turmas", "Manha");
				ArrayList<String> turmasTarde = p.pesquisarTudoEmString(this.BD, "controle_turmas", "Tarde");
				ArrayList<String> turmasNoite = p.pesquisarTudoEmString(this.BD, "controle_turmas", "Noite");

				ArrayList<ArrayList<String>> todasTurmas = new ArrayList<>();

				todasTurmas.add(turmasManha);
				todasTurmas.add(turmasTarde);
				todasTurmas.add(turmasNoite);

				ArrayList<String> alunosManha = new ArrayList<>();
				ArrayList<String> alunosTarde = new ArrayList<>();
				ArrayList<String> alunosNoite = new ArrayList<>();

				int contador = 0;
				for (ArrayList<String> cadaTurno : todasTurmas) {

					++contador;
					for (String turma : cadaTurno) {

						ArrayList<String> alunos = p.pesquisarTudoEmString(this.BD, turma, "Alunos");

						if (contador == 1) {
							System.out.println("Manha: ");
							for (String al : alunos) {

								System.out.println("Aluno: " + al);
								alunosManha.add(al);
							}
						} else if (contador == 2) {
							System.out.println("Tarde: ");
							for (String al : alunos) {

								System.out.println("Aluno: " + al);
								alunosTarde.add(al);
							}
						} else if (contador == 3) {

							System.out.println("Noite: ");
							for (String al : alunos) {

								System.out.println("Aluno: " + al);
								alunosNoite.add(al);
							}
						}
					}

				}

				
				model.addAttribute("cursos", tCursos);
				model.addAttribute("niveis", niveis);
				model.addAttribute("manha", alunosManha);
				model.addAttribute("tarde", alunosTarde);
				model.addAttribute("noite", alunosNoite);
				model.addAttribute("data", sdf.format(d));
				model.addAttribute("p", p2);
				model.addAttribute("mc", mc);
				model.addAttribute("os", os);

				model.addAttribute("nome", configurarNome);
				model.addAttribute("escola", configurarEscola);

				nomeDaPagina = "WebGnius/secretaria/confirmacao";
				this.pagina = nomeDaPagina;
				// }

			} else {

				estado = 0;
				nomeDaPagina = "redirect:login";
				this.pagina = nomeDaPagina;
			}

		} else if (requisicao.equalsIgnoreCase("/Secretaria-Minha-Financa")) {

			if (entrarNoSistema.podeAbrirAPagina(senha)) {

				// if(acesso.bloqueandoRequisicoes(TechShine_Controller.request, request)) {

				Pesquisar_SQL p = new Pesquisar_SQL();
				Salvar_SQL s = new Salvar_SQL();

				String mes = s.mesActual(this.BD);

				int renda = p.pesquisarUmConteudo_Numa_Linha_Int(this.BD, this.quem_E_O_func + "_financa", mes, "id",
						"", this.id);
				int p2 = p.pesquisarUmConteudo_Numa_Linha_Int(this.BD, "func_diario", "Prop", "bi", this.bi, 0);
				int mc = p.pesquisarUmConteudo_Numa_Linha_Int(this.BD, "func_diario", "MatEConf", "bi", this.bi, 0);
				int os = p.pesquisarUmConteudo_Numa_Linha_Int(this.BD, "func_diario", "servicos", "bi", this.bi, 0);

				if (renda == 0) {
					model.addAttribute("renda", "Irregular");
				} else {
					model.addAttribute("renda", "Regular");
				}

				/*
				 * p.pesquisarUmConteudo_Numa_Linha_String(this.BD, "disciplinas_dos_profs",
				 * "turma", "bi", this.bi, 0); ArrayList<String> turmas
				 * =p.pesquisarTudoEmString_Restrito(this.BD, "disciplinas_dos_profs", "turma",
				 * "bi", this.bi, 0);
				 * 
				 * int qalunos=0; int contador=0;
				 * 
				 * for (String turma : turmas) {
				 * 
				 * qalunos= qalunos + p.pesquisarTudoEmInt(this.BD, turma, "NDeAlunos").get(0);
				 * System.out.println("qalunos: "+qalunos); ++contador; }
				 * 
				 * //ArrayList<String> desciplinas =p.pesquisarTudoEmString_Restrito(this.BD,
				 * "disciplinas_dos_profs", "desciplina", "bi", this.bi, 0); //ArrayList<String>
				 * cursos =p.pesquisarTudoEmString_Restrito(this.BD, "disciplinas_dos_profs",
				 * "curso", "bi", this.bi, 0); //ArrayList<String> turnos
				 * =p.pesquisarTudoEmString_Restrito(this.BD, "disciplinas_dos_profs", "turno",
				 * "bi", this.bi, 0); //ArrayList<String> niveis
				 * =p.pesquisarTudoEmString_Restrito(this.BD, "disciplinas_dos_profs", "nivel",
				 * "bi", this.bi, 0);
				 * 
				 * Date d = new Date(); SimpleDateFormat sdf = new
				 * SimpleDateFormat("dd/MM/yyyy"); String data = sdf.format(d);
				 * 
				 */

				model.addAttribute("p", p2);
				model.addAttribute("mc", mc);
				model.addAttribute("os", os);

				model.addAttribute("nome", configurarNome);
				model.addAttribute("escola", configurarEscola);

				nomeDaPagina = "WebGnius/secretaria/financa";
				this.pagina = nomeDaPagina;
				// }

			} else {

				estado = 0;
				nomeDaPagina = "redirect:login";
				this.pagina = nomeDaPagina;
			}

		} else if (requisicao.equalsIgnoreCase("/Secretaria-Matricula")) {
			

			if (entrarNoSistema.podeAbrirAPagina(senha)) {

				// if(acesso.bloqueandoRequisicoes(TechShine_Controller.request, request)) {

				Pesquisar_SQL p = new Pesquisar_SQL();
				Salvar_SQL s = new Salvar_SQL();

				String mes = s.mesActual(this.BD);

				int renda = p.pesquisarUmConteudo_Numa_Linha_Int(this.BD, this.quem_E_O_func + "_financa", mes, "id",
						"", this.id);
				int p2 = p.pesquisarUmConteudo_Numa_Linha_Int(this.BD, "func_diario", "Prop", "bi", this.bi, 0);
				int mc = p.pesquisarUmConteudo_Numa_Linha_Int(this.BD, "func_diario", "MatEConf", "bi", this.bi, 0);
				int os = p.pesquisarUmConteudo_Numa_Linha_Int(this.BD, "func_diario", "servicos", "bi", this.bi, 0);

				if (renda == 0) {
					model.addAttribute("renda", "Irregular");
				} else {
					model.addAttribute("renda", "Regular");
				}

				ArrayList<Curso> cursos = p.Listar_Cursos(this.BD);
				ArrayList<String> tCursos = new ArrayList<>();

				for (Curso c : cursos) {

					tCursos.add(c.getNome());
				}
				ArrayList<String> niveis = p.pesquisarTudoEmString(this.BD, "infoescola", "niveis");

				
				/*
				 * p.pesquisarUmConteudo_Numa_Linha_String(this.BD, "disciplinas_dos_profs",
				 * "turma", "bi", this.bi, 0); ArrayList<String> turmas
				 * =p.pesquisarTudoEmString_Restrito(this.BD, "disciplinas_dos_profs", "turma",
				 * "bi", this.bi, 0);
				 * 
				 * int qalunos=0; int contador=0;
				 * 
				 * for (String turma : turmas) {
				 * 
				 * qalunos= qalunos + p.pesquisarTudoEmInt(this.BD, turma, "NDeAlunos").get(0);
				 * System.out.println("qalunos: "+qalunos); ++contador; }
				 * 
				 * //ArrayList<String> desciplinas =p.pesquisarTudoEmString_Restrito(this.BD,
				 * "disciplinas_dos_profs", "desciplina", "bi", this.bi, 0); //ArrayList<String>
				 * cursos =p.pesquisarTudoEmString_Restrito(this.BD, "disciplinas_dos_profs",
				 * "curso", "bi", this.bi, 0); //ArrayList<String> turnos
				 * =p.pesquisarTudoEmString_Restrito(this.BD, "disciplinas_dos_profs", "turno",
				 * "bi", this.bi, 0); //ArrayList<String> niveis
				 * =p.pesquisarTudoEmString_Restrito(this.BD, "disciplinas_dos_profs", "nivel",
				 * "bi", this.bi, 0);
				 * 
				 * Date d = new Date(); SimpleDateFormat sdf = new
				 * SimpleDateFormat("dd/MM/yyyy"); String data = sdf.format(d);
				 * 
				 */

				
				Date d = new Date();
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				
				model.addAttribute("data", sdf.format(d));
				
				model.addAttribute("p", p2);
				model.addAttribute("mc", mc);
				model.addAttribute("os", os);
				model.addAttribute("cursos", tCursos);
				model.addAttribute("niveis", niveis);

				model.addAttribute("nome", configurarNome);
				model.addAttribute("escola", configurarEscola);

				nomeDaPagina = "WebGnius/secretaria/cadastrarAluno";
				this.pagina = nomeDaPagina;
				// }

			} else {

				estado = 0;
				nomeDaPagina = "redirect:login";
				this.pagina = nomeDaPagina;
			}

		} else if (requisicao.equalsIgnoreCase("/Secretaria-Agenda2")) {

			if (entrarNoSistema.podeAbrirAPagina(senha)) {

				// if(acesso.bloqueandoRequisicoes(TechShine_Controller.request, request)) {

				nomeDaPagina = "WebGnius/secretaria/agendaServico2";
				this.pagina = nomeDaPagina;
				// }

			} else {

				estado = 0;
				nomeDaPagina = "redirect:login";
				this.pagina = nomeDaPagina;
			}

		} else if (requisicao.equalsIgnoreCase("/Secretaria-Lista")) {

			if (entrarNoSistema.podeAbrirAPagina(senha)) {

				// if(acesso.bloqueandoRequisicoes(TechShine_Controller.request, request)) {

				nomeDaPagina = "WebGnius/secretaria/agendaServico2";
				this.pagina = nomeDaPagina;
				// }

			} else {

				estado = 0;
				nomeDaPagina = "redirect:login";
				this.pagina = nomeDaPagina;
			}

		} else if (requisicao.equalsIgnoreCase("/Secretaria-Minha-Seguranca")) {

			if (entrarNoSistema.podeAbrirAPagina(senha)) {

				// if(acesso.bloqueandoRequisicoes(TechShine_Controller.request, request)) {

				Pesquisar_SQL p = new Pesquisar_SQL();
				Salvar_SQL s = new Salvar_SQL();

				String mes = s.mesActual(this.BD);

				int renda = p.pesquisarUmConteudo_Numa_Linha_Int(this.BD, this.quem_E_O_func + "_financa", mes, "id",
						"", this.id);
				int p2 = p.pesquisarUmConteudo_Numa_Linha_Int(this.BD, "func_diario", "Prop", "bi", this.bi, 0);
				int mc = p.pesquisarUmConteudo_Numa_Linha_Int(this.BD, "func_diario", "MatEConf", "bi", this.bi, 0);
				int os = p.pesquisarUmConteudo_Numa_Linha_Int(this.BD, "func_diario", "servicos", "bi", this.bi, 0);

				if (renda == 0) {
					model.addAttribute("renda", "Irregular");
				} else {
					model.addAttribute("renda", "Regular");
				}

				/*
				 * p.pesquisarUmConteudo_Numa_Linha_String(this.BD, "disciplinas_dos_profs",
				 * "turma", "bi", this.bi, 0); ArrayList<String> turmas
				 * =p.pesquisarTudoEmString_Restrito(this.BD, "disciplinas_dos_profs", "turma",
				 * "bi", this.bi, 0);
				 * 
				 * int qalunos=0; int contador=0;
				 * 
				 * for (String turma : turmas) {
				 * 
				 * qalunos= qalunos + p.pesquisarTudoEmInt(this.BD, turma, "NDeAlunos").get(0);
				 * System.out.println("qalunos: "+qalunos); ++contador; }
				 * 
				 * //ArrayList<String> desciplinas =p.pesquisarTudoEmString_Restrito(this.BD,
				 * "disciplinas_dos_profs", "desciplina", "bi", this.bi, 0); //ArrayList<String>
				 * cursos =p.pesquisarTudoEmString_Restrito(this.BD, "disciplinas_dos_profs",
				 * "curso", "bi", this.bi, 0); //ArrayList<String> turnos
				 * =p.pesquisarTudoEmString_Restrito(this.BD, "disciplinas_dos_profs", "turno",
				 * "bi", this.bi, 0); //ArrayList<String> niveis
				 * =p.pesquisarTudoEmString_Restrito(this.BD, "disciplinas_dos_profs", "nivel",
				 * "bi", this.bi, 0);
				 * 
				 * Date d = new Date(); SimpleDateFormat sdf = new
				 * SimpleDateFormat("dd/MM/yyyy"); String data = sdf.format(d);
				 * 
				 */

				
				Date d = new Date();
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				
				model.addAttribute("data", sdf.format(d));
				model.addAttribute("p", p2);
				model.addAttribute("mc", mc);
				model.addAttribute("os", os);

				model.addAttribute("nome", configurarNome);
				model.addAttribute("escola", configurarEscola);

				nomeDaPagina = "WebGnius/secretaria/definicao";
				this.pagina = nomeDaPagina;
				// }

			} else {

				estado = 0;
				nomeDaPagina = "redirect:login";
				this.pagina = nomeDaPagina;
			}

		}

		return this.pagina;
	}

	@GetMapping("/Secretaria-Mini-Pauta")
	public String secretariaMiniPauta(Model model) {

		String nomeDaPagina = null;
		estado = -5;

		if (entrarNoSistema.podeAbrirAPagina(senha)) {

			// if(acesso.bloqueandoRequisicoes(TechShine_Controller.request, request)) {

			Pesquisar_SQL p = new Pesquisar_SQL();
			Salvar_SQL s = new Salvar_SQL();

			String mes = s.mesActual(this.BD);

			int renda = p.pesquisarUmConteudo_Numa_Linha_Int(this.BD, this.quem_E_O_func + "_financa", mes, "id", "",
					this.id);
			int p2 = p.pesquisarUmConteudo_Numa_Linha_Int(this.BD, "func_diario", "Prop", "bi", this.bi, 0);
			int mc = p.pesquisarUmConteudo_Numa_Linha_Int(this.BD, "func_diario", "MatEConf", "bi", this.bi, 0);
			int os = p.pesquisarUmConteudo_Numa_Linha_Int(this.BD, "func_diario", "servicos", "bi", this.bi, 0);

			if (renda == 0) {
				model.addAttribute("renda", "Irregular");
			} else {
				model.addAttribute("renda", "Regular");
			}

			/*
			 * p.pesquisarUmConteudo_Numa_Linha_String(this.BD, "disciplinas_dos_profs",
			 * "turma", "bi", this.bi, 0); ArrayList<String> turmas
			 * =p.pesquisarTudoEmString_Restrito(this.BD, "disciplinas_dos_profs", "turma",
			 * "bi", this.bi, 0);
			 * 
			 * int qalunos=0; int contador=0;
			 * 
			 * for (String turma : turmas) {
			 * 
			 * qalunos= qalunos + p.pesquisarTudoEmInt(this.BD, turma, "NDeAlunos").get(0);
			 * System.out.println("qalunos: "+qalunos); ++contador; }
			 * 
			 * //ArrayList<String> desciplinas =p.pesquisarTudoEmString_Restrito(this.BD,
			 * "disciplinas_dos_profs", "desciplina", "bi", this.bi, 0); //ArrayList<String>
			 * cursos =p.pesquisarTudoEmString_Restrito(this.BD, "disciplinas_dos_profs",
			 * "curso", "bi", this.bi, 0); //ArrayList<String> turnos
			 * =p.pesquisarTudoEmString_Restrito(this.BD, "disciplinas_dos_profs", "turno",
			 * "bi", this.bi, 0); //ArrayList<String> niveis
			 * =p.pesquisarTudoEmString_Restrito(this.BD, "disciplinas_dos_profs", "nivel",
			 * "bi", this.bi, 0);
			 * 
			 * Date d = new Date(); SimpleDateFormat sdf = new
			 * SimpleDateFormat("dd/MM/yyyy"); String data = sdf.format(d);
			 * 
			 */

			model.addAttribute("p", p2);
			model.addAttribute("mc", mc);
			model.addAttribute("os", os);

			model.addAttribute("nome", configurarNome);
			model.addAttribute("escola", configurarEscola);

			nomeDaPagina = "WebGnius/secretaria/mini_Pautas";
			this.pagina = nomeDaPagina;
			// }

		} else {

			estado = 0;
			nomeDaPagina = "redirect:login";
			this.pagina = nomeDaPagina;
		}

		return this.pagina;

	}

	@GetMapping("/Secretaria-Provas-Marcadas")
	public String secretariaProvas() {

		String nomeDaPagina = null;
		estado = -5;
		if (entrarNoSistema.podeAbrirAPagina(senha)) {

			// if(acesso.bloqueandoRequisicoes(TechShine_Controller.request, request)) {

			nomeDaPagina = "WebGnius/secretaria/provasMarcadas";
			this.pagina = nomeDaPagina;
			// }

		} else {

			estado = 0;
			nomeDaPagina = "redirect:login";
			this.pagina = nomeDaPagina;
		}

		return this.pagina;
	}

	@GetMapping("/Secretaria-Prova-Info")
	public String secretariaProvaInfo() {

		String nomeDaPagina = null;
		estado = -5;
		if (entrarNoSistema.podeAbrirAPagina(senha)) {

			// if(acesso.bloqueandoRequisicoes(TechShine_Controller.request, request)) {

			nomeDaPagina = "WebGnius/secretaria/provaInfo";
			this.pagina = nomeDaPagina;
			// }

		} else {

			estado = 0;
			nomeDaPagina = "redirect:login";
			this.pagina = nomeDaPagina;
		}

		return this.pagina;
	}

	@GetMapping("/Secretaria-Turma-Info")
	public String secretariaTurmaInfo() {

		String nomeDaPagina = null;
		estado = -5;
		if (entrarNoSistema.podeAbrirAPagina(senha)) {

			// if(acesso.bloqueandoRequisicoes(TechShine_Controller.request, request)) {

			nomeDaPagina = "WebGnius/secretaria/turma_Info";
			this.pagina = nomeDaPagina;
			// }

		} else {

			estado = 0;
			nomeDaPagina = "redirect:login";
			this.pagina = nomeDaPagina;
		}

		return this.pagina;
	}

	@GetMapping("/Secretaria-Marcar-Prova")
	public String secretariaMarprova() {

		String nomeDaPagina = null;
		estado = -5;
		if (entrarNoSistema.podeAbrirAPagina(senha)) {

			// if(acesso.bloqueandoRequisicoes(TechShine_Controller.request, request)) {

			nomeDaPagina = "WebGnius/secretaria/marcarProva";
			this.pagina = nomeDaPagina;
			// }

		} else {

			estado = 0;
			nomeDaPagina = "redirect:login";
			this.pagina = nomeDaPagina;
		}

		return this.pagina;
	}

	@GetMapping("/Secretaria-Imprimir-Boletin")
	public String secretariaBoletin(Model model) {

		String nomeDaPagina = null;
		estado = -5;
		if (entrarNoSistema.podeAbrirAPagina(senha)) {

			// if(acesso.bloqueandoRequisicoes(TechShine_Controller.request, request)) {

			Pesquisar_SQL p = new Pesquisar_SQL();
			Salvar_SQL s = new Salvar_SQL();

			ArrayList<Curso> cursos = p.Listar_Cursos(this.BD);
			ArrayList<String> tCursos = new ArrayList<>();

			for (Curso c : cursos) {

				tCursos.add(c.getNome());
			}
			ArrayList<String> niveis = p.pesquisarTudoEmString(this.BD, "infoescola", "niveis");

			String mes = s.mesActual(this.BD);

			int renda = p.pesquisarUmConteudo_Numa_Linha_Int(this.BD, this.quem_E_O_func + "_financa", mes, "id", "",
					this.id);
			int p2 = p.pesquisarUmConteudo_Numa_Linha_Int(this.BD, "func_diario", "Prop", "bi", this.bi, 0);
			int mc = p.pesquisarUmConteudo_Numa_Linha_Int(this.BD, "func_diario", "MatEConf", "bi", this.bi, 0);
			int os = p.pesquisarUmConteudo_Numa_Linha_Int(this.BD, "func_diario", "servicos", "bi", this.bi, 0);

			if (renda == 0) {
				model.addAttribute("renda", "Irregular");
			} else {
				model.addAttribute("renda", "Regular");
			}

			/*
			 * p.pesquisarUmConteudo_Numa_Linha_String(this.BD, "disciplinas_dos_profs",
			 * "turma", "bi", this.bi, 0); ArrayList<String> turmas
			 * =p.pesquisarTudoEmString_Restrito(this.BD, "disciplinas_dos_profs", "turma",
			 * "bi", this.bi, 0);
			 * 
			 * int qalunos=0; int contador=0;
			 * 
			 * for (String turma : turmas) {
			 * 
			 * qalunos= qalunos + p.pesquisarTudoEmInt(this.BD, turma, "NDeAlunos").get(0);
			 * System.out.println("qalunos: "+qalunos); ++contador; }
			 * 
			 * //ArrayList<String> desciplinas =p.pesquisarTudoEmString_Restrito(this.BD,
			 * "disciplinas_dos_profs", "desciplina", "bi", this.bi, 0); //ArrayList<String>
			 * cursos =p.pesquisarTudoEmString_Restrito(this.BD, "disciplinas_dos_profs",
			 * "curso", "bi", this.bi, 0); //ArrayList<String> turnos
			 * =p.pesquisarTudoEmString_Restrito(this.BD, "disciplinas_dos_profs", "turno",
			 * "bi", this.bi, 0); //ArrayList<String> niveis
			 * =p.pesquisarTudoEmString_Restrito(this.BD, "disciplinas_dos_profs", "nivel",
			 * "bi", this.bi, 0);
			 * 
			 * Date d = new Date(); SimpleDateFormat sdf = new
			 * SimpleDateFormat("dd/MM/yyyy"); String data = sdf.format(d);
			 * 
			 */
			
			Date d = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			
			model.addAttribute("data", sdf.format(d));

			model.addAttribute("p", p2);
			model.addAttribute("mc", mc);
			model.addAttribute("os", os);

			model.addAttribute("cursos", tCursos);
			model.addAttribute("niveis", niveis);

			model.addAttribute("nome", configurarNome);
			model.addAttribute("escola", configurarEscola);

			nomeDaPagina = "WebGnius/secretaria/imprimirBoletin";
			this.pagina = nomeDaPagina;
			// }

		} else {

			estado = 0;
			nomeDaPagina = "redirect:login";
			this.pagina = nomeDaPagina;
		}

		return this.pagina;
	}

	@PostMapping("/Secretaria-Imprimir-Boletin")
	public String secretariaBoletin2(Model model, Aluno aluno) {

		Pesquisar_SQL p = new Pesquisar_SQL();

		AlunoNotas aluno2 = p.Listar_TurmasDoCurso3(this.BD, aluno);

		if (aluno2.isAlunoExiste()) {

			model.addAttribute("resultado", "Impressão Feita");
		} else {

			model.addAttribute("resultado", "Este Aluno Não Existe !");
		}

		return "redirect:Secretaria-Imprimir-Boletin";
	}

	@PostMapping("/Secretaria-Provas-Marcadas")
	public String secretariaMarcarProvao() {

		return "WebGnius/secretaria/provasMarcadas";
	}

	@PostMapping("/Secretaria-Matricula")
	public String secretariaMatricularAluno(HttpServletResponse response,Aluno aluno, Model model) {

		Salvar_SQL s = new Salvar_SQL();
		Turma dadosAluno = s.inserirAluno(this.BD, aluno);

		String turma2 = dadosAluno.getTurma();
		String turma3 = turma2;
		
		System.out.println("TURMA2:"+turma2);
		dadosAluno.setTurma(turma2);
	

		String[] a = turma2.split("_");

		turma2 = this.configurarEscola + " " + a[0] + " AL";
		dadosAluno.setUsuario(turma2);
		this.turma = dadosAluno;

		String curso = aluno.getCurso();
		String nivel = aluno.getNivel();
		String turno = aluno.getTurno();
		String nomeDoAluno = aluno.getNome();
		System.out.println("ALuno: " + nomeDoAluno);

		aluno.setCurso(curso);
		aluno.setNivel(nivel);
		aluno.setTurno(turno);
		aluno.setNome(nomeDoAluno);

		this.aluno.setNome(nomeDoAluno);
		this.aluno.setCurso(curso);
		this.aluno.setNivel(nivel);
		this.aluno.setTurno(turno);

		if (dadosAluno.isAlunoInserido()) {

			model.addAttribute("resultado", "Aluno Inserido Com Sucesso !");
			model.addAttribute("nome", configurarNome);
			model.addAttribute("escola", configurarEscola);
			model.addAttribute("aluno", dadosAluno);

			
			Pesquisar_SQL p = new Pesquisar_SQL();
			Tabela_Actualizar_SQL ta = new Tabela_Actualizar_SQL();
			
			//Actualizando a tabela de trablhos diarios na propina do Tesoureiro
			int diario= p.pesquisarUmConteudo_Numa_Linha_Int(BD, "Func_Diario", "MatEConf", "bi", this.bi, 0);
			++diario;
			ta.actualizarColuna_Qualquer_Linha(BD, "Func_Diario", "MatEConf", diario, "", "bi", this.bi);
		    
			Relatorio r = new Relatorio();
			ImprimirRelatorio ir = new ImprimirRelatorio();
			
			String mes = s.mesActual(this.BD);
			
			this.cursoAluno= curso;
			this.turmaAluno = turma3;
			this.nivel = nivel;
			this.turnoAluno= turno;
			
           
			this.pagina = "redirect:Secretaria-Info";
		} else {

			model.addAttribute("resultado", "Falha ao Inserir o Aluno, volte a Inserir !");
			model.addAttribute("nome", configurarNome);
			model.addAttribute("escola", configurarEscola);

			ArrayList<Turma> alunoDaTurma = new ArrayList<>();
			alunoDaTurma.add(dadosAluno);

			model.addAttribute("aluno", alunoDaTurma);

			this.pagina = "redirect:Secretaria-Matricula";
		}
		return this.pagina;
	}

	@PostMapping("/Secretaria-Lista")
	public String secretariaConfirmacao(@Validated Agenda agenda, Model model, BindingResult erros) {

		if (erros.hasErrors()) {

			return "redirect:Secretaria-Lista";
		}

		return "redirect:Secretaria-Lista";
	}

	@PostMapping({ "/Admin-Minha-Seguranca" })
	public String seguranca(@Validated RecuperarSenha recuperar, Model model) {

		Salvar_SQL s = new Salvar_SQL();
		String pagina = null;

		String senha = recuperar.getSenha();
		String palavra = recuperar.getPalavra();

		if ((senha.equalsIgnoreCase("")) || (senha == null)) {

		} else {

			if (this.quem_E_O_func.equalsIgnoreCase("pca")) {
				s.sistema_Mudar_A_Senha(this.BD, senha, this.BD, 0, this.bi, true);
			} else {
				s.sistema_Mudar_A_Senha(this.BD, senha, this.quem_E_O_func, this.id, "", false);
			}

		}

		if ((palavra.equalsIgnoreCase("")) || (palavra == null)) {

		} else {

			if (this.quem_E_O_func.equalsIgnoreCase("pca")) {
				s.sistema_Mudar_A_Senha(this.BD, palavra, this.BD, 0, this.bi, true);
			} else {
				s.sistema_Mudar_A_Senha(this.BD, palavra, this.quem_E_O_func, this.id, "", false);
			}
		}

		if ((senha.equalsIgnoreCase("")) || (senha == null)) {

		} else {

			model.addAttribute("resultado1", "A Senha Foi Alterada !");
		}

		if ((palavra.equalsIgnoreCase("")) || (palavra == null)) {

		} else {

			model.addAttribute("resultado1", "A palavra Foi Configurada !");
		}

		pagina = "redirect:Admin-Minha-Seguranca";
		this.pagina = pagina;

		// nomeDaPagina= entrarNoSistema.quemEstaAFazerLogin(senha, model);
		return this.pagina;
	}

	@PostMapping({ "/Tesouraria-Minha-Seguranca" })
	public String seguranca7(@Validated RecuperarSenha recuperar, Model model) {

		String pagina = null;

		Salvar_SQL s = new Salvar_SQL();

		String senha = recuperar.getSenha();
		String palavra = recuperar.getPalavra();

		if ((senha.equalsIgnoreCase("")) || (senha == null)) {

		} else {

			s.sistema_Mudar_A_Senha(this.BD, senha, this.quem_E_O_func, this.id, "", false);

		}

		if ((palavra.equalsIgnoreCase("")) || (palavra == null)) {

		} else {

			s.sistema_Mudar_A_Senha(this.BD, palavra, this.quem_E_O_func, this.id, "", false);
		}

		if ((senha.equalsIgnoreCase("")) || (senha == null)) {

		} else {

			model.addAttribute("resultado1", "A Senha Foi Alterada !");
		}

		if ((palavra.equalsIgnoreCase("")) || (palavra == null)) {

		} else {

			model.addAttribute("resultado1", "A palavra Foi Configurada !");
		}

		pagina = "redirect:Tesouraria-Minha-Seguranca";
		this.pagina = pagina;

		// nomeDaPagina= entrarNoSistema.quemEstaAFazerLogin(senha, model);
		return pagina;
	}

	@GetMapping({ "/Coord-Cursos" })
	public String coord2(Model model) {

		String nomeDaPagina = null;
		estado = -5;

		if (entrarNoSistema.podeAbrirAPagina(senha)) {

			// if(acesso.bloqueandoRequisicoes(TechShine_Controller.request, request)) {

			Salvar_SQL s = new Salvar_SQL();
			Pesquisar_SQL p = new Pesquisar_SQL();

			ArrayList<Coordenador> cursos = new ArrayList<>();

			int contador = 0;
			int contador2 = 0;
			int contador3 = 0;
			ArrayList<String> cursos2 = p.pesquisarTudoEmString_Restrito(this.BD, "cursos", "nome", "bi", this.bi, 0);

			System.out.println("Entrou Nos Cursos");
			for (String cadaC : cursos2) {
				++contador2;
				System.out.println("Contador2: " + contador2);
				ArrayList<String> profs = p.pesquisarTudoEmString_Restrito(this.BD, "disciplinas_dos_profs",
						"professor", "curso", cadaC, 0);
				for (String pr : profs) {

					++contador;
					contador3 = contador;
					System.out.println("Contador: " + contador);
				}
				
				Coordenador c = new Coordenador();
				
				c.setCurso(cadaC);
				c.setqProfs(contador);
				cursos.add(c);
				contador=0;

			}

			String mes = s.mesActual(this.BD);
			Date d = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			int renda = p.pesquisarUmConteudo_Numa_Linha_Int(this.BD, this.quem_E_O_func + "_financa", mes, "id", "",
					this.id);

			model.addAttribute("qCursos", contador2);
			model.addAttribute("qProfs", contador3);
			model.addAttribute("data", sdf.format(d));

			if (renda == 0) {
				model.addAttribute("renda", "Irregular");
			} else {
				model.addAttribute("renda", "Regular");
			}

			model.addAttribute("nome", configurarNome);
			model.addAttribute("escola", configurarEscola);

			model.addAttribute("cursos", cursos);

			nomeDaPagina = "TechShine/Coordenador/meusCursos";
			this.pagina = nomeDaPagina;
			// }

		} else {

			estado = 0;
			nomeDaPagina = "redirect:login";
			this.pagina = nomeDaPagina;
		}

		// nomeDaPagina= entrarNoSistema.quemEstaAFazerLogin(senha, model);
		return this.pagina;
	}

	@PostMapping({ "/Coord-Professores" })
	public String coord3(Curso curso, Model model) {

		String pagina = null;

		this.curso2 = curso.getNome();
		pagina = "redirect:Coord-Professores-2";
		this.pagina = pagina;

		// nomeDaPagina= entrarNoSistema.quemEstaAFazerLogin(senha, model);
		return this.pagina;
	}

	@PostMapping({ "/Coord-Permissoes" })
	public String coord4(Coordenador coord, Model model) {

		String pagina = null;

		Salvar_SQL s = new Salvar_SQL();

		System.out.println("Avalicao: " + coord.isAvaliacao());
		System.out.println("Prova: " + coord.isProva());
		System.out.println("Exame: " + coord.isExame());
		System.out.println("Bloqueio: " + coord.isBloqueio());

		s.coordenador_Permitir_Lancar_Provas(this.BD, coord, this.bi);
		pagina = "redirect:Coord-Permissoes";
		this.pagina = pagina;

		// nomeDaPagina= entrarNoSistema.quemEstaAFazerLogin(senha, model);
		return this.pagina;
	}

	@PostMapping({ "/Secretaria-Minha-Seguranca", "/Coord-Minha-Seguranca", "/Aluno-Minha-Seguranca" })
	public String seguranca2(@Validated RecuperarSenha recuperar, Model model) {

		Salvar_SQL s = new Salvar_SQL();
		String pagina = null;

		String senha = recuperar.getSenha();
		String palavra = recuperar.getPalavra();

		if ((senha.equalsIgnoreCase("")) || (senha == null)) {

		} else {

			s.sistema_Mudar_A_Senha(this.BD, senha, this.quem_E_O_func, this.id, "", false);

		}

		if ((palavra.equalsIgnoreCase("")) || (palavra == null)) {

		} else {

			s.sistema_Mudar_A_Senha(this.BD, palavra, this.quem_E_O_func, this.id, "", false);
		}

		if ((senha.equalsIgnoreCase("")) || (senha == null)) {

		} else {

			model.addAttribute("resultado1", "A Senha Foi Alterada !");
		}

		if ((palavra.equalsIgnoreCase("")) || (palavra == null)) {

		} else {

			model.addAttribute("resultado1", "A palavra Foi Configurada !");
		}

		if (this.quem_E_O_func.equalsIgnoreCase("secretaria")) {

			pagina = "redirect:Secretaria-Minha-Seguranca";
		} else if (this.quem_E_O_func.equalsIgnoreCase("coord")) {
			pagina = "redirect:Coord-Minha-Seguranca";
		} else {
			pagina = "redirect:Aluno-Minha-Seguranca";
		}

		this.pagina = pagina;

		// nomeDaPagina= entrarNoSistema.quemEstaAFazerLogin(senha, model);
		return this.pagina;
	}

	// =====================================================
	// Métodos Da WebGenius

	@GetMapping("/WebGenius")
	public String webGenius(Model model) {

		String nomeDaPagina = null;
		estado = -5;

		if (entrarNoSistema.podeAbrirAPagina(senha)) {

			// if(acesso.bloqueandoRequisicoes(TechShine_Controller.request, request)) {
			nomeDaPagina = "WebGnius/WG/inicio";
			this.pagina = nomeDaPagina;

			model.addAttribute("pca", configurarNome);
			// }

		} else {
			estado = 0;
			this.pagina = "redirect:login";
		}

		return this.pagina;

	}

	@GetMapping("/WebGenius-Cadastrar-Escola")
	public String webGenius_Escola(Model model) {

		String nomeDaPagina = null;
		estado = -5;

		if (entrarNoSistema.podeAbrirAPagina(senha)) {

			// if(acesso.bloqueandoRequisicoes(TechShine_Controller.request, request)) {
			nomeDaPagina = "WebGnius/WG/cadastrarEscola";
			this.pagina = nomeDaPagina;

			model.addAttribute("pca", configurarNome);
			// }

		} else {
			estado = 0;
			this.pagina = "redirect:login";
		}

		return this.pagina;

	}

	@PostMapping("/WebGenius-Listar-Escola")
	public String Lista_De_Escolas(Model model, Escola escola) {

		String nomeDaPagina = null;
		estado = -5;

		if (entrarNoSistema.podeAbrirAPagina(senha)) {

			Salvar_SQL s = new Salvar_SQL();
			Pesquisar_SQL p = new Pesquisar_SQL();

			s.inserirTabelaEscolas(escola);

			model.addAttribute("escolas", p.Listar_Escolas_Na_WG());

			// if(acesso.bloqueandoRequisicoes(TechShine_Controller.request, request)) {
			nomeDaPagina = "WebGnius/WG/listaDeEscolas";
			this.pagina = nomeDaPagina;

			model.addAttribute("pca", configurarNome);
			// }

		} else {
			estado = 0;
			this.pagina = "redirect:login";
		}

		return this.pagina;

	}

	@GetMapping("/WebGenius-Listar-Escola")
	public String webGenius_Listar_Escolas(Model model) {

		String nomeDaPagina = null;
		estado = -5;

		Pesquisar_SQL p = new Pesquisar_SQL();

		if (entrarNoSistema.podeAbrirAPagina(senha)) {

			// if(acesso.bloqueandoRequisicoes(TechShine_Controller.request, request)) {
			nomeDaPagina = "WebGnius/WG/listaDeEscolas";
			this.pagina = nomeDaPagina;

			model.addAttribute("pca", configurarNome);
			model.addAttribute("escolas", p.Listar_Escolas_Na_WG());
			// }

		} else {
			estado = 0;
			this.pagina = "redirect:login";
		}

		return this.pagina;

	}

	@GetMapping("/WebGenius-Minha-Seguranca")
	public String webGenius_Minha_Seguranca(Model model) {

		String nomeDaPagina = null;
		estado = -5;

		if (entrarNoSistema.podeAbrirAPagina(senha)) {

			// if(acesso.bloqueandoRequisicoes(TechShine_Controller.request, request)) {
			nomeDaPagina = "WebGnius/WG/definicao";
			this.pagina = nomeDaPagina;

			model.addAttribute("pca", configurarNome);
			// }

		} else {
			estado = 0;
			this.pagina = "redirect:login";
		}

		return this.pagina;

	}

	@GetMapping("/WebGenius-Acesso")
	public String webGenius_Acesso(Model model) {

		String nomeDaPagina = null;
		estado = -5;

		if (entrarNoSistema.podeAbrirAPagina(senha)) {

			// if(acesso.bloqueandoRequisicoes(TechShine_Controller.request, request)) {
			nomeDaPagina = "WebGnius/WG/acesso";
			this.pagina = nomeDaPagina;

			model.addAttribute("pca", configurarNome);
			// }

		} else {
			estado = 0;
			this.pagina = "redirect:login";
		}

		return this.pagina;

	}

	@PostMapping("/WebGenius-Acesso")
	public String webGenius_Escolas(Escola escola, Model model) {

		String nomeDaPagina = null;
		String senha;

		escola.setId(1);
		senha = escola.getId() + "WG";

		model.addAttribute("escola", escola);

		nomeDaPagina = "WebGnius/WG/acesso";
		this.pagina = nomeDaPagina;
		return this.pagina;

	}

	@PostMapping("/imprimirMatricula-2")
	public void impressão(HttpServletResponse response, Model model) {

		
		Pesquisar_SQL p = new Pesquisar_SQL();
		Salvar_SQL s = new Salvar_SQL();
		String nomeDaPagina = null;
		String senha;

		// this.aluno;
		
		int precoDoCurso=0;		
		
		if(this.nivel.contains("10")) {
			precoDoCurso= p.pesquisarUmConteudo_Numa_Linha_Int(BD, "cursos_Niveis", "Decima", "cursos", this.cursoAluno, 0);	
		}else if(this.nivel.contains("11")) {
			precoDoCurso= p.pesquisarUmConteudo_Numa_Linha_Int(BD, "cursos_Niveis", "DecimaPrimeira", "cursos", this.cursoAluno, 0);	
		}else if(this.nivel.contains("12")) {
			precoDoCurso= p.pesquisarUmConteudo_Numa_Linha_Int(BD, "cursos_Niveis", "DecimaSegunda", "cursos", this.cursoAluno, 0);	
		}else if(this.nivel.contains("13")) {
			precoDoCurso= p.pesquisarUmConteudo_Numa_Linha_Int(BD, "cursos_Niveis", "DecimaTerceira", "cursos", this.cursoAluno, 0);	
		}
		
		int valorPago = precoDoCurso;
		String mes = s.mesActual(this.BD);
		ImprimirRelatorio ir = new ImprimirRelatorio();
		ir.relatorioOutrosServicos(BD, response, this.configurarEscola, mes, this.cursoAluno, "matricula", valorPago, this.configurarNome,
				"matricula",this.turnoAluno,this.nivel,this.turmaAluno,this.nomeCompleto);
		

		if (this.quem_E_O_func.equalsIgnoreCase("secretaria")) {

			nomeDaPagina = "redirect:Secretaria-Matricula";
		} else {

			nomeDaPagina = "redirect:Admin-Alunos";
		}

		this.pagina = nomeDaPagina;
		//return this.pagina;

	}
	
	@PostMapping("/imprimirMatricula")
	public String impressão2(Model model) {

		String nomeDaPagina = null;
	

		// this.aluno;

		if (this.quem_E_O_func.equalsIgnoreCase("secretaria")) {

			nomeDaPagina = "redirect:Secretaria-Matricula";
		} else {

			nomeDaPagina = "redirect:Admin-Alunos";
		}

		this.pagina = nomeDaPagina;
		return this.pagina;

	}

	@PostMapping("/WebGenius-Minha-Seguranca")
	public String webGeniusSecurity(RecuperarSenha senha) {

		return "redirect:WebGenius-Minha-Seguranca";
	}

	@GetMapping("/{id}")
	public String removerCurso(Model model, @PathVariable("id") int id) {

		boolean eliminado;
		EliminarLinha_SQL e = new EliminarLinha_SQL();
		eliminado = e.EliminarLinha(this.BD, "cursos", "id", id, "", true);

		if (eliminado) {

			model.addAttribute("resultado", "Curso Eliminado Com Sucesso");
		} else {
			model.addAttribute("resultado", "Falha, O Curso Não Foi Eliminado");
		}
		this.pagina = "redirect:Admin-Todos-Cursos";

		return this.pagina;
	}

	public String imprimir(Pagamento pag, Aluno aluno, String imprimir_O_Que) {

		// Aqui vai a ideia para Imprimir a matricula

		// Abaixo tem os objectos a ser utilizados para a impressão
		// this.aluno
		// this.BD

		this.pagina = "redirect:Admin-Alunos";

		return this.pagina;

	}

	@GetMapping("/imprimirFuncionario")
	public String imprimirFuncionario() {

		// Aqui vai a ideia para Imprimir a matricula

		// Abaixo tem os objectos a ser utilizados para a impressão
		// this.aluno
		// this.BD

		this.pagina = "redirect:Cadastrar-Sistema-5b";

		return this.pagina;

	}

	public void inserirArquivoNoServido(HttpServletRequest request, HttpServletResponse response, MultipartFile arquivo,
			boolean e_Um_Logotipo, String descricao, String sigla,String turma) throws IOException {

		Tabela_Actualizar_SQL ta = new Tabela_Actualizar_SQL();
		Pesquisar_SQL p = new Pesquisar_SQL();
		Salvar_SQL s = new Salvar_SQL();

		this.disciplina = ta.tirarCaracteres(this.disciplina);

		java.io.InputStream is = null;
		FileOutputStream output = null;

		System.out.println("Configurar Escola: " + configurarEscola);
		try {
			String caminho;
			if (e_Um_Logotipo) {
				caminho = request.getServletContext().getRealPath("/" + this.BD + " logotipo") + "/";
			} else {
				caminho = request.getServletContext().getRealPath("/" + configurarEscola + " "+turma+" "+ this.disciplina) + "/";

			}

			System.out.println("Caminho: " + caminho);
			File directorio = new File(caminho);

			if (!directorio.exists()) {

				System.out.println("Criando O Directório");
				directorio.mkdir();
			} else {

				System.out.println(" O Directório Ja Existe ");
			}

			// Mandasr O Arquivo No Directorio Informado
			File file = new File(directorio, arquivo.getOriginalFilename());
			output = new FileOutputStream(file);

			is = arquivo.getInputStream();

			byte[] buffer = new byte[1000000];
			int nLidos;
			while ((nLidos = is.read(buffer)) >= 0) {

				System.out.println("Escrevendo A Imagem no Directorio ");
				output.write(buffer, 0, nLidos);

			}
			output.flush();

			if (e_Um_Logotipo) {

				ta.actualizarColuna_QualquerLinha_String(this.BD, "pca_" + this.BD, "logotipo", caminho, 1);
			} else {

				s.inserir_materiasOnline(this.BD, caminho, arquivo.getOriginalFilename(), descricao, this.bi, sigla);
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {

			is.close();
			output.close();

		}

	}
	
	
	@GetMapping(value = "/Tirar-Acesso-Ao-Sistema/{bi}")
	public String tirarAcesso(@PathVariable String bi) { 

		System.out.println("====  bi: "+bi);

	 return "redirect:"+this.retorno ;
	}
	@GetMapping(value = "/Repor-Acesso-Ao-Sistema/{bi}")
	public String reporAcesso(@PathVariable String bi) { 

		System.out.println("====  bi: "+bi);

		return "redirect:"+this.retorno ;
	}

	public ModelAndView erroDeConnexao() {

		ModelAndView mv = new ModelAndView(this.pagina);
		mv.addObject("mensagem", "Falha Interna, Repita o Processo");
		return mv;
	}

	// =============================================

	@GetMapping("/terminar-sessao")
	public String webGenius_TerminarSessão() {

		senha = " ";
		request = " ";
		this.estado = 0;

		pca = null;
		aluno = null;
		admin = null;
		prof = null;
		coord = null;
		secret = null;
		tesou = null;
		configurarNome = null;
		configurarEscola = null;
		estado2 = 0;

		return "TechShine/login";
	}

	// fIM DOS METODOS ACIMA
	// ===============================

	// =============================
	// FIM DOS MÉTODOS DA WEBGENIUS
	// =============================

	/*
	 * 
	 * @GetMapping("/formacao") public String formacao(){
	 * 
	 * return "TechShine/formacao"; }
	 * 
	 * @GetMapping("/comercio") public String comercio(){
	 * 
	 * return "TechShine/comercio";
	 * 
	 * }
	 * 
	 * @GetMapping("/conheca-nos") public String about_TechShine(){
	 * 
	 * return "TechShine/about_techShine"; }
	 * 
	 * @GetMapping("/compra") public String compra(HttpServletRequest request){
	 * 
	 * 
	 * System.out.println(request.getRequestURL()); return
	 * "TechShine/compra_Comercio"; }
	 * 
	 * @GetMapping("/Ensino-Superior") public String ensinoSuperior(){
	 * 
	 * 
	 * return "TechShine/aprendizagem"; }
	 * 
	 * @GetMapping("/Ensino-Medio") public String ensinoMedio(){
	 * 
	 * 
	 * return "TechShine/aprendizagem"; }
	 * 
	 * @GetMapping("/Ensino-De-Base") public String ensinoDeBase(){
	 * 
	 * 
	 * return "TechShine/aprendizagem"; }
	 * 
	 * @GetMapping("/Centros-De-Formacoes") public String ensinoDeFormacoes(){
	 * 
	 * 
	 * return "TechShine/aprendizagem"; }
	 * 
	 * @GetMapping("/Creches") public String creches(){
	 * 
	 * 
	 * return "TechShine/aprendizagem"; }
	 * 
	 * @GetMapping("/Estude-Connosco") public String descricao(){
	 * 
	 * 
	 * return "TechShine/descricaoGeral"; }
	 * 
	 * 
	 */
	
	
	

}