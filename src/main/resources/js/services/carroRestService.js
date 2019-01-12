/**
 * API para acessar os web services REST dos carros
 */
(function() {
	angular
		.module('carRestService', []);
	
	carRestService = function($http, Upload, APP_CONFIG) {
		var urlApi = APP_CONFIG.API_REST + '/cars';

		function findAll() {
			return $http.get(this.api);
		}
		
		function save(car) {
			if(car.id) {
				return $http.put(this.api, car);
			}
			return $http.post(this.api, car);
		}
		
		function find(id) {
			return $http.get(this.api + '/' + id);
		}
		
		function deleteCar(car, file) {
			return $http.delete(this.api + '/' + car.id);
		}
		
		function uploadPhoto(file) {
			return Upload.upload({
                url: this.api,
                file: file
            });
		}
		
		function findByType(type) {
			return $http.get(this.api + '/type/' + type);
		}
		
		function findByName(name) {
			return $http.get(this.api + '/name/' + name);
		}
		
		function searchByName(name) {
			return $http.get(this.api + '/name/' + name);
		}
		
		return {
			api: urlApi,
			save: save,
			findAll: findAll,
			find: find,
			findByType: findByType,
			findByName: findByName,
			delete: deleteCar,
			searchByName: searchByName,
			uploadPhoto: uploadPhoto
		}
	}
	angular
		.module('carRestService')
		.service('carRestService', carRestService);
} ());