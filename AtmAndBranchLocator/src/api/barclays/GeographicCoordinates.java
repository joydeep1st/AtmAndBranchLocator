
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
    "Latitude",
    "Longitude"
})
public class GeographicCoordinates {

    @JsonProperty("Latitude")
    private String latitude;
    @JsonProperty("Longitude")
    private String longitude;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("Latitude")
    public String getLatitude() {
        return latitude;
    }

    @JsonProperty("Latitude")
    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public GeographicCoordinates withLatitude(String latitude) {
        this.latitude = latitude;
        return this;
    }

    @JsonProperty("Longitude")
    public String getLongitude() {
        return longitude;
    }

    @JsonProperty("Longitude")
    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public GeographicCoordinates withLongitude(String longitude) {
        this.longitude = longitude;
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

    public GeographicCoordinates withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(latitude).append(longitude).append(additionalProperties).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof GeographicCoordinates) == false) {
            return false;
        }
        GeographicCoordinates rhs = ((GeographicCoordinates) other);
        return new EqualsBuilder().append(latitude, rhs.latitude).append(longitude, rhs.longitude).append(additionalProperties, rhs.additionalProperties).isEquals();
    }

}
