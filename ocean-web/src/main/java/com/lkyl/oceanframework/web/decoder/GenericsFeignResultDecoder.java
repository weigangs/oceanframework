package com.lkyl.oceanframework.web.decoder;

import com.google.gson.Gson;
import feign.FeignException;
import feign.Response;
import feign.Util;
import feign.codec.DecodeException;
import feign.codec.Decoder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.NamedThreadLocal;

import java.lang.reflect.Type;

@Slf4j
public class GenericsFeignResultDecoder implements Decoder {

	private static NamedThreadLocal<Class> feignReturnTypeThreadLocal=new NamedThreadLocal<Class>("feignReturnTypeThreadLocal");

	// 调用Feign的泛型接口前，先调用GenericsFeignResultDecoder.setReturnType()方法设置接口返回类型
	public static void setReturnType(Class returnType){
        feignReturnTypeThreadLocal.set(returnType);
    }
    
    // 重写Decode
    @Override
    public Object decode(Response response, Type type) throws FeignException {
    	try{
	        if (response.body() == null) {
	            throw new DecodeException(response.status(), "no data response", null);
	        }
	        Class returnType=feignReturnTypeThreadLocal.get();
	        String bodyStr = Util.toString(response.body().asReader(Util.UTF_8));
	        return new Gson().fromJson(bodyStr,returnType);
	    } catch (Exception e) {
            log.error("GenericsFeignResultDecoder.decode error", e);
        }finally {
            feignReturnTypeThreadLocal.remove();
        }
        return null;
    }
}