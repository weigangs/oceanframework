//package com.lkyl.oceanframework.security.endpoint;
//
//import com.lkyl.oceanframework.common.utils.constant.CommonCode;
//import com.lkyl.oceanframework.common.utils.constant.CommonResult;
//import com.lkyl.oceanframework.common.utils.utils.Base64;
//import com.lkyl.oceanframework.common.utils.utils.VerifyCode;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.oauth2.provider.endpoint.FrameworkEndpoint;
//import org.springframework.web.bind.annotation.GetMapping;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//import java.awt.image.BufferedImage;
//import java.io.IOException;
//
///**
// * TODO
// *
// * @version 1.0
// * @author: nicholas
// * @createTime: 2022年05月03日 15:18
// */
//@FrameworkEndpoint
//public class CaptchaCodeEndpoint{
//    @GetMapping("/verify/code")
//    public ResponseEntity<?> code(HttpServletRequest req, HttpServletResponse resp) throws IOException {
//        //创建验证码生成器实例取得生成图片和随机字符串
//        VerifyCode vc = new VerifyCode();
//        BufferedImage image = vc.getImage();
//        String img = Base64.encode(vc.getText().getBytes());
//        //随机字符串存入session中
//        HttpSession session = req.getSession();
//        session.setAttribute(CommonCode.CAPTCHA_CODE_KEY, img);
//        //用流传输
//        return ResponseEntity.ok(new CommonResult().setCode(CommonCode.SUCCESS).setMsg(CommonCode.SUCCESS_MSG).setData(img));
//
//    }
//}
