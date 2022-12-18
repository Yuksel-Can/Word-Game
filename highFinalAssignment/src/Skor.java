public class Skor {

    private Integer skor;
    private Oyuncu oyuncu;

    public Skor(Integer skor, Oyuncu oyuncu) {
        this.skor = skor;
        this.oyuncu = oyuncu;
    }

    public Integer getSkor() {
        return skor;
    }

    public void setSkor(Integer skor) {
        this.skor = skor;
    }

    public Oyuncu getOyuncu() {
        return oyuncu;
    }

    public void setOyuncu(Oyuncu oyuncu) {
        this.oyuncu = oyuncu;
    }

    @Override
    public String toString() {
        return "Oyuncu Adı: " + oyuncu.getOyuncuAdi() + ", skoru: " + skor;
    }
}
