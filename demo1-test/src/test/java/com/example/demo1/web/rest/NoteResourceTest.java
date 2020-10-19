package com.example.demo1.web.rest;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.equalTo;

@QuarkusTest
public class NoteResourceTest {

    @Test
    public void testFindOne() {
        Long id = 1l;
        given()
                .when().get("/notes/id/{id}", id)
                .then()
                .statusCode(200)
                .body("id", equalTo(1));
    }

    @Test
    public void testFindAll() {
        given()
                .when().get("/notes")
                .then()
                .statusCode(200)
                .body("size()", is(4));
    }

}