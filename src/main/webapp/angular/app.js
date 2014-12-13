angular.module('fhwsApp', [])
        .controller('userController', ['$scope', '$http', function ($scope, $http) {
                $http.get("http://localhost:8080/fhws/api/info")
                        .success(function (info) {
                            console.log(info);
                            $scope.info = info;
                        });

                $http.get("http://localhost:8080/fhws/api/users")
                        .success(function (users) {
                            $scope.users = users;
                        });


            }]);
