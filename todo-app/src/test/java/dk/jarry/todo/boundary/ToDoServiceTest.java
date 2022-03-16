package dk.jarry.todo.boundary;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import dk.jarry.todo.entity.ToDo;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import javax.inject.Inject;


@QuarkusTest
public class ToDoServiceTest {

    @Inject
    ToDoService toDoService;

    @Test
    void create(){

        String expectedSubject = "Test subject";
        String expectedBody = "Test body";

        ToDo toDo = new ToDo();
        

        toDo.subject = expectedSubject;
        toDo.body = expectedBody;
        ToDo actual = toDoService.create(toDo);

        assertNotNull(actual);

        String actualSubject = actual.subject;
        String actualBody = actual.body;

        assertEquals(expectedSubject, actualSubject);
        assertEquals(expectedBody, actualBody);
        assertNotNull(actual.id);
        System.out.println(actual.id);

    }

}