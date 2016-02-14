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
            templateUrl : '/',
            controller  : 'mainController'
        })

        // route for the about page
        .when('/login', {        	
            templateUrl : '/login',
            controller  : 'loginController'
        })

        // route for the contact page
        .when('/myArticles', {
            templateUrl : 'pages/contact.html',
            controller  : 'contactController'
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

blueRootserApp.controller('contactController', function($scope) {
    $scope.message = 'Contact us! JK. This is just a demo.';
});