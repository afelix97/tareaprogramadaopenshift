package mx.com.aforecoppel.registromovilguardardatos.config;

import javax.sql.DataSource;
import mx.com.aforecoppel.dao.ExcepcionesDAO;
import mx.com.aforecoppel.dao.ExcepcionesImpl;
import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * Clase con configuración de Base de Datos del microservicio.
 *
 * @author adrian
 */
@Configuration
@Profile({"develop", "default"})
public class DatabasesConfig {

    @Autowired
    PropertiesConfiguration properties;

    @Bean(name = "bitacoraAforedigitalDataSource")
    public DataSource bitacoraAforedigitalDataSource() {
        BasicDataSource bitacora = new BasicDataSource();
        bitacora.setDriverClassName(properties.getBitacoraDriverClassName());
        bitacora.setUrl(properties.getBitacoraUrl());
        bitacora.setUsername(properties.getBitacoraUsername());
        bitacora.setPassword(properties.getBitacoraPassword());
        bitacora.setInitialSize(properties.getBitacoraInitialSize());
        bitacora.setMaxActive(properties.getBitacoraMaxActive());
        bitacora.setMaxIdle(properties.getBitacoraMaxIdle());
        bitacora.setMinIdle(properties.getBitacoraMinIdle());
        bitacora.setTimeBetweenEvictionRunsMillis(properties.getBitacoraTimeBetweenEvictionRunsMillis());
        bitacora.setMinEvictableIdleTimeMillis(properties.getBitacoraMinEvictableIdleTimeMillis());
        bitacora.setTestOnBorrow(properties.getBitacoraTestOnBorrow());
        bitacora.setValidationQuery(properties.getBitacoraValidationQuery());
        return bitacora;
    }

    @Bean(name = "aforeGlobalDataSource")
    public DataSource aforeGlobalDataSource() {
        BasicDataSource global = new BasicDataSource();
        global.setDriverClassName(properties.getAforeDriverClassName());
        global.setUrl(properties.getAforeUrl());
        global.setUsername(properties.getAforeUsername());
        global.setPassword(properties.getAforePassword());
        global.setInitialSize(properties.getAforeInitialSize());
        global.setMaxActive(properties.getAforeMaxActive());
        global.setMaxIdle(properties.getAforeMaxIdle());
        global.setMinIdle(properties.getAforeMinIdle());
        global.setTimeBetweenEvictionRunsMillis(properties.getAforeTimeBetweenEvictionRunsMillis());
        global.setMinEvictableIdleTimeMillis(properties.getAforeMinEvictableIdleTimeMillis());
        global.setTestOnBorrow(properties.getAforeTestOnBorrow());
        global.setValidationQuery(properties.getAforeValidationQuery());
        return global;
    }
    
     @Bean(name = "aforeDigitalDataSource")
    public DataSource aforeDigitalDataSource() {
        BasicDataSource digital = new BasicDataSource();
        digital.setDriverClassName(properties.getDigitalDriverClassName());
        digital.setUrl(properties.getDigitalUrl());
        digital.setUsername(properties.getDigitalUsername());
        digital.setPassword(properties.getDigitalPassword());
        digital.setInitialSize(properties.getDigitalInitialSize());
        digital.setMaxActive(properties.getDigitalMaxActive());
        digital.setMaxIdle(properties.getDigitalMaxIdle());
        digital.setMinIdle(properties.getDigitalMinIdle());
        digital.setTimeBetweenEvictionRunsMillis(properties.getDigitalTimeBetweenEvictionRunsMillis());
        digital.setMinEvictableIdleTimeMillis(properties.getDigitalMinEvictableIdleTimeMillis());
        digital.setTestOnBorrow(properties.getDigitalTestOnBorrow());
        digital.setValidationQuery(properties.getDigitalValidationQuery());
        return digital;
    }
    
        @Bean
    public ExcepcionesDAO excepcionesDAO(){
        return new ExcepcionesImpl(bitacoraAforedigitalDataSource());
    }


}
