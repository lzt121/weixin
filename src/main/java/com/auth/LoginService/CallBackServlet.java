package com.auth.LoginService;

import com.auth.util.AuthUtil;
import net.sf.json.JSONObject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;

@RestController
@RequestMapping("/")
public class CallBackServlet extends HttpServlet {
    @RequestMapping("/callBack")
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException, ServletException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        String code = req.getParameter("code");
        String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=" + AuthUtil.APPID+
                "&secret=" +AuthUtil.APPSECRET+
                "&code=" +code +
                "&grant_type=authorization_code";
        try {
            JSONObject jsonObject = AuthUtil.doGetJson(url);
            System.out.println(jsonObject);
            String openid = jsonObject.getString("openid");
            String access_token = jsonObject.getString("access_token");
            String infoUrl = "https://api.weixin.qq.com/sns/userinfo?access_token=" +access_token+
                    "&openid=" +openid+
                    "&lang=zh_CN";
            System.out.println(infoUrl);
            JSONObject info = AuthUtil.doGetJson(infoUrl);
            System.out.println(info);
            //1.使用微信用户信息直接登录,无需注册绑定
//            req.setAttribute("info",info);
//            req.getRequestDispatcher("#").forward(req,resp);
            //2.将微信与当前系统的账号进行绑定
            //1.通过ipenid来查询与数据库的信息
            // 如果有信息则跳的绑定成功页面
            // 如果没有成功则跳到绑定页面openid
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
