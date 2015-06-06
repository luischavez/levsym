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
package com.github.luischavez.levsym;

import com.github.luischavez.levsym.modulos.almacen.EliminarAlmacen;
import com.github.luischavez.levsym.modulos.almacen.ModificarAlmacen;
import com.github.luischavez.levsym.modulos.almacen.NuevoAlmacen;
import com.github.luischavez.levsym.modulos.funcion.DrawImageBG;
import com.github.luischavez.levsym.modelos.ClosableInternalFrame;
import com.github.luischavez.levsym.modulos.funcion.Log;
import com.github.luischavez.levsym.modulos.producto.NuevoProducto;
import com.github.luischavez.levsym.modulos.widget.WClock;
import com.github.luischavez.levsym.modulos.funcion.Widget;
import com.github.luischavez.levsym.modulos.ayuda.Acerca;
import com.github.luischavez.levsym.modulos.categoria.EliminarCategoria;
import com.github.luischavez.levsym.modulos.categoria.ModificarCategoria;
import com.github.luischavez.levsym.modulos.categoria.NuevoCategoria;
import com.github.luischavez.levsym.modulos.cliente.EliminarCliente;
import com.github.luischavez.levsym.modulos.cliente.ModificarCliente;
import com.github.luischavez.levsym.modulos.cliente.NuevoCliente;
import com.github.luischavez.levsym.modulos.cobrador.EliminarCobrador;
import com.github.luischavez.levsym.modulos.cobrador.ModificarCobrador;
import com.github.luischavez.levsym.modulos.cobrador.NuevoCobrador;
import com.github.luischavez.levsym.modulos.compra.NuevoCompra;
import com.github.luischavez.levsym.modulos.departamento.EliminarDepartamento;
import com.github.luischavez.levsym.modulos.departamento.ModificarDepartamento;
import com.github.luischavez.levsym.modulos.departamento.NuevoDepartamento;
import com.github.luischavez.levsym.modulos.estado.BuscarEstado;
import com.github.luischavez.levsym.modulos.funcion.GetConfig;
import com.github.luischavez.levsym.modulos.funcion.Sql;
import com.github.luischavez.levsym.modulos.producto.ModificarProducto;
import com.github.luischavez.levsym.modulos.opcion.Opciones;
import com.github.luischavez.levsym.modulos.producto.EliminarProducto;
import com.github.luischavez.levsym.modulos.proveedor.EliminarProveedor;
import com.github.luischavez.levsym.modulos.proveedor.ModificarProveedor;
import com.github.luischavez.levsym.modulos.proveedor.NuevoProveedor;
import com.github.luischavez.levsym.modulos.reporte.NuevoReporte;
import com.github.luischavez.levsym.modulos.vendedor.EliminarVendedor;
import com.github.luischavez.levsym.modulos.vendedor.ModificarVendedor;
import com.github.luischavez.levsym.modulos.vendedor.NuevoVendedor;
import com.github.luischavez.levsym.modulos.venta.MostrarClientes;
import com.github.luischavez.levsym.modulos.herramientas.Calculator;
import com.github.luischavez.levsym.modulos.widget.WCalendar;
import com.github.luischavez.levsym.modulos.herramientas.Local;
import com.github.luischavez.levsym.modulos.herramientas.Logs;
import com.github.luischavez.levsym.modulos.herramientas.Shell;
import com.github.luischavez.levsym.modulos.herramientas.UploadSQL;

import java.awt.Color;
import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.SQLException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;

/**
 *
 * @author Luis
 */
public class MainFrame extends javax.swing.JFrame {

    Widget clock;
    Widget calendar;
    private Thread t;
    private ImageIcon On = new ImageIcon(getClass().getResource("/image/on.png"));
    private ImageIcon Off = new ImageIcon(getClass().getResource("/image/off.png"));

    /**
     * Creates new form MainFrame
     */
    public MainFrame() {
        initComponents();
        GetConfig config;
        try {
            config = new GetConfig();
            if (!config.Contains("WIDGETS")) {
                config.Set("WIDGETS", "true");
            }
            if (Boolean.valueOf(config.Get("WIDGETS"))) {
                /*
                 * Configura los Widgets
                 */
                clock = new Widget(new WClock(Color.BLUE, Color.white));
                clock.ShowWidget(true);
                clock.SetSize(100, 42);
                clock.SetLocation(0, 0);
                clock.SetWidget(PanelPrincipal);

                calendar = new Widget(new WCalendar());
                calendar.ShowWidget(true);
                calendar.SetSize(204, 175);
                calendar.SetLocation(this.getWidth() - calendar.GetW(), 0);
                calendar.SetWidget(PanelPrincipal);
            }
        } catch (IOException ex) {
            Log.SaveLog(ex.toString());
        }

        /*
         * Selecciona y dibuja el fondo
         */
        try {
            PanelPrincipal.setBorder(new DrawImageBG(ImageIO.read(this.getClass().getResource("/image/image.jpg"))));
        } catch (IOException ex) {
            Log.SaveLog(ex.toString());
        }

        /*
         * Crea un nuevo hilo y verifica el estado de la conexion
         * cada 30 segundos
         */
        t = new Thread(new Runnable() {

            @Override
            public void run() {

                for (;;) {

                    try {
                        if (Sql.IsActive()) {
                            Estado.setForeground(Color.green);
                            Estado.setText(" Conectado  ");
                            ReConectar.setEnabled(false);
                            Icon.setIcon(On);
                        } else {
                            Estado.setForeground(Color.red);
                            Estado.setText(" Desconectado  ");
                            ReConectar.setEnabled(true);
                            Icon.setIcon(Off);
                        }
                    } catch (SQLException ex) {
                        Log.SaveLog(ex.toString());
                    }

                    try {
                        t.sleep(1000 * 30);
                    } catch (InterruptedException ex) {
                        Log.SaveLog(ex.toString());
                    }

                }
            }
        });
        t.start();

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        PanelPrincipal = new javax.swing.JDesktopPane();
        StateBar = new javax.swing.JPanel();
        Icon = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        Estado = new javax.swing.JLabel();
        ReConectar = new javax.swing.JButton();
        Menu = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        jMenuItem1 = new javax.swing.JMenuItem();
        Cuentas = new javax.swing.JMenu();
        EstadoCuenta = new javax.swing.JMenuItem();
        RealizarPago = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        MenuOpciones = new javax.swing.JMenuItem();
        Cacluladora = new javax.swing.JMenuItem();
        Shell = new javax.swing.JMenuItem();
        Reporte = new javax.swing.JMenuItem();
        Upload = new javax.swing.JMenuItem();
        Locales = new javax.swing.JMenuItem();
        Limpiar = new javax.swing.JMenuItem();
        MenuNuevo = new javax.swing.JMenu();
        NuevoProducto = new javax.swing.JMenuItem();
        NuevoProveedor = new javax.swing.JMenuItem();
        NuevoCategoria = new javax.swing.JMenuItem();
        NuevoAlmacen = new javax.swing.JMenuItem();
        NuevoDepartamento = new javax.swing.JMenuItem();
        NuevoCompra = new javax.swing.JMenuItem();
        NuevoVenta = new javax.swing.JMenuItem();
        NuevoCliente = new javax.swing.JMenuItem();
        NuevoVendedor = new javax.swing.JMenuItem();
        NuevoCobrador = new javax.swing.JMenuItem();
        MenuModificar = new javax.swing.JMenu();
        ModificarProducto = new javax.swing.JMenuItem();
        ModificarProveedor = new javax.swing.JMenuItem();
        ModificarDepartamento = new javax.swing.JMenuItem();
        ModificarCategoria = new javax.swing.JMenuItem();
        ModificarAlmacen = new javax.swing.JMenuItem();
        ModificarCliente = new javax.swing.JMenuItem();
        ModificarVendedor = new javax.swing.JMenuItem();
        ModificarCobrador = new javax.swing.JMenuItem();
        MenuEliminar = new javax.swing.JMenu();
        EliminarProducto = new javax.swing.JMenuItem();
        EliminarProveedor = new javax.swing.JMenuItem();
        EliminarDepartamento = new javax.swing.JMenuItem();
        EliminarCategoria = new javax.swing.JMenuItem();
        EliminarAlmacen = new javax.swing.JMenuItem();
        EliminarCliente = new javax.swing.JMenuItem();
        EliminarVendedor = new javax.swing.JMenuItem();
        EliminarCobrador = new javax.swing.JMenuItem();
        MenuAyuda = new javax.swing.JMenu();
        MostrarLog = new javax.swing.JMenuItem();
        Acerca = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                formComponentResized(evt);
            }
        });
        addWindowStateListener(new java.awt.event.WindowStateListener() {
            public void windowStateChanged(java.awt.event.WindowEvent evt) {
                formWindowStateChanged(evt);
            }
        });
        getContentPane().setLayout(new java.awt.GridBagLayout());

        PanelPrincipal.setPreferredSize(new java.awt.Dimension(480, 600));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.5;
        gridBagConstraints.weighty = 1.5;
        getContentPane().add(PanelPrincipal, gridBagConstraints);

        StateBar.setLayout(new javax.swing.BoxLayout(StateBar, javax.swing.BoxLayout.LINE_AXIS));
        StateBar.add(Icon);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel1.setText("Conexion:  ");
        StateBar.add(jLabel1);

        Estado.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        Estado.setText("Estado    ");
        StateBar.add(Estado);

        ReConectar.setText("Re-conectar");
        ReConectar.setEnabled(false);
        ReConectar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ReConectarActionPerformed(evt);
            }
        });
        StateBar.add(ReConectar);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        getContentPane().add(StateBar, gridBagConstraints);

        jMenu1.setText("File");
        jMenu1.add(jSeparator1);

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Q, java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem1.setText("Salir");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        Menu.add(jMenu1);

        Cuentas.setText("Cuentas");

        EstadoCuenta.setText("Estado de Cuenta");
        EstadoCuenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EstadoCuentaActionPerformed(evt);
            }
        });
        Cuentas.add(EstadoCuenta);

        RealizarPago.setText("Realizar Pago");
        RealizarPago.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RealizarPagoActionPerformed(evt);
            }
        });
        Cuentas.add(RealizarPago);

        Menu.add(Cuentas);

        jMenu2.setText("Herramientas");

        MenuOpciones.setText("Opciones");
        MenuOpciones.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuOpcionesActionPerformed(evt);
            }
        });
        jMenu2.add(MenuOpciones);

        Cacluladora.setText("Calculadora");
        Cacluladora.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CacluladoraActionPerformed(evt);
            }
        });
        jMenu2.add(Cacluladora);

        Shell.setText("Shell");
        Shell.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ShellActionPerformed(evt);
            }
        });
        jMenu2.add(Shell);

        Reporte.setText("Reporte");
        Reporte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ReporteActionPerformed(evt);
            }
        });
        jMenu2.add(Reporte);

        Upload.setText("Upload");
        Upload.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UploadActionPerformed(evt);
            }
        });
        jMenu2.add(Upload);

        Locales.setText("Locales");
        Locales.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LocalesActionPerformed(evt);
            }
        });
        jMenu2.add(Locales);

        Limpiar.setText("Limpiar memoria");
        Limpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LimpiarActionPerformed(evt);
            }
        });
        jMenu2.add(Limpiar);

        Menu.add(jMenu2);

        MenuNuevo.setText("Nuevo");

        NuevoProducto.setText("Producto");
        NuevoProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NuevoProductoActionPerformed(evt);
            }
        });
        MenuNuevo.add(NuevoProducto);

        NuevoProveedor.setText("Proveedor");
        NuevoProveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NuevoProveedorActionPerformed(evt);
            }
        });
        MenuNuevo.add(NuevoProveedor);

        NuevoCategoria.setText("Categoria");
        NuevoCategoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NuevoCategoriaActionPerformed(evt);
            }
        });
        MenuNuevo.add(NuevoCategoria);

        NuevoAlmacen.setText("Almacen");
        NuevoAlmacen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NuevoAlmacenActionPerformed(evt);
            }
        });
        MenuNuevo.add(NuevoAlmacen);

        NuevoDepartamento.setText("Departamento");
        NuevoDepartamento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NuevoDepartamentoActionPerformed(evt);
            }
        });
        MenuNuevo.add(NuevoDepartamento);

        NuevoCompra.setText("Compra");
        NuevoCompra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NuevoCompraActionPerformed(evt);
            }
        });
        MenuNuevo.add(NuevoCompra);

        NuevoVenta.setText("Venta");
        NuevoVenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NuevoVentaActionPerformed(evt);
            }
        });
        MenuNuevo.add(NuevoVenta);

        NuevoCliente.setText("Cliente");
        NuevoCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NuevoClienteActionPerformed(evt);
            }
        });
        MenuNuevo.add(NuevoCliente);

        NuevoVendedor.setText("Vendedor");
        NuevoVendedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NuevoVendedorActionPerformed(evt);
            }
        });
        MenuNuevo.add(NuevoVendedor);

        NuevoCobrador.setText("Cobrador");
        NuevoCobrador.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NuevoCobradorActionPerformed(evt);
            }
        });
        MenuNuevo.add(NuevoCobrador);

        Menu.add(MenuNuevo);

        MenuModificar.setText("Modificar");

        ModificarProducto.setText("Producto");
        ModificarProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ModificarProductoActionPerformed(evt);
            }
        });
        MenuModificar.add(ModificarProducto);

        ModificarProveedor.setText("Proveedor");
        ModificarProveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ModificarProveedorActionPerformed(evt);
            }
        });
        MenuModificar.add(ModificarProveedor);

        ModificarDepartamento.setText("Departamento");
        ModificarDepartamento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ModificarDepartamentoActionPerformed(evt);
            }
        });
        MenuModificar.add(ModificarDepartamento);

        ModificarCategoria.setText("Categoria");
        ModificarCategoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ModificarCategoriaActionPerformed(evt);
            }
        });
        MenuModificar.add(ModificarCategoria);

        ModificarAlmacen.setText("Almacen");
        ModificarAlmacen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ModificarAlmacenActionPerformed(evt);
            }
        });
        MenuModificar.add(ModificarAlmacen);

        ModificarCliente.setText("Cliente");
        ModificarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ModificarClienteActionPerformed(evt);
            }
        });
        MenuModificar.add(ModificarCliente);

        ModificarVendedor.setText("Vendedor");
        ModificarVendedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ModificarVendedorActionPerformed(evt);
            }
        });
        MenuModificar.add(ModificarVendedor);

        ModificarCobrador.setText("Cobrador");
        ModificarCobrador.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ModificarCobradorActionPerformed(evt);
            }
        });
        MenuModificar.add(ModificarCobrador);

        Menu.add(MenuModificar);

        MenuEliminar.setText("Eliminar");

        EliminarProducto.setText("Producto");
        EliminarProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EliminarProductoActionPerformed(evt);
            }
        });
        MenuEliminar.add(EliminarProducto);

        EliminarProveedor.setText("Proveedor");
        EliminarProveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EliminarProveedorActionPerformed(evt);
            }
        });
        MenuEliminar.add(EliminarProveedor);

        EliminarDepartamento.setText("Departamento");
        EliminarDepartamento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EliminarDepartamentoActionPerformed(evt);
            }
        });
        MenuEliminar.add(EliminarDepartamento);

        EliminarCategoria.setText("Categoria");
        EliminarCategoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EliminarCategoriaActionPerformed(evt);
            }
        });
        MenuEliminar.add(EliminarCategoria);

        EliminarAlmacen.setText("Almacen");
        EliminarAlmacen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EliminarAlmacenActionPerformed(evt);
            }
        });
        MenuEliminar.add(EliminarAlmacen);

        EliminarCliente.setText("Cliente");
        EliminarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EliminarClienteActionPerformed(evt);
            }
        });
        MenuEliminar.add(EliminarCliente);

        EliminarVendedor.setText("Vendedor");
        EliminarVendedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EliminarVendedorActionPerformed(evt);
            }
        });
        MenuEliminar.add(EliminarVendedor);

        EliminarCobrador.setText("Cobrador");
        EliminarCobrador.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EliminarCobradorActionPerformed(evt);
            }
        });
        MenuEliminar.add(EliminarCobrador);

        Menu.add(MenuEliminar);

        MenuAyuda.setText("Ayuda");

        MostrarLog.setText("Mostrar Log");
        MostrarLog.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MostrarLogActionPerformed(evt);
            }
        });
        MenuAyuda.add(MostrarLog);

        Acerca.setText("Acerca de..");
        Acerca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AcercaActionPerformed(evt);
            }
        });
        MenuAyuda.add(Acerca);

        Menu.add(MenuAyuda);

        setJMenuBar(Menu);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void NuevoProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NuevoProductoActionPerformed
        // TODO add your handling code here:
        ShowWindow(new NuevoProducto(), "Nuevo Producto", 400, 496, 0);
    }//GEN-LAST:event_NuevoProductoActionPerformed

    private void formWindowStateChanged(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowStateChanged
        // TODO add your handling code here:
        SetWdigetsLocation();
    }//GEN-LAST:event_formWindowStateChanged

    private void MenuOpcionesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuOpcionesActionPerformed
        // TODO add your handling code here:
        ShowWindow(new Opciones(), "Opciones", 420, 324, 0);
    }//GEN-LAST:event_MenuOpcionesActionPerformed

    private void CacluladoraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CacluladoraActionPerformed
        // TODO add your handling code here:
        ShowWindow(new Calculator(), "Calculadora", 220, 191, 0);
    }//GEN-LAST:event_CacluladoraActionPerformed

    private void ShellActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ShellActionPerformed
        // TODO add your handling code here:
        ShowWindow(new Shell(), "Shell", 400, 300, 1);
    }//GEN-LAST:event_ShellActionPerformed

    private void AcercaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AcercaActionPerformed
        // TODO add your handling code here:
        ShowWindow(new Acerca(), "Acerca de...", 400, 300, 0);
    }//GEN-LAST:event_AcercaActionPerformed

    private void MostrarLogActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MostrarLogActionPerformed
        // TODO add your handling code here:
        ShowWindow(new Logs(), "Log", 400, 300, 0);
    }//GEN-LAST:event_MostrarLogActionPerformed

    private void ModificarProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ModificarProductoActionPerformed
        // TODO add your handling code here:
        ShowWindow(new ModificarProducto(), "Modificar Producto", 439, 353, 0);
    }//GEN-LAST:event_ModificarProductoActionPerformed

    private void formComponentResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentResized
        // TODO add your handling code here:
        SetWdigetsLocation();
    }//GEN-LAST:event_formComponentResized

    private void ReporteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ReporteActionPerformed
        // TODO add your handling code here:
        ShowWindow(new NuevoReporte(), "Reportes", 420, 535, 0);
    }//GEN-LAST:event_ReporteActionPerformed

    private void EliminarProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EliminarProductoActionPerformed
        // TODO add your handling code here:
        ShowWindow(new EliminarProducto(), "Eliminar Producto", 458, 353, 0);
    }//GEN-LAST:event_EliminarProductoActionPerformed

    private void NuevoProveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NuevoProveedorActionPerformed
        // TODO add your handling code here:
        ShowWindow(new NuevoProveedor(), "Nuevo Proveedor", 390, 580, 0);
    }//GEN-LAST:event_NuevoProveedorActionPerformed

    private void ModificarProveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ModificarProveedorActionPerformed
        // TODO add your handling code here:
        ShowWindow(new ModificarProveedor(), "Modificar Proveedor", 466, 353, 0);
    }//GEN-LAST:event_ModificarProveedorActionPerformed

    private void EliminarProveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EliminarProveedorActionPerformed
        // TODO add your handling code here:
        ShowWindow(new EliminarProveedor(), "Eliminar Proveedor", 466, 353, 0);
    }//GEN-LAST:event_EliminarProveedorActionPerformed

    private void ReConectarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ReConectarActionPerformed
        try {
            // TODO add your handling code here:
            Sql.Conect();
        } catch (SQLException ex) {
            Log.SaveLog(ex.toString());
        }
    }//GEN-LAST:event_ReConectarActionPerformed

    private void UploadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UploadActionPerformed
        // TODO add your handling code here:
        ShowWindow(new UploadSQL(), "Upload", 437, 140, 0);
    }//GEN-LAST:event_UploadActionPerformed

    private void NuevoCategoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NuevoCategoriaActionPerformed
        // TODO add your handling code here:
        ShowWindow(new NuevoCategoria(), "Nueva Categoria", 420, 290, 0);
    }//GEN-LAST:event_NuevoCategoriaActionPerformed

    private void NuevoAlmacenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NuevoAlmacenActionPerformed
        // TODO add your handling code here:
        ShowWindow(new NuevoAlmacen(), "Nuevo Almacen", 420, 290, 0);
    }//GEN-LAST:event_NuevoAlmacenActionPerformed

    private void NuevoDepartamentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NuevoDepartamentoActionPerformed
        // TODO add your handling code here:
        ShowWindow(new NuevoDepartamento(), "Nuevo Departamento", 420, 290, 0);
    }//GEN-LAST:event_NuevoDepartamentoActionPerformed

    private void ModificarDepartamentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ModificarDepartamentoActionPerformed
        // TODO add your handling code here:
        ShowWindow(new ModificarDepartamento(), "Modificar Departamento", 440, 350, 0);
    }//GEN-LAST:event_ModificarDepartamentoActionPerformed

    private void ModificarCategoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ModificarCategoriaActionPerformed
        // TODO add your handling code here:
        ShowWindow(new ModificarCategoria(), "Modificar Categoria", 440, 350, 0);
    }//GEN-LAST:event_ModificarCategoriaActionPerformed

    private void ModificarAlmacenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ModificarAlmacenActionPerformed
        // TODO add your handling code here:
        ShowWindow(new ModificarAlmacen(), "Modificar Almacen", 440, 350, 0);
    }//GEN-LAST:event_ModificarAlmacenActionPerformed

    private void EliminarDepartamentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EliminarDepartamentoActionPerformed
        // TODO add your handling code here:
        ShowWindow(new EliminarDepartamento(), "Eliminar Departamento", 490, 350, 0);
    }//GEN-LAST:event_EliminarDepartamentoActionPerformed

    private void EliminarCategoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EliminarCategoriaActionPerformed
        // TODO add your handling code here:
        ShowWindow(new EliminarCategoria(), "Eliminar Categoria", 490, 350, 0);
    }//GEN-LAST:event_EliminarCategoriaActionPerformed

    private void EliminarAlmacenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EliminarAlmacenActionPerformed
        // TODO add your handling code here:
        ShowWindow(new EliminarAlmacen(), "Eliminar Almacen", 490, 350, 0);
    }//GEN-LAST:event_EliminarAlmacenActionPerformed

    private void NuevoCompraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NuevoCompraActionPerformed
        // TODO add your handling code here:
        ShowWindow(new NuevoCompra(), "Nueva Compra", 390, 520, 0);
    }//GEN-LAST:event_NuevoCompraActionPerformed

    private void LocalesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LocalesActionPerformed
        // TODO add your handling code here:
        ShowWindow(new Local(), "Administrar Locales", 410, 220, 0);
    }//GEN-LAST:event_LocalesActionPerformed

    private void LimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LimpiarActionPerformed
        // TODO add your handling code here:
        System.gc();
    }//GEN-LAST:event_LimpiarActionPerformed

    private void NuevoClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NuevoClienteActionPerformed
        // TODO add your handling code here:
        ShowWindow(new NuevoCliente(), "Nuevo Cliente", 450, 450, 0);
    }//GEN-LAST:event_NuevoClienteActionPerformed

    private void ModificarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ModificarClienteActionPerformed
        // TODO add your handling code here:
        ShowWindow(new ModificarCliente(), "Modificar Cliente", 450, 350, 0);
    }//GEN-LAST:event_ModificarClienteActionPerformed

    private void EliminarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EliminarClienteActionPerformed
        // TODO add your handling code here:
        ShowWindow(new EliminarCliente(), "Eliminar Cliente", 450, 350, 0);
    }//GEN-LAST:event_EliminarClienteActionPerformed

    private void NuevoVendedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NuevoVendedorActionPerformed
        // TODO add your handling code here:
        ShowWindow(new NuevoVendedor(), "Nuevo Vendedor", 420, 160, 0);
    }//GEN-LAST:event_NuevoVendedorActionPerformed

    private void ModificarVendedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ModificarVendedorActionPerformed
        // TODO add your handling code here:
        ShowWindow(new ModificarVendedor(), "Modificar Vendedor", 450, 350, 0);
    }//GEN-LAST:event_ModificarVendedorActionPerformed

    private void EliminarVendedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EliminarVendedorActionPerformed
        // TODO add your handling code here:
        ShowWindow(new EliminarVendedor(), "Eliminar Vendedor", 470, 350, 0);
    }//GEN-LAST:event_EliminarVendedorActionPerformed

    private void NuevoCobradorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NuevoCobradorActionPerformed
        // TODO add your handling code here:
        ShowWindow(new NuevoCobrador(), "Nuevo Cobrador", 420, 160, 0);
    }//GEN-LAST:event_NuevoCobradorActionPerformed

    private void ModificarCobradorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ModificarCobradorActionPerformed
        // TODO add your handling code here:
        ShowWindow(new ModificarCobrador(), "Modificar Cobrador", 440, 350, 0);
    }//GEN-LAST:event_ModificarCobradorActionPerformed

    private void EliminarCobradorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EliminarCobradorActionPerformed
        // TODO add your handling code here:
        ShowWindow(new EliminarCobrador(), "Eliminar Cobrador", 440, 350, 0);
    }//GEN-LAST:event_EliminarCobradorActionPerformed

    private void NuevoVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NuevoVentaActionPerformed
        // TODO add your handling code here:
        ShowWindow(new MostrarClientes(), "Nueva Venta", 400, 310, 0);
    }//GEN-LAST:event_NuevoVentaActionPerformed

    private void EstadoCuentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EstadoCuentaActionPerformed
        // TODO add your handling code here:
        ShowWindow(new BuscarEstado(0), "Buscar Estado", 400, 560, 0);
    }//GEN-LAST:event_EstadoCuentaActionPerformed

    private void RealizarPagoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RealizarPagoActionPerformed
        // TODO add your handling code here:
        ShowWindow(new BuscarEstado(1), "Buscar Estado", 400, 560, 0);
    }//GEN-LAST:event_RealizarPagoActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem Acerca;
    private javax.swing.JMenuItem Cacluladora;
    private javax.swing.JMenu Cuentas;
    private javax.swing.JMenuItem EliminarAlmacen;
    private javax.swing.JMenuItem EliminarCategoria;
    private javax.swing.JMenuItem EliminarCliente;
    private javax.swing.JMenuItem EliminarCobrador;
    private javax.swing.JMenuItem EliminarDepartamento;
    private javax.swing.JMenuItem EliminarProducto;
    private javax.swing.JMenuItem EliminarProveedor;
    private javax.swing.JMenuItem EliminarVendedor;
    private javax.swing.JLabel Estado;
    private javax.swing.JMenuItem EstadoCuenta;
    private javax.swing.JLabel Icon;
    private javax.swing.JMenuItem Limpiar;
    private javax.swing.JMenuItem Locales;
    private javax.swing.JMenuBar Menu;
    private javax.swing.JMenu MenuAyuda;
    private javax.swing.JMenu MenuEliminar;
    private javax.swing.JMenu MenuModificar;
    private javax.swing.JMenu MenuNuevo;
    private javax.swing.JMenuItem MenuOpciones;
    private javax.swing.JMenuItem ModificarAlmacen;
    private javax.swing.JMenuItem ModificarCategoria;
    private javax.swing.JMenuItem ModificarCliente;
    private javax.swing.JMenuItem ModificarCobrador;
    private javax.swing.JMenuItem ModificarDepartamento;
    private javax.swing.JMenuItem ModificarProducto;
    private javax.swing.JMenuItem ModificarProveedor;
    private javax.swing.JMenuItem ModificarVendedor;
    private javax.swing.JMenuItem MostrarLog;
    private javax.swing.JMenuItem NuevoAlmacen;
    private javax.swing.JMenuItem NuevoCategoria;
    private javax.swing.JMenuItem NuevoCliente;
    private javax.swing.JMenuItem NuevoCobrador;
    private javax.swing.JMenuItem NuevoCompra;
    private javax.swing.JMenuItem NuevoDepartamento;
    private javax.swing.JMenuItem NuevoProducto;
    private javax.swing.JMenuItem NuevoProveedor;
    private javax.swing.JMenuItem NuevoVendedor;
    private javax.swing.JMenuItem NuevoVenta;
    private static javax.swing.JDesktopPane PanelPrincipal;
    private javax.swing.JButton ReConectar;
    private javax.swing.JMenuItem RealizarPago;
    private javax.swing.JMenuItem Reporte;
    private javax.swing.JMenuItem Shell;
    private javax.swing.JPanel StateBar;
    private javax.swing.JMenuItem Upload;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    // End of variables declaration//GEN-END:variables

    /**
     * Estableze la localizacion de los widgets despues de maximizar o minimizar
     * la ventana
     *
     */
    private void SetWdigetsLocation() {
        if (clock != null) {
            clock.SetLocation(0, 0);
        }
        if (calendar != null) {
            calendar.SetLocation(this.getWidth() - calendar.GetW(), 0);
        }
    }

    /**
     * Crea un nuevo JInternalFrame con el contenido de un JPanel y lo agrega a
     * la pantalla principal con sus correspondientes botones para cerrar y
     * minimizar
     *
     */
    private void ShowWindow(JPanel content, String Title, int w, int h, int Type) {
        JInternalFrame Ventana;
        if (Type == 0) {
            Ventana = new ClosableInternalFrame(true, true, false);
        } else {
            Ventana = new ClosableInternalFrame(true, true, true);
        }
        Ventana.setTitle(Title);
        Ventana.setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
        Ventana.setBounds(100, 100, w, h);
        Ventana.add(content);
        content.setVisible(true);
        Ventana.setVisible(true);
        PanelPrincipal.add(Ventana);
        try {
            Ventana.setSelected(true);
        } catch (PropertyVetoException ex) {
            Log.SaveLog(ex.toString());
        }
    }

    public static void Window(JPanel content, String Title, int w, int h, int Type) {
        JInternalFrame Ventana;
        if (Type == 0) {
            Ventana = new ClosableInternalFrame(true, true, false);
        } else {
            Ventana = new ClosableInternalFrame(true, true, true);
        }
        Ventana.setTitle(Title);
        Ventana.setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
        Ventana.setBounds(100, 100, w, h);
        Ventana.add(content);
        content.setVisible(true);
        Ventana.setVisible(true);
        PanelPrincipal.add(Ventana);
        try {
            Ventana.setSelected(true);
        } catch (PropertyVetoException ex) {
            Log.SaveLog(ex.toString());
        }
    }
}
