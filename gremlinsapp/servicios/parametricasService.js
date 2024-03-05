(function () {
    'use strict';

    angular
        .module('app')
        .factory('ParametricasService', ParametricasService);

    ParametricasService.$inject = ['$http', '$rootScope','$timeout', '$q', 'Flash','$location', '$localStorage'];
    function ParametricasService($http, $rootScope, $timeout, $q, Flash,$location , $localStorage) {
        var service = {};

        service.save = save;
        service.getAll = getAll;
        service.findById = findById;
        service.edit = edit;
        service.find = find;
        service.delet = delet;
        service.getbyfilter = getbyfilter;
        service.findByCase = findByCase;

        service.saveFile = saveFile;
        service.saveImg = saveImg;
        service.editFile = editFile;


        return service;
        /// metodo que para invocar loading 




        function getAll(nameParametrica) {
            $rootScope.$broadcast("verLoading");
      
            var deferred = $q.defer();
            $http({
                headers: {
                    'Content-Type': 'application/json',
                    "Access-Control-Allow-Origin": "*",
                    'Accept': '*/*'
                },
                method: 'GET',
                url: $rootScope.URL + $rootScope.PARAMETRICAS.get(nameParametrica) + '/' + $rootScope.REST.get('GETALL')
                // data:  JSON.stringify(dto) 
            }).then(function successCallback(response) {
                $rootScope.$broadcast("ocultarLoading");
                deferred.resolve(response);
            }, function errorCallback(response) {
                $rootScope.$broadcast("ocultarLoading");
                if( response.status == 403){
                    $rootScope.globalData = {
                        verTopMenu: false,
                        verLeeftMenu: false
                    };
                    $localStorage.globals = null;
                    $location.path('/login');
                }
                deferred.resolve(response);
            });

            return deferred.promise;
        };

        function getbyfilter(nameParametrica, filter) {
            $rootScope.$broadcast("verLoading");
            var deferred = $q.defer();
            $http({
                headers: {
                    'Content-Type': 'application/json',
                    "Access-Control-Allow-Origin": "*",
                    'Accept': '*/*'
                },
                method: 'GET',
                url: $rootScope.URL + $rootScope.PARAMETRICAS.get(nameParametrica) + '/' + $rootScope.REST.get('GETALL')+ getFilter(filter)
                // data:  JSON.stringify(dto) 
            }).then(function successCallback(response) {
                $rootScope.$broadcast("ocultarLoading");
                deferred.resolve(response);
            }, function errorCallback(response) {
                $rootScope.$broadcast("ocultarLoading");
                deferred.resolve(response);
            });
            $rootScope.loading = false;
            return deferred.promise;
        };

        function getFilter(filters) {
            
            if (filters !== 'undefined' && filters != null) {
                var items = Object.keys(filters);
                var urlComplement = "";
                for (var a = 0; a < items.length; a++) {
                        urlComplement = urlComplement + "/"+ Object.values(filters)[a];
                }
                return urlComplement;
            }else{
                return "";
            }
        }


        function saveFile(nameParametrica, dto) {
            $rootScope.$broadcast("verLoading");
            var deferred = $q.defer();
            $http({
                headers: {
                    'Content-Type': undefined
                },
                method: 'POST',
                data: dto,
                url: $rootScope.URL + $rootScope.PARAMETRICAS.get(nameParametrica) + '/' + $rootScope.REST.get('CREATE')
                // data:  JSON.stringify(dto) 
            }).then(function successCallback(response) {
                $rootScope.$broadcast("ocultarLoading");
                deferred.resolve(response);
            }, function errorCallback(response) {
                $rootScope.$broadcast("ocultarLoading");
                mostrarError(response, "Guardar Archivo ");
                deferred.resolve(response);
            });
            $rootScope.loading = false;
            return deferred.promise;
        }

        function saveImg(nameParametrica, dto){
            $rootScope.$broadcast("verLoading");
            var deferred = $q.defer();
            $http({
                headers: {
                    'Content-Type': undefined
                },
                method: 'POST',
                data: dto,
                url: $rootScope.URL + $rootScope.PARAMETRICAS.get(nameParametrica) + '/' + $rootScope.REST.get('IMG')
                // data:  JSON.stringify(dto) 
            }).then(function successCallback(response) {
                $rootScope.$broadcast("ocultarLoading");
                deferred.resolve(response);
            }, function errorCallback(response) {
                $rootScope.$broadcast("ocultarLoading");
                mostrarError(response, "Guardar Archivo ");
                deferred.resolve(response);
            });
            $rootScope.loading = false;
            return deferred.promise;
        }

        function save(nameParametrica, dto) {
            $rootScope.$broadcast("verLoading");
            var deferred = $q.defer();
            $http({
                headers: {
                    'Content-Type': 'application/json',
                    "Access-Control-Allow-Origin": "*",
                    'Accept': '*/*'
                },
                method: 'POST',
                data: dto,
                url: $rootScope.URL + $rootScope.PARAMETRICAS.get(nameParametrica) + '/' + $rootScope.REST.get('CREATE')
                // data:  JSON.stringify(dto) 
            }).then(function successCallback(response) {
                $rootScope.$broadcast("ocultarLoading");
                deferred.resolve(response);
            }, function errorCallback(response) {
                $rootScope.$broadcast("ocultarLoading");
                mostrarError(response, "Guardar ");
                deferred.resolve(response);
            });
            $rootScope.loading = false;
            return deferred.promise;
        }

        function edit(nameParametrica, dto) {
            $rootScope.$broadcast("verLoading");
            var deferred = $q.defer();
            $http({
                headers: {
                    'Content-Type': 'application/json',
                    "Access-Control-Allow-Origin": "*",
                    'Accept': '*/*'
                },
                method: 'PUT',
                url: $rootScope.URL + $rootScope.PARAMETRICAS.get(nameParametrica) + '/' + $rootScope.REST.get('EDIT'),
                data: dto
            }).then(function successCallback(response) {
                $rootScope.$broadcast("ocultarLoading");
                deferred.resolve(response);
            }, function errorCallback(response) {
                $rootScope.$broadcast("ocultarLoading");
                mostrarError(response, "Editar ");
                deferred.resolve(response);
            });
            $rootScope.loading = false;
            return deferred.promise;
        }


        function editFile(nameParametrica, dto) {
            $rootScope.$broadcast("verLoading");
            var deferred = $q.defer();
            $http({
                headers: {
                    'Content-Type': undefined
                },
                method: 'POST',
                data: dto,
                url: $rootScope.URL + $rootScope.PARAMETRICAS.get(nameParametrica) + '/' + $rootScope.REST.get('EDIT')
                // data:  JSON.stringify(dto) 
            }).then(function successCallback(response) {
                $rootScope.$broadcast("ocultarLoading");
                deferred.resolve(response);
            }, function errorCallback(response) {
                $rootScope.$broadcast("ocultarLoading");
                mostrarError(response, "Editar Archivo");
                deferred.resolve(response);
            });
            $rootScope.loading = false;
            return deferred.promise;
        }

        function find(nameParametrica) {
            $rootScope.$broadcast("verLoading");
            var deferred = $q.defer();
            $http({
                headers: {
                    'Content-Type': 'application/json',
                    "Access-Control-Allow-Origin": "*",
                    'Accept': '*/*'
                },
                method: 'GET',
                url: $rootScope.URL + $rootScope.PARAMETRICAS.get(nameParametrica) + '/' + $rootScope.REST.get('GET')
                // data:  JSON.stringify(dto) 
            }).then(function successCallback(response) {
                $rootScope.$broadcast("ocultarLoading");
                deferred.resolve(response);
            }, function errorCallback(response) {
                $rootScope.$broadcast("ocultarLoading");
                mostrarError(response, "Buscar");
                deferred.resolve(response);
            });
            $rootScope.loading = false;
            return deferred.promise;
        }


        function findByCase (nameParametrica, model) {
            $rootScope.$broadcast("verLoading");
            var deferred = $q.defer();
            $http({
                headers: {
                    'Content-Type': 'application/json',
                    "Access-Control-Allow-Origin": "*",
                    'Accept': '*/*'
                },
                method: 'GET',
                url: $rootScope.URL + $rootScope.PARAMETRICAS.get(nameParametrica) + '/' + model
                // data:  JSON.stringify(dto) 
            }).then(function successCallback(response) {
                $rootScope.$broadcast("ocultarLoading");
                deferred.resolve(response);
            }, function errorCallback(response) {
                $rootScope.$broadcast("ocultarLoading");
                mostrarError(response, "Buscar");
                deferred.resolve(response);
            });
            $rootScope.loading = false;
            return deferred.promise;
        }


        function findById(nameParametrica, model) {
            $rootScope.$broadcast("verLoading");
            var deferred = $q.defer();
            $http({
                headers: {
                    'Content-Type': 'application/json',
                    "Access-Control-Allow-Origin": "*",
                    'Accept': '*/*'
                },
                method: 'POST',
                data: model,
                url: $rootScope.URL + $rootScope.PARAMETRICAS.get(nameParametrica) + '/' + $rootScope.REST.get('FINDBYID')
                // data:  JSON.stringify(dto) 
            }).then(function successCallback(response) {
                $rootScope.$broadcast("ocultarLoading");
                deferred.resolve(response);
            }, function errorCallback(response) {
                $rootScope.$broadcast("ocultarLoading");
                mostrarError(response, "Buscar");
                deferred.resolve(response);
            });
            $rootScope.loading = false;
            return deferred.promise;
        }


        function delet(nameParametrica, dto) { 
            $rootScope.$broadcast("verLoading");

            var deferred = $q.defer();
            $http({
                headers: {
                    'Content-Type': 'application/json',
                    "Access-Control-Allow-Origin": "*",
                    'Accept': '*/*'
                },
                method: 'DELETE',
                url: $rootScope.URL + $rootScope.PARAMETRICAS.get(nameParametrica) + '/' + $rootScope.REST.get('DELETE'),
                data: dto
            }).then(function successCallback(response) {
                $rootScope.$broadcast("ocultarLoading");
                deferred.resolve(response);
            }, function errorCallback(response) {
                $rootScope.$broadcast("ocultarLoading");
                mostrarError(response, "Borrar");
                deferred.resolve(response);
            });
            $rootScope.loading = false;
            return deferred.promise;
        }




        function mostrarError(response, operacion) {
            if (response !== 'undefined' && response != null) {

                if (response.status == 500) {
                    Flash.create('danger', $rootScope.ERROR_GENERAL_RESPONSER_500 + operacion, 5000, { class: 'custom-class', id: 'custom-id' }, true);
                    $('html, body').animate({ scrollTop: 0 }, 'slow');
                }

                if (response.status == 403) {
                    Flash.create('danger', $rootScope.SESSION_EXPIRO + operacion, 5000, { class: 'custom-class', id: 'custom-id' }, true);
                    $('html, body').animate({ scrollTop: 0 }, 'slow');
                }

            }

        }




    }



})();