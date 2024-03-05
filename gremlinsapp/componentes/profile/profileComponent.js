


(function () {
    'use strict';

    angular
        .module('app')
        .component('profile', {
    templateUrl: 'componentes/profile/profile.view.html',
    controller: profileController
  });

  profileController.$inject = [ '$location','$route', '$scope', '$rootScope', 'Flash','ParametricasService','$localStorage'];
  function profileController( $location, $route, $scope, $rootScope, Flash , ParametricasService, $localStorage) {
    var vm = this;



    vm.logout = logout;
    vm.getImgPerfil= getImgPerfil;



    function logout(){
                /* Metodo para ocultar los paneles cuando se invoca al login 
        */
                $rootScope.globalData = {
                    verTopMenu: false,
                    verLeeftMenu: false
                };
                $rootScope.globals = null;
                $localStorage.globals = null;

                $location.path('/login');
                $route.reload();
    }



    function getImgPerfil(){
        if ( $rootScope.globals  !== 'undefined'  && $rootScope.globals != null ) {
            if ( $rootScope.globals.configuracion  !== 'undefined'  && $rootScope.globals.configuracion != null ) {
                    if ( $rootScope.globals.configuracion.urlImgProfile  !== 'undefined'  && $rootScope.globals.configuracion.urlImgProfile != null ) {
                        return $rootScope.URL_API_FILE+$rootScope.globals.configuracion.urlImgProfile
                    }else{
                        if ($rootScope.globals.juzPersona.juzSexo.sNombreSexo !== 'undefined' &&  $rootScope.globals.juzPersona.juzSexo.sNombreSexo === 'M'  ){
                            return "img/boy.png";
                        }
                        if ($rootScope.globals.juzPersona.juzSexo.sNombreSexo !== 'undefined' &&  $rootScope.globals.juzPersona.juzSexo.sNombreSexo === 'F'  ){
                            return "img/girl.png";
                        }          
                    }
            }else{
                if($rootScope.globals.juzPersona.juzSexo ) {
                    if ($rootScope.globals.juzPersona.juzSexo.sNombreSexo !== 'undefined' &&  $rootScope.globals.juzPersona.juzSexo.sNombreSexo === 'M'  ){
                        return "img/boy.png";
                    }
                    if ($rootScope.globals.juzPersona.juzSexo.sNombreSexo !== 'undefined' &&  $rootScope.globals.juzPersona.juzSexo.sNombreSexo === 'F'  ){
                        return "img/girl.png";
                    }
                }

            }


        }
    }


}




})();