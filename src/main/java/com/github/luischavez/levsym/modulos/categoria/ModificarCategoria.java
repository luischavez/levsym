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
package com.github.luischavez.levsym.modulos.categoria;

import com.github.luischavez.levsym.modulos.funcion.Log;
import com.github.luischavez.levsym.modulos.funcion.Sql;
import com.github.luischavez.levsym.MainFrame;
import com.github.luischavez.levsym.modelos.TableModelNotEdit;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

/**
 *
 * @author Luis
 */
public class ModificarCategoria extends javax.swing.JPanel {

    private DefaultTableModel modelo;

    /**
     * Creates new form ModificarCategoria
     */
    public ModificarCategoria() {
        initComponents();
        Cargar();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        setBackground(new java.awt.Color(0, 102, 204));
        setMaximumSize(new java.awt.Dimension(419, 323));
        setMinimumSize(new java.awt.Dimension(419, 323));

        jPanel1.setBackground(new java.awt.Color(0, 153, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, null, java.awt.Color.lightGray, null, null));

        jScrollPane1.setAutoscrolls(true);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Para editar una Categoria da doble click en la Categoria");

        jButton1.setText("Recargar Categorias");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(jButton1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        // TODO add your handling code here:
        if (evt.getClickCount() == 2) {
            int selectedRow = jTable1.getSelectedRow();
            if (selectedRow >= 0) {
                Object valueAt = jTable1.getValueAt(selectedRow, 0);
                Editar(Integer.parseInt(valueAt.toString()));
            }
        }
}//GEN-LAST:event_jTable1MouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        Cargar();
}//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables

    private void Cargar() {
        try {
            modelo = new TableModelNotEdit();
            ResultSet SqlQuery = Sql.SqlQuery(Sql.connection, "SELECT * FROM categoria");
            int columnCount = SqlQuery.getMetaData().getColumnCount();
            if (jTable1.getModel() != modelo) {
                for (int x = 0; x < 3; x++) {
                    modelo.addColumn(SqlQuery.getMetaData().getColumnName(x + 1));
                }
                jTable1.setModel(modelo);
                JTableHeader Header = jTable1.getTableHeader();
                Header.setReorderingAllowed(false);
            }
            while (SqlQuery.next()) {
                Object[] Row = new Object[columnCount];
                for (int x = 0; x < 3; x++) {
                    Row[x] = SqlQuery.getObject(x + 1);
                }
                modelo.addRow(Row);
            }
            for (int x = 0; x < 3; x++) {
                DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
                tcr.setHorizontalAlignment(SwingConstants.CENTER);
                jTable1.getColumnModel().getColumn(x).setCellRenderer(tcr);
            }
        } catch (SQLException ex) {
            Log.SaveLog(ex.toString());
        }
    }

    public void Editar(int Clave) {
        try {
            ResultSet SqlQuery = Sql.SqlQuery(Sql.connection, "SELECT * FROM categoria WHERE clave=" + Clave);
            int columnCount = SqlQuery.getMetaData().getColumnCount();
            if (SqlQuery.next()) {
                Object[] Row = new Object[columnCount];
                for (int x = 0; x < columnCount; x++) {
                    Row[x] = SqlQuery.getObject(x + 1);
                }
                MainFrame.Window(new NuevoCategoria(Row), "Modificar categoria - " + SqlQuery.getString(2), 420, 290, 0);
            }
        } catch (SQLException ex) {
            Log.SaveLog(ex.toString());
        }
    }

}
