package ebay.coding.assignment.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class SingleOperationRequest {
    private String op;
    private Number num1;
    private Number num2;
}
