package gkyt.controller.api;

import gkyt.model.Sysuser;
import gkyt.pojo.SysuserDto;
import gkyt.service.SysuserServiceI;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 用户登录
 * @author hk
 *
 */
@Controller
@RequestMapping("/api")
public class LoginController {
	private static final Logger log = Logger.getLogger(LoginController.class); 
	
    @Autowired
    private SysuserServiceI sysuserService;
	
	/**
	 * 登录
	 * @param request
	 * @param response
	 * @return
	 */
    @RequestMapping(value = "/login", method = { RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
    public Map<String,Object> login(HttpServletRequest request, HttpServletResponse response,SysuserDto dto){
    	log.info("调用登录接口开始......");
    	Map<String,Object> retData=new HashMap<String,Object>();
    	
		if(StringUtils.isBlank(dto.getLoginName()) || StringUtils.isBlank(dto.getLoginPwd())){
			retData.put("code", "1");
			retData.put("msg", "请输入账号或密码");
			retData.put("data", "");
			return retData;
		}
		
		Sysuser u = sysuserService.login(dto);
		if(u==null){
			retData.put("code", "1");
			retData.put("msg", "账号不存在，请确认");
			retData.put("data", "");
			return retData;
		}
		if(u.getStatus()==1){
			retData.put("code", "1");
			retData.put("msg", "账号已禁用");
			retData.put("data", "");
			return retData;
		}
		if(u.getIsDelete()==1){
			retData.put("code", "1");
			retData.put("msg", "账号已删除");
			retData.put("data", "");
			return retData;
		}
		
		HttpSession session = request.getSession();
		session.setAttribute("s_user",u);	
		
		retData.put("code", "0");
		retData.put("msg", "登录成功");
		retData.put("dto", dto);
		retData.put("data", u);
		
		log.info("调用登录接口结束......");
    	return retData;
    }

    /**
     * 退出登录
     * @param request
     * @param response
     * @return
     */
	@RequestMapping(value = "/logout",method = { RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public Map<String,Object> logout(HttpServletRequest request, HttpServletResponse response){
    	Map<String,Object> retData=new HashMap<String,Object>();
    	
		log.info("调用退出登录接口开始......");
		HttpSession session = request.getSession();
		Sysuser u  = (Sysuser)session.getAttribute("s_user");
		if(u!=null){
			session.removeAttribute("s_user");		
			log.info(u.getLoginName()+"退出了系统");
			retData.put("msg", u.getLoginName()+"退出了系统");
		}else{
			log.info("退出了系统");
			retData.put("msg", "退出了系统");
		}
		
		retData.put("code", "0");
		retData.put("data", "");
		
		log.info("调用退出登录接口结束......");
    	return retData;
	}
}
