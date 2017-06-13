package gkyt.controller.api;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSONObject;
import gkyt.model.Jail;
import gkyt.model.Sysuser;
import gkyt.model.User;
import gkyt.service.JailService;
import gkyt.service.UserService;
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
import java.util.Map;

/**
 * Created by lyt38 on 2017/6/7.
 */
public class JailController {
    private static final Logger log = Logger.getLogger(JailController.class);
    @Autowired
    private JailService jailService;
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/addPrison",method = RequestMethod.POST)
    @ResponseBody
    public JSONObject addPrison(HttpServletRequest request, HttpServletResponse response, String prisonName, String zipCode){
        log.info("添加监狱addPrison:prisonName="+prisonName);
        HttpSession session = request.getSession();
        Sysuser u  = (Sysuser)session.getAttribute("s_user");
        if(u==null){
            return ErrorEnums.getResult(ErrorEnums.LOGIN_OVERTIME, null, null);
        }
        if (StringUtils.isEmpty(prisonName) || StringUtils.isEmpty(zipCode)){
            return ErrorEnums.getResult(ErrorEnums.PARAM_ERROR, null, null);
        }
        Integer jailId = jailService.addJail(prisonName, zipCode);
        if (jailId == 0){
            return ErrorEnums.getResult(ErrorEnums.SERVER_ERROR, null, null);
        }
        Integer shId = userService.addUser(zipCode+"_sh", jailId, 1);
        if (shId == 0){
            return ErrorEnums.getResult(ErrorEnums.SERVER_ERROR, null, null);
        }
        Integer spId = userService.addUser(zipCode+"_sp", jailId, 2);
        if (spId == 0){
            return ErrorEnums.getResult(ErrorEnums.SERVER_ERROR, null, null);
        }
        Integer xxId = userService.addUser(zipCode+"_xx", jailId, 3);
        if (xxId == 0){
            return ErrorEnums.getResult(ErrorEnums.SERVER_ERROR, null, null);
        }
        Jail jail = jailService.getJailById(jailId);
        User usersh = userService.getUserById(shId);
        User usersp = userService.getUserById(spId);
        User userxx = userService.getUserById(xxId);
        JSONObject object = new JSONObject();
        object.put("jail", jail);
        object.put("usersh", usersh);
        object.put("usersp", usersp);
        object.put("userxx", userxx);
        return ErrorEnums.getResult(ErrorEnums.SUCCESS, "添加监狱", object);
    }

    @RequestMapping(value = "/getJails", method = RequestMethod.GET)
    @ResponseBody
    public JSONObject getJails(String title, @RequestParam(required = false, defaultValue = "1") int indexPage,
                                   @RequestParam(required = false, defaultValue = "10") int pageSize, HttpServletRequest request) {
        log.info("查询罪犯列表getJails:title=" + title + ";indexPage=" + indexPage + ";PageSize=" + pageSize);
        try {
            List<Jail> jails = jailService.getJails(title, indexPage, pageSize);
            int jailSize = jailService.countJails(title, indexPage, pageSize);
            JSONObject obj = new JSONObject();
            obj.put("jails", jails);
            obj.put("jailSize", jailSize);
            return ErrorEnums.getResult(ErrorEnums.SUCCESS, "查询监狱列表", obj);
        } catch (Exception e) {
            return ErrorEnums.getResult(ErrorEnums.SERVER_ERROR, null, null);
        }
    }
}
