package app_data;
import java.util.Scanner;

public class Application {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        PatientDB patient = new PatientDB(sc);
        AppointmentDB appointment = new AppointmentDB(sc);
        MedicalRecordDB medicalRecord = new MedicalRecordDB(sc);
        while (true) {
        	System.out.println();
            System.out.println("Clinic Management System");
            System.out.println("1. Patient Management");
            System.out.println("2. Appointment Management");
            System.out.println("3. Medical Record Management");
            System.out.println("4. Exit");
            System.out.print("Select an option: ");
            int ch = sc.nextInt();
            sc.nextLine();
            switch (ch) {
                case 1:
                    patient.manage();
                    break;
                case 2:
                    appointment.manage();
                    break;
                case 3:
                    medicalRecord.manage();
                    break;
                case 4:
                    System.out.println("Exiting...");
                    sc.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}