/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import koneksi.MySQLKoneksi;

/**
 *
 * @author Muhammad Rizki F
 */
public class laporan {
    
    // menampilkan semua data mahasiswa di db
    public void dataMhs(MySQLKoneksi m){
        
        // query sql untuk menampilkan semua data mahasiswa
        String sql = "SELECT * FROM `mahasiswa`";
        
        // lakukan koneksi ke mysql
        Connection koneksi = m.conn;
        
        try {
            Statement statement = koneksi.createStatement();
            // jalankan query
            ResultSet result = statement.executeQuery(sql);

            // membuat header table untuk output
            System.out.println("=========================================================================================");
            System.out.println("                                   DATA MAHASISWA");
            System.out.println("-----------------------------------------------------------------------------------------");
            String header = "%3s %20s %20s %20s %22s";
            System.out.println(String.format(header, "NIM", "NAMA", "PRODI", "FAKULTAS", "ALAMAT"));
            System.out.println("-----------------------------------------------------------------------------------------");
            
            // looping untuk baca data per record
            while (result.next()){
                // baca data mahasiswa per record
                String Nim = result.getString("nim");
                String Nama = result.getString("nama");
                String Prodi = result.getString("prodi");
                String Fakultas = result.getString("fakultas");
                String Alamat = result.getString("alamat");
                // tampilkan data mahasiswa per record
                String output = "%3s %20s %20s %20s %22s";
                System.out.println(String.format(output, Nim, Nama, Prodi, Fakultas, Alamat));
            }
            
            System.out.println("=========================================================================================");
            
        } catch (SQLException ex){
            System.out.println("Tampil data mahasiswa gagal");
        }
        
    }
    
    //menampilkan semua data buku di db
    public void dataBuku(MySQLKoneksi m){
        
        // query sql untuk mencari menampilkan semua data buku di db
        String sql = "SELECT * FROM `buku`";
        
        // lakukan koneksi ke mysql
        Connection koneksi = m.conn;
        
        try {
            Statement statement = koneksi.createStatement();
            // jalankan query
            ResultSet result = statement.executeQuery(sql);

            // membuat header table untuk output
            System.out.println("");
            System.out.println("");
            System.out.println("=========================================================================================");
            System.out.println("                                       DATA BUKU");
            System.out.println("-----------------------------------------------------------------------------------------");
            String header = "%3s %20s %20s %20s %21s";
            System.out.println(String.format(header, "KODE", "JUDUL", "PENERBIT", "PENGARANG", "THN TERBIT"));
            System.out.println("-----------------------------------------------------------------------------------------");
            
            // looping untuk baca data per record
            while (result.next()){
                // baca data buku per record
                String KodeBuku = result.getString("kode_buku");
                String Judul = result.getString("judul");
                String Penerbit = result.getString("penerbit");
                String Pengarang = result.getString("pengarang");
                String TahunTerbit = result.getString("tahun_terbit");
                // tampilkan data buku per record
                String output = "%3s %20s %20s %20s %22s";
                System.out.println(String.format(output, KodeBuku, Judul, Penerbit, Pengarang, TahunTerbit));
            }
            
            System.out.println("=========================================================================================");
            
        } catch (SQLException ex){
            System.out.println("Tampil data buku gagal");
        }
        
    }
    
    //menampilkan semua data peminjaman
    public void dataPeminjaman(MySQLKoneksi m){
        
        // query sql untuk menampilkan data peminjam
        String sql = "SELECT * FROM pinjam INNER JOIN mahasiswa ON pinjam.nim = mahasiswa.nim INNER JOIN buku ON pinjam.kode_buku = buku.kode_buku WHERE status='belum dikembalikan'";
        
        // lakukan koneksi ke mysql
        Connection koneksi = m.conn;
        
        try {
            Statement statement = koneksi.createStatement();
            // jalankan query
            ResultSet result = statement.executeQuery(sql);

            // membuat header table untuk output
            System.out.println("");
            System.out.println("");
            System.out.println("=========================================================================================");
            System.out.println("                                       DATA PEMINJAMAN");
            System.out.println("-----------------------------------------------------------------------------------------");
            String header = "%3s %12s %12s %12s %12s %12s %20s";
            System.out.println(String.format(header, "NIM", "NAMA", "KODE BUKU", "JUDUL", "TGL PINJAM", "TGL KEMBALI", "STATUS"));
            System.out.println("-----------------------------------------------------------------------------------------");
            
            // looping untuk baca data per record
            while (result.next()){
                // baca data buku per record
                String nim = result.getString("nim");
                String nama = result.getString("nama");
                String kode = result.getString("kode_buku");
                String judul = result.getString("judul");
                String tgl_pinjam = result.getString("tgl_pinjam");
                String tgl_kembali = result.getString("tgl_kembali");
                String status = result.getString("status");
                // tampilkan data buku per record
                String output = "%3s %12s %12s %12s %12s %12s %20s";
                System.out.println(String.format(output, nim, nama, kode, judul, tgl_pinjam, tgl_kembali, status));
            }
            
            System.out.println("=========================================================================================");
            
        } catch (SQLException ex){
            System.out.println("Tampil data buku gagal");
        }
        
    }
    
    //menampilkan semua data pengembalian
    public void dataPengembalian(MySQLKoneksi m){
        
        // query sql untuk menampilkan data peminjam
        String sql = "SELECT * FROM pinjam INNER JOIN mahasiswa ON pinjam.nim = mahasiswa.nim INNER JOIN buku ON pinjam.kode_buku = buku.kode_buku INNER JOIN kembali ON pinjam.id_pinjam = kembali.id_pinjam";
        
        // lakukan koneksi ke mysql
        Connection koneksi = m.conn;
        
        try {
            Statement statement = koneksi.createStatement();
            // jalankan query
            ResultSet result = statement.executeQuery(sql);

            // membuat header table untuk output
            System.out.println("");
            System.out.println("");
            System.out.println("=========================================================================================");
            System.out.println("                                       DATA PENGEMBALIAN");
            System.out.println("-----------------------------------------------------------------------------------------");
            String header = "%3s %12s %12s %12s %12s %20s %12s";
            System.out.println(String.format(header, "NIM", "NAMA", "KODE BUKU", "JUDUL", "TGL PINJAM", "TGL DIKEMBALIKAN", "DENDA"));
            System.out.println("-----------------------------------------------------------------------------------------");
            
            // looping untuk baca data per record
            while (result.next()){
                // baca data buku per record
                String nim = result.getString("nim");
                String nama = result.getString("nama");
                String kode = result.getString("kode_buku");
                String judul = result.getString("judul");
                String tgl_pinjam = result.getString("tgl_pinjam");
                String tgl_kembali = result.getString("dikembalikan_tgl");
                String denda = result.getString("denda");
                // tampilkan data buku per record
                String output = "%3s %12s %12s %12s %12s %20s %12s";
                System.out.println(String.format(output, nim, nama, kode, judul, tgl_pinjam, tgl_kembali, denda));
            }
            
            System.out.println("=========================================================================================");
            
        } catch (SQLException ex){
            System.out.println("Tampil data buku gagal");
        }
        
    }
}
