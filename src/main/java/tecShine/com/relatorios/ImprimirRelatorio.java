package tecShine.com.relatorios;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import tecShine.com.JDBC.Pesquisar_SQL;
import tecShine.com.model.Relatorio;

public class ImprimirRelatorio {

	
	
	/*
	 * Impressao do Relatorio
	 */
	private void relatorio(ArrayList<Relatorio> lista,String arquivoJasper,HttpServletResponse response) {
		
		String relatorio="Relatorio/"+arquivoJasper;
		
		//InputStream is = getClass().getResourceAsStream(relatorio);
	    
		
		JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource(lista);
	    
		Map parametros = new HashMap();
		
		JasperPrint print = null;
		
		try {
			InputStream b = new FileInputStream(relatorio);
			print = JasperFillManager.fillReport(b
					, parametros,ds);
			
			JRExporter ep = new JRPdfExporter();
			ep.setParameter(JRExporterParameter.JASPER_PRINT, print);
			ep.setParameter(JRExporterParameter.OUTPUT_STREAM, response.getOutputStream());
			
			ep.exportReport();
			//JasperViewer jv = new JasperViewer(print);
			//jv.setVisible(true);
			
		}catch(Exception e) {
			
			e.printStackTrace();
		}
		
	
	}
	
	
	public void relatorioPropina(String BD,HttpServletResponse response,String instituicao,String curso,String tipoDeTitulo,int valorPago,String funcionario,
			int qMesesPagos,String turma, ArrayList<String> mesesPagos,String estudante
			,String numeroDeEstudante){
		
		
		Pesquisar_SQL p = new Pesquisar_SQL();
		
		//========================================================
		
		// Aqui estáº o Logotipo
		
		
		Blob logotipo = p.pesquisarUm_Logotipo(BD, "pca_"+BD, "logotipo");
		String imagem=null;
		
		
		InputStream in=null;
		try {
			StringBuilder ret;
			in = logotipo.getBinaryStream();			
			ret = new StringBuilder();
			int lidos=0;
			byte[] b = new byte[2048];
			String temp = null;
			while ( (lidos=in.read(b)) != -1){
			      temp = new String(b,0,lidos);
			     ret.append(temp);
			}
			
			imagem = ret.toString();
			//System.out.println("-->"+ret.toString());
			in.close();
		}catch(Exception e) {
			
			e.printStackTrace();
			
		}
		

		 
		
		//=======================================================
		
		ArrayList<Relatorio> lista = new ArrayList<>();
		
		int tamanho = mesesPagos.size();
		for(int i=0;i<qMesesPagos;i++) {
			
			--tamanho;
			Relatorio relatorio = new Relatorio();
			
			
			relatorio.setParcela(mesesPagos.get(tamanho));
			relatorio.setSituacao("OK");
			
			
			relatorio.setValorPAgo(valorPago+",00 Kz");
			
		
			
			
			if(i==0) {
				relatorio.setTurma(turma);
				relatorio.setCurso(curso);
				relatorio.setEstudante(estudante);
				relatorio.setInstituicao(instituicao);
				relatorio.setTipoDeTitulo(tipoDeTitulo);
				relatorio.setFuncionario(funcionario);
				relatorio.setNumeroDeEstudante(numeroDeEstudante);
				relatorio.setTotalPago(valorPago*qMesesPagos+",00 Kz");
				relatorio.setFuncionario(funcionario);
				
				
			}else {
				
				relatorio.setTurma("");
				relatorio.setTipoDeTitulo("");
			}
			
			if(i==qMesesPagos-1) {
				
			}
			
			lista.add(relatorio);
		}
		
		
		
		
		
		
		
		
		
		
		relatorio(lista, "propina.jasper",response);
		
		
	}
	
	public void relatorioOutrosServicos(String BD,HttpServletResponse response,String instituicao,String mes,String curso,String tipoDeTitulo,int valorPago,String funcionario,
			String imprimir_o_q,String turno,String nivel,String turma, String estudante) {
		
		
		
		
		Relatorio relatorio = new Relatorio();
		
		relatorio.setInstituicao(instituicao);
		relatorio.setParcela(mes);
		relatorio.setCurso(curso);
		relatorio.setTipoDeTitulo(tipoDeTitulo);
		relatorio.setValorPAgo(valorPago+",00 Kz");
		relatorio.setFuncionario(funcionario);
		relatorio.setTotalPago(valorPago+",00 Kz");
		relatorio.setTurno(turno);
		relatorio.setNivel(nivel);
		relatorio.setTurma(turma);
		relatorio.setEstudante(estudante);
		
		
		
		
		ArrayList<Relatorio> lista = new ArrayList<>();
		lista.add(relatorio);
		
		if(imprimir_o_q.equalsIgnoreCase("matricula")) {
			relatorio(lista, "matricula.jasper",response);
			
		}else if(imprimir_o_q.equalsIgnoreCase("confirmacao")) {
			
			relatorio(lista, "confirmacao.jasper",response);
		}else if(imprimir_o_q.equalsIgnoreCase("estagio")) {
			
			relatorio(lista, "estagio.jasper",response);
		}else if(imprimir_o_q.equalsIgnoreCase("documetos")) {
			relatorio(lista, "documeto.jasper",response);
		}
		
		
		
		
		
		
	}
	
	
}
