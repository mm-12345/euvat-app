package mikmik.bcl.euvat;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

class VATProvider {
    private String defaultSource = "http://jsonvat.com/";
    String localSource = null;
    String remoteSource = null;
    private Gson gson = new Gson();

    /**
     * Helper class to parse JSON document.
     */
    private static class JSONDoc {
        @SerializedName("rates")
        private Country[] countries = null;
    }

    /**
     * Parse source raw data and return array of countries.
     * If localSource is not null, read data from local file defined by localSource.
     * If remoteSource is not null, read data from remote source.
     * Otherwise use read data from remote default source.
     * @return array of Country objects with VAT data.
     */
    Country[] getData() throws IOException {
        BufferedReader bufferedReader;
        if (localSource == null) {
            if (remoteSource == null) {
                remoteSource = defaultSource;
            }
            bufferedReader = new BufferedReader(
                    new InputStreamReader(new URL(remoteSource).openStream()));
        } else {
            bufferedReader = new BufferedReader(new FileReader(localSource));
        }
        JSONDoc doc = gson.fromJson(bufferedReader, JSONDoc.class);
        return doc.countries;
    }
}
