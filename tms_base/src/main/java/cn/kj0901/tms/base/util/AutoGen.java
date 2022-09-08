package cn.kj0901.tms.base.util;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

/**
 * KJ200901
 *
 * @Description : 自动生成代码
 * @Author : Aedes
 * @Date: 2021/4/5 15:13
 */
public class AutoGen {
    public static void main(String[] args) {
        //1.创建一个生成器
        AutoGenerator autoGenerator = new AutoGenerator();

        //2.配置生成器全局信息
        GlobalConfig globalConfig = new GlobalConfig();
        globalConfig.setAuthor("kj0901");  //作者
        globalConfig.setMapperName("%sDao"); //mapper接口的后缀
        globalConfig.setServiceName("%sService");//Service接口的后缀
        globalConfig.setServiceImplName("%sServiceImpl");//Serviceimpl接口的后缀
        globalConfig.setControllerName("%sController");//Controller接口的后缀
        globalConfig.setOutputDir("e:\\myp"); //导出位置
        globalConfig.setFileOverride(true);  //是否覆盖
        autoGenerator.setGlobalConfig(globalConfig);

        //3.数据源配置
        DataSourceConfig dc = new DataSourceConfig();
        dc.setDbType(DbType.MYSQL);
        dc.setDriverName("com.mysql.cj.jdbc.Driver");
        dc.setUrl("jdbc:mysql://localhost:3306/tms?useUnicode=true&characterEncoding=utf-8&useSSL=false&serverTimezone=UTC");
        dc.setUsername("root");
        dc.setPassword("123456");
        autoGenerator.setDataSource(dc);


        //4.生成策略配置
        StrategyConfig sc = new StrategyConfig();
        sc.setTablePrefix("tms");  //表前缀
        sc.setNaming(NamingStrategy.underline_to_camel); //命名方式-下划线转驼峰
        sc.setEntityLombokModel(true); //配置Lombok
        sc.setRestControllerStyle(true); //配置RestController
        autoGenerator.setStrategy(sc);

        //5.包的生成策略
        PackageConfig pc = new PackageConfig();
        pc.setParent("cn.kj0901.tms");
        pc.setEntity("base.entity");
        pc.setMapper("base.dao");
        pc.setController("driver.controller");
        pc.setService("driver.service");
        pc.setServiceImpl("driver.service.impl");
        autoGenerator.setPackageInfo(pc);

        //5.执行
        autoGenerator.execute();

    }
}

