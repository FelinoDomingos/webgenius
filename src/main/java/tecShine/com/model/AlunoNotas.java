package tecShine.com.model;

import java.util.ArrayList;

public class AlunoNotas {

	
	private ArrayList<String> desciplinas = new ArrayList<>();
	private ArrayList<Integer> notas = new ArrayList<>();
	private boolean alunoExiste;
	private String situacaoFinal;
	
	
	
	public String getSituacaoFinal() {
		return situacaoFinal;
	}
	public void setSituacaoFinal(String situacaoFinal) {
		this.situacaoFinal = situacaoFinal;
	}
	public boolean isAlunoExiste() {
		return alunoExiste;
	}
	public void setAlunoExiste(boolean alunoExiste) {
		this.alunoExiste = alunoExiste;
	}
	public ArrayList<String> getDesciplinas() {
		return desciplinas;
	}
	public void setDesciplinas(ArrayList<String> desciplinas) {
		this.desciplinas = desciplinas;
	}
	public ArrayList<Integer> getNotas() {
		return notas;
	}
	public void setNotas(ArrayList<Integer> notas) {
		this.notas = notas;
	}
	
	
}
