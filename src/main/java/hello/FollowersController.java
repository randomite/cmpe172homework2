package hello;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import twitter4j.IDs;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

@Controller
public class FollowersController {

    @GetMapping("/followers")
    public String followers(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
    	ConfigurationBuilder cb = new ConfigurationBuilder();
    	cb.setDebugEnabled(true)
    	  .setOAuthConsumerKey("BUo2i2DGfvgre5ZG7amnQJwuW")
    	  .setOAuthConsumerSecret("CKR3aVJlKNFWr8BNKayy16Ir4NkfEUzqntvDKlcpfTAOAnbKTi")
    	  .setOAuthAccessToken("625448859-W8bxEXgl9j4EBdYZXCvrnAUWI4VTO4OwIeJjugHf")
    	  .setOAuthAccessTokenSecret("zLWg68fUFQHI1OVKofya8Q3vR9NoxG0r14uhTs7OmEQL6");
    	TwitterFactory tf = new TwitterFactory(cb.build());
    	Twitter twitter = tf.getInstance();
    	try {
            IDs ids  = twitter.getFollowersIDs(-1);
            model.addAttribute("name", ids.getIDs().length);
        } catch (TwitterException te) {
            te.printStackTrace();
        }
    	
        return "followers";
    }

}