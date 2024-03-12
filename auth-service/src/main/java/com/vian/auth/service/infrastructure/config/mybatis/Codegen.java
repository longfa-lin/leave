package com.vian.auth.service.infrastructure.config.mybatis;

import com.mybatisflex.codegen.Generator;
import com.mybatisflex.codegen.config.ColumnConfig;
import com.mybatisflex.codegen.config.EntityConfig;
import com.mybatisflex.codegen.config.GlobalConfig;
import com.mybatisflex.core.BaseMapper;
import com.mybatisflex.core.service.IService;
import com.mybatisflex.spring.service.impl.ServiceImpl;
import com.zaxxer.hikari.HikariDataSource;

import java.io.Serializable;

public class Codegen {

    public static void main(String[] args) {
        //配置数据源
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setJdbcUrl("jdbc:mysql://10.0.148.133:30346/db_leave?useUnicode=true&characterEncoding=utf-8&serverTimezone=Asia/Shanghai&tinyInt1isBit=false");
        dataSource.setUsername("root");
        dataSource.setPassword("1qazxsw2");

        //创建配置内容，两种风格都可以。
        GlobalConfig globalConfig = createGlobalConfigUseStyle1();
        //GlobalConfig globalConfig = createGlobalConfigUseStyle2();

        //通过 datasource 和 globalConfig 创建代码生成器
        Generator generator = new Generator(dataSource, globalConfig);

        //生成代码
        generator.generate();
    }

    public static GlobalConfig createGlobalConfigUseStyle1() {
        //创建配置内容
        GlobalConfig globalConfig = new GlobalConfig();

        //设置根包
        globalConfig.getPackageConfig()
                .setSourceDir(System.getProperty("user.dir") + "/auth-service/src/main/java")
                .setBasePackage("com.vian.auth.service")
                .setEntityPackage(globalConfig.getBasePackage() + ".domain.user.repository.po")
                .setMapperPackage(globalConfig.getBasePackage() + ".domain.user.repository.mapper")
                .setServicePackage(globalConfig.getBasePackage() + ".domain.user.repository.facade")
                .setServiceImplPackage(globalConfig.getBasePackage() + "domain.user.repository.persistence")
                .setControllerPackage(globalConfig.getBasePackage() + "interfaces.facade")
        ;

        //设置表前缀和只生成哪些表
        globalConfig.setTablePrefix("sys_");
        globalConfig.setGenerateTable("sys_user");

        entityConfig(globalConfig);
        mapperConfig(globalConfig);
        serviceConfig(globalConfig);
        serviceImplConfig(globalConfig);
        controllerConfig(globalConfig);

        //设置生成 entity 并启用 Lombok
        globalConfig.setEntityGenerateEnable(true);
        globalConfig.setEntityWithLombok(true);
        //设置项目的JDK版本，项目的JDK为14及以上时建议设置该项，小于14则可以不设置
        globalConfig.setEntityJdkVersion(17);

        //设置生成 mapper
        globalConfig.setMapperGenerateEnable(true);
        globalConfig.setServiceGenerateEnable(true);
        globalConfig.setServiceImplGenerateEnable(true);
        globalConfig.setControllerGenerateEnable(true);

        //可以单独配置某个列
        ColumnConfig columnConfig = new ColumnConfig();
        columnConfig.setColumnName("tenant_id");
        columnConfig.setLarge(true);
        columnConfig.setVersion(true);
        globalConfig.setColumnConfig("tb_account", columnConfig);

        return globalConfig;
    }

    public static GlobalConfig entityConfig(GlobalConfig globalConfig) {
        globalConfig.getEntityConfig()
                .setWithLombok(true)
//                .setClassPrefix("My")
                .setClassSuffix("PO")
                .setJdkVersion(17)
                .setImplInterfaces(Serializable.class)
                //是否覆盖之前生成的文件
                .setOverwriteEnable(true)
                .setWithSwagger(true)
                .setSwaggerVersion(EntityConfig.SwaggerVersion.FOX)
//                .setSuperClass(BaseEntity.class)
        ;
        return globalConfig;
    }

    public static GlobalConfig mapperConfig(GlobalConfig globalConfig) {
        globalConfig.getMapperConfig()
                .setClassSuffix("Dao")
                .setOverwriteEnable(true)
                .setSuperClass(BaseMapper.class)
        ;
        return globalConfig;
    }

    public static GlobalConfig serviceConfig(GlobalConfig globalConfig) {
        globalConfig.getServiceConfig()
                .setClassSuffix("Repository")
                .setOverwriteEnable(true)
                .setSuperClass(IService.class)
        ;
        return globalConfig;
    }

    public static GlobalConfig serviceImplConfig(GlobalConfig globalConfig) {
        globalConfig.getServiceImplConfig()
                .setClassSuffix("RepositoryImpl")
                .setOverwriteEnable(true)
                .setSuperClass(ServiceImpl.class)
        ;
        return globalConfig;
    }

    public static GlobalConfig controllerConfig(GlobalConfig globalConfig) {
        globalConfig.getControllerConfig()
                .setClassSuffix("Api")
                .setOverwriteEnable(true)
                .setRestStyle(true)
        ;
        return globalConfig;
    }


    public static GlobalConfig createGlobalConfigUseStyle2() {
        //创建配置内容
        GlobalConfig globalConfig = new GlobalConfig();

        //设置根包
        globalConfig.getPackageConfig()
                .setBasePackage("com.test");

        //设置表前缀和只生成哪些表，setGenerateTable 未配置时，生成所有表
        globalConfig.getStrategyConfig()
                .setTablePrefix("tb_")
                .setGenerateTable("tb_account", "tb_account_session");

        //设置生成 entity 并启用 Lombok
        globalConfig.enableEntity()
                .setWithLombok(true)
                .setJdkVersion(17);

        //设置生成 mapper
        globalConfig.enableMapper();

        //可以单独配置某个列
        ColumnConfig columnConfig = new ColumnConfig();
        columnConfig.setColumnName("tenant_id");
        columnConfig.setLarge(true);
        columnConfig.setVersion(true);
        globalConfig.getStrategyConfig()
                .setColumnConfig("tb_account", columnConfig);

        return globalConfig;
    }
}