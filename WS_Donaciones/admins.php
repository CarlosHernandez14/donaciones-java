<?php

require 'DBManager.php';

header('Content-Type: application/json');

$db = new DBManager();

try {
    $method = $_SERVER['REQUEST_METHOD']; // Determina el método HTTP

    switch ($method) {
        case 'GET':
            // Obtener administradores o un administrador específico según los parámetros
            if (isset($_GET['id'])) {
                $id = $_GET['id'];
                $admins = $db->getAdminById($id);
            } elseif (isset($_GET['idUsuario'])) {
                $idUsuario = $_GET['idUsuario'];
                $admins = $db->getAdminByUserId($idUsuario);
            } else {
                $admins = $db->getAdmins();
            }

            // if (empty($admins)) {
            //     throw new Exception("No se encontraron administradores");
            // }

            echo json_encode([
                'OK' => true,
                'message' => 'Administradores obtenidos correctamente',
                'data' => $admins
            ]);
            break;

        case 'POST':
            // Crear un nuevo administrador
            $input = json_decode(file_get_contents('php://input'), true);

            if (!isset($input['idUsuario'])) {
                throw new Exception("Faltan datos necesarios para crear el administrador");
            }

            $userId = $input['idUsuario'];

            $result = $db->createAdmin($userId);

            echo json_encode([
                'OK' => true,
                'message' => 'Administrador creado correctamente',
                'data' => $result
            ]);
            break;

        case 'PUT':
            // Actualizar un administrador existente
            $input = json_decode(file_get_contents('php://input'), true);

            if (!isset($input['id']) || !isset($input['name']) || !isset($input['userId'])) {
                throw new Exception("Faltan datos necesarios para actualizar el administrador");
            }

            $id = $input['id'];
            $name = $input['name'];
            $userId = $input['userId'];

            //$result = $db->updateAdmin($id, $name, $userId);

            echo json_encode([
                'OK' => true,
                'message' => 'Administrador actualizado correctamente',
                'data' => $result
            ]);
            break;

        case 'DELETE':
            // Eliminar un administrador existente
            $input = json_decode(file_get_contents('php://input'), true);

            if (!isset($input['id'])) {
                throw new Exception("Falta el ID del administrador a eliminar");
            }

            $id = $input['id'];

            //$result = $db->deleteAdmin($id);

            echo json_encode([
                'OK' => true,
                'message' => 'Administrador eliminado correctamente',
                'data' => $result
            ]);
            break;

        default:
            throw new Exception("Método no permitido");
    }
} catch (Exception $e) {
    // Manejo de errores
    echo json_encode([
        'OK' => false,
        'message' => 'Error al procesar la petición',
        'error' => $e->getMessage()
    ]);
}

?>
