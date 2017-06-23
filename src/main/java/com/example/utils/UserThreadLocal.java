package com.example.utils;

import com.example.model.SysUser;

/**
 * 把用户信息保存到本地线程
 * @author leo
 *
 */
public class UserThreadLocal {

    private static final ThreadLocal<SysUser> THREAD_LOCAL = new ThreadLocal<SysUser>();

    public static void set(SysUser user) {
        THREAD_LOCAL.set(user);
    }

    public static SysUser get() {
        return THREAD_LOCAL.get();
    }
}
