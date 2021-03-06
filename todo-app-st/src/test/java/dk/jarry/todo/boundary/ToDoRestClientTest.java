package dk.jarry.todo.boundary;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import dk.jarry.todo.control.ToDoRestClient;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;

import org.eclipse.microprofile.rest.client.inject.RestClient;

@QuarkusTest
public class ToDoRestClientTest {

    @Inject
    @RestClient
    ToDoRestClient client;
    
    @Test
    public void create() {

        JsonObjectBuilder createObjectBuilder = Json.createObjectBuilder();
        createObjectBuilder.add("subject", "Subject - test");
        createObjectBuilder.add("body", "Body - test");
        JsonObject todoInput = createObjectBuilder.build();
        
        var todoOutput = this.client.create(todoInput);

        assertEquals(todoInput.getString("subject"), todoOutput.getString("subject"));
        assertEquals(todoInput.getString("body"), todoOutput.getString("body"));

        System.out.println("create - Todo " + todoOutput);
       
    }

    @Test
    public void read() {

        JsonObjectBuilder createObjectBuilder = Json.createObjectBuilder();
        createObjectBuilder.add("subject", "Subject - test");
        createObjectBuilder.add("body", "Body - test");
        
        JsonObject todoInput = createObjectBuilder.build();        
        var todoOutput = this.client.create(todoInput);

        assertEquals(todoInput.getString("subject"), todoOutput.getString("subject"));
        assertEquals(todoInput.getString("body"), todoOutput.getString("body"));

        System.out.println("read - Todo [1] " + todoOutput);

        Integer id = todoOutput.getInt("id");

        todoOutput = this.client.read(id);

        assertEquals(todoInput.getString("subject"), todoOutput.getString("subject"));
        assertEquals(todoInput.getString("body"), todoOutput.getString("body"));

        System.out.println("read - Todo [2] " + todoOutput);
       
    }

    @Test
    public void update() {

        JsonObjectBuilder createObjectBuilder = Json.createObjectBuilder();
        createObjectBuilder.add("subject", "Subject - test");
        createObjectBuilder.add("body", "Body - test");
        
        JsonObject todoInput = createObjectBuilder.build();        
        var todoOutput = this.client.create(todoInput);

        assertEquals(todoInput.getString("subject"), todoOutput.getString("subject"));
        assertEquals(todoInput.getString("body"), todoOutput.getString("body") + "--FF");

        System.out.println("update - Todo [1] " + todoOutput);

        Integer id = todoOutput.getInt("id");

        JsonObjectBuilder todoUpdateBuilder = Json.createObjectBuilder(todoOutput);
        todoUpdateBuilder.add("subject", "new subject");
        var todoUpdated = todoUpdateBuilder.build();
         
        todoOutput = this.client.update(id, todoUpdated);

        assertEquals(todoUpdated.getString("subject"), "new subject");
        assertEquals(todoInput.getString("body"), todoOutput.getString("body"));

        System.out.println("update - Todo [2] " + todoOutput);
       
    }

    @Test
    public void delete() {

        JsonObjectBuilder createObjectBuilder = Json.createObjectBuilder();
        createObjectBuilder.add("subject", "Subject - test");
        createObjectBuilder.add("body", "Body - test");
        
        JsonObject todoInput = createObjectBuilder.build();        
        var todoOutput = this.client.create(todoInput);

        assertEquals(todoInput.getString("subject"), todoOutput.getString("subject"));
        assertEquals(todoInput.getString("body"), todoOutput.getString("body"));

        System.out.println("delete- Todo " + todoOutput);

        Integer id = todoOutput.getInt("id");
       
        this.client.delete(id);

        try{
            todoOutput = this.client.read(id);
        } catch (javax.ws.rs.WebApplicationException we){
            assertTrue(we.getResponse().getStatus() == 404);
        }
       
    }

}