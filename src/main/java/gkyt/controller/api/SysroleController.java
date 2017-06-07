package gkyt.controller.api;

import gkyt.commons.paginator.domain.Order;
import gkyt.commons.paginator.domain.PageBounds;
import gkyt.commons.paginator.domain.PageResult;
import gkyt.model.Sysmenu;
import gkyt.model.Sysrole;
import gkyt.model.Sysuser;
import gkyt.pojo.SysroleDto;
import gkyt.service.SysmenuServiceI;
import gkyt.service.SysroleServiceI;

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
 * 系统角色管理
 * @author hk
 *
 */
@Controller
@RequestMapping("/api/sysrole")
public class SysroleController {
	private static final Logger log = Logger.getLogger(SysroleController.class); 
	
    @Autowired
    private SysroleServiceI sysroleService;
    
    @Autowired
    private SysmenuServiceI sysmenuService;
	
    /**
     * 分页查询角色
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
			Integer page,Integer rows,SysroleDto dto){
    	Map<String,Object> retData=new HashMap<String,Object>();
		
		HttpSession session = request.getSession();
		Sysuser u = (Sysuser)session.getAttribute("s_user");
		if(u==null){
			retData.put("code", "1");
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
		
		PageResult<Sysrole> result = sysroleService.findPage(bounds,dto);
		
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
	 * 角色列表
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
			Integer page,Integer rows,SysroleDto dto){
	   	Map<String,Object> retData=new HashMap<String,Object>();
		
		HttpSession session = request.getSession();
		Sysuser u = (Sysuser)session.getAttribute("s_user");
		if(u==null){
			retData.put("code", "1");
			retData.put("msg", "用户已超时，请退出登录");
			retData.put("data", "");
			return retData;
		} 
		
		List<Sysrole> result = sysroleService.getAll(dto);
		
		retData.put("code", "0");
		retData.put("msg", "查询成功");
		retData.put("dto", dto);
		retData.put("data", result);
		
    	return retData;
	}
	
	/**
	 * 编辑角色
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
			retData.put("code", "1");
			retData.put("msg", "用户已超时，请退出登录");
			retData.put("data", "");
			return retData;
		}
		
		Sysrole role=sysroleService.getById(id);
		retData.put("code", "0");
		retData.put("msg", "获取编辑对象成功");
		retData.put("data", role);

		return retData;
	}	
	
	/**
	 * 角色权限编辑
	 * @param request
	 * @param response
	 * @param roleId
	 * @return
	 */
	@RequestMapping(value="/menuSetup",method = { RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public Map<String,Object> menuSetup(HttpServletRequest request,HttpServletResponse response,String roleId){	
	   	Map<String,Object> retData=new HashMap<String,Object>();
	   	
		HttpSession session = request.getSession();
		Sysuser u = (Sysuser)session.getAttribute("s_user");
		if(u==null){
			retData.put("code", "1");
			retData.put("msg", "用户已超时，请退出登录");
			retData.put("data", "");
			return retData;
		}
		
		//获取系统所有菜单权限
		List<Sysmenu> allList = sysmenuService.getAll();
		//获取用户已分配的菜单权限
		List<Sysmenu> ownList = sysmenuService.getByRoleId(roleId);
		
		retData.put("code", "0");
		retData.put("msg", "获取菜单信息列表成功");
		retData.put("allList", allList);
		retData.put("ownList", ownList);
		retData.put("roleId", roleId);
		
		return retData;
	}
	
	/**
	 * 保存角色权限
	 * @param menuIds
	 * @param roleId
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/saveRoleMenuSetup",method = { RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public Map<String,Object> saveRoleMenuSetup(String[] menuIds,String roleId,HttpServletRequest request,HttpServletResponse response){		
	   	Map<String,Object> retData=new HashMap<String,Object>();
	   	
		HttpSession session = request.getSession();
		Sysuser u = (Sysuser)session.getAttribute("s_user");
		if(u==null){
			retData.put("code", "1");
			retData.put("msg", "用户已超时，请退出登录");
			retData.put("data", "");
			return retData;
		}
		
		retData.put("code", "0");
		retData.put("msg", "保存角色权限成功");
		retData.put("data", "");
		return retData;
		
/*		if(userlimits==null || userlimits.length<1){
			return "redirect:/admin/roles/menuSetup.do?roleId="+roleId;	
		}
		
		//获取用户已分配的菜单权限
		List<Sysmenu> umlist =  menuDao.getByRoleId(roleId);
		
		//新增的菜单ID
		Set<Integer> addLimitSet = null;
		addLimitSet = new HashSet<Integer>();
		for(int i=0,len=userlimits.length;i<len;i++){
			addLimitSet.add(userlimits[i]);
		}
		
		for(int i=0,len=umlist.size();i<len;i++){
			Integer old = umlist.get(i).getId();
			if(addLimitSet.contains(old)){
				addLimitSet.remove(old);
			}
		}
		
		userlimits = new Integer[addLimitSet.size()];
		int index = 0;
		for(Integer i : addLimitSet){
			userlimits[index]=i;
			index++;
		}
	
		if(roleDao.saveRoleMenus(roleId,userlimits)){		
			syslogsDao.saveOrUpdate(u.getLoginName(),
					SyslogOptype.op_update.getOptype(),
					"更新角色的权限信息");
			return "redirect:/admin/roles/menuSetup.do?roleId="+roleId;	
		}else{
			r.setAttribute("msg", "分配权限失败！");
			return "admin/error";
		}		*/		
	}	
	
	/**
	 * 保存角色
	 * @param o
	 * @param r
	 * @return
	 */
	@RequestMapping(value ="/save",method = { RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public Map<String,Object> save(HttpServletRequest request,HttpServletResponse response,Sysrole role){		
		Map<String,Object> retData=new HashMap<String,Object>();
		
		HttpSession session = request.getSession();
		Sysuser u = (Sysuser)session.getAttribute("s_user");
		if(u==null){
			retData.put("code", "1");
			retData.put("msg", "用户已超时，请退出登录");
			retData.put("data", "");
			return retData;
		}
		try {
			retData=sysroleService.saveOrUpdate(role);
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
	 * 删除角色
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
			retData.put("code", "1");
			retData.put("msg", "用户已超时，请退出登录");
			retData.put("data", "");
			return retData;
		}
		
		try {
			int result=sysroleService.delete(id);
			if(result>0){
				retData.put("code", "0");
				retData.put("msg", "删除用户成功");
			}else{
				retData.put("code", "1");
				retData.put("msg", "删除用户失败");
			}
			return retData;
		} catch (Exception e) {
			e.printStackTrace();
			retData.put("code", "1");
			retData.put("msg", "系统出现异常");
			return retData;
		}
		
	}
	
}
