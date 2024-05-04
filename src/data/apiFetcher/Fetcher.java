package Data.ApiFetcher;

public class Fetcher {

	public void fetch() throws Exception {
		OpenseaFetcher opensea = new OpenseaFetcher();
		RaribleFetcher rarible = new RaribleFetcher();
		
		try {
			opensea.fetch();
			rarible.fetch();
		} catch(Exception e) {
			throw e;
		}
	}
	
	

}
