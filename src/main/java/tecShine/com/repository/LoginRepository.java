package tecShine.com.repository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tecShine.com.model.LoginIntegrantes;
import tecShine.com.model.Usuario;
import tecShine.com.model.Usuario2;

public interface LoginRepository {

	
	public Usuario2 login2(LoginIntegrantes login, HttpServletRequest request,
			HttpServletResponse response);
}
