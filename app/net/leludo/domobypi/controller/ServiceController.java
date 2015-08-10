package net.leludo.domobypi.controller;

import java.util.List;

import net.leludo.domobypi.bootstrap.ApplicationContext;
import net.leludo.domobypi.model.AbstractSensor;
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
