package gkyt.controller.web;

import gkyt.commons.poi.excel.ExcelImportUtil;
import gkyt.commons.poi.excel.entity.ImportParams;
import gkyt.commons.utils.DateSupport;
import gkyt.commons.utils.PropertiesReader;
import gkyt.model.PrisonTerms;
import gkyt.model.Prisoners;
import gkyt.model.Sysuser;
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

import org.apache.commons.lang.StringUtils;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.alibaba.fastjson.JSONObject;

/**
 * 本地的
 * 
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/prison")
public class PrisonController {

	@Autowired
	private SysuserServiceI userService;

	@Autowired
	private ExportLogServiceI exportLogService;

	@Autowired
	private PrisonServiceI prisonService;

	@Autowired
	private PrisonersServiceI prisonersService;
	
	@Autowired
	private PrisonTermsServiceI prisonTermsService;
	
	// 配置文件读取类
	private PropertiesReader propertyReader = PropertiesReader.getIntance();

	@RequestMapping("/export.do")
	public void export(String startTime, String endTime, HttpServletRequest r,
			HttpServletResponse response) {
		JSONObject result = new JSONObject();

		Sysuser u = (Sysuser) r.getSession().getAttribute("s_user");

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

			String tmpPath = r.getSession().getServletContext()
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

			String savePath = r.getSession().getServletContext()
					.getRealPath("/filedown/reports/" + filename);

			FileOutputStream os = new FileOutputStream(savePath);
			work.write(os);
			os.close();

			// 直接下载文件
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
			toClient.close();

		} catch (Exception e) {
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
	
    @RequestMapping(value="/uploadPrisonerInfo.do",produces = "text/html;charset=UTF-8")
    @ResponseBody
	public String uploadPrisonerInfo(MultipartHttpServletRequest request, HttpServletResponse response) throws Exception {
    	JSONObject result = new JSONObject();
    	
        String fileId = request.getParameter("fileId");
        MultipartFile fileName = request.getFile(fileId);
    	
        Sysuser u = (Sysuser) request.getSession().getAttribute("s_user");
		if (!fileName.isEmpty()) {
			// 判断文件是否为excel文件
			String validSuffix=fileName.getOriginalFilename().substring(fileName.getOriginalFilename().lastIndexOf("."));
			if (!(validSuffix.equals(".xls")|| validSuffix.equals(".xlsx"))) {
				result.put("msg", "数据文件必须为xls格式或者xlsx格式");
		        return result.toString();
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
	        result.put("msg", ret);
	        return result.toString();
		//	return count > 0 ? "导入成功, 导入" + count + "条数据" : "导入失败";
		}
		System.out.println("没有找到该文件");
        result.put("msg", "没有找到该文件");
        return result.toString();
	 //	return "没有找到该文件";
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
	
	
    /**
     * 根据监狱id下载会见和购物信息到XML文件
     *
     * @param jail 监狱ID
     * @return null
     */
/*    @RequestMapping(value = "downLoad", produces = "text/html;charset=UTF-8", method = RequestMethod.POST)
    @ResponseBody
    public String downLoad(String jail, HttpServletRequest request,HttpServletResponse response) throws Exception {
    	User u = (User) request.getSession().getAttribute("s_user");
        if (StringUtil.isNull(jail))
            return "监狱ID不能为空";
        //文件名
        String fileName = jail + "-" + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + ".xml";
        //获取文件下载路径
        String path = request.getSession().getServletContext()
                .getRealPath(File.separator + "WEB-INF" + File.separator + "download");
        File pathFile = new File(path);
        if (!pathFile.exists()) {
            pathFile.mkdirs();
        }

        LOGGER.info(path + File.separator + fileName);

        response.setContentType("octets/stream");
        response.addHeader("Content-Type", "text/html; charset=utf-8");
        response.setHeader("Content-Disposition", "attachment;fileName=" + fileName);
        ServletOutputStream out;
        String filePath = path + File.separator + fileName;
        File file = new File(filePath);
        insertService.downloadXML(filePath, jail);

        try {
            FileInputStream inputStream = new FileInputStream(filePath);
            out = response.getOutputStream();

            int b = 0;
            byte[] buffer = new byte[1024];
            while ((b = inputStream.read(buffer)) != -1) {
                out.write(buffer, 0, b);
            }
            inputStream.close();
            out.flush();
            out.close();

            if (file.exists()) file.delete();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
*/
	
}
