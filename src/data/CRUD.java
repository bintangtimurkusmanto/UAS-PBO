/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import koneksi.MySQLKoneksi;

/**
 *
 * @author Bintang
 */
public abstract class CRUD {
    // atribut buku
    public String kodeBuku;
    public String judul;
    public String penerbit;
    public String pengarang;
    public Integer tahunTerbit;
    
    // atribut mahasiswa
    public int nim;
    public String nama;
    public String prodi;
    public String fakultas;
    public String alamat;
    
    // class abstract select, insert, dan delete
    public abstract void select(MySQLKoneksi m, String judul);
    public abstract void insert(MySQLKoneksi m);
    public abstract void delete(MySQLKoneksi m, Integer kode);
}
