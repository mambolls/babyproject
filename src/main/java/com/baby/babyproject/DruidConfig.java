
/** 
* @Description: (用一句话描述该文件做什么) 
* @author heliang
* @date 2019-6-19 下午3:32:09 
* @version V2.1 
*/

package com.baby.babyproject;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import com.alibaba.druid.filter.Filter;
import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import com.alibaba.druid.wall.WallConfig;
import com.alibaba.druid.wall.WallFilter;


/** 
 * @ClassName: DruidConfig 
 * @Description: Druid 配置
 * @author heliang
 * @date 2019-6-19 下午3:32:09 
 * @version V2.1 * Update Logs: * Name: * Date: * Description: 初始化
 */
@Configuration
public class DruidConfig {
    
    private Logger logger = LoggerFactory.getLogger(DruidConfig.class);
    
    @Value("${spring.datasource.url}")
    private String dbUrl;
 
    @Value("${spring.datasource.type}")
    private String type;
 
    @Value("${loginUsername}")
    private String loginUsername;
 
    @Value("${loginPassword}")
    private String loginPassword;
 
    @Value("${spring.datasource.username}")
    private String username;
 
    @Value("${spring.datasource.password}")
    private String password;
 
    @Value("${spring.datasource.driver-class-name}")
    private String driverClassName;
 
    @Value("${spring.datasource.initialSize}")
    private int initialSize;
 
    @Value("${spring.datasource.minIdle}")
    private int minIdle;
 
    @Value("${spring.datasource.maxActive}")
    private int maxActive;
 
    @Value("${spring.datasource.maxWait}")
    private int maxWait;
 
    @Value("${spring.datasource.timeBetweenEvictionRunsMillis}")
    private int timeBetweenEvictionRunsMillis;
 
    @Value("${spring.datasource.minEvictableIdleTimeMillis}")
    private int minEvictableIdleTimeMillis;
 
    @Value("${spring.datasource.validationQuery}")
    private String validationQuery;
 
    @Value("${spring.datasource.testWhileIdle}")
    private boolean testWhileIdle;
 
    @Value("${spring.datasource.testOnBorrow}")
    private boolean testOnBorrow;
 
    @Value("${spring.datasource.testOnReturn}")
    private boolean testOnReturn;
 
    @Value("${spring.datasource.filters}")
    private String filters;
 
    @Value("${spring.datasource.logSlowSql}")
    private String logSlowSql;
 
    @Bean
    public ServletRegistrationBean druidServlet() {
        ServletRegistrationBean reg = new ServletRegistrationBean();
        reg.setServlet(new StatViewServlet());
        reg.addUrlMappings("/druid/*");
        reg.addInitParameter("loginUsername", loginUsername);
        reg.addInitParameter("loginPassword", loginPassword);
        reg.addInitParameter("logSlowSql", logSlowSql);
        return reg;
    }
 
    @Bean
    public FilterRegistrationBean filterRegistrationBean() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new WebStatFilter());
        filterRegistrationBean.addUrlPatterns("/*");
        filterRegistrationBean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
        filterRegistrationBean.addInitParameter("profileEnable", "true");
        return filterRegistrationBean;
    }
 
    @Bean
    public DataSource druidDataSource() {
        DruidDataSource datasource = new DruidDataSource();
        List<Filter> arrayList = new ArrayList<>();
        arrayList.add(wallFilter);
        datasource.setProxyFilters(arrayList);
        datasource.setUrl(dbUrl);
        datasource.setUsername(username);
        datasource.setPassword(password);
        datasource.setDriverClassName(driverClassName);
        datasource.setInitialSize(initialSize);
        datasource.setMinIdle(minIdle);
        datasource.setMaxActive(maxActive);
        //datasource.setDbType(type);
        datasource.setMaxWait(maxWait);
        datasource.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
        datasource.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
        datasource.setValidationQuery(validationQuery);
        datasource.setTestWhileIdle(testWhileIdle);
        datasource.setTestOnBorrow(testOnBorrow);
        datasource.setTestOnReturn(testOnReturn);
        try {
            datasource.setFilters(filters);
        } catch (SQLException e) {
            logger.error("druid configuration initialization system", e);
        }
        return datasource;
    }
    
    
    @Autowired
    WallFilter wallFilter;

    @Bean(name = "wallConfig")
    WallConfig wallFilterConfig(){
        WallConfig wc = new WallConfig ();
        wc.setMultiStatementAllow(true);
        return wc;
    }

    @Bean(name = "wallFilter")
    @DependsOn("wallConfig")
    WallFilter wallFilter(WallConfig wallConfig){
        WallFilter wfilter = new WallFilter ();
        wfilter.setConfig(wallConfig);
        return wfilter;
    }
    
}
