package tecShine.com.model;

import java.io.File;
import java.sql.Blob;

import org.springframework.web.multipart.MultipartFile;

import tecShine.com.model.WG.Escola;

public class Fase2 {

	private String instituicao;
	private String pca;
	private String bi;
	private String sigla;
	
	
	
	private boolean estagio;
	private MultipartFile logotipo;
	
	public Escola escola= new Escola();
	
	private String usuarioAcesso;
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
	public String getInstituicao() {
		return instituicao;
	}
	public void setInstituicao(String instituicao) {
		this.instituicao = instituicao;
	}
	public String getPca() {
		return pca;
	}
	public void setPca(String pca) {
		this.pca = pca;
	}
	public String getSigla() {
		return sigla;
	}
	public void setSigla(String sigla) {
		this.sigla = sigla;
	}
	
	public boolean isEstagio() {
		return estagio;
	}
	public void setEstagio(boolean estagio) {
		this.estagio = estagio;
	}
	
	public MultipartFile getLogotipo() {
		return logotipo;
	}
	public void setLogotipo(MultipartFile logotipo) {
		this.logotipo = logotipo;
	}
	public String getBi() {
		return bi;
	}
	public void setBi(String bi) {
		this.bi = bi;
	}
	
	
}
