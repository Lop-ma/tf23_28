package ru.tinkoff.qa.apitests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.tinkoff.qa.hibernate.apimodels.Category;
import ru.tinkoff.qa.hibernate.apimodels.TagsItem;
import ru.tinkoff.qa.hibernate.apimodels.Pet;

import java.util.List;

public class PetsApiTests {

    Pet petRequest;
    @BeforeEach
    public void beforeEach() {
        Category category = new Category();
        category.setId(1555);
        category.setName("qqq");

        TagsItem tagsItem = new TagsItem();
        tagsItem.setId(1325);
        tagsItem.setName("ring");

        petRequest = new Pet();
        petRequest.setId(1457);
        petRequest.setCategory(category);
        petRequest.setName("million");
        petRequest.setPhotoUrls(List.of("ten"));
        petRequest.setTags(List.of(tagsItem));
        petRequest.setStatus("available");
    }
    @Test
    public void addCodeTest() {
        RestAssured.given()
                .contentType(ContentType.JSON)
                .body(petRequest)
                .post("https://petstore.swagger.io/v2/pet")
                .then().statusCode(200);
    }
    @Test
    public void findCodeTest() {
        RestAssured.given()
                .contentType(ContentType.JSON)
                .body(petRequest)
                .post("https://petstore.swagger.io/v2/pet");
        RestAssured.given()
                .contentType(ContentType.JSON)
                .get("https://petstore.swagger.io/v2/pet/" + petRequest.getId())
                .then().statusCode(200);
    }
    @Test
    public void updateCodeTest() {
        RestAssured.given()
                .contentType(ContentType.JSON)
                .body(petRequest)
                .post("https://petstore.swagger.io/v2/pet");
        RestAssured.given()
                .contentType(ContentType.JSON)
                .body(petRequest)
                .put("https://petstore.swagger.io/v2/pet")
                .then().statusCode(200);
    }
    @Test
    public void deleteCodeTest() {
        RestAssured.given()
                .contentType(ContentType.JSON)
                .body(petRequest)
                .post("https://petstore.swagger.io/v2/pet");
        RestAssured.given()
                .contentType(ContentType.JSON)
                .delete("https://petstore.swagger.io/v2/pet/" + petRequest.getId())
                .then().statusCode(200);
    }
    @AfterEach
    public void afterEach() {
        int status = RestAssured.given()
                .contentType(ContentType.JSON)
                .get("https://petstore.swagger.io/v2/pet/" + petRequest.getId()).statusCode();
        if (status == 200) {
            RestAssured.given()
                    .contentType(ContentType.JSON)
                    .delete("https://petstore.swagger.io/v2/pet/" + petRequest.getId())
                    .then().statusCode(200);
        }
    }
}
