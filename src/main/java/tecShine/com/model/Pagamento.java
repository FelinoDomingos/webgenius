package tecShine.com.model;

import java.util.ArrayList;

public class Pagamento {

	
	private String instituiçao;
	private String data;
	private String curso;
	private String NDMatricula;
	private ArrayList<Integer> valoresPagos = new ArrayList<>();
	private ArrayList<String> ParcelasMeses = new ArrayList<>();
	private String turma;
	private String tipoDeTitulo;
	private String situacaoPagamento;
	private String nomeDoTesoureiro;
	private boolean pagamentoEfectuado;
	private String aluno;
	
	private String pagos;
	private String nPagos;
	
	
	
	
	
	public String getPagos() {
		return pagos;
	}
	public void setPagos(String pagos) {
		this.pagos = pagos;
	}
	public String getnPagos() {
		return nPagos;
	}
	public void setnPagos(String nPagos) {
		this.nPagos = nPagos;
	}
	public String getAluno() {
		return aluno;
	}
	public void setAluno(String aluno) {
		this.aluno = aluno;
	}
	public String getCurso() {
		return curso;
	}
	public void setCurso(String curso) {
		this.curso = curso;
	}
	public String getInstituiçao() {
		return instituiçao;
	}
	public void setInstituiçao(String instituiçao) {
		this.instituiçao = instituiçao;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public String getNDMatricula() {
		return NDMatricula;
	}
	public void setNDMatricula(String nDMatricula) {
		NDMatricula = nDMatricula;
	}
	public ArrayList<Integer> getValoresPagos() {
		return valoresPagos;
	}
	public void setValoresPagos(ArrayList<Integer> valoresPagos) {
		this.valoresPagos = valoresPagos;
	}
	public ArrayList<String> getParcelasMeses() {
		return ParcelasMeses;
	}
	public void setParcelasMeses(ArrayList<String> parcelasMeses) {
		ParcelasMeses = parcelasMeses;
	}
	public String getTurma() {
		return turma;
	}
	public void setTurma(String turma) {
		this.turma = turma;
	}
	public String getTipoDeTitulo() {
		return tipoDeTitulo;
	}
	public void setTipoDeTitulo(String tipoDeTitulo) {
		this.tipoDeTitulo = tipoDeTitulo;
	}
	public String getSituacaoPagamento() {
		return situacaoPagamento;
	}
	public void setSituacaoPagamento(String situacaoPagamento) {
		this.situacaoPagamento = situacaoPagamento;
	}
	public String getNomeDoTesoureiro() {
		return nomeDoTesoureiro;
	}
	public void setNomeDoTesoureiro(String nomeDoTesoureiro) {
		this.nomeDoTesoureiro = nomeDoTesoureiro;
	}
	public boolean isPagamentoEfectuado() {
		return pagamentoEfectuado;
	}
	public void setPagamentoEfectuado(boolean pagamentoEfectuado) {
		this.pagamentoEfectuado = pagamentoEfectuado;
	}
	
	
	
	
	
	
	
}
