package com.jess.wiki.util;

import java.io.Serializable;

/**
 * @Description 上下文 定义线程本地变量
 * 线程本地变量仅在当前线程有效，且线程之间不会互相干扰
 * @Author Jessica
 * @Version v
 * @Date 2021/9/5
 */
public class RequestContext implements Serializable {

    private static ThreadLocal<String> remoteAddr = new ThreadLocal<>();

    public static String getRemoteAddr() {
        return remoteAddr.get();
    }

    public static void setRemoteAddr(String remoteAddr) {
        RequestContext.remoteAddr.set(remoteAddr);
    }

}

