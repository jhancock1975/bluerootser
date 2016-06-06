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
	})
	
	// route for the help page
	.when('/signup', {
		templateUrl : '/signup',
		controller  : 'signupController'
	});

});


//create the controller and inject Angular's $scope
blueRootserApp.controller('mainController', function($scope, $http) {

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
	$scope.loginPreSubmit = function(){
		console.log('login pre submit');
		passwdHash = sha256_digest($('#inputPassword').val());
		$('#inputPassword').val(passwdHash);
	};
});

blueRootserApp.controller('helpController', function($scope) {
	console.log("site help page");
});

blueRootserApp.controller('memorizationTechniquesController', function($scope) {
	console.log("memorization techniques page");
});

blueRootserApp.controller('signupController', function($scope, $http, $location) {
	console.log("signup page");
	
	var resetForm = function(postObject){
		$('#updateMessages').text('To create a new account,\
				please fill out the form below.');
				
		setClass($('#updateMessages'), 'normal');

		$('#newPasswdLabel').text('New Password');
		$('#newPasswdLabel2').text('New Password (Again)');
		$('#inputEmailLabel').text('Email address');
		
		[$('#inputPassword'), $('#inputNewPassword'), $('#inputNewPassword2'), $('inputEmail')].forEach(function(elt) {
			elt.removeClass('err');
		});

	};
	
	$scope.createUser = function(){
		var postObject = new Object();
		
		postObject.userName = $('#inputEmail').val();
		postObject.email = $('#inputEmail').val();
		postObject.firstName = $('#inputFirstName').val();
		postObject.lastName = $('#inputLastName').val();
		postObject.dob = $('#inputDob').val();
		postObject.newPassword = sha256_digest($('#inputNewPassword').val());
		postObject.newPassword2 = sha256_digest($('#inputNewPassword2').val());
		postObject.password = sha256_digest($('#inputNewPassword').val());
		
		resetForm(postObject);
		updateUser('/createUser', postObject, $http, $location, $scope, '/login');
	};
});

var validate = function(postObject){
	
	var valid  = true;		

	if (! (postObject.newPassword === postObject.newPassword2)){
		valid=false;
		[$('#newPasswdLabel'), $('#newPasswdLabel2')].forEach(function(elt){
			elt.text('New passwords do not match.');
		});
		
		[$('#inputNewPassword'), $('#inputNewPassword2')].forEach(function(elt){
			setClass(elt, 'err');
		});
	} 
	

	var emailAddr = $('#inputEmail').val();
	if ( isBlank(emailAddr) || emailAddr.indexOf('@') === -1){
		valid = false;
		$('#inputEmailLabel').text("Email address does not contain a '@' character");
		$('#inputEmail').addClass('err');
	}
	
	if (isBlank(postObject.password)){
		valid = false;
		$('#inputPassword').addClass('err');
		$('#curPasswordLbl').text(' Please enter your current password.');
	}
	return valid;
};

/*
 * we need this to avoid having multiple styles applied
 * to ensure we get the proper background color
 */
var setClass = function(elt, classname){
	['normal', 'err', 'successful'].forEach(function(cssStyleName){
		elt.removeClass(cssStyleName);
	});
	
	elt.addClass(classname);
}

var updateUser = function(restOperation, postObject, $http, $location, $scope, pathStr){
		
	if (validate(postObject, $('#updateUserForm'))){
		$http.defaults.headers.post['X-CSRF-TOKEN'] = $('#csrfToken').val();
		$http({
			url: restOperation,
			dataType: 'json',
			method: 'POST',
			data: postObject,
			headers: {
				"Content-Type": "application/json"
			}
		})
		.success(function (response){
			$('#updateMessages').text(response.updateMsg);
			if (response.updateStatus === true){
				setClass($('#updateMessages'), 'successful');
				$location.path(pathStr);
			} else {
				setClass($('#updateMessages'), 'err');
			}
		}).error(function(response){
			console.log('error');
			$('#updateMessages').text(response.updateMsg);
			setClass($('#updateMessages'), 'err');
			console.log(response);
		});
	}
};
blueRootserApp.controller('myAreaController', function($scope, $http, $location) {
	console.log('in my area controller');
	
	
	
	var resetForm = function(postObject){
		$('#updateMessages').text('To update your account settings, \
				enter your current password below, make changes,\
				and click on the update button. Changes will be saved to your account.');
				
		setClass($('#updateMessages'), 'normal');

		$('#newPasswdLabel').text('New Password');
		$('#newPasswdLabel2').text('New Password (Again)');
		$('#inputEmailLabel').text('Email address');
		
		[$('#inputPassword'), $('#inputNewPassword'), $('#inputNewPassword2'), $('inputEmail')].forEach(function(elt) {
			elt.removeClass('err');
		});
		
		$('#curPasswordLbl').text('Current Password');

	}

	$scope.updateUser = function(){

		//http://stackoverflow.com/questions/29867310/angularjs-does-not-post-json-data-to-rest-api
		var postObject = new Object();
		postObject.userName = $('#curUserId').val();
		postObject.email = $('#inputEmail').val();
		postObject.firstName = $('#inputFirstName').val();
		postObject.lastName = $('#inputLastName').val();
		postObject.dob = $('#inputDob').val();
		postObject.newPassword = sha256_digest($('#inputNewPassword').val());
		postObject.newPassword2 = sha256_digest($('#inputNewPassword2').val());
		postObject.password = sha256_digest($('#inputPassword').val());

		console.log(postObject);

		resetForm(postObject);
		updateUser('/updateUser', postObject, $http, $location, '/myArea'); 
	}
});
