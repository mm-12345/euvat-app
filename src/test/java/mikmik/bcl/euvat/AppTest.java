package mikmik.bcl.euvat;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

/**
 * Unit test for simple App.
 */
public class AppTest{
    private ClassLoader classLoader = getClass().getClassLoader();

    @Before
    public void setUp() {
        App.provider = new VATProvider();
        App.provider.localSource = classLoader.getResource("jsonvat.json").getPath();
    }

    /**
     * Simply tests if VATProvider.getData return an array and it's length is expected.
     */
    @Test
    public void providerGetData() throws Exception {
        Country[] countries = App.provider.getData();
        assertEquals(28, countries.length);
    }

    /**
     * Verifies that App.printHighVATCountries(3) prints 3 expected countries with highest standard VAT rate.
     */
    @Test
    public void top3() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        App.printHighVATCountries(3);
        String expected = "3 highest VAT EU countries:\n"
                + "1. Hungary: 27.0\n"
                + "2. Croatia: 25.0\n"
                + "3. Denmark: 25.0\n";
        assertEquals(expected, out.toString());
    }

    /**
     * Verifies that App.printLowVATCountries(3) prints 3 expected countries with lowest standard VAT rate.
     */
    @Test
    public void bottom3() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        App.printLowVATCountries(3);
        String expected = "3 lowest VAT EU countries:\n"
                + "1. Luxembourg: 17.0\n"
                + "2. Malta: 18.0\n"
                + "3. Cyprus: 19.0\n";
        assertEquals(expected, out.toString());
    }

    /**
     * Verifies that App.main prints expected output of 3 lowest VAT and 3 highest VAT countries.
     */
    @Test
    public void main() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        App.main(new String[]{});
        String expected = "3 lowest VAT EU countries:\n"
                + "1. Luxembourg: 17.0\n"
                + "2. Malta: 18.0\n"
                + "3. Cyprus: 19.0\n"
                + "-\n"
                + "3 highest VAT EU countries:\n"
                + "1. Hungary: 27.0\n"
                + "2. Croatia: 25.0\n"
                + "3. Denmark: 25.0\n";
        assertEquals(expected, out.toString());
    }
}
