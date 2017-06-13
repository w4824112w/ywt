package gkyt.controller.api;

import com.alibaba.fastjson.JSONObject;
import gkyt.model.Family;
import gkyt.service.FamilyService;
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
 * Created by lyt38 on 2017/6/9.
 */
public class FamilyController {
    private static final Logger log = Logger.getLogger(FamilyController.class);
    @Autowired
    private FamilyService familyService;

//    @RequestMapping(value = "/getFamilies", method = RequestMethod.GET)
//    @ResponseBody
//    public JSONObject getFamilies(String title, @RequestParam(required = false, defaultValue = "1") int indexPage,
//                               @RequestParam(required = false, defaultValue = "10") int pageSize, HttpServletRequest request) {
//        log.info("查询家属列表getFamilies·:title=" + title + ";indexPage=" + indexPage + ";PageSize=" + pageSize);
//        try {
//            List<Family> families = familyService.getFamilies(title, indexPage, pageSize);
//            int familySize = familyService.countFamilies(title, indexPage, pageSize);
//            JSONObject obj = new JSONObject();
//            obj.put("families", families);
//            obj.put("familySize", familySize);
//            return ErrorEnums.getResult(ErrorEnums.SUCCESS, "查询家属列表", obj);
//        } catch (Exception e) {
//            return ErrorEnums.getResult(ErrorEnums.SERVER_ERROR, null, null);
//        }
//    }
}
