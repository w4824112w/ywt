package gkyt.controller.api;

import gkyt.commons.paginator.domain.Order;
import gkyt.commons.paginator.domain.PageBounds;
import gkyt.commons.paginator.domain.PageResult;
import gkyt.model.Sysuser;
import gkyt.pojo.SysuserDto;
import gkyt.service.SysuserServiceI;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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



@Controller
@RequestMapping("/api/sysuser")
public class SysuserController {
	private static final Logger log = Logger.getLogger(SysuserController.class); 
	
    @Autowired
    private SysuserServiceI sysuserService;
	
    /**
     * 
     * @param account
     * @param roleId
     * @param r
     * @param pno
     * @param psize
     * @param cacheFlag
     * @return
     */
	@RequestMapping(value ="/list",method = { RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public Map<String,Object> list(HttpServletRequest request,HttpServletResponse response,
			Integer page,Integer rows,SysuserDto dto){
    	log.info("调用用户查询接口开始......");
    	Map<String,Object> retData=new HashMap<String,Object>();
		
		HttpSession session = request.getSession();
		Sysuser u = (Sysuser)session.getAttribute("s_user");
		if(u==null){
			retData.put("code", "1");
			retData.put("msg", "查询失败，用户已超时，请退出登录");
			retData.put("data", "");
			return retData;
		} 
		
		if(page==null){
			page = 1;
		}
		if(rows==null){
			rows = 10;
		}
		
		List<Order> listOrder = new ArrayList<Order>();
		listOrder.add(Order.create("created_at", "asc"));
		PageBounds bounds = new PageBounds(page,rows,listOrder);
		
		PageResult<Sysuser> result = sysuserService.findPage(bounds,dto);
		
		retData.put("code", "0");
		retData.put("msg", "查询成功");
		retData.put("page", result.getPaginator().getPage());
		retData.put("rows", result.getPaginator().getLimit());
		retData.put("totalCount", result.getPaginator().getTotalCount());
		retData.put("dto", dto);
		retData.put("data", result.getPageList());
		
		log.info("调用用户查询接口结束......");
    	return retData;
	}	
    
}
