remote

```

<bean id="remoteConfig" class="com.hjx.property.remote.spring.RemoteLoadSpringSupport">
	<property name="url" value="[你的配置文件在svn的地址]"/>
	<property name="userName" value="[用户名]"/>
	<property name="passWord" value="[密码]"/>
	<property name="appName" value="[项目名]"/>
	<!-- 是否每次更新 -->
	<property name="update" value="true"/>
</bean>

```