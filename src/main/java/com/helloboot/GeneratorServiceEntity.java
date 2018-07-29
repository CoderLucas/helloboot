package com.helloboot;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.DbType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import org.junit.Test;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by CoderLucas on 2018/7/29.
 */
public class GeneratorServiceEntity {

    @Test
    public void generateCode() {
        String packageName = "com.helloboot";
        generateByTables(packageName, "user");
    }

    void generateByTables(String packageName, String... tableNames) {
        // 数据源配置
        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        String dbUrl = "jdbc:mysql://localhost:3306/crawler?useUnicode=true&characterEncoding=utf-8&useSSL=false";
        dataSourceConfig.setDbType(DbType.MYSQL)
                .setUrl(dbUrl)
                .setUsername("root")
                .setPassword("Lu123456@")
                .setDriverName("com.mysql.jdbc.Driver");
        // 策略配置
        StrategyConfig strategyConfig = new StrategyConfig();
        strategyConfig
                .setCapitalMode(true)
                .setRestControllerStyle(true)
                .setEntityLombokModel(false)
                .setDbColumnUnderline(true)
                .setNaming(NamingStrategy.underline_to_camel)
                .setInclude(tableNames);//修改替换成你需要的表名，多个表名传数组
        // 全局配置
        GlobalConfig config = new GlobalConfig();
        config.setActiveRecord(false)
                .setAuthor("CoderLucas")
                .setOutputDir("/Users/mac/IdeaProjects/helloboot/src/main/java")   // 输出目录
                .setFileOverride(true)                                             // 是否覆盖文件
                .setActiveRecord(true)                                             // 开启 activeRecord 模式
                .setEnableCache(false)                                             // XML 二级缓存
                .setBaseResultMap(true)                                            // XML ResultMap
                .setBaseColumnList(true)                                           // XML columListƒ
                .setOpen(false)                                                    //生成后打开文件夹
                // 自定义文件命名，注意 %s 会自动填充表实体属性！
                .setMapperName("%sMapper")
                .setXmlName("%sMapper")
                .setServiceName("%sService")
                .setServiceImplName("%sServiceImpl")
                .setControllerName("%sController");

        // 注入自定义配置，可以在 VM 中使用 cfg.abc 设置的值
        InjectionConfig injectionConfig =   new InjectionConfig() {
            @Override
            public void initMap() {
                Map<String, Object> map = new HashMap<>();
                map.put("abc", this.getConfig().getGlobalConfig().getAuthor() + "-mp");
                this.setMap(map);
            }
        }.setFileOutConfigList(Collections.<FileOutConfig>singletonList(new FileOutConfig("/templates/mapper.xml.vm") {
            // 自定义输出文件目录
            @Override
            public String outputFile(TableInfo tableInfo) {
                return "/Users/mac/IdeaProjects/helloboot/src/main/resources/mapper/" + tableInfo.getEntityName() + "Mapper.xml";
            }
        }));

        new AutoGenerator().setGlobalConfig(config)
                .setDataSource(dataSourceConfig)
                .setStrategy(strategyConfig)
                .setPackageInfo(
                        new PackageConfig()
                                .setParent(packageName)
                                .setController("controller")                        // 这里是控制器包名，默认 web
                                .setEntity("domain")
                                .setMapper("dao")
                                .setService("service")
                                .setServiceImpl("service")
                ).setCfg(injectionConfig).setTemplate(
                // 关闭默认 xml 生成，调整生成 至 根目录
                new TemplateConfig().setXml(null)
        ).execute();

        System.err.println("执行成功");

    }
}
