package tests;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;
import dto.RequestDto;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.jayway.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class Get_hero_By_ID {
    int id;

    @BeforeMethod
    public void preCondition() {
        RestAssured.baseURI = "https://superhero.qa-test.csssr.com";

        RequestDto request = RequestDto.builder()
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
    public void GetHeroByID() {
        Response response= given()
                .contentType(ContentType.JSON)
                .when()
                .get("/superheroes/{id}", id);
        System.out.printf("ID: %d\n", id);
        System.out.println("Response body: " + response.getBody().asString());
        System.out.println("Status code: " + response.getStatusCode());
        assertEquals(200, response.getStatusCode());

    }
}
