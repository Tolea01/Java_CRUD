package org.example.pojo;

public class Tara {
    private Long id;
    private String nume;
    private String cod;
    private String limbaOficiala;
    private String moneda;

    public Tara() {

    }

    public Tara(Long id, String nume, String cod, String limbaOficiala, String moneda) {
        this.id = id;
        this.nume = nume;
        this.cod = cod;
        this.limbaOficiala = limbaOficiala;
        this.moneda = moneda;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getCod() {
        return cod;
    }

    public void setCod(String cod) {
        this.cod = cod;
    }

    public String getLimbaOficiala() {
        return limbaOficiala;
    }

    public void setLimbaOficiala(String limbaOficiala) {
        this.limbaOficiala = limbaOficiala;
    }

    public String getMoneda() {
        return moneda;
    }

    public void setMoneda(String moneda) {
        this.moneda = moneda;
    }
}

