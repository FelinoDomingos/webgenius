package tecShine.com.model;

import java.util.ArrayList;

public class FaltaEEstagio {

	
	private int estagio;
	private ArrayList<Integer> faltas= new ArrayList<>();
	
	
	
	public ArrayList<Integer> getFaltas() {
		return faltas;
	}
	public void setFaltas(ArrayList<Integer> faltas) {
		this.faltas = faltas;
	}
	public int getEstagio() {
		return estagio;
	}
	public void setEstagio(int estagio) {
		this.estagio = estagio;
	}
	
	
}
