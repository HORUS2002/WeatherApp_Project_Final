package dao;
import base_data.Patient;
import dbcon.DBConnect;
import dbcon.DBUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PatientDAO {
    // Add a new patient to the database
    public void add(Patient patient) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = DBConnect.getConnection();
            String sql = "INSERT INTO patient (name, date_of_birth, gender, contact_info) VALUES (?, ?, ?, ?)";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, patient.getName());
            stmt.setString(2, patient.getDate_of_birth());
            stmt.setString(3, patient.getGender());
            stmt.setString(4, patient.getContact_info());
            stmt.executeUpdate();
        } finally {
            DBUtil.close(conn, stmt);
        }
    }

    // Retrieve patient details from the database by ID
    public Patient getId(int patientId) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Patient patient = null;
        try {
            conn = DBConnect.getConnection();
            String sql = "SELECT * FROM patient WHERE patient_id = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, patientId);
            rs = stmt.executeQuery();
            if (rs.next()) {
                patient = new Patient();
                patient.setPatient_id(rs.getInt("patient_id"));
                patient.setName(rs.getString("name"));
                patient.setDate_of_birth(rs.getString("date_of_birth"));
                patient.setGender(rs.getString("gender"));
                patient.setContact_info(rs.getString("contact_info"));
            }
        } finally {
            DBUtil.close(conn, stmt, rs);
        }
        return patient;
    }

    // Update patient information in the database
    public void update(Patient patient) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = DBConnect.getConnection();
            String sql = "UPDATE patient SET name = ?, date_of_birth = ?, gender = ?, contact_info = ? WHERE patient_id = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, patient.getName());
            stmt.setString(2, patient.getDate_of_birth());
            stmt.setString(3, patient.getGender());
            stmt.setString(4, patient.getContact_info());
            stmt.setInt(5, patient.getPatient_id());
            stmt.executeUpdate();
        } finally {
            DBUtil.close(conn, stmt);
        }
    }

    // Delete a patient from the database by ID
    public void delete(int patientId) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = DBConnect.getConnection();
            String sql = "DELETE FROM patient WHERE patient_id = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, patientId);
            stmt.executeUpdate();
        } finally {
            DBUtil.close(conn, stmt);
        }
    }

    // Retrieve all patients from the database
    public List<Patient> getAll() throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Patient> patients = new ArrayList<>();
        try {
            conn = DBConnect.getConnection();
            String sql = "SELECT * FROM patient";
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            while (rs.next()) {
                Patient patient = new Patient();
                patient.setPatient_id(rs.getInt("patient_id"));
                patient.setName(rs.getString("name"));
                patient.setDate_of_birth(rs.getString("date_of_birth"));
                patient.setGender(rs.getString("gender"));
                patient.setContact_info(rs.getString("contact_info"));
                patients.add(patient);
            }
        } finally {
            DBUtil.close(conn, stmt, rs);
        }
        return patients;
    }
}