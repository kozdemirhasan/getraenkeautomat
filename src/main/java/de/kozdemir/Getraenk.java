package de.kozdemir;

import java.util.Objects;

/**
 * Created By hasan
 * Date : 15.06.2023
 */
public class Getraenk {
    private int id;
    private String name;
    private int preis;

    public Getraenk(int id) {
        this.id = id;
    }

    public Getraenk(int id, String name, int preis) {
        this.id = id;
        this.name = name;
        this.preis = preis;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getPreis() {
        return preis;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Getraenk getraenk = (Getraenk) o;
        return id == getraenk.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
