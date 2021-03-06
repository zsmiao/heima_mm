package xyz.itclay.heima_mm.utils;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.datasource.DataSourceFactory;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @author ZhangSenmiao
 * @date 2021/3/1 11:58
 **/
public class DruidDataSourceUtils implements DataSourceFactory {
    private Properties properties;

    @Override
    public void setProperties(Properties properties) {
        this.properties = properties;
    }

    @Override
    public DataSource getDataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(this.properties.getProperty("driver"));
        dataSource.setUrl(this.properties.getProperty("url"));
        dataSource.setUsername(this.properties.getProperty("username"));
        dataSource.setPassword(this.properties.getProperty("password"));

        try {
            dataSource.init();
        } catch (SQLException e) {
            System.out.println("获取数据源异常!");
            e.printStackTrace();
        }
        return dataSource;
    }
}
