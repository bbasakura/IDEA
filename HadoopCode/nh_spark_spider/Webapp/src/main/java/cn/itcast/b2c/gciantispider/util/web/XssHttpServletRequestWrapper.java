package cn.itcast.b2c.gciantispider.util.web;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class XssHttpServletRequestWrapper extends HttpServletRequestWrapper {

    public XssHttpServletRequestWrapper(HttpServletRequest servletRequest)  
    {  
        super(servletRequest);  
    }  
      
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public Map getParameterMap() {  
  
        HashMap paramMap = (HashMap) super.getParameterMap();  
        paramMap = (HashMap) paramMap.clone();  
  
        for (Iterator iterator = paramMap.entrySet().iterator(); iterator.hasNext(); )   
        {  
            Map.Entry entry = (Map.Entry) iterator.next();  
            String [] values = (String[])entry.getValue();  
            for (int i = 0; i < values.length; i++) {  
                if(values[i] instanceof String){  
                    values[i] = cleanXSS(values[i]);  
                }  
            }  
            entry.setValue(values);  
        }  
        return paramMap;  
    }  
      
    @Override    
    public String[] getParameterValues(String name) {    
        String[] values = super.getParameterValues(name);    
        if (values == null) {    
            return null;    
        }    
        String[] newValues = new String[values.length];    
    
        for (int i = 0; i < values.length; i++) {    
            newValues[i] = cleanXSS(values[i]);    
        }    
    
        return newValues;    
    }    
      
    public String getParameter(String parameter)  
    {  
        super.getParameterMap();  
        String value = super.getParameter(parameter);  
        if (value == null)  
        {  
            return null;  
        }  
        return cleanXSS(value);  
    }  
      
    public String getHeader(String name)  
    {  
        String value = super.getHeader(name);  
        if (value == null)  
            return null;  
        return cleanXSS(value);  
    }  
      
    public String getQueryString()  
    {  
        String str = super.getQueryString();  
        if(str == null)  
            return null;  
        return cleanXSS(str);  
    }  
  
    private String cleanXSS(String value)  
    {  
         if (value == null || "".equals(value)) {    
                return value;    
            }    
            
            value = value.replaceAll("<", "&lt;").replaceAll(">", "&gt;");
            value = value.replaceAll("%3C", "&lt;").replaceAll("%3E", "&gt;");
            value = value.replaceAll("[\\\"\\\'][\\s]*javascript:(.*)[\\\"\\\']", "\"\"");
            value = value.replaceAll("script", "").replaceAll("&", "&amp;");;
            return value;    
    }  
}