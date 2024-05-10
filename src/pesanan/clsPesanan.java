/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pesanan;
import persediaan.koneksi;
import java.sql.*;
import javax.swing.JOptionPane;
/**
 *
 * @author User
 */
public class clsPesanan {
    protected String nopsn, kodeplg, kodebrg;
    protected int jumlah, harga, flag;
    protected Date tglpesan;
    
    public void setNoPesan(String N) {
        nopsn = N;
    }
    public void setTanggal(Date Tg) {
        tglpesan = Tg;
    }
    public void setKdPel(String kdpl) {
        kodeplg = kdpl;
    }
    public void setKdBar(String kdbr) {
        kodebrg = kdbr;
    }
    public void setJum(int jml) {
        jumlah = jml;
    }
    public void setHr(int hrg) {
        harga = hrg;
    }
    public void setFlag(int F) {
        flag = F;
    }
    
    public String getNoPesan() {
        return nopsn;
    }
    public String getKdPel() {
        return kodeplg;
    }
    public String getKdBr() {
        return kodebrg;
    }
    public int getJum() {
        return jumlah;
    }
    public int getHr() {
        return harga;
    }
    public int getFlag() {
        return flag;
    }
    public Date getTanggal() {
        return tglpesan;
    }
    
    public void simpanPesan() {
        try {
            koneksi k = new koneksi();
            Connection cn = k.openKoneksi();
            Statement st = cn.createStatement();
            String sql = "INSERT INTO pesanan VALUES(";
                       sql+="'"+getNoPesan()+"','"+getTanggal()+"',";
                       sql+="'"+getKdPel()+"')";
            st.executeUpdate(sql);
            setFlag(1);
            st.close();
            cn.close();
            
            JOptionPane.showMessageDialog(null, "Data Berhasil disimpan pesan", "SIMPAN",
                    JOptionPane.INFORMATION_MESSAGE);
        }catch(Exception e) {
            JOptionPane.showMessageDialog(null, "Data gagal disimpan pesan" + e.getMessage());
        }
    }
    
    public void simpanDetailPesan(){
        try{
            koneksi k = new koneksi();
            Connection cn = k.openKoneksi();
            Statement st = cn.createStatement();
            String sql = "INSERT INTO detailpesan VALUES(";
                       sql+="'"+getNoPesan()+"','"+getKdBr()+"',";
                       sql+="'"+getJum()+"','"+getHr()+"')";
            st.executeUpdate(sql);
            setFlag(1);
            st.close();
            cn.close();
            
            JOptionPane.showMessageDialog(null, "Data Berhasil disimpan detail pesan", "SIMPAN",
                    JOptionPane.INFORMATION_MESSAGE);
        }catch(Exception e) {
            JOptionPane.showMessageDialog(null, "Data gagal disimpan di detail pesan pesan" + 
                    e.getMessage());
        }
    }
    
    public void updateStok() {
        try{
            koneksi k = new koneksi();
            Connection cn = k.openKoneksi();
            Statement st = cn.createStatement();
            String sql = "UPDATE barang SET stok = stok -'"+getJum()+"'";
                       sql+="WHERE kdbrg='" + getKdBr() +"'";
            st.executeUpdate(sql);
            st.close();
            cn.close();
        }catch(SQLException e) {
            
        }
    }
    
    public void autopesan() {
        try{ 
            int hit = 0;
            koneksi k = new koneksi();
            Connection cn = k.openKoneksi();
            Statement st = cn.createStatement();
            String sql = "SELECT COUNT(nopesan) FROM pesanan";
            ResultSet rs = st.executeQuery(sql);
            
            if(rs.next()) {
                if (Integer.parseInt(rs.getString(1))== 0){
                    setNoPesan("PS0001");
                
                st.close();
                rs.close();
            } else {
                sql = "SELECT MAX(mid(nopesan, 3, 3)) FROM pesanan";
                rs = st.executeQuery(sql);
                rs.next();
                hit = (Integer.parseInt(rs.getString(1))) + 1;
                if(hit < 10) {
                    setNoPesan("PS000"+hit);
                } else if(hit < 100) {
                    setNoPesan("PS00"+hit);
                } else if(hit < 10) {
                    setNoPesan("PS0"+hit);
                } else {
                    setNoPesan("PS"+hit);
                }
                st.close();
                rs.close();
                }
            }
        } catch(SQLException sqe) {
            
        }
    }
}
