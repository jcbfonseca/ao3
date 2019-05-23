var app = angular.module('myapp', ['ngResource','ngRoute']);

app.constant("CONSTANTS", {
	getUserByIdUrl : "/user/getUser/",
	getAllUsers : "/user/getAllUsers",
	saveUser : "/user/saveUser",
	GET_ALL_USERS_BY_BOOK_ID: "/user/getAllUsersByBookId",
	
	getBookByIdUrl : "/book/getBook/",
	getAllBooks : "/book/getAllBooks",
	getAllBooksByUserId : "/book/getAllBooksByUserId",
	saveBook : "/book/saveBook",
	
	SAVE_USER_BOOK: "/book/saveUserBook/",
	GET_USER_BOOK: "/book/getUserBook/"
});

// Definindo Rotas
app.config(function($routeProvider, $locationProvider) {
  
  $routeProvider
  .when('/', {
      templateUrl: 'home.html',
      controller: 'HomeController'
    })
  .when('/detalhar', {
      templateUrl: 'views/detalhar.html',
      controller: 'DetailsController'
    })
    .when('/meusLivros', {
      templateUrl: 'views/livros-meus.html',
      controller: 'MeusLivrosController'
    })
    .when('/internetLivros', {
      templateUrl: 'views/livros-internet.html',
      controller: 'BookListFromInternet'
    })
    .when('/contato', {
      templateUrl: 'views/contato.html',
      controller: 'ContactController'
    })
    .when('/logout', {
      templateUrl: '',
      controller: 'LogoutController'
    })
    .otherwise({
      redirectTo: '/'
    });
    
    // Utilizando o HTML5 History API
    $locationProvider.html5Mode(true);
});

app.controller('DetailsController', function($scope, BookService1, UserBookService) {
	$scope.currentItem=BookService1.getItem();
    $scope.userBookDto = {
    		book_id: $scope.currentItem.id,
            star: null,
            startDate: null,
            endDate: null,
            opinion: null,
            progress: null
    };
   
    
    UserBookService.getUserBook($scope.currentItem.id).then(function(value) {
        console.log("SUCESSO - getUserBook");
    	$scope.userBookDto = value.data;
        console.log($scope.userBookDto.startDate);

    }, function(reason) {
        console.log("error occured");
    }, function(value) {
        console.log("no callback");
    });

    UserBookService.getAllUsersByBookId($scope.currentItem.id).then(function(value) {
        console.log("SUCESSO - getAllUsersByBookId");
    	$scope.userResults = value.data;

    }, function(reason) {
        console.log("error occured");
    }, function(value) {
        console.log("no callback");
    });
    
	

    console.log($scope.userBookDto.startDate);
    $scope.updateStar = function () {
    	var json = "{\"star\": " + $scope.userBookDto.star + "}";
    	UserBookService.saveUserBookPost($scope.userBookDto.book_id, json);
    }
    
    $scope.updateStartDate = function () {
    	var json = "{\"startDate\": 1}";
    	UserBookService.saveUserBookPost($scope.userBookDto.book_id, json).then(function(value) {
        	$scope.userBookDto = value.data;
        });
    }
    
    $scope.updateEndDate = function () {
    	var json = "{\"endDate\": 1}";
    	UserBookService.saveUserBookPost($scope.userBookDto.book_id, json).then(function(value) {
        	$scope.userBookDto = value.data;
        });
    }
	
});

app.controller('BookListFromInternet', function ($scope, BookService,BookService1) {
    $scope.filter = "";
    $scope.bookDto = {
            title: null,
            authors: null,
            imgLink: null
    };
    
    $scope.doSearch = function () {
    	if($scope.filter.length === 0){
            $scope.filterMsg = "Digite uma palavra no campo Filtro.";
         }else{
            $scope.filterMsg = "";
            BookService.get({ q: $scope.filter }, function (response) {
                $scope.bookResults = response.items;
            });
         }    	
    }
    $scope.remove=function(item){ 
        var index=$scope.bookResults.indexOf(item)
        $scope.bookResults.splice(index,1);     
      }

    $scope.saveBook=function(item){
    	console.log('javascript-savebook()');
        var index=$scope.bookResults.indexOf(item)
        $scope.bookDto.title=item.volumeInfo.title;
        $scope.bookDto.authors=item.volumeInfo.authors[0];
        $scope.bookDto.imgLink=item.volumeInfo.imageLinks.smallThumbnail;

        BookService1.saveBookPost($scope.bookDto).then(function() {
            console.log("SUCESSO");
        	$scope.itemSaved="Salvo";
            $scope.bookDto = {
                title: null,
                authors: null
            };
        }, function(reason) {
            console.log("error occured");
        }, function(value) {
            console.log("no callback");
        });
        
      }
    
});

app.controller('MeusLivrosController', function ($scope, $location, BookService1) {
        BookService1.getAllBooksByUserId().then(function(value) {
        	console.log(value.data);
        	$scope.bookResults = value.data;
        }, function(reason) {
            console.log("error occured");
        }, function(value) {
            console.log("no callback");
        });
	    
	    $scope.detalhar=function(item){
	    	BookService1.setItem(item);
	    	$location.path("/detalhar");
	    }
	    
	    $scope.remove=function(item){ 
	        var index=$scope.bookResults.indexOf(item)
	        $scope.bookResults.splice(index,1);     
	      }
	});


/*
 * app.controller('AboutController', function($scope) { $scope.message = 'You
 * can see more about ngRoute in the oficial website.'; });
 */
app.controller('ContactController', function($scope) {
	  $scope.message = 'No. :P';
	});

app.controller('LogoutController', function($scope) {
    localStorage.clearAll();
    window.open("/logout", "_parent");
	});

//$scope.logout = function () {
//    localStorage.clearAll();
//    window.location = '/logout';
//};


app.filter('formatFilter', function() {
  return function(text) {
    if (text !== null) {
      for (var i in text) {
        var authors = text[i];
      }
      return authors;
    }
  };
});


app.filter('dateFilter', function() {
  return function(text) {
    if (text !== null) {
      var d = new Date(text);
      var day = d.getDate();
      var month = d.getMonth() + 1;
      var year = d.getFullYear();
      return (month + "/" + day + "/" + year);
    }
  };
});


app.factory('BookService', function ($resource) {
    return $resource('https://www.googleapis.com/books/v1/volumes', {maxResults: '40', callback: 'JSON_CALLBACK', key: 'AIzaSyATldFLGtPPZVLecasP0nFXkX6RqXa7VEI'},{ get: { method: 'JSONP' }});
});


app.factory('BookService1', ["$http", "CONSTANTS", function($http, CONSTANTS) {

	 var service = {};
	 var savedItem = {}
	 service.setItem = function(data) {
		 savedItem = data;
	 }
	 service.getItem = function() {
	  return savedItem;
	 }

	
    service.getBookById = function(bookId) {
        var url = CONSTANTS.getBookByIdUrl + bookId;
        return $http.get(url);
    }
    service.getAllBooksByUserId = function() {
        return $http.get(CONSTANTS.getAllBooksByUserId);
    }
    service.saveBookPost = function(bookDto) {
        return $http.post(CONSTANTS.saveBook, bookDto);
    }
    return service;
}]);

app.factory('UserBookService', ["$http", "CONSTANTS", function($http, CONSTANTS) {

	 var service = {};

     service.getAllUsersByBookId = function(book_id) {
		 console.log('getAllUsersByBookId - ' + book_id);
        return $http.get(CONSTANTS.GET_ALL_USERS_BY_BOOK_ID,book_id);
     }
	 service.saveUserBookPost = function(book_id, json) {
         var url = CONSTANTS.SAVE_USER_BOOK + book_id;
		 return $http({
			  method: 'PATCH',
			  url: url,
			  data: json
			});
     }
	 service.getUserBook = function(book_id) {
		 console.log('getUserBook - ' + book_id);
         var url = CONSTANTS.GET_USER_BOOK + book_id;
         return $http.get(url);
     }
   return service;
}]);