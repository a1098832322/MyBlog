package org.sang.config;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter4;
import org.apache.commons.dbcp.BasicDataSource;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.http.MediaType;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import javax.annotation.Resource;
import javax.servlet.MultipartConfigElement;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by sang on 17-3-8.
 */
@Configuration
@EnableWebMvc
@ComponentScan("org.sang")
public class MyMVCConfig extends WebMvcConfigurerAdapter {
    @Resource
    DBConfig dbConfig;

    @Bean
    public InternalResourceViewResolver viewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/");
        viewResolver.setSuffix(".jsp");
        viewResolver.setViewClass(JstlView.class);
        return viewResolver;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //两个*表示以/assets开始的任意层级的路径都可以访问得到图片，如<img src="../assets/img/1.png">
        //一个*表示只可以访问assets目录下的图片文件
        registry.addResourceHandler("/static/**").addResourceLocations("/WEB-INF/static/");
    }

    @Bean
    public BasicDataSource dataSource() {
//        System.out.println("driver:"+dbConfig.driver);
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/blog?useUnicode=true&characterEncoding=UTF-8");
        dataSource.setUsername("root");
        dataSource.setPassword("root");
//        dataSource.setInitialSize(initialSize);
//        dataSource.setMaxActive(maxActive);
//        dataSource.setMaxIdle(maxIdle);
//        dataSource.setMinIdle(minIdle);
//        dataSource.setMaxWait(maxWait);
        return dataSource;
    }

    @Bean
    public SqlSessionFactoryBean sqlSessionFactoryBean() {
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource());
        try {
            sqlSessionFactoryBean.setMapperLocations(resolver.getResources("classpath:mapping/*.xml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sqlSessionFactoryBean;
    }

    @Bean
    public MapperScannerConfigurer mapperScannerConfigurer() {
        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
        mapperScannerConfigurer.setBasePackage("org.sang.dao");
        mapperScannerConfigurer.setSqlSessionFactoryBeanName("sqlSessionFactoryBean");
        return mapperScannerConfigurer;
    }

    @Bean
    public DataSourceTransactionManager transactionManager() {
        DataSourceTransactionManager dataSourceTransactionManager = new DataSourceTransactionManager();
        dataSourceTransactionManager.setDataSource(dataSource());
        return dataSourceTransactionManager;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginInterceptors());
    }

    @Bean
    public LoginInterceptors loginInterceptors() {
        return new LoginInterceptors();
    }

    //文件上传
    @Bean
    public MultipartResolver multipartResolver() {
        return new CommonsMultipartResolver();
    }

    @Bean
    public FastJsonHttpMessageConverter4 fastJsonHttpMessageConverter4() {
        FastJsonHttpMessageConverter4 converter = new FastJsonHttpMessageConverter4();
        List<MediaType> list = buildDefaultMediaTypes();
        converter.setSupportedMediaTypes(list);
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        fastJsonConfig.setDateFormat("yyyy-MM-dd HH:mm:ss");
        fastJsonConfig.setCharset(Charset.forName("utf-8"));
        //此处是可变参数可以配置多个
        fastJsonConfig.setSerializerFeatures(SerializerFeature.WriteDateUseDateFormat);//SerializerFeature.WriteMapNullValue此配置加上后会输出null
        converter.setFastJsonConfig(fastJsonConfig);
        return converter;
    }

    private static List<MediaType> buildDefaultMediaTypes() {
        List<MediaType> list = new ArrayList<MediaType>();
        list.add(MediaType.TEXT_HTML);
        list.add(MediaType.APPLICATION_JSON_UTF8);
        return list;
    }
}
