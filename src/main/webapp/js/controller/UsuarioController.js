myApp.controller('UsuarioController',
function UsuarioController($scope, $http, UsuarioFactory){
    
  $scope.dados = [{"idUsuario":1,
      "idGrupo":1,
      "Login":"Vanessa",
      "Nome": "Vanessa Godoy",
      "Atvio":'F'
  
    
    
    
    }];
$scope.buscaUsuarios = function(){
    UsuarioFactory.getUsuarios($scope.callbackUsuarios );
    }
    
$scope.callbackUsuarios = function(resposta){
    $scope.dados = resposta.data;
}
})
