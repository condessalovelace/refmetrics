package br.com.condessalovelace.refmetrics;

import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.codahale.metrics.annotation.Metric;
import com.codahale.metrics.jvm.CachedThreadStatesGaugeSet;
import com.codahale.metrics.jvm.GarbageCollectorMetricSet;
import com.codahale.metrics.jvm.MemoryUsageGaugeSet;

import br.com.condessalovelace.refmetrics.ReportController;

/**
 * Classe que testa a instrumentação da JVM que executa a aplicação.
 * 
 * @author condessalovelace
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@Configuration
@ContextConfiguration(classes = { MemoryUsageGaugeSet.class, GarbageCollectorMetricSet.class,
		CachedThreadStatesMetrics.class, ReportController.class })
public class JVMInstrumentationTest {
	@Autowired
	@Metric(name = "memoryUsageJVM", absolute = true)
	private MemoryUsageGaugeSet memoryUsageGaugeSet;

	@Autowired
	@Metric(name = "gcJVM", absolute = true)
	private GarbageCollectorMetricSet garbageCollectorMetricSet;

	@Autowired
	@Metric(name = "cachedThreadStates", absolute = true)
	private CachedThreadStatesMetrics cachedThreadStatesGaugeSet;

	@Test
	public void testJVMInstrumentation() throws InterruptedException {
		Thread.sleep(5000);
	}
}

@Component
class CachedThreadStatesMetrics extends CachedThreadStatesGaugeSet {
	public CachedThreadStatesMetrics() {
		super(10, TimeUnit.SECONDS);
	}
}
