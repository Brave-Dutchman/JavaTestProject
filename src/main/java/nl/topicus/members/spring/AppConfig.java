package nl.topicus.members.spring;

import org.springframework.context.annotation.*;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * Created by Thijs Reeringh on 4/26/2016.
 */
@Configuration
@EnableTransactionManagement
@ComponentScan("nl.topicus.members.domain")
public class AppConfig {
    @Bean
    public LocalSessionFactoryBean sessionFactory() {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();

        sessionFactory.setHibernateProperties(HibernateProperties());
        sessionFactory.setDataSource(DataSource());

        sessionFactory.setPackagesToScan("nl.topicus.members.domain");

        return sessionFactory;
    }

    @Bean
    public PlatformTransactionManager TransactionalManager() {
        DataSourceTransactionManager trans = new DataSourceTransactionManager();
        trans.setDataSource(DataSource());

        return trans;
    }

    public DataSource DataSource() {
        DriverManagerDataSource driver = new DriverManagerDataSource();

        driver.setDriverClassName("org.postgresql.Driver");
        driver.setUrl("jdbc:postgresql://localhost/test");
        driver.setUsername("postgres");
        driver.setPassword("@Password1");

        return driver;
    }

    @Bean
    public Properties HibernateProperties() {
        Properties properties = new Properties();
        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
        properties.setProperty("hibernate.hbm2ddl.auto", "save"); //TODO remove in production
        properties.setProperty("hibernate.show_sql", "true");

        return properties;
    }
}