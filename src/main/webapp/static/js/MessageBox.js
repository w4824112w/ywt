function show(id, user, status) {
    if (status == "-1") {
        $("#useridpwd").val(user);
        $("#change h4:first").html("您确定要重置密码？");
    } else {
        $("#userid").val(user);
        if (status == "启用")
            $("#enable h4:first").html("您确定要停用该项？");
        else
            $("#enable h4:first").html("您确定要启用该项？");
    }
    $("div[id='" + id + "']").fadeIn(200);
    $("a:contains(×)").click(function () {
        $("div[id='" + id + "']").fadeOut(200);
    });
    $("input[class='qxbtn']").click(function () {
        //取消
        $("div[id='" + id + "']").fadeOut(200);
    });
};

function showmessage(title, content) {
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
};

