
package api.barclays;

import java.util.HashMap;
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
    "LastUpdated",
    "TotalResults",
    "Agreement",
    "License",
    "TermsOfUse"
})
public class Meta {

    @JsonProperty("LastUpdated")
    private String lastUpdated;
    @JsonProperty("TotalResults")
    private Integer totalResults;
    @JsonProperty("Agreement")
    private String agreement;
    @JsonProperty("License")
    private String license;
    @JsonProperty("TermsOfUse")
    private String termsOfUse;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("LastUpdated")
    public String getLastUpdated() {
        return lastUpdated;
    }

    @JsonProperty("LastUpdated")
    public void setLastUpdated(String lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public Meta withLastUpdated(String lastUpdated) {
        this.lastUpdated = lastUpdated;
        return this;
    }

    @JsonProperty("TotalResults")
    public Integer getTotalResults() {
        return totalResults;
    }

    @JsonProperty("TotalResults")
    public void setTotalResults(Integer totalResults) {
        this.totalResults = totalResults;
    }

    public Meta withTotalResults(Integer totalResults) {
        this.totalResults = totalResults;
        return this;
    }

    @JsonProperty("Agreement")
    public String getAgreement() {
        return agreement;
    }

    @JsonProperty("Agreement")
    public void setAgreement(String agreement) {
        this.agreement = agreement;
    }

    public Meta withAgreement(String agreement) {
        this.agreement = agreement;
        return this;
    }

    @JsonProperty("License")
    public String getLicense() {
        return license;
    }

    @JsonProperty("License")
    public void setLicense(String license) {
        this.license = license;
    }

    public Meta withLicense(String license) {
        this.license = license;
        return this;
    }

    @JsonProperty("TermsOfUse")
    public String getTermsOfUse() {
        return termsOfUse;
    }

    @JsonProperty("TermsOfUse")
    public void setTermsOfUse(String termsOfUse) {
        this.termsOfUse = termsOfUse;
    }

    public Meta withTermsOfUse(String termsOfUse) {
        this.termsOfUse = termsOfUse;
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

    public Meta withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(lastUpdated).append(totalResults).append(agreement).append(license).append(termsOfUse).append(additionalProperties).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Meta) == false) {
            return false;
        }
        Meta rhs = ((Meta) other);
        return new EqualsBuilder().append(lastUpdated, rhs.lastUpdated).append(totalResults, rhs.totalResults).append(agreement, rhs.agreement).append(license, rhs.license).append(termsOfUse, rhs.termsOfUse).append(additionalProperties, rhs.additionalProperties).isEquals();
    }

}
