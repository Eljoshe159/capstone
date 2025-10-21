/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clases;

import infra.db;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RecipeDao {
 public List<String> listarNombresPorCategoria(String categoryName, String filtro) throws Exception {
        String sql =
            "SELECT r.name " +
            "FROM recipes r " +
            "JOIN categories c ON c.category_id = r.category_id " +
            "WHERE c.name = ? AND r.name LIKE CONCAT('%', ?, '%') " +
            "ORDER BY r.name";

        List<String> out = new ArrayList<>();
        try (Connection cn = db.getConnection();
             PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setString(1, categoryName);
            ps.setString(2, (filtro == null) ? "" : filtro.trim());

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    out.add(rs.getString(1));
                }
            }
        }
        return out;
    }

public static class Detalle {
    public String ingrediente;  // nombre exacto del ingrediente
    public double cantidad;
    public String unidad;       // símbolo o nombre de unidad (g, kg, ml, L, u)
    public String nota;
}

public int insertarRecetaConDetalle(
        String categoryName, String nombreReceta, String descripcionComoNota,
        double servingsBase, java.util.List<Detalle> items) throws Exception {

    String sqlCat   = "SELECT category_id FROM categories WHERE name=?";
    String sqlInsR  = "INSERT INTO recipes (category_id, name, servings_base, notes) VALUES (?,?,?,?)";
    // Si tu tabla tiene columna 'description', usa esta otra línea y comenta la anterior:
    // String sqlInsR = "INSERT INTO recipes (category_id, name, servings_base, description) VALUES (?,?,?,?)";

    String sqlIngId = "SELECT ingredient_id FROM ingredients WHERE name=?";
    String sqlUniId = "SELECT unit_id FROM units WHERE symbol=? OR name=? LIMIT 1";
    String sqlDet   = "INSERT INTO recipe_ingredients (recipe_id, ingredient_id, quantity, unit_id, notes) VALUES (?,?,?,?,?)";

    try (java.sql.Connection cn = infra.db.getConnection()) {
        cn.setAutoCommit(false);
        try {
            int catId;
            try (java.sql.PreparedStatement ps = cn.prepareStatement(sqlCat)) {
                ps.setString(1, categoryName);
                try (java.sql.ResultSet rs = ps.executeQuery()) {
                    if (!rs.next()) throw new RuntimeException("Categoría no encontrada: " + categoryName);
                    catId = rs.getInt(1);
                }
            }

            int recipeId;
            try (java.sql.PreparedStatement ps = cn.prepareStatement(sqlInsR, java.sql.Statement.RETURN_GENERATED_KEYS)) {
                ps.setInt(1, catId);
                ps.setString(2, nombreReceta);
                ps.setDouble(3, servingsBase);
                ps.setString(4, descripcionComoNota == null ? "" : descripcionComoNota);
                ps.executeUpdate();
                try (java.sql.ResultSet rs = ps.getGeneratedKeys()) {
                    if (!rs.next()) throw new RuntimeException("No se obtuvo ID de la receta");
                    recipeId = rs.getInt(1);
                }
            }

            try (java.sql.PreparedStatement psIng = cn.prepareStatement(sqlIngId);
                 java.sql.PreparedStatement psUni = cn.prepareStatement(sqlUniId);
                 java.sql.PreparedStatement psDet = cn.prepareStatement(sqlDet)) {

                for (Detalle d : items) {
                    if (d == null) continue;
                    if (d.ingrediente == null || d.ingrediente.trim().isEmpty()) continue;

                    int ingId;
                    psIng.setString(1, d.ingrediente.trim());
                    try (java.sql.ResultSet rs = psIng.executeQuery()) {
                        if (!rs.next()) throw new RuntimeException("Ingrediente no existe: " + d.ingrediente);
                        ingId = rs.getInt(1);
                    }

                    int uniId;
                    psUni.setString(1, d.unidad == null ? "" : d.unidad.trim());
                    psUni.setString(2, d.unidad == null ? "" : d.unidad.trim());
                    try (java.sql.ResultSet rs = psUni.executeQuery()) {
                        if (!rs.next()) throw new RuntimeException("Unidad no existe: " + d.unidad);
                        uniId = rs.getInt(1);
                    }

                    psDet.setInt(1, recipeId);
                    psDet.setInt(2, ingId);
                    psDet.setDouble(3, d.cantidad);
                    psDet.setInt(4, uniId);
                    psDet.setString(5, d.nota == null ? "" : d.nota);
                    psDet.executeUpdate();
                }
            }

            cn.commit();
            return recipeId;
        } catch (Exception ex) {
            cn.rollback();
            throw ex;
        } finally {
            cn.setAutoCommit(true);
        }
    }
 }
}