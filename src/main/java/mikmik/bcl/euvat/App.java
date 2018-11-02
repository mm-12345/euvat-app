package mikmik.bcl.euvat;

import java.util.Arrays;
import java.util.Comparator;

/**
 * The only purpose of this simple application is to print 3 highest VAT and 3 lowest VAT countries in EU.
 */
public class App {
    static Country[] countries = null;
    static int rankSize = 3;
    static VATProvider provider = null;

    public static void main( String[] args ) {
        printLowVATCountries(rankSize);
        System.out.println("-");
        printHighVATCountries(rankSize);
    }

    private static Country[] getRankedData() {
        if (countries != null) {
            return countries;
        }
        try {
            if (provider == null) {
                provider = new VATProvider();
            }
            countries = provider.getData();
        } catch (Exception e) {
            System.err.println("Failed to get VAT data:\n" + e);
            System.exit(1);
        }
        Arrays.sort(countries, Comparator.comparing(Country::getRate));
        return countries;
    }

    static void printHighVATCountries(int rankSize) {
        countries = getRankedData();
        System.out.println(rankSize + " highest VAT EU countries:");
        for (int i=0; i < rankSize; i++) {
            Country country = countries[countries.length - 1 - i];
            System.out.println(i+1 + ". " + country.getName() + ": " + country.getRate());
        }
    }

    static void printLowVATCountries(int rankSize) {
        countries = getRankedData();
        System.out.println(rankSize + " lowest VAT EU countries:");
        for (int i=0; i < rankSize; i++) {
            Country country = countries[i];
            System.out.println(i+1 + ". " + country.getName() + ": " + country.getRate());
        }
    }
}
