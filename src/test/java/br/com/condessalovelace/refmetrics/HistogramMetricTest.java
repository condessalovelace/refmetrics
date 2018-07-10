package br.com.condessalovelace.refmetrics;

import static com.codahale.metrics.MetricRegistry.name;
import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.codahale.metrics.Histogram;
import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.Reservoir;
import com.ryantenney.metrics.spring.config.annotation.EnableMetrics;

import br.com.condessalovelace.refmetrics.ReportController;

/**
 * Testa um Histogram de um método. Existem diversas implementações de
 * {@link Reservoir} que podem ser usadas para aplicações específicas.
 * 
 * @author condessalovelace
 * @see Histogram
 */
@RunWith(SpringJUnit4ClassRunner.class)
@Configuration
@ContextConfiguration(classes = { HistogramDAO.class, ReportController.class })
public class HistogramMetricTest {
	@Autowired
	private MetricRegistry metricRegistry;
	@Autowired
	private HistogramDAO histogramDAO;

	@Before
	public void before() {
		metricRegistry.histogram(name(HistogramDAO.class, "result-size"));
	}

	@Test
	public void testHistogramMetric() throws InterruptedException {
		histogramDAO.buscarDados(3);
		histogramDAO.buscarDados(5);
		assertEquals(4,
				metricRegistry.getHistograms().get(name(HistogramDAO.class, "result-size")).getSnapshot().getMean(),
				0.05);
	}
}

/**
 * Classe que mocka um DAO para testes.
 * 
 * @author condessalovelace
 *
 */
@Component
@EnableMetrics
class HistogramDAO {
	@Autowired
	private MetricRegistry metricRegistry;

	public Integer buscarDados(int size) throws InterruptedException {
		metricRegistry.getHistograms().get(name(HistogramDAO.class, "result-size")).update(size);
		Thread.sleep(5000);
		return size;
	}
}
