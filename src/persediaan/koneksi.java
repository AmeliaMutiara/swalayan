/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persediaan;
import java.sql.*;
import com.mysql.jdbc.Driver;
/**
 *
 * @author User
 */
public class koneksi {
    private Connection koneksi;
    public Connection openKoneksi() throws SQLException {
        if (koneksi == null) {
            new Driver();
            koneksi = DriverManager.getConnection("jdbc:mysql://localhost/swalayan", "root","");
        }
        return koneksi;
    }
}
