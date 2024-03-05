(function () {
    'use strict';
    
    var appGlobal = angular
       // .module('app', ['ngRoute', 'ngMaterial','ngCookies','ngStorage','smart-table','ngFlash','ngFileUpload','ngSanitize'])
        .module('app', ['ngRoute','ngStorage','smart-table','ngFlash','ngFileUpload','ngSanitize','ui.tinymce'])
        .config(config)
        .run(run);

    config.$inject = ['$routeProvider', '$locationProvider','$qProvider'];
    function config($routeProvider, $locationProvider,$qProvider) {
        
        $routeProvider
            .when('/home', {
                controller: 'HomeController',
                templateUrl: 'componentes/home/home.view.html',
                controllerAs: 'vm'
            })
            .when('/login', {
                controller: 'LoginController',
                templateUrl: 'componentes/login/login.view.html',
                controllerAs: 'vm'
            })

            .when('/register', {
                controller: 'RegisterController',
                templateUrl: 'componentes/registro/register.view.html',
                controllerAs: 'vm'
            })
          .when('/perfil', {
            controller: 'profileController',
            templateUrl: 'componentes/profile/admin.view.html',
            controllerAs: 'vm'
           })
            


            .otherwise({ redirectTo: '/home' });

           $qProvider.errorOnUnhandledRejections(false);

         
         

    }

   

    run.$inject = ['$rootScope', '$location', '$http','$localStorage'];
    function run($rootScope, $location, $http,$localStorage) {


        $rootScope.activeLoanding = false;
        $rootScope.active = 0;

        $rootScope.ERROR_GENERARL_SERVIDOR = "ERROR INTENTANDO COMUNICARSE";
        $rootScope.ERROR_LOGIN_202_SERVIDOR = "LAS CREDENCIALES SON INVALIDAS O INCORRECTAS";
        $rootScope.EXITO_LOGIN_200_SERVIDOR = "INGRESO CORRECTO";
        $rootScope.ERROR_GENERAL_RESPONSER_500= "NO SE HA PODIDO EJECUTAR LA OPERACION :";

         //NOTIFICACIONES PARAMETRICAS 
        $rootScope.PARAMETRICA_GUARDADO_CON_EXITO = "SE HA GUARDADO CON EXITO ";
        $rootScope.PARAMETRICA_MODIFICADO_CON_EXITO = "SE HA MODIFICADO CON EXITO ";
        $rootScope.PARAMETRICA_ELIMINADO_CON_EXITO = "SE HA ELIMINADO CON EXITO ";
        $rootScope.PARAMETRICA_CARGADA_CON_EXITO = "SE HA CARGADO CON EXITO ";
        $rootScope.PARAMETRICA_YA_EXISTENTE = "YA EXISTE LA PARAMETRICA A CREAR ";
        $rootScope.PARAMETRICA_NO_EXISTENTE = "NO EXISTE LA PARAMETRICA A MODIFICAR ";
        $rootScope.PARAMETRICA_ERROR_GUARDADO = "NO SE A PODIDO GUARDAR  ";


        $rootScope.OPCION_SELECCION_CORRECTA = "SE HA SELECCIONADO CORRECTAMENTE   ";

        // SU SESSION A CADUCADO, INTENTE LOGUIARSE DE NUEVO
        $rootScope.SESSION_EXPIRO = "SESSION_EXPIRO ";

        $rootScope.PARAMETRICA_ERROR_DATOS_CONTACTO= "ERROR CARGANDO LOS DATOS DE CONTACTO ";


        $rootScope.PARAMETRICAS = new Map()

         //// URL PARAMETRICAS GENERICAS ////
         $rootScope.PARAMETRICAS.set('PARAMETRICAS', 'Parameters');
        /// PARAMETRICAS URL API JUZGADO////
        $rootScope.PARAMETRICAS.set('TIPO_IDENTIFICACION', 'TipoIdentificacion');
    


    

        ///REST SERVICES
        $rootScope.REST = new Map()
        $rootScope.REST.set('GETALL', 'getAll');
        $rootScope.REST.set('FINDBYID', 'findById');
        $rootScope.REST.set('GET', 'get');
        $rootScope.REST.set('CREATE', 'create');
        $rootScope.REST.set('IMG', 'img');
        $rootScope.REST.set('EDIT', 'edit');
        $rootScope.REST.set('DELETE', 'delete');
        

        $rootScope.globalData = {
            verTopMenu: false,
            verLeeftMenu: false
        };
   
        $rootScope.changeView = function(view) {
            $location.path(view);
        }

        $rootScope.URLBASE ="http://localhost:4003/";

        $rootScope.URL ="http://localhost:8080/gremlinsapi/";
     
        
       

        if ($localStorage.globals  !== 'undefined'  && $localStorage.globals != null ) {
            $http.defaults.headers.common['Authorization'] =  $localStorage.globals.token; // jshint ignore:line
            // Bnderas mostrar control de menu y usuario 
            $rootScope.globalData = {
                verTopMenu: true,
                verLeeftMenu: true
            };
            $rootScope.globals = $localStorage.globals;
            $rootScope.restrictedPage = $.inArray($location.path(), ['/login', '/register']) === -1;
            $rootScope.loggedIn = true;
            $location.path('/home');
        }else{
          $http.defaults.headers.common['Authorization'] =  null;
          $rootScope.globalData = {
            verTopMenu: false,
            verLeeftMenu: false
        };
        $rootScope.globals = null;
        $rootScope.restrictedPage = $.inArray($location.path(), ['/login', '/register']) === -1;
        $rootScope.loggedIn = false;
        }

        $rootScope.logout = function() {
            $rootScope.globalData = {
                verTopMenu: true,
                verLeeftMenu: true
            };
            $rootScope.globals = null;
            $localStorage.globals = null;

        };

        $rootScope.$on('$locationChangeStart', function (event, next, current) {
            // redirect to login page if not logged in and trying to access a restricted page
            if ($rootScope.restrictedPage &&  $rootScope.loggedIn == false) {
                  $rootScope.globalData = {
                    verTopMenu: false,
                    verLeeftMenu: false
                };
                $rootScope.globals = null;
                $localStorage.globals = null;
                $location.path('/login');
            }


        });

        $rootScope.$on("verLoading", function(){


              $rootScope.activeLoanding = true;
              $rootScope.active ++;



      });
      $rootScope.$on("ocultarLoading", function(){

            $rootScope.active --;
            if($rootScope.active == 0 ){
              $rootScope.activeLoanding = false;
            }


        
    });

     

    }

    appGlobal.directive('convertToNumber', function() {
        return {
          require: 'ngModel',
          link: function(scope, element, attrs, ngModel) {
            ngModel.$parsers.push(function(val) {
              return parseInt(val, 10);
            });
            ngModel.$formatters.push(function(val) {
              return '' + val;
            });
          }
        };
      });

      appGlobal.directive('stringToNumber', function() {
        return {
          require: 'ngModel',
          link: function(scope, element, attrs, ngModel) {
            ngModel.$parsers.push(function(value) {
              return '' + value;
            });
            ngModel.$formatters.push(function(value) {
              return parseFloat(value);
            });
          }
        };
      });


  
      

    appGlobal.directive('dateField', function($filter) {
        return {
            require: 'ngModel',
            link: function(scope, element, attrs, ngModelController) {
                 ngModelController.$parsers.push(function(data) {
      
                    //View -> Model
                    //var date = Date.parseExact(data,'dd/MM/yyyy');
      
                    // if the date field is not a valid date 
                    // then set a 'date' error flag by calling $setValidity
                    //ngModelController.$setValidity('date', date!=null);
                    //return date == null ? undefined : date;
                    var ano = data.getUTCFullYear();
                    var mes = data.getUTCMonth();
                    var dia = data.getUTCDate();
                    return new Date(ano,mes ,dia);
                    //return data.toDateString();
                   // return data;
                 });
                 ngModelController.$formatters.push(function(data) {
                    //Model -> View
                  //  return $filter('date')(data, "dd/MM/yyyy");
                  // new Date(year, month, day, hours, minutes, seconds, milliseconds)
                  if( data !== 'undefined' && data != null){
                    var date = data.split('-');

                    var ano = parseInt(date[0]);
                    var mes = parseInt(date[1]);
                    var dia = parseInt(date[2]);
                    return new Date(ano,mes-1,dia,0,0,0,0);
                  }else{
                    return null;
                  }
                  
                    
                    //return $filter('date')(data, "yyyy-MM-dd");
                 });    
             }
          }
      });
      

  
})();




