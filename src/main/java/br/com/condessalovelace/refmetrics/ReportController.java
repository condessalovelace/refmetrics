package br.com.condessalovelace.refmetrics;

import java.util.concurrent.TimeUnit;

import org.springframework.context.annotation.Configuration;

import com.codahale.metrics.ConsoleReporter;
import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.Slf4jReporter;
import com.ryantenney.metrics.spring.config.annotation.EnableMetrics;
import com.ryantenney.metrics.spring.config.annotation.MetricsConfigurerAdapter;

/**
 * Representa um relatório de métricas que será agendado para rodar a cada
 * segundo.
 * 
 * @author condessalovelace
 *
 */
@Configuration
@EnableMetrics
public class ReportController extends MetricsConfigurerAdapter {

	@Override
	public void configureReporters(MetricRegistry metricRegistry) {
		registerReporter(ConsoleReporter.forRegistry(metricRegistry).build()).start(1, TimeUnit.SECONDS);
		registerReporter(Slf4jReporter.forRegistry(metricRegistry).build()).start(1, TimeUnit.SECONDS);
	}
}
