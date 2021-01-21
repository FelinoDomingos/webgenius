package tecShine.com.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.context.annotation.SessionScope;

import tecShine.com.dao.Login;
import tecShine.com.model.LoginIntegrantes;
import tecShine.com.model.Usuario;
import tecShine.com.model.Usuario2;
import tecShine.com.service.TecShineService;
import tecShine.com.sessao.SessaoActual;


@Controller
@SessionScope
@SessionAttributes("usuario")
public class LoginController {

	
	
	private TecShineService ts = new TecShineService();
	private TechShineController tc = new TechShineController();
	private Login entrarNoSistema = new Login();
	
	
	@GetMapping( {"/","/login","/webgenius/login"})
	public String login() {
		
		

		
		    String pagina;
			pagina = "TechShine/login";
			return pagina;
		
	}
	
	@PostMapping({"/login","/webgenius/webgenius/login","/webgenius/login"})
	public String login2(LoginIntegrantes login, HttpServletRequest request,
			HttpServletResponse response,
			final Model model, @ModelAttribute Usuario usuario) {
		
		
		
		
		
		
			
		SessaoActual sa = new SessaoActual();
    	
		 String pagina= null; 
 
		try {
			Thread.sleep(5000);
		    Usuario2 usuario2  = tc.login2(login, request, response);
		    
		    System.out.println("tA AQUI");
		    if(usuario!=null) {
		    	
		    	
		    	if(usuario.isExisteUsuario()) {
		    		
		    		return "redirect:fechar-a-conta-amterior";
		    	}else {
		    		
		    		  //shoppingCart.setProduct(productCode);
			    	
			    	System.out.println("eNTROU aQUItA AQUI");
					 
			    	
					 usuario.setBD(usuario2.getBD());
					 usuario.setBi(usuario2.getBi());
					 usuario.setEscola(usuario2.getEscola());
					 usuario.setExisteUsuario(usuario2.isExisteUsuario());
					 usuario.setId(usuario2.getId());
					 usuario.setIntegrante(usuario2.getIntegrante());
					 usuario.setMensagem(usuario2.getMensagem());
					 usuario.setNome(usuario2.getNome());
					 usuario.setPagina(usuario2.getPagina());
					 usuario.setSenha(usuario2.getSenha());
					 usuario.setTurma(usuario2.getTurma());
			    	  
					 System.out.println("pagina 2 :"+pagina); 
					 System.out.println("usuario.getBD() : "+usuario.getBD());
					 
					 model.addAttribute("usuario", usuario);
					 
					 
					 //return "redirect:login";
					 
					 pagina = usuario.getPagina();
					 return pagina;
		    		
		    	}
		    	
		    	 
				//sa.setarNoRequest(usuario);
				
				/*
		    	boolean usuarioLogado = ts.logarUsuario(usuario,request);
		    	if(usuarioLogado) {
		    		
		    		 
		    		 pagina = usuario.getPagina();
		    	}else {
		    		pagina="redirect:login";
		    	}
				
				*/
			   
		    }else {
		    	
		    	System.out.println("aGORA tA AQUI");
		    	if(usuario2 != null) {
		    		
		    		System.out.println("vEIO tA AQUI");
		    		
		    		 Usuario user = new Usuario();
		    		 
		    		  pagina = usuario2.getPagina();
		    		  
		    		 
			    	  user.setBD(usuario2.getBD());
			    	  user.setBi(usuario2.getBi());
			    	  user.setEscola(usuario2.getEscola());
			    	  user.setExisteUsuario(usuario2.isExisteUsuario());
			    	  user.setId(usuario2.getId());
			    	  user.setIntegrante(usuario2.getIntegrante());
			    	  user.setMensagem(usuario2.getMensagem());
			    	  user.setNome(usuario2.getNome());
			    	  user.setPagina(usuario2.getPagina());
			    	  user.setSenha(usuario2.getSenha());
			    	  user.setTurma(usuario2.getTurma());
			    	  
			    	   model.addAttribute("user", user);
			    	   System.out.println("pagina 2 :"+pagina);
			    	   System.out.println("pagina 2.2 :"+pagina.toLowerCase());
			    	   return pagina.toLowerCase();
			    	   
		    	}else {
		    		
		    		System.out.println("OLHA tA AQUI");
		    		
		    		return "redirect:login";
		    	}
		    	 
		    }
		    
			
		}catch(Exception e) {
			System.out.println("iNFELIZMENTE TAtA AQUI");
			e.printStackTrace();
		}
		
		System.out.println("pagina 3 :"+pagina);
			return pagina;
		
	}
	
	
	
	
	
	
	
	/*
	
	 @GetMapping("/product-detail-page")
	 public String viewPDP(Model model, @ModelAttribute("shoppingCart") ShoppingCart shoppingCart) {
	  if (shoppingCart != null) {
	   model.addAttribute("cart", shoppingCart);
	  } else {
	   model.addAttribute("cart", new ShoppingCart());
	  }
	  return "product";
	 }
	 
	 
	 */

	 @ModelAttribute("usuario")
	 public Usuario usuario() {
	  return new Usuario();
	 }
	
	
}
