package com.lab1;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class App {
    private static final Scanner scanner = new Scanner(System.in);

    private static final Hospital hospital = new Hospital();

    private static int doctorIDCounter = 0, patientIDCounter = 0;

    public static void main(String[] args) throws IOException {
        scanner.useDelimiter(System.lineSeparator());
        boolean running = true;
        while (running) {
            scanner.reset();
            System.out.println("\nMenu");
            System.out.println("1. Add a doctor");
            System.out.println("2. Add a patient");
            System.out.println("3. Get an appointment");
            System.out.println("4. Find a doctor by field");
            System.out.println("5. Show all info");
            System.out.println("6. Remove a doctor");
            System.out.println("7. Remove a patient");
            System.out.println("8. Export data");
            System.out.println("9. Import data");
            System.out.println("10. Update a doctor's name");
            System.out.println("11. Update a patient's name");
            System.out.println("Insert any other data to exit.");

            String choice = scanner.next();
            switch (choice) {
                case "1":
                    addDoctor();
                    break;
                case "2":
                    addPatient();
                    break;
                case "3":
                    getAppointment();
                    break;
                case "4":
                    findDoctorByField();
                    break;
                case "5":
                    hospital.printAll();
                    break;
                case "6":
                    removeDoctor();
                    break;
                case "7":
                    removePatient();
                    break;
                case "9":
                    importData();
                    break;
                case "8":
                    exportData();
                    break;
                case "10":
                    updateDoctor();
                    break;
                case "11":
                    updatePatient();
                    break;
                default:
                    System.out.println("Bye!");
                    running = false;
                    break;
            }
            System.out.println("Press Enter key to continue...");
            try {
                System.in.read();
            } catch (Exception e) {
            }
            System.out.print("\033[H\033[2J");
            System.out.flush();
        }
    }

    private static void addDoctor() {
        scanner.useDelimiter(System.lineSeparator());
        System.out.println("Enter the name: ");
        String name = scanner.next();
        System.out.println("Enter the field of work: ");
        String field = scanner.next();
        doctorIDCounter++;
        hospital.addDoctor(new Doctor(name, field, doctorIDCounter));
    }

    private static void addPatient() {
        scanner.useDelimiter(System.lineSeparator());
        System.out.println("Enter the name: ");
        String name = scanner.next();
        patientIDCounter++;
        hospital.addPatient(new Patient(name, patientIDCounter));
    }

    private static void getAppointment() {
        scanner.useDelimiter(System.lineSeparator());
        boolean invalid = true;
        // String date = null;
        Patient patient = new Patient(null, 0);
        Doctor doctor = new Doctor(null, null, 0);
        while (invalid) {
            System.out.println("Enter the name of the patient:");
            String name = scanner.next();
            patient = findPatient(name);
            if (patient.getName().equals("null")) {
                System.out.println("This patient doesn't exist");
            } else
                invalid = false;
        }
        invalid = true;
        while (invalid) {
            System.out.println("Enter the name of the doctor:");
            String name = scanner.next();
            doctor = findDoctorByName(name);
            if (doctor.getName().equals("null")) {
                System.out.println("This doctor doesn't exist");
            } else
                invalid = false;
        }
        /*
         * invalid = true;
         * while (invalid) {
         * System.out.println("Enter the desired date of the appointment (dd/mm format)"
         * );
         * date = scanner.next();
         * if ((date.length() == 5) && (date.indexOf("/") == 2)) {
         * int day, month;
         * try {
         * day = Integer.parseInt(date.substring(0, 1));
         * month = Integer.parseInt(date.substring(3, 4));
         * } catch (NumberFormatException nfe) {
         * System.out.println("NumberFormatException: " + nfe.getMessage());
         * return;
         * }
         * if (((day < 32) && (month < 13)) == false)
         * System.out.println("Invalid date");
         * else
         * invalid = false;
         * } else
         * System.out.println("Invalid date");
         * }
         */
        doctor.addAppointment(patient, doctor);
        System.out.println("Thanks for making the appointment!");
    }

    private static Patient findPatient(String name) {
        for (Patient p : hospital.getPatients()) {
            if (p.getName().equalsIgnoreCase(name)) {
                return p;
            }
        }
        Patient p = new Patient("null", 0);
        return p;
    }

    private static Doctor findDoctorByName(String name) {
        for (Doctor d : hospital.getDoctors()) {
            if (d.getName().equalsIgnoreCase(name)) {
                System.out.println(d);
                return d;
            }
        }
        Doctor d = new Doctor("null", null, 0);
        return d;
    }

    private static void findDoctorByField() {
        scanner.useDelimiter(System.lineSeparator());
        System.out.println("Enter the field of the doctor you're interested in:");
        String field = scanner.next();
        hospital.findByField(field);
    }

    private static void removeDoctor() {
        scanner.useDelimiter(System.lineSeparator());
        List<Doctor> doctors = hospital.getDoctors();
        if (doctors.isEmpty()) {
            System.out.println("No doctors to remove.");
            return;
        }
        System.out.println("Select a doctor to remove:");
        for (int i = 0; i < doctors.size(); i++) {
            System.out.println((i + 1) + ". " + doctors.get(i).getName());
        }
        int index = scanner.nextInt() - 1;
        scanner.next();
        if (index >= 0 && index < doctors.size()) {
            Doctor removed = doctors.get(index);
            hospital.removeDoctor(removed);
            System.out.println("Doctor " + removed.getName() + " removed.");
        } else {
            System.out.println("Invalid selection.");
        }
    }

    private static void removePatient() {
        scanner.useDelimiter(System.lineSeparator());
        List<Patient> patients = hospital.getPatients();
        if (patients.isEmpty()) {
            System.out.println("No patients to remove.");
            return;
        }
        System.out.println("Select a patient to remove:");
        for (int i = 0; i < patients.size(); i++) {
            System.out.println((i + 1) + ". " + patients.get(i).getName());
        }
        int index = scanner.nextInt() - 1;
        scanner.next();
        if (index >= 0 && index < patients.size()) {
            Patient removed = patients.get(index);
            hospital.removePatient(removed);
            System.out.println("Patient " + removed.getName() + " removed.");
        } else {
            System.out.println("Invalid selection.");
        }

    }

    private static void updateDoctor() {
        scanner.useDelimiter(System.lineSeparator());
        List<Doctor> doctors = hospital.getDoctors();
        if (doctors.isEmpty()) {
            System.out.println("No doctors to update.");
            return;
        }
        System.out.println("Select a doctor to update:");
        for (int i = 0; i < doctors.size(); i++) {
            System.out.println((i + 1) + ". " + doctors.get(i).getName());
        }
        int index = scanner.nextInt() - 1;
        scanner.next();
        System.out.println("Please insert a new name for the doctor:");
        Doctor newDoctor = new Doctor(scanner.next(), doctors.get(index).getField(), doctors.get(index).getID());
        if (index >= 0 && index < doctors.size()) {
            doctors.set(index, newDoctor);
            hospital.updateDoctor(index, newDoctor);
            System.out.println("Doctor " + doctors.get(index).getName() + "'s name was updated.");
        } else {
            System.out.println("Invalid selection.");
        }
    }

    private static void updatePatient() {
        scanner.useDelimiter(System.lineSeparator());
        List<Patient> patients = hospital.getPatients();
        if (patients.isEmpty()) {
            System.out.println("No patients to update.");
            return;
        }
        System.out.println("Select a patient to update their name:");
        for (int i = 0; i < patients.size(); i++) {
            System.out.println((i + 1) + ". " + patients.get(i).getName());
        }
        int index = scanner.nextInt() - 1;
        scanner.next();
        System.out.println("Please insert a new name for the patient:");
        Patient newPatient = new Patient(scanner.next(), patients.get(index).getID());
        if (index >= 0 && index < patients.size()) {
            patients.set(index, newPatient);
            hospital.updatePatient(index, newPatient);
            System.out.println("Patient " + patients.get(index).getName() + "'s name was updated.");
        } else {
            System.out.println("Invalid selection.");
        }

    }

    private static void exportData() throws IOException {
        scanner.useDelimiter(System.lineSeparator());
        System.out.print("File to export to: ");
        String file = scanner.next();
        hospital.exportData(file, Comparator.comparing(Doctor::getID), Comparator.comparing(Patient::getID));
        System.out.println("Exported.");
    }

    private static void importData() throws IOException {
        scanner.useDelimiter(System.lineSeparator());
        System.out.print("File to import from: ");
        String file = scanner.next();
        hospital.importData(file);
        System.out.println("Imported.");
    }

}
