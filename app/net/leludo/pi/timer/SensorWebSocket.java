package net.leludo.pi.timer;

import net.leludo.domobypi.bootstrap.ApplicationContext;
import play.Logger;
import play.libs.F.Callback;
import play.libs.F.Callback0;
import play.mvc.WebSocket;

public class SensorWebSocket extends WebSocket<String> {

	WebSocket.Out<String> registeredOut ;

	@Override
	public void onReady(WebSocket.In<String> in, WebSocket.Out<String> out) {

		Logger.info("Websocket connected.");
		this.registeredOut = out ;
		ApplicationContext.getInstance().register(this.registeredOut);
		
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
				ApplicationContext.getInstance().unregister(registeredOut);
				Logger.info("Websocket disconnected.");
			}
		});
	}

}
