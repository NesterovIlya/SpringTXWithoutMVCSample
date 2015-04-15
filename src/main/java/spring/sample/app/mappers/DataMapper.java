package spring.sample.app.mappers;

import org.apache.ibatis.annotations.Select;
import spring.sample.mybatis.annotations.Mapper;

import java.util.List;

/**
 * Created by ilya on 15.04.15.
 */
@Mapper
public interface DataMapper {

    @Select("SELECT text FROM data ORDER BY id")
    List<String> selectData();
}
