package base_data;

public class MedicalRecord {
	private int record_id;     //Record Number of Patient
	private int patient_id;    //Unique ID of Patient
	private String diagnosis;  //Diagnosis of Patient
	private String treatment;  //Treatment prescribed
	private String date;       //Date of treatment
	
	//Getters and Setters
	public int getRecord_id() {
		return record_id;
	}
	
	public void setRecord_id(int record_id) {
		this.record_id = record_id;
	}
	
	public int getPatient_id() {
		return patient_id;
	}
	
	public void setPatient_id(int patient_id) {
		this.patient_id = patient_id;
	}
	
	public String getDiagnosis() {
		return diagnosis;
	}
	
	public void setDiagnosis(String diagnosis) {
		this.diagnosis = diagnosis;
	}
	
	public String getTreatment() {
		return treatment;
	}
	
	public void setTreatment(String treatment) {
		this.treatment = treatment;
	}
	
	public String getDate() {
		return date;
	}
	
	public void setDate(String date) {
		this.date = date;
	}
}