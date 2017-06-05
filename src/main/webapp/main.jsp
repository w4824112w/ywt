<%@ page language="java"  pageEncoding="UTF-8"%>
<%@ include file="common/taglib.jsp"%>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<link href="${ctx }/static/css/style.css" rel="stylesheet" type="text/css" /><!--后台全部样式-->
<link href="${ctx }/static/css/font-awesome.min.css" rel="stylesheet" type="text/css" /><!--字符图标样式-->


<script type="text/javascript" src = "${ctx }/static/js/jquery-2.1.3.min.js"></script>
<script type="text/javascript" src = "${ctx }/static/js/jquery.form.js"></script>
<script type="text/javascript" src="${ctx }/static/js/jquery.js"></script><!--jQuery库-->
<script type="text/javascript" src = "${ctx }/static/js/popwin.js?v3"></script>
<script type="text/javascript" src="${ctx }/static/js/ajaxfileupload.js"></script><!--jQuery库-->
<!--时间控件-->
<script src="static/laydate/laydate.js"></script>


<title>Insert title here</title>
</head>
<body>	
<!-- 	<h1>主页</h1>
	<input type="button" value="导出数据"> -->
  <div class="formbody">              <!--内容区域-->
  	
	  <div  class="tabson">
	    <div class="TableA_Search">     <!--搜索栏区域-->
	    <form action="${ctx }/prison/export.do" id="exportForm" method="post">
	      <ul class="seachform">
	       <li><input placeholder="订购起始日期" class="dfinput input1" name="startTime"  onclick="laydate()" title="订购起始日期" ></li>
	       
	       <li><input placeholder="订购截止日期" class="dfinput input1" name="endTime" onclick="laydate()" title="订购截止日期" ></li>
	       
	       <li><input type="button" class="scbtn" value="导出" onclick="toExport()" ><!-- <a href="java" ><input name="" type="button" class="scbtn" value="导出"/></a> --> </li>
	     </ul>
	     </form>
	   </div>
	   
	   <div  class="tabson">
	    <div class="TableA_Search">     <!--搜索栏区域-->
	    <form action="${ctx }/prison/uploadPrisonerInfo.do" id="ImportForm" method="post" enctype="multipart/form-data">
	      <ul class="seachform">
	       	<input type="file" id="fileName" name="fileName"  /><span id="msg"></span><br><br>
	        <li><input type="button" onclick="toImport()" class="scbtn" value="导入囚犯数据"  ></li> 
	       
	     </ul>
	     </form>
	   </div>
	   
		   导入XML数据<br>
	<form id="up" action="upload" method="post" enctype="multipart/form-data">
	    监狱ID<input type="text" name="jail"/><br>
	    <input type="file"  name="fileXmlName"/><br><br>
	    <input type="submit" value="导入数据"/>
	</form>

	<br>
	---------------------------------------------------------<br>
	导出到XML文件<br>
	<form id="down" action="downLoad" method="post">
	    监狱ID<input type="text" name="jail"/><br>
	    <input type="submit" value="导出数据"/>
	</form>
	   
	   
    <!--搜索栏区域结束-->
    </div>
    
   <%--  <div>
    	<span class="span5"><c:if test="${not empty msg }">${msg }</c:if></span>
  	</div> --%>
  
 </div>   
 
 <%@ include file="/common/popwin.jsp" %>
 
<script type="text/javascript">
	
 	function toExport(){
		var p = $("#exportForm").serialize();
		window.location.href="${ctx }/prison/export.do?"+p;
	} 
 	
 	function toImport_bak(){
		var p = $("#ImportForm").serialize();
		window.location.href="${ctx }/prison/upload.do?"+p;
	} 
	
/* 	function downfile(path){
		window.location.href="${ctx }/admin/orders/downFiles.do?path="+path;
	} */
	
 	function toImport(){
		showPopWin(0,"正在处理，请稍候...");
	        $.ajaxFileUpload({
	            url:'${ctx }/prison/uploadPrisonerInfo.do?fileId=fileName&width=225&height=146',
	            secureuri:false,
	            fileElementId:'fileName',//文件选择框的id属性（必须）
	            dataType: 'json',
	            success: function (data, status){   
	            	showPopWin(1,data.msg); 
        			$("#btnOK").click(function(){
        				hidePopWin();
        			});
	            	$("#msg").html(data.msg);
	            },
	            error: function (data, status, e){
	            	showPopWin(1,"上传失败"); 
        			$("#btnOK").click(function(){
        				hidePopWin();
        			});	
	            	$("#msg").html("上传失败");
	            }
	     });
	} 
	
/* 	$(function(){
		 $("#fileName").live("change", function () {
	            $.ajaxFileUpload({
	                        url:'${ctx }/upload/uploadfile.do?fileId=fileName&width=225&height=146',
	                        secureuri:false,
	                        fileElementId:'fileName',//文件选择框的id属性（必须）
	                        dataType: 'json',
	                        success: function (data, status){    
	                        	
	                        },
	                        error: function (data, status, e){
	                        	
	                        }
	                 });
	        });
		 
	}) */
</script>    
</body>
</html>