package rodrigues.adriane.demo.webfluxerrorlist.error;

import java.util.List;
import lombok.Data;

@Data
public class Error {
    private String code;
    private String target;
    private String message;
    private List<ErrorDetail> detailList;
}
