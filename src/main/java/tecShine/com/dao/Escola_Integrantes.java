package tecShine.com.dao;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.GetMapping;

import tecShine.com.repository.IntegrantesDaEscola;

public class Escola_Integrantes implements IntegrantesDaEscola{

	private static int podeContinuar;
	private FuncLogin func= new FuncLogin();
	
	
	
	/*
	 *  @GetMapping({"/Cadastrar-Sistema-1","/Cadastrar-Sistema-2","/Cadastrar-Sistema-3",
		   "/Cadastrar-Sistema-4","/Cadastrar-Sistema-5a","/Cadastrar-Sistema-5b"
		   ,"/Cadastrar-Sistema-6","/Cadastrar-Sistema-7","/Cadastrar-Sistema-8"
		   ,"/Cadastrar-Sistema-9","/Cadastrar-Sistema-9a","/Cadastrar-Sistema-9b"
		   ,"/Cadastrar-Sistema-9c","/Cadastrar-Sistema-9d","/Cadastrar-Sistema-9e"
		   ,"/Cadastrar-Sistema-9f","/Cadastrar-Sistema-10","/WgContacts","/Admin-Minha-Seguranca",
		   "/Admin-Alterar-Salario","/Admin-Alterar-Propina","Admin-Alterar-MC",
		   "/Admin-Alterar-Doc","/Admin-Alterar-Vendas","/Admin-Alterar-FE","/Admin-Ver-PM",
		   "/Admin-Ver-MC","/Admin-Ver-Salario","/Admin-Ver-Doc","/Admin-Ver-Materias","/Admin-Ver-FE",
		   "/Admin-Cadastro"})
	 * 
	 * 
	 * 
	 * 
	 */
	
	
	
	public boolean bloqueandoRequisicoes(String request1,HttpServletRequest request2){
		
	
		/*
		 * boolean retorno=false;
		if((request1.startsWith("admin"))&&
				(
				  (request2.getRequestURI().equalsIgnoreCase("admin"))||
				  (request2.getRequestURI().equalsIgnoreCase("Cadastrar-Sistema-1"))
				  
						)) {
			
			retorno= true;
		}else {
			retorno= false;
		}
		return retorno;
		 * 
		 * 
		 */
		
		return false;
	}
	
	
	@Override
	
	public void cadastrarIntegrantes(String referencia, String tipoDeMembro) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String existeNaEscola(String id_De_Login, String escola) {
		 /*
		
		String[] acessando= {"Admin","ALuno","FuncCoord","FuncSecret","FuncTesou","FuncProf"};
        String acessando2;
		
        String referencia=null;
        
       
          sair:
         for(String cadaC: acessando) {
        	
        	acessando2=escola+""+cadaC+""+id_De_Login;
        	System.out.println("Dentro de Escola Integrante acessando2: "+acessando2);
        	if(acessando2.equalsIgnoreCase("vassovavaAdmin1234")) {
            	podeContinuar=2;
            	referencia=acessando2;
            	System.out.println("Entrou No vassovavaAdmin1234");
            	break sair;
            	
            	
            }else if(acessando2.equalsIgnoreCase("vassovavaALuno12345")) {
            	
            	podeContinuar=2;
            	System.out.println("Entrou No vassovavaALuno1234");
            	referencia=acessando2;
            	break sair;
            	
            	
            }else if(acessando2.equalsIgnoreCase("VassovavaFuncCoord123456")) {
            	
            	
            	podeContinuar=2;
            	System.out.println("Entrou No vassovavaFunc1234");
            	referencia=acessando2;
            	System.out.println("Não Entrou Em nengum Lugar");
            	break sair;
            	
            	
            }else if(acessando2.equalsIgnoreCase("VassovavaFuncSecret1234567")) {
            	
            	podeContinuar=2;
            	System.out.println("Entrou No vassovavaFunc1234");
            	referencia=acessando2;
            	System.out.println("Não Entrou Em nengum Lugar");
            	break sair;
            	
            }
            else if(acessando2.equalsIgnoreCase("VassovavaFuncTesou12345678")) {
            	
            	
            	podeContinuar=2;
            	System.out.println("Entrou Na Tesouraria");
            	referencia=acessando2;
            	System.out.println("Não Entrou Em nengum Lugar");
            	break sair;
            	
            }
            else if(acessando2.equalsIgnoreCase("VassovavaFuncProf123456789")) {
            	
            	
            	podeContinuar=2;
            	System.out.println("Entrou No Sitio Certo");
            	referencia=acessando2;
            	System.out.println("Não Entrou Em nengum Lugar");
            	break sair;
            	
            	   
            }
            else  {
            	podeContinuar=1;
            	referencia=acessando2;
            }
       // }
        
        */
        
		return "";
		
	}
   
	public int getPodeContinuar() {
		return podeContinuar;
	}

	public void setPodeContinuar(int podeContinuar) {
		Escola_Integrantes.podeContinuar = podeContinuar;
	}

	
	

	

}
