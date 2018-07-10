package br.com.condessalovelace.refmetrics;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.codahale.metrics.Gauge;
import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.annotation.Metric;
import com.ryantenney.metrics.spring.config.annotation.EnableMetrics;

import br.com.condessalovelace.refmetrics.ReportController;

/**
 * Testa um Gauge da aplicação.
 * 
 * @author condessalovelace
 * @see GaugeMetric
 */
@RunWith(SpringJUnit4ClassRunner.class)
@Configuration
@ContextConfiguration(classes = { GaugeMetric.class, ReportController.class })
public class GaugeMetricTest {
	@Autowired
	@Metric(name = "threads", absolute = true)
	private GaugeMetric gaugeMetric;

	@Autowired
	private MetricRegistry metricRegistry;

	/**
	 * Utiliza um gauge para monitorar o número de threads executando na aplicação.
	 * 
	 * @throws InterruptedException
	 */
	@Test
	public void testGaugeMetric() throws InterruptedException {
		Thread.sleep(5000);
		assertEquals(Thread.activeCount(), metricRegistry.getGauges().get("threads").getValue());
	}
}

/**
 * O objetivo dessa métrica é retornar um valor específico. Esse valor por algum
 * motivo precisa ser monitorado, por isso, a forma ideal de logá-lo é usando
 * essa métrica. Existem implementações de Gauges, que podem ser usados para
 * propósitos específicos.
 * 
 * @author condessalovelace
 *
 */
@Component
@EnableMetrics
class GaugeMetric implements Gauge<Integer> {

	@Override
	public Integer getValue() {
		return Thread.activeCount();
	}
}
