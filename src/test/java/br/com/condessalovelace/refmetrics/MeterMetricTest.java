package br.com.condessalovelace.refmetrics;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.annotation.Metered;
import com.codahale.metrics.jvm.MemoryUsageGaugeSet;
import com.ryantenney.metrics.spring.config.annotation.EnableMetrics;

import br.com.condessalovelace.refmetrics.ReportController;

/**
 * Testa a métrica Meter.
 * 
 * @author condessalovelace
 * @see MeterMetric
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@Configuration
@ContextConfiguration(classes = { MeterMetric.class, ReportController.class, MemoryUsageGaugeSet.class })
public class MeterMetricTest {

	@Autowired
	private MetricRegistry metricRegistry;

	@Autowired
	private MeterMetric meterMetric;

	@Test
	public void testMeterMetric() throws InterruptedException {
		assertEquals(0, metricRegistry.getMeters().get(MetricRegistry.name(MeterMetric.class, MeterMetric.METERMETRIC))
				.getCount());
		meterMetric.metodo();
		assertEquals(1, metricRegistry.getMeters().get(MetricRegistry.name(MeterMetric.class, MeterMetric.METERMETRIC))
				.getCount());
	}
}

/**
 * Essa métrica obtém o número de chamadas a um método e também a média delas no
 * ciclo de vida do objeto. Para que seja possível contar as ocorrências é
 * necessário chamar o método <i>mark</i> para registrar a contagem. Foi
 * adicionada a configuração de um relatório periódico no console para calcular
 * as métricas registradas para o objeto.
 * 
 * @author condessalovelace
 *
 */
@Component
@EnableMetrics
class MeterMetric {
	public static final String METERMETRIC = "metodo";

	@Metered(name = METERMETRIC)
	public void metodo() throws InterruptedException {
		Thread.sleep(5000);
	}
}