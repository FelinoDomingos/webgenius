package tecShine.com.model.WG;

public class PCA_WG {

	
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
    private int telefone;
    private String email;
    private int alternativo1;
    private int alternativo2;
    private Escola escola;
    private String usuarioAcesso;
    public PCA_Financa financa = new PCA_Financa();
	
	//Esses dois atributos vão servir
	//para quando o PCA quiser configurar
	//Seus dados de acesso
	
	
	private String acessoPersonalizado1;
	private String acessoPersonalizado2;
    
	
	
    
    
	public String getUsuarioAcesso() {
		return usuarioAcesso;
	}
	public void setUsuarioAcesso(String usuarioAcesso) {
		this.usuarioAcesso = usuarioAcesso;
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
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getMae() {
		return mae;
	}
	public void setMae(String mae) {
		this.mae = mae;
	}
	public String getPai() {
		return Pai;
	}
	public void setPai(String pai) {
		Pai = pai;
	}
	public String getNaturalidade() {
		return naturalidade;
	}
	public void setNaturalidade(String naturalidade) {
		this.naturalidade = naturalidade;
	}
	public String getNacionalidade() {
		return nacionalidade;
	}
	public void setNacionalidade(String nacionalidade) {
		this.nacionalidade = nacionalidade;
	}
	public String getBi() {
		return bi;
	}
	public void setBi(String bi) {
		this.bi = bi;
	}
	public boolean isSexo() {
		return sexo;
	}
	public void setSexo(boolean sexo) {
		this.sexo = sexo;
	}
	public String getProvincia() {
		return provincia;
	}
	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}
	public String getRua() {
		return rua;
	}
	public void setRua(String rua) {
		this.rua = rua;
	}
	public String getBairro() {
		return bairro;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	public String getMunicipio() {
		return municipio;
	}
	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}
	public int getTelefone() {
		return telefone;
	}
	public void setTelefone(int telefone) {
		this.telefone = telefone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getAlternativo1() {
		return alternativo1;
	}
	public void setAlternativo1(int alternativo1) {
		this.alternativo1 = alternativo1;
	}
	public int getAlternativo2() {
		return alternativo2;
	}
	public void setAlternativo2(int alternativo2) {
		this.alternativo2 = alternativo2;
	}
    
    
    
    
}
