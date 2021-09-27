package rodrigues.adriane.demo.webfluxerrorlist;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import rodrigues.adriane.demo.webfluxerrorlist.entity.Contact;
import rodrigues.adriane.demo.webfluxerrorlist.request.CreateContactRequest;
import rodrigues.adriane.demo.webfluxerrorlist.request.FindContactResponse;

@Slf4j
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class Controller {

    private final ContactService contactService;

    @PostMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> createContact(@RequestBody CreateContactRequest request) {
        log.info("create contact {}", request);
        return contactService.saveContact(
            Contact.newContact(request.getName(), request.getEmail())
        )
        .doOnSuccess(contact -> log.info("Created contact {}", contact))
        .then();
    }

    @GetMapping("/{contactId}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<FindContactResponse> findContactById(@PathVariable Integer contactId) {
        return contactService.findContact(contactId)
            .flatMap(contact -> {
                final FindContactResponse response = new FindContactResponse();
                response.setId(contact.getId());
                response.setName(contact.getName());
                response.setEmail(contact.getEmail());
                return Mono.just(response);
            });
    }
}
