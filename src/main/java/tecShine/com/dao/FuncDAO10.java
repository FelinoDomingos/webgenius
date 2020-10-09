package tecShine.com.dao;

import java.util.ArrayList;

import tecShine.com.model.Funcionario;
import tecShine.com.repository.AcessoFuncionario;

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
public class FuncDAO10 implements AcessoFuncionario{

	@Override
	public boolean cadasNaWG_Pamento_Em_Dia() {

        
		return false;
	}

	@Override
	public boolean e_Um_Funcionario() {

        
		return true;
	}

	
	@Override
	public boolean terminou_O_Cadastro_DoSistema() {
		
		return true;
	}

	@Override
	public boolean permissaoDoAdmin1() {

		
		return false;
	}

	@Override
	public boolean permissaoDoAdmin2() {
		
		
		return false;
	}

	@Override
	public String cadastrarOPermitido() {
		
		/*
		 * 
		 * Metodo Para dizer Ao Funcionario
		 * Para Cadastrar So o que 
		 * Lhe Foi Permitido
		 */
		return null;
	}

	@Override
	public int quantFuncCadastrado() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ArrayList<Funcionario> FuncionáriosDaEscola() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Funcionario quem_E_O_Funcionario(String id_Funcionario) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean esseFuncionarioTemPermissao(String id_Funcionario) {
		// TODO Auto-generated method stub
		return false;
	}

	
	
	
	
}
