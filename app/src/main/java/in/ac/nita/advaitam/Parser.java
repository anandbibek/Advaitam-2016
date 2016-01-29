package in.ac.nita.advaitam;

import java.util.ArrayList;

/**
 * Created by Anand on 26-Jan-16 4:35 PM .
 */
public class Parser {

    public static ArrayList<FeedItem> parseDetails (String datum){

        String delimiter1 = "\nxxxxxx\n";
        String delimiter2 = "\nxxxx\n";

        ArrayList<FeedItem> feedItems = new ArrayList<>();

        String[] events = datum.split(delimiter1);

        for (String e : events) {
            try {
                String[] details = e.split(delimiter2);
                FeedItem item = new FeedItem();

                item.title = details[0];
                item.smallDesc = "This is placeholder small description";
                item.desc = details[1];
                item.contact = details[2];
                item.link = details[3];
                item.backdrop = details[4];
                item.thumbnail = details[5];

                feedItems.add(item);

            } catch (Exception ex){
                ex.printStackTrace();
            }
        }
        return feedItems;
    }
}
