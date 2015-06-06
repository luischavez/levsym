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
package com.github.luischavez.levsym.modulos.estado;

import com.github.luischavez.levsym.modulos.funcion.Calc;
import com.github.luischavez.levsym.modulos.funcion.Log;
import com.github.luischavez.levsym.modulos.funcion.Sql;
import com.github.luischavez.levsym.MainFrame;
import com.github.luischavez.levsym.modelos.TableModelNotEdit;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

/**
 *
 * @author Luis
 */
public class BuscarEstado extends javax.swing.JPanel {

    private DefaultTableModel modelo = new TableModelNotEdit();
    private DefaultTableModel modelo2 = new TableModelNotEdit();
    private Object[] Item, Item2;
    private int Modo = 0;

    /**
     * Creates new form BuscarEstado
     */
    public BuscarEstado(int Modo) {
        initComponents();
        ObtenerClientes();
        this.Modo = Modo;
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
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox();
        jTextField1 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        Estado = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(0, 102, 204));

        jPanel1.setBackground(new java.awt.Color(0, 153, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, null, java.awt.Color.lightGray, null, null));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Cliente");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Buscar");

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Selecciona" }));

        jButton1.setText("Buscar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jTable1.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jScrollPane1, 0, 0, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addGap(3, 3, 3)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 185, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel2.setBackground(new java.awt.Color(0, 153, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, null, java.awt.Color.lightGray, null, null));

        Estado.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        Estado.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        Estado.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                EstadoMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(Estado);

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Estado");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 332, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        try {
            if (this.jComboBox2.getSelectedIndex() > 0) {
                ResultSet Resultado = Sql.SqlQuery(Sql.connection, "SELECT * FROM clientes WHERE " + jComboBox2.getSelectedItem() + " = '" + jTextField1.getText() + "'");
                ResultSetMetaData metaData = Resultado.getMetaData();
                Object[] ColumnName = new Object[metaData.getColumnCount()];
                Object[] Row = new Object[metaData.getColumnCount()];

                if (Resultado.next()) {
                    modelo = new TableModelNotEdit();
                    for (int x = 0; x < metaData.getColumnCount(); x++) {
                        ColumnName[x] = metaData.getColumnName(x + 1);
                    }
                    Resultado.previous();
                    for (int x = 0; x < metaData.getColumnCount(); x++) {
                        modelo.addColumn(ColumnName[x]);
                    }
                }

                jTable1.setModel(modelo);
                JTableHeader Header = jTable1.getTableHeader();
                Header.setReorderingAllowed(false);

                while (Resultado.next()) {
                    for (int x = 0; x < metaData.getColumnCount(); x++) {
                        Row[x] = Resultado.getObject(x + 1);
                    }
                    modelo.addRow(Row);
                }
            }
        } catch (SQLException ex) {
            Log.SaveLog(ex.toString());
        }
}//GEN-LAST:event_jButton1ActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        // TODO add your handling code here:
        if (evt.getClickCount() == 2) {
            try {
                Object Clave = jTable1.getValueAt(jTable1.getSelectedRow(), 0);
                ResultSet SqlQuery = Sql.SqlQuery(Sql.connection, "SELECT * FROM clientes WHERE clave=" + Clave);
                ResultSetMetaData metaData = SqlQuery.getMetaData();
                Object[] Row = new Object[metaData.getColumnCount()];
                while (SqlQuery.next()) {
                    for (int x = 0; x < Row.length; x++) {
                        Row[x] = SqlQuery.getObject(x + 1);
                    }
                }
                Item = Row;
                ObtenerEstados(Row);
            } catch (SQLException ex) {
                Log.SaveLog(ex.toString());
            }
        }
}//GEN-LAST:event_jTable1MouseClicked

    private void EstadoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_EstadoMouseClicked
        // TODO add your handling code here:
        if (evt.getClickCount() == 2) {
            try {
                Object Clave = Estado.getValueAt(Estado.getSelectedRow(), 0);
                ResultSet SqlQuery = Sql.SqlQuery(Sql.connection, "SELECT * FROM ventas WHERE clave=" + Clave);
                ResultSetMetaData metaData = SqlQuery.getMetaData();
                Object[] Row = new Object[metaData.getColumnCount()];
                while (SqlQuery.next()) {
                    for (int x = 0; x < Row.length; x++) {
                        Row[x] = SqlQuery.getObject(x + 1);
                    }
                }
                Item2 = Row;
                switch (Modo) {
                    case 0:
                        MainFrame.Window(new EstadoCliente(Item, Item2), "Estado de Cuenta - " + Calc.ZeroFill("00000", Row[0].toString()), 415, 570, 0);
                        break;
                    case 1:
                        MainFrame.Window(new RealizarPago(Item, Item2), "Cuenta - " + Calc.ZeroFill("00000", Row[0].toString()), 430, 400, 0);
                        break;
                }
            } catch (SQLException ex) {
                Log.SaveLog(ex.toString());
            }
        }
    }//GEN-LAST:event_EstadoMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable Estado;
    private javax.swing.JButton jButton1;
    private javax.swing.JComboBox jComboBox2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables

    private void ObtenerClientes() {
        try {
            ResultSet SqlQuery = Sql.SqlQuery(Sql.connection, "SELECT * FROM clientes");
            ResultSetMetaData metaData = SqlQuery.getMetaData();
            Object[] Row = new Object[metaData.getColumnCount()];
            for (int x = 0; x < metaData.getColumnCount(); x++) {
                modelo.addColumn(metaData.getColumnName(x + 1));
                jComboBox2.addItem(metaData.getColumnName(x + 1));
            }
            while (SqlQuery.next()) {
                for (int x = 0; x < Row.length; x++) {
                    Row[x] = SqlQuery.getObject(x + 1);
                }
                modelo.addRow(Row);
            }
            jTable1.setModel(modelo);
            Sql.Clear();
        } catch (SQLException ex) {
            Log.SaveLog(ex.toString());
        }
    }

    private void ObtenerEstados(Object[] Data) {
        try {
            for (int x = 0; x < modelo2.getRowCount(); x++) {
                modelo2.removeRow(x);
            }
            ResultSet SqlQuery = Sql.SqlQuery(Sql.connection, "SELECT * FROM ventas WHERE cliente=" + Calc.ZeroFill("00000", Data[0].toString()));
            ResultSetMetaData metaData = SqlQuery.getMetaData();
            Object[] Row = new Object[metaData.getColumnCount()];
            if (modelo2.getColumnCount() <= 0) {
                for (int x = 0; x < Row.length; x++) {
                    modelo2.addColumn(metaData.getColumnName(x + 1));
                }
            }

            while (SqlQuery.next()) {
                for (int x = 0; x < Row.length; x++) {
                    Row[x] = SqlQuery.getObject(x + 1);
                }
                modelo2.addRow(Row);
            }
            Estado.setModel(modelo2);
            Sql.Clear();
        } catch (SQLException ex) {
            Log.SaveLog(ex.toString());
        }
    }

}