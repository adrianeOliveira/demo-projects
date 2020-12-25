package br.com.adriane.demo.jaxrs

import org.springframework.stereotype.Component
import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.QueryParam

@Component
@Path("/hello")
class HelloResource {

    @GET
    @Path("/cliche")
    fun helloWordCliche() = "Hello World!"

    @GET
    @Path("/name")
    fun helloWorldName(@QueryParam("name") name: String) = "Hello $name"
}