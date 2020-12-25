package br.com.adriane.demo.jaxrs

import org.glassfish.jersey.server.ResourceConfig
import org.springframework.stereotype.Component

@Component
class HelloConfig: ResourceConfig() {

    init {
        registerEndpoints()
    }

    private fun registerEndpoints() {
        register(HelloResource::class.java)
    }
}