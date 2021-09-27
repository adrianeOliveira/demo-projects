package rodrigues.adriane.demo.webfluxerrorlist;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import rodrigues.adriane.demo.webfluxerrorlist.entity.Contact;

@Slf4j
@Service
@RequiredArgsConstructor
public class ContactService {

    private final ContactRepository contactRepository;

    public Mono<Contact> saveContact(final Contact contact) {
        log.info("save contact {}", contact);
        return contactRepository.save(contact);
    }

    public Mono<Contact> findContact(final Integer id) {
        return contactRepository.findById(id);
    }
}
