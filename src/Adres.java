public class Adres {

     private String ulica;
     private String kodPocztowy;
     private String numerUlicy;
     private String miasto;


    public Adres(String ulica, String numerUlicy, String kodPocztowy, String miasto) {
        this.miasto = miasto;
        this.numerUlicy = numerUlicy;
        this.kodPocztowy = kodPocztowy;
        this.ulica = ulica;
    }

    public String getUlica() {
        return this.ulica;
    }
    public String getNumerUlicy() {
        return this.numerUlicy;
    }
    public String getKodPocztowy() {
        return this.kodPocztowy;
    }
    public String getMiasto() {
        return this.miasto;
    }

    @Override
    public String toString() {
        return "Adres{" +
                "ulica='" + ulica + '\'' +
                ", kodPocztowy='" + kodPocztowy + '\'' +
                ", numerUlicy='" + numerUlicy + '\'' +
                ", miasto='" + miasto + '\'' +
                '}';
    }
}
