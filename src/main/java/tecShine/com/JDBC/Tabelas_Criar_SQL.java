package tecShine.com.JDBC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import tecShine.com.model.Turma;


/**
 * Essa Classe Vai criar Tabelas de Diversas BDs
 * 
 * 
 * @author Jose Euclides Pedro Pereira dos Santos
 * @version 1.0
 * @see ConnectionFactory
 * 
 *
 */
public class Tabelas_Criar_SQL {

	private ConnectionFactory cf= new ConnectionFactory();
	
	/**
	 * Esse Metodo Vai criar tabela de nome estagio, 
	 * nas diversas BDs
	 * @param BD
	 */
    

/**
 * Esse Metodo Vai criar tabela de nome escolas, 
 * simplesmente na WG
 * @param BD
 */
public  void criarTabelaEscolas_WG (){

    String escolas="escolas";
	String sql="create table "+escolas+"(id int NOT NULL AUTO_INCREMENT PRIMARY KEY,"
			+ "contrato varchar(20) NOT NULL, alunos int ,valor int,"
			+ "bi varchar(40) NOT NULL, telefone varchar(20) NOT NULL,data varchar(20),"
			+ "escola varchar(40),sisCadatrado varchar(40),Onde_Parou int);"
			;
	String usarBD="use wg";
	
	
	 Connection con=null;
	 PreparedStatement stm=null ;
	
	try{
		con = ConnectionFactory.getConnection();
		stm = con.prepareStatement(sql);
		stm.executeUpdate(usarBD);
		stm.executeUpdate();
		
	
		System.out.println("Tabela  Criada !!!");
		
		
	}catch (Exception e) {
		e.printStackTrace();
	}
	finally {
		try {
			con.close();
			stm.close();
			 
			
			System.out.println("Conexões Fechadas !!!");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}

/**
 * Esse Metodo Vai criar tabela de nome Admin, 
 * nas diversas BDs
 * @param BD
 */
public  void criarTabelaAdmin (String BD,String nomeDaTabela){
		

	
		String sql="create table "+nomeDaTabela+"("
				+ "nome varchar(20),sigla varchar(20),"
				+ "bi varchar(30),contrato varchar(20),"
				+ "valor int,usuario varchar(30),acesso1 varchar(30),"
				+ "acesso2 varchar(30),instituicao varchar(255),"
				+ "logotipo blob);"
				;
		String usarBD="use "+BD;
		
		
		 Connection con=null;
		 PreparedStatement stm=null ;
		
		try{
			con = ConnectionFactory.getConnection();
			stm = con.prepareStatement(sql);
			stm.executeUpdate(usarBD);
			stm.executeUpdate();
			
		
			System.out.println("Tabela  Criada !!!");
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				con.close();
				stm.close();
				 

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}



/**
 * Esse Metodo Vai criar tabela de nome pca, 
 * simplesmente na WG
 * @param BD
 */

public  void criarTabelaPCA_WG (String BD,String nomeDaTabela){
	
	String sql="create table "+nomeDaTabela+"("
			+ "nome varchar(65),"
			+ "bi varchar(40),"
			+ "valor int,usuario varchar(30),"
			+ "acesso1 varchar(30),acesso2 varchar(30));"
			;
	String usarBD="use "+BD;
	
	
	 Connection con=null;
	 PreparedStatement stm=null ;
	
	try{
		con = ConnectionFactory.getConnection();
		stm = con.prepareStatement(sql);
		stm.executeUpdate(usarBD);
		stm.executeUpdate();
		
	
		System.out.println("Tabela  Criada !!!");
		
		
	}catch (Exception e) {
		e.printStackTrace();
	}
	finally {
		try {
			con.close();
			stm.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}


public  void criarTabelaAdminFinanca (String BD){
	
	String sql="create table adminfinanca("
			+ "bi_admin varchar(35),"
			+ "renda int,"
			+ "despeza int, wg int,"
			+ "finCadastrada int,multa int);";
	
	
	
	String usarBD="use "+BD;
	
	
	 Connection con=null;
	 PreparedStatement stm=null ;
	
	try{
		con = ConnectionFactory.getConnection();
		stm = con.prepareStatement(sql);
		stm.executeUpdate(usarBD);
		stm.executeUpdate();
		
	
		System.out.println("Tabela  Criada !!!");
		
		
	}catch (Exception e) {
		e.printStackTrace();
	}
	finally {
		try {
			con.close();
			stm.close();
			 

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}

public void criarTabelaFuncFinanca (String BD,String cargo){
	
	
	
		 
		String usarBD="use "+BD;
		
		
		 Connection con=null;
		 PreparedStatement stm=null ;
		
		try {
		        
			String sql="create table"+" "+cargo+"_Financa"+"("
					 
			+ "id int NOT NULL PRIMARY KEY,"
			+ "Janeiro int,Fevereiro int,Marco int,"
			+ "Abril int,Maio int,Junho int,Julho int,"
			+ "Agosto int, Setembro int,Outubro int,Novembro int,"
			+ "Dezembro int,"
			+ "Falta int,DataPagamento varchar(20))";

		
			  
			
			
			
			con = ConnectionFactory.getConnection();
			stm = con.prepareStatement(sql);
			stm.executeUpdate(usarBD);
			stm.executeUpdate();
			
		
			System.out.println("Tabela  Criada !!!");
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				con.close();
				stm.close();
				 

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	
	
}



public void criarTabela_Cursos(String BD){
	

		 
		String usarBD="use "+BD;
		
		String sql="create table cursos("
				 
				+ "id int NOT NULL PRIMARY KEY,"
				+ "nome varchar(100),"
				+ "cargaHoraria int,"
				+ "preco int,coord varchar(80),"
				+ "trancar varchar(20),"
				+ "bi varchar(60),"
				+ "Situacao varchar(60));";
		
		
		 Connection con=null;
		 PreparedStatement stm=null ;
		
		try {
		        
			
			
			
			con = ConnectionFactory.getConnection();
			stm = con.prepareStatement(sql);
			stm.executeUpdate(usarBD);
			stm.executeUpdate();
			
		
			System.out.println("Tabela  Criada !!!");
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				con.close();
				stm.close();
				 

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
	
	
}




public void criarTabelaDinamica(String BD,String tabela, ArrayList<String>cursos, int quantCursos){
	

	String colunas="id int,";
	int controle=0;
	
	for (String curso : cursos) {
		++controle;
		
		if(controle<quantCursos) {
			
			colunas=colunas+curso+" varchar(150),";
		}else {
			colunas=colunas+curso+" varchar(150)";
		}
	}
	
	
	 
	String usarBD="use "+BD;
	
	String sql="create table "+tabela+"("+
			colunas+");";
	
	System.out.println(sql);
	
	
	 Connection con=null;
	 PreparedStatement stm=null ;
	
	try {
	        
		
		
		
		con = ConnectionFactory.getConnection();
		stm = con.prepareStatement(sql);
		stm.executeUpdate(usarBD);
		stm.executeUpdate();
		
	
		System.out.println("Tabela  Criada !!!");
		
		
	}catch (Exception e) {
		e.printStackTrace();
	}
	finally {
		try {
			con.close();
			stm.close();
			 

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	


}


public void criarTabela_CursosPorNiveis(String BD){
	

	String usarBD="use "+BD;
	
	String sql="create table cursos_Niveis("
			+ "id int,cursos varchar(255),"
			+ "Decima  int, DecimaPrimeira int,"
			+ "DecimaSegunda int,DecimaTerceira int);";
	
	System.out.println(sql);
	
	
	 Connection con=null;
	 PreparedStatement stm=null ;
	
	try {
	        
		
		
		
		con = ConnectionFactory.getConnection();
		stm = con.prepareStatement(sql);
		stm.executeUpdate(usarBD);
		stm.executeUpdate();
		
	
		System.out.println("Tabela  Criada !!!");
		
		
	}catch (Exception e) {
		e.printStackTrace();
	}
	finally {
		try {
			con.close();
			stm.close();
			 

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	


}


public void criarTabelaDinamica_Avaliiacao_EProva(String BD,String tabela, ArrayList<String> desciplinas, int quantCursos){
	

	String colunas="id int NOT NULL PRIMARY KEY,Trimestre varchar(6),Alunos varchar(60),bi varchar(45),";
	int controle=0;
	
	for (String desciplina : desciplinas) {
		++controle;
		
		
		
		if(controle<quantCursos) {
			
			colunas=colunas+desciplina+" int,";
		}else {
			colunas=colunas+desciplina+" int";
		}
	}
	
	
	 
	String usarBD="use "+BD;
	
	String sql="create table "+tabela+"("+
			colunas+");";
	
	System.out.println("Ver SQL: "+sql);
	
	
	 Connection con=null;
	 PreparedStatement stm=null ;
	
	try {
	        
		
		
		
		con = ConnectionFactory.getConnection();
		stm = con.prepareStatement(sql);
		stm.executeUpdate(usarBD);
		stm.executeUpdate();
		
	
		System.out.println("Tabela  Criada !!!");
		
		
	}catch (Exception e) {
		e.printStackTrace();
	}
	finally {
		try {
			con.close();
			stm.close();
			 

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	


}

public void criarTabelaDinamica_Turma_Media(String BD,String tabela, ArrayList<String> desciplinas, int quantCursos){
	

	String colunas="id int NOT NULL PRIMARY KEY,Trimestre varchar(6),Alunos varchar(60),bi varchar(45),";
	int controle=0;

	for (String desciplina : desciplinas) {
		++controle;
		
		
		  String actualizar="";
		  if((desciplina.contains(" "))||(desciplina.contains("  "))) {
			  
			  String[] espacos=desciplina.split(" ");
			  for (String e : espacos) {
				  actualizar=actualizar+e;
				  
			}
			  desciplina=actualizar;
		  }
		
		
		if(controle<quantCursos) {
			
			colunas=colunas+desciplina+" float,";
		}else {
			colunas=colunas+desciplina+" float,";
		}
	}
	 colunas=colunas+"Situacao varchar(20)";
	
	 
	String usarBD="use "+BD;
	
	String sql="create table "+tabela+"("+
			colunas+");";
	
	System.out.println("SQL VER: "+sql);
	
	
	 Connection con=null;
	 PreparedStatement stm=null ;
	
	try {
	        
		
		
		
		con = ConnectionFactory.getConnection();
		stm = con.prepareStatement(sql);
		stm.executeUpdate(usarBD);
		stm.executeUpdate();
		
	
		System.out.println("Tabela  Criada !!!");
		
		
	}catch (Exception e) {
		e.printStackTrace();
	}
	finally {
		try {
			con.close();
			stm.close();
			 

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	


}


public void criarTabela_Classes_Disciplinas(String BD, ArrayList<String> classes, int quantClasses){
	

	String colunas="";
	int controle=0;
	
	for (String desciplina : classes) {
		++controle;
		
		if(controle<quantClasses) {
			
			colunas=colunas+desciplina+" varchar(40),";
		}else {
			colunas=colunas+desciplina+" varchar(40)";
		}
	}
	
	 
	String usarBD="use "+BD;
	
	String sql="create table Classes_Disciplinas("+
			colunas+");";
	
	
	
	
	 Connection con=null;
	 PreparedStatement stm=null ;
	
	try {
	        
		
		
		
		con = ConnectionFactory.getConnection();
		stm = con.prepareStatement(sql);
		stm.executeUpdate(usarBD);
		stm.executeUpdate();
		
	
		System.out.println("Tabela  Criada !!!");
		
		
	}catch (Exception e) {
		e.printStackTrace();
	}
	finally {
		try {
			con.close();
			stm.close();
			 

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	


}


public void criarTabela_InfoEscola(String BD){

	String usarBD="use "+BD;
	
	String sql="create table infoescola("
			 
			+ "id int NOT NULL PRIMARY KEY,estagio varchar(7) ,niveis varchar(20),Preco int,"
			+ "Materias varchar(75),"
			+ "Documentos varchar(75),"
			+ "qalunos int,qfunc int,QCurso int,ensino varchar(7));";
	
	
	 Connection con=null;
	 PreparedStatement stm=null ;
	
	try {
	        
		
		
		
		con = ConnectionFactory.getConnection();
		stm = con.prepareStatement(sql);
		stm.executeUpdate(usarBD);
		stm.executeUpdate();
		
	
		System.out.println("Tabela  Criada !!!");
		
		
	}catch (Exception e) {
		e.printStackTrace();
	}
	finally {
		try {
			con.close();
			stm.close();
			 

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	


}
public void criarTabela_InfoEscola2_DatasProvas(String BD){

	String usarBD="use "+BD;
	
	String sql="create table infoescola2("
			 
			+ "id int NOT NULL PRIMARY KEY,Conteudos varchar(40) ,"
			+ "Datas varchar(20),Recurso varchar(30));";
	
	
	 Connection con=null;
	 PreparedStatement stm=null ;
	
	try {
	        
		
		
		
		con = ConnectionFactory.getConnection();
		stm = con.prepareStatement(sql);
		stm.executeUpdate(usarBD);
		stm.executeUpdate();
		
	
		System.out.println("Tabela  Criada !!!");
		
		
	}catch (Exception e) {
		e.printStackTrace();
	}
	finally {
		try {
			con.close();
			stm.close();
			 

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	


}

public void criarTabelaTurma(String BD, Turma turma,String escola){
	
	
	String turno = turma.getTurno();
	String informeO_Periodo=turno;
	String NomeDoCurso= turma.getCurso();
	String curso= NomeDoCurso;
	
	String turno2=(String)turno.subSequence(0, 1);
	String nivel2=(String)turma.getNivel().subSequence(0, 2);
	
	
	
	String abeviaturaCurso_Ou_Disciplina= NomeDoCurso;
	abeviaturaCurso_Ou_Disciplina=abeviaturaCurso_Ou_Disciplina.substring(0, 3);
	
	
	
	 Tabela_Actualizar_SQL t = new Tabela_Actualizar_SQL();
	 Salvar_SQL salvar= new Salvar_SQL();
	 Pesquisar_SQL p = new Pesquisar_SQL();
	 

	int contador=t.retornarUmConteudo_Int(BD,"controle_turmas","qTurmas");
	++contador;

	String usarBD="use "+BD;
	
	String sigla= turma.getSigla();
	String nomeTurma="";
	
	
	// O IF abaixo está a ver se a turma tem uma sigla, para personaliza-la
	if((sigla.equalsIgnoreCase(""))||(sigla==null)) {
		
		
		// O codigo abaixo está  a gerar dturma do medio ou 
		//Primario ou secundario, dependendo do nivel selecionado !
		
		
		if((nivel2.contains("10"))||
				(nivel2.contains("11"))||(nivel2.contains("12"))||
				(nivel2.contains("13"))) {
			
			nomeTurma = contador+"_"+"Turma"+turno2+""+
		             abeviaturaCurso_Ou_Disciplina+""+nivel2;
		}else {
			nomeTurma = contador+"_"+"Turma"+turno2+""+
		             ""+""+nivel2;
			
		}
		 
	}else {
		
		
		if((nivel2.contains("10"))||
				(nivel2.contains("11"))||(nivel2.contains("12"))||
				(nivel2.contains("13"))) {
			
			
			nomeTurma = contador+"_"+"Turma"+turno2+""+
		             abeviaturaCurso_Ou_Disciplina+""+nivel2+"__"+sigla;
			
		}else {
			
			nomeTurma = contador+"_"+"Turma"+turno2+""+
		             ""+""+nivel2+"__"+sigla;
		}
		
		
	}
	
	
	
		
		String sql="create table"+" "+nomeTurma+"("
				 
			+ "id int NOT NULL PRIMARY KEY,"+nomeTurma+" varchar(90),Alunos varchar(160),"
			+ "Disciplinas varchar(45),"
			+ "Professores varchar(70),"
			+ "NSala int,NProc int,NDeAlunos int,curso varchar(30),DirecTurma varchar(70),"
			+ "Instituicao varchar(70));";
	
	
	 Connection con=null;
	 PreparedStatement stm=null ;
	
	try {
	        
		
		
		con = ConnectionFactory.getConnection();
		stm = con.prepareStatement(sql);
		stm.executeUpdate(usarBD);
		stm.executeUpdate();
		
		// Criando Tabelas Auxiliares 
		
		criar_Turma_Dados_Pessoais(BD, nomeTurma);
		criar_Turma_Acesso(BD,nomeTurma);
		criar_Turma_Financa(BD, nomeTurma);
		
		//salvar.ins
		
		
		ArrayList<String> desciplinas= p.pesquisarTudoEmString(BD, "cursos_disciplinas", NomeDoCurso);
		int quantCursos= 0;
		
		
		ArrayList<String> dessciplinasFiltrada = new ArrayList<>();
		
		ArrayList<String> desc = new ArrayList<>();
		String desc2;
		// Filtrando As Desciplinas do curso e do nivel selecionado !
		for (String desciplina : desciplinas) {
			System.out.println("Disci: "+desciplina);
			
			if(desciplina.contains(nivel2)) {

				++quantCursos;
				desc = t.tirarCaracteres_RetornandoA_A_Lista(desciplina);
				desc2= desc.get(desc.size()-1);
				
				System.out.println("Desc2: "+desc2);
				
				dessciplinasFiltrada.add(t.tirarCaracteres(desc2));
			}
		}
		
		
		criarTabelaDinamica_Avaliiacao_EProva(BD, nomeTurma+"_Avaliacao", dessciplinasFiltrada, quantCursos);
		criarTabelaDinamica_Avaliiacao_EProva(BD, nomeTurma+"_Prova", dessciplinasFiltrada, quantCursos);
		criarTabelaDinamica_Turma_Media(BD, nomeTurma+"_Media", dessciplinasFiltrada, quantCursos);
		
		
		
		// Colocando A Turma No Periodo Da Manha Tarde Ou Noite
		t.alterarcolocarDadosNo_ControleDeTurmas(BD, informeO_Periodo, nomeTurma);
		
		//Colocando Disciplinas Na Turma
		
		//Inserindo a turma no seu devido Curso
		salvar.inserir_Turma(BD, nomeTurma, NomeDoCurso);
		
		/*
		
		for (String desciplina : dessciplinasFiltrada) {
			
			if(desciplina.contains(nivel2)) {
				
				System.out.println("Nome Das Disciplinas: "+desciplina);
				salvar.inserir_Uma_Discip_Turma_Prof_Ou_Coord2(BD, nomeTurma, desciplina, "Disciplinas");
			}
			
		}  */
		
		
		System.out.println("Tabelas  Criadas !!!");
		
		
		
		Tabela_Actualizar_SQL ta= new Tabela_Actualizar_SQL();
		salvar.inserirLinhaVazia1(BD, nomeTurma);

		// Defenindo o Nº de Alunos da Turma
		ta.actualizarColuna_Na_PrimeiraLinha(BD, nomeTurma, "NDeAlunos", turma.getnAlunos());
		// Defenindo o Curso da Turma
		ta.actualizarColuna_QualquerLinha_String(BD, nomeTurma, "curso", NomeDoCurso, 1);
		// Defenindo o Nome da Turma
		ta.actualizarColuna_QualquerLinha_String(BD, nomeTurma, nomeTurma, nomeTurma, 1);
		ta.actualizarColuna_QualquerLinha_Int(BD, nomeTurma, "Instituicao", 0, escola, 1);
		ta.actualizarColuna_Na_PrimeiraLinha(BD, "controle_turmas", "qTurmas", contador);
		
		//Esses conteudos Abaixo não tirei porque posso vir a precisa~los!
		
		
		//ArrayList<String> todosCursos= p.pesquisarTudoEmString(BD, "cursos", "nome");
		//salvar.inserirLinhaVaziaDinamica(BD, "cursos_turmas", todosCursos);
		//salvar.inserirEmQualquer_Coluna_String(BD, "cursos_turmas", nomeTurma, todosCursos, NomeDoCurso);
		
		
	}catch (Exception e) {
		e.printStackTrace();
	}
	finally {
		try {
			con.close();
			stm.close();
			 

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	
	

}



private  void criar_Turma_Dados_Pessoais(String BD,String nomeTurma){

	String usarBD="use "+BD;
	
	String sql="create table"+" "+nomeTurma+"_DadosPessoais"+"("
			 
			+ "id int NOT NULL PRIMARY KEY,bi varchar(60),"
			+ "Telefone int,telefone2 int,telefone3 int,"
			+ "email varchar(45),"		
			+ "mae varchar(65),pai varchar(65)"
			+ ",naturalidae varchar(40),nacionalidade varchar(30),"
			+ "sexo varchar(15),provincia varchar(35),rua varchar(35)"
			+ ",bairro varchar(25),municipio varchar(25),"
			+ "nomes varchar(25),instituicao varchar(80));";
	
	
	 Connection con=null;
	 PreparedStatement stm=null ;
	
	try {
	        
		
		
		
		con = ConnectionFactory.getConnection();
		stm = con.prepareStatement(sql);
		stm.executeUpdate(usarBD);
		stm.executeUpdate();
		
	
		System.out.println("Tabela  Criada !!!");
		
		
	}catch (Exception e) {
		e.printStackTrace();
	}
	finally {
		try {
			con.close();
			stm.close();
			 

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	


}



private  void criar_Func_Dados_Pessoais(String BD,String cargo){

	String usarBD="use "+BD;
	
	String sql="create table"+" "+cargo+"_DadosPessoais"+"("
			 
			+ "id int NOT NULL PRIMARY KEY,bi varchar(60),"
			+ "Telefone int,telefone2 int,telefone3 int,"
			+ "email varchar(45),"		
			+ "mae varchar(65),pai varchar(65)"
			+ ",naturalidae varchar(40),nacionalidade varchar(30),"
			+ "sexo varchar(15),provincia varchar(35),rua varchar(35)"
			+ ",bairro varchar(25),municipio varchar(25),"
			+ "nomes varchar(60),instituicao varchar(80));";
	
	
	 Connection con=null;
	 PreparedStatement stm=null ;
	
	try {
	        
		
		
		
		con = ConnectionFactory.getConnection();
		stm = con.prepareStatement(sql);
		stm.executeUpdate(usarBD);
		stm.executeUpdate();
		
	
		System.out.println("Tabela  Criada !!!");
		
		
	}catch (Exception e) {
		e.printStackTrace();
	}
	finally {
		try {
			con.close();
			stm.close();
			 

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	


}





private  void criar_Turma_Acesso(String BD,String nomeTurma){

	String usarBD="use "+BD;
	
	String sql="create table"+" "+nomeTurma+"_Acesso"+"("
			 
			+ "id int NOT NULL PRIMARY KEY,"
			+ "usuario varchar(120),"
			+ "acesso1 varchar(100),"
			+ "acesso2 varchar(100),"
			+ "contrato varchar(10));";
	
	
	 Connection con=null;
	 PreparedStatement stm=null ;
	
	try {
	        
		
		
		
		con = ConnectionFactory.getConnection();
		stm = con.prepareStatement(sql);
		stm.executeUpdate(usarBD);
		stm.executeUpdate();
		
	
		System.out.println("Tabela  Criada !!!");
		
		
	}catch (Exception e) {
		e.printStackTrace();
	}
	finally {
		try {
			con.close();
			stm.close();
			 

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	


}



private  void criar_Func_Acesso(String BD,String cargo){

	String usarBD="use "+BD;
	
	String sql="create table"+" "+cargo+"_Acesso"+"("
			 
			+ "id int NOT NULL PRIMARY KEY,"
			+ "usuario varchar(40),"
			+ "acesso1 varchar(30),"
			+ "acesso2 varchar(30),"
			+ "contrato varchar(30));";
	
	
	 Connection con=null;
	 PreparedStatement stm=null ;
	
	try {
	        
		
		
		
		con = ConnectionFactory.getConnection();
		stm = con.prepareStatement(sql);
		stm.executeUpdate(usarBD);
		stm.executeUpdate();
		
	
		System.out.println("Tabela  Criada !!!");
		
		
	}catch (Exception e) {
		e.printStackTrace();
	}
	finally {
		try {
			con.close();
			stm.close();
			 

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	


}

public  void criar_Tabela_Estagio(String BD){

	String usarBD="use "+BD;
	
	String sql="create table Estagios("
			 
			+ "id int NOT NULL PRIMARY KEY,"
			+ "Nome varchar(100),"
			+ "bi varchar(70)"
			+ ");";
	
	
	 Connection con=null;
	 PreparedStatement stm=null ;
	
	try {
	        
		
		
		
		con = ConnectionFactory.getConnection();
		stm = con.prepareStatement(sql);
		stm.executeUpdate(usarBD);
		stm.executeUpdate();
		
	
		System.out.println("Tabela  Criada !!!");
		
		
	}catch (Exception e) {
		e.printStackTrace();
	}
	finally {
		try {
			con.close();
			stm.close();
			 

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	


}

public  void criar_Tabela_Agendas(String BD){

	String usarBD="use "+BD;
	
	String sql="create table AgendaDeDoc("
			 
			+ "id int NOT NULL PRIMARY KEY,"
			+ "Documento varchar(70),"
			+ "Data_Agenda varchar(30),"
			+ "Data_Entrega varchar(30)"
			+ ");";
	
	
	 Connection con=null;
	 PreparedStatement stm=null ;
	
	try {
	        
		
		
		
		con = ConnectionFactory.getConnection();
		stm = con.prepareStatement(sql);
		stm.executeUpdate(usarBD);
		stm.executeUpdate();
		
	
		System.out.println("Tabela  Criada !!!");
		
		
	}catch (Exception e) {
		e.printStackTrace();
	}
	finally {
		try {
			con.close();
			stm.close();
			 

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	


}

public  void criar_Tabela_materias_Online(String BD){

	String usarBD="use "+BD;
	
	String sql="create table Materias_Online("
			 
			+ "id int NOT NULL PRIMARY KEY,"
			+ "endereco varchar(255),"
			+ "Data varchar(30),"
			+ "Descricao varchar(30),"
			+ "Ficheiro varchar(255),"
			+ "bi varchar(255),"
			+ "sigla varchar(255)"
			+ ");";
	
	
	 Connection con=null;
	 PreparedStatement stm=null ;
	
	try {
	        
		
		
		
		con = ConnectionFactory.getConnection();
		stm = con.prepareStatement(sql);
		stm.executeUpdate(usarBD);
		stm.executeUpdate();
		
	
		System.out.println("Tabela  Criada !!!");
		
		
	}catch (Exception e) {
		e.printStackTrace();
	}
	finally {
		try {
			con.close();
			stm.close();
			 

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	


}


private  void criar_Turma_Financa(String BD,String nomeTurma){

	String usarBD="use "+BD;
	
	String sql="create table"+" "+nomeTurma+"_Financa"+"("
			 
			+ "id int NOT NULL PRIMARY KEY,"
			+ "Janeiro int,Fevereiro int,Marco int,"
			+ "Abril int,Maio int,Junho int,Julho int,"
			+ "Agosto int, Setembro int,Outubro int,Novembro int,"
			+ "Dezembro int,"
			+ "Falta int,DataPagamento varchar(20),"
			+ "DataPagFalta varchar(20));";
	
	
	 Connection con=null;
	 PreparedStatement stm=null ;
	
	try {
	        
		
		
		
		con = ConnectionFactory.getConnection();
		stm = con.prepareStatement(sql);
		stm.executeUpdate(usarBD);
		stm.executeUpdate();
		
	
		System.out.println("Tabela  Criada !!!");
		
		
	}catch (Exception e) {
		e.printStackTrace();
	}
	finally {
		try {
			con.close();
			stm.close();
			 

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	


}

public void criarTabelaFase2(String BD){

	String usarBD="use "+BD;
	
	String sql="create table Controle_Turmas("
			 
			+ "id int NOT NULL PRIMARY KEY,Manha varchar(100),Tarde varchar(100),"
			+ "Noite varchar(105),"
			+ "qTurmas int);";
	
	
	 Connection con=null;
	 PreparedStatement stm=null ;
	
	try {
	        
		
		
		
		con = ConnectionFactory.getConnection();
		stm = con.prepareStatement(sql);
		stm.executeUpdate(usarBD);
		stm.executeUpdate();
		
	
		System.out.println("Tabela  Criada !!!");
		controleTurmas_InserirLinhaVazia(BD, "Controle_Turmas");
		
	}catch (Exception e) {
		e.printStackTrace();
	}
	finally {
		try {
			con.close();
			stm.close();
			 

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	


}

public void criarTabelaFinancaMatEDoc(String BD){

	String usarBD="use "+BD;
	
	String sql="create table FinancaMatEDoc("
			 
			+ "id int NOT NULL PRIMARY KEY,Materias varchar(40),"
			+ "preco1 int,"
			+ "Documentos varchar(45),"
			+ "preco2 int);";
	
	
	 Connection con=null;
	 PreparedStatement stm=null ;
	
	try {
	        
		
		
		
		con = ConnectionFactory.getConnection();
		stm = con.prepareStatement(sql);
		stm.executeUpdate(usarBD);
		stm.executeUpdate();
		
	
		System.out.println("Tabela  Criada !!!");
		
		
	}catch (Exception e) {
		e.printStackTrace();
	}
	finally {
		try {
			con.close();
			stm.close();
			 

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	


}

private void controleTurmas_InserirLinhaVazia(String BD,String tabela
		
		   ){
	
	
	
	String sql="insert into "+tabela+" values(?,?,?,?,?)";
	Controle_ID_SQL cID= new Controle_ID_SQL();
	
	String usarBD="use "+BD;
	
	
	 Connection con=null;
	 PreparedStatement stm=null ;
	
	try{
		con = ConnectionFactory.getConnection();
		stm = con.prepareStatement(sql);
		stm.executeUpdate(usarBD);
		
	
		
		int codigo;
		
		codigo= cID.recuperarCodigo(BD, tabela, "id");
		
		
		++codigo;
		stm.setInt(1, codigo);
		stm.setString(2, "");
		stm.setString(3, "");
		stm.setString(4, "");
		stm.setInt(5, 0);
		


		stm.executeUpdate();
		
	
		System.out.println(" Linha Vazia Inserida!!!");
		
	
		
	}catch (Exception e) {
		e.printStackTrace();
	}
	finally {
		try {
			con.close();
			stm.close();
			 

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}

public void criarTabela_DisciplinasDos_Professores(String BD){
	
	


	String usarBD="use "+BD;
	
	String sql="create table Disciplinas_Dos_Profs("
	
	
			 
			+ "id int NOT NULL PRIMARY KEY,"
			+ "curso varchar(45),professor varchar(85),"
			+ "desciplina varchar(65),nivel varchar(45),"
			+ "turma varchar(45),tempo int,"
			+ "bi varchar(45),turno varchar(45));";
	
	
	 Connection con=null;
	 PreparedStatement stm=null ;
	
	try {
	        
		
		 
		
		con = ConnectionFactory.getConnection();
		stm = con.prepareStatement(sql);
		stm.executeUpdate(usarBD);
		stm.executeUpdate();
		
	
		System.out.println("Tabela  Criada !!!");
		
		
	}catch (Exception e) {
		e.printStackTrace();
	}
	finally {
		try {
			con.close();
			stm.close();
			 

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	



	
}


public void criarTabela_Escola_Financa(String BD) {
	
	

	String usarBD="use "+BD;
	
	String sql="create table Escola_Financa(id int NOT NULL PRIMARY KEY,Funcionarios varchar(80),\r\n" + 
			"		    	PrecoFunc int,Propinas varchar(50),precoProp int,\r\n" + 
			"		    	Docs varchar(50),precoDoc int,Mat varchar(70),precoMat int,\r\n" + 
			"		    	Estagio int,Recurso int,Matricula varchar(50),precoMatricula int,\r\n" + 
			"		    	Faltas varchar(30), precoFalta int,TempoPropina int, \r\n" + 
			"		    	confirmacao varchar(50), precoConfir int\r\n" + 
			"				 );";
	
	
	 Connection con=null;
	 PreparedStatement stm=null ;
	
	try {
	        
		
		
		
		con = ConnectionFactory.getConnection();
		stm = con.prepareStatement(sql);
		stm.executeUpdate(usarBD);
		stm.executeUpdate();
		
	
		System.out.println("Tabela  Criada !!!");
		
		
	}catch (Exception e) {
		e.printStackTrace();
	}
	finally {
		try {
			con.close();
			stm.close();
			 

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	



	

	
}

public void criarTabela_Tesoureiro_diario(String BD) {
	

	
	


	String usarBD="use "+BD;
	
	String sql="create table Func_Diario("
	
	
			 
			+ "bi varchar(100),"
			+ "Prop int,MatEConf int,"
			+ "servicos int);";
	
	
	 Connection con=null;
	 PreparedStatement stm=null ;
	
	try {
	        
		
		
		
		con = ConnectionFactory.getConnection();
		stm = con.prepareStatement(sql);
		stm.executeUpdate(usarBD);
		stm.executeUpdate();
		
	
		System.out.println("Tabela  Criada !!!");
		
		
	}catch (Exception e) {
		e.printStackTrace();
	}
	finally {
		try {
			con.close();
			stm.close();
			 

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	



	

	
}

  public void criarVariasTabelas(String BD) {
	  
	  
	  ArrayList<String> funcionarios= new ArrayList<>();
	  
	 
		
	   funcionarios.add("Professor");
	   funcionarios.add("Secretaria");
	   funcionarios.add("Tesouraria");
	   funcionarios.add("Coord");
	   funcionarios.add("DG");
	   funcionarios.add("DA");
	   funcionarios.add("DP");
	   
	   
       
	   for (String cargo : funcionarios) {
		
		   
		   criarTabelaFuncFinanca(BD,cargo);
		   criar_Func_Acesso(BD, cargo);
		   criar_Func_Dados_Pessoais(BD, cargo); 
	}
	   
	   
	   
	   criarTabelaFuncFinanca(BD,"Limpeza");
	   criar_Func_Dados_Pessoais(BD, "Limpeza"); 
	   
	   
	   
	   criarTabelaFuncFinanca(BD,"Seguranca");
	   criar_Func_Dados_Pessoais(BD, "Seguranca"); 
	   
	   criarTabelaFuncFinanca(BD,"Cozinheira");
	   criar_Func_Dados_Pessoais(BD, "Cozinheira"); 
	   
	   
	   
  }
  

}
