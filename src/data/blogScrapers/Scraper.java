package Data.BlogScrapers;

import Data.ApiFetcher.Fetcher;

public class Scraper {

    public void scrape(){
    	Fetcher fetcher = new Fetcher();
        AirnftScraper airnft = new AirnftScraper();
        NfticallyScraper nftically = new NfticallyScraper();
        NFTNewsTodayScraper nftnews = new NFTNewsTodayScraper();
        NonFungibleScraper nonfungible = new NonFungibleScraper();

        try {
        	System.out.println("Fetching API data...");
        	fetcher.fetch();
        	
            System.out.println("Scraping Airnft...");
            airnft.scrape();

            System.out.println("Scraping Nftically...");
            nftically.scrape();

            System.out.println("Scraping NFTNewsToday...");
            nftnews.scrape();

            System.out.println("Scraping NonFungible...");
            nonfungible.scrape();

            System.out.println("Scraping complete!");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Add resource cleanup logic if necessary
        }
    }
}
