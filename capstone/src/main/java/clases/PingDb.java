
package clases;


public class PingDb {
    public static void main(String[] args) {
        try (
            java.sql.Connection cn = infra.db.getConnection();
            java.sql.Statement st = cn.createStatement();
            java.sql.ResultSet rs = st.executeQuery("SELECT 1")
        ) {
            rs.next();
            System.out.println("Conectado. Resultado: " + rs.getInt(1));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

    