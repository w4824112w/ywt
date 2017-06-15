package gkyt.controller.api;

import gkyt.commons.utils.DateSupport;
import gkyt.commons.utils.PropertiesReader;
import gkyt.model.PrisonTerms;
import gkyt.model.Prisoners;
import gkyt.model.Sysuser;
import gkyt.pojo.SysuserDto;
import gkyt.service.ExportLogServiceI;
import gkyt.service.PrisonServiceI;
import gkyt.service.PrisonTermsServiceI;
import gkyt.service.PrisonersServiceI;
import gkyt.service.SysuserServiceI;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.alibaba.fastjson.JSONObject;

/**
 * 数据交换管理
 * @author hk
 *
 */
@Controller
@RequestMapping("/api/dataexchange")
public class DataExchangeController {
	private static final Logger log = Logger.getLogger(DataExchangeController.class); 

	@Autowired
	private PrisonServiceI prisonService;

	@Autowired
	private PrisonersServiceI prisonersService;
	
	@Autowired
	private PrisonTermsServiceI prisonTermsService;
	
	// 配置文件读取类
	private PropertiesReader propertyReader = PropertiesReader.getIntance();

	/**
	 * 商品数据导出
	 * @param startTime
	 * @param endTime
	 * @param request
	 * @param response
	 * @return
	 */
    @RequestMapping(value = "/export", method = { RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public Map<String,Object> export(String startTime, String endTime,HttpServletRequest request, HttpServletResponse response) {
       	Map<String,Object> retData=new HashMap<String,Object>();
       	
		HttpSession session = request.getSession();
		Sysuser u = (Sysuser)session.getAttribute("s_user");
		if(u==null){
			retData.put("code", "2");
			retData.put("msg", "用户已超时，请退出登录");
			return retData;
		} 

		// 查询字段
		String query_fields = propertyReader.getProperty("query_fields");
		
		String[] files = query_fields.split(",");
		for (int i = 0; i < files.length; i++) {
			files[i] = files[i].substring(files[i].lastIndexOf(".") + 1);
		}
		// 查询表
		String query_tables = propertyReader.getProperty("query_tables");
		// 查询关系
		String query_relations = propertyReader.getProperty("query_relations");
		
		String[] sql_relations = query_relations.split(",");
		//导出标题栏中文说明
		String export_title = propertyReader.getProperty("export_title");
		
		Map<String,String> titles=new HashMap<String,String>();
		String[] export_titles=export_title.split(",");
		
		for(int i=0;i<export_titles.length;i++){
			String[] tmp=export_titles[i].split("=");
			titles.put(tmp[0], tmp[1]);
		}

		StringBuffer query_sql = new StringBuffer();

		// 组装导出sql语句
		query_sql.append("select ").append(query_fields).append(" from ")
				.append(query_tables).append(" where 1=1 ");
		for (String s : sql_relations) {
			query_sql.append(" and ").append(s);
		}
		// 必要条件
		query_sql
				.append(" and orders.`status`='TRADE_SUCCESS' and items.barcode not in('A','B') ");

		if (u.getJailId() != null) { // 监狱id
			query_sql.append(" and orders.jail_id =").append(u.getJailId());
		}

		if (StringUtils.isNotBlank(startTime)) { // 开始时间
			query_sql.append(" and orders.created_at >='" + startTime
					+ " 00:00:00" + "'");
		}

		if (StringUtils.isNotBlank(endTime)) { // 结束时间
			query_sql.append(" and orders.created_at <='" + endTime
					+ " 23:59:59" + "'");
		}

		List<Map<String, Object>> list = prisonService.exportData(query_sql
				.toString());

		int colIndex = 0;

		try {

			String filename = "excel_file.xlsx";

			String tmpPath = request.getSession().getServletContext()
					.getRealPath("/filedown/" + filename);

			InputStream is = new FileInputStream(tmpPath);

			XSSFWorkbook work = new XSSFWorkbook(is);

			// 获取第一个工作表
			XSSFSheet sheet = work.getSheetAt(0);

			// 创建第一行
			XSSFRow row = null;
			XSSFCell cell = null;
			row = sheet.createRow(0);
			/*
			 * XSSFCell cell = row.createCell(colIndex);
			 * cell.setCellValue("序号");
			 */

			for (int j = 0; j < files.length; j++) {
			//	colIndex++;
				cell = row.createCell(j);
				cell.setCellValue(titles.get(files[j]));
			//	cell.setCellValue(files[j]);
			}

			colIndex = 0;

			for (int i = 0; i < list.size(); i++) {

				// 从第三行开始写数据，第一行和第二行分别为标题和信息列
				row = sheet.createRow(i + 1);

				Map<String, Object> o = list.get(i);

				// 第1列：序号
				/*
				 * cell = row.createCell(colIndex); cell.setCellValue(i + 1);
				 */

				for (int j = 0; j < files.length; j++) {
				//	colIndex++;
					cell = row.createCell(j);
					String temp = files[j];
					if (o.get(files[j]) != null) {
						cell.setCellValue(o.get(files[j]).toString());
					} else {
						cell.setCellValue("");
					}

				}

				colIndex = 0;
			}

			String savePath = request.getSession().getServletContext()
					.getRealPath("/filedown/reports/" + filename);

			FileOutputStream os = new FileOutputStream(savePath);
			work.write(os);
			os.close();

/*			// 直接下载文件
			String codedFileName = java.net.URLEncoder.encode("监狱订单详情_"
					+ DateSupport.getTimestamps() + ".xlsx", "UTF-8");

			File file = new File(savePath);

			// 以流的形式下载文件。
			InputStream fis = new BufferedInputStream(new FileInputStream(
					savePath));
			byte[] buffer = new byte[fis.available()];
			fis.read(buffer);
			fis.close();

			// 设置response的Header
			response.addHeader("Content-Disposition", "attachment;filename="
					+ codedFileName);
			response.addHeader("Content-Length", "" + file.length());
			response.setContentType("application/msexcel;charset=UTF-8");
			OutputStream toClient = new BufferedOutputStream(
					response.getOutputStream());

			toClient.write(buffer);
			toClient.flush();
			toClient.close();*/
			
			retData.put("code", "0");
			retData.put("msg", "商品数据导出成功");
			retData.put("filepath", "/filedown/reports/"+filename);
			return retData;
			
		} catch (Exception e) {
			e.printStackTrace();
			retData.put("code", "1");
			retData.put("msg", "系统出现异常，导出失败");
			return retData;
		}


	}

	/**
	 * 下载excel文件
	 * @param path
	 * @param request
	 * @param response
	 */
	@RequestMapping(value="/downFiles.do")
	public void downFiles(String path,HttpServletRequest request,HttpServletResponse response){
		 
		try{	
			String codedFileName = java.net.URLEncoder.encode("监狱订单详情_"
					+ DateSupport.getTimestamps() + ".xlsx", "UTF-8");
			
			String savePath =request.getSession().getServletContext().getRealPath(path);
			
			File file = new File(savePath);
					
			 // 以流的形式下载文件。
	        InputStream fis = new BufferedInputStream(new FileInputStream(savePath));
	        byte[] buffer = new byte[fis.available()];
	        fis.read(buffer);
	        fis.close();
	        
	        // 设置response的Header
	        response.addHeader("Content-Disposition", "attachment;filename=" + codedFileName);
	        response.addHeader("Content-Length", "" + file.length());
	        response.setContentType("application/msexcel;charset=UTF-8");
	        OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
	      
	        toClient.write(buffer);
	        toClient.flush();
	        toClient.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
    
	/**
	 * 将xml文件数据插入狱务通表
	 *
	 * @param fileName
	 *            文件名
	 * @param jail
	 *            监狱ID
	 * @param request
	 *            request
	 * @return null
	 */
/*	@RequestMapping(value="/uploadPrisonerInfo.do", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String uploadPrisonerInfo(@RequestParam MultipartFile fileName, String jail,
			HttpServletRequest request) throws Exception {
		User u = (User) request.getSession().getAttribute("s_user");
		if (!fileName.isEmpty()) {
			// 判断文件是否为excel文件
			
			 * if (-1 == fileName.getOriginalFilename().indexOf(".xls")) {
			 * return "数据文件必须为xls格式"; }
			 

			InputStream inputstream = fileName.getInputStream();
			
			// 查询字段
			String excel_fields = propertyReader.getProperty("excel_fields");
			String[] fields=excel_fields.split(",");
			
			String[] read_fields=new String[fields.length];
			for(int i=0;i<fields.length;i++){
				read_fields[i]=fields[i].substring(fields[i].lastIndexOf("=")+1);
			}
			List<Map<String,Object>> datas=analysisPrisonerInfo(inputstream,read_fields);

			int count = 0;
			
			for(int i=0;i<datas.size();i++){
				Prisoners prisoner=new Prisoners();
				Map data=datas.get(i);
				
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
				if(data.get("nowsentencestart")!=null){	//坐牢开始时间
					String time=data.get("nowsentencestart").toString();
					if(!(StringUtils.isBlank(time)||time.equals("--"))){
						prisoner.setPrisonTermStartedAt(format.parse(time));
					}
				}
				if(data.get("nowsentenceend")!=null){	//坐牢结束时间
					String time=data.get("nowsentenceend").toString();
					if(!(StringUtils.isBlank(time)||time.equals("--"))){
						prisoner.setPrisonTermEndedAt(format.parse(time));
					}
				}
				
				//判断犯人编号
				if(data.get("prisoner_number")!=null){
					prisoner.setPrisonerNumber(data.get("prisoner_number").toString());
					//验证数据库中是否已经存在此犯人
					Prisoners temp=prisonersService.validate(prisoner);
					if(temp!=null){
						PrisonTerms record=new PrisonTerms();
						record.setPrisonerId(temp.getId());
						record.setTermStart(prisoner.getPrisonTermStartedAt());			
						record.setTermFinish(prisoner.getPrisonTermEndedAt());
						record.setCreatedAt(new Date());
						
						if(!prisonTermsService.validate(record)){	//数据库中如果不存在则新增该犯人的刑期变动
							prisonTermsService.insert(record);
							count++;
						}
						
						continue;
						
					}
				}else{
					continue;
				}
				
				if(data.get("name")!=null){	//犯人姓名
					prisoner.setName(data.get("name").toString());
				}
				if(data.get("gender")!=null){	//犯人性别
					prisoner.setGender(data.get("gender").toString());
				}
				if(data.get("charges")!=null){	//犯罪说明
					prisoner.setName(data.get("charges").toString());
				}
				if(data.get("areaname")!=null){	//犯罪说明
					prisoner.setPrisonArea(data.get("areaname").toString());
				}
				prisoner.setJailId(u.getJailId());	//犯人所属监狱
				prisoner.setCreatedAt(new Date());
				prisoner.setUpdatedAt(new Date());
				int ret=prisonersService.insert(prisoner);

				if(ret>0){	//插入犯人基本信息表成功
					//插入刑期变动表
					PrisonTerms record=new PrisonTerms();
					record.setPrisonerId(prisoner.getId());
					record.setTermStart(prisoner.getPrisonTermStartedAt());			
					record.setTermFinish(prisoner.getPrisonTermEndedAt());
					record.setCreatedAt(new Date());
					prisonTermsService.insert(record);
					
					count++;
				}
				
			}
			// 数据导入之后删除上传文件
			// File file = new File(path);
			// if (file.exists() && file.isFile()) file.delete();
			System.out.println("count------"+count);
			
			return count > 0 ? "导入成功, 导入" + count + "条数据" : "导入失败";
		}
		System.out.println("没有找到该文件");
		return "没有找到该文件";
	}*/
	
    /**
     * 犯人数据导入
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/uploadPrisonerInfo.do",method = { RequestMethod.POST})
    @ResponseBody
	public Map<String,Object> uploadPrisonerInfo(MultipartHttpServletRequest request, HttpServletResponse response) {
    	Map<String,Object> retData=new HashMap<String,Object>();
    	
		HttpSession session = request.getSession();
		Sysuser u = (Sysuser)session.getAttribute("s_user");
		if(u==null){
			retData.put("code", "2");
			retData.put("msg", "用户已超时，请退出登录");
			return retData;
		} 
		
    	try {
    		String fileId = request.getParameter("fileId");
            MultipartFile fileName = request.getFile(fileId);
        	
    		if (!fileName.isEmpty()) {
    			// 判断文件是否为excel文件
    			String validSuffix=fileName.getOriginalFilename().substring(fileName.getOriginalFilename().lastIndexOf("."));
    			if (!(validSuffix.equals(".xls")|| validSuffix.equals(".xlsx"))) {
    				retData.put("code", "1");
    				retData.put("msg", "数据文件必须为xls格式或者xlsx格式");
    		        return retData;
    			}

    			InputStream inputstream = fileName.getInputStream();
    			
    			// 查询字段
    			String excel_fields = propertyReader.getProperty("excel_fields");
    			String[] fields=excel_fields.split(",");
    			
    			String[] read_fields=new String[fields.length];
    			for(int i=0;i<fields.length;i++){
    				read_fields[i]=fields[i].substring(fields[i].lastIndexOf("=")+1);
    			}
    			List<Map<String,Object>> datas=analysisPrisonerInfo(inputstream,read_fields);

    			int count = 0;
    			
    			for(int i=0;i<datas.size();i++){
    				Prisoners prisoner=new Prisoners();
    				Map data=datas.get(i);
    				
    				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    				if(data.get("nowsentencestart")!=null){	//坐牢开始时间
    					String time=data.get("nowsentencestart").toString();
    					if(!(StringUtils.isBlank(time)||time.equals("--"))){
    						prisoner.setPrisonTermStartedAt(format.parse(time));
    					}
    				}
    				if(data.get("nowsentenceend")!=null){	//坐牢结束时间
    					String time=data.get("nowsentenceend").toString();
    					if(!(StringUtils.isBlank(time)||time.equals("--"))){
    						prisoner.setPrisonTermEndedAt(format.parse(time));
    					}
    				}
    				
    				//判断犯人编号
    				if(data.get("prisoner_number")!=null){
    					prisoner.setPrisonerNumber(data.get("prisoner_number").toString());
    					//验证数据库中是否已经存在此犯人
    					Prisoners temp=prisonersService.validate(prisoner);
    					if(temp!=null){
    						PrisonTerms record=new PrisonTerms();
    						record.setPrisonerId(temp.getId());
    						record.setTermStart(prisoner.getPrisonTermStartedAt());			
    						record.setTermFinish(prisoner.getPrisonTermEndedAt());
    						record.setCreatedAt(new Date());
    						
    						if(!prisonTermsService.validate(record)){	//数据库中如果不存在则新增该犯人的刑期变动
    							prisonTermsService.insert(record);
    							count++;
    						}
    						
    						continue;
    						
    					}
    				}else{
    					continue;
    				}
    				
    				if(data.get("name")!=null){	//犯人姓名
    					prisoner.setName(data.get("name").toString());
    				}
    				if(data.get("gender")!=null){	//犯人性别
    					prisoner.setGender(data.get("gender").toString());
    				}
    				if(data.get("charges")!=null){	//犯罪说明
    					prisoner.setName(data.get("charges").toString());
    				}
    				if(data.get("areaname")!=null){	//犯罪说明
    					prisoner.setPrisonArea(data.get("areaname").toString());
    				}
    				prisoner.setJailId(u.getJailId());	//犯人所属监狱
    				prisoner.setCreatedAt(new Date());
    				prisoner.setUpdatedAt(new Date());
    				int ret=prisonersService.insert(prisoner);

    				if(ret>0){	//插入犯人基本信息表成功
    					//插入刑期变动表
    					PrisonTerms record=new PrisonTerms();
    					record.setPrisonerId(prisoner.getId());
    					record.setTermStart(prisoner.getPrisonTermStartedAt());			
    					record.setTermFinish(prisoner.getPrisonTermEndedAt());
    					record.setCreatedAt(new Date());
    					prisonTermsService.insert(record);
    					
    					count++;
    				}
    				
    			}
    			// 数据导入之后删除上传文件
    			// File file = new File(path);
    			// if (file.exists() && file.isFile()) file.delete();
    			System.out.println("count------"+count);
    			String ret=count > 0 ? "导入成功, 导入" + count + "条数据" : "数据库数据已存在,导入失败";
    			retData.put("code", "0");
    			retData.put("msg", ret);
    	        return retData;
    		}
    		
    		System.out.println("没有找到该文件");
    		retData.put("code", "1");
    		retData.put("msg", "没有找到该文件");
            return retData;
		} catch (Exception e) {
			e.printStackTrace();
    		retData.put("code", "1");
    		retData.put("msg", "系统出现异常，导入失败");
            return retData;
		}
        
        
	}

	/**
	 * 分析excel数据加工成list
	 * @param inputstream	读取的文件流
	 * @param fields	读取的excel字段值
	 * @return
	 * @throws Exception
	 */
	public List<Map<String,Object>> analysisPrisonerInfo(InputStream inputstream,String[] fields) throws Exception{  
	    List<Map<String,Object>> ret = new ArrayList<Map<String,Object>>();  
	    //根据指定的文件输入流导入Excel从而产生Workbook对象  
	    Workbook wb0 = new HSSFWorkbook(inputstream);  
	    //获取Excel文档中的第一个表单  
	    Sheet sheet0 = wb0.getSheetAt(0);  
	  //获取Excel文档中的第一行  
	    Row title=sheet0.getRow(0);
	 //   int cells=title.getPhysicalNumberOfCells();
	    List<Map<Object,Object>> columnIndexs=new ArrayList<Map<Object,Object>>();
	    int count=title.getPhysicalNumberOfCells();
	    for(int i=0;i<title.getPhysicalNumberOfCells();i++){
	    	Cell c=title.getCell(i);
	    	String val=c.getStringCellValue();
	    	for(int j=0;j<fields.length;j++){
	    		if(val.equals(fields[j])){
	    			Map<Object,Object> map=new HashMap<Object,Object>();
	    			map.put(c.getColumnIndex(),fields[j]);
	    			columnIndexs.add(map);
	    		}
	    	}
	    }
	    //分析excel，读取所需的字段值封装至list<map>中
	    for(Row r:sheet0){
	    	 if(r.getRowNum()<1){  
	 		    continue;  
	 		}
	    	Map addVal= new HashMap();
	    	for(int i=0;i<r.getPhysicalNumberOfCells();i++){
	    		//取出单元格数据，并封装在Map中  
	    		Cell cur=r.getCell(i);
	    		for(Map m:columnIndexs){
	    			if(m.get(cur.getColumnIndex())!=null){
	    				if(cur.getCellType()==cur.CELL_TYPE_STRING){
	    					addVal.put(m.get(cur.getColumnIndex()), cur.getStringCellValue());
	    				}else if(cur.getCellType()==cur.CELL_TYPE_NUMERIC){
	    					DecimalFormat df = new DecimalFormat("#");
	    					String value = df.format(cur.getNumericCellValue());
	    					addVal.put(m.get(cur.getColumnIndex()), value);
	    				}
	    				
	    			}
	    		}
	    		
	    	}
	    	ret.add(addVal);
	    }
	    
	    	return ret;     
	    }
	
	
	
}
