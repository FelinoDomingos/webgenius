package tecShine.com.OutrasOperacoes;

import java.util.ArrayList;

import tecShine.com.model.Usuario;

public class Operacoes {

	
	public int operacoesAdicionais(ArrayList<Integer> conteudo){
		
		int valor=0;
		for (Integer v : conteudo) {
			
			valor= valor + v;
		}
		
		return valor;
	}
	
	
	public Usuario retornarUsuario2(ArrayList<Usuario> usuarios,String idSessao){
		
		
		Usuario usuario = null;
		sair:
		for (Usuario cadaC : usuarios) {
			
			if(cadaC.getIdSessao().equalsIgnoreCase(idSessao)) {
				
				usuario = new Usuario();
				usuario = cadaC;
				break sair;
			}
		}
		
		return usuario;
	}
}
