package tests;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;
import dto.RequestDto;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.jayway.restassured.RestAssured.given;

public class UpDate_Super_hero {
    private static int id;
    RequestDto request;

    @BeforeMethod()
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
    public void testUpdateSuperheroById() {
        // Get the superhero by ID
        RestAssured.baseURI = "https://superhero.qa-test.csssr.com";

        Response responseGet = given()
                .when()
                .get("/superheroes/" + id);
        Assert.assertEquals(responseGet.getStatusCode(),200);
        System.out.println("Response body: " + responseGet.getBody().asString());
        System.out.println("Status code: " + responseGet.getStatusCode());
        request = RequestDto.builder()
                .id(id)
                .birthDate(request.getBirthDate())
                .city(request.getCity())
                .fullName(request.getFullName()+"_Edited")
                .gender(request.getGender())
                .mainSkill(request.getMainSkill())
                .phone(request.getPhone())
                .build();

        Response responseUpDate = given()
                .contentType(ContentType.JSON)
                .body(request)
                .when()
                .put("/superheroes/" + id);
        Assert.assertEquals(responseUpDate.getStatusCode(),200);
        System.out.println("Response body: " + responseUpDate.getBody().asString());
        System.out.println("Status code: " + responseUpDate.getStatusCode());
        System.out.println("Status code: " + request.getFullName());
        Response responseGetAfter = given()
                .when()
                .get("/superheroes/" + id);

        System.out.println("Response body after Update: " + responseGetAfter.getBody().asString());
        System.out.println("Status code: " + responseGetAfter.getStatusCode());
    }
}
