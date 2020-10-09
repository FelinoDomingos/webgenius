package tecShine.com.dao;

import java.util.ArrayList;

import tecShine.com.JDBC.Pesquisar_SQL;


/**
 * 
 * 
 * @author José Euclides Pedro Pereira dos Santos
 *
 */
public class Verificar_Login {


	
	
	
	
	

	
	
public ArrayList<String>  existeNaWG(String senha,String usuario){
		
	
	
		Pesquisar_SQL p = new Pesquisar_SQL();
		ArrayList<String> retorno=  p.usuario_Tem_Acesso(usuario, senha);
		
		return retorno;
		
	}
    
    

    
    /*
     * 
     * Esse Metodo Vai Pegar O nome completo do Individuo
     * A ser Cadastrado, neste nome completo vai pegar o 
     * Primeiro nome e o ultimo para servirem como senha de 
     * Acesso a WG
     */
    
    
    public  ArrayList<String> acessoAWG(String nomeCompleto) {
		
    	ArrayList<String> nomes= new ArrayList<>();
    	String[] conteudos = nomeCompleto.split(" ");
    	String primeiroNome= conteudos[0];
    	String segundoNome= conteudos[conteudos.length-1];
    	
    	nomes.add(primeiroNome);
    	nomes.add(segundoNome);
    	
    	return nomes;
    	
	}
    
    public String usuarioAcesso(ArrayList<String> primeiroEUltimoNome){
    	int contador=0;
    	String acesso="";

		
		for(String cadaC: primeiroEUltimoNome) {
			++contador;
			if(contador==1) {
				
				acesso=acesso+""+cadaC+" ";
			}else {
				acesso=acesso+""+cadaC;
			}
			
		}
		
		return acesso;
    	
    }
}
