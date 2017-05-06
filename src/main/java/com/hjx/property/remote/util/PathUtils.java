package com.hjx.property.remote.util;

/**
 * Created by hjx on 2017/4/10 0010.
 */
public class PathUtils {

    private static String projectPath;


    public static String getClassPath() {
        String classPath = ClassUtils.getClassLoader().getResource("").getPath();
        return classPath;
    }

    public static String getProjectPath() {

        return System.getProperty("user.dir");
    }

    public static String getUserHome() {

        return System.getProperty("user.home");
    }

}
