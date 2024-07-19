package dao;
import base_data.MedicalRecord;
import dbcon.DBConnect;
import dbcon.DBUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MedicalRecordDAO {
    // Add a new medical record
    public void add(MedicalRecord medicalRecord) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = DBConnect.getConnection();
            String sql = "INSERT INTO medical_record (patient_id, diagnosis, treatment, date) VALUES (?, ?, ?, ?)";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, medicalRecord.getPatient_id());
            stmt.setString(2, medicalRecord.getDiagnosis());
            stmt.setString(3, medicalRecord.getTreatment());
            stmt.setString(4, medicalRecord.getDate());
            stmt.executeUpdate();
        } finally {
            DBUtil.close(conn, stmt);
        }
    }

    // Retrieve medical record details by ID
    public MedicalRecord getId(int recordId) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        MedicalRecord medicalRecord = null;
        try {
            conn = DBConnect.getConnection();
            String sql = "SELECT * FROM medical_record WHERE record_id = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, recordId);
            rs = stmt.executeQuery();
            if (rs.next()) {
                medicalRecord = new MedicalRecord();
                medicalRecord.setRecord_id(rs.getInt("record_id"));
                medicalRecord.setPatient_id(rs.getInt("patient_id"));
                medicalRecord.setDiagnosis(rs.getString("diagnosis"));
                medicalRecord.setTreatment(rs.getString("treatment"));
                medicalRecord.setDate(rs.getString("date"));
            }
        } finally {
            DBUtil.close(conn, stmt, rs);
        }
        return medicalRecord;
    }

    // Update medical record information
    public void update(MedicalRecord medicalRecord) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = DBConnect.getConnection();
            String sql = "UPDATE medical_record SET patient_id = ?, diagnosis = ?, treatment = ?, date = ? WHERE record_id = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, medicalRecord.getPatient_id());
            stmt.setString(2, medicalRecord.getDiagnosis());
            stmt.setString(3, medicalRecord.getTreatment());
            stmt.setString(4, medicalRecord.getDate());
            stmt.setInt(5, medicalRecord.getRecord_id());
            stmt.executeUpdate();
        } finally {
            DBUtil.close(conn, stmt);
        }
    }

    // Delete a medical record
    public void delete(int recordId) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = DBConnect.getConnection();
            String sql = "DELETE FROM medical_record WHERE record_id = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, recordId);
            stmt.executeUpdate();
        } finally {
            DBUtil.close(conn, stmt);
        }
    }

    // Retrieve all medical records
    public List<MedicalRecord> getAll() throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<MedicalRecord> medicalRecords = new ArrayList<>();
        try {
            conn = DBConnect.getConnection();
            String sql = "SELECT * FROM medical_record";
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            while (rs.next()) {
                MedicalRecord medicalRecord = new MedicalRecord();
                medicalRecord.setRecord_id(rs.getInt("record_id"));
                medicalRecord.setPatient_id(rs.getInt("patient_id"));
                medicalRecord.setDiagnosis(rs.getString("diagnosis"));
                medicalRecord.setTreatment(rs.getString("treatment"));
                medicalRecord.setDate(rs.getString("date"));
                medicalRecords.add(medicalRecord);
            }
        } finally {
            DBUtil.close(conn, stmt, rs);
        }
        return medicalRecords;
    }
}