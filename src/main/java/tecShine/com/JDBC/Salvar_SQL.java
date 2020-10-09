package tecShine.com.JDBC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import tecShine.com.AcessoInteligente.AcessoInteligente;
import tecShine.com.dao.Verificar_Login;
import tecShine.com.model.Agenda;
import tecShine.com.model.Aluno;
import tecShine.com.model.Coordenador;
import tecShine.com.model.Curso;
import tecShine.com.model.Estagio;
import tecShine.com.model.Fase1;
import tecShine.com.model.Fase2;
import tecShine.com.model.Financa;
import tecShine.com.model.Funcionario;
import tecShine.com.model.Minhas_Financas;
import tecShine.com.model.Pagamento;
import tecShine.com.model.Turma;
import tecShine.com.model.WG.Escola;
import tecShine.com.model.WG.PCA_WG;

/**
 * Essa Classe Vai Inserir Conteudos nas
 * Diversas Tabelas de diversas BD como
 * Admin,escolas,pca
 *
   @author Jose Euclides Pedro Pereira dos Santos
 * @version 1.0
 * @see ConnectionFactory
 *
 */
public class Salvar_SQL {

	private  ConnectionFactory cf= new ConnectionFactory();
	private  Controle_ID_SQL cID= new Controle_ID_SQL();


	private String BD;
	int actualizado;

	
	/**
	 * Esse Metodo Abaixo vai inserir Registo na Tabela
	 * Admin de uma dada Escola
	 * @param BD
	 * @param admin 
	 */
	public String inserirTabelaAdmin (Fase2 admin,Fase1 fase1 ){

		BD_Criar_SQL bd= new BD_Criar_SQL();
		Tabela_Actualizar_SQL ta= new Tabela_Actualizar_SQL();
		Tabelas_Criar_SQL c = new Tabelas_Criar_SQL();
		Pesquisar_SQL p = new Pesquisar_SQL();
		
		
		
		  
		String nomeDaEscola=admin.getInstituicao();
		String BD= ta.tirarCaracteres2(nomeDaEscola);
		
		String bi=admin.getBi();
		
		int codigo= p.pesquisarUmConteudo_Numa_Linha_Int("wg", "Escolas", "id", "bi", bi, 0);
		String nomeDaEscola2=codigo+"_"+nomeDaEscola;
		this.BD=nomeDaEscola2;
		
		
		
		
		
		// Criando A Base De Dados e as tabelas
		// Cursos, pca_escola,infoescola,infoescola2,
		// infoescola_Financa
		
		BD=nomeDaEscola2;
		
	    bd.criarBaseDeDados(BD);

	    c.criarTabelaAdmin(BD, "pca_"+BD); 
	    c.criarTabela_Cursos(BD);
	    c.criarTabela_InfoEscola(BD);
	    
	    
	    //Recuperando todos os niveis
	    ArrayList<String> niveis= recuperar_Os_Niveis(fase1); 
	    
	    //IRecuperando o Tipo De Ensino
	    String ensino=niveis.get(niveis.size()-1);
	    niveis.remove(niveis.size()-1);
	    
	    //Inserindo Os Niveis em infoescola
	    for (String nivel : niveis) {
			
	    	inserirNiveis_Ou_Estagio(BD, nivel, false);
		}
	    
	    
	    //Inserindo Se há Estagio ou não em infoescola
	    if(admin.isEstagio()) {
	    	
	    	inserirNiveis_Ou_Estagio(BD, "Sim", true);
	    }else {
	    	inserirNiveis_Ou_Estagio(BD, "Não", true);
	    }
	   
	    //Inserindo O Tipo de Ensino
	    inserir_Ensino(BD, ensino);
	    
	    
	    //Criando Tabela Das Datas De Prova
	    c.criarTabela_InfoEscola2_DatasProvas(BD);
	    
	    //Criando a Tabela de Financas do Admin(vendo as Rendas,Despesas,
	    //valor da WG
	    c.criarTabelaAdminFinanca(BD);
	    
	  //Criando Tabela infoescola
	    c.criarTabelaFase2(BD);
	    c.criar_Tabela_materias_Online(BD);
	    c.criarTabela_CursosPorNiveis(BD);
	    
	    //Criando a Tabela Disciplinas_Dos_Profs, Onde 
	    //Contem varias informações importantes, como:
	    // O nome do Professor, o turno, o Curso, A Disciplina,
	    // O nivel  e a Turma
	    
	    c.criarTabela_DisciplinasDos_Professores(BD);
	    
	    // Aqui está a ser criada diversas tabelas com 
	    // Dados Pessoais, Financeiros, e De Acesso de Todos
	    // Funcionarios da Instituicao Escolar
	    
	    
		   
	    c.criarVariasTabelas(BD);
	    
	    // Aqui Está a ser Criada a Tabela das Finanças
	    
	    c.criarTabela_Escola_Financa(BD);
	    
	    c.criarTabela_Tesoureiro_diario(BD);
	    
	    c.criar_Tabela_Estagio(BD);
	    
	    c.criar_Tabela_Agendas(BD);
	    
	    
	    // Aqui está a ser adicionado od funcionarios e as fatas na Financa
	    inserir_Em_Escola_Financa(BD);
	    inserirLinha_DataProva(BD); 
	    inserir_No_AdminFinanca(BD, bi);
	    
		String sql="insert into pca_"+BD+" values(?,?,?,?,?,?,?,?,?,?)";
		
		
		String usarBD="use "+BD;
		
		
		 Connection con=null;
		 PreparedStatement stm=null ;
		
		try{
			con = ConnectionFactory.getConnection();
			stm = con.prepareStatement(sql);
			stm.executeUpdate(usarBD);
			
			
		     
		     
		     
					
			
			stm.setString(1, admin.getPca());
			stm.setString(2, admin.getSigla());
			stm.setString(3, bi);
			stm.setString(4, admin.escola.getContrato());
			stm.setInt(5, admin.escola.getValor());
			stm.setString(6,nomeDaEscola+" PCA");
			stm.setString(7, bi);
			stm.setString(8, "");
			stm.setString(9, nomeDaEscola);
			
			stm.setBlob(10, admin.getLogotipo().getInputStream(), admin.getLogotipo().getSize());
			
			
			
			
			stm.executeUpdate();
			
		
			System.out.println("Admin Dados Inseridos!!!");
			
			
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
		
		return nomeDaEscola2;
	}
	
	
	
	private void inserir_Em_Escola_Financa(String BD){

		
	
		
	    Controle_ID_SQL cID= new Controle_ID_SQL();
	    
		String sql="insert into Escola_Financa values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		
		
		String usarBD="use "+BD;
		
		
		 Connection con=null;
		 PreparedStatement stm=null ;
		 
		 ArrayList<String> funcionarios= new ArrayList<>();
			
		   funcionarios.add("Professor");
		   funcionarios.add("Secretaria");
		   funcionarios.add("Tesouraria");
		   funcionarios.add("Coord");
		   funcionarios.add("DG");
		   funcionarios.add("DA");
		   funcionarios.add("DP");
		   funcionarios.add("Limpeza");
		   funcionarios.add("Seguranca");
		   funcionarios.add("Cozinheira");
		
		int contador =0;
		int codigo=0;
		
		for (String func : funcionarios) {
			
			
			try{
				con = ConnectionFactory.getConnection();
				stm = con.prepareStatement(sql);
				stm.executeUpdate(usarBD);
				
				
			
				
				
				
				
				
					++contador;
					 codigo=cID.recuperarCodigo(BD, "Escola_Financa", "id");
					 ++codigo;
					 
					 
				
						
						
					 
					 stm.setInt(1, codigo);
					 stm.setString(2, func);
					 stm.setInt(3, 0);
					 stm.setString(4, "");
					 stm.setInt(5, 0);
					 stm.setString(6, "");
					 stm.setInt(7, 0);
					 stm.setString(8, "");
					 stm.setInt(9, 0);
					 stm.setInt(10, 0);
					 stm.setInt(11, 0);
					 stm.setString(12, "");
					 stm.setInt(13, 0);
					 
					if(contador==1) {
						stm.setString(14, "Atraso");
						stm.setInt(15, 0);
					}else if(contador==2) {
						stm.setString(14, "Vermelha");
						stm.setInt(15, 0);
					}else if(contador==3) {
						stm.setString(14, "Azul");
						stm.setInt(15, 0);
					}else {
						
						stm.setString(14, "");
						stm.setInt(15, 0);
					}
						
					
						
					
					stm.setInt(16, 0);
					stm.setString(17, "");
					stm.setInt(18, 0);
				
				
				
				stm.executeUpdate();
				
			
				System.out.println("Admin Dados Inseridos!!!");
				
				
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
		
		
	}
	
	
	
	public Turma inserirAluno(String BD,
    		 Aluno aluno  ){
    	   
      Tabela_Actualizar_SQL t= new Tabela_Actualizar_SQL();
      Pesquisar_SQL p= new Pesquisar_SQL();
      Controle_ID_SQL cID= new Controle_ID_SQL();
      AcessoInteligente integrante=new AcessoInteligente();
        
      
      String bi= aluno.getBi();
      String nome= aluno.getNome();
      
      aluno.setNome(nome);
      aluno.setBi(bi);
      
      
      
      String nivel = aluno.getNivel();
      String turno= aluno.getTurno();
      String curso= aluno.getCurso();
      
       Turma alunoNaTurma = new  Turma();
       alunoNaTurma.setAlunoInserido(false);
         
     	
     	turno= turno.toLowerCase();
     	String turno2=(String)turno.subSequence(0, 1);
     	String nivel2=(String)nivel.subSequence(0, 2);
     	
     	
     	String abeviaturaCurso=curso;
     	abeviaturaCurso=abeviaturaCurso.substring(0, 3).toLowerCase();
     	int nDoProcesso;
     	String turma;
     	String turma_Em_Minuscula;
     	String biComparacao;
     	
     	

     	String nomeTurma="turma"+turno2+""+abeviaturaCurso+""+nivel2;
     	
     	
     	ArrayList<String> turmasNoTurno= p.pesquisarTudoEmString(BD, "Controle_Turmas", turno);
        
     	System.out.println("Entrando nas Turmas ... ");
     	if(turmasNoTurno!=null) {
     		
     		System.out.println("Entrou nas Turmas Do Turno");
     		
     		ArrayList<String> turmasNoTurno_Filtrada= new ArrayList<>();
         	
         	for (String turmas : turmasNoTurno) {
    			
         		turma_Em_Minuscula= turmas.toLowerCase();
         		System.out.println("nomeTurma: "+nomeTurma);
         		System.out.println("Turma Filtrada: "+turmas);
         		if(turma_Em_Minuscula.contains(nomeTurma)) {
         			
         			turmasNoTurno_Filtrada.add(turmas);
         		}
    		}
         	
         	
         	if(turmasNoTurno_Filtrada!=null) {
         		
         		System.out.println("Entrou nas Turmas Filtradas Do Turno");
         		int controle=0;
             	int controle2=0;
             	
             	for (String te : turmasNoTurno_Filtrada) {
             		System.out.println(te);
        			++controle;
        		}
             	
             	int nDeAlunos=0;
             	int codigo=0;
             	
             	int l=0;
             	int qTurmas=turmasNoTurno_Filtrada.size();
             	
             	System.out.println("qTurmas: "+qTurmas);
             	System.out.println("L: "+l);
             	sair:
             	while(l<qTurmas) {
             		 

        			
             		
             		++controle2;
             		
             		// O Codigo abaixo está a recuperar a quantidade alunos que a turma
             		//Suporta (nDeAlunos)e também está a recuperar a quantidade actual de 
             		//Alunos na turma
             		
             		nDeAlunos= p.pesquisarUmConteudo_Numa_Linha_Int(BD, turmasNoTurno_Filtrada.get(l), "NDeAlunos", "id", "", 1);
             		codigo= cID.recuperarCodigo(BD, turmasNoTurno_Filtrada.get(l), "id");
             		
             		System.out.println("Nº De Alunos:"+ turmasNoTurno_Filtrada.get(l)+": "+nDeAlunos);
             		System.out.println("Codigo: "+codigo);
             		
             		
             		++codigo;
             		System.out.println("CODIGO: "+codigo);
             		
             		// Esse Codigo abaixo está a verificar Se A Turma ainda
             		// Está Vazia, permitindo assim colocar o aluno nessa Turma Filtrada
             		if(codigo<=nDeAlunos) {
             			
             			biComparacao = p.pesquisarUmConteudo_Numa_Linha_String(BD, turmasNoTurno_Filtrada.get(l)+"_dadospessoais", "bi", "bi", bi, 0);
             			
             			if(bi.equals(biComparacao)) {
             				
             				alunoNaTurma.setAlunoInserido(false);
             				
             				++l;
             				break sair;
             			}else {
             				
             				
             				  
             				System.out.println("Entroe Aqui");
                 			if(codigo<=1) {
                 				
                 				
                 				nDoProcesso = t.alterarTabela_Aluno(BD, turmasNoTurno_Filtrada.get(l), aluno, 1);
                 				
                 				inserirProva_Avaliacao_Media_Matricula(BD, turmasNoTurno_Filtrada.get(l), 1, curso, "prova", nome, bi,turmasNoTurno_Filtrada.get(l),nivel);
                     			inserirProva_Avaliacao_Media_Matricula(BD, turmasNoTurno_Filtrada.get(l), 1, curso, "avaliacao", nome, bi,turmasNoTurno_Filtrada.get(l),nivel);
                     			inserirProva_Avaliacao_Media_Matricula(BD, turmasNoTurno_Filtrada.get(l), 1, curso, "media",nome,bi,turmasNoTurno_Filtrada.get(l),nivel);
                     			
                     			
                 			}else {
                 				
                 				nDoProcesso = t.alterarTabela_Aluno(BD, turmasNoTurno_Filtrada.get(l), aluno, codigo);
                 			
                 				inserirProva_Avaliacao_Media_Matricula(BD, turmasNoTurno_Filtrada.get(l), codigo, curso, "prova", nome, bi,turmasNoTurno_Filtrada.get(l),nivel);
                     			inserirProva_Avaliacao_Media_Matricula(BD, turmasNoTurno_Filtrada.get(l), codigo, curso, "avaliacao", nome,bi,turmasNoTurno_Filtrada.get(l),nivel);
                     			inserirProva_Avaliacao_Media_Matricula(BD, turmasNoTurno_Filtrada.get(l), codigo, curso, "media", nome, bi,turmasNoTurno_Filtrada.get(l),nivel);
                     			
                     			
                 			}
                 			
                 			
                 			
                 			// O codigo abaixo vai colocar os dados Pessoais, De Acesso,
                 			//Financeiros  e Academicos(Avaliacao,Prova,E Media) do aluno
                 			
                 			
                 			inserir_DAdosPessoais(BD, turmasNoTurno_Filtrada.get(l), aluno,null);
                 			
                 			String[] a = turmasNoTurno_Filtrada.get(l).split("_");
                 			String usuario = a[0];
                 			inserir_DAdosAcesso(BD, turmasNoTurno_Filtrada.get(l), usuario, bi,"",true);
                 			inserir_DAdosFinanca_Aluno(BD, turmasNoTurno_Filtrada.get(l), nivel, curso);
                 			
                 			
                 			if(turmasNoTurno_Filtrada.get(l).contains("__")) {
                 				
                 				turma= t.tirarCaracteres(turmasNoTurno_Filtrada.get(l));
                 				alunoNaTurma.setTurma(turma);
                 			    alunoNaTurma.setUsuario(turma);
                 			}else {
                 				
                 				turma = turmasNoTurno_Filtrada.get(l);
                 				alunoNaTurma.setTurma(turma);
                 				alunoNaTurma.setUsuario(turma);
                 			}
                 			
                 			alunoNaTurma.setAlunoInserido(true);
                 			alunoNaTurma.setAluno(integrante.nome);
                 			
                 			alunoNaTurma.setnDoProcesso(nDoProcesso);
                 			alunoNaTurma.setAluno(nome);
                 			
                 			
                 			
                 			integrante=null;
                 			++l;
                 			break sair;
             			}
             			
             			
             			
             		}else {
             			
             			if(controle2<=controle) {
                 			
             				
             			
             				
             				
             				// Esse If abaixo so vai Entrar em acao, assim que verificar 
             				// Que todas as Turmas nessa lista Filtrada de Turmas nesse curso
             				// Nivel e turno, todas estiverem cheias, escolhendo uma qualquer
             				// Para colocar o aluno 
             				if(controle2==qTurmas) {
             					
             					
             					
             				   // Esse Codigo serve para que o Sistema Escolha umas 
             					// Turma para colocar o Aluno
             					
             					Random r= new Random();
                     			String nomeDaTurma=turmasNoTurno_Filtrada.get(r.nextInt(controle));
                     			
                     			//Aqui está a ser efectivamente colocado o aluno na Turma
                     			
             					
             					biComparacao = p.pesquisarUmConteudo_Numa_Linha_String(BD, turmasNoTurno_Filtrada.get(l)+"_dadospessoais", "bi", "bi", bi, 0);
                     			
                     			if(bi.equals(biComparacao)) {
                     				
                     				alunoNaTurma.setAlunoInserido(false);
                     				
                     				++l;
                     				break sair;
                     			}else {
                     				
                     				
                     				
                     				
                     				if(codigo<=1) {
                         				nDoProcesso = t.alterarTabela_Aluno(BD, nomeDaTurma, aluno, 1);
                         				
                         				inserirProva_Avaliacao_Media_Matricula(BD, nomeDaTurma, 1, curso, "prova", nome, bi, nomeDaTurma,nivel);
                             			inserirProva_Avaliacao_Media_Matricula(BD, nomeDaTurma, 1, curso, "avaliacao", nome, bi, nomeDaTurma,nivel);
                             			inserirProva_Avaliacao_Media_Matricula(BD, nomeDaTurma, 1, curso, "media",nome, bi, nomeDaTurma,nivel);
                         			}else {
                         				nDoProcesso = t.alterarTabela_Aluno(BD, nomeDaTurma, aluno, codigo);
                         				
                         				inserirProva_Avaliacao_Media_Matricula(BD, nomeDaTurma, codigo, curso, "prova",nome, bi, nomeDaTurma,nivel);
                             			inserirProva_Avaliacao_Media_Matricula(BD, nomeDaTurma, codigo, curso, "avaliacao",nome, bi, nomeDaTurma,nivel);
                             			inserirProva_Avaliacao_Media_Matricula(BD, nomeDaTurma, codigo, curso, "media", nome,bi, nomeDaTurma,nivel);
                         			}
                         			
                         			
                         		    // O codigo abaixo vai colocar os dados Pessoais, De Acesso,
                         			//Financeiros  e Academicos(Avaliacao,Prova,E Media) do aluno
                         			
                         			
                         			inserir_DAdosPessoais(BD, nomeDaTurma,aluno,null);
                         			
                         			String[] a = nomeDaTurma.split("_");
                         			String usuario = a[0];
                         			
                         			inserir_DAdosAcesso(BD, nomeDaTurma, usuario,bi,"",true);
                         			inserir_DAdosFinanca_Aluno(BD, nomeDaTurma, nivel, curso);
                         			
                         			
                         			if(nomeDaTurma.contains("__")) {
                         					
                         				turma= t.tirarCaracteres(nomeDaTurma);
                         				alunoNaTurma.setTurma(turma);
                         			    alunoNaTurma.setUsuario(turma);
                         			}else {
                         				
                         				turma = nomeDaTurma;
                         				alunoNaTurma.setTurma(turma);
                         				alunoNaTurma.setUsuario(turma);
                         			}
                         			
                         			alunoNaTurma.setAlunoInserido(true);
                         			alunoNaTurma.setAluno(nome);
                         			
                         			alunoNaTurma.setnDoProcesso(nDoProcesso);
                         			
                         			
                         			
                         			
                         			
                         			integrante=null;
                         			
                         			++l;
                         			break sair;
                     				
                     			}
             					
             					
             					
             					
             					
                     			
                     		
             				}
             				
             				++l;
                 			
                 		}
             		}
             		
             	  
             	  
             		
             	}
         	}
         	
         	
     		
     	}
     	
     	
     	
     	
		return alunoNaTurma;
		
	}
       
       
       public void inserirLinhaVazia1(String BD,String tabela
    		   ){
    	
    	   
    	 
	
		
		String sql="insert into "+tabela+" values(?,?,?,?,?,?,?,?,?,?,?)";
		
		
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
			stm.setString(5, "");
			stm.setInt(6, 0);
			stm.setInt(7, 0);
			stm.setInt(8, 0);
			stm.setString(9, "");
			stm.setString(10, "");
			stm.setString(11, "");


			stm.executeUpdate();
			
		
			System.out.println(" Dados Inseridos!!!");
			
		
			
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
       
       
       private void inserir_No_AdminFinanca(String BD,String bi
    		   ){
    	
    	   
    	 
	
		
		String sql="insert into adminfinanca values(?,?,?,?,?,?)";
		
		
		String usarBD="use "+BD;
		
		
		 Connection con=null;
		 PreparedStatement stm=null ;
		
		try{
			con = ConnectionFactory.getConnection();
			stm = con.prepareStatement(sql);
			stm.executeUpdate(usarBD);
			
		
			stm.setString(1, bi);
			stm.setInt(2, 0);
			stm.setInt(3, 0);
			stm.setInt(4, 0);
			stm.setInt(5, 0);
			stm.setInt(6, 0);


			stm.executeUpdate();
			
		
			System.out.println(" Dados Inseridos!!!");
			
		
			
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
       
       
       public void inserir_No_Cursos_Niveis(String BD,String curso,int CursoDecima,
    		   int cursoDecPrimeira,int cursoDecSegunda,int cursoDecTerceira
    		   ){
    	
    	   
    	 
	
		
		String sql="insert into cursos_Niveis values(?,?,?,?,?,?)";
		
		
		String usarBD="use "+BD;
		
		
		 Connection con=null;
		 PreparedStatement stm=null ;
		
		try{
			con = ConnectionFactory.getConnection();
			stm = con.prepareStatement(sql);
			stm.executeUpdate(usarBD);
			int codigo=0;
			
			codigo= cID.recuperarCodigo(BD, "cursos_Niveis", "id");
			stm.setInt(1, codigo);
			stm.setString(2, curso);
			stm.setInt(3, CursoDecima);
			stm.setInt(4, cursoDecPrimeira);
			stm.setInt(5, cursoDecSegunda);
			stm.setInt(6, cursoDecTerceira);


			stm.executeUpdate();
			
		
			System.out.println(" Dados Inseridos!!!");
			
		
			
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
       
       
       public void inserir_Tesoureiro(String BD,String bi
    		   ){
    	
    	   
    	 
	
		
		String sql="insert into Func_Diario values(?,?,?,?)";
		
		
		String usarBD="use "+BD;
		
		
		 Connection con=null;
		 PreparedStatement stm=null ;
		
		try{
			con = ConnectionFactory.getConnection();
			stm = con.prepareStatement(sql);
			stm.executeUpdate(usarBD);
			
			
			stm.setString(1, bi);
			stm.setInt(2, 0);
			stm.setInt(3, 0);
			stm.setInt(4, 0);
			
			
			
			stm.executeUpdate();
			
		
			System.out.println(" Dados Inseridos!!!");
			
		
			
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
       
       public void inserirLinha_DataProva(String BD
    		   ){
    	
    	   
    	 
	
		
		String sql="insert into infoescola2 values(?,?,?,?)";
		
		
		String usarBD="use "+BD;
		
		
		ArrayList<String> provas = new ArrayList<>();
		provas.add("Trimestre");
		
		
		Connection con=null;
		PreparedStatement stm=null ;
		
		int codigo;
		for (String prova : provas) {
			
			
			try{
				con = ConnectionFactory.getConnection();
				stm = con.prepareStatement(sql);
				stm.executeUpdate(usarBD);
				
				
				
				
				
				codigo= cID.recuperarCodigo(BD, "infoescola2", "id");
				++codigo;
				stm.setInt(1, codigo);
				stm.setString(2, prova);
				stm.setString(3, "S");
				stm.setString(4, "");
				
				
				
				stm.executeUpdate();
				
			
				System.out.println(" Dados Inseridos!!!");
				
			
				
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
		
		
		 
		
		
	}
       
       
       public void inserirEstagio(String BD,String nome,String bi
    		   ){
    	
    	   
    	 
	
		
		String sql="insert into Estagios values(?,?,?)";
		
		
		String usarBD="use "+BD;
		
		
		 Connection con=null;
		 PreparedStatement stm=null ;
		
		try{
			con = ConnectionFactory.getConnection();
			stm = con.prepareStatement(sql);
			stm.executeUpdate(usarBD);
			
			
			
			int codigo;
			
			codigo= cID.recuperarCodigo(BD, "Estagios", "id");
			++codigo;
			stm.setInt(1, codigo);
			stm.setString(2,nome);
			stm.setString(3, bi);
			
			
			
			stm.executeUpdate();
			
		
			System.out.println(" Dados Inseridos!!!");
			
		
			
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
       
       
       public void inserir_AgendaDeDoc(String BD,String documento,String dataEntrega
    		   ){
    	
    	   
    	 
	
		
		String sql="insert into agendadedoc values(?,?,?,?)";
		
		
		String usarBD="use "+BD;
		
		
		 Connection con=null;
		 PreparedStatement stm=null ;
		
		try{
			con = ConnectionFactory.getConnection();
			stm = con.prepareStatement(sql);
			stm.executeUpdate(usarBD);
			
			
			
			int codigo;
			
			codigo= cID.recuperarCodigo(BD, "agendadedoc", "id");
			++codigo;
			stm.setInt(1, codigo);
			stm.setString(2,documento);
			
			Date dataActual= new Date();
			SimpleDateFormat sdf= new SimpleDateFormat("dd/MM/yyyy");
			
			
			stm.setString(3, sdf.format(dataActual));
			stm.setString(4, dataEntrega);
			
			
			
			stm.executeUpdate();
			
		
			System.out.println(" Dados Inseridos!!!");
			
		
			
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
       
       
       public void inserir_materiasOnline(String BD,String endereco
    		   ,String ficheiro,String descricao,String bi_func,
    		   String sigla
    		   ){
    	
    	   
    	 
	
		
		String sql="insert into Materias_Online values(?,?,?,?,?,?,?)";
		
		
		String usarBD="use "+BD;
		
		
		 Connection con=null;
		 PreparedStatement stm=null ;
		
		try{
			con = ConnectionFactory.getConnection();
			stm = con.prepareStatement(sql);
			stm.executeUpdate(usarBD);
			
			
			
			int codigo;
			
			codigo= cID.recuperarCodigo(BD, "Materias_Online", "id");
			++codigo;
			stm.setInt(1, codigo);
			stm.setString(2,endereco);
			
			Date dataActual= new Date();
			SimpleDateFormat sdf= new SimpleDateFormat("dd/MM/yyyy");
			
			
			stm.setString(3, sdf.format(dataActual));
			stm.setString(4, descricao);
			stm.setString(5, ficheiro);
			stm.setString(6, bi_func);
			stm.setString(7, sigla);
			
			
			
			stm.executeUpdate();
			
		
			System.out.println(" Dados Inseridos!!!");
			
		
			
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
       
       public void inserir_Disciplinas_Prof(String BD,String curso,
    		   String professor,String desciplina,String nivel,String turma,
    		   int tempoPorDesciplinaNaTurma,String bi,String turno
    		   ){
    	   
    	   
    	
    	   
		
		String sql="insert into  Disciplinas_Dos_Profs values(?,?,?,?,?,?,?,?,?)";
		
		
		String usarBD="use "+BD;
		int codigo=0;
		
		 Connection con=null;
		 PreparedStatement stm=null ;
		
		try{
			con = ConnectionFactory.getConnection();
			stm = con.prepareStatement(sql);
			stm.executeUpdate(usarBD);
			
			
			
			
			
			

		
			
			codigo= cID.recuperarCodigo(BD, "Disciplinas_Dos_Profs", "id");
			++codigo;
			stm.setInt(1, codigo);
			stm.setString(2, curso);
			stm.setString(3, professor);
			stm.setString(4, desciplina);
			stm.setString(5, nivel);
			stm.setString(6, turma);
			stm.setInt(7, tempoPorDesciplinaNaTurma);
			stm.setString(8, bi);
			stm.setString(9, turno);
			


			stm.executeUpdate();
			
		
			System.out.println(" Dados Inseridos!!!");
			
		
			
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
       
   
       
       
       public void inserir_DAdosPessoais(String BD,String tabela,
               Aluno aluno, Funcionario func
    		   ){
    		

         
		
		String sql="insert into "+tabela+"_DadosPessoais"+" values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		Controle_ID_SQL  cID= new Controle_ID_SQL();
		
		String usarBD="use "+BD;
 
		
		 Connection con=null;
		 PreparedStatement stm=null ;
		
		try{
			con = ConnectionFactory.getConnection();
			stm = con.prepareStatement(sql);
			stm.executeUpdate(usarBD);
			
			int codigo=cID.recuperarCodigo(BD, tabela+"_DadosPessoais", "id");
			
			if(aluno==null) {
				
				
				
			}else {
				
				++codigo;
				stm.setInt(1, codigo);
				stm.setString(2, aluno.getBi());
				stm.setInt(3, aluno.getTelefone());
				stm.setInt(4, aluno.getAlternativo1());
				stm.setInt(5, aluno.getAlternativo2());
				stm.setString(6, aluno.getEmail());
				stm.setString(7, aluno.getMae());
				stm.setString(8, aluno.getPai());
				stm.setString(9, aluno.getNaturalidade());
				stm.setString(10, aluno.getNacionalidade());
				
				String sexo2;
				if(aluno.getSexo()) {
					
					sexo2="M";
				}else {
					sexo2="F";
				}
				stm.setString(11, sexo2);
				stm.setString(12, aluno.getProvincia());
				stm.setString(13, aluno.getRua());
				stm.setString(14, aluno.getBairro());
				stm.setString(15, aluno.getMunicipio());
				stm.setString(16, aluno.getNome());
				stm.setString(17, "");
				
			}
			
			
			
           if(func==null) {
				
				
				
			}else {
				
				++codigo;
				stm.setInt(1, codigo);
				stm.setString(2, func.getBi());
				stm.setInt(3, func.getTelefone());
				stm.setInt(4, func.getAlternativo1());
				stm.setInt(5, func.getAlternativo2());
				stm.setString(6, func.getEmail());
				stm.setString(7, func.getMae());
				stm.setString(8, func.getPai());
				stm.setString(9, func.getNaturalidade());
				stm.setString(10, func.getNacionalidade());
				
				String sexo2;
				if(func.isSexo()) {
					
					sexo2="M";
				}else {
					sexo2="F";
				}
				stm.setString(11, sexo2);
				stm.setString(12, func.getProvincia());
				stm.setString(13, func.getRua());
				stm.setString(14, func.getBairro());
				stm.setString(15, func.getMunicipio());
				stm.setString(16, func.getNome());
				stm.setString(17, "");
				
			}
			


			stm.executeUpdate();
			
		
			System.out.println(" Dados Inseridos!!!");
			if(codigo==1) {
				 Tabela_Actualizar_SQL ta = new Tabela_Actualizar_SQL();
				 Pesquisar_SQL p = new Pesquisar_SQL();
				 
				 ArrayList<String> conteudo= p.pesquisarTudoEmString(BD, "pca_"+BD, "instituicao");
				 
				 String instituicao = conteudo.get(0);
				 ta.actualizarColuna_QualquerLinha_String(BD, tabela+"_DadosPessoais", "instituicao", instituicao, 1);
			 }
		
	
	
	
	
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
       
      
       
       
       public void inserir_DAdosAcesso(String BD,String tabela,
    		   String usuario,String bi,String contrato,boolean e_aluno
    		   
    		   ){
    	   
    	   
    		
		Pesquisar_SQL p = new Pesquisar_SQL();
		ArrayList<String> nomeSaEscola= p.pesquisarTudoEmString(BD, "pca_"+BD, "instituicao");
		
		String sql;
		if(e_aluno) {
			 sql="insert into "+tabela+"_Acesso"+" values(?,?,?,?,?)";
		}else {
			 sql="insert into "+tabela+"_Acesso"+" values(?,?,?,?,?)";
		}
		
		String escola="";
		if(nomeSaEscola.size()>0) {
			
			escola = nomeSaEscola.get(0);
		}
		String usarBD="use "+BD;
		
		
		 Connection con=null;
		 PreparedStatement stm=null ;
		
		try{
			con = ConnectionFactory.getConnection();
			stm = con.prepareStatement(sql);
			stm.executeUpdate(usarBD);
			
		    int codigo= cID.recuperarCodigo(BD, tabela+"_Acesso", "id");
		    ++codigo;
			
		    
		    if(e_aluno) {
		    	
		    	stm.setInt(1, codigo);
				stm.setString(2, escola+" "+usuario+" AL");
				stm.setString(3, bi);
				stm.setString(4, "");
				stm.setString(5, "");
		    }else {
		    	
		    	stm.setInt(1, codigo);
				stm.setString(2, escola+" "+usuario);
				stm.setString(3, bi);
				stm.setString(4, "");
				stm.setString(5, "");
		    }
			
			
			
			
			
			
			stm.executeUpdate();
			
		
			System.out.println(" Dados Inseridos!!!");
			
		
			
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
       
       public void inserir_DAdosFinanca_Aluno(String BD,String tabela
    		   ,String nivel,String curso
    		   
    		   ){
    		 
    	 
	
		Controle_ID_SQL cID= new Controle_ID_SQL();
		
		String sql="insert into "+tabela+"_Financa"+" values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		
		
		String usarBD="use "+BD;
		
		
		 Connection con=null;
		 PreparedStatement stm=null ;
		
		try{
			con = ConnectionFactory.getConnection();
			stm = con.prepareStatement(sql);
			stm.executeUpdate(usarBD);
			
			int codigo=cID.recuperarCodigo(BD, tabela+"_Financa", "id");
		    ++codigo;
			
			stm.setInt(1, codigo);
			stm.setInt(2, 0);
			stm.setInt(3, 0);
			stm.setInt(4, 0);
			stm.setInt(5, 0);
			stm.setInt(6, 0);
			stm.setInt(7, 0);
			stm.setInt(8, 0);
			stm.setInt(9, 0);
			stm.setInt(10, 0);
			stm.setInt(11, 0);
			stm.setInt(12, 0);
			stm.setInt(13, 0);
			stm.setInt(14, 0);
			stm.setString(15, "");
			stm.setString(16, "");
			
			

			stm.executeUpdate();
			


			System.out.println(" Dados Inseridos!!!");
			
			auxiliarFinanca(BD, tabela, nivel, curso, codigo);
			
		
			
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
       
       
       
       public void inserir_DAdosFinanceiros(String BD,String tabela
    		   
    		   
    		   ){
    		
    	 
        Controle_ID_SQL cID= new Controle_ID_SQL();
	
		
		String sql="insert into "+tabela+"_Financa"+" values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		
		
		String usarBD="use "+BD;
		
		
		 Connection con=null;
		 PreparedStatement stm=null ;
		
		try{
			con = ConnectionFactory.getConnection();
			stm = con.prepareStatement(sql);
			stm.executeUpdate(usarBD);
			
			
		    int codigo=cID.recuperarCodigo(BD, tabela+"_Financa", "id");
		    ++codigo;
			
			stm.setInt(1, codigo);
			stm.setInt(2, 0);
			stm.setInt(3, 0);
			stm.setInt(4, 0);
			stm.setInt(5, 0);
			stm.setInt(6, 0);
			stm.setInt(7, 0);
			stm.setInt(8, 0);
			stm.setInt(9, 0);
			stm.setInt(10, 0);
			stm.setInt(11, 0);
			stm.setInt(12, 0);
			stm.setInt(13, 0);
			stm.setInt(14, 0);
			stm.setString(15, "");
			
			
		

			stm.executeUpdate();
			

			System.out.println(" Dados Inseridos!!!");
			
			
		
			
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
       
       
       public void inserirEmQualquer_Coluna_String(String BD,String tabela,String conteudo,
    		   ArrayList<String> todosCursos, String cursoCorrente
    		   ){
    	   
    	   int contador=0;
    	   sair:
         for (String curso : todosCursos) {
			
        	 if(cursoCorrente.equalsIgnoreCase(curso)) {
        		 
        		 break sair;
        	 }
        	 ++contador;
		}
		
		String sql="insert into "+tabela+" values(?)";
		
		
		String usarBD="use "+BD;
		
		
		 Connection con=null;
		 PreparedStatement stm=null ;
		
		try{
			con = ConnectionFactory.getConnection();
			stm = con.prepareStatement(sql);
			stm.executeUpdate(usarBD);
			
			
		
			
				stm.setString(contador, conteudo);
			
			stm.executeUpdate();
			
		
			System.out.println(" Dados Inseridos!!!");
			
			
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
       
       
       
       public void inserirProva_Avaliacao_Media_Matricula(String BD,String tabela,int codigo2,String curso
    		   ,String prova_Avaliacao_Ou_Media,String aluno,String bi,String turma,String nivel){
    	   
    	   
    	   
    	    int codigo;
    	   Pesquisar_SQL p = new Pesquisar_SQL();
           Tabela_Actualizar_SQL t = new Tabela_Actualizar_SQL();
           String nivel2=(String)nivel.subSequence(0, 2);
    	   
    	   
    	   
    	   Connection con=null;
  		   PreparedStatement stm=null ;
  		   String usarBD="use "+BD;
    	   
  		   
  		 ArrayList<String> desciplinas= p.pesquisarTudoEmString(BD, "cursos_disciplinas", curso);
 		int contador=0;
 		
 		ArrayList<String> dessciplinasFiltrada = new ArrayList<>();
 		
 		
 		// Filtrando As Desciplinas do curso e do nivel selecionado !
 		for (String desciplina : desciplinas) {
 			System.out.println("Disci: "+desciplina);
 			
 			if(desciplina.contains(nivel2)) {

 				++contador;
 				
 				dessciplinasFiltrada.add(t.tirarCaracteres(desciplina));
 			}
 		}
 		
 		    
    	   String trechoSQL=gerarSQL_Dinamico(dessciplinasFiltrada);
    	   
    	   String sql;
    	   if(prova_Avaliacao_Ou_Media.equalsIgnoreCase("prova")){
    		   
    		  
    		    
				
				
    		   sql="insert into "+tabela+"_Prova values(?,?,?,?,"+trechoSQL+")";
    		   
    		   try{
    				con = ConnectionFactory.getConnection();
    				stm = con.prepareStatement(sql);
    				stm.executeUpdate(usarBD);
    				
    				
    				
    				int v= cID.recuperarCodigo(BD, tabela+"_Prova", "id");
    				++v;
    				
    				System.out.println("V1: "+v);
    				System.out.println("codigo2: "+codigo2);
    				
    				stm.setInt(1, v);
    				stm.setString(2, "");
    				stm.setString(3, aluno);
    				stm.setString(4, bi);
    				int controle2=4;
    				while(contador>=1) {
    					 
    					++controle2;
    					stm.setInt(controle2, 0);
    					
    					--contador;
    				}
    				
    				stm.executeUpdate();
    				
    			
    				System.out.println(" Dados Inseridos!!!");
    				
    			
    				
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
    		   
    		   
    	   }else if(prova_Avaliacao_Ou_Media.equalsIgnoreCase("Avaliacao")) {
    		   
    		   
    		   
    		   sql="insert into "+tabela+"_Avaliacao values(?,?,?,?,"+trechoSQL+")";
    		   
    		   try{
    				con = ConnectionFactory.getConnection();
    				stm = con.prepareStatement(sql);
    				stm.executeUpdate(usarBD);
    				
    				
    				int v= cID.recuperarCodigo(BD, tabela+"_Avaliacao", "id");
    				++v;
    				
    				System.out.println("V2: "+v);
    				
    				stm.setInt(1, v);
    				stm.setString(2, "");
    				stm.setString(3, aluno);
    				stm.setString(4, bi);
    				int controle2=4;
    				while(contador>=1) {
    					 
    					++controle2;
    					stm.setInt(controle2, 0);
    					
    					--contador;
    				}
    				
    				stm.executeUpdate();
    				
    			
    				System.out.println(" Dados Inseridos!!!");
    				
    			
    				
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
    		   
    		   
    	   }else if(prova_Avaliacao_Ou_Media.equalsIgnoreCase("Media")) {
    		   
    		   //colunas=colunas+"CAP int,CPE int,CF int";
    		   
    		   sql="insert into "+tabela+"_Media values(?,?,?,?,"+trechoSQL+",?)";
    		   
    		   try{
    				con = ConnectionFactory.getConnection();
    				stm = con.prepareStatement(sql);
    				stm.executeUpdate(usarBD);
    				
    				
    				int v= cID.recuperarCodigo(BD, tabela+"_Media", "id");
    				++v;
    				
    				System.out.println("V3: "+v);
    				
    				stm.setInt(1, v);
    				stm.setString(2, "");
    				stm.setString(3, aluno);
    				stm.setString(4, bi);
    				int controle2=4;
    				while(contador>=1) {
    					 
    					
    					
    						
    				    ++controle2;
    					
    					
    					System.out.println("Controle Dentro"+ controle2);
    					stm.setFloat(controle2, 0);
    					
    					--contador;
    				}
    				
    				System.out.println("Controle Fora"+ controle2);
    				stm.setString(++controle2, "");
    				
    				stm.executeUpdate();
    				
    			
    				System.out.println(" Dados Inseridos!!!");
    				
    			
    				
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
    	   
    	   
		
	}
       
       
       public void inserirLinhaVaziaDinamica(String BD,String tabela,ArrayList<String> conteudos
    		   ){
    	   //?,?,??
    	 String carac="";  
         for (int i=0;i<conteudos.size();i++) {
			
        	 if(i<conteudos.size()-1) {
        		 carac=carac+"?,";
        	 }else {
        		 carac=carac+"?";
        	 }
        	
		}
        
         int codigo=0;
         
         
		String sql="insert into "+tabela+" values("+carac+")";
		System.out.println(sql);
		
		
		String usarBD="use "+BD;
		
		
		 Connection con=null;
		 PreparedStatement stm=null ;
		
		try{
			con = ConnectionFactory.getConnection();
			stm = con.prepareStatement(sql);
			stm.executeUpdate(usarBD);
			
			
			for (int i=0;i<conteudos.size();i++) {
				
				++codigo;
				stm.setString(codigo, "");
			}
		
			stm.executeUpdate();
			
		
			System.out.println(" Dados Inseridos!!!");
			
		
			
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
	 * Esse Metodo Abaixo Vai inserir uma Escola
	 * Na lista de Escoolas cadastradas na WG
	 * 
	 * @param escola
	 */
	public void inserirTabelaEscolas (Escola escola){


		
		String sql="insert into Escolas values(?,?,?,?,?,?,?,?,?,?)";
		
		String usarBD="use wg";
		int codigo;
		
		
		 Connection con=null;
		 PreparedStatement stm=null ;
		
		try{
			con = ConnectionFactory.getConnection();
			stm = con.prepareStatement(sql);
			stm.executeUpdate(usarBD);
			
			

			codigo=cID.recuperarCodigo("wg", "Escolas", "id");
			++codigo;
			
			
			stm.setInt(1,codigo);
			stm.setString(2,escola.getContrato());
			stm.setInt(3, escola.getAlunosQuant());
			stm.setInt(4, escola.getValor());
			stm.setString(5, escola.getBi());
			stm.setString(6, escola.getTel());
			
			
			Date dataActual= new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			
			stm.setString(7, sdf.format(dataActual));  
			stm.setString(8, "");
			stm.setString(9, "");
			stm.setInt(10, 0);
			
			
			
			
			stm.executeUpdate();
			
		
			System.out.println("Escola inserida Dados Inseridos!!!");
			
			
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
	 * 
	 * Esse Metodo Abaixo vai salvar Um PCA 
	 * No Banco de Dados 
	 * @param pca
	 */
	public  void inserir_PCA (PCA_WG pca){
		

		
		String sql="insert into PCA values(?,?,?,?,?,?)";
		
		
		
		String usarBD="use wg";
		String nome_PCA=pca.getNome();
		
		 Connection con=null;
		 PreparedStatement stm=null ;
		
		try{
			con = ConnectionFactory.getConnection();
			stm = con.prepareStatement(sql);
			stm.executeUpdate(usarBD);
			
			stm.setString(1, nome_PCA);
			stm.setString(2, pca.getBi());
			stm.setInt(3, 0);
			
			Verificar_Login usuarioAcesso = new Verificar_Login();
			
			
			
			stm.setString(4, usuarioAcesso.usuarioAcesso(usuarioAcesso.acessoAWG(nome_PCA)));
			stm.setString(5, "");
			stm.setString(6, "");
			
			
			stm.executeUpdate();
			
		
			System.out.println("Dados Inseridos!!!");
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				con.close();
				stm.close();
				 
				
				System.out.println("Coneccoes fechadas");

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
	
	
	
public  boolean inserirCurso(String BD,Curso curso){
	
	
	Tabela_Actualizar_SQL ta= new Tabela_Actualizar_SQL();
	Pesquisar_SQL p = new Pesquisar_SQL();
	
	
	
	
	ArrayList<String> todos_Cursos = p.pesquisarTudoEmString2(BD, "cursos", "nome");
	System.out.println(ta.tirarCaracteres("E,p"));
		
	
	    boolean retorno=false;
	   Tabelas_Criar_SQL tbc= new Tabelas_Criar_SQL();
	   Salvar_SQL s = new Salvar_SQL();
	   
	 
		
		String sql="insert into cursos values(?,?,?,?,?,?,?,?)";
		int codigo=0;
		int cursosQuant=0;
		String crs=curso.getNome();
		String novoCurso=crs;
		
		boolean curso_Existe=true;
		
		
		if(todos_Cursos!=null) {
		
			sair:
				for (String curs : todos_Cursos) {

					if(crs.equalsIgnoreCase(curs)) {
						curso_Existe=false;
						break sair;

					}
				}
		}
		
		
		
		int precoDoCurso1= curso.getPreco1();
		int precoDoCurso2= curso.getPreco2();
		int precoDoCurso3= curso.getPreco3();
		int precoDoCurso4= curso.getPreco4();
		
		if(curso_Existe) {
			
			if(crs.contains(".")) {
				
				retorno=false;
			}else {
				
				
				
				
				
				String usarBD="use "+BD;
				
				
				
				
				 Connection con=null;
				 PreparedStatement stm=null ;
				
				try{
					con = ConnectionFactory.getConnection();
					stm = con.prepareStatement(sql);
					stm.executeUpdate(usarBD);
					
					codigo=cID.recuperarCodigo(BD,"cursos","id");
					++codigo;
					
					
					
					stm.setInt(1, codigo);
					stm.setString(2, crs);
					stm.setInt(3, curso.getCargaHoraria());
					stm.setInt(4, 0);
					stm.setString(5, "");
					stm.setString(6, "");
					stm.setString(7, "");
					stm.setString(8, "");
					
					
					stm.executeUpdate();
					
				
				
					System.out.println("Dados Inseridos!!!");  
					
					
					
				}catch (Exception e) {
					e.printStackTrace();
				}
				finally {
					try {
						con.close();
						stm.close();
						 
						
						tbc=null;
						
						System.out.println("Coneccoes fechadas");

					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
				  
				
				inserir_Nome_Curso_Propinas(BD, novoCurso);
				inserir_Nome_Do_Curso_ParaConfirmacao(BD, novoCurso);
				inserir_Nome_Do_Curso_Matricula(BD, novoCurso);
				
				inserir_Preco_Do_Curso_Propina(BD, novoCurso, 0);
				s.inserir_Mat_Doc_QFunc_QAlunos_(BD, "infoescola", "QCurso", null, 0, 0, codigo);
				inserir_No_Cursos_Niveis(BD, novoCurso, precoDoCurso1, precoDoCurso2, precoDoCurso3, precoDoCurso4);
				
				cursosQuant=codigo;
				if(cursosQuant==1) {

					//ArrayList<String> cursos= p.pesquisarTudoEmString(BD, "cursos", "nome");
					
					ArrayList<String> cursos= new ArrayList<>();
					tbc= new Tabelas_Criar_SQL();
					
					
					crs=ta.tirarCaracteres(crs);
					cursos.add(crs);
					
					
					
					tbc.criarTabelaDinamica(BD, "Cursos_Disciplinas", cursos, cursosQuant);
					tbc.criarTabelaDinamica(BD, "Cursos_Turmas", cursos, cursosQuant);
					//tbc.criarTabelaDinamica(BD, "Cursos_Profs_E_Coord", cursos, cursosQuant);
								
					tbc=null;
				}else {
					
					
					System.out.println("cursosQuant: "+cursosQuant);
					crs = ta.tirarCaracteres(crs);
					recuperarElinar_Criar_Actualizar(BD,cursosQuant,crs);
					 
				}
				
				retorno=true;
			}
			
		}
		
		
		
		
		return retorno;
	}


public void inserirInfoEscola(String BD,
		ArrayList<String> niveis, String ensino){
	
	String sql="insert into infoescola values(?,?,?,?,?,?,?,?)";
	String usarBD="use "+BD;
	int codigo=0;
	
	
	
	 Connection con=null;
	 PreparedStatement stm=null ;
	 
	 for (String nivel : niveis) {
	
		 try{

			 





			 con = ConnectionFactory.getConnection();
			 stm = con.prepareStatement(sql);
			 stm.executeUpdate(usarBD);





			 codigo=cID.recuperarCodigo(BD,"infoescola","id");
			 System.out.println("Codigo="+codigo);
			 ++codigo;

			 stm.setInt(1, codigo);
			 stm.setString(2, "");
			 stm.setString(3, nivel);
			 stm.setString(4, "");
			 stm.setString(5, "");
			 stm.setInt(6, 0);
			 stm.setInt(7, 0);
			 stm.setString(8, ensino);

             ensino="";
			 stm.executeUpdate();


			 System.out.println("Dados Inseridos!!!");


		 }catch (Exception e) {
			 e.printStackTrace();
		 }
		 finally {
			 try {
				 con.close();
				 stm.close();
				  

				 System.out.println("Coneccoes fechadas");

			 } catch (SQLException e) {
				 // TODO Auto-generated catch block
				 e.printStackTrace();
			 }

		 }
	 }
}



public boolean inserir_NaTabela_InfoEscola2_DatasProvas(String BD,
		ArrayList<String> datas, String ensino){
	
	
	
	String sql="insert into infoescola2 values(?,?,?,?)";
	String usarBD="use "+BD;
	int codigo=0;
	boolean estaCheio=false;
	
	
	
	 Connection con=null;
	 PreparedStatement stm=null ;
	 
	  codigo= cID.recuperarCodigo(BD, "infoescola2", "id");
	 
	 if(codigo==8) {
		 
		 estaCheio=true;
	 }else {
		 
		 String[] conteudos= {"avaliacao1","prova1","avaliacao2","prova2","avaliacao3",
				 "prova3","Exame","Recurso"};
		 
		 
		 for (int i=0;i<datas.size();i++) {
				
			 try{

				 

				 if(i==1) {
					 
					 sql="update infoescola2 set Conteudos="+conteudos[i]+
							 ",Datas="+datas.get(i)+" where id="+1;
				 
				     
					 con = ConnectionFactory.getConnection();
					 stm = con.prepareStatement(sql);
					 stm.executeUpdate(usarBD);


					 stm.executeUpdate();


					 System.out.println("Dados Actualizados!!!");

				 
				 }else {
					 
					 
					 con = ConnectionFactory.getConnection();
					 stm = con.prepareStatement(sql);
					 stm.executeUpdate(usarBD);



					 
					 System.out.println("Codigo="+codigo);
					 ++codigo;

					 stm.setInt(1, codigo);
					 stm.setString(2, conteudos[i]);
					 stm.setString(3, datas.get(i));
					 stm.setInt(4, 0);
					 
					 
					 stm.executeUpdate();


					 System.out.println("Dados Inseridos!!!");
					 
					 
				 }
           


				 


			 }catch (Exception e) {
				 e.printStackTrace();
			 }
			 finally {
				 try {
					 con.close();
					 stm.close();
					  

					 System.out.println("Coneccoes fechadas");

				 } catch (SQLException e) {
					 // TODO Auto-generated catch block
					 e.printStackTrace();
				 }

			 }
		 }
	 }
	 
	 return estaCheio;
}



/*
 * 
 * 
 * public void inserirMatEDocumentos(String BD,
		ArrayList<Financa> financas){
	
	String sql="insert into FinancaMatEDoc values(?,?,?,?,?);";
	String usarBD="use "+BD;
	int codigo=0;
	
	
	
	 Connection con=null;
	 PreparedStatement stm=null ;
	 
	 for (Financa financa : financas) {
	
		 try{

			 





			 con = cf.criarConexao();
			 stm = con.prepareStatement(sql);
			 stm.executeUpdate(usarBD);

             
			 codigo=cID.recuperarCodigo(BD, "FinancaMatEDoc", "id");
			 ++codigo;

			 stm.setInt(1, codigo);
			 stm.setString(2, financa.getMaterias());
			 stm.setInt(3, 0);
			 stm.setString(4, financa.getDocumento());
			 stm.setInt(5, 0);
			 
			 stm.executeUpdate();


			 System.out.println("Dados Inseridos!!!");


		 }catch (Exception e) {
			 e.printStackTrace();
		 }
		 finally {
			 try {
				 con.close();
				 stm.close();
				  

				 System.out.println("Coneccoes fechadas");

			 } catch (SQLException e) {
				 // TODO Auto-generated catch block
				 e.printStackTrace();
			 }

		 }
	 }
}
 * 
 * 
 * 
 */


   private void inserir_Mat_Doc_QFunc_QAlunos_(String BD,String tabela,String coluna,ArrayList<String> conteudos,
			int qalunos,int qfunc,int QCurso){
	
	   Tabela_Actualizar_SQL ta= new Tabela_Actualizar_SQL();
	   
	   ta.alterarTabela_InfoEscola_(BD, tabela, coluna, conteudos, qalunos, qfunc,QCurso);
	   
	   ta=null;
}
   
   public void inserir_Materias(String BD,ArrayList<String> materias){
	
	   Tabela_Actualizar_SQL ta= new Tabela_Actualizar_SQL();
	   
	   ta.alterarTabela_InfoEscola_(BD, "infoescola", "materias", materias, 0, 0,0);
	   
	   for (String material : materias) {
		   inserir_Nome_Do_Material(BD, material); 
	}
	   
	   
}
   public void inserir_Documentos(String BD,ArrayList<String> documentos){
	
	   Tabela_Actualizar_SQL ta= new Tabela_Actualizar_SQL();
	   
	   ta.alterarTabela_InfoEscola_(BD, "infoescola", "documentos", documentos, 0, 0,0);
	   
	   for (String documento : documentos) {
		   
		   inserir_Nome_Do_Documento(BD, documento);
		
	}
	   
	   
}  
   
   


public void inserirNiveis_Ou_Estagio(String BD
		,String conteudo,boolean e_um_estagio){
	
	String sql="insert into infoescola values(?,?,?,?,?,?,?,?,?,?)";
	String usarBD="use "+BD;
	int codigo=0;
	String estagio="sim";
	
	
	 Connection con=null;
	 PreparedStatement stm=null ;
	 
	 
		    if(e_um_estagio) {
		    	
		    	
		    	 sql="update infoescola set estagio='"+estagio+"' where id="+1;
		    	 try{

					 
		    	    con = ConnectionFactory.getConnection();

		 			stm = con.prepareStatement(sql);
		 			stm.executeUpdate(usarBD);
		 			stm.executeUpdate();

		 			System.out.println("Actualizado com  Sucesso !!!");



				 }catch (Exception e) {
					 e.printStackTrace();
				 }
				 finally {
					 try {
						 con.close();
						 stm.close();
						  

						 System.out.println("Coneccoes fechadas");

					 } catch (SQLException e) {
						 // TODO Auto-generated catch block
						 e.printStackTrace();
					 }

				 }
		    	
		    }else {
		    	
		    	 try{

					 





					 con = ConnectionFactory.getConnection();
					 stm = con.prepareStatement(sql);
					 stm.executeUpdate(usarBD);





					 codigo=cID.recuperarCodigo(BD,"infoescola","id");
					 System.out.println("Codigo="+codigo);
					 ++codigo;
					
					 stm.setInt(1, codigo);
					 stm.setString(2, "");
					 stm.setString(3, conteudo);
					 stm.setInt(4, 0);
					 stm.setString(5, "");
					 stm.setString(6, "");
					 stm.setInt(7, 0);
					 stm.setInt(8, 0);
					 stm.setInt(9, 0);
					 stm.setString(10, "");
					 
					 
					 

					 stm.executeUpdate();


					 System.out.println("Dados Inseridos!!!");


				 }catch (Exception e) {
					 e.printStackTrace();
				 }
				 finally {
					 try {
						 con.close();
						 stm.close();
						  

						 System.out.println("Coneccoes fechadas");

					 } catch (SQLException e) {
						 // TODO Auto-generated catch block
						 e.printStackTrace();
					 }

				 }
		    	
		    	
		    }
		
				
			
		 
	 
	 
}


public void inserir_Ensino(String BD
		,String conteudo_Abreviado){
	
	String sql;
	String usarBD="use "+BD;
	
	
	 Connection con=null;
	 PreparedStatement stm=null ;
	 
	 
		    	
		    	
		    	 sql="update infoescola set ensino='"+conteudo_Abreviado+"' where id="+1;
		    	 try{

					 
		    	    con = ConnectionFactory.getConnection();

		 			stm = con.prepareStatement(sql);
		 			stm.executeUpdate(usarBD);
		 			stm.executeUpdate();

		 			System.out.println("Actualizado com  Sucesso !!!");



				 }catch (Exception e) {
					 e.printStackTrace();
				 }
				 finally {
					 try {
						 con.close();
						 stm.close();
						  

						 System.out.println("Coneccoes fechadas");

					 } catch (SQLException e) {
						 // TODO Auto-generated catch block
						 e.printStackTrace();
					 }

				 }
		    	
		    
		
				
			
		 
	 
	 
}
	

  public void recuperarElinar_Criar_Actualizar(String BD,int cursosQuant,String conteudo){
	
	  Tabelas_Criar_SQL tbc= new Tabelas_Criar_SQL();
	   Tabela_Eliminar_SQL eliminar = new Tabela_Eliminar_SQL();
	   Pesquisar_SQL p = new Pesquisar_SQL();
	   Tabela_Actualizar_SQL ta = new Tabela_Actualizar_SQL();
	   
	   
	   /**
	    * 
	    * Recuoerando as desciplinas , Eliminando a Tabela
	    * , Criando outra
	    * Maior e Recoloar os dados anteriormente tirados
	    */
	  
	   
	  ArrayList<String> cursos= p.pesquisarTudoEmString(BD, "cursos", "nome");
	  
	  // Filtrando os cursos anteriores(os que têm as suas desciplijnas respetivas)
	  
	  ArrayList<String> cursost= new ArrayList<>();
	 
	  for(int i=0; i<cursos.size()-1;i++) {
			
			cursost.add(cursos.get(i));
		}
	  
	  
	   // Esse Array Abaixo vai servir como repositorio de toads as Desciplinas de cada curso
		 ArrayList<ArrayList<String>> todas_Desciplinas= new ArrayList<>();
		 

		 
		 
		 //Aqui Está a se recuperar os Nomes De Todas Discplinas emm cada curso
		 for (String c : cursost) {
			 
				
          
			 ArrayList<String> desciplinas = p.pesquisarTudoEmString(BD, "Cursos_Disciplinas", c);
			 todas_Desciplinas.add(desciplinas);
			 
			 
		}
		 
		 //Aqui Está a se Eliminar A Tabela curso_Disciplinas
		 eliminar.EliminarQualTabela(BD, "Cursos_Disciplinas",false);
		 
		// Aqui Está a se Incrementar o novo curso para ter também as suas disciplinas
		 //cursos.add(conteudo);
		 
		// Aqui Está a ser criada a nova tabela  Curso_disciplinas
		 
		 tbc.criarTabelaDinamica(BD, "Cursos_Disciplinas", cursos, cursosQuant);
		 
			 
		  /*
		   *  Aqui Está a ser Implementados os dados tirados
		   *  anteriormente em cada Curso nas seus
		   *  respectivos cursos e Criando também uma nova coluna de 
		   *  Disciplinas para o no curso  na tabela Curso_disciplinas
		   */
		 
		    String deseciplina;
		    String nomeDoCurso;
			 for (int i = 0; i < cursost.size(); i++) {
				 
				 nomeDoCurso = cursost.get(i);
				 for (int j = 0; j < todas_Desciplinas.get(i).size(); j++) {
					 
					 
					 deseciplina = todas_Desciplinas.get(i).get(j);
					 
					 ta.alterarTabela_Coluna_String(BD, "Cursos_Disciplinas", deseciplina, nomeDoCurso);
				}
				
			}
		 
 
	         
			 //=======================================================
			 
			 
			 
			 /**
			    * 
			    * Recuoerando as turmad , Eliminando a Tabela
			    * , Criando outra
			    * Maior e Recoloar os dados anteriormente tirados
			    */
			 
			 
		     ArrayList<ArrayList<String>> todas_Turmas = new ArrayList<>();
		     
			
			
			// Aqui Está a se recuperar os Nomes De Todas Turmas emm cada curso
			 for (String c : cursost) {
		          
				 ArrayList<String> turmas = p.pesquisarTudoEmString(BD, "Cursos_Turmas", c);
                 todas_Turmas.add(turmas);
			}
			 
			// Aqui Está a se Eliminar A Tabela curso_Disciplinas
			 eliminar.EliminarQualTabela(BD, "Cursos_turmas",false);
			 
			// Aqui Está a ser criada a nova tabela  curso_Turmas
			 tbc.criarTabelaDinamica(BD, "Cursos_Turmas", cursos, cursosQuant);
		 
			 
                  
			 /*
			   *  Aqui Está a ser Implementados os dados tirados
			   *  anteriormente em cada Turma nas seus
			   *  respectivos cursos e Criando também uma nova coluna de 
			   *  Turma para o novo curso  na tabela Curso_Turmas
			   */
			 
			 
			     String turma;
				 for (int i = 0; i < cursost.size(); i++) {
					 
					 nomeDoCurso = cursost.get(i);
					 for (int j = 0; j < todas_Turmas.get(i).size(); j++) {
						 
						 
						 turma = todas_Turmas.get(i).get(j);
						 
						 ta.alterarTabela_Coluna_String(BD, "Cursos_Disciplinas", turma, nomeDoCurso);
					}
					
				}
			 
			 
				
				//=========================================================
				
				//DINAMISMO DE TABELA DOS CURSOS_PERTENCENTES
				
				/*
				ArrayList<ArrayList<String>> todos_Profs =new ArrayList<>();
				
				
				
				// Aqui Está a se recuperar os Nomes De Todas Turmas emm cada curso
				 for (String c : cursost) {
			          
					 ArrayList<String> profs =  p.pesquisarTudoEmString(BD, "Cursos_Profs_E_Coord", c);
					 todos_Profs.add(profs);
				}
				// Aqui Está a se Eliminar A Tabela curso_Disciplinas
				 eliminar.EliminarQualTabela(BD, "Cursos_Profs_E_Coord",false);
				 
				// Aqui Está a ser criada a nova tabela  curso_Turmas
				 tbc.criarTabelaDinamica(BD, "Cursos_Profs_E_Coord", cursos, cursosQuant);
			 
				 
	                  
				 *
				   *  Aqui Está a ser Implementados os dados tirados
				   *  anteriormente em cada Turma nas seus
				   *  respectivos cursos e Criando também uma nova coluna de 
				   *  Turma para o novo curso  na tabela Curso_Turmas
				   *
				 
				 
				 String prof;
				 for (int i = 0; i < cursost.size(); i++) {
					 
					 nomeDoCurso = cursost.get(i);
					 for (int j = 0; j < todos_Profs.get(i).size(); j++) {
						 
						 
						 prof = todos_Profs.get(i).get(j);
						 
						 ta.alterarTabela_Coluna_String(BD, "Cursos_Disciplinas", prof, nomeDoCurso);
					}
					
				}
				 
				   
					 
				
				
				
			    tbc=null;
				eliminar=null;
				p=null;  
				*/
}

  //Inserindo Um Unico Conteudo
  private void inserir_Uma_Discip_Turma_Prof_Ou_Coord(String BD,String tabela,String conteudo,
			String curso){
	  
	  Tabela_Actualizar_SQL ta= new Tabela_Actualizar_SQL();
	  ta.alterarTabela_Coluna_String(BD, tabela, conteudo, curso);
	  
	  ta=null;
  }
  public void inserir_Uma_Discip_Turma_Prof_Ou_Coord2(String BD,String tabela,String conteudo,
			String curso){
	  
	  Tabela_Actualizar_SQL ta= new Tabela_Actualizar_SQL();
	  ta.alterarTabela_Coluna_String(BD, tabela, conteudo, curso);
	  
	  
	  
	  ta=null;
}
  
 //Inserindo Varios Conteudos
  private void inserir_Varias_Discip_Turma_Prof_Ou_Coord(String BD,String tabela,ArrayList<String> conteudos,
			String curso,String nivel){
	  
	  Tabela_Actualizar_SQL ta= new Tabela_Actualizar_SQL();
	  // Inserindo Desciplinas Em Cursos_Disciplinas
	  for (String conteudo : conteudos) {
		  
		  if(conteudo.equalsIgnoreCase("")) {
			  
		  }else {
			  
			  ta.alterarTabela_Coluna_String(BD, tabela, conteudo, curso); 
		  }

		 
	  }
	  
	  
}
  

	
	
	
	 public String gerarSQL_Dinamico(ArrayList<String> conteudos
  		   ){
  	   //?,?,??
  	 String carac="";  
       for (int i=0;i<conteudos.size();i++) {
			
      	 if(i<conteudos.size()-1) {
      		 carac=carac+"?,";
      	 }else {
      		 carac=carac+"?";
      	 }
      	
		}
       
       return carac;
	 }
	 
	 
	 
	 private void auxiliarFinanca(String BD,String tabela,String nivel,String curso,int codigo){
		 
		 
		    Pesquisar_SQL p = new Pesquisar_SQL();
			Tabela_Actualizar_SQL ta= new Tabela_Actualizar_SQL();
			Salvar_SQL s = new Salvar_SQL();
			
			
			ArrayList<String> meses = new ArrayList<>();
			meses.add("Janeiro");
			meses.add("Fevereiro");
			meses.add("Marco");
			meses.add("Abril");
			meses.add("Maio");
			meses.add("Junho");
			meses.add("Julho");
			meses.add("Agosto");
			meses.add("Setembro");
			meses.add("Outubro");
			meses.add("Novembro");
			meses.add("Dezembro");
			
            int precoDoCurso=0;		
			
			if(nivel.contains("10")) {
				precoDoCurso= p.pesquisarUmConteudo_Numa_Linha_Int(BD, "cursos_Niveis", "Decima", "cursos", curso, 0);	
			}else if(nivel.contains("11")) {
				precoDoCurso= p.pesquisarUmConteudo_Numa_Linha_Int(BD, "cursos_Niveis", "DecimaPrimeira", "cursos", curso, 0);	
			}else if(nivel.contains("12")) {
				precoDoCurso= p.pesquisarUmConteudo_Numa_Linha_Int(BD, "cursos_Niveis", "DecimaSegunda", "cursos", curso, 0);	
			}else if(nivel.contains("13")) {
				precoDoCurso= p.pesquisarUmConteudo_Numa_Linha_Int(BD, "cursos_Niveis", "DecimaTerceira", "cursos", curso, 0);	
			}
			
            int preco = precoDoCurso; 
			
			String mes = s.mesActual(BD);
			
			if((nivel.contains("10"))||
					(nivel.contains("11"))||
					(nivel.contains("12"))||
							(nivel.contains("13"))){
				
				sair:
				for(String c : meses) {
					
					
					
					if(c.equalsIgnoreCase(mes)) {
						
						System.out.println("É IGUAL");
						  ta.actualizarColuna_QualquerLinha_Int(BD, tabela+"_Financa", 
							mes,preco, "", codigo);
						
						
						break sair;
					}else { 
						
						System.out.println("É DIFERENTE");
						
						   ta.actualizarColuna_QualquerLinha_Int(BD, tabela+"_Financa", 
				    		   c, 
				    		   1, "", codigo);
					
					
					}
					
					
				}
				
				
				
				
			}else {
				
				preco= p.pesquisarUmConteudo_Numa_Linha_Int(BD,
			       		"infoescola", "Preco",
			    		   "niveis", nivel,0);
				
				 ta.actualizarColuna_QualquerLinha_Int(BD, tabela+"_Financa", 
			    		   mes, 
			    		   preco, "", codigo);
			}
			
			
			
			
				
				
				
				
		 
	 }
			
	 
	 /*
	  * 
	  * 
	  *  public void repor_Cursos_Ou_Disciplinas(String BD,String tabela,
   		   ArrayList<ArrayList<String>> todos_Cursos_Ou_Disciplinas
   		   ){
   	   
   	     
		  
		  int maior=0;
		  for(int i=0;i<todos_Cursos_Ou_Disciplinas.size();i++) {
			  
			  
			  if(maior>=todos_Cursos_Ou_Disciplinas.get(i).size()) {
				  
				  
			  }else {
				  maior=todos_Cursos_Ou_Disciplinas.get(i).size();
			  }
		  }
		  
		  int diferenca=0;
		  for(int i=0;i<todos_Cursos_Ou_Disciplinas.size();i++) {
			  
			  if(todos_Cursos_Ou_Disciplinas.get(i).size()==maior) {
				  
				  
			  }else {
				  
				  diferenca=maior-todos_Cursos_Ou_Disciplinas.get(i).size();
				  
				  for(int j=1;j<=diferenca;j++) {
					  
					  todos_Cursos_Ou_Disciplinas.get(i).add("");
				  }
			  }
			  
		  }
		  
		  System.out.println("Maior: "+maior);
		  System.out.println("diferenca: "+diferenca);
		  
		  for (ArrayList<String> desciplinas : todos_Cursos_Ou_Disciplinas) {
			  
			  for (String d : desciplinas) {
				System.out.println("Conteudo: "+d);
			}
			
		}
		  
		  System.out.println("Tamanho: n"+todos_Cursos_Ou_Disciplinas.size());
		  ArrayList<String> conteudos= new ArrayList<>();
		  int repeticao= todos_Cursos_Ou_Disciplinas.size();
		  
		  for(int i=0;i<repeticao;i++) {
			  
			  conteudos.add("");
		  }
		 String trecho_SQL=gerarSQL_Dinamico(conteudos); 
		 trecho_SQL=trecho_SQL+",?";
		
		String sql="insert into "+tabela+" values("+trecho_SQL+");";
		System.out.println("SQL Dinamico: "+sql);
		
		
		String usarBD="use "+BD;
		int codigo=0;
		int j=1;
		
		
		 Connection con=null;
		 PreparedStatement stm=null ;
		
		try{
			con = cf.criarConexao();
			stm = con.prepareStatement(sql);
			stm.executeUpdate(usarBD);
			
			
			
			for(int i=0;i<maior;i++) {
				
				codigo=cID.recuperarCodigo(BD, tabela, "id");
				++codigo;
				
				stm.setInt(1, codigo);
				 j=1;
				 
				 //Colocando os conteudos nas colunas
				 
				 for(int k=0;k<todos_Cursos_Ou_Disciplinas.size();k++) {
					 
					 ++j;
					 
					 stm.setString(j, todos_Cursos_Ou_Disciplinas.get(k).get(i));
				 }
				 
			}
		
			stm.executeUpdate();
			
		
			System.out.println(" Dados Inseridos!!!");
			
		
			
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
	  * 
	  * 
	  * 
	  * 
	  */
	  
			
			public ArrayList<String> retornarClasses_Do_Ensino(String ensino){
				
				 ArrayList<String> niveis= new ArrayList<>();
				 
				 if(ensino.equalsIgnoreCase("p")) {
					 niveis.add("1º Classe");
					 niveis.add("2º Classe");
					 niveis.add("3º Classe");
					 niveis.add("4º Classe");
					 niveis.add("5º Classe");
					 niveis.add("6º Classe");
					 
				 }else if(ensino.equalsIgnoreCase("S")) {
					 niveis.add("7º Classe");
					 niveis.add("8º Classe");
					 niveis.add("9º Classe");
					
					 
				 }else if(ensino.equalsIgnoreCase("M")) {
					 niveis.add("10º Classe");
					 niveis.add("11º Classe");
					 niveis.add("12 Classe");
					 niveis.add("13 Classe");
					
					 
				 }else if(ensino.equalsIgnoreCase("PS")) {
					 niveis.add("1º Classe");
					 niveis.add("2º Classe");
					 niveis.add("3º Classe");
					 niveis.add("4º Classe");
					 niveis.add("5º Classe");
					 niveis.add("6º Classe");
					 niveis.add("7º Classe");
					 niveis.add("8º Classe");
					 niveis.add("9º Classe");
					
					 
				 }else if(ensino.equalsIgnoreCase("MP")) {
					 niveis.add("1º Classe");
					 niveis.add("2º Classe");
					 niveis.add("3º Classe");
					 niveis.add("4º Classe");
					 niveis.add("5º Classe");
					 niveis.add("6º Classe");
					 niveis.add("10º Classe");
					 niveis.add("11º Classe");
					 niveis.add("12 Classe");
					 niveis.add("13 Classe");
					
					 
				 }
				 else if(ensino.equalsIgnoreCase("MS")) {
					 niveis.add("7º Classe");
					 niveis.add("8º Classe");
					 niveis.add("9º Classe");
					 niveis.add("10º Classe");
					 niveis.add("11º Classe");
					 niveis.add("12 Classe");
					 niveis.add("13 Classe");
					
					 
				 }
				 return niveis;
			}	
			
			public String retornar_SQL(String ensino){
				
				 String sql="";
				 
				 if(ensino.equalsIgnoreCase("p")) {
					 
					 sql="?,?,?,?,?,?";
					 
					 
				 }else if(ensino.equalsIgnoreCase("S")) {
					 
					 sql="?,?,?";
					
					 
				 }else if(ensino.equalsIgnoreCase("M")) {
					
					
					 
				 }else if(ensino.equalsIgnoreCase("PS")) {
					 sql="?,?,?,?,?,?,?,?,?";
					
					 
				 }else if(ensino.equalsIgnoreCase("MP")) {
					 sql="?,?,?,?,?,?";
					
					 
				 }
				 else if(ensino.equalsIgnoreCase("MS")) {
					 sql="?,?,?";
					
					 
				 }
				 return sql;
			}	
		
		
			
			public String retornarTrimestre(String BD) {
				   
				   Pesquisar_SQL p = new Pesquisar_SQL();
				   
				 
		    	   String trimestre= p.pesquisarTudoEmString(BD, "infoescola2", "Datas").get(0);
		    	   
		    	  
		    	   
		    	   return trimestre;
			}
			
			private boolean inserir__Disciplinas(String BD,boolean Disciplina_Chave,String nivel,ArrayList<String> desciplinas,
					String curso) {
				
				
				
				
				
                boolean retorno=false;
                boolean continuar=true;
				sair:
					
					for(int i=0;i<desciplinas.size();i++) {

						if(desciplinas.get(i).contains(".")) {
							retorno=false;
							continuar=false;
							break sair;


						}
					}
						
					if(continuar) {
						
						ArrayList<String> conteudos2= new ArrayList<>();
						String desciplina2;
						
						for (String c : desciplinas) {
							
							
								
								 
								 if(Disciplina_Chave) {
										desciplina2="Chave  "+nivel.substring(0, 2)+"  "+c;
									}else {
										desciplina2="Nao  "+nivel.substring(0, 2)+"  "+c;
									}
								 conteudos2.add(desciplina2);
								
							
					  }
						inserir_Varias_Discip_Turma_Prof_Ou_Coord(BD, "cursos_disciplinas",
								 conteudos2, curso,nivel);
						retorno=true;
					}
					return retorno;
				}
			
			
			public boolean inserir_Varias_Disciplinas(String BD,ArrayList<Boolean> Disciplina_Chave,
					String nivel,ArrayList<String> desciplinas,
					String curso) {
				
				   boolean inserido = false;
				   
				   
				   ArrayList<String> DisciplinasChaves = new ArrayList<>();
				   ArrayList<String> Disciplinas_N_Chaves = new ArrayList<>();
				   for (int i = 0; i < desciplinas.size(); i++) {
					
					   
					   if((desciplinas.get(i).equalsIgnoreCase(""))||
							   (desciplinas.get(i)==null)){
						   
						   
					   }else {
						   
						   
						   if(Disciplina_Chave.get(i)) {
							   
							   DisciplinasChaves.add(desciplinas.get(i));
						   }else {
							   
							   Disciplinas_N_Chaves.add(desciplinas.get(i));
						   }
						   
					   }
					   
					   
				}
				   
				   
				   if(DisciplinasChaves!=null) {
					   
					   
					   inserir__Disciplinas(BD, true, nivel, DisciplinasChaves, curso);
				   }
				   
				   if(Disciplinas_N_Chaves!=null) {


					   inserir__Disciplinas(BD, false, nivel, Disciplinas_N_Chaves, curso);
				   }
				   
				   
				   inserido = true;
				
				return inserido;
			}
			
			
			
			
			

			public void inserir_Turma(String BD,String turma,String curso) {

				inserir_Uma_Discip_Turma_Prof_Ou_Coord(BD, "cursos_turmas", turma, curso);
			}
			
			
			public void inserir_Professor_Automaticamente_Na_Turma(String BD,String professor,String curso,
					String desciplina,String nivel,String usuario,String bi,String turno,int tempoPorDesciplinaNaTurma) {

				inserir_Uma_Discip_Turma_Prof_Ou_Coord(BD, "cursos_profs_e_coord;", professor, curso);
			//	inserir_Funcionarios(BD, "professor", curso, professor, desciplina, nivel, usuario, bi, turno,tempoPorDesciplinaNaTurma);
			}
			
			private void inserir_Professor_Manualmente_Na_Turma(String BD,Funcionario prof) {

				System.out.println("Inserindo Professor ... !");
				
				String professor= prof.getNome();
				prof.setNome(professor);
				String curso= prof.getCurso();
				ArrayList<String> desciplinas = prof.getDisciplinas();
				String nivel = prof.getNivel();
				ArrayList<String> turmas = prof.getTurmas();
				String bi = prof.getBi();
				prof.setBi(bi);
				
				String turno = prof.getTurno();
				
				ArrayList<Integer> tempoPorDesciplinaNaTurma = prof.getTempoPorDesciplinas();
				
				Pesquisar_SQL p = new Pesquisar_SQL();
				Tabela_Actualizar_SQL ta = new Tabela_Actualizar_SQL();
				
				
				
				for(int i=0;i< desciplinas.size();i++) {
					
					for(String turma: turmas) {
						
						inserindo_Prof_Na_Turma_Manualalmente(BD, nivel, curso, turno, desciplinas.get(i), professor, turma,tempoPorDesciplinaNaTurma.get(i),bi);
					}
				}
				
				
				
				
				//inserir_Uma_Discip_Turma_Prof_Ou_Coord(BD, "cursos_profs_e_coord;", professor, curso);
				
				int quantAlunos=p.pesquisarUmConteudo_Numa_Linha_Int(BD, "infoescola", "qfunc", "id", "", 1);
				++quantAlunos;
				   
			    // Defenindo o Nº de Funcionarios Da Instituição
			     ta.actualizarColuna_Na_PrimeiraLinha(BD, "infoescola", "qfunc", quantAlunos);
			
			     inserir_DAdosPessoais(BD, "Professor", null,prof);
      			
			     Date d= (java.util.Date)(prof.getContrato());
			     SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			     String contrato =sdf.format(d);
			     
			     inserir_DAdosAcesso(BD, "Professor", "Professor", bi,contrato,false);
      			 inserir_DAdosFinanceiros(BD, "Professor");
			
			}
			
			
			
			
			public boolean inserir_Integrante(String BD,Funcionario integrante) {

				boolean funInserido=false;
				System.out.println("Inserindo Secretaria ... !");
				
				
				
				String bi = integrante.getBi();
				String cargo = integrante.getCargo();
				integrante.setCargo(cargo);
				
				if(cargo.equalsIgnoreCase("professor")) {
					
					inserir_Professor_Manualmente_Na_Turma(BD, integrante);
					funInserido=true;   
				}else if(cargo.equalsIgnoreCase("Coord")) {
					
					Pesquisar_SQL p = new Pesquisar_SQL();
					Tabela_Actualizar_SQL ta = new Tabela_Actualizar_SQL();
					
					
					
					
					
					//inserir_Uma_Discip_Turma_Prof_Ou_Coord(BD, "cursos_profs_e_coord;", professor, curso);
					
					int quantAlunos=p.pesquisarUmConteudo_Numa_Linha_Int(BD, "infoescola", "qfunc", "id", "", 1);
					++quantAlunos;
					   
				    // Defenindo o Nº de Funcionarios Da Instituição
				     ta.actualizarColuna_Na_PrimeiraLinha(BD, "infoescola", "qfunc", quantAlunos);
				
				     Date d= (java.util.Date)(integrante.getContrato());
				     SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				     String contrato =sdf.format(d); 
				     
				     String curso = integrante.getCurso();
				     String bi2 = integrante.getBi();
				     
				     integrante.setBi(bi2);
				     integrante.setCurso(curso);
				    
				     inserir_CoordenadorDoCurso(BD, curso, bi);
				     
				     
				     inserir_DAdosPessoais(BD, cargo, null,integrante);
	      			 inserir_DAdosAcesso(BD, cargo, cargo, bi,contrato,false);
	      			 inserir_DAdosFinanceiros(BD, cargo);
	      			 
	      			 
	      			funInserido=true;
	      			 
	      			
	      			
				}else {
					
					Pesquisar_SQL p = new Pesquisar_SQL();
					Tabela_Actualizar_SQL ta = new Tabela_Actualizar_SQL();
					
					
					
					
					
					//inserir_Uma_Discip_Turma_Prof_Ou_Coord(BD, "cursos_profs_e_coord;", professor, curso);
					
					int quantAlunos=p.pesquisarUmConteudo_Numa_Linha_Int(BD, "infoescola", "qfunc", "id", "", 1);
					++quantAlunos;
					   
				    // Defenindo o Nº de Funcionarios Da Instituição
				     ta.actualizarColuna_Na_PrimeiraLinha(BD, "infoescola", "qfunc", quantAlunos);
				
				     Date d= (java.util.Date)(integrante.getContrato());
				     SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				     String contrato =sdf.format(d); 
				    
				     inserir_DAdosPessoais(BD, cargo, null,integrante);
	      			 inserir_DAdosAcesso(BD, cargo, cargo, bi,contrato,false);
	      			 inserir_DAdosFinanceiros(BD, cargo);
	      			 
	      			 
	      			 if(cargo.equalsIgnoreCase("tesouraria")||
	      					 (cargo.equalsIgnoreCase("secretaria"))) {
	      				 
	      				inserir_Tesoureiro(BD, bi);
	      			 }
	      			funInserido=true;
	      			 
					
				}
				
				
				
				
			  return funInserido;
			}
			
			
			
			
			
			
			public void inserir_CoordenadorDoCurso(String BD,String curso,
					String bi) {

				Pesquisar_SQL p = new Pesquisar_SQL();
				
				String coordenador = p.pesquisarUmConteudo_Numa_Linha_String(BD, "Professor_DadosPessoais", "nomes", "bi", bi, 0);
				
				inserir_Uma_Discip_Turma_Prof_Ou_Coord(BD, "cursos", coordenador, "coord");
				//inserir_Funcionarios(BD, "Coord", curso, coordenador, "", "", bi, bi, "",0);
				
				Tabela_Actualizar_SQL ta= new Tabela_Actualizar_SQL();
				
				ta.actualizarColuna_Qualquer_Linha(BD, "cursos", "bi", 0, bi, "nome", curso);
			}
			
			public void ELIMINAR_CoordenadorDoCurso_(String BD,String curso,
					String bi) {

				
				Pesquisar_SQL p= new Pesquisar_SQL();
				EliminarLinha_SQL e = new EliminarLinha_SQL();
				
				
				int id = p.pesquisarUmConteudo_Numa_Linha_Int(BD, "Coord_Acesso", "id", "bi", bi, 0);
				e.EliminarLinha(BD, "Coord_Acesso", "id", id, "",false);
				e.EliminarLinha(BD, "Coord_Financa", "id", id, "",false);
				e.EliminarLinha(BD, "Coord_DadosPessoais", "id", id, "",false);
				
				
				//preciso removwe os dados pessoais !
				
				Tabela_Actualizar_SQL ta= new Tabela_Actualizar_SQL();
				
				ta.actualizarColuna_Qualquer_Linha(BD, "cursos", "bi", 0, "", "nome", curso);
				ta.actualizarColuna_Qualquer_Linha(BD, "cursos", "coord", 0, "", "nome", curso);
			}
			

			
				
				
			
			public void inserir_Outros_Funcionarios(String BD,String funcionario,String curso,
					String bi,String cargo) {
				
				   ArrayList<String> funcionarios= new ArrayList<>();
				   funcionarios.add("Secretaria");
				   funcionarios.add("Tesouraria");
				   funcionarios.add("DG");
				   funcionarios.add("DA");
				   funcionarios.add("DP");
				   
				   sair:
				   for (String func : funcionarios) {
					
					   if(cargo.contains(func)) {
						   
						
						   
						//   inserir_Funcionarios(BD, func, curso, funcionario, "", "", bi, bi, "",0);
						   break sair;
					   }
				}

				
			}
			
			
			
			private void inserir_Funcionarios(String BD,Funcionario func) {

				
				   
				   
				   
				   
				    String quem_E_O_Funcionario= func.getCargo();
					//String curso = func.get,
							
					//		,String desciplina,String nivel,
					// String usuario,String bi,String turno,int tempoPorDesciplinaNaTurma
				
				   ArrayList<String> funcionarios= new ArrayList<>();
				   ArrayList<String> tabelas= new ArrayList<>();
				   Tabela_Actualizar_SQL ta= new Tabela_Actualizar_SQL();
				   Pesquisar_SQL p = new Pesquisar_SQL();
					
					// Defenindo o Nº de Funcionarios Da Instituição
				   
				   
				   funcionarios.add("Professor");
				   funcionarios.add("Secretaria");
				   funcionarios.add("Tesouraria");
				   funcionarios.add("Coord");
				   funcionarios.add("DG");
				   funcionarios.add("DA");
				   funcionarios.add("DP");
				   
				   
				   tabelas.add("_DadosPessoais");
				   tabelas.add("_Acesso");
				   tabelas.add("_Financa");
				   
				   String funcionario;
				   int contador=0;
				   
				   if(quem_E_O_Funcionario.equalsIgnoreCase("Professor")) {
					   
					   contador=0;
					   for (String tabela : tabelas) {
						   ++contador;
						   funcionario= quem_E_O_Funcionario+tabela;
						   
						   if(contador==1) {
							   
							  // inserindo_Prof_Na_Turma(BD, nivel, curso, turno, desciplina, professor,tempoPorDesciplinaNaTurma,bi);
							   
							   
							   AcessoInteligente integrante = new AcessoInteligente();
							 //  inserir_DAdosPessoais(BD, funcionario, integrante);
							   
						   }else if(contador==2) {
							   
							  // inserir_DAdosAcesso(BD, funcionario, usuario, bi);
						   }else if(contador==3) {
							   
							  // inserir_DAdosFinanceiros(BD, tabela, nivel, curso);
							   
							   int quantAlunos=p.pesquisarUmConteudo_Numa_Linha_Int(BD, "infoescola", "qfunc", "id", "", 1);
							   ++quantAlunos;
							   // Defenindo o Nº de Funcionarios Da Instituição
								ta.actualizarColuna_Na_PrimeiraLinha(BD, "infoescola", "qfunc", quantAlunos);
							   
						   
					   }
						   
					}
					   
				   }else if(quem_E_O_Funcionario.equalsIgnoreCase("Coord")) {
					   
					   
						   
						   contador=0;
						   for (String tabela : tabelas) {
							   ++contador;
							   funcionario= quem_E_O_Funcionario+tabela;
							   
							   if(contador==1) {
								   
								   
								//   AcessoInteligente integrante = p.pesquisar_Dados_Pessoais_Dum_Intefrante(BD, "Professor_DadosPessoais", bi);
								  // inserir_DAdosPessoais(BD, funcionario, integrante);
								   
							   }else if(contador==2) {
								   
								  // inserir_DAdosAcesso(BD, funcionario, usuario, bi);
							   }else if(contador==3) {
								   
								 //  inserir_DAdosFinanceiros(BD, tabela, nivel, curso);
								   
								   int quantAlunos=p.pesquisarUmConteudo_Numa_Linha_Int(BD, "infoescola", "qfunc", "id", "", 1);
								   ++quantAlunos;
								   // Defenindo o Nº de Funcionarios Da Instituição
									ta.actualizarColuna_Na_PrimeiraLinha(BD, "infoescola", "qfunc", quantAlunos);
								   
							   
						   }
							   
						}
						   
						
					   
					   
				   }else {
					   
					   sair:
					   for (String fun : funcionarios) {
						
						   if(quem_E_O_Funcionario.equalsIgnoreCase(fun)) {
							   
							   
							   
							   contador=0;
							   for (String tabela : tabelas) {
								   ++contador;
								   funcionario= quem_E_O_Funcionario+tabela;
								   
								   if(contador==1) {
									   
									   AcessoInteligente integrante = new AcessoInteligente();
									//   inserir_DAdosPessoais(BD, funcionario, integrante);
									   
								   }else if(contador==2) {
									   
									//   inserir_DAdosAcesso(BD, funcionario, usuario, bi);
								   }else if(contador==3) {
									   
									//   inserir_DAdosFinanceiros(BD, tabela, nivel, curso);
									   
									   int quantAlunos=p.pesquisarUmConteudo_Numa_Linha_Int(BD, "infoescola", "qfunc", "id", "", 1);
									   ++quantAlunos;
									   // Defenindo o Nº de Funcionarios Da Instituição
										ta.actualizarColuna_Na_PrimeiraLinha(BD, "infoescola", "qfunc", quantAlunos);
									   
								   
							   }
								   
							}
							   
							   
							   break sair;
						   }
						   
					}
					  
					   
				   }
				  
				
			}
			
			
			public ArrayList<String> recuperar_Os_Niveis(Fase1 fase1){
				
				
				 
				 ArrayList<String> niveis= new ArrayList<>();
				 if(fase1.isPrimario()) {
					 niveis.add("1º Classe");
					 niveis.add("2º Classe");
					 niveis.add("3º Classe");
					 niveis.add("4º Classe");
					 niveis.add("5º Classe");
					 niveis.add("6º Classe");
					 
				 }else if(fase1.isSecundario()) {
					 niveis.add("7º Classe");
					 niveis.add("8º Classe");
					 niveis.add("9º Classe");
					
					 
				 }else if(fase1.isMedio()) {
					 niveis.add("10º Classe");
					 niveis.add("11º Classe");
					 niveis.add("12 Classe");
					 niveis.add("13 Classe");
					
					 
				 }
			
				 
				 if(niveis.size()==6) {
					 niveis.add("p");
				 }else if(niveis.size()==3) {
					 niveis.add("S");
				 }else if(niveis.size()==4) {
					 niveis.add("M");
				 }else if(niveis.size()==9) {
					 niveis.add("PS");
				 }else if(niveis.size()==10) {
					 niveis.add("MP");
				 }else if(niveis.size()==7) {
					 niveis.add("MS");
				 }
				 
				
			
				 for (String nivel : niveis) {
					
					 System.out.println("Nivel: "+nivel);
				}
				
				return niveis;
			}
	  
			 
			
			public void inserindo_Prof_Na_Turma(String BD,String nivel,
					   String curso,String turno,String desciplina,String professor,
					   int tempoPorDesciplinaNaTurma,String bi){
					
				
				   Pesquisar_SQL p = new Pesquisar_SQL();
				   ArrayList<String> turmasDoTurno= p.pesquisar_Turmas_Do_Turno(BD, turno);
				   ArrayList<String> turmasFiltradas= new ArrayList<>();
				   
				   String turno2=(String)turno.subSequence(0, 1);
					String nivel2=(String)nivel.subSequence(0, 2);
					
					
					
					String abeviaturaCurso_Ou_Disciplina=curso;
					abeviaturaCurso_Ou_Disciplina=abeviaturaCurso_Ou_Disciplina.substring(0, 3);
					
					
					
				   
				   for (String turma: turmasDoTurno) {
					   
					   if(turma.contains("Turma"+turno2+""+
					             abeviaturaCurso_Ou_Disciplina+""+nivel2)) {
						   
						   turmasFiltradas.add(turma);
					   }
				   }
				   
				   String desciplina2="";
				   String turma="";
				   sair:
				   for (String tf : turmasFiltradas) {
					
					   desciplina2= p.pesquisarUmConteudo_Numa_Linha_String(BD, tf, "Disciplinas", "Disciplinas", desciplina, 0);
				       if(desciplina.equalsIgnoreCase(desciplina2)) {
				    	   
				    	   turma=tf;
				    	   break sair;
				       }
				   }
				  
				   
				   if((turma.equalsIgnoreCase(""))||(turma==null)) {
					   
				   }else {
					   
					   Tabela_Actualizar_SQL ta = new Tabela_Actualizar_SQL();
					   ta.actualizarColuna_Qualquer_Linha(BD, turma, "Professores", 0, professor, "Disciplinas", desciplina2);
					   inserir_Disciplinas_Prof(BD, curso, professor, desciplina, nivel,turma,tempoPorDesciplinaNaTurma,bi,turno);
				   }
				   
				   
				  
			}
			
			public void inserindo_Prof_Na_Turma_Manualalmente(String BD,String nivel,
					   String curso,String turno,String desciplina,String professor,String turma,
					   int tempo,String bi ){
					
				
				
				   
				   if((turma.equalsIgnoreCase(""))||(turma==null)) {
					   
				   }else {
					   
					   Tabela_Actualizar_SQL ta = new Tabela_Actualizar_SQL();
					   //ta.actualizarColuna_Qualquer_Linha(BD, turma, "Professores", 0, professor, "Disciplinas", desciplina);
					   inserir_Disciplinas_Prof(BD, curso, professor, desciplina, nivel,turma,tempo,bi,turno);
				   }
				   
				   
				  
			}
			
			
			public String retornarBaseDeDados() {
				
				return this.BD;
			}
			
			private void inserir_Nome_Do_Documento(String BD,String documento) {

				Tabela_Actualizar_SQL ta = new Tabela_Actualizar_SQL();
				ta.alterarTabela_Escola_Financa(BD, documento, "documentos", 0, "", 0);
			}
			public void inserir_Preco_Do_Documento(String BD,String documento,int preco) {
				
				Tabela_Actualizar_SQL ta = new Tabela_Actualizar_SQL();
				ta.alterarTabela_Escola_Financa(BD, "", "preco3", 0, documento, preco);
			}
			
			public void inserir_Nome_Curso_Propinas(String BD,String curso) {

				Tabela_Actualizar_SQL ta = new Tabela_Actualizar_SQL();
				ta.alterarTabela_Escola_Financa(BD, curso, "propinas", 0, "", 0);
			}
			public void inserir_Preco_Do_Curso_Propina(String BD,String curso,int preco) {
				
				Tabela_Actualizar_SQL ta = new Tabela_Actualizar_SQL();
				ta.alterarTabela_Escola_Financa(BD, "", "preco2", 0, curso, preco);
			}
			
			private void inserir_Nome_Do_Material(String BD,String material) {

				Tabela_Actualizar_SQL ta = new Tabela_Actualizar_SQL();
				ta.alterarTabela_Escola_Financa(BD, material, "Materias", 0, "", 0);
			}
			public void inserir_Preco_Do_Material(String BD,String material,int preco) {
				
				Tabela_Actualizar_SQL ta = new Tabela_Actualizar_SQL();
				ta.alterarTabela_Escola_Financa(BD, "", "preco4", 0, material, preco);
			}
			
			public void inserir_Nome_Do_Curso_Matricula(String BD,String matricula) {

				Tabela_Actualizar_SQL ta = new Tabela_Actualizar_SQL();
				ta.alterarTabela_Escola_Financa(BD, matricula, "matricula", 0, "", 0);
			}
			public void inserir_Preco_Da_matricula_curso(String BD,String curso,int preco) {
				
				Tabela_Actualizar_SQL ta = new Tabela_Actualizar_SQL();
				ta.alterarTabela_Escola_Financa(BD, "", "preco5", 0, curso, preco);
			}
			
			public void inserir_Nome_Do_Curso_ParaConfirmacao(String BD,String matricula) {

				Tabela_Actualizar_SQL ta = new Tabela_Actualizar_SQL();
				ta.alterarTabela_Escola_Financa(BD, matricula, "confirmacao", 0, "", 0);
			}
			public void inserir_Preco_Da_Confirmacao_curso(String BD,String curso,int preco) {
				
				Tabela_Actualizar_SQL ta = new Tabela_Actualizar_SQL();
				ta.alterarTabela_Escola_Financa(BD, "", "preco7", 0, curso, preco);
			}
			
			
			
			public void inserir_SalarioDoFuncionario(String BD,String cargoDoFuncionario,int preco) {
				
				Tabela_Actualizar_SQL ta = new Tabela_Actualizar_SQL();
				ta.alterarTabela_Escola_Financa(BD, "", "preco1", 0, cargoDoFuncionario, preco);
			}
			public void inserir_Precos_Das_Faltas(String BD,String tipoDeFalta,int preco) {

				Tabela_Actualizar_SQL ta = new Tabela_Actualizar_SQL();
				ta.alterarTabela_Escola_Financa(BD, "", "preco6", 0, tipoDeFalta, preco);
			}
			
			public void inserir_Preco_Do_Estagio(String BD,int preco) {

				Tabela_Actualizar_SQL ta = new Tabela_Actualizar_SQL();
				ta.alterarTabela_Escola_Financa(BD, "", "Estagio", preco, "", 0);
			}
			public void inserir_Precos_Do_Recurso(String BD,int preco) {

				Tabela_Actualizar_SQL ta = new Tabela_Actualizar_SQL();
				ta.alterarTabela_Escola_Financa(BD, "", "Recurso", preco, "", 0);
			}
			
			public void inserir_Preco_Tempo_de_PagamentoPropina(String BD,int qDiasDePagamantoPropina) {

				Tabela_Actualizar_SQL ta = new Tabela_Actualizar_SQL();
				ta.alterarTabela_Escola_Financa(BD, "", "TempoPropina", qDiasDePagamantoPropina, "", 0);
			}
			
		
			
			
			
			
			public Minhas_Financas tesourariaPagar_Propina_FASE1(String BD,Aluno aluno) {
				
				Pesquisar_SQL p = new Pesquisar_SQL();
				
				//p.tesouraria_AlunosDadosFinanceiros(BD, turno, nivel, curso, aluno);
				
				return p.Listar_TurmasDoCurso4(BD, aluno);
			}
			
			public ArrayList<String>  tesouraria_AlunosMezesPagos(String BD,String turma,int idAluno){
				
			    Pesquisar_SQL p = new Pesquisar_SQL();
				ArrayList<String> mesesPagos= new ArrayList<>();
					mesesPagos.add("Janeiro");
				
					mesesPagos.add("Fevereiro");
				
					mesesPagos.add("Marco");
				
				
					mesesPagos.add("Abril");
				
				
					mesesPagos.add("Maio");
				
					mesesPagos.add("Junho");
				
					mesesPagos.add("Julho");
				
					mesesPagos.add("Agosto");
				
					mesesPagos.add("Setembro");
				
				
					mesesPagos.add("Outubro");
				
					mesesPagos.add("Novembro");
				
					mesesPagos.add("Dezembro");
					
					
					ArrayList<String> todosMeses= new ArrayList<>();
					int mes=0;
					
					for(String m: mesesPagos ) {
					 mes =  p.pesquisarUmConteudo_Numa_Linha_Int(BD, turma+"_financa", m, "id", "", idAluno);
					
					  if(mes>0) {
						  todosMeses.add(m);
					  }
					}
				
					int diferenca=12-todosMeses.size();
					
					if(diferenca==0) {
						
					}else {
						
						for(int i=1;i<=diferenca;i++) {
							
							todosMeses.add("");
						}
					}
				return todosMeses;
			}
			
			
			
			
			public ArrayList<String>  tesouraria_AlunosMezes_Não_Pagos(String BD,String turma,int idAluno){

				 Pesquisar_SQL p = new Pesquisar_SQL();
					ArrayList<String> mesesPagos= new ArrayList<>();
						mesesPagos.add("Janeiro");
					
						mesesPagos.add("Fevereiro");
					
						mesesPagos.add("Marco");
					
					
						mesesPagos.add("Abril");
					
					
						mesesPagos.add("Maio");
					
						mesesPagos.add("Junho");
					
						mesesPagos.add("Julho");
					
						mesesPagos.add("Agosto");
					
						mesesPagos.add("Setembro");
					
					
						mesesPagos.add("Outubro");
					
						mesesPagos.add("Novembro");
					
						mesesPagos.add("Dezembro");
						
						
						ArrayList<String> todosMeses= new ArrayList<>();
						int mes=0;
						
						
						
						for(String m: mesesPagos ) {
						 mes =  p.pesquisarUmConteudo_Numa_Linha_Int(BD, turma+"_financa", m, "id", "", idAluno);
						
						  if(mes==0) {
							  todosMeses.add(m);
							  
						  }
						}
					
						int diferenca=12-todosMeses.size();
						
						if(diferenca==0) {
							
						}else {
							
							for(int i=1;i<=diferenca;i++) {
								
								todosMeses.add("");
							}
						}
					return todosMeses;
			}
			
			
			public ArrayList<String> tesouraria_MsesQueVaiPagar(String BD,String curso,String nivel){
				
				  
				Pesquisar_SQL p = new Pesquisar_SQL();
				int precoDoCurso=0;		
				
				if(nivel.contains("10")) {
					precoDoCurso= p.pesquisarUmConteudo_Numa_Linha_Int(BD, "cursos_Niveis", "Decima", "cursos", curso, 0);	
				}else if(nivel.contains("11")) {
					precoDoCurso= p.pesquisarUmConteudo_Numa_Linha_Int(BD, "cursos_Niveis", "DecimaPrimeira", "cursos", curso, 0);	
				}else if(nivel.contains("12")) {
					precoDoCurso= p.pesquisarUmConteudo_Numa_Linha_Int(BD, "cursos_Niveis", "DecimaSegunda", "cursos", curso, 0);	
				}else if(nivel.contains("13")) {
					precoDoCurso= p.pesquisarUmConteudo_Numa_Linha_Int(BD, "cursos_Niveis", "DecimaTerceira", "cursos", curso, 0);	
				}
				
			    ArrayList<String> pagamento2= new ArrayList<>();
			    
			    
			   
			    	
			    	pagamento2.add("1 Mes -"+precoDoCurso+",00Kz");
				    pagamento2.add("2 Meses -"+precoDoCurso*2+",00Kz");
				    pagamento2.add("3 Meses -"+precoDoCurso*3+",00Kz");
				    pagamento2.add("4 Meses -"+precoDoCurso*4+",00Kz");
				    pagamento2.add("5 Meses -"+precoDoCurso*5+",00Kz");
				    pagamento2.add("6 Meses -"+precoDoCurso*6+",00Kz");
				    pagamento2.add("7 Meses -"+precoDoCurso*7+",00Kz");
				    pagamento2.add("8 Meses -"+precoDoCurso*8+",00Kz");
				    pagamento2.add("9 Meses -"+precoDoCurso*9+",00Kz");
				    pagamento2.add("10 Meses -"+precoDoCurso*10+",00Kz");
				    pagamento2.add("11 Meses -"+precoDoCurso*11+",00Kz");
				    pagamento2.add("12 Meses -"+precoDoCurso*12+",00Kz");
			    
			    
			    return pagamento2;
			    
			}
			
			
			public ArrayList<String> tesouraria_Multas(String BD,String curso,String nivel){
				
				  
				Pesquisar_SQL p = new Pesquisar_SQL();
				
                int precoDoCurso=0;		
				
				if(nivel.contains("10")) {
					precoDoCurso= p.pesquisarUmConteudo_Numa_Linha_Int(BD, "cursos_Niveis", "Decima", "cursos", curso, 0);	
				}else if(nivel.contains("11")) {
					precoDoCurso= p.pesquisarUmConteudo_Numa_Linha_Int(BD, "cursos_Niveis", "DecimaPrimeira", "cursos", curso, 0);	
				}else if(nivel.contains("12")) {
					precoDoCurso= p.pesquisarUmConteudo_Numa_Linha_Int(BD, "cursos_Niveis", "DecimaSegunda", "cursos", curso, 0);	
				}else if(nivel.contains("13")) {
					precoDoCurso= p.pesquisarUmConteudo_Numa_Linha_Int(BD, "cursos_Niveis", "DecimaTerceira", "cursos", curso, 0);	
				}
				int multa= p.pesquisarTudoEmInt(BD, "adminfinanca", "multa").get(0);
				ArrayList<String> pagamento2= new ArrayList<>();
			    
			    
			   
			    	
			    	pagamento2.add("1 Mes -"+(multa+precoDoCurso)+",00Kz");
				    pagamento2.add("2 Meses -"+((multa*2)+(precoDoCurso*2))+",00Kz");
				    pagamento2.add("3 Meses -"+((multa*3)+(precoDoCurso*3))+",00Kz");
				    pagamento2.add("4 Meses -"+((multa*4)+(precoDoCurso*4))+",00Kz");
				    pagamento2.add("5 Meses -"+((multa*5)+(precoDoCurso*5))+",00Kz");
				    pagamento2.add("6 Meses -"+((multa*6)+(precoDoCurso*6))+",00Kz");
				    pagamento2.add("7 Meses -"+((multa*7)+(precoDoCurso*7))+",00Kz");
				    pagamento2.add("8 Meses -"+((multa*8)+(precoDoCurso*8))+",00Kz");
				    pagamento2.add("9 Meses -"+((multa*9)+(precoDoCurso*9))+",00Kz");
				    pagamento2.add("10 Meses -"+((multa*10)+(precoDoCurso*10))+",00Kz");
				    pagamento2.add("11 Meses -"+((multa*11)+(precoDoCurso*11))+",00Kz");
				    pagamento2.add("12 Meses -"+((multa*12)+(precoDoCurso*12))+",00Kz");
			    
			    
			    return pagamento2;
			    
			}
			
			
			public ArrayList<String> tesouraria_MesesAnteriores_Com_Multa(ArrayList<String> 
			mesesNPagos, int mesesAPagar) {
				
				    Date dataActual= new Date();
				    SimpleDateFormat sdf= new SimpleDateFormat("dd/MM/yyyy");
				    String[] data=sdf.format(dataActual).split("/");
				    
				    Pesquisar_SQL p = new Pesquisar_SQL();
				    int tempoSemMulta= p.pesquisarUmConteudo_Numa_Linha_Int(BD, "Escola_Financa", "TempoPropina", "id", "", 1);
				    
				    
				    int mesActual=Integer.parseInt(data[1]);
				    ArrayList<String> mesesComMulta= new ArrayList<>();
				  
			

				    int contador=0;
				    sair:
				    for (String mes : mesesNPagos) {
						
				    	++contador;
				    	
				    	
				    	if(contador<=mesesAPagar) {
				    		
				    		if(contador<mesActual) {
					    		
					    		mesesComMulta.add(mes);
					    	}else if(contador==mesActual) {
					    		
					    		if(Integer.parseInt(data[0])>tempoSemMulta) {
						    		
					    			mesesComMulta.add(mes);
						    	}
					    	}
				    		
				    		if(contador==mesesAPagar) {
				    			
				    			break sair;
				    		}
				    	}
				    	
					}
				    
				    
				return mesesComMulta;
			}
			
			
			public int  tesouraria_Pagando_mesesAnteriores_Com_Multa(String BD,
					ArrayList<String> mesesAnteriores_Com_Multa,int valorDaProina) {
				
				     Pesquisar_SQL p = new Pesquisar_SQL();
				   
				    int multas= p.pesquisarUmConteudo_Numa_Linha_Int(BD, "Escola_Financa", "multa", "id", "", 1);
				    int valorDoPagamentoMultas=0;
			

				    int contador=0;
				    for (String mes : mesesAnteriores_Com_Multa) {
						
				    	++contador;
				    	multas=multas*contador;
				    	
					}
				    
				    valorDoPagamentoMultas=valorDaProina+multas;
				return valorDoPagamentoMultas;
			}
			
			
			
			
			
			
			public Pagamento tesouraria_EfectuarOPagamento(String BD,String turma,int idAluno,
					String qMesesAPagar,String curso,int idFunc,String bi_func,String nivel) {
				
				
				Pagamento aluno = new Pagamento();
				
				Tabela_Actualizar_SQL ta = new Tabela_Actualizar_SQL();
				aluno.setPagamentoEfectuado(false);
				
				String[] a = qMesesAPagar.split(" ");
				
				String mesApagar = a[0];
				System.out.println(mesApagar);
				int pagarQMeses= Integer.parseInt(mesApagar);
				
				
				Pesquisar_SQL p = new Pesquisar_SQL();
				
				
                int precoDoCurso=0;		
				
				if(nivel.contains("10")) {
					precoDoCurso= p.pesquisarUmConteudo_Numa_Linha_Int(BD, "cursos_Niveis", "Decima", "cursos", curso, 0);	
				}else if(nivel.contains("11")) {
					precoDoCurso= p.pesquisarUmConteudo_Numa_Linha_Int(BD, "cursos_Niveis", "DecimaPrimeira", "cursos", curso, 0);	
				}else if(nivel.contains("12")) {
					precoDoCurso= p.pesquisarUmConteudo_Numa_Linha_Int(BD, "cursos_Niveis", "DecimaSegunda", "cursos", curso, 0);	
				}else if(nivel.contains("13")) {
					precoDoCurso= p.pesquisarUmConteudo_Numa_Linha_Int(BD, "cursos_Niveis", "DecimaTerceira", "cursos", curso, 0);	
				}
				
				  
				String tesoureiro= p.pesquisarUmConteudo_Numa_Linha_String(BD, "tesouraria_dadospessoais", "nomes", "id", "", idFunc);
			    int tempoSemMulta= p.pesquisarUmConteudo_Numa_Linha_Int(BD, "Escola_Financa", "TempoPropina", "id", "", 1);
			    int multa= p.pesquisarTudoEmInt(BD, "adminfinanca", "multa").get(0);
			    int rendaAdmin= 0;
			    ArrayList<Integer> todasRendas = p.pesquisarTudoEmInt(BD, "adminfinanca", "renda");
			    if(todasRendas.size()>0) {
			    	
			    	rendaAdmin= todasRendas.get(0);
			    }
			    
			    String bi_admin = p.pesquisarTudoEmString(BD, "adminfinanca", "bi_admin").get(0);
			    String instituicao = p.pesquisarTudoEmString(BD, "pca_"+BD, "instituicao").get(0);
			    aluno.setInstituiçao(instituicao);
			    
			    Date d = new Date();
			    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			    
			    aluno.setData(sdf.format(d));
			    
			    int nMatricula = p.pesquisarUmConteudo_Numa_Linha_Int(BD, turma, "NProc", "id", "", idAluno);
			    String nomeDoAluno = p.pesquisarUmConteudo_Numa_Linha_String(BD, turma, "Alunos", "id", "", idAluno);
			    aluno.setAluno(nomeDoAluno);
			    aluno.setNDMatricula(""+nMatricula);
			    
			    aluno.setCurso(curso);
			    aluno.setTurma(turma);
			    aluno.setTipoDeTitulo("Propina");
			    aluno.setNomeDoTesoureiro(tesoureiro);
			    
			    
			    
			    
			    Date dataActual= new Date();
			    String[] data=sdf.format(dataActual).split("/");
			    
			    int diaActual=Integer.parseInt(data[0]);
			    int mesActual=Integer.parseInt(data[1]);
			    
			    int comparacaoMes;
			   
			    ArrayList<String> mesP = new ArrayList<>();
			    ArrayList<Integer> valorP = new ArrayList<>();
				
				ArrayList<String> mesesNPagos= new ArrayList<>();
					mesesNPagos.add("Janeiro");
					mesesNPagos.add("Fevereiro");
					mesesNPagos.add("Marco");
					mesesNPagos.add("Abril");
					mesesNPagos.add("Maio");
					mesesNPagos.add("Junho");
					mesesNPagos.add("Julho");
				
					mesesNPagos.add("Agosto");
				
					mesesNPagos.add("Setembro");
				
					mesesNPagos.add("Outubro");
				
					mesesNPagos.add("Novembro");
				
					mesesNPagos.add("Dezembro");
					
					ArrayList<String> mesesQFaltamPagar= new ArrayList<>();
					int mes=0;
					for(String c : mesesNPagos) {
						
						 mes= p.pesquisarUmConteudo_Numa_Linha_Int(BD, turma+"_financa", c, "id", "", idAluno);
						 if(mes==0) {
							 mesesQFaltamPagar.add(c);
						 }
					}
				
				
					if(mesesQFaltamPagar.size()>0) {
						
						
						sair:
						for(int i=0;i<pagarQMeses;i++) {
							
							if(mesesQFaltamPagar.get(i).equalsIgnoreCase(mesesNPagos.get(mesActual))) {
								
								// Aqui está a dizer que a Escola não usa Multas
								if(multa==0) {
									System.out.println("A Escola Não usa Multas");
									System.out.println("Pagando o mes Actual sem Multas");
									ta.actualizarColuna_QualquerLinha_Int(BD, turma+"_financa", mesesQFaltamPagar.get(i), precoDoCurso, "", idAluno);
								
									mesP.add(mesesQFaltamPagar.get(i));
									valorP.add(precoDoCurso);
									rendaAdmin=rendaAdmin+precoDoCurso;
									ta.actualizarColuna_Qualquer_Linha(BD, "adminfinanca", "renda", rendaAdmin, "", "bi_admin", bi_admin);
									
								}else {
									
									System.out.println("A Escola Usa Multas");
									
									// Pagando O mes Actual Com Multa
                                    if(diaActual> tempoSemMulta) {
                                    	
                                    	System.out.println("Pagando o mes Actual com Multas");
								        ta.actualizarColuna_QualquerLinha_Int(BD, turma+"_financa", mesesQFaltamPagar.get(i), precoDoCurso+multa, "", idAluno);
								        rendaAdmin=rendaAdmin+(precoDoCurso+multa);
								        ta.actualizarColuna_Qualquer_Linha(BD, "adminfinanca", "renda", rendaAdmin, "", "bi_admin", bi_admin);
										
								        mesP.add(mesesQFaltamPagar.get(i));
										valorP.add(precoDoCurso+multa);
                                    }else {
                                    	
                                    	System.out.println("Pagando o mes Actual sem Multas");
                                    	ta.actualizarColuna_QualquerLinha_Int(BD, turma+"_financa", mesesQFaltamPagar.get(i), precoDoCurso, "", idAluno);
                                    	rendaAdmin=rendaAdmin+precoDoCurso;
                                    	ta.actualizarColuna_Qualquer_Linha(BD, "adminfinanca", "renda", rendaAdmin, "", "bi_admin", bi_admin);
                        				
								    	// Pagando o mes Actual Sem Multa
								    }
								}
								
								
								 
							}else {
								
								
								if(multa==0) {
									System.out.println("A Escola Não usa Multas");
									System.out.println("Pagando o mes Actual sem Multas");
									ta.actualizarColuna_QualquerLinha_Int(BD, turma+"_financa", mesesQFaltamPagar.get(i), precoDoCurso, "", idAluno);
								
									rendaAdmin=rendaAdmin+precoDoCurso;
									ta.actualizarColuna_Qualquer_Linha(BD, "adminfinanca", "renda", rendaAdmin, "", "bi_admin", bi_admin);
									
									mesP.add(mesesQFaltamPagar.get(i));
									valorP.add(precoDoCurso);
									
								}else {
									
									

									System.out.println("a Escola Usa Multas");
									
									// Meses antecedentes ao mes actual 
									
									
									comparacaoMes = mesesNPagos.indexOf(mesesNPagos.get(i));
									System.out.println("Comparacao= "+comparacaoMes);
									
									if(comparacaoMes<mesActual) {
										System.out.println("Pagando o mes Antecedente com Multas");
										
										ta.actualizarColuna_QualquerLinha_Int(BD, turma+"_financa", mesesQFaltamPagar.get(i), precoDoCurso+multa, "", idAluno);
										
										rendaAdmin=rendaAdmin+(precoDoCurso+multa);
										ta.actualizarColuna_Qualquer_Linha(BD, "adminfinanca", "renda", rendaAdmin, "", "bi_admin", bi_admin);
										
										mesP.add(mesesQFaltamPagar.get(i));
										valorP.add(precoDoCurso+multa);
										
									}else if(comparacaoMes>mesActual) {
										
										System.out.println("Pagando o mes A seguir sem Multas");
										
										ta.actualizarColuna_QualquerLinha_Int(BD, turma+"_financa", mesesQFaltamPagar.get(i), precoDoCurso, "", idAluno);
										rendaAdmin=rendaAdmin+precoDoCurso;
										ta.actualizarColuna_Qualquer_Linha(BD, "adminfinanca", "renda", rendaAdmin, "", "bi_admin", bi_admin);
										
										mesP.add(mesesQFaltamPagar.get(i));
										valorP.add(precoDoCurso);
									}
									
								}
								
								
							}
							
							if(mesesQFaltamPagar.get(i).equalsIgnoreCase("Dezembro")) {
								
								break sair;
							}
						}
					
					
					System.out.println("Actualizando A Prop do Funcionarios ");
					
					
					//Actualizando a tabela de trablhos diarios na propina do Tesoureiro
					int diario= p.pesquisarUmConteudo_Numa_Linha_Int(BD, "Func_Diario", "Prop", "bi", bi_func, 0);
					++diario;

					ta.actualizarColuna_Qualquer_Linha(BD, "Func_Diario", "Prop", diario, "", "bi", bi_func);
						
					aluno.setSituacaoPagamento("");  
					aluno.setPagamentoEfectuado(true);
					
					
					
					aluno.setValoresPagos(valorP);
					aluno.setParcelasMeses(mesP);
					
					}
				
				
			
				
				
			

				return aluno;
			}
			
			
			
			public void tesouraria_Pagar_Matricula(String BD,String curso,String bi_func) {
				
				Pesquisar_SQL p = new Pesquisar_SQL();
				Tabela_Actualizar_SQL ta = new Tabela_Actualizar_SQL();
				
				int preco= p.pesquisarUmConteudo_Numa_Linha_Int(BD, "Escola_Financa", "precoMatricula", "Matricula", curso, 0);
				
				//Actualizando a tabela de de Financa do Admin na coluna Renda


				int rendaAdmin= 0;
			    ArrayList<Integer> todasRendas = p.pesquisarTudoEmInt(BD, "adminfinanca", "renda");
			    if(todasRendas.size()>0) {
			    	
			    	rendaAdmin= todasRendas.get(0);
			    }
				rendaAdmin=rendaAdmin+preco;
				
				
				String bi_admin = p.pesquisarTudoEmString(BD, "adminfinanca", "bi_admin").get(0);
				ta.actualizarColuna_Qualquer_Linha(BD, "adminfinanca", "renda", rendaAdmin, "", "bi_admin", bi_admin);
				
				
				
				//Actualizando a tabela de trablhos diarios na propina do Tesoureiro
				int diario= p.pesquisarUmConteudo_Numa_Linha_Int(BD, "Func_Diario", "MatEConf", "bi", bi_func, 0);
				++diario;
				ta.actualizarColuna_Qualquer_Linha(BD, "Func_Diario", "MatEConf", diario, "", "bi", bi_func);
			}
			
			public void tesouraria_Pagar_Confirmacao(String BD,String curso,String bi_func) {

				Pesquisar_SQL p = new Pesquisar_SQL();
				Tabela_Actualizar_SQL ta = new Tabela_Actualizar_SQL();

				int preco= p.pesquisarUmConteudo_Numa_Linha_Int(BD, "Escola_Financa", "precoConfir", "confirmacao", curso, 0);

				//Actualizando a tabela de de Financa do Admin na coluna Renda


				int rendaAdmin= 0;
			    ArrayList<Integer> todasRendas = p.pesquisarTudoEmInt(BD, "adminfinanca", "renda");
			    if(todasRendas.size()>0) {
			    	
			    	rendaAdmin= todasRendas.get(0);
			    }
				rendaAdmin=rendaAdmin+preco;
				String bi_admin = p.pesquisarTudoEmString(BD, "adminfinanca", "bi_admin").get(0);
				ta.actualizarColuna_Qualquer_Linha(BD, "adminfinanca", "renda", rendaAdmin, "", "bi_admin", bi_admin);
				



				//Actualizando a tabela de trablhos diarios na propina do Tesoureiro
				int diario= p.pesquisarUmConteudo_Numa_Linha_Int(BD, "Func_Diario", "MatEConf", "bi", bi_func, 0);
				++diario;
				ta.actualizarColuna_Qualquer_Linha(BD, "Func_Diario", "MatEConf", diario, "", "bi", bi_func);
			}
			
			
			public void tesouraria_Pagar_Documento(String BD,String documento,String bi_func) {

				Pesquisar_SQL p = new Pesquisar_SQL();
				Tabela_Actualizar_SQL ta = new Tabela_Actualizar_SQL();

				int preco= p.pesquisarUmConteudo_Numa_Linha_Int(BD, "Escola_Financa", "precoDoc", "Docs", documento, 0);

				//Actualizando a tabela de de Financa do Admin na coluna Renda


				int rendaAdmin= 0;
			    ArrayList<Integer> todasRendas = p.pesquisarTudoEmInt(BD, "adminfinanca", "renda");
			    if(todasRendas.size()>0) {
			    	
			    	rendaAdmin= todasRendas.get(0);
			    }
			    
				rendaAdmin=rendaAdmin+preco;
				String bi_admin = p.pesquisarTudoEmString(BD, "adminfinanca", "bi_admin").get(0);
				ta.actualizarColuna_Qualquer_Linha(BD, "adminfinanca", "renda", rendaAdmin, "", "bi_admin", bi_admin);
				



				//Actualizando a tabela de trablhos diarios na propina do Tesoureiro
				int diario= p.pesquisarUmConteudo_Numa_Linha_Int(BD, "Func_Diario", "servicos", "bi", bi_func, 0);
				++diario;

				ta.actualizarColuna_Qualquer_Linha(BD, "Func_Diario", "servicos", diario, "", "bi", bi_func);
			}
			
			public void tesouraria_Pagar_Falta(String BD,String tipoDaFalta,String bi_func) {

				Pesquisar_SQL p = new Pesquisar_SQL();
				Tabela_Actualizar_SQL ta = new Tabela_Actualizar_SQL();

				int preco= p.pesquisarUmConteudo_Numa_Linha_Int(BD, "Escola_Financa", "precoFalta", "Faltas", tipoDaFalta, 0);

				//Actualizando a tabela de de Financa do Admin na coluna Renda


				int rendaAdmin= 0;
			    ArrayList<Integer> todasRendas = p.pesquisarTudoEmInt(BD, "adminfinanca", "renda");
			    if(todasRendas.size()>0) {
			    	
			    	rendaAdmin= todasRendas.get(0);
			    }
				rendaAdmin=rendaAdmin+preco;
				String bi_admin = p.pesquisarTudoEmString(BD, "adminfinanca", "bi_admin").get(0);
				ta.actualizarColuna_Qualquer_Linha(BD, "adminfinanca", "renda", rendaAdmin, "", "bi_admin", bi_admin);
				



				//Actualizando a tabela de trablhos diarios na propina do Tesoureiro
				int diario= p.pesquisarUmConteudo_Numa_Linha_Int(BD, "Func_Diario", "servicos", "bi", bi_func, 0);
				++diario;
				ta.actualizarColuna_Qualquer_Linha(BD, "Func_Diario", "servicos", diario, "", "bi", bi_func);

			}
			
			public void tesouraria_Pagar_Materias(String BD,String material,String bi_func) {

				Pesquisar_SQL p = new Pesquisar_SQL();
				Tabela_Actualizar_SQL ta = new Tabela_Actualizar_SQL();

				int preco= p.pesquisarUmConteudo_Numa_Linha_Int(BD, "Escola_Financa", "precoMat", "Mat", material, 0);

				//Actualizando a tabela de de Financa do Admin na coluna Renda


				int rendaAdmin= 0;
			    ArrayList<Integer> todasRendas = p.pesquisarTudoEmInt(BD, "adminfinanca", "renda");
			    if(todasRendas.size()>0) {
			    	
			    	rendaAdmin= todasRendas.get(0);
			    }
				rendaAdmin=rendaAdmin+preco;
				String bi_admin = p.pesquisarTudoEmString(BD, "adminfinanca", "bi_admin").get(0);
				ta.actualizarColuna_Qualquer_Linha(BD, "adminfinanca", "renda", rendaAdmin, "", "bi_admin", bi_admin);
				



				//Actualizando a tabela de trablhos diarios na propina do Tesoureiro
				int diario= p.pesquisarUmConteudo_Numa_Linha_Int(BD, "Func_Diario", "servicos", "bi", bi_func, 0);
				++diario;
				ta.actualizarColuna_Qualquer_Linha(BD, "Func_Diario", "servicos", diario, "", "bi", bi_func);

			}
			
			public void tesouraria_Pagar_Estagio(String BD,String bi_func) {

				Pesquisar_SQL p = new Pesquisar_SQL();
				Tabela_Actualizar_SQL ta = new Tabela_Actualizar_SQL();

				int preco= p.pesquisarUmConteudo_Numa_Linha_Int(BD, "Escola_Financa", "Estagio", "id", "", 1);

				
				if(preco>0) {
					
					
					//Actualizando a tabela de de Financa do Admin na coluna Renda


					int rendaAdmin= 0;
				    ArrayList<Integer> todasRendas = p.pesquisarTudoEmInt(BD, "adminfinanca", "renda");
				    if(todasRendas.size()>0) {
				    	
				    	rendaAdmin= todasRendas.get(0);
				    }
				    
				rendaAdmin=rendaAdmin+preco;
				String bi_admin = p.pesquisarTudoEmString(BD, "adminfinanca", "bi_admin").get(0);
				ta.actualizarColuna_Qualquer_Linha(BD, "adminfinanca", "renda", rendaAdmin, "", "bi_admin", bi_admin);
				



				//Actualizando a tabela de trablhos diarios na propina do Tesoureiro
				int diario= p.pesquisarUmConteudo_Numa_Linha_Int(BD, "Func_Diario", "servicos", "bi", bi_func, 0);
				++diario;
				ta.actualizarColuna_Qualquer_Linha(BD, "Func_Diario", "servicos", diario, "", "bi", bi_func);

				
				}
				
				
			
				}
			
			public void tesouraria_Pagar_Recurso(String BD,String bi_func) {

				Pesquisar_SQL p = new Pesquisar_SQL();
				Tabela_Actualizar_SQL ta = new Tabela_Actualizar_SQL();

				int preco= p.pesquisarUmConteudo_Numa_Linha_Int(BD, "Escola_Financa", "Recurso", "id", "", 1);

				//Actualizando a tabela de de Financa do Admin na coluna Renda


				int rendaAdmin= 0;
			    ArrayList<Integer> todasRendas = p.pesquisarTudoEmInt(BD, "adminfinanca", "renda");
			    if(todasRendas.size()>0) {
			    	
			    	rendaAdmin= todasRendas.get(0);
			    }
				rendaAdmin=rendaAdmin+preco;
				String bi_admin = p.pesquisarTudoEmString(BD, "adminfinanca", "bi_admin").get(0);
				ta.actualizarColuna_Qualquer_Linha(BD, "adminfinanca", "renda", rendaAdmin, "", "bi_admin", bi_admin);
				



				//Actualizando a tabela de trablhos diarios na propina do Tesoureiro
				int diario= p.pesquisarUmConteudo_Numa_Linha_Int(BD, "Func_Diario", "servicos", "bi", bi_func, 0);
				++diario;
				ta.actualizarColuna_Qualquer_Linha(BD, "Func_Diario", "servicos", diario, "", "bi", bi_func);

			}
			
			public ArrayList<Financa> listar_Salario_Dos_Funcionarios(String BD){
				
				ArrayList<String> funcionarios= new ArrayList<>();
				
				   funcionarios.add("Professor");
				   funcionarios.add("Secretaria");
				   funcionarios.add("Tesouraria");
				   funcionarios.add("Coord");
				   funcionarios.add("DG");
				   funcionarios.add("DA");
				   funcionarios.add("DP");
				   funcionarios.add("Limpeza");
				   funcionarios.add("Seguranca");
				   funcionarios.add("Cozinheira");
				   
				   ArrayList<Financa> salarioDeTodos= new ArrayList<>();
				   
				   Pesquisar_SQL p = new Pesquisar_SQL();
				   ArrayList<Integer> salarios= p.pesquisarTudoEmInt(BD, "Escola_Financa", "PrecoFunc");
				   
				   
				   for(int i=0;i<funcionarios.size();i++) {
					   
					   
					   Financa financa= new Financa();
					   
					   financa.setConteudo(funcionarios.get(i));
					   financa.setValor(salarios.get(i));
					   
				   }
				   return salarioDeTodos;
			}
			
			
                public ArrayList<Financa> listar_Propinas_Dos_Cursos(String BD){
				
	
	            Pesquisar_SQL p = new Pesquisar_SQL();
	            
	            ArrayList<String> funcionarios= p.pesquisarTudoEmString(BD, "Escola_Financa", "Propinas");
	            ArrayList<Integer> salarios= p.pesquisarTudoEmInt(BD, "Escola_Financa", "precoProp");
				   
				 ArrayList<Financa> salarioDeTodos= new ArrayList<>();
				   
				  
				   
				   
				   
				   for(int i=0;i<funcionarios.size();i++) {
					   
					   
					   Financa financa= new Financa();
					   
					   financa.setConteudo(funcionarios.get(i));
					   financa.setValor(salarios.get(i));
					   
				   }
				   return salarioDeTodos;
			}


public ArrayList<Financa> listar_Documetos_Da_Escola(String BD){
	
	
    Pesquisar_SQL p = new Pesquisar_SQL();
    
    ArrayList<String> funcionarios= p.pesquisarTudoEmString(BD, "Escola_Financa", "Docs");
    ArrayList<Integer> salarios= p.pesquisarTudoEmInt(BD, "Escola_Financa", "precoDoc");
	   
	 ArrayList<Financa> salarioDeTodos= new ArrayList<>();
	   
	  
	   
	   
	   
	   for(int i=0;i<funcionarios.size();i++) {
		   
		   
		   Financa financa= new Financa();
		   
		   financa.setConteudo(funcionarios.get(i));
		   financa.setValor(salarios.get(i));
		   
	   }
	   return salarioDeTodos;
}


public ArrayList<Financa> listar_Materias_Da_Escola(String BD){
	
	
    Pesquisar_SQL p = new Pesquisar_SQL();
    
    ArrayList<String> funcionarios= p.pesquisarTudoEmString(BD, "Escola_Financa", "Mat");
    ArrayList<Integer> salarios= p.pesquisarTudoEmInt(BD, "Escola_Financa", "precoMat");
	   
	 ArrayList<Financa> salarioDeTodos= new ArrayList<>();
	   
	  
	   
	   
	   
	   for(int i=0;i<funcionarios.size();i++) {
		   
		   
		   Financa financa= new Financa();
		   
		   financa.setConteudo(funcionarios.get(i));
		   financa.setValor(salarios.get(i));
		   
	   }
	   return salarioDeTodos;
}


public ArrayList<Financa> listar_Valor_Das_Matriculas(String BD){
	
	
    Pesquisar_SQL p = new Pesquisar_SQL();
    
    ArrayList<String> funcionarios= p.pesquisarTudoEmString(BD, "Escola_Financa", "Matricula");
    ArrayList<Integer> salarios= p.pesquisarTudoEmInt(BD, "Escola_Financa", "precoMatricula");
	   
	 ArrayList<Financa> salarioDeTodos= new ArrayList<>();
	   
	  
	   
	   
	   
	   for(int i=0;i<funcionarios.size();i++) {
		   
		   
		   Financa financa= new Financa();
		   
		   financa.setConteudo(funcionarios.get(i));
		   financa.setValor(salarios.get(i));
		   
	   }
	   return salarioDeTodos;
}


public ArrayList<Financa> listar_Valor_Das_Faltas(String BD){
	
	
    Pesquisar_SQL p = new Pesquisar_SQL();
    
    ArrayList<String> funcionarios= p.pesquisarTudoEmString(BD, "Escola_Financa", "Faltas");
    ArrayList<Integer> salarios= p.pesquisarTudoEmInt(BD, "Escola_Financa", "precoFalta");
	   
	 ArrayList<Financa> salarioDeTodos= new ArrayList<>();
	   
	  
	   
	   
	   
	   for(int i=0;i<funcionarios.size();i++) {
		   
		   
		   Financa financa= new Financa();
		   
		   financa.setConteudo(funcionarios.get(i));
		   financa.setValor(salarios.get(i));
		   
	   }
	   return salarioDeTodos;
}

public ArrayList<Financa> listar_Valor_Da_Confirmacao(String BD){
	
	
    Pesquisar_SQL p = new Pesquisar_SQL();
    
    ArrayList<String> funcionarios= p.pesquisarTudoEmString(BD, "Escola_Financa", "confirmacao");
    ArrayList<Integer> salarios= p.pesquisarTudoEmInt(BD, "Escola_Financa", "precoConfir");
	   
	 ArrayList<Financa> salarioDeTodos= new ArrayList<>();
	   
	  
	   
	   
	   
	   for(int i=0;i<funcionarios.size();i++) {
		   
		   
		   Financa financa= new Financa();
		   
		   financa.setConteudo(funcionarios.get(i));
		   financa.setValor(salarios.get(i));
		   
	   }
	   return salarioDeTodos;
}


public int valorDoEstagio(String BD){
	
	
	int preco=0;
    Pesquisar_SQL p = new Pesquisar_SQL();
    
	   
    preco= p.pesquisarUmConteudo_Numa_Linha_Int(BD, "Escola_Financa", "Estagio", "id", "", 1);
	
	   return preco;
}

public int valorDoRecurso(String BD){
	
	
	int preco=0;
    Pesquisar_SQL p = new Pesquisar_SQL();
    	   
    preco= p.pesquisarUmConteudo_Numa_Linha_Int(BD, "Escola_Financa", "Recurso", "id", "", 1);
	
	   return preco;
}

 public void sistema_Mudar_A_Senha(String BD,String senha,
		 String tabela_Do_Integrante,int idDoIntegrante,String bi,
		 boolean que_E_O_Integrante) {
	 
	 Tabela_Actualizar_SQL ta = new Tabela_Actualizar_SQL();
	 if(idDoIntegrante==0) {
		 
		 if(que_E_O_Integrante) {
			 
			 ta.actualizarColuna_Qualquer_Linha(BD, "pca_"+tabela_Do_Integrante, "acesso1", 0, senha, "bi", bi);	 
		 }else {
			 ta.actualizarColuna_Qualquer_Linha(BD, tabela_Do_Integrante+"_Acesso", "acesso1", 0, senha, "bi", bi);	 
		 }
		
		 
	 }else {
		 ta.actualizarColuna_QualquerLinha_Int(BD, tabela_Do_Integrante+"_Acesso", "acesso1", 0, senha, idDoIntegrante);
		 
	 }
	 
 }
 
 public void sistema_Recuperar_A_Senha(String BD,String senha,
		 String tabela_Do_Integrante,int idDoIntegrante,String bi,
		 boolean que_E_O_Integrante) {
	 
	 Tabela_Actualizar_SQL ta = new Tabela_Actualizar_SQL();
	 if(idDoIntegrante==0) {
		 
		 if(que_E_O_Integrante) {
			 
			 ta.actualizarColuna_Qualquer_Linha(BD, "pca_"+tabela_Do_Integrante, "acesso2", 0, senha, "bi", bi);	 
		 }else {
			 ta.actualizarColuna_Qualquer_Linha(BD, tabela_Do_Integrante+"_Acesso", "acesso2", 0, senha, "bi", bi);	 
		 }
		
		 
	 }else {
		 ta.actualizarColuna_QualquerLinha_Int(BD, tabela_Do_Integrante+"_Acesso", "acesso2", 0, senha, idDoIntegrante);
		 
	 }
	 
 }
			
	
 
  public boolean secretaria_Matricula(String BD, Aluno aluno) {
	  
	  boolean retorno=false;
	  inserirAluno(BD, aluno);
	  
	  retorno=true;
	  return retorno;
  }
  
  public boolean secretaria_Estagio(String BD, String bi,String nome) {
	  
	  boolean retorno=false;
	  inserirEstagio(BD, nome, bi);
	  
	  retorno=true;
	  return retorno;
  }
  
 public boolean secretaria_AgendarDocumentos(String BD, String documento,String DataEntrega) {
	  
	  boolean retorno=false;
	  inserir_AgendaDeDoc(BD, documento, DataEntrega);
	  
	  retorno=true;
	  return retorno;
  }
 
 public ArrayList<Estagio> secretaria_Listar_Estagio(String BD) {
	  
	 
	  Pesquisar_SQL p = new Pesquisar_SQL();
	  
	  ArrayList<Integer> ids= p.pesquisarTudoEmInt(BD, "Estagios", "id");
	  ArrayList<String> nomes= p.pesquisarTudoEmString(BD, "Estagios", "Nome");
	  ArrayList<String> bis= p.pesquisarTudoEmString(BD, "Estagios", "bi");
	  
	  ArrayList<Estagio> listarEstagio= new ArrayList<>();
	  
	  for(int i=0;i<ids.size();i++) {
		  
		  
		  Estagio estagio = new Estagio();
		  
		  estagio.setId(ids.get(i));
		  estagio.setBi(bis.get(i));
		  estagio.setNome(nomes.get(i));
		  
		  listarEstagio.add(estagio);
	  }
	  
	  
	  return listarEstagio;
 }
 
 
 public ArrayList<Agenda> secretaria_Listar_AgendaDeDoC(String BD) {
	  
	 
	  Pesquisar_SQL p = new Pesquisar_SQL();
	  
	  ArrayList<Integer> ids= p.pesquisarTudoEmInt(BD, "AgendaDeDoc", "id");
	  ArrayList<String> docs= p.pesquisarTudoEmString(BD, "AgendaDeDoc", "Documento");
	  ArrayList<String> agendaData= p.pesquisarTudoEmString(BD, "AgendaDeDoc", "Data_Agenda");
	  ArrayList<String> entregaData= p.pesquisarTudoEmString(BD, "AgendaDeDoc", "Data_Entrega");
	  
	  ArrayList<Agenda> listarAgendas= new ArrayList<>();
	  
	  for(int i=0;i<ids.size();i++) {
		  
		  
		  Agenda agenda= new Agenda();
		  
		  agenda.setId(ids.get(i));
		  agenda.setDocumento(docs.get(i));
		  agenda.setDataEmissao(agendaData.get(i));
		  agenda.setDataEntrega2(entregaData.get(i));
		  
		
		  
		  listarAgendas.add(agenda);
	  }
	  
	  
	  return listarAgendas;
}
 
 
 
 public boolean secretaria_Imprimir_Boletim_Do_Aluno(String BD,String turma,int idAluno) {
	  
	 
	  boolean retorno=false;
	  Pesquisar_SQL p = new Pesquisar_SQL();
	  
	  
	  // Nome do Aluno
	  String aluno= p.pesquisarUmConteudo_Numa_Linha_String(BD, turma, "Alunos", "id", "", idAluno);
	  //Disciplinas Da Turma
	  ArrayList<String> desciplinas= p.pesquisarTudoEmString(BD, turma, "Disciplinas");
	  
	  
	  Date data = new Date();
	  SimpleDateFormat sdf= new SimpleDateFormat("dd/MM/yyyy");
	  int mes= Integer.parseInt(sdf.format(data).split("/")[1]);
	  Tabela_Actualizar_SQL ta = new Tabela_Actualizar_SQL();
	  
	  
	  ArrayList<String> meses= new ArrayList<>();
	  
	  meses.add("Janeiro");
	  meses.add("Fevereiro");
	  meses.add("Marco");
	  meses.add("Abril");
	  meses.add("Maio");
	  meses.add("Junho");
	  meses.add("Julho");
	  meses.add("Agosto");
	  meses.add("Setembro");
	  meses.add("Outubro");
	  meses.add("Novembro");
	  meses.add("Dezembro");
	  
	  int situacaoDoMes= p.pesquisarUmConteudo_Numa_Linha_Int(BD, turma+"_Media", meses.get(mes), "id", "", idAluno);
	  
      if(situacaoDoMes!=0) {
    	  
    	  // Notas Do Aluno em cada Desciplina
    	  ArrayList<Integer> notas= new ArrayList<>();
    	  
    	  int nota;
    	  for (String desciplina : desciplinas) {
    		
    		  
    		  nota= p.pesquisarUmConteudo_Numa_Linha_Int(BD, turma+"_Media", ta.tirarCaracteres_RetornandoA_A_Lista(desciplina).get(2), "id", "", idAluno);
    		  notas.add(nota);
    	  }
    	  
    	  
    	  // Aqui vai A geracao do Relatorio !!!
    	  
    	  retorno=true;
      }
	  
	  
	  
	  return retorno;
}

 
 public boolean secretaria_Confirmacao_Do_Aluno(String BD,String biAluno, String curso,
		 String nivel,String turno) {
	  
	 
	  Pesquisar_SQL p = new Pesquisar_SQL();
	  
	  
	  ArrayList<String> turmasDoCurso = p.Listar_TurmasDoCurso(BD, turno, nivel, curso);
	  int id=0;
	  boolean estaConfirmado=false;
	  
	  if(turmasDoCurso!=null) {
		  
		  
		  sair:
			  for (String turma : turmasDoCurso) {
				
				  id= p.pesquisarUmConteudo_Numa_Linha_Int(BD, turma+"_DadosPessoais", "id", "bi", biAluno, 0);
			      
				  if(id!=0) {
					  
					  String situacao= p.pesquisarUmConteudo_Numa_Linha_String(BD, turma+"_Media", "Situacao", "id", "",id);
					  
					  if((situacao.equalsIgnoreCase("Aprovado"))||((situacao.equalsIgnoreCase("Aprovada")))){
						  
						    ArrayList<String> niveisDaEscola=p.pesquisarTudoEmString(BD, 
						    		"infoescola", "niveis");
						    
						   
						    
							String nivel2="";
							
							int contador=0;
							
						    for (String niv : niveisDaEscola) {
								
						    	if(niv.equalsIgnoreCase(nivel2)) {
						    		nivel2= niveisDaEscola.get(contador+1);
						    				
						    	   break sair;
						    	}
						    	++contador;
							}
                            
						    EliminarLinha_SQL e = new EliminarLinha_SQL();
						    
						    Aluno aluno = p.pesquisar_Dados_Pessoais_Dum_Aluno(BD, turma+"_DadosPessoais", biAluno);
							aluno.setNivel(nivel);
							aluno.setTurno(turno);
							aluno.setCurso(curso);
						    
						    e.EliminarLinha(BD, turma, "id", id, "",false);
						    e.EliminarLinha(BD, turma+"_DadosPessoais", "id", id, "",false);
						    e.EliminarLinha(BD, turma+"_Acesso", "id", id, "",false);
						    e.EliminarLinha(BD, turma+"_Financa", "id", id, "",false);
						    e.EliminarLinha(BD, turma+"_Avaliacao", "id", id, "",false);
						    e.EliminarLinha(BD, turma+"_Prova", "id", id, "",false);
						    e.EliminarLinha(BD, turma+"_Media", "id", id, "",false);
							
						    
							inserirAluno(BD, aluno);
							
							estaConfirmado=true;
					  }
					  break sair;
				  }
			  }
	  }
	  
	  return estaConfirmado;
}
 
 
 
 
 public boolean secretaria_Tirar_Alunos_UltimosNiveis(String BD,Aluno aluno, String curso,
		 String turno) {
	  
	 boolean estaConfirmado=false;
	 
	  Pesquisar_SQL p = new Pesquisar_SQL();
	  
	  
	  ArrayList<String> niveisDaEscola=p.pesquisarTudoEmString(BD, 
	    		"infoescola", "niveis");
	  ArrayList<String> cursosDaEscola=p.pesquisarTudoEmString(BD, 
	    		"cursos", "nome");
	  
	  ArrayList<String> turnos= new ArrayList<>();
	  turnos.add("manha");
	  turnos.add("tarde");
	  turnos.add("noite");
	  
	  for (String turno2 : turnos) {
		
		 
		  for (String nivel : niveisDaEscola) {


			  for (String curso2 : cursosDaEscola) {
					
				  
				  ArrayList<String> turmasDoCurso = p.Listar_TurmasDoCurso(BD, turno2, nivel, curso2);
				  ArrayList<Integer> id_Masc= new ArrayList<>();
				  ArrayList<Integer> id_Fem= new ArrayList<>();
				  
				  
				  if(turmasDoCurso!=null) {
					  
					  
						  for (String turma : turmasDoCurso) {
							
							  id_Masc=p.pesquisarTudoEmInt_Restrito(BD, turma+"_Media", "id", "Situacao", "Aprovado", 0);
							  id_Fem=p.pesquisarTudoEmInt_Restrito(BD, turma+"_Media", "id", "Situacao", "Aprovada", 0);
						      
							  
							  for (Integer id : id_Masc) {
								  
								  EliminarLinha_SQL e = new EliminarLinha_SQL();
								    
								    e.EliminarLinha(BD, turma, "id", id, "",false);
								    e.EliminarLinha(BD, turma+"_DadosPessoais", "id", id, "",false);
								    e.EliminarLinha(BD, turma+"_Acesso", "id", id, "",false);
								    e.EliminarLinha(BD, turma+"_Financa", "id", id, "",false);
								    e.EliminarLinha(BD, turma+"_Avaliacao", "id", id, "",false);
								    e.EliminarLinha(BD, turma+"_Prova", "id", id, "",false);
								    e.EliminarLinha(BD, turma+"_Media", "id", id, "",false);
							   }
							  
							  for (Integer id : id_Fem) {

								  EliminarLinha_SQL e = new EliminarLinha_SQL();

								  e.EliminarLinha(BD, turma, "id", id, "",false);
								  e.EliminarLinha(BD, turma+"_DadosPessoais", "id", id, "",false);
								  e.EliminarLinha(BD, turma+"_Acesso", "id", id, "",false);
								  e.EliminarLinha(BD, turma+"_Financa", "id", id, "",false);
								  e.EliminarLinha(BD, turma+"_Avaliacao", "id", id, "",false);
								  e.EliminarLinha(BD, turma+"_Prova", "id", id, "",false);
								  e.EliminarLinha(BD, turma+"_Media", "id", id, "",false);
							  }
							
						  }
				  }
				  
				  
				  turmasDoCurso=null;
				}


		  }
		  
		 
		  
	}
	  estaConfirmado=true;
	  
	  return estaConfirmado;
}
  
 public ArrayList<Turma> professor_Listar_Turmas(String BD, String bi){
	 
	 
	 Pesquisar_SQL p = new Pesquisar_SQL();
	 ArrayList<String> turmasDoProfessor= p.pesquisarTudoEmString_Restrito(BD, "Disciplinas_Dos_Profs", "turma", "bi", bi, 0);
	 ArrayList<String> profNoCurso= p.pesquisarTudoEmString_Restrito(BD, "Disciplinas_Dos_Profs", "curso", "bi", bi, 0);
     
	 ArrayList<Turma> turmasDoProf= new ArrayList<>();
	 
	 int nDAlunos;
	 for (int i = 0; i < turmasDoProfessor.size();i++) {
		
		 nDAlunos=p.pesquisarUmConteudo_Numa_Linha_Int(BD, turmasDoProfessor.get(i), "NDeAlunos", "id", "", 1);
	     
		 Turma turma= new Turma();
		 
		 turma.setTurma(turmasDoProfessor.get(i));
		 turma.setCurso(profNoCurso.get(i));
		 turma.setnAlunos(nDAlunos);
		 
		 turmasDoProf.add(turma);
	 }
	 
	 return turmasDoProf;

 }
 
 
 public ArrayList<Aluno> professor_Listar_AlunosPor_Turma(String BD, String turma){
	 
	 
	 Pesquisar_SQL p = new Pesquisar_SQL();
	
	 ArrayList<Aluno> alunosDasTurma= p.pesquisar_Alunos_De_Uma_Tuma(BD, turma);
	 
	 return alunosDasTurma;

 }
 
 private void professor_Disponiblizar_Material(String BD, String turma){
	 
	 
	 // Aqui vai a Logica Para Disponibilizar Material Escolar!
	 

 }
 
 
 public boolean professor_Disponiblizar_Material(String BD,ArrayList<String> turmas){
	 
	 
	 for (String turma : turmas) {
		
		// ADisponibilizando Materias em varias Turmas do Professor
		 professor_Disponiblizar_Material(BD, turma);
	}
	 
	 
	 
	 return false;

 }
 
 
public ArrayList<String> professor_Listar_Disciplinas_Na_Turma(String BD, String turma){
	 
	 
	 Pesquisar_SQL p = new Pesquisar_SQL();
	 ArrayList<String> turmasDoProfessor= p.pesquisarTudoEmString_Restrito(BD, "Disciplinas_Dos_Profs", "desciplina", "turma", turma, 0);
	
	 return turmasDoProfessor;

 }

public boolean professor_Lancar_Avaliacao(String BD, String turma,String desciplina,
		ArrayList<Integer> notasDosAlunos,String curso){
	 
	boolean lancadas=false;
	Tabela_Actualizar_SQL ta = new Tabela_Actualizar_SQL();
	
		lancadas=true;
		String trimestre= retornarTrimestre(BD);
		
		String desc2= ta.tirarCaracteres(desciplina);
		for(int i=0;i<notasDosAlunos.size();i++) {
			
			ta.actualizarColuna_QualquerLinha_String(BD, turma+"_Avaliacao", "Trimestre", trimestre, 1);
			ta.actualizarColuna_QualquerLinha_Int(BD, turma+"_Avaliacao", desc2, notasDosAlunos.get(i), "", i+1);
			
		}
		
		
	
	
	return lancadas;

} 


public boolean professor_Lancar_Prova(String BD, String turma,String desciplina,
		ArrayList<Integer> notasDosAlunos,String curso){
	
	Pesquisar_SQL p = new Pesquisar_SQL();
	 
	int nota1;
	int nota2;
	float media;
	float media_Antiga;
	
	boolean lancadas=false;
	Tabela_Actualizar_SQL ta = new Tabela_Actualizar_SQL();
	
		
		
		String trimestre= retornarTrimestre(BD);
		for(int i=0;i<notasDosAlunos.size();i++) {
			
			
			nota1=p.pesquisarUmConteudo_Numa_Linha_Int(BD, turma+"_Avaliacao", desciplina, "id", "", i+1);
			media_Antiga= p.pesquisarUmConteudo_Numa_Linha_Int(BD, turma+"_Media", desciplina, "id", "", i+1);
			
			nota2=notasDosAlunos.get(i);
			media= (nota1+nota2)/2;
			media=media+media_Antiga;
			
			ta.actualizarColuna_QualquerLinha_String(BD, turma+"_Prova", "Trimestre", trimestre, 1);
			ta.actualizarColuna_QualquerLinha_Int(BD, turma+"_Prova", desciplina, notasDosAlunos.get(i), "", i+1);
			
			
			
			
			
			ta.actualizarColuna_QualquerLinha_String(BD, turma+"_Media", "Trimestre", trimestre, 1);
			ta.actualizarColuna_QualquerLinha_Float(BD, turma+"_Media", desciplina, media, "", i+1);
		  
			lancadas=true;
		}
		
		
	
	
	return lancadas;

}




public boolean professor_Lancar_ExamesFinal(String BD, String turma,String desciplina,
		ArrayList<Integer> notasDosAlunos,String curso){
	
	Pesquisar_SQL p = new Pesquisar_SQL();
	 

	float media;
	float media_Antiga;
	int media2;
	
	boolean lancadas=false;
	Tabela_Actualizar_SQL ta = new Tabela_Actualizar_SQL();
	
		
		
		String trimestre= retornarTrimestre(BD);
		for(int i=0;i<notasDosAlunos.size();i++) {
			
			
			
			media_Antiga= p.pesquisarUmConteudo_Numa_Linha_Int(BD, turma+"_Media", desciplina, "id", "", i+1);
			media_Antiga= media_Antiga/3;
			media= (media_Antiga+notasDosAlunos.get(i))/2;
			
			
			media2=(int) media;
			
			
			ta.actualizarColuna_QualquerLinha_String(BD, turma+"_Media", "Trimestre", trimestre, 1);
			ta.actualizarColuna_QualquerLinha_Float(BD, turma+"_Media", desciplina, media2, "", i+1);
			
			situacaoDoAluno(BD, turma, desciplina, media, trimestre, i+1,curso);
		
			lancadas=true;
		}
		
		
		
	
	return lancadas;

}


public boolean pode_Lancar_Nota_Ou_Prova(String BD, String turma,String desciplina,
		ArrayList<Integer> notasDosAlunos,String curso) {
	
	boolean estaTrancado=false;
	Pesquisar_SQL p = new Pesquisar_SQL();
	
	Tabela_Actualizar_SQL ta = new Tabela_Actualizar_SQL();
	String desc = ta.tirarCaracteres(desciplina);
	
	String tracado= p.pesquisarUmConteudo_Numa_Linha_String(BD, "cursos", "trancar", "nome", curso, 0);
	
	if(tracado.equalsIgnoreCase("A")) {
		estaTrancado=true;
		
		professor_Lancar_Avaliacao(BD, turma, desc, notasDosAlunos, curso);
	}else if(tracado.equalsIgnoreCase("P")) {
		estaTrancado=true;
		
		professor_Lancar_Prova(BD, turma, desc, notasDosAlunos, curso);
	}else if(tracado.equalsIgnoreCase("E")) {
		estaTrancado=true;
		
		professor_Lancar_ExamesFinal(BD, turma, desc, notasDosAlunos, curso);
	}
	return estaTrancado;
}






	
	
   public ArrayList<String> listar_Provas(){
	   
	   ArrayList<String> provas = new ArrayList<>();
		provas.add("Prova 1");
		provas.add("Avaliação 1");
		provas.add("Prova 2");
		provas.add("Avaliação 2");
		provas.add("Prova 3");
		provas.add("Avaliação 3");
		provas.add("Recurso 1");
		
		return provas;
   }
   
   
   
 private void definir_Data_De_Prova(String BD,String prova,String data){
	   
	   ArrayList<String> provas = new ArrayList<>();
	   Tabela_Actualizar_SQL ta =new Tabela_Actualizar_SQL();
	   
		provas.add("Prova 1");
		provas.add("Avaliação 1");
		provas.add("Prova 2");
		provas.add("Avaliação 2");
		provas.add("Prova 3");
		provas.add("Avaliação 3");
		provas.add("Recurso 1");
		
		
		if(prova.equalsIgnoreCase("Recurso")) {
			
			
		    ta.actualizarColuna_QualquerLinha_String(BD, "infoescola2", "Recurso", prova, 1);
		}else {
			
			sair:
				for (String p : provas) {
					
					if(p.equalsIgnoreCase(prova)) {
						
						ta.actualizarColuna_Qualquer_Linha(BD, "infoescola2", "Datas", 0, data, "Conteudos", p);
						break sair;
					}
				}
				
		}
		
		
   }
 
 
 public boolean definir_Datas_De_Prova(String BD,ArrayList<String>  provas,ArrayList<String>  datas){
	   
	 boolean datadDefinidas=false;
	 for (int i = 0; i < provas.size(); i++) {
		
		 definir_Data_De_Prova(BD, provas.get(i), datas.get(i));
	}
	  datadDefinidas=true;
	  
	  return datadDefinidas;
		
 }
 
 
 
 // Aqui Tem a ideia de Saber Se o Aluno Aprovou ou não
 // Esta a ver se a nota da disciplina actual é maioor que 9
 // e so vai aprovar se tiver nota posiva na disciplina não chave
 // Vai Reprovar se A disciplina for do ultimo ano da Turma e ele(a)
 // Tiver Negativa ou se essa desciplina não for do ultimo ano, mas for chave 
 // E Tambem tiver negativa, 
 
 
 // É importante guardar as informações Da Turma No Servidor, Caso o aluno 
 //Aprovou nas desciplinas não chaves com negativas para que o valor do ano corrente do Nivel
 // Que Passou de Classe Seja suficinte para eliminar a desciplina,
 // No Caso Se Tiver 8 na 10 Classe em matematica,Se Essa Matematica na 11 Classe
 // for ou não Eliminatoria, o aluno vai Precisar de 12 para completar o 8 do ano anterior
 // No Caso Ficando 8 + 12 e Fechando com Positiva
 
 
 
 
 public void situacaoDoAluno(String BD,String turma,String desciplina,
		 float media,String trimestre,int id,String curso){
	 
	 Tabela_Actualizar_SQL ta = new Tabela_Actualizar_SQL();
	 Pesquisar_SQL p = new  Pesquisar_SQL();
	 String situacao1="";
	 String situacao2="";
	 
	 ArrayList<String> desciplinas =  p.pesquisarTudoEmString(BD, "cursos_disciplinas", curso);
	 ArrayList<String> desciplinasF = new ArrayList<>();
	 
	 String sexo = p.pesquisarUmConteudo_Numa_Linha_String(BD, turma+"_DadosPessoais", "sexo", "id", "", id);
	 if(sexo.equalsIgnoreCase("M")) {
		 situacao1="Aprovado";
		 situacao2="Reprovado";
	 }else {
		 situacao1="Aprovada";
		 situacao2="Reprovada";
	 }
	 
	 
	 
	 
	 
	 boolean chave=false;
	 
	 sair:
	 for (String d : desciplinas) {
		
		 
			 
			 if(d.contains(desciplina)) {
				 
				 if(d.contains("Chave")) {
					
					 chave=false;
					 break sair;	
				 }
				   
			 }
			 
			 
		 
		
	}
	 
	 
	 
	 
	 
	String situacao = p.pesquisarUmConteudo_Numa_Linha_String(BD, turma+"_Media", "Situacao", "id", "", id);
	 
	 
	 if(situacao.equalsIgnoreCase("Reprovado")||
			 (situacao.equalsIgnoreCase("Reprovada"))) {
		 
	 }else {
		 
		 if(chave){
			 
			 if(media<9) {
				 ta.actualizarColuna_QualquerLinha_String(BD, turma+"_Media", "Situacao", situacao2, id);
			 }else {
				 ta.actualizarColuna_QualquerLinha_String(BD, turma+"_Media", "Situacao", situacao1, id);
			 }
		 }else {
			 
			 String[] a = turma.split("_");
			   
			   System.out.println(a[1]);
			   
			   if(a[1].endsWith("10")) {
				   ta.actualizarColuna_QualquerLinha_String(BD, turma+"_Media", "Situacao", situacao1, id);
			   }else {
				   
				   //Buscar a Media no Servido do Anto Anterior
				   //Se a soma da media do ano anterior com o actual for
				   //Maior que 9 está aprovado senao está Reprovado 
				   
				   if(media<9) {
						 ta.actualizarColuna_QualquerLinha_String(BD, turma+"_Media", "Situacao", situacao2, id);
					 }else {
						 ta.actualizarColuna_QualquerLinha_String(BD, turma+"_Media", "Situacao", situacao1, id);
					 }
				   
			   }
			 
			 
		 }
	 }
	 
	 
	 
	 
	 
	 
	
	 
	  
		
 }
 
 
 public ArrayList<String> coordenador_Minhas_Turmas(String BD,String curso){
	   
	 Pesquisar_SQL p = new Pesquisar_SQL();
	 ArrayList<String> turmas = p.pesquisar_Todas_Turmas_De_Um_Curso(BD, curso);
	  return turmas;
		
 }
 
 public ArrayList<String> coordenador_Profs_Do_Curso(String BD,String curso){
	   
	 Pesquisar_SQL p = new Pesquisar_SQL();
	 ArrayList<String> profs = p.pesquisar_Todos_Professores_De_Um_Curso(BD, curso);
	  return profs;
		
 }
 
 public boolean coordenador_Permitir_Lancar_Provas(String BD, Coordenador coord,String bi
		 ) {
		
		boolean estaTrancado=false;
		Tabela_Actualizar_SQL ta = new Tabela_Actualizar_SQL();
		Pesquisar_SQL p = new Pesquisar_SQL();
		Salvar_SQL s = new Salvar_SQL();
		
		int idCoord= p.pesquisarUmConteudo_Numa_Linha_Int(BD, "cursos", "id", "bi", bi, 0);
		
		if(idCoord!=0) {
			
			
			if(coord.isAvaliacao()&&
					(coord.isProva()==false)&&
					(coord.isExame()==false)&&
					(coord.isBloqueio()==false)) {
				estaTrancado=true;
				ta.actualizarColuna_QualquerLinha_String(BD, "cursos", "trancar", "A", idCoord);
				ta.actualizarColuna_QualquerLinha_String(BD, "cursos", "Situacao", "A", idCoord);
			
			}
			
			if(coord.isProva()&&
					((coord.isExame()==false))&&
					(coord.isAvaliacao()==false)&&
					(coord.isBloqueio()==false)) {
				estaTrancado=true;
				ta.actualizarColuna_QualquerLinha_String(BD, "cursos", "trancar", "P", idCoord);
				ta.actualizarColuna_QualquerLinha_String(BD, "cursos", "Situacao", "P", idCoord);
				
			}
			
			if(coord.isExame()&&
					((coord.isAvaliacao()==false))&&
					(coord.isBloqueio()==false)&&
					(coord.isProva()==false)) {
				estaTrancado=true;
				ta.actualizarColuna_QualquerLinha_String(BD, "cursos", "trancar", "E", idCoord);
				ta.actualizarColuna_QualquerLinha_String(BD, "cursos", "Situacao", "E", idCoord);
				
			}
			
			if(coord.isBloqueio()) {
				estaTrancado=true;
				ta.actualizarColuna_QualquerLinha_String(BD, "cursos", "trancar", "S", idCoord);
			
			}
			
			
			
			
			ta.actualizarColuna_Na_PrimeiraLinha(BD, "infoescola2", "Datas", 1);
			ta.actualizarColuna_QualquerLinha_String(BD, "infoescola2", "Datas", coord.getTrimestre(), 1);
			
			
			
		}
		return estaTrancado;
		
	}
 
 

 
 
 public ArrayList<String> direcao_Pedagocica_Listar_Profs_Do_Curso(String BD,String curso){
	   
	 Pesquisar_SQL p = new Pesquisar_SQL();
	 ArrayList<String> profs = p.pesquisar_Todos_Professores_De_Um_Curso(BD, curso);
	  return profs;
		
 }
 
 public boolean direcao_Pedagocica_Escolher_Coord_Do_Curso(String BD,String curso,String bi){
	   
	 boolean adicionado=false;
	 
	 inserir_CoordenadorDoCurso(BD, curso, bi);
	 adicionado=true;
	  return adicionado;
		
 }
 
 public boolean direcao_Pedagocica_Remover_Coord_Do_Curso(String BD,String curso,String bi){
	   
	 boolean eliminado=false;
	 
	  ELIMINAR_CoordenadorDoCurso_(BD, curso, bi);
	  eliminado=true;
	  return eliminado;
		
 }
 
 public int sistema_OndeParou_O_Cadastro(String bi) {
	 
	 Pesquisar_SQL p = new Pesquisar_SQL();
	 int paragem=p.pesquisarUmConteudo_Numa_Linha_Int("wg", "Escolas", "Onde_Parou", "bi", bi, 0);
	 
      return paragem;
 }
 
 public void sistema_Actualizar_OndeParou_O_Cadastro(String bi,int faseActual) {
	 
	 Tabela_Actualizar_SQL ta = new Tabela_Actualizar_SQL();
	 ta.actualizarColuna_Qualquer_Linha("wg", "escolas", "Onde_Parou", faseActual, "", "bi", bi);
	 
 }
 
public void sistema_Cadastrado_Com_Sucesso(String bi) {
	 
	 Tabela_Actualizar_SQL ta = new Tabela_Actualizar_SQL();
	 ta.actualizarColuna_Qualquer_Linha("wg", "Escolas", "sisCadatrado", 0, "S", "bi", bi);
	 
 }

public boolean sistema_Esta_Cadastro(String bi) {
	 
	 Pesquisar_SQL p = new Pesquisar_SQL();
	 String paragem=p.pesquisarUmConteudo_Numa_Linha_String("wg", "Escolas", "sisCadatrado", "bi", bi, 0);
	 
	 boolean retorno;
	 if(paragem.equalsIgnoreCase("")) {
		 retorno=false;
		 
	 }else {
		  retorno=true;
		  
	 }
     return retorno;
}

   
public void TempoDePag_Da_Propina_Sem_Multa(String BD,int dias_Sem_Multa) {
	 
	 Tabela_Actualizar_SQL ta = new Tabela_Actualizar_SQL();
	 ta.actualizarColuna_Na_PrimeiraLinha(BD, "Escola_Financa", "TempoPropina", dias_Sem_Multa);
	 
}  


     
     public void adminPagar(String BD,String bi,int valor) {
    	 
    	 Tabela_Actualizar_SQL ta = new Tabela_Actualizar_SQL();
    	 ArrayList<String> meses= new ArrayList<>();
    	    meses.add("Janeiro");
    	    meses.add("Fevereiro");
    	    meses.add("Março");
    	    meses.add("Abril");
    	    meses.add("Maio");
    	    meses.add("Junho");
    	    meses.add("Julho");
    	    meses.add("Agosto");		
    	    meses.add("Setembro");		
    	    meses.add("Outubro");		
    	    meses.add("Novembro");	
    	    meses.add("Dezembro");
    	    
    	    
    	    ArrayList<String> funcionarios= new ArrayList<>();
    		  
    		 
    		
    		   funcionarios.add("Professor_financa");
    		   funcionarios.add("Secretaria_financa");
    		   funcionarios.add("Tesouraria_financa");
    		   funcionarios.add("Coord_financa");
    		   funcionarios.add("DG_financa");
    		   funcionarios.add("DA_financa");
    		   funcionarios.add("DP_financa");
    		   
    		   
    		   Controle_ID_SQL cID = new Controle_ID_SQL();
    	       int idProfs = cID.recuperarCodigo(BD, "Professor_financa", "id");
    	       int idSec = cID.recuperarCodigo(BD, "Secretaria_financa", "id");
    	       int idTesou = cID.recuperarCodigo(BD, "Tesouraria_financa", "id");
    	       int idCoord = cID.recuperarCodigo(BD, "Coord_financa", "id");
    	       int idDG = cID.recuperarCodigo(BD, "DG_financa", "id");
    	       int idDA = cID.recuperarCodigo(BD, "DA_financa", "id");
    	       int idDP = cID.recuperarCodigo(BD, "DP_financa", "id");
    	    
    	       
    	       ArrayList<Integer> ids = new ArrayList<>();
    	       ids.add(idProfs);
    	       ids.add(idSec);
    	       ids.add(idTesou);
    	       ids.add(idCoord);
    	       ids.add(idDG);
    	       ids.add(idDA);
    	       ids.add(idDP);
    	       
    	       
    	    Date d = new Date();
    	    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    	    
			String mesActual = ta.tirarCaracteres_RetornandoA_A_Lista(sdf.format(d)).get(1);
			int mesActual2 = Integer.parseInt(mesActual);
			
			
    	 Pesquisar_SQL p = new Pesquisar_SQL();
    	 
    	 
    	 for (int i=0; i<funcionarios.size();i++) {
    		 
    		 for(int j=0;j<ids.get(i);j++  ) {
    			 
    			 ta.actualizarColuna_QualquerLinha_Int(BD, funcionarios.get(i), meses.get(mesActual2-1), 1, "", j+1);
    		 }
    		
			
		}
    	 int renda = p.pesquisarUmConteudo_Numa_Linha_Int(BD, "adminfinanca", "renda", "bi_admin", bi, 0);
    	 renda=renda-valor;
    	 
    	
    	 ta.actualizarColuna_Qualquer_Linha(BD, "adminfinanca", "renda", renda, "", "bi_admin", bi);
    	 ta.actualizarColuna_Qualquer_Linha(BD, "adminfinanca", "despeza", valor, "", "bi_admin", bi);
    	 
    	 
     }
     
     
 public String  mesActual(String BD) {
    	 
    	 Tabela_Actualizar_SQL ta = new Tabela_Actualizar_SQL();
    	 ArrayList<String> meses= new ArrayList<>();
    	    meses.add("Janeiro");
    	    meses.add("Fevereiro");
    	    meses.add("Março");
    	    meses.add("Abril");
    	    meses.add("Maio");
    	    meses.add("Junho");
    	    meses.add("Julho");
    	    meses.add("Agosto");		
    	    meses.add("Setembro");		
    	    meses.add("Outubro");		
    	    meses.add("Novembro");	
    	    meses.add("Dezembro");
    	    
    	    
    	    ArrayList<String> funcionarios= new ArrayList<>();
    		  
    		 
    		
    		   funcionarios.add("Professor_financa");
    		   funcionarios.add("Secretaria_financa");
    		   funcionarios.add("Tesouraria_financa");
    		   funcionarios.add("Coord_financa");
    		   funcionarios.add("DG_financa");
    		   funcionarios.add("DA_financa");
    		   funcionarios.add("DP_financa");
    		   
    		   
    		   Controle_ID_SQL cID = new Controle_ID_SQL();
    	       int idProfs = cID.recuperarCodigo(BD, "Professor_financa", "id");
    	       int idSec = cID.recuperarCodigo(BD, "Secretaria_financa", "id");
    	       int idTesou = cID.recuperarCodigo(BD, "Tesouraria_financa", "id");
    	       int idCoord = cID.recuperarCodigo(BD, "Coord_financa", "id");
    	       int idDG = cID.recuperarCodigo(BD, "DG_financa", "id");
    	       int idDA = cID.recuperarCodigo(BD, "DA_financa", "id");
    	       int idDP = cID.recuperarCodigo(BD, "DP_financa", "id");
    	    
    	       
    	       ArrayList<Integer> ids = new ArrayList<>();
    	       ids.add(idProfs);
    	       ids.add(idSec);
    	       ids.add(idTesou);
    	       ids.add(idCoord);
    	       ids.add(idDG);
    	       ids.add(idDA);
    	       ids.add(idDP);
    	       
    	       
    	    Date d = new Date();
    	    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    	    
			String mesActual = ta.tirarCaracteres_RetornandoA_A_Lista(sdf.format(d)).get(1);
			int mesActual2 = Integer.parseInt(mesActual);
			
			
    	 
    	 
			return meses.get(mesActual2-1);
    	 
     }
 
		
}
