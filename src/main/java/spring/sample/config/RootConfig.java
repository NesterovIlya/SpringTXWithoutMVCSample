package spring.sample.config;


import org.apache.ibatis.session.AutoMappingBehavior;
import org.apache.ibatis.session.SqlSessionFactory;
import org.hibernate.SessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import spring.sample.mybatis.annotations.Mapper;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * @author Nesterov Ilya (nesterovilyan@gmail.com)
 */

@Configuration
@ComponentScan( basePackages = {"spring.sample.app", "spring.sample.config"})
@MapperScan(value = "spring.sample.app.mappers",
        annotationClass = Mapper.class)
@EnableTransactionManagement
public class RootConfig {


    @Autowired
    @Bean(name = "sessionFactory")
    public SessionFactory getSessionFactory(DataSource dataSource) {
        LocalSessionFactoryBuilder sessionBuilder = new LocalSessionFactoryBuilder(dataSource);
        sessionBuilder.scanPackages("ru.fs_info");
        sessionBuilder.addProperties(getHibernateProperties());
        return sessionBuilder.buildSessionFactory();
    }

    private Properties getHibernateProperties() {
        Properties properties = new Properties();
        properties.put("hibernate.show_sql", "true");
        properties.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQL9Dialect");
//        properties.put("hibernate.format_sql","true");
        return properties;
    }

    @Autowired
    @Bean(name = "hibernateTransactionManager")
    public HibernateTransactionManager getTransactionManager(SessionFactory sessionFactory) {
        return new HibernateTransactionManager(sessionFactory);
    }


    @Autowired
    @Bean(name = "myBatisSessionFactory")
    public SqlSessionFactory getMyBatisSessionFactory(DataSource dataSource) throws Exception{
        try {
            SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();

            factoryBean.setDataSource(dataSource);


            factoryBean.setTypeAliases(new Class[]{

            });

            factoryBean.setMapperLocations(new Resource[]{

            });

            SqlSessionFactory sessionFactory = factoryBean.getObject();

            org.apache.ibatis.session.Configuration configuration = sessionFactory.getConfiguration();
            configuration.setAutoMappingBehavior(AutoMappingBehavior.NONE);
            return sessionFactory;
        }
        catch(Exception ex){
            throw ex;
        }
    }

    @Autowired
    @Bean(name = "myBatisTransactionManager")
    public DataSourceTransactionManager getMyBatisTransactionManager(DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }
}
