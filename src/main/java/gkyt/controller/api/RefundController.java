package gkyt.controller.api;

import com.alibaba.fastjson.JSONObject;
import gkyt.model.Drawback;
import gkyt.service.RefundService;
import gkyt.utils.ErrorEnums;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
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
        log.info("查询家属列表getFamilies:jailId=" + jailId + ";prisonName="+ prisonerName+ ";phone="+phone+";name="+name+";indexPage=" + indexPage + ";PageSize=" + pageSize);
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
}
