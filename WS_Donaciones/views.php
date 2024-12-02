<?php

require 'DBManager.php';

header('Content-Type: application/json');

$db = new DBManager();

try {
    $method = $_SERVER['REQUEST_METHOD']; // Determina el método HTTP

    switch ($method) {
        case 'GET':
            // Obtener vistas o una vista específica según los parámetros
            if (isset($_GET['id'])) {
                $id = $_GET['id'];
                $views = $db->getViewById($id);
            } elseif (isset($_GET['idContenido'])) {
                $idContenido = $_GET['idContenido'];
                $views = $db->getViewsByContentId($idContenido);
            } elseif (isset($_GET['idUsuario'])) {
                $idUsuario = $_GET['idUsuario'];
                $views = $db->getViewsByUserId($idUsuario);
            } else {
                $views = $db->getViews();
            }

            // if (empty($views)) {
            //     throw new Exception("No se encontraron vistas");
            // }

            echo json_encode([
                'OK' => true,
                'message' => 'Vistas obtenidas correctamente',
                'data' => $views
            ]);
            break;

        case 'POST':
            // Crear una nueva vista
            $input = json_decode(file_get_contents('php://input'), true);

            if (!isset($input['idContenido']) || !isset($input['idUsuario'])) {
                throw new Exception("Faltan datos necesarios para crear la vista");
            }

            $idContenido = $input['idContenido'];
            $idUsuario = $input['idUsuario'];

            //$result = $db->createView($idContenido, $idUsuario);

            echo json_encode([
                'OK' => true,
                'message' => 'Vista creada correctamente',
                'data' => $result
            ]);
            break;

        case 'PUT':
            // Actualizar una vista existente
            $input = json_decode(file_get_contents('php://input'), true);

            if (!isset($input['id']) || !isset($input['idContenido']) || !isset($input['idUsuario'])) {
                throw new Exception("Faltan datos necesarios para actualizar la vista");
            }

            $id = $input['id'];
            $idContenido = $input['idContenido'];
            $idUsuario = $input['idUsuario'];

            //$result = $db->updateView($id, $idContenido, $idUsuario);

            echo json_encode([
                'OK' => true,
                'message' => 'Vista actualizada correctamente',
                'data' => $result
            ]);

            break;

        case 'DELETE':
            // Eliminar una vista existente
            $input = json_decode(file_get_contents('php://input'), true);

            if (!isset($input['id'])) {
                throw new Exception("Faltan datos necesarios para eliminar la vista");
            }

            $id = $input['id'];

            //$result = $db->deleteView($id);

            echo json_encode([
                'OK' => true,
                'message' => 'Vista eliminada correctamente',
                'data' => $result
            ]);

            break;

        default:
            throw new Exception("Método HTTP no soportado");
    }
} catch (Exception $e) {
    echo json_encode([
        'OK' => false,
        'message' => $e->getMessage()
    ]);
}


?>