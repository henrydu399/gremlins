(function () {
    'use strict';

    angular
        .module('app')
        .controller('PersonasController', PersonasController);

        PersonasController.$inject = [ '$location','$route', '$scope', '$rootScope', 'Flash','ParametricasService'];
    function PersonasController( $location, $route, $scope, $rootScope, Flash , ParametricasService) {
        var vm = this;

        vm.action=action;
        vm.save = save;
        vm.select = select;
        vm.edit = edit;
        vm.clean = clean;
        vm.delet = delet;
        


     

        vm.model ={};
        vm.isEdit = false;

        

        const nameModel ='PERSONAS';
        vm.listModels =[];
        vm.safelist =[];

        //lista de grupos etnicos ///
        vm.listGroupEnticos =[];

        //////////////////

        //lista de sexos ///
        vm.listSexos =[];
        
        const nameModelSexo ='SEXO';
        ///////////////////////

        /////MANEJO DE TABS ///
        vm.tabSeleccion = 'ver';
        
        const nameModelGrupoEtnico ='GRUPO_ETNICO';

        /////////////////////////


        ////MANEJO DE DATOS DE CONTACTO //7
        vm.listDatosContacto =[];
        vm.safeListDatosContacto =[];
        vm.modelDatosContacto = {};
        const MODEL_DATOS_CONSTANTE = "DATOS_CONTACTO";
        vm.modelPersonaTemp = {};
        vm.typeButtonActivate = 'save';
        vm.verDatosContacto = verDatosContacto;
        vm.saveDatosContacto = saveDatosContacto;
        vm.cleanDatosContacto = cleanDatosContacto;
        vm.editDatosContacto = editDatosContacto;
        vm.deleteDatosContacto = deleteDatosContacto;

        function cleanDatosContacto(){
            if (vm.modelDatosContacto   !== 'undefined'  && vm.modelDatosContacto  != null ) {
                vm.modelDatosContacto.dcNumeroTelefonoFijo = null;
                vm.modelDatosContacto.dcNumeroTelefonoCelular = null;
                vm.modelDatosContacto.dcDireccion = null;
                vm.modelDatosContacto.juzCiudade = null;
                vm.modelDatosContacto.dcDireccion = null;
                vm.typeButtonActivate = 'save';
            }

        }
        function verDatosContacto(model){
            vm.modelPersonaTemp = model;

            vm.modelDatosContacto.id = {};
            vm.modelDatosContacto.id.dcIdDatosContacto = null;
            vm.modelDatosContacto.id.dcTipoIdentificacion = vm.modelPersonaTemp.id.ptipoIdentificacion;
            vm.modelDatosContacto.id.dcNumeroIdentificacion = vm.modelPersonaTemp.id.pnumeroIdentificacion;

            ParametricasService.findById(MODEL_DATOS_CONSTANTE,model)
            .then(function (response) {
                if (response.status == 200) {
                    vm.safeListDatosContacto = response.data;
                    vm.listDatosContacto = response.data;
                    if (vm.listDatosContacto   !== 'undefined'  && vm.listDatosContacto  != null &&   vm.listDatosContacto.length > 0) {
                        vm.modelDatosContacto = vm.listDatosContacto [0];
                        vm.typeButtonActivate = 'edit';
                    }else{
                       
                        vm.typeButtonActivate = 'save';
                    }
                } else {   
                    Flash.create('danger', $rootScope.PARAMETRICA_ERROR_DATOS_CONTACTO, 5000, {class: 'custom-class', id: 'custom-id'}, true);                       
                        
                }
            });
        }
        function saveDatosContacto(){

            if( validarInfDatosContacto() ){

                ParametricasService.save(MODEL_DATOS_CONSTANTE,vm.modelDatosContacto)
                .then(function (response) {
                    if (response.status == 200) {
                        verDatosContacto(vm.modelPersonaTemp );
                        Flash.create('success', $rootScope.PARAMETRICA_GUARDADO_CON_EXITO, 5000, {class: 'custom-class', id: 'custom-id'}, true);                                  
                    } 
                });
            }

        }

        function editDatosContacto(){
            if( validarInfDatosContacto() ){
            ParametricasService.edit(MODEL_DATOS_CONSTANTE,vm.modelDatosContacto)
            .then(function (response) {
                if (response.status == 200) {
                    verDatosContacto(vm.modelPersonaTemp );                       
                    Flash.create('success', $rootScope.PARAMETRICA_MODIFICADO_CON_EXITO, 5000, {class: 'custom-class', id: 'custom-id'}, true);   
                    $('html, body').animate({scrollTop:0}, 'slow');    
                } 
            });
        }
        }
        function deleteDatosContacto(){

        }
        function validarInfDatosContacto(){
            if ( vm.modelDatosContacto  !== 'undefined'  &&  vm.modelDatosContacto   !== null  && vm.modelDatosContacto.id   !== 'undefined'  && vm.modelDatosContacto.id  != null ) {
                return true;
            }else{
                Flash.create('danger', 'Erro con los datos de contacto, no pueden estar vacios' ,  5000, {class: 'custom-class', id: 'custom-id'}, true);
                    $('html, body').animate({scrollTop:0}, 'slow');  
                    return false;
            }
        }
        //////////////////////  FIN DATOS DE CONTACTO  //////////////////77

        ///////////////// DATOS PARA DEPARTAMENTOS  //////////////////////
        vm.listDepartamentos =[];
        vm.modelselectDepartamentos = {};
        const nameModelDepartamentos = "DEPARTAMENTOS";
        getDepartamentos();
        function getDepartamentos(){
            ParametricasService.getAll(nameModelDepartamentos)
                    .then(function (response) {
                        if (response.status == 200) {
                            vm.listDepartamentos =  response.data;
                        } else {   
                            Flash.create('danger', $rootScope.ERROR_GENERARL_SERVIDOR, 5000, {class: 'custom-class', id: 'custom-id'}, true);                                               
                        }
                    });
        }
        ////////////////////////////////////////////////////////////////
        ///////////////// DATOS PARA CIUDADES  //////////////////////
        vm.listCiudades =[];
        vm.safelistCiudades =[];
        vm.modelselectCiudades = {};
        const nameModelCiudades= "CIUDADES";
        getCiudades();
        vm.buscaByDepartamentoId= buscaByDepartamentoId;
        function getCiudades(){
            ParametricasService.getAll(nameModelCiudades)
                    .then(function (response) {
                        if (response.status == 200) {
                            vm.listCiudades =  response.data;
                            vm.safelistCiudades = response.data;
                        } else {   
                            Flash.create('danger', $rootScope.ERROR_GENERARL_SERVIDOR, 5000, {class: 'custom-class', id: 'custom-id'}, true);                                               
                        }
                    });
        }
        function buscaByDepartamentoId(){
            vm.listCiudades =[];
            for ( var a = 0 ;  a < vm.safelistCiudades.length; a++){
                if ( vm.modelDatosContacto.juzCiudade.juzDepartamento.id   !== 'undefined'  && vm.modelDatosContacto.juzCiudade.juzDepartamento.id  != null ) { 
                    if( vm.modelDatosContacto.juzCiudade.juzDepartamento.id == vm.safelistCiudades[a].juzDepartamento.id ){
                        vm.listCiudades.push(vm.safelistCiudades[a]);
                    }
                }else{
                    vm.listCiudades = safelistCiudades; 
                }
            }
        }
        
        ////////////////////////////////////////////////////////////////



        //  tipos de procesos //     
        const nameModelTipoDocumento ='TIPO_IDENTIFICACION';
        vm.listDocumentos =[];
        ParametricasService.getAll(nameModelTipoDocumento);
        //////////////////////


       
        vm.nameParametricaView = 'PERSONAS';
 
        vm.foo = true;
    
      

        get();
        CargarAreasyTipos();

        function select(model){
            vm.model = model;           
            vm.isEdit = true;
            verDatosContacto(model);

            $('html, body').animate({scrollTop:0}, 'slow'); 
            vm.tabSeleccion = 'createOrEdit';
            if(model!= null){
                Flash.create('success', $rootScope.PARAMETRICA_CARGADA_CON_EXITO, 5000, {class: 'custom-class', id: 'custom-id'}, true);  

            }    
        }

      

        function action(){
            if ( vm.isEdit == false ){
                save() ;
            }else{
                edit();
            }
        }

        function clean(){
            vm.isEdit = false;
            vm.model = null;
        }

    


        function get() {          
            ParametricasService.getAll(nameModel)
                    .then(function (response) {
                        if (response.status == 200) {
                            vm.safelist = response.data;
                            vm.listModels = response.data;
                        } else {   
                            Flash.create('danger', $rootScope.ERROR_GENERARL_SERVIDOR, 5000, {class: 'custom-class', id: 'custom-id'}, true);                       
                                
                        }
                    });
        }

        function save() {
            
            ParametricasService.save(nameModel,vm.model)
                    .then(function (response) {
                        if (response.status == 200) {
                            get();
                            Flash.create('success', $rootScope.PARAMETRICA_GUARDADO_CON_EXITO, 5000, {class: 'custom-class', id: 'custom-id'}, true);                                  
                        } 
                    });
        }

        function edit() {
            ParametricasService.edit(nameModel,vm.model)
                    .then(function (response) {
                        if (response.status == 200) {
                            get();                        
                            Flash.create('success', $rootScope.PARAMETRICA_MODIFICADO_CON_EXITO, 5000, {class: 'custom-class', id: 'custom-id'}, true);   
                            $('html, body').animate({scrollTop:0}, 'slow');    
                        } 
                    });
        }

        function find() {
            ParametricasService.find(nameModel,vm.model)
                    .then(function (response) {
                        if (response.status == 200) {
                            $scope.safelist = response.data;
                            $scope.listTipoDocumentos = response.data;
                        } 
                    });
        }


        function delet(model) {
            vm.model= model;
            ParametricasService.delet(nameModel,vm.model)
                    .then(function (response) {
                        if (response.status == 200) {
                            get();                        
                            Flash.create('success', $rootScope.PARAMETRICA_ELIMINADO_CON_EXITO, 5000, {class: 'custom-class', id: 'custom-id'}, true);   
                            $('html, body').animate({scrollTop:0}, 'slow');      
                        }
                    });
        }

    

        function CargarAreasyTipos(){
            getTipoDocumentos();   
            getTipoSexos();
            getTipoGrupoEtnicos();

        }


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

        function getTipoGrupoEtnicos(){
            ParametricasService.getAll(nameModelGrupoEtnico)
                    .then(function (response) {
                        if (response.status == 200) {
                            vm.listGroupEnticos =  response.data;
                        } else {   
                            Flash.create('danger', $rootScope.ERROR_GENERARL_SERVIDOR, 5000, {class: 'custom-class', id: 'custom-id'}, true);                                               
                        }
                    });
        }




    }




})();