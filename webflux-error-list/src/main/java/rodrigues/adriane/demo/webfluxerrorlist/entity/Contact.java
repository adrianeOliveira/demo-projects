package rodrigues.adriane.demo.webfluxerrorlist.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;
import rodrigues.adriane.demo.webfluxerrorlist.ContactRepository;

@Data
@Table
@AllArgsConstructor
@NoArgsConstructor
public class Contact {

    @Id
    private Integer id;

    private String name;

    private String email;

    public static Contact newContact(final String name, final String email) {
        return new Contact(null, name, email) ;
    }
}
