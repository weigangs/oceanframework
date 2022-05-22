package com.lkyl.oceanframework.common.utils.exception;

import com.lkyl.oceanframework.common.utils.constant.CommonCode;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class ErrorCodeConfig {

    private Log logger = LogFactory.getLog(ErrorCodeConfig.class);

    private static final String UNKNOWN_ERROR_CODE = "未知的错误代码，{0}";

    private ErrorCodeConfig(){

    }

    private static class ErrorCodeConfigPlaceHolder{
        private static ErrorCodeConfig errorCodeConfig = new ErrorCodeConfig();
    }

    public static ErrorCodeConfig getInstance(){
        return ErrorCodeConfigPlaceHolder.errorCodeConfig;
    }

    public void init(){
        try{
            logger.info("loading error config files");
            Properties properties = new Properties();
            ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
            Resource[] resources =  resolver.getResources("classpath*:config/error_*.properties");
            for (Resource r : resources) {
                properties.clear();
                properties.load(r.getInputStream());
                code.putAll(new HashMap<>((Map)properties));
                if(logger.isDebugEnabled()){
                    logger.debug(properties);
                }
            }
            logger.info("error config files loaded！");
            if(StringUtils.isBlank(code.get(CommonCode.EXCEPTION))){
                code.put(CommonCode.EXCEPTION, CommonCode.PLATFORM_ERR_MSG);
            }
        }catch(IOException e){
            logger.error("load error config file failed: ", e);
        }

    }

    private static Map<String, String> code = new HashMap<String, String>();

    public static String getErrorMessage(String errorCode){
        if(code.containsKey(errorCode)){
            return code.get(errorCode);
        }
        return UNKNOWN_ERROR_CODE;
    }

    public static boolean containsCode(String errorCode){
        return code.containsKey(errorCode);
    }

    public static Map<String, String> getCode() {
        return code;
    }

    public static void setCode(Map<String, String> code) {
        ErrorCodeConfig.code = code;
    }
}
