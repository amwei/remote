package com.hjx.property.remote.spring;

import com.hjx.property.remote.Config;
import com.hjx.property.remote.load.PropertyLoader;
import com.hjx.property.remote.load.impl.SvnPropertyLoader;
import com.hjx.property.remote.util.FileUtils;

import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

/**
 * Created by hjx on 2017/4/10 0010.
 */
public class RemoteLoadSpringSupport extends Config {


    @Override
    protected Properties mergeProperties() throws IOException {

        String pre = getPre();
        String suf = getSuf();
        String update = getUpdate();

        String url = getUrl();

        if (url == "" || url == null) {
            return super.mergeProperties();
        }

        Properties properties = new Properties();
        PropertyLoader loader = new SvnPropertyLoader();
        if (pre == null || "".equals(pre)) {
            super.setPre("");
        }
        if (suf == null || "".equals(suf)) {
            super.setSuf(".properties");
        }
        List<File> files = null;

        /**
         * 更新配置文件
         */
        if ("true".equalsIgnoreCase(update)) {
            FileUtils.removeFiles(PropertyLoader.REPOSITORY_PATH);
            files = loader.load(this);
        }
        // 读取现有的配置文件
        else {
            String localPath = PropertyLoader.REPOSITORY_PATH + PropertyLoader.PATH_SEPARATOR_CHAR + super.getAppName();
            File file = new File(localPath);
            files = Arrays.asList(file.listFiles());
        }

        for (File file : files) {
            InputStream stream = null;
            try {
                stream = new FileInputStream(file);
                properties.load(stream);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    stream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return properties;
    }


    public RemoteLoadSpringSupport() {
    }


}
