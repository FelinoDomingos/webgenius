package tecShine.com.JDBC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;





/**
 * Essa Classe Vai controlar os ultimos IDs
 * da Tabela, para que não sejam implementados
 * IDs iguais 
 * 
 * 
 * @author Jose Euclides Pedro Pereira dos Santos
 * @version 1.0
 * @see ConnectionFactory
 * 
 *
 */
public class Controle_ID_SQL {

	private  ConnectionFactory cf= new ConnectionFactory();
	
/**
 * Esse Metodo vai recuperar o ultimo 
 * ID de uma especifica BD, tabela e coluna
 * 	
 * @param BD
 * @param tabela
 * @param coluna
 * @return contador
 */
	
	
	
public  int recuperarCodigo(String BD,String tabela,String coluna){
		
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
				
				
				++contador;
				
			}
			
			System.out.println("ultimo codigo: "+contador);
			
			
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


}
