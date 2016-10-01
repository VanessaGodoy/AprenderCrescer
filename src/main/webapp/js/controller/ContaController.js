myApp.controller('ContaController',
function ContaController($scope, $http,ContaFactory){
    
  $scope.dados = [{"idConta":1,
      "Descricao":"a",
      "TipoConta": "Administrador",
      "Valor":"1",
    
    
    
    }];
$scope.buscaConta = function(){
   ContaFactory.getConta($scope.callbackConta );
    }
    
$scope.callbackConta = function(resposta){
    $scope.dados = resposta.data;
}
})



