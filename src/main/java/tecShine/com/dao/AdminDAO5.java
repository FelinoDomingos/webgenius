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
public class AdminDAO5 implements AcessoAdmin{

	/*==============================
	 * INICIO
	 * LOGICA DO ADMIN
	 * 
	 * 
	 * MEdotos Abaixo são do Admin
     *
	 * 5
	 * Admin DAO Fase 5
	 * 
	 * 	
	 * O Metodo Abaixo Vai Verificar Se Por 
	 * Ventura O Admin Deu Permissão A(aos) 
	 * Funcionario(os) De Cadastrar O Sistema, 
	 * Quantos Funcionários Ele Definiu Que daria 
	 * Permissão, Fazendo com que ele coloque simplesme
	 * NEsse Intervalo Permitido
	 * 
	 */
	
	
    public int vezesPorRepetir(Admin_DarPermissao permissao) {
		
		
		/*
		 *  Vou Colocar a Logica para Saber
		 *  Quantos Funcionários O Admin
		 *  Disse que vão lhe Ajudar No Cadastro 
		 *  Do Sistema Aqui Aqui
		 */
    	System.out.println("Estrou no AdminDAO05");
    	
    	
		int quant=permissao.getCadsQuantasVezes();
		if(quant==0) {
			quant=permissao.getCadsQuantCursos();
			System.out.println("Quantidade De Cursos: "+quant);
		}else {
			System.out.println("Quantidade De Funcionarios: "+quant);
		}
		
		 
		return quant;
	}
	public int quantFuncCadastrado() {
		
		
		/*
		 *  Vou Colocar a Logica para Saber
		 *  Quantos Funcionários O Admin
		 *  Disse que vão lhe Ajudar No Cadastro 
		 *  Do Sistema Aqui Aqui
		 */
		 
		 
		return 0;
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
