package Functions.function1;

import java.io.IOException;

import util.path.JsonURL;

public class testBlog {
	
	 
	    public static void testIsInTargetWeek_Negative() {
	        detailBlog weekUtil = new detailBlog();
	        if(   weekUtil.isInTargetWeek("2024-05-04", "2024-04-28")) { // Tuesday in a different week) {
	        	System.out.println("right");
	        	
	        }
	        else {
	        	System.out.println("unright");// Sunday in a different week
	        }
	    }
	 
	 public static void main(String[] args) {
	    String blog1JsonString = null;
    	String blog2JsonString = null;
    	String blog3JsonString = null;
    	String blog4JsonString = null;
    	
		try {
			blog1JsonString = JsonProcessor.readJsonFile(JsonURL.AIRNFT);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			blog2JsonString = JsonProcessor.readJsonFile(JsonURL.NFTIFICALLY);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			blog3JsonString = JsonProcessor.readJsonFile(JsonURL.NFTNEWS);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			blog4JsonString = JsonProcessor.readJsonFile(JsonURL.NONFUNGIBLE);
			
		}catch (IOException e) {
			e.printStackTrace();
		}
	 	detailBlog detail =new detailBlog();
	 	detail.loadData(blog3JsonString);
	 	detail.loadData(blog2JsonString);
	 	detail.loadData(blog4JsonString);
	 	detail.loadData(blog1JsonString);

	 	
	 	//detail.displayJsonArray(detail.getListBlog());
	 	System.out.print("\n");
	 	System.out.print("\n");
	 	System.out.print("\n");
	 	System.out.print("\n");
	 	System.out.print("\n");
	 	
	 	
	 	testIsInTargetWeek_Negative();
	 //	detail.filterByKeyword(detail.getListBlog(), "collectibles");
	 	//System.out.println("Most Popular Hashtag: " + detail.findMostPopularKeyword(detail.getPostsForDay(detail.getListBlog(), "2023-12-18")));
		 }
}