/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package infra;
import java.sql.Connection;
import java.sql.DriverManager;
import java.io.InputStream;
import java.util.Properties;
public class db {
      private static String URL;
    private static String USER;
    private static String PASS;

    // Carga configuración una sola vez
    static {
        try (InputStream in = db.class.getClassLoader().getResourceAsStream("db.properties")) {
            Properties p = new Properties();
            if (in == null) {
                throw new RuntimeException("No se encontró db.properties en classpath");
            }
            p.load(in);
            URL  = p.getProperty("db.url");
            USER = p.getProperty("db.user");
            PASS = p.getProperty("db.pass");
        } catch (Exception e) {
            throw new RuntimeException("Error cargando db.properties: " + e.getMessage(), e);
        }
    }

    /** Obtiene una conexión nueva a MySQL/MariaDB */
    public static Connection getConnection() throws Exception {
        // Con JDBC 4+ no necesitas Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(URL, USER, PASS);
    }
}

