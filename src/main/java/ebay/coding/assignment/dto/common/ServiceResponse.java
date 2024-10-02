package ebay.coding.assignment.dto.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Generic response wrapper for service responses.
 *
 * @param <T> The type of data to be returned.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ServiceResponse<T> implements Serializable {
    private String statusCode;
    private String message;
    private T data;
    private boolean success;

    /**
     * Returns a success response with the provided data.
     *
     * @param data The data to include in the response.
     * @param <T> The type of data.
     * @return A ServiceResponse with status "200" and success flag true.
     */

    public static <T> ServiceResponse<T> success(T data) {
        return ServiceResponse.<T>builder()
                .statusCode("200")
                .success(true)
                .message("Operation successful")
                .data(data)
                .build();
    }
    /**
     * Returns an error response with a specific code and message.
     *
     * @param code The error status code.
     * @param message The error message.
     * @param <T> The type of data (usually null for errors).
     * @return A ServiceResponse with success flag false.
     */
    public static <T> ServiceResponse<T> error(String code, String message) {
        return ServiceResponse.<T>builder()
                .statusCode(code)
                .success(false)
                .message(message)
                .build();
    }
}
