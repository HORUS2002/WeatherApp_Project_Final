package app_data;
import dao.AppointmentDAO;
import base_data.Appointment;
import java.util.List;
import java.util.Scanner;

public class AppointmentDB {
    private final Scanner sc;
    private final AppointmentDAO appointmentDAO;

    //Constructor
    public AppointmentDB(Scanner sc) {
        this.sc = sc;
        this.appointmentDAO = new AppointmentDAO();
    }

    //Perform operations on Appointment Database
    public void manage() {
        while (true) {
        	System.out.println();
            System.out.println("Appointment Management");
            System.out.println("1. Schedule Appointment");
            System.out.println("2. View Appointment");
            System.out.println("3. Update Appointment");
            System.out.println("4. Cancel Appointment");
            System.out.println("5. View All Appointments");
            System.out.println("6. Back to Main Menu");
            System.out.print("Select an option: ");
            int ch = sc.nextInt();
            sc.nextLine();
            switch (ch) {
                case 1:
                    schedule();
                    break;
                case 2:
                    view();
                    break;
                case 3:
                    update();
                    break;
                case 4:
                    cancel();
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
    private void schedule() {
        System.out.print("Enter patient ID: ");
        int patientId = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter appointment date (YYYY-MM-DD): ");
        String appointmentDate = sc.nextLine();
        System.out.print("Enter doctor name: ");
        String doctorName = sc.nextLine();
        System.out.print("Enter status (scheduled/completed/cancelled): ");
        String status = sc.nextLine();
        Appointment appointment = new Appointment();
        appointment.setPatient_id(patientId);
        appointment.setAppointment_date(appointmentDate);
        appointment.setDoctor_name(doctorName);
        appointment.setStatus(status);
        try {
            appointmentDAO.add(appointment);
            System.out.println("Appointment scheduled successfully.");
        } catch (Exception e) {
            System.out.println("Error scheduling appointment: " + e.getMessage());
        }
    }

    private void view() {
        System.out.print("Enter appointment ID: ");
        int id = sc.nextInt();
        sc.nextLine();
        try {
            Appointment appointment = appointmentDAO.getId(id);
            if (appointment != null) {
                System.out.println("Appointment Details:");
                System.out.println("ID: " + appointment.getAppointment_id());
                System.out.println("Patient ID: " + appointment.getPatient_id());
                System.out.println("Date: " + appointment.getAppointment_date());
                System.out.println("Doctor: " + appointment.getDoctor_name());
                System.out.println("Status: " + appointment.getStatus());
            } else {
                System.out.println("Appointment not found.");
            }
        } catch (Exception e) {
            System.out.println("Error viewing appointment: " + e.getMessage());
        }
    }

    private void update() {
        System.out.print("Enter appointment ID: ");
        int id = sc.nextInt();
        sc.nextLine();
        try {
            Appointment appointment = appointmentDAO.getId(id);
            if (appointment != null) {
                System.out.print("Enter new patient ID (current: " + appointment.getPatient_id() + "): ");
                int patientId = sc.nextInt();
                sc.nextLine();
                System.out.print("Enter new appointment date (current: " + appointment.getAppointment_date() + "): ");
                String appointmentDate = sc.nextLine();
                System.out.print("Enter new doctor name (current: " + appointment.getDoctor_name() + "): ");
                String doctorName = sc.nextLine();
                System.out.print("Enter new status (current: " + appointment.getStatus() + "): ");
                String status = sc.nextLine();
                appointment.setPatient_id(patientId);
                appointment.setAppointment_date(appointmentDate.isEmpty() ? appointment.getAppointment_date() : appointmentDate);
                appointment.setDoctor_name(doctorName.isEmpty() ? appointment.getDoctor_name() : doctorName);
                appointment.setStatus(status.isEmpty() ? appointment.getStatus() : status);
                appointmentDAO.update(appointment);
                System.out.println("Appointment updated successfully.");
            } else {
                System.out.println("Appointment not found.");
            }
        } catch (Exception e) {
            System.out.println("Error updating appointment: " + e.getMessage());
        }
    }

    private void cancel() {
        System.out.print("Enter appointment ID: ");
        int id = sc.nextInt();
        sc.nextLine();
        try {
            appointmentDAO.delete(id);
            System.out.println("Appointment canceled successfully.");
        } catch (Exception e) {
            System.out.println("Error canceling appointment: " + e.getMessage());
        }
    }

    private void list() {
        try {
            List<Appointment> appointments = appointmentDAO.getAll();
            System.out.println("All Appointments:");
            for (Appointment appointment : appointments) {
                System.out.println("ID: " + appointment.getAppointment_id() + ", Date: " + appointment.getAppointment_date() + ", Doctor: " + appointment.getDoctor_name() + ", Status: " + appointment.getStatus());
            }
        } catch (Exception e) {
            System.out.println("Error viewing all appointments: " + e.getMessage());
        }
    }
}