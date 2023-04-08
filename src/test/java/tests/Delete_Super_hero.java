package tests;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;
import dto.RequestDto;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.jayway.restassured.RestAssured.given;

public class Delete_Super_hero {
    private static int id;
    RequestDto request;

    @BeforeMethod
    public void preCondition() {
        RestAssured.baseURI = "https://superhero.qa-test.csssr.com";
        request = RequestDto.builder()
                .birthDate("2019-02-21")
                .city("Haifa")
                .fullName("Batman")
                .gender("M")
                .mainSkill("magic")
                .phone("+975225515178")
                .build();
        Response response = given()
                .body(request)
                .contentType(ContentType.JSON)
                .when()
                .post("/superheroes");
        id = response.jsonPath().getInt("id");
        System.out.printf("ID: %d\n", id);
    }

    @Test(invocationCount = 4)
    public void delete_Super_hero() {

        Response responseGet = given()
                .when()
                .get("/superheroes/" + id);
        // Assert.assertEquals(responseGet.getStatusCode(), 200);
        System.out.println("Response body: " + responseGet.getBody().asString());
        System.out.println("Status code: " + responseGet.getStatusCode());
        Response responseDel = given()
                .body(request)
                .contentType(ContentType.JSON)
                .when()
                .delete("/superheroes/" + id);
        Assert.assertEquals(responseDel.getStatusCode(), 200);
        System.out.println("Response body: " + responseDel.getBody().asString());
        System.out.println("Status code: " + responseDel.getStatusCode());
        Response responseGetAfter = given()
                .when()
                .get("/superheroes/" + id);
        Assert.assertEquals(responseGetAfter.getStatusCode(), 400);
        System.out.println("Response body: " + responseGetAfter.getBody().asString());
        System.out.println("Status code: " + responseGetAfter.getStatusCode());


    }
}
