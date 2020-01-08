/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import myinterface.*;
import data.*;
import koneksi.MySQLKoneksi;
import java.sql.*;
import java.util.Scanner;
import java.util.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author Bintang
 */
public class MainClass implements tanggal {

    public static void main(String[] args) throws ParseException {
        MainClass start = new MainClass();
        start.MenuUtama();
    }
    
    // tanggal hari ini
    @Override
    public String getTanggal(){
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Calendar now = Calendar.getInstance();
        return dateFormat.format(now.getTime());
    }
    
    // tanggal pengembalian buku (lama pinjam 7 hari)
    @Override
    public String getTglKembali(){
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Calendar now = Calendar.getInstance();
        now.add(Calendar.DATE,7);
        return dateFormat.format(now.getTime());
    }
    
    // tampilan menu utama
    public void MenuUtama() throws ParseException{
        MySQLKoneksi db1 = new MySQLKoneksi("localhost", "perpustakaan", "root", "");
        String pilih, next;
        Scanner keyboard = new Scanner(System.in);
        
        System.out.println("-----------------------------");
        System.out.println("SISTEM INFORMASI PERPUSTAKAAN");
        System.out.println("-----------------------------");
        System.out.println("Menu :");
        System.out.println("------------ BUKU -----------");
        System.out.println("1.  Cari Buku");
        System.out.println("2.  Tambah Buku");
        System.out.println("3.  Edit Buku");
        System.out.println("4.  Hapus Buku");
        System.out.println("--------- MAHASISWA ---------");
        System.out.println("5.  Cari Mahasiswa");
        System.out.println("6.  Tambah Mahasiswa");
        System.out.println("7.  Edit Mahasiswa");
        System.out.println("8.  Hapus Mahasiswa");
        System.out.println("--------- TRANSAKSI ---------");
        System.out.println("9.  Peminjaman Buku");
        System.out.println("10. Pengembalian Buku");
        System.out.println("11. Laporan");
        System.out.println("------------ EXIT -----------");
        System.out.println("99. EXIT");
        
        System.out.print("Pilih no menu : ");
        pilih = keyboard.nextLine();
        int pilihan = Integer.parseInt(pilih);
        
        // cari buku
        if(pilihan == 1){
            String judul;
            
            System.out.println("-------------------");
            System.out.println("     CARI BUKU     ");
            System.out.println("-------------------");
            System.out.print("Judul       : "); judul = keyboard.nextLine();
            
            buku b1 = new buku();
            b1.select(db1, judul);
        }
        
        // tambah buku
        if(pilihan == 2){
            String judul, pengarang, penerbit;
            int tahun;
            
            System.out.println("-------------------");
            System.out.println("    TAMBAH BUKU    ");
            System.out.println("-------------------");
            
            System.out.print("Judul       : "); judul = keyboard.nextLine();
            System.out.print("Penerbit    : "); penerbit = keyboard.nextLine();
            System.out.print("Pengarang   : "); pengarang = keyboard.nextLine();
            System.out.print("Tahun       : "); tahun = keyboard.nextInt();
            
            buku b1 = new buku(judul, penerbit, pengarang, tahun);
            b1.insert(db1);
        }
        
        // edit buku
        if(pilihan == 3){
            String judul, pengarang, penerbit, kode;
            int tahun;
            
            System.out.println("-------------------");
            System.out.println("     EDIT BUKU     ");
            System.out.println("-------------------");
            
            System.out.print("Kode Buku   : "); kode = keyboard.nextLine();
            int kodeBuku = Integer.parseInt(kode);
            
            System.out.print("Judul       : "); judul = keyboard.nextLine();
            System.out.print("Penerbit    : "); penerbit = keyboard.nextLine();
            System.out.print("Pengarang   : "); pengarang = keyboard.nextLine();
            System.out.print("Tahun       : "); tahun = keyboard.nextInt();
            
            buku b1 = new buku(judul, penerbit, pengarang, tahun);
            b1.update(db1, kodeBuku, judul, penerbit, pengarang, tahun);
        }
        
        // delete buku
        if(pilihan == 4){
            String kode;
            
            System.out.println("-------------------");
            System.out.println("    HAPUS BUKU     ");
            System.out.println("-------------------");
            
            System.out.print("Kode Buku   : "); kode = keyboard.nextLine();
            int kodeBuku = Integer.parseInt(kode);
            
            buku b1 = new buku();
            b1.delete(db1, kodeBuku);            
        }
        
        // cari mahasiswa
        if(pilihan == 5){
            String nama;
            
            System.out.println("-------------------");
            System.out.println("   CARI MAHASISWA  ");
            System.out.println("-------------------");
            System.out.print("Nama  : "); nama = keyboard.nextLine();
            
            mahasiswa m = new mahasiswa();
            m.select(db1, nama);
        }
        
        // tambah mahasiswa
        if(pilihan == 6){
            String nama, prodi, fakultas, alamat;
            
            System.out.println("-------------------");
            System.out.println("  TAMBAH MAHASISWA ");
            System.out.println("-------------------");
            
            System.out.print("Nama      : "); nama = keyboard.nextLine();
            System.out.print("Prodi     : "); prodi = keyboard.nextLine();
            System.out.print("Fakultas  : "); fakultas = keyboard.nextLine();
            System.out.print("Alamat    : "); alamat = keyboard.nextLine();
            
            mahasiswa m = new mahasiswa(nama, prodi, fakultas, alamat);
            m.insert(db1);
        }
        
        // edit mahasiswa
        if(pilihan == 7){
            String nim, nama, prodi, fakultas, alamat;
            
            System.out.println("-------------------");
            System.out.println("   EDIT MAHASISWA  ");
            System.out.println("-------------------");
            
            System.out.print("NIM   : "); nim = keyboard.nextLine();
            int NIM = Integer.parseInt(nim);
            
            System.out.print("Nama      : "); nama = keyboard.nextLine();
            System.out.print("Prodi     : "); prodi = keyboard.nextLine();
            System.out.print("Fakultas  : "); fakultas = keyboard.nextLine();
            System.out.print("Alamat    : "); alamat = keyboard.nextLine();
            
            mahasiswa m = new mahasiswa(nama, prodi, fakultas, alamat);
            m.update(db1, NIM, nama, prodi, fakultas, alamat);
        }
        
        // delete buku
        if(pilihan == 8){
            String nim;
            
            System.out.println("-------------------");
            System.out.println("  HAPUS MAHASISWA  ");
            System.out.println("-------------------");
            
            System.out.print("NIM   : "); nim = keyboard.nextLine();
            int NIM = Integer.parseInt(nim);
            
            mahasiswa m = new mahasiswa();
            m.delete(db1, NIM);            
        }
        
        // peminjaman buku
        if(pilihan == 9){
            String nim, kodeBuku, tglPinjam, tglKembali;
            
            System.out.println("-------------------");
            System.out.println("    PINJAM BUKU    ");
            System.out.println("-------------------");
            
            while(true){
                System.out.print("NIM       : "); nim = keyboard.nextLine();
                // mengecek apakah nim ada di db
                try {
                    Connection koneksi;
                    koneksi = db1.conn;
                    Statement st = koneksi.createStatement(); 
                    ResultSet rs = st.executeQuery("select nim from mahasiswa where nim=" + nim);
                    if (rs.first() == false) {
                        System.out.println("NIM tidak ditemukan!");
                    } else{
                        break;
                    }
                }
                catch (SQLException e) {
                    System.out.println("Error Query tidak bisa! " + e.toString());
                }
            }
            
            while(true){
                System.out.print("Kode Buku : "); kodeBuku = keyboard.nextLine();
                // mengecek apakah kode buku ada di db
                try {
                    Connection koneksi;
                    koneksi = db1.conn;
                    Statement st = koneksi.createStatement(); 
                    ResultSet rs = st.executeQuery("select kode_buku from buku where kode_buku=" + kodeBuku);
                    if (rs.first() == false) {
                        System.out.println("Kode buku tidak ditemukan!");
                    } else{
                        break;
                    }
                }
                catch (SQLException e) {
                    System.out.println("Error Query tidak bisa! " + e.toString());
                }
            }
            
            tglPinjam = getTanggal();
            System.out.println("Tanggal Pinjam    : " + tglPinjam);
            
            tglKembali = getTglKembali();
            System.out.println("Tanggal Kembali   : " + tglKembali);
                       
            pinjam p = new pinjam(nim, kodeBuku, tglPinjam, tglKembali);
            p.input_pinjam(db1);
        }
        
        // pengembalian buku
        if(pilihan == 10){
            String idPinjam, tglKembali;
            int denda;
            
            System.out.println("-------------------");
            System.out.println(" PENGEMBALIAN BUKU ");
            System.out.println("-------------------");
            
            // mengecek apakah id pinjam ada di db
            while(true){
                System.out.print("Id Pinjam : "); idPinjam = keyboard.nextLine();
                try {
                    Connection koneksi;
                    koneksi = db1.conn;
                    Statement st = koneksi.createStatement(); 
                    String sql = "select id_pinjam, tgl_kembali from pinjam where id_pinjam=" + idPinjam;
                    ResultSet rs = st.executeQuery(sql);
                    if (rs.first() == false) {
                        System.out.println("Id pinjam tidak ditemukan!");
                    } else{
                        // menampilkan tgl wajib kembali
                        String tglWjbKembali = rs.getString("tgl_kembali");
                        System.out.println("Tanggal wajib kembali   : " + tglWjbKembali);
                        
                        // menampilkan tgl hari ini
                        tglKembali = getTanggal();
                        System.out.println("Dikembalikan tanggal    : " +tglKembali);
                        
                        // mengecek apakah ada keterlambatan
                        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                        Date tglAwal    = (Date) dateFormat.parse(tglWjbKembali);
                        Date tglAkhir   = (Date) dateFormat.parse(tglKembali);                        
                        
                        if((tglAkhir.getTime() >= tglAwal.getTime())){
                            // menghitung selisih hari
                            long bedaHari = Math.abs(tglAkhir.getTime() - tglAwal.getTime());
                            System.out.println("Terlambat           : " + TimeUnit.MILLISECONDS.toDays(bedaHari) + " hari");
                            
                            //menghitung denda
                            denda = (int) (TimeUnit.MILLISECONDS.toDays(bedaHari) * 1000);
                            System.out.println("Denda               : " + denda);
                        }else{
                            denda = 0;
                        }
                        
                        kembali k = new kembali(idPinjam, denda, tglKembali);
                        k.pengembalian(db1);
                        break;
                    }
                }
                catch (SQLException e) {
                    System.out.println("Error Query tidak bisa! " + e.toString());
                }
            }
        }
        
        // laporan
        if(pilihan == 11){        
            // memanggil class laporan dan menjalankannya
            laporan l = new laporan();
            l.dataMhs(db1);
            l.dataBuku(db1);
            l.dataPeminjaman(db1);
            l.dataPengembalian(db1);          
        }
        
        // keluar dari aplikasi
        if(pilihan == 99){
            System.out.println("-----------------------------");
            System.out.println("     KELUAR DARI APLIKASI    ");
            System.out.println("-----------------------------");
            System.exit(0);
        }
        
        
        
        db1.close();
        System.out.println("");        
        System.out.print("Tekan Enter untuk kembali ke menu.. "); next = keyboard.nextLine();
        MainClass start = new MainClass();
        start.MenuUtama();
    }
    
}