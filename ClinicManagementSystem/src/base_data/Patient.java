package base_data;

public class Patient {
	private int patient_id;        //Unique ID of Patient
	private String name;           //Name of Patient
	private String date_of_birth;  //Date of Birth of Patient
	private String gender;         //Gender of Patient
	private String contact_info;   //Contact Information of Patient
	
	//Getters and Setters
	public int getPatient_id() {
		return patient_id;
	}
	
	public void setPatient_id(int patient_id) {
		this.patient_id = patient_id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getDate_of_birth() {
		return date_of_birth;
	}
	
	public void setDate_of_birth(String date_of_birth) {
		this.date_of_birth = date_of_birth;
	}
	
	public String getGender() {
		return gender;
	}
	
	public void setGender(String gender) {
		this.gender = gender;
	}
	
	public String getContact_info() {
		return contact_info;
	}
	
	public void setContact_info(String contact_info) {
		this.contact_info = contact_info;
	}
}