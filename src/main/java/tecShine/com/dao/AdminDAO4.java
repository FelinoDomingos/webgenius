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
public class AdminDAO4 implements AcessoAdmin{

	/*==============================
	 * INICIO
	 * LOGICA DO ADMIN
	 * 
	 * 
	 * MEdoto Abaixo é do Admin
	 
	 * 4
	 * Admin DAO Fase 4
	 * 
	 * O Metodo Abaixo O Admin vai Optar Em dar 
	 * Permissão Ou Não A Seus Funcionarios Em
	 * Prosseguir no Cadastro Do Sistema, Caso 
	 * Dê vai ir directamente para o Seu Inicio 
	 * Se não der Vai Continuar A Cadastrar O 
	 * Sistema
	 * 
	 */
	
	 private static int controle=0;
	 
	public boolean dar_Permissao(int permissao1, int permissao2) {

		/*
		 *  Vou Colocar a Logica para Saber
		 *  Se Vai Dar Permissão A(Aos) 
		 *  Funcionário(os) Para Terminar 
		 *  O Processo De Cadastramento Do 
		 *  Sistema Aqui
		 */
		   Admin_DarPermissao permissao= new Admin_DarPermissao();
		   boolean retorno=false;
		   
		   
		   
		   if((permissao1>0)||(permissao2>0)) {
			   
			   
			   
			   if(permissao1>0) {
				   System.out.println("Tem Permissao? SIM");
				   System.out.println("Funcionário Permitido");
				   
				   permissao.setCadsQuantasVezes(permissao1+1);
				   retorno=true;
			   }else {
				   System.out.println("Tem Permissao? NAO");
				   System.out.println("Funcionário Não Permido Permitido");
				   
				   permissao.setCadsQuantCursos(permissao2+1);
				   retorno=false;
			   }
			   
		   }
		   
		   
		return retorno; 
	}

	@Override
	public boolean cadasNaWG_Pamento_Em_Dia() {
		// TODO Auto-generated method stub
		return false;
	}

	

	@Override
	public int ondeParouOCadastro() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int quantFuncCadastrado() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean dar_Permissao() {
		// TODO Auto-generated method stub
		return false;
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
