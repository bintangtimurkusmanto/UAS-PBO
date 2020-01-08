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
 * @author Bintang
 */
public class buku extends CRUD{
   
    // constructor
    public buku(String jdl, String Penerbit, String Pengarang, int tahun){
        this.judul = jdl;
        this.penerbit = Penerbit;
        this.pengarang = Pengarang;
        this.tahunTerbit = tahun;
    }

    public buku() {

    }
    
    // mencari data buku berdasarkan judul
    @Override
    public void select(MySQLKoneksi m, String judul){
        
        // query sql untuk mencari data buku berdasarkan judul
        String sql = "SELECT * FROM `buku` WHERE `judul` LIKE '%" + judul +"%'";
        
        // lakukan koneksi ke mysql
        Connection koneksi = m.conn;
        
        try {
            Statement statement = koneksi.createStatement();
            // jalankan query
            ResultSet result = statement.executeQuery(sql);

            // membuat header table untuk output
            System.out.println("==============================================================================");
            String header = "%3s %20s %20s %15s %15s";
            System.out.println(String.format(header, "KODE", "JUDUL", "PENERBIT", "PENGARANG", "THN TERBIT"));
            System.out.println("------------------------------------------------------------------------------");
            
            // looping untuk baca data per record
            while (result.next()){
                // baca data buku per record
                String KodeBuku = result.getString("kode_buku");
                String Judul = result.getString("judul");
                String Penerbit = result.getString("penerbit");
                String Pengarang = result.getString("pengarang");
                String TahunTerbit = result.getString("tahun_terbit");
                // tampilkan data buku per record
                String output = "%3s %20s %20s %15s %15s";
                System.out.println(String.format(output, KodeBuku, Judul, Penerbit, Pengarang, TahunTerbit));
            }
            
            System.out.println("==============================================================================");
            
        } catch (SQLException ex){
            System.out.println("Tampil data buku gagal");
        }
        
    }
    
    // insert data buku
    @Override
    public void insert(MySQLKoneksi m){
        // lakukan koneksi ke mysql
        Connection koneksi = m.conn;
        
        // query sql untuk insert data buku
        String sql = "INSERT INTO buku (judul, penerbit, pengarang, tahun_terbit) VALUES (?, ?, ?, ?)";
 
        try {
            PreparedStatement statement = koneksi.prepareStatement(sql);
            
            // mapping nilai parameter dari query sql nya (sesuai urutan)
            statement.setString(1, this.judul);
            statement.setString(2, this.penerbit);
            statement.setString(3, this.pengarang);
            statement.setString(4, this.tahunTerbit.toString());

            // jalankan query (baca jumlah row affectednya)
            int rowsInserted = statement.executeUpdate();
            // jika ada row affected nya, maka status sukses
            if (rowsInserted > 0) {
                System.out.println("Tambah buku baru sukses");
            }

        } catch (SQLException ex) {
            // jika query gagal
            System.out.println("Insert data buku gagal");
        }
    }
    
    // update data buku
    public void update(MySQLKoneksi m, Integer kode, String judul, String penerbit, String pengarang, Integer tahunTerbit){
        
        // query sql untuk update data buku berdasarkan idbook
        String sql = "UPDATE buku SET judul=?, penerbit=?, pengarang=?, tahun_terbit=? WHERE kode_buku=?";
        // lakukan koneksi ke mysql
        Connection koneksi = m.conn;
        
        try {
            PreparedStatement statement = koneksi.prepareStatement(sql);
            // mapping nilai parameter ke query sqlnya
            statement.setString(1, judul);
            statement.setString(2, penerbit);
            statement.setString(3, pengarang);
            statement.setString(4, tahunTerbit.toString());
            statement.setString(5, kode.toString());

            // jalankan query, dan baca jumlah row affectednya
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Update data buku sukses");
            }
        } catch (SQLException ex) {
             System.out.println("Update data buku gagal");
        }
    }
    
    // delete data buku 
    @Override
    public void delete(MySQLKoneksi m, Integer kode){
        
        // query sql untuk hapus data buku berdasarkan idbook
        String sql = "DELETE FROM buku WHERE kode_buku=?";
        // lakukan koneksi ke mysql
        Connection koneksi = m.conn;
        
        try {
            PreparedStatement statement;
            statement = koneksi.prepareStatement(sql);
            
            // mapping nilai parameter dari query sql nya
            statement.setString(1, kode.toString());
            
            // jalankan query, dan lihat jumlah row affected nya
            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Buku sudah berhasil dihapus");
            }
        } catch (SQLException ex) {
            System.out.println("Hapus data buku gagal");
        }
        
    }
    
}
