package tecShine.com.dao;

import tecShine.com.JDBC.Salvar_SQL;
import tecShine.com.model.Admin_DarPermissao;
import tecShine.com.repository.AcessoAdmin;

public class AdminLogin {        
   
	private static int controle=0;
	
	// Essesncial Para Fazer Logout
	private static int vezesARepetir=0;
	private String pagina = null;
	private AcessoAdmin acesso1= new AdminDAO1();
	private AdminDAO2 acesso2= new AdminDAO2();
	private AdminDAO3 acesso3= new AdminDAO3();
	private AdminDAO4 acesso4= new AdminDAO4();
	private AdminDAO5 acesso5= new AdminDAO5();
	private boolean darPermissao;
	static boolean  primeiraVez=false;
	
	
	
	
	/*
	 * 
	 * Este Metodo Abaixo é o Responsável
	 * Por Efectuar O Login
	 */
	public String adminLogin(int parouEm,String bi) {
		
		Salvar_SQL s = new Salvar_SQL();
		  
		
		
		//++controle;
		//darPermissao=acesso4.dar_Permissao(this.permissao2.getCadsQuantasVezes()+1,this.permissao2.getCadsQuantCursos()+1);
		
		
		if(acesso1.cadasNaWG_Pamento_Em_Dia()) {
		
			
			if(acesso2.terminouO_Cadas_Do_Sistema(bi)) {
				
				
					pagina="TechShine/Admin/inicio";
				
				System.out.println("Cadastrado Terminado");
				  // Precisa Saber Quem Está a Logar
				
			}else { 
				 
				  switch (acesso3.ondeParouOCadastro(parouEm)) {
				case 1:
					
					s.sistema_Actualizar_OndeParou_O_Cadastro(bi, parouEm);
					pagina="WebGnius/cadastrar_Empresa/sistema_fase1";
					break; 
				case 2:
					
					s.sistema_Actualizar_OndeParou_O_Cadastro(bi, parouEm);
					pagina="WebGnius/cadastrar_Empresa/sistema_fase2";
					break;	
				case 3:
					
					s.sistema_Actualizar_OndeParou_O_Cadastro(bi, parouEm);
					pagina="WebGnius/cadastrar_Empresa/sistema_fase3";
					break;
				case 4:
					
					s.sistema_Actualizar_OndeParou_O_Cadastro(bi, parouEm);
					pagina="redirect:Cadastrar-Sistema-4";
					break;
				case 5:
					
					s.sistema_Actualizar_OndeParou_O_Cadastro(bi, parouEm);
					pagina="TechShine/Admin/inicio";
					s.sistema_Cadastrado_Com_Sucesso(bi);
					
					break;
				default:
					break;
				}
				//Onde Parou O Cadastro
			}
			
		}else {
			
			
			// Deve Pagar O Sistema
			pagina="redirect:login";
		}
		
		return pagina;
	}
}
