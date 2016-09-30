'use strict'

myApp.factory('GrupoFactory',['$http', function($http){
    return{
        getGrupo:function(callback){
         $http({"method":"GET", "url":"/AprenderCrescer/rest/grupo/getgrupo"}).then(
                 function(resposta){
                    callback(resposta);
                    
                 });   
                    
             },
                }
}]);

        



