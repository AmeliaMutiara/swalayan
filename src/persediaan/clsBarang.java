/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persediaan;
import java.sql.*;
import javax.swing.JOptionPane;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import net.sf.jasperreports.view.save.JRPdfSaveContributor;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperDesignViewer;
import net.sf.jasperreports.view.JasperViewer;
/**
 *
 * @author User
 */
public class clsBarang {
    JasperReport jasperReport;
    JasperDesign jasperDesign;
    JasperPrint jasperPrint;
    Map<String, Object> pm = new HashMap<String,Object>();
    protected String kode, nama, sat;
    protected int hrg, stok, flag;
    
    public void setKode (String Kd) {
        kode = Kd;
    }
    public void setNama (String Nm) {
        nama = Nm;
    } 
    public void setSatuan (String St) {
        sat = St;
    }
    public void setHarga (int Hr) {
        hrg = Hr;
    }
    public void setStok (int Sk) {
        stok = Sk;
    }
    public String getKode() {
        return(kode);
    }
    public String getNama() {
        return(nama);
    }
    public String getSatuan() {
        return(sat);
    }
    public int getHarga() {
        return(hrg);
    }
    public int getStok() {
        return(stok);
    }
    public void setFlag(int F) {
        flag = F;
    }
    public int getFlag() {
        return(flag);
    }
    
    public void simpan() {
        try {
            koneksi k = new koneksi();
            Connection cn = k.openKoneksi();
            Statement st = cn.createStatement();
            String sql = "INSERT INTO barang VALUES(";
                   sql+= "'"+getKode()+"','"+getNama()+"',";
                   sql+= "'"+getSatuan()+"','"+getHarga()+"',";
                   sql+= "'"+getStok()+"');";                
                st.executeUpdate(sql);
                setFlag(1);
                st.close();
                cn.close();
                JOptionPane.showMessageDialog(null, "Data berhasil disimpan",
                        "SIMPAN", JOptionPane.INFORMATION_MESSAGE);
        }catch (SQLException sqe) {
            JOptionPane.showMessageDialog(null, "Gagal Simpan", "Gagal Simpan",
                    JOptionPane.WARNING_MESSAGE);
        }
    }
    
    public void ubah()  {
        try {
            koneksi k = new koneksi();
            Connection cn = k.openKoneksi();
            Statement st = cn.createStatement();
            String sql = "UPDATE barang SET ";
                   sql+= "nmbrg='"+getNama()+"',";
                   sql+= "sat='"+getSatuan()+"',";
                   sql+= "hrgbrg='"+getHarga()+"',";
                   sql+= "stok='"+getStok()+"'";
                   sql+= "WHERE kdbrg='"+getKode()+"'";
                st.executeUpdate(sql);
                setFlag(2);
                st.close();
                cn.close();
                JOptionPane.showMessageDialog(null, "Data berhasil diubah",
                        "UBAH", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException sqe) {
            JOptionPane.showMessageDialog(null, "Data Gagal Diubah", 
                    "Gagal Ubah", JOptionPane.WARNING_MESSAGE);
        }
    }
    
    public void hapus()  {
        try {
            koneksi k = new koneksi();
            Connection cn = k.openKoneksi();
            Statement st = cn.createStatement();
            String sql = "DELETE FROM barang ";
                   sql+= "WHERE kdbrg='"+getKode()+"'";
            st.executeUpdate(sql);
            setFlag(3);
            st.close();
            cn.close();
            JOptionPane.showMessageDialog(null, "Data berhasil di Hapus",
                        "HAPUS", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException sqe) {
            JOptionPane.showMessageDialog(null, "Data Gagal Dihapus", 
                    "Gagal Hapus", JOptionPane.WARNING_MESSAGE);
        }
    }
    
    public void tampil()  {
        try{
            koneksi k = new koneksi();
            Connection cn = k.openKoneksi();
            Statement st = cn.createStatement();
            String sql = "SELECT * FROM barang ";
                   sql+= "WHERE kdbrg='"+getKode()+"'";
            ResultSet rs = st.executeQuery(sql);
            if(rs.next()) {
                setFlag(4);
                setKode(rs.getString("kdbrg"));
                setNama(rs.getString("nmbrg"));
                setSatuan(rs.getString("sat"));
                setHarga(rs.getInt("hrgbrg"));
                setStok(rs.getInt("stok"));
                st.close();
                rs.close();
            }
        } catch(SQLException sqe) {
            {}
        }
    } 
    
    public void autoCode()  {
        try {
            int hit = 0;
            koneksi k = new koneksi();
            Connection cn = k.openKoneksi();
            Statement st = cn.createStatement();
            String sql = "SELECT COUNT(kdbrg) FROM barang";
            ResultSet rs = st.executeQuery(sql);
            
            if(rs.next()) {
                if (Integer.parseInt(rs.getString(1))==0) {
                    setKode("B001");
                    st.close();
                    rs.close();
                } else {
                    sql = "SELECT MAX(mid(kdbrg,2,4)) FROM barang";
                    rs = st.executeQuery(sql);
                    rs.next();
                    hit = (Integer.parseInt(rs.getString(1))) + 1;
                    if(hit < 10) {
                        setKode("B00" + hit);
                    } else if (hit < 100) {
                        setKode("B0" + hit);
                    } else if (hit < 1000) {
                        setKode("B" + hit);
                    }
                    st.close();
                    rs.close();
                }
            }
        } catch (SQLException sqe) {
            
        }
    }
    
    public void cetak() {
        try {
            koneksi k = new koneksi();
            Connection cn = k.openKoneksi();
            File f = new File("src/persediaan/report_barang.jrxml");
            jasperDesign = JRXmlLoader.load(f);
            pm.clear();
            
            jasperReport = JasperCompileManager.compileReport(jasperDesign);
            jasperPrint = JasperFillManager.fillReport(jasperReport, pm, cn);
            JasperViewer.viewReport(jasperPrint, false);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
