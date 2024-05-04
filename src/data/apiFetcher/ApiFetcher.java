package Data.ApiFetcher;

import java.io.IOException;

public interface ApiFetcher {
    void fetch() throws IOException, InterruptedException;

    String formatJson(String jsonData) throws IOException;

    void writeJsonToFile(String data, String fileName);
}
