package tecShine.com.model;

public class Usuario2 {




	
	private String integrante;
	private String BD;
	private String senha;
	private String nome;
	private String escola;
	private int id;
	private String bi;
	private boolean existeUsuario;
	private String turma;
	private String mensagem;
	private String pagina;
	//private List < String > products;
	
	
	

	public String getPagina() {
		return pagina;
	}

	public void setPagina(String pagina) {
		this.pagina = pagina;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public String getTurma() {
		return turma;
	}

	public void setTurma(String turma) {
		this.turma = turma;
	}

	public boolean isExisteUsuario() {
		return existeUsuario;
	}

	public void setExisteUsuario(boolean existeUsuario) {
		this.existeUsuario = existeUsuario;
	}
	
	public String getIntegrante() {
		return integrante;
	}
	public void setIntegrante(String integrante) {
		this.integrante = integrante;
	}
	public String getBD() {
		return BD;
	}
	public void setBD(String BD) {
		this.BD = BD;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEscola() {
		return escola;
	}
	public void setEscola(String escola) {
		this.escola = escola;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getBi() {
		return bi;
	}
	public void setBi(String bi) {
		this.bi = bi;
	}
	
}
