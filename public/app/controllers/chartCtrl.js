angular.module('domobyPi').controller('ChartCtrl',
		function($scope, temperatureService) {

	console.log($scope.sensor);
	this.sensor = $scope.sensor ;
	this.sensor.temps = [] ;
	this.sensor.min = 0 ;
	this.sensor.max = 0 ;
	this.sensor.current = 0 ;
	
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
				var value = Math.round(measure.temp / 1000 * 100) / 100 ;
				this.sensor.temps.push([measure.date, value]) ;
				
				this.sensor.current = value ;
				if (this.sensor.min == 0 && this.sensor.max == 0) {
	                this.sensor.min = value ;
	                this.sensor.max = value ;
	            } else if (value < this.sensor.min ) {
	                this.sensor.min = value ;
	            }
				else if (value > this.sensor.max) {
	                this.sensor.max = value;				
				}
				/*
	            if (value < this.sensor.min) {
	                $scope.minTemp = $scope.temp;
	            } else if ($scope.temp > $scope.maxTemp) {
	                $scope.maxTemp = $scope.temp;
	            }
	            */
			}
			
		} );