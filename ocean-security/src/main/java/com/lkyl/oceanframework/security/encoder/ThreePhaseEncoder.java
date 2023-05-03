package com.lkyl.oceanframework.security.encoder;

import com.lkyl.oceanframework.common.utils.exception.CommonException;
import com.lkyl.oceanframework.common.utils.exception.CommonExceptionEnum;
import com.lkyl.oceanframework.common.utils.utils.DesUtil;
import com.lkyl.oceanframework.common.utils.utils.RSAUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * 三段式密码解密
 *
 * @version 1.0
 * @author: nicholas
 * @createTime: 2022年05月18日 22:42
 */
@Slf4j
public class ThreePhaseEncoder extends BCryptPasswordEncoder{

    private static class ThreePhaseEncoderHolder {
        private static ThreePhaseEncoder instance = new ThreePhaseEncoder();
    }

    private ThreePhaseEncoder() {}

    public String encode(String rawPassword, String privateKey) {

        return super.encode(this.getRawCharSeq(rawPassword, privateKey));
    }

    public boolean matches(String rawPassword, String encodedPassword, String privateKey) {
        return super.matches(this.getRawCharSeq(rawPassword, privateKey), encodedPassword);
    }

    private String getRawCharSeq(String cryptPassword, String privateKey) {
        if(StringUtils.isNotBlank(cryptPassword)){
            try {
                String [] strArr = cryptPassword.split(cryptPassword.substring(32, 61));
                return DesUtil.decryptedDES(strArr[1], RSAUtil.decrypt(strArr[2], privateKey));
            } catch (Exception e) {
                log.error("error:", e);
                throw new CommonException(CommonExceptionEnum.DECODE_ERR);
            }
        }
        return null;
    }

    public static ThreePhaseEncoder getInstance() {
        return ThreePhaseEncoderHolder.instance;
    }
}
