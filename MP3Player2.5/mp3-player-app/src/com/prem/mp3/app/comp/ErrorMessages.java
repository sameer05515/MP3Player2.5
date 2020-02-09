package com.prem.mp3.app.comp;
public class ErrorMessages {    
    
    public static String getErrorCodeDesc(String code){
        //return (String) ht.get(code);
        return "";//ResourceBundleManager.getValue(ResourceBundleManager.ERROR_MESSAGES,code);
    }
        
}