/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicacion;

import clases.RecipeDao;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author jose
 */
public class EDITAR_TORTAS extends javax.swing.JFrame {
    class IngredientesTableModel extends javax.swing.table.DefaultTableModel {
    IngredientesTableModel() { super(new Object[]{"Ingrediente","Cantidad","Unidad","Nota"}, 0); }
    @Override public boolean isCellEditable(int row, int col) { return true; }        // todas editables
    @Override public Class<?> getColumnClass(int col) { return (col == 1) ? Double.class : String.class; }
}
    private IngredientesTableModel ingredientesModel = new IngredientesTableModel();
    private final RecipeDao recipeDao = new RecipeDao();
    private int recipeId = -1;
    
    public EDITAR_TORTAS() {
        initComponents();
        setLocationRelativeTo(null);
        configurarTablaIngredientes();
    }

   public EDITAR_TORTAS(int recipeId) {
        this();
        this.recipeId = recipeId;
        cargarReceta();
    }
   private void configurarTablaIngredientes() {
    jTable1.setModel(ingredientesModel);
    jTable1.setAutoCreateRowSorter(true);
    jTable1.setRowHeight(22);
   }
    private void cargarReceta() {
        try {
        RecipeDao.Receta r = recipeDao.obtenerRecetaPorId(recipeId);

        // Cabecera
        jTextField1.setText(r.nombre);
        jTextField2.setText(r.notas == null ? "" : r.notas);

        // Detalle en el model editable
        ingredientesModel.setRowCount(0); // limpia
        for (RecipeDao.Detalle d : r.items) {
            ingredientesModel.addRow(new Object[]{ d.ingrediente, d.cantidad, d.unidad, d.nota });
        }
    } catch (Exception ex) {
        javax.swing.JOptionPane.showMessageDialog(this,
            "Error cargando receta: " + ex.getMessage(),
            "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
    }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        jLabel1.setText("EDITAR POSTRE");

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
        jButton4.setText("CANCELAR");
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
                .addGap(171, 171, 171))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addGap(28, 28, 28)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 638, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(122, 122, 122))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jTextField2)
                    .addComponent(jScrollPane1))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLabel1)
                .addGap(17, 17, 17)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
                 int r = javax.swing.JOptionPane.showConfirmDialog(
        this, // si estás en un JFrame; si es un JPanel, igual sirve
        "¿Estás seguro?",
        "Confirmación",
        javax.swing.JOptionPane.YES_NO_OPTION,
        javax.swing.JOptionPane.QUESTION_MESSAGE
    );

    if (r == javax.swing.JOptionPane.YES_OPTION) {
        new INICIO_TORTAS().setVisible(true);
        dispose();
    }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        if (jTable1.isEditing()) {
        jTable1.getCellEditor().stopCellEditing();
    }

    try {
        // 1) Lee cabecera
        String nombre = jTextField1.getText().trim();
        String notas  = jTextField2.getText();   // si luego usas JTextArea, queda igual
        double base   = 1.0; // si usas servings_base, aquí pon el valor real (spinner/campo)

        if (nombre.isEmpty()) {
            javax.swing.JOptionPane.showMessageDialog(this, "El nombre no puede estar vacío.",
                    "Validación", javax.swing.JOptionPane.WARNING_MESSAGE);
            return;
        }

        // 2) Lee detalle desde la tabla editable (ingredientesModel)
        java.util.List<clases.RecipeDao.Detalle> items = new java.util.ArrayList<>();
        for (int i = 0; i < ingredientesModel.getRowCount(); i++) {
            String ing   = String.valueOf(ingredientesModel.getValueAt(i, 0)).trim();
            String qtyS  = String.valueOf(ingredientesModel.getValueAt(i, 1)).trim();
            String unit  = String.valueOf(ingredientesModel.getValueAt(i, 2)).trim();
            String note  = String.valueOf(ingredientesModel.getValueAt(i, 3));

            if (ing.isEmpty() && unit.isEmpty() && qtyS.isEmpty()) continue; // fila vacía → ignora

            double qty;
            try { qty = Double.parseDouble(qtyS); }
            catch (NumberFormatException nfe) {
                javax.swing.JOptionPane.showMessageDialog(this,
                        "Cantidad inválida en la fila " + (i + 1) + ".", "Validación",
                        javax.swing.JOptionPane.WARNING_MESSAGE);
                return;
            }
            if (ing.isEmpty()) {
                javax.swing.JOptionPane.showMessageDialog(this,
                        "Ingrediente vacío en la fila " + (i + 1) + ".", "Validación",
                        javax.swing.JOptionPane.WARNING_MESSAGE);
                return;
            }
            if (unit.isEmpty()) {
                javax.swing.JOptionPane.showMessageDialog(this,
                        "Unidad vacía en la fila " + (i + 1) + ".", "Validación",
                        javax.swing.JOptionPane.WARNING_MESSAGE);
                return;
            }

            clases.RecipeDao.Detalle d = new clases.RecipeDao.Detalle();
            d.ingrediente = ing;
            d.cantidad    = qty;
            d.unidad      = unit;
            d.nota        = note == null ? "" : note;
            items.add(d);
        }

        // 3) Persiste
        recipeDao.actualizarReceta(recipeId, nombre, base, notas, items);

        javax.swing.JOptionPane.showMessageDialog(this, "Receta actualizada correctamente.",
                "Éxito", javax.swing.JOptionPane.INFORMATION_MESSAGE);

        // 4) Volver a la lista (opcional)
        new INICIO_TORTAS().setVisible(true);
        dispose();

    } catch (Exception ex) {
        javax.swing.JOptionPane.showMessageDialog(this,
                "Error al guardar: " + ex.getMessage(), "Error",
                javax.swing.JOptionPane.ERROR_MESSAGE);
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
            java.util.logging.Logger.getLogger(EDITAR_TORTAS.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(EDITAR_TORTAS.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(EDITAR_TORTAS.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(EDITAR_TORTAS.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new EDITAR_TORTAS().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    // End of variables declaration//GEN-END:variables
}
