package spring.sample.app.services.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.sample.app.mappers.DataMapper;
import spring.sample.app.services.DataService;

import java.util.List;


/**
 * Created by ilya on 15.04.15.
 */

@Service()
public class MyBatisDataService implements DataService{

    Logger LOGGER = LoggerFactory.getLogger(MyBatisDataService.class);

    @Autowired
    DataMapper dataMapper;

    @Transactional("myBatisTransactionManager")
    @Override
    public List<String> getData() {
        LOGGER.info("Перед выборкой.");
        List<String> list = dataMapper.selectData();
        LOGGER.info("После выборки.");
        return list;
    }
}
