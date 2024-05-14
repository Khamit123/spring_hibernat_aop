package spring.config;


import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;


import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.io.FileInputStream;

import java.io.IOException;
import java.util.Properties;

@EnableWebMvc
@Configuration
@EnableAspectJAutoProxy
@ComponentScan(basePackages = "spring")
@EnableTransactionManagement
public class MyConfig {


    private final String rootPath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
    private final String appConfigPath = rootPath+"application.properties";


    @Bean
    public ViewResolver viewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/views/");
        viewResolver.setSuffix(".jsp");
        return viewResolver;
    }

    @Bean(value = "one")
    public DataSource dataSource() {
        ComboPooledDataSource cpds = new ComboPooledDataSource();

        try {
            System.out.println(appConfigPath);
            Properties appProps = new Properties();
            appProps.load(new FileInputStream(appConfigPath));
            cpds.setDriverClass(appProps.getProperty("jdbc.driver"));
            cpds.setJdbcUrl(appProps.getProperty("jdbc.url"));
            cpds.setUser(appProps.getProperty("jdbc.user"));
            cpds.setPassword(appProps.getProperty("jdbc.password"));
        } catch (PropertyVetoException | IOException e) {
            throw new RuntimeException(e);
        }
        return cpds;
    }

    @Bean()
    public DataSource dataSourceTwo() {
        ComboPooledDataSource cpds = new ComboPooledDataSource();


        try {
            System.out.println(appConfigPath);
            Properties appProps = new Properties();
            appProps.load(new FileInputStream(appConfigPath));
            cpds.setDriverClass(appProps.getProperty("jdbc.driver1"));
            cpds.setJdbcUrl(appProps.getProperty("jdbc.url1"));
            cpds.setUser(appProps.getProperty("jdbc.user1"));
            cpds.setPassword(appProps.getProperty("jdbc.password1"));
        } catch (PropertyVetoException | IOException e) {
            throw new RuntimeException(e);
        }
        return cpds;
    }

    @Bean
    public LocalSessionFactoryBean sf()  {
        LocalSessionFactoryBean lssf = new LocalSessionFactoryBean();
        lssf.setDataSource(dataSourceTwo());
        lssf.setPackagesToScan("spring.entity");
        Properties props = new Properties();
        props.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
        props.setProperty("hibernate.show_sql", "true");
        lssf.setHibernateProperties(props);
        return lssf;
    }

    @Bean
    public HibernateTransactionManager txManager() {
        HibernateTransactionManager txManager = new HibernateTransactionManager();
        txManager.setSessionFactory(sf().getObject());
        return txManager;
    }
}
