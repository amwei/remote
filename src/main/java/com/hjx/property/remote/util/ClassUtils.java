package com.hjx.property.remote.util;

/**
 * Created by hjx on 2017/4/10 0010.
 */
public class ClassUtils {

    static private ClassLoader classLoader;

    public static ClassLoader getClassLoader() {
        if (classLoader == null) {
            classLoader = Thread.currentThread().getContextClassLoader();
        }
        return classLoader;
    }
}
