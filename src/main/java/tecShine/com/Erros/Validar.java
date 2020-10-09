package tecShine.com.Erros;

import java.util.ArrayList;

import tecShine.com.JDBC.Controle_ID_SQL;
import tecShine.com.JDBC.Pesquisar_SQL;
import tecShine.com.model.Validacoes;

public class Validar {

	
	public Validacoes tesouraria_Propina_Validar(String BD,
			String curso,String nivel, String turno,
			String nome,String bi){
		
		
		Pesquisar_SQL p = new Pesquisar_SQL();
		Controle_ID_SQL cID = new Controle_ID_SQL();
		boolean temBi=false;
		
		
		ArrayList<String> niveis = p.pesquisarTudoEmString(BD, "infoescola", "niveis");
		

		ArrayList<String> turmasManha = p.pesquisarTudoEmString(BD, "controle_turmas", "Manha");
		ArrayList<String> turmasTarde = p.pesquisarTudoEmString(BD, "controle_turmas", "Tarde");
		ArrayList<String> turmasNoite = p.pesquisarTudoEmString(BD, "controle_turmas", "Noite");

		
		System.out.println("=======================================\n\n");
		
		System.out.println("CURSO: "+curso);
		System.out.println("NOME: "+nome);
		System.out.println("TURNO: "+turno);
		
		if(nome.endsWith(",,")) {
			
	    	String[]a = nome.split(",,");
	    	
	    	if(a.length>0) {
	    		
	    		System.out.println(a[0]);
				nome= 	a[0];
	    	}
	    	
		}
		System.out.println("NOME: "+nome);
		ArrayList<String> todosAlunos = new ArrayList<>();
		
		int nAlunos;
		String aluno;
		int nAlunos2=0;
		  
		sair:
		for (String turma : turmasManha) {
			
			nAlunos = cID.recuperarCodigo(BD, turma, "id");
			for(int i=1;i<=nAlunos;i++) {
				
			 	aluno = p.pesquisarUmConteudo_Numa_Linha_String(BD, turma, "Alunos", "id", "", i);
				nAlunos2 = p.pesquisarUmConteudo_Numa_Linha_Int(BD, turma, "NProc", "id", "", i);
				
				if((nAlunos2+"").equalsIgnoreCase(bi)) {
					
					temBi = true;
					break sair;
				}
				todosAlunos.add(aluno);
			}
		}
		
		 
		
		sair:
		for (String turma : turmasTarde) {

			nAlunos = cID.recuperarCodigo(BD, turma, "id");
			for(int i=1;i<=nAlunos;i++) {

				aluno = p.pesquisarUmConteudo_Numa_Linha_String(BD, turma, "Alunos", "id", "", i);
				nAlunos2 = p.pesquisarUmConteudo_Numa_Linha_Int(BD, turma, "NProc", "id", "", i);
				
				
				if((nAlunos2+"").equalsIgnoreCase(bi)) {

					temBi = true;
					break sair;
				}
				todosAlunos.add(aluno);
			}
		}
		
		
		sair:
		for (String turma : turmasNoite) {

			nAlunos = cID.recuperarCodigo(BD, turma, "id");
			for(int i=1;i<=nAlunos;i++) {

				aluno = p.pesquisarUmConteudo_Numa_Linha_String(BD, turma, "Alunos", "id", "", i);
				
				
				nAlunos2 = p.pesquisarUmConteudo_Numa_Linha_Int(BD, turma, "NProc", "id", "", i);


				if((nAlunos2+"").equalsIgnoreCase(bi)) {

					temBi = true;
					break sair;
				}
				todosAlunos.add(aluno);
			}
		}
		
		Validacoes v = new Validacoes();
		
		v.setTemErro(false);
		
		
		if(temBi) {
			
			
			ArrayList<String> cursosE = p.pesquisarTudoEmString(BD, "cursos", "nome");
			
			sair:
			for(String c : cursosE) {
				
				
				if(c.equalsIgnoreCase(curso)) {
					
					System.out.println("Curso Igual");
					
					for(String n : niveis) {
						
						if(n.equalsIgnoreCase(nivel)) {
							
							System.out.println("Nivel Igual");
							
							if((turno.equalsIgnoreCase("Manha"))||
									(turno.equalsIgnoreCase("Tarde"))||
									(turno.equalsIgnoreCase("Noite"))) {
								
								System.out.println("Turno Igual");
								v.setTemErro(true);
								break sair;
								
							}else {
								System.out.println("Turno Diferente");
								v.setDescricao("Falha, Escolha Um Turno Na Lista" );
							}
							
						}else {
							
							System.out.println("Nivel Diferente");
							
							v.setDescricao("Falha, Escolha Um Nivel Na Lista" );
						}
						
					}
					
				}else {
					System.out.println("Curso Diferente");
					v.setDescricao("Falha, Escolha Um Curso Na Lista" );
				}
			}
			
		}else {
			
			
			
			sair:
				for(String a : todosAlunos) {
					
					if(a.equalsIgnoreCase(nome)) {
						
						System.out.println("Aluno Igual");
						ArrayList<String> cursosE = p.pesquisarTudoEmString(BD, "cursos", "nome");
						
						for(String c : cursosE) {
							
							
							if(c.equalsIgnoreCase(curso)) {
								
								System.out.println("Curso Igual");
								
								for(String n : niveis) {
									
									if(n.equalsIgnoreCase(nivel)) {
										
										System.out.println("Nivel Igual");
										
										if((turno.equalsIgnoreCase("Manha"))||
												(turno.equalsIgnoreCase("Tarde"))||
												(turno.equalsIgnoreCase("Noite"))) {
											
											System.out.println("Turno Igual");
											v.setTemErro(true);
											break sair;
											
										}else {
											System.out.println("Turno Diferente");
											v.setDescricao("Falha, Escolha Um Turno Na Lista" );
										}
										
									}else {
										
										System.out.println("Nivel Diferente");
										
										v.setDescricao("Falha, Escolha Um Nivel Na Lista" );
									}
									
								}
								
							}else {
								System.out.println("Curso Diferente");
								v.setDescricao("Falha, Escolha Um Curso Na Lista" );
							}
						}
					}else {
						
						v.setDescricao("Falha, O "+nome+" Não Pertence a Escola" );
					}
				}
		}
		
		
		
		
		
		return v;
	}
}
