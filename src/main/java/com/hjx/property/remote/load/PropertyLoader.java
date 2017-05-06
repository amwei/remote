package com.hjx.property.remote.load;

import com.hjx.property.remote.Config;
import com.hjx.property.remote.util.PathUtils;

import java.io.File;
import java.util.List;

/**
 * 读取远程配置
 *
 * Created by hjx on 2017/4/10 0010.
 */
public interface PropertyLoader {

    char PATH_SEPARATOR_CHAR = '/';

    String REPOSITORY_PATH = PathUtils.getUserHome() + PATH_SEPARATOR_CHAR + "remot_config";

    List<File> load(Config config);

}
