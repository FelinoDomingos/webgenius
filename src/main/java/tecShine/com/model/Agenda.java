package tecShine.com.model;

import java.sql.Date;




public class Agenda {
	


	private long id;
	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	private String documento;
	
	private Date dataEntrega;
	private String dataEntrega2;
	private String dataEmissao;
	
	
	
	
	public String getDataEntrega2() {
		return dataEntrega2;
	}
	public void setDataEntrega2(String dataEntrega2) {
		this.dataEntrega2 = dataEntrega2;
	}
	public String getDataEmissao() {
		return dataEmissao;
	}
	public void setDataEmissao(String dataEmissao) {
		this.dataEmissao = dataEmissao;
	}
	public String getDocumento() {
		return documento;
	}
	public void setDocumento(String documento) {
		this.documento = documento;
	}
	public Date getDataEntrega() {
		return dataEntrega;
	}
	public void setDataEntrega(Date dataEntrega) {
		this.dataEntrega = dataEntrega;
	}
	
	
	
	/*
	public Date getDataEntrega() {
		return dataEntrega;
	}
	public void setDataEntrega(Date dataEntrega) {
		this.dataEntrega = dataEntrega;
	}
	public Date getDataEmissao() {
		return dataEmissao;
	}
	public void setDataEmissao(Date dataEmissao) {
		this.dataEmissao = dataEmissao;
	}
	
	
	*/
	

}
