/**
 * 
 */
//create the module and name it blueRootserApp
var blueRootserApp = angular.module('blueRootserApp', [ 'ui.bootstrap', 
                                                        'ngRoute','ngSanitize']);

//configure our routes
blueRootserApp.config(function($routeProvider) {
	$routeProvider

	// route for the home page
	.when('/', {
		templateUrl : '/bluerootser/home',
		controller  : 'mainController'
	})

	// route for the login page
	.when('/login', {        	
		templateUrl : '/bluerootser/loginView',
		controller  : 'loginController'
	})


	// route for the contact page
	.when('/myArea', {
		templateUrl : '/bluerootser/myArea',
		controller  : 'myAreaController'
	})
	
	// route for the memorization techniques page
	.when('/memorizationTechniques', {
		templateUrl : '/bluerootser/memorizationTechniques',
		controller  : 'memorizationTechniquesController'
	})
	
	// route for the help page
	.when('/help', {
		templateUrl : '/bluerootser/help',
		controller  : 'helpController'
	});

});


//create the controller and inject Angular's $scope
blueRootserApp.controller('mainController', function($scope, $http) {
	// create a message to display in our view
	$scope.message = 'Everyone come and see how good I look!';
	$scope.onselect ="";

	var data = {};
	$http.defaults.headers.post['X-CSRF-TOKEN'] = csrfTokenVal;
	result = $http.post("/bluerootser/articles", data).success(function(data, status, headers, config)	{
		console.log('success');
		$scope.artcileHtml =  data;
	}).error(function(data, status, headers, config){
		console.log('error');
		console.log("data = " + data);
		console.log("scope.message = " + scope.message)
	});

	$scope.onselect = function(){
		console.log("on select");
	}

});



blueRootserApp.directive("highlight", ['$http', function($http) {
	return function(scope, element, attrs) {
		
		element.on('mouseup', function(event) {
			console.log("mouse up");
			
		});
		element.on("mousedown", function(event){
			console.log("mouse down");
		});
		
		zhongwenMain.loadDictionary();
		zhongwenContent.enableTab();
		window.zhongwen.config = zhongwenMain.config;
		element.on("mousemove", function(event){
			zhongwenContent.onMouseMove(event);
		});
		
	}
}]);

blueRootserApp.controller('loginController', function($scope) {
	$scope.message = 'Look! I am the login page.';
});

blueRootserApp.controller('helpController', function($scope) {
	console.log("site help page");
});

blueRootserApp.controller('memorizationTechniquesController', function($scope) {
	console.log("site help page");
});

blueRootserApp.controller('myAreaController', function($scope, $http) {
	$http.get('/bluerootser/user')
		.success(function (data){
			console.log('success');
		}).error(function(data){
			console.log('error');
		});
});
