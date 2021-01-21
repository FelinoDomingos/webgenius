package tecShine.com.servicos;

import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import tecShine.com.model.Usuario;
import tecShine.com.repository.IUsuarioSession;


@Service
public class TecShineServicos implements IUsuarioSession{

	
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    private static final String KEY_SESSION_USER = "session";
    

	@Override
    public void logarUser(Usuario usuario) throws Exception {
        if( getUserLogado() == null ) { RequestContextHolder.getRequestAttributes().setAttribute(TecShineServicos.KEY_SESSION_USER, usuario, RequestAttributes.SCOPE_SESSION);
        } else {
            throw new Exception("Já existe um usuário logado nesta session.");
        }
    }
 
    @Override
    public void logout() throws Exception {
        if( getUserLogado() != null ) {
            RequestContextHolder.getRequestAttributes().removeAttribute(TecShineServicos.KEY_SESSION_USER, RequestAttributes.SCOPE_SESSION);
        } else {
            throw new Exception("Já existe um usuário logado nesta session.");
        }
    }
 
    @Override
    public Usuario getUserLogado() {
        try {
            return (Usuario) RequestContextHolder.getRequestAttributes().getAttribute(TecShineServicos.KEY_SESSION_USER, RequestAttributes.SCOPE_SESSION);
        } catch (Exception e) {
            return null;
        }
    }
	
	
}
