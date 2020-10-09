package tecShine.com.model;



public class Turma {
    
	

	private String id;
	private int nAlunos;
	private String curso;
	private String turno;
	private String nivel;
	private String aluno;
	private String sigla;
	private String turma;
	
	private int nDoProcesso;
	private int nDaSala;
	private String bi;
	private String usuario;
	private String acessoPersonalizado1;
	private String acessoPersonalizado2;
	private boolean alunoInserido;
	
	
	
	
	
	
	
	
	public boolean isAlunoInserido() {
		return alunoInserido;
	}
	public void setAlunoInserido(boolean alunoInserido) {
		this.alunoInserido = alunoInserido;
	}
	public String getTurma() {
		return turma;
	}
	public void setTurma(String turma) {
		this.turma = turma;
	}
	public String getSigla() {
		return sigla;
	}
	public void setSigla(String sigla) {
		this.sigla = sigla;
	}
	public String getAluno() {
		return aluno;
	}
	public void setAluno(String aluno) {
		this.aluno = aluno;
	}
	public int getnDoProcesso() {
		return nDoProcesso;
	}
	public void setnDoProcesso(int nDoProcesso) {
		this.nDoProcesso = nDoProcesso;
	}
	public int getnDaSala() {
		return nDaSala;
	}
	public void setnDaSala(int nDaSala) {
		this.nDaSala = nDaSala;
	}
	public String getBi() {
		return bi;
	}
	public void setBi(String bi) {
		this.bi = bi;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getAcessoPersonalizado1() {
		return acessoPersonalizado1;
	}
	public void setAcessoPersonalizado1(String acessoPersonalizado1) {
		this.acessoPersonalizado1 = acessoPersonalizado1;
	}
	public String getAcessoPersonalizado2() {
		return acessoPersonalizado2;
	}
	public void setAcessoPersonalizado2(String acessoPersonalizado2) {
		this.acessoPersonalizado2 = acessoPersonalizado2;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public int getnAlunos() {
		return nAlunos;
	}
	public void setnAlunos(int nAlunos) {
		this.nAlunos = nAlunos;
	}
	public String getCurso() {
		return curso;
	}
	public void setCurso(String curso) {
		this.curso = curso;
	}
	public String getTurno() {
		return turno;
	}
	public void setTurno(String turno) {
		this.turno = turno;
	}
	public String getNivel() {
		return nivel;
	}
	public void setNivel(String nivel) {
		this.nivel = nivel;
	}
	
	
}
