import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class JavaJDBC {
    public static void main(String[] args) {
        try {
            // Carga el driver JDBC de MySQL
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establece la conexión con la base de datos
            Connection connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/eventos", "root", ""
            );

            // Crea el Statement para ejecutar consultas
            Statement statement = connection.createStatement();

            System.out.println("=== ESTADÍSTICAS DE EVENTOS ===\n");

            // 1. CANTIDAD TOTAL DE PERSONAS INSCRITAS
            ResultSet totalPersonas = statement.executeQuery(
                "SELECT COUNT(DISTINCT persona_id) AS total FROM inscripciones"
            );
            if (totalPersonas.next()) {
                System.out.println("📊 Total de personas inscritas: " + totalPersonas.getInt("total"));
            }
            System.out.println();

            // 2. NÚMERO DE PERSONAS INSCRITAS POR EVENTO
            System.out.println("Inscritos por evento:");
            System.out.println("----------------------------------------");
            ResultSet inscritosPorEvento = statement.executeQuery(
                "SELECT e.nombre, COUNT(i.persona_id) AS cantidad " +
                "FROM eventos e " +
                "LEFT JOIN inscripciones i ON e.id = i.evento_id " +
                "GROUP BY e.id, e.nombre"
            );
            while (inscritosPorEvento.next()) {
                System.out.println(inscritosPorEvento.getString("nombre") + 
                    ": " + inscritosPorEvento.getInt("cantidad") + " personas");
            }
            System.out.println();

            // 3. LISTADO DE PERSONAS POR EVENTO
            System.out.println("Listado de personas por evento:");
            System.out.println("========================================");
            ResultSet personasPorEvento = statement.executeQuery(
                "SELECT e.nombre AS evento, p.nombre AS persona, p.email " +
                "FROM eventos e " +
                "LEFT JOIN inscripciones i ON e.id = i.evento_id " +
                "LEFT JOIN personas p ON i.persona_id = p.id " +
                "ORDER BY e.nombre, p.nombre"
            );
            
            String eventoActual = "";
            while (personasPorEvento.next()) {
                String evento = personasPorEvento.getString("evento");
                String persona = personasPorEvento.getString("persona");
                String email = personasPorEvento.getString("email");
                
                if (!evento.equals(eventoActual)) {
                    eventoActual = evento;
                    System.out.println("\n🎯 " + evento);
                }
                
                if (persona != null) {
                    System.out.println("   • " + persona + " (" + email + ")");
                }
            }

            // Cierra la conexión
            connection.close();

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}