<?php

    class DBManager {
        private $db;
        private $host;
        private $user;
        private $pass;
        private $port;


        public function __construct() {
            $this->db = "donaciones";
            $this->host = "localhost";
            $this->user = "root";
            $this->pass = null;
            $this->port = 3306;
        }

        public function open () {
            $link = mysqli_connect(
                $this->host, $this->user, $this->pass, $this->db, $this->port
            ) or die("No se pudo abrir la coneccion a la base de datos");
            return $link;
        }

        public function close($link) {
            mysqli_close($link);
        }

        // FUNCIONES PARA MANEJO DE USUARIOS EN LA BASE DE DATOS
        // Obtener todos los usuarios
        public function getUsers() {
            $link = $this->open();
            $query = "SELECT * FROM usuarios";
            $result = mysqli_query($link, $query);
            
            if ($result) {
                $users = [];
                while ($row = mysqli_fetch_assoc($result)) {
                    $users[] = $row;
                }

                $this->close($link);

                return $users;
            } else {
                $this->close($link);
                die("Error al obtener los usuarios");
            }
        }

        // Obtener un usuario por su id
        public function getUserById($id) {
            $link = $this->open();
            $query = "SELECT * FROM usuarios WHERE idUsuario = '$id'";
            
            $result = mysqli_query($link, $query);
            
            if ($result) {
                $user = mysqli_fetch_assoc($result);
                $this->close($link);
                return $user;
            } else {
                $this->close($link);
                die("Error al obtener el usuario");
            }
        }

        // Funcion para crear un usuario
        public function createUser($name, $email, $password) {
            $link = $this->open();
            
            // Modificamos la consulta para que retorne el UUID generado
            $query = "INSERT INTO usuarios (idUsuario, nombre, correo, contrasena) 
                      VALUES (UUID(), '$name', '$email', SHA1('$password'))";
            
            $result = mysqli_query($link, $query);
            
            if ($result) {
                // Obtenemos el último UUID insertado
                $query = "SELECT idUsuario FROM usuarios WHERE correo = '$email' LIMIT 1";
                $result = mysqli_query($link, $query);
                
                if ($result) {
                    $row = mysqli_fetch_assoc($result);
                    $id = $row['idUsuario'];
                    $this->close($link);
                    return $id; // Devolvemos el UUID insertado
                } else {
                    $this->close($link);
                    die("Error al obtener el ID del usuario");
                }
            } else {
                $this->close($link);
                die("Error al crear el usuario");
            }
        }

        // Funcion para actualizar un usuario
        public function updateUser($id, $name = null, $email = null, $password = null) {
            $link = $this->open();

            // Consultamos si el usuario existe
            $query = "SELECT * FROM usuarios WHERE idUsuario = '$id'";
            $result = mysqli_query($link, $query);

            $currentUser = mysqli_fetch_assoc($result);

            if (!$currentUser) {
                $this->close($link);
                die("El usuario no existe");
            }

            // Si no se envia un campo, se mantiene el valor actual
            if (!$name) {
                $name = $currentUser['nombre'];
            }

            if (!$email) {
                $email = $currentUser['correo'];
            }

            if (!$password) {
                $password = $currentUser['contrasena'];
            }

            // Actualizamos el usuario

            $query = "UPDATE usuarios SET nombre = '$name', correo = '$email', contrasena = SHA1('$password') WHERE idUsuario = '$id'";
            $result = mysqli_query($link, $query);
            
            if ($result) {
                $this->close($link);
                // Devolvemos el ID del usuario actualizado
                return $id;
            } else {
                $this->close($link);
                die("Error al actualizar el usuario");
            }
        }




        /////////////////////////////////////////////////////////////////////////////////////
        // FUNCIONES PARA MANEJO DE ADMINISTRADORES EN LA BASE DE DATOS
        // Obtener todos los administradores
        public function getAdmins() {
            $link = $this->open();
            $query = "SELECT * FROM administradores";
            $result = mysqli_query($link, $query);
            
            if ($result) {
                $admins = [];
                while ($row = mysqli_fetch_assoc($result)) {
                    $admins[] = $row;
                }

                $this->close($link);

                return $admins;
            } else {
                $this->close($link);
                die("Error al obtener los administradores");
            }
        }

        // Obtener un administrador por su id
        public function getAdminById($id) {
            $link = $this->open();
            $query = "SELECT * FROM administradores WHERE idAdministrador = '$id'";
            
            $result = mysqli_query($link, $query);
            
            if ($result) {
                $admin = mysqli_fetch_assoc($result);
                $this->close($link);
                return $admin;
            } else {
                $this->close($link);
                die("Error al obtener el administrador");
            }
        }

        // Obtener un administrador por idUsuario
        public function getAdminByUserId($idUsuario) {
            $link = $this->open();
            $query = "SELECT * FROM administradores WHERE idUsuario = '$idUsuario'";
            
            $result = mysqli_query($link, $query);
            
            if ($result) {
                $admin = mysqli_fetch_assoc($result);
                $this->close($link);
                return $admin;
            } else {
                $this->close($link);
                die("Error al obtener el administrador");
            }
        }

        // Funcion para crear un administrador
        public function createAdmin($idUsuario) {
            $link = $this->open();
            
            // Modificamos la consulta para que retorne el UUID generado
            $query = "INSERT INTO administradores (idAdministrador, idUsuario) 
                      VALUES (UUID(), '$idUsuario')";

            $result = mysqli_query($link, $query);

            if ($result) {
                // Obtenemos el último UUID insertado
                $query = "SELECT idAdministrador FROM administradores WHERE idUsuario = '$idUsuario' LIMIT 1";
                $result = mysqli_query($link, $query);
                
                if ($result) {
                    $row = mysqli_fetch_assoc($result);
                    $id = $row['idAdministrador'];
                    $this->close($link);
                    return $id; // Devolvemos el UUID insertado
                } else {
                    $this->close($link);
                    die("Error al obtener el ID del administrador");
                }
            } else {
                $this->close($link);
                die("Error al crear el administrador");
            }
        }

        /////////////////////////////////////////////////////////////////////////////////////
        // FUNCIONES PARA MANEJO DE CREADORES EN LA BASE DE DATOS
        // Obtener todos los creadores
        public function getCreators() {
            $link = $this->open();
            $query = "SELECT * FROM creador";
            $result = mysqli_query($link, $query);
            
            if ($result) {
                $creators = [];
                while ($row = mysqli_fetch_assoc($result)) {
                    $creators[] = $row;
                }

                $this->close($link);

                return $creators;
            } else {
                $this->close($link);
                die("Error al obtener los creadores");
            }
        }

        // Obtener un creador por su id
        public function getCreatorById($id) {
            $link = $this->open();
            $query = "SELECT * FROM creador WHERE idCreador = '$id'";
            
            $result = mysqli_query($link, $query);
            
            if ($result) {
                $creator = mysqli_fetch_assoc($result);
                $this->close($link);
                return $creator;
            } else {
                $this->close($link);
                die("Error al obtener el creador");
            }
        }

        // Obtener un creador por idUsuario
        public function getCreatorByUserId($idUsuario) {
            $link = $this->open();
            $query = "SELECT * FROM creador WHERE idUsuario = '$idUsuario'";
            
            $result = mysqli_query($link, $query);
            
            if ($result) {
                $creator = mysqli_fetch_assoc($result);
                $this->close($link);
                return $creator;
            } else {
                $this->close($link);
                die("Error al obtener el creador");
            }
        }

        // Funcion para crear un creador
        public function createCreator($idUsuario) {
            $link = $this->open();
            
            // Modificamos la consulta para que retorne el UUID generado
            $query = "INSERT INTO creador (idCreador, idUsuario) 
                      VALUES (UUID(), '$idUsuario')";

            $result = mysqli_query($link, $query);

            if ($result) {
                // Obtenemos el último UUID insertado
                $query = "SELECT idCreador FROM creador WHERE idUsuario = '$idUsuario' LIMIT 1";
                $result = mysqli_query($link, $query);
                
                if ($result) {
                    $row = mysqli_fetch_assoc($result);
                    $id = $row['idCreador'];
                    $this->close($link);
                    return $id; // Devolvemos el UUID insertado
                } else {
                    $this->close($link);
                    die("Error al obtener el ID del creador");
                }
            } else {
                $this->close($link);
                die("Error al crear el creador");
            }
        }

        // Funcion para actualizar un creador
        public function updateCreator($id, $cuenta_bloqueada = null, $partner = null) {
            $link = $this->open();

            // Consultamos si el creador existe
            $query = "SELECT * FROM creador WHERE idCreador = '$id'";
            $result = mysqli_query($link, $query);

            $currentCreator = mysqli_fetch_assoc($result);

            if (!$currentCreator) {
                $this->close($link);
                die("El creador no existe");
            }

            // Si no se envia un campo, se mantiene el valor actual
            if (!$cuenta_bloqueada) {
                $cuenta_bloqueada = $currentCreator['cuenta_bloqueada'];
            }

            if (!$partner) {
                $partner = $currentCreator['partner'];
            }

            // Actualizamos el creador

            $query = "UPDATE creador SET cuenta_bloqueada = '$cuenta_bloqueada', partner = '$partner' WHERE idCreador = '$id'";
            $result = mysqli_query($link, $query);
            
            if ($result) {
                $this->close($link);
                // Devolvemos el ID del creador actualizado
                return $id;
            } else {
                $this->close($link);
                die("Error al actualizar el creador");
            }
        }

        /////////////////////////////////////////////////////////////////////////////////////
        // FUNCIONES PARA MANEJO DE CONTENIDOS EN LA BASE DE DATOS
        // Obtener todos los contenidos
        public function getContents() {
            $link = $this->open();
            $query = "SELECT * FROM contenido";
            $result = mysqli_query($link, $query);
            
            if ($result) {
                $contents = [];
                while ($row = mysqli_fetch_assoc($result)) {
                    $contents[] = $row;
                }

                $this->close($link);

                return $contents;
            } else {
                $this->close($link);
                die("Error al obtener los contenidos");
            }
        }

        // Obtener un contenido por su id
        public function getContentById($id) {
            $link = $this->open();
            $query = "SELECT * FROM contenido WHERE idContenido = '$id'";
            
            $result = mysqli_query($link, $query);
            
            if ($result) {
                $content = mysqli_fetch_assoc($result);
                $this->close($link);
                return $content;
            } else {
                $this->close($link);
                die("Error al obtener el contenido");
            }
        }

        // Obtener un contenido por idCreador
        public function getContentByCreatorId($idCreador) {
            $link = $this->open();
            $query = "SELECT * FROM contenido WHERE idCreador = '$idCreador'";
            
            $result = mysqli_query($link, $query);
            
            if ($result) {
                $content = mysqli_fetch_assoc($result);
                $this->close($link);
                return $content;
            } else {
                $this->close($link);
                die("Error al obtener el contenido");
            }
        }

        /////////////////////////////////////////////////////////////////////////////////////
        // FUNCIONES PARA MANEJO DE COMENTARIOS EN LA BASE DE DATOS
        // Obtener todos los comentarios
        public function getComments() {
            $link = $this->open();
            $query = "SELECT * FROM comentarios";
            $result = mysqli_query($link, $query);
            
            if ($result) {
                $comments = [];
                while ($row = mysqli_fetch_assoc($result)) {
                    $comments[] = $row;
                }

                $this->close($link);

                return $comments;
            } else {
                $this->close($link);
                die("Error al obtener los comentarios");
            }
        }

        // Obtener un comentario por su id
        public function getCommentById($id) {
            $link = $this->open();
            $query = "SELECT * FROM comentarios WHERE idComentario = '$id'";
            
            $result = mysqli_query($link, $query);
            
            if ($result) {
                $comment = mysqli_fetch_assoc($result);
                $this->close($link);
                return $comment;
            } else {
                $this->close($link);
                die("Error al obtener el comentario");
            }
        }

        // Obtener comentarios por el idContenido
        public function getCommentsByContentId($idContenido) {
            $link = $this->open();
            $query = "SELECT * FROM comentarios WHERE idContenido = '$idContenido'";
            
            $result = mysqli_query($link, $query);
            
            if ($result) {
                $comments = [];
                while ($row = mysqli_fetch_assoc($result)) {
                    $comments[] = $row;
                }

                $this->close($link);

                return $comments;
            } else {
                $this->close($link);
                die("Error al obtener los comentarios");
            }
        }

        // Obtener comentarios por el idUsuario
        public function getCommentsByUserId($idUsuario) {
            $link = $this->open();
            $query = "SELECT * FROM comentarios WHERE idUsuario = '$idUsuario'";
            
            $result = mysqli_query($link, $query);
            
            if ($result) {
                $comments = [];
                while ($row = mysqli_fetch_assoc($result)) {
                    $comments[] = $row;
                }

                $this->close($link);

                return $comments;
            } else {
                $this->close($link);
                die("Error al obtener los comentarios");
            }
        }

        /////////////////////////////////////////////////////////////////////////////////////

        // METODOS PARA MANEJAR LOS LIKES DE LOS CONTENIDOS EN LA BASE DE DATOS
        // Obtener todos los likes
        public function getLikes() {
            $link = $this->open();
            $query = "SELECT * FROM likes";
            $result = mysqli_query($link, $query);
            
            if ($result) {
                $likes = [];
                while ($row = mysqli_fetch_assoc($result)) {
                    $likes[] = $row;
                }

                $this->close($link);

                return $likes;
            } else {
                $this->close($link);
                die("Error al obtener los likes");
            }
        }

        // Obtener un like por su id
        public function getLikeById($id) {
            $link = $this->open();
            $query = "SELECT * FROM likes WHERE idLike = '$id'";
            
            $result = mysqli_query($link, $query);
            
            if ($result) {
                $like = mysqli_fetch_assoc($result);
                $this->close($link);
                return $like;
            } else {
                $this->close($link);
                die("Error al obtener el like");
            }
        }

        // Obtener likes por el idContenido
        public function getLikesByContentId($idContenido) {
            $link = $this->open();
            $query = "SELECT * FROM likes WHERE idContenido = '$idContenido'";
            
            $result = mysqli_query($link, $query);
            
            if ($result) {
                $likes = [];
                while ($row = mysqli_fetch_assoc($result)) {
                    $likes[] = $row;
                }

                $this->close($link);

                return $likes;
            } else {
                $this->close($link);
                die("Error al obtener los likes");
            }
        }

        // Obtener likes por el idUsuario
        public function getLikesByUserId($idUsuario) {
            $link = $this->open();
            $query = "SELECT * FROM likes WHERE idUsuario = '$idUsuario'";
            
            $result = mysqli_query($link, $query);
            
            if ($result) {
                $likes = [];
                while ($row = mysqli_fetch_assoc($result)) {
                    $likes[] = $row;
                }

                $this->close($link);

                return $likes;
            } else {
                $this->close($link);
                die("Error al obtener los likes");
            }
        }

        /////////////////////////////////////////////////////////////////////////////////////

        // METODOS PARA MANEJAR LAS VISUALIZACIONES DE LOS CONTENIDOS EN LA BASE DE DATOS
        // Obtener todas las visualizaciones
        public function getViews() {
            $link = $this->open();
            $query = "SELECT * FROM visualizaciones";
            $result = mysqli_query($link, $query);
            
            if ($result) {
                $views = [];
                while ($row = mysqli_fetch_assoc($result)) {
                    $views[] = $row;
                }

                $this->close($link);

                return $views;
            } else {
                $this->close($link);
                die("Error al obtener las visualizaciones");
            }
        }

        // Obtener una visualizacion por su id
        public function getViewById($id) {
            $link = $this->open();
            $query = "SELECT * FROM visualizaciones WHERE idVisualizacion = '$id'";
            
            $result = mysqli_query($link, $query);
            
            if ($result) {
                $view = mysqli_fetch_assoc($result);
                $this->close($link);
                return $view;
            } else {
                $this->close($link);
                die("Error al obtener la visualizacion");
            }
        }

        // Obtener visualizaciones por el idContenido
        public function getViewsByContentId($idContenido) {
            $link = $this->open();
            $query = "SELECT * FROM visualizaciones WHERE idContenido = '$idContenido'";
            
            $result = mysqli_query($link, $query);
            
            if ($result) {
                $views = [];
                while ($row = mysqli_fetch_assoc($result)) {
                    $views[] = $row;
                }

                $this->close($link);

                return $views;
            } else {
                $this->close($link);
                die("Error al obtener las visualizaciones");
            }
        }

        // Obtener visualizaciones por el idUsuario
        public function getViewsByUserId($idUsuario) {
            $link = $this->open();
            $query = "SELECT * FROM visualizaciones WHERE idUsuario = '$idUsuario'";
            
            $result = mysqli_query($link, $query);
            
            if ($result) {
                $views = [];
                while ($row = mysqli_fetch_assoc($result)) {
                    $views[] = $row;
                }

                $this->close($link);

                return $views;
            } else {
                $this->close($link);
                die("Error al obtener las visualizaciones");
            }
        }

        /////////////////////////////////////////////////////////////////////////////////////

        // METODOS PARA MANEJAR LAS SUSCRIPCIONES DE LOS USUARIOS EN LA BASE DE DATOS
        // Obtener todas las suscripciones
        public function getSubscriptions() {
            $link = $this->open();
            $query = "SELECT * FROM suscripciones";
            $result = mysqli_query($link, $query);
            
            if ($result) {
                $subscriptions = [];
                while ($row = mysqli_fetch_assoc($result)) {
                    $subscriptions[] = $row;
                }

                $this->close($link);

                return $subscriptions;
            } else {
                $this->close($link);
                die("Error al obtener las suscripciones");
            }
        }

        // Obtener una suscripcion por su id
        public function getSubscriptionById($id) {
            $link = $this->open();
            $query = "SELECT * FROM suscripciones WHERE idSuscripcion = '$id'";
            
            $result = mysqli_query($link, $query);
            
            if ($result) {
                $subscription = mysqli_fetch_assoc($result);
                $this->close($link);
                return $subscription;
            } else {
                $this->close($link);
                die("Error al obtener la suscripcion");
            }
        }

        // Obtener suscripciones por el idCreador
        public function getSubscriptionsByCreatorId($idCreador) {
            $link = $this->open();
            $query = "SELECT * FROM suscripciones WHERE idCreador = '$idCreador'";
            
            $result = mysqli_query($link, $query);
            
            if ($result) {
                $subscriptions = [];
                while ($row = mysqli_fetch_assoc($result)) {
                    $subscriptions[] = $row;
                }

                $this->close($link);

                return $subscriptions;
            } else {
                $this->close($link);
                die("Error al obtener las suscripciones");
            }
        }

        // Obtener suscripciones por el idUsuario
        public function getSubscriptionsByUserId($idUsuario) {
            $link = $this->open();
            $query = "SELECT * FROM suscripciones WHERE idUsuario = '$idUsuario'";
            
            $result = mysqli_query($link, $query);
            
            if ($result) {
                $subscriptions = [];
                while ($row = mysqli_fetch_assoc($result)) {
                    $subscriptions[] = $row;
                }

                $this->close($link);

                return $subscriptions;
            } else {
                $this->close($link);
                die("Error al obtener las suscripciones");
            }
        }



    }

?>