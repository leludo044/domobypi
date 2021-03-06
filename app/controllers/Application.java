package controllers;

import net.leludo.domobypi.model.led.AbstractLed;
import net.leludo.domobypi.model.led.Led;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.index;



public class Application extends Controller
{
    private static Led led = AbstractLed.createInstance("test", 0, "virtual");

    public static Result index()
    {
        return ok(index.render("off"));
    }

    public static Result led(String sw)
    {
        if (sw.equals("on"))
        {
            led.on();
        } else
        {
            led.off();
        }
        return ledState();
    }

    public static Result ledState()
    {
        return ok(index.render(led.getState()));
    }

}
