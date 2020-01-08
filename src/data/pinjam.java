/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import koneksi.MySQLKoneksi;
import java.sql.*;

/**
 *
 * @author Zulfa Candraningrum
 */
public class pinjam {
    
    // atribut pinjam
    public String nim;
    public String kodeBuku;
    public String tglPinjam;
    public String tglKembali;
    
    // constructor
    public pinjam(String NIM, String kode, String tgl_pinjam, String tgl_kembali){
        this.nim = NIM;
        this.kodeBuku = kode;
        this.tglPinjam = tgl_pinjam;
        this.tglKembali = tgl_kembali;
    }
    
    public pinjam(){
    
    }
    
    public void input_pinjam(MySQLKoneksi m){
        // lakukan koneksi ke mysql
        Connection koneksi = m.conn;
        
        // query sql untuk insert pinjam buku
        String sql = "INSERT INTO pinjam (nim, kode_buku, tgl_pinjam, tgl_kembali) VALUES (?, ?, ?, ?)";
 
        try {
            PreparedStatement statement = koneksi.prepareStatement(sql);
            
            // mapping nilai parameter dari query sql nya (sesuai urutan)
            statement.setString(1, this.nim);
            statement.setString(2, this.kodeBuku);
            statement.setString(3, this.tglPinjam);
            statement.setString(4, this.tglKembali);

            // jalankan query (baca jumlah row affectednya)
            int rowsInserted = statement.executeUpdate();
            // jika ada row affected nya, maka status sukses
            if (rowsInserted > 0) {
                System.out.println("Peminjaman sukses");
            }

        } catch (SQLException ex) {
            // jika query gagal
            System.out.println("Peminjaman gagal");
        }
    }
    
}
