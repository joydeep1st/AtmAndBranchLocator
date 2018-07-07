
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
    "Identification",
    "SupportedLanguages",
    "ATMServices",
    "Accessibility",
    "SupportedCurrencies",
    "Branch",
    "Location"
})
public class ATM {

    @JsonProperty("Identification")
    private String identification;
    @JsonProperty("SupportedLanguages")
    private List<String> supportedLanguages = new ArrayList<String>();
    @JsonProperty("ATMServices")
    private List<String> aTMServices = new ArrayList<String>();
    @JsonProperty("Accessibility")
    private List<String> accessibility = new ArrayList<String>();
    @JsonProperty("SupportedCurrencies")
    private List<String> supportedCurrencies = new ArrayList<String>();
    @JsonProperty("Branch")
    private Branch branch;
    @JsonProperty("Location")
    private Location location;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("Identification")
    public String getIdentification() {
        return identification;
    }

    @JsonProperty("Identification")
    public void setIdentification(String identification) {
        this.identification = identification;
    }

    public ATM withIdentification(String identification) {
        this.identification = identification;
        return this;
    }

    @JsonProperty("SupportedLanguages")
    public List<String> getSupportedLanguages() {
        return supportedLanguages;
    }

    @JsonProperty("SupportedLanguages")
    public void setSupportedLanguages(List<String> supportedLanguages) {
        this.supportedLanguages = supportedLanguages;
    }

    public ATM withSupportedLanguages(List<String> supportedLanguages) {
        this.supportedLanguages = supportedLanguages;
        return this;
    }

    @JsonProperty("ATMServices")
    public List<String> getATMServices() {
        return aTMServices;
    }

    @JsonProperty("ATMServices")
    public void setATMServices(List<String> aTMServices) {
        this.aTMServices = aTMServices;
    }

    public ATM withATMServices(List<String> aTMServices) {
        this.aTMServices = aTMServices;
        return this;
    }

    @JsonProperty("Accessibility")
    public List<String> getAccessibility() {
        return accessibility;
    }

    @JsonProperty("Accessibility")
    public void setAccessibility(List<String> accessibility) {
        this.accessibility = accessibility;
    }

    public ATM withAccessibility(List<String> accessibility) {
        this.accessibility = accessibility;
        return this;
    }

    @JsonProperty("SupportedCurrencies")
    public List<String> getSupportedCurrencies() {
        return supportedCurrencies;
    }

    @JsonProperty("SupportedCurrencies")
    public void setSupportedCurrencies(List<String> supportedCurrencies) {
        this.supportedCurrencies = supportedCurrencies;
    }

    public ATM withSupportedCurrencies(List<String> supportedCurrencies) {
        this.supportedCurrencies = supportedCurrencies;
        return this;
    }

    @JsonProperty("Branch")
    public Branch getBranch() {
        return branch;
    }

    @JsonProperty("Branch")
    public void setBranch(Branch branch) {
        this.branch = branch;
    }

    public ATM withBranch(Branch branch) {
        this.branch = branch;
        return this;
    }

    @JsonProperty("Location")
    public Location getLocation() {
        return location;
    }

    @JsonProperty("Location")
    public void setLocation(Location location) {
        this.location = location;
    }

    public ATM withLocation(Location location) {
        this.location = location;
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

    public ATM withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(identification).append(supportedLanguages).append(aTMServices).append(accessibility).append(supportedCurrencies).append(branch).append(location).append(additionalProperties).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof ATM) == false) {
            return false;
        }
        ATM rhs = ((ATM) other);
        return new EqualsBuilder().append(identification, rhs.identification).append(supportedLanguages, rhs.supportedLanguages).append(aTMServices, rhs.aTMServices).append(accessibility, rhs.accessibility).append(supportedCurrencies, rhs.supportedCurrencies).append(branch, rhs.branch).append(location, rhs.location).append(additionalProperties, rhs.additionalProperties).isEquals();
    }

}
