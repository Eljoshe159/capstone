
package aplicacion;

import javax.swing.*;
import javax.swing.table.*;
import java.util.Vector;
public class NUEVO_PANES extends javax.swing.JFrame {

    public NUEVO_PANES() {
        initComponents();
        setLocationRelativeTo(null);
        configurarTablaDetalle();
    }

    private void configurarTablaDetalle() {

        DefaultTableModel model = new DefaultTableModel(
            new Object[]{"Ingrediente","Cantidad","Unidad","Nota"}, 0
        ) {
            @Override public boolean isCellEditable(int row, int column) { return true; }
            @Override public Class<?> getColumnClass(int columnIndex) {
                switch (columnIndex) {
                    case 1: return Double.class; // Cantidad como número
                    default: return String.class;
                }
            }
        };

        jTable1.setModel(model);
        jTable1.setFillsViewportHeight(true);
        jTable1.setRowHeight(24);
        TableColumnModel cm = jTable1.getColumnModel();
        cm.getColumn(0).setPreferredWidth(240);
        cm.getColumn(1).setPreferredWidth(90); 
        cm.getColumn(2).setPreferredWidth(120);
        cm.getColumn(3).setPreferredWidth(260);
        
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        nombre_receta = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        menos = new javax.swing.JButton();
        mas = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        jLabel1.setText("NUEVA RECETA DE PAN");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jButton4.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton4.setText("SALIR");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton5.setText("GUARDAR");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jLabel2.setText("NOMBRE DE LA RECETA");

        jLabel3.setText("descripción");

        menos.setText("-");
        menos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menosActionPerformed(evt);
            }
        });

        mas.setText("+");
        mas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                masActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(137, 137, 137)
                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(183, 183, 183))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(227, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addGap(28, 28, 28)
                .addComponent(nombre_receta, javax.swing.GroupLayout.PREFERRED_SIZE, 638, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(122, 122, 122))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTextField2)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(343, 343, 343)
                        .addComponent(menos, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(235, 235, 235)
                        .addComponent(mas, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1)
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLabel1)
                .addGap(17, 17, 17)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nombre_receta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 288, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(menos)
                    .addComponent(mas))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 9, Short.MAX_VALUE)
                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(9, 9, 9)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(28, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
         int r = javax.swing.JOptionPane.showConfirmDialog(
        this, 
        "¿Estás seguro?",
        "Confirmación",
        javax.swing.JOptionPane.YES_NO_OPTION,
        javax.swing.JOptionPane.QUESTION_MESSAGE
    );

    if (r == javax.swing.JOptionPane.YES_OPTION) {
        new INICIO_PANES().setVisible(true);
        dispose();
    }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void masActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_masActionPerformed
        DefaultTableModel m = (DefaultTableModel) jTable1.getModel();
    m.addRow(new Object[]{"", 0.0, "", ""});
    int last = m.getRowCount() - 1;
    if (last >= 0) {
        jTable1.changeSelection(last, 0, false, false);
        jTable1.editCellAt(last, 0);
    }
    }//GEN-LAST:event_masActionPerformed

    private void menosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menosActionPerformed
           int row = jTable1.getSelectedRow();
    DefaultTableModel m = (DefaultTableModel) jTable1.getModel();
    if (row >= 0) {
        m.removeRow(row);
    } else if (m.getRowCount() > 0) {
        m.removeRow(m.getRowCount() - 1); 
    }
    }//GEN-LAST:event_menosActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
         try {
        String nombre = nombre_receta.getText().trim();
        String desc   = jTextField2.getText().trim();
        if (nombre.isEmpty()) {
            javax.swing.JOptionPane.showMessageDialog(this, "Ingresa el nombre de la receta.");
            return;
        }

        javax.swing.table.DefaultTableModel m = (javax.swing.table.DefaultTableModel) jTable1.getModel();
        java.util.List<clases.RecipeDao.Detalle> items = new java.util.ArrayList<>();

        for (int i = 0; i < m.getRowCount(); i++) {
            Object ing  = m.getValueAt(i, 0);
            Object cant = m.getValueAt(i, 1);
            Object uni  = m.getValueAt(i, 2);
            Object nota = m.getValueAt(i, 3);

            if (ing == null || ing.toString().trim().isEmpty()) continue;

            clases.RecipeDao.Detalle d = new clases.RecipeDao.Detalle();
            d.ingrediente = ing.toString().trim();
            d.cantidad    = (cant == null || cant.toString().isEmpty()) ? 0.0 : Double.parseDouble(cant.toString());
            d.unidad      = (uni == null) ? "" : uni.toString().trim();
            d.nota        = (nota == null) ? "" : nota.toString().trim();
            items.add(d);
        }

        if (items.isEmpty()) {
            javax.swing.JOptionPane.showMessageDialog(this, "Agrega al menos un ingrediente.");
            return;
        }

        clases.RecipeDao dao = new clases.RecipeDao();
        int id = dao.insertarRecetaConDetalle("PANES", nombre, desc, 1.0, items);

        javax.swing.JOptionPane.showMessageDialog(this, "Receta guardada (ID " + id + ").");

    } catch (Exception ex) {
        javax.swing.JOptionPane.showMessageDialog(this, "Error al guardar: " + ex.getMessage(),
                "BD", javax.swing.JOptionPane.ERROR_MESSAGE);
    }
    }//GEN-LAST:event_jButton5ActionPerformed

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
            java.util.logging.Logger.getLogger(NUEVO_PANES.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(NUEVO_PANES.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(NUEVO_PANES.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NUEVO_PANES.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new NUEVO_PANES().setVisible(true);
            }
        });
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JButton mas;
    private javax.swing.JButton menos;
    private javax.swing.JTextField nombre_receta;
    // End of variables declaration//GEN-END:variables
}
