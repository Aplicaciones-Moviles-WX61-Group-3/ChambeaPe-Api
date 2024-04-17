package com.digitaldark.ChambeaPe_Api.shared.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

/**
 * Class that handles other http errors
 * @author Ray Del Carmen
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomException extends RuntimeException {
    private HttpStatus status;

    public CustomException(HttpStatus status, String _message) {
        super(_message);
        this.status = status;
    }
}
