(function () {
    'use strict';

    angular
        .module('app')
        .controller('ActividadesController', ActividadesController);

        ActividadesController.$inject = [ '$location','$route', '$scope', '$rootScope', 'Flash','ParametricasService','$sce'];
    function ActividadesController( $location, $route, $scope, $rootScope, Flash , ParametricasService , $sce) {
        var vm = this;

        vm.get=get;
        vm.action=action;
        vm.save = save;
        vm.select = select;
        vm.edit = edit;
        vm.clean = clean;
        vm.delet = delet;
        vm.selectProceso = selectProceso;
        vm.selectUser = selectUser;
        vm.filtrarTiposProcesos= filtrarTiposProcesos;
        vm.AlertFecha = AlertFecha;
        vm.deletImplicado=deletImplicado;
        vm.updateHtml = updateHtml;

        vm.selectPlantilla = selectPlantilla;

        vm.buscarUser = buscarUser;

        
     

        vm.model ={
            userViewDto:{},
            juzTipoActividadesProceso:{},
            juzProcesosViewDto: {},
            implicados : [],
            documentos : []
        };
        vm.isEdit = false;

        vm.pk ={};

        const nameModelUserView ='USERVIEW';
        vm.listUsers =[];
        vm.safelistUser =[];

        const nameModel ='ACTIVIDAD_PROCESO';

        const nameModelView ='PROCESOS_VIEW';
        vm.listProcesos = [];
        vm.safeListProcesos = [];

        /// filtro para las actividades //
        vm.filter = {
            estado : null,
            tipo: null,
            user : null,
            proceso : null
             };
        ///
        //***filtro plantillas */
        vm.filterPlantillas = {
            tipo: null
             };
        /////


        ///////tiny editor //////////////
         function updateHtml() {
            vm.tinymceHtml = $sce.trustAsHtml(vm.model.apDescripcionActividad);
          };
          vm.tinymceOptions = {
            readonly : 1,
            resize: true,
            width: '100%',  // I *think* its a number and not '400' string
            height: 'auto',
            plugins: 'link image code',
            toolbar: ''
          };

          vm.tinymceOptionsActividades = {
            resize: true,
            width: '100%',  // I *think* its a number and not '400' string
            height: 'auto',
            plugins: 'link image code',
            toolbar: ''
          };


        //////////////////////////////////////////////

        const nameUrl = "PLANTILLA";
        //************* MODAL DE PLANTILLAS ********* */
            vm.listPlantillas = [];
            vm.safePlantillas = [];
            vm.modelPlantillaSelect = {};
            vm.getAllPlantillas= getAllPlantillas;
                // ** PLANTILLAS ***
                getAllPlantillas() ;
                function getAllPlantillas() {
              
                    ParametricasService.getbyfilter(nameUrl,vm.filterPlantillas)
                        .then(function (response) {
                            if (response.status == 200) {
                                vm.listPlantillas = response.data;
                                vm.safePlantillas = response.data;
                            } else {
                                Flash.create('danger', $rootScope.ERROR_GENERARL_SERVIDOR, 5000, { class: 'custom-class', id: 'custom-id' }, true);
                            }
                        });
                }

            //************ */
          
            // TIPOS DE PLANTILLA //
            vm.listTipoPlantillas = [];

            let tipoPlatillas = 'TIPO_DE_PLANTILLA';
            getTiposPlantillas();
            function getTiposPlantillas() {
                ParametricasService.getAll(tipoPlatillas)
                    .then(function (response) {
                        if (response.status == 200) {
                            vm.listTipoPlantillas = response.data;

                        } else {
                            Flash.create('danger', $rootScope.ERROR_GENERARL_SERVIDOR, 5000, { class: 'custom-class', id: 'custom-id' }, true);

                        }
                    });
            }
            ///////////////////////////

            function selectPlantilla(item ){
                vm.modelPlantillaSelect = item; 
                vm.model.plantillaId = item.id;
                $('html, body').animate({scrollTop:0}, 'slow'); 
                Flash.create('success', $rootScope.OPCION_SELECCION_CORRECTA, 5000, {class: 'custom-class', id: 'custom-id'}, true);  

            }

        //***************************************** */

        // /////****IMPICADOS A NOTIFICAR***********
        const nameModelImplicados ='IMPLICADOS';
        vm.listImplicadosNotificar = [];
        vm.listImplicadosNotificarTemp = [];
        function cargarImplicados(proceso){
            if ( proceso !== 'undefined'  &&  proceso  != null){
                    ParametricasService.findByCase(nameModelImplicados,'proceso/'+proceso)
                            .then(function (response) {
                                if (response.status == 200) {
                                    vm.listImplicadosNotificar = response.data;
                                    vm.listImplicadosNotificarTemp = response.data;
                                } else {
                                    if(response.status == 202 ){
                                        Flash.create('danger', $rootScope.ERROR_202_SERVIDOR, 5000, {class: 'custom-class', id: 'custom-id'}, true);
                                        $('html, body').animate({scrollTop:0}, 'slow');  
                                       
                                    }else{
                                        Flash.create('danger', $rootScope.ERROR_202_SERVIDOR, 5000, {class: 'custom-class', id: 'custom-id'}, true);   
                                        $('html, body').animate({scrollTop:0}, 'slow');                       
                                    }
                                
                                }
                            });
                
            }
        }

        function deletImplicado(imp){
            let tempList = [];

            for( var i = 0; i < vm.listImplicadosNotificar.length; i++){                                  
                if ( vm.listImplicadosNotificar[i].piIdJuzProcesosImplicados === item.piIdJuzProcesosImplicados) { 
                    vm.listImplicadosNotificar.splice(i, 1); 
                    vm.listImplicadosNotificarTemp.splice(i, 1);
                }
                
            }

        }
        /////////////***FIN IMPLICADOS A NOTIFICAR ******/////////////

            // /////****************** DOCUMENTOS ***********
            vm.listDocumentos = [];
            vm.listSafeDocumentos = [];
            const nameModelDocumentos = "DOCUMENTOS";
            vm.filterDocumento = {
                idProceso : null
            };

            function getDocumentos(){
                ParametricasService.getbyfilter(nameModelDocumentos,vm.filterDocumento)
                .then(function (response) {
                    if (response.status == 200) {
                        vm.listDocumentos =  response.data;
                        vm.listSafeDocumentos =  response.data;
                    } else {   
                        Flash.create('danger', $rootScope.ERROR_GENERARL_SERVIDOR, 5000, {class: 'custom-class', id: 'custom-id'}, true);                                               
                    }
                });
            }
            vm.deleteDocumentoList= deleteDocumentoList;
            function deleteDocumentoList(item){
                for( var i = 0; i < vm.listDocumentos.length; i++){                                  
                    if ( vm.listDocumentos[i].tdpIdDocumentoProceso === item.tdpIdDocumentoProceso) { 
                        vm.listDocumentos.splice(i, 1); 
                        vm.listSafeDocumentos.splice(i, 1);
                    }
                    
                }
            }


            // /////*****************FIN DOCUMENTOS ***********


        // Modelos Area y tipos de procesos //
        const nameModelAreas ='AREA_PROCESOS';
        const nameModelTiposProcesos ='TIPOS_DE_PROCESOS';


        const nameModelEstadosActividades= 'ESTADO_ACTIVIDAD'
        vm.listEstadosActividades = [];


        const nameModelTipoActividades ='TIPO_ACTIVIDAD_PROCESO';
        vm.listTiposActividades = [];
       
        /////MANEJO DE TABS ///
        vm.tabSeleccion = 'ver';
        ////////////////


       
        vm.nameParametricaView = 'ACTIVIDADES';
        
        vm.listAreasProcesos = [];
        vm.listTiposProcesos = [];
        vm.listEstadosProcesos = [];

        vm.listTempTiposProcesos = [];

        vm.foo = true;
    
        vm.listModels =[];
        vm.safelist =[];

        get();
        CargarAreasyTipos();

        function select(model){
            vm.model = model;
            vm.model.userViewDto= buscarUser(model);
            vm.model.juzTipoActividadesProceso=buscarTipoActividad(model);
            vm.model.juzProcesosViewDto = buscarProceso(model);

            vm.filterDocumento = {
                idProceso : model.pIdProceso
            };
            getDocumentos();
            cargarImplicados(model.pIdProceso);
            
            vm.tabSeleccion = 'createOrEdit';
            
            vm.isEdit = true;
            $('html, body').animate({scrollTop:0}, 'slow'); 
            if(model!= null){
                $('html, body').animate({scrollTop:0}, 'slow'); 
                Flash.create('success', $rootScope.PARAMETRICA_CARGADA_CON_EXITO, 5000, {class: 'custom-class', id: 'custom-id'}, true);  

            }    
        }

        function buscarPlantilla(modelSelect){
           // for ( var a = 0 ;  a< vm.listPlantillas.length ; a++){
           // }
           // vm.modelPlantillaSelect = item; 
          //  vm.model.plantillaId = item.id;
            
        }

        function buscarUser(modelSelect){   
            var  userTemp = {};                  
                    vm.listUsers.forEach(function(user){
                    if ( user.uIdUsuario ==  modelSelect.uIdUsuario &&  user.uIdTipoPersona ==  modelSelect.pTipoIdentificacion && user.uNumeroIdentificacion ==  modelSelect.pNumeroIdentificacion){
                        userTemp =  user;
                        return;
                    }
                });
                return userTemp;      
        }

        function buscarTipoActividad(modelSelect){
            var  tipoActividadTemp = {};                  
            vm.listTiposActividades.forEach(function(actividad){
            if ( actividad.tapIdActividad ==  modelSelect.tapIdActividad ){
                tipoActividadTemp =  actividad;
                //vm.model.juzTipoActividadesProceso.tapIdActividad = actividad.tapIdActividad;
                return;    
            }
        });
        return tipoActividadTemp; 
        }

        function buscarProceso (modelSelect){
            let  ProcesoTemp = {};                  
            vm.listProcesos.forEach(function(proceso){
            if ( proceso.pIdProceso ==  modelSelect.pIdProceso ){
                ProcesoTemp =  proceso;
                
                return;    
            }
        });
        return ProcesoTemp;  
        }




        function selectUser(model){
            vm.model.userViewDto = model;
        }

        function selectProceso(model){
            if ( model !== 'undefined'  &&  model  != null){
                    vm.filterDocumento = {
                        idProceso : model.pIdProceso
                    };
                getDocumentos();

                vm.model.juzProcesosViewDto = model;
                cargarImplicados(model.pCodigoRadicadoProceso);
                $('html, body').animate({scrollTop:0}, 'slow'); 
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
            ParametricasService.getbyfilter(nameModel,vm.filter)
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
            vm.model.implicados = vm.listImplicadosNotificar;
            vm.model.documentos = vm.listDocumentos; 
            
            ParametricasService.save(nameModel,vm.model)
                    .then(function (response) {
                        if (response.status == 200) {
                            get();
                            vm.model ={
                                userViewDto:{},
                                juzTipoActividadesProceso:{},
                                juzProcesosViewDto: {}
                            };
                            Flash.create('success', $rootScope.PARAMETRICA_GUARDADO_CON_EXITO, 5000, {class: 'custom-class', id: 'custom-id'}, true);  
                            $('html, body').animate({scrollTop:0}, 'slow');  
                          
                        } else {
                            if(response.data !== 'undefined'  &&  response.data != null ){
                                Flash.create('danger',response.data , 5000, {class: 'custom-class', id: 'custom-id'}, true); 
                                $('html, body').animate({scrollTop:0}, 'slow');   
                               
                            }else{
                                Flash.create('danger', $rootScope.ERROR_GENERARL_SERVIDOR, 5000, {class: 'custom-class', id: 'custom-id'}, true);  
                                $('html, body').animate({scrollTop:0}, 'slow');  
                                                           
                            }
                        
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
                        } else {
                            if(response.status == 202 ){
                                Flash.create('danger', $rootScope.PARAMETRICA_NO_EXISTENTE, 5000, {class: 'custom-class', id: 'custom-id'}, true);   
                                $('html, body').animate({scrollTop:0}, 'slow');                              
                            }else{
                                Flash.create('danger', $rootScope.ERROR_202_SERVIDOR, 5000, {class: 'custom-class', id: 'custom-id'}, true);   
                                $('html, body').animate({scrollTop:0}, 'slow');                                                      
                            }
                        
                        }
                    });
        }

        function find() {
            ParametricasService.find(nameModel,vm.model)
                    .then(function (response) {
                        if (response.status == 200) {
                            $scope.safelist = response.data;
                            $scope.listTipoDocumentos = response.data;
                        } else {
                            if(response.status == 202 ){
                                Flash.create('danger', $rootScope.ERROR_202_SERVIDOR, 5000, {class: 'custom-class', id: 'custom-id'}, true);
                                $('html, body').animate({scrollTop:0}, 'slow');  
                               
                            }else{
                                Flash.create('danger', $rootScope.ERROR_202_SERVIDOR, 5000, {class: 'custom-class', id: 'custom-id'}, true);   
                                $('html, body').animate({scrollTop:0}, 'slow');                       
                            }
                        
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

                            
                        } else {
                            if(response.status == 202 ){
                                Flash.create('danger', $rootScope.ERROR_202_SERVIDOR, 5000, {class: 'custom-class', id: 'custom-id'}, true); 
                                $('html, body').animate({scrollTop:0}, 'slow');    
                            }else{
                                Flash.create('danger', $rootScope.ERROR_202_SERVIDOR, 5000, {class: 'custom-class', id: 'custom-id'}, true);   
                                $('html, body').animate({scrollTop:0}, 'slow');                                
                            }
                        
                        }
                    });
        }

    
        function filtrarTiposProcesos(){
            if ( vm.model.pIdArea !== 'undefined'  &&  vm.model.pIdArea  != null){
                vm.listTempTiposProcesos = [];
                    for( var a = 0; a <  vm.listTiposProcesos.length ; a++ ){
                    if ( parseInt (vm.listTiposProcesos[a].id.tpIdArea) ==  parseInt (vm.model.pIdArea) ){
                        vm.listTempTiposProcesos.push(vm.listTiposProcesos[a]);
                    }

                }
                
            }

        }

        function CargarAreasyTipos(){
            getTipoActividades();
            getUsers();
            getEstadoActividades();
            getProcesos();
           
        }


        function getProcesos(){
            ParametricasService.getAll(nameModelView)
                    .then(function (response) {
                        if (response.status == 200) {
                            vm.listProcesos =  response.data;
                            vm.safeListProcesos =  response.data;
                        } else {   
                            Flash.create('danger', $rootScope.ERROR_GENERARL_SERVIDOR, 5000, {class: 'custom-class', id: 'custom-id'}, true);                                               
                        }
                    });
        }

       
        function getEstadoActividades() {          
            ParametricasService.getAll(nameModelEstadosActividades)
                    .then(function (response) {
                        if (response.status == 200) {
                            vm.listEstadosActividades = response.data;
                        } else {   
                            Flash.create('danger', $rootScope.ERROR_GENERARL_SERVIDOR, 5000, {class: 'custom-class', id: 'custom-id'}, true);                                               
                        }
                    });
        }

        function getTipoActividades() {          
            ParametricasService.getAll(nameModelTipoActividades,vm)
                    .then(function (response) {
                        if (response.status == 200) {
                            vm.listTiposActividades = response.data;
                        } else {   
                            Flash.create('danger', $rootScope.ERROR_GENERARL_SERVIDOR, 5000, {class: 'custom-class', id: 'custom-id'}, true);                                               
                        }
                    });
        }
    
        function getUsers() {          
            ParametricasService.getAll(nameModelUserView,vm)
                    .then(function (response) {
                        if (response.status == 200) {
                            vm.safelistUser = response.data;
                            vm.listUsers = response.data;
                        } else {   
                            Flash.create('danger', $rootScope.ERROR_GENERARL_SERVIDOR, 5000, {class: 'custom-class', id: 'custom-id'}, true);                       
                                
                        }
                    });
        }


        function AlertFecha(fecha){
            var temp = new Date(fecha);
            let fechaActual = new Date()
        let resta = fechaActual.getTime() - temp.getTime();
        let dias = Math.round(resta/ (1000*60*60*24));
        dias = (dias *-1);
        if(dias < 3){  return "btn-danger";   }
        if (dias >= 3  &&  dias <= 5){ return "btn-warning";    }
        if (dias > 5){   return "btn-light";     }    
        }



    }




})();