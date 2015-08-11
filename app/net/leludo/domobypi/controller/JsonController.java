package net.leludo.domobypi.controller;

import java.io.IOException;

import com.fasterxml.jackson.databind.node.ObjectNode;

import net.leludo.domobypi.bootstrap.ApplicationContext;
import net.leludo.domobypi.model.led.Led;
import net.leludo.domobypi.model.sensor.SensorException;
import net.leludo.domobypi.model.sensor.TemperatureSensor;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;



public class JsonController extends Controller
{
	private static Led led = ApplicationContext.getInstance().getLeds().get("red") ;

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
    
    public static Result sensor() throws IOException, SensorException {
    	String temp = new TemperatureSensor().read() ;
    	ObjectNode result = Json.newObject() ;
    	result.put("temp", temp) ;
    	return ok(result) ;    	
    }

}
