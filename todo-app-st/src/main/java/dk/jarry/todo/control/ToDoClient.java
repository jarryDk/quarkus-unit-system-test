package dk.jarry.todo.control;

import java.io.Closeable;
import java.io.IOException;

import javax.enterprise.context.Dependent;
import javax.json.JsonObject;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

@Dependent
public class ToDoClient implements Closeable {

    WebTarget target;
    Client client;

    public ToDoClient(){
        this.client = ClientBuilder.newClient();
        this.target = client.target("http://localhost:8080/todos");
    }

    public JsonObject create(JsonObject toDo) {

        JsonObject response = target //
                .request() //
                .post(Entity.entity(toDo, MediaType.APPLICATION_JSON_TYPE), JsonObject.class);

        return response;        
    }

	@Override
	public void close() throws IOException {
		this.client.close();		
	}

}
