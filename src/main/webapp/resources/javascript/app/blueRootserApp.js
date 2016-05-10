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
		templateUrl : '/home',
		controller  : 'mainController'
	})

	// route for the login page
	.when('/login', {        	
		templateUrl : '/loginView',
		controller  : 'loginController'
	})


	// route for the contact page
	.when('/myArea', {
		templateUrl : '/myArea',
		controller  : 'myAreaController'
	})

	// route for the update user form
	.when('/updateUser', {
		templateUrl : '/updateUser',
		controller  : 'updateUser'
	})

	// route for the memorization techniques page
	.when('/memorizationTechniques', {
		templateUrl : '/memorizationTechniques',
		controller  : 'memorizationTechniquesController'
	})

	// route for the help page
	.when('/help', {
		templateUrl : '/help',
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
	result = $http.post("/articles", data).success(function(data, status, headers, config)	{
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
	console.log('in my area controller');

	var validate = function(postObject, form){
		
		var valid  = true;
		var errColor='#FA93AA';

		$('#newPasswdLabel1').text('New Password');
		$('#newPasswdLabel2').text('New Password (Again)');
		$('#inputEmailLabel').text('Email address');
		[$('#inputNewPassword1'), $('#inputNewPassword2'), $('inputEmail')].forEach(function(elt) {
			elt.removeClass('err');
		});

		if (! (postObject.newPassword1 === postObject.newPassword2)){
			valid=false;
			$('#newPasswdLabel1').text('New passwords do not match.');
			$('#newPasswdLabel2').text('New passwords do not match.');
			$('#inputNewPassword1').addClass('err');
			$('#inputNewPassword2').addClass('err');
		} 
		
		var emailAddr = $('#inputEmail').val();
		if ( isBlank(emailAddr) || emailAddr.indexOf('@') === -1){
			valid = false;
			$('#inputEmailLabel').text("Email address does not contain a '@' character");
			$('#inputEmail').addClass('err');
		}
		return valid;
	};

	$scope.updateUser = function(){
		//http://stackoverflow.com/questions/29867310/angularjs-does-not-post-json-data-to-rest-api
		var postObject = new Object();
		postObject.userName = $('#curUserId').val();
		postObject.email = $('#inputEmail').val();
		postObject.firstName = $('#inputFirstName').val();
		postObject.lastName = $('#inputLastName').val();
		postObject.dob = $('#inputDob').val();
		postObject.newPassword1 = $('#inputNewPassword1').val();
		postObject.newPassword2 = $('#inputNewPassword2').val();


		console.log(postObject);
		
		if (validate(postObject, $('#updateUserForm'))){
			$http.defaults.headers.post['X-CSRF-TOKEN'] = $('#_csrf').val();
			$http({
				url: '/updateUser',
				dataType: 'json',
				method: 'POST',
				data: postObject,
				headers: {
					"Content-Type": "application/json"
				}
			})
			.success(function (response){
				console.log('Your account was updated successfully.');
			}).error(function(error){
				console.log('error');
				$('#updateMessages').text('Server error updating account.');
				console.log(error);
			});
		}
	};
});
