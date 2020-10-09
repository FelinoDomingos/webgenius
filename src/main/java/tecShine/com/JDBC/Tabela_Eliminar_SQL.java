package tecShine.com.JDBC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class Tabela_Eliminar_SQL {

	public void EliminarQualTabela(String BD,String tabela,
			boolean e_Um_Curso) {
		
		

		ConnectionFactory cf= new ConnectionFactory();
		Tabela_Actualizar_SQL t= new Tabela_Actualizar_SQL();
		Pesquisar_SQL p = new Pesquisar_SQL();
		String usarBD="use "+BD;

		Connection con=null;
		PreparedStatement stm=null;
		
		
		String sql;
		
        
		
            if(tabela.contains("_turma")||
            		(tabela.contains("_Turma"))) {
			
			
			ArrayList<String> turmas= new ArrayList<>();
			
			turmas.add(tabela);
			turmas.add(tabela+"_DadosPessoais");
			turmas.add(tabela+"_Acesso");
			turmas.add(tabela+"_Financa");
		    turmas.add(tabela+"_Avaliacao");
			turmas.add(tabela+"_Prova");
			turmas.add(tabela+"_Media");
			int contaodor=0;
			
			for (String turma : turmas) {
				
				++contaodor;
				
				System.out.println(contaodor);
				
				 sql="drop table "+turma;

				try{
					con = ConnectionFactory.getConnection();

					stm = con.prepareStatement(sql);
					stm.executeUpdate(usarBD);
					stm.executeUpdate();

					System.out.println(turma+" Eliminado com  Sucesso  !!!");


				}catch (Exception e) {
					e.printStackTrace();
				}                                                                                                      
				finally {
					try {
						con.close();
						stm.close();
						 

						System.out.println("Coneccões Fechadas !!!");

					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}
			}
			
			
			if((tabela.contains("_turma"))||(tabela.contains("_Turma"))) {

				t.actualizarColuna_Qualquer_Linha(BD, "controle_turmas", "Manha", 0, "", "Manha", tabela);

				ArrayList<String> cursos= p.pesquisarTudoEmString(BD, "cursos", "nome");

				for (String c : cursos) {




					t.actualizarColuna_Qualquer_Linha(BD, "cursos_turmas", c, 0, "", c, tabela);

				}
			}
			
			
			
			int conteudo= t.retornarUmConteudo_Int(BD, "controle_turmas", "qTurmas");
			--conteudo;
			t.actualizarColuna_Na_PrimeiraLinha(BD, "controle_turmas", "qTurmas", conteudo);
			
			turmas=null;
			t=null;
			
		}else {
			
			   
			if(e_Um_Curso) {
				
				
				 sql="drop table "+tabela;

				try{
					con = ConnectionFactory.getConnection();

					stm = con.prepareStatement(sql);
					stm.executeUpdate(usarBD);
					stm.executeUpdate();

					System.out.println(tabela+" Eliminado com  Sucesso !!!");


				}catch (Exception e) {
					e.printStackTrace();
				}
				finally {
					try {
						con.close();
						stm.close();
						 
						cf=null;

						System.out.println("Coneccões Fechadas !!!");

					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}
				
				int conteudo= t.retornarUmConteudo_Int(BD, "cursos", "QCurso");
				--conteudo;
				t.actualizarColuna_Na_PrimeiraLinha(BD, "cursos", "QCurso", conteudo);
				
			}else {
				
				
				 sql="drop table "+tabela;

				try{
					con = ConnectionFactory.getConnection();

					stm = con.prepareStatement(sql);
					stm.executeUpdate(usarBD);
					stm.executeUpdate();

					System.out.println(tabela+" Eliminado com  Sucesso !!!");


				}catch (Exception e) {
					e.printStackTrace();
				}
				finally {
					try {
						con.close();
						stm.close();
						 
						cf=null;

						System.out.println("Coneccões Fechadas !!!");

					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}
				
			}
			
		}
		
	}
		
	
}
