package tecShine.com.repository;

import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.annotation.Validated;

import tecShine.com.model.Aluno;
import tecShine.com.model.RecuperarSenha;

public interface AlunoRepository {

	
	public String aluno3(Aluno aluno);
	public String materias(HttpServletResponse response, Aluno aluno);
	public String seguranca2(@Validated RecuperarSenha recuperar);
}
