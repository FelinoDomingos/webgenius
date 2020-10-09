package tecShine.com.model;

import java.sql.Date;
import java.util.ArrayList;

import tecShine.com.AcessoInteligente.AcessoInteligente;

public class Funcionario {


	
	private int id;
	private int tempo;
	
	

	private String nome;
    private String mae;
    private String Pai;
    private String naturalidade;
    
    
   

	private String nacionalidade;
    private String bi;
    private boolean sexo;
    private String sexo2;
  
    
	private String provincia;
    private String rua;
    private String bairro;
    private String municipio;
    private int telefone;
    private String email;
    private int alternativo1;
    private int alternativo2;
    
    
    private String instituicao;
    private String usuarioAcesso;
    
    private  Date contrato;
	private  String cargo;
	private String referencia;
	private ArrayList<String> permissoes = new ArrayList<>();
	
	private String curso;
    private String nivel;
    private String turno;
    private String contrato2;
  
    
  
    // Para Professores
    
    public String getContrato2() {
		return contrato2;
	}

	public void setContrato2(String contrato2) {
		this.contrato2 = contrato2;
	}

	public ArrayList<String> getPermissoes() {
		return permissoes;
	}

	public void setPermissoes(ArrayList<String> permissoes) {
		this.permissoes = permissoes;
	}

	private ArrayList<String> disciplinas = new ArrayList<>();
    private ArrayList<String> turmas = new ArrayList<>();
    private ArrayList<Integer> tempoPorDesciplinas = new ArrayList<>();
    
    public ArrayList<Integer> getTempoPorDesciplinas() {
		return tempoPorDesciplinas;
	}

	public void setTempoPorDesciplinas(ArrayList<Integer> tempoPorDesciplinas) {
		this.tempoPorDesciplinas = tempoPorDesciplinas;
	}

	
    
    
    public Date getContrato() {
		return contrato;
	}

	public void setContrato(Date contrato) {
		this.contrato = contrato;
	}

	private int id2;
    public int getId2() {
		return id2;
	}

	public void setId2(int id2) {
		this.id2 = id2;
	}

	public String getCurso() {
		return curso;
	}

	public void setCurso(String curso) {
		this.curso = curso;
	}

	public ArrayList<String> getTurmas() {
		return turmas;
	}

	public void setTurmas(ArrayList<String> turmas) {
		this.turmas = turmas;
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

	public ArrayList<String> getDisciplinas() {
		return disciplinas;
	}

	public void setDisciplinas(ArrayList<String> disciplinas) {
		this.disciplinas = disciplinas;
	}

	public int getTempo() {
		return tempo;
	}

	public void setTempo(int tempo) {
		this.tempo = tempo;
	}

	public String getSexo2() {
		return sexo2;
	}

	public void setSexo2(String sexo2) {
		this.sexo2 = sexo2;
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

	public int getAlternativo2() {
		return alternativo2;
	}

	
	public String getMae() {
		return mae;
	}
	
	 public String getNacionalidade() {
			return nacionalidade;
		}

     public String getMunicipio() {
			return municipio;
     }
	

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}
	public String getNaturalidade() {
		return naturalidade;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}
	public String getPai() {
		return Pai;
	}
	
	public String getBi() {
		return bi;
	}
	public boolean isSexo() {
		return sexo;
	}
	public String getRua() {
		return rua;
	}
	
	public String getProvincia() {
		return provincia;
	}
	
	
	
	
	public void setAlternativo2(int alternativo2) {
		this.alternativo2 = alternativo2;
		AcessoInteligente a = new AcessoInteligente();
		a.alternativo2=alternativo2;
		a=null;
	}

	public void setNome(String nome) {
		this.nome = nome;
		AcessoInteligente a = new AcessoInteligente();
		a.nome=this.nome;
		a=null;
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
	
	
	public int getAlternativo1() {
		return alternativo1;
	}
	
	public String getEmail() {
		return email;
	}
	
	

	public String getReferencia() {
		return referencia;
	}

	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}
	
	public String getBairro() {
		return bairro;
	}
	
	public int getTelefone() {
		return telefone;
	}
	
}
