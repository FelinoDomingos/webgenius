package tecShine.com.controller;

import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.context.annotation.SessionScope;

import tecShine.com.JDBC.Pesquisar_SQL;
import tecShine.com.JDBC.Salvar_SQL;
import tecShine.com.JDBC.Tabela_Actualizar_SQL;
import tecShine.com.dao.Login;
import tecShine.com.model.Aluno;
import tecShine.com.model.DadosAdicionais;
import tecShine.com.model.RecuperarSenha;
import tecShine.com.model.Usuario;
import tecShine.com.repository.AlunoRepository;


@Controller
@SessionScope
public class AlunoController {

	
	
	private Login entrarNoSistema = new Login();
	
	@Autowired
	private AlunoRepository alunoRepository;
	
	
	@GetMapping("/Aluno-Financa")
	public String alunoFinanca(@SessionAttribute("usuario") Usuario usuario, 
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

			Salvar_SQL s = new Salvar_SQL();
			Pesquisar_SQL p = new Pesquisar_SQL();

			String mes = s.mesActual(BD);
			Date d = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			int renda = p.pesquisarUmConteudo_Numa_Linha_Int(BD, quem_E_O_func + "_Financa", mes, "id", "",
					id);
			int nDeEstudante = p.pesquisarUmConteudo_Numa_Linha_Int(BD, quem_E_O_func, "NProc", "id", "",
					id);

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

			String curso = p.pesquisarUmConteudo_Numa_Linha_String(BD, quem_E_O_func, "curso", "id", "", 1);

			
			String n=quem_E_O_func;
			String turma=null;
			
			if(quem_E_O_func.contains("__")) {
				
				String[] a = n.split("__");
				 turma = a[0];
			}else {
				turma = quem_E_O_func;
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
				mes2 = p.pesquisarUmConteudo_Numa_Linha_Int(BD, quem_E_O_func + "_Financa", cadaC, "id", "",
						id);

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

			return "TechShine/Aluno/financa";
			// }

		} 
	   }else {

			return "redirect:login";
		}
		return pagina;

	}
	
	
	
	
	@GetMapping("/Aluno-Notas")
	public String boletimDeNotas(@SessionAttribute("usuario") Usuario usuario, 
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

			Salvar_SQL s = new Salvar_SQL();
			Pesquisar_SQL p = new Pesquisar_SQL();
			Tabela_Actualizar_SQL ta = new Tabela_Actualizar_SQL();

			String mes = s.mesActual(BD);
			Date d = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			int renda = p.pesquisarUmConteudo_Numa_Linha_Int(BD, quem_E_O_func + "_Financa", mes, "id", "",
					id);
			int nDeEstudante = p.pesquisarUmConteudo_Numa_Linha_Int(BD, quem_E_O_func, "NProc", "id", "",
					id);

			if (renda == 0) {

				nomeDaPagina = "TechShine/Aluno/info1";
			} else {

				String turma = quem_E_O_func;
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
				String curso = p.pesquisarUmConteudo_Numa_Linha_String(BD, quem_E_O_func, "curso", "id", "",
						1);
				ArrayList<String> desciplinas = p.pesquisarTudoEmString(BD, "Cursos_Disciplinas", curso);
				String situacao = p.pesquisarUmConteudo_Numa_Linha_String(BD, "cursos", "Situacao", "nome", curso,
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

							aluno.setNota(p.pesquisarUmConteudo_Numa_Linha_Int(BD,
									quem_E_O_func + "_Avaliacao", dessciplinasFiltrada.get(i), "id", "", id));
							aluno.setDisciplina(dessciplinasFiltrada2.get(i));
							aluno.setSituacao("");

							dados.add(aluno);

							break sair;
						} else if (situacao.equalsIgnoreCase("P")) {

							Aluno aluno = new Aluno();

							aluno.setNota(p.pesquisarUmConteudo_Numa_Linha_Int(BD, quem_E_O_func + "_Prova",
									dessciplinasFiltrada.get(i), "id", "", id));
							aluno.setDisciplina(dessciplinasFiltrada2.get(i));
							aluno.setSituacao("");

							dados.add(aluno);

							break sair;
						} else if (situacao.equalsIgnoreCase("E")) {

							Aluno aluno = new Aluno();

							aluno.setNota(p.pesquisarUmConteudo_Numa_Linha_Int(BD, quem_E_O_func + "_Media",
									dessciplinasFiltrada.get(i), "id", "", id));
							aluno.setDisciplina(dessciplinasFiltrada2.get(i));
							aluno.setSituacao(p.pesquisarUmConteudo_Numa_Linha_String(BD,
									quem_E_O_func + "_Media", "Situacao", "id", "", id));

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

			return nomeDaPagina;
			// }

		} 
	  }else {

			return "redirect:login";
		}
		return pagina;

	}
	
	
	@GetMapping("/Aluno")
	public String inicio(@SessionAttribute("usuario") Usuario usuario, 
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

			Salvar_SQL s = new Salvar_SQL();
			Pesquisar_SQL p = new Pesquisar_SQL();

			String mes = s.mesActual(BD);
			Date d = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			int renda = p.pesquisarUmConteudo_Numa_Linha_Int(BD, quem_E_O_func + "_Financa", mes, "id", "",
					id);
			int nDeEstudante = p.pesquisarUmConteudo_Numa_Linha_Int(BD, quem_E_O_func, "NProc", "id", "",
					id);

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

			return "TechShine/Aluno/inicio";
			// }

		} 
	  }else {

			return "redirect:login";
		}
		return pagina;

	}
	
	
	@GetMapping({"/Aluno-Materias-2","/webgenius/Aluno-Materias-2"})
	public String aluno4(@SessionAttribute("usuario") Usuario usuario, 
			final Model model) {
		 
		
		DadosAdicionais da = new DadosAdicionais(); 
		
		String pagina = null;
		String senha = null;
		String nome = null;
		String quem_E_O_func = null;
		String configurarEscola =null;
		String configurarNome = null;
		String BD = null;
		String bi = null;
		int id= 0;
		String turmaAluno = da.getTurma();
		String disciplina = da.getDisciplina();
		String nomeDaPagina;
		
		


		if (entrarNoSistema.podeAbrirAPagina(senha)) {

			// if(acesso.bloqueandoRequisicoes(TechShine_Controller.request, request)) {

			Salvar_SQL s = new Salvar_SQL();
			Pesquisar_SQL p = new Pesquisar_SQL();
			Tabela_Actualizar_SQL ta = new Tabela_Actualizar_SQL();

			String mes = s.mesActual(BD);
			Date d = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			int renda = p.pesquisarUmConteudo_Numa_Linha_Int(BD, quem_E_O_func + "_Financa", mes, "id", "",
					id);
			int nDeEstudante = p.pesquisarUmConteudo_Numa_Linha_Int(BD, quem_E_O_func, "NProc", "id", "",
					id);

			String desciplina = ta.tirarCaracteres(disciplina);

			ArrayList<String> minhasMaterias = new ArrayList<>();

			ArrayList<String> enderecos = p.pesquisarTudoEmString(BD, "Materias_Online", "descricao");

			boolean siglaIgual = false;
			ArrayList<String> siglas = p.pesquisarTudoEmString(BD, "Materias_Online", "sigla");

			sair: for (String c : siglas) {

				
				if ((turmaAluno.contains(c))||
						(turmaAluno.contains(c.toLowerCase()))) {

					siglaIgual = true;
					break sair;
				}
			}

			String ficheiro;
			String data;
			String endereco;

			ArrayList<String> conteudos = new ArrayList<>();
			ArrayList<String> conteudos3 = new ArrayList<>();
			for (String c : enderecos) {

				endereco = p.pesquisarUmConteudo_Numa_Linha_String(BD, "Materias_Online", "endereco", "Descricao",
						c, 0);

				System.out.println("Descricao: " + c);
				System.out.println("ficheiro: " + endereco);
				System.out.println("Desciplina: " + desciplina);
				if ((endereco.contains(desciplina)) && (siglaIgual) && (endereco.contains(turmaAluno))) {

					System.out.println("Iguais");

					data = p.pesquisarUmConteudo_Numa_Linha_String(BD, "Materias_Online", "Data", "Descricao", c,
							0);
					ficheiro = p.pesquisarUmConteudo_Numa_Linha_String(BD, "Materias_Online", "Ficheiro",
							"Descricao", c, 0);

					System.out.println("Data: " + data);
					System.out.println("ficheiro: " + ficheiro);
					System.out.println("Endereco: " + endereco);

					conteudos.add(data + " - " + c);
					conteudos3.add(data + " - " + c + ";" + endereco + ";" + ficheiro);
				}

			}

			for (int i = conteudos.size() - 1; i >= 0; i--) {

				minhasMaterias.add(conteudos.get(i));

			}

		
			if (renda == 0) {
				model.addAttribute("renda", "Irregular");
				model.addAttribute("situacao1", "Indisponivel");
			} else {
				model.addAttribute("renda", "Regular");
				model.addAttribute("situacao1", "Disponivel");
			}

			model.addAttribute("desciplina", disciplina);
			model.addAttribute("materias", minhasMaterias);
			model.addAttribute("numero", nDeEstudante);
			model.addAttribute("data", sdf.format(d));

			model.addAttribute("nome", configurarNome);
			model.addAttribute("escola", configurarEscola);

			nomeDaPagina = "TechShine/Aluno/material2";
			pagina = nomeDaPagina;
			// }

		} else {

			nomeDaPagina = "redirect:login";
			pagina = nomeDaPagina;
		}
		return pagina;

	}
	
	
	
	@GetMapping("/Aluno-Materias")
	public String material(Model model,HttpServletRequest request) {
		
		
		String pagina = null;
		String senha = null;
		String nome = null;
		String quem_E_O_func = null;
		String configurarEscola =null;
		String configurarNome = null;
		String BD = null;
		String bi = null;
		int id= 0;
		
Cookie[] cookies = request.getCookies();

        
		
		try {
		
		
        for (Cookie aCookie : cookies) {
            String name = aCookie.getName();

            if (name.equals("BD")) {
            	BD = aCookie.getValue();
            	BD = URLDecoder.decode(BD, "UTF-8");
            	System.out.println("BD: "+BD);
            }

            if (name.equals("bi")) {
            	bi = aCookie.getValue();
            	bi = URLDecoder.decode(bi, "UTF-8");
            	System.out.println("bi: "+bi);
            }

            
            //if (name.equals("turma")) {
            	//this.turmaAluno = aCookie.getValue();
           // }

            if (name.equals("id")) {
               String id2 = aCookie.getValue();
               id = Integer.parseInt(id2);
               
               System.out.println("id: "+id);
               
            }
            
            if (name.equals("integrante")) {
            	quem_E_O_func = aCookie.getValue();
            	System.out.println("quem_E_O_func: "+quem_E_O_func);
            }
            
            if (name.equals("senha")) {
            	senha = aCookie.getValue();
            	senha = URLDecoder.decode(senha, "UTF-8");
            	System.out.println("senha: "+senha);
            }
            
            if (name.equals("nome")) {
            	configurarNome = aCookie.getValue();
            	configurarNome = URLDecoder.decode(configurarNome, "UTF-8");
            	System.out.println("configurarNome: "+configurarNome);
            	
            }
            if (name.equals("escola")) {
            	configurarEscola = aCookie.getValue();
            	configurarEscola = URLDecoder.decode(configurarEscola, "UTF-8");
            	System.out.println("configurarEscola: "+configurarEscola);
            	
            }

        }
		}catch(Exception e){
			
			
			e.printStackTrace();
		}
		
		

		String nomeDaPagina = null;

		if (entrarNoSistema.podeAbrirAPagina(senha)) {

			// if(acesso.bloqueandoRequisicoes(TechShine_Controller.request, request)) {

			Salvar_SQL s = new Salvar_SQL();
			Pesquisar_SQL p = new Pesquisar_SQL();
			Tabela_Actualizar_SQL ta = new Tabela_Actualizar_SQL();

			String mes = s.mesActual(BD);
			Date d = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			int renda = p.pesquisarUmConteudo_Numa_Linha_Int(BD, quem_E_O_func + "_Financa", mes, "id", "",
					id);
			int nDeEstudante = p.pesquisarUmConteudo_Numa_Linha_Int(BD, quem_E_O_func, "NProc", "id", "",
					id);

			String turma = quem_E_O_func;
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
			String curso = p.pesquisarUmConteudo_Numa_Linha_String(BD, quem_E_O_func, "curso", "id", "", 1);
			ArrayList<String> desciplinas = p.pesquisarTudoEmString(BD, "Cursos_Disciplinas", curso);
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
			pagina = nomeDaPagina;
			// }

		} else {

			nomeDaPagina = "redirect:login";
			pagina = nomeDaPagina;
		}
		return pagina;

	}
	
	
	@GetMapping({ "/Aluno-Minha-Seguranca","/webgenius/Aluno-Minha-Seguranca" })
	public String aluno(Model model,HttpServletRequest request) {
		
		
		String pagina = null;
		String senha = null;
		String nome = null;
		String quem_E_O_func = null;
		String configurarEscola =null;
		String configurarNome = null;
		String BD = null;
		String bi = null;
		int id= 0;
		
Cookie[] cookies = request.getCookies();

        
		
		try {
		
		
        for (Cookie aCookie : cookies) {
            String name = aCookie.getName();

            if (name.equals("BD")) {
            	BD = aCookie.getValue();
            	BD = URLDecoder.decode(BD, "UTF-8");
            	System.out.println("BD: "+BD);
            }

            if (name.equals("bi")) {
            	bi = aCookie.getValue();
            	bi = URLDecoder.decode(bi, "UTF-8");
            	System.out.println("bi: "+bi);
            }

            
            //if (name.equals("turma")) {
            	//this.turmaAluno = aCookie.getValue();
           // }

            if (name.equals("id")) {
               String id2 = aCookie.getValue();
               id = Integer.parseInt(id2);
               
               System.out.println("id: "+id);
               
            }
            
            if (name.equals("integrante")) {
            	quem_E_O_func = aCookie.getValue();
            	System.out.println("quem_E_O_func: "+quem_E_O_func);
            }
            
            if (name.equals("senha")) {
            	senha = aCookie.getValue();
            	senha = URLDecoder.decode(senha, "UTF-8");
            	System.out.println("senha: "+senha);
            }
            
            if (name.equals("nome")) {
            	configurarNome = aCookie.getValue();
            	configurarNome = URLDecoder.decode(configurarNome, "UTF-8");
            	System.out.println("configurarNome: "+configurarNome);
            	
            }
            if (name.equals("escola")) {
            	configurarEscola = aCookie.getValue();
            	configurarEscola = URLDecoder.decode(configurarEscola, "UTF-8");
            	System.out.println("configurarEscola: "+configurarEscola);
            	
            }

        }
		}catch(Exception e){
			
			
			e.printStackTrace();
		}
		
		
		

		String nomeDaPagina = null;

		if (entrarNoSistema.podeAbrirAPagina(senha)) {

			// if(acesso.bloqueandoRequisicoes(TechShine_Controller.request, request)) {

			
			Salvar_SQL s = new Salvar_SQL();
			Pesquisar_SQL p = new Pesquisar_SQL();

			String mes = s.mesActual(BD);
			Date d = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			int renda = p.pesquisarUmConteudo_Numa_Linha_Int(BD, quem_E_O_func + "_Financa", mes, "id", "",
					id);
			int nDeEstudante = p.pesquisarUmConteudo_Numa_Linha_Int(BD, quem_E_O_func, "NProc", "id", "",
					id);

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
			pagina = nomeDaPagina;
			// }

		} else {

			nomeDaPagina = "redirect:login";
			pagina = nomeDaPagina;
		}

		// nomeDaPagina= entrarNoSistema.quemEstaAFazerLogin(senha, model);
		return pagina;
	}
	
	
	
	@PostMapping({ "/Aluno-Materias" })
	public String aluno3(Aluno aluno) {
		
		String pagina = alunoRepository.aluno3(aluno);
		
		return pagina;
		
		
	}
	
	
	@PostMapping("/Aluno-Materias-2")
	public String materias(HttpServletResponse response, Aluno aluno) {
		
		
		String pagina = alunoRepository.materias(response, aluno);
		return pagina;
	}
	
	
	@PostMapping({ "/Secretaria-Minha-Seguranca", "/Coord-Minha-Seguranca", "/Aluno-Minha-Seguranca" })
	public String seguranca2(@Validated RecuperarSenha recuperar) {
		
		String pagina = alunoRepository.seguranca2(recuperar);
		return pagina;
		
	}
	
	
}
