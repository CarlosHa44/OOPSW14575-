
package ec.edu.espe.icecreamdeve.view;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import ec.edu.espe.icecreamdeve.utils.MDBManage;
import java.util.ArrayList;
import org.bson.Document;

/**
 *
 * @author JosuéG
 */
public class FrmShowInvoices extends javax.swing.JFrame {

    /**
     * Creates new form FrmShowInvoices
     */
    public FrmShowInvoices() {
        initComponents();
        showAllInvoices();
        setLocationRelativeTo(null);
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
        jPanel2 = new javax.swing.JPanel();
        pnlAccion5 = new javax.swing.JPanel();
        btnProducts5 = new javax.swing.JButton();
        btnClient3 = new javax.swing.JButton();
        btnInvoice3 = new javax.swing.JButton();
        btnSaleNote3 = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        jLabel30 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtInvoices = new javax.swing.JTextArea();
        btnSalir3 = new javax.swing.JButton();
        btnMenu = new javax.swing.JButton();

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        pnlAccion5.setBackground(new java.awt.Color(51, 51, 51));
        pnlAccion5.setForeground(new java.awt.Color(102, 102, 102));

        btnProducts5.setBackground(new java.awt.Color(51, 51, 51));
        btnProducts5.setFont(new java.awt.Font("Segoe UI Black", 1, 18)); // NOI18N
        btnProducts5.setForeground(new java.awt.Color(255, 255, 255));
        btnProducts5.setText("Productos");
        btnProducts5.setBorder(null);
        btnProducts5.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnProducts5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProducts5ActionPerformed(evt);
            }
        });

        btnClient3.setBackground(new java.awt.Color(51, 51, 51));
        btnClient3.setFont(new java.awt.Font("Segoe UI Black", 1, 18)); // NOI18N
        btnClient3.setForeground(new java.awt.Color(255, 255, 255));
        btnClient3.setText("Clientes");
        btnClient3.setBorder(null);
        btnClient3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClient3ActionPerformed(evt);
            }
        });

        btnInvoice3.setBackground(new java.awt.Color(51, 51, 51));
        btnInvoice3.setFont(new java.awt.Font("Segoe UI Black", 1, 18)); // NOI18N
        btnInvoice3.setForeground(new java.awt.Color(255, 255, 255));
        btnInvoice3.setText("Facturas");
        btnInvoice3.setBorder(null);
        btnInvoice3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInvoice3ActionPerformed(evt);
            }
        });

        btnSaleNote3.setBackground(new java.awt.Color(51, 51, 51));
        btnSaleNote3.setFont(new java.awt.Font("Segoe UI Black", 1, 18)); // NOI18N
        btnSaleNote3.setForeground(new java.awt.Color(255, 255, 255));
        btnSaleNote3.setText("Notas de Venta");
        btnSaleNote3.setBorder(null);

        javax.swing.GroupLayout pnlAccion5Layout = new javax.swing.GroupLayout(pnlAccion5);
        pnlAccion5.setLayout(pnlAccion5Layout);
        pnlAccion5Layout.setHorizontalGroup(
            pnlAccion5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlAccion5Layout.createSequentialGroup()
                .addContainerGap(41, Short.MAX_VALUE)
                .addGroup(pnlAccion5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnSaleNote3, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnInvoice3, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlAccion5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(btnProducts5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnClient3, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(36, 36, 36))
        );
        pnlAccion5Layout.setVerticalGroup(
            pnlAccion5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlAccion5Layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addComponent(btnProducts5, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnClient3, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnInvoice3, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSaleNote3, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(152, Short.MAX_VALUE))
        );

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setPreferredSize(new java.awt.Dimension(525, 233));

        jLabel30.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel30.setText("Lista de Productos");

        txtInvoices.setColumns(20);
        txtInvoices.setRows(5);
        jScrollPane2.setViewportView(txtInvoices);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel30)
                .addContainerGap(186, Short.MAX_VALUE))
            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(jLabel30)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 353, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(118, 118, 118)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlAccion5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(133, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(45, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(pnlAccion5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, 445, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18))
        );

        btnSalir3.setBackground(new java.awt.Color(204, 204, 204));
        btnSalir3.setFont(new java.awt.Font("Californian FB", 0, 12)); // NOI18N
        btnSalir3.setText("SALIR");
        btnSalir3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalir3ActionPerformed(evt);
            }
        });

        btnMenu.setBackground(new java.awt.Color(204, 204, 204));
        btnMenu.setFont(new java.awt.Font("Californian FB", 0, 14)); // NOI18N
        btnMenu.setText("Return Menu");
        btnMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMenuActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnSalir3, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnMenu)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSalir3)
                    .addComponent(btnMenu))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
  private void showAllInvoices() {
    
    MongoCollection<Document> collection = MDBManage.getFromCollection("Invoices", Document.class);

   
    FindIterable<Document> documents = collection.find();

    
    txtInvoices.setText("");

   
    for (Document document : documents) {
        
        
       
        StringBuilder invoiceInfo = new StringBuilder();
        invoiceInfo.append("                                  Número de Factura:  ").append(document.getInteger("_id")).append("\n");
        invoiceInfo.append("Detalles de la Factura:       ").append("\n");
        
        invoiceInfo.append("Fecha: ").append(document.getDate("dateI")).append("\n");
        
        ArrayList<Document> boxes = (ArrayList<Document>) document.get("boxes");
        invoiceInfo.append("Productos comprados:\n");
        invoiceInfo.append("\tProducto" + "\tCantidad" + "\tValorUnitario").append("\n");
        for (Document box : boxes) {
        double cost1 = box.getDouble("cost");
        String cost = String.format("%.2f", cost1);
        invoiceInfo.append("\t").append(box.getString("name"))
               .append("\t").append(box.getInteger("amount"))
               .append("\t").append(cost).append("\n");
      }
        double value = document.getDouble("value");
        String formattedValue = String.format("%.2f", value);
        invoiceInfo.append("Valor Total:  ").append(formattedValue).append("\n");
        invoiceInfo.append("\n");

       
        txtInvoices.append(invoiceInfo.toString());
    }
}
    
    private void btnProducts5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProducts5ActionPerformed
        FrmProductsMenu productMenu=new FrmProductsMenu();
        this.setVisible(false);
        productMenu.setVisible(true);
    }//GEN-LAST:event_btnProducts5ActionPerformed

    private void btnClient3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClient3ActionPerformed
        FrmProductsMenu productMenu=new FrmProductsMenu();
        this.setVisible(false);
        productMenu.setVisible(true);
    }//GEN-LAST:event_btnClient3ActionPerformed

    private void btnInvoice3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInvoice3ActionPerformed
        FrmMenuInvoices invoiceMenu=new FrmMenuInvoices();
        this.setVisible(false);
        invoiceMenu.setVisible(true);
    }//GEN-LAST:event_btnInvoice3ActionPerformed

    private void btnSalir3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalir3ActionPerformed
        this.setVisible(false);
        this.dispose();
        System.exit(0);
        // TODO add your handling code here:
    }//GEN-LAST:event_btnSalir3ActionPerformed

    private void btnMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMenuActionPerformed
        FrmProductsMenu  frmProductmenu = new FrmProductsMenu();
        this.setVisible(false);
        frmProductmenu.setVisible(true);

    }//GEN-LAST:event_btnMenuActionPerformed

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
            java.util.logging.Logger.getLogger(FrmShowInvoices.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmShowInvoices.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmShowInvoices.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmShowInvoices.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmShowInvoices().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnClient3;
    private javax.swing.JButton btnInvoice3;
    private javax.swing.JButton btnMenu;
    private javax.swing.JButton btnProducts5;
    private javax.swing.JButton btnSaleNote3;
    private javax.swing.JButton btnSalir3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPanel pnlAccion5;
    private javax.swing.JTextArea txtInvoices;
    // End of variables declaration//GEN-END:variables
}
