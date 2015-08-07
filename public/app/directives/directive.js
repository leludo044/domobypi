angular.module('domobyPi').directive('statsTemperature', function () {
    return {
        restrict: 'E',
        transclude: true,

        scope: {
		      sensor: '=sensor'
		    },
        templateUrl: '/assets/app/views/sensor.html'
    };
});