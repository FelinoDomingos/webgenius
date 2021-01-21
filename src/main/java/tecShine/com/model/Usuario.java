package tecShine.com.model;

import java.util.Objects;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;


@Component
@SessionScope
public class Usuario {



	
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
	private String idSessao;
	
	
    
	public String getIdSessao() {
		return idSessao;
	}

	public void setIdSessao(String idSessao) {
		this.idSessao = idSessao;
	}

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
	
	/*
	
	 public void setProduct(String product) {
		  if (CollectionUtils.isEmpty(this.getProducts())) {
		   List < String > products = new ArrayList < > ();
		   products.add(product);
		   this.setProducts(products);
		  } else {
		   this.getProducts().add(product);
		  }
		 }
	
	*/
	@Override
	public boolean equals(Object o) {
		// TODO Auto-generated method stub
		
		 if (this == o) return true;
		 if (!(o instanceof Usuario)) return false;
		 Usuario that = (Usuario) o;
		 return Objects.equals(getPagina(), that.getPagina()) &&
				   Objects.equals(getMensagem(), that.getMensagem())&&
				   Objects.equals(getTurma(), that.getTurma())&&
				   Objects.equals(isExisteUsuario(), that.isExisteUsuario())&&
				   Objects.equals(getIntegrante(), that.getIntegrante())&&
				   Objects.equals(getBD(), that.getBD())&&
				   Objects.equals(getSenha(), that.getSenha())&&
				   Objects.equals(getNome(), that.getNome())&&
				   Objects.equals(getId(), that.getId())&&
				   Objects.equals(getBi(), that.getBi());
	}
	
	
	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return Objects.hash(getPagina(),
				getMensagem(),
				getTurma(),
				isExisteUsuario(),
				getIntegrante(),
				getBD(),
				getSenha(),
				getNome(),
				getId(),
				getBi());
	}
	
}
