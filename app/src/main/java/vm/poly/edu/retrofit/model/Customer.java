
package vm.poly.edu.retrofit.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Customer {

    @SerializedName("CUS_ID")
    @Expose
    private long cusId;
    @SerializedName("CUS_FULL_NAME")
    @Expose
    private String cusFullName;
    @SerializedName("CUS_FACE_IMAGE")
    @Expose
    private String cusFaceImage;
    @SerializedName("CUS_FROM_DATE")
    @Expose
    private String cusFromDate;
    @SerializedName("CUS_TO_DATE")
    @Expose
    private String cusToDate;
    @SerializedName("CUS_TYPE")
    @Expose
    private Integer cusType;
    @SerializedName("CUS_ZONE_ALLOWED")
    @Expose
    private String cusZoneAllowed;

    public long getCusId() {
        return cusId;
    }

    public void setCusId(Integer cusId) {
        this.cusId = cusId;
    }

    public String getCusFullName() {
        return cusFullName;
    }

    public void setCusFullName(String cusFullName) {
        this.cusFullName = cusFullName;
    }

    public String getCusFaceImage() {
        return cusFaceImage;
    }

    public void setCusFaceImage(String cusFaceImage) {
        this.cusFaceImage = cusFaceImage;
    }

    public String getCusFromDate() {
        return cusFromDate;
    }

    public void setCusFromDate(String cusFromDate) {
        this.cusFromDate = cusFromDate;
    }

    public String getCusToDate() {
        return cusToDate;
    }

    public void setCusToDate(String cusToDate) {
        this.cusToDate = cusToDate;
    }

    public Integer getCusType() {
        return cusType;
    }

    public void setCusType(Integer cusType) {
        this.cusType = cusType;
    }

    public String getCusZoneAllowed() {
        return cusZoneAllowed;
    }

    public void setCusZoneAllowed(String cusZoneAllowed) {
        this.cusZoneAllowed = cusZoneAllowed;
    }

}
