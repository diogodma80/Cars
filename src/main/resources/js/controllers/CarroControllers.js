/**
 * Controller responsável pelo distribuição das funcionalidades do sistema.
 */
(function() {
	angular
		.module('CarControllers', ['carRestService', 'carService']);
	
	var type = [{
		type: "Classico",
		id: "classicos"
	}, 
	{
		type: "Esportivo",
		id: "esportivos"
	},
	{
		type: "Luxo",
		id: "luxo"
	}]
	
	var CARS_CONFIG = {
		type: type
	}
	
	angular
		.module('CarControllers')
		.constant("CARS_CONFIG", CARS_CONFIG);
	
	angular
		.module('CarControllers')
		.controller("CarFilterController", CarFilterController);

	function CarFilterController($scope, ngDialog, carRestService, carService) {
		$scope.carService = carService;
		$scope.findCarByName = function () {
			if($scope.name) {
				var $promise = carRestService.searchByName($scope.name);
				$promise.success(function(response, status) {
					if(status == 204) {
						$scope.carService.cars = null;
					}
					carService.set(response);
					$scope.carService = carService;
				});
				return;
			}
			var $promise = carRestService.findAll();
			$promise.success(function(response) {
				$scope.carService.cars = response;
			});
		};
	}
	
	angular
		.module('CarControllers')
		.controller('CarListController', CarListController);
	
	function CarListController($scope, ngDialog, carRestService, carService) {
		$scope.carService = carroService;
		$scope.tab = 0;
		
		$scope.deleteCar = function(car) {
			$scope.car = car;
			ngDialog.open({ 
				template: './pages/cars/modal.confirm.html',
				scope: $scope,
				showClose: false,
				closeByEscape: false,
				closeByDocument : false
			});
		}
		
		$scope.confirmDeletar = function() {
			carroRestService.delete($scope.carro).success(function() {
				ngDialog.closeAll();
				ngDialog.open({
					template:'\
						<p>Carro deletado com sucesso</p>\
		                <div class="ngdialog-buttons">\
		                    <button type="button" class="ngdialog-button ngdialog-button-primary" ng-click="closeThisDialog(0)">OK</button>\
		                    \</div>',
		            plain: true
				});
				$scope.carro = null;
				carregarCarros();
			});
		}
		
		$scope.openModalEditar = function(carro) {
			ngDialog.open({ 
				template: './pages/cars/modal.form.html',
				showClose: false,
		        closeByDocument: false,
		        closeByEscape: false,
		        controller: 'CarroEditController',
		        resolve: {
		            carro: function() {
		                return carro;
		            }
		        }
			});
		}
		
		var carregarCarros = function() {
			var $promise = carroRestService.findAll();
			$promise.success(function(response) {
				$scope.carroService.cars = response;
			});
		}
		carregarCars();

		$scope.changeFilter = function(tipo, tab) {
			$scope.tab = tab;
			if(tipo) {
				var $promise = carroRestService.findByTipo(tipo);
				$promise.success(function(response) {
					$scope.carroService.cars = response;
				});
				return;
			}
			carregarCars();
		}
	}
	
	
	
	angular
		.module('CarroControllers')
		.controller('CarroNewController', CarroNewController);
	function CarroNewController($scope, $window, ngDialog, carroRestService, CARS_CONFIG, Upload) {
		$scope.types = CARS_CONFIG.tipo;
		$scope.carro = {};
		$scope.openModalCadasro = function() {
			ngDialog.open({ 
				template: './pages/cars/modal.form.html',
				scope: $scope,
				showClose: false,
		        closeByDocument: false,
		        closeByEscape: false
			});
		}
		
		$scope.upload = function(file) {
			if(file) {
				$scope.progress = true;
				$scope.progressPhoto = true;
				var $promise = carroRestService.uploadPhoto(file);
	    		$promise.success(function(response) {
	    			if(response.status == 'OK') {
	    				$scope.carro.urlPhoto = response.url;
	    				$scope.progress = false;
	    				$scope.progressPhoto = false;
	    				return;
	    			}
	    			alert("Não foi possível realiazr o upload do arquivo verifique se configurou todos os dados do Bucket do google corretamente");
	    		});
			}
		}
		
	    $scope.salvar = function (carro) {
	    	var $promise = carroRestService.save(carro);
    		$promise.success(function() {
    			$scope.progress = false;
    			$window.location.reload();
    		});
	    };
	}
	angular
		.module('CarroControllers')
		.controller('CarroEditController', CarroEditController);
	
	function CarroEditController($scope, $window, ngDialog, carroRestService, CARS_CONFIG, carro) {
		$scope.carro = carro;
		$scope.types = CARS_CONFIG.tipo;
		$scope.upload = function(file) {
			if(file) {
				$scope.progress = true;
				$scope.progressPhoto = true;
				var $promise = carRestService.uploadPhoto(file);
	    		$promise.success(function(response) {
	    			if(response.status == 'OK') {
	    				$scope.car.urlPhoto = response.url;
	    				$scope.progress = false;
	    				$scope.progressPhoto = false;
	    				return;
	    			}
	    			alert("Não foi possível realiazr o upload do arquivo verifique se configurou todos os dados do Bucket do google corretamente");
	    		});
			}
		}
		
	    $scope.save = function (car) {
	    	var $promise = carRestService.save(car);
    		$promise.success(function() {
    			$scope.progress = false;
    			$window.location.reload();
    		});
	    };
	}
} ());