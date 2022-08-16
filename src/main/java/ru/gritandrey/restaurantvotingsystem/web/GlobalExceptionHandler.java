package ru.gritandrey.restaurantvotingsystem.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import ru.gritandrey.restaurantvotingsystem.util.SecurityUtil;
import ru.gritandrey.restaurantvotingsystem.util.validation.ValidationUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ModelAndView defaultErrorHandler(HttpServletRequest req, Exception e) throws Exception {
        log.error("Exception at request " + req.getRequestURL(), e);
        Throwable rootCause = ValidationUtil.getRootCause(e);

        HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        ModelAndView mav = new ModelAndView("exception",
                Map.of("exception", rootCause, "message", rootCause.toString(), "status", httpStatus));
        mav.setStatus(httpStatus);

        // Interceptor is not invoked, put userTo
        AuthUser authUser = SecurityUtil.safeGet();
        if (authUser != null) {
            mav.addObject("userTo", authUser.getUser());
        }
        return mav;
    }
}
