angular.module('myApp.register', ['ngRoute'])

.config(['$routeProvider', function($routeProvider) {
  $routeProvider.when('/register', {
    templateUrl: 'register/register.html',
    controller: 'registerCtrl',
    controllerAs : 'ctrl'
  });
}])

.controller('registerCtrl', ["$http",function($http) {
  var self = this;
  
  self.register = function(){
      $http.post("api/register",self.user);
  }
}]);