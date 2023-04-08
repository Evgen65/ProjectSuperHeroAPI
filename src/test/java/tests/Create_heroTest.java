package tests;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import dto.RequestDto;
import dto.ResponseDto;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.jayway.restassured.RestAssured.given;

public class Create_heroTest {
    @BeforeMethod
    public void preCondition() {
        RestAssured.baseURI = "https://superhero.qa-test.csssr.com";
        // RestAssured.basePath="";
    }

    @Test(invocationCount = 4)
    public void create_heroTestPositive() {
        RequestDto request = RequestDto.builder()
                .birthDate("2019-02-21")
                .city("Haifa")
                .fullName("Batman")
                .gender("M")
                .mainSkill("magic")
                .phone("+975225515178")
                .build();
        ResponseDto responseDto = given()
                .body(request)
                .contentType(ContentType.JSON)
                .when()
                .post("superheroes")
                .then()
                .assertThat().statusCode(200)
                .extract()
                .as(ResponseDto.class);
        System.out.println("ID ="+responseDto.getId());
        System.out.println("birthDate : "+responseDto.getBirthDate());
        System.out.println("City  :"+responseDto.getCity());
        System.out.println("FullName  :"+responseDto.getFullName());
        System.out.println("Gender  :"+responseDto.getGender());
        System.out.println("MainSkill  :"+responseDto.getMainSkill());
        System.out.println("Phone : "+responseDto.getPhone());

        System.out.println("========================================================================");

    }
}
