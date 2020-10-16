import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;



public class Main {


    public static void main(String[] args) throws IOException {

        BufferedReader phrase = new BufferedReader(new InputStreamReader(System.in));
        String sp = phrase.readLine();

        String[] splPhrase = sp.split(" ");


        try {
            int a = Integer.parseInt(splPhrase[0]);
            int b = Integer.parseInt(splPhrase[2]);
            int c = 0;

            if (splPhrase[1].equals("-")) c = a - b;
            else {
                if (splPhrase[1].equals("+")) c = a + b;
                else {
                    if (splPhrase[1].equals("*")) c = a * b;
                    else {
                        if (splPhrase[1].equals("/")) c = a / b;
                        else c = a / b / 0;

                    }
                }
            }
            System.out.println(c);
        }
        catch (NumberFormatException ex) {
            int a = Converter.romanToArabic(splPhrase[0]);
            int b = Converter.romanToArabic(splPhrase[2]);
            int c = 0;

            if (splPhrase[1].equals("-")) c = a - b;
            else {
                if (splPhrase[1].equals("+")) c = a + b;
                else {
                    if (splPhrase[1].equals("*")) c = a * b;
                    else {
                        if (splPhrase[1].equals("/")) c = a / b;
                        else c = a / b / 0;

                    }
                }
            }
            if (c == 0) System.out.println("Zero");
            System.out.println(Converter.arabicToRoman(c));

        }


    }
}


class Converter{


    enum RomanNumeral {
        I(1), IV(4), V(5), IX(9), X(10),
        XL(40), L(50), XC(90), C(100);

        private int value;

        RomanNumeral(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }

        public static List<RomanNumeral> getReverseSortedValues() {
            return Arrays.stream(values())
                    .sorted(Comparator.comparing((RomanNumeral e) -> e.value).reversed())
                    .collect(Collectors.toList());
        }
    }



    public static int romanToArabic(String input) {
        String romanNumeral = input.toUpperCase();
        int result = 0;

        List<RomanNumeral> romanNumerals = RomanNumeral.getReverseSortedValues();

        int i = 0;

        while ((romanNumeral.length() > 0) && (i < romanNumerals.size())) {
            RomanNumeral symbol = romanNumerals.get(i);
            if (romanNumeral.startsWith(symbol.name())) {
                result += symbol.getValue();
                romanNumeral = romanNumeral.substring(symbol.name().length());
            } else {
                i++;
            }
        }
        return result;
    }




    public static String arabicToRoman(int number) {

        List<RomanNumeral> romanNumerals = RomanNumeral.getReverseSortedValues();

        int i = 0;
        StringBuilder sb = new StringBuilder();

        while ((number > 0) && (i < romanNumerals.size())) {
            RomanNumeral currentSymbol = romanNumerals.get(i);
            if (currentSymbol.getValue() <= number) {
                sb.append(currentSymbol.name());
                number -= currentSymbol.getValue();
            } else {
                i++;
            }
        }

        return sb.toString();
    }



}


