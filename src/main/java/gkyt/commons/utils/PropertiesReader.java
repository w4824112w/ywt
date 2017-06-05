package gkyt.commons.utils;
import java.util.Properties;
import java.util.Enumeration;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

import org.apache.commons.lang.StringUtils;
/**
 * 属性文件读取类，
 * 默认从根目录中的conf.properties文件中读取
 * @author zxj
 * @date 20130817
 *
 */
public class PropertiesReader {
   
	private static PropertiesReader _propertiesReader = null;
    
    private Properties _properties = null;
   
    /**
     * <p> 获取配置实例
     * @return PropertyReader
     */
    public static PropertiesReader getIntance() {
        if (_propertiesReader == null)
            _propertiesReader = new PropertiesReader("/export.properties");
        return _propertiesReader;
    }
    
    /**
     * <p> 获取配置实例
     * @return PropertyReader
     */
    public static PropertiesReader getIntance(String path) {
        if (_propertiesReader == null){
        	if(StringUtils.isNotBlank(path)){
        		_propertiesReader = new PropertiesReader(path);
        	}else{
        		_propertiesReader = getIntance();
        	}
        }           
        return _propertiesReader;
    }
   
    /**
    * 指定配置文件的路径
    */
    private PropertiesReader(String path) {
        _properties = this.createProperties(path);
        if (_properties == null)
            _properties = new Properties();
    }
    
    /**
     * <p> 得到一个整数属性
     * @param key String
     * @return Integer
     * */
    public Integer getInt(String key) {
    	String prop = this.getProperty(key);
    	if (prop == null || prop.length() == 0){
    		return Integer.valueOf(0);
    	}
    	return Integer.valueOf(this.getProperty(key));
    }

    /**
     * <p> 得到一个长整数属性
     * @param key String
     * @return Integer
     * */
    public Long getLong(String key) {
    	String prop = this.getProperty(key);
    	if (prop == null || prop.length() == 0){
    		return Long.valueOf(0);
    	}
    	return Long.valueOf(prop);
    }
    
    /**
     * <p> 从property中得到一个属性
     * @param key String
     * @return String
     */
    public String getProperty(String key) {
        return _properties.getProperty(key);
    }

    /**
     * <p> 从property中得到一个属性
     * @param param String
     * @param String default value
     * @return String
     */
    public String getProperty(String key, String defaultValue) {
        return _properties.getProperty(key, defaultValue);
    }
    
    /**
     * <p> 把读取的文字进行中文转化
     * @param key type of String
     * @return String
     * */
    public String getConvertGBK(String key) {
    	
    	String rs = "";
    	
    	if (key == null || key.length() == 0){
    		return rs;
    	}
    	
    	String prop = this.getProperty(key);
    	
    	if (prop == null || prop.length() == 0){
    		return rs;
    	}else {
    		try {
				rs= new String(prop.getBytes("ISO8859_1"),"GBK");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
    	}   	
    	return rs;
    }
    /**
     * <p> 从property中得到所有的属性
     * @return Iterator
     * */
    public Enumeration<?> getValues() {
        try {
            return _properties.elements();
        } catch (Exception ex) {
            return null;
        }
    }

    /**
     * <p> Get all keys in the property file
     * @return Iterator
     * */
    public Enumeration<?> getKeys() {
        try {
            return _properties.propertyNames();
        } catch (Exception ex) {
            return null;
        }
    }

    /**
     * <p> 根据给定的路径读取属性
     * @param String path
     * @return properties
     * */
    private Properties createProperties(String path) {
        Properties p = null;
        Object obj = null;
        try {
            obj = getClass().getResourceAsStream(path);
            if (obj == null){
                obj = new FileInputStream(path);
            }
        } catch (IOException e) {
            System.err.println("System can not find the property!");
        }
        if (obj != null) {
            try {
                p = new Properties();
                p.load( (InputStream) obj);
                ( (InputStream) obj).close();
            } catch (IOException e) {
                p = null;
            }
        }
        return p;
    }
    /**
     * <p> 根据给定的路径读取属性

     * @param String path
     * @return properties
     * */
    public Properties createPropertie(String path) {
        Properties p = null;
        Object obj = null;
        try {
            obj = getClass().getResourceAsStream(path);
            if (obj == null){
                obj = new FileInputStream(path);
            }
        } catch (IOException e) {
            System.err.println("System can not find the property!");
        }
        if (obj != null) {
            try {
                p = new Properties();
                p.load( (InputStream) obj);
                ( (InputStream) obj).close();
            } catch (IOException e) {
                p = null;
            }
        }
        return p;
    }
}
