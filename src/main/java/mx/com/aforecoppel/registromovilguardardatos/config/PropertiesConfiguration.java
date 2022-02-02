package mx.com.aforecoppel.registromovilguardardatos.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "registromovilguardardatos")
public class PropertiesConfiguration {

	private String aforeId;
    private String tipoMensajeId;
    private String startDelay;
    private String cronSuspencion;
    private String cronInicio;
    private String cronFinalizados;

    private String cronMaintenanceStart;
	private String cronMaintenanceEnd;
	
	private String jmsUri;
	private String jmsUriContinuacion;
	
    private String bitacoraDriverClassName;
	private String bitacoraUrl;
	private String bitacoraUsername;
	private String bitacoraPassword;
	private Integer bitacoraInitialSize;
	private Integer bitacoraMaxActive;
	private Integer bitacoraMaxIdle;
	private Integer bitacoraMinIdle;
	private Long bitacoraTimeBetweenEvictionRunsMillis;
	private Long bitacoraMinEvictableIdleTimeMillis;
	private Boolean bitacoraTestOnBorrow;
	private String bitacoraValidationQuery;

	private String aforeDriverClassName;
	private String aforeUrl;
	private String aforeUsername;
	private String aforePassword;
	private Integer aforeInitialSize;
	private Integer aforeMaxActive;
	private Integer aforeMaxIdle;
	private Integer aforeMinIdle;
	private Long aforeTimeBetweenEvictionRunsMillis;
	private Long aforeMinEvictableIdleTimeMillis;
	private Boolean aforeTestOnBorrow;
	private String aforeValidationQuery;

	private String digitalDriverClassName;
	private String digitalUrl;
	private String digitalUsername;
	private String digitalPassword;
	private Integer digitalInitialSize;
	private Integer digitalMaxActive;
	private Integer digitalMaxIdle;
	private Integer digitalMinIdle;
	private Long digitalTimeBetweenEvictionRunsMillis;
	private Long digitalMinEvictableIdleTimeMillis;
	private Boolean digitalTestOnBorrow;
	private String digitalValidationQuery;

    private Integer alertaRequiereReintento;
    private Integer alertaNumeroReintentos;
    private String alertaMensajeAlerta;
    private String alertaCorreos;
    private Integer alertaLimiteOcurrencia;
    private String alertaDescripcion;

    private String jmsBrokerUrl;
    private String jmsUsername;
    private String jmsPassword;
    private Integer jmsPoolMaxConnections;
    private Integer jmsConcurrentConsumers;
    private Integer jmsMaximumActiveSessionPerConnection;

    private String endpointUrl;
    private int endpointConnectionTimeout;
    private int endpointReceiveTimeout;
    private int endpointRequestTimeout;

    private Boolean internalSSL;
    private Boolean hostnameVerifierSSL;
    private String keyStorePath;
    private String keyStorePassword;

    private int databasePollSize;
    private int databasePollDelay;

    private int volumeThreshold;
    private int windowBucket;

    private int sedaConcurrentConsumers;
    private int sedaSize;
    private int sedaRedelivery;

    /**
     * @return the aforeId
     */
    public String getAforeId() {
        return aforeId;
    }

    /**
     * @param aforeId the aforeId to set
     */
    public void setAforeId(String aforeId) {
        this.aforeId = aforeId;
    }

    /**
     * @return the tipoMensajeId
     */
    public String getTipoMensajeId() {
        return tipoMensajeId;
    }

    /**
     * @param tipoMensajeId the tipoMensajeId to set
     */
    public void setTipoMensajeId(String tipoMensajeId) {
        this.tipoMensajeId = tipoMensajeId;
    }

    /**
     * @return the startDelay
     */
    public String getStartDelay() {
        return startDelay;
    }

    /**
     * @param startDelay the startDelay to set
     */
    public void setStartDelay(String startDelay) {
        this.startDelay = startDelay;
    }

    /**
     * @return the cronSuspencion
     */
    public String getCronSuspencion() {
        return cronSuspencion;
    }

    /**
     * @param cronSuspencion the cronSuspencion to set
     */
    public void setCronSuspencion(String cronSuspencion) {
        this.cronSuspencion = cronSuspencion;
    }

    /**
     * @return the cronInicio
     */
    public String getCronInicio() {
        return cronInicio;
    }

    /**
     * @param cronInicio the cronInicio to set
     */
    public void setCronInicio(String cronInicio) {
        this.cronInicio = cronInicio;
    }

    /**
     * @return the cronFinalizados
     */
    public String getCronFinalizados() {
        return cronFinalizados;
    }

    /**
     * @param cronFinalizados the cronFinalizados to set
     */
    public void setCronFinalizados(String cronFinalizados) {
        this.cronFinalizados = cronFinalizados;
    }

    public String getCronMaintenanceStart() {
		return cronMaintenanceStart;
	}

	public void setCronMaintenanceStart(String cronMaintenanceStart) {
		this.cronMaintenanceStart = cronMaintenanceStart;
	}

	public String getCronMaintenanceEnd() {
		return cronMaintenanceEnd;
	}

	public void setCronMaintenanceEnd(String cronMaintenanceEnd) {
		this.cronMaintenanceEnd = cronMaintenanceEnd;
	}

	public String getBitacoraDriverClassName() {
		return bitacoraDriverClassName;
	}

	public void setBitacoraDriverClassName(String bitacoraDriverClassName) {
		this.bitacoraDriverClassName = bitacoraDriverClassName;
	}

	public String getBitacoraUrl() {
		return bitacoraUrl;
	}

	public void setBitacoraUrl(String bitacoraUrl) {
		this.bitacoraUrl = bitacoraUrl;
	}

	public String getBitacoraUsername() {
		return bitacoraUsername;
	}

	public void setBitacoraUsername(String bitacoraUsername) {
		this.bitacoraUsername = bitacoraUsername;
	}

	public String getBitacoraPassword() {
		return bitacoraPassword;
	}

	public void setBitacoraPassword(String bitacoraPassword) {
		this.bitacoraPassword = bitacoraPassword;
	}

	public Integer getBitacoraInitialSize() {
		return bitacoraInitialSize;
	}

	public void setBitacoraInitialSize(Integer bitacoraInitialSize) {
		this.bitacoraInitialSize = bitacoraInitialSize;
	}

	public Integer getBitacoraMaxActive() {
		return bitacoraMaxActive;
	}

	public void setBitacoraMaxActive(Integer bitacoraMaxActive) {
		this.bitacoraMaxActive = bitacoraMaxActive;
	}

	public Integer getBitacoraMaxIdle() {
		return bitacoraMaxIdle;
	}

	public void setBitacoraMaxIdle(Integer bitacoraMaxIdle) {
		this.bitacoraMaxIdle = bitacoraMaxIdle;
	}

	public Integer getBitacoraMinIdle() {
		return bitacoraMinIdle;
	}

	public void setBitacoraMinIdle(Integer bitacoraMinIdle) {
		this.bitacoraMinIdle = bitacoraMinIdle;
	}

	public Long getBitacoraTimeBetweenEvictionRunsMillis() {
		return bitacoraTimeBetweenEvictionRunsMillis;
	}

	public void setBitacoraTimeBetweenEvictionRunsMillis(Long bitacoraTimeBetweenEvictionRunsMillis) {
		this.bitacoraTimeBetweenEvictionRunsMillis = bitacoraTimeBetweenEvictionRunsMillis;
	}

	public Long getBitacoraMinEvictableIdleTimeMillis() {
		return bitacoraMinEvictableIdleTimeMillis;
	}

	public void setBitacoraMinEvictableIdleTimeMillis(Long bitacoraMinEvictableIdleTimeMillis) {
		this.bitacoraMinEvictableIdleTimeMillis = bitacoraMinEvictableIdleTimeMillis;
	}

	public Boolean getBitacoraTestOnBorrow() {
		return bitacoraTestOnBorrow;
	}

	public void setBitacoraTestOnBorrow(Boolean bitacoraTestOnBorrow) {
		this.bitacoraTestOnBorrow = bitacoraTestOnBorrow;
	}

	public String getBitacoraValidationQuery() {
		return bitacoraValidationQuery;
	}

	public void setBitacoraValidationQuery(String bitacoraValidationQuery) {
		this.bitacoraValidationQuery = bitacoraValidationQuery;
	}

	public String getDigitalDriverClassName() {
		return digitalDriverClassName;
	}

	public void setDigitalDriverClassName(String digitalDriverClassName) {
		this.digitalDriverClassName = digitalDriverClassName;
	}

	public String getDigitalUrl() {
		return digitalUrl;
	}

	public void setDigitalUrl(String digitalUrl) {
		this.digitalUrl = digitalUrl;
	}

	public String getDigitalUsername() {
		return digitalUsername;
	}

	public void setDigitalUsername(String digitalUsername) {
		this.digitalUsername = digitalUsername;
	}

	public String getDigitalPassword() {
		return digitalPassword;
	}

	public void setDigitalPassword(String digitalPassword) {
		this.digitalPassword = digitalPassword;
	}

	public Integer getDigitalInitialSize() {
		return digitalInitialSize;
	}

	public void setDigitalInitialSize(Integer digitalInitialSize) {
		this.digitalInitialSize = digitalInitialSize;
	}

	public Integer getDigitalMaxActive() {
		return digitalMaxActive;
	}

	public void setDigitalMaxActive(Integer digitalMaxActive) {
		this.digitalMaxActive = digitalMaxActive;
	}

	public Integer getDigitalMaxIdle() {
		return digitalMaxIdle;
	}

	public void setDigitalMaxIdle(Integer digitalMaxIdle) {
		this.digitalMaxIdle = digitalMaxIdle;
	}

	public Integer getDigitalMinIdle() {
		return digitalMinIdle;
	}

	public void setDigitalMinIdle(Integer digitalMinIdle) {
		this.digitalMinIdle = digitalMinIdle;
	}

	public Long getDigitalTimeBetweenEvictionRunsMillis() {
		return digitalTimeBetweenEvictionRunsMillis;
	}

	public void setDigitalTimeBetweenEvictionRunsMillis(Long digitalTimeBetweenEvictionRunsMillis) {
		this.digitalTimeBetweenEvictionRunsMillis = digitalTimeBetweenEvictionRunsMillis;
	}

	public Long getDigitalMinEvictableIdleTimeMillis() {
		return digitalMinEvictableIdleTimeMillis;
	}

	public void setDigitalMinEvictableIdleTimeMillis(Long digitalMinEvictableIdleTimeMillis) {
		this.digitalMinEvictableIdleTimeMillis = digitalMinEvictableIdleTimeMillis;
	}

	public Boolean getDigitalTestOnBorrow() {
		return digitalTestOnBorrow;
	}

	public void setDigitalTestOnBorrow(Boolean digitalTestOnBorrow) {
		this.digitalTestOnBorrow = digitalTestOnBorrow;
	}

	public String getDigitalValidationQuery() {
		return digitalValidationQuery;
	}

	public void setDigitalValidationQuery(String digitalValidationQuery) {
		this.digitalValidationQuery = digitalValidationQuery;
	}

	/**
     * @return the aforeDriverClassName
     */
    public String getAforeDriverClassName() {
        return aforeDriverClassName;
    }

    /**
     * @param aforeDriverClassName the aforeDriverClassName to set
     */
    public void setAforeDriverClassName(String aforeDriverClassName) {
        this.aforeDriverClassName = aforeDriverClassName;
    }

    /**
     * @return the aforeUrl
     */
    public String getAforeUrl() {
        return aforeUrl;
    }

    /**
     * @param aforeUrl the aforeUrl to set
     */
    public void setAforeUrl(String aforeUrl) {
        this.aforeUrl = aforeUrl;
    }

    /**
     * @return the aforeUsername
     */
    public String getAforeUsername() {
        return aforeUsername;
    }

    /**
     * @param aforeUsername the aforeUsername to set
     */
    public void setAforeUsername(String aforeUsername) {
        this.aforeUsername = aforeUsername;
    }

    /**
     * @return the aforePassword
     */
    public String getAforePassword() {
        return aforePassword;
    }

    /**
     * @param aforePassword the aforePassword to set
     */
    public void setAforePassword(String aforePassword) {
        this.aforePassword = aforePassword;
    }

    /**
     * @return the aforeInitialSize
     */
    public Integer getAforeInitialSize() {
        return aforeInitialSize;
    }

    /**
     * @param aforeInitialSize the aforeInitialSize to set
     */
    public void setAforeInitialSize(Integer aforeInitialSize) {
        this.aforeInitialSize = aforeInitialSize;
    }

    /**
     * @return the aforeMaxActive
     */
    public Integer getAforeMaxActive() {
        return aforeMaxActive;
    }

    /**
     * @param aforeMaxActive the aforeMaxActive to set
     */
    public void setAforeMaxActive(Integer aforeMaxActive) {
        this.aforeMaxActive = aforeMaxActive;
    }

    /**
     * @return the aforeMaxIdle
     */
    public Integer getAforeMaxIdle() {
        return aforeMaxIdle;
    }

    /**
     * @param aforeMaxIdle the aforeMaxIdle to set
     */
    public void setAforeMaxIdle(Integer aforeMaxIdle) {
        this.aforeMaxIdle = aforeMaxIdle;
    }

    /**
     * @return the aforeMinIdle
     */
    public Integer getAforeMinIdle() {
        return aforeMinIdle;
    }

    /**
     * @param aforeMinIdle the aforeMinIdle to set
     */
    public void setAforeMinIdle(Integer aforeMinIdle) {
        this.aforeMinIdle = aforeMinIdle;
    }

    /**
     * @return the aforeTimeBetweenEvictionRunsMillis
     */
    public Long getAforeTimeBetweenEvictionRunsMillis() {
        return aforeTimeBetweenEvictionRunsMillis;
    }

    /**
     * @param aforeTimeBetweenEvictionRunsMillis the aforeTimeBetweenEvictionRunsMillis to set
     */
    public void setAforeTimeBetweenEvictionRunsMillis(Long aforeTimeBetweenEvictionRunsMillis) {
        this.aforeTimeBetweenEvictionRunsMillis = aforeTimeBetweenEvictionRunsMillis;
    }

    /**
     * @return the aforeMinEvictableIdleTimeMillis
     */
    public Long getAforeMinEvictableIdleTimeMillis() {
        return aforeMinEvictableIdleTimeMillis;
    }

    /**
     * @param aforeMinEvictableIdleTimeMillis the aforeMinEvictableIdleTimeMillis to set
     */
    public void setAforeMinEvictableIdleTimeMillis(Long aforeMinEvictableIdleTimeMillis) {
        this.aforeMinEvictableIdleTimeMillis = aforeMinEvictableIdleTimeMillis;
    }

    /**
     * @return the aforeTestOnBorrow
     */
    public Boolean getAforeTestOnBorrow() {
        return aforeTestOnBorrow;
    }

    /**
     * @param aforeTestOnBorrow the aforeTestOnBorrow to set
     */
    public void setAforeTestOnBorrow(Boolean aforeTestOnBorrow) {
        this.aforeTestOnBorrow = aforeTestOnBorrow;
    }

    /**
     * @return the aforeValidationQuery
     */
    public String getAforeValidationQuery() {
        return aforeValidationQuery;
    }

    /**
     * @param aforeValidationQuery the aforeValidationQuery to set
     */
    public void setAforeValidationQuery(String aforeValidationQuery) {
        this.aforeValidationQuery = aforeValidationQuery;
    }

    /**
     * @return the alertaRequiereReintento
     */
    public Integer getAlertaRequiereReintento() {
        return alertaRequiereReintento;
    }

    /**
     * @param alertaRequiereReintento the alertaRequiereReintento to set
     */
    public void setAlertaRequiereReintento(Integer alertaRequiereReintento) {
        this.alertaRequiereReintento = alertaRequiereReintento;
    }

    /**
     * @return the alertaNumeroReintentos
     */
    public Integer getAlertaNumeroReintentos() {
        return alertaNumeroReintentos;
    }

    /**
     * @param alertaNumeroReintentos the alertaNumeroReintentos to set
     */
    public void setAlertaNumeroReintentos(Integer alertaNumeroReintentos) {
        this.alertaNumeroReintentos = alertaNumeroReintentos;
    }

    /**
     * @return the alertaMensajeAlerta
     */
    public String getAlertaMensajeAlerta() {
        return alertaMensajeAlerta;
    }

    /**
     * @param alertaMensajeAlerta the alertaMensajeAlerta to set
     */
    public void setAlertaMensajeAlerta(String alertaMensajeAlerta) {
        this.alertaMensajeAlerta = alertaMensajeAlerta;
    }

    /**
     * @return the alertaCorreos
     */
    public String getAlertaCorreos() {
        return alertaCorreos;
    }

    /**
     * @param alertaCorreos the alertaCorreos to set
     */
    public void setAlertaCorreos(String alertaCorreos) {
        this.alertaCorreos = alertaCorreos;
    }

    /**
     * @return the alertaLimiteOcurrencia
     */
    public Integer getAlertaLimiteOcurrencia() {
        return alertaLimiteOcurrencia;
    }

    /**
     * @param alertaLimiteOcurrencia the alertaLimiteOcurrencia to set
     */
    public void setAlertaLimiteOcurrencia(Integer alertaLimiteOcurrencia) {
        this.alertaLimiteOcurrencia = alertaLimiteOcurrencia;
    }

    /**
     * @return the alertaDescripcion
     */
    public String getAlertaDescripcion() {
        return alertaDescripcion;
    }

    /**
     * @param alertaDescripcion the alertaDescripcion to set
     */
    public void setAlertaDescripcion(String alertaDescripcion) {
        this.alertaDescripcion = alertaDescripcion;
    }

    /**
     * @return the jmsBrokerUrl
     */
    public String getJmsBrokerUrl() {
        return jmsBrokerUrl;
    }

    /**
     * @param jmsBrokerUrl the jmsBrokerUrl to set
     */
    public void setJmsBrokerUrl(String jmsBrokerUrl) {
        this.jmsBrokerUrl = jmsBrokerUrl;
    }

    /**
     * @return the jmsUsername
     */
    public String getJmsUsername() {
        return jmsUsername;
    }

    /**
     * @param jmsUsername the jmsUsername to set
     */
    public void setJmsUsername(String jmsUsername) {
        this.jmsUsername = jmsUsername;
    }

    /**
     * @return the jmsPassword
     */
    public String getJmsPassword() {
        return jmsPassword;
    }

    /**
     * @param jmsPassword the jmsPassword to set
     */
    public void setJmsPassword(String jmsPassword) {
        this.jmsPassword = jmsPassword;
    }

    /**
     * @return the jmsPoolMaxConnections
     */
    public Integer getJmsPoolMaxConnections() {
        return jmsPoolMaxConnections;
    }

    /**
     * @param jmsPoolMaxConnections the jmsPoolMaxConnections to set
     */
    public void setJmsPoolMaxConnections(Integer jmsPoolMaxConnections) {
        this.jmsPoolMaxConnections = jmsPoolMaxConnections;
    }

    /**
     * @return the jmsConcurrentConsumers
     */
    public Integer getJmsConcurrentConsumers() {
        return jmsConcurrentConsumers;
    }

    /**
     * @param jmsConcurrentConsumers the jmsConcurrentConsumers to set
     */
    public void setJmsConcurrentConsumers(Integer jmsConcurrentConsumers) {
        this.jmsConcurrentConsumers = jmsConcurrentConsumers;
    }

    /**
     * @return the jmsMaximumActiveSessionPerConnection
     */
    public Integer getJmsMaximumActiveSessionPerConnection() {
        return jmsMaximumActiveSessionPerConnection;
    }

    /**
     * @param jmsMaximumActiveSessionPerConnection the jmsMaximumActiveSessionPerConnection to set
     */
    public void setJmsMaximumActiveSessionPerConnection(Integer jmsMaximumActiveSessionPerConnection) {
        this.jmsMaximumActiveSessionPerConnection = jmsMaximumActiveSessionPerConnection;
    }

    /**
     * @return the endpointUrl
     */
    public String getEndpointUrl() {
        return endpointUrl;
    }

    /**
     * @param endpointUrl the endpointUrl to set
     */
    public void setEndpointUrl(String endpointUrl) {
        this.endpointUrl = endpointUrl;
    }

    /**
     * @return the endpointConnectionTimeout
     */
    public int getEndpointConnectionTimeout() {
        return endpointConnectionTimeout;
    }

    /**
     * @param endpointConnectionTimeout the endpointConnectionTimeout to set
     */
    public void setEndpointConnectionTimeout(int endpointConnectionTimeout) {
        this.endpointConnectionTimeout = endpointConnectionTimeout;
    }

    /**
     * @return the endpointReceiveTimeout
     */
    public int getEndpointReceiveTimeout() {
        return endpointReceiveTimeout;
    }

    /**
     * @param endpointReceiveTimeout the endpointReceiveTimeout to set
     */
    public void setEndpointReceiveTimeout(int endpointReceiveTimeout) {
        this.endpointReceiveTimeout = endpointReceiveTimeout;
    }

    /**
     * @return the endpointRequestTimeout
     */
    public int getEndpointRequestTimeout() {
        return endpointRequestTimeout;
    }

    /**
     * @param endpointRequestTimeout the endpointRequestTimeout to set
     */
    public void setEndpointRequestTimeout(int endpointRequestTimeout) {
        this.endpointRequestTimeout = endpointRequestTimeout;
    }

    /**
     * @return the internalSSL
     */
    public Boolean getInternalSSL() {
        return internalSSL;
    }

    /**
     * @param internalSSL the internalSSL to set
     */
    public void setInternalSSL(Boolean internalSSL) {
        this.internalSSL = internalSSL;
    }

    /**
     * @return the hostnameVerifierSSL
     */
    public Boolean getHostnameVerifierSSL() {
        return hostnameVerifierSSL;
    }

    /**
     * @param hostnameVerifierSSL the hostnameVerifierSSL to set
     */
    public void setHostnameVerifierSSL(Boolean hostnameVerifierSSL) {
        this.hostnameVerifierSSL = hostnameVerifierSSL;
    }

    /**
     * @return the keyStorePath
     */
    public String getKeyStorePath() {
        return keyStorePath;
    }

    /**
     * @param keyStorePath the keyStorePath to set
     */
    public void setKeyStorePath(String keyStorePath) {
        this.keyStorePath = keyStorePath;
    }

    /**
     * @return the keyStorePassword
     */
    public String getKeyStorePassword() {
        return keyStorePassword;
    }

    /**
     * @param keyStorePassword the keyStorePassword to set
     */
    public void setKeyStorePassword(String keyStorePassword) {
        this.keyStorePassword = keyStorePassword;
    }

    /**
     * @return the databasePollSize
     */
    public int getDatabasePollSize() {
        return databasePollSize;
    }

    /**
     * @param databasePollSize the databasePollSize to set
     */
    public void setDatabasePollSize(int databasePollSize) {
        this.databasePollSize = databasePollSize;
    }

    /**
     * @return the databasePollDelay
     */
    public int getDatabasePollDelay() {
        return databasePollDelay;
    }

    /**
     * @param databasePollDelay the databasePollDelay to set
     */
    public void setDatabasePollDelay(int databasePollDelay) {
        this.databasePollDelay = databasePollDelay;
    }

    /**
     * @return the volumeThreshold
     */
    public int getVolumeThreshold() {
        return volumeThreshold;
    }

    /**
     * @param volumeThreshold the volumeThreshold to set
     */
    public void setVolumeThreshold(int volumeThreshold) {
        this.volumeThreshold = volumeThreshold;
    }

    /**
     * @return the windowBucket
     */
    public int getWindowBucket() {
        return windowBucket;
    }

    /**
     * @param windowBucket the windowBucket to set
     */
    public void setWindowBucket(int windowBucket) {
        this.windowBucket = windowBucket;
    }

    /**
     * @return the sedaConcurrentConsumers
     */
    public int getSedaConcurrentConsumers() {
        return sedaConcurrentConsumers;
    }

    /**
     * @param sedaConcurrentConsumers the sedaConcurrentConsumers to set
     */
    public void setSedaConcurrentConsumers(int sedaConcurrentConsumers) {
        this.sedaConcurrentConsumers = sedaConcurrentConsumers;
    }

    /**
     * @return the sedaSize
     */
    public int getSedaSize() {
        return sedaSize;
    }

    /**
     * @param sedaSize the sedaSize to set
     */
    public void setSedaSize(int sedaSize) {
        this.sedaSize = sedaSize;
    }

    /**
     * @return the sedaRedelivery
     */
    public int getSedaRedelivery() {
        return sedaRedelivery;
    }

    /**
     * @param sedaRedelivery the sedaRedelivery to set
     */
    public void setSedaRedelivery(int sedaRedelivery) {
        this.sedaRedelivery = sedaRedelivery;
    }

	public String getJmsUri() {
		return jmsUri;
	}

	public void setJmsUri(String jmsUri) {
		this.jmsUri = jmsUri;
	}

	public String getJmsUriContinuacion() {
		return jmsUriContinuacion;
	}

	public void setJmsUriContinuacion(String jmsUriContinuacion) {
		this.jmsUriContinuacion = jmsUriContinuacion;
	}


}