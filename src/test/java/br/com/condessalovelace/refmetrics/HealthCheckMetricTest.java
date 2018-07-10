package br.com.condessalovelace.refmetrics;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.codahale.metrics.health.HealthCheck;
import com.codahale.metrics.health.HealthCheck.Result;
import com.codahale.metrics.health.HealthCheckRegistry;
import com.ryantenney.metrics.spring.config.annotation.EnableMetrics;

/**
 * Testa um {@link HealthCheck} para monitorar o funcionamento de um banco de
 * dados mockado.
 * 
 * @author condessalovelace
 * @see HealthCheck
 */
@RunWith(SpringJUnit4ClassRunner.class)
@Configuration
@ContextConfiguration(classes = { DatabaseHealthCheck.class })
public class HealthCheckMetricTest {
	@Autowired
	private DatabaseHealthCheck database;

	@Test
	public void testHealthCheckMetric() throws InterruptedException {
		Result resultConnected = database.report();
		DatabaseHealthCheck.connected = false;
		Result resultNotConnected = database.report();
		assertEquals("Connected!", resultConnected.getMessage());
		assertEquals("Connnection failed!", resultNotConnected.getMessage());

	}
}

@Component
@EnableMetrics
class DatabaseHealthCheck extends HealthCheck {
	public static boolean connected = true;

	@Autowired
	private HealthCheckRegistry healthChecks;

	@Override
	protected Result check() throws Exception {
		if (connected) {
			return HealthCheck.Result.healthy("Connected!");
		} else {
			return HealthCheck.Result.unhealthy("Connnection failed!");
		}
	}

	public Result report() {
		Result result = healthChecks.runHealthCheck("databaseHealthCheck");
		if (result.isHealthy()) {
			LoggerFactory.getLogger(DatabaseHealthCheck.class).info("Connected!");
		} else {
			LoggerFactory.getLogger(DatabaseHealthCheck.class).info("Connnection failed!");
		}
		return result;
	}

}