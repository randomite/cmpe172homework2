package hello;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import twitter4j.JSONObject;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;


@Controller
public class SearchController {

    @GetMapping("/search")
    public String search(@RequestParam(name="query", required=false, defaultValue="World") String query, Model model) {
    	ConfigurationBuilder cb = new ConfigurationBuilder();
    	cb.setDebugEnabled(true)
    	  .setOAuthConsumerKey("BUo2i2DGfvgre5ZG7amnQJwuW")
    	  .setOAuthConsumerSecret("CKR3aVJlKNFWr8BNKayy16Ir4NkfEUzqntvDKlcpfTAOAnbKTi")
    	  .setOAuthAccessToken("625448859-W8bxEXgl9j4EBdYZXCvrnAUWI4VTO4OwIeJjugHf")
    	  .setOAuthAccessTokenSecret("zLWg68fUFQHI1OVKofya8Q3vR9NoxG0r14uhTs7OmEQL6");
    	TwitterFactory tf = new TwitterFactory(cb.build());
    	Twitter twitter = tf.getInstance();
        Query twitterQuery = new Query(query);
        QueryResult result;
        JSONObject data = new JSONObject();
		try {
			result = twitter.search(twitterQuery);
			for (Status status : result.getTweets()) {
				data.put(status.getUser().getScreenName(), status.getText());
	            System.out.println("@" + status.getUser().getScreenName() + ":" + status.getText());
	        }
		} catch (TwitterException e) {
			e.printStackTrace();
		}
    	model.addAttribute("name", data.toString());
        return "search";
    }

}
