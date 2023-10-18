package contactlist_apiautomation_assignment;
import Resources.APIResources;
import Resources.ResourceBuilder.Users.DataBuilder;
import Resources.ResponseBody.Users.CreateUsersResponsePOJO;
import Resources.ResponseBody.Users.User;
import Resources.Utils;
import com.github.javafaker.Faker;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.put;

public class TestUser {
    Faker faker;
    CreateUsersResponsePOJO createUsersResponseBody;
    User user;

    DataBuilder dataBuilder;
    String registerToken =null;
    String loginToken=null;
    String userFirstName="Hemanth";
    String userLastName="N B";

    String email=Utils.generateEmail();
    String password=Utils.generatePassword();




    @Test(priority = 0)
    public void shouldTestCreateUser() throws IOException {
         dataBuilder = new DataBuilder();
        createUsersResponseBody=given().
                spec(Utils.requestSpecBuilder())
                .body(dataBuilder.createUsers(userFirstName, userLastName, email, password))
                .when().post(APIResources.CreateUserAPI.getResource())
                .then().spec(Utils.responseSpecificationBuilder())
                .extract().response().as(CreateUsersResponsePOJO.class);

        registerToken = createUsersResponseBody.getToken();
        Assert.assertEquals(createUsersResponseBody.getUser().getFirstName(), "Hemanth");
    }

    @Test(priority = 1)
    void shouldGetUser() throws IOException {
        user=given()
                .header("Authorization", "Bearer "+ registerToken)
                .spec(Utils.requestSpecBuilder())
                .when()
                .get(APIResources.GetUserProfileAPI.getResource())
                .then()
                .spec(Utils.responseSpecificationBuilder())
                .extract().response().as(User.class);

        Assert.assertEquals(user.getFirstName(),"Hemanth");
    }

    @Test(priority = 2)
    void shouldLoginUser() throws IOException {

                createUsersResponseBody=given().header("Authorization","Bearer "+registerToken)
                        .spec(Utils.requestSpecBuilder())
                        .body(dataBuilder.loginUser(email,password))
                        .when()
                        .post(APIResources.LogInUserAPI.getResource())
                        .then()
                        .extract().response().as(CreateUsersResponsePOJO.class);
                loginToken=createUsersResponseBody.getToken();
        System.out.println(loginToken);
        Assert.assertEquals(user.getFirstName(),"Hemanth");

    }

    @Test(priority = 3)
    public void shouldTestUpdateUser() throws IOException {
        user=given().
                header("Authorization", "Bearer "+loginToken).
                spec(Utils.requestSpecBuilder())
                .body(dataBuilder.createUsers("Davy", "Jones", email, password))
                .when().patch(APIResources.UpdateUserAPI.getResource())
                .then().spec(Utils.responseSpecificationBuilder())
                .extract().response().as(User.class);

        Assert.assertEquals(user.getFirstName(), "Davy");
    }


    @Test(priority = 4)
    public void shouldLogoutUser() throws IOException {
        Response res=
                given().header("Authorization","Bearer "+loginToken)
                        .spec(Utils.requestSpecBuilder())
                        .when()
                        .post(APIResources.LogOutUserAPI.getResource())
                        .then().extract().response();

        Assert.assertEquals(res.getStatusCode(),200);
    }




}
