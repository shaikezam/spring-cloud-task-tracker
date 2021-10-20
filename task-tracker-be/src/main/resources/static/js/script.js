var app = angular.module('myApp', [ 'ngRoute' ]);
app.controller('customersCtrl', function($scope, $http, $route) {
	function get() {
		$http.get("/webapi/tasks").then(function(response) {
			$scope.tasks = response.data;
		});
	};
	function post(title, description) {
		$http.post("/webapi/tasks", {
			"title" : title,
			"description" : description
		}).then(function(response) {
			get();
		});
	};
	function put(id, title, description) {
		$http.put("/webapi/tasks/" + id, {
			"title" : title,
			"description" : description
		}).then(function(response) {
			get();
		});
	};
	function deleteRequest(id) {
		$http.delete("/webapi/tasks/" + id).then(function(response) {
			$scope.tasks = response.data;
		}).then(function(response) {
			get();
		});
	};
	$scope.submit = function(task) {
		var title = (task && task.title) ? task.title : "";
		var description = (task && task.description) ? task.description : "";
		post(title, description);
	};
	$scope.updateTask = function(id, title, description) {
		put(id, title, description);
	};
	$scope.deleteTask = function(id) {
		deleteRequest(id);
	}
	$scope.showTask = function(task) {
		$scope.id = task.id;
		$scope.title = task.title;
		$scope.description = task.description;
	};

	get();
});