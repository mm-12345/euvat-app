package mikmik.bcl.euvat;

import com.google.gson.annotations.SerializedName;

import java.util.Arrays;
import java.util.Date;

public class Country {
    private String name;
    @SerializedName("code")
    private String isoCode;
    private Period[] periods;

    public Country(String name, String isoCode, Period[] periods) {
        this.name = name;
        this.isoCode = isoCode;
        this.periods = periods;
    }

    public String getName() {
        return name;
    }

    public String getIsoCode() {
        return isoCode;
    }

    public Period[] getPeriods() {
        return periods;
    }

    @Override
    public String toString() {
        return "Country{" +
                "name='" + name + '\'' +
                ", isoCode='" + isoCode + '\'' +
                ", periods=" + Arrays.toString(periods) +
                '}';
    }

    /**
     * @param date
     * @param rateType
     * @return VAT rate of a given type (standard, reduced, etc) as of given date
     */
    public Double getRate(Date date, String rateType) {
        Double res = null;
        for (Period period : periods) {
            if (period.effectiveFrom == null || date.after(period.effectiveFrom)) {
                res = period.rates.get(rateType);
                break;
            }
        }
        return res;
    }

    /**
     * @param date
     * @return standard VAT rate as of given date
     */

    public Double getRate(Date date) {
        return getRate(date, "standard");
    }

    /**
     * @param rateType
     * @return VAT rate of a given type (standard, reduced, etc) as of today
     */
    public Double getRate(String rateType) {
        return getRate(new Date(), rateType);
    }

    /**
     * @return standard VAT rate as of today
     */
    public Double getRate() {
        return getRate(new Date());
    }
}
