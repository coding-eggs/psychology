'use strict';

$(document).ready(function() {

	function generateData(baseval, count, yrange) {
		var i = 0;
		var series = [];
		while (i < count) {
			var x = Math.floor(Math.random() * (750 - 1 + 1)) + 1;;
			var y = Math.floor(Math.random() * (yrange.max - yrange.min + 1)) + yrange.min;
			var z = Math.floor(Math.random() * (75 - 15 + 1)) + 15;

			series.push([x, y, z]);
			baseval += 86400000;
			i++;
		}
		return series;
	}




	//Pie Chart
	var pieCtx = document.getElementById("invoice_chart"),
	pieConfig = {
		colors: ['#7638ff', '#ff737b', '#fda600', '#1ec1b0'],
		series: [55, 40, 20, 10],
		chart: {
			fontFamily: 'Poppins, sans-serif',
			height: 350,
			type: 'donut',
		},
		labels: ['Paid', 'Unpaid', 'Overdue', 'Draft'],
		legend: {show: false},
		responsive: [{
			breakpoint: 480,
			options: {
				chart: {
					width: 200
				},
				legend: {
					position: 'bottom'
				}
			}
		}]
	};
	var pieChart = new ApexCharts(pieCtx, pieConfig);
	pieChart.render();
  
});