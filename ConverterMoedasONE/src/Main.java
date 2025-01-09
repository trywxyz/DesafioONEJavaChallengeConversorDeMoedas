import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ConversorDeMoedas conversor = new ConversorDeMoedas();

        Map<String, String> supportedCurrencies = Map.of(
                "USD", "Dólar Americano",
                "EUR", "Euro",
                "BRL", "Real Brasileiro",
                "JPY", "Iene Japonês",
                "GBP", "Libra Esterlina"
        );

        while (true) {
            System.out.println("----------------------------------------------");
            System.out.println("Bem-vindo ao Conversor de Moedas!");
            System.out.println("Escolha uma conversão:");
            int index = 1;
            for (String currency : supportedCurrencies.keySet()) {
                System.out.println(index + ") - Converter para " + supportedCurrencies.get(currency));
                index++;
            }
            System.out.println("0) - Sair");
            System.out.println("Digite um número que está na lista");

            int choice = scanner.nextInt();

            if (choice == 0) {
                System.out.println("Obrigado por usar o Conversor de Moedas! Até logo!");
                break;
            }

            if (choice < 1 || choice > supportedCurrencies.size()) {
                System.out.println("Escolha inválida. Tente novamente");
                continue;
            }

            String[] currencies = supportedCurrencies.keySet().toArray(new String[0]);
            String targetCurrency = currencies[choice - 1];

            System.out.print("Digite o valor em BRL a ser convertido: ");
            double amount = scanner.nextDouble();

            double conversionRate = conversor.getConversionRate("BRL", targetCurrency);
            if (conversionRate != -1) {
                double convertedAmount = amount * conversionRate;
                System.out.printf("%.2f BRL equivale a %.2f %s (%s).%n",
                        amount, convertedAmount, targetCurrency, supportedCurrencies.get(targetCurrency));
            } else {
                System.out.println("Não foi possível obter a taxa de conversão. Tente novamente mais tarde.");
            }
        }

        scanner.close();
    }
}