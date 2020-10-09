package tecShine.com.dao;

import tecShine.com.model.Admin_DarPermissao;
import tecShine.com.repository.AcessoAdmin;

/**
 * 
 * @author Jose Euclides Pedro Pereira Dos Santos
 * Email: 
 * euclidessdossantoss@gmail.com
 * Ou
 * euclidesssdossantoss@gmail.com
 * Meu Site https://joseeuclidesss.herokuapp.com/index
 *
 */
   public  class AdminDAO3 implements AcessoAdmin{

	/*==============================
	 * INICIO
	 * LOGICA DO ADMIN
	 * 
	 * 
	 * MEdoto Abaixo é do Admin
	
	 * 3
	 * Admin DAO Fase 3
	 * 
	 * O Metodo Abaixo Vai reconhecer Onde O Admin 
	 * Parou O Cadastro do Sistema E Continuar
	 * Onde Havia Parado
	 */
	   
	   int retorno=0;   
	   
	   
	   private boolean terminouCadastro=false;
	
	   public boolean isTerminouCadastro() {
		   return terminouCadastro;
	   }
	   public void setTerminouCadastro(boolean terminouCadastro) {
		   this.terminouCadastro = terminouCadastro;
	   }
	   
	   
	public int ondeParouOCadastro(int parouEm) {
		
		
		/*
		 *  Vou Colocar a Logica para Saber
		 *  Se A O Admin Parou Aonde O Cadastro 
		 *  Do Sistema Aqui
		 */
		
		System.out.println("Entrou no metodo ondeparouOCadastro");
		
		System.out.println("Estado Actual: "+parouEm);
		
		
		
		
		
		
		return parouEm;
	}
  

	@Override
	public boolean cadasNaWG_Pamento_Em_Dia() {
		// TODO Auto-generated method stub
		return false;
	}



	@Override
	public boolean dar_Permissao() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int quantFuncCadastrado() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public int ondeParouOCadastro() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public boolean terminouO_Cadas_Do_Sistema(String bi) {
		// TODO Auto-generated method stub
		return false;
	}
	
	
	/*
	 * 
	 * FIM LOGICA DO ADMIN
	 * ============================
	 * 
	 */
	
	
	
}
