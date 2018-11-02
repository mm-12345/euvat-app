package mikmik.bcl.euvat;

import com.google.gson.annotations.SerializedName;

import java.util.Date;
import java.util.HashMap;

public class Period {
    @SerializedName("effective_from")
    Date effectiveFrom;
    HashMap<String,Double> rates;

    public Period(Date effectiveFrom, HashMap<String, Double> rates) {
        this.effectiveFrom = effectiveFrom;
        this.rates = rates;
    }

    @Override
    public String toString() {
        return "Period{" +
                "effectiveFrom=" + effectiveFrom +
                ", rates=" + rates +
                '}';
    }
}
