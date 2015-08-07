angular.module('domobyPi').controller('ChartCtrl',
		function($scope, temperatureService) {

	console.log($scope.sensor);
	this.sensor = $scope.sensor ;
	this.sensor.temps = [] ;
	
	temperatureService.register(this);
	
	
			this.config = {
				options : {
					chart : {
						type : 'spline'
					},
					tooltip : {
						valueSuffix : '°C'
					},
				},
				title : {
					text : 'Sensor '+this.sensor.type+' '+this.sensor.id,
					x : -20
				// center
				},
				xAxis : {
					type : 'datetime',
					dateTimeLabelFormats : {
						month : '%e. %b',
						year : '%b',
					},
					tickColor: '#00004A',
				    tickWidth: 2,
				    minorGridLineWidth: 0,
		            minorTickInterval: 'auto',
		            minorTickColor: '#000000',
		            minorTickWidth: 1,
					title : {
						text: null,
						style: { "color": "#00002E", "fontWeight": "bold" }
					}
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
				legend: {
					enabled: false
				},
				series : [ {
					name : 'Home',
					data : this.sensor.temps
				} ]
			};

			this.update = function(measure) {
				console.log("Receiving "+measure.temp+" on "+this.sensor.id);
				this.sensor.temps.push([measure.date, Math.round(measure.temp / 1000 * 100) / 100]) ;
			}
			
		} );