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
public class FuncDAO4 implements AcessoFuncionario{

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
		/*
		 * 
		 * Metodo Para Saber Se OI Sistema
		 * Ja Está Cadastrado
		 */
		return true;
	}

	@Override
	public boolean permissaoDoAdmin1() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean permissaoDoAdmin2() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String cadastrarOPermitido() {
		// TODO Auto-generated method stub
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
