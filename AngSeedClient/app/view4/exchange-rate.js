'use strict';

angular.module('myApp.exchange-rate', ['ngRoute'])

        .config(['$routeProvider', function ($routeProvider) {
                $routeProvider.when('/exchange-rate', {
                    templateUrl: 'view4/exchange-rate.html',
                    controller: 'exchangeController',
                    controllerAs: 'ctrl'
                });
            }]).controller('exchangeController', function ($http, $scope) {

    var self = this;

    var exchangerates = {};

    $http.get("api/currency/dailyrates").then(function (result) {
        self.data = result.data;
        
        self.data.forEach(function(entry){
            exchangerates[entry.code] = entry.rate;
        });

    }, function (error) {

    });
    
    self.calculate = function(){
      self.total = (self.amount * exchangerates[self.from]) / exchangerates[self.to]; 
    };



});