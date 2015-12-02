package app;


import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.interceptor.TransactionInterceptor;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
@MapperScan("app.mapper")
public class DataConfig {

   @Bean
   public SqlSessionFactoryBean sqlSessionFactory(DataSource dataSource) throws Exception{
       SqlSessionFactoryBean factory = new SqlSessionFactoryBean();
       factory.setDataSource(dataSource);
       factory.setConfigLocation(new ClassPathResource("/mybatis-config.xml"));
       return factory;
   }

   @Bean
   public TransactionInterceptor txAdvice(DataSource dataSource) {
       DataSourceTransactionManager txManager = new DataSourceTransactionManager(dataSource);
       Properties attributes = new Properties();
       attributes.setProperty("*", "PROPAGATION_REQUIRED");
       TransactionInterceptor txAdvice = new TransactionInterceptor(txManager, attributes);
       return txAdvice;
   }

//   @Bean
//   @Autowired
//   protected PlatformTransactionManager createTransactionManager(DataSource dataSource) {
//       return new DataSourceTransactionManager(dataSource);
//   }

}
