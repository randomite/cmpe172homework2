package hello;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import twitter4j.JSONArray;
import twitter4j.JSONObject;
import twitter4j.ResponseList;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

@Controller
public class MyTweetsController {

    @GetMapping("/mytweets")
    public String mytweets(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) throws TwitterException {
    	ConfigurationBuilder cb = new ConfigurationBuilder();
    	cb.setDebugEnabled(true)
    	  .setOAuthConsumerKey("BUo2i2DGfvgre5ZG7amnQJwuW")
    	  .setOAuthConsumerSecret("CKR3aVJlKNFWr8BNKayy16Ir4NkfEUzqntvDKlcpfTAOAnbKTi")
    	  .setOAuthAccessToken("625448859-W8bxEXgl9j4EBdYZXCvrnAUWI4VTO4OwIeJjugHf")
    	  .setOAuthAccessTokenSecret("zLWg68fUFQHI1OVKofya8Q3vR9NoxG0r14uhTs7OmEQL6");
    	TwitterFactory tf = new TwitterFactory(cb.build());
    	Twitter twitter = tf.getInstance();
    	
    	ResponseList<Status> responses = twitter.getUserTimeline();
    	JSONArray array = new JSONArray();
    	for(Status response :responses) {
    		JSONObject data = new JSONObject();
			data.put("username", response.getUser().getName());
			data.put("tweet", response.getText());
			array.put(data);
    	}
        model.addAttribute("name", array.toString());
        return "mytweets";
    }

}