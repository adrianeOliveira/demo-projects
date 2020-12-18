package br.com.rabbitmq.rabbitmqproject;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/rabbitmq")
public class SenderResource {

	private static final Logger log = LogManager.getLogger(SenderResource.class);

	@Autowired
	private Sender sender;

	@GetMapping
	public HttpStatus sendMsg() {
		sender.send();
		return HttpStatus.OK;
	}
}
