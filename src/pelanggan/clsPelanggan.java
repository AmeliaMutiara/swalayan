/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pelanggan;
import java.io.File;
import persediaan.koneksi;
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
public class clsPelanggan {
    JasperReport jasperReport;
    JasperDesign jasperDesign;
    JasperPrint jasperPrint;
    Map<String, Object> pm = new HashMap<String,Object>();
    protected String kode, nama, jk, alamat, telp;
    protected int flag;
    
    public void setKode (String Kd) {
        kode = Kd;
    }
    public void setNama (String Nm) {
        nama = Nm;
    }
    public void setJk (String Jk) {
        jk = Jk;
    }
    public void setAlamat (String Am) {
        alamat = Am;
    }
    public void setTelp (String Tp) {
        telp = Tp;
    }
    public String getKode() {
        return(kode);
    }
    public String getNama() {
        return(nama);
    }
    public String getJk() {
        return(jk);
    }
    public String getAlamat() {
        return(alamat);
    }
    public String getTelp() {
        return(telp);
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
            String sql = "INSERT INTO pelanggan VALUES(";
                   sql+= "'"+getKode()+"','"+getNama()+"',";
                   sql+= "'"+getJk()+"','"+getAlamat()+"',";
                   sql+= "'"+getTelp()+"');";                
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
            String sql = "UPDATE pelanggan SET ";
                   sql+= "nmplg='"+getNama()+"',";
                   sql+= "jk='"+getJk()+"',";
                   sql+= "alamat='"+getAlamat()+"',";
                   sql+= "telp='"+getTelp()+"'";
                   sql+= "WHERE kd_plg='"+getKode()+"'";
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
            String sql = "DELETE FROM pelanggan ";
                   sql+= "WHERE kd_plg='"+getKode()+"'";
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
            String sql = "SELECT * FROM pelanggan ";
                   sql+= "WHERE kd_plg='"+getKode()+"'";
            ResultSet rs = st.executeQuery(sql);
            if(rs.next()) {
                setFlag(4);
                setKode(rs.getString("kd_plg"));
                setNama(rs.getString("nmplg"));
                setJk(rs.getString("jk"));
                setAlamat(rs.getString("alamat"));
                setTelp(rs.getString("telp"));
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
            String sql = "SELECT COUNT(kd_plg) FROM pelanggan";
            ResultSet rs = st.executeQuery(sql);
            
            if(rs.next()) {
                if (Integer.parseInt(rs.getString(1))==0) {
                    setKode("P001");
                    st.close();
                    rs.close();
                } else {
                    sql = "SELECT MAX(mid(kd_plg,2,4)) FROM pelanggan";
                    rs = st.executeQuery(sql);
                    rs.next();
                    hit = (Integer.parseInt(rs.getString(1))) + 1;
                    if(hit < 10) {
                        setKode("P00" + hit);
                    } else if (hit < 100) {
                        setKode("P0" + hit);
                    } else if (hit < 1000) {
                        setKode("P" + hit);
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
            File f = new File("src/pelanggan/report_pelanggan.jrxml");
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
