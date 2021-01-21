package tecShine.com.service;


import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import tecShine.com.model.Usuario;
import tecShine.com.repository.TecShineRepository;

@Service
public class TecShineService implements TecShineRepository{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final String sessao="session";
	public static ArrayList<Usuario> usuarios = new ArrayList<>();
	
	
	@Override
	public boolean logarUsuario(Usuario usuario,HttpServletRequest request)  {

		
		boolean usuarioLogado = false;
         
		if( getUsuario() == null ) { 
			
			RequestAttributes ra =RequestContextHolder.getRequestAttributes();
			ra.setAttribute(TecShineService.sessao, usuario, RequestAttributes.SCOPE_SESSION);
			String idSessao = ra.getSessionId();
			usuario.setIdSessao(idSessao);
			System.out.println("Sessao Usuaro Actual :"+ idSessao);
			TecShineService.usuarios.add(usuario);
			usuarioLogado = true;
			
        } else {
        	
        	HttpSession session = request.getSession();
        	System.out.println("Sessao Usuaro Actual :"+ session.getId());
        	
        	session.invalidate();
        	System.out.println("Já existe um usuário logado nesta session.");
        }
		return usuarioLogado;
	}
	@Override
	public Usuario getUsuario() {
        
		
		try {
            return (Usuario) RequestContextHolder.getRequestAttributes().getAttribute(TecShineService.sessao, RequestAttributes.SCOPE_SESSION);
        } catch (Exception e) {
            return null;
        }
		
	}
	@Override
	public void removerUsuario(Usuario usuario) {
		// TODO Auto-generated method stub
		
		if( getUsuario() != null ) {
			RequestContextHolder.getRequestAttributes().removeAttribute(TecShineService.sessao, RequestAttributes.SCOPE_SESSION);
		}
		
		
	}
	@Override
	public void retornarSessao() {
		
	}
	
	

	
	
}
