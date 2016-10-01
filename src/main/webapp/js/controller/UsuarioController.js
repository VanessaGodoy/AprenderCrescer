myApp.controller('UsuarioController',
        function UsuarioController($scope, $http, UsuarioFactory) {

            $scope.editando = false;

            $scope.dados = [{"idUsuario": 1,
                    "idGrupo": 1,
                    "Login": "Vanessa",
                    "Nome": "Vanessa Godoy",
                    "Atvio": 'F'




                }];
            $scope.buscaUsuarios = function () {
                UsuarioFactory.getUsuarios($scope.callbackUsuarios);
            }

            $scope.callbackUsuarios = function (resposta) {
                $scope.dados = resposta.data;
            }

            $scope.editarUsuario = function () {
                $scope.editando = !$scope.editando;
            }
            $scope.cadastroUsuario = function(usuario){
                UsuarioFactory.setUsuario($scope.callbackCadastroUsuario, usuario);
                     
            }
            $scope.callbackCadastroUsuario = function(resposta){
                if (resposta.status != 200){
                    alert("Deu erro");
                }else{
                    alert("Ok");
                }
                
            }
        })
        
