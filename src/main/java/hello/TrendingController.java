package hello;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import twitter4j.IDs;
import twitter4j.JSONArray;
import twitter4j.Location;
import twitter4j.ResponseList;
import twitter4j.Status;
import twitter4j.Trend;
import twitter4j.Trends;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

@Controller
public class TrendingController {

    @GetMapping("/trending")
    public String trending(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) throws TwitterException {
    	ConfigurationBuilder cb = new ConfigurationBuilder();
    	cb.setDebugEnabled(true)
    	  .setOAuthConsumerKey("BUo2i2DGfvgre5ZG7amnQJwuW")
    	  .setOAuthConsumerSecret("CKR3aVJlKNFWr8BNKayy16Ir4NkfEUzqntvDKlcpfTAOAnbKTi")
    	  .setOAuthAccessToken("625448859-W8bxEXgl9j4EBdYZXCvrnAUWI4VTO4OwIeJjugHf")
    	  .setOAuthAccessTokenSecret("zLWg68fUFQHI1OVKofya8Q3vR9NoxG0r14uhTs7OmEQL6");
    	TwitterFactory tf = new TwitterFactory(cb.build());
    	Twitter twitter = tf.getInstance();
    	
    	int woeid = 23424977;
        Trends trends = twitter.getPlaceTrends(woeid);
        JSONArray array = new JSONArray();
        for (Trend trend : trends.getTrends()) {
        	array.put(trend.getName());
        }
        model.addAttribute("name", array.toString());
        System.out.println(array.toString());
        return "trending";
    }

}