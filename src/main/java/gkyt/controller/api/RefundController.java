package gkyt.controller.api;

import com.alibaba.fastjson.JSONObject;
import gkyt.model.Drawback;
import gkyt.model.Sysuser;
import gkyt.service.RefundService;
import gkyt.utils.ErrorEnums;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by lyt38 on 2017/6/12.
 */
public class RefundController {
    private static final Logger log = Logger.getLogger(FamilyController.class);
    @Autowired
    private RefundService refundService;

    @RequestMapping(value = "/getDrawbacks", method = RequestMethod.GET)
    @ResponseBody
    public JSONObject getDrawbacks(Integer jailId, String prisonerName, String phone, String name, @RequestParam(required = false, defaultValue = "1") int indexPage,
                                   @RequestParam(required = false, defaultValue = "10") int pageSize, HttpServletRequest request) {
        log.info("查询退款列表getFamilies:jailId=" + jailId + ";prisonName="+ prisonerName+ ";phone="+phone+";name="+name+";indexPage=" + indexPage + ";PageSize=" + pageSize);
        try {
            List<Drawback> drawbacks = refundService.getDrawbacks(jailId, prisonerName, phone, name, indexPage, pageSize);
            int drawbackSize = refundService.countDrawbacks(jailId, prisonerName, phone, name, indexPage, pageSize);
            JSONObject obj = new JSONObject();
            obj.put("drawbacks", drawbacks);
            obj.put("drawbackSize", drawbackSize);
            return ErrorEnums.getResult(ErrorEnums.SUCCESS, "查询退款列表", obj);
        } catch (Exception e) {
            return ErrorEnums.getResult(ErrorEnums.SERVER_ERROR, null, null);
        }
    }

    @RequestMapping(value = "/drawbackVerify", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject drawbackVerify(Integer dId, Integer verifyType, String verifyMsg, HttpServletRequest request, HttpServletResponse response){
        log.info("退款审核drawbackVerify:dId="+dId+";verifyType="+verifyType+";verifyMsg="+verifyMsg);
        Drawback drawback = refundService.getDrawbackById(dId);
        HttpSession session = request.getSession();
        Sysuser u  = (Sysuser)session.getAttribute("s_user");
        if (u.getRoleId() == 4 && drawback.getStatus() == 0){
            int isFlag = refundService.updateDrawback(dId, verifyType);
            if (isFlag == 0 ){
                return ErrorEnums.getResult(ErrorEnums.SERVER_ERROR, null, null);
            }
            int isLogFlag = refundService.addDrawbackLog(dId, verifyType, verifyMsg, u.getId());
            if (isLogFlag == 0){
                return ErrorEnums.getResult(ErrorEnums.SERVER_ERROR, null, null);
            }
            String msg = "";
            if (verifyType == 1){
                msg = "同意";
            }else if (verifyType == 2){
                msg = "拒绝";
            }
            drawback = refundService.getDrawbackById(dId);
            return ErrorEnums.getResult(ErrorEnums.SUCCESS, "财务审核完成，结果："+msg, drawback);
        }
        if (u.getRoleId() == 1 && drawback.getStatus() == 1){
            int isFlag = refundService.updateDrawback(dId, verifyType);
            if (isFlag == 0 ){
                return ErrorEnums.getResult(ErrorEnums.SERVER_ERROR, null, null);
            }
            int isLogFlag = refundService.addDrawbackLog(dId, verifyType, verifyMsg, u.getId());
            if (isLogFlag == 0){
                return ErrorEnums.getResult(ErrorEnums.SERVER_ERROR, null, null);
            }
            String msg = "";
            if (verifyType == 3){
                msg = "同意";
            }else if (verifyType == 4){
                msg = "拒绝";
            }
            drawback = refundService.getDrawbackById(dId);
            return ErrorEnums.getResult(ErrorEnums.SUCCESS, "管理审核完成，结果："+msg, drawback);
        }
        return ErrorEnums.getResult(ErrorEnums.SERVER_ERROR, null, null);
    }
}
