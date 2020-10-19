package com.example.demo1.web.rest;

import com.example.demo1.entity.Note;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectMock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.Instant;
import java.util.Collections;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.when;

@QuarkusTest
public class MockNoteResourceTest {

    @InjectMock
    NoteResource resource;

    @BeforeEach
    public void setup() {
        Note n1 = new Note();
        n1.setId(10l);
        n1.setNote("Note 1");
        n1.setOwner("Owner 1");
        n1.setCreatedAt(Instant.now());

        when(resource.findOne(10l)).thenReturn(n1);
        when(resource.findAll()).thenReturn(Collections.EMPTY_LIST);
    }

    @Test
    public void testFindOne() {
        Long id = 10l;

        given()
                .when().get("/notes/id/{id}", id)
                .then()
                .statusCode(200)
                .body("id", equalTo(10));
    }

    @Test
    public void testFindAll() {
        given()
                .when().get("/notes")
                .then()
                .statusCode(200)
                .body("size()", is(0));
    }

}