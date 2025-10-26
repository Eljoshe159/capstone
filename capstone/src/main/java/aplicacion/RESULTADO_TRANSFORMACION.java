
package aplicacion;

import clases.RecipeDao;
import javax.swing.table.DefaultTableModel;
import java.io.FileOutputStream;
import java.util.List;

// OpenPDF
import com.lowagie.text.*;
import com.lowagie.text.pdf.*;

public class RESULTADO_TRANSFORMACION extends javax.swing.JFrame {
    private final String nombreReceta;
    private final double factor;
    private final List<RecipeDao.Detalle> items;
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(RESULTADO_TRANSFORMACION.class.getName());


    public RESULTADO_TRANSFORMACION(String nombreReceta, double factor, List<RecipeDao.Detalle> items) {
        initComponents();
        setLocationRelativeTo(null);
        this.nombreReceta = nombreReceta;
        this.factor = factor;
        this.items = items;
        cargarTabla();
        jTextField1.setText(nombreReceta + "  (x" + factor + ")");
        jTextField1.setEditable(false);
    }
    
    private void cargarTabla() {
        DefaultTableModel m = (DefaultTableModel) jTable1.getModel();
        m.setRowCount(0);
        for (RecipeDao.Detalle d : items) {
            m.addRow(new Object[]{ d.ingrediente, d.cantidad, d.unidad, d.nota });
        }
   
        jTable1.setDefaultEditor(Object.class, null);
        jTable1.setRowHeight(22);
    }


    private void descargarPDF() {
        javax.swing.JFileChooser fc = new javax.swing.JFileChooser();
        fc.setSelectedFile(new java.io.File(nombreReceta.replaceAll("[^\\w\\s-]", "") + "_x" + factor + ".pdf"));
        int r = fc.showSaveDialog(this);
        if (r != javax.swing.JFileChooser.APPROVE_OPTION) return;

        try (FileOutputStream fos = new FileOutputStream(fc.getSelectedFile())) {
            Document doc = new Document(PageSize.A4, 36, 36, 36, 36);
            PdfWriter.getInstance(doc, fos);
            doc.open();

            Font h1 = new Font(Font.HELVETICA, 16, Font.BOLD);
            Font normal = new Font(Font.HELVETICA, 11, Font.NORMAL);

            Paragraph title = new Paragraph("Receta transformada: " + nombreReceta + " (x" + factor + ")", h1);
            title.setSpacingAfter(12f);
            doc.add(title);

            PdfPTable table = new PdfPTable(4);
            table.setWidths(new float[]{40f, 20f, 20f, 20f});
            table.setWidthPercentage(100);

            addHeader(table, "Ingrediente");
            addHeader(table, "Cantidad");
            addHeader(table, "Unidad");
            addHeader(table, "Nota");

            for (RecipeDao.Detalle d : items) {
                table.addCell(new Phrase(d.ingrediente, normal));
                table.addCell(new Phrase(String.valueOf(d.cantidad), normal));
                table.addCell(new Phrase(d.unidad == null ? "" : d.unidad, normal));
                table.addCell(new Phrase(d.nota == null ? "" : d.nota, normal));
            }

            doc.add(table);
            doc.close();

            javax.swing.JOptionPane.showMessageDialog(this, "PDF generado correctamente.",
                    "PDF", javax.swing.JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            javax.swing.JOptionPane.showMessageDialog(this, "Error generando PDF: " + e.getMessage(),
                    "PDF", javax.swing.JOptionPane.ERROR_MESSAGE);
        }
    }


    private static void addHeader(PdfPTable t, String txt) {
        Font f = new Font(Font.HELVETICA, 11, Font.BOLD);
        PdfPCell c = new PdfPCell(new Phrase(txt, f));
        c.setGrayFill(0.9f);
        c.setPadding(6f);
        t.addCell(c);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

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

        jButton1.setText("pdf");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/medios-de-comunicacion-social (1).png"))); // NOI18N
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(342, 342, 342)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 477, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 962, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(414, 414, 414)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(52, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jButton8))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(40, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

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
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
