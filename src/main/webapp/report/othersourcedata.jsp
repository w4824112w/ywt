<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="<%=request.getContextPath()%>/res/plugin/echarts-2.2.7/build/dist/echarts-all.js" charset="utf-8"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/res/plugin/jquery/jquery-1.8.3.js" charset="utf-8"></script>

</head>
<body>
	<div>
		<a href="<%=request.getContextPath()%>/travelflow/index.do" >返回首页</a>
	</div>
	<div id="main" style="height:400px"></div>
	<div id="sub" style="height:400px"></div>
	<div id="temp" style="height:400px"></div>
<script type="text/javascript">

$(function(){
	zhuzhuangtu();
	bingtu();
	monthzhuzhuangtu();
}); 

function zhuzhuangtu(){
	$.ajax({
		type : "POST",
		url : "othersourcedata.do",
		success : function(jsondata) {
		    // 基于准备好的dom，初始化echarts实例
		    var myChart = echarts.init(document.getElementById('main'));
    
 		   var data=eval('('+jsondata+')');
 		   
 		   var showdate="[{name:'女',type:'bar',stack: '人数',data:["+data.womenStr+"]},"+
  			"{name:'男',type:'bar',stack: '人数',data:["+data.manStr+"]}]";
 		   
 		//   var showdate="[{name:'总量',type:'bar',data:["+data.dataStr+"]}]";
 		   
 		  var finaldata=eval('('+showdate+')');
 		  
		    // 指定图表的配置项和数据
			option = {
			    tooltip : {
			        trigger: 'axis',
			        axisPointer : {            // 坐标轴指示器，坐标轴触发有效
			            type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
			        }
			    },
			    legend: {
			        data:['女','男']
			    },
			    toolbox: {
			        show : true,
			        orient: 'vertical',
			        x: 'right',
			        y: 'center',
			        feature : {
			            mark : {show: true},
			            dataView : {show: true, readOnly: false},
			            magicType : {show: true, type: ['line', 'bar', 'stack', 'tiled']},
			            restore : {show: true},
			            saveAsImage : {show: true}
			        }
			    },
			    calculable : true,
			    xAxis : [
			        {
			            type : 'category',
			            data : ['上海','湖北','河北','广东','天津','其他']
			        }
			    ],
			    yAxis : [
			        {
			            type : 'value'
			        }
			    ],
			    series : finaldata
 			    /* [
			        {
			            name:'女',
			            type:'bar',
			            stack: '人数',
			            data:[320, 332, 301, 334, 390, 330, 320]
			        },
			        {
			            name:'男',
			            type:'bar',
			            stack: '人数',
			            data:[62, 82, 91, 84, 109, 110, 120]
			        }
			    ]  */
			};
                    
			
			    // 使用刚指定的配置项和数据显示图表。
			    myChart.setOption(option);
			}
		
		});	
	
}

function bingtu(){
	$.ajax({
		type : "POST",
		url : "othersourceagedata.do",
		success : function(jsondata) {
		    // 基于准备好的dom，初始化echarts实例
		    var myChart = echarts.init(document.getElementById('sub'));
    
 		   var data=eval('('+jsondata+')');
 		   
 	//	   var showdate="[{name:'总量',type:'bar',data:["+data.dataStr+"]}]";
 		   
 	//	  var finaldata=eval('('+showdate+')');
 		  
		    // 指定图表的配置项和数据
			option = {
			    title : {
			        text: '外省游客年龄分布图',
			    //    subtext: '纯属虚构',
			        x:'center'
			    },
			    tooltip : {
			        trigger: 'item',
			        formatter: "{a} <br/>{b} : {c} ({d}%)"
			    },
			    legend: {
			        orient : 'vertical',
			        x : 'left',
			    //    data:['直接访问','邮件营销','联盟广告','视频广告','搜索引擎']
			        data:data.legends
			    },
			    toolbox: {
			        show : true,
			        feature : {
			            mark : {show: true},
			            dataView : {show: true, readOnly: false},
			            magicType : {
			                show: true, 
			                type: ['pie', 'funnel'],
			                option: {
			                    funnel: {
			                        x: '25%',
			                        width: '50%',
			                        funnelAlign: 'left',
			                        max: 1548
			                    }
			                }
			            },
			            restore : {show: true},
			            saveAsImage : {show: true}
			        }
			    },
			    calculable : true,
			    series : 
			     [
			        {
			            name:'访问来源',
			            type:'pie',
			            radius : '55%',
			            center: ['50%', '60%'],
			            data: data.datas
			           /*  [
			                {value:335, name:'直接访问'},
			                {value:310, name:'邮件营销'},
			                {value:234, name:'联盟广告'},
			                {value:135, name:'视频广告'},
			                {value:1548, name:'搜索引擎'}
			            ] */
			        }
			    ] 
			};
                     
                    
			
			    // 使用刚指定的配置项和数据显示图表。
			    myChart.setOption(option);
			}
		});	

}


function monthzhuzhuangtu(){
	$.ajax({
		type : "POST",
		url : "othersourcemonthdata.do",
		success : function(jsondata) {
		    // 基于准备好的dom，初始化echarts实例
		    var myChart = echarts.init(document.getElementById('temp'));
    
 		   var data=eval('('+jsondata+')');
 		   
 	//	   var showdate="[{name:'女',type:'bar',stack: '人数',data:["+data.womenStr+"]},"+
  	//		"{name:'男',type:'bar',stack: '人数',data:["+data.manStr+"]}]";
 		   
 		//   var showdate="[{name:'总量',type:'bar',data:["+data.dataStr+"]}]";
 		   
 		  var showdate="[";
 		  for(var i=0;i<data.datas.length;i++){
 			 if(i==data.datas.length-1){
 				showdate=showdate+"{name:'"+data.datas[i].name+"',type:'bar',stack: '人数',data:["+data.datas[i].data+"]}"
 			 }else{
 				showdate=showdate+"{name:'"+data.datas[i].name+"',type:'bar',stack: '人数',data:["+data.datas[i].data+"]},"
 			 }
 		  }
 		 showdate=showdate+"]";
 	
 		var finaldata=eval('('+showdate+')');
 		  
		    // 指定图表的配置项和数据
			option = {
			    tooltip : {
			        trigger: 'axis',
			        axisPointer : {            // 坐标轴指示器，坐标轴触发有效
			            type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
			        }
			    },
			    legend: {
			     //   data:['女','男']
			    	  data:data.legends
			    },
			    toolbox: {
			        show : true,
			        orient: 'vertical',
			        x: 'right',
			        y: 'center',
			        feature : {
			            mark : {show: true},
			            dataView : {show: true, readOnly: false},
			            magicType : {show: true, type: ['line', 'bar', 'stack', 'tiled']},
			            restore : {show: true},
			            saveAsImage : {show: true}
			        }
			    },
			    calculable : true,
			    xAxis : [
			        {
			            type : 'category',
			            data : [
			                    '1','2','3','4','5','6','7','8','9','10','11',
			                    '12','13','14','15','16','17','18','19','20','21',
			                    '22','23','24','25','26','27','28','29','30','31'
			                    ]
			        }
			    ],
			    yAxis : [
			        {
			            type : 'value'
			        }
			    ],
			    series : finaldata
			    //	data.retdata
 			     /* [
			        {
			            name:'女',
			            type:'bar',
			            stack: '人数',
			            data:[320, 332, 301, 334, 390, 330, 320]
			        },
			        {
			            name:'男',
			            type:'bar',
			            stack: '人数',
			            data:[62, 82, 91, 84, 109, 110, 120]
			        }
			    ]  */
			};
                    
			
			    // 使用刚指定的配置项和数据显示图表。
			    myChart.setOption(option);
			}
		
		});	
	
}
</script>

</body>
</html>