package dev.robit.config

import org.springframework.context.annotation.Bean
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.jdbc.datasource.DriverManagerDataSource
import org.springframework.orm.jpa.JpaTransactionManager
import org.springframework.orm.jpa.JpaVendorAdapter
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean
import org.springframework.orm.jpa.vendor.Database
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter
import java.util.Properties
import javax.persistence.EntityManagerFactory
import javax.sql.DataSource

@EnableJpaRepositories(basePackages = ["dev.robit", "dev.robit.repository"])
open class DataJpaConfig {

    @Bean
    fun dataSource(): DataSource {
        val dataSource = DriverManagerDataSource()
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver")
        dataSource.url = "jdbc:mysql://localhost:3306/test_db"
        dataSource.username = "username"
        dataSource.password = "password"
        return dataSource;
    }

    @Bean
    fun transactionManager(emf: EntityManagerFactory): JpaTransactionManager {
        return JpaTransactionManager(emf)
    }

    @Bean
    fun jpaVendorAdapter(): JpaVendorAdapter {
        val jpaVendorAdapter = HibernateJpaVendorAdapter()
        jpaVendorAdapter.setDatabase(Database.MYSQL)
        jpaVendorAdapter.setShowSql(true)
        jpaVendorAdapter.setGenerateDdl(true)
        return jpaVendorAdapter;
    }

    @Bean
    fun entityManagerFactory(): LocalContainerEntityManagerFactoryBean {
        val localContainerEntityManagerFactoryBean = LocalContainerEntityManagerFactoryBean();
        localContainerEntityManagerFactoryBean.dataSource = dataSource();
        val properties = Properties();
        properties.put("hibernate.hbm2ddl.auto", "update");
        localContainerEntityManagerFactoryBean.setJpaProperties(properties);
        localContainerEntityManagerFactoryBean.jpaVendorAdapter = jpaVendorAdapter();
        localContainerEntityManagerFactoryBean.setPackagesToScan("dev.robit");
        return localContainerEntityManagerFactoryBean;
    }
}
