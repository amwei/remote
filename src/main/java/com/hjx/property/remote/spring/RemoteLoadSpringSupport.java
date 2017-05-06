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
        super.logger.info("开始加载配置。。。");
        // 调用spring
        Properties properties = super.mergeProperties();
        // 没有配置url 的情况下 跳过加载svn配置
        String url = getUrl();
        if (url == null || "".equals(url)) {
            return properties;
        }

        PropertyLoader loader = new SvnPropertyLoader();
        List<File> files = null;
        //更新配置文件
        if ("true".equalsIgnoreCase(getUpdate())) {
            FileUtils.removeFiles(getCachePath());
            files = loader.load(this);
        }
        // 读取现有的配置文件
        else {
            String localPath = getCachePath() + PropertyLoader.PATH_SEPARATOR_CHAR + super.getAppName();
            File file = new File(localPath);
            files = Arrays.asList(file.listFiles());
        }
        //合并
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
