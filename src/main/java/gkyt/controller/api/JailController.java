package gkyt.controller.api;

import com.alibaba.druid.util.StringUtils;
import gkyt.model.Sysuser;
import gkyt.service.JailService;
import gkyt.utils.ErrorEnums;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by lyt38 on 2017/6/7.
 */
public class JailController {
    private static final Logger log = Logger.getLogger(JailController.class);
    @Autowired
    private JailService jailService;

    @RequestMapping(value = "/addPrison",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> logout(HttpServletRequest request, HttpServletResponse response, String prisonName){
        log.info("添加监狱addPrison:prisonName="+prisonName);
        Map<String,Object> retData=new HashMap<String,Object>();
        HttpSession session = request.getSession();
        Sysuser u  = (Sysuser)session.getAttribute("s_user");
        if(u==null){
            return ErrorEnums.getResult(ErrorEnums.LOGIN_OVERTIME, null, null);
        }
        if (StringUtils.isEmpty(prisonName)){
            return ErrorEnums.getResult(ErrorEnums.PARAM_ERROR, null, null);
        }

        Integer jailId = jailService.addJail(prisonName);
        if (jailId == 0){
            return ErrorEnums.getResult(ErrorEnums.SERVER_ERROR, null, null);
        }
        
        return ErrorEnums.getResult(ErrorEnums.SUCCESS, "添加监狱名称", prisonName);
    }
}
