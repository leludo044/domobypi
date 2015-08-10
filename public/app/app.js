var testConnectivity = {
	serveur : "localhost:9000",
	ledstate : "/mock/ledstate",
	led : "/mock/led",
	websocket : "/socket"
};

var piConnectivity = {
	serveur : "pi:9000",
	ledstate : "/json/ledstate",
	led : "/json/led",
	websocket : "/socket"
};

var internetConnectivity = {
	serveur : "leludo.dtdns.net:9000",
	ledstate : "/json/ledstate",
	led : "/json/led",
	websocket : "/socket"
};

var domobypi = angular.module('domobyPi', [ "highcharts-ng" ])

domobypi.value("connect", testConnectivity);

domobypi.config([ '$controllerProvider', function($controllerProvider) {
	$controllerProvider.allowGlobals();
} ]);

angular.module('domobyPi').controller(
		'LedController',
		function($scope, $http, connect, temperatureService) {

			ledState = function() {
				$http.get('http://' + connect.serveur + connect.ledstate)
						.success(function(data) {
							$scope.led = data.led;
						});
			};
			ledState();
			$scope.isOn = function() {
				return $scope.led == "on";
			};
			$scope.ledOn = function() {
				$http.get('http://' + connect.serveur + connect.led + '/on')
						.success(function(data) {
							$scope.led = data.led;
						});
			};
			$scope.ledOff = function() {
				$http.get('http://' + connect.serveur + connect.led + '/off')
						.success(function(data) {
							$scope.led = data.led;
						});
			};

			sensor = function() {
				$http.get(url + '/sensor').success(function(data) {
					$scope.temp = Math.round((data.temp / 1000) * 100) / 100;
				});
			};

			$scope.updateTemp = function(measure) {
			}

			$scope.sensors = [];

			temperatureService.start($scope);

			$http.get('http://' + connect.serveur + '/json/sensors').success(
					function(data) {
						$scope.sensors = data;
					});
		});

angular.module('domobyPi').factory(
		'temperatureService',
		function($q, connect) {

			function temperatureService() {
				var self = this;

				var temp = 0;
				var deffered = $q.defer();
				var registeredScope = null;
				var ws = null;
				var isStarted = false;
				var controllers = {};

				self.start = function(scope) {
					registeredScope = scope;
					console.log("starting with " + registeredScope);
					ws = new WebSocket("ws://" + connect.serveur
							+ connect.websocket);

					ws.onopen = function() {
						console.log("Socket has been opened!");
					}

					ws.onclose = function() {
						console.log("Socket has benn closed!");
					}

					ws.onmessage = function(message) {

						measure = JSON.parse(message.data);
						date = new Date(measure.date);
						registeredScope.$apply(function() {
						});
						deffered.resolve(message);
						console.log("Receiving "+measure.temp + " at " + date.getHours()
								+ ":" + date.getMinutes() + ":"
								+ date.getSeconds()+" from " +measure.id);
						controllers[measure.id].update(measure);
					}

					isStarted = true;
				}

				self.stop = function() {
					ws.close();
					isStarted = false;
				}

				self.isStarted = function() {
					return isStarted;
				}

				self.getTemp = function() {
					// return temp ;
					return deffered.promise;
				}

				self.register = function(ctrl) {
					console.log("Registration of " + ctrl.sensor.id);
					controllers[ctrl.sensor.id] = ctrl;
				}
			}

			return new temperatureService();
		});