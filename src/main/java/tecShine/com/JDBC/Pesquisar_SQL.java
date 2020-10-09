package tecShine.com.JDBC;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import tecShine.com.AcessoInteligente.AcessoInteligente;
import tecShine.com.controller.TechShineController;
import tecShine.com.model.Aluno;
import tecShine.com.model.AlunoNotas;
import tecShine.com.model.Curso;
import tecShine.com.model.Fase2;
import tecShine.com.model.Funcionario;
import tecShine.com.model.Minhas_Financas;
import tecShine.com.model.Tesouraria_Diario;
import tecShine.com.model.WG.Escola;
import tecShine.com.model.WG.PCA_WG;


/**
 * Essa Classe Vai fazer uma pesquisa nas diversas 
 * Tabelas de diversas BDs
 * 
 * 
 * @author Jose Euclides Pedro Pereira dos Santos
 * @version 1.0
 * @see ConnectionFactory
 *
 */


public class Pesquisar_SQL {

	private  ConnectionFactory cf= new ConnectionFactory();
	public String mensagem2;

	
	/**
	 * Esse Metodo Vai Pesquisar Todos Os PCAs Da WG
	 * 
	 * @return PCAs
	 */
	public  ArrayList<PCA_WG> pesquisarTabelaPCA (){
		
		
		String sql="select * from pca";
		
		ArrayList<PCA_WG> PCAs= new ArrayList<>();
		
		
		String usarBD="use pca";
		
		
		 Connection con=null;
		 ResultSet rs = null;
		 PreparedStatement stm=null ;
		
		try{
			con = ConnectionFactory.getConnection();
			
			stm = con.prepareStatement(sql);
			stm.executeUpdate(usarBD);
			
			rs =stm. executeQuery();
			
			
		    
			while(rs.next()) {
				
				
				
				PCA_WG pca = new PCA_WG();
				
				pca.setNome(rs.getString("nome"));
				pca.setBi(rs.getString("bi"));
				pca.financa.setRenda(rs.getInt("valor"));
				pca.setUsuarioAcesso(rs.getString("acesso"));
				pca.setAcessoPersonalizado1(rs.getString("config1"));
				pca.setAcessoPersonalizado2(rs.getString("config2"));
				
				
				
				PCAs.add(pca);
			}
			
			System.out.println("Pesquisa Concluida com Sucesso !!!");
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				con.close();
				stm.close();
				rs.close();
				 
				
				System.out.println("Coneccões Fechadas !!!");

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		
		return PCAs;
	}
	
	
	
    
	
	
      
	/*
	 * 
	 * 
	 * public ArrayList<Financa> pesquisarMatOuDocr(String BD, String coluna){
	
	
	String sql="select "+coluna+" from infoescola";
	ArrayList<Financa> financas= new ArrayList<>();
	
	String usarBD="use "+BD;
	
	
	 Connection con=null;
	 ResultSet rs = null;
	 PreparedStatement stm=null ;
	
	try{
		con = cf.criarConexao();
		
		stm = con.prepareStatement(sql);
		stm.executeUpdate(usarBD);
		
		rs =stm. executeQuery();
		
		
		
	    
		while(rs.next()) {
		       
			 Financa f = new Financa();
			 if(coluna.equalsIgnoreCase("materias")) {
				 
				 
					f.setMaterias(rs.getString("materias"));
					financas.add(f);
			 }else if(coluna.equalsIgnoreCase("documentos")) {
				 
				    
				 
					f.setDocumento(rs.getString("documentos"));
					financas.add(f);
			 }
			
			 
			
		}
		
		System.out.println("Pesquisa Concluida com Sucesso !!!");
		
		
	}catch (Exception e) {
		e.printStackTrace();
	}
	finally {
		try {
			con.close();
			stm.close();
			rs.close();
			 
			
			System.out.println("Coneccões Fechadas !!!");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	return financas;
}
	 */

	
	/**
	 * Esse Metodo Vai Pesquisar Todos Os PCAs Da WG
	 * 
	 * @return PCAs
	 */
	public ArrayList<Escola> pesquisarTodasEscolas(){
		
		
		String sql="select * from escolas";
		
		ArrayList<Escola> escolas= new ArrayList<>();
		
		
		String usarBD="use wg";
		
		
		 Connection con=null;
		 ResultSet rs = null;
		 PreparedStatement stm=null ;
		
		try{
			con = ConnectionFactory.getConnection();
			
			stm = con.prepareStatement(sql);
			stm.executeUpdate(usarBD);
			
			rs =stm. executeQuery();
			
			
			
		    
			while(rs.next()) {
				
				
				Escola escola = new Escola();
				
				escola.setNomeEscola(rs.getString("nome"));
				escola.setBi(rs.getString("bi"));
				
				
				
				escolas.add(escola);
			}
			
			System.out.println("Pesquisa Concluida com Sucesso !!!");
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				con.close();
				stm.close();
				rs.close();
				 
				
				System.out.println("Coneccões Fechadas !!!");

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		
		return escolas;
	}
	
	
public Fase2 pesquisarTodosAdmin(String BD,String bi,String usuario){
		
		
		String sql="select * from admin";
		Fase2 admin = new Fase2();
		
		
		
		String usarBD="use "+BD;
		
		
		 Connection con=null;
		 ResultSet rs = null;
		 PreparedStatement stm=null ;
		
		try{
			con = ConnectionFactory.getConnection();
			
			stm = con.prepareStatement(sql);
			stm.executeUpdate(usarBD);
			
			rs =stm. executeQuery();
			
			
			
		    
			while(rs.next()) {
				
				
              
				admin.setPca(rs.getString("nome"));
				admin.setSigla(rs.getString("sigla"));
				admin.escola.setContrato(rs.getString("contrato"));
				admin.escola.setBi(rs.getString("bi"));
				admin.escola.setValor(rs.getInt("valor"));
				admin.setUsuarioAcesso(rs.getString("acesso"));
				admin.setAcessoPersonalizado1(rs.getString("config1"));
				admin.setAcessoPersonalizado2(rs.getString("config2"));
				
				
				
		
				
			}
			
			System.out.println("Pesquisa Concluida com Sucesso !!!");
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				con.close();
				stm.close();
				rs.close();
				 
				
				System.out.println("Coneccões Fechadas !!!");

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		
		return admin;
	}


public ArrayList<String> pesquisarColunaVazia(String BD,String coluna){
	
	
	String sql="select "+coluna+" from controle_turmas ";
	ArrayList<String> conteudos= new ArrayList<>();
	
	
	String usarBD="use "+BD;
	
	
	 Connection con=null;
	 ResultSet rs = null;
	 PreparedStatement stm=null ;
	
	try{
		con = ConnectionFactory.getConnection();
		
		stm = con.prepareStatement(sql);
		stm.executeUpdate(usarBD);
		
		rs =stm. executeQuery();
		
		
		
	    
		while(rs.next()) {
			
			
			
			conteudos.add(rs.getString(coluna));
          
		}
		
		System.out.println("Pesquisa Concluida com Sucesso !!!");
		
		
	}catch (Exception e) {
		e.printStackTrace();
	}
	finally {
		try {
			con.close();
			stm.close();
			rs.close();
			 
			
			System.out.println("Coneccões Fechadas !!!");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	return conteudos;
}




public ArrayList<String>pesquisarTudoEmString(String BD,String tabela,
		String coluna){
	
	
	String sql="select "+coluna+" from "+tabela;
	ArrayList<String> conteudos= new ArrayList<>();
	
	System.out.println("Outro Slect: "+sql);
	String usarBD="use "+BD;
	
	
	 Connection con=null;
	 ResultSet rs = null;
	 PreparedStatement stm=null ;
	
	try{
		con = ConnectionFactory.getConnection();
		
		stm = con.prepareStatement(sql);
		stm.executeUpdate(usarBD);
		
		rs =stm. executeQuery();
		
		
		
	    
		while(rs.next()) {
			
			
			if((rs.getString(coluna).equalsIgnoreCase(""))||(rs.getString(coluna)==null)){
				
			}else {
				conteudos.add(rs.getString(coluna));
			}
			
          
		}
		
		System.out.println("Pesquisa Concluida com Sucesso !!!");
		
		
	}catch (Exception e) {
		e.printStackTrace();
	}
	finally {
		try {
			con.close();
			stm.close();
			rs.close();
			 
			
			System.out.println("Coneccões Fechadas !!!");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	return conteudos;
}


public ArrayList<String>pesquisarTudoEmString2(String BD,String tabela,
		String coluna){
	
	
	String sql="select "+coluna+" from "+tabela;
	ArrayList<String> conteudos= new ArrayList<>();
	
	
	String usarBD="use "+BD;
	
	
	 Connection con=null;
	 ResultSet rs = null;
	 PreparedStatement stm=null ;
	
	try{
		con = ConnectionFactory.getConnection();
		
		stm = con.prepareStatement(sql);
		stm.executeUpdate(usarBD);
		
		rs =stm. executeQuery();
		
		
		
	    
		while(rs.next()) {
			
			
				
				conteudos.add(rs.getString(coluna));

          
		}
		
		System.out.println("Pesquisa Concluida com Sucesso !!!");
		
		
	}catch (Exception e) {
		e.printStackTrace();
	}
	finally {
		try {
			con.close();
			stm.close();
			rs.close();
			 
			
			System.out.println("Coneccões Fechadas !!!");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	return conteudos;
}


public ArrayList<Funcionario>pesquisar_Dados_Pessoais_Funcionários(String BD,String tabela,
		String coluna){
	
	
	
	
	
	String sql="select * from "+tabela;
	ArrayList<Funcionario> conteudos= new ArrayList<>();
	
	
	String usarBD="use "+BD;
	
	
	 Connection con=null;
	 ResultSet rs = null;
	 PreparedStatement stm=null ;
	
	try{
		con = ConnectionFactory.getConnection();
		
		stm = con.prepareStatement(sql);
		stm.executeUpdate(usarBD);
		
		rs =stm. executeQuery();
		
		
		
	    
		while(rs.next()) {
			
			
			
			Funcionario func = new Funcionario();
			
			func.setId2(rs.getInt("id"));
			func.setBi(rs.getString("bi"));
			func.setTelefone(rs.getInt("Telefone"));
			func.setAlternativo1(rs.getInt("telefone2"));
			func.setAlternativo2(rs.getInt("telefone3"));
			func.setEmail(rs.getString("email"));
			func.setMae(rs.getString("mae"));
			func.setPai(rs.getString("pai"));
			func.setNaturalidade(rs.getString("naturalidae"));
			func.setNacionalidade(rs.getString("nacionalidade"));
			func.setSexo2(rs.getString("sexo"));
			func.setProvincia(rs.getString("provincia"));
			func.setRua(rs.getString("rua"));
			func.setBairro(rs.getString("bairro"));
			func.setMunicipio(rs.getString("municipio"));
			func.setNome(rs.getString("nomes"));
			func.setInstituicao(rs.getString("instituicao"));
			
			
			
			conteudos.add(func);
			
          
		}
		
		System.out.println("Pesquisa Concluida com Sucesso !!!");
		
		
	}catch (Exception e) {
		e.printStackTrace();
	}
	finally {
		try {
			con.close();
			stm.close();
			rs.close();
			 
			
			System.out.println("Coneccões Fechadas !!!");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	return conteudos;
}


public ArrayList<AcessoInteligente>pesquisar_Dados_Pessoais_DeIntefrantes(String BD,String tabela
		){
	
	
	
	
	
	String sql="select * from "+tabela;
	ArrayList<AcessoInteligente> conteudos= new ArrayList<>();
	
	
	String usarBD="use "+BD;
	
	
	 Connection con=null;
	 ResultSet rs = null;
	 PreparedStatement stm=null ;
	
	try{
		con = ConnectionFactory.getConnection();
		
		stm = con.prepareStatement(sql);
		stm.executeUpdate(usarBD);
		
		rs =stm. executeQuery();
		
		
		
	    
		while(rs.next()) {
			
			
			
			AcessoInteligente func = new AcessoInteligente();
			
			func.id=rs.getInt("id");
			func.bi=rs.getString("bi");
			func.telefone=rs.getInt("Telefone");
			func.alternativo1=rs.getInt("telefone2");
			func.alternativo2=rs.getInt("telefone3");
			func.email=rs.getString("email");
			func.mae=rs.getString("mae");
			func.Pai=rs.getString("pai");
			func.naturalidade=rs.getString("naturalidae");
			func.nacionalidade=rs.getString("nacionalidade");
			
			if(rs.getString("sexo").equalsIgnoreCase("M")) {
				
				func.sexo=true;
			}else {
				
					
					func.sexo=false;
				
			}
			
			
			func.provincia=rs.getString("provincia");
			func.rua=rs.getString("rua");
			func.bairro=rs.getString("bairro");
			func.municipio=rs.getString("municipio");
			
			
			
			conteudos.add(func);
			
          
		}
		
		System.out.println("Pesquisa Concluida com Sucesso !!!");
		
		
	}catch (Exception e) {
		e.printStackTrace();
	}
	finally {
		try {
			con.close();
			stm.close();
			rs.close();
			 
			
			System.out.println("Coneccões Fechadas !!!");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	return conteudos;
}


public AcessoInteligente pesquisar_Dados_Pessoais_Dum_Intefrante(String BD,String tabela
		,String bi){
	
	
	
	
	AcessoInteligente func = new AcessoInteligente();
	String sql="select * from "+tabela+" where bi='"+bi+"';";	
	
	String usarBD="use "+BD;
	
	
	 Connection con=null;
	 ResultSet rs = null;
	 PreparedStatement stm=null ;
	
	try{
		con = ConnectionFactory.getConnection();
		
		stm = con.prepareStatement(sql);
		stm.executeUpdate(usarBD);
		
		rs =stm. executeQuery();
		
		
		
		
		while(rs.next()) {
			
			
			
			
			
			func.id=rs.getInt("id");
			func.nome=rs.getString("nomes");
			func.bi=rs.getString("bi");
			func.telefone=rs.getInt("Telefone");
			func.alternativo1=rs.getInt("telefone2");
			func.alternativo2=rs.getInt("telefone3");
			func.email=rs.getString("email");
			func.mae=rs.getString("mae");
			func.Pai=rs.getString("pai");
			func.naturalidade=rs.getString("naturalidae");
			func.nacionalidade=rs.getString("nacionalidade");
			
			if(rs.getString("sexo").equalsIgnoreCase("M")) {
				
				func.sexo=true;
			}else {
				
					
					func.sexo=false;
				
			}
			
			
			func.provincia=rs.getString("provincia");
			func.rua=rs.getString("rua");
			func.bairro=rs.getString("bairro");
			func.municipio=rs.getString("municipio");
			
			
			
          
		}
		
		System.out.println("Pesquisa Concluida com Sucesso !!!");
		
		
	}catch (Exception e) {
		e.printStackTrace();
	}
	finally {
		try {
			con.close();
			stm.close();
			rs.close();
			 
			
			System.out.println("Coneccões Fechadas !!!");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	return func;
}




public Aluno pesquisar_Dados_Pessoais_Dum_Aluno(String BD,String tabela
		,String bi){
	
	
	
	
	Aluno aluno = new Aluno();
	String sql="select * from "+tabela+" where bi='"+bi+"';";	
	
	String usarBD="use "+BD;
	
	
	 Connection con=null;
	 ResultSet rs = null;
	 PreparedStatement stm=null ;
	
	try{
		con = ConnectionFactory.getConnection();
		
		stm = con.prepareStatement(sql);
		stm.executeUpdate(usarBD);
		
		rs =stm. executeQuery();
		
		
		
		
		while(rs.next()) {
			
			
			
			aluno.setId2(rs.getInt("id"));
			aluno.setBi(rs.getString("bi"));
			aluno.setTelefone(rs.getInt("Telefone"));
			aluno.setAlternativo1(rs.getInt("telefone2"));
			aluno.setAlternativo2(rs.getInt("telefone3"));
			aluno.setEmail(rs.getString("email"));
			aluno.setMae(rs.getString("mae"));
			aluno.setPai(rs.getString("pai"));
			aluno.setNaturalidade(rs.getString("naturalidae"));
			aluno.setNacionalidade(rs.getString("nacionalidade"));
			
			
			
			if(rs.getString("sexo").equalsIgnoreCase("M")) {
				
				aluno.setSexo(true);
			}else {
				
					
				aluno.setSexo(false);
				
			}
			
			aluno.setProvincia(rs.getString("provincia"));
			aluno.setRua(rs.getString("rua"));
			aluno.setBairro(rs.getString("bairro"));
			aluno.setMunicipio(rs.getString("municipio"));
			aluno.setInstituicao(rs.getString("instituicao"));
			
			
			
			
          
		}
		
		System.out.println("Pesquisa Concluida com Sucesso !!!");
		
		
	}catch (Exception e) {
		e.printStackTrace();
	}
	finally {
		try {
			con.close();
			stm.close();
			rs.close();
			 
			
			System.out.println("Coneccões Fechadas !!!");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	return aluno;
}


public ArrayList<Integer> pesquisarTudoEmInt(String BD,String tabela,String coluna){
	
	
	String sql="select "+coluna+" from "+tabela;
	ArrayList<Integer> conteudos= new ArrayList<>();
	
	
	String usarBD="use "+BD;
	
	
	 Connection con=null;
	 ResultSet rs = null;
	 PreparedStatement stm=null ;
	
	try{
		con = ConnectionFactory.getConnection();
		
		stm = con.prepareStatement(sql);
		stm.executeUpdate(usarBD);
		
		rs =stm. executeQuery();
		
		
		
	    
		while(rs.next()) {
			
			
			
			conteudos.add(rs.getInt(coluna));
          
		}
		
		System.out.println("Pesquisa Concluida com Sucesso !!!");
		
		
	}catch (Exception e) {
		e.printStackTrace();
	}
	finally {
		try {
			con.close();
			stm.close();
			rs.close();
			 
			
			System.out.println("Coneccões Fechadas !!!");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	return conteudos;
}


public ArrayList<Integer> pesquisarTudoEmInt_Restrito(String BD,String tabela,String coluna_RecuperarValor
		,String colunaReferencia,String conteudoString,int conteudoInt){
	
	
	String sql;
	if(conteudoInt==0) {
		
		 sql="select "+coluna_RecuperarValor+" from "+tabela+" where "+colunaReferencia+"='"+conteudoString+"'";
	}else {
		 sql="select "+coluna_RecuperarValor+" from "+tabela+" where "+colunaReferencia+"="+conteudoInt;
	}
	
	
	ArrayList<Integer> conteudos= new ArrayList<>();
	
	
	String usarBD="use "+BD;
	
	
	 Connection con=null;
	 ResultSet rs = null;
	 PreparedStatement stm=null ;
	
	try{
		con = ConnectionFactory.getConnection();
		
		stm = con.prepareStatement(sql);
		stm.executeUpdate(usarBD);
		
		rs =stm. executeQuery();
		
		
		
		while(rs.next()) {
			
			
			
			
			conteudos.add(rs.getInt(coluna_RecuperarValor));
          
		}
		
		System.out.println("Pesquisa Concluida com Sucesso !!!");
		
		
	}catch (Exception e) {
		e.printStackTrace();
	}
	finally {
		try {
			con.close();
			stm.close();
			rs.close();
			 
			
			System.out.println("Coneccões Fechadas !!!");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	return conteudos;
}


public ArrayList<String> pesquisarTudoEmString_Restrito(String BD,String tabela,String coluna_RecuperarValor
		,String colunaReferencia,String conteudoString,int conteudoInt){
	
	
	String sql;
	if(conteudoInt==0) {
		
		 sql="select "+coluna_RecuperarValor+" from "+tabela+" where "+colunaReferencia+"='"+conteudoString+"';";
	}else {
		 sql="select "+coluna_RecuperarValor+" from "+tabela+" where "+colunaReferencia+"="+conteudoInt+";";
	}
	
	System.out.println("SQL Restrito: "+sql);
	
	ArrayList<String> conteudos= new ArrayList<>();
	
	
	String usarBD="use "+BD;
	
	
	 Connection con=null;
	 ResultSet rs = null;
	 PreparedStatement stm=null ;
	
	try{
		con = ConnectionFactory.getConnection();
		
		stm = con.prepareStatement(sql);
		stm.executeUpdate(usarBD);
		
		rs =stm. executeQuery();
		
		
		
		while(rs.next()) {
			
			
			
			
			conteudos.add(rs.getString(coluna_RecuperarValor));
          
		}
		
		System.out.println("Pesquisa Concluida com Sucesso !!!");
		
		
	}catch (Exception e) {
		e.printStackTrace();
	}
	finally {
		try {
			con.close();
			stm.close();
			rs.close();
			 
			
			System.out.println("Coneccões Fechadas !!!");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	return conteudos;
}


public int pesquisarQConteudos_Na_coluna(String BD,String tabela,String coluna,
		boolean coluna_E_Uma_String){
	
	
	int contador=0;
	String sql="select "+coluna+" from "+tabela;
	
	
	String usarBD="use "+BD;
	
	
	 Connection con=null;
	 ResultSet rs = null;
	 PreparedStatement stm=null ;
	
	try{
		con = ConnectionFactory.getConnection();
		
		stm = con.prepareStatement(sql);
		stm.executeUpdate(usarBD);
		
		rs =stm. executeQuery();
		
		
		
	    
		while(rs.next()) {
			
			if(coluna_E_Uma_String) {
				
				if((rs.getString(coluna).equalsIgnoreCase(""))||(rs.getString(coluna)==null)){
					
				}else {
					++contador;
				}
			}else {
				
				if((rs.getInt(coluna))==0){

				}else {
					++contador;
				}
			}
			
          
		}
		
		System.out.println("Existem "+contador+" conteudos");
		
		
	}catch (Exception e) {
		e.printStackTrace();
	}
	finally {
		try {
			con.close();
			stm.close();
			rs.close();
			 
			
			System.out.println("Coneccões Fechadas !!!");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	return contador;
}


public ArrayList<String> pesquisar_Turmas_Do_Turno(String BD,String coluna_turno
		){
	
	String sql="select "+coluna_turno+" from Controle_Turmas";
	ArrayList<String> todasTurmas= new ArrayList<>();
	
	String usarBD="use "+BD;
	
	
	 Connection con=null;
	 ResultSet rs = null;
	 PreparedStatement stm=null ;
	
	try{
		con = ConnectionFactory.getConnection();
		
		stm = con.prepareStatement(sql);
		stm.executeUpdate(usarBD);
		
		rs =stm. executeQuery();
		
		
		
	    
		while(rs.next()) {
			
				
				if((rs.getString(coluna_turno).equalsIgnoreCase(""))||(rs.getString(coluna_turno)==null)){
					
				}else {
                   todasTurmas.add(rs.getString(coluna_turno));
				}
			
			
          
		}
		
		
		
	}catch (Exception e) {
		e.printStackTrace();
	}
	finally {
		try {
			con.close();
			stm.close();
			rs.close();
			 
			
			System.out.println("Coneccões Fechadas !!!");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	return todasTurmas;
}



public ArrayList<String> pesquisar_Turmas_Do_Curso(String BD,String coluna_turno
		){
	
	String sql="select "+coluna_turno+" from cursos_turmas;";
	System.out.println("Novo SQL: "+sql);
	ArrayList<String> todasTurmas= new ArrayList<>();
	
	String usarBD="use "+BD;
	
	
	 Connection con=null;
	 ResultSet rs = null;
	 PreparedStatement stm=null ;
	
	try{
		con = ConnectionFactory.getConnection();
		
		stm = con.prepareStatement(sql);
		stm.executeUpdate(usarBD);
		
		rs =stm. executeQuery();
		
		
		
	    
		while(rs.next()) {
			
				
				if((rs.getString(coluna_turno).equalsIgnoreCase(""))||(rs.getString(coluna_turno)==null)){
					
					System.out.println("coluna vazia ");
				}else {
                   todasTurmas.add(rs.getString(coluna_turno));
                   System.out.println("coluna cheia ");
				}
			
			
          
		}
		
		
		
	}catch (Exception e) {
		e.printStackTrace();
	}
	finally {
		try {
			con.close();
			stm.close();
			rs.close();
			 
			
			System.out.println("Coneccões Fechadas !!!");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	 
	
	return todasTurmas;
}

public ArrayList<Tesouraria_Diario> pesquisar_Precos_Das_Propinas(String BD
		){
	
	String sql="select Propinas,precoProp from Escola_Financa";
	ArrayList<Tesouraria_Diario> preccos= new ArrayList<>();
	
	String usarBD="use "+BD;
	
	
	 Connection con=null;
	 ResultSet rs = null;
	 PreparedStatement stm=null ;
	
	try{
		con = ConnectionFactory.getConnection();
		
		stm = con.prepareStatement(sql);
		stm.executeUpdate(usarBD);
		
		rs =stm. executeQuery();
		
		
		
	    
		while(rs.next()) {
			
			    Tesouraria_Diario d= new Tesouraria_Diario();
			    
				
				if((rs.getString("Propinas").equalsIgnoreCase(""))){
					
				}else {
					d.setConteudo(rs.getString("Propinas"));
					preccos.add(d);
				}
				
				if((rs.getInt("precoProp")==0)){

				}else {
					d.setPreco(rs.getInt("precoProp"));
					preccos.add(d);
				}
			
			
          
		}
		
		
		
	}catch (Exception e) {
		e.printStackTrace();
	}
	finally {
		try {
			con.close();
			stm.close();
			rs.close();
			 
			
			System.out.println("Coneccões Fechadas !!!");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	return preccos;
}	

public ArrayList<Tesouraria_Diario> pesquisar_Precos_Dos_Documentos(String BD
		){
	
	String sql="select Docs,precoDoc,TempoPropina from Escola_Financa";
	ArrayList<Tesouraria_Diario> preccos= new ArrayList<>();
	
	String usarBD="use "+BD;
	
	
	 Connection con=null;
	 ResultSet rs = null;
	 PreparedStatement stm=null ;
	
	try{
		con = ConnectionFactory.getConnection();
		
		stm = con.prepareStatement(sql);
		stm.executeUpdate(usarBD);
		
		rs =stm. executeQuery();
		
		
		
	    
		while(rs.next()) {
			
			    Tesouraria_Diario d= new Tesouraria_Diario();
			    
				
				if((rs.getString("Docs").equalsIgnoreCase(""))){
					
				}else {
					d.setConteudo(rs.getString("Docs"));
					preccos.add(d);
				}
				
				if((rs.getInt("precoDoc")==0)){

				}else {
					d.setPreco(rs.getInt("precoDoc"));
					preccos.add(d);
				}
				
				if((rs.getInt("TempoPropina")==0)){

				}else {
					d.setTempoPropina(rs.getInt("TempoPropina"));
					preccos.add(d);
				}
			
			
          
		}
		
		
		
	}catch (Exception e) {
		e.printStackTrace();
	}
	finally {
		try {
			con.close();
			stm.close();
			rs.close();
			 
			
			System.out.println("Coneccões Fechadas !!!");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	return preccos;
}


public ArrayList<Tesouraria_Diario> pesquisar_Precos_Dos_Materias(String BD
		){
	
	String sql="select Mat,precoMat from Escola_Financa";
	ArrayList<Tesouraria_Diario> preccos= new ArrayList<>();
	
	String usarBD="use "+BD;
	
	
	 Connection con=null;
	 ResultSet rs = null;
	 PreparedStatement stm=null ;
	
	try{
		con = ConnectionFactory.getConnection();
		
		stm = con.prepareStatement(sql);
		stm.executeUpdate(usarBD);
		
		rs =stm. executeQuery();
		
		
		
	    
		while(rs.next()) {
			
			    Tesouraria_Diario d= new Tesouraria_Diario();
			    
				
				if((rs.getString("Mat").equalsIgnoreCase(""))){
					
				}else {
					d.setConteudo(rs.getString("Mat"));
					preccos.add(d);
				}
				
				if((rs.getInt("precoMat")==0)){

				}else {
					d.setPreco(rs.getInt("precoMat"));
					preccos.add(d);
				}
			
			
          
		}
		
		
		
	}catch (Exception e) {
		e.printStackTrace();
	}
	finally {
		try {
			con.close();
			stm.close();
			rs.close();
			 
			
			System.out.println("Coneccões Fechadas !!!");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	return preccos;
}


public ArrayList<Tesouraria_Diario> pesquisar_Precos_Daas_Matriculass(String BD
		){
	
	String sql="select Matricula,precoMatricula from Escola_Financa";
	ArrayList<Tesouraria_Diario> preccos= new ArrayList<>();
	
	String usarBD="use "+BD;
	
	
	 Connection con=null;
	 ResultSet rs = null;
	 PreparedStatement stm=null ;
	
	try{
		con = ConnectionFactory.getConnection();
		
		stm = con.prepareStatement(sql);
		stm.executeUpdate(usarBD);
		
		rs =stm. executeQuery();
		
		
		
	    
		while(rs.next()) {
			
			    Tesouraria_Diario d= new Tesouraria_Diario();
			    
				
				if((rs.getString("Matricula").equalsIgnoreCase(""))){
					
				}else {
					d.setConteudo(rs.getString("Matricula"));
					preccos.add(d);
				}
				
				if((rs.getInt("precoMatricula")==0)){

				}else {
					d.setPreco(rs.getInt("precoMatricula"));
					preccos.add(d);
				}
			
			
          
		}
		
		
		
	}catch (Exception e) {
		e.printStackTrace();
	}
	finally {
		try {
			con.close();
			stm.close();
			rs.close();
			 
			
			System.out.println("Coneccões Fechadas !!!");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	return preccos;
}

public ArrayList<Tesouraria_Diario> pesquisar_Precos_Daas_Faltass(String BD
		){
	
	String sql="select Faltas,precoFalta from Escola_Financa";
	ArrayList<Tesouraria_Diario> preccos= new ArrayList<>();
	
	String usarBD="use "+BD;
	
	
	 Connection con=null;
	 ResultSet rs = null;
	 PreparedStatement stm=null ;
	
	try{
		con = ConnectionFactory.getConnection();
		
		stm = con.prepareStatement(sql);
		stm.executeUpdate(usarBD);
		
		rs =stm. executeQuery();
		
		
		
	    
		while(rs.next()) {
			
			    Tesouraria_Diario d= new Tesouraria_Diario();
			    
				
				if((rs.getString("Faltas").equalsIgnoreCase(""))){
					
				}else {
					d.setConteudo(rs.getString("Faltas"));
					preccos.add(d);
				}
				
				if((rs.getInt("precoFalta")==0)){

				}else {
					d.setPreco(rs.getInt("precoFalta"));
					preccos.add(d);
				}
			
			
          
		}
		
		
		
	}catch (Exception e) {
		e.printStackTrace();
	}
	finally {
		try {
			con.close();
			stm.close();
			rs.close();
			 
			
			System.out.println("Coneccões Fechadas !!!");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	return preccos;
}


public int pesquisar_Preco_Do_Estagio(String BD
		){
	
	String sql="select Estagio from Escola_Financa";
	int  estagio=0;
	
	String usarBD="use "+BD;
	
	
	 Connection con=null;
	 ResultSet rs = null;
	 PreparedStatement stm=null ;
	
	try{
		con = ConnectionFactory.getConnection();
		
		stm = con.prepareStatement(sql);
		stm.executeUpdate(usarBD);
		
		rs =stm. executeQuery();
		
		
		
	    
		while(rs.next()) {
			
			    
				
				
				if((rs.getInt("Estagio")==0)){

				}else {
					estagio=rs.getInt("Estagio");
				}
			
			
          
		}
		
		
		
	}catch (Exception e) {
		e.printStackTrace();
	}
	finally {
		try {
			con.close();
			stm.close();
			rs.close();
			 
			
			System.out.println("Coneccões Fechadas !!!");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	return estagio;
}


public int pesquisar_Preco_Do_Recurso(String BD
		){
	
	String sql="select Recurso from Escola_Financa";
	int  recurso=0;
	
	String usarBD="use "+BD;
	
	
	 Connection con=null;
	 ResultSet rs = null;
	 PreparedStatement stm=null ;
	
	try{
		con = ConnectionFactory.getConnection();
		
		stm = con.prepareStatement(sql);
		stm.executeUpdate(usarBD);
		
		rs =stm. executeQuery();
		
		
		
	    
		while(rs.next()) {
			
			    
				
				
				if((rs.getInt("Recurso")==0)){

				}else {
					recurso=rs.getInt("Recurso");
				}
			
			
          
		}
		
		
		
	}catch (Exception e) {
		e.printStackTrace();
	}
	finally {
		try {
			con.close();
			stm.close();
			rs.close();
			 
			
			System.out.println("Coneccões Fechadas !!!");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	return recurso;
}

public ArrayList<Tesouraria_Diario> pesquisar_Salario_Funcs(String BD
		){
	
	String sql="select Funcionarios,PrecoFunc from Escola_Financa";
	ArrayList<Tesouraria_Diario> preccos= new ArrayList<>();
	
	String usarBD="use "+BD;
	
	
	 Connection con=null;
	 ResultSet rs = null;
	 PreparedStatement stm=null ;
	
	try{
		con = ConnectionFactory.getConnection();
		
		stm = con.prepareStatement(sql);
		stm.executeUpdate(usarBD);
		
		rs =stm. executeQuery();
		
		
		
	    
		while(rs.next()) {
			
			    Tesouraria_Diario d= new Tesouraria_Diario();
			    
				
				if((rs.getString("Funcionarios").equalsIgnoreCase(""))){
					
				}else {
					d.setConteudo(rs.getString("Funcionarios"));
					preccos.add(d);
				}
				
				if((rs.getInt("PrecoFunc")==0)){

				}else {
					d.setPreco(rs.getInt("PrecoFunc"));
					preccos.add(d);
				}
			
			
          
		}
		
		
		
	}catch (Exception e) {
		e.printStackTrace();
	}
	finally {
		try {
			con.close();
			stm.close();
			rs.close();
			 
			
			System.out.println("Coneccões Fechadas !!!");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	return preccos;
}





public String pesquisarUmConteudo_Numa_Linha_String(String BD,String tabela,
		String coluna, String criterio,String idString,int idInt){
	
	String  sql;
	if(idInt==0) {
		
		  sql="select "+coluna+" from "+tabela+" where "+criterio+"='"+idString+"';";
	}else {
		  sql="select "+coluna+" from "+tabela+" where "+criterio+"="+idInt+";";
	}
	
	  System.out.println("Meu SQL: "+sql);
	String conteudo="";
	
	
	String usarBD="use "+BD;
	
	
	 Connection con=null;
	 ResultSet rs = null;
	 PreparedStatement stm=null ;
	
	try{
		con = ConnectionFactory.getConnection();
		
		stm = con.prepareStatement(sql);
		stm.executeUpdate(usarBD);
		
		rs =stm. executeQuery();
		
		
		
	    
		while(rs.next()) {
			
			
			
			conteudo=rs.getString(coluna);
          
		}
		 
		System.out.println("Pesquisa Concluida com Sucesso !!!");
		
		
	}catch (Exception e) {
		e.printStackTrace();
	}
	finally {
		try {
			con.close();
			stm.close();
			rs.close();
			 
			
			System.out.println("Coneccões Fechadas !!!");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	return conteudo;
}



public Blob pesquisarUm_Logotipo(String BD,String tabela,
		String coluna){
	
	String  sql="select logotipo from "+tabela+";";
	
	
	  System.out.println("Meu SQL: "+sql);
	Blob conteudo=null;
	
	
	String usarBD="use "+BD;
	
	
	 Connection con=null;
	 ResultSet rs = null;
	 PreparedStatement stm=null ;
	
	try{
		con = ConnectionFactory.getConnection();
		
		stm = con.prepareStatement(sql);
		stm.executeUpdate(usarBD);
		
		rs =stm. executeQuery();
		
		
		
	    int contador=0;
	    sair:
		while(rs.next()) {
			
			
			++contador;
			
			if(contador==1) {
				
				conteudo=(Blob)rs.getBlob(coluna);
				break sair;
			}
			
          
		}
		
		System.out.println("Pesquisa Concluida com Sucesso !!!");
		
		
	}catch (Exception e) {
		e.printStackTrace();
	}
	finally {
		try {
			con.close();
			stm.close();
			rs.close();
			 
			
			System.out.println("Coneccões Fechadas !!!");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	return conteudo;
}




public int pesquisarUmConteudo_Numa_Linha_Int(String BD,String tabela,
		String coluna,String criterio,String idString,int idInt){
	
	
	
	String  sql;
	if(idInt==0) {
		
		  sql="select "+coluna+" from "+tabela+" where "+criterio+"='"+idString+"';";
	}else {
		  sql="select "+coluna+" from "+tabela+" where "+criterio+"="+idInt+";";
	}
	System.out.println("SQL 4: "+sql);
	
	int conteudo=0;
	
	
	String usarBD="use "+BD;
	
	
	 Connection con=null;
	 ResultSet rs = null;
	 PreparedStatement stm=null ;
	
	try{
		con = ConnectionFactory.getConnection();
		
		stm = con.prepareStatement(sql);
		stm.executeUpdate(usarBD);
		
		rs =stm. executeQuery();
		
		
		
	    
		while(rs.next()) {
			
			
			
			conteudo=rs.getInt(coluna);
          
		}
		
		System.out.println("Pesquisa Concluida com Sucesso !!!");
		
		
	}catch (Exception e) {
		e.printStackTrace();
	}
	finally {
		try {
			con.close();
			stm.close();
			rs.close();
			 
			
			System.out.println("Coneccões Fechadas !!!");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	return conteudo;
}

   public ArrayList<String> pesquisar_Desciplinas_Dum_Curso(String BD,String curso){
	
	   ArrayList<String> desciplinas= pesquisarTudoEmString(BD, "cursos_disciplinas", curso);
	   ArrayList<String> retorno= new ArrayList<>();
	   Tabela_Actualizar_SQL ta = new Tabela_Actualizar_SQL();
	   String desc;
	   
	   for (String desciplina : desciplinas) {
		   desc=ta.tirarCaracteres_RetornandoA_A_Lista(desciplina).get(2);
		   retorno.add(desc);
	   }
	   
	   
	   
	   
	   return retorno;
}
   
   public ArrayList<String> pesquisar_Desciplinas_Chaves(String BD,String curso){
		
	   ArrayList<String> desciplinas= pesquisarTudoEmString(BD, "cursos_disciplinas", curso);
	   ArrayList<String> retorno= new ArrayList<>();
	   Tabela_Actualizar_SQL ta = new Tabela_Actualizar_SQL();
	   String desc;
	   
	   for (String desciplina : desciplinas) {
		   desc=ta.tirarCaracteres_RetornandoA_A_Lista(desciplina).get(1);
		   retorno.add(desc);
	   }
	   
	   
	   
	   
	   return retorno;
}
   
   public ArrayList<String> pesquisar_Todos_Niveis_De_Uma_Desciplina(String BD,String curso){
		
	   ArrayList<String> desciplinas= pesquisarTudoEmString(BD, "cursos_disciplinas", curso);
	   ArrayList<String> retorno= new ArrayList<>();
	   Tabela_Actualizar_SQL ta = new Tabela_Actualizar_SQL();
	   String desc;
	   
	   for (String desciplina : desciplinas) {
		   desc=ta.tirarCaracteres_RetornandoA_A_Lista(desciplina).get(0);
		   retorno.add(desc);
	   }
	   
	   
	   
	   
	   return retorno;
}
   
   public ArrayList<String> pesquisar_Desciplinas_De_Um_Unico_Nivel(String BD,String curso,String nivel){
		
	   ArrayList<String> desciplinas= pesquisarTudoEmString(BD, "cursos_disciplinas", curso);
	   ArrayList<String> retorno= new ArrayList<>();
	   Tabela_Actualizar_SQL ta = new Tabela_Actualizar_SQL();
	   String desc;
	   
	   for (String desciplina : desciplinas) {
		   desc=ta.tirarCaracteres_RetornandoA_A_Lista(desciplina).get(0);
		   if(nivel.contains(desc)) {
			   
			   retorno.add(desc);
		   }
		   
	   }
	   
	   
	   
	   
	   return retorno;
}
   
   
   public ArrayList<String> pesquisar_Todas_Turmas_De_Um_Curso(String BD,String curso){
		
	   ArrayList<String> desciplinas= pesquisarTudoEmString(BD, "cursos_turmas", curso);
	   ArrayList<String> retorno= new ArrayList<>();
	   
	   for (String desciplina : desciplinas) {
		   
		   retorno.add(desciplina);
	   }
	   
	   
	   
	   
	   return retorno;
}
   
   public ArrayList<String> pesquisar_Todos_Professores_De_Um_Curso(String BD,String curso){
		
	   ArrayList<String> desciplinas= pesquisarTudoEmString(BD, "cursos_profs_e_coord", curso);
	   ArrayList<String> retorno= new ArrayList<>();
	   
	   for (String desciplina : desciplinas) {
		   
		   if(desciplina.contains("Coord")) {
			   
			   
		   }else{
			   retorno.add(desciplina);
		   }
		   
	   }
	   
	   
	   
	   
	   return retorno;
}
   
   public String pesquisar_Coordenador_De_Um_Curso(String BD,String curso){
		
	   ArrayList<String> desciplinas= pesquisarTudoEmString(BD, "cursos_profs_e_coord", curso);
	   Tabela_Actualizar_SQL ta = new Tabela_Actualizar_SQL();
	   String coordenador="";
	   
	   sair:
	   for (String desciplina : desciplinas) {
		   
		   if(desciplina.contains("Coord")) {
			   
			    coordenador=ta.tirarCaracteres_RetornandoA_A_Lista(desciplina).get(1);
			    break sair;
			   
		   }else{
		   }
		   
	   }
	   
	   
	   
	   
	   return coordenador;
}
   
   public ArrayList<Aluno> pesquisar_Alunos_De_Uma_Tuma(String BD,String turma){
		
	   ArrayList<String> alunos= pesquisarTudoEmString(BD, turma, "Alunos");
	   ArrayList<Integer> ids= pesquisarTudoEmInt(BD, turma, "id");
	   
	   ArrayList<Aluno> todosAlunos= new ArrayList<>();
	   
	   
	   
	   for (int i=0;i<alunos.size();i++) {
		   
		   Aluno aluno= new Aluno();
		   
		   aluno.setId2(ids.get(i));
		   aluno.setNome(alunos.get(i));
		   
		   todosAlunos.add(aluno);
	   }
	   
	  
	   
	   
	   return todosAlunos;
}
   
   
   
   public Minhas_Financas  tesouraria_AlunosDadosFinanceiros(String BD,String turno,String nivel,
		   String curso,String aluno) {
	   
	   
	   String turno2=(String)turno.subSequence(0, 1);
	   String nivel2=(String)nivel.subSequence(0, 2);
	   Pesquisar_SQL p = new Pesquisar_SQL();
	   Minhas_Financas  dadosDoAluno= new Minhas_Financas();
	   
		
		
		String abeviaturaCurso_Ou_Disciplina=curso;
		abeviaturaCurso_Ou_Disciplina=abeviaturaCurso_Ou_Disciplina.substring(0, 3);
		
		
		
		 


		ArrayList<String> turmasFiltradas= new ArrayList<>();
		String nomeTurma="Turma"+turno2+""+
		             abeviaturaCurso_Ou_Disciplina+""+nivel2;
		
		String Turma = "";
		int idAluno=0;
		ArrayList<String> turmas= pesquisar_Turmas_Do_Turno(BD, turno);
		if(turmas!=null) {
			
			for (String turma : turmas) {
				
				if(turma.contains(nomeTurma)){
					
					turmasFiltradas.add(turma);
				}
			}
		}
		
		if(turmasFiltradas!=null) {
			
			sair:
			for (String turma : turmasFiltradas) {
				
				idAluno=0;
				for (String aluno2 : p.pesquisarTudoEmString(BD, turma, "Alunos")) {
					
					++idAluno;
					if(aluno.equalsIgnoreCase(aluno2)) {
						
						Turma=turma;
						break sair;
					}
					
				}
			}
		
		  dadosDoAluno= pesquisarDadosFinanceirosDoAluno(BD,
				        Turma,idAluno);
		  dadosDoAluno.setNome(aluno);
		  dadosDoAluno.setTurmaDoAluno(Turma);
		
		}
	   
	   return dadosDoAluno;
   }
   
   
   public ArrayList<String>  Listar_TurmasDoCurso(String BD,String turno,String nivel,
		   String curso) {
	   
	   String turno2=(String)turno.subSequence(0, 1);
	   String nivel2=(String)nivel.subSequence(0, 2);
	   
		
		
		String abeviaturaCurso_Ou_Disciplina=curso;
		abeviaturaCurso_Ou_Disciplina=abeviaturaCurso_Ou_Disciplina.substring(0, 3);
		
		
		
		 


		ArrayList<String> turmasFiltradas= new ArrayList<>();
		String nomeTurma="Turma"+turno2+""+
		             abeviaturaCurso_Ou_Disciplina+""+nivel2;
		
		System.out.println("Curso: "+curso);
		System.out.println("Nivel: "+nivel);
		System.out.println("Turno: "+turno);
		System.out.println("nivel2: "+nivel2);
		System.out.println("abeviaturaCurso_Ou_Disciplina : "+abeviaturaCurso_Ou_Disciplina);
		
		System.out.println("nomeTurma: "+nomeTurma);
		
		ArrayList<String> turmas= pesquisar_Turmas_Do_Curso(BD, curso); 
	
			
			
			for (String turma : turmas) {
				
				
				
				
				   String[] t = turma.split("_");
					String tt = t[1];
					
				
				System.out.println("tt: "+tt);
				
				
				System.out.println("Valor: "+abeviaturaCurso_Ou_Disciplina+""+turno2);
				
				if(tt.endsWith(abeviaturaCurso_Ou_Disciplina+""+nivel2)) {
					
					System.out.println("Entrou");
					
					if(t.length>2){

						System.out.println("Tem sigla");
		 				 
						 
							
							turmasFiltradas.add(t[(t.length-1)]);
						
					}else {
						
					
							
							System.out.println("Não Tem sigla");
							turmasFiltradas.add(t[1]);
						
						
					}
					
				}
				
				
			}
		
		
	
	   
	   return turmasFiltradas;
   }
   
   public AlunoNotas  Listar_TurmasDoCurso3(String BD,Aluno aluno) {
	   
	   
	   String turno= aluno.getTurno();
	   String nivel= aluno.getNivel();
	   String curso = aluno.getCurso();
	   String bi = aluno.getBi();
	   
	   Pesquisar_SQL p = new Pesquisar_SQL();
	   Tabela_Actualizar_SQL ta = new Tabela_Actualizar_SQL();
	   
	   
	   String turno2=(String)turno.subSequence(0, 1);
	   String nivel2=(String)nivel.subSequence(0, 2);
	   
		
		
		String abeviaturaCurso_Ou_Disciplina=curso;
		abeviaturaCurso_Ou_Disciplina=abeviaturaCurso_Ou_Disciplina.substring(0, 3);
		
		
		
		 


		ArrayList<String> turmasFiltradas= new ArrayList<>();
		String nomeTurma="Turma"+turno2+""+
		             abeviaturaCurso_Ou_Disciplina+""+nivel2;
		
		System.out.println("Curso: "+curso);
		System.out.println("Nivel: "+nivel);
		System.out.println("Turno: "+turno);
		System.out.println("nivel2: "+nivel2);
		System.out.println("abeviaturaCurso_Ou_Disciplina : "+abeviaturaCurso_Ou_Disciplina);
		
		System.out.println("nomeTurma: "+nomeTurma);
		
		
		ArrayList<String> desciplinas= p.pesquisarTudoEmString(BD, "cursos_disciplinas", curso);
		int quantCursos= 0;
		
		
		ArrayList<String> dessciplinasFiltrada = new ArrayList<>();
		
		ArrayList<String> desc = new ArrayList<>();
		String desc2;
		// Filtrando As Desciplinas do curso e do nivel selecionado !
		for (String desciplina : desciplinas) {
			System.out.println("Disci: "+desciplina);
			
			if(desciplina.contains(nivel2)) {

				++quantCursos;
				desc = ta.tirarCaracteres_RetornandoA_A_Lista(desciplina);
				desc2= desc.get(desc.size()-1);
				
				System.out.println("Desc2: "+desc2);
				
				dessciplinasFiltrada.add(ta.tirarCaracteres(desc2));
			}
		}
		
		
		ArrayList<String> turmas= pesquisar_Turmas_Do_Curso(BD, curso); 
	    String ttt;
			
			
			for (String turma : turmas) {
				
				
				
				
				   String[] t = turma.split("_");
					String tt = t[1];
					ttt = tt.toLowerCase();
					
					
				
				System.out.println("tt: "+tt);
				
				
				System.out.println("Valor: "+abeviaturaCurso_Ou_Disciplina+""+turno2);
				
				if(tt.endsWith(abeviaturaCurso_Ou_Disciplina+""+nivel2)) {
					
					
					if(ttt.contains("turma"+turno2.toLowerCase())) {
						
						System.out.println("Entrou");
							
								turmasFiltradas.add(turma);
							    System.out.println("Turma P Boletin: "+turma);
						


					}
					
					
				}
				
				
			}
		
			AlunoNotas aluno2 = new AlunoNotas();
			aluno2.setAlunoExiste(false);
			
			int id=0;
			sair:
			for(String c : turmasFiltradas) {
				
				id = p.pesquisarUmConteudo_Numa_Linha_Int(BD, c+"_dadospessoais", "id", "bi", bi, 0);
				
				if(id>0) {
					
					ArrayList<Integer> notas =new ArrayList<>();
					for(String d : dessciplinasFiltrada) {
						
						
						notas.add(p.pesquisarUmConteudo_Numa_Linha_Int(BD, c+"_Media", d, "id", "", id));
					}
					
					String situacaoFinal = p.pesquisarUmConteudo_Numa_Linha_String(BD, c+"_Media", "Situacao", "id", "", id);
					
					aluno2.setDesciplinas(dessciplinasFiltrada);
					aluno2.setNotas(notas);
					aluno2.setAlunoExiste(true);
					aluno2.setSituacaoFinal(situacaoFinal);
					
					
					break sair;
				}
			}
		
	
	   
	   return aluno2;
   }
   
   
 public Minhas_Financas  Listar_TurmasDoCurso4(String BD,Aluno aluno) {
	   
	   
	   Minhas_Financas alunoFinanca = new Minhas_Financas();
	   String turno= aluno.getTurno();
	   String nivel= aluno.getNivel();
	   String curso = aluno.getCurso();
	   String bi = aluno.getBi();
	   
	   Pesquisar_SQL p = new Pesquisar_SQL();
	   Tabela_Actualizar_SQL ta = new Tabela_Actualizar_SQL();
	   
	   
	   String turno2=(String)turno.subSequence(0, 1);
	   String nivel2=(String)nivel.subSequence(0, 2);
	   
		
		
		String abeviaturaCurso_Ou_Disciplina=curso;
		abeviaturaCurso_Ou_Disciplina=abeviaturaCurso_Ou_Disciplina.substring(0, 3);
		
		
		
		 


		ArrayList<String> turmasFiltradas= new ArrayList<>();
		String nomeTurma="Turma"+turno2+""+
		             abeviaturaCurso_Ou_Disciplina+""+nivel2;
		
		System.out.println("Curso: "+curso);
		System.out.println("Nivel: "+nivel);
		System.out.println("Turno: "+turno);
		System.out.println("nivel2: "+nivel2);
		System.out.println("abeviaturaCurso_Ou_Disciplina : "+abeviaturaCurso_Ou_Disciplina);
		
		System.out.println("nomeTurma: "+nomeTurma);
		
		
		ArrayList<String> desciplinas= p.pesquisarTudoEmString(BD, "cursos_disciplinas", curso);
		int quantCursos= 0;
		
		
		ArrayList<String> dessciplinasFiltrada = new ArrayList<>();
		
		ArrayList<String> desc = new ArrayList<>();
		String desc2;
		// Filtrando As Desciplinas do curso e do nivel selecionado !
		for (String desciplina : desciplinas) {
			System.out.println("Disci: "+desciplina);
			
			if(desciplina.contains(nivel2)) {

				++quantCursos;
				desc = ta.tirarCaracteres_RetornandoA_A_Lista(desciplina);
				desc2= desc.get(desc.size()-1);
				
				System.out.println("Desc2: "+desc2);
				
				dessciplinasFiltrada.add(ta.tirarCaracteres(desc2));
			}
		}
		
		
		ArrayList<String> turmas= pesquisar_Turmas_Do_Curso(BD, curso); 
	    String ttt;
			
			
			for (String turma : turmas) {
				
				
				
				
				   String[] t = turma.split("_");
					String tt = t[1];
					ttt = tt.toLowerCase();
					
					
				
				System.out.println("tt: "+tt);
				
				
				System.out.println("Valor: "+abeviaturaCurso_Ou_Disciplina+""+turno2);
				
				if(tt.endsWith(abeviaturaCurso_Ou_Disciplina+""+nivel2)) {
					
					
					if(ttt.contains("turma"+turno2.toLowerCase())) {
						
						System.out.println("Entrou");
							
								turmasFiltradas.add(turma);
							    System.out.println("Turma P Boletin: "+turma);
						


					}
					
					
				}
				
				
			}
		
			AlunoNotas aluno2 = new AlunoNotas();
			aluno2.setAlunoExiste(false);
			String alunoNome = aluno.getNome();
			
			if(alunoNome.endsWith(",,")) {
				
		    	String[]a = alunoNome.split(",,");
		    	
		    	if(a.length>0) {
		    		
		    		System.out.println(a[0]);
					alunoNome= 	a[0];
		    	}
		    	
			}
			System.out.println("ALUNO_NOME: "+alunoNome);
			
			int proc=0;
			int contador=0;
			int id=0;
			sair:
			for(String c : turmasFiltradas) {
				
				++contador;
				
				if(contador==1) {
					
					 proc = Integer.parseInt(bi);
				}
				if(bi.equals("")||
						(bi==null)) {
					
					
					id = p.pesquisarUmConteudo_Numa_Linha_Int(BD, c, "id", "Alunos", alunoNome, 0);
				}else {
					id = p.pesquisarUmConteudo_Numa_Linha_Int(BD, c, "id", "NProc", "", proc);
				}
				
				
				
				if(id>0) {
					
					System.out.println("Renderizando O Aluno");
					
					alunoNome = p.pesquisarUmConteudo_Numa_Linha_String(BD, c, "Alunos", "id", "", id);
					alunoFinanca = pesquisarDadosFinanceirosDoAluno(BD, c, id);
					alunoFinanca.setNome(alunoNome);
					alunoFinanca.setTurmaDoAluno(c);
					alunoFinanca.setId(id);
					
					
					
					break sair;
				}
			}
		
	
	   
	   return alunoFinanca;
   }
   
   
   
   public ArrayList<String>  Listar_TurmasDoCurso2(String BD,String turno,String nivel,
		   String curso) {
	   
	   String turno2=(String)turno.subSequence(0, 1);
	   String nivel2=(String)nivel.subSequence(0, 2);
	   
		
		
		String abeviaturaCurso_Ou_Disciplina=curso;
		abeviaturaCurso_Ou_Disciplina=abeviaturaCurso_Ou_Disciplina.substring(0, 3);
		
		
		
		 


		ArrayList<String> turmasFiltradas= new ArrayList<>();
		String nomeTurma="Turma"+turno2+""+
		             abeviaturaCurso_Ou_Disciplina+""+nivel2;
		
		System.out.println("Curso: "+curso);
		System.out.println("Nivel: "+nivel);
		System.out.println("Turno: "+turno);
		System.out.println("nivel2: "+nivel2);
		System.out.println("abeviaturaCurso_Ou_Disciplina : "+abeviaturaCurso_Ou_Disciplina);
		
		System.out.println("nomeTurma: "+nomeTurma);
		
		ArrayList<String> turmas= pesquisar_Turmas_Do_Curso(BD, curso); 
		String trecho="";
		int numero;
		
		
		
		
		for (String turma : turmas) {
			
			
			
			
			   String[] t = turma.split("_");
				String tt = t[1];
				
			
			System.out.println("tt: "+tt);
			
			
			System.out.println("Valor: "+abeviaturaCurso_Ou_Disciplina+""+turno2);
			
			if(tt.endsWith(abeviaturaCurso_Ou_Disciplina+""+nivel2)) {
				
				System.out.println("Entrou");
				
				turmasFiltradas.add(turma);
				
			}
			
			
		}
		
		
	   
	   return turmasFiltradas;
   }
   
   
   
   public Minhas_Financas pesquisarDadosFinanceirosDoAluno(String BD
		   ,String tabela_Turma,int idAluno){
	   

		
		
		String sql="select * from "+tabela_Turma+"_Financa";
		Minhas_Financas aluno = new Minhas_Financas();
		
		
		
		String usarBD="use "+BD;
		
		
		 Connection con=null;
		 ResultSet rs = null;
		 PreparedStatement stm=null ;
		
		try{
			con = ConnectionFactory.getConnection();
			
			stm = con.prepareStatement(sql);
			stm.executeUpdate(usarBD);
			
			rs =stm. executeQuery();
			
			
			
			
			
			sair:
			while(rs.next()) {
				
				
				
				if(idAluno==rs.getInt("id")) {
					
					
					aluno.setJaneiro(rs.getInt("Janeiro"));
					aluno.setFevereiro(rs.getInt("Fevereiro"));
					aluno.setMarco(rs.getInt("Marco"));
					aluno.setAbril(rs.getInt("Abril"));
					aluno.setMaio(rs.getInt("Maio"));
					aluno.setJunho(rs.getInt("Junho"));
					aluno.setJulho(rs.getInt("Julho"));
					aluno.setAgosto(rs.getInt("Agosto"));
					aluno.setSetembro(rs.getInt("Setembro"));
					aluno.setOutubro(rs.getInt("Outubro"));
					aluno.setNovembro(rs.getInt("Novembro"));
					aluno.setDezembro(rs.getInt("Dezembro"));
					aluno.setDataPagamento_Da_Propina(rs.getString("DataPagamento"));
					
					break sair;
				}
				
				
				
				
				
			}
			
			System.out.println("Pesquisa Concluida com Sucesso !!!");
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				con.close();
				stm.close();
				rs.close();
				 
				
				System.out.println("Coneccões Fechadas !!!");

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		
		
	
	   
	   return aluno;
   }




public ArrayList<Escola> Listar_Escolas_Na_WG(
		){
	
	String sql="select id,escola,contrato,telefone,alunos,valor,data,bi from escolas";
	ArrayList<Escola> escolas= new ArrayList<>();
	
	String usarBD="use wg";
	
	
	 Connection con=null;
	 ResultSet rs = null;
	 PreparedStatement stm=null ;
	
	try{
		con = ConnectionFactory.getConnection();
		
		stm = con.prepareStatement(sql);
		stm.executeUpdate(usarBD);
		
		rs =stm. executeQuery();
		
		
		
	    
		while(rs.next()) {
			
			
			
			    Escola escola= new Escola();
			    
			    escola.setAlunosQuant(rs.getInt("alunos"));
			    escola.setBi(rs.getString("bi"));
			    escola.setContrato(rs.getString("contrato"));
			    escola.setDate(rs.getString("data"));
			    escola.setId(rs.getInt("id"));
			    escola.setNomeEscola(rs.getString("escola"));
			    escola.setTel(rs.getString("telefone"));
			    escola.setValor(rs.getInt("valor"));
			    
				
			
			    escolas.add(escola);
          
		}
		
		
		
	}catch (Exception e) {
		e.printStackTrace();
	}
	finally {
		try {
			con.close();
			stm.close();
			rs.close();
			 
			
			System.out.println("Coneccões Fechadas !!!");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	return escolas;
}

public ArrayList<Curso> Listar_Cursos(
		String BD){
	
	
	String sql="select id,nome,coord from cursos";
	ArrayList<Curso> cursos= new ArrayList<>();
	
	String usarBD="use "+BD;
	
	
	 Connection con=null;
	 ResultSet rs = null;
	 PreparedStatement stm=null ;
	
	try{
		con = ConnectionFactory.getConnection();
		
		stm = con.prepareStatement(sql);
		stm.executeUpdate(usarBD);
		
		rs =stm. executeQuery();
		
		
		
	    
		while(rs.next()) {
			
			
			
			    Curso curso= new Curso();
			    
			     
			    curso.setId(rs.getInt("id"));
			    curso.setNome(rs.getString("nome"));
			    curso.setCoord(rs.getString("coord"));
			    
				
			
			    cursos.add(curso);
          
		}
		
		
		
	}catch (Exception e) {
		e.printStackTrace();
	}
	finally {
		try {
			con.close();
			stm.close();
			rs.close();
			 
			
			System.out.println("Coneccões Fechadas !!!");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	return cursos;
}
 
public ArrayList<String> usuario_Tem_Acesso(String usuario,String senha){
	 
	 TechShineController wg = new TechShineController();
	 Pesquisar_SQL p= new Pesquisar_SQL();
	 String usuarioBD;
	 
	 int id=0;
	 String bi;
	 String acesso_Personalizado;
	 String usuario2;
	 String conteudo;
	 
	 boolean retorno= false;
	 
	 
	 ArrayList<String> escolas = p.pesquisarTudoEmString2("wg", "escolas", "escola");
	 ArrayList<String> BIs_PCAS = p.pesquisarTudoEmString2("wg", "escolas", "bi");
	 ArrayList<String> escolas_Cadastradas = p.pesquisarTudoEmString("wg", "escolas", "escola");
	 
	 
	 String BD ;
	 ArrayList<String> retorno2= new ArrayList<>();
	 
	 if(usuario.endsWith("PCA")) {
		 
		 System.out.println("Entrou no PCA Acesso");
		 
		 if(BIs_PCAS.size()> 0) {
			 
			
			 
			 sair:
			 for (int i=0;i<BIs_PCAS.size();i++) {
				
				 System.out.println("BI: "+BIs_PCAS.get(i));
				 if(BIs_PCAS.get(i).equals(senha)) {
					 
					 
					 
					 
					 
					 retorno2.add(""+0);
					 retorno2.add(BIs_PCAS.get(i));
					 BD = escolas.get(i);
					 System.out.println("Nova BD: "+BD);
					 retorno2.add(BD);
					 
					 
					 System.out.println("BI Igual");
					 retorno=true;
					 break sair;
					 
				 }else {
					 
					 if(escolas.size()>0) {
						 
						 if(escolas.get(i).equalsIgnoreCase("")) {
							 
							   if(BIs_PCAS.get(i).equalsIgnoreCase(senha)){
								   
								   
								     retorno2.add(""+0);
									 retorno2.add(BIs_PCAS.get(i));
									 
									 BD = escolas.get(i);
									 System.out.println("Nova BD2: "+BD);
									 retorno2.add(BD);
									 
								   retorno=true;
								   break sair;
							   }
							 
							 }else {
								 
								 
								 if(escolas_Cadastradas.size()>0) {
									 
									 
									 
									 for (String escola : escolas_Cadastradas) {
										
										 
										 acesso_Personalizado= p.pesquisarUmConteudo_Numa_Linha_String(escola, "pca_"+escola, "bi", "acesso1", senha, 0);
										 usuario2 = p.pesquisarUmConteudo_Numa_Linha_String(escola, "pca_"+escola, "usuario", "acesso1", acesso_Personalizado, 0);
										 String bi2 = p.pesquisarUmConteudo_Numa_Linha_String(escola, "pca_"+escola, "bi", "acesso1", acesso_Personalizado, 0);
										 System.out.println("ACESSU PERSONALIZADO: "+acesso_Personalizado);
										 System.out.println("BI: "+bi2);
										 
										 if(acesso_Personalizado.equals(senha)&&
												 (usuario.equalsIgnoreCase(usuario2))) {
											 
											 
											 
											 retorno2.add(""+0);
											 retorno2.add(bi2);
											 retorno2.add(escola);
											
											 

											 retorno=true;
											 break sair;
									 }
								 
									}
								 }
								 
								 
								 
								 
							 }
						 
						 
					 }
					 
					 
					 
				 }
				 
			 }
		 }
		 
	 }else if(usuario.endsWith("AL")) {
		 
		 Tabela_Actualizar_SQL ta= new Tabela_Actualizar_SQL();
		 String quebrar;
		 String turma=null;
		 
		  sair:
			 for (String escola : escolas) {
				
				  String[]a= usuario.split(" ");
				  quebrar = a[1];
				  
				  
				  
				  ArrayList<String> tManha = p.pesquisarTudoEmString(escola, "controle_turmas", "Manha");
				  ArrayList<String> tTarde = p.pesquisarTudoEmString(escola, "controle_turmas", "Tarde");
				  ArrayList<String> tNoite = p.pesquisarTudoEmString(escola, "controle_turmas", "Noite");
					 
				  sair2:
				  for(String cadaC: tManha) {
					  
					  if(cadaC.startsWith(quebrar)) {
						  
						  turma = cadaC;
						  break sair2;
					  }
				  }
				  
				  sair3:
					  for(String cadaC: tTarde) {
						  
						  if(cadaC.startsWith(quebrar)) {
							  
							  turma = cadaC;
							  break sair3;
						  }
					  }
				  
				  sair4:
					  for(String cadaC: tNoite) {
						  
						  if(cadaC.startsWith(quebrar)) {
							  
							  turma = cadaC;
							  break sair4;
						  }
					  }
					  
					  
					  
					  if(turma!=null) {
						  
						  
						  id = p.pesquisarUmConteudo_Numa_Linha_Int(escola, turma+"_DadosPessoais", "id", "bi", senha, 0);
							 if(id==0) {
								 id = p.pesquisarUmConteudo_Numa_Linha_Int(escola, turma+"_Acesso", "id", "acesso1", senha, 0);
							 }
							 bi= p.pesquisarUmConteudo_Numa_Linha_String(escola, turma+"_DadosPessoais", "bi", "id", "", id);
							 acesso_Personalizado= p.pesquisarUmConteudo_Numa_Linha_String(escola, turma+"_acesso", "acesso1", "id", "", id);
							 usuarioBD = p.pesquisarUmConteudo_Numa_Linha_String(escola, turma+"_acesso", "usuario", "id", "", id);
								 
							 if(acesso_Personalizado.endsWith("_NP")) {
								 
								 this.mensagem2= "Não tem Permissão Para Aceder o Sistema";
								 
							 }else {
								 
								 if((acesso_Personalizado.equals(senha))&&(usuarioBD.equalsIgnoreCase(usuario))) {
							    	 
										
									 retorno2.add(""+id);
									 retorno2.add(bi);
									 retorno2.add(escola);
									 
									
									 retorno2.add(turma);
									 
									 
									 retorno=true;
							    	 break sair;
							     }
								 
							 }
							 
						  
					  }
				  
				  
				
			
			     
			 }
		 
	 }else if((usuario.contains("uclid"))||
			 (usuario.contains("elino"))||
			 (usuario.contains("ander"))) {
		 
		 
		 ArrayList<String> PCAs = p.pesquisarTudoEmString("wg", "PCA", "usuario");
		 ArrayList<String> BIs = p.pesquisarTudoEmString("wg", "PCA", "bi");
		 ArrayList<String> acessoConfigurado = p.pesquisarTudoEmString("wg", "PCA", "acesso2");
		 
		 sair:
		 for (int i = 0; i < PCAs.size(); i++) {
			
			 if((PCAs.get(i).equalsIgnoreCase(usuario))&&
					 (BIs.get(i).equalsIgnoreCase(senha))) {
				 
				 
				 
				 
				 retorno2.add(""+0);
				 retorno2.add(BIs.get(i));
				 retorno2.add("wg");
				 
				 retorno=true;
				 
				 break sair;
			 }
			 
			 if(acessoConfigurado.size()!=0) {
				 
				 
				 if((PCAs.get(i).equalsIgnoreCase(usuario))&&
						 (acessoConfigurado.get(i).equalsIgnoreCase(senha))) {
					 
					 
					 
					 retorno2.add(""+0);
					 retorno2.add(BIs.get(i));
					 retorno2.add("wg");
					 
					 System.out.println("Nome: "+PCAs.get(i));
					 retorno=true;
					 
				 }
				 
				  
			 }
		}
		 
		 
		 
		 
	 }else if((usuario.contains("Coord"))||
			 (usuario.contains("Secretaria"))||
			 (usuario.contains("Tesouraria"))||
			 (usuario.contains("Professor"))||
			 (usuario.contains("DP"))||
			 (usuario.contains("DA"))||
			 (usuario.contains("DG"))) {
		 
		 
			 ArrayList<String> acedendo_Ao_sistema = new ArrayList<>();
			 acedendo_Ao_sistema.add("Coord");
			 acedendo_Ao_sistema.add("Secretaria");
			 acedendo_Ao_sistema.add("Tesouraria");
			 acedendo_Ao_sistema.add("Professor");
			 acedendo_Ao_sistema.add("DP");
			 acedendo_Ao_sistema.add("DA");
			 acedendo_Ao_sistema.add("DG");
			 
			 
			 sair:
			 for (String escola : escolas) {
				 
				 System.out.println("Escola: "+escola);
				 for (String func_BD : acedendo_Ao_sistema) {
					 System.out.println("Funcionario: "+func_BD);
					 if((usuario.toLowerCase()).contains(func_BD.toLowerCase())) {
						 
						 System.out.println("Contém E Entrou !!!");
						 
						 System.out.println("Senha: "+senha);
						 id = p.pesquisarUmConteudo_Numa_Linha_Int(escola, func_BD+"_DadosPessoais", "id", "bi", senha, 0);
						    
						 if(id==0) {
							 id = p.pesquisarUmConteudo_Numa_Linha_Int(escola, func_BD+"_Acesso", "id", "acesso1", senha, 0);
						 }
							 bi= p.pesquisarUmConteudo_Numa_Linha_String(escola, func_BD+"_DadosPessoais", "bi", "id", "", id);
							 usuarioBD = p.pesquisarUmConteudo_Numa_Linha_String(escola, func_BD+"_acesso", "usuario", "id", "", id);
						 
						 acesso_Personalizado= p.pesquisarUmConteudo_Numa_Linha_String(escola, func_BD+"_acesso", "acesso1", "id", "", id);
					
						 
						 if(acesso_Personalizado.endsWith("_NP")) {
							 
							 this.mensagem2= "Não tem Permissão Para Aceder o Sistema";
							 
						 }else {
							 
							 if((acesso_Personalizado.equals(senha))&&(usuarioBD.equalsIgnoreCase(usuario))){
								 
									
								 System.out.println("BI Igual\nUsuario Ifual");
								 retorno2.add(""+id);
								 retorno2.add(bi);
								 retorno2.add(escola);
						    	 
								 retorno=true;
						    	 break sair;
						     }
							 
						 }
					     
						
					 
					 }
				}
				 
			 }
			 
			 
		 
		 
		 
	 }
	
	
	 return retorno2;
	 
	 
 }
 
   public ArrayList<String> listarFuncionarios() {
	   
	   ArrayList<String> funcionarios= new ArrayList<>();
		  	 
	   funcionarios.add("Professor");
	   funcionarios.add("Secretaria");
	   funcionarios.add("Tesouraria");
	   funcionarios.add("Coord");
	   funcionarios.add("DG");
	   funcionarios.add("DA");
	   funcionarios.add("DP");
	   
	   return funcionarios;
   }
   

 
   
   
  
  
}
