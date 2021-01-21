package tecShine.com.JDBC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import tecShine.com.model.Aluno;
import tecShine.com.model.Conexao;

public class Tabela_Actualizar_SQL {

	private  ConnectionFactory cf= new ConnectionFactory();
	private  Controle_ID_SQL cID= new Controle_ID_SQL();
	private  Pesquisar_SQL p= new Pesquisar_SQL();
	
	
	
	
	public int retornarUmConteudo_Int(String BD,String tabela, String coluna){
		
		String usarBD="use "+BD;
		
		
		 Connection con=null;
		 ResultSet rs = null;
		 PreparedStatement stm=null ;
		 int conteudos=0;
		 
		 	 String sql="select "+coluna +" from "+tabela;
					
					
					try{
						
						Conexao conexao = new Conexao();
					    conexao.setBD(BD);
					    
						con = ConnectionFactory.getConnection(BD);
						
						stm = con.prepareStatement(sql);
						stm.executeUpdate(usarBD);
						
						rs =stm. executeQuery();
						
						
						
					    sair:
						while(rs.next()) {
							
							
							
							conteudos=rs.getInt(coluna);
							if(conteudos>0) {
								
								break sair;
							}
							System.out.println("Ver conteudo: "+conteudos);
				          
						}
						
						System.out.println("Pesquisa Concluida com Sucesso !!!");
						
						
					}catch (Exception e) {
						e.printStackTrace();
					}
					finally {
						try {
							con.close();
							stm.close();
							rs.close();
							 
							
							System.out.println("Coneccões Fechadas !!!");

						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
					}
				 
				  return conteudos;
	}
	
	private void actualizarQTurmas(String BD,int coluna) {
		
        int conteudos;
        String usarBD="use "+BD;
		
		
		 Connection con=null;
		 PreparedStatement stm=null ;
		 
        
		 
		 //System.out.println("valor do conteudo: "+conteudos);
		 String sql="update Controle_Turmas set qTurmas="+coluna+" where id="+1;
		 System.out.println(sql); 	
			
			try{
				
				Conexao conexao = new Conexao();
			    conexao.setBD(BD);
			    
			    
				con = ConnectionFactory.getConnection(BD);
				
				stm = con.prepareStatement(sql);
				stm.executeUpdate(usarBD);
				stm.executeUpdate();
				
				System.out.println("Actualizado com  Sucesso !!!");
				
				
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
	
	
	
	
	
	public void alterarcolocarDadosNo_ControleDeTurmas(String BD,String coluna,String nomeDaTurma) {
		
		
		 String usarBD="use "+BD;
		 Connection con=null;
		 PreparedStatement stm=null ;
		 int codigo = 0;
		 int controle=0;
		 
		 
		 
			
			 ArrayList<String> conteudos = p.pesquisarColunaVazia(BD, coluna);
			 
			 sair:
			 for (String conteudo : conteudos) {
				
				 ++controle; 
				 if((conteudo.equalsIgnoreCase("")||(conteudo==null))) {
					 
					 codigo=cID.recuperarCodigo(BD,"Controle_Turmas","id");
					 ++codigo;
					 
					 
					 
					 String sql="update Controle_Turmas set "+coluna+"='"+nomeDaTurma
							 +"' where id="+controle;
					 
					 try {
					        
							
						    Conexao conexao = new Conexao();
						    conexao.setBD(BD);
							
							con = ConnectionFactory.getConnection(BD);
							stm = con.prepareStatement(sql);
							stm.executeUpdate(usarBD);
							stm.executeUpdate();
							
						
							System.out.println("Alteração Feita !!!");
							   
						}catch (Exception e) {
							e.printStackTrace();
						}
						finally {
							try {
								con.close();
								stm.close();
								 
								System.out.println("Conexão Principal Fechada !!!");

							} catch (SQLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							
						}
					 
					 break sair;
				 }
			}
			
			 int controle2=cID.recuperarCodigo(BD, "Controle_Turmas", "id");
			 
			 
			  if(controle==controle2) {
				  
				  
				  
				  try{

						 

                        String sql2="insert into Controle_Turmas values(?,?,?,?,?)";

                        Conexao conexao = new Conexao();
					    conexao.setBD(BD);


						 con = ConnectionFactory.getConnection(BD);
						 stm = con.prepareStatement(sql2);
						 stm.executeUpdate(usarBD);




                           
						 codigo=cID.recuperarCodigo(BD,"Controle_Turmas","id");
						 System.out.println("Codigo="+codigo);
						 ++codigo;
                         
						 if(coluna.equalsIgnoreCase("manha")) {
							 
							 stm.setInt(1, codigo);
							 stm.setString(2, nomeDaTurma);
							 stm.setString(3, "");
							 stm.setString(4, "");
							 stm.setInt(5, 0);
						 }else if(coluna.equalsIgnoreCase("Tarde")) {
							 
							 stm.setInt(1, codigo);
							 stm.setString(2, "");
							 stm.setString(3, nomeDaTurma);
							 stm.setString(4, "");
							 stm.setInt(5, 0);
						 }else if(coluna.equalsIgnoreCase("Noite")) {
							 
							 stm.setInt(1, codigo);
							 stm.setString(2, "");
							 stm.setString(3, "");
							 stm.setString(4, nomeDaTurma);
							 stm.setInt(5, 0);
						 }
						 
						
						 stm.executeUpdate();


						 System.out.println("Dados Inseridos!!!");
						 
  
					 }catch (Exception e) {
						 e.printStackTrace();
					 }
					 finally {
						 try {
							 con.close();
							 stm.close();
							  

							 System.out.println("Coneccoes fechadas");

						 } catch (SQLException e) {
							 // TODO Auto-generated catch block
							 e.printStackTrace();
						 }

					 }
				  
			  }
			  
				  int qTurmas=  p.pesquisarUmConteudo_Numa_Linha_Int(BD, "Controle_Turmas", "qTurmas", "id", "", 1);
			      ++qTurmas;
			      
			      Tabela_Actualizar_SQL ta = new Tabela_Actualizar_SQL();
			      ta.actualizarColuna_Na_PrimeiraLinha(BD, "Controle_Turmas", "qTurmas", qTurmas);
	}
	
	
	
	
	
	
	public void alterarTabela_InfoEscola_(String BD,String tabela,String coluna,ArrayList<String> conteudos,
			int qalunos,int qfunc,int QCurso){

		String usarBD="use "+BD;
		int i=1;

		 String sql2="insert into infoescola values(?,?,?,?,?,?,?,?,?,?)";
		 int codigo=0;
			
			
			
		
		
		if(coluna.equalsIgnoreCase("estagio")) {
			
			for (String conteudo : conteudos) {
				
				
				String sql="update "+tabela+ " set "+coluna+"='"+conteudo+"' where id="+i;
				
				Connection con=null;
				 PreparedStatement stm=null ;
				
				try {
				        
					
					Conexao conexao = new Conexao();
				    conexao.setBD(BD);
					
					con = ConnectionFactory.getConnection(BD);
					stm = con.prepareStatement(sql);
					stm.executeUpdate(usarBD);
					stm.executeUpdate();
					
				
					System.out.println("Alteração Feita !!!");
					
					
				}catch (Exception e) {
					e.printStackTrace();
				}
				finally {
					try {
						con.close();
						stm.close();
						 
						System.out.println("Conexão Principal Fechada !!!");

					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
				
			}
			
		}else if(coluna.equalsIgnoreCase("qalunos")) {
			
			
				
				
				String sql="update "+tabela+ " set "+coluna+"="+qalunos+" where id="+1;
				
				Connection con=null;
				 PreparedStatement stm=null ;
				
				try {
				        
					
					Conexao conexao = new Conexao();
				    conexao.setBD(BD);
					
					con = ConnectionFactory.getConnection(BD);
					stm = con.prepareStatement(sql);
					stm.executeUpdate(usarBD);
					stm.executeUpdate();
					
				
					System.out.println("Alteração Feita !!!");
					
					
				}catch (Exception e) {
					e.printStackTrace();
				}
				finally {
					try {
						con.close();
						stm.close();
						 
						System.out.println("Conexão Principal Fechada !!!");

					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
				
			
			
		} else if(coluna.equalsIgnoreCase("qfunc")) {
			
			
			
			
			String sql="update "+tabela+ " set "+coluna+"="+qfunc+" where id="+1;
			
			Connection con=null;
			 PreparedStatement stm=null ;
			
			try {
			        
				Conexao conexao = new Conexao();
			    conexao.setBD(BD);
				
				
				con = ConnectionFactory.getConnection(BD);
				stm = con.prepareStatement(sql);
				stm.executeUpdate(usarBD);
				stm.executeUpdate();
				
			
				System.out.println("Alteração Feita !!!");
				
				
			}catch (Exception e) {
				e.printStackTrace();
			}
			finally {
				try {
					con.close();
					stm.close();
					 
					System.out.println("Conexão Principal Fechada !!!");

				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			
		
		
	}   else if(coluna.equalsIgnoreCase("QCurso")) {
		
		
		
		
		String sql="update "+tabela+ " set "+coluna+"="+QCurso+" where id="+1;
		
		Connection con=null;
		 PreparedStatement stm=null ;
		
		try {
		        
			
			Conexao conexao = new Conexao();
		    conexao.setBD(BD);
			
			con = ConnectionFactory.getConnection(BD);
			stm = con.prepareStatement(sql);
			stm.executeUpdate(usarBD);
			stm.executeUpdate();
			
		
			System.out.println("Alteração Feita !!!");
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				con.close();
				stm.close();
				 
				System.out.println("Conexão Principal Fechada !!!");

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
	
	
}
		else if(coluna.equalsIgnoreCase("materias")) {
			
			
			 int controle=cID.recuperarCodigo(BD, tabela, coluna);
			 int controle2=p.pesquisarQConteudos_Na_coluna(BD, tabela, coluna, true);
			 
			 Connection con=null;
			 PreparedStatement stm=null ;
			 
			 for (String conteudo : conteudos) {
				 
				 ++controle2;
				 if(controle2<=controle) {
					 
					 String sql="update "+tabela+ " set "+coluna+"='"+conteudo+"' where id="+controle2;
					 
					 try {
						   Conexao conexao = new Conexao();
						    conexao.setBD(BD);
							
							
							
							con = ConnectionFactory.getConnection(BD);
							stm = con.prepareStatement(sql);
							stm.executeUpdate(usarBD);
							stm.executeUpdate();
							
						
							System.out.println("Alteração Feita !!!");
							
							
						}catch (Exception e) {
							e.printStackTrace();
						}
						finally {
							try {
								con.close();
								stm.close();
								 
								System.out.println("Conexão Principal Fechada !!!");

							} catch (SQLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							
						}
						
				 }else {
					 
					 
					 
					 try{

						 


						 Conexao conexao = new Conexao();
						    conexao.setBD(BD);


						 con = ConnectionFactory.getConnection(BD);
						 stm = con.prepareStatement(sql2);
						 stm.executeUpdate(usarBD);





						 codigo=cID.recuperarCodigo(BD,"infoescola","id");
						 System.out.println("Codigo="+codigo);
						 ++codigo;

						 stm.setInt(1, codigo);
						 stm.setString(2, "");
						 stm.setString(3, "");
						 stm.setInt(4, 0);
						 stm.setString(5, conteudo);
						 stm.setString(6, "");
						 stm.setInt(7, 0);
						 stm.setInt(8, 0);
						 stm.setInt(9, 0);
						 stm.setString(10, "");

			            
						 stm.executeUpdate();


						 System.out.println("Dados Inseridos!!!");


					 }catch (Exception e) {
						 e.printStackTrace();
					 }
					 finally {
						 try {
							 con.close();
							 stm.close();
							  

							 System.out.println("Coneccoes fechadas");

						 } catch (SQLException e) {
							 // TODO Auto-generated catch block
							 e.printStackTrace();
						 }

					 }
					 
				 }
				 
				
			 }
			 
			 
		}else if(coluna.equalsIgnoreCase("documentos")) {
			
			
			 int controle=cID.recuperarCodigo(BD, tabela, coluna);
			 int controle2=p.pesquisarQConteudos_Na_coluna(BD, tabela, coluna, true);
			 
			 System.out.println("Controle: "+controle);
			 System.out.println("Controle2: "+controle2);
			 
			 Connection con=null;
			 PreparedStatement stm=null ;
			 
			 for (String conteudo : conteudos) {
				 
				 
				 ++controle2;
				 if(controle2<=controle) {
					 
					 System.out.println("Entrou Aqui");
					 String sql="update "+tabela+ " set "+coluna+"='"+conteudo+"' where id="+controle2;
					 
					 try {
					        
						 Conexao conexao = new Conexao();
						    conexao.setBD(BD);
							
							
							con = ConnectionFactory.getConnection(BD);
							stm = con.prepareStatement(sql);
							stm.executeUpdate(usarBD);
							stm.executeUpdate();
							
						
							System.out.println("Alteração Feita !!!");
							
							
						}catch (Exception e) {
							e.printStackTrace();
						}
						finally {
							try {
								con.close();
								stm.close();
								 
								System.out.println("Conexão Principal Fechada !!!");

							} catch (SQLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							
						}
						
				 }else {
					 
					 
					 
					 try{

						 Conexao conexao = new Conexao();
						    conexao.setBD(BD);
						
						 con = ConnectionFactory.getConnection(BD);
						 stm = con.prepareStatement(sql2);
						 stm.executeUpdate(usarBD);





						 codigo=cID.recuperarCodigo(BD,"infoescola","id");
						 System.out.println("Codigo="+codigo);
						 ++codigo;

						 stm.setInt(1, codigo);
						 stm.setString(2, "");
						 stm.setString(3, "");
						 stm.setString(4, "");
						 stm.setString(5, conteudo);
						 stm.setInt(6, 0);
						 stm.setInt(7, 0);
						 stm.setInt(8, 0);
						 stm.setString(9, "");

			            
						 stm.executeUpdate();


						 System.out.println("Dados Inseridos!!!");


					 }catch (Exception e) {
						 e.printStackTrace();
					 }
					 finally {
						 try {
							 con.close();
							 stm.close();
							  

							 System.out.println("Coneccoes fechadas");

						 } catch (SQLException e) {
							 // TODO Auto-generated catch block
							 e.printStackTrace();
						 }

					 }
					 
				 }
				 
				 
			 }
			 
			 
		}
		
	}
	
	
	public void alterarTabela_Coluna_String(String BD,String tabela,String conteudo,
			String curso){

		String usarBD="use "+BD;
		int codigo=0;
		
		 Salvar_SQL s = new Salvar_SQL();
		 Pesquisar_SQL p= new Pesquisar_SQL();
		 
		ArrayList<String> conteudos= p.pesquisarTudoEmString(BD, "cursos", "nome");
		 
		 if(conteudos!=null) {
			 
			 String trechoDoSQL=s.gerarSQL_Dinamico(conteudos);
			 trechoDoSQL=trechoDoSQL+",?";
			 int j=0;
			 
			 sair:
			 for (String c : conteudos) {
				
				 ++j;
				 if(curso.equalsIgnoreCase(c)) {
					 
					 
					 int controle=cID.recuperarCodigo(BD, tabela, curso);
					 int controle2=p.pesquisarQConteudos_Na_coluna(BD, tabela, curso, true);
					 
					 Connection con=null;
					 PreparedStatement stm=null ;
					 
					 

					 
					 ++controle2;
					 if(controle2<=controle) {
						 
						 String sql="update "+tabela+ " set "+curso+"='"+conteudo+"' where id="+controle2;
						 
						 try {
						        
								
							 Conexao conexao = new Conexao();
							    conexao.setBD(BD);
								
								con = ConnectionFactory.getConnection(BD);
								stm = con.prepareStatement(sql);
								stm.executeUpdate(usarBD);
								stm.executeUpdate();
								
							
								System.out.println("Alteração Feita !!!");
								
								
							}catch (Exception e) {
								e.printStackTrace();
							}
							finally {
								try {
									con.close();
									stm.close();
									 
									System.out.println("Conexão Principal Fechada !!!");

								} catch (SQLException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								
							}
							
					 }else {
						 
						 
						 
						 try{

							 

                            String sql2= "insert into "+tabela+" values("+trechoDoSQL+");";
                            System.out.println("SQL="+sql2);

                            Conexao conexao = new Conexao();
    					    conexao.setBD(BD);

							 con = ConnectionFactory.getConnection(BD);
							 stm = con.prepareStatement(sql2);
							 stm.executeUpdate(usarBD);





							 codigo=cID.recuperarCodigo(BD,tabela,"id");
							 System.out.println("Codigo="+codigo);
							 ++codigo;

							 stm.setInt(1, codigo);
							 
							 int i=0;
							 int m;
							 for (int k=0;k<conteudos.size();k++) {
								
								 
								++i;
								m=k+2;
								
								System.out.println("I="+i);
								System.out.println("M="+m);
								 
								 
								if(i==j) {
									
									System.out.println("Atingiu !!!");
									System.out.println("J no Atingiu: "+j);
									
									stm.setString(m, conteudo);
								}else {
									System.out.println("Vezes");
									stm.setString(m, "");
								}
								
							}
							 
							 stm.executeUpdate();


							 System.out.println("Dados Inseridos!!!");


						 }catch (Exception e) {
							 e.printStackTrace();
						 }
						 finally {
							 try {
								 con.close();
								 stm.close();
								  

								 System.out.println("Coneccoes fechadas");

							 } catch (SQLException e) {
								 // TODO Auto-generated catch block
								 e.printStackTrace();
							 }

						 }
						 
					 }
					 
					
				 
					 
					 
					 break sair;
				 }
			}
			 
		 }
		
          
	}
	
	
	
	public void alterarTabela_Escola_Financa(String BD,String conteudo,
			String porEmQualColuna,int preco_Estagio_Recurso_PropTempo,
			String nome_Doc_Mat_Func_Maatricula_E_Faltas_Propina_Confir,
			int preco_Doc_Mat_Func_Maatricula_E_Faltas){
          
		String usarBD="use "+BD;
		int codigo=0;
		
		 Pesquisar_SQL p= new Pesquisar_SQL();
		 
		        
		  if(porEmQualColuna.equalsIgnoreCase("documentos")) {
		    	
		    	
		    	int controle=cID.recuperarCodigo(BD, "Escola_Financa", "id");
				 int controle2=p.pesquisarQConteudos_Na_coluna(BD, "Escola_Financa", "Docs", true);
				 
				 Connection con=null;
				 PreparedStatement stm=null ;
				 
				 

				 
				 ++controle2;
				 if(controle2<=controle) {
					 
					 String sql="update Escola_Financa set "+"Docs"+"='"+conteudo+"' where id="+controle2;
					 
					 try {
					        
						 Conexao conexao = new Conexao();
						    conexao.setBD(BD);
							
							
							con = ConnectionFactory.getConnection(BD);
							stm = con.prepareStatement(sql);
							stm.executeUpdate(usarBD);
							stm.executeUpdate();
							
						
							System.out.println("Alteração Feita !!!");
							
							
						}catch (Exception e) {
							e.printStackTrace();
						}
						finally {
							try {
								con.close();
								stm.close();
								 
								System.out.println("Conexão Principal Fechada !!!");

							} catch (SQLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							
						}
						
				 }else {
					 
					 
					 
					 try{

						 

                       String sql2= "insert into Escola_Financa values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
                       System.out.println("SQL="+sql2);

                       Conexao conexao = new Conexao();
					    conexao.setBD(BD);

						 con = ConnectionFactory.getConnection(BD);
						 stm = con.prepareStatement(sql2);
						 stm.executeUpdate(usarBD);





						 codigo=cID.recuperarCodigo(BD,"Escola_Financa","id");
						 System.out.println("Codigo="+codigo);
						 ++codigo;

							
						 

						 stm.setInt(1, codigo);
						 stm.setString(2, "");
						 stm.setInt(3, 0);
						 stm.setString(4, "");
						 stm.setInt(5, 0);
						 stm.setString(6, conteudo);
						 stm.setInt(7, 0);
						 stm.setString(8, "");
						 stm.setInt(9, 0);
						 stm.setInt(10, 0);
						 stm.setInt(11, 0);
						 stm.setString(12, "");
						 stm.setInt(13, 0);
						 stm.setString(14, "");
						 stm.setInt(15, 0);
					     stm.setInt(16, 0);
					     stm.setString(17, "");
					     stm.setInt(18, 0);
					
						 
						 stm.executeUpdate();


						 System.out.println("Dados Inseridos!!!");


					 }catch (Exception e) {
						 e.printStackTrace();
					 }
					 finally {
						 try {
							 con.close();
							 stm.close();
							  

							 System.out.println("Coneccoes fechadas");

						 } catch (SQLException e) {
							 // TODO Auto-generated catch block
							 e.printStackTrace();
						 }

					 }
					 
				 }
		    	
		    }else if(porEmQualColuna.equalsIgnoreCase("Materias")) {
		    	
		    	
		    	int controle=cID.recuperarCodigo(BD, "Escola_Financa", "id");
				 int controle2=p.pesquisarQConteudos_Na_coluna(BD, "Escola_Financa", "Mat", true);
				 
				 Connection con=null;
				 PreparedStatement stm=null ;
				 
				 

				 
				 ++controle2;
				 if(controle2<=controle) {
					 
					 String sql="update Escola_Financa set "+"Mat"+"='"+conteudo+"' where id="+controle2;
					 
					 try {
					        
						 Conexao conexao = new Conexao();
						    conexao.setBD(BD);
							
							
							con = ConnectionFactory.getConnection(BD);
							stm = con.prepareStatement(sql);
							stm.executeUpdate(usarBD);
							stm.executeUpdate();
							
						
							System.out.println("Alteração Feita !!!");
							
							
						}catch (Exception e) {
							e.printStackTrace();
						}
						finally {
							try {
								con.close();
								stm.close();
								 
								System.out.println("Conexão Principal Fechada !!!");

							} catch (SQLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							
						}
						
				 }else {
					 
					 
					 
					 try{

						 

                       String sql2= "insert into Escola_Financa values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
                       System.out.println("SQL="+sql2);

                       
                       Conexao conexao = new Conexao();
					    conexao.setBD(BD);

						 con = ConnectionFactory.getConnection(BD);
						 stm = con.prepareStatement(sql2);
						 stm.executeUpdate(usarBD);





						 codigo=cID.recuperarCodigo(BD,"Escola_Financa","id");
						 System.out.println("Codigo="+codigo);
						 ++codigo;

							
						 

						 stm.setInt(1, codigo);
						 stm.setString(2, "");
						 stm.setInt(3, 0);
						 stm.setString(4, "");
						 stm.setInt(5, 0);
						 stm.setString(6, "");
						 stm.setInt(7, 0);
						 stm.setString(8, conteudo);
						 stm.setInt(9, 0);
						 stm.setInt(10, 0);
						 stm.setInt(11, 0);
						 stm.setString(12, "");
						 stm.setInt(13, 0);
						 stm.setString(14, "");
						 stm.setInt(15, 0);
					     stm.setInt(16, 0);
					     stm.setString(17, "");
					     stm.setInt(18, 0);
					
						 
						 stm.executeUpdate();


						 System.out.println("Dados Inseridos!!!");


					 }catch (Exception e) {
						 e.printStackTrace();
					 }
					 finally {
						 try {
							 con.close();
							 stm.close();
							  

							 System.out.println("Coneccoes fechadas");

						 } catch (SQLException e) {
							 // TODO Auto-generated catch block
							 e.printStackTrace();
						 }

					 }
					 
				 }
		    	
		    } else if(porEmQualColuna.equalsIgnoreCase("confirmacao")) {
		    	
		    	
		    	int controle=cID.recuperarCodigo(BD, "Escola_Financa", "id");
				 int controle2=p.pesquisarQConteudos_Na_coluna(BD, "Escola_Financa", "Confirmacao", true);
				 
				 Connection con=null;
				 PreparedStatement stm=null ;
				 
				 

				 
				 ++controle2;
				 if(controle2<=controle) {
					 
					 String sql="update Escola_Financa set "+"Confirmacao"+"='"+conteudo+"' where id="+controle2;
					 
					 try {
					        
						 Conexao conexao = new Conexao();
						    conexao.setBD(BD);
							
							
							con = ConnectionFactory.getConnection(BD);
							stm = con.prepareStatement(sql);
							stm.executeUpdate(usarBD);
							stm.executeUpdate();
							
						
							System.out.println("Alteração Feita !!!");
							
							
						}catch (Exception e) {
							e.printStackTrace();
						}
						finally {
							try {
								con.close();
								stm.close();
								 
								System.out.println("Conexão Principal Fechada !!!");

							} catch (SQLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							
						}
						
				 }else {
					 
					 
					 
					 try{

						 

                       String sql2= "insert into Escola_Financa values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
                       System.out.println("SQL="+sql2);


                       
                       Conexao conexao = new Conexao();
					    conexao.setBD(BD);
					    
					    
						 con = ConnectionFactory.getConnection(BD);
						 stm = con.prepareStatement(sql2);
						 stm.executeUpdate(usarBD);





						 codigo=cID.recuperarCodigo(BD,"Escola_Financa","id");
						 System.out.println("Codigo="+codigo);
						 ++codigo;

							
						 

						 stm.setInt(1, codigo);
						 stm.setString(2, "");
						 stm.setInt(3, 0);
						 stm.setString(4, "");
						 stm.setInt(5, 0);
						 stm.setString(6, "");
						 stm.setInt(7, 0);
						 stm.setString(8, "");
						 stm.setInt(9, 0);
						 stm.setInt(10, 0);
						 stm.setInt(11, 0);
						 stm.setString(12, "");
						 stm.setInt(13, 0);
						 stm.setString(14, "");
						 stm.setInt(15, 0);
					     stm.setInt(16, 0);
					     stm.setString(17,conteudo);
					     stm.setInt(18, 0);
					
						 
						 stm.executeUpdate();


						 System.out.println("Dados Inseridos!!!");


					 }catch (Exception e) {
						 e.printStackTrace();
					 }
					 finally {
						 try {
							 con.close();
							 stm.close();
							  

							 System.out.println("Coneccoes fechadas");

						 } catch (SQLException e) {
							 // TODO Auto-generated catch block
							 e.printStackTrace();
						 }

					 }
					 
				 }
		    	
		    }
		    
		    
		    else if(porEmQualColuna.equalsIgnoreCase("Propinas")) {
		    	
		    	
		    	int controle=cID.recuperarCodigo(BD, "Escola_Financa", "id");
				 int controle2=p.pesquisarQConteudos_Na_coluna(BD, "Escola_Financa", "Propinas", true);
				 
				 Connection con=null;
				 PreparedStatement stm=null ;
				 
				 

				 
				 ++controle2;
				 if(controle2<=controle) {
					 
					 String sql="update Escola_Financa set "+"Propinas"+"='"+conteudo+"' where id="+controle2;
					 
					 try {
					        
						 Conexao conexao = new Conexao();
						    conexao.setBD(BD);
							
							
							
							con = ConnectionFactory.getConnection(BD);
							stm = con.prepareStatement(sql);
							stm.executeUpdate(usarBD);
							stm.executeUpdate();
							
						
							System.out.println("Alteração Feita !!!");
							
							
						}catch (Exception e) {
							e.printStackTrace();
						}
						finally {
							try {
								con.close();
								stm.close();
								 
								System.out.println("Conexão Principal Fechada !!!");

							} catch (SQLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							
						}
						
				 }else {
					 
					 
					 
					 try{

						 

                       String sql2= "insert into Escola_Financa values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
                       System.out.println("SQL="+sql2);


                       
                       Conexao conexao = new Conexao();
					    conexao.setBD(BD);
					    
					    
						 con = ConnectionFactory.getConnection(BD);
						 stm = con.prepareStatement(sql2);
						 stm.executeUpdate(usarBD);





						 codigo=cID.recuperarCodigo(BD,"Escola_Financa","id");
						 System.out.println("Codigo="+codigo);
						 ++codigo;

							
						 

						 stm.setInt(1, codigo);
						 stm.setString(2, "");
						 stm.setInt(3, 0);
						 stm.setString(4, conteudo);
						 stm.setInt(5, 0);
						 stm.setString(6, "");
						 stm.setInt(7, 0);
						 stm.setString(8, "");
						 stm.setInt(9, 0);
						 stm.setInt(10, 0);
						 stm.setInt(11, 0);
						 stm.setString(12, "");
						 stm.setInt(13, 0);
						 stm.setString(14, "");
						 stm.setInt(15, 0);
					     stm.setInt(16, 0);
					     stm.setString(17, "");
					     stm.setInt(18, 0);
					
						 
						 stm.executeUpdate();


						 System.out.println("Dados Inseridos!!!");


					 }catch (Exception e) {
						 e.printStackTrace();
					 }
					 finally {
						 try {
							 con.close();
							 stm.close();
							  

							 System.out.println("Coneccoes fechadas");

						 } catch (SQLException e) {
							 // TODO Auto-generated catch block
							 e.printStackTrace();
						 }

					 }
					 
				 }
		    	
		    }else if(porEmQualColuna.equalsIgnoreCase("Matricula")) {
		    	
		    	
		    	int controle=cID.recuperarCodigo(BD, "Escola_Financa", "id");
				int controle2=p.pesquisarQConteudos_Na_coluna(BD, "Escola_Financa", "Matricula", true);
				 
				 Connection con=null;
				 PreparedStatement stm=null ;
				 
				 

				 
				 ++controle2;
				 if(controle2<=controle) {
					 
					 String sql="update Escola_Financa set "+"Matricula"+"='"+conteudo+"' where id="+controle2;
					 
					 try {
					        
							
							
						 Conexao conexao = new Conexao();
						    conexao.setBD(BD);
							
							con = ConnectionFactory.getConnection(BD);
							stm = con.prepareStatement(sql);
							stm.executeUpdate(usarBD);
							stm.executeUpdate();
							
						
							System.out.println("Alteração Feita !!!");
							
							
						}catch (Exception e) {
							e.printStackTrace();
						}
						finally {
							try {
								con.close();
								stm.close();
								 
								System.out.println("Conexão Principal Fechada !!!");

							} catch (SQLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							
						}
						
				 }else {
					 
					 
					 
					 try{

						 

                       String sql2= "insert into Escola_Financa values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
                       System.out.println("SQL="+sql2);


                       
                       Conexao conexao = new Conexao();
					    conexao.setBD(BD);
					    
					    
						 con = ConnectionFactory.getConnection(BD);
						 stm = con.prepareStatement(sql2);
						 stm.executeUpdate(usarBD);





						 codigo=cID.recuperarCodigo(BD,"Escola_Financa","id");
						 System.out.println("Codigo="+codigo);
						 ++codigo;

							
						 

						 stm.setInt(1, codigo);
						 stm.setString(2, "");
						 stm.setInt(3, 0);
						 stm.setString(4, "");
						 stm.setInt(5, 0);
						 stm.setString(6, "");
						 stm.setInt(7, 0);
						 stm.setString(8, "");
						 stm.setInt(9, 0);
						 stm.setInt(10, 0);
						 stm.setInt(11, 0);
						 stm.setString(12, conteudo);
						 stm.setInt(13, 0);
						 stm.setString(14, "");
						 stm.setInt(15, 0);
					     stm.setInt(16, 0);
					     stm.setString(17, "");
					     stm.setInt(18, 0);
					     
					
						 
						 stm.executeUpdate();


						 System.out.println("Dados Inseridos!!!");


					 }catch (Exception e) {
						 e.printStackTrace();
					 }
					 finally {
						 try {
							 con.close();
							 stm.close();
							  

							 System.out.println("Coneccoes fechadas");

						 } catch (SQLException e) {
							 // TODO Auto-generated catch block
							 e.printStackTrace();
						 }

					 }
					 
				 }
		    	
		    }else if(porEmQualColuna.equalsIgnoreCase("TempoPropina")) {
		    	
		    	
				 Connection con=null;
				 PreparedStatement stm=null ;
				 
				 

					 String sql="update Escola_Financa set "+"TempoPropina"+"="+preco_Estagio_Recurso_PropTempo+" where id="+1;
					 
					 try {
					        
							
						 Conexao conexao = new Conexao();
						    conexao.setBD(BD);
							
							
							con = ConnectionFactory.getConnection(BD);
							stm = con.prepareStatement(sql);
							stm.executeUpdate(usarBD);
							stm.executeUpdate();
							
						
							System.out.println("Alteração Feita !!!");
							
							
						}catch (Exception e) {
							e.printStackTrace();
						}
						finally {
							try {
								con.close();
								stm.close();
								 
								System.out.println("Conexão Principal Fechada !!!");

							} catch (SQLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							
						}
						
				 }else if(porEmQualColuna.equalsIgnoreCase("Recurso")) {
				    	
				    	
					 
					 Connection con=null;
					 PreparedStatement stm=null ;
					 
					 

						 String sql="update Escola_Financa set "+"Recurso"+"="+preco_Estagio_Recurso_PropTempo+" where id="+1;
						 
						 try {
						        
							 Conexao conexao = new Conexao();
							    conexao.setBD(BD);
								
								
								con = ConnectionFactory.getConnection(BD);
								stm = con.prepareStatement(sql);
								stm.executeUpdate(usarBD);
								stm.executeUpdate();
								
							
								System.out.println("Alteração Feita !!!");
								
								
							}catch (Exception e) {
								e.printStackTrace();
							}
							finally {
								try {
									con.close();
									stm.close();
									 
									System.out.println("Conexão Principal Fechada !!!");

								} catch (SQLException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								
							}
							
					 }else if(porEmQualColuna.equalsIgnoreCase("Estagio")) {
					    	
					    	
						 
						 Connection con=null;
						 PreparedStatement stm=null ;
						 
						 

							 String sql="update Escola_Financa set "+"Estagio"+"="+preco_Estagio_Recurso_PropTempo+" where id="+1;
							 
							 try {
							        
									
								 Conexao conexao = new Conexao();
								    conexao.setBD(BD);
									
									con = ConnectionFactory.getConnection(BD);
									stm = con.prepareStatement(sql);
									stm.executeUpdate(usarBD);
									stm.executeUpdate();
									
								
									System.out.println("Alteração Feita !!!");
									
									
								}catch (Exception e) {
									e.printStackTrace();
								}
								finally {
									try {
										con.close();
										stm.close();
										 
										System.out.println("Conexão Principal Fechada !!!");

									} catch (SQLException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
									
								}
								
						 }else if(porEmQualColuna.equalsIgnoreCase("preco1")) {
						    	
						    	
							 
							 Connection con=null;
							 PreparedStatement stm=null ;
							 
							
						

								 String sql="update Escola_Financa set "+"PrecoFunc"+"="+
								 preco_Doc_Mat_Func_Maatricula_E_Faltas+" where Funcionarios='"+
								 nome_Doc_Mat_Func_Maatricula_E_Faltas_Propina_Confir+"'";
								 
								 try {
								        
									 Conexao conexao = new Conexao();
									    conexao.setBD(BD);
										
										
										con = ConnectionFactory.getConnection(BD);
										stm = con.prepareStatement(sql);
										stm.executeUpdate(usarBD);
										stm.executeUpdate();
										
									
										System.out.println("Alteração Feita !!!");
										
										
									}catch (Exception e) {
										e.printStackTrace();
									}
									finally {
										try {
											con.close();
											stm.close();
											 
											System.out.println("Conexão Principal Fechada !!!");

										} catch (SQLException e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
										}
										
									}
									
							 }else if(porEmQualColuna.equalsIgnoreCase("preco2")) {
							    	
							    	
								 
								 Connection con=null;
								 PreparedStatement stm=null ;
								 
								 

									 String sql="update Escola_Financa set "+"precoProp"+"="+
									 preco_Doc_Mat_Func_Maatricula_E_Faltas+" where Propinas='"+
									 nome_Doc_Mat_Func_Maatricula_E_Faltas_Propina_Confir+"'";
									 
									 try {
									        
											
											
										 Conexao conexao = new Conexao();
										    conexao.setBD(BD);
											
											con = ConnectionFactory.getConnection(BD);
											stm = con.prepareStatement(sql);
											stm.executeUpdate(usarBD);
											stm.executeUpdate();
											
										
											System.out.println("Alteração Feita !!!");
											
											
										}catch (Exception e) {
											e.printStackTrace();
										}
										finally {
											try {
												con.close();
												stm.close();
												 
												System.out.println("Conexão Principal Fechada !!!");

											} catch (SQLException e) {
												// TODO Auto-generated catch block
												e.printStackTrace();
											}
											
										}
										
								 }else if(porEmQualColuna.equalsIgnoreCase("preco3")) {
								    	
								    	
									 
									 Connection con=null;
									 PreparedStatement stm=null ;
									 
									

										 String sql="update Escola_Financa set "+"precoDoc"+"="+
										 preco_Doc_Mat_Func_Maatricula_E_Faltas+" where Docs='"+
										 nome_Doc_Mat_Func_Maatricula_E_Faltas_Propina_Confir+"'";
										 
										 try {
										        
												
											 Conexao conexao = new Conexao();
											    conexao.setBD(BD);
												
												
												con = ConnectionFactory.getConnection(BD);
												stm = con.prepareStatement(sql);
												stm.executeUpdate(usarBD);
												stm.executeUpdate();
												
											
												System.out.println("Alteração Feita !!!");
												
												
											}catch (Exception e) {
												e.printStackTrace();
											}
											finally {
												try {
													con.close();
													stm.close();
													 
													System.out.println("Conexão Principal Fechada !!!");

												} catch (SQLException e) {
													// TODO Auto-generated catch block
													e.printStackTrace();
												}
												
											}
											
									 }else if(porEmQualColuna.equalsIgnoreCase("preco4")) {
									    	
									    	
										 
										 Connection con=null;
										 PreparedStatement stm=null ;
										 
										 
												 
						

											 String sql="update Escola_Financa set "+"precoMat"+"="+
											 preco_Doc_Mat_Func_Maatricula_E_Faltas+" where Mat='"+
											 nome_Doc_Mat_Func_Maatricula_E_Faltas_Propina_Confir+"'";
											 
											 try {
											        
													
												 Conexao conexao = new Conexao();
												    conexao.setBD(BD);
													
													con = ConnectionFactory.getConnection(BD);
													stm = con.prepareStatement(sql);
													stm.executeUpdate(usarBD);
													stm.executeUpdate();
													
												
													System.out.println("Alteração Feita !!!");
													
													
												}catch (Exception e) {
													e.printStackTrace();
												}
												finally {
													try {
														con.close();
														stm.close();
														 
														System.out.println("Conexão Principal Fechada !!!");

													} catch (SQLException e) {
														// TODO Auto-generated catch block
														e.printStackTrace();
													}
													
												}
												
										 }else if(porEmQualColuna.equalsIgnoreCase("preco5")) {
										    	
										    	
											 
											 Connection con=null;
											 PreparedStatement stm=null ;
											 
										

												 String sql="update Escola_Financa set "+"precoMatricula"+"="+
												 preco_Doc_Mat_Func_Maatricula_E_Faltas+" where Matricula='"+
												 nome_Doc_Mat_Func_Maatricula_E_Faltas_Propina_Confir+"'";
												 
												 try {
												        
														
													 Conexao conexao = new Conexao();
													    conexao.setBD(BD);
														
														con = ConnectionFactory.getConnection(BD);
														stm = con.prepareStatement(sql);
														stm.executeUpdate(usarBD);
														stm.executeUpdate();
														
													
														System.out.println("Alteração Feita !!!");
														
														
													}catch (Exception e) {
														e.printStackTrace();
													}
													finally {
														try {
															con.close();
															stm.close();
															 
															System.out.println("Conexão Principal Fechada !!!");

														} catch (SQLException e) {
															// TODO Auto-generated catch block
															e.printStackTrace();
														}
														
													}
													
											 }else if(porEmQualColuna.equalsIgnoreCase("preco6")) {
											    	
											    	
												 
												 Connection con=null;
												 PreparedStatement stm=null ;
												 
												 

													 String sql="update Escola_Financa set "+"precoFalta"+"="+
													 preco_Doc_Mat_Func_Maatricula_E_Faltas+" where Faltas='"+
													 nome_Doc_Mat_Func_Maatricula_E_Faltas_Propina_Confir+"'";
													 
													 try {
													        
														 Conexao conexao = new Conexao();
														    conexao.setBD(BD);
															
															
															con = ConnectionFactory.getConnection(BD);
															stm = con.prepareStatement(sql);
															stm.executeUpdate(usarBD);
															stm.executeUpdate();
															
														
															System.out.println("Alteração Feita !!!");
															
															
														}catch (Exception e) {
															e.printStackTrace();
														}
														finally {
															try {
																con.close();
																stm.close();
																 
																System.out.println("Conexão Principal Fechada !!!");

															} catch (SQLException e) {
																// TODO Auto-generated catch block
																e.printStackTrace();
															}
															
														}
														
												 }else if(porEmQualColuna.equalsIgnoreCase("preco7")) {
												    	
												    	
													 
													 Connection con=null;
													 PreparedStatement stm=null ;
													 
													 

														 String sql="update Escola_Financa set "+"precoConfir"+"="+
														 preco_Doc_Mat_Func_Maatricula_E_Faltas+" where Confirmacao='"+
														 nome_Doc_Mat_Func_Maatricula_E_Faltas_Propina_Confir+"'";
														 
														 try {
														        
															 Conexao conexao = new Conexao();
															    conexao.setBD(BD);
																
																
																con = ConnectionFactory.getConnection(BD);
																stm = con.prepareStatement(sql);
																stm.executeUpdate(usarBD);
																stm.executeUpdate();
																
															
																System.out.println("Alteração Feita !!!");
																
																
															}catch (Exception e) {
																e.printStackTrace();
															}
															finally {
																try {
																	con.close();
																	stm.close();
																	 
																	System.out.println("Conexão Principal Fechada !!!");

																} catch (SQLException e) {
																	// TODO Auto-generated catch block
																	e.printStackTrace();
																}
																
															}
															
													 }
		    	
		    
		  
					 
					 
					
			
          
	}
	
	
	public int alterarTabela_Aluno(String BD,String turma,Aluno aluno,int codigo){

		String usarBD="use "+BD;
		
		 Pesquisar_SQL p= new Pesquisar_SQL();
		 Tabela_Actualizar_SQL ta= new Tabela_Actualizar_SQL();
		 
		 
		             int nProc = 0;
					 int controle=cID.recuperarCodigo(BD, turma, "id");
					 int controle2=p.pesquisarQConteudos_Na_coluna(BD, turma, "Alunos", true);
					 
					 Connection con=null;
					 PreparedStatement stm=null ;
					 
					 
					 System.out.println("V1 codigo: "+codigo);
					 
					 ++controle2;
					 if(controle2<=controle) {
						 
						 String sql="update "+turma+ " set Alunos='"+aluno.getNome()+"',NSala="+codigo+" where id="+controle2;
						 
						 try {
						        
								
							 Conexao conexao = new Conexao();
							    conexao.setBD(BD);
								
								con = ConnectionFactory.getConnection(BD);
								stm = con.prepareStatement(sql);
								stm.executeUpdate(usarBD);
								stm.executeUpdate();
								
							
								System.out.println("Alteração Feita !!!");
								
							    nProc=p.pesquisarUmConteudo_Numa_Linha_Int(BD, "infoescola", "qalunos", "id", "", 1);
								
								
								
								++nProc;
								ta.actualizarColuna_Na_PrimeiraLinha(BD, turma, "NProc", nProc);
								ta.actualizarColuna_Na_PrimeiraLinha(BD, "infoescola", "qalunos", nProc);
								
								
							}catch (Exception e) {
								e.printStackTrace();
							}
							finally {
								try {
									con.close();
									stm.close();
									 
									System.out.println("Conexão Principal Fechada !!!");
                                   
                                    p=null;
                                    ta=null;
                                    
								} catch (SQLException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								
							}
							
					 }else {
						 
						 
						 
						 try{

							 
                         
							 
							 
                            String sql2= "insert into "+turma+" values(?,?,?,?,?,?,?,?,?,?,?);";
                            System.out.println("SQL="+sql2);
                            
                            nProc=p.pesquisarUmConteudo_Numa_Linha_Int(BD, "infoescola", "qalunos", "id", "", 1);
							
							++nProc;
							
							
							Conexao conexao = new Conexao();
						    conexao.setBD(BD);


							 con = ConnectionFactory.getConnection(BD);
							 stm = con.prepareStatement(sql2);
							 stm.executeUpdate(usarBD);

							 int v= cID.recuperarCodigo(BD, turma, "id");
			    				++v;
			    				
			    				System.out.println("V4 : "+v);
			    		     stm.setInt(1, v);
							 stm.setString(2, "");
							 stm.setString(3, aluno.getNome());
							 stm.setString(4, "");
							 stm.setString(5, "");
							 stm.setInt(6, codigo);
							 stm.setInt(7, nProc);
							 stm.setInt(8, 0);
							 stm.setString(9, "");
							 stm.setString(10, "");
							 stm.setString(11, "");
							 
							 
							 stm.executeUpdate();

							 if(codigo==1) {
								 
								 ArrayList<String> conteudo= p.pesquisarTudoEmString(BD, "pca_"+BD, "instituicao");
								 
								 String instituicao = conteudo.get(0);
								 ta.actualizarColuna_QualquerLinha_String(BD, turma, "instituicao", instituicao, 1);
							 }

							 System.out.println("Dados Inseridos!!!");
							 ta.actualizarColuna_Na_PrimeiraLinha(BD, "infoescola", "qalunos", nProc);

						 }catch (Exception e) {
							 e.printStackTrace();
						 }
						 finally {
							 try {
								 con.close();
								 stm.close();
								  

								 System.out.println("Coneccoes fechadas");

							 } catch (SQLException e) {
								 // TODO Auto-generated catch block
								 e.printStackTrace();
							 }

						 }
						 
					 }
					 
					
				 
				return nProc;
	}
	
	
	public void actualizarColuna_Na_PrimeiraLinha(String BD,String tabela,
			String coluna,int conteudo) {

		String usarBD="use "+BD;


		Connection con=null;
		PreparedStatement stm=null ;


		String sql="update "+tabela+" set "+coluna+"="+conteudo+" where id="+1;
		System.out.println(sql); 	

		try{
			
			Conexao conexao = new Conexao();
		    conexao.setBD(BD);
		    
			con = ConnectionFactory.getConnection(BD);

			stm = con.prepareStatement(sql);
			stm.executeUpdate(usarBD);
			stm.executeUpdate();

			System.out.println("Actualizado com  Sucesso !!!");


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
	
	
	public void actualizarColuna_QualquerLinha_String(String BD,String tabela,
			String coluna,String conteudo,int linha) {

		String usarBD="use "+BD;


		Connection con=null;
		PreparedStatement stm=null ;


		String sql="update "+tabela+" set "+coluna+"='"+conteudo+"' where id="+linha;
		System.out.println(sql); 	

		try{
			
			Conexao conexao = new Conexao();
		    conexao.setBD(BD);
		    
			con = ConnectionFactory.getConnection(BD);

			stm = con.prepareStatement(sql);
			stm.executeUpdate(usarBD);
			stm.executeUpdate();

			System.out.println("Actualizado com  Sucesso !!!");


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
	
	
	public void actualizarColuna_QualquerLinha_Int(String BD,String tabela,
			String coluna,int conteudo_Em_Int,String conteudo_Em_String,int linha) {

		String usarBD="use "+BD;


		Connection con=null;
		PreparedStatement stm=null ;
        
		String sql;
		if(conteudo_Em_Int==0) {
			 sql="update "+tabela+" set "+coluna+"='"+conteudo_Em_String+"'"+" where id="+linha;
		}else {
			sql="update "+tabela+" set "+coluna+"="+conteudo_Em_Int+" where id="+linha;
		}

		
		System.out.println(sql); 	

		try{
			
			Conexao conexao = new Conexao();
		    conexao.setBD(BD);
		    
			con = ConnectionFactory.getConnection(BD);

			stm = con.prepareStatement(sql);
			stm.executeUpdate(usarBD);
			stm.executeUpdate();

			System.out.println("Actualizado com  Sucesso !!!");


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
	
	
	public void actualizarColuna_QualquerLinha_Float(String BD,String tabela,
			String coluna,float conteudo_Em_Int,String conteudo_Em_String,int linha) {

		String usarBD="use "+BD;


		Connection con=null;
		PreparedStatement stm=null ;
        
		String sql;
		if(conteudo_Em_Int==0) {
			 sql="update "+tabela+" set "+coluna+"='"+conteudo_Em_String+"'"+" where id="+linha;
		}else {
			sql="update "+tabela+" set "+coluna+"="+conteudo_Em_Int+" where id="+linha;
		}

		
		System.out.println(sql); 	

		try{
			
			Conexao conexao = new Conexao();
		    conexao.setBD(BD);
		    
			con = ConnectionFactory.getConnection(BD);

			stm = con.prepareStatement(sql);
			stm.executeUpdate(usarBD);
			stm.executeUpdate();

			System.out.println("Actualizado com  Sucesso !!!");


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
	
	
	public void actualizarColuna_Qualquer_Linha(String BD,String tabela,
			String coluna,int conteudo_Em_Int,String conteudo_Em_String,
			String coluna_referencia,String conteudo_coluna_referencia) {

		String usarBD="use "+BD;


		Connection con=null;
		PreparedStatement stm=null ;
        
		String sql;
		if(conteudo_Em_Int==0) {
			 sql="update "+tabela+" set "+coluna+"='"+conteudo_Em_String
					 +"'"+" where "+coluna_referencia+"='"+
					 conteudo_coluna_referencia+"'";
		}else {
			sql="update "+tabela+" set "+coluna+"="+conteudo_Em_Int+
					" where "+coluna_referencia+"='"+
					conteudo_coluna_referencia+"'";
		}

		
		System.out.println(sql); 	

		try{
			
			Conexao conexao = new Conexao();
		    conexao.setBD(BD);
		    
		    
			con = ConnectionFactory.getConnection(BD);

			stm = con.prepareStatement(sql);
			stm.executeUpdate(usarBD);
			stm.executeUpdate();

			System.out.println("Actualizado com  Sucesso !!!");


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
	
	public String tirarCaracteres(String palavra) {
		
		String actualizar="";
		  if(palavra.contains(" ")) {
			  
			  
			  String[] espacos=palavra.split(" ");
			  for (String e : espacos) {
				  actualizar=actualizar+e;
				  
			}
			  
		  }else if(palavra.contains(",")) {
			  
			  
			  String[] espacos=palavra.split(",");
			  for (String e : espacos) {
				  actualizar=actualizar+e;
				  
			}
			  
		  }else if(palavra.contains(",,")) {
			  
			  
			  String[] espacos=palavra.split(",,");
			  for (String e : espacos) {
				  actualizar=actualizar+e;
				  
			}
			  
		  }else {
			  
			  actualizar=palavra;
		  }
		  return actualizar;
	}
public String tirarCaracteres2(String palavra) {
		
		String actualizar="";
		  if(palavra.contains("_")) {
			  
			  
			  String[] espacos=palavra.split("_");
			  for (String e : espacos) {
				  actualizar=actualizar+e;
				  
			}
			  
		  }else  if(palavra.contains(" ")) {
			  
			  
			  String[] espacos=palavra.split(" ");
			  for (String e : espacos) {
				  actualizar=actualizar+e;
				  
			}
			  
		  }else {
			  
			  actualizar=palavra;
		  }
		  return actualizar;
	}
	public ArrayList<String> tirarCaracteres_RetornandoA_A_Lista(String palavra) {

		String[] espacos;
		
		ArrayList<String> retorno= new ArrayList<>();
		 if(palavra.contains(",")) {


			 espacos=palavra.split(",");
			for (String e : espacos) {
               
				retorno.add(e);
			}

		}else if((palavra.contains("  "))&&(palavra.contains(" "))) {


			 espacos=palavra.split("  ");
			for (String e : espacos) {
             
				retorno.add(e);
			}

		}
		
		else if(palavra.contains("__")) {


			 espacos=palavra.split("__");
			for (String e : espacos) {
              
				retorno.add(e);
			}

		}else if(palavra.contains("/")) {


			 espacos=palavra.split("/");
			for (String e : espacos) {
             
				retorno.add(e);
			}

		}
		
		else {
			
			retorno.add(palavra);
		}
		return retorno;
	}
	
     

	
}
