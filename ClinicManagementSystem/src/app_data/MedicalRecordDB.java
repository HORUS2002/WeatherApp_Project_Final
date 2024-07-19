package app_data;
import dao.MedicalRecordDAO;
import base_data.MedicalRecord;
import java.util.List;
import java.util.Scanner;

public class MedicalRecordDB {
    private final Scanner sc;
    private final MedicalRecordDAO medicalRecordDAO;
    
    //Constructor
    public MedicalRecordDB(Scanner sc) {
        this.sc = sc;
        this.medicalRecordDAO = new MedicalRecordDAO();
    }
    
    //Perform operations on Medical Record Database
    public void manage() {
        while (true) {
        	System.out.println();
            System.out.println("Medical Record Management");
            System.out.println("1. Add Medical Record");
            System.out.println("2. View Medical Record");
            System.out.println("3. Update Medical Record");
            System.out.println("4. Delete Medical Record");
            System.out.println("5. View All Medical Records");
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
    
    //CRUD and List methods
    private void add() {
        System.out.print("Enter patient ID: ");
        int patientId = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter diagnosis: ");
        String diagnosis = sc.nextLine();
        System.out.print("Enter treatment: ");
        String treatment = sc.nextLine();
        System.out.print("Enter date (YYYY-MM-DD): ");
        String date = sc.nextLine();
        MedicalRecord medicalRecord = new MedicalRecord();
        medicalRecord.setPatient_id(patientId);
        medicalRecord.setDiagnosis(diagnosis);
        medicalRecord.setTreatment(treatment);
        medicalRecord.setDate(date);
        try {
            medicalRecordDAO.add(medicalRecord);
            System.out.println("Medical record added successfully.");
        } catch (Exception e) {
            System.out.println("Error adding medical record: " + e.getMessage());
        }
    }
    
    private void view() {
        System.out.print("Enter medical record ID: ");
        int id = sc.nextInt();
        sc.nextLine();
        try {
            MedicalRecord medicalRecord = medicalRecordDAO.getId(id);
            if (medicalRecord != null) {
                System.out.println("Medical Record Details:");
                System.out.println("ID: " + medicalRecord.getRecord_id());
                System.out.println("Patient ID: " + medicalRecord.getPatient_id());
                System.out.println("Diagnosis: " + medicalRecord.getDiagnosis());
                System.out.println("Treatment: " + medicalRecord.getTreatment());
                System.out.println("Date: " + medicalRecord.getDate());
            } else {
                System.out.println("Medical record not found.");
            }
        } catch (Exception e) {
            System.out.println("Error viewing medical record: " + e.getMessage());
        }
    }
    
    private void update() {
        System.out.print("Enter medical record ID: ");
        int id = sc.nextInt();
        sc.nextLine();
        try {
            MedicalRecord medicalRecord = medicalRecordDAO.getId(id);
            if (medicalRecord != null) {
                System.out.print("Enter new patient ID (current: " + medicalRecord.getPatient_id() + "): ");
                int patientId = sc.nextInt();
                sc.nextLine();
                System.out.print("Enter new diagnosis (current: " + medicalRecord.getDiagnosis() + "): ");
                String diagnosis = sc.nextLine();
                System.out.print("Enter new treatment (current: " + medicalRecord.getTreatment() + "): ");
                String treatment = sc.nextLine();
                System.out.print("Enter new date (current: " + medicalRecord.getDate() + "): ");
                String date = sc.nextLine();
                medicalRecord.setPatient_id(patientId);
                medicalRecord.setDiagnosis(diagnosis.isEmpty() ? medicalRecord.getDiagnosis() : diagnosis);
                medicalRecord.setTreatment(treatment.isEmpty() ? medicalRecord.getTreatment() : treatment);
                medicalRecord.setDate(date.isEmpty() ? medicalRecord.getDate() : date);
                medicalRecordDAO.update(medicalRecord);
                System.out.println("Medical record updated successfully.");
            } else {
                System.out.println("Medical record not found.");
            }
        } catch (Exception e) {
            System.out.println("Error updating medical record: " + e.getMessage());
        }
    }
    
    private void delete() {
        System.out.print("Enter medical record ID: ");
        int id = sc.nextInt();
        sc.nextLine();
        try {
            medicalRecordDAO.delete(id);
            System.out.println("Medical record deleted successfully.");
        } catch (Exception e) {
            System.out.println("Error deleting medical record: " + e.getMessage());
        }
    }
    
    private void list() {
        try {
            List<MedicalRecord> medicalRecords = medicalRecordDAO.getAll();
            System.out.println("All Medical Records:");
            for (MedicalRecord medicalRecord : medicalRecords) {
                System.out.println("ID: " + medicalRecord.getRecord_id() + ", Patient ID: " + medicalRecord.getPatient_id() + ", Diagnosis: " + medicalRecord.getDiagnosis() + ", Treatment: " + medicalRecord.getTreatment() + ", Date: " + medicalRecord.getDate());
            }
        } catch (Exception e) {
            System.out.println("Error viewing all medical records: " + e.getMessage());
        }
    }
}