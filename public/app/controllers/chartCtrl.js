angular.module('domobyPi').controller('ChartCtrl',
		function($scope, temperatureService) {

	console.log($scope.sensor);
	this.sensor = $scope.sensor ;
	this.sensor.values = [] ;
	this.sensor.minValue = 0 ;
	this.sensor.maxValue = 0 ;
	this.sensor.currentValue = 0 ;
	
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
					style : {
						display : 'none'
					}
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
						text: null
					}
				},
				yAxis : {
					title : {
						text : 'todo'
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
					data : this.sensor.values,
					showInLegend: false
				} ]
			};

			this.update = function(measure) {
				//console.log("Receiving "+measure.temp+" on "+this.sensor.id);
				var value = Math.round(measure.temp / 1000 * 100) / 100 ;
				this.sensor.values.push([measure.date, value]) ;
				
				this.sensor.currentValue = value ;
				if (this.sensor.minValue == 0 && this.sensor.maxValue == 0) {
	                this.sensor.minValue = value ;
	                this.sensor.maxValue = value ;
	            } else if (value < this.sensor.minValue ) {
	                this.sensor.minValue = value ;
	            }
				else if (value > this.sensor.maxValue) {
	                this.sensor.maxValue = value;				
				}
			}
			
		} );