public class Soru {

    private String metin;
    private String cevap;
    private Integer puan;

    public Soru() {

    }

    public Soru(String metin, String cevap) {
        this.metin = metin;
        this.cevap = cevap;
        this.puan = cevap.length() * 10;
    }

    public String getMetin() {
        return metin;
    }

    public void setMetin(String metin) {
        this.metin = metin;
    }


    public String getCevap() {
        return cevap;
    }

    public void setCevap(String cevap) {
        this.cevap = cevap;
    }

    public void setPuan(Integer puan) {
        this.puan = puan;
    }
}
