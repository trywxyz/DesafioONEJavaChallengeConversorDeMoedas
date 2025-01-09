import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


class ConversorDeMoedas {

    private static final String api = "https://v6.exchangerate-api.com/v6/00d5249474829ed187e95b41/latest/";

    public double getConversionRate(String baseCurrency, String targetCurrency) {
        try {
            URL url = new URL(api + baseCurrency);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            int responseCode = connection.getResponseCode();
            if (responseCode == 200) {
                InputStreamReader reader = new InputStreamReader(connection.getInputStream());
                JsonObject jsonObject = JsonParser.parseReader(reader).getAsJsonObject();
                JsonObject rates = jsonObject.getAsJsonObject("conversion_rates");
                reader.close();
                if (rates.has(targetCurrency)) {
                    return rates.get(targetCurrency).getAsDouble();
                } else {
                    System.out.println("Moeda não encontrada: " + targetCurrency);
                }
            } else {
                System.out.println("Erro na API: " + responseCode);
            }
        } catch (IOException e) {
            System.out.println("Erro ao acessar a API: " + e.getMessage());
        }
        return -1;
    }
}
