package tecShine.com.model;

public class Curso {

	
	private int id;
	private String nome;
	private String professor;
	private String lancou;
	private int cargaHoraria;
	
	private boolean inserir_Disciplinas;
	private String coord;
	private int qProfs;
	private int qAlunos;
	private int telefone;
	private int preco1;
	
	private int preco2;
	private int preco3;
	private int preco4;
	
	
	
	
	public int getPreco1() {
		return preco1;
	}
	public void setPreco1(int preco1) {
		this.preco1 = preco1;
	}
	public int getPreco2() {
		return preco2;
	}
	public void setPreco2(int preco2) {
		this.preco2 = preco2;
	}
	public int getPreco3() {
		return preco3;
	}
	public void setPreco3(int preco3) {
		this.preco3 = preco3;
	}
	public int getPreco4() {
		return preco4;
	}
	public void setPreco4(int preco4) {
		this.preco4 = preco4;
	}
	public int getTelefone() {
		return telefone;
	}
	public void setTelefone(int telefone) {
		this.telefone = telefone;
	}
	public String getLancou() {
		return lancou;
	}
	public void setLancou(String lancou) {
		this.lancou = lancou;
	}
	public String getProfessor() {
		return professor;
	}
	public void setProfessor(String professor) {
		this.professor = professor;
	}
	public int getqProfs() {
		return qProfs;
	}
	public void setqProfs(int qProfs) {
		this.qProfs = qProfs;
	}
	public int getqAlunos() {
		return qAlunos;
	}
	public void setqAlunos(int qAlunos) {
		this.qAlunos = qAlunos;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCoord() {
		return coord;
	}
	public void setCoord(String coord) {
		this.coord = coord;
	}
	public boolean isInserir_Disciplinas() {
		return inserir_Disciplinas;
	}
	public void setInserir_Disciplinas(boolean inserir_Disciplinas) {
		this.inserir_Disciplinas = inserir_Disciplinas;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	
	public int getCargaHoraria() {
		return cargaHoraria;
	}
	public void setCargaHoraria(int cargaHoraria) {
		this.cargaHoraria = cargaHoraria;
	}
	
}
