package net.leludo.pi.component.mock;

import java.util.Timer;

import net.leludo.pi.component.MockSensor;
import play.Logger;
import play.libs.F.Callback;
import play.libs.F.Callback0;
import play.mvc.WebSocket;

public class SensorWebSocketMock<T extends MockSensor> extends WebSocket<String> {

	T sensor;

	public SensorWebSocketMock(T sensor) {
		this.sensor = sensor;
	}

	@Override
	public void onReady(WebSocket.In<String> in, WebSocket.Out<String> out) {

		Logger.info("Websocket connected.");
		final Timer t = new Timer("sensor");
		//t.schedule(new SensorTask<T>(this.sensor, out), 0, 5000);

		// For each event received on the socket,
		in.onMessage(new Callback<String>() {
			public void invoke(String event) {
				// Log events to the console
				System.out.println(event);

			}
		});

		// When the socket is closed.
		in.onClose(new Callback0() {
			public void invoke() {
				t.cancel();
				Logger.info("Websocket disconnected.");
			}
		});
	}

}
