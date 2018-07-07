
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
    "AddressLine",
    "BuildingNumber",
    "StreetName",
    "TownName",
    "CountrySubDivision",
    "Country",
    "PostCode",
    "GeoLocation"
})
public class PostalAddress {

    @JsonProperty("AddressLine")
    private List<String> addressLine = new ArrayList<String>();
    @JsonProperty("BuildingNumber")
    private String buildingNumber;
    @JsonProperty("StreetName")
    private String streetName;
    @JsonProperty("TownName")
    private String townName;
    @JsonProperty("CountrySubDivision")
    private List<String> countrySubDivision = new ArrayList<String>();
    @JsonProperty("Country")
    private String country;
    @JsonProperty("PostCode")
    private String postCode;
    @JsonProperty("GeoLocation")
    private GeoLocation geoLocation;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("AddressLine")
    public List<String> getAddressLine() {
        return addressLine;
    }

    @JsonProperty("AddressLine")
    public void setAddressLine(List<String> addressLine) {
        this.addressLine = addressLine;
    }

    public PostalAddress withAddressLine(List<String> addressLine) {
        this.addressLine = addressLine;
        return this;
    }

    @JsonProperty("BuildingNumber")
    public String getBuildingNumber() {
        return buildingNumber;
    }

    @JsonProperty("BuildingNumber")
    public void setBuildingNumber(String buildingNumber) {
        this.buildingNumber = buildingNumber;
    }

    public PostalAddress withBuildingNumber(String buildingNumber) {
        this.buildingNumber = buildingNumber;
        return this;
    }

    @JsonProperty("StreetName")
    public String getStreetName() {
        return streetName;
    }

    @JsonProperty("StreetName")
    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public PostalAddress withStreetName(String streetName) {
        this.streetName = streetName;
        return this;
    }

    @JsonProperty("TownName")
    public String getTownName() {
        return townName;
    }

    @JsonProperty("TownName")
    public void setTownName(String townName) {
        this.townName = townName;
    }

    public PostalAddress withTownName(String townName) {
        this.townName = townName;
        return this;
    }

    @JsonProperty("CountrySubDivision")
    public List<String> getCountrySubDivision() {
        return countrySubDivision;
    }

    @JsonProperty("CountrySubDivision")
    public void setCountrySubDivision(List<String> countrySubDivision) {
        this.countrySubDivision = countrySubDivision;
    }

    public PostalAddress withCountrySubDivision(List<String> countrySubDivision) {
        this.countrySubDivision = countrySubDivision;
        return this;
    }

    @JsonProperty("Country")
    public String getCountry() {
        return country;
    }

    @JsonProperty("Country")
    public void setCountry(String country) {
        this.country = country;
    }

    public PostalAddress withCountry(String country) {
        this.country = country;
        return this;
    }

    @JsonProperty("PostCode")
    public String getPostCode() {
        return postCode;
    }

    @JsonProperty("PostCode")
    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public PostalAddress withPostCode(String postCode) {
        this.postCode = postCode;
        return this;
    }

    @JsonProperty("GeoLocation")
    public GeoLocation getGeoLocation() {
        return geoLocation;
    }

    @JsonProperty("GeoLocation")
    public void setGeoLocation(GeoLocation geoLocation) {
        this.geoLocation = geoLocation;
    }

    public PostalAddress withGeoLocation(GeoLocation geoLocation) {
        this.geoLocation = geoLocation;
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

    public PostalAddress withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(addressLine).append(buildingNumber).append(streetName).append(townName).append(countrySubDivision).append(country).append(postCode).append(geoLocation).append(additionalProperties).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof PostalAddress) == false) {
            return false;
        }
        PostalAddress rhs = ((PostalAddress) other);
        return new EqualsBuilder().append(addressLine, rhs.addressLine).append(buildingNumber, rhs.buildingNumber).append(streetName, rhs.streetName).append(townName, rhs.townName).append(countrySubDivision, rhs.countrySubDivision).append(country, rhs.country).append(postCode, rhs.postCode).append(geoLocation, rhs.geoLocation).append(additionalProperties, rhs.additionalProperties).isEquals();
    }

}
