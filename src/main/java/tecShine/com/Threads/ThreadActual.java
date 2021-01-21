package tecShine.com.Threads;

import java.util.ArrayList;

import tecShine.com.model.Usuario;

public class ThreadActual {

	
	
	public Usuario retornarThreadActual(ArrayList<Usuario> usuarios) {
		
		Usuario usuario = null;
		long idThreadActual = Thread.currentThread().getId();
		
		sair:
		for(Usuario cadaC : usuarios) {
			
			/*
			if(cadaC.getIdThread()== idThreadActual) {
				
				usuario = new Usuario();
				usuario = cadaC ;
				
				
				
				break sair;
			}
			*/
		}
		
		return usuario;
	}
	
	
	
	
	
	
public void removerThreadActual(ArrayList<Usuario> usuarios) {
		
		long idThreadActual = Thread.currentThread().getId();
		
		sair:
		for(Usuario cadaC : usuarios) {
			
			/*
			if(cadaC.getIdThread()== idThreadActual) {
				
				usuarios.remove(cadaC);
				
				break sair;
			}
			
			*/
		}
		
	}
}
