<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/3.6.0/chart.min.js"></script>

<title>Insert title here</title>
</head>
<body>
	<div id ="chart">
		<canvas id="ctx"></canvas>
	</div>
<script>
	var year='${year}'
	$(function(){
		$.getJSON("/sales/"+year)
		.done(function(data){
			console.log(data);
			var ctx=$("#ctx").get(0).getContext('2d');
			var labels=data.map(obj=>obj.MONTH);
			var datas=data.map(obj=>obj.SALES);
			console.log(labels)
			console.log(datas)
			var bgColor=['rgba(255, 99, 132, 0.2)',
			                'rgba(54, 162, 235, 0.2)',
			                'rgba(255, 206, 86, 0.2)',
			                'rgba(75, 192, 192, 0.2)',
			                'rgba(153, 102, 255, 0.2)',
			                'rgba(255, 159, 64, 0.2)'
			                ];
			var borderColor=[
							'rgba(255,99,132,1)',
			                'rgba(54, 162, 235, 1)',
			                'rgba(255, 206, 86, 1)',
			                'rgba(75, 192, 192, 1)',
			                'rgba(153, 102, 255, 1)',
			                'rgba(255, 159, 64, 1)'
			            	];
			
			var myChart = new Chart(ctx, {
			    type: 'bar',
			    data: {
			        labels: labels ,
			        datasets: [{
			            label: '월별매출현황',
			            data: datas,
			            backgroundColor:bgColor,
			            borderColor:borderColor,
			            borderWidth: 1
			        }]
			    },
			    options: {
			        maintainAspectRatio: true, // default value. false일 경우 포함된 div의 크기에 맞춰서 그려짐.
			        scales: {
			            yAxes: [{
			                ticks: {
			                    beginAtZero:true
			                }
			            }]
			        }
			    }
			});
			
		})
	})
</script>
</body>
</html>