<%@ page language="java"  pageEncoding="UTF-8"%>
<%@ include file="common/taglib.jsp"%>

<!DOCTYPE html >
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<link href="${ctx }/static/css/login.css" rel="stylesheet" type="text/css">
<link href="${ctx }/static/css/style.css" rel="stylesheet" type="text/css">
<link href="${ctx }/static/css/font-awesome.min.css" rel="stylesheet" type="text/css">
<link href="${ctx }/static/css/font-awesome.css" rel="stylesheet" type="text/css">

<title>登录</title>
</head>
<body>
 <div style="width:100%;">
  <img src="${ctx }/static/images/login_top.jpg" class="topbg">

</div>
<div style="width:100%;"><img src="${ctx }/static/images/top_line.jpg" style="width:100%; height:30px; display:block;"></div>
<div class="full">
  <form action="${ctx }/check.do" method="post" id="form">
  <div class="remind">
    <span class="span1"><input type="text" name="uname" placeholder="请输入用户名" class=" inpuser"></span>
    <span class="span2"><input type="password" name="pwd" placeholder="请输入6-12位密码" class=" inpword"></span>
   <%--  <span class="span3"><input type="text" name="validcode" placeholder="请输入验证码"  class="inpa" onclick="JavaScript:this.value=''" />
     <a href="javascript:void(0)" onclick="refreshCode()" title="点击图片换一张"><img src="${ctx}/codeimg" id="validCodeImg" style="width:75px; height:38px; float:right; padding:0; cursor:pointer;"></a>
    </span> --%>
    <span class="span4"><input type="submit"  class="BntA" value="登  录"/></span>
    <span class="span5"><c:if test="${not empty msg }">${msg }</c:if></span>
    <span class="span6">V0.1</span>
      <!-- <img src="static/images/tit.png" class="logo"> -->
  </div>
    </form>
</div>

<%-- <%@ include file="/prison/popwin.jsp" %> --%>

<script type="text/javascript">

	var login=0;
	function checkLogin(bthObj){
		if(login==0){
			login=1;
		}
		if(login==1){
			var form = document.getElementById("form");
			var error = document.getElementById("msgtip");
			if(form.username.value==""){
				error.innerHTML="请输入账号";	
				login=0;
				return false;
			}
			if(form.pwd.value==""){
				error.innerHTML="请输入密码";	
				login=0;
				return false;					
			}
			
			error.innerHTML="正在登录中...";	
			form.submit();	
		}
	}
	
</script>
</body>
</html>