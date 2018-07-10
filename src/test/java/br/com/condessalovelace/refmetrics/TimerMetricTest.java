package br.com.condessalovelace.refmetrics;

import static com.codahale.metrics.MetricRegistry.name;
import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.codahale.metrics.Histogram;
import com.codahale.metrics.Meter;
import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.Timer;
import com.codahale.metrics.annotation.Timed;

import br.com.condessalovelace.refmetrics.ReportController;

/**
 * Testa um Timer, que é basicamente um {@link Meter} das chamadas ao recurso e
 * um {@link Histogram} da duração das mesmas. OBS: No fim das contas, é melhor
 * usar um {@link Timer} que é mais completo do que os outros.
 * 
 * @author condessalovelace
 * @see Timer
 */
@RunWith(SpringJUnit4ClassRunner.class)
@Configuration
@ContextConfiguration(classes = { TimerDAO.class, ReportController.class })
public class TimerMetricTest {
	@Autowired
	private MetricRegistry metricRegistry;

	@Autowired
	private TimerDAO timerDAO;

	@Test
	public void testTimerMetric() throws InterruptedException {
		assertEquals(0L, metricRegistry.getTimers().get(name(TimerDAO.class, "buscar")).getCount());
		timerDAO.buscar(5000);
		assertEquals(1L, metricRegistry.getTimers().get(name(TimerDAO.class, "buscar")).getCount());
		timerDAO.buscar(3000);
		assertEquals(2L, metricRegistry.getTimers().get(name(TimerDAO.class, "buscar")).getCount());
	}
}

@Component
class TimerDAO {
	@Timed
	public void buscar(long time) throws InterruptedException {
		Thread.sleep(time);
	}
}