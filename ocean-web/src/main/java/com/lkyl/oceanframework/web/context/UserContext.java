package com.lkyl.oceanframework.web.context;

import com.lkyl.oceanframework.common.utils.principal.UserPrincipal;

/**
 * @author nicholas
 * @date 2023/05/15 21:24
 */
public class UserContext {
    private static ThreadLocal<UserPrincipal> userContext = new ThreadLocal<>();
    public static UserPrincipal getUser() {
        return userContext.get();
    }

    public static void setUser(UserPrincipal user) {
        userContext.set(user);
    }

    public static void removeCurrentUser() {
        userContext.remove();
    }
}
