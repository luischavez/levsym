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
package com.github.luischavez.levsym.modulos.venta;

import com.github.luischavez.levsym.modulos.funcion.Calc;
import com.github.luischavez.levsym.modulos.funcion.Log;
import com.github.luischavez.levsym.modulos.funcion.Sql;
import com.github.luischavez.levsym.modelos.TableModelNotEdit;

import java.awt.event.MouseEvent;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Calendar;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Luis
 */
public class NuevoVenta extends javax.swing.JPanel {

    private DefaultTableModel modelo = new TableModelNotEdit();

    /**
     * Creates new form NuevoVenta
     */
    public NuevoVenta() {
        initComponents();
        ObtenerProductos();
        ObtenerVendedores();
        ObtenerCobradores();
    }

    public NuevoVenta(Object[] Item) {
        initComponents();
        ObtenerProductos();
        ObtenerVendedores();
        ObtenerCobradores();
        ClaveCliente.setText(Calc.ZeroFill("00000", Item[0].toString()));
        Nombre.setText(Item[1].toString());

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        PopMenu = new javax.swing.JPopupMenu();
        Editar = new javax.swing.JMenuItem();
        Quitar = new javax.swing.JMenuItem();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        NoFactura = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        ClaveCliente = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        Nombre = new javax.swing.JTextField();
        Fecha = new com.toedter.calendar.JDateChooser();
        jPanel2 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        Producto = new javax.swing.JComboBox();
        Vendedor = new javax.swing.JComboBox();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        Ventas = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        ImporteVenta = new javax.swing.JTextField();
        ImportePago = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        TipoPago = new javax.swing.JComboBox();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        SubTotal = new javax.swing.JTextField();
        Cobrador = new javax.swing.JComboBox();
        jLabel6 = new javax.swing.JLabel();
        NoPagos = new javax.swing.JSpinner();
        jLabel11 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        Enganche = new com.github.luischavez.levsym.modelos.JTextFieldOnlyNum();
        Saldo = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        Enviar = new javax.swing.JButton();

        Editar.setText("Editar");
        Editar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EditarActionPerformed(evt);
            }
        });
        PopMenu.add(Editar);

        Quitar.setText("Quitar");
        Quitar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                QuitarActionPerformed(evt);
            }
        });
        PopMenu.add(Quitar);

        setBackground(new java.awt.Color(0, 102, 204));

        jPanel1.setBackground(new java.awt.Color(0, 153, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, null, java.awt.Color.lightGray, null, null));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("No. Factura");

        NoFactura.setEditable(false);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Clave Cliente");

        ClaveCliente.setEditable(false);

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Nombre");

        Nombre.setEditable(false);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(NoFactura, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 181, Short.MAX_VALUE)
                        .addComponent(Fecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(ClaveCliente, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 78, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 78, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 49, Short.MAX_VALUE)
                                .addGap(212, 212, 212))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(Nombre, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(NoFactura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)))
                    .addComponent(Fecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ClaveCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Nombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        Calendar c = Calendar.getInstance();
        Fecha.setDate(c.getTime());

        jPanel2.setBackground(new java.awt.Color(0, 153, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, null, java.awt.Color.lightGray, null, null));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Producto");

        Producto.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Selecciona" }));
        Producto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ProductoActionPerformed(evt);
            }
        });

        Vendedor.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Selecciona" }));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Vendedor");

        Ventas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        Ventas.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        Ventas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                VentasMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(Ventas);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 329, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Producto, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE)
                                .addGap(99, 99, 99)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Vendedor, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))
                        .addGap(2, 2, 2)))
                .addGap(20, 20, 20))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Producto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Vendedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(0, 153, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, null, java.awt.Color.lightGray, null, null));

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Importe Venta");

        ImporteVenta.setEditable(false);
        ImporteVenta.setText("0");

        ImportePago.setEditable(false);
        ImportePago.setText("0");

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Tipo de Pago");

        TipoPago.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Contado", "Abonos" }));
        TipoPago.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TipoPagoActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Importe C/Pago");

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("SubTotal");

        SubTotal.setEditable(false);
        SubTotal.setText("0");

        Cobrador.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Selecciona" }));
        Cobrador.setEnabled(false);

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Cobrador");

        NoPagos.setEnabled(false);
        NoPagos.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                NoPagosStateChanged(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("No. Pagos");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(TipoPago, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(jLabel10)
                    .addComponent(SubTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(ImporteVenta, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.LEADING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(ImportePago, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel6)
                            .addComponent(Cobrador, 0, 122, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(NoPagos, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11)))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jLabel6)
                    .addComponent(jLabel11))
                .addGap(5, 5, 5)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TipoPago, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Cobrador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(NoPagos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jLabel9)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(ImporteVenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(SubTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(ImportePago, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.setBackground(new java.awt.Color(0, 153, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, null, java.awt.Color.lightGray, null, null));

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Enganche");

        Enganche.setEditable(false);
        Enganche.setText("0");
        Enganche.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                EngancheKeyReleased(evt);
            }
        });

        Saldo.setEditable(false);
        Saldo.setText("0");

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Saldo");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Enganche, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel13)
                    .addComponent(Saldo, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(111, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(jLabel13))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Enganche, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Saldo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        Enviar.setText("Enviar");
        Enviar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EnviarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, 363, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Enviar, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Enviar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void EnviarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EnviarActionPerformed
        // TODO add your handling code here:
        Object[] Entry = new Object[12];
        Entry[0] = ClaveCliente.getText();
        String[] splitVendedor = Vendedor.getSelectedItem().toString().split(" ", 2);
        Entry[1] = splitVendedor[0];
        String Product = new String();
        String Cantidad = new String();
        for (int x = 0; x < Ventas.getRowCount(); x++) {
            Product += Ventas.getValueAt(x, 1) + ",";
            Cantidad += Ventas.getValueAt(x, 4);
        }
        Entry[2] = Product;
        Entry[3] = Cantidad;
        Entry[4] = SubTotal.getText();
        Entry[5] = ImporteVenta.getText();
        Entry[6] = Enganche.getText();
        Entry[7] = TipoPago.getSelectedItem();
        Entry[8] = Saldo.getText();
        Entry[9] = NoPagos.getValue();
        Entry[10] = ImportePago.getText();
        if (Cobrador.getSelectedIndex() <= 0) {
            Entry[11] = 0;
        } else {
            String[] splitCobrador = Cobrador.getSelectedItem().toString().split(" ", 2);
            Entry[11] = splitCobrador[0];
        }
        Update(Entry);
    }//GEN-LAST:event_EnviarActionPerformed

    private void TipoPagoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TipoPagoActionPerformed
        // TODO add your handling code here:
        if (TipoPago.getSelectedIndex() == 0) {
            Cobrador.setEnabled(false);
            Cobrador.setSelectedIndex(0);
            NoPagos.setEnabled(false);
            NoPagos.setValue(0);
            Enganche.setEditable(false);
            Enganche.setText("0");
        } else {
            Cobrador.setEnabled(true);
            NoPagos.setEnabled(true);
            Enganche.setEditable(true);
            Saldo.setText(ImporteVenta.getText());
        }
    }//GEN-LAST:event_TipoPagoActionPerformed

    private void ProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ProductoActionPerformed
        // TODO add your handling code here:
        Boolean IsFree = true;
        if (Producto.getSelectedIndex() > 0) {
            String[] splitProducto = Producto.getSelectedItem().toString().split(" ", 2);
            for (int x = 0; x < Ventas.getRowCount(); x++) {
                if (Ventas.getValueAt(x, 0).toString().equals(splitProducto[0])) {
                    IsFree = false;
                }
            }
            if (IsFree) {
                AgregarProducto(Producto.getSelectedItem());
                ActualizarPrecios();
            } else {
                JOptionPane.showMessageDialog(Ventas, "Producto ya agregado, si quieres cambiar la cantidad editalo", "Error", JOptionPane.ERROR_MESSAGE, null);
            }
        }
    }//GEN-LAST:event_ProductoActionPerformed

    private void QuitarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_QuitarActionPerformed
        // TODO add your handling code here:
        if (Ventas.getSelectedRow() >= 0) {
            modelo.removeRow(Ventas.getSelectedRow());
            ActualizarPrecios();
        }
    }//GEN-LAST:event_QuitarActionPerformed

    private void EditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EditarActionPerformed
        // TODO add your handling code here:
        if (Ventas.getSelectedRow() >= 0) {
            String Cantidad = JOptionPane.showInputDialog(this, "Cantidad", "Selecciona la cantidad", JOptionPane.INFORMATION_MESSAGE);
            if (Cantidad != null) {
                Ventas.setValueAt(Cantidad, Ventas.getSelectedRow(), Ventas.getColumnCount() - 1);
            }
            ActualizarPrecios();
        }
    }//GEN-LAST:event_EditarActionPerformed

    private void VentasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_VentasMouseClicked
        // TODO add your handling code here:
        if (Ventas.getSelectedRow() >= 0) {
            if (evt.getButton() == MouseEvent.BUTTON3) {
                PopMenu.show(Ventas, evt.getX(), evt.getY());
            }
        }
    }//GEN-LAST:event_VentasMouseClicked

    private void NoPagosStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_NoPagosStateChanged
        // TODO add your handling code here:
        Double Total = Double.parseDouble(ImporteVenta.getText());
        int Pagos = Integer.valueOf(NoPagos.getValue().toString());
        ImportePago.setText(String.valueOf(Total / Pagos));
    }//GEN-LAST:event_NoPagosStateChanged

    private void EngancheKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_EngancheKeyReleased
        // TODO add your handling code here:
        Double enganche = Double.valueOf(Enganche.getText());
        Double Total = Double.valueOf(ImporteVenta.getText());
        Saldo.setText(String.valueOf(Total - enganche));
    }//GEN-LAST:event_EngancheKeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField ClaveCliente;
    private javax.swing.JComboBox Cobrador;
    private javax.swing.JMenuItem Editar;
    private javax.swing.JTextField Enganche;
    private javax.swing.JButton Enviar;
    private com.toedter.calendar.JDateChooser Fecha;
    private javax.swing.JTextField ImportePago;
    private javax.swing.JTextField ImporteVenta;
    private javax.swing.JTextField NoFactura;
    private javax.swing.JSpinner NoPagos;
    private javax.swing.JTextField Nombre;
    private javax.swing.JPopupMenu PopMenu;
    private javax.swing.JComboBox Producto;
    private javax.swing.JMenuItem Quitar;
    private javax.swing.JTextField Saldo;
    private javax.swing.JTextField SubTotal;
    private javax.swing.JComboBox TipoPago;
    private javax.swing.JComboBox Vendedor;
    private javax.swing.JTable Ventas;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables

    private void ObtenerProductos() {
        try {
            ResultSet SqlQuery = Sql.SqlQuery(Sql.connection, "SELECT clave,nombre FROM productos");
            while (SqlQuery.next()) {
                Producto.addItem(SqlQuery.getObject(1).toString()
                        + " " + SqlQuery.getObject(2).toString());
            }
            Sql.Clear();
        } catch (SQLException ex) {
            Log.SaveLog(ex.toString());
        }
    }

    private void ObtenerVendedores() {
        try {
            ResultSet SqlQuery = Sql.SqlQuery(Sql.connection, "SELECT clave,nombre FROM vendedor");
            while (SqlQuery.next()) {
                Vendedor.addItem(SqlQuery.getObject(1).toString()
                        + " " + SqlQuery.getObject(2).toString());
            }
            Sql.Clear();
        } catch (SQLException ex) {
            Log.SaveLog(ex.toString());
        }
    }

    private void ObtenerCobradores() {
        try {
            ResultSet SqlQuery = Sql.SqlQuery(Sql.connection, "SELECT clave,nombre FROM cobradores");
            while (SqlQuery.next()) {
                Cobrador.addItem(SqlQuery.getObject(1).toString()
                        + " " + SqlQuery.getObject(2).toString());
            }
            Sql.Clear();
        } catch (SQLException ex) {
            Log.SaveLog(ex.toString());
        }
    }

    private void AgregarProducto(Object Clave) {
        String[] splitProducto = Clave.toString().split(" ", 2);
        String Cantidad = JOptionPane.showInputDialog(this, "Cantidad", "Selecciona la cantidad", JOptionPane.INFORMATION_MESSAGE);
        try {
            ResultSet SqlQuery = Sql.SqlQuery(Sql.connection, "SELECT clave,nombre,iva,precio_venta FROM productos WHERE clave=" + splitProducto[0]);
            ResultSetMetaData metaData = SqlQuery.getMetaData();
            Object[] Row = new Object[metaData.getColumnCount() + 1];
            if (modelo.getColumnCount() <= 0) {
                for (int x = 0; x < metaData.getColumnCount(); x++) {
                    modelo.addColumn(metaData.getColumnName(x + 1));
                }
                modelo.addColumn("Cantidad");
                Ventas.setModel(modelo);
            }

            if (SqlQuery.next()) {
                for (int x = 0; x < metaData.getColumnCount(); x++) {
                    Row[x] = SqlQuery.getObject(x + 1);
                }
                Row[metaData.getColumnCount()] = Integer.valueOf(Cantidad);
            }
            modelo.addRow(Row);
            Sql.Clear();
        } catch (SQLException ex) {
            Log.SaveLog(ex.toString());
        }
    }

    private void Update(Object[] Entry) {
        try {
            PreparedStatement SqlQueryStmts = null;
            SqlQueryStmts = Sql.SqlQueryStmts(Sql.connection, "INSERT INTO ventas VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
            SqlQueryStmts.setNull(1, java.sql.Types.INTEGER); //clave
            SqlQueryStmts.setDate(2, new Date(Fecha.getDate().getTime())); //Fecha
            SqlQueryStmts.setInt(3, Integer.valueOf(Entry[0].toString())); //Clave Cliente
            SqlQueryStmts.setInt(4, Integer.valueOf(Entry[1].toString())); //Clave Vendedor
            SqlQueryStmts.setString(5, Entry[2].toString()); //Productos
            SqlQueryStmts.setInt(6, Integer.valueOf(Entry[3].toString())); //Cantidad
            SqlQueryStmts.setDouble(7, Double.valueOf(Entry[4].toString())); //Subtotal
            SqlQueryStmts.setDouble(8, Double.valueOf(Entry[5].toString())); //Importe Venta
            SqlQueryStmts.setDouble(9, Double.valueOf(Entry[6].toString())); //Enganche
            SqlQueryStmts.setString(10, Entry[7].toString()); //Tipo Pago
            SqlQueryStmts.setDouble(11, Double.valueOf(Entry[8].toString())); //Saldo
            SqlQueryStmts.setInt(12, Integer.valueOf(Entry[9].toString())); //numero de pagos
            SqlQueryStmts.setDouble(13, Double.valueOf(Entry[11].toString())); //importe por pago
            SqlQueryStmts.setInt(14, Integer.valueOf(Entry[11].toString())); //cobrador
            SqlQueryStmts.execute();
            ResultSet generatedKeys = SqlQueryStmts.getGeneratedKeys();
            if (generatedKeys.next()) {
                NoFactura.setText(Calc.ZeroFill("00000", generatedKeys.getObject(1).toString()));
            }
            SqlQueryStmts.close();
            Sql.Clear();
            JOptionPane.showMessageDialog(this, "Registro Insertado Correctamente", "SQL INFO", JOptionPane.INFORMATION_MESSAGE, null);
        } catch (SQLException ex) {
            Log.SaveLog(ex.toString());
            JOptionPane.showMessageDialog(this, "Ocurrio un error", "SQL ERROR", JOptionPane.ERROR_MESSAGE, null);
        }
    }

    private void ActualizarPrecios() {
        Double IVA = new Double(0);
        Double Costo = new Double(0);
        for (int x = 0; x < Ventas.getRowCount(); x++) {
            IVA += Double.parseDouble(Ventas.getValueAt(x, 2).toString()) * Integer.parseInt(Ventas.getValueAt(x, 4).toString());
            Costo += Double.parseDouble(Ventas.getValueAt(x, 3).toString()) * Integer.parseInt(Ventas.getValueAt(x, 4).toString());
        }
        SubTotal.setText(String.valueOf(IVA));
        ImporteVenta.setText(String.valueOf(IVA + Costo));
    }

}