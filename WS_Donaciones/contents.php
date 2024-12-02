<?php

require 'DBManager.php';

header('Content-Type: application/json');

$db = new DBManager();

try {
    $method = $_SERVER['REQUEST_METHOD']; // Determina el método HTTP

    switch ($method) {
        case 'GET':
            // Obtener contenidos o un contenido específico según los parámetros
            if (isset($_GET['id'])) {
                $id = $_GET['id'];
                $contents = $db->getContentById($id);
            } elseif (isset($_GET['idCreador'])) {
                $userId = $_GET['idCreador'];
                $contents = $db->getContentByCreatorId($userId);
            } else {
                $contents = $db->getContents();
            }

            // if (empty($contents)) {
            //     throw new Exception("No se encontraron contenidos");
            // }

            echo json_encode([
                'OK' => true,
                'message' => 'Contenidos obtenidos correctamente',
                'data' => $contents
            ]);
            break;

        case 'POST':
            // Crear un nuevo contenido
            $input = json_decode(file_get_contents('php://input'), true);

            if (!isset($input['name']) || !isset($input['userId'])) {
                throw new Exception("Faltan datos necesarios para crear el contenido");
            }

            $name = $input['name'];
            $userId = $input['userId'];

            //$result = $db->createContent($name, $userId);

            echo json_encode([
                'OK' => true,
                'message' => 'Contenido creado correctamente',
                'data' => $result
            ]);
            break;

        case 'PUT':
            // Actualizar un contenido existente
            $input = json_decode(file_get_contents('php://input'), true);

            if (!isset($input['id']) || !isset($input['name']) || !isset($input['userId'])) {
                throw new Exception("Faltan datos necesarios para actualizar el contenido");
            }

            $id = $input['id'];
            $name = $input['name'];
            $userId = $input['userId'];

            //$result = $db->updateContent($id, $name, $userId);

            echo json_encode([
                'OK' => true,
                'message' => 'Contenido actualizado correctamente',
                'data' => $result
            ]);
            break;

        case 'DELETE':
            // Eliminar un contenido existente
            $input = json_decode(file_get_contents('php://input'), true);

            if (!isset($input['id'])) {
                throw new Exception("Falta el ID del contenido a eliminar");
            }

            $id = $input['id'];

            //$result = $db->deleteContent($id);

            echo json_encode([
                'OK' => true,
                'message' => 'Contenido eliminado correctamente',
                'data' => $result
            ]);

            break;

        default:
            throw new Exception("Método no permitido");
    }
} catch (Exception $e) {
    echo json_encode([
        'OK' => false,
        'message' => $e->getMessage()
    ]);
}


?>