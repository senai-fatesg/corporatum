package br.com.ambientinformatica.fatesg.corporatum.util;

import java.util.List;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import br.com.ambientinformatica.fatesg.api.Disciplina;

public class PlanoDeEnsinoRelatorio {

	private String path; // Caminho base

	private String pathToReportPackage; // Caminho para o package onde estão
										// armazenados os relatorios Jasper

	// Recupera os caminhos para que a classe possa encontrar os relatórios
	public PlanoDeEnsinoRelatorio() {
		this.path = this.getClass().getClassLoader().getResource("").getPath();
		this.pathToReportPackage = this.path + "br/com/ambientinformatica/fatesg/corporatum/util";
		System.out.println(path);
	}

	// Imprime/gera uma lista de disciplinas
	public void imprimir(List<Disciplina> disciplinas) throws Exception {
		JasperReport report = JasperCompileManager.compileReport(this
				.getPathToReportPackage() + "disciplina.jrxml");

		JasperPrint print = JasperFillManager.fillReport(report, null,
				new JRBeanCollectionDataSource(disciplinas));

		JasperExportManager.exportReportToPdfFile(print,
				"c:/Relatorio_de_disciplina.pdf");
	}

	public String getPathToReportPackage() {
		return this.pathToReportPackage;
	}

	public String getPath() {
		return this.path;
	}
}
