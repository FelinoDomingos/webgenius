package tecShine.com.dao;




public class FuncLogin {
   
	private String pagina = null;
	private FuncDAO1 acesso1= new FuncDAO1();
	private FuncDAO4 acesso4= new FuncDAO4();
	private FuncDAO5 acesso5= new FuncDAO5();
	private FuncDAO6 acesso6= new FuncDAO6();
	public static int permissaoAdmin1=0;
	
	
	
	
	/*
	 * 
	 * Este Metodo Abaixo é o Responsável
	 * Por Efectuar O Login
	 */
	public String funcLogin(String usuario,String escola) {
		
		//Permissao_SistemaCadastrado permitido= (Permissao_SistemaCadastrado)funcPermitido1.
				//recuperarFuncionarioPermitido(escola, usuario);
		
		if(acesso1.cadasNaWG_Pamento_Em_Dia()) {
			
			
			
			
				
				//cargoDoFuncionario= (Funcionario)acesso3.quem_E_O_Funcionario(usuario);
				
				if(acesso4.terminou_O_Cadastro_DoSistema()) {
					
					if(acesso5.permissaoDoAdmin1()) {
						
						if(acesso6.esseFuncionarioTemPermissao(usuario)) {
							
							// Aqui está a dizer que o Admin Deu permissão 
							//Para fazer alguns trabalhos tendo ja o Sistema Cadastrado
							
							
							/*
							 * 
							 * 
							 * permissaoAdmin1=1;
							if((cargoDoFuncionario.getCargo()).equalsIgnoreCase("professor")) {
								
								
								model.addAttribute("permissaoSistJaCadastrado", permitido);
								pagina="TechShine/Professor/inicio";
							}else if((cargoDoFuncionario.getCargo()).equalsIgnoreCase("coordenador")) {
								
								model.addAttribute("permissaoSistJaCadastrado", permitido);
								pagina="TechShine/Coordenador/inicio";
							}else if((cargoDoFuncionario.getCargo()).equalsIgnoreCase("tesouraria")) {
								
								model.addAttribute("permissaoSistJaCadastrado", permitido);
								pagina="WebGnius/tesouraria/inicio";
							}else if((cargoDoFuncionario.getCargo()).equalsIgnoreCase("secretaria")) {
								
								model.addAttribute("permissaoSistJaCadastrado", permitido);
								pagina="WebGnius/secretaria/inicio";
							}
							 * 
							 */
							
						}
						
					}else {
						
						
						/*
						 * 
						 *
      
       				 */
						System.out.println("Entrou Na Apresentacao da Pagina");
						System.out.println("ID Funcionário: "+usuario);
						
						// Aqui está a dizer que o Admin Deu permissão 
						//Para fazer alguns trabalhos tendo ja o Sistema Cadastrado
						
						
					
						
						
						                                
						if(usuario.equalsIgnoreCase("Coordenador")) {
							
							pagina="TechShine/Coordenador/inicio";
						}else  if(usuario.equalsIgnoreCase("Secretaria")) {
							
							pagina="WebGnius/secretaria/inicio";
						}else  if(usuario.equalsIgnoreCase("Tesouraria")) {
							
							pagina="WebGnius/tesouraria/inicio";
						}else if(usuario.equalsIgnoreCase("Professor")){
							pagina= "TechShine/Professor/inicio";
						}else if(usuario.equalsIgnoreCase("DG")){
							pagina= "TechShine/DG/inicio";
						}else if(usuario.equalsIgnoreCase("DP")){
							pagina= "TechShine/DP/inicio";
						}else if(usuario.equalsIgnoreCase("DA")){
							pagina= "TechShine/DA/inicio";
						}
					
					}
				}else {
					
					 
					/*
					 * 
					 * 
					 * Aqui Vai A Logica A Dizer Que ainda não terminou o cadasro
					 * Do Sistema E que O Funcionário deve continuar a Cadastrar O 
					   Sistema Simplesmente O que que lhe permitiram
					 */
					
					  
					
					
				}
				
			
		}else {
			
			pagina="redirect:login";
		}
		
		return pagina;
	}
}
