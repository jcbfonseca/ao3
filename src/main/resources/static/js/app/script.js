var app = angular.module('myapp', ['ngResource','ngRoute']);

app.constant("CONSTANTS", {
	GET_ALL_USERS_BY_BOOK_ID: "/user/userbook/",//GET
	GET_ALL_BOOKS_BY_USER_ID : "/book/booksByUserId",//GET

	SAVE_BOOK : "/book/",//POST
	SAVE_USER_BOOK: "/book/userbook/",//PATCH
	GET_USER_BOOK: "/book/userbook/",//GET
	DELETE_USER_BOOK : "/book/userbook/",//DELETE
});

// Definindo Rotas
app.config(function($routeProvider, $locationProvider) {
  
  $routeProvider
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
    	console.log($scope.userResults[0])
    	console.log($scope.userResults[0].userBookDtos[0].star)

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
    
    $scope.updateOpinion = function () {
    	console.log('updateOpinion - ' + $scope.userBookDto.opinion);
    	var json = "{\"opinion\": \"" + $scope.userBookDto.opinion + "\"}";
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
    $scope.saveData= {};
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
                $scope.saveData= {};
                $scope.filter = "";
                $scope.bookResults = response.items;
            });
         }    	
    }
//    $scope.remove=function(item){ 
//        var index=$scope.bookResults.indexOf(item)
//        $scope.bookResults.splice(index,1);     
//      }

    $scope.saveBook=function(item){
        var index=$scope.bookResults.indexOf(item)
    	console.log('javascript-savebook() - index=' + index);
        $scope.bookDto.title=item.volumeInfo.title;
        $scope.bookDto.authors=item.volumeInfo.authors[0];
        $scope.bookDto.imgLink=item.volumeInfo.imageLinks.smallThumbnail;
        
        	

        BookService1.saveBookPost($scope.bookDto).then(function() {
            console.log("SUCESSO - " + index);
        	$scope.saveData[index]="Salvo";
            $scope.bookDto = {
                title: null,
                authors: null
            };
        }, function(reason) {
        	$scope.saveData[index]="Erro";
            console.log("error occured");
        }, function(value) {
            console.log("no callback");
        });
        }
    
});

app.controller('MeusLivrosController', function ($scope, $location, BookService1) {
        $scope.saveData= {};
        
        BookService1.getAllBooksByUserId().then(function(value) {
        	console.log(value.data);
        	$scope.bookResults = value.data;
        }, function(reason) {
        	$scope.saveData[index]="Erro";
            console.log("error occured");
        }, function(value) {
            console.log("no callback");
        });
	    
	    $scope.detalhar=function(item){
	    	BookService1.setItem(item);
	    	$location.path("/detalhar");
	    }
	    
	    $scope.remove=function(item){ 
	        var index=$scope.bookResults.indexOf(item);
	        console.log("delete - " + index + " and Id=" + $scope.bookResults[index].book_id);
	    	BookService1.deleteUserBook($scope.bookResults[index].id).then(function() {
		        $scope.bookResults.splice(index,1);     
	        }, function(reason) {
	            console.log("error occured");
	        }, function(value) {
	            console.log("no callback");
	        });
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

	
    service.getAllBooksByUserId = function() {
        return $http.get(CONSTANTS.GET_ALL_BOOKS_BY_USER_ID);
    }
    service.saveBookPost = function(bookDto) {
        return $http.post(CONSTANTS.SAVE_BOOK, bookDto);
    }
    service.deleteUserBook = function(book_id) {
		 console.log('Service - deleteUserBook - ' + book_id);
    	var url = CONSTANTS.DELETE_USER_BOOK + book_id;
        return $http.delete(url);
    }
    return service;
}]);

app.factory('UserBookService', ["$http", "CONSTANTS", function($http, CONSTANTS) {

	 var service = {};

     service.getAllUsersByBookId = function(book_id) {
		 console.log('getAllUsersByBookId - ' + book_id);
         var url = CONSTANTS.GET_ALL_USERS_BY_BOOK_ID + book_id;
        return $http.get(url);
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