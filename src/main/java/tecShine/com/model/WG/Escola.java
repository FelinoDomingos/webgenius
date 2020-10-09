package tecShine.com.model.WG;

import java.sql.Date;



public class Escola {

	
  private int id;	
  private String contrato;
  private int alunosQuant;
  private int valor;
  private String bi;
  private String tel;
  private String date;
  private String nomeEscola;
  
  
  
 
  


public String getNomeEscola() {
	return nomeEscola;
}

public void setNomeEscola(String nomeEscola) {
	this.nomeEscola = nomeEscola;
}




  
  
  
  public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
  

public String getContrato() {
	return contrato;
}

public void setContrato(String contrato) {
	this.contrato = contrato;
}

public int getAlunosQuant() {
	return alunosQuant;
}

public void setAlunosQuant(int alunosQuant) {
	this.alunosQuant = alunosQuant;
}

public int getValor() {
	return valor;
}

public void setValor(int valor) {
	this.valor = valor;
}

public String getBi() {
	return bi;
}

public void setBi(String bi) {
	this.bi = bi;
}


public String getTel() {
	return tel;
}

public void setTel(String tel) {
	this.tel = tel;
}

public String getDate() {
	return date;
}

public void setDate(String date) {
	this.date = date;
}



}