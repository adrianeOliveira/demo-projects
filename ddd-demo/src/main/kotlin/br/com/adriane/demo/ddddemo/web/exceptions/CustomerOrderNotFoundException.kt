package br.com.adriane.demo.ddddemo.web.exceptions

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.client.HttpStatusCodeException

@ResponseStatus(HttpStatus.NOT_FOUND)
class CustomerOrderNotFoundException : HttpStatusCodeException(HttpStatus.NOT_FOUND)