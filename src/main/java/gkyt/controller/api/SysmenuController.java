package gkyt.controller.api;

import gkyt.commons.paginator.domain.Order;
import gkyt.commons.paginator.domain.PageBounds;
import gkyt.commons.paginator.domain.PageResult;
import gkyt.model.Sysmenu;
import gkyt.model.Sysuser;
import gkyt.pojo.SysmenuDto;
import gkyt.service.SysmenuServiceI;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;



/**
 * 系统菜单管理
 * @author hk
 *
 */
@Controller
@RequestMapping("/api/sysmenu")
public class SysmenuController {
	private static final Logger log = Logger.getLogger(SysmenuController.class); 
	
    @Autowired
    private SysmenuServiceI sysmenuService;
	
    /**
     * 分页查询菜单
     * @param request
     * @param response
     * @param page
     * @param rows
     * @param dto
     * @return
     */
	@RequestMapping(value ="/page",method = { RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public Map<String,Object> page(HttpServletRequest request,HttpServletResponse response,
			Integer page,Integer rows,SysmenuDto dto){
    	Map<String,Object> retData=new HashMap<String,Object>();
		
		HttpSession session = request.getSession();
		Sysuser u = (Sysuser)session.getAttribute("s_user");
		if(u==null){
			retData.put("code", "2");
			retData.put("msg", "用户已超时，请退出登录");
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
		
		PageResult<Sysmenu> result = sysmenuService.findPage(bounds,dto);
		
		retData.put("code", "0");
		retData.put("msg", "查询成功");
		retData.put("page", result.getPaginator().getPage());
		retData.put("rows", result.getPaginator().getLimit());
		retData.put("totalCount", result.getPaginator().getTotalCount());
		retData.put("dto", dto);
		retData.put("data", result.getPageList());
		
    	return retData;
	}	
    
	/**
	 * 一级菜单菜单列表
	 * @param request
	 * @param response
	 * @param page
	 * @param rows
	 * @param dto
	 * @return
	 */
	@RequestMapping(value ="/oneLevelList",method = { RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public Map<String,Object> oneLevelList(HttpServletRequest request,HttpServletResponse response,
			Integer page,Integer rows,SysmenuDto dto){
	   	Map<String,Object> retData=new HashMap<String,Object>();
		
		HttpSession session = request.getSession();
		Sysuser u = (Sysuser)session.getAttribute("s_user");
		if(u==null){
			retData.put("code", "2");
			retData.put("msg", "用户已超时，请退出登录");
			retData.put("data", "");
			return retData;
		} 
		
		List<Sysmenu> result = sysmenuService.getOneLevelMenu();
		
		retData.put("code", "0");
		retData.put("msg", "查询成功");
		retData.put("dto", dto);
		retData.put("data", result);
		
    	return retData;
	}
	
	
	/**
	 * 菜单列表
	 * @param request
	 * @param response
	 * @param page
	 * @param rows
	 * @param dto
	 * @return
	 */
	@RequestMapping(value ="/list",method = { RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public Map<String,Object> list(HttpServletRequest request,HttpServletResponse response,
			Integer page,Integer rows,SysmenuDto dto){
	   	Map<String,Object> retData=new HashMap<String,Object>();
		
		HttpSession session = request.getSession();
		Sysuser u = (Sysuser)session.getAttribute("s_user");
		if(u==null){
			retData.put("code", "2");
			retData.put("msg", "用户已超时，请退出登录");
			retData.put("data", "");
			return retData;
		} 
		
		List<Sysmenu> result = sysmenuService.getAll();
		
		retData.put("code", "0");
		retData.put("msg", "查询成功");
		retData.put("data", result);
		
    	return retData;
	}
	
	/**
	 * 编辑菜单
	 * @param request
	 * @param response
	 * @param id
	 * @return
	 */
	@RequestMapping(value ="/edit",method = { RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public Map<String,Object> edit(HttpServletRequest request,HttpServletResponse response,String id){
		Map<String,Object> retData=new HashMap<String,Object>();
		
		HttpSession session = request.getSession();
		Sysuser u = (Sysuser)session.getAttribute("s_user");
		if(u==null){
			retData.put("code", "2");
			retData.put("msg", "用户已超时，请退出登录");
			retData.put("data", "");
			return retData;
		}
		
		Sysmenu role=sysmenuService.getById(id);
		retData.put("code", "0");
		retData.put("msg", "获取编辑对象成功");
		retData.put("data", role);

		return retData;
	}	
	
	/**
	 * 保存菜单
	 * @param o
	 * @param r
	 * @return
	 */
	@RequestMapping(value ="/save",method = { RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public Map<String,Object> save(HttpServletRequest request,HttpServletResponse response,Sysmenu role){		
		Map<String,Object> retData=new HashMap<String,Object>();
		
		HttpSession session = request.getSession();
		Sysuser u = (Sysuser)session.getAttribute("s_user");
		if(u==null){
			retData.put("code", "2");
			retData.put("msg", "用户已超时，请退出登录");
			retData.put("data", "");
			return retData;
		}
		try {
			retData=sysmenuService.saveOrUpdate(role);
			return retData;
		} catch (Exception e) {
			e.printStackTrace();
			retData.put("code", "1");
			retData.put("msg", "系统出现异常");
			retData.put("data", "");
			return retData;
		}

			
	}
	
	/**
	 * 删除菜单
	 * @param request
	 * @param response
	 * @param id
	 * @return
	 */
	@RequestMapping(value ="/delete",method = { RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public Map<String,Object> delete(HttpServletRequest request,HttpServletResponse response,String id){
		Map<String,Object> retData=new HashMap<String,Object>();
		
		HttpSession session = request.getSession();
		Sysuser u = (Sysuser)session.getAttribute("s_user");
		if(u==null){
			retData.put("code", "2");
			retData.put("msg", "用户已超时，请退出登录");
			retData.put("data", "");
			return retData;
		}
		
		try {
			int result=sysmenuService.delete(id);
			if(result>0){
				retData.put("code", "0");
				retData.put("msg", "删除菜单成功");
			}else{
				retData.put("code", "1");
				retData.put("msg", "删除菜单失败");
			}
			return retData;
		} catch (Exception e) {
			e.printStackTrace();
			retData.put("code", "1");
			retData.put("msg", "系统出现异常");
			return retData;
		}
		
	}
	
	/**
	 * 批量删除菜单
	 * @param request
	 * @param response
	 * @param id
	 * @return
	 */
	@RequestMapping(value ="/batchDelete",method = { RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public Map<String,Object> batchDelete(String[] ids,HttpServletRequest request,HttpServletResponse response){
		Map<String,Object> retData=new HashMap<String,Object>();
		
		HttpSession session = request.getSession();
		Sysuser u = (Sysuser)session.getAttribute("s_user");
		if(u==null){
			retData.put("code", "2");
			retData.put("msg", "用户已超时，请退出登录");
			return retData;
		}
		
		try {
			if(sysmenuService.delBatch(ids)){
				retData.put("code", "0");
				retData.put("msg", "批量删除菜单成功");
				return retData;
			}else{
				retData.put("code", "1");
				retData.put("msg", "批量删除菜单失败");
				return retData;
			}
		} catch (Exception e) {
			e.printStackTrace();
			retData.put("code", "1");
			retData.put("msg", "系统出现异常");
			return retData;
		}
		
	}
	
}
