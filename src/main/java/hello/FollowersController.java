package hello;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import twitter4j.IDs;
import twitter4j.JSONArray;
import twitter4j.ResponseList;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.User;
import twitter4j.conf.ConfigurationBuilder;

@Controller
public class FollowersController {

    @GetMapping("/followers")
    public String followers(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) throws TwitterException {
    	ConfigurationBuilder cb = new ConfigurationBuilder();
    	cb.setDebugEnabled(true)
    	  .setOAuthConsumerKey("BUo2i2DGfvgre5ZG7amnQJwuW")
    	  .setOAuthConsumerSecret("CKR3aVJlKNFWr8BNKayy16Ir4NkfEUzqntvDKlcpfTAOAnbKTi")
    	  .setOAuthAccessToken("625448859-W8bxEXgl9j4EBdYZXCvrnAUWI4VTO4OwIeJjugHf")
    	  .setOAuthAccessTokenSecret("zLWg68fUFQHI1OVKofya8Q3vR9NoxG0r14uhTs7OmEQL6");
    	TwitterFactory tf = new TwitterFactory(cb.build());
    	Twitter twitter = tf.getInstance();    	
    	long cursor = -1;
        IDs ids = twitter.getFollowersIDs(cursor);
        JSONArray array = new JSONArray();
        ResponseList<User> users =  twitter.lookupUsers(ids.getIDs());
        for(User user: users) {
        	array.put(user.getName());
        }
        model.addAttribute("name", array.toString());  	
        return "followers";
    }

}