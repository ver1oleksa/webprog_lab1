package com.lab1;

import java.util.Objects;

public class Patient {
    private String name;
    private int id;

    public Patient(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public int getID() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Patient))
            return false;
        Patient that = (Patient) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
