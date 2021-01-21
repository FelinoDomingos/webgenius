package tecShine.com.repository;

import java.io.Serializable;

import tecShine.com.model.Usuario;

public interface IUsuarioSession extends Serializable{

	
	public void logarUser( Usuario usuario ) throws Exception;
	public void logout() throws Exception;
    public Usuario getUserLogado() throws Exception;
}
