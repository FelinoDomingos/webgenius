package tecShine.com.repository;

import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;

import tecShine.com.model.Usuario;

public interface TecShineRepository extends Serializable{

	
	public boolean logarUsuario(Usuario usuario,HttpServletRequest request);
	public Usuario getUsuario();
	public void removerUsuario(Usuario usuario);
	public void retornarSessao();
}
