
package api.barclays;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "BrandName",
    "ATM"
})
public class Brand {

    @JsonProperty("BrandName")
    private String brandName;
    @JsonProperty("ATM")
    private List<ATM> aTM = new ArrayList<ATM>();
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("BrandName")
    public String getBrandName() {
        return brandName;
    }

    @JsonProperty("BrandName")
    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public Brand withBrandName(String brandName) {
        this.brandName = brandName;
        return this;
    }

    @JsonProperty("ATM")
    public List<ATM> getATM() {
        return aTM;
    }

    @JsonProperty("ATM")
    public void setATM(List<ATM> aTM) {
        this.aTM = aTM;
    }

    public Brand withATM(List<ATM> aTM) {
        this.aTM = aTM;
        return this;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    public Brand withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(brandName).append(aTM).append(additionalProperties).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Brand) == false) {
            return false;
        }
        Brand rhs = ((Brand) other);
        return new EqualsBuilder().append(brandName, rhs.brandName).append(aTM, rhs.aTM).append(additionalProperties, rhs.additionalProperties).isEquals();
    }

}
