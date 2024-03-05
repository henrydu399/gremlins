(function () {
    'use strict';

    angular
        .module('app')
        .controller('profileController', profileController);

        profileController.$inject = [ '$location','$route', '$scope', '$rootScope', 'Flash','ParametricasService'];
    function profileController( $location, $route, $scope, $rootScope, Flash , ParametricasService) {
        var vm = this;
     

        vm.model ={};
        vm.isEdit = false;
        vm.action =action;
        vm.save = save ;
        vm.saveImg = saveImg;
        vm.selectImg= selectImg;

        vm.getImgPerfil= getImgPerfil;
        const nameModel = "USERPROFILE"
        
   

        vm.tabSeleccion = 'ver';
  

        if ( $rootScope.globals  !== 'undefined'  && $rootScope.globals != null ) {
            vm.model =$rootScope.globals;
        }
        

        // ************** TIPOS DE DOCUMENTO **********************
        vm.listDocumentos=[];
        const nameModelTipoDocumento ='TIPO_IDENTIFICACION';
        getTipoDocumentos();
        function getTipoDocumentos(){
            ParametricasService.getAll(nameModelTipoDocumento)
                    .then(function (response) {
                        if (response.status == 200) {
                            vm.listDocumentos =  response.data;
                        } else {   
                            Flash.create('danger', $rootScope.ERROR_GENERARL_SERVIDOR, 5000, {class: 'custom-class', id: 'custom-id'}, true);                                               
                        }
                    });
        }
        //************* FIN TIPOS DE DOCUMENTO ******** */

       // ************** TIPOS DE SEXO **********************
            vm.listSexos =[];
            const nameModelSexo ='SEXO';
            getTipoSexos();
        function getTipoSexos(){
            ParametricasService.getAll(nameModelSexo)
                    .then(function (response) {
                        if (response.status == 200) {
                            vm.listSexos =  response.data;
                        } else {   
                            Flash.create('danger', $rootScope.ERROR_GENERARL_SERVIDOR, 5000, {class: 'custom-class', id: 'custom-id'}, true);                                               
                        }
                    });
        }
        //************* FIN TIPOS DE SEXO ******** */


        // ******************** EVENTOS DE VISTA **************************
        function action(){
                save();         
        }

        function save() {    
            ParametricasService.edit(nameModel,vm.model)
                    .then(function (response) {
                        if (response.status == 200) {
                            Flash.create('success', $rootScope.PARAMETRICA_GUARDADO_CON_EXITO, 5000, {class: 'custom-class', id: 'custom-id'}, true);                                  
                        } 
                    });
        }

            function selectImg(){
                vm.file = document.getElementById('file').files[0].name;
            }

        function saveImg() {    
            var formData = new FormData();
            formData.append('file', document.getElementById('file').files[0]);
            
            ParametricasService.saveImg(nameModel,formData)
                    .then(function (response) {
                        if (response.status == 200) {
                            Flash.create('success', $rootScope.PARAMETRICA_GUARDADO_CON_EXITO, 5000, {class: 'custom-class', id: 'custom-id'}, true);                                  
                        } 
                    });
        }

        // ******************************************************************



        function getImgPerfil(){
            

            if ( $rootScope.globals  !== 'undefined'  && $rootScope.globals != null ) {
                if ( $rootScope.globals.configuracion  !== 'undefined'  && $rootScope.globals.configuracion != null ) {
                    if ( $rootScope.globals.configuracion.urlImgProfile  !== 'undefined'  && $rootScope.globals.configuracion.urlImgProfile != null ) {
                        return $rootScope.URL_API_FILE+$rootScope.globals.configuracion.urlImgProfile
                    }else{
                        return "/img/perfil.png";
                    }
                }else{
                    return "/img/perfil.png";
                }

            }
        }

    }




})();