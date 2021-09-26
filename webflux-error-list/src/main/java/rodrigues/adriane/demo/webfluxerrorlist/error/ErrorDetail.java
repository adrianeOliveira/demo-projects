package rodrigues.adriane.demo.webfluxerrorlist.error;

import java.util.List;
import lombok.Data;

@Data
public class ErrorDetail {
    private String code;
    private String target;
    private String message;
}
