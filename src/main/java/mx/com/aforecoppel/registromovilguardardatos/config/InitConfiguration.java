package mx.com.aforecoppel.registromovilguardardatos.config;

import java.net.UnknownHostException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import mx.com.aforecoppel.dao.ExcepcionesDAO;
import mx.com.aforecoppel.dto.ConfiguracionAlertaDTO;
import mx.com.aforecoppel.dto.ServicioDTO;
import mx.com.aforecoppel.exceptionsmodel.utils.ServerInfo;
import mx.com.aforecoppel.registromovilguardardatos.model.SolicitudInMemory;
import mx.com.aforecoppel.registromovilguardardatos.utils.ConstantesExcepciones;
import mx.com.aforecoppel.registromovilguardardatos.utils.Metricas;

import org.apache.camel.component.seda.SedaComponent;
import org.apache.camel.component.sql.SqlComponent;
import org.apache.camel.model.HystrixConfigurationDefinition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * Clase principal de configuración inicial del microservicio.
 *
 * @author adrian
 */
@Configuration
@Profile({"default", "develop"})
public class InitConfiguration {

    private static final Logger logger = LoggerFactory.getLogger(InitConfiguration.class);

    @Autowired
   	@Qualifier("bitacoraAforedigitalDataSource")
   	private DataSource datasourceBitacoraAforedigital;

   	@Autowired
   	@Qualifier("aforeGlobalDataSource")
   	private DataSource datasourceAfore;

   	@Autowired
   	@Qualifier("aforeDigitalDataSource")
   	private DataSource datasourceAforeDigital;
   	
    @Autowired
    private ExcepcionesDAO excepcionesDAO;

    ConstantesExcepciones constantes = new ConstantesExcepciones();

    Metricas metrica = new Metricas();

    @Bean(name = "constantes")
    public ConstantesExcepciones constantes() {
        return constantes;
    }

    @Bean(name = "metrica")
    public Metricas metrica() {
        return metrica;
    }
    
    @Bean(name = "serverInfo")
    @Profile("develop")
    public ServerInfo serverInfoDevelop() {
        return new ServerInfo("127.0.0.1", "localhost", "container");
    }

    @Bean(name = "serverInfo")
    @Profile("default")
    public ServerInfo serverInfoDefault() throws UnknownHostException {
        ServerInfo serverInfo = new ServerInfo();
        logger.info("-- ServerInfo --");
        logger.info("Hostname: " + serverInfo.getServerhostname());
        logger.info("Container: " + serverInfo.getServercontainer());
        logger.info("IP: " + serverInfo.getServerip());
        logger.info("----------------");
        return serverInfo;
    }

    @Bean
    public ConfiguracionAlertaDTO configuracionAlertaDTO(PropertiesConfiguration properties) {
        return new ConfiguracionAlertaDTO(
                properties.getAlertaRequiereReintento(),
                properties.getAlertaNumeroReintentos(),
                properties.getAlertaMensajeAlerta(),
                properties.getAlertaCorreos(),
                properties.getAlertaLimiteOcurrencia(),
                properties.getAlertaDescripcion()
        );
    }

    @Bean
    public SqlComponent sqlAforeGlobal() {
        SqlComponent sqlComponent = new SqlComponent();
        sqlComponent.setDataSource(datasourceAfore);
        return sqlComponent;
    }

    @Bean
	public SqlComponent sqlAforeDigital() {
		SqlComponent sqlComponent = new SqlComponent();
		sqlComponent.setDataSource(datasourceAforeDigital);
		return sqlComponent;
	}

    @Bean
    public ConcurrentMap<String, SolicitudInMemory> solicitudesEnProceso() {
        return new ConcurrentHashMap<>();
    }

    @Bean
    public HystrixConfigurationDefinition hystrixConfigurationDefinition(PropertiesConfiguration properties) {
        HystrixConfigurationDefinition hcd = new HystrixConfigurationDefinition();
        hcd.circuitBreakerRequestVolumeThreshold(properties.getVolumeThreshold());
        hcd.metricsRollingStatisticalWindowBuckets(properties.getWindowBucket());
        return hcd;
    }

    @Bean
    public SedaComponent mySeda(PropertiesConfiguration properties) {
        SedaComponent mySeda = new SedaComponent();
        mySeda.setConcurrentConsumers(properties.getSedaConcurrentConsumers());
        mySeda.setDefaultBlockWhenFull(true);
        mySeda.setQueueSize(properties.getSedaSize());
        return mySeda;
    }

    @PostConstruct
    public void init() {
    	logger.info("STARTUP");
        inicilizarConstantes();
    }

    public void inicilizarConstantes() {

        ServicioDTO servicioDTO = excepcionesDAO.findByNombre(ConstantesExcepciones.NOMBRE_SERVICIO);

        if (servicioDTO != null) {
            constantes.setClaveServicio(servicioDTO.getClaveservicio());
            logger.info("::WSREGISTROMOVILGUARDARDATOS::[InitServiceImpl.inicilizarConstantes] ClaveServicio --> " + constantes.getClaveServicio());
        } else {
            logger.error("::WSREGISTROMOVILGUARDARDATOS::[InitServiceImpl.inicilizarConstantes] ClaveServicio --> Error al obtener la clave del Servicio.");
        }
    }
}
