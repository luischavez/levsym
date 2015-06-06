/* 
 * Copyright (C) 2015 Luis Chávez
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.github.luischavez.levsym.modulos.funcion;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Element;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfTemplate;
import com.itextpdf.text.pdf.PdfWriter;

import java.awt.Graphics2D;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.Calendar;

import javax.swing.JOptionPane;
import javax.swing.JTable;

public class PDF {

    public void GeneraPDF(ResultSet Resultados) throws Exception {

        ResultSetMetaData metaData = Resultados.getMetaData();
        Object[] Columnas = new Object[metaData.getColumnCount()];

        String encabezado = "Reportes del Sistema Administrativo" + "\n"
                + "REGISTROS ACTUALES EN AL BASE DE DATOS" + "\n" + "\n" + "\n" + "\n" + "\n" + "\n" + "\n";
        Calendar c = Calendar.getInstance();
        String date = Integer.toString(c.get(Calendar.DAY_OF_MONTH))
                + "-" + Integer.toString(c.get(Calendar.MONTH))
                + "-" + Integer.toString(c.get(Calendar.YEAR))
                + " " + Integer.toString(c.get(Calendar.HOUR_OF_DAY))
                + "-" + Integer.toString(c.get(Calendar.MINUTE))
                + "-" + Integer.toString(c.get(Calendar.SECOND));

        Font fuente = new Font(Font.getFamily("ARIAL"), 12, Font.BOLD);

        String choro = "Reporte por fecha de los modulos\n"
                + "de catalogo" + "\n"
                + "Systema Administrativo" + "\n" + "\n" + "\n" + "\n";

        Image imagen = Image.getInstance(System.getProperty("user.dir") + "/Image/logo.png");
        imagen.setAlignment(Image.TEXTWRAP);

        try {
            Paragraph linea = new Paragraph(encabezado, fuente);
            Phrase para = new Phrase(choro);
            Paragraph fecha = new Paragraph(date + "\n" + "\n");

            PdfPTable tabla = new PdfPTable(Columnas.length);
            tabla.setWidthPercentage(100);

            //Document documento = new Document(PageSize.LETTER);
            Document documento = new Document(PageSize.A4.rotate(), 50, 50, 100, 72);
            File Dir = new File(System.getProperty("user.dir") + "/Reportes/");
            if (!Dir.exists()) {
                Dir.mkdirs();
            }

            String file = System.getProperty("user.dir") + "/Reportes/" + metaData.getTableName(1) + " " + date + ".pdf";

            PdfWriter.getInstance(documento, new FileOutputStream(file));

            documento.open();
            documento.add(imagen);
            documento.add(linea);
            documento.add(para);
            documento.add(fecha);

            for (int x = 0; x < Columnas.length; x++) {
                PdfPCell Celda = new PdfPCell(new Paragraph(metaData.getColumnName(x + 1), FontFactory.getFont("arial", 9, Font.BOLD, BaseColor.RED)));
                Celda.setHorizontalAlignment(Element.ALIGN_CENTER);
                tabla.addCell(Celda);
            }

            while (Resultados.next()) {
                for (int x = 0; x < Columnas.length; x++) {
                    //if(Resultados.getObject(x+1).getClass().getSimpleName().equals("Integer"))
                    PdfPCell Celda = new PdfPCell(new Paragraph(String.valueOf(Resultados.getObject(x + 1)), FontFactory.getFont("arial", 9, BaseColor.BLACK)));
                    Celda.setHorizontalAlignment(Element.ALIGN_CENTER);
                    tabla.addCell(Celda);
                }
            }

            documento.add(tabla);
            documento.close();
        } catch (DocumentException e) {
            Log.SaveLog(e.toString());
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error",
                    JOptionPane.ERROR_MESSAGE);
        } catch (IOException e) {
            Log.SaveLog(e.toString());
            JOptionPane.showMessageDialog(null, e.getMessage(), "error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void CreateTablePDF(JTable tabla) {
        boolean shapes = false;
        Document document = new Document();
        Calendar c = Calendar.getInstance();
        String date = Integer.toString(c.get(Calendar.DAY_OF_MONTH))
                + "-" + Integer.toString(c.get(Calendar.MONTH))
                + "-" + Integer.toString(c.get(Calendar.YEAR))
                + " " + Integer.toString(c.get(Calendar.HOUR_OF_DAY))
                + "-" + Integer.toString(c.get(Calendar.MINUTE))
                + "-" + Integer.toString(c.get(Calendar.SECOND));
        File Dir = new File(System.getProperty("user.dir") + "/Reportes/");
        if (!Dir.exists()) {
            Dir.mkdirs();
        }
        try {
            PdfWriter writer;
            if (shapes) {
                writer = PdfWriter.getInstance(document,
                        new FileOutputStream(System.getProperty("user.dir") + "/Reportes/Tabla " + date + ".pdf"));
            } else {
                writer = PdfWriter.getInstance(document,
                        new FileOutputStream(System.getProperty("user.dir") + "/Reportes/Tabla " + date + ".pdf"));
            }

            document.open();
            PdfContentByte cb = writer.getDirectContent();
            PdfTemplate tp = cb.createTemplate(500, 500);
            Graphics2D g2;
            if (shapes) {
                g2 = tp.createGraphicsShapes(500, 500);
            } else {
                g2 = tp.createGraphics(500, 500);
            }

            g2.dispose();
            cb.addTemplate(tp, 30, 300);
        } catch (Exception e) {
            Log.SaveLog(e.toString());
        }
        document.close();
    }

}
