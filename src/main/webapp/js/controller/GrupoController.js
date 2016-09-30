myApp.controller('GrupoController',
function GrupoController($scope, $http,GrupoFactory){
    
  $scope.dados = [{"idGrupo":1,
      "TipoUsuario":"A",
      "DescricaoGrupo": "Administrador",
    
    
    
    }];
$scope.buscaGrupo = function(){
   GrupoFactory.getGrupo($scope.callbackGrupo );
    }
    
$scope.callbackGrupo = function(resposta){
    $scope.dados = resposta.data;
}
})


