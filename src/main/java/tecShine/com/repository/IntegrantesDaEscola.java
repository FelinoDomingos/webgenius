package tecShine.com.repository;


public interface IntegrantesDaEscola {

	public void cadastrarIntegrantes(String referencia,
			String tipoDeMembro);
	
	public String existeNaEscola(String id_De_Login,String escola);
}
