package com.hjx.property.remote;

import com.hjx.property.remote.load.PropertyLoader;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

import java.io.Serializable;

/**
 * 远程文件用户配置
 * <p>
 * Created by hjx on 2017/4/10 0010.
 */
public class Config extends PropertyPlaceholderConfigurer implements Serializable {

    /**
     * 用户名
     */
    private String userName;

    /**
     * 密码
     */
    private String passWord;

    /**
     * 地址
     */
    private String url;

    /**
     * 文件后缀
     */
    private String suf;

    /**
     * 文件前缀
     */
    private String pre;

    /**
     * 是否更新缓存
     */
    private String update;

    /**
     * 应用名称
     */
    private String appName;

    /**
     * 缓存的地址
     */
    private String cachePath;


    public Config() {
        setAppName("app_config");
        setUpdate("true");
        setPre("");
        setSuf(".properties");
        setCachePath(PropertyLoader.REPOSITORY_PATH);
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getSuf() {
        return suf;
    }

    public void setSuf(String suf) {
        this.suf = suf;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getPre() {
        return pre;
    }

    public void setPre(String pre) {
        this.pre = pre;
    }

    public String getUpdate() {
        return update;
    }

    public void setUpdate(String update) {
        this.update = update;
    }

    public String getCachePath() {
        return cachePath;
    }

    public void setCachePath(String cachePath) {
        this.cachePath = cachePath;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("   \"userName\"=\"").append(userName).append('\"');
        sb.append(",    \"passWord\"=\"").append(passWord).append('\"');
        sb.append(",    \"url\"=\"").append(url).append('\"');
        sb.append(",    \"suf\"=\"").append(suf).append('\"');
        sb.append(",    \"pre\"=\"").append(pre).append('\"');
        sb.append(",    \"update\"=\"").append(update).append('\"');
        sb.append(",    \"appName\"=\"").append(appName).append('\"');
        sb.append(",    \"cachePath\"=\"").append(cachePath).append('\"');
        sb.append('}');
        return sb.toString();
    }
}
