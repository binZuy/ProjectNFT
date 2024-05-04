package Functions.function1;

import java.io.IOException;

public class testTwitter {
	
	 public static void main(String[] args) {
		 
			String twitterFile = "src/data/outputData/TwitterData/twitter.json";
	 		String twitterJsonString = null;
	 	try {
				twitterJsonString = JsonProcessor.readJsonFile(twitterFile);
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	 	detailTwitter detail =new detailTwitter();
	 	detail.loadData(twitterJsonString);
	 	//detail.displayJsonArray(detail.getListTwitter());
	 	System.out.print("\n");
	 	System.out.print("\n");
	 	System.out.print("\n");
	 	System.out.print("\n");
	 	System.out.print("\n");
	 	detail.filterByHashtag(detail.getListTwitter(), "#NFTs");
	 	System.out.println("Most Popular Hashtag: " + detail.findMostPopularHashtag(detail.getPostsForDay(detail.getListTwitter(), "2023-12-13")));
		 }
}
