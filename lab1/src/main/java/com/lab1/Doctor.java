package com.lab1;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Doctor {
    private String name, field;
    private int id;
    private static Map<Patient, Doctor> appointments = new HashMap<>();

    public Doctor(String name, String field, int id) {
        this.name = name;
        this.field = field;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getID() {
        return id;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public Map<Patient, Doctor> getAppointments() {
        return appointments;
    }

    public void addAppointment(Patient patient, Doctor doctor) {
        appointments.put(patient, doctor);
    }

    public static void printAppointments() {
        for (var entry : appointments.entrySet()) {
            System.out.println("  " + entry.getKey().getName() + ": " + entry.getValue().getName());
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Doctor))
            return false;
        Doctor that = (Doctor) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
