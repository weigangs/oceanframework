package com.lkyl.oceanframework.web.config;

import com.lkyl.oceanframework.web.exception.CommonException;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;

//import io.swagger.annotations.ApiModel;
//import io.swagger.annotations.ApiModelProperty;

@Accessors(chain = true)
@Getter
@Setter
@NoArgsConstructor
@ToString
//@ApiModel(value = "返回说明")
@SuppressWarnings("all")
public class CommonResult implements Serializable {
    private static final long serialVersionUID = 6191745064790884707L;
//    @ApiModelProperty(value = "返回状态码；0000:成功")
    private String code;
//    @ApiModelProperty(value = "返回信息")
    private String msg;
//    @ApiModelProperty(value = "返回数据")
    private Object data;

    public CommonResult(CommonException commonException){
        this.code = commonException.getCode();
        this.msg = commonException.getMsg();
    }

}
