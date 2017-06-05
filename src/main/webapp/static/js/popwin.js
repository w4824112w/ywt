
function openPop(id) {
	document.getElementById(id).style.display="block";
}

function close(id){
	document.getElementById(id).style.display="none";
}

function closePop(id){
	document.getElementById(id).style.display="none";
}


function showPopWin(wintype,msg){
	switch(wintype){
	case 0:
		showLoading(msg);
		hideMsg();
		hideConfirm();
	break;
	
	case 1:
		hideLoading();
		showMsg(msg);
		hideConfirm();
		
	break;
	
	case 2:
		hideLoading();
		hideMsg();
		showConfirm(msg);
		break;
	}
}

function showLoading(msg){
	$("#loadingtip").html(msg);
	openPop('loadingWin');
}

function hideLoading(){
	close('loadingWin');
}

function showMsg(msg){
	$("#msgtip").html(msg);
	openPop('msgWin');
}

function hideMsg(){
	close('msgWin');
}

function showConfirm(msg){
	$("#confirmtip").html(msg);
	openPop('confirmWin');
}

function hideConfirm(){
	close('confirmWin');
}

function hidePopWin(){
	close('confirmWin');
	close('msgWin');
	close('loadingWin');
}

/**
 * 验证号码的合法性
 * @param str
 * @returns {Boolean}
 */
function checkPhone(str){
	var reg1 = /^(13|14|15|17|18)\d{9}$/;
	var reg2 = /^0\d{2,3}\d{7,8}$/;
	if (reg1.test(str)) {
	     return true;
	} else {
		if (reg2.test(str)) { 
			return true;
		}
	}
	return false;
}

function checkDatetime(str){
	var reg1=/^\d{4}-\d{2}-\d{2} \d{2}:\d{2}:\d{2}$/;
	if (reg1.test(str)) {
	     return true;
	}
	return false;
}

function checkDate(str){
	var reg1=/^\d{4}-\d{2}-\d{2}$/;
	if (reg1.test(str)) {
	     return true;
	}
	return false;
}


//封装异步请求
//reqUrl : 请求地址
//params : 请求参数
//m_succ : 请求成功后的回调方法
//m_fail : 请求失败后的回调
//m_session : 会话失效后的回调
function ajaxReq(reqUrl,params,m_succ,m_fail,m_session){
		$.ajax({
			url:reqUrl,
			dataType:'json',
			type:'post',
			data:params,//"ids="+ids+"&status="+s,
			timeout:30000,
			cache:false,
			contentType:'application/x-www-form-urlencoded',
			success:function(jd){
				if(jd.retcode=="0"){
					showPopWin(1,jd.retmsg);
					$("#btnOK").click(function(){
						showPopWin(0,"正在刷新数据，请稍候...");
						//回调方法
						m_succ();
					});						
				}else if(jd.retcode=="1"){
					showPopWin(1,jd.retmsg);
					$("#btnOK").click(function(){
						hidePopWin();
					});
				}else if(jd.retcode=="99"){
					showPopWin(1,jd.retmsg);
					$("#btnOK").click(function(){
						m_session();
					});
				}
			},
			error:function(jd){
				showPopWin(1,"无效的请求，请稍后重试");
				$("#btnOK").click(function(){
					hidePopWin();
				});
			}
		});	
}		
