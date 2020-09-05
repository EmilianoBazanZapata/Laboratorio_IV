/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vistas;

import Clases.Alumno;
import Clases.Curso;
import Clases.Nivel;
import Clases.Sexo;
import Clases.Turno;
import GestorBD.GestorBD;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

/**
 *
 * @author Emiliano
 */
public class AgregarAlumno extends javax.swing.JFrame {

    /**
     * Creates new form AgregarAlumno
     */
    public AgregarAlumno() {
        initComponents();
        CargarLista();
        CargarComboNiveles();
        CargarComboSexo();
        CargarListaAlumnos();
        txtCurso.setEnabled(false);
        txtCupo.setEnabled(false);
        DesactivarBotonesAlumno();
        btnActualizar.setEnabled(false);
    }

    public void DesactivarBotonesAlumno() {
        txtLegajo.setEnabled(false);
        txtNombre.setEnabled(false);
        btnAgregarAlumno.setEnabled(false);
        cboNivel.setEnabled(false);
        btnElegirCurso.setEnabled(true);
    }

    public void ActivarBotonesAlumno() {
        txtLegajo.setEnabled(true);
        txtNombre.setEnabled(true);
        btnAgregarAlumno.setEnabled(true);
        cboNivel.setEnabled(true);
        btnElegirCurso.setEnabled(false);
    }

    public void ActivarBotonesParaEditarAlumno() {
        txtLegajo.setEnabled(true);
        txtNombre.setEnabled(true);
        cboNivel.setEnabled(true);
        btnElegirCurso.setEnabled(true);
    }

    public void LimpiarTexts() {
        txtLegajo.setText("");
        txtNombre.setText("");
        txtCupo.setText("");
        txtCurso.setText("");
    }

    public void CargarComboNiveles() {
        GestorBD gestor = new GestorBD();

        ArrayList<Nivel> lista = gestor.ObtenerNiveles();

        DefaultComboBoxModel model = new DefaultComboBoxModel();
        for (Nivel t : lista) {
            model.addElement(t);
        }
        cboNivel.setModel(model);
    }
    public void CargarComboSexo() {
        GestorBD gestor = new GestorBD();

        ArrayList<Sexo> lista = gestor.ObtenerSexos();

        DefaultComboBoxModel model = new DefaultComboBoxModel();
        for (Sexo t : lista) {
            model.addElement(t);
        }
        cboSexo.setModel(model);
    }

    private void CargarLista() {
        GestorBD gestor = new GestorBD();
        ArrayList<Curso> venta = gestor.ObtenerCursos();
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.setColumnIdentifiers(new String[]{"Id", "Nombre", "Cupo restante", "Turno"});
        for (Curso v : venta) {
            modelo.addRow(new Object[]{v.getIdcurso(), v.getNombre_curso(), v.getCapacidad(), v.getTurno().getTurno()});
        }
        tblCursos.setModel(modelo);
        //esto es necesario para ocultar el campo id y no mostrarlo en la tabla
        TableColumn columna = tblCursos.getColumnModel().getColumn(0);
        //coloco un tamaño maximo y minimo de 0 
        columna.setMaxWidth(0);
        columna.setMinWidth(0);
        columna.setPreferredWidth(0);
        //desactivo la propiedad para poder aumentar el tamaño de la celda con el mouse
        TableColumn columna1 = tblCursos.getColumnModel().getColumn(0);
        columna1.setResizable(false);
    }

    private void CargarListaAlumnos() {
        GestorBD gestor = new GestorBD();
        ArrayList<Alumno> venta = gestor.ObtenerAlumnos();
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.setColumnIdentifiers(new String[]{"Legajo", "Nombre", "Nivel", "Curso" ,"Sexo","idcurso"});
        for (Alumno v : venta) {
            modelo.addRow(new Object[]{v.getLegajo(), v.getApellido(), v.getNivel().getNombreNivel(), v.getCurso().getNombre_curso(),v.getSexo().getSexo(),v.getCurso().getIdcurso()});
        }
        tblAlumnos.setModel(modelo);
        //esto es necesario para ocultar el campo id y no mostrarlo en la tabla
        TableColumn columna = tblAlumnos.getColumnModel().getColumn(5);
        //coloco un tamaño maximo y minimo de 0 
        columna.setMaxWidth(0);
        columna.setMinWidth(0);
        columna.setPreferredWidth(0);
        //desactivo la propiedad para poder aumentar el tamaño de la celda con el mouse
        TableColumn columna1 = tblAlumnos.getColumnModel().getColumn(5);
        columna1.setResizable(false);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tblCursos = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        txtCurso = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtCupo = new javax.swing.JTextField();
        btnElegirCurso = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblAlumnos = new javax.swing.JTable();
        txtLegajo = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        cboNivel = new javax.swing.JComboBox<>();
        btnAgregarAlumno = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();
        btnActualizar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        cboSexo = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tblCursos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(tblCursos);

        jLabel1.setText("Curso");

        jLabel2.setText("Cupo Restane ");

        btnElegirCurso.setText("Elegir Curso");
        btnElegirCurso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnElegirCursoActionPerformed(evt);
            }
        });

        tblAlumnos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane2.setViewportView(tblAlumnos);

        jLabel3.setText("Legajo ");

        jLabel4.setText("Nombre");

        btnAgregarAlumno.setText("Agregar Alumno");
        btnAgregarAlumno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarAlumnoActionPerformed(evt);
            }
        });

        btnEditar.setText("Editar");
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });

        btnActualizar.setText("Actualizar");
        btnActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarActionPerformed(evt);
            }
        });

        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                    .addComponent(jLabel1)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(txtCurso, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                    .addComponent(jLabel2)
                                    .addGap(18, 18, 18)
                                    .addComponent(txtCupo))
                                .addComponent(btnElegirCurso, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel3)
                                    .addGap(18, 18, 18)
                                    .addComponent(txtLegajo, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(cboNivel, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel4)
                                    .addGap(18, 18, 18)
                                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(cboSexo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGap(77, 77, 77))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnAgregarAlumno)
                        .addGap(18, 18, 18)
                        .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 306, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 306, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnActualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(txtCurso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txtCupo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(60, 60, 60)
                        .addComponent(btnElegirCurso)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtLegajo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))
                        .addGap(18, 18, 18)
                        .addComponent(cboNivel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(cboSexo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 76, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAgregarAlumno)
                    .addComponent(btnEliminar)
                    .addComponent(btnActualizar)
                    .addComponent(btnEditar))
                .addGap(12, 12, 12))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnElegirCursoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnElegirCursoActionPerformed
        //paso el valor de la fila seleccionada a los txts 
        try {
            txtCurso.setText(tblCursos.getValueAt(tblCursos.getSelectedRow(), 1).toString());
            txtCupo.setText(tblCursos.getValueAt(tblCursos.getSelectedRow(), 2).toString());
            //activo el boton actualizar
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "ddebe seleccionar un curso");
        }
        ActivarBotonesAlumno();
    }//GEN-LAST:event_btnElegirCursoActionPerformed

    private void btnAgregarAlumnoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarAlumnoActionPerformed

        try {

            String Cupo = txtCupo.getText();

            int i = Integer.parseInt(Cupo);

            if (i == 0) {
                JOptionPane.showMessageDialog(this, "No hay mas cupo");
            } else {
                //paso por variables el valor de cata textbox
                String nombre = txtNombre.getText();
                int legajo = Integer.parseInt(txtLegajo.getText());
                Nivel niv = (Nivel) cboNivel.getSelectedItem();
                 Sexo se = (Sexo) cboSexo.getSelectedItem();
                int curso = Integer.parseInt((tblCursos.getValueAt(tblCursos.getSelectedRow(), 0).toString()));

                Curso cu = new Curso();
                cu.setIdcurso(curso);
                //instancias un nuevo producto
                //el cero lo colocas por que es un campo identity 
                //y asi no generas algun error
                Alumno c = new Alumno(legajo, nombre, cu, niv,se);

                //instancias el gestor y llamas el metodo para realizar el insert  
                GestorBD gestor = new GestorBD();

                boolean consulta = gestor.AgregarAlumno(c);
                if (consulta) {
                    JOptionPane.showMessageDialog(this, "El Alumno se registro exitosamente");
                } else {
                    JOptionPane.showMessageDialog(this, "El Alumno no se registro exitosamente");
                }
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "debe ingresar datos validos");
        }
        CargarLista();
        CargarListaAlumnos();
        DesactivarBotonesAlumno();
        LimpiarTexts();
    }//GEN-LAST:event_btnAgregarAlumnoActionPerformed

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        //paso el valor de la fila seleccionada a los txts 
        try {
            txtLegajo.setText(tblAlumnos.getValueAt(tblAlumnos.getSelectedRow(), 0).toString());
            txtNombre.setText(tblAlumnos.getValueAt(tblAlumnos.getSelectedRow(), 1).toString());
            txtCurso.setText(tblAlumnos.getValueAt(tblAlumnos.getSelectedRow(), 2).toString());
            ActivarBotonesParaEditarAlumno();
            btnActualizar.setEnabled(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "debe seleccionar un elemento de la lista para editar");
        }
        
    }//GEN-LAST:event_btnEditarActionPerformed

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed

        try {
              //paso por variables el valor de cata textbox
                String nombre = txtNombre.getText();
                int legajo = Integer.parseInt(txtLegajo.getText());
                Nivel niv = (Nivel) cboNivel.getSelectedItem();
                Sexo se = (Sexo) cboSexo.getSelectedItem();
                int curso = Integer.parseInt((tblAlumnos.getValueAt(tblAlumnos.getSelectedRow(), 4).toString()));

                Curso cu = new Curso();
                cu.setIdcurso(curso);

                //instancias un nuevo producto
                //el cero lo colocas por que es un campo identity 
                //y asi no generas algun error
                Alumno c = new Alumno(legajo, nombre, cu, niv,se);

                //instancias el gestor y llamas el metodo para realizar el insert  
                GestorBD gestor = new GestorBD();

                boolean consulta = gestor.ActualizarAlumno(c);
                if (consulta) {
                    JOptionPane.showMessageDialog(this, "El curso se registro exitosamente");
                } else {
                    JOptionPane.showMessageDialog(this, "El curso no se registro exitosamente");
                }
                btnActualizar.setEnabled(false);
        } catch (Exception e) {
        }
              

        CargarLista();
        CargarListaAlumnos();
        DesactivarBotonesAlumno();
        LimpiarTexts();
    }//GEN-LAST:event_btnActualizarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        //paso el valor de la fila seleccionada a los txts 
        try {
            int legajo =Integer.parseInt((tblAlumnos.getValueAt(tblAlumnos.getSelectedRow(), 0).toString()));
            int id =Integer.parseInt((tblAlumnos.getValueAt(tblAlumnos.getSelectedRow(), 5).toString()));
             GestorBD gestor = new GestorBD();

                boolean consulta = gestor.EliminarAlumno(legajo,id);
                if (consulta) {
                    JOptionPane.showMessageDialog(this, "El alumno se elimino correctamente");
                } else {
                    JOptionPane.showMessageDialog(this, "El alumno NO se elimino correctamente");
                }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "debe seleccionar un elemento de la lista para eliminar");
        }
        CargarLista();
        CargarListaAlumnos();
        DesactivarBotonesAlumno();
    }//GEN-LAST:event_btnEliminarActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(AgregarAlumno.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AgregarAlumno.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AgregarAlumno.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AgregarAlumno.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AgregarAlumno().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnAgregarAlumno;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnElegirCurso;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JComboBox<String> cboNivel;
    private javax.swing.JComboBox<String> cboSexo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tblAlumnos;
    private javax.swing.JTable tblCursos;
    private javax.swing.JTextField txtCupo;
    private javax.swing.JTextField txtCurso;
    private javax.swing.JTextField txtLegajo;
    private javax.swing.JTextField txtNombre;
    // End of variables declaration//GEN-END:variables
}
