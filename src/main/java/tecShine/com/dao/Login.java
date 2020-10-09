package tecShine.com.dao;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import tecShine.com.JDBC.Salvar_SQL;
import tecShine.com.model.Admin_DarPermissao;
import tecShine.com.model.WG.Escola;
import tecShine.com.repository.IntegrantesDaEscola;

public class Login {
  
	private String pagina=null;
	private String nomeDaEscola;
	private  int parouEm=0;
	private int controleParouEm=1;
	private FuncLogin login = new FuncLogin();
	public String bi;
	 
	
	
	
	/*
	 * 
	 * Esse Metodo Serve Para Saber Se A Devida 
	 * Pessoa A Fazer Login Faz Parte Da Escola
	 * ou Não
	 */
	
	 
	
	public boolean podeAbrirAPagina(String senha){
		
		
		
		
		
		boolean retorno;
		
       
	     
	     if(senha.equalsIgnoreCase(" ")) {
	    	 
	    	 
	    	 retorno=false;
	    	 
	     }else {
	    	 retorno=true;
	     }
      
		return retorno;
	}
	
	
	
	public String quemEstaAFazerLogin(String senha,String usuario){
		
		Salvar_SQL s= new Salvar_SQL();
		
		
		System.out.println("Parou Em: "+parouEm);
		bi= senha;
		
		parouEm = s.sistema_OndeParou_O_Cadastro(bi);
		if(parouEm<=5) {
			
		}else {
			parouEm=5;
		}
		
	        
		//if(controle==2) {
			
			
			
		    System.out.println("senha"+senha);
			if(usuario.equalsIgnoreCase("PCA")) {
				
				
				
				// Login Do Admin
				System.out.println("entrou no Admin");
				System.out.println("PArouEm: "+parouEm);
				System.out.println("Controle PArouEm: "+controleParouEm);
				++parouEm;
				if((parouEm==4)&&(controleParouEm==4)) {
					++parouEm;
				}
				
				AdminLogin login = new AdminLogin();
				
				pagina= login.adminLogin(parouEm,bi);
				System.out.println("A Pagina: "+pagina);
				
				if((parouEm==4)&&(controleParouEm<=3)) {

					--parouEm;
					++controleParouEm;
				}
				
			}else if((usuario.equalsIgnoreCase("Professor"))||
					(usuario.equalsIgnoreCase("Coordenador"))||
					(usuario.equalsIgnoreCase("Secretaria"))||
					(usuario.equalsIgnoreCase("Tesouraria"))||
					(usuario.equalsIgnoreCase("DG"))||
					(usuario.equalsIgnoreCase("DA"))||
					(usuario.equalsIgnoreCase("DP")))
			        {
				
				
				//Login Do Funcionário
				
				System.out.println("Entrou no Vassovava Func");
				pagina=login.funcLogin(senha,this.nomeDaEscola);
				
				
				
			}else if(senha.contains("Turma")) {
				
				pagina="TechShine/Aluno/inicio";
			}
			
		//}
		
			System.out.println("Minha Pagina2: "+pagina);
		return pagina;
			
	}
	
}
