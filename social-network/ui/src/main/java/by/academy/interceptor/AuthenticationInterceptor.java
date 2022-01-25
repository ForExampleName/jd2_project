package by.academy.interceptor;

import by.academy.enums.AccountStatus;
import by.academy.service.UserStatusService;
import by.academy.session.UserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Arrays;


public class AuthenticationInterceptor implements HandlerInterceptor {
    @Autowired
    private UserDetails userDetails;

    @Autowired
    private UserStatusService userStatusService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (userDetails.isAuthenticated()) {
            if (isAccountBlocked()) {
                request.getSession().invalidate();
                response.sendRedirect("/ui/blocking.html");
                return false;
            }
            return true;
        }

        if (isRecoveryHappening(request)) {
            return true;
        }

        response.sendRedirect("/ui/login.html");
        return false;
    }

    private boolean isRecoveryHappening(HttpServletRequest request) {
        String url = request.getRequestURI();

        String[] recoveryUrls = {
                "/ui/user_recovery/second_phase.html",
                "/ui/user_recovery/second_phase.do",
                "/ui/user_recovery/change_password.html",
                "/ui/user_recovery/change_password.do"
        };

        if (Arrays.asList(recoveryUrls).contains(url)) {
            HttpSession session = request.getSession();
            Boolean recoveryFlag = (Boolean) session.getAttribute("recovery");
            return recoveryFlag != null && recoveryFlag;
        }
        return false;
    }

    private boolean isAccountBlocked() {
        AccountStatus currentAccountStatus = userStatusService
                .findCurrentAccountStatusByUserId(userDetails.getUserId());

        if (currentAccountStatus == AccountStatus.BLOCKED) {
            return true;
        }
        return false;
    }
}
