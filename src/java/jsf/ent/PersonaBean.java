package jsf.ent;

import app.sql.conexion;
import folderdb.Usuario;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.ooxml.JRDocxExporter;
import net.sf.jasperreports.engine.export.ooxml.JRPptxExporter;

@ManagedBean
@RequestScoped
public class PersonaBean {

    public void exportarPDF(ActionEvent actionEvent, String nombre, String ruta) throws JRException, IOException {

        conexion conn = new conexion();
        conn.conectarbdreporte();

        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath(ruta));
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), null, conn.getConnection());

        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        response.addHeader("Content-disposition", "attachment; filename="+nombre+".pdf");
        ServletOutputStream stream = response.getOutputStream();

        JasperExportManager.exportReportToPdfStream(jasperPrint, stream);
        stream.flush();
        stream.close();

        FacesContext.getCurrentInstance().responseComplete();
    }


    public void verPDF(ActionEvent actionEvent, String ruta ) throws Exception {
        conexion conne = new conexion();
        conne.conectarbdreporte();
        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath(ruta));

        byte[] bytes = JasperRunManager.runReportToPdf(jasper.getPath(), null, conne.getConnection());
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        response.setContentType("application/pdf");
        response.setContentLength(bytes.length);
        ServletOutputStream outStream = response.getOutputStream();
        outStream.write(bytes, 0, bytes.length);
        outStream.flush();
        outStream.close();

        FacesContext.getCurrentInstance().responseComplete();
    }


    public void exportarExcel(ActionEvent actionEvent, String nombre, String ruta) throws JRException, IOException {
        conexion conne = new conexion();
        conne.conectarbdreporte();
        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath(ruta));
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), null, conne.getConnection());

        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        response.addHeader("Content-disposition", "attachment; filename="+nombre+".xls");
        ServletOutputStream outStream = response.getOutputStream();

        JRXlsExporter exporter = new JRXlsExporter();
        exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
        exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, outStream);
        exporter.exportReport();

        outStream.flush();
        outStream.close();
        FacesContext.getCurrentInstance().responseComplete();
    }


    public void exportarDOC(ActionEvent actionEvent, String nombre, String ruta) throws JRException, IOException {
        conexion conne = new conexion();
        conne.conectarbdreporte();
    
        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath(nombre));
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), null, conne.getConnection());

        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        response.addHeader("Content-disposition", "attachment; filename="+nombre+".doc");
        ServletOutputStream outStream = response.getOutputStream();

        JRDocxExporter exporter = new JRDocxExporter();
        exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
        exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, outStream);
        exporter.exportReport();

        outStream.flush();
        outStream.close();
        FacesContext.getCurrentInstance().responseComplete();
    }
    
}