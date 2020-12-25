package br.com.adriane.demo.jaxrs.config

import br.com.adriane.demo.jaxrs.rest.resource.ContactResource
import org.glassfish.jersey.server.ResourceConfig
import org.springframework.stereotype.Component

@Component
class JaxRsConfig: ResourceConfig() {

    init {
        registerEndpoints()
    }

    private fun registerEndpoints() {
        register(ContactResource::class.java)
    }
}