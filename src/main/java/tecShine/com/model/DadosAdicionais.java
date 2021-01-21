package tecShine.com.model;

import java.util.ArrayList;

public class DadosAdicionais {

	
	
	private String disciplina;
	private String turma;
	private String pagina;
	private ArrayList<String> turmas = new ArrayList<>();
	private  Aluno aluno = new Aluno();
	
	
	
	
	public Aluno getAluno() {
		return aluno;
	}
	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}
	public ArrayList<String> getTurmas() {
		return turmas;
	}
	public void setTurmas(ArrayList<String> turmas) {
		this.turmas = turmas;
	}
	public String getPagina() {
		return pagina;
	}
	public void setPagina(String pagina) {
		this.pagina = pagina;
	}
	public String getDisciplina() {
		return disciplina;
	}
	public void setDisciplina(String disciplina) {
		this.disciplina = disciplina;
	}
	public String getTurma() {
		return turma;
	}
	public void setTurma(String turma) {
		this.turma = turma;
	}
	
	
}
