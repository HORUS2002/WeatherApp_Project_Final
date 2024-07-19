package dao;
import base_data.Appointment;
import dbcon.DBConnect;
import dbcon.DBUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AppointmentDAO {
    // Schedule a new appointment
    public void add(Appointment appointment) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = DBConnect.getConnection();
            String sql = "INSERT INTO appointment (patient_id, appointment_date, doctor_name, status) VALUES (?, ?, ?, ?)";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, appointment.getPatient_id());
            stmt.setString(2, appointment.getAppointment_date());
            stmt.setString(3, appointment.getDoctor_name());
            stmt.setString(4, appointment.getStatus());
            stmt.executeUpdate();
        } finally {
            DBUtil.close(conn, stmt);
        }
    }

    // Retrieve appointment details by ID
    public Appointment getId(int appointmentId) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Appointment appointment = null;
        try {
            conn = DBConnect.getConnection();
            String sql = "SELECT * FROM appointment WHERE appointment_id = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, appointmentId);
            rs = stmt.executeQuery();
            if (rs.next()) {
                appointment = new Appointment();
                appointment.setAppointment_id(rs.getInt("appointment_id"));
                appointment.setPatient_id(rs.getInt("patient_id"));
                appointment.setAppointment_date(rs.getString("appointment_date"));
                appointment.setDoctor_name(rs.getString("doctor_name"));
                appointment.setStatus(rs.getString("status"));
            }
        } finally {
            DBUtil.close(conn, stmt, rs);
        }
        return appointment;
    }

    // Update appointment information
    public void update(Appointment appointment) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = DBConnect.getConnection();
            String sql = "UPDATE appointment SET patient_id = ?, appointment_date = ?, doctor_name = ?, status = ? WHERE appointment_id = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, appointment.getPatient_id());
            stmt.setString(2, appointment.getAppointment_date());
            stmt.setString(3, appointment.getDoctor_name());
            stmt.setString(4, appointment.getStatus());
            stmt.setInt(5, appointment.getAppointment_id());
            stmt.executeUpdate();
        } finally {
            DBUtil.close(conn, stmt);
        }
    }

    // Cancel an appointment
    public void delete(int appointmentId) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = DBConnect.getConnection();
            String sql = "DELETE FROM appointment WHERE appointment_id = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, appointmentId);
            stmt.executeUpdate();
        } finally {
            DBUtil.close(conn, stmt);
        }
    }

    // Retrieve all appointments
    public List<Appointment> getAll() throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Appointment> appointments = new ArrayList<>();
        try {
            conn = DBConnect.getConnection();
            String sql = "SELECT * FROM appointment";
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            while (rs.next()) {
                Appointment appointment = new Appointment();
                appointment.setAppointment_id(rs.getInt("appointment_id"));
                appointment.setPatient_id(rs.getInt("patient_id"));
                appointment.setAppointment_date(rs.getString("appointment_date"));
                appointment.setDoctor_name(rs.getString("doctor_name"));
                appointment.setStatus(rs.getString("status"));
                appointments.add(appointment);
            }
        } finally {
            DBUtil.close(conn, stmt, rs);
        }
        return appointments;
    }
}