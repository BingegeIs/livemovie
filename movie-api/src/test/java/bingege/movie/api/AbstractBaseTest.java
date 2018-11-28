package bingege.movie.api;

import bingege.movie.config.security.JwtTokenProvider;
import bingege.movie.config.security.UserPrincipal;
import bingege.movie.model.model.User;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

public abstract class AbstractBaseTest {

    public final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    public MockMvc mvc;
    @Autowired
    protected Aggregator agr;
    @Autowired
    protected PasswordEncoder encoder;
    @Autowired
    public JwtTokenProvider jwtTokenProvider;

    protected String getAdminToken() {
        return "Bearer " + jwtTokenProvider.generateToken(UserPrincipal.create(createAdmin()));
    }

    protected User createAdmin(String nickname, String username, String password) {
        return agr.userService.add(nickname, username, password);
    }

    protected User createAdmin(String nickname, String username) {
        return createAdmin(nickname, username, "123456");
    }

    protected User createAdmin() {
        return createAdmin("test", "test");
    }

    protected MockHttpServletRequestBuilder query(MockHttpServletRequestBuilder pathUrl) {
        return pathUrl.header(HttpHeaders.AUTHORIZATION, getAdminToken()).contentType(MediaType.APPLICATION_JSON_UTF8);
    }

    protected MockHttpServletRequestBuilder query(MockHttpServletRequestBuilder pathUrl, Object data) {
        return pathUrl.header(HttpHeaders.AUTHORIZATION, getAdminToken()).contentType(MediaType.APPLICATION_JSON_UTF8).content(JSONObject.toJSONString(data));
    }

    protected MockHttpServletRequestBuilder query(MockHttpServletRequestBuilder pathUrl, String token) {
        return pathUrl.header(HttpHeaders.AUTHORIZATION, token).contentType(MediaType.APPLICATION_JSON_UTF8);
    }

    protected MockHttpServletRequestBuilder query(MockHttpServletRequestBuilder pathUrl, Object data, String token) {
        return pathUrl.header(HttpHeaders.AUTHORIZATION, token).contentType(MediaType.APPLICATION_JSON_UTF8).content(JSONObject.toJSONString(data));
    }
}
