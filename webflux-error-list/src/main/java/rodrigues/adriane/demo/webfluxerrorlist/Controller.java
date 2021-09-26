package rodrigues.adriane.demo.webfluxerrorlist;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import rodrigues.adriane.demo.webfluxerrorlist.request.CreateContactRequest;

@RestController
@RequestMapping("/api")
public class Controller {

    @PostMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void createContact(@RequestBody CreateContactRequest request) {

    }
}
