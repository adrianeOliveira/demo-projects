package br.com.adrianerodrigues.demo;

import java.io.InputStream;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.util.JRSaver;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimplePdfExporterConfiguration;
import net.sf.jasperreports.export.SimplePdfReportConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootApplication
public class JasperReportDemoApplication {

    public static void main(String[] args) throws JRException, SQLException {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        ctx.register(JasperReportsConfig.class);
        ctx.refresh();

        InputStream employeeReportStream= JasperReportDemoApplication.class.getResourceAsStream("/employeeReport.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(employeeReportStream);
        JRSaver.saveObject(jasperReport, "employeeReport.jasper");

        InputStream emailReportStream = JasperReportDemoApplication.class.getResourceAsStream("/employeeEmailReport.jrxml");
        JRSaver.saveObject(JasperCompileManager.compileReport(emailReportStream), "employeeEmailReport.jasper");


        DataSource dataSource = ctx.getBean(DataSource.class);
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("title", "Employee Report");
        parameters.put("minSalary", 15000.0);
        parameters.put("condition", " LAST_NAME ='Smith' ORDER BY FIRST_NAME");
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource.getConnection());

        exportToPdf(jasperPrint, "employeeReport.pdf", "baeldung");
    }

    public static void exportToPdf(JasperPrint jasperPrint, String fileName, String author) {
        JRPdfExporter exporter = new JRPdfExporter();

        exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
        exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(fileName));

        SimplePdfReportConfiguration reportConfig = new SimplePdfReportConfiguration();
        reportConfig.setSizePageToContent(true);
        reportConfig.setForceLineBreakPolicy(false);

        SimplePdfExporterConfiguration exportConfig = new SimplePdfExporterConfiguration();
        exportConfig.setMetadataAuthor(author);
        exportConfig.setEncrypted(true);
        exportConfig.setAllowedPermissionsHint("PRINTING");

        exporter.setConfiguration(reportConfig);
        exporter.setConfiguration(exportConfig);
        try {
            exporter.exportReport();
        } catch (JRException ex) {
            Logger.getLogger(JasperReportDemoApplication.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
