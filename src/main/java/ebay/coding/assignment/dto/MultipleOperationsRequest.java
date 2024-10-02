package ebay.coding.assignment.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class MultipleOperationsRequest {
    @JsonProperty("initial_value")
    private Number initialValue;
    List<OperationRequest> chainOperations;
}
