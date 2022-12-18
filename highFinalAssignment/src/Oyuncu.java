public class Oyuncu extends Kullanici {

    private String oyuncuAdi;
    private String sifre;
    private Integer totalPuan = 0;

    public Oyuncu() {
        super();
    }

    public Oyuncu(String adi, String soyadi, String email, String kullaniciAdi, String sifre) {
        super(adi, soyadi, email);
        this.oyuncuAdi = kullaniciAdi;
        this.sifre = sifre;
        this.totalPuan = 0;
    }

    public String getOyuncuAdi() {
        return oyuncuAdi;
    }

    public void setKOyuncuAdi(String oyuncuAdi) {
        this.oyuncuAdi = oyuncuAdi;
    }

    public String getSifre() {
        return sifre;
    }

    public void setSifre(String sifre) {
        this.sifre = sifre;
    }

    public void setTotalPuan(Integer bilinenKelimeSayisi) {
        this.totalPuan += bilinenKelimeSayisi * 10;
    }

    public Integer getTotalPuan() {
        return totalPuan;
    }

    @Override
    public String toString() {
        return "oyuncuAdi='" + oyuncuAdi + ", sifre='" + sifre + ", totalPuan=" + totalPuan;
    }
}
