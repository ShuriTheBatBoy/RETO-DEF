import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class JavaJDBC {
    public static void main(String[] args) {
        try {
            // 1. Cargar el driver JDBC de MySQL
            Class.forName("com.mysql.cj.jdbc.Driver");

            // 2. Conectarse a tu base de datos (ajusta nombre si es necesario)
            Connection connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/mou5labs", "root", ""
            );

            Statement statement = connection.createStatement();

            System.out.println("=== ESTADÍSTICAS DE INSCRIPCIONES ===\n");

            // 3. CANTIDAD TOTAL DE PERSONAS INSCRITAS
            ResultSet total = statement.executeQuery("SELECT COUNT(*) AS total FROM inscripciones");
            if (total.next()) {
                System.out.println("Total de personas inscritas: " + total.getInt("total"));
            }

            System.out.println();

            // 4. PERSONAS INSCRITAS POR EVENTO (elección)
            System.out.println("Inscritos por evento:");
            System.out.println("------------------------------------");
            ResultSet porEvento = statement.executeQuery(
                "SELECT eleccion, COUNT(*) AS cantidad FROM inscripciones GROUP BY eleccion"
            );
            while (porEvento.next()) {
                System.out.println(porEvento.getString("eleccion") + ": " + porEvento.getInt("cantidad") + " personas");
            }

            System.out.println();

            // 5. LISTADO DE PERSONAS CON SU EVENTO
            System.out.println("Listado de personas inscritas:");
            System.out.println("====================================");
            ResultSet listado = statement.executeQuery(
                "SELECT nombre, apellido, mail, eleccion, dia FROM inscripciones ORDER BY eleccion, nombre"
            );
            while (listado.next()) {
                System.out.println("- " + listado.getString("nombre") + " " + listado.getString("apellido")
                        + " (" + listado.getString("mail") + ") → "
                        + listado.getString("eleccion") + " (" + listado.getString("dia") + ")");
            }

            // 6. Cerrar conexión
            connection.close();

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
