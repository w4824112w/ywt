package gkyt.commons;

import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONException;

/*
一.首先概述一下Fastjson中的经常调用的方法：  避免使用org.apche.json的包。

1 public static final Object parse(String text); // 把JSON文本parse为JSONObject或者JSONArray
2 public static final JSONObject parseObject(String text)； // 把JSON文本parse成JSONObject
3 public static final T parseObject(String text, Class clazz); // 把JSON文本parse为JavaBean
4 public static final JSONArray parseArray(String text); // 把JSON文本parse成JSONArray
5 public static final List parseArray(String text, Class clazz); //把JSON文本parse成JavaBean集合
6 public static final String toJSONString(Object object); // 将JavaBean序列化为JSON文本
7 public static final String toJSONString(Object object, boolean prettyFormat); // 将JavaBean序列化为带格式的JSON文本
8 public static final Object toJSON(Object javaObject); 将JavaBean转换为JSONObject或者JSONArray。
*/

public class JsonHelper {
	
	private static final Logger log = Logger.getLogger(JsonHelper.class);
   //第一部分  javabean转JSON  或者jsonstr
  
   /**
    * 将一个 Object 或者List对象转化为JSONObject或者JSONArray
    * @param ObjOrList  Object 或者List对象
    * @return
    */
   public static Object toJSON(Object ObjOrList){
       Object obj=null;
       try {
           obj=JSON.toJSON(ObjOrList);

       } catch (Exception e) {
           e.printStackTrace();
           log.error("toJSON转换异常"+e);
       }
       return obj;
   }
  
   /**
    * 将一个 Object 或者List对象转化为JSOObject或者JSONArray
    * @param ObjOrList  Object 或者List对象 或者hashmap 但是如果是set  就会有问题
    * @return
    */
   public static String toJSONStr(Object ObjOrList){
       String  jsonstr="";

       try {
           jsonstr=JSON.toJSONString(ObjOrList);
       } catch (Exception e) {
           e.printStackTrace();
           log.error("toJSONStr转换异常"+e);
       }

       return jsonstr;
   }
   
   /**
    * 把任何对象转换成json字符串
    * @Title: toJSONString
    * @Description: TODO
    * @param Obj
    * @return String
    */
   public static String toJSONString(Object Obj){
       String  jsonstr="";

       try {
           jsonstr=JSON.toJSONStringWithDateFormat(Obj, "yyyy-MM-dd HH:mm:ss");
       } catch (Exception e) {
           e.printStackTrace();
           log.error("toJSONString转换异常"+e);
       }

       return jsonstr;
   }


   //第二部分字符串转  obj list
   /**
    * 字符串转obj
    * @param jsonstr
    * @param clazz
    * @return
    */
   public static Object parseToObject(String jsonstr,Class<?> clazz){
       Object parseObj=null;
       try {
           parseObj=JSON.parseObject(jsonstr, clazz);
       } catch (Exception e) {
           e.printStackTrace();
           log.error("parseToObject转换异常"+e);
       }
       return parseObj;
   }

   /**
    * 字符串转list
    * @param jsonstr
    * @param clazz
    * @return
    */
   public static List<?> parseToList(String jsonstr,Class<?> clazz){
       List<?> parseObj=null;
       try {
           parseObj=JSON.parseArray(jsonstr, clazz);
       } catch (Exception e) {
           e.printStackTrace();
           log.error("parseToList转换异常"+e);
       }
       return parseObj;
   }


   //第三部分  字符串转JSONObj  或者JSONArray

   /**
    * 字符串转jsonobj
    * @param jsonstr
    * @return
    */
   public static JSONObject parseToJSONObejct(String jsonstr){
       JSONObject parseObj=null;
       try {
           parseObj=JSON.parseObject(jsonstr);

       } catch (Exception e) {
           e.printStackTrace();
           log.error("parseToJSONObejct转换异常"+e);
       }
       return parseObj;
   }

   /**
    * 字符串转list
    * @param jsonstr
    * @return
    */
   public static JSONArray parseToJSONArray(String jsonstr){
       JSONArray parseObj=null;
       try {
           parseObj=JSON.parseArray(jsonstr);
       } catch (Exception e) {
           e.printStackTrace();
           log.error("parseToJSONArray转换异常"+e);
       }
       return parseObj;
   }



   //第四部分 com.alibaba包下 JSONObj 或者JSONArr 转 javabean或者 java array
   /**
    *
    * @param jsonObj
    * @param obj
    * @return
    */
   public static Object parseToObject(JSONObject jsonObj,Class<?> obj){
       Object parseObj=null;
       try {
           parseObj=JSON.parseObject(jsonObj + "", obj);
       } catch (Exception e) {
           e.printStackTrace();
           log.error("parseToObject转换异常"+e);
       }
       return parseObj;
   }

   /**
    *
    * @param jsonArr
    * @param obj
    * @return
    */
   public static List<?> parseToList(JSONArray jsonArr,Class<?> obj){
       List list=null;
       try {
           list=JSON.parseArray(jsonArr + "", obj);
       } catch (Exception e) {
           e.printStackTrace();
           log.error("parseToList转换异常"+e);
       }
       return list;
   }

   //第五部分 将android系统下的JSONObj 或者JSONArr 转 javabean或者 javaarr
   //第五部分看似没用不过想想特么的 用的人偶尔还是会用到系统的JSON对象  所以决定加下面这两个方法
   /**
    *
    * @param jsonObj  android系统下的JSONObj
    * @param obj
    * @return
    */
//   public static Object parseToObject(org.json.JSONObject jsonObj,Class<?> obj){
//       Object parseObj=null;
//       try {
//           parseObj=JSON.parseObject(jsonObj.toString(), obj);
//       } catch (Exception e) {
//           e.printStackTrace();
//           log.error("parseToObject转换异常"+e);
//       }
//       return parseObj;
//   }

   /**
    * @param jsonArr  android系统下的JSONArr
    * @param obj
    * @return
    */
//   public static List<?> parseToList(org.json.JSONArray jsonArr,Class<?> obj){
//       List list=null;
//       try {
//           list=JSON.parseArray(jsonArr.toString(), obj);
//       } catch (Exception e) {
//           e.printStackTrace();
//           log.error("parseToListp转换异常"+e);
//       }
//       return list;
//   }
   
   /**
    * json转换为Map
    * @Title: toMap
    * @Description: json转换为Map
    * @param jsonstr
    * @return
    */
   public static Map toMap(String jsonstr ){ 
	    Map map1 =null;
	    try {
	    	map1= JSON.parseObject(jsonstr); 
	       } catch (Exception e) {
	           e.printStackTrace();
	           log.error("toMap转换异常"+e);
	       }
	    return map1;
   } 
   
   
   
   
   public static void main(String[] args) { 
	   String tmp="{\"err_code\":0,\"err_msg\":\"\u64cd\u4f5c\u6210\u529f\uff01\",\"sms_count\":1,\"tick_ids\":\"27911030\",\"remain_count\":6,\"server_time\":\"2015-05-07 11:47:56\"}";
	   toMap(tmp);
   } 

}
