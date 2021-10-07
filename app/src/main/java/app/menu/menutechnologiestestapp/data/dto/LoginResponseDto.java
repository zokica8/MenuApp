package app.menu.menutechnologiestestapp.data.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginResponseDto {

    private String status;

    private int code;

    @SerializedName("data")
    @Expose
    private CustomerDataDto customerData;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public CustomerDataDto getCustomerData() {
        return customerData;
    }

    public void setCustomerData(CustomerDataDto customerData) {
        this.customerData = customerData;
    }
}
