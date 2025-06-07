package dao;

import model.TipoDocumento;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TipoDocumentoDAO {

    public List<TipoDocumento> listar() {
        List<TipoDocumento> lista = new ArrayList<>();
        String sql = "SELECT * FROM tipo_documento";

        try (Connection conn = Conexion.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                TipoDocumento td = new TipoDocumento(
                        rs.getInt("id_tipo_doc"),
                        rs.getString("descripcion")
                );
                lista.add(td);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }

    // Aquí pones el nuevo método
    public TipoDocumento buscarPorId(int id) {
        TipoDocumento td = null;
        String sql = "SELECT * FROM tipo_documento WHERE id_tipo_doc = ?";

        try (Connection conn = Conexion.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    td = new TipoDocumento(
                            rs.getInt("id_tipo_doc"),
                            rs.getString("descripcion")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return td;
    }
}
