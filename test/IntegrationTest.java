import static org.fest.assertions.Assertions.assertThat;
import static play.test.Helpers.HTMLUNIT;
import static play.test.Helpers.fakeApplication;
import static play.test.Helpers.running;
import static play.test.Helpers.testServer;

import java.util.List;

import net.leludo.domobypi.bootstrap.ApplicationContext;
import net.leludo.domobypi.model.sensor.AbstractSensor;

import org.junit.Test;

import play.libs.F.Callback;
import play.libs.ws.WS;
import play.libs.ws.WSResponse;
import play.test.TestBrowser;

import com.fasterxml.jackson.databind.ObjectMapper;

public class IntegrationTest {

	/**
	 * add your integration test here in this example we just check if the
	 * welcome page is being shown
	 */
	@Test
	public void test() {
		running(testServer(3333, fakeApplication(new Global())), HTMLUNIT,
				new Callback<TestBrowser>() {
					public void invoke(TestBrowser browser) {
						browser.goTo("http://localhost:3333");
						System.out.println("toto"+browser.pageSource());
						assertThat(browser.pageSource()).contains("domobyPi");
						// assertThat(browser.$("#title").getText()).isEqualTo("domobyPi");
					}
				});
	}

	@Test
	public void testModule() {
		running(fakeApplication(new Global()), new Runnable() {

			ApplicationContext context = ApplicationContext.getInstance();

			@Override
			public void run() {
				assertThat(context.getSensors()).isNotNull();
				assertThat(context.getSensors().size()).isEqualTo(2);
				assertThat(context.getLeds()).isNotNull();
				assertThat(context.getLeds().size()).isEqualTo(2);
			}

		});
	}

	@Test
	public void testWsSensors() {
		running(testServer(3333, fakeApplication(new Global())),
				new Runnable() {

					@Override
					public void run() {
						WSResponse response = WS
								.url("http://localhost:3333/json/sensors")
								.get().get(1000);
						String jsonResponse = response.asJson().toString() ;
						System.out.println(jsonResponse);
						assertThat(response.getStatus()).isEqualTo(200);
						assertThat(jsonResponse)
								.isEqualTo(
										"[{\"id\":\"random01\",\"type\":\"random\",\"frequency\":5000,\"model\":\"inconnu\",\"min\":0,\"max\":24,\"led\":\"red\"},{\"id\":\"random02\",\"type\":\"random\",\"frequency\":10000,\"model\":\"inconnu\",\"min\":0,\"max\":30,\"led\":null}]");
					}

				});
	}

}
