/**
 * 
 */
// create the module and name it blueRootserApp
var blueRootserApp = angular.module('blueRootserApp', [ 'ui.bootstrap',
		'ngRoute' ]);

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
            templateUrl : '/login',
            controller  : 'loginController'
        })
        
        // route for the logout page
        .when('/logout', {        	
            templateUrl : '/login',
            controller  : 'logoutController'
        })
        
        // route for the contact page
        .when('/myArticles', {
            templateUrl : '/myArticles',
            controller  : 'myArticlesController'
        });


});

// create the controller and inject Angular's $scope
blueRootserApp.controller('mainController', function($scope) {
    // create a message to display in our view
    $scope.message = 'Everyone come and see how good I look!';
});

blueRootserApp.controller('loginController', function($scope) {
    $scope.message = 'Look! I am the login page.';
});

blueRootserApp.controller('myArticlesController', function($scope) {
    $scope.message = 'Contact us! JK. This is just a demo.';
});

blueRootserApp.controller('logoutController', function($scope, $http) {
    console.log("logout controller ");
    console.log("$scope= " + $scope + "token name " + csrfTokenName + " token value " + csrfToken);
    $http.defaults.headers.post['X-CSRF-TOKEN']=csrfToken;
    $http.post("/logout", data)
    	
    	.success( function(data, status, headers, config){
    		console.log("logout success");
    	})
    	.error(function(data, status, headers, config){
    		console.log("logout error");
    		console.log(status);
    		console.log(data);
    	});
});