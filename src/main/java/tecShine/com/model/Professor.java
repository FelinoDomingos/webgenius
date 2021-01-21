package tecShine.com.model;

import java.util.ArrayList;

import org.springframework.web.multipart.MultipartFile;

public class Professor {

	private MultipartFile ficheiro;
	private String turma;
	private String desciplina;
	private ArrayList<Integer> notasDosAlunos= new ArrayList<>();
	private String curso;
	private String nivel;
	private String descricao;
	private String idFunc;
	 
	
	
	
	
	public String getIdFunc() {
		return idFunc;
	}
	public void setIdFunc(String idFunc) {
		this.idFunc = idFunc;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public String getNivel() {
		return nivel;
	}
	public void setNivel(String nivel) {
		this.nivel = nivel;
	}
	public ArrayList<Integer> getNotasDosAlunos() {
		return notasDosAlunos;
	}
	public void setNotasDosAlunos(ArrayList<Integer> notasDosAlunos) {
		this.notasDosAlunos = notasDosAlunos;
	}
	public String getCurso() {
		return curso;
	}
	public void setCurso(String curso) {
		this.curso = curso;
	}
	
	
	
	
	public MultipartFile getFicheiro() {
		return ficheiro;
	}
	public void setFicheiro(MultipartFile ficheiro) {
		this.ficheiro = ficheiro;
		
	}
	public String getDesciplina() {
		return desciplina;
	}
	public void setDesciplina(String desciplina) {
		this.desciplina = desciplina;
	}
	
	public String getTurma() {
		return turma;
	}
	public void setTurma(String turma) {
		this.turma = turma;
	}
	
	
	
}
