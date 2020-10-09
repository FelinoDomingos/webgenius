package tecShine.com.dao;

import tecShine.com.JDBC.Salvar_SQL;
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
public class AdminDAO2 implements AcessoAdmin{

	/*==============================
	 * INICIO
	 * LOGICA DO ADMIN
	 * 
	 * 
	 * MEdoto Abaixo é do Admin
	 
	 *
	 * 2
	 * Admin DAO Fase 2
	 * 
	 * O Metodo Abaixo vai verificar Se Ja Terminou 
	 * De Cadastrar a Escola ou Não
	 * 
	 */
	
	private int terminouCadastro=1;
	private int controle=0;
	
	public int getTerminouCadastro_BD() {
		return terminouCadastro;
	}

	public void setTerminouCadastro_BD(int terminouCadastro) {
		this.terminouCadastro = terminouCadastro;
	}

	public boolean terminouO_Cadas_Do_Sistema(String bi){
		
		
		/*
		 *  Vou Colocar a Logica para Saber
		 *  Se O Admin JA Terminou Ou Não O
		 *   Processo De Cadastro Do Sistema Aqui
		 *   
		 *   
		 */
		
		 Salvar_SQL s = new Salvar_SQL();
		 
		 
		 
		return s.sistema_Esta_Cadastro(bi);
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
	public boolean dar_Permissao() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int quantFuncCadastrado() {
		// TODO Auto-generated method stub
		return 0;
	}

	
	
	
	/*
	 * 
	 * FIM LOGICA DO ADMIN
	 * ============================
	 * 
	 */
	
	
	
}
