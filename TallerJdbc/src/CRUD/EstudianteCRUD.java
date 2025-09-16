package CRUD;

import Conexion.DbConexion;

import java.sql.*;

public class EstudianteCRUD {
    public void insertar(String nombre, String apellido, String correo, int edad, String estadoCivil) {
        String sql = "INSERT INTO estudiantes (nombre, apellido, correo, edad, estado_civil) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DbConexion.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, nombre);
            ps.setString(2, apellido);
            ps.setString(3, correo);
            ps.setInt(4, edad);
            ps.setString(5, estadoCivil);

            ps.executeUpdate();
            System.out.println("Estudiante insertado correctamente");
        } catch (SQLException e) {
            if (e.getErrorCode() == 1062) {
                System.out.println("El correo ya existe, debe ser único.");
            } else {
                e.printStackTrace();
            }
        }
    }

    // Actualizar estudiante por correo
    public void actualizar(String correo, String nombre, String apellido, int edad, String estadoCivil) {
        String sql = "UPDATE estudiantes SET nombre=?, apellido=?, edad=?, estado_civil=? WHERE correo=?";
        try (Connection conn = DbConexion.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, nombre);
            ps.setString(2, apellido);
            ps.setInt(3, edad);
            ps.setString(4, estadoCivil);
            ps.setString(5, correo);

            int filas = ps.executeUpdate();
            if (filas > 0) {
                System.out.println("Estudiante actualizado");
            } else {
                System.out.println("No se encontró estudiante con ese correo");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Eliminar estudiante por correo
    public void eliminar(String correo) {
        String sql = "DELETE FROM estudiantes WHERE correo=?";
        try (Connection conn = DbConexion.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, correo);

            int filas = ps.executeUpdate();
            if (filas > 0) {
                System.out.println("Estudiante eliminado");
            } else {
                System.out.println("No se encontró estudiante con ese correo");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Consultar todos
    public void consultarTodos() {
        String sql = "SELECT * FROM estudiantes";
        try (Connection conn = DbConexion.conectar();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                System.out.println(
                        rs.getInt("id") + " | " +
                                rs.getString("nombre") + " " + rs.getString("apellido") +
                                " | " + rs.getString("correo") +
                                " | " + rs.getInt("edad") +
                                " | " + rs.getString("estado_civil")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Consultar por correo
    public void consultarPorCorreo(String correo) {
        String sql = "SELECT * FROM estudiantes WHERE correo=?";
        try (Connection conn = DbConexion.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, correo);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                System.out.println("Estudiante encontrado: " +
                        rs.getString("nombre") + " " + rs.getString("apellido") +
                        ", edad " + rs.getInt("edad") +
                        ", estado civil: " + rs.getString("estado_civil"));
            } else {
                System.out.println("No se encontró estudiante con ese correo");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}