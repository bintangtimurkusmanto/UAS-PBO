/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import koneksi.MySQLKoneksi;

/**
 *
 * @author Halim Mukti M
 */
public class kembali {
    
    // atribut
    public String idPinjam;
    public String tglKembali;
    public Integer denda;
    
    // constructor
    public kembali(String id, int Denda, String tglKbl){
        this.idPinjam = id;
        this.tglKembali = tglKbl;
        this.denda = Denda;
    }
    
    public kembali(){
        
    }
    
    public void pengembalian(MySQLKoneksi m){
        
        // query sql untuk update data buku berdasarkan idbook
        String sqlUpdate = "UPDATE pinjam SET status='sudah dikembalikan' WHERE id_pinjam=?";
        // lakukan koneksi ke mysql
        Connection koneksi = m.conn;
        
        try {
            PreparedStatement statement = koneksi.prepareStatement(sqlUpdate);
            // mapping nilai parameter ke query sqlnya
            statement.setString(1, idPinjam);

            // jalankan query, dan baca jumlah row affectednya
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("");
            }
        } catch (SQLException ex) {
             System.out.println("Update data pinjam gagal");
        }
        
        // query sql untuk insert kembali buku
        String sql = "INSERT INTO kembali (id_pinjam, dikembalikan_tgl, denda) VALUES (?, ?, ?)";
 
        try {
            PreparedStatement statement = koneksi.prepareStatement(sql);
            
            // mapping nilai parameter dari query sql nya (sesuai urutan)
            statement.setString(1, this.idPinjam);
            statement.setString(2, this.tglKembali);
            statement.setString(3, this.denda.toString());

            // jalankan query (baca jumlah row affectednya)
            int rowsInserted = statement.executeUpdate();
            // jika ada row affected nya, maka status sukses
            if (rowsInserted > 0) {
                System.out.println("Pengembalian sukses");
            }

        } catch (SQLException ex) {
            // jika query gagal
            System.out.println("Insert data pengembalian gagal");
        }
    }
}
