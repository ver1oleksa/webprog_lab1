package com.lab1;

import java.io.IOException;
import java.util.*;

public class Hospital {
    private List<Doctor> doctors = new ArrayList<>();
    private List<Patient> patients = new ArrayList<>();
    private static Map<Integer, Patient> cards = new HashMap<>();

    /*
     * public Hospital(FileWriterClass writer, FileReaderClass reader) {
     * this.writer = writer;
     * this.reader = reader;
     * }
     */

    public List<Doctor> getDoctors() {
        return doctors;
    }

    public List<Patient> getPatients() {
        return patients;
    }

    public void addDoctor(Doctor doctor) {
        doctors.add(doctor);
    }

    public void addPatient(Patient patient) {
        patients.add(patient);
        addCard(patient.getID(), patient);
    }

    public void removeDoctor(Doctor doctor) {
        doctors.remove(doctor);
    }

    public void removePatient(Patient patient) {
        patients.remove(patient);
    }

    public void updateDoctor(int index, Doctor doctor) {
        doctors.set(index, doctor);
    }

    public void updatePatient(int index, Patient patient) {
        patients.set(index, patient);
    }

    public Map<Integer, Patient> getCards() {
        return cards;
    }

    public void addCard(Integer integer, Patient patient) {
        cards.put(integer, patient);
    }

    public static void printCards() {
        for (var entry : cards.entrySet()) {
            System.out.println("  " + entry.getKey() + ": " + entry.getValue().getName());
        }
    }

    public void exportData(String filename, Comparator<Doctor> comparatorDoc, Comparator<Patient> comparatorPat)
            throws IOException {
        List<Doctor> sortedDoctors = new ArrayList<>(doctors);
        List<Patient> sortedPatients = new ArrayList<>(patients);
        sortedDoctors.sort(comparatorDoc);
        sortedPatients.sort(comparatorPat);
        StringBuilder sb = new StringBuilder();

        for (Doctor d : sortedDoctors) {
            sb.append("Doctor: ").append(d.getName()).append("; ").append(d.getField()).append("; ").append(d.getID())
                    .append("\n");
            for (var entry : d.getAppointments().entrySet()) {
                sb.append("   ").append(entry.getKey().getName()).append("; ").append(entry.getKey().getID())
                        .append(" - ")
                        .append(entry.getValue().getName()).append("; ").append(entry.getValue().getField())
                        .append("; ")
                        .append(entry.getValue().getID()).append("\n");
            }
        }
        for (Patient p : sortedPatients) {
            sb.append("Patient: ").append(p.getName()).append("; ").append(p.getID()).append("\n");
        }
        FileWriterClass writer = new FileWriterClass();
        writer.write(filename, sb.toString());
    }

    public void importData(String filename) throws IOException {
        FileReaderClass reader = new FileReaderClass();
        List<String> lines = reader.readLines(filename);
        Doctor currentDoctor = null;
        Patient currentPatient = null;

        for (String line : lines) {
            if (line.startsWith("Doctor: ")) {
                String[] nameFieldID = line.substring(8).split("; ");
                currentDoctor = new Doctor(nameFieldID[0], nameFieldID[1], Integer.parseInt(nameFieldID[2]));
                addDoctor(currentDoctor);
            } else if (line.startsWith("   ") && currentDoctor != null) {
                String[] parts = line.trim().split(" - ");
                String[] appointPatientInfo = parts[0].trim().split("; ");
                Patient p = new Patient(appointPatientInfo[0], Integer.parseInt(appointPatientInfo[1]));
                currentDoctor.addAppointment(p, currentDoctor);
            } else {
                String[] nameID = line.substring(9).split("; ");
                currentPatient = new Patient(nameID[0], Integer.parseInt(nameID[1]));
                addPatient(currentPatient);
                ;
            }
        }
    }

    public void findByField(String field) {
        for (Doctor d : doctors) {
            if (d.getField().equalsIgnoreCase(field))
                System.out.println("Doctor: " + d.getName());
        }
    }

    public void printAll() {
        for (Doctor d : doctors) {
            System.out.println("Doctor: " + d.getName() + "; " + d.getField());

        }
        printCards();
        Doctor.printAppointments();
    }
}
