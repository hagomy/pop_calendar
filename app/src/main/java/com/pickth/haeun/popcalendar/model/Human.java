package com.pickth.haeun.popcalendar.model;

public class Human {
    public String name;
    public String tel;

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Human && ((Human) obj).name.equals(name) && ((Human) obj).tel.equals(tel);
    }
}
