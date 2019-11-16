package model;

/**
 *
 * @author a_lpha
 */
public class Pesanan {

    int idBarang, hargaBarang, jumlahBarang;
    String namaBarang;

    public Pesanan() {
    }

    public Pesanan(int idBarang, int hargaBarang, int jumlahBarang, String namaBarang) {
        this.idBarang = idBarang;
        this.hargaBarang = hargaBarang;
        this.jumlahBarang = jumlahBarang;
        this.namaBarang = namaBarang;
    }

    public int getIdBarang() {
        return idBarang;
    }

    public void setIdBarang(int idBarang) {
        this.idBarang = idBarang;
    }

    public int getHargaBarang() {
        return hargaBarang;
    }

    public void setHargaBarang(int hargaBarang) {
        this.hargaBarang = hargaBarang;
    }

    public int getJumlahBarang() {
        return jumlahBarang;
    }

    public void setJumlahBarang(int jumlahBarang) {
        this.jumlahBarang = jumlahBarang;
    }

    public String getNamaBarang() {
        return namaBarang;
    }

    public void setNamaBarang(String namaBarang) {
        this.namaBarang = namaBarang;
    }

}
