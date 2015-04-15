package spring.sample.app;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;
import spring.sample.app.services.DataService;
import spring.sample.config.DataSourceConfig;
import spring.sample.config.RootConfig;
import java.util.List;

/**
 * Created by ilya on 15.04.15.
 */

public class Main {

    public static void main(String[] args) {

        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(RootConfig.class, DataSourceConfig.class);

        Main mainClass = new Main();

        DataService dataService = (DataService)ctx.getBean("myBatisDataService");
        List<String> data = dataService.getData();
        data.forEach(System.out::println);
    }

}
