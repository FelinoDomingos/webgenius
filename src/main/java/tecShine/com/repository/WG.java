package tecShine.com.repository;

import java.util.ArrayList;

import tecShine.com.model.WG.Escola;

public interface WG {

	public ArrayList<Escola>listaDeEscolas();
	public String cadastrarEsola(Escola escola);
	public void removerEsola(int id);
	public void AlterarEscola(int id);
	
}
