package su.vfe.foodmanager.security;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component("authEntryPoint")
public class AuthEntryPoint extends BasicAuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authEx)
            throws IOException, ServletException {
        String UNAUTH_REASON_MESSAGE = "API secured with JWT authentication";
        response.sendError(HttpStatus.UNAUTHORIZED.value(), UNAUTH_REASON_MESSAGE);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        setRealmName("FoodManager");
        super.afterPropertiesSet();
    }
}