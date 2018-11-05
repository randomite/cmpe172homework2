package hello;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;

@Controller
public class GreetingController {

    @GetMapping("/greeting")
    public String greeting(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
    	name = "joooooooo";
    	model.addAttribute("name", name);
        return "greeting";
    }

}
