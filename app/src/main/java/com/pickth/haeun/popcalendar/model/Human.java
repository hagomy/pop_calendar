package com.pickth.haeun.popcalendar.model;

public class Human {
    String name = "";
    String tel = "";

    public Human(String tel) {
        this.tel = tel;
    }

    public Human(String name, String tel) {
        this.name = name;
        this.tel = tel;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Human && ((Human) obj).tel.equals(tel);
    }
}
