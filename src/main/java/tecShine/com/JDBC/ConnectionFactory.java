package tecShine.com.JDBC;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.springframework.stereotype.Repository;

import tecShine.com.controller.TechShineController;




/**
 * Essa Classe Vai Criar UmaConexao com 
 * O BD MySQL e  simplesmente passar a copia dessa 
 * Coneccao
 *   
 * @author Jose Euclides Pedro Pereira dos Santos
 * @version 1.0
 * @see ConnectionFactory
 *
 */
@Repository
public class ConnectionFactory {

	private Connection con=null;
	
	public void fecharConexao(){
		
		try {
			con.close();
			System.out.println("Conexao Principal Fechada");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/*
public static Connection getConnection(){
		
		
		TechShineController tc = new TechShineController();
		Connection con=null;
		
		  		
		try {

			Class.forName("com.mysql.cj.jdbc.Driver");
			String url="jdbc:mysql://localhost:3306/MySQL?useTimezone=true&serverTimezone=UTC";
			String user="root";
			String password="1+mM?!--ATtWG*";

			con =DriverManager.getConnection(url, user, password);
			
			
			System.out.println("Conectado");



		}catch (Exception e) {
			// TODO: handle exception
			System.out.println("Erro Ao se Conectar");
			tc.erroDeConnexao();
			//e.printStackTrace();
			
			
		}
		
		return con;
	}
*/
	public static Connection getConnection() throws URISyntaxException, SQLException {
	    URI dbUri = new URI(System.getenv("CLEARDB_DATABASE_URL"));

	    String username = dbUri.getUserInfo().split(":")[0];
	    String password = dbUri.getUserInfo().split(":")[1];
	    String dbUrl = "jdbc:mysql://" + dbUri.getHost() + dbUri.getPath();

	    return DriverManager.getConnection(dbUrl, username, password);
	}

	
	/*
	 * 
	 * Conexao Ao POSTGRESSQL
	 */
	
	/*
public static Connection getConnection(){
		
	
		
		
	Connection con=null; 		
		try {

			Class.forName("org.postgresql.Driver");
			String url="jdbc:postgresql://localhost:5432/hsegura_tutorial";
			String user="root";
			String password="1+mM?!--ATtWG*";

			 con =DriverManager.getConnection(url, user, password);
			
			
			System.out.println("Conectado");



		}catch (Exception e) {
			// TODO: handle exception
			System.out.println("Erro Ao se Conectar");
			//e.printStackTrace();
			
			
		}
		
		return con;
	}

   /*
	@Profile("prod")
	public static Connection getConnection() throws URISyntaxException, SQLException {
	    URI dbUri = new URI(System.getenv("DATABASE_URL"));

	    String username = dbUri.getUserInfo().split(":")[0];
	    String password = dbUri.getUserInfo().split(":")[1];
	    String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + ':' + dbUri.getPort() + dbUri.getPath() + "?sslmode=require";

	    return DriverManager.getConnection(dbUrl, username, password);
	}
	*/
	/*
	@Profile("dev")
	public  Connection getConnection(){
		
		
		TechShineController tc = new TechShineController();
		
		  		
		try {

			Class.forName("com.mysql.cj.jdbc.Driver");
			String url="jdbc:mysql://localhost:3306/MySQL?useTimezone=true&serverTimezone=UTC";
			String user="root";
			String password="1+mM?!--ATtWG*";

			con =DriverManager.getConnection(url, user, password);
			
			
			System.out.println("Conectado");



		}catch (Exception e) {
			// TODO: handle exception
			System.out.println("Erro Ao se Conectar");
			tc.erroDeConnexao();
			//e.printStackTrace();
			
			
		}
		
		return con;
	}
	
	/*
	
	@Profile("prod")
	private static Connection getConnection() throws URISyntaxException, SQLException {
	    URI dbUri = new URI(System.getenv("DATABASE_URL"));

	    String username = dbUri.getUserInfo().split(":")[0];
	    String password = dbUri.getUserInfo().split(":")[1];
	    String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + ':' + dbUri.getPort() + dbUri.getPath() + "?sslmode=require";

	    return DriverManager.getConnection(dbUrl, username, password);
	}
	
	
	/*
	 * 
	 * String url = "jdbc:postgresql://localhost:5432/hsegura_tutorial";
String usuario = "hsegura_gerente";
String senha = "123456";
try {
    Class.forName("org.postgresql.Driver");
    Connection conexao = DriverManager.getConnection(url, usuario, senha);
} catch (ClassNotFoundException e) {
    // Erro caso o driver JDBC não foi instalado
    e.printStackTrace();
} catch (SQLException e) {
    // Erro caso haja problemas para se conectar ao banco de dados
    e.printStackTrace();

	 */
	

	
	 

}
