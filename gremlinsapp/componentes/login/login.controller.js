
(function () {
    'use strict';

    angular
        .module('app')
        .controller('LoginController', LoginController);

    LoginController.$inject = ['$location','$route', 'AuthenticationService', 'Flash','$scope','$rootScope','$localStorage'];
    function LoginController($location,$route, AuthenticationService, Flash,$scope, $rootScope,$localStorage) {
        var vm = this;

        vm.login = login;

        vm.LoginDTO = {
            'email' : null,
            'password' : null
        }


        /* Metodo para ocultar los paneles cuando se invoca al login 
        */
        $rootScope.globalData = {
            verTopMenu: false,
            verLeeftMenu: false
        };
        $rootScope.globals = null;
        $localStorage.globals = null;

       
        
        // *************
        
        function login() {

            if(validarEmail(vm.LoginDTO.email) ){
                vm.dataLoading = true;
                AuthenticationService.Login(vm.LoginDTO)
                    .then(function (response) {
                        if (response.status == 200) {
                            $rootScope.globalData.verTopMenu= true;
                            $rootScope.globalData.verLeeftMenu= true;
                            $rootScope.loggedIn=true;

            
                            AuthenticationService.SetCredentials(response.data )  ;
                            Flash.create('success', $rootScope.EXITO_LOGIN_200_SERVIDOR, 5000, {class: 'custom-class', id: 'custom-id'}, true);  
                               

                            $location.path('/home');
                            $route.reload();
                          


                            //window.location.href = $rootScope.URLBASE+"/#!/home"; 
                            
                        
                        } else {
                            if(response.status == 202 ){
                                Flash.create('danger', $rootScope.ERROR_LOGIN_202_SERVIDOR, 5000, {class: 'custom-class', id: 'custom-id'}, true);  
                               
                                vm.dataLoading = false;
                            }else{

                                if (response.status == 422 ){
                                    Flash.create('danger', response.data, 5000, {class: 'custom-class', id: 'custom-id'}, true);  
                                    vm.dataLoading = false;
                                }else{
                                    Flash.create('danger', $rootScope.ERROR_GENERARL_SERVIDOR, 5000, {class: 'custom-class', id: 'custom-id'}, true);  
                                    vm.dataLoading = false;
                                }

                            }
                        

                        }
                    });
            }else{
                Flash.create('danger', 'El formato del correo no es  valido', 5000, {class: 'custom-class', id: 'custom-id'}, true);  
               
            }


        }


        function validarEmail (valor){
             let re=/^([\da-z_\.-]+)@([\da-z\.-]+)\.([a-z\.]{2,6})$/
            if(!re.exec(valor)){
               return false;
            }
            else  return true;
            }

    
    }
        




})();