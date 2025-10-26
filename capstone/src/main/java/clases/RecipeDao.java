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
    public String ingrediente;
    public double cantidad;
    public String unidad;   
    public String nota;
}

public int insertarRecetaConDetalle(
        String categoryName, String nombreReceta, String descripcionComoNota,
        double servingsBase, java.util.List<Detalle> items) throws Exception {

    String sqlCat   = "SELECT category_id FROM categories WHERE name=?";
    String sqlInsR  = "INSERT INTO recipes (category_id, name, servings_base, notes) VALUES (?,?,?,?)";

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
private Integer findRecipeId(Connection cn, String categoryName, String recipeName) throws SQLException {
    final String sql =
        "SELECT r.recipe_id " +
        "FROM recipes r " +
        "JOIN categories c ON c.category_id = r.category_id " +
        "WHERE c.name = ? AND r.name = ? " +
        "LIMIT 1";
    try (PreparedStatement ps = cn.prepareStatement(sql)) {
        ps.setString(1, categoryName);
        ps.setString(2, recipeName);
        try (ResultSet rs = ps.executeQuery()) {
            return rs.next() ? rs.getInt(1) : null;
        }
    }
}
public boolean eliminarPorCategoriaYNombre(String categoryName, String recipeName) throws Exception {
    try (Connection cn = db.getConnection()) {
        cn.setAutoCommit(false);
        try {
            Integer recipeId = findRecipeId(cn, categoryName, recipeName);
            if (recipeId == null) { // no existe
                cn.rollback();
                return false;
            }

            try (PreparedStatement ps = cn.prepareStatement(
                    "DELETE FROM recipe_ingredients WHERE recipe_id = ?")) {
                ps.setInt(1, recipeId);
                ps.executeUpdate();
            }

            int affected;
            try (PreparedStatement ps = cn.prepareStatement(
                    "DELETE FROM recipes WHERE recipe_id = ?")) {
                ps.setInt(1, recipeId);
                affected = ps.executeUpdate();
            }

            cn.commit();
            return affected > 0;
        } catch (Exception ex) {
            cn.rollback();
            throw ex;
        } finally {
            try { cn.setAutoCommit(true); } catch (SQLException ignore) {}
        }
    }
}
public static class Receta {
    public int recipeId;
    public String nombre;
    public double servingsBase;
    public String notas;           // usa 'notes' o 'description' según tu tabla
    public List<Detalle> items = new ArrayList<>();
}


public Integer buscarIdPorCategoriaYNombre(String categoryName, String recipeName) throws Exception {
    final String sql =
        "SELECT r.recipe_id " +
        "FROM recipes r " +
        "JOIN categories c ON c.category_id = r.category_id " +
        "WHERE c.name = ? AND r.name = ? " +
        "LIMIT 1";
    try (Connection cn = db.getConnection();
         PreparedStatement ps = cn.prepareStatement(sql)) {
        ps.setString(1, categoryName);
        ps.setString(2, recipeName);
        try (ResultSet rs = ps.executeQuery()) {
            return rs.next() ? rs.getInt(1) : null;
        }
    }
}
public Receta obtenerRecetaPorId(int recipeId) throws Exception {
    Receta out = new Receta();


    final String sqlHead =
        "SELECT r.recipe_id, r.name, r.servings_base, r.notes " +
        "FROM recipes r " +
        "WHERE r.recipe_id = ?";


    final String sqlDet =
        "SELECT i.name AS ingrediente, ri.quantity, " +
        "       COALESCE(u.symbol, u.name) AS unidad, ri.notes " +
        "FROM recipe_ingredients ri " +
        "JOIN ingredients i ON i.ingredient_id = ri.ingredient_id " +
        "LEFT JOIN units u ON u.unit_id = ri.unit_id " +
        "WHERE ri.recipe_id = ? " +
        "ORDER BY i.name";

    try (Connection cn = db.getConnection()) {

        try (PreparedStatement ps = cn.prepareStatement(sqlHead)) {
            ps.setInt(1, recipeId);
            try (ResultSet rs = ps.executeQuery()) {
                if (!rs.next()) throw new RuntimeException("Receta no encontrada (id=" + recipeId + ")");
                out.recipeId    = rs.getInt("recipe_id");
                out.nombre      = rs.getString("name");
                out.servingsBase= rs.getDouble("servings_base");
                out.notas       = rs.getString("notes"); // o "description"
            }
        }

        try (PreparedStatement ps = cn.prepareStatement(sqlDet)) {
            ps.setInt(1, recipeId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Detalle d = new Detalle();
                    d.ingrediente = rs.getString("ingrediente");
                    d.cantidad    = rs.getDouble("quantity");
                    d.unidad      = rs.getString("unidad");
                    d.nota        = rs.getString("notes");
                    out.items.add(d);
                }
            }
        }
    }
    return out;
}


public void actualizarReceta(int recipeId, String nuevoNombre, double nuevaBase, String nuevasNotas,
                             java.util.List<Detalle> items) throws Exception {
    final String sqlUpdHead =
        "UPDATE recipes SET name = ?, servings_base = ?, notes = ? WHERE recipe_id = ?";
    final String sqlDelDet  =
        "DELETE FROM recipe_ingredients WHERE recipe_id = ?";
    final String sqlIngId   =
        "SELECT ingredient_id FROM ingredients WHERE name = ?";
    final String sqlUniId   =
        "SELECT unit_id FROM units WHERE symbol = ? OR name = ? LIMIT 1";
    final String sqlInsDet  =
        "INSERT INTO recipe_ingredients (recipe_id, ingredient_id, quantity, unit_id, notes) " +
        "VALUES (?,?,?,?,?)";

    try (java.sql.Connection cn = infra.db.getConnection()) {
        cn.setAutoCommit(false);
        try {

            try (java.sql.PreparedStatement ps = cn.prepareStatement(sqlUpdHead)) {
                ps.setString(1, nuevoNombre);
                ps.setDouble(2, nuevaBase);
                ps.setString(3, nuevasNotas == null ? "" : nuevasNotas);
                ps.setInt(4, recipeId);
                ps.executeUpdate();
            }


            try (java.sql.PreparedStatement ps = cn.prepareStatement(sqlDelDet)) {
                ps.setInt(1, recipeId);
                ps.executeUpdate();
            }


            try (java.sql.PreparedStatement psIng = cn.prepareStatement(sqlIngId);
                 java.sql.PreparedStatement psUni = cn.prepareStatement(sqlUniId);
                 java.sql.PreparedStatement psDet = cn.prepareStatement(sqlInsDet)) {

                for (Detalle d : items) {
                    if (d == null) continue;
                    String ing = d.ingrediente == null ? "" : d.ingrediente.trim();
                    if (ing.isEmpty()) continue; // ignora filas vacías

                    int ingId;
                    psIng.setString(1, ing);
                    try (java.sql.ResultSet rs = psIng.executeQuery()) {
                        if (!rs.next()) throw new RuntimeException("Ingrediente no existe: " + ing);
                        ingId = rs.getInt(1);
                    }

                    String uni = d.unidad == null ? "" : d.unidad.trim();
                    int uniId;
                    psUni.setString(1, uni);
                    psUni.setString(2, uni);
                    try (java.sql.ResultSet rs = psUni.executeQuery()) {
                        if (!rs.next()) throw new RuntimeException("Unidad no existe: " + uni);
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
        } catch (Exception e) {
            cn.rollback();
            throw e;
        } finally {
            try { cn.setAutoCommit(true); } catch (java.sql.SQLException ignore) {}
        }
    }
    
}


}
