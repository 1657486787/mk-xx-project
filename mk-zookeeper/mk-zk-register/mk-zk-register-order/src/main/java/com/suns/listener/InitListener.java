/**
 * Project Name:mk-project <br>
 * Package Name:com.suns.listener <br>
 *
 * @author mk <br>
 * Date:2018-11-6 10:26 <br>
 */

package com.suns.listener;

import com.suns.utils.ZkNativeUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * ClassName: InitListener <br>
 * Description: zk原生客户端实现服务注册 <br>
 * @author mk
 * @Date 2018-11-6 10:26 <br>
 * @version
 */
public class InitListener implements ServletContextListener {

    @Value("${zookeeper.ip.port}")
    private String zookeeperConn;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        //一定要加这句，不然启动报错：Caused by: org.springframework.boot.web.server.WebServerException: Unable to start embedded Tomcat
        WebApplicationContextUtils.getRequiredWebApplicationContext(sce.getServletContext())
                .getAutowireCapableBeanFactory().autowireBean(this);
        ZkNativeUtil.init(zookeeperConn);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
