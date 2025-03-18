package org.example;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.data.JRCsvDataSource;
import net.sf.jasperreports.view.JasperViewer;

import java.io.File;
import java.io.FileNotFoundException;

public class GenerarInforme {
    public static void generarInforme() {
        try {
            String csvFile = "src/main/jasperreports/productos.csv";
            String jrxmlFile = "C:\\Users\\juanc\\JaspersoftWorkspace\\MyReports\\InformeReport.jrxml";
            String jasperFile = "C:\\Users\\juanc\\JaspersoftWorkspace\\MyReports\\InformeReport.jasper";
            String pdfFile = "src/main/jasperreports/reporte.pdf";
            String htmlFile = "src/main/jasperreports/reporte.html";

            // Compilar el archivo JRXML
            JasperCompileManager.compileReportToFile(jrxmlFile, jasperFile);

            // Cargar datos desde el archivo CSV
            //JRDataSource dataSource = new JRBeanCollectionDataSource()
            JRCsvDataSource dataSource = new JRCsvDataSource(new File(csvFile));
            dataSource.setUseFirstRowAsHeader(true); // Usa la primera fila como encabezado

            // Llenar el informe con datos
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperFile, null, dataSource);

            // Exportar a PDF
            JasperExportManager.exportReportToPdfFile(jasperPrint, pdfFile);
            System.out.println("Informe generado correctamente en PDF: " + pdfFile);

            // Exportar a HTML
            //JasperViewer.viewReport(jasperPrint);
            JasperExportManager.exportReportToHtmlFile(jasperPrint, htmlFile);
            System.out.println("Informe generado correctamente en HTML: " + htmlFile);



        } catch (JRException | FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}