package com.hjx.property.remote.util;

import com.hjx.property.remote.Config;
import org.eclipse.jgit.api.CloneCommand;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.transport.CredentialsProvider;
import org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider;

import java.io.File;

/**
 * Created by hjx on 2017/4/10 0010.
 */
public class RepositoryUtils {

    /**
     * 创建本地仓库
     * @param dir
     * @return
     */
    public static boolean createRepository(Config user, File dir) {
        String url = user.getUrl();
        String userName = user.getUserName();
        String passWord = user.getPassWord();
        System.out.println("url:" + user.getUrl() + "。path:" + dir.getPath());
        //  设置 用户名和密码
        CredentialsProvider provider = new UsernamePasswordCredentialsProvider(userName, passWord);
        // 创建一个 git 仓库
        Git git = null;
        CloneCommand cloneCommand = Git.cloneRepository();
        cloneCommand.setURI(url);
        cloneCommand.setCredentialsProvider(provider);
        cloneCommand.setDirectory(dir);
        try {
            git = cloneCommand.call();
        } catch (GitAPIException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

}
