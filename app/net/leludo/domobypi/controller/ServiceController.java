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



public class ServiceController extends Controller
{
	
    public static Result sensors() {
    	List<AbstractSensor> sensors = ApplicationContext.getInstance().getSensors() ;
    	return ok(Json.toJson(sensors)) ;
    }

}
