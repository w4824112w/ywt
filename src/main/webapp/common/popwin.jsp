<%@ page language="java"  pageEncoding="UTF-8"%>
<%-- <%@ include file="/common/taglib.jsp" %> --%>

<!-- 加载弹窗 -->
<div class="Popup" style = "display:none;z-index:19999" id = "loadingWin">
    <div class="PopupC">
    <div class="PopupCA" style="padding:50px; font-size:60px;">
       <i class="fa fa-spinner fa-spin"></i><br>
       	<span style="line-height:30px; font-size:16px; display:block;" id="loadingtip">加载中...</span>   
    </div>
  </div>
</div>


<!-- 消息弹窗 -->
<div class="Popup" style="display:none;z-index:19999" id = "msgWin" >
      <div class="PopupC">
    <h3>信息提示<a onclick="javascript:hidePopWin()">×</a></h3>
    <div class="PopupCA" id="msgtip"></div>
   	<div class="btnall" style="width:120px;">
   		<!-- <a class="PopupCA_foot1" id="btnOK">确定</a> -->
   		<input  class="scbtn" style=" width:120px; margin-right:10px;" id="btnOK" value="确定" type="button"/>
   	</div>
  </div>
</div>

<!-- 确认弹窗 -->
<div class="Popup" style="display:none;" id = "confirmWin" >
      <div class="PopupC">
    <h3>&nbsp;&nbsp;信息提示<a onclick="hidePopWin()">×</a></h3>
    <div class="PopupCA" ><span style="text-align:center; font-weight:bold;" id="confirmtip"></span></div>
   	<div class="btnall" style=" width:300px;">
   		<input  class="scbtn" style=" width:120px; margin-right:10px;" id="confirmOK" value="确认" type="button"/>
   		<input class="qxbtn" onclick="javascript:hidePopWin()" style=" width:120px;"  value="取消" type="button"/>
   	</div>
  </div>
</div>

<!-- function showmessage(title, content) {
    $('body').prepend('<div class="Popup" id="tanchuan" style="display:none;">' +
        '<div class="PopupC" id="tanchuan">' +
        '<h3>&nbsp;&nbsp;' + title + '<a href="javascript:;" class="close">×</a></h3>' +
        '<div class="PopupCA"> <span style="text-align:center; font-weight:bold;">' + content + '</span> </div>' +
        '<div class="btnall" style=" width:250px;">' +
        '<input class="scbtn" name="" value="确认" style=" width:120px; margin-right:10px;" type="button">' +
        '<input class="qxbtn" name="" value="取消" style=" width:120px;" type="button">' +
        '</div>' +
        '</div>' +
        '</div>');
    $('#tanchuan').show();
    $('.close,.scbtn,.qxbtn').live('click', function () {
        $('#tanchuan').hide();
        $('.PopupC').remove();
    });
}; -->


