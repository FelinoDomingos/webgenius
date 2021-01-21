package tecShine.com.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.context.annotation.SessionScope;

import tecShine.com.JDBC.Pesquisar_SQL;
import tecShine.com.JDBC.Salvar_SQL;
import tecShine.com.OutrasOperacoes.Operacoes;
import tecShine.com.dao.Login;
import tecShine.com.model.Aluno;
import tecShine.com.model.Curso;
import tecShine.com.model.DadosAdicionais;
import tecShine.com.model.Turma;
import tecShine.com.model.Usuario;
import tecShine.com.service.TecShineService;
import tecShine.com.sessao.SessaoActual;


@Controller
@SessionScope


public class SecretariaController {

	
	
	@Autowired
    private Usuario usuarioActual;
	
	
	private Login entrarNoSistema = new Login();
	private Turma turma = new Turma();
	private ArrayList<String> turmas_Indisponiveis = new ArrayList<String>(); 
	private static Operacoes op = new Operacoes();
	
	
	
	
	private TecShineService ts = new TecShineService();
	
	private TechShineController tc = new TechShineController();
	
	//
	
	@GetMapping({ "/Secretaria","/webgenius/Secretaria"})
	public String secretaria_Inicio(@SessionAttribute("usuario") Usuario usuario, 
			final Model model) {
		
		
    	

		String pagina=null;
		try {
			
			Thread.sleep(5000);
			
			
			
			String BD=null;
			String bi=null;
			int id=0;
			String senha=null;
			String quem_E_O_func=null;
			String configurarNome=null;
			String configurarEscola=null;
			String nomeDaPagina;
			
			String idSessao=null;
			
			
			

			
		
	        /*
	
			idSessao = session.getId();
			
			System.out.println("idSessao: "+idSessao);
		    System.out.println("ID Sessao: "+session.getId());
			for (Usuario element : TecShineService.usuarios) {
				
				System.out.println(element.getIdSessao());
			}*/
			
			//Usuario usuario = op.retornarUsuario2(TecShineService.usuarios, idSessao);
			
			
			//Usuario usuario = (Usuario) ts.getUsuario();
			
			
			if(usuario != null) {
				
				System.out.println("====== !!!!!!  USUARIO SESSAO CONTEM ");
				  
			}
			
			//SessaoActual sa = new SessaoActual();
			//Usuario usuario = sa.pegaarNoRequest();
			if(usuario != null) {
			
			
				
				System.out.println("============    USUARIOS CONTEM  ----------");
				
			
			BD = usuario.getBD();
			System.out.println("BD: "+BD);
			bi = usuario.getBi();
			System.out.println("bi: "+bi);
			configurarEscola=  usuario.getEscola();
			System.out.println("configurarEscola: "+configurarEscola);
			id = usuario.getId();
			System.out.println("id: "+id);
			quem_E_O_func = usuario.getIntegrante();
			System.out.println("quem_E_O_func: "+quem_E_O_func);
			configurarNome = usuario.getNome();
			System.out.println("configurarNome: "+configurarNome);
			senha = usuario.getSenha();
			System.out.println("senha: "+senha);
			
			
			
			if (entrarNoSistema.podeAbrirAPagina(senha)) {

				// if(acesso.bloqueandoRequisicoes(TechShine_Controller.request, request)) {

				Pesquisar_SQL p = new Pesquisar_SQL();
				Salvar_SQL s = new Salvar_SQL();

				String mes = s.mesActual(BD);

				int renda = p.pesquisarUmConteudo_Numa_Linha_Int(BD, quem_E_O_func + "_Financa", mes, "id",
						"", id);


				ArrayList<Integer> propinas = p.pesquisarTudoEmInt(BD, "Func_Diario", "Prop");
				ArrayList<Integer> matri = p.pesquisarTudoEmInt(BD, "Func_Diario", "MatEConf");
				ArrayList<Integer> servicos = p.pesquisarTudoEmInt(BD, "Func_Diario", "servicos");
				
				
				
				int p2 = op.operacoesAdicionais(propinas);
				int mc = op.operacoesAdicionais(matri);
				int os = op.operacoesAdicionais(servicos);
				
				
				
				
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
				
				
				//configurarNome = (String) RequestContextHolder.getRequestAttributes().getAttribute(TechShineController.nome, RequestAttributes.SCOPE_SESSION);

				model.addAttribute("nome", configurarNome);
				model.addAttribute("escola", configurarEscola);

				nomeDaPagina = "WebGnius/secretaria/inicio";
				pagina = nomeDaPagina;
				return "WebGnius/secretaria/inicio";
				//String erURL = response.encodeRedirectURL(pagina);
				//response.sendRedirect(erURL);
				// }
				//response.encodeURL(pagina);

			  
			}
		
         
		 
		 
			} else {


			nomeDaPagina = "redirect:login";
			pagina = nomeDaPagina;
			return "redirect:login";
		}

	
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println(pagina);
		
		return pagina;
	}
	
	
	@GetMapping({"/Secretaria-Imprimir-Boletin","/webgenius/Secretaria-Imprimir-Boletin"})
	public String secretariaBoletin(@SessionAttribute("usuario") Usuario usuario, 
			final Model model) {
		
		
		
		
		
		
		String BD=null;
		String bi=null;
		int id=0;
		String senha=null;
		String quem_E_O_func=null;
		String configurarNome=null;
		String configurarEscola=null;
		String nomeDaPagina;
		String pagina=null;

		
		try {
		
		
           Thread.sleep(5000);
		
		
		
		 /*
	
			idSessao = session.getId();
			
			System.out.println("idSessao: "+idSessao);
		    System.out.println("ID Sessao: "+session.getId());
			for (Usuario element : TecShineService.usuarios) {
				
				System.out.println(element.getIdSessao());
			}*/
			
			//Usuario usuario = op.retornarUsuario2(TecShineService.usuarios, idSessao);
			
			
			//Usuario usuario = (Usuario) ts.getUsuario();
			
           if(usuario != null) {
				
				System.out.println("====== !!!!!!  USUARIO SESSAO CONTEM ");
				  
			}
		
		     if(usuario != null) {
				
				
				System.out.println("============    USUARIOS CONTEM  ----------");
				
			
			BD = usuario.getBD();
			System.out.println("BD: "+BD);
			bi = usuario.getBi();
			System.out.println("bi: "+bi);
			configurarEscola=  usuario.getEscola();
			System.out.println("configurarEscola: "+configurarEscola);
			id = usuario.getId();
			System.out.println("id: "+id);
			quem_E_O_func = usuario.getIntegrante();
			System.out.println("quem_E_O_func: "+quem_E_O_func);
			configurarNome = usuario.getNome();
			System.out.println("configurarNome: "+configurarNome);
			senha = usuario.getSenha();
			System.out.println("senha: "+senha);
			
			
		
		

		if (entrarNoSistema.podeAbrirAPagina(senha)) {

			// if(acesso.bloqueandoRequisicoes(TechShine_Controller.request, request)) {

			Pesquisar_SQL p = new Pesquisar_SQL();
			Salvar_SQL s = new Salvar_SQL();

			ArrayList<Curso> cursos = p.Listar_Cursos(BD);
			ArrayList<String> tCursos = new ArrayList<>();

			for (Curso c : cursos) {

				tCursos.add(c.getNome());
			}
			ArrayList<String> niveis = p.pesquisarTudoEmString(BD, "infoescola", "niveis");

			String mes = s.mesActual(BD);

			int renda = p.pesquisarUmConteudo_Numa_Linha_Int(BD, quem_E_O_func + "_Financa", mes, "id", "",
					id);


			
			    ArrayList<Integer> propinas = p.pesquisarTudoEmInt(BD, "Func_Diario", "Prop");
				ArrayList<Integer> matri = p.pesquisarTudoEmInt(BD, "Func_Diario", "MatEConf");
				ArrayList<Integer> servicos = p.pesquisarTudoEmInt(BD, "Func_Diario", "servicos");
				
				Operacoes op = new Operacoes();
				
				
				int p2 = op.operacoesAdicionais(propinas);
				int mc = op.operacoesAdicionais(matri);
				int os = op.operacoesAdicionais(servicos);
				
				
				
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

			model.addAttribute("cursos", tCursos);
			model.addAttribute("niveis", niveis);

			model.addAttribute("nome", configurarNome);
			model.addAttribute("escola", configurarEscola);

			nomeDaPagina = "WebGnius/secretaria/imprimirBoletin";
			pagina = nomeDaPagina;
			
			return "WebGnius/secretaria/imprimirBoletin";
			// }

		}
			 } else {

			nomeDaPagina = "redirect:login";
			return "redirect:login";
		}

		}catch(Exception e){
			
			
			e.printStackTrace();
		}
		
		
		return pagina;
	}
	
	
	
	@GetMapping({ "/Secretaria-Confirmacao","/webgenius/Secretaria-Confirmacao"})
	public String secretaria_Confirmacao(@SessionAttribute("usuario") Usuario usuario, 
			final Model model) {
		
		
	
		String BD=null;
		String bi=null;
		int id=0;
		String senha=null;
		String quem_E_O_func=null;
		String configurarNome=null;
		String configurarEscola=null;
		String nomeDaPagina;
		String pagina=null;
        
		
		try {
			Thread.sleep(5000);
		
		 

            if(usuario != null) {
				
				System.out.println("====== !!!!!!  USUARIO SESSAO CONTEM ");
				  
			}
		
		     if(usuario != null) {
				
				
				System.out.println("============    USUARIOS CONTEM  ----------");
				
			
			BD = usuario.getBD();
			System.out.println("BD: "+BD);
			bi = usuario.getBi();
			System.out.println("bi: "+bi);
			configurarEscola=  usuario.getEscola();
			System.out.println("configurarEscola: "+configurarEscola);
			id = usuario.getId();
			System.out.println("id: "+id);
			quem_E_O_func = usuario.getIntegrante();
			System.out.println("quem_E_O_func: "+quem_E_O_func);
			configurarNome = usuario.getNome();
			System.out.println("configurarNome: "+configurarNome);
			senha = usuario.getSenha();
			System.out.println("senha: "+senha);
			
			


		

		if (entrarNoSistema.podeAbrirAPagina(senha)) {

			// if(acesso.bloqueandoRequisicoes(TechShine_Controller.request, request)) {

			Pesquisar_SQL p = new Pesquisar_SQL();
			Salvar_SQL s = new Salvar_SQL();

			String mes = s.mesActual(BD);

			int renda = p.pesquisarUmConteudo_Numa_Linha_Int(BD, quem_E_O_func + "_Financa", mes, "id",
					"", id);
			
			
			ArrayList<Integer> propinas = p.pesquisarTudoEmInt(BD, "Func_Diario", "Prop");
			ArrayList<Integer> matri = p.pesquisarTudoEmInt(BD, "Func_Diario", "MatEConf");
			ArrayList<Integer> servicos = p.pesquisarTudoEmInt(BD, "Func_Diario", "servicos");
			
			Operacoes op = new Operacoes();
			
			
			int p2 = op.operacoesAdicionais(propinas);
			int mc = op.operacoesAdicionais(matri);
			int os = op.operacoesAdicionais(servicos);
			
			
			if (renda == 0) {
				model.addAttribute("renda", "Irregular");
			} else {
				model.addAttribute("renda", "Regular");
			}

			

			ArrayList<Curso> cursos = p.Listar_Cursos(BD);
			ArrayList<String> tCursos = new ArrayList<>();

			for (Curso c : cursos) {

				tCursos.add(c.getNome());
			}
			ArrayList<String> niveis = p.pesquisarTudoEmString(BD, "infoescola", "niveis");

			
			
			
			
			Date d = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			
			ArrayList<String> turmasManha = p.pesquisarTudoEmString(BD, "Controle_Turmas", "Manha");
			ArrayList<String> turmasTarde = p.pesquisarTudoEmString(BD, "Controle_Turmas", "Tarde");
			ArrayList<String> turmasNoite = p.pesquisarTudoEmString(BD, "Controle_Turmas", "Noite");

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

					ArrayList<String> alunos = p.pesquisarTudoEmString(BD, turma, "Alunos");

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
			pagina = nomeDaPagina;
			return "WebGnius/secretaria/confirmacao";
			// }

		} 
		     }else {

					nomeDaPagina = "redirect:login";
					pagina = nomeDaPagina;
					return "redirect:login";
				}

	
		}catch(Exception e){
			
			
			e.printStackTrace();
		}
		
		
		return pagina;
	}
	
	
	@GetMapping({ "/webgenius/Secretaria-Info" , "/Secretaria-Info"})
	@Async
	public String admin_Info(Model model,HttpServletRequest request, HttpServletResponse response) {
		
		
		
		String BD=null;
		String bi=null;
		int id=0;
		String senha=null;
		String quem_E_O_func=null;
		String configurarNome=null;
		String configurarEscola=null;
		String pagina = null;
		
		
		try {
			
			Thread.sleep(5000);
			
	
		
		
		

		Pesquisar_SQL p = new Pesquisar_SQL();
		Salvar_SQL s = new Salvar_SQL();
		String nomeDaPagina = null;

		if (entrarNoSistema.podeAbrirAPagina(senha)) {


			
				

				String mes = s.mesActual(BD);

				int renda = p.pesquisarUmConteudo_Numa_Linha_Int(BD, quem_E_O_func + "_Financa", mes, "id",
						"", id);
				
				
				    ArrayList<Integer> propinas = p.pesquisarTudoEmInt(BD, "Func_Diario", "Prop");
					ArrayList<Integer> matri = p.pesquisarTudoEmInt(BD, "Func_Diario", "MatEConf");
					ArrayList<Integer> servicos = p.pesquisarTudoEmInt(BD, "Func_Diario", "servicos");
					
					Operacoes op = new Operacoes();
					
					
					int p2 = op.operacoesAdicionais(propinas);
					int mc = op.operacoesAdicionais(matri);
					int os = op.operacoesAdicionais(servicos);
					
					
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

			

			model.addAttribute("resultado", "Aluno Inserido Com Sucesso !");
			
			model.addAttribute("nome", configurarNome);
			model.addAttribute("escola", configurarEscola);

			ArrayList<Turma> alunoDaTurma = new ArrayList<>();
			alunoDaTurma.add(this.turma);

			model.addAttribute("aluno", alunoDaTurma);

			pagina = nomeDaPagina;
			// }

		} else {

			nomeDaPagina = "redirect:login";
			pagina = nomeDaPagina;
		}
		
	}catch (Exception e) {
		e.printStackTrace();
	}
		return pagina;

	}
	
	@Async
	@GetMapping({ "/Secretaria-Matricula","/webgenius/Secretaria-Matricula"})
	public String secretaria_Matricula(Model model,HttpServletRequest request, HttpServletResponse response) {
		
		
		
		String BD=null;
		String bi=null;
		int id=0;
		String senha=null;
		String quem_E_O_func=null;
		String configurarNome=null;
		String configurarEscola=null;
		String pagina = null;
		String nomeDaPagina = null;
		
		
		try {
			Thread.sleep(5000);
		
            Usuario usuario = (Usuario) ts.getUsuario();
			
			BD = usuario.getBD();
			bi = usuario.getBi();
			configurarEscola=  usuario.getEscola();
			id = usuario.getId();
			quem_E_O_func = usuario.getIntegrante();
			configurarNome = usuario.getNome();
			senha = usuario.getSenha();

		


		if (entrarNoSistema.podeAbrirAPagina(senha)) {

			// if(acesso.bloqueandoRequisicoes(TechShine_Controller.request, request)) {

			Pesquisar_SQL p = new Pesquisar_SQL();
			Salvar_SQL s = new Salvar_SQL();

			String mes = s.mesActual(BD);

			int renda = p.pesquisarUmConteudo_Numa_Linha_Int(BD, quem_E_O_func + "_Financa", mes, "id",
					"", id);
			

			
			ArrayList<Integer> propinas = p.pesquisarTudoEmInt(BD, "Func_Diario", "Prop");
			ArrayList<Integer> matri = p.pesquisarTudoEmInt(BD, "Func_Diario", "MatEConf");
			ArrayList<Integer> servicos = p.pesquisarTudoEmInt(BD, "Func_Diario", "servicos");
			
			Operacoes op = new Operacoes();
			
			
			int p2 = op.operacoesAdicionais(propinas);
			int mc = op.operacoesAdicionais(matri);
			int os = op.operacoesAdicionais(servicos);
			
			
			
			if (renda == 0) {
				model.addAttribute("renda", "Irregular");
			} else {
				model.addAttribute("renda", "Regular");
			}

			ArrayList<Curso> cursos = p.Listar_Cursos(BD);
			ArrayList<String> tCursos = new ArrayList<>();

			for (Curso c : cursos) {

				tCursos.add(c.getNome());
			}
			ArrayList<String> niveis = p.pesquisarTudoEmString(BD, "infoescola", "niveis");

			
			Date d = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			
			model.addAttribute("data", sdf.format(d));
			
			model.addAttribute("p", p2);
			model.addAttribute("mc", mc);
			model.addAttribute("os", os);
			model.addAttribute("cursos", tCursos);
			model.addAttribute("niveis", niveis);

			
			//configurarNome = (String) RequestContextHolder.getRequestAttributes().getAttribute(TechShineController.nome, RequestAttributes.SCOPE_SESSION);
			
			
			model.addAttribute("nome", configurarNome);
			model.addAttribute("escola", configurarEscola);

			nomeDaPagina = "WebGnius/secretaria/cadastrarAluno";
			pagina = nomeDaPagina;
			// }

		} else {

			nomeDaPagina = "redirect:login";
			pagina = nomeDaPagina;
		}

	
		}catch (Exception e) {
			e.printStackTrace();
			
		} 
		
		return pagina;
	}
	
	
	@Async
	@GetMapping({ "/Secretaria-Cadastrar_Aluno","/webgenius/Secretaria-Cadastrar_Aluno"})
	public String secretaria_Cadastrar_Aluno(Model model,HttpServletRequest request, HttpServletResponse response) {
		
		
		
		
		String BD=null;
		String bi=null;
		int id=0;
		String senha=null;
		String quem_E_O_func=null;
		String configurarNome=null;
		String configurarEscola=null;
		String pagina = null;
		
		
		Pesquisar_SQL p = new Pesquisar_SQL();
		Salvar_SQL s = new Salvar_SQL();
		
		try {
			Thread.sleep(5000);
		
			String nomeDaPagina= null;
			HttpSession session = request.getSession();


			  bi = (String)session.getAttribute("bi");
			senha = (String)session.getAttribute("senha");
			BD = (String)session.getAttribute("BD");
			quem_E_O_func = (String)session.getAttribute("integrante");
			configurarNome = (String)session.getAttribute("nome");
			configurarEscola = (String)session.getAttribute("escola");

		


		if (entrarNoSistema.podeAbrirAPagina(senha)) {
		

		String mes = s.mesActual(BD);
	    int renda = p.pesquisarUmConteudo_Numa_Linha_Int(BD, quem_E_O_func + "_Financa", mes, "id",
				"", id);
	    
	    ArrayList<Integer> propinas = p.pesquisarTudoEmInt(BD, "Func_Diario", "Prop");
		ArrayList<Integer> matri = p.pesquisarTudoEmInt(BD, "Func_Diario", "MatEConf");
		ArrayList<Integer> servicos = p.pesquisarTudoEmInt(BD, "Func_Diario", "servicos");
		
		Operacoes op = new Operacoes();
		
		
		int p2 = op.operacoesAdicionais(propinas);
		int mc = op.operacoesAdicionais(matri);
		int os = op.operacoesAdicionais(servicos);
	    
	    
		
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
		model.addAttribute("nome", configurarNome);
		model.addAttribute("escola", configurarEscola);
		
		
		
		
		DadosAdicionais da = new DadosAdicionais();
		
		String curso = da.getAluno().getCurso();
		String nivel = da.getAluno().getNivel();
		String turno = da.getAluno().getTurno();
		model.addAttribute("curso", curso);
		model.addAttribute("nivel", nivel);
		model.addAttribute("turno", turno);
		model.addAttribute("turmasVazia", da.getTurmas());
		model.addAttribute("turmasCheias", this.turmas_Indisponiveis);
		model.addAttribute("nome", configurarNome);
		model.addAttribute("escola", configurarEscola);
		
		pagina = "WebGnius/secretaria/aluno";
		
		} else {

			nomeDaPagina = "redirect:login";
			pagina = nomeDaPagina;
		}
		
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return pagina;
		
	}
	
		
		
	@GetMapping({  "/Secretaria-Minha-Seguranca","/webgenius/Secretaria-Minha-Seguranca"})
	public String secretaria(HttpServletRequest request, Model model) {
		
		
		String BD=null;
		String bi=null;
		int id=0;
		String senha=null;
		String quem_E_O_func=null;
		String configurarNome=null;
		String configurarEscola=null;
		String pagina = null;
		
		
		try {
		    Thread.sleep(5000);
		
       
		

		String nomeDaPagina = null;
		

			if (entrarNoSistema.podeAbrirAPagina(senha)) {

				// if(acesso.bloqueandoRequisicoes(TechShine_Controller.request, request)) {

				Pesquisar_SQL p = new Pesquisar_SQL();
				Salvar_SQL s = new Salvar_SQL();

				String mes = s.mesActual(BD);

				int renda = p.pesquisarUmConteudo_Numa_Linha_Int(BD, quem_E_O_func + "_Financa", mes, "id",
						"", id);


				   ArrayList<Integer> propinas = p.pesquisarTudoEmInt(BD, "Func_Diario", "Prop");
					ArrayList<Integer> matri = p.pesquisarTudoEmInt(BD, "Func_Diario", "MatEConf");
					ArrayList<Integer> servicos = p.pesquisarTudoEmInt(BD, "Func_Diario", "servicos");
					
					Operacoes op = new Operacoes();
					
					
					int p2 = op.operacoesAdicionais(propinas);
					int mc = op.operacoesAdicionais(matri);
					int os = op.operacoesAdicionais(servicos);
					
					
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

				model.addAttribute("nome", configurarNome);
				model.addAttribute("escola", configurarEscola);

				nomeDaPagina = "WebGnius/secretaria/definicao";
				pagina = nomeDaPagina;
				// }

			} else {

				nomeDaPagina = "redirect:login";
				pagina = nomeDaPagina;
			}

		

		
	}catch (Exception e) {
		e.printStackTrace();
	}	
		return pagina;
	}		
	
	
	@PostMapping({"/Secretaria-Cadastrar_Aluno"})
	public String Admin_Cadastrar__Aluno(HttpServletRequest request,Aluno aluno, Model model) {
		
		
		
		String pagina = tc.Admin_Cadastrar__Aluno(request, aluno, model);
		return pagina;
		
	}
	
	@PostMapping({"/Secretaria-Aluno"})
	public String matricularAluno(Model model,Aluno aluno) {
		
		String pagina = tc.matricularAluno(model, aluno);
		return pagina;
		
	}
	
	
	@PostMapping("/Secretaria-Matricula")
	public String secretariaMatricularAluno(HttpServletResponse response,Aluno aluno, Model model) {
		
		
		String pagina = tc.secretariaMatricularAluno(response, aluno, model);
		return pagina;
	}
	
	
	
}
