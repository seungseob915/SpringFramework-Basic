package interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AuthCheckInterceptor  implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        HttpSession session=request.getSession(false);      // 이미 Session 있을 시 해당 세션 반환, 없을 시 null 반환.
        if(session!=null){
            Object authInfo=session.getAttribute("authInfo");
            if(authInfo!=null){
                return true;
            }
        }
        response.sendRedirect(request.getContextPath()+"/login");       // getContextPath() : 현재 컨텍스트 경로 리턴
        return false;
    }
}
