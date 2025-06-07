package dao;

import model.Funcionario;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FuncionarioDAO {

    // Listar funcionarios
    public List<Funcionario> listar() {
        List<Funcionario> lista = new ArrayList<>();
        String sql = "SELECT * FROM funcionarios";

        try (Connection conn = Conexion.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Funcionario f = new Funcionario(
                        rs.getInt("id_funcionario"),
                        rs.getInt("id_tipo_doc"),
                        rs.getInt("id_estado_civil"),
                        rs.getString("nombres"),
                        rs.getString("apellidos"),
                        rs.getString("direccion"),
                        rs.getString("telefono")
                );
                lista.add(f);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    // Agregar funcionario
    public void agregar(Funcionario f) {
        String sql = "INSERT INTO funcionarios(id_tipo_doc, id_estado_civil, nombres, apellidos, direccion, telefono) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = Conexion.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, f.getIdTipoDoc());
            stmt.setInt(2, f.getIdEstadoCivil());
            stmt.setString(3, f.getNombres());
            stmt.setString(4, f.getApellidos());
            stmt.setString(5, f.getDireccion());
            stmt.setString(6, f.getTelefono());

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Actualizar funcionario
    public void actualizar(Funcionario f) {
        String sql = "UPDATE funcionarios SET nombres=?, apellidos=?, direccion=?, telefono=? WHERE id_funcionario=?";

        try (Connection conn = Conexion.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, f.getNombres());
            stmt.setString(2, f.getApellidos());
            stmt.setString(3, f.getDireccion());
            stmt.setString(4, f.getTelefono());
            stmt.setInt(5, f.getIdFuncionario());

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Eliminar funcionario
    public void eliminar(int idFuncionario) {
        String sql = "DELETE FROM funcionarios WHERE id_funcionario=?";

        try (Connection conn = Conexion.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idFuncionario);
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
