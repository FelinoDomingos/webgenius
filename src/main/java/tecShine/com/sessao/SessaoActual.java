package tecShine.com.sessao;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import tecShine.com.model.Usuario;

public class SessaoActual {
 
	

	//AuthenticationManager authManager;


	public HttpSession retornarSessao(){
		
		
			
			ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
			HttpSession session = attr.getRequest().getSession(true);

			return session;
		}


	public HttpServletRequest retornarRequisicao(){
		
		
		
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		HttpServletRequest request = attr.getRequest();

		return request;
	}


	   public void autenticarUsuario(HttpServletRequest req,Usuario usuario){
		   
		   
		   //UsernamePasswordAuthenticationTo authentication = new UsernamePasswordAuthenticationToken(usuario, null);

		   //Authentication auth = authManager.authenticate(authentication);
		   //SecurityContextHolder.getContext().setAuthentication(auth);
		   
		  // SecurityContext sc = SecurityContextHolder.getContext();
	      // sc.setAuthentication(authentication);
		   
	       
		   
	       //HttpSession session = req.getSession(true);
	       //session.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, sc);
		   
		    
	    
	    
		   
	   }
	   
}
