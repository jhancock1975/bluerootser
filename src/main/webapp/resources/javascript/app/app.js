/**
 * 
 */
var app = angular.module('store', ['ui.bootstrap']);
app.controller("StoreController", function($scope) {
    $scope.message = "";
    $scope.left  = function() {return 100 - $scope.message.length;};
    $scope.clear = function() {$scope.message = "";};
    $scope.save  = function() {alert("Note Saved");};
});
