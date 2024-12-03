<?php


require 'DBManager.php';

header('Content-Type: application/json');

$db = new DBManager();

try {
    $method = $_SERVER['REQUEST_METHOD']; // Determina el método HTTP

    switch ($method) {
        case 'GET':
            // Obtener likes o un like específico según los parámetros
            if (isset($_GET['id'])) {
                $id = $_GET['id'];
                $likes = $db->getLikeById($id);
            } elseif (isset($_GET['idContenido'])) {
                $idContenido = $_GET['idContenido'];
                $likes = $db->getLikesByContentId($idContenido);
            } elseif (isset($_GET['idUsuario'])) {
                $idUsuario = $_GET['idUsuario'];
                $likes = $db->getLikesByUserId($idUsuario);
            } else {
                $likes = $db->getLikes();
            }

            // if (empty($likes)) {
            //     throw new Exception("No se encontraron likes");
            // }

            echo json_encode([
                'OK' => true,
                'message' => 'Likes obtenidos correctamente',
                'data' => $likes
            ]);
            break;

        case 'POST':
            // Crear un nuevo like
            $input = json_decode(file_get_contents('php://input'), true);

            if (!isset($input['idUsuario']) || !isset($input['idContenido'])) {
                throw new Exception("Faltan datos necesarios para crear el like");
            }

            $idUsuario = $input['idUsuario'];   
            $idContenido = $input['idContenido'];

            $result = $db->createLike($idUsuario, $idContenido);

            echo json_encode([
                'OK' => true,
                'message' => 'Like creado correctamente',
                'data' => $result
            ]);
            break;

        case 'PUT':
            // Actualizar un like existente
            $input = json_decode(file_get_contents('php://input'), true);

            if (!isset($input['id']) || !isset($input['idContenido']) || !isset($input['idUsuario'])) {
                throw new Exception("Faltan datos necesarios para actualizar el like");
            }

            $id = $input['id'];
            $idContenido = $input['idContenido'];
            $idUsuario = $input['idUsuario'];

            //$result = $db->updateLike($id, $idContenido, $idUsuario);

            echo json_encode([
                'OK' => true,
                'message' => 'Like actualizado correctamente',
                'data' => $result
            ]);
            break;
            
        case 'DELETE':
            // Eliminar un like existente
            if (!isset($_GET['idLike'])) {
                throw new Exception("Faltan datos necesarios para eliminar el like");
            }

            $id = $_GET['idLike'];

            $result = $db->deleteLike($id);

            echo json_encode([
                'OK' => true,
                'message' => 'Like eliminado correctamente',
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