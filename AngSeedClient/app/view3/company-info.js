'use strict';

angular.module('myApp.company-info', ['ngRoute'])

        .config(['$routeProvider', function ($routeProvider) {
                $routeProvider.when('/company-info', {
                    templateUrl: 'view3/company-info.html',
                    controller: 'companyInfoCtrl',
                    controllerAs: 'ctrl'
                });
            }])

        .controller('companyInfoCtrl', function ($http, $scope) {

            var self = this;

            self.search = function () {
                var searchType;

                switch (self.option) {
                    case 'Regular':
                        searchType = 'search';
                        break;
                    case 'CVR':
                        searchType = 'vat';
                        break;
                    case 'Production entity':
                        searchType = 'produ';
                        break;
                    case 'Phone':
                        searchType = 'phone';
                        break;
                }

                var url = 'http://cvrapi.dk/api?' + searchType + '=' + self.query + '&country=' + self.country.toLowerCase();;

                $http.get(url).then(function (result) {
                    self.data = result.data;
                }, function (error) {

                });
            };



        });