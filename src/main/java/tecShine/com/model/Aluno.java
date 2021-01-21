package tecShine.com.model;

import java.util.ArrayList;

import tecShine.com.AcessoInteligente.AcessoInteligente;

public class Aluno {

	
	private String disciplina;
	private int nota;
	private String situacao;
	
	private int id;
    private String nome;
    private String mae;
    private String Pai;
    private String naturalidade;
    private String nacionalidade;
    private String bi;
    private boolean sexo;
	private String provincia;
    private String rua;
    private String bairro;
    private String municipio;
    private int id2;
	private String sexo2;
	private ArrayList<String> mesesPagos = new ArrayList<>();
	private ArrayList<String> valoresPagoss = new ArrayList<>();
	private boolean pagamentoEfectuado;
	private boolean semBI;
	private String mesesAPagar;
	
	private String mes;
	private String pagamento;
	private String multa;
	private String materia;
	private String turmasVazia;
	private String turmasCheias;
	private String idFunc;
	private String idAluno;
	
	
	
	
	
	
    
    
    

	public String getIdAluno() {
		return idAluno;
	}

	public void setIdAluno(String idAluno) {
		this.idAluno = idAluno;
	}

	public boolean isSemBI() {
		return semBI;
	}

	public void setSemBI(boolean semBI) {
		this.semBI = semBI;
	}

	public String getTurmasVazia() {
		return turmasVazia;
	}

	public void setTurmasVazia(String turmasVazia) {
		this.turmasVazia = turmasVazia;
	}
	
	public String getIdFunc(){
		return idFunc;
	}

	public void setIdFunc(String idFunc) {
		this.idFunc = idFunc;
	}

	public String getTurmasCheias() {
		return turmasCheias;
	}

	public void setTurmasCheias(String turmasCheias) {
		this.turmasCheias = turmasCheias;
	}

	public String getMateria() {
		return materia;
	}

	public void setMateria(String materia) {
		this.materia = materia;
	}

	public String getMes() {
		return mes;
	}

	public void setMes(String mes) {
		this.mes = mes;
	}

	public String getPagamento() {
		return pagamento;
	}

	public void setPagamento(String pagamento) {
		this.pagamento = pagamento;
	}

	public String getMulta() {
		return multa;
	}

	public void setMulta(String multa) {
		this.multa = multa;
	}

	public String getDisciplina() {
		return disciplina;
	}

	public void setDisciplina(String disciplina) {
		this.disciplina = disciplina;
	}

	public int getNota() {
		return nota;
	}

	public void setNota(int nota) {
		this.nota = nota;
	}

	public String getMesesAPagar() {
		return mesesAPagar;
	}

	public void setMesesAPagar(String mesesAPagar) {
		this.mesesAPagar = mesesAPagar;
	}

	public ArrayList<String> getMesesPagos() {
		return mesesPagos;
	}

	public void setMesesPagos(ArrayList<String> mesesPagos) {
		this.mesesPagos = mesesPagos;
	}

	public ArrayList<String> getValoresPagoss() {
		return valoresPagoss;
	}

	public void setValoresPagoss(ArrayList<String> valoresPagoss) {
		this.valoresPagoss = valoresPagoss;
	}

	public boolean isPagamentoEfectuado() {
		return pagamentoEfectuado;
	}

	public void setPagamentoEfectuado(boolean pagamentoEfectuado) {
		this.pagamentoEfectuado = pagamentoEfectuado;
	}

	public String getSituacao() {
		return situacao;
	}

	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}

	public int getId2() {
		return id2;
	}

	public void setId2(int id2) {
		this.id2 = id2;
	}

	public String getSexo2() {
		return sexo2;
	}

	public void setSexo2(String sexo2) {
		this.sexo2 = sexo2;
	}

	private int telefone;
    private String email;
    private int alternativo1;
    private int alternativo2;
    
    
	private  String nivel;
    private String turno;
    private  String curso;
	private  String turma;
	private String referencia;
	private String instituicao;
	private String usuarioAcesso;
	private String acessoPersonalizado1;
	private String acessoPersonalizado2;
	
	private int avaliacao;
	private int prova;
	private int media;
	
	
	//Esses dois atributos vão servir
	//para quando o aluno quiser configurar
	//Seus dados de acesso
	
	
	
	
	  public int getAvaliacao() {
		return avaliacao;
	}

	public void setAvaliacao(int avaliacao) {
		this.avaliacao = avaliacao;
	}

	public int getProva() {
		return prova;
	}

	public void setProva(int prova) {
		this.prova = prova;
	}

	public int getMedia() {
		return media;
	}

	public void setMedia(int media) {
		this.media = media;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMunicipio() {
			return municipio;
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
	
	public String getInstituicao() {
		return instituicao;
	}
	public void setInstituicao(String instituicao) {
		this.instituicao = instituicao;
	}
	public String getUsuarioAcesso() {
		return usuarioAcesso;
	}
	public void setUsuarioAcesso(String usuarioAcesso) {
		this.usuarioAcesso = usuarioAcesso;
	}
	
	
	public String getNivel() {
		return nivel;
	}
	public void setNivel(String nivel) {
		this.nivel = nivel;
	}
	public String getTurno() {
		return turno;
	}
	public void setTurno(String turno) {
		this.turno = turno;
	}
	public String getCurso() {
		return curso;
	}
	public void setCurso(String curso) {
		this.curso = curso;
	}
	public String getTurma() {
		return turma;
	}
	public void setTurma(String turma) {
		this.turma = turma;
	}
	public String getReferencia() {
		return referencia;
	}
	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}
	  

    
    
	public void setAlternativo2(int alternativo2) {
		this.alternativo2 = alternativo2;
		AcessoInteligente a = new AcessoInteligente();
		a.alternativo2=alternativo2;
		a=null;
	}

	public void setNome(String nome) {
		this.nome = nome;
		
	}

	

	public void setMae(String mae) {
		this.mae = mae;
		AcessoInteligente a = new AcessoInteligente();
		a.mae=this.mae;
		a=null;
	}

	

	public void setPai(String pai) {
		Pai = pai;
		AcessoInteligente a = new AcessoInteligente();
		a.Pai=this.Pai;
		a=null;
	}

	

	public void setNaturalidade(String naturalidade) {
		this.naturalidade = naturalidade;
		AcessoInteligente a = new AcessoInteligente();
		a.nacionalidade=this.naturalidade;
		a=null;
	}

	

	public void setNacionalidade(String nacionalidade) {
		this.nacionalidade = nacionalidade;
		AcessoInteligente a = new AcessoInteligente();
		a.nacionalidade=nacionalidade;
		a=null;
	}

	

	public void setBi(String bi) {
		this.bi = bi;
		AcessoInteligente a = new AcessoInteligente();
		a.bi=bi;
		a=null;
	}

	

	public void setSexo(boolean sexo) {
		this.sexo = sexo;
		AcessoInteligente a = new AcessoInteligente();
		a.sexo=sexo;
		a=null;
	}

	

	public void setProvincia(String provincia) {
		this.provincia = provincia;
		AcessoInteligente a = new AcessoInteligente();
		a.provincia=provincia;
		a=null;
	}

	

	public void setRua(String rua) {
		this.rua = rua;
		AcessoInteligente a = new AcessoInteligente();
		a.rua=rua;
		a=null;
	}

	

	public void setBairro(String bairro) {
		this.bairro = bairro;
		AcessoInteligente a = new AcessoInteligente();
		a.bairro=bairro;
		
	}

	

	public void setMunicipio(String municipio) {
		this.municipio = municipio;
		AcessoInteligente a = new AcessoInteligente();
		a.municipio=municipio;
		a=null;
	}

	

	public void setTelefone(int telefone) {
		this.telefone = telefone;
		AcessoInteligente a = new AcessoInteligente();
		a.telefone=telefone;
		a=null;
	}

	

	public void setEmail(String email) {
		this.email = email;
		AcessoInteligente a = new AcessoInteligente();
		a.email=email;
		a=null;
	}



	public void setAlternativo1(int alternativo1) {
		this.alternativo1 = alternativo1;
		AcessoInteligente a = new AcessoInteligente();
		a.alternativo1=alternativo1;
		a=null;
	}
    
    
    
    
    public boolean getSexo() {
		return sexo;
	}
	
	
	
	public int getId() {
		return id;
	}
	
	public String getNome() {
		return nome;
	}
	
	public String getMae() {
		return mae;
	}
	
	public String getPai() {
		return Pai;
	}
	
	public String getNaturalidade() {
		return naturalidade;
	}
	
	public String getNacionalidade() {
		return nacionalidade;
	}
	
	public String getBi() {
		return bi;
	}
	
	
	public String getProvincia() {
		return provincia;
	}
	
	public String getRua() {
		return rua;
	}
	
	public String getBairro() {
		return bairro;
	}
	
	
	public int getTelefone() {
		return telefone;
	}
	
	public String getEmail() {
		return email;
	}
	
	public int getAlternativo1() {
		return alternativo1;
	}
	
	public int getAlternativo2() {
		return alternativo2;
	}
	
	
	
	
	
	
}
