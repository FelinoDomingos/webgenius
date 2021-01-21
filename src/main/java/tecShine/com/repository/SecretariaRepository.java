package tecShine.com.repository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.Model;

import tecShine.com.model.Aluno;

public interface SecretariaRepository {

	
	public String Admin_Cadastrar__Aluno(HttpServletRequest request,Aluno aluno, Model model);
	public String matricularAluno(Model model,Aluno aluno) ;
	public String secretariaBoletin2(Model model, Aluno aluno);
	public String secretariaMatricularAluno(HttpServletResponse response,Aluno aluno, Model model);
}
