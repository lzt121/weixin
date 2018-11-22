package com.auth.LoginService;

import com.auth.util.AuthUtil;
import net.sf.json.JSONObject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;

@RestController
@RequestMapping("/")
public class LoginServlet extends HttpServlet {
    @RequestMapping("/login")
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException, ServletException {
        String backUrl = "http://cf6c778d.ngrok.io/callBack";
        String url ="https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + AuthUtil.APPID+
                "&redirect_uri="+  URLEncoder.encode(backUrl) +
                "&response_type=code" +
                "&scope=snsapi_userinfo" +
                "&state=STATE#wechat_redirect";
       resp.sendRedirect(url);

    }


}
