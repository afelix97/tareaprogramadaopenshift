package mx.com.aforecoppel.registromovilguardardatos.utils;

import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.codahale.metrics.JmxReporter;
import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.Timer;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;

@Component
public class Metricas {

	private static final Logger LOG = LoggerFactory.getLogger(Metricas.class);

	private static final MetricRegistry metrics = new MetricRegistry();
	
	private JmxReporter reporter;

	@PreDestroy
	public void destroy() {
		this.reporter.stop();
		this.reporter.close();
		this.reporter = null;
	}

	public Timer.Context startReport(String name,Timer timer, Timer.Context tContext) {
		this.reporter = JmxReporter.forRegistry(metrics).convertRatesTo(TimeUnit.SECONDS).convertDurationsTo(TimeUnit.MILLISECONDS).build();
		timer = metrics.timer(name);
		tContext = timer.time();
		this.reporter.start();
		return tContext;
	}

}
