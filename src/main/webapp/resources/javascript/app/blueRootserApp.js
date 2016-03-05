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
	.when('/myArticles', {
		templateUrl : '/myArticles',
		controller  : 'myArticlesController'
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

//position of the tooltip relative to the mouse in pixel //
var offsetx = 12;
var offsety =  8;

function newelement(newid){
    if(document.createElement)
    {
        var el = document.createElement('div');
        el.id = newid;
        with(el.style)
        {
            display = 'none';
            position = 'absolute';
        }
        el.innerHTML = '&nbsp;';
        document.body.appendChild(el);
    }
}

var ie5 = (document.getElementById && document.all);
var ns6 = (document.getElementById && !document.all);
var ua = navigator.userAgent.toLowerCase();
var isapple = (ua.indexOf('applewebkit') != -1 ? 1 : 0);

function getMousePosition(e){
    if(document.getElementById) {
        var iebody=(document.compatMode &&
        	document.compatMode != 'BackCompat') ?
        		document.documentElement : document.body;
        pagex = (isapple == 1 ? 0:(ie5)?iebody.scrollLeft:window.pageXOffset);
        pagey = (isapple == window.scrollY ? 0:(ie5)?iebody.scrollTop:window.pageYOffset);
        mousex = (ie5)?event.x:(ns6)?clientX = e.clientX:false;
        mousey = (ie5)?event.y:(ns6)?clientY = e.clientY:false;

        var lixlpixel_tooltip = document.getElementById('tooltip');
        lixlpixel_tooltip.style.left = (mousex+pagex+offsetx) + 'px';
        lixlpixel_tooltip.style.top = (mousey+pagey+offsety) + 'px';
    }
}

function errMsg(tooltip, str) {
	tooltip.html(str + "not found");
}

function parseJson(tooltip){
	pinYin = $(wikiInfo).find('span[class*="pinyin"]').find("a").attr("title");
	zhuYin = $(wikiInfo).find("span[class='Bopo']");
	return pinYin + " " + zhuYin;
}

function lookup(str, $http, tooltip){
	
	wikiUrl = "https://en.wiktionary.org/" +
		"w/api.php?action=parse&format=json&prop=text|revid|displaytitle&callback=?&page="+str;
	
	$.getJSON(wikiUrl,
		    function(json) {
		      if((json.parse) && (json.parse.revid > 0)) {
		    	  tooltip.html(json.parse.text['*']);
		      } else {
		        errMsg(tooltip, str);
		      }
		    }).fail(function(jqxhr, textStatus, error){
		    	console.log(textStatus + " " + error);
		    	errMsg(tooltip, str);
		    	});
}

function tooltip(tip, element, $http){
    
	if(!document.getElementById('tooltip')){
		newelement('tooltip');
	}
    
	var tooltip = $('#tooltip');
    tooltip.html(tip);
    tooltip.css("display", "block");
    
    lookup(tip, $http, tooltip);
    
    element.on("mousemove", function(event){
		getMousePosition(event);
	});
}

function exit(element){
    document.getElementById('tooltip').style.display = 'none';
    element.on("mousemove", null);
}

blueRootserApp.directive("highlight", ['$http', function($http) {
	return function(scope, element, attrs) {
		element.on('mouseup', function(event) {
			console.log("mouse up");
			var text = "";
			if (window.getSelection) {
				text = window.getSelection().toString();
			} else if (document.selection && document.selection.type != "Control") {
				text = document.selection.createRange().text;
			}
			console.log(text);
			if (! isBlank(text)){
				tooltip(text, element, $http);
			}
		});
		element.on("mousedown", function(event){
			exit(element);
		});
		
	}
}]);

blueRootserApp.controller('loginController', function($scope) {
	$scope.message = 'Look! I am the login page.';
});

blueRootserApp.controller('myArticlesController', function($scope) {
	$scope.message = 'Contact us! JK. This is just a demo.';
});