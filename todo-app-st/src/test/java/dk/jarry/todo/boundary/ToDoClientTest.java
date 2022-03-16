package dk.jarry.todo.boundary;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import dk.jarry.todo.control.ToDoClient;

import static org.junit.jupiter.api.Assertions.assertEquals;

import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;

@QuarkusTest
public class ToDoClientTest {

    @Inject    
    ToDoClient client;
    
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

    

}