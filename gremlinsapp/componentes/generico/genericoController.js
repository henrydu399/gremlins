(function () {
    'use strict';

    angular
        .module('app')
        .controller('GenericoController', GenericoController);

    GenericoController.$inject = ['$location', '$route', '$scope', '$rootScope', 'Flash', 'ParametricasService', '$q', '$http'];
    function GenericoController($location, $route, $scope, $rootScope, Flash, ParametricasService, $q, $http) {
        var vm = this;

        vm.action = action;
        vm.save = save;
        vm.selectValue = selectValue;
        vm.edit = edit;
        vm.clean = clean;
        vm.close = close;
        vm.delet = delet;


        vm.loading = false;


        vm.typefield = typefield;
        vm.isRequeride = isRequeride;
        vm.selectModel = selectModel;
        vm.getClass = getClass;

     

        vm.SelectModelList= SelectModelList;
        vm.getValueTable = getValueTable;

        vm.model = {};
        vm.isEdit = false;


        var nameModel = "";

        const nameUrl = "PARAMETRICAS";


        ///LISTA DE PARAMETRICAS Y SUS PROPIEDADES
        vm.safelist = [];
        vm.listModels = [];
        vm.parametricaSelectList = ""; // valor seleccionado de la lista de parametricas
        ////////////////////////////////////

        ///LISTA DE VALORES DE UNA PARAMETRICA SELECCIONADA
        vm.safelistModelsSelecion = [];
        vm.listModelsSelecion = [];

        ////////////////////////////////////
        vm.modelList ={};


        /////MANEJO DE TABS ///
        vm.tabSeleccion = 'ver';
        /////////////////////////


        get();

        vm.foo = true;


     

        /*
        Funciones Utilidades Para vista generica
        */
        vm.a = 0; // contador para mapear el objeto del formulario
        // funcion para identificar el typo de imput en la vista
        function typefield(type) {
            switch (type) {
                case "String": return 'text'; break;
                case "Integer": return 'number'; break;
                case "int": return 'number'; break;
                case "byte": return 'number'; break;
                case "boolean": return 'radio'; break;
            }
        }
        // funcion para selecionar el modelo dependiendo de la lista de parametricas 
        function selectModel() {
         
           
        }
        // ver valor de cada columna de la tabla
        function getValueTable(item, item2){


           var items = Object.keys(item);
           for ( var a = 0;  a< items.length ; a++){
               if(item2.nameColumn == items[a]){

               return Object.values(item)[a];
               }
           }          
        }

      

        // funcion que se ejecuta cuando cambia el valor seleccionado de la lista
        function SelectModelList(){
            for (var a = 0; a < vm.listModels.length; a++) {
                if (vm.listModels[a].idparametrica === vm.parametricaSelectList) {
                    vm.modelList = vm.listModels[a];          
                    break;
                    return;
                }
            }
            nameModel = vm.modelList.parametricaUrlApi;
            getAll();
        }
        // funcion para crear un imput requeride
        function isRequeride(item) {
            if (item == true) {
                return 'required';
            }
        }
        //FUNCION PARA OBTENER LA CLASS DEL INPUT
        function getClass(type) {
            switch (type) {
                case "java.lang.Integer": return 'form-control'; break;
                case "java.lang.Long": return 'form-control'; break;
                case "java.lang.String": return 'form-control'; break;
                case "String": return 'form-control'; break;
                case "Integer": return 'form-control'; break;
                case "int": return 'form-control'; break;
                case "byte": return 'form-control'; break;

            }
        }
        /*
        ***************************************
        */


        function selectValue(model,item2) {
            vm.model = model;
            vm.isEdit = true;
            cargarFormGenerico(model,item2);

            $('html, body').animate({ scrollTop: 0 }, 'slow');
            vm.tabSeleccion = 'createOrEdit';
            if (model != null) {
                Flash.create('success', $rootScope.PARAMETRICA_CARGADA_CON_EXITO, 5000, { class: 'custom-class', id: 'custom-id' }, true);

            }
        }

                /*
        Metodo que carga el formulario generico con los datos de un modelo
        */
       function cargarFormGenerico(model, item2){
        //console.log(model);
        var items = Object.keys(item2);
        console.log(item2);
        console.log(items);
        console.log($rootScope);


        var elementos = document.getElementsByName("idformularioGenerico");
        var elementosForm = elementos[0];
        for (var i = 0; i < elementosForm.length; i++) {
            for( var b = 0 ; b < items.length  ; b++){
                if ( items[b] == elementosForm[i].id){
                    elementosForm[i].value = Object.values(item2)[b] ;
                }
            }
        }
     
    }

        function action() {
          
            var modelTemp = "{";
            for (var b = 0; b < vm.modelList.column.length; b++) {
                modelTemp = GuardarValueJson(modelTemp, b);

            }
            modelTemp = modelTemp.substring(0, modelTemp.length - 1);
            modelTemp = modelTemp + "}"
            vm.model = JSON.parse(modelTemp);
            if (vm.isEdit == false) {
                save();
            } else {
                edit();
            }
        }

        /*Metod que verifica si es la ultima posicion
        */
        function GuardarValueJson(modelTemp,b ) {
            if( findNameValue(vm.modelList.column[b].nameColumn) != null){
                return modelTemp = modelTemp + '"' + vm.modelList.column[b].nameColumn + '"' + " : " + '"' + findNameValue(vm.modelList.column[b].nameColumn) + '" ,';
            }else{
                return modelTemp;
            }

        }

        /*
        *Metodo que me busca el valor en el formulario por nombre
        */
        function findNameValue(idName) {
            var elementos = document.getElementsByName("idformularioGenerico");
            var elementosForm = elementos[0];
            for (var i = 0; i < elementosForm.length; i++) {
                if (elementosForm[i].id == idName) {
                    if (elementosForm[i].value  !== 'undefined'  && elementosForm[i].value != null &&   elementosForm[i].value !== "") {
                        return elementosForm[i].value
                    }else{
                        return null;
                    }
                    
                }
                //alert(" Elemento: " + elementos[i].value + "\n Seleccionado: " + elementos[i].checked);
            }
        }



        function clean() {
            vm.isEdit = false;
            vm.model = null;
        }

        
        function close() {
            vm.isEdit = false;
            vm.model = null;
            vm.tabSeleccion = 'ver';
        }




        function get() {
            ParametricasService.getAll(nameUrl)
                .then(function (response) {
                    if (response.status == 200) {
                        vm.safelist = response.data;
                        vm.listModels = response.data;
                    } else {
                        Flash.create('danger', $rootScope.ERROR_GENERARL_SERVIDOR, 5000, { class: 'custom-class', id: 'custom-id' }, true);

                    }
                });
        }

        function getAll() {
            ParametricasService.getAll(nameModel)
                .then(function (response) {
                    if (response.status == 200) {
                        vm.ssafelistModelsSelecion  = response.data;
                        vm.listModelsSelecion = response.data;
                    } else {
                        Flash.create('danger', $rootScope.ERROR_GENERARL_SERVIDOR, 5000, { class: 'custom-class', id: 'custom-id' }, true);

                    }
                });
        }

        function save() {

            ParametricasService.save(nameModel, vm.model)
                .then(function (response) {
                    if (response.status == 200) {
                        getAll()
                        Flash.create('success', $rootScope.PARAMETRICA_GUARDADO_CON_EXITO, 5000, { class: 'custom-class', id: 'custom-id' }, true);
                        $('html, body').animate({ scrollTop: 0 }, 'slow');
                        close();
                    } else {
                        if (response.data !== 'undefined' && response.data != null) {
                            Flash.create('danger', response.data, 5000, { class: 'custom-class', id: 'custom-id' }, true);
                            $('html, body').animate({ scrollTop: 0 }, 'slow');

                        } else {
                            Flash.create('danger', $rootScope.ERROR_GENERARL_SERVIDOR, 5000, { class: 'custom-class', id: 'custom-id' }, true);
                            $('html, body').animate({ scrollTop: 0 }, 'slow');

                        }

                    }
                });
        }

        function edit() {
            ParametricasService.edit(nameModel, vm.model)
                .then(function (response) {
                    if (response.status == 200) {
                        getAll()
                        Flash.create('success', $rootScope.PARAMETRICA_MODIFICADO_CON_EXITO, 5000, { class: 'custom-class', id: 'custom-id' }, true);
                        $('html, body').animate({ scrollTop: 0 }, 'slow');
                        close();
                    } else {
                        if (response.status == 202) {
                            Flash.create('danger', $rootScope.PARAMETRICA_NO_EXISTENTE, 5000, { class: 'custom-class', id: 'custom-id' }, true);
                            $('html, body').animate({ scrollTop: 0 }, 'slow');
                        } else {
                            Flash.create('danger', $rootScope.ERROR_202_SERVIDOR, 5000, { class: 'custom-class', id: 'custom-id' }, true);
                            $('html, body').animate({ scrollTop: 0 }, 'slow');
                        }

                    }
                });
        }

        function find() {
            ParametricasService.find(nameModel, vm.model)
                .then(function (response) {
                    if (response.status == 200) {
                        $scope.safelist = response.data;
                        $scope.listTipoDocumentos = response.data;
                    } else {
                        if (response.status == 202) {
                            Flash.create('danger', $rootScope.ERROR_202_SERVIDOR, 5000, { class: 'custom-class', id: 'custom-id' }, true);
                            $('html, body').animate({ scrollTop: 0 }, 'slow');

                        } else {
                            Flash.create('danger', $rootScope.ERROR_202_SERVIDOR, 5000, { class: 'custom-class', id: 'custom-id' }, true);
                            $('html, body').animate({ scrollTop: 0 }, 'slow');
                        }

                    }
                });
        }


        function delet(model) {
            vm.model = model;
            ParametricasService.delet(nameModel, vm.model)
                .then(function (response) {
                    if (response.status == 200) {
                        getAll()
                        Flash.create('success', $rootScope.PARAMETRICA_ELIMINADO_CON_EXITO, 5000, { class: 'custom-class', id: 'custom-id' }, true);
                        $('html, body').animate({ scrollTop: 0 }, 'slow');


                    } else {
                        if (response.status == 202) {
                            Flash.create('danger', $rootScope.ERROR_202_SERVIDOR, 5000, { class: 'custom-class', id: 'custom-id' }, true);
                            $('html, body').animate({ scrollTop: 0 }, 'slow');
                        } else {
                            Flash.create('danger', $rootScope.ERROR_202_SERVIDOR, 5000, { class: 'custom-class', id: 'custom-id' }, true);
                            $('html, body').animate({ scrollTop: 0 }, 'slow');
                        }

                    }
                });
        }







    }




})();