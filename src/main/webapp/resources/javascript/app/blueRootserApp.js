/**
 * 
 */
// create the module and name it blueRootserApp
var blueRootserApp = angular.module('blueRootserApp', [ 'ui.bootstrap',
		'ngRoute','ngSanitize']);

//configure our routes
blueRootserApp.config(function($routeProvider) {
    $routeProvider

        // route for the home page
        .when('/', {
            templateUrl : '/home',
            controller  : 'mainController'
        })

        // route for the login page
        .when('/login', {        	
            templateUrl : '/loginView',
            controller  : 'loginController'
        })
        
        
        // route for the contact page
        .when('/myArticles', {
            templateUrl : '/myArticles',
            controller  : 'myArticlesController'
        });


});

// create the controller and inject Angular's $scope
blueRootserApp.controller('mainController', function($scope, $http, $sce) {
    // create a message to display in our view
    $scope.message = 'Everyone come and see how good I look!';
    var data = {};
    $http.defaults.headers.post['X-CSRF-TOKEN'] = csrfTokenVal;
    result = $http.post("/articles", data).success(function(data, status, headers, config)	{
    	console.log('success');
    	$scope.artcileHtml = $sce.trustAsHtml(data).toString();
    }).error(function(data, status, headers, config){
    	console.log('error');
    	console.log("data = " + data);
    	console.log("scope.message = " + scope.message)
    });
    
});

blueRootserApp.controller('loginController', function($scope) {
    $scope.message = 'Look! I am the login page.';
});

blueRootserApp.controller('myArticlesController', function($scope) {
    $scope.message = 'Contact us! JK. This is just a demo.';
});