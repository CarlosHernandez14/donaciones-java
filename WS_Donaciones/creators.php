<?php

require 'DBManager.php';

header('Content-Type: application/json');

$db = new DBManager();

try {
    $method = $_SERVER['REQUEST_METHOD']; // Determina el método HTTP

    switch ($method) {
        case 'GET':
            // Obtener creadores o un creador específico según los parámetros
            if (isset($_GET['id'])) {
                $id = $_GET['id'];
                $creators = $db->getCreatorById($id);
            } elseif (isset($_GET['idUsuario'])) {
                $userId = $_GET['idUsuario'];
                $creators = $db->getCreatorByUserId($userId);
            } else {
                $creators = $db->getCreators();
            }

            // if (empty($creators)) {
            //     throw new Exception("No se encontraron creadores");
            // }

            echo json_encode([
                'OK' => true,
                'message' => 'Creadores obtenidos correctamente',
                'data' => $creators
            ]);
            break;

        case 'POST':
            // Crear un nuevo creador
            $input = json_decode(file_get_contents('php://input'), true);

            if (!isset($input['idUsuario'])) {
                throw new Exception("Faltan datos necesarios para crear el creador");
            }

            $userId = $input['idUsuario'];

            $result = $db->createCreator($userId);

            echo json_encode([
                'OK' => true,
                'message' => 'Creador creado correctamente',
                'data' => $result
            ]);
            break;

        case 'PUT':
            // Actualizar un creador existente
            $input = json_decode(file_get_contents('php://input'), true);

            if (!isset($input['idCreador'])) {
                throw new Exception("Faltan datos necesarios para actualizar el creador");
            }

            $id = $input['idCreador'];
            $cuenta_bloqueada = $input['cuenta_bloqueada'] ?? null;
            $partner = $input['partner'] ?? null;

            $result = $db->updateCreator($id, $cuenta_bloqueada, $partner);

            echo json_encode([
                'OK' => true,
                'message' => 'Creador actualizado correctamente',
                'data' => $result
            ]);
            break;

        case 'DELETE':
            // Eliminar un creador existente
            $input = json_decode(file_get_contents('php://input'), true);

            if (!isset($input['id'])) {
                throw new Exception("Falta el ID del creador a eliminar");
            }

            $id = $input['id'];

            //$result = $db->deleteCreator($id);

            echo json_encode([
                'OK' => true,
                'message' => 'Creador eliminado correctamente',
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
