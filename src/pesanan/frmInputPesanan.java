/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pesanan;
import persediaan.koneksi;
import java.awt.event.KeyEvent;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author User
 */
public class frmInputPesanan extends javax.swing.JFrame {
    DefaultTableModel tabMode;
    
    public frmInputPesanan() {
        initComponents();
        
        String [] row = {"Kode", "Nama", "Harga", "Jumlah", "Total"};
        tabMode = new DefaultTableModel(null, row);
        
        ViewDataPesan.setModel(tabMode);
        kosong();
        showdate();
    }
    
    public void kosong() {
        clsPesanan psn = new clsPesanan();
        int baris = tabMode.getRowCount();
        for(int i = 0; 1 < baris; i++) {
            tabMode.removeRow(0);
        }
        txtNoPesan.setEnabled(false);
        txtKodePel.setText("");
        txtNamaPel.setText("");
        txtAlmPel.setText("");
        txtTelpPel.setText("");
        txtKodeBrg.setText("");
        txtNamaBrg.setText("");
        txtSat.setText("");
        txtHarga.setText("");
        txtJml.setText("");
        btnTambah.setEnabled(true);
        btnSimpan.setEnabled(true);
        psn.autopesan();
        txtNoPesan.setText(psn.getNoPesan());
    }
    
    public void showdate() {
        Date tglpesan = new Date();
        SimpleDateFormat sf = new SimpleDateFormat("dd/MM/yyyy");
        txtTglPesan.setText(sf.format(tglpesan));
    }
    
    public void cariPelanggan() {
        try {
            koneksi k = new koneksi();
            Connection cn = k.openKoneksi();
            String sql = "SELECT * FROM pelanggan WHERE kdplg ='" + txtKodePel.getText() + "'";
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            
            while(rs.next()) {
                txtNamaPel.setText(rs.getString("nmplg"));
                txtAlmPel.setText(rs.getString("almplg"));
                txtTelpPel.setText(rs.getString("tlpplg"));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "GAGAL");
        }
    }
    
    public void cariBarang() {
        try {
            koneksi k = new koneksi();
            Connection cn = k.openKoneksi();
            String sql = "SELECT * FROM barang WHERE kdbrg ='" + txtKodeBrg.getText() + "'";
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            
            while(rs.next()) {
                txtNamaBrg.setText(rs.getString("nmbrg"));
                txtSat.setText(rs.getString("sat"));
                txtHarga.setText(rs.getString("hrgbrg"));
            }
        } catch(SQLException e) {
            JOptionPane.showMessageDialog(null, "GAGAL");
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        txtNoPesan = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        txtTglPesan = new javax.swing.JTextField();
        txtKodePel = new javax.swing.JTextField();
        txtNamaPel = new javax.swing.JTextField();
        txtAlmPel = new javax.swing.JTextField();
        txtTelpPel = new javax.swing.JTextField();
        txtKodeBrg = new javax.swing.JTextField();
        txtNamaBrg = new javax.swing.JTextField();
        txtSat = new javax.swing.JTextField();
        txtHarga = new javax.swing.JTextField();
        txtJml = new javax.swing.JTextField();
        btnSimpan = new javax.swing.JButton();
        btnTambah = new javax.swing.JButton();
        btnBatal = new javax.swing.JButton();
        btnKeluar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        ViewDataPesan = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Input Pesanan");

        jLabel1.setFont(new java.awt.Font("Verdana", 1, 25)); // NOI18N
        jLabel1.setText("ENTRY DATA PESANAN");

        jLabel2.setText("No. Pesanan");

        jLabel3.setText("Tgl. Pesanan");

        jLabel4.setText("Kode Pelanggan");

        jLabel5.setText("Nama Pelanggan");

        jLabel6.setText("Alamat Pelanggan");

        jLabel7.setText("Telepon");

        jLabel8.setText("Kode Barang");

        jLabel9.setText("Nama Barang");

        jLabel10.setText("Satuan");

        jLabel11.setText("Harga");

        txtNoPesan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNoPesanActionPerformed(evt);
            }
        });

        jLabel12.setText("Jumlah");

        txtKodePel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtKodePelActionPerformed(evt);
            }
        });
        txtKodePel.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtKodePelKeyPressed(evt);
            }
        });

        txtKodeBrg.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtKodeBrgKeyPressed(evt);
            }
        });

        txtJml.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtJmlKeyPressed(evt);
            }
        });

        btnSimpan.setText("Simpan");
        btnSimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSimpanActionPerformed(evt);
            }
        });

        btnTambah.setText("Tambah");
        btnTambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTambahActionPerformed(evt);
            }
        });

        btnBatal.setText("Batal");
        btnBatal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBatalActionPerformed(evt);
            }
        });

        btnKeluar.setText("Keluar");
        btnKeluar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKeluarActionPerformed(evt);
            }
        });

        ViewDataPesan.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(ViewDataPesan);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(264, 264, 264))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 23, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btnBatal, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(94, 94, 94)
                        .addComponent(btnKeluar, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(260, 260, 260))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 788, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(txtAlmPel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 223, Short.MAX_VALUE)
                                    .addComponent(txtNamaPel, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtKodePel, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtTglPesan, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtNoPesan, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtTelpPel))
                                .addGap(30, 30, 30)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(94, 94, 94)
                                        .addComponent(jLabel12))
                                    .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addGap(41, 41, 41)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(btnSimpan)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(btnTambah))
                                    .addComponent(txtKodeBrg)
                                    .addComponent(txtNamaBrg)
                                    .addComponent(txtSat)
                                    .addComponent(txtHarga)
                                    .addComponent(txtJml, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(40, 40, 40))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(jLabel1)
                .addGap(57, 57, 57)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtNoPesan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addGap(21, 21, 21)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtTglPesan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addGap(21, 21, 21)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtKodePel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtNamaPel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtAlmPel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtKodeBrg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8))
                        .addGap(25, 25, 25)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtNamaBrg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(txtSat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(txtHarga, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12)
                            .addComponent(txtJml, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnSimpan)
                            .addComponent(btnTambah)))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtTelpPel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7))))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnBatal)
                    .addComponent(btnKeluar))
                .addContainerGap(87, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtNoPesanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNoPesanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNoPesanActionPerformed

    private void txtKodePelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtKodePelActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtKodePelActionPerformed

    private void btnKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKeluarActionPerformed
        setVisible(false);
    }//GEN-LAST:event_btnKeluarActionPerformed

    private void btnTambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTambahActionPerformed
        String kd, nm, hr, jml, tt, tp, cekkode;
        int hit = 0; int tb = 0;
        int baris = tabMode.getRowCount();
        
        kd=""+txtKodeBrg.getText();
        nm=txtNamaBrg.getText();
        hr=txtHarga.getText();
        jml=txtJml.getText();
        
        tt=""+(Integer.parseInt(txtHarga.getText())*Integer.parseInt(txtJml.getText()));
        for (int i = 0; i < baris; i++) {
            cekkode = "" + ViewDataPesan.getValueAt(i, 0);
            if (kd.equals(cekkode))
                hit++;
        }
        if (hit == 0) {
            String []data = {kd, nm, hr, jml, tt};
            tabMode.addRow(data);
            txtNamaBrg.setText("");
            txtSat.setText("");
            txtHarga.setText("");
            txtJml.setText("");
            btnSimpan.setEnabled(true);
            txtKodeBrg.requestFocus();
            btnTambah.setEnabled(false);
        } else {
            JOptionPane.showMessageDialog(null, "kode barang sudah ada !!", "PESANAN",
                    JOptionPane.INFORMATION_MESSAGE);
            txtKodeBrg.requestFocus();
        }
    }//GEN-LAST:event_btnTambahActionPerformed

    private void btnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSimpanActionPerformed
        String tampilan = "yyyy-MM-dd";
        clsPesanan psn = new clsPesanan();
        int baris = tabMode.getRowCount();
        
        for (int i = 0; i < baris; i++) {
            psn.setNoPesan(txtNoPesan.getText());
            psn.setKdBar("" + ViewDataPesan.getValueAt(i, 0));
            psn.setHr(Integer.parseInt("" + ViewDataPesan.getValueAt(i, 2)));
            psn.setJum(Integer.parseInt("" + ViewDataPesan.getValueAt(i, 3)));
            psn.simpanDetailPesan();
            psn.updateStok();
            //menambahkan tanggal
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            String tanggalString = txtTglPesan.getText();
            
        try {
            psn.setNoPesan(txtNoPesan.getText());
            Date tanggalUtil = dateFormat.parse(tanggalString);
            java.sql.Date tanggalSql = new java.sql.Date(tanggalUtil.getTime());
            psn.setTanggal(psn.tglpesan);
        } catch(ParseException e) {
            e.printStackTrace();
        }
        
        psn.setKdPel("" + txtKodePel.getText());
        psn.simpanPesan();
        kosong();
        }
        
    }//GEN-LAST:event_btnSimpanActionPerformed

    private void btnBatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBatalActionPerformed
        kosong();
    }//GEN-LAST:event_btnBatalActionPerformed

    private void txtKodeBrgKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtKodeBrgKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            cariBarang();
            txtJml.requestFocus();
        }
    }//GEN-LAST:event_txtKodeBrgKeyPressed

    private void txtKodePelKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtKodePelKeyPressed
        if(evt.getKeyCode() == KeyEvent.VK_ENTER) {
            cariPelanggan();
            txtKodeBrg.requestFocus();
        }
    }//GEN-LAST:event_txtKodePelKeyPressed

    private void txtJmlKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtJmlKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            btnTambah.setEnabled(true);
            btnSimpan.requestFocus();
        }
    }//GEN-LAST:event_txtJmlKeyPressed

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
            java.util.logging.Logger.getLogger(frmInputPesanan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmInputPesanan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmInputPesanan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmInputPesanan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmInputPesanan().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable ViewDataPesan;
    private javax.swing.JButton btnBatal;
    private javax.swing.JButton btnKeluar;
    private javax.swing.JButton btnSimpan;
    private javax.swing.JButton btnTambah;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField txtAlmPel;
    private javax.swing.JTextField txtHarga;
    private javax.swing.JTextField txtJml;
    private javax.swing.JTextField txtKodeBrg;
    private javax.swing.JTextField txtKodePel;
    private javax.swing.JTextField txtNamaBrg;
    private javax.swing.JTextField txtNamaPel;
    private javax.swing.JTextField txtNoPesan;
    private javax.swing.JTextField txtSat;
    private javax.swing.JTextField txtTelpPel;
    private javax.swing.JTextField txtTglPesan;
    // End of variables declaration//GEN-END:variables
}
