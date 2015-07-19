package net.leludo.domobypi.controller;

import com.fasterxml.jackson.databind.node.ObjectNode;

import net.leludo.pi.component.MockSensor;
import net.leludo.pi.component.mock.Led;
import net.leludo.pi.component.mock.SensorWebSocketMock;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.WebSocket;



public class MockController extends Controller
{
    private static Led led = new Led("red");

    public static Result led(String sw)
    {
        if (sw.equals("on"))
        {
            led.on();
        } else
        {
            led.off();
        }
        return ledState() ;
    }

    public static Result ledState()
    {
    	ObjectNode result = Json.newObject() ;
    	result.put("led", led.getState()) ;
    	return ok(result) ;    	
    }

    public static WebSocket<String> socket() {		
		return new SensorWebSocketMock<MockSensor>(new MockSensor()) ;
	}
}
