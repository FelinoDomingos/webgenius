package tecShine.com.repository;

import java.util.ArrayList;

import tecShine.com.model.Funcionario;

/**
 * 
 * Interface De Acesso Aos Metodos Do Admin
 * 
 * 
 * @author Jose Euclides Pedro Pereira Dos Santos
 * Email: 
 * euclidessdossantoss@gmail.com
 * Ou
 * euclidesssdossantoss@gmail.com
 * Meu Site https://joseeuclidesss.herokuapp.com/index
 *
 */
public interface AcessoFuncionario {

	
	
	/*==============================
	 * INICIO
	 * LOGICA DO FUNCIONARIO
	 * 
	 * 
	 * MEdoto Abaixo é do Funcionario
	
	
	 * 1
	 * Funcionario DAO Fase 1
	 * 
	 * 
	 * Saber Se A Escola está Cadastrada
	//Cadastrada na WG E Tem Pagamento Em
	 * Dia
	 */
 
public boolean cadasNaWG_Pamento_Em_Dia();
	
	/*
	 * 2
	 * Funvcionario DAO Fase 2
	 * 
	 * O Metodo Abaixo vai verificar É Um Funcionario
	 * Da Escola Ou Não
	 * 
	 */
	
	public boolean e_Um_Funcionario();
	
	

	/*
	 * 3
	 * Funcionario DAO Fase 3
	 * 
	 * O Metodo Abaixo vai verificar O Cargo
	 * Do Funcionario
	 * 
	 */
	
	public Funcionario quem_E_O_Funcionario(String id_Funcionario);
	
	
	/*
	 * 4
	 * Funcionario DAO Fase 4
	 * 
	 * O Metodo Abaixo vai verificar Se Ja 
	 * Terminou O Cadastro Do Sistema
	 * 
	 */
	
	public boolean terminou_O_Cadastro_DoSistema();
	
	
	
	
	/*
	 * 5
	 * Funcionario DAO Fase 5a
	 * 
	 * O Metodo Abaixo vai verificar Se Tem 
	 * Permissão Do Admin Para Fazer Outros 
	 * Trabalhos No Caso que O Sistema Ja Esteja 
	 * Cadastrado
	 * 
	 */
	
	
	
	
	public boolean permissaoDoAdmin1();
	/*
	 * 6a
	 * Funcionario DAO Fase 6a
	 * 
	 * O Metodo Abaixo O Admin vai Optar Em dar 
	 * Permissão Ou Não A Seus Funcionarios Em
	 * Prosseguir no Cadastro Do Sistema, Caso 
	 * Dê vai ir directamente para o Seu Inicio 
	 * Se não der Vai Continuar A Cadastrar O 
	 * Sistema
	 * 
	 */
	
	public boolean permissaoDoAdmin2();
	/*
	 * 6b
	 * Funcionario DAO Fase 6b
	 * 
	 * O Metodo Abaixo O Admin vai Optar Em dar 
	 * Permissão Ou Não A Seus Funcionarios Em
	 * Prosseguir no Cadastro Do Sistema, Caso 
	 * Dê vai ir directamente para o Seu Inicio 
	 * Se não der Vai Continuar A Cadastrar O 
	 * Sistema
	 * 
	 */
	public String cadastrarOPermitido();
	
	
	/*
	 * 7
	 * Funcionario DAO Fase 7
	 * 
	 * O Metodo Abaixo Vai Verificar Se Por 
	 * Ventura O Admin Deu Permissão A(aos) 
	 * Funcionario(os) De Cadastrar O Sistema, 
	 * Quantos Funcionários Ele Definiu Que daria 
	 * Permissão, Fazendo com que ele coloque simplesme
	 * NEsse Intervalo Permitido
	 * 
	 */
	public int quantFuncCadastrado();
	
	/*
	 * 8
	 * Admin DAO Fase 8
	 * 
	 *Lista Dos Cargos de Todos Os Funcionarios Da Escola
	 * 
	 */
	
	public ArrayList<Funcionario> FuncionáriosDaEscola();
	
	/*
	 * 
	 * Metodo Para Saber Se O Funcionario recebeu Permissão
	 * Do Admin
	 */
	public boolean esseFuncionarioTemPermissao(String id_Funcionario);	
		
}
