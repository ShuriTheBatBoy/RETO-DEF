import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class JavaJDBC {
    public static void main(String[] args) {
        try {
            // Carga el driver JDBC de MySQL
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Conexión a la base de datos
            Connection connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/mou5labs", "root", ""
            );

            Statement statement = connection.createStatement();

            // 1. Cantidad total de personas inscritas
            ResultSet totalResult = statement.executeQuery("SELECT COUNT(*) AS total FROM inscripciones");
            if (totalResult.next()) {
                int total = totalResult.getInt("total");
                System.out.println(" Total de personas inscritas: " + total);
            }

            // 2. Número de personas inscritas por evento
            System.out.println("\n Número de personas por evento:");
            ResultSet countByEvent = statement.executeQuery(
                "SELECT eleccion, COUNT(*) AS cantidad FROM inscripciones GROUP BY eleccion"
            );
            while (countByEvent.next()) {
                String evento = countByEvent.getString("eleccion");
                int cantidad = countByEvent.getInt("cantidad");
                System.out.println(" - " + evento + ": " + cantidad);
            }

            // 3. Listado de personas por evento
            System.out.println("\n Listado de personas por evento:");
            ResultSet listByEvent = statement.executeQuery(
                "SELECT eleccion, dni, nombre, apellido FROM inscripciones ORDER BY eleccion, apellido, nombre"
            );
            String eventoActual = "";
            while (listByEvent.next()) {
                String evento = listByEvent.getString("eleccion");
                String dni = listByEvent.getString("dni");
                String nombre = listByEvent.getString("nombre");
                String apellido = listByEvent.getString("apellido");

                if (!evento.equals(eventoActual)) {
                    eventoActual = evento;
                    System.out.println("\n🗂 Evento: " + evento);
                }
                System.out.println("   - " + nombre + " " + apellido + " (" + dni + ")");
            }

            // Cierra la conexión
            connection.close();

        } catch (Exception e) {
            System.out.println(" Error: " + e.getMessage());
        }
    }
}