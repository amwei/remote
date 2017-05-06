package com.hjx.property.remote.util;

import java.io.File;
import java.io.IOException;

/**
 * Created by hjx on 2017/5/6 0006.
 */
public class FileUtils {

    /**
     * 移除文件夹内的文件
     *
     * @param path
     */
    public static void removeFiles(String path) {
        File file = new File(path);
        // 如果是一个目录
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            for (File f : files) {
                f.delete();
            }
        }
    }

    public static void createFile(File file) {
        if (file.exists()) {
            return;
        }
        if (!file.getParentFile().exists()) {
            if (!file.getParentFile().mkdirs()) {
                throw new RuntimeException("缓存配置文件失败");
            }
        }
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
