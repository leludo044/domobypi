angular.module('domobyPi').controller('ChartCtrl',
		function($scope, temperatureService) {

	console.log($scope.sensor);
	this.sensor = $scope.sensor ;
	this.sensor.temps = [] ;
	
	temperatureService.register(this);
	
	
			this.config = {
				options : {
					tooltip : {
						valueSuffix : '°C'
					},
				},
				chart : {
					renderTo : $("div#random1"),
				},
				title : {
					text : 'Sensor '+this.sensor.type+' '+this.sensor.id,
					x : -20
				// center
				},
				yAxis : {
					title : {
						text : 'Temperature (°C)'
					},
					plotLines : [ {
						value : 0,
						width : 1,
						color : '#808080'
					} ]
				},
				tooltip : {
					valueSuffix : '°C'
				},
				legend : {
					layout : 'vertical',
					align : 'right',
					verticalAlign : 'middle',
					borderWidth : 0
				},
				series : [ {
					name : 'Home',
					data : this.sensor.temps
				} ]
			};

			this.update = function(measure) {
				console.log("Receiving "+measure.temp+" on "+this.sensor.id);
				this.sensor.temps.push(measure.temp) ;
			}
			
		} );