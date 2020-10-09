package tecShine.com.repository;

   
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
public interface AcessoAdmin {

	
	
	/*==============================
	 * INICIO
	 * LOGICA DO ADMIN
	 * 
	 * 
	 * MEdoto Abaixo é do Admin
	
	
	 * 1
	 * Admin DAO Fase 1
	 * 
	 * 
	 * Saber Se A Escola está Cadastrada
	//Cadastrada na WG E Tem Pagamento Em
	 * Dia
	 */
 
public boolean cadasNaWG_Pamento_Em_Dia();
	
	/*
	 * 2
	 * Admin DAO Fase 2
	 * 
	 * O Metodo Abaixo vai verificar Se Ja Terminou 
	 * De Cadastrar a Escola ou Não
	 * 
	 */
	
	public boolean terminouO_Cadas_Do_Sistema(String bi);
	
	public int ondeParouOCadastro();
	/*
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
	public boolean dar_Permissao();
	/*
	 * 5
	 * Admin DAO Fase 5
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
		
		
}
