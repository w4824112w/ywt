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
<script type="text/javascript">

$(function(){
	zhexiantu();
	zhuzhuangtu();
}); 

function zhexiantu(){
	$.ajax({
		type : "POST",
		url : "yeardata.do",
	//	contentType: "application/json",
	//	dataType: "json", 
		success : function(jsondata) {
		    // 基于准备好的dom，初始化echarts实例
		    var myChart = echarts.init(document.getElementById('main'));

 			//   var jsondata='{"nodes":[{"name":"Total"},{"name":"Environment"},{"name":"Land use"},{"name":"Cocoa butter (Organic)"},{"name":"Cocoa mass (Organic)"},{"name":"Hazelnuts (Organic)"},{"name":"Cane sugar (Organic)"},{"name":"Vegetables (Organic)"},{"name":"Climate change"},{"name":"Harmful substances"},{"name":"Water use"},{"name":"Resource depletion"},{"name":"Refrigeration"},{"name":"Packaging"},{"name":"Human rights"},{"name":"Child labour"},{"name":"Coconut oil (Organic)"},{"name":"Forced labour"},{"name":"Health safety"},{"name":"Access to water"},{"name":"Freedom of association"},{"name":"Access to land"},{"name":"Sufficient wage"},{"name":"Equal rights migrants"},{"name":"Discrimination"},{"name":"Working hours"}],"links":[{"source":"Total","target":"Environment","value":0.342284047256003},{"source":"Environment","target":"Land use","value":0.32322870366987},{"source":"Land use","target":"Cocoa butter (Organic)","value":0.177682517071359},{"source":"Land use","target":"Cocoa mass (Organic)","value":0.137241325342711},{"source":"Land use","target":"Hazelnuts (Organic)","value":0.00433076373512774},{"source":"Land use","target":"Cane sugar (Organic)","value":0.00296956039863467},{"source":"Land use","target":"Vegetables (Organic)","value":0.00100453712203756},{"source":"Environment","target":"Climate change","value":0.0112886157414413},{"source":"Climate change","target":"Cocoa butter (Organic)","value":0.00676852971933996},{"source":"Climate change","target":"Cocoa mass (Organic)","value":0.00394686874786743},{"source":"Climate change","target":"Cane sugar (Organic)","value":0.000315972058711838},{"source":"Climate change","target":"Hazelnuts (Organic)","value":0.000218969462265292},{"source":"Climate change","target":"Vegetables (Organic)","value":0.0000382757532567656},{"source":"Environment","target":"Harmful substances","value":0.00604275542495656},{"source":"Harmful substances","target":"Cocoa mass (Organic)","value":0.0055125989240741},{"source":"Harmful substances","target":"Cocoa butter (Organic)","value":0.000330017607892127},{"source":"Harmful substances","target":"Cane sugar (Organic)","value":0.000200138892990337},{"source":"Harmful substances","target":"Hazelnuts (Organic)","value":0},{"source":"Harmful substances","target":"Vegetables (Organic)","value":0},{"source":"Environment","target":"Water use","value":0.00148345269044703},{"source":"Water use","target":"Cocoa butter (Organic)","value":0.00135309891304186},{"source":"Water use","target":"Cocoa mass (Organic)","value":0.000105714137908639},{"source":"Water use","target":"Hazelnuts (Organic)","value":0.0000133452642581887},{"source":"Water use","target":"Cane sugar (Organic)","value":0.00000878074837009238},{"source":"Water use","target":"Vegetables (Organic)","value":0.0000025136268682477},{"source":"Environment","target":"Resource depletion","value":0.000240519729288764},{"source":"Resource depletion","target":"Cane sugar (Organic)","value":0.000226237279345084},{"source":"Resource depletion","target":"Vegetables (Organic)","value":0.0000142824499436793},{"source":"Resource depletion","target":"Hazelnuts (Organic)","value":0},{"source":"Resource depletion","target":"Cocoa mass (Organic)","value":0},{"source":"Resource depletion","target":"Cocoa butter (Organic)","value":0},{"source":"Environment","target":"Refrigeration","value":0},{"source":"Environment","target":"Packaging","value":0},{"source":"Total","target":"Human rights","value":0.307574096993239},{"source":"Human rights","target":"Child labour","value":0.0410641202645833},{"source":"Child labour","target":"Hazelnuts (Organic)","value":0.0105339381639722},{"source":"Child labour","target":"Cocoa mass (Organic)","value":0.0105},{"source":"Child labour","target":"Cocoa butter (Organic)","value":0.0087294420777},{"source":"Child labour","target":"Coconut oil (Organic)","value":0.00474399974233333},{"source":"Child labour","target":"Cane sugar (Organic)","value":0.00388226450884445},{"source":"Child labour","target":"Vegetables (Organic)","value":0.00267447577173333},{"source":"Human rights","target":"Forced labour","value":0.0365458590642445},{"source":"Forced labour","target":"Hazelnuts (Organic)","value":0.0114913076376389},{"source":"Forced labour","target":"Cocoa butter (Organic)","value":0.0081134807347},{"source":"Forced labour","target":"Cocoa mass (Organic)","value":0.00765230236575},{"source":"Forced labour","target":"Cane sugar (Organic)","value":0.004},{"source":"Forced labour","target":"Vegetables (Organic)","value":0.00296668823626667},{"source":"Forced labour","target":"Coconut oil (Organic)","value":0.00232208008988889},{"source":"Human rights","target":"Health safety","value":0.0345435327843611},{"source":"Health safety","target":"Hazelnuts (Organic)","value":0.0121419536385},{"source":"Health safety","target":"Cocoa mass (Organic)","value":0.00766772850678333},{"source":"Health safety","target":"Cocoa butter (Organic)","value":0.0056245892061},{"source":"Health safety","target":"Coconut oil (Organic)","value":0.00361616847688889},{"source":"Health safety","target":"Cane sugar (Organic)","value":0.00277374682533333},{"source":"Health safety","target":"Vegetables (Organic)","value":0.00271934613075556},{"source":"Human rights","target":"Access to water","value":0.0340206659360667},{"source":"Access to water","target":"Cocoa mass (Organic)","value":0.0105},{"source":"Access to water","target":"Cocoa butter (Organic)","value":0.0089274160792},{"source":"Access to water","target":"Hazelnuts (Organic)","value":0.0054148022845},{"source":"Access to water","target":"Cane sugar (Organic)","value":0.00333938149786667},{"source":"Access to water","target":"Vegetables (Organic)","value":0.00314663377488889},{"source":"Access to water","target":"Coconut oil (Organic)","value":0.00269243229961111},{"source":"Human rights","target":"Freedom of association","value":0.0320571523941667},{"source":"Freedom of association","target":"Hazelnuts (Organic)","value":0.0132312483463611},{"source":"Freedom of association","target":"Cocoa butter (Organic)","value":0.0077695200707},{"source":"Freedom of association","target":"Cocoa mass (Organic)","value":0.00510606573995},{"source":"Freedom of association","target":"Vegetables (Organic)","value":0.00354321156324444},{"source":"Freedom of association","target":"Cane sugar (Organic)","value":0.00240710667391111},{"source":"Freedom of association","target":"Coconut oil (Organic)","value":0},{"source":"Human rights","target":"Access to land","value":0.0315022209894056},{"source":"Access to land","target":"Hazelnuts (Organic)","value":0.00964970063322223},{"source":"Access to land","target":"Cocoa mass (Organic)","value":0.00938530207965},{"source":"Access to land","target":"Cocoa butter (Organic)","value":0.0060110791848},{"source":"Access to land","target":"Cane sugar (Organic)","value":0.00380818314608889},{"source":"Access to land","target":"Vegetables (Organic)","value":0.00264795594564445},{"source":"Access to land","target":"Coconut oil (Organic)","value":0},{"source":"Human rights","target":"Sufficient wage","value":0.0287776757227333},{"source":"Sufficient wage","target":"Cocoa mass (Organic)","value":0.00883512456493333},{"source":"Sufficient wage","target":"Cocoa butter (Organic)","value":0.0078343367268},{"source":"Sufficient wage","target":"Coconut oil (Organic)","value":0.00347879026511111},{"source":"Sufficient wage","target":"Hazelnuts (Organic)","value":0.00316254211388889},{"source":"Sufficient wage","target":"Vegetables (Organic)","value":0.00281013722808889},{"source":"Sufficient wage","target":"Cane sugar (Organic)","value":0.00265674482391111},{"source":"Human rights","target":"Equal rights migrants","value":0.0271146645119444},{"source":"Equal rights migrants","target":"Cocoa butter (Organic)","value":0.0071042315061},{"source":"Equal rights migrants","target":"Cocoa mass (Organic)","value":0.00636673210005},{"source":"Equal rights migrants","target":"Hazelnuts (Organic)","value":0.00601459775836111},{"source":"Equal rights migrants","target":"Coconut oil (Organic)","value":0.00429185583138889},{"source":"Equal rights migrants","target":"Cane sugar (Organic)","value":0.00182647471915556},{"source":"Equal rights migrants","target":"Vegetables (Organic)","value":0.00151077259688889},{"source":"Human rights","target":"Discrimination","value":0.0211217763359833},{"source":"Discrimination","target":"Cocoa mass (Organic)","value":0.00609671700306667},{"source":"Discrimination","target":"Cocoa butter (Organic)","value":0.0047738806325},{"source":"Discrimination","target":"Coconut oil (Organic)","value":0.00368119084494444},{"source":"Discrimination","target":"Vegetables (Organic)","value":0.00286009813604444},{"source":"Discrimination","target":"Cane sugar (Organic)","value":0.00283706180951111},{"source":"Discrimination","target":"Hazelnuts (Organic)","value":0.000872827909916666},{"source":"Human rights","target":"Working hours","value":0.02082642898975},{"source":"Working hours","target":"Hazelnuts (Organic)","value":0.0107216773848333},{"source":"Working hours","target":"Coconut oil (Organic)","value":0.00359009052944444},{"source":"Working hours","target":"Vegetables (Organic)","value":0.00212300379075556},{"source":"Working hours","target":"Cocoa butter (Organic)","value":0.0018518584356},{"source":"Working hours","target":"Cocoa mass (Organic)","value":0.00158227069058333},{"source":"Working hours","target":"Cane sugar (Organic)","value":0.000957528158533333}]}';
    
 		   var data=eval('('+jsondata+')');
 		   
 		   var showdate="[{name:'最大接待人数',type:'line',stack: '总量',data:["+data.maxStr+"]},"+
 		           		"{name:'最小接待人数',type:'line',stack: '总量',data:["+data.minStr+"]},"+
		       			"{name:'平均接待人数',type:'line',stack: '总量',data:["+data.avgStr+"]}]";
 		   
 		  var finaldata=eval('('+showdate+')');
 		//  var showdata=JSON.stringify(data.datas).replace(/\"/g,"'");
 		// showdata=showdata.replace("'stack'","stack");
 		  
 		
		    // 指定图表的配置项和数据
	     option = {
	    		    tooltip : {
	    		        trigger: 'axis'
	    		    },
	    		    legend: {
	    		        data:['最大接待人数','最小接待人数','平均接待人数']
	    		    },
	    		    toolbox: {
	    		        show : true,
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
	    		            boundaryGap : false,
	    		            data : ['','1','2','3','4','5','6','7','8','9','10','11','12']
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
	    		            name:'最大接待人数',
	    		            type:'line',
	    		            stack: '总量',
	    		            data:[,97,99,100,100,100,,,,,,,,]
	    		        },
	    		        {
	    		            name:'最小接待人数',
	    		            type:'line',
	    		            stack: '总量',
	    		            data:[,1,1,100,100,100,,,,,,,,]
	    		        },
	    		        {
	    		            name:'平均接待人数',
	    		            type:'line',
	    		            stack: '总量',
	    		            data:[,33,50,100,100,100,,,,,,,,]
	    		        }
	    		    ] */
	    		};
			
			    // 使用刚指定的配置项和数据显示图表。
			    myChart.setOption(option);
			}
		});	

}

function zhuzhuangtu(){
	$.ajax({
		type : "POST",
		url : "yeartotaldata.do",
	//	contentType: "application/json",
	//	dataType: "json", 
		success : function(jsondata) {
		    // 基于准备好的dom，初始化echarts实例
		    var myChart = echarts.init(document.getElementById('sub'));
    
 		   var data=eval('('+jsondata+')');
 		   
 		   var showdate="[{name:'总量',type:'bar',data:["+data.dataStr+"]}]";
 		   
 		  var finaldata=eval('('+showdate+')');
 		  
		    // 指定图表的配置项和数据
        var option = {
            tooltip: {
                show: true
            },
            legend: {
                data:['景区客流接待总量（年）']
            },
            xAxis : [
                {
                    type : 'category',
                    data : ["","1","2","3","4","5","6","7","8","9","10","11","12"]
                }
            ],
            yAxis : [
                {
                    type : 'value'
                }
            ],
            series : finaldata
           /*  [
                {
                    "name":"总量",
                    "type":"bar",
                    "data":[5, 20, 40, 10, 10, 20, ,14,56]
                }
            ] */
        };
                    
			
			    // 使用刚指定的配置项和数据显示图表。
			    myChart.setOption(option);
			}
		});	

}
</script>

</body>
</html>