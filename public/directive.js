angular.module('domobyPi').directive('statsTemperature', function () {
    return {
        restrict: 'E',
        transclude: true,

        scope: {
		      sensor: '=sensor'
		    },
        templateUrl: '/assets/sensor.html'
    };
});