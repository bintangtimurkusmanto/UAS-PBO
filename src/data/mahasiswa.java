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
public class mahasiswa extends CRUD {
    
    // constructor
    public mahasiswa(String NAMA, String PRODI, String FAKULTAS, String ALAMAT){
        this.nama = NAMA;
        this.prodi = PRODI;
        this.fakultas = FAKULTAS;
        this.alamat = ALAMAT;
    }
    
    public mahasiswa(){
    
    }
    
    // mencari nama mahasiswa di db
    @Override
    public void select(MySQLKoneksi m, String nama){
        
        // query sql untuk mencari data mahasiswa berdasarkan nama
        String sql = "SELECT * FROM `mahasiswa` WHERE `nama` LIKE '%" + nama +"%'";
        
        // lakukan koneksi ke mysql
        Connection koneksi = m.conn;
        
        try {
            Statement statement = koneksi.createStatement();
            // jalankan query
            ResultSet result = statement.executeQuery(sql);

            // membuat header table untuk output
            System.out.println("==============================================================================");
            String header = "%3s %15s %20s %15s %20s";
            System.out.println(String.format(header, "NIM", "NAMA", "PRODI", "FAKULTAS", "ALAMAT"));
            System.out.println("------------------------------------------------------------------------------");
            
            // looping untuk baca data per record
            while (result.next()){
                // baca data mahasiswa per record
                String Nim = result.getString("nim");
                String Nama = result.getString("nama");
                String Prodi = result.getString("prodi");
                String Fakultas = result.getString("fakultas");
                String Alamat = result.getString("alamat");
                // tampilkan data mahasiswa per record
                String output = "%3s %15s %20s %15s %20s";
                System.out.println(String.format(output, Nim, Nama, Prodi, Fakultas, Alamat));
            }
            
            System.out.println("==============================================================================");
            
        } catch (SQLException ex){
            System.out.println("Tampil data mahasiswa gagal");
        }
        
    }
    
    // insert data mahasiswa
    @Override
    public void insert(MySQLKoneksi m){
        // lakukan koneksi ke mysql
        Connection koneksi = m.conn;
        
        // query sql untuk insert data mahasiswa
        String sql = "INSERT INTO mahasiswa (nama, prodi, fakultas, alamat) VALUES (?, ?, ?, ?)";
 
        try {
            PreparedStatement statement = koneksi.prepareStatement(sql);
            
            // mapping nilai parameter dari query sql nya (sesuai urutan)
            statement.setString(1, this.nama);
            statement.setString(2, this.prodi);
            statement.setString(3, this.fakultas);
            statement.setString(4, this.alamat);

            // jalankan query (baca jumlah row affectednya)
            int rowsInserted = statement.executeUpdate();
            // jika ada row affected nya, maka status sukses
            if (rowsInserted > 0) {
                System.out.println("Tambah data mahasiswa sukses");
            }

        } catch (SQLException ex) {
            // jika query gagal
            System.out.println("Insert data mahasiswa gagal");
        }
    }
    
    // update data mahasiswa
    public void update(MySQLKoneksi m, Integer nim, String nama, String prodi, String fakultas, String alamat){
        
        // query sql untuk update data mahasiswa berdasarkan nim
        String sql = "UPDATE mahasiswa SET nama=?, prodi=?, fakultas=?, alamat=? WHERE nim=?";
        // lakukan koneksi ke mysql
        Connection koneksi = m.conn;
        
        try {
            PreparedStatement statement = koneksi.prepareStatement(sql);
            // mapping nilai parameter ke query sqlnya
            statement.setString(1, nama);
            statement.setString(2, prodi);
            statement.setString(3, fakultas);
            statement.setString(4, alamat);
            statement.setString(5, nim.toString());

            // jalankan query, dan baca jumlah row affectednya
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Update data mahasiswa sukses");
            }
        } catch (SQLException ex) {
             System.out.println("Update data mahasiswa gagal");
        }
    }
    
    // delete data mahasiswa 
    @Override
    public void delete(MySQLKoneksi m, Integer nim){
        
        // query sql untuk hapus data mahasiswa berdasarkan idbook
        String sql = "DELETE FROM mahasiswa WHERE nim=?";
        // lakukan koneksi ke mysql
        Connection koneksi = m.conn;
        
        try {
            PreparedStatement statement;
            statement = koneksi.prepareStatement(sql);
            
            // mapping nilai parameter dari query sql nya
            statement.setString(1, nim.toString());
            
            // jalankan query, dan lihat jumlah row affected nya
            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Mahasiswa sudah berhasil dihapus");
            }
        } catch (SQLException ex) {
            System.out.println("Hapus data mahasiswa gagal");
        }
        
    }
    
    
    
}
