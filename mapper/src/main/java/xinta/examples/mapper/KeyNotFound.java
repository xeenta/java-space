package xinta.examples.mapper;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason="key not found")
public class KeyNotFound extends RuntimeException {
    private static final long serialVersionUID = -8071011625815613946L;
}
