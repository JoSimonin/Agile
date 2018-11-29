package optimodlyon.agile.endpoints;

import optimodlyon.agile.models.Round;
import optimodlyon.agile.controller.Controller;
import optimodlyon.agile.models.CityMap;
import optimodlyon.agile.models.Delivery;
import optimodlyon.agile.models.Intersection;
import optimodlyon.agile.models.Segment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;

@CrossOrigin(origins = "http://localhost:8000")
@RestController
public class EndPoints {
	Controller controller = new Controller();
	
    @GetMapping("/map/{type}")
    public CityMap  getMap(@PathVariable String type) {
        controller.InitializeGraph(type);
        return controller.map;
    }
    
    @GetMapping("/deliveries/{file}")
    public CityMap get(@PathVariable String file) {
        controller.GetDeliveries(file);
        return controller.map;
    }
    
    @GetMapping("/calc/{nb}")
    public List<Round> get(@PathVariable int nb) {
    	List<Round> algo = controller.doAlgorithm(nb);
        return algo;
    }
    
    @GetMapping("/testcalc/{nb}")
    public List<Round> getRounds(@PathVariable int nb) {
    	controller.InitializeGraph("petit");
    	controller.GetDeliveries("dl-petit-6");
    	List<Round> algo = controller.doAlgorithm(nb);
        return algo;
    }
}