package tecShine.com.model;

public class Coordenador {

	
	private boolean avaliacao;
	private boolean prova;
	private boolean exame;
	private boolean bloqueio;
	private String curso;
	private int qProfs;
	
	
	
	
	public String getCurso() {
		return curso;
	}


	public void setCurso(String curso) {
		this.curso = curso;
	}


	public int getqProfs() {
		return qProfs;
	}


	public void setqProfs(int qProfs) {
		this.qProfs = qProfs;
	}


	public boolean isBloqueio() {
		return bloqueio;
	}


	public void setBloqueio(boolean bloqueio) {
		this.bloqueio = bloqueio;
	}


	private String trimestre;


	public boolean isAvaliacao() {
		return avaliacao;
	}


	public void setAvaliacao(boolean avaliacao) {
		this.avaliacao = avaliacao;
	}


	public boolean isProva() {
		return prova;
	}


	public void setProva(boolean prova) {
		this.prova = prova;
	}


	public boolean isExame() {
		return exame;
	}


	public void setExame(boolean exame) {
		this.exame = exame;
	}


	public String getTrimestre() {
		return trimestre;
	}


	public void setTrimestre(String trimestre) {
		this.trimestre = trimestre;
	}
	
	
	
}
