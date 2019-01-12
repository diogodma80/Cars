/**
 * Arquivo que mantém a lista o estado da lista de carros. 
 */
(function() {

	angular.module('carService', []);
	
	function car() {
		// lista de carros
		var cars = [];

		function setCars(cars) {
			this.cars = cars;
		}
		
		function getCars() {
			return this.cars;
		}
		
		return {
			cars: cars,
			set : setCars,
			get : getCars
		}
	}
	
	angular.module('carService').factory('carService', car);
}());