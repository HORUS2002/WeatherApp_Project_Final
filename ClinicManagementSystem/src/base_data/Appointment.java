package base_data;

public class Appointment {
	private int appointment_id;       //Appointment Number of Patient
	private int patient_id;           //Unique ID of Patient
	private String appointment_date;  //Date of Appointment of Patient
	private String doctor_name;       //Name of Doctor
	private String status;            //Status of Appointment(Scheduled / Completed / Cancelled)
	
	//Getters and Setters
	public int getAppointment_id() {
		return appointment_id;
	}
	
	public void setAppointment_id(int appointment_id) {
		this.appointment_id = appointment_id;
	}
	
	public int getPatient_id() {
		return patient_id;
	}
	
	public void setPatient_id(int patient_id) {
		this.patient_id = patient_id;
	}
	
	public String getAppointment_date() {
		return appointment_date;
	}
	
	public void setAppointment_date(String appointment_date) {
		this.appointment_date = appointment_date;
	}
	
	public String getDoctor_name() {
		return doctor_name;
	}
	
	public void setDoctor_name(String doctor_name) {
		this.doctor_name = doctor_name;
	}
	
	public String getStatus() {
		return status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
}