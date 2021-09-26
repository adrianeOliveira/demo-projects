package rodrigues.adriane.demo.webfluxerrorlist.request;

import lombok.Data;

@Data
public class CreateContactRequest {

    private int id;

    private String name;

    private String email;

    private boolean throwError;
}
