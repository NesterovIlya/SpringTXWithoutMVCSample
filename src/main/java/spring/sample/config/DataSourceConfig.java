package spring.sample.config;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * @author Nesterov Ilya (nesterovilyan@gmail.com)
 */

@Configuration
public class DataSourceConfig {

    @Bean(name = "dataSource")
    public DataSource getDataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl("jdbc:postgresql://localhost:5433/SpringTXSample?characterEncoding=utf8");
        dataSource.setUsername("postgres");
        dataSource.setPassword("1234");

        return dataSource;
    }
}
