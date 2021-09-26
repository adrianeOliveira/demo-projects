package rodrigues.adriane.demo.webfluxerrorlist.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table
public class Contact {

    @Id
    private int id;

    private String name;

    private String email;
}
