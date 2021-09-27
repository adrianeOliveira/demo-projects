package rodrigues.adriane.demo.webfluxerrorlist;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import rodrigues.adriane.demo.webfluxerrorlist.entity.Contact;

@Repository
public interface ContactRepository extends ReactiveCrudRepository<Contact, Integer> {
}
