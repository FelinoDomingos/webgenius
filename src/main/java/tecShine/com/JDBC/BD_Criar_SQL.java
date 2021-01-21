package tecShine.com.JDBC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Essa Classe Vai Criar Uma Base de Dados
 * Para Cada Escola, Ela será criada na Fase 2
 * de Registo do sistema feito pelo Admin 
 *   
 * @author Jose Euclides Pedro Pereira dos Santos
 * @version 1.0
 * @see ConnectionFactory
 *
 */
public class BD_Criar_SQL {

	private  ConnectionFactory cf= new ConnectionFactory();
/**
 * Esse Metodo Abaixo vai criar a devida Base De Dados, 
 * Ela vai receber o parametro nomeDaBD, que é o nome
 * da ewscola fornecido pelo Ad
 * 	
 * @param nomeDaBD
 * @return
 */
	
	/*
public  String criarBaseDeDados( String nomeDaBD){
		
		String sql="create database "+nomeDaBD;
		
		 Connection con=null;
		 PreparedStatement stm=null ;
		
		try{
			con = ConnectionFactory.getConnection();
			stm = con.prepareStatement(sql);
			stm.executeUpdate();
			
		
			System.out.println("Base De Dado Criada !!!");
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				con.close();
				stm.close();
				System.out.println("Todas conexoes Fechadas");

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		return null;
	}

  */
}
