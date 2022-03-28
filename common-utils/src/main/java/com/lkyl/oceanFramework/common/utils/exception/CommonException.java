package com.lkyl.oceanframework.common.utils.exception;

import com.lkyl.oceanframework.common.utils.constant.CommonCode;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.text.MessageFormat;
import java.util.Optional;

@Accessors(chain = true)
@Data
@NoArgsConstructor
@SuppressWarnings("all")
public class CommonException extends RuntimeException{

    private static final long serialVersionUID = 2565431806475335331L;

    private String code;

    private String msg;

    @Override
    public String getMessage() {
        return Optional.ofNullable(msg).orElse(ErrorCodeConfig.getErrorMessage(code));
    }

    public CommonException(String code){
        this.code = code;
        this.msg = ErrorCodeConfig.getErrorMessage(code);
    }

    public CommonException(String code, String ...args){
        this.code = code;
       this.msg =  MessageFormat.format(ErrorCodeConfig.getErrorMessage(code), args);
    }


//    public CommonException(String code, String ...args){
//        this.code = code;
//        this.msg = ErrorCodeConfig.getErrorMessage(code);
//    }

    public CommonException exception(String msg){
        return new CommonException(CommonCode.EXCEPTION, msg);
    }

//    public CommonException(String code, String msg){
//        this.code = code;
//        this.msg = msg;
//    }

}
