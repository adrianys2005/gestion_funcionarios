package dao;

import model.EstadoCivil;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EstadoCivilDAO {
    private final String url = "jdbc:mysql://localhost:3306/gestion_funcionarios";
    private final String user = "root";
    private final String password = "songoku"; // Pon tu contraseña si la tienes

    public List<EstadoCivil> listar() {
        List<EstadoCivil> lista = new ArrayList<>();

        String sql = "SELECT id_estado_civil, descripcion FROM estado_civil";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                EstadoCivil ec = new EstadoCivil(
                        rs.getInt("id_estado_civil"),
                        rs.getString("descripcion")
                );
                lista.add(ec);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }
}
