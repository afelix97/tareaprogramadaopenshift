package mx.com.aforecoppel.registromovilguardardatos.config;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.RedeliveryPolicy;
import org.apache.activemq.camel.component.ActiveMQComponent;
import org.apache.activemq.pool.PooledConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.Arrays;
import org.apache.camel.spring.spi.SpringTransactionPolicy;
import org.springframework.context.annotation.Profile;
import org.springframework.jms.connection.JmsTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

/**
 *
 * @author adrian
 */
@Configuration
@Profile({"develop", "default"})
public class JmsConfig {

    @Autowired
    PropertiesConfiguration properties;

    @Bean
    public RedeliveryPolicy redeliveryPolicy() {
        RedeliveryPolicy redpol = new RedeliveryPolicy();
        redpol.setInitialRedeliveryDelay(60000);
        redpol.setMaximumRedeliveries(6);
        redpol.setQueue("*");
        redpol.setRedeliveryDelay(15000);
        return redpol;
    }

    @Bean
    public ActiveMQConnectionFactory jmsConnectionFactory() {
        ActiveMQConnectionFactory jmsConnFactory = new ActiveMQConnectionFactory(properties.getJmsUsername(), properties.getJmsPassword(), properties.getJmsBrokerUrl());
        jmsConnFactory.setRedeliveryPolicy(redeliveryPolicy());
        jmsConnFactory.setAlwaysSessionAsync(false);
        jmsConnFactory.setTrustedPackages(Arrays.asList("mx.com.aforecoppel.registromovilguardardatos"));
        return jmsConnFactory;
    }

    @Bean(initMethod="start",destroyMethod="stop")
    public PooledConnectionFactory pooledConnectionFactoryMessages() {
        PooledConnectionFactory pool = new PooledConnectionFactory(jmsConnectionFactory());
        pool.setMaxConnections(properties.getJmsPoolMaxConnections()); 
        pool.setMaximumActiveSessionPerConnection(properties.getJmsMaximumActiveSessionPerConnection()); 
        return pool;
    }

    @Bean(name="amqOutput")
    public ActiveMQComponent amqOutput() {
        ActiveMQComponent amqComponent = ActiveMQComponent.activeMQComponent();
        amqComponent.setConnectionFactory(pooledConnectionFactoryMessages());
        amqComponent.setTransacted(true);
        amqComponent.setLazyCreateTransactionManager(false);
        return amqComponent;
    }
    
    @Bean(name="txPolicyName")
    public SpringTransactionPolicy txPolicyName(){
        SpringTransactionPolicy stp = new SpringTransactionPolicy();
        stp.setTransactionManager(jmsTransactionManager2());
        return stp;
    }
    
    @Bean
    public ActiveMQConnectionFactory jmsConnectionTransactedFactory() {
        ActiveMQConnectionFactory jmsConnFactory = new ActiveMQConnectionFactory(properties.getJmsUsername(), properties.getJmsPassword(), properties.getJmsBrokerUrl());
        jmsConnFactory.setTrustedPackages(Arrays.asList("mx.com.aforecoppel.registromovilguardardatos"));
        return jmsConnFactory;
    }
    
    @Bean
    public PlatformTransactionManager jmsTransactionManager2() {
        JmsTransactionManager jmsTransactionManager = new JmsTransactionManager();
        jmsTransactionManager.setConnectionFactory(pooledConnectionFactoryMessages());
        jmsTransactionManager.setValidateExistingTransaction(true);
        return jmsTransactionManager;
    }
    
    @Bean
    public PlatformTransactionManager jmsTransactionManager() {
        JmsTransactionManager jmsTransactionManager = new JmsTransactionManager();
        jmsTransactionManager.setConnectionFactory(jmsConnectionTransactedFactory());
        jmsTransactionManager.setValidateExistingTransaction(true);
        return jmsTransactionManager;
    }
    
    @Bean(name="amqInput")
    public ActiveMQComponent amqInput() {
        ActiveMQComponent amqComponent = ActiveMQComponent.activeMQComponent();
        amqComponent.setConnectionFactory(jmsConnectionTransactedFactory());
        amqComponent.setTransacted(true);
        amqComponent.setLazyCreateTransactionManager(false);
        amqComponent.setConcurrentConsumers(properties.getJmsConcurrentConsumers());
        
        return amqComponent;
    }
}