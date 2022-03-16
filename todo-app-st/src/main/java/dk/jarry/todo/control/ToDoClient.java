package dk.jarry.todo.control;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.json.JsonObject;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.config.inject.ConfigProperty;

@Dependent
public class ToDoClient {

	@Inject
    @ConfigProperty(name = "todoBaseUri", defaultValue = "http://localhost:8080") 
    String toDoBaseUri;
	
    WebTarget target;
    Client client;

    @PostConstruct
    public void init(){
        this.client = ClientBuilder.newClient();
        this.target = client.target(toDoBaseUri);
    }

    @PreDestroy
    public void destroy(){
        this.client.close();
    }

    public JsonObject create(JsonObject toDo) {
        
        JsonObject response = target.path("todos") //
                .request() //
                .post(Entity.entity(toDo, MediaType.APPLICATION_JSON_TYPE), JsonObject.class);

        return response;        
    }
	

}
