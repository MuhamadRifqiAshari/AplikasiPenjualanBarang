/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Aplikasipenjualan;
import java.awt.Desktop;
import java.net.URL;
import java.sql.*;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.awt.event.KeyEvent;
/**
 *
 * @author Rima Regiani
 */
public class formbeli extends javax.swing.JFrame {
    public long total;
    public long bayar;
    public long kembali;
    public Statement st;
    Connection cn = koneksi.getKoneksi();
            
    private DefaultTableModel model;
    /**
     * Creates new form formbeli
     */
    public formbeli() {
        initComponents();
        model = new DefaultTableModel();

        tabel.setModel(model);
        model.addColumn("ID");
        model.addColumn("kode barang");
        model.addColumn("nama barang");
        model.addColumn("harga satuan");
        model.addColumn("jumlah beli");
        model.addColumn("harga");
        loadData();
        nofaktur();
        tampilpilih();
        
    }
   public void FilterHuruf(KeyEvent a){
       if(Character.isDigit(a.getKeyChar())){
           a.consume();
           JOptionPane.showMessageDialog(null, "masukan huruf saja!", "peringatan", JOptionPane.WARNING_MESSAGE);
       }
   }
    public void FilterAngka(KeyEvent a){
       if(Character.isAlphabetic(a.getKeyChar())){
           a.consume();
           JOptionPane.showMessageDialog(null, "masukan angka saja!", "peringatan", JOptionPane.WARNING_MESSAGE);
       }
   }
   public final void loadData() {
        model.getDataVector().removeAllElements();
        model.fireTableDataChanged();
        try {
            Connection c = koneksi.getKoneksi();
            Statement s = c.createStatement();

            String sql = "SELECT * FROM tbl_tmp_beli";
            ResultSet r = s.executeQuery(sql);

            while (r.next()) {
                Object[] o = new Object[6];
                o[0] = r.getString("id_tmp");
                o[1] = r.getString("kd_barang");
                o[2] = r.getString("nama_barang");
                o[3] = r.getString("hsatuan");
                o[4] = r.getString("jumlah_beli");
                o[5] = r.getString("harga");
               
                model.addRow(o);
            }
            r.close();
            s.close();
        } catch (SQLException e) {
            System.out.println("Terjadi Error");
        }
   }
    private void tampilpilih() {
        try {
            Connection c = koneksi.getKoneksi();
            Statement s = c.createStatement();

            String sql = "SELECT nama_barang FROM tbl_barang WHERE jumlah_barang !='0'";
            ResultSet r = s.executeQuery(sql);

            while (r.next()) {
                pilihbarang.addItem(r.getString("nama_barang"));
            }

            r.last();
            int jumlahdata = r.getRow();
            r.first();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }
  private void nofaktur() {
        try {
            Connection c = koneksi.getKoneksi();
            Statement s = c.createStatement();

            String sql = "SELECT * FROM tbl_beli ORDER by nofaktur desc";
            ResultSet r = s.executeQuery(sql);

            if (r.next()) {
                String nofak = r.getString("nofaktur").substring(1);
                String AN = "" + (Integer.parseInt(nofak) + 1);
                String Nol = "";

                if (AN.length() == 1) {
                    Nol = "000";
                } else if (AN.length() == 2) {
                    Nol = "00";
                } else if (AN.length() == 3) {
                    Nol = "0";
                } else if (AN.length() == 4) {
                    Nol = "";
                }

                faktur.setText("F" + Nol + AN);
            } else {
                faktur.setText("F0001");
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
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

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        faktur = new javax.swing.JTextField();
        kdbarang = new javax.swing.JTextField();
        pilihbarang = new javax.swing.JComboBox<>();
        tjumlah = new javax.swing.JTextField();
        hsatuan = new javax.swing.JTextField();
        ttotal = new javax.swing.JTextField();
        hitung = new javax.swing.JButton();
        tambah = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabel = new javax.swing.JTable();
        jLabel9 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jButton5 = new javax.swing.JButton();
        jTextField3 = new javax.swing.JTextField();
        jTextField1 = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jumlah = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(44, 54, 93));
        jPanel1.setForeground(new java.awt.Color(0, 51, 51));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("No.Faktur");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 80, -1, -1));

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Kode Barang  ");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 170, -1, -1));

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Nama barang  ");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 130, -1, -1));

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Harga Satuan");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 200, -1, -1));

        faktur.setEditable(false);
        jPanel1.add(faktur, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 80, 90, 30));

        kdbarang.setEditable(false);
        jPanel1.add(kdbarang, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 160, 120, 30));

        pilihbarang.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "pilih barang" }));
        pilihbarang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pilihbarangActionPerformed(evt);
            }
        });
        jPanel1.add(pilihbarang, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 120, 130, 30));

        tjumlah.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tjumlahKeyTyped(evt);
            }
        });
        jPanel1.add(tjumlah, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 240, 130, 30));

        hsatuan.setEditable(false);
        hsatuan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hsatuanActionPerformed(evt);
            }
        });
        jPanel1.add(hsatuan, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 200, 130, 30));

        ttotal.setEditable(false);
        ttotal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ttotalActionPerformed(evt);
            }
        });
        jPanel1.add(ttotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 280, 130, 30));

        hitung.setText("HITUNG");
        hitung.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hitungActionPerformed(evt);
            }
        });
        jPanel1.add(hitung, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 280, 80, 30));

        tambah.setText("TAMBAH");
        tambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tambahActionPerformed(evt);
            }
        });
        jPanel1.add(tambah, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 330, 330, 30));

        tabel.setModel(new javax.swing.table.DefaultTableModel(
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
        tabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabel);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 370, 540, 100));

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Jumlah Beli");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 240, -1, -1));

        jPanel2.setBackground(new java.awt.Color(255, 94, 58));

        jLabel5.setFont(new java.awt.Font("Bradley Hand ITC", 1, 24)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Mendata Barang");

        jButton4.setText("kembali");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 666, Short.MAX_VALUE)
                .addComponent(jButton4)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 940, 50));

        jPanel3.setBackground(new java.awt.Color(255, 94, 58));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton5.setText("TOTAL");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(69, 107, 194, 30));

        jTextField3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField3ActionPerformed(evt);
            }
        });
        jTextField3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField3KeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField3KeyTyped(evt);
            }
        });
        jPanel3.add(jTextField3, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 170, 160, 40));

        jTextField1.setEditable(false);
        jPanel3.add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 230, 160, 40));

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("bayar");
        jPanel3.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 180, -1, -1));

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("kembalian");
        jPanel3.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 240, -1, -1));

        jButton3.setText("CETAK");
        jButton3.setEnabled(false);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 350, 230, 30));

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jPanel3.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(128, 60, -1, -1));

        jButton1.setText("SELESAI TRANSAKSI");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 300, 150, 30));

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Rp.");
        jPanel3.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 60, 50, -1));

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 80, 320, 400));

        jumlah.setEnabled(false);
        jPanel1.add(jumlah, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 90, 20, 10));

        jTextField2.setEditable(false);
        jTextField2.setText("jTextField2");
        jPanel1.add(jTextField2, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 380, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 935, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 529, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 24, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void hsatuanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hsatuanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_hsatuanActionPerformed

    private void pilihbarangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pilihbarangActionPerformed
        // TODO add your handling code here:
         if (pilihbarang.getSelectedItem().equals("pilih barang")){
            kdbarang.setText("");
            hsatuan.setText("");
        }else{
        try {
            Connection c = koneksi.getKoneksi();
            Statement s = c.createStatement();

            String sql = "SELECT kd_barang, jumlah_barang FROM tbl_barang WHERE nama_barang ='" + pilihbarang.getSelectedItem() + "'";
            ResultSet r = s.executeQuery(sql);

            while (r.next()) {
                kdbarang.setText(r.getString("kd_barang"));
                jumlah.setText(r.getString("jumlah_barang"));
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        try {
            Connection c = koneksi.getKoneksi();
            Statement s = c.createStatement();

            String sql = "SELECT harga_jual FROM tbl_barang WHERE nama_barang ='" + pilihbarang.getSelectedItem() + "'";
            ResultSet r = s.executeQuery(sql);

            while (r.next()) {
                hsatuan.setText(r.getString("harga_jual"));
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        }
    }//GEN-LAST:event_pilihbarangActionPerformed

    private void hitungActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hitungActionPerformed
        // TODO add your handling code here:
         if(faktur.getText().equals("") ||kdbarang.getText().equals("") || pilihbarang.getSelectedItem().equals("")|| hsatuan.getText().equals("")|| tjumlah.getText().equals("")){
            JOptionPane.showMessageDialog(null, "LENGKAPI DATA !", "elektronik berkah", JOptionPane.INFORMATION_MESSAGE);
        
        }else{
        String a = tjumlah.getText();
        int aa = Integer.parseInt(a);
        
        String b = jumlah.getText();
        int bb = Integer.parseInt(b);
        if(aa > bb){
             JOptionPane.showMessageDialog(null, "jumlah melebihi stok", "Elektronik Berkah", JOptionPane.INFORMATION_MESSAGE);
             tjumlah.setText("");
        }else{
       
        if(tjumlah.getText().equals("")){
            JOptionPane.showMessageDialog(null, "ISI JUMLAH BELI !");
        }else{
        int jumlah, harga, total;
       
        jumlah = Integer.parseInt(tjumlah.getText().toString());
        harga = Integer.parseInt(hsatuan.getText().toString());
        total = jumlah * harga;
       
       
             ttotal.setText(Integer.toString(total));
        
        }
        }
        }  
        
        
    }//GEN-LAST:event_hitungActionPerformed

    private void tambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tambahActionPerformed
        // TODO add your handling code here:
        
        if(faktur.getText().equals("") ||kdbarang.getText().equals("") || pilihbarang.getSelectedItem().equals("")|| hsatuan.getText().equals("")|| tjumlah.getText().equals("")|| ttotal.getText().equals("")){
            JOptionPane.showMessageDialog(null, "LENGKAPI DATA !", "elektronik berkah", JOptionPane.INFORMATION_MESSAGE);
        
        }else{
            String kdbarangg = kdbarang.getText();
            String pilihbarangg = (String)pilihbarang.getSelectedItem();
            String hsatuann = hsatuan.getText();
            String tjumlahh = tjumlah.getText();
            String totall = ttotal.getText();
           
            try {
                Connection c = koneksi.getKoneksi();
                
                String sql = "INSERT INTO tbl_tmp_beli VALUES (?, ?, ?, ?, ?, ?)";
                
               
                PreparedStatement p = c.prepareStatement(sql);
                p.setString(1, null);
                p.setString(2, kdbarangg);
                p.setString(3, pilihbarangg);
                p.setString(4, hsatuann);
                p.setString(5, tjumlahh);
                p.setString(6, totall);
                
                p.executeUpdate();
                p.close();
            } catch (SQLException e) {
                System.out.println("Terjadi Error");
            } finally {
                nofaktur();
                kdbarang.setText("");
                pilihbarang.setSelectedItem("");
                hsatuan.setText("");
                tjumlah.setText("");
                ttotal.setText("");
                JOptionPane.showMessageDialog(null, "Data berhasil disimpan", "Elektronik Berkah", JOptionPane.INFORMATION_MESSAGE);
                loadData();
                
                
            }
        }
    }//GEN-LAST:event_tambahActionPerformed

    private void tabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelMouseClicked
        // TODO add your handling code here:
        int jawaban;
        if ((jawaban = JOptionPane.showConfirmDialog(null,"Yakin batal?", "Konfirmasi", JOptionPane.YES_NO_OPTION)) == 0) {
        try{
        
        int i = tabel.getSelectedRow();
        if (i == -1) {
            return;
        }
        String id = (String) model.getValueAt(i, 0);
        
        st = cn.createStatement();
        st.executeUpdate("delete from tbl_tmp_beli where id_tmp = '"+id+ "'");
        
        nofaktur();
        loadData();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    }//GEN-LAST:event_tabelMouseClicked

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
         formmenu fb = new formmenu();
        fb.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
         if(jTextField3.getText().equals("") ||jTextField1.getText().equals("")){
            JOptionPane.showMessageDialog(null, "LENGKAPI DATA !", "elektronik berkah", JOptionPane.INFORMATION_MESSAGE);
        
        }else{
            String a = jTextField1.getText();
            int ab = Integer.parseInt(String.valueOf(jTextField1.getText()));
              if(ab < 0){
                JOptionPane.showMessageDialog(null, "Uang anda kurang", "Elektronik Berkah", JOptionPane.INFORMATION_MESSAGE);
                jTextField3.setText("");
            jTextField1.setText("");
              }else{
           try {
            Connection c = koneksi.getKoneksi();
            Statement s = c.createStatement();

            String sql = "SELECT * FROM tbl_tmp_beli";
            ResultSet r = s.executeQuery(sql);

            while (r.next()) {
                long millis=System.currentTimeMillis();  
                java.sql.Date date=new java.sql.Date(millis);  
                System.out.println(date); 
                String tgl = date.toString();
                String sqla = "INSERT INTO tbl_beli VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
         
                PreparedStatement p = c.prepareStatement(sqla);
                p.setString(1, faktur.getText());
                p.setString(2, r.getString("kd_barang"));
                p.setString(3, r.getString("nama_barang"));
                p.setString(4, r.getString("hsatuan"));
                p.setString(5, r.getString("jumlah_beli"));
                p.setString(6, r.getString("harga"));
                p.setString(7, jTextField3.getText());
                p.setString(8, jTextField1.getText());
                p.setString(9, tgl);
                
                p.executeUpdate();
                p.close();
                
                
            }
            r.close();
            s.close();
        } catch (SQLException e) {
            System.out.println("Terjadi Error");
        }finally{
         try {
            String sqla ="TRUNCATE `tbl_tmp_beli";
            java.sql.Connection conn=(Connection)koneksi.getKoneksi();
            java.sql.PreparedStatement pst=conn.prepareStatement(sqla);
            pst.execute();
            JOptionPane.showMessageDialog(null, "TRANSAKSI SELESAI", "Elektronik Berkah", JOptionPane.INFORMATION_MESSAGE);
            loadData();
            jTextField2.setText(faktur.getText());
            jTextField3.setText("");
            jTextField1.setText("");
            jLabel4.setText("");
            nofaktur();
            jButton3.setEnabled(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
           }
              }
         }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        //
        
        try {
            Connection c = koneksi.getKoneksi();
            Statement s = c.createStatement();

            String sql = "SELECT SUM(`harga`) AS total FROM tbl_tmp_beli";
            ResultSet r = s.executeQuery(sql);
           
            while (r.next()) {
                jLabel4.setText(r.getString(""+"total"));
                
            }
            r.close();
            s.close();
        } catch (SQLException e) {
            System.out.println("Terjadi Error");
        }
        
        
        
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jTextField3KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField3KeyReleased
        // TODO add your handling code here:
        
         bayar = Integer.parseInt(String.valueOf(jTextField3.getText()));
            total = Integer.parseInt(String.valueOf(jLabel4.getText()));
            kembali = bayar - total;
            
            jTextField1.setText(Long.toString(kembali));
    }//GEN-LAST:event_jTextField3KeyReleased

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
         try{
            Desktop.getDesktop().browse(new URL("http://localhost:81/penjualanelektronik/invoice.php?lap&fk="+jTextField2.getText()+"").toURI());
            } catch (Exception e){
                System.out.println(e);
            }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jTextField3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField3ActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_jTextField3ActionPerformed

    private void jTextField3KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField3KeyTyped
        // TODO add your handling code here:
       FilterAngka(evt);
    }//GEN-LAST:event_jTextField3KeyTyped

    private void ttotalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ttotalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ttotalActionPerformed

    private void tjumlahKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tjumlahKeyTyped
        // TODO add your handling code here:
        FilterAngka(evt);
    }//GEN-LAST:event_tjumlahKeyTyped

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
            java.util.logging.Logger.getLogger(formbeli.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(formbeli.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(formbeli.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(formbeli.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new formbeli().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField faktur;
    private javax.swing.JButton hitung;
    private javax.swing.JTextField hsatuan;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
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
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jumlah;
    private javax.swing.JTextField kdbarang;
    private javax.swing.JComboBox<String> pilihbarang;
    private javax.swing.JTable tabel;
    private javax.swing.JButton tambah;
    private javax.swing.JTextField tjumlah;
    private javax.swing.JTextField ttotal;
    // End of variables declaration//GEN-END:variables
}
