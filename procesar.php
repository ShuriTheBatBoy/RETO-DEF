<?php
// Colocar este archivo, por ejemplo en C:\xampp\htdocs\miweb
// luego en el formulario poner action="http://localhost/miweb/procesar.php"
// Datos de conexión
$servername = "localhost";
$username   = "root";
$password   = "";
$dbname     = "mou5labs";

// Recoger dato del formulario, podría ser un get ($_GET)
if (isset($_POST["dni"]) && isset($_POST["apellido"]) && isset($_POST["nombre"]) && isset($_POST["mail"]) 
    && isset($_POST["telefono"]) && isset($_POST["dia"]) && isset($_POST["eleccion"])) {

    $dni = $_POST["dni"];
    $nombre = $_POST["nombre"];
    $apellido = $_POST["apellido"];
    $mail = $_POST["mail"];
    $telefono = $_POST["telefono"];
    $dia = $_POST["dia"];
    $eleccion = $_POST["eleccion"];
    $empresa = isset($_POST["empresa"]) ? $_POST["empresa"] : "";
    $comentarios = isset($_POST["comentarios"]) ? $_POST["comentarios"] : "";

} else {
    die("Faltan datos del formulario");
}

// Conectar a MySQL
$conn = new mysqli($servername, $username, $password, $dbname);

// Comprobar conexión
if ($conn->connect_error) {
    die("Error de conexión: " . $conn->connect_error);
}

// Evitar insertar vacío
if ($dni === "" || $nombre === "" || $apellido === "" || $mail === "" || $dia === "" || $eleccion === "") {
    die("El dni/nombre/apellido/mail/día/elección no puede estar vacío");
}

// Consulta SQL para insertar
$sql = "INSERT INTO inscripciones (dni, nombre, apellido, mail, telefono, dia, eleccion, empresa, comentarios) 
        VALUES ('$dni', '$nombre', '$apellido', '$mail', '$telefono', '$dia', '$eleccion', '$empresa', '$comentarios')";

// Ejecutar
if ($conn->query($sql) === TRUE) {
    echo "<h2>¡Registro correcto!</h2>";
    echo "<p><strong>DNI:</strong> " . htmlspecialchars($dni) . "</p>";
    echo "<p><strong>Nombre:</strong> " . htmlspecialchars($nombre) . "</p>";
    echo "<p><strong>Apellido:</strong> " . htmlspecialchars($apellido) . "</p>";
    echo "<p><strong>Correo:</strong> " . htmlspecialchars($mail) . "</p>";
    echo "<p><strong>Teléfono:</strong> " . htmlspecialchars($telefono) . "</p>";
    echo "<p><strong>Día:</strong> " . htmlspecialchars($dia) . "</p>";
    echo "<p><strong>Elección:</strong> " . htmlspecialchars($eleccion) . "</p>";
    echo "<p><strong>Empresa:</strong> " . htmlspecialchars($empresa) . "</p>";
    echo "<p><strong>Comentarios:</strong> " . htmlspecialchars($comentarios) . "</p>";
    echo '<p><a href="javascript:history.back()">Volver</a></p>';
} else {
    echo "Error: " . $conn->error;
}

// Cerrar conexión
$conn->close();
?>
