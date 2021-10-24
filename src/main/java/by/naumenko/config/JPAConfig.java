package by.naumenko.config;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.Properties;

@Configuration
@PropertySource("classpath:db.properties")
@EnableTransactionManagement
@ComponentScan("by.naumenko")
public class JPAConfig {

    @Value("${db.driver}")
    private String driverName;
    @Value("${db.url}")
    private String url;
    @Value("${db.username}")
    private String username;
    @Value("${db.password}")
    private String password;

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(driverName);
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        return dataSource;
    }

//    @Bean
//    public LocalSessionFactoryBean sessionFactory(Properties hibernateProperties) {
//        LocalSessionFactoryBean localSessionFactoryBean = new LocalSessionFactoryBean();
//        localSessionFactoryBean.setDataSource(dataSource());
//        localSessionFactoryBean.setPackagesToScan("by.naumenko.entity");
//        localSessionFactoryBean.setHibernateProperties(hibernateProperties);
//        return localSessionFactoryBean;
//    }

//    @Bean("entityManagerFactory")
//    public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean(Properties hibernateProperties) {
//        LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
//        factoryBean.setDataSource(dataSource());
//        factoryBean.setPackagesToScan("by.naumenko");
//        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
//        factoryBean.setJpaVendorAdapter(vendorAdapter);
//        factoryBean.setJpaProperties(hibernateProperties);
//        return factoryBean;
//    }

    @Bean
    public LocalSessionFactoryBean sessionFactory(Properties hibernateProperties){
        LocalSessionFactoryBean localSessionFactoryBean = new LocalSessionFactoryBean();
        localSessionFactoryBean.setDataSource(dataSource());
        localSessionFactoryBean.setPackagesToScan("by.naumenko.entity");
        localSessionFactoryBean.setHibernateProperties(hibernateProperties);
        return localSessionFactoryBean;
    }

    @Bean
    public Properties hibernateProperties(@Value("classpath:hibernate.properties") Resource resource) throws IOException {
        Properties properties = new Properties();
        properties.load(resource.getInputStream());

        return properties;
    }

//    @Bean
//    public JpaVendorAdapter jpaVendorAdapter() {
//        return new HibernateJpaVendorAdapter();
//    }

//    @Bean(name = "transactionManager")
//    public PlatformTransactionManager getTransactionManager() {
//        return new JpaTransactionManager(entityManagerFactoryBean().getObject());
//    }

    @Bean
    public HibernateTransactionManager transactionManager(SessionFactory sessionFactory) {
        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
        transactionManager.setSessionFactory(sessionFactory);
        return transactionManager;
    }

}
