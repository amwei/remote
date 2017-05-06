package com.hjx.property.remote.load.impl;

import com.hjx.property.remote.Config;
import com.hjx.property.remote.load.PropertyLoader;
import com.hjx.property.remote.util.PathUtils;
import com.hjx.property.remote.util.RepositoryUtils;

import java.io.File;
import java.util.List;

/**
 * Created by hjx on 2017/4/10 0010.
 */
public class GitPropertyLoader implements PropertyLoader {

    private static String REPOSITORY_PATH = "remot_config";

    static {
        REPOSITORY_PATH = PathUtils.getUserHome() + "\\" + REPOSITORY_PATH;
    }

    @Override
    public List<File> load(Config user) {
        File repositoryFile = new File(REPOSITORY_PATH);
        // 检查仓库是否创建 springBoot-study/pom.xml
//        if (! repositoryFile.exists()) {
        RepositoryUtils.createRepository(user, repositoryFile);
//        }

        return null;
    }

}
