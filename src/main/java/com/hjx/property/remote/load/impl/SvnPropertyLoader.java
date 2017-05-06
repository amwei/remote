package com.hjx.property.remote.load.impl;

import com.hjx.property.remote.Config;
import com.hjx.property.remote.load.PropertyLoader;
import com.hjx.property.remote.util.FileUtils;
import org.tmatesoft.svn.core.SVNDirEntry;
import org.tmatesoft.svn.core.SVNException;
import org.tmatesoft.svn.core.SVNNodeKind;
import org.tmatesoft.svn.core.SVNURL;
import org.tmatesoft.svn.core.auth.ISVNAuthenticationManager;
import org.tmatesoft.svn.core.internal.io.dav.DAVRepositoryFactory;
import org.tmatesoft.svn.core.internal.io.fs.FSRepositoryFactory;
import org.tmatesoft.svn.core.internal.io.svn.SVNRepositoryFactoryImpl;
import org.tmatesoft.svn.core.io.SVNRepository;
import org.tmatesoft.svn.core.io.SVNRepositoryFactory;
import org.tmatesoft.svn.core.wc.SVNWCUtil;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * Created by hjx on 2017/4/10 0010.
 */
public class SvnPropertyLoader implements PropertyLoader {

    static {
        // 对于使用http://和https：//
        DAVRepositoryFactory.setup();
        //对于使用svn：/ /和svn+xxx：/ /
        SVNRepositoryFactoryImpl.setup();
        //对于使用file://
        FSRepositoryFactory.setup();
    }

    private SVNRepository repository;

    private Config config;


    @Override
    public List<File> load(Config config) {
        this.config = config;
        String url = config.getUrl();
        String userName = config.getUserName();
        String passWord = config.getPassWord();
        String fileType = config.getSuf();
        String filePre = config.getPre();
        repository = login(url, userName, passWord);
        Collection collection = null;
        try {
            collection = repository.getDir("", -1, null, (Collection) null);
        } catch (SVNException e) {
            e.printStackTrace();
        }
        List<SVNDirEntry> entries = new ArrayList<SVNDirEntry>();
        Iterator<SVNDirEntry> iterator = collection.iterator();
        while (iterator.hasNext()) {
            SVNDirEntry entry = iterator.next();
            SVNNodeKind kind = entry.getKind();
            if (kind == SVNNodeKind.FILE) {
                String fileName = entry.getRelativePath();
                if (fileName.endsWith(fileType) && fileName.startsWith(filePre)) {
                    entries.add(entry);
                }
            }
        }
        return getFile(entries);
    }

    private List<File> getFile(List<SVNDirEntry> entries) {

        List<File> files = new ArrayList<File>();

        String url = config.getUrl();
        for (SVNDirEntry dirEntry : entries) {
            try {
                File cacheFile = cacheFile(dirEntry.getRelativePath());
                OutputStream outputStream = new FileOutputStream(cacheFile);
                String filepath = dirEntry.getRelativePath();
                long length = repository.getFile(filepath, dirEntry.getRevision(), null, outputStream);
                files.add(cacheFile);
                System.out.println(length);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (SVNException e) {
                e.printStackTrace();
            }
        }
        return files;
    }

    private File cacheFile(String fileName) {
        File file = new File(REPOSITORY_PATH +PATH_SEPARATOR_CHAR+config.getAppName()+ PATH_SEPARATOR_CHAR + fileName);
        FileUtils.createFile(file);
        return file;
    }

    private SVNRepository login(String url, String userName, String passWord) {
        SVNRepository repository = null;
        try {
            repository = SVNRepositoryFactory.create(SVNURL.parseURIEncoded(url));
            //身份验证
            ISVNAuthenticationManager authManager = SVNWCUtil.createDefaultAuthenticationManager(userName, passWord);
            //创建身份验证管理器
            repository.setAuthenticationManager(authManager);
        } catch (SVNException e) {
            e.printStackTrace();
        }
        return repository;
    }
}
