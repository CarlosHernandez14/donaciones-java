<?php

require 'DBManager.php';

header('Content-Type: application/json');

$db = new DBManager();

try {
    $method = $_SERVER['REQUEST_METHOD']; // Determina el método HTTP

    switch ($method) {
        case 'GET':
            // Obtener suscripciones o una suscripción específica según los parámetros
            if (isset($_GET['id'])) {
                $id = $_GET['id'];
                $suscriptions = $db->getSubscriptionById($id);
            } elseif (isset($_GET['idUsuario'])) {
                $idUsuario = $_GET['idUsuario'];
                $suscriptions = $db->getSubscriptionsByUserId($idUsuario);
            } else {
                $suscriptions = $db->getSubscriptions();
            }

            // if (empty($suscriptions)) {
            //     throw new Exception("No se encontraron suscripciones");
            // }

            echo json_encode([
                'OK' => true,
                'message' => 'Suscripciones obtenidas correctamente',
                'data' => $suscriptions
            ]);
            break;

        case 'POST':
            // Crear una nueva suscripción
            $input = json_decode(file_get_contents('php://input'), true);

            if (!isset($input['idCreador']) || !isset($input['idUsuario'])) {
                throw new Exception("Faltan datos necesarios para crear la suscripción");
            }

            $idCreador = $input['idCreador'];
            $idUsuario = $input['idUsuario'];

            $result = $db->createSubscription($idCreador, $idUsuario);

            echo json_encode([
                'OK' => true,
                'message' => 'Suscripción creada correctamente',
                'data' => $result
            ]);
            break;

        case 'PUT':
            // Actualizar una suscripción existente
            $input = json_decode(file_get_contents('php://input'), true);

            if (!isset($input['id']) || !isset($input['idContenido']) || !isset($input['idUsuario'])) {
                throw new Exception("Faltan datos necesarios para actualizar la suscripción");
            }

            $id = $input['id'];
            $idContenido = $input['idContenido'];
            $idUsuario = $input['idUsuario'];

            //$result = $db->updateSuscription($id, $idContenido, $idUsuario);

            echo json_encode([
                'OK' => true,
                'message' => 'Suscripción actualizada correctamente',
                'data' => $result
            ]);
            break;

        case 'DELETE':
            // Eliminar una suscripción existente
            $input = json_decode(file_get_contents('php://input'), true);

            if (!isset($input['idSuscripcion'])) {
                throw new Exception("Faltan datos necesarios para eliminar la suscripción");
            }

            $id = $input['idSuscripcion'];

            $result = $db->deleteSubscription($id);

            echo json_encode([
                'OK' => true,
                'message' => 'Suscripción eliminada correctamente',
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