package com.lkyl.oceanframework.common.utils.interceptor;

import com.lkyl.oceanframework.common.utils.context.PageContext;
import com.lkyl.oceanframework.common.utils.page.PageArgs;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

public class PageArgsHandlerInterceptor implements HandlerInterceptor {
    
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // Extract parameter from URL
        String pageNumStr = request.getParameter("pageNum");
        String pageSizeStr = request.getParameter("pageSize");
        if (StringUtils.isBlank(pageNumStr) && StringUtils.isBlank(pageSizeStr)) {
            return true;
        }
        PageArgs pageArgs = getPageArgs(pageNumStr, pageSizeStr);
        PageContext.setPageArgs(pageArgs);
        // Returning true allows the request to proceed to the controller
        return true;
    }

    private static PageArgs getPageArgs(String pageNumStr, String pageSizeStr) {
        PageArgs pageArgs = new PageArgs();
        try {
            if (pageNumStr != null) {
                pageArgs.setPageNum(Integer.parseInt(pageNumStr));
            }
            if (pageSizeStr != null) {
                pageArgs.setPageSize(Integer.parseInt(pageSizeStr));
            }
        } catch (NumberFormatException e) {
            // Handle invalid number format as needed (e.g., set default values or throw an exception)
            pageArgs.setPageNum(1);   // Default page number
            pageArgs.setPageSize(10); // Default page size
        }
        return pageArgs;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        // Optional: Post-processing after the controller executes
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        // Optional: After completion, like for cleaning up resources
        if (Objects.nonNull(PageContext.getPageArgs())) {
            PageContext.removePageArgs();
        }
    }
}