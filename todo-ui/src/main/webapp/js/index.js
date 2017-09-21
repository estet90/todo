angular
		.module('todo', [ 'ngWebSocket' ])
		.factory(
				'Tasks',
				function($websocket) {
					var ws = $websocket("ws://" + document.location.host + "/todo-api/statuses");
					var tasks = [];
					ws.onMessage(function(event) {
								console.log('message: ', event.data);
								var response;
								try {
									response = angular.fromJson(event.data);
								} catch (e) {
									document.getElementById("helloId").innerHTML = "Sorry, connection failed ...";
									document.getElementById("btnAtpId").disabled = false;
									console.log('error: ', e);
									response = {
										'error' : e
									};
								}
								if (response.hello) {
									document.getElementById("helloId").innerHTML = response.hello;
									document.getElementById("btnAtpId").disabled = false;
								} else {
									for (var i = 0; i < response.length; i++) {
										tasks.push({
											name : response[i].name,
											code : response[i].code
										});
									}
								}
							});
					ws.onError(function(event) {
						console.log('connection Error', event);
					});
					ws.onClose(function(event) {
						console.log('connection closed', event);
					});
					ws.onOpen(function() {
						console.log('connection open');
					});
					return {
						tasks : tasks,
						status : function() {
							return ws.readyState;
						},
						send : function(message) {
							if (angular.isString(message)) {
								ws.send(message);
							} else if (angular.isObject(message)) {
								ws.send(JSON.stringify(message));
							}
						}
					};
				}).controller('tasksController', function($scope, Tasks) {
			$scope.Tasks = Tasks;
			$scope.tasksList = function() {
				Tasks.send("filter");
			};
		});