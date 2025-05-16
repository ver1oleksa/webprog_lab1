package com.lab1;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;

public class AppTest {

    private Hospital hospital;
    private Doctor doctor;
    private Patient patient;

    @BeforeEach
    void setUp() {
        hospital = new Hospital();
        patient = new Patient("James Carol", 1);
        doctor = new Doctor("Dr. Kim", "Pediatrician", 3);
        hospital.addDoctor(doctor);
        hospital.addPatient(patient);
    }

    @Test
    public void testAddPatient() {
        assertTrue(hospital.getPatients().contains(patient));
    }

    @Test
    public void testRemovePatient() {
        hospital.removePatient(patient);
        assertFalse(hospital.getPatients().contains(patient));
    }

    @Test
    public void testAddDoctor() {
        assertTrue(hospital.getDoctors().contains(doctor));
    }

    @Test
    public void testRemoveDoctor() {
        hospital.removeDoctor(doctor);
        assertFalse(hospital.getDoctors().contains(doctor));
    }

    @Test
    public void testCreateAppointment() {
        doctor.addAppointment(patient, doctor);
        assertTrue(doctor.getAppointments().containsKey(patient));
        assertTrue(doctor.getAppointments().containsValue(doctor));
    }

    @Test
    public void testUpdatePatient() {
        int index = hospital.getPatients().indexOf(patient);
        patient.setName("John Doe");
        hospital.updatePatient(index, patient);
        assertTrue(patient.getName().equals("John Doe"));
    }

    @Test
    public void testUpdateDoctor() {
        int index = hospital.getDoctors().indexOf(doctor);
        doctor.setName("Jane Doe");
        hospital.updateDoctor(index, doctor);
        assertTrue(doctor.getName().equals("Jane Doe"));
    }

    @Test
    public void testAddCard() {
        assertTrue(hospital.getCards().containsKey(patient.getID()));
    }

}
