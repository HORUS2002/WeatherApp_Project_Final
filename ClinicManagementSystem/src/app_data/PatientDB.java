package app_data;
import dao.PatientDAO;
import base_data.Patient;
import java.util.List;
import java.util.Scanner;

public class PatientDB {
    private final Scanner sc;
    private final PatientDAO patientDAO;
    
    //Constructor
    public PatientDB(Scanner sc) {
        this.sc = sc;
        this.patientDAO = new PatientDAO();
    }

    //Perform operations on Patient Database
    public void manage() {
        while (true) {
        	System.out.println();
            System.out.println("Patient Management");
            System.out.println("1. Add Patient");
            System.out.println("2. View Patient");
            System.out.println("3. Update Patient");
            System.out.println("4. Delete Patient");
            System.out.println("5. View All Patients");
            System.out.println("6. Back to Main Menu");
            System.out.print("Select an option: ");
            int ch = sc.nextInt();
            sc.nextLine();
            switch (ch) {
                case 1:
                    add();
                    break;
                case 2:
                    view();
                    break;
                case 3:
                    update();
                    break;
                case 4:
                    delete();
                    break;
                case 5:
                    list();
                    break;
                case 6:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
    
    //The CRUD and List methods
    private void add() {
        System.out.print("Enter name: ");
        String name = sc.nextLine();
        System.out.print("Enter date of birth (YYYY-MM-DD): ");
        String dob = sc.nextLine();
        System.out.print("Enter gender: ");
        String gender = sc.nextLine();
        System.out.print("Enter contact info: ");
        String contactInfo = sc.nextLine();
        Patient patient = new Patient();
        patient.setName(name);
        patient.setDate_of_birth(dob);
        patient.setGender(gender);
        patient.setContact_info(contactInfo);
        try {
            patientDAO.add(patient);
            System.out.println("Patient added successfully.");
        } catch (Exception e) {
            System.out.println("Error adding patient: " + e.getMessage());
        }
    }

    private void view() {
        System.out.print("Enter patient ID: ");
        int id = sc.nextInt();
        sc.nextLine();
        try {
            Patient patient = patientDAO.getId(id);
            if (patient != null) {
                System.out.println("Patient Details:");
                System.out.println("ID: " + patient.getPatient_id());
                System.out.println("Name: " + patient.getName());
                System.out.println("Date of Birth: " + patient.getDate_of_birth());
                System.out.println("Gender: " + patient.getGender());
                System.out.println("Contact Info: " + patient.getContact_info());
            } else {
                System.out.println("Patient not found.");
            }
        } catch (Exception e) {
            System.out.println("Error viewing patient: " + e.getMessage());
        }
    }

    private void update() {
        System.out.print("Enter patient ID: ");
        int id = sc.nextInt();
        sc.nextLine();
        try {
            Patient patient = patientDAO.getId(id);
            if (patient != null) {
                System.out.print("Enter new name (current: " + patient.getName() + "): ");
                String name = sc.nextLine();
                System.out.print("Enter new date of birth (current: " + patient.getDate_of_birth() + "): ");
                String dob = sc.nextLine();
                System.out.print("Enter new gender (current: " + patient.getGender() + "): ");
                String gender = sc.nextLine();
                System.out.print("Enter new contact info (current: " + patient.getContact_info() + "): ");
                String contactInfo = sc.nextLine();
                patient.setName(name.isEmpty() ? patient.getName() : name);
                patient.setDate_of_birth(dob.isEmpty() ? patient.getDate_of_birth() : dob);
                patient.setGender(gender.isEmpty() ? patient.getGender() : gender);
                patient.setContact_info(contactInfo.isEmpty() ? patient.getContact_info() : contactInfo);
                patientDAO.update(patient);
                System.out.println("Patient updated successfully.");
            } else {
                System.out.println("Patient not found.");
            }
        } catch (Exception e) {
            System.out.println("Error updating patient: " + e.getMessage());
        }
    }

    private void delete() {
        System.out.print("Enter patient ID: ");
        int id = sc.nextInt();
        sc.nextLine();
        try {
            patientDAO.delete(id);
            System.out.println("Patient deleted successfully.");
        } catch (Exception e) {
            System.out.println("Error deleting patient: " + e.getMessage());
        }
    }

    private void list() {
        try {
            List<Patient> patients = patientDAO.getAll();
            System.out.println("All Patients:");
            for (Patient patient : patients) {
                System.out.println("ID: " + patient.getPatient_id() + ", Name: " + patient.getName());
            }
        } catch (Exception e) {
            System.out.println("Error viewing all patients: " + e.getMessage());
        }
    }
}