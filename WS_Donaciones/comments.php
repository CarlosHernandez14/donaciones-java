<?php


require 'DBManager.php';

header('Content-Type: application/json');

$db = new DBManager();

try {
    $method = $_SERVER['REQUEST_METHOD']; // Determina el método HTTP

    switch ($method) {
        case 'GET':
            // Obtener comentarios o un comentario específico según los parámetros
            if (isset($_GET['id'])) {
                $id = $_GET['id'];
                $comments = $db->getCommentById($id);
            } elseif (isset($_GET['idContenido'])) {
                $idContenido = $_GET['idContenido'];
                $comments = $db->getCommentsByContentId($idContenido);
            } elseif (isset($_GET['idUsuario'])) {
                $idUsuario = $_GET['idUsuario'];
                $comments = $db->getCommentsByUserId($idUsuario);
            } else {
                $comments = $db->getComments();
            }

            // if (empty($comments)) {
            //     throw new Exception("No se encontraron comentarios");
            // }

            echo json_encode([
                'OK' => true,
                'message' => 'Comentarios obtenidos correctamente',
                'data' => $comments
            ]);
            break;

        case 'POST':
            // Crear un nuevo comentario
            $input = json_decode(file_get_contents('php://input'), true);

            if (!isset($input['content']) || !isset($input['userId'])) {
                throw new Exception("Faltan datos necesarios para crear el comentario");
            }

            $content = $input['content'];
            $userId = $input['userId'];

            //$result = $db->createComment($content, $userId);

            echo json_encode([
                'OK' => true,
                'message' => 'Comentario creado correctamente',
                'data' => $result
            ]);
            break;

        case 'PUT':
            // Actualizar un comentario existente
            $input = json_decode(file_get_contents('php://input'), true);

            if (!isset($input['id']) || !isset($input['content']) || !isset($input['userId'])) {
                throw new Exception("Faltan datos necesarios para actualizar el comentario");
            }

            $id = $input['id'];
            $content = $input['content'];
            $userId = $input['userId'];

            //$result = $db->updateComment($id, $content, $userId);

            echo json_encode([
                'OK' => true,
                'message' => 'Comentario actualizado correctamente',
                'data' => $result
            ]);
            break;

        case 'DELETE':
            // Eliminar un comentario existente
            $input = json_decode(file_get_contents('php://input'), true);

            if (!isset($input['id'])) {
                throw new Exception("Falta el ID del comentario a eliminar");
            }

            $id = $input['id'];

            //$result = $db->deleteComment($id);

            echo json_encode([
                'OK' => true,
                'message' => 'Comentario eliminado correctamente',
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