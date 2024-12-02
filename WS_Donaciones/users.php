<?php

require 'DBManager.php';

header('Content-Type: application/json');

$db = new DBManager();

try {
    $method = $_SERVER['REQUEST_METHOD']; // Determina el método HTTP

    switch ($method) {
        case 'GET':
            // Si se envía un ID como parámetro, obtenemos un usuario específico
            if (isset($_GET['id'])) {
                $id = $_GET['id'];
                $users = $db->getUserById($id);
            } else {
                // Si no hay ID, obtenemos todos los usuarios
                $users = $db->getUsers();
            }

            echo json_encode([
                'OK' => true,
                'message' => 'Usuarios obtenidos correctamente',
                'data' => $users
            ]);
            break;

        case 'POST':
            // Leemos los datos enviados en el cuerpo de la petición
            $input = json_decode(file_get_contents('php://input'), true);

            if (!isset($input['nombre']) || !isset($input['correo']) || !isset($input['contrasena'])) {
                throw new Exception("Faltan datos necesarios para crear el usuario");
            }

            $name = $input['nombre'];
            $email = $input['correo'];
            $password = $input['contrasena'];

            $result = $db->createUser($name, $email, $password);

            echo json_encode([
                'OK' => true,
                'message' => 'Usuario creado correctamente',
                'data' => $result
            ]);
            break;

        case 'PUT':
            // Leer datos enviados para actualizar un usuario
            $input = json_decode(file_get_contents('php://input'), true);

            if (!isset($input['idUsuario'])) {
                throw new Exception("Faltan datos necesarios para actualizar el usuario");
            }

            $id = $input['idUsuario'];
            // Si no se envían los datos, se mantienen los que ya tiene el usuario
            $name = $input['nombre'] ?? null;
            $email = $input['correo'] ?? null;
            $password = $input['contrasena'] ?? null;

            $result = $db->updateUser($id, $name, $email, $password);

            echo json_encode([
                'OK' => true,
                'message' => 'Usuario actualizado correctamente',
                'data' => $result
            ]);
            break;

        case 'DELETE':
            // Leer el ID del usuario a eliminar
            $input = json_decode(file_get_contents('php://input'), true);

            if (!isset($input['id'])) {
                throw new Exception("Falta el ID del usuario a eliminar");
            }

            $id = $input['id'];

            //$result = $db->deleteUser($id);

            echo json_encode([
                'OK' => true,
                'message' => 'Usuario eliminado correctamente',
                'data' => $result
            ]);
            break;

        default:
            throw new Exception("Método no permitido");
    }
} catch (Exception $e) {
    // Enviar una respuesta con error en formato JSON
    echo json_encode([
        'OK' => false,
        'message' => 'Error en la operación',
        'error' => $e->getMessage()
    ]);
}
