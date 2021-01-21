package tecShine.com.relatorios;

import java.io.OutputStream;
import java.net.URL;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.util.JRLoader;
import tecShine.com.JDBC.ConnectionFactory;
import tecShine.com.model.Relatorio;

public class ImprimirRelatorio {

	
	
	


	
	
	/*
	 * Impressao do Relatorio
	 */
	
	/*
	private void relatorio(HttpServletResponse response,byte[] bytes) {
		
			
			try {

		
		    
			
		    response.setContentType("application/pdf");
		    response.setContentLength(bytes.length);
		    ServletOutputStream os = response.getOutputStream();
		    os.write(bytes,0,bytes.length);
		    os.flush();
		    os.close();
		    
			
		    
			//JasperViewer jv = new JasperViewer(print);
			//jv.setVisible(true);
			
		}catch(Exception e) {
			
			e.printStackTrace();
		}
		
		
	}
	
	*/
	public void geraRelatorio(String relatorio, Map<String, Object> parametros, OutputStream saida,
			ArrayList<Relatorio> dadosDoAluno,HttpServletRequest request,HttpServletResponse response) {

		
		try {
			
			System.out.println("Imprimindo o Relatorio");
			System.out.println("Abrindo Conexao");
			
			//lista de Dados do aluno
			JRBeanCollectionDataSource jrbcds = new JRBeanCollectionDataSource(dadosDoAluno);

			System.out.println("Compilando o Relatorio");
			// Compila o jrxml na memoria
			
			
			
			
			System.out.println("Relatorio: "+relatorio);
			JasperReport jasper = JasperCompileManager.compileReport(relatorio);

			if(jasper==null) {
				System.out.println("  Relatorio N Compilando");
			}else {
				
				System.out.println("  Relatorio  Compilando");
			}
			
			System.out.println("Jasper: "+jasper);
			// Preencher o Relatorio
			
			
			JasperPrint print = JasperFillManager.fillReport(jasper, parametros, jrbcds);

			//byte[] bytes = JasperRunManager.runReportToPdf(jasper, parametros, jrbcds);
			//JasperExportManager.exportReportToPdfFile(print, "C:/Users/LEPA LEPA/Documents/workspace-spring-tool-suite-4-4.6.2.RELEASE/TecShine/Relatorio/matricula.pdf");
			
			//relatorio( response, bytes);
			System.out.println("print: "+print);
			// Exportando para Pdf

			
			JRExporter exporter = new JRPdfExporter();

			exporter.setParameter(JRExporterParameter.JASPER_PRINT, print);
			exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, saida);

			//System.out.println("exporter: "+exporter);
			exporter.exportReport();
			
			System.out.println("exportado ");

		} catch (Exception e) {
			// tratar exceção
		} finally {

		}
		
		
		
        Runtime r = Runtime.getRuntime();
		
		r.freeMemory();
		r.runFinalization();
		r.gc();

	}
	
	
	public void relatorioPropina(String BD,HttpServletResponse response,String instituicao,String curso,String tipoDeTitulo,int valorPago,String funcionario,
			int qMesesPagos,String turma, ArrayList<String> mesesPagos,String estudante
			,String numeroDeEstudante,HttpServletRequest request,String turno,String nivel){
		
		
		ArrayList<Relatorio> lista = new ArrayList<>();
		
		String meses="";
		
		int tamanho = mesesPagos.size();
		for(int i=0;i<qMesesPagos;i++) {
			
			
			tamanho= tamanho-1;
			
			Relatorio relatorio = new Relatorio();
			
			
			relatorio.setParcela(mesesPagos.get(tamanho));
			
			
			if(i<qMesesPagos) {
				
				meses = meses + mesesPagos.get(i)+",";
			}else if(i == qMesesPagos) {
				
				meses = meses + mesesPagos.get(i);
			}
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
				relatorio.setNivel(nivel);
				relatorio.setTurno(turno);
				
			}else {
				
				relatorio.setTurma("");
				relatorio.setTipoDeTitulo("");
			}
			
			if(i==qMesesPagos-1) {
				
			}
			
			
			relatorio.setMeses(meses);
			lista.add(relatorio);
		}
		
		
		
		
		
		
		imprimirRelatorio(request, response, lista, "matricula.jrxml");
	}
	
	public void relatorioOutrosServicos(String BD,HttpServletResponse response,String instituicao,String mes,String curso,String tipoDeTitulo,int valorPago,String funcionario,
			String imprimir_o_q,String turno,String nivel,String turma, String estudante,HttpServletRequest request) {
		
		
		
		
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
			//relatorio(lista, "matricula.jasper",response);
			
		}else if(imprimir_o_q.equalsIgnoreCase("confirmacao")) {
			
			//relatorio(lista, "confirmacao.jasper",response);
		}else if(imprimir_o_q.equalsIgnoreCase("estagio")) {
			
			//relatorio(lista, "estagio.jasper",response);
		}else if(imprimir_o_q.equalsIgnoreCase("documetos")) {
			//relatorio(lista, "documeto.jasper",response);
		}
		
		
		
		
		
		
	}
	
public void imprimirRelatorio(HttpServletRequest  request,HttpServletResponse response,ArrayList<Relatorio> aluno,String arquivo) {

		
		// caminho do Jrxml na aplicacao
		String relatorio ="/home/webgenius/webapps/webgenius/WEB-INF/classes//Relatorio/"+arquivo;
		
		System.out.println("ARQUIVO TOTAL: "+relatorio);
		

		// Prerparando os Parametros

		Map<String, Object> parametros = new HashMap<>();

		try {
			
			System.out.println("Tentando Imprimir o Relatorio");
			geraRelatorio(relatorio, parametros, response.getOutputStream(),aluno,request,response);
		} catch (Exception e) {

			e.printStackTrace();
		}

	}
	
	
	
	

}



