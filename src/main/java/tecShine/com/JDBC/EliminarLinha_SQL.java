package tecShine.com.JDBC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import tecShine.com.model.Conexao;

public class EliminarLinha_SQL {

	
	public boolean EliminarLinha(String BD,String tabela,String coluna,
			int conteuEm_Inteiro,String conteuEm_String,boolean e_Um_Curso) {
        
		
		boolean eliminado=false;
		String sql;
		String usarBD="use "+BD;
		if(conteuEm_Inteiro>0) {
			sql="delete from "+tabela+" where "+coluna+"= "+conteuEm_Inteiro+";";
		    System.out.println(sql);
		}else {
			sql="delete from "+tabela+" where "+coluna+"='"+conteuEm_String+"'";
			System.out.println(sql);
		}
		ConnectionFactory cf= new ConnectionFactory();


		Connection con=null;
		PreparedStatement stm=null ;


		

		try{
			
			Conexao conexao = new Conexao();
			conexao.setBD(BD);
			con = ConnectionFactory.getConnection(BD);

			stm = con.prepareStatement(sql);
			stm.executeUpdate(usarBD);
			stm.executeUpdate();

			System.out.println("Linha Eliminada com  Sucesso !!!");
			eliminado=true;

		}catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				con.close();
				stm.close();
				 
				cf=null;

				System.out.println("Coneccões Fechadas !!!");

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		
		if(e_Um_Curso) {
			Tabela_Actualizar_SQL t = new Tabela_Actualizar_SQL();
			int conteudo= t.retornarUmConteudo_Int(BD, "cursos", "QCurso");
			--conteudo;
			t.actualizarColuna_Na_PrimeiraLinha(BD, "cursos", "QCurso", conteudo);
		}
		
		return eliminado;
	}
	
	
	public void EliminarTodasLinhas(String BD,String tabela,String coluna,
			int conteuEm_Inteiro,String conteuEm_String) {
        
		String sql="";
		String usarBD="use "+BD;
		if(conteuEm_Inteiro>0) {
			sql="delete from "+tabela+" where "+coluna+"<= "+conteuEm_Inteiro+";";
		    System.out.println(sql);
		}
		ConnectionFactory cf= new ConnectionFactory();


		Connection con=null;
		PreparedStatement stm=null ;


		

		try{
			
			Conexao conexao = new Conexao();
			conexao.setBD(BD);
			
			
			con = ConnectionFactory.getConnection(BD);

			stm = con.prepareStatement(sql);
			stm.executeUpdate(usarBD);
			stm.executeUpdate();

			System.out.println("Linha Eliminada com  Sucesso !!!");


		}catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				con.close();
				stm.close();
				 
				cf=null;

				System.out.println("Coneccões Fechadas !!!");

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}
	
	
	
}
