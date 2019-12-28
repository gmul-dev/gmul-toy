package com.gmul.toy.config;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.orm.jpa.EntityManagerFactoryBuilderCustomizer;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateProperties;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.persistenceunit.PersistenceUnitManager;
import org.springframework.orm.jpa.vendor.AbstractJpaVendorAdapter;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@EnableJpaAuditing
@EnableTransactionManagement
@EnableConfigurationProperties({JpaProperties.class, HibernateProperties.class})
public class HibernateJpaConfig {

    private final JpaProperties jpaProperties;
    private final ObjectProvider<PersistenceUnitManager> persistenceUnitManager;
    private final ObjectProvider<EntityManagerFactoryBuilderCustomizer> customizer;
    private final DataSource dataSource;

    public HibernateJpaConfig(JpaProperties jpaProperties,
                              ObjectProvider<PersistenceUnitManager> persistenceUnitManager, ObjectProvider<EntityManagerFactoryBuilderCustomizer> customizer, DataSource dataSource) {
        this.jpaProperties = jpaProperties;
        this.persistenceUnitManager = persistenceUnitManager;
        this.customizer = customizer;
        this.dataSource = dataSource;
    }

    @Bean
    public JpaVendorAdapter jpaVendorAdapter(DataSource dataSource) {
        AbstractJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
        adapter.setShowSql(jpaProperties.isShowSql());
        adapter.setDatabase(jpaProperties.getDatabase());
        adapter.setDatabasePlatform(jpaProperties.getDatabasePlatform());
        adapter.setGenerateDdl(jpaProperties.isGenerateDdl());
        return adapter;
    }

    private EntityManagerFactoryBuilder entityManagerFactoryBuilder(JpaVendorAdapter jpaVendorAdapter){
        EntityManagerFactoryBuilder builder = new EntityManagerFactoryBuilder(jpaVendorAdapter, jpaProperties.getProperties(), persistenceUnitManager.getIfAvailable());
        customizer.orderedStream().forEach(customizer -> customizer.customize(builder));
        return builder;
    }

//    @Bean
//    public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean(EntityManagerFactoryBuilder builder) {
//        return
//    }
}
