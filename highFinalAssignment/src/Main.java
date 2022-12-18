import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class Main {

    public static Scanner input = new Scanner(System.in);
    public static Oyuncu loginOlmusKullanici;
    public static List<Oyuncu> oyuncular = new ArrayList<>();
    public static List<Skor> skorlar = new ArrayList<>();

    public static List<Soru> dortHarfSorular = new ArrayList<>();
    public static List<Soru> besHarfSorular = new ArrayList<>();
    public static List<Soru> altiHarfSorular = new ArrayList<>();
    public static List<Soru> yediHarfSorular = new ArrayList<>();
    public static List<Soru> sekizHarfSorular = new ArrayList<>();
    public static List<Soru> dokuzHarfSorular = new ArrayList<>();
    public static List<Soru> onHarfSorular = new ArrayList<>();

    public static void main(String[] args) {

        System.out.println("Kelime Oyununa Hoşgeldiniz");
        System.out.println("---------------------------");

        sorulariKlasordenOku();

        Scanner input = new Scanner(System.in);

        while (true) {

            loginOlmusKullanici = null;
            System.out.println("Oyuncu tanımalamak için => 1");
            System.out.println("Giriş yapmak için => 2");
            System.out.println("Çıkış => 0");

            String girdi = input.next();

            switch (girdi) {
                case "1":
                    System.out.println("Adınızı giriniz:");
                    String ad = input.next();

                    System.out.println("Soyadınızı giriniz:");
                    String soyad = input.next();

                    System.out.println("E Mail adersinizi giriniz:");
                    String email = input.next();

                    System.out.println("Kullanıcı/Oyuncu adınızı giriniz:");
                    String girilenOyuncuAdi = input.next();

                    System.out.println("Şifrenizi giriniz:");
                    String girilenSifre = input.next();

                    if (!oyuncuTanimla(ad, soyad, email, girilenOyuncuAdi, girilenSifre)) {
                        continue;
                    } else {
                        System.out.println("Giris başarıyla Yapıldı");
                        System.out.println("---------------------------");
                    }
                    break;
                case "2":
                    System.out.println("Kullanıcı/Oyuncu adınızı giriniz:");
                    String girisOyuncuAdi = input.next();

                    System.out.println("Şifrenizi giriniz:");
                    String girisSifre = input.next();
                    if (oyuncular.size() > 0) {

                        for (Oyuncu oyuncu : oyuncular) {
                            if (oyuncu.getOyuncuAdi().equals(girisOyuncuAdi)) {
                                if (oyuncu.getSifre().equals(girisSifre)) {
                                    loginOlmusKullanici = oyuncu;
                                    System.out.println("Giris Başarıyla Yapıldı");
                                    System.out.println("---------------------------");

                                } else {
                                    System.out.println("Giris Yapılamadı, kullanıcı adı veya parola hatalı");
                                    continue;
                                }
                            } else {
                                System.out.println("Giris Yapılamadı, kullanıcı adı veya parola hatalı");
                                continue;
                            }
                        }
                    } else {
                        System.out.println("Giris Yapılamadı,sisteme kayıtlı oyuncu buluanamdı");
                        continue;
                    }

                    break;
                case "0":
                    System.out.println("Kelime oyunundan çıkış yapıldı");
                    return;
                default:
                    System.out.println("Lütfen tanımlı bir sayı giriniz");
                    continue;
            }

            if (loginOlmusKullanici != null) {
                System.out.println("Oyun başlıyor");

                double kelimeUzunlugu = 4;
                while (kelimeUzunlugu < 11) {
                    switch ((int) kelimeUzunlugu) {
                        case 4:
                            if (!dortHarfliSoruSor()) {
                                kelimeUzunlugu = 99;
                                continue;
                            }
                            break;
                        case 5:
                            if (!besHarfliSoruSor()) {
                                kelimeUzunlugu = 99;
                                continue;
                            }
                            break;
                        case 6:
                            if (!altiHarfliSoruSor()) {
                                kelimeUzunlugu = 99;
                                continue;
                            }
                            break;
                        case 7:
                            if (!yediHarfliSoruSor()) {
                                kelimeUzunlugu = 99;
                                continue;
                            }
                            break;
                        case 8:
                            if (!sekizHarfliSoruSor()) {
                                kelimeUzunlugu = 99;
                                continue;
                            }
                            break;
                        case 9:
                            if (!dokuzHarfliSoruSor()) {
                                kelimeUzunlugu = 99;
                                continue;
                            }
                            break;
                        case 10:
                            if (!onHarfliSoruSor()) {
                                kelimeUzunlugu = 99;
                                continue;
                            }
                            break;
                        default:
                            break;
                    }
                    kelimeUzunlugu += 0.5;
                }
                if (kelimeUzunlugu == 11) {
                    System.out.println("--TEBRİKLER OYUN BİTTİ--");
                    System.out.println("Total Puanınız: " + loginOlmusKullanici.getTotalPuan());
                    skorlar.add(new Skor(loginOlmusKullanici.getTotalPuan(), loginOlmusKullanici));
                    System.out.println("Genel Skorlar;");
                    for (Skor skor : skorlar) {
                        System.out.println("Oyuncu : " + skor.getOyuncu().getOyuncuAdi() + ", skoru: " + skor.getSkor());
                    }
                    System.out.println("---------------------------");
                    System.out.println("---------------------------");
                }

            }

        }
    }

    private static boolean oyuncuTanimla(String ad, String soyad, String email, String oyuncuAdi, String sifre) {
        if (ad == null) {
            System.out.println("Ad boş olamaz");
            return false;
        }
        if (soyad == null) {
            System.out.println("soyad boş olamaz");
            return false;
        }
        if (email == null) {
            System.out.println("Email olamaz");
            return false;
        }
        if (oyuncuAdi == null) {
            System.out.println("Oyuncu Adi boş olamaz");
            return false;
        }
        if (sifre == null) {
            System.out.println("Şifre boş olamaz");
            return false;
        }

        for (Oyuncu oyuncu : oyuncular) {
            if (oyuncu.getOyuncuAdi().equals(oyuncuAdi)) {
                System.out.println("Bu oyuncu adı daha önce tanımlanmış lütfen tanımsız bir oyuncu adı giriniz");
                return false;
            }
        }
        Oyuncu oyuncu = new Oyuncu(ad, soyad, email, oyuncuAdi, sifre);
        oyuncular.add(oyuncu);
        System.out.println("Oyuncu başarıyla tanımalandı");
        loginOlmusKullanici = oyuncular.get(oyuncular.size() - 1);
        return true;
    }

    private static boolean dortHarfliSoruSor() {
        String[] cevap = new String[4];
        int alinanHarfSayisi = 0;
        cevap[0] = "_";
        cevap[1] = "_";
        cevap[2] = "_";
        cevap[3] = "_";
        System.out.println("Dört harfli soru soruluyor");
        System.out.println("---------------------------");
        Soru soru = dortHarfSorular.get((int) (Math.random() * 10) + 1);
        System.out.println(soru.getMetin());
        while (alinanHarfSayisi < 4) {

            System.out.println(cevap[0] + " " + cevap[1] + " " + cevap[2] + " " + cevap[3] + " ");
            System.out.println("Cevabınızı yazabilir, 'h' tuşu ile harf basabilir, 'q' tuşu ile çıkış yapabilirsiniz: ");
            String cvp = input.next();
            Locale trlocale = new Locale("tr-TR");
            cvp = cvp.toLowerCase(trlocale);
            if (cvp.equals("h")) {
                alinanHarfSayisi++;
                while (true) {
                    int random = (int) (Math.random() * 4);
                    if (cevap[random].equals("_")) {
                        cevap[random] = soru.getCevap().split("")[random];
                        if (alinanHarfSayisi == 4) {
                            System.out.println(cevap[0] + " " + cevap[1] + " " + cevap[2] + " " + cevap[3] + " ");
                            System.out.println("Kelime Bilinemedi, Yeni kelimeye geçiliyor");
                        }
                        break;
                    } else {
                        continue;
                    }
                }
            } else if (cvp.equals("q")) {
                return false;
            } else {
                if (cvp.equals(soru.getCevap())) {
                    loginOlmusKullanici.setTotalPuan(soru.getCevap().length() - alinanHarfSayisi);
                    System.out.println("TEBRİKLERR");
                    System.out.println("Şuanki Total Puanınız: " + loginOlmusKullanici.getTotalPuan());
                    System.out.println("------------");
                    System.out.println("Yeni Soruya Geçiş Yapılıyor");
                    break;
                } else {
                    System.out.println("Cevap yanlış,Tekrar Deneyiniz");
                }
            }
        }
        return true;
    }

    private static boolean besHarfliSoruSor() {
        String[] cevap = new String[5];
        int alinanHarfSayisi = 0;
        cevap[0] = "_";
        cevap[1] = "_";
        cevap[2] = "_";
        cevap[3] = "_";
        cevap[4] = "_";
        System.out.println("Beş harfli soru soruluyor");
        System.out.println("---------------------------");
        Soru soru = besHarfSorular.get((int) (Math.random() * 10) + 1);
        System.out.println(soru.getMetin());
        while (alinanHarfSayisi < 5) {

            System.out.println(cevap[0] + " " + cevap[1] + " " + cevap[2] + " " + cevap[3] + " " + cevap[4]);
            System.out.println("Cevabınızı yazabilir, 'h' tuşu ile harf basabilir, 'q' tuşu ile çıkış yapabilirsiniz: ");
            String cvp = input.next();
            Locale trlocale = new Locale("tr-TR");
            cvp = cvp.toLowerCase(trlocale);
            if (cvp.equals("h")) {
                alinanHarfSayisi++;
                while (true) {
                    int random = (int) (Math.random() * 5);
                    if (cevap[random].equals("_")) {
                        cevap[random] = soru.getCevap().split("")[random];
                        if (alinanHarfSayisi == 5) {
                            System.out.println(cevap[0] + " " + cevap[1] + " " + cevap[2] + " " + cevap[3] + " " + cevap[4]);
                            System.out.println("Kelime Bilinemedi, Yeni kelimeye geçiliyor");
                        }
                        break;
                    } else {
                        continue;
                    }
                }
            } else if (cvp.equals("q")) {
                return false;
            } else {
                if (cvp.equals(soru.getCevap())) {
                    loginOlmusKullanici.setTotalPuan(soru.getCevap().length() - alinanHarfSayisi);
                    System.out.println("TEBRİKLERR");
                    System.out.println("Şuanki Total Puanınız: " + loginOlmusKullanici.getTotalPuan());
                    System.out.println("------------");
                    System.out.println("Yeni Soruya Geçiş Yapılıyor");
                    break;
                } else {
                    System.out.println("Cevap yanlış,Tekrar Deneyiniz");
                }
            }
        }
        return true;
    }

    private static boolean altiHarfliSoruSor() {
        String[] cevap = new String[6];
        int alinanHarfSayisi = 0;
        cevap[0] = "_";
        cevap[1] = "_";
        cevap[2] = "_";
        cevap[3] = "_";
        cevap[4] = "_";
        cevap[5] = "_";
        System.out.println("Altı harfli soru soruluyor");
        System.out.println("---------------------------");
        Soru soru = altiHarfSorular.get((int) (Math.random() * 10) + 1);
        System.out.println(soru.getMetin());
        while (alinanHarfSayisi < 6) {

            System.out.println(cevap[0] + " " + cevap[1] + " " + cevap[2] + " " + cevap[3] + " " + cevap[4] + " " + cevap[5]);
            System.out.println("Cevabınızı yazabilir, 'h' tuşu ile harf basabilir, 'q' tuşu ile çıkış yapabilirsiniz: ");
            String cvp = input.next();
            Locale trlocale = new Locale("tr-TR");
            cvp = cvp.toLowerCase(trlocale);
            if (cvp.equals("h")) {
                alinanHarfSayisi++;
                while (true) {
                    int random = (int) (Math.random() * 6);
                    if (cevap[random].equals("_")) {
                        cevap[random] = soru.getCevap().split("")[random];
                        if (alinanHarfSayisi == 6) {
                            System.out.println(cevap[0] + " " + cevap[1] + " " + cevap[2] + " " + cevap[3] + " " + cevap[4] + " " + cevap[5]);
                            System.out.println("Kelime Bilinemedi, Yeni kelimeye geçiliyor");
                        }
                        break;
                    } else {
                        continue;
                    }
                }
            } else if (cvp.equals("q")) {
                return false;
            } else {
                if (cvp.equals(soru.getCevap())) {
                    loginOlmusKullanici.setTotalPuan(soru.getCevap().length() - alinanHarfSayisi);
                    System.out.println("TEBRİKLERR");
                    System.out.println("Şuanki Total Puanınız: " + loginOlmusKullanici.getTotalPuan());
                    System.out.println("------------");
                    System.out.println("Yeni Soruya Geçiş Yapılıyor");
                    break;
                } else {
                    System.out.println("Cevap yanlış,Tekrar Deneyiniz");
                }
            }
        }
        return true;
    }

    private static boolean yediHarfliSoruSor() {
        String[] cevap = new String[7];
        int alinanHarfSayisi = 0;
        cevap[0] = "_";
        cevap[1] = "_";
        cevap[2] = "_";
        cevap[3] = "_";
        cevap[4] = "_";
        cevap[5] = "_";
        cevap[6] = "_";
        System.out.println("Yedi harfli soru soruluyor");
        System.out.println("---------------------------");
        Soru soru = yediHarfSorular.get((int) (Math.random() * 10) + 1);
        System.out.println(soru.getMetin());
        while (alinanHarfSayisi < 7) {

            System.out.println(cevap[0] + " " + cevap[1] + " " + cevap[2] + " " + cevap[3] + " " + cevap[4] + " " + cevap[5] + " " + cevap[6]);
            System.out.println("Cevabınızı yazabilir, 'h' tuşu ile harf basabilir, 'q' tuşu ile çıkış yapabilirsiniz: ");
            String cvp = input.next();
            Locale trlocale = new Locale("tr-TR");
            cvp = cvp.toLowerCase(trlocale);
            if (cvp.equals("h")) {
                alinanHarfSayisi++;
                while (true) {
                    int random = (int) (Math.random() * 7);
                    if (cevap[random].equals("_")) {
                        cevap[random] = soru.getCevap().split("")[random];
                        if (alinanHarfSayisi == 7) {
                            System.out.println(cevap[0] + " " + cevap[1] + " " + cevap[2] + " " + cevap[3] + " " + cevap[4] + " " + cevap[5] + " " + cevap[6]);
                            System.out.println("Kelime Bilinemedi, Yeni kelimeye geçiliyor");
                        }
                        break;
                    } else {
                        continue;
                    }
                }
            } else if (cvp.equals("q")) {
                return false;
            } else {
                if (cvp.equals(soru.getCevap())) {
                    loginOlmusKullanici.setTotalPuan(soru.getCevap().length() - alinanHarfSayisi);
                    System.out.println("TEBRİKLERR");
                    System.out.println("Şuanki Total Puanınız: " + loginOlmusKullanici.getTotalPuan());
                    System.out.println("------------");
                    System.out.println("Yeni Soruya Geçiş Yapılıyor");
                    break;
                } else {
                    System.out.println("Cevap yanlış,Tekrar Deneyiniz");
                }
            }
        }
        return true;
    }

    private static boolean sekizHarfliSoruSor() {
        String[] cevap = new String[8];
        int alinanHarfSayisi = 0;
        cevap[0] = "_";
        cevap[1] = "_";
        cevap[2] = "_";
        cevap[3] = "_";
        cevap[4] = "_";
        cevap[5] = "_";
        cevap[6] = "_";
        cevap[7] = "_";
        System.out.println("Sekiz harfli soru soruluyor");
        System.out.println("---------------------------");
        Soru soru = sekizHarfSorular.get((int) (Math.random() * 10) + 1);
        System.out.println(soru.getMetin());
        while (alinanHarfSayisi < 8) {

            System.out.println(cevap[0] + " " + cevap[1] + " " + cevap[2] + " " + cevap[3] + " " + cevap[4] + " " + cevap[5] + " " + cevap[6] + " " + cevap[7]);
            System.out.println("Cevabınızı yazabilir, 'h' tuşu ile harf basabilir, 'q' tuşu ile çıkış yapabilirsiniz: ");
            String cvp = input.next();
            Locale trlocale = new Locale("tr-TR");
            cvp = cvp.toLowerCase(trlocale);
            if (cvp.equals("h")) {
                alinanHarfSayisi++;
                while (true) {
                    int random = (int) (Math.random() * 8);
                    if (cevap[random].equals("_")) {
                        cevap[random] = soru.getCevap().split("")[random];
                        if (alinanHarfSayisi == 8) {
                            System.out.println(cevap[0] + " " + cevap[1] + " " + cevap[2] + " " + cevap[3] + " " + cevap[4] + " " + cevap[5] + " " + cevap[6] + " " + cevap[7]);
                            System.out.println("Kelime Bilinemedi, Yeni kelimeye geçiliyor");
                        }
                        break;
                    } else {
                        continue;
                    }
                }
            } else if (cvp.equals("q")) {
                return false;
            } else {
                if (cvp.equals(soru.getCevap())) {
                    loginOlmusKullanici.setTotalPuan(soru.getCevap().length() - alinanHarfSayisi);
                    System.out.println("TEBRİKLERR");
                    System.out.println("Şuanki Total Puanınız: " + loginOlmusKullanici.getTotalPuan());
                    System.out.println("------------");
                    System.out.println("Yeni Soruya Geçiş Yapılıyor");
                    break;
                } else {
                    System.out.println("Cevap yanlış,Tekrar Deneyiniz");
                }
            }
        }
        return true;
    }

    private static boolean dokuzHarfliSoruSor() {
        String[] cevap = new String[9];
        int alinanHarfSayisi = 0;
        cevap[0] = "_";
        cevap[1] = "_";
        cevap[2] = "_";
        cevap[3] = "_";
        cevap[4] = "_";
        cevap[5] = "_";
        cevap[6] = "_";
        cevap[7] = "_";
        cevap[8] = "_";
        System.out.println("Dokuz harfli soru soruluyor");
        System.out.println("---------------------------");
        Soru soru = dokuzHarfSorular.get((int) (Math.random() * 10) + 1);
        System.out.println(soru.getMetin());
        while (alinanHarfSayisi < 9) {

            System.out.println(cevap[0] + " " + cevap[1] + " " + cevap[2] + " " + cevap[3] + " " + cevap[4] + " " + cevap[5] + " " + cevap[6] + " " + cevap[7] + " " + cevap[8]);
            System.out.println("Cevabınızı yazabilir, 'h' tuşu ile harf basabilir, 'q' tuşu ile çıkış yapabilirsiniz: ");
            String cvp = input.next();
            Locale trlocale = new Locale("tr-TR");
            cvp = cvp.toLowerCase(trlocale);
            if (cvp.equals("h")) {
                alinanHarfSayisi++;
                while (true) {
                    int random = (int) (Math.random() * 9);
                    if (cevap[random].equals("_")) {
                        cevap[random] = soru.getCevap().split("")[random];
                        if (alinanHarfSayisi == 9) {
                            System.out.println(cevap[0] + " " + cevap[1] + " " + cevap[2] + " " + cevap[3] + " " + cevap[4] + " " + cevap[5] + " " + cevap[6] + " " + cevap[7] + " " + cevap[8]);
                            System.out.println("Kelime Bilinemedi, Yeni kelimeye geçiliyor");
                        }
                        break;
                    } else {
                        continue;
                    }
                }
            } else if (cvp.equals("q")) {
                return false;
            } else {
                if (cvp.equals(soru.getCevap())) {
                    loginOlmusKullanici.setTotalPuan(soru.getCevap().length() - alinanHarfSayisi);
                    System.out.println("TEBRİKLERR");
                    System.out.println("Şuanki Total Puanınız: " + loginOlmusKullanici.getTotalPuan());
                    System.out.println("------------");
                    System.out.println("Yeni Soruya Geçiş Yapılıyor");
                    break;
                } else {
                    System.out.println("Cevap yanlış,Tekrar Deneyiniz");
                }
            }
        }
        return true;
    }

    private static boolean onHarfliSoruSor() {
        String[] cevap = new String[10];
        int alinanHarfSayisi = 0;
        cevap[0] = "_";
        cevap[1] = "_";
        cevap[2] = "_";
        cevap[3] = "_";
        cevap[4] = "_";
        cevap[5] = "_";
        cevap[6] = "_";
        cevap[7] = "_";
        cevap[8] = "_";
        cevap[9] = "_";
        System.out.println("On harfli soru soruluyor");
        System.out.println("---------------------------");
        Soru soru = onHarfSorular.get((int) (Math.random() * 10) + 1);
        System.out.println(soru.getMetin());
        while (alinanHarfSayisi < 10) {

            System.out.println(cevap[0] + " " + cevap[1] + " " + cevap[2] + " " + cevap[3] + " " + cevap[4] + " " + cevap[5] + " " + cevap[6] + " " + cevap[7] + " " + cevap[8] + " " + cevap[9]);
            System.out.println("Cevabınızı yazabilir, 'h' tuşu ile harf basabilir, 'q' tuşu ile çıkış yapabilirsiniz: ");
            String cvp = input.next();
            Locale trlocale = new Locale("tr-TR");
            cvp = cvp.toLowerCase(trlocale);
            if (cvp.equals("h")) {
                alinanHarfSayisi++;
                while (true) {
                    int random = (int) (Math.random() * 10);
                    if (cevap[random].equals("_")) {
                        cevap[random] = soru.getCevap().split("")[random];
                        if (alinanHarfSayisi == 10) {
                            System.out.println(cevap[0] + " " + cevap[1] + " " + cevap[2] + " " + cevap[3] + " " + cevap[4] + " " + cevap[5] + " " + cevap[6] + " " + cevap[7] + " " + cevap[8] + " " + cevap[9]);
                            System.out.println("Kelime Bilinemedi, Yeni kelimeye geçiliyor");
                        }
                        break;
                    } else {
                        continue;
                    }
                }
            } else if (cvp.equals("q")) {
                return false;
            } else {
                if (cvp.equals(soru.getCevap())) {
                    loginOlmusKullanici.setTotalPuan(soru.getCevap().length() - alinanHarfSayisi);
                    System.out.println("TEBRİKLERR");
                    System.out.println("Şuanki Total Puanınız: " + loginOlmusKullanici.getTotalPuan());
                    System.out.println("------------");
                    System.out.println("Yeni Soruya Geçiş Yapılıyor");
                    break;
                } else {
                    System.out.println("Cevap yanlış,Tekrar Deneyiniz");
                }
            }
        }
        return true;
    }

    private static void sorulariKlasordenOku() {
        Locale trlocale = new Locale("tr-TR");

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("sorular/dort.txt"))) {

            String line = null;

            String specialCharacter = "-";

            while ((line = bufferedReader.readLine()) != null) {
                String[] values = line.split(specialCharacter);
                Soru soru = new Soru(values[0], values[1].toLowerCase(trlocale));
                dortHarfSorular.add(soru);
            }
        } catch (FileNotFoundException fileNotFoundException) {
            fileNotFoundException.printStackTrace();
        } catch (Exception exception) {
            exception.printStackTrace();
        }

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("sorular/bes.txt"))) {

            String line = null;

            String specialCharacter = "-";

            while ((line = bufferedReader.readLine()) != null) {
                String[] values = line.split(specialCharacter);
                Soru soru = new Soru(values[0], values[1].toLowerCase(trlocale));
                besHarfSorular.add(soru);
            }
        } catch (FileNotFoundException fileNotFoundException) {
            fileNotFoundException.printStackTrace();
        } catch (Exception exception) {
            exception.printStackTrace();
        }

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("sorular/alti.txt"))) {

            String line = null;

            String specialCharacter = "-";

            while ((line = bufferedReader.readLine()) != null) {
                String[] values = line.split(specialCharacter);
                Soru soru = new Soru(values[0], values[1].toLowerCase(trlocale));
                altiHarfSorular.add(soru);
            }
        } catch (FileNotFoundException fileNotFoundException) {
            fileNotFoundException.printStackTrace();
        } catch (Exception exception) {
            exception.printStackTrace();
        }

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("sorular/yedi.txt"))) {

            String line = null;

            String specialCharacter = "-";

            while ((line = bufferedReader.readLine()) != null) {
                String[] values = line.split(specialCharacter);
                Soru soru = new Soru(values[0], values[1].toLowerCase(trlocale));
                yediHarfSorular.add(soru);
            }
        } catch (FileNotFoundException fileNotFoundException) {
            fileNotFoundException.printStackTrace();
        } catch (Exception exception) {
            exception.printStackTrace();
        }

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("sorular/sekiz.txt"))) {

            String line = null;

            String specialCharacter = "-";

            while ((line = bufferedReader.readLine()) != null) {
                String[] values = line.split(specialCharacter);
                Soru soru = new Soru(values[0], values[1].toLowerCase(trlocale));
                sekizHarfSorular.add(soru);
            }
        } catch (FileNotFoundException fileNotFoundException) {
            fileNotFoundException.printStackTrace();
        } catch (Exception exception) {
            exception.printStackTrace();
        }

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("sorular/dokuz.txt"))) {

            String line = null;

            String specialCharacter = "-";

            while ((line = bufferedReader.readLine()) != null) {
                String[] values = line.split(specialCharacter);
                Soru soru = new Soru(values[0], values[1].toLowerCase(trlocale));
                dokuzHarfSorular.add(soru);
            }
        } catch (FileNotFoundException fileNotFoundException) {
            fileNotFoundException.printStackTrace();
        } catch (Exception exception) {
            exception.printStackTrace();
        }

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("sorular/on.txt"))) {

            String line = null;

            String specialCharacter = "-";

            while ((line = bufferedReader.readLine()) != null) {
                String[] values = line.split(specialCharacter);
                Soru soru = new Soru(values[0], values[1].toLowerCase(trlocale));
                onHarfSorular.add(soru);
            }
        } catch (FileNotFoundException fileNotFoundException) {
            fileNotFoundException.printStackTrace();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}

