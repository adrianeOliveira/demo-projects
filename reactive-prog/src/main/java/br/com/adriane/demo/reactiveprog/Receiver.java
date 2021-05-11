package br.com.adriane.demo.reactiveprog;

import org.springframework.stereotype.Component;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class Receiver {

    public void receiveMessage(ContactMessage msg) {
      log.info("Mensagem >> {}", msg);
    }
}
