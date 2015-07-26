package net.leludo.domobypi.controller;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.databind.node.ObjectNode;

import net.leludo.domobypi.bootstrap.ApplicationContext;
import net.leludo.domobypi.model.AbstractSensor;
import net.leludo.pi.component.ComponentFactory;
import net.leludo.pi.component.Led;
import net.leludo.pi.component.PiPins;
import net.leludo.pi.component.SensorException;
import net.leludo.pi.component.TemperatureSensor;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;



public class JsonController extends Controller
{
	
    private static ComponentFactory manager = ComponentFactory.getInstance() ;
    private static Led led = (Led)manager.createComponent("led", "Red led", PiPins.TWELVE) ;
    

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
