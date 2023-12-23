package Functions;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import Functions.function3.*;
import Functions.function3.apiNFT.RaribleItem;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

public class Function3 {

    public static void main(String[] args) {
    	//Xử lý json
        List<RaribleItem> raribleItems = readRaribleJson(".\\src\\data\\outputData\\apiData\\rarible.json");

        // Tiến hành phân tích dữ liệu ở đây
        // Ví dụ: tính toán hệ số tương quan Pearson giữa giá và volume trên sàn Rarible
        double[] rariblePrices = raribleItems.stream()
                .mapToDouble(RaribleItem::getFloorPriceETH)
                .toArray();
        double[] raribleVolumes = raribleItems.stream()
                .mapToDouble(RaribleItem::getTotalItemSupply)
                .toArray();
        double correlationOpenseaPriceVolume = calculatePearsonCorrelation(rariblePrices, raribleVolumes);

        System.out.println("Correlation between Rarible Price and Volume: " + correlationOpenseaPriceVolume);
        if (correlationOpenseaPriceVolume >= 0.04) {
        	System.out.println("Có thể có xu hướng tăng giữa Floor Price và Total Item Supply. Từ đó, ta khẳng định rằng sàn Rarible này có thể đang thực hiện các chiến lược để duy trì tính khan hiếm, giữ giá trị và thu hút sự chú ý tích cực từ cộng đồng, tạo ra một môi trường thị trường NFT động và linh hoạt.");
        } else if (correlationOpenseaPriceVolume <= -0.04) {
        	System.out.println("Có thể có xu hướng giảm giữa Floor Price và Total Item Supply. Từ đó, ta khẳng định rằng sàn Rarible này có thể đối mặt với thách thức về áp lực giảm giá và cần phải xem xét chiến lược quản lý cung cấp để duy trì sự cân bằng giữa tính khan hiếm và giá trị.");
        } else {
        	System.out.println("Chưa thể xác định rõ ràng xu hướng giữa Floor Price và Total Item Supply. Có thẻ khẳng định rằng sàn có thể đối mặt với thách thức trong việc hiểu và dự đoán biến động của giá thấp nhất dựa trên Total Item Supply.");
        }
    }

    // Các phương thức đọc dữ liệu từ file JSON

    private static List<RaribleItem> readRaribleJson(String filename) {
        try (FileReader reader = new FileReader(filename)) {
            Gson gson = new Gson();
            // Sử dụng TypeToken để chỉ định kiểu dữ liệu của mảng
            Type collectionType = new TypeToken<ResultWrapper<RaribleItem>>(){}.getType();
            ResultWrapper<RaribleItem> resultWrapper = gson.fromJson(reader, collectionType);
            // Lấy danh sách RaribleItem từ thuộc tính "result"
            return resultWrapper.getResult();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    

    // Các phương thức tính toán và xử lý dữ liệu

    private static double calculatePearsonCorrelation(double[] array1, double[] array2) {
        // Tính mean của mỗi mảng
        double mean1 = calculateMean(array1);
        double mean2 = calculateMean(array2);

        // Tính tử số và mẫu số của hệ số tương quan Pearson
        double numerator = 0.0;
        double denominator1 = 0.0;
        double denominator2 = 0.0;

        int n = array1.length;
        for (int i = 0; i < n; i++) {
            numerator += (array1[i] - mean1) * (array2[i] - mean2);
            denominator1 += Math.pow(array1[i] - mean1, 2);
            denominator2 += Math.pow(array2[i] - mean2, 2);
        }

        // Tính hệ số tương quan Pearson
        double correlation;
        if (denominator1 != 0 && denominator2 != 0) {
            correlation = numerator / Math.sqrt(denominator1 * denominator2);
        } else {
            // Nếu mẫu số là 0, hệ số tương quan sẽ không xác định
            correlation = Double.NaN;
        }

        return correlation;
    }

    private static double calculateMean(double[] array) {
        double sum = 0.0;
        for (double value : array) {
            sum += value;
        }
        return sum / array.length;
    }
}
