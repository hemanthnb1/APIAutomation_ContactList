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
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.put;

public class TestUser {
    CreateUsersResponsePOJO createUsersResponseBody;
    User user;

    DataBuilder dataBuilder;
    public  static String registerToken =null;

    public  static String loginToken=null;
    String userFirstName;
    String userLastName;

    String email;
    String password;

    @BeforeClass
   public TestUser setup(){
         dataBuilder = new DataBuilder();
        userFirstName="Hemanth";
        userLastName="N B";
        email=Utils.generateEmail();
        password=Utils.generatePassword();
        return this;
    }



    @Test(priority = 0,groups = "functional")
    public void shouldTestCreateUser() throws IOException {
        createUsersResponseBody=given().
                spec(Utils.requestSpecBuilder())
                .body(dataBuilder.createUsers(userFirstName, userLastName, email, password))
                .when().post(APIResources.CreateUserAPI.getResource())
                .then().spec(Utils.responseSpecificationBuilder())
                .extract().response().as(CreateUsersResponsePOJO.class);

        registerToken = createUsersResponseBody.getToken();
        Assert.assertEquals(createUsersResponseBody.getUser().getFirstName(), userFirstName);
    }

    @Test(priority = 1,groups = "functional")
    void shouldGetUser() throws IOException {
        user=given()
                .header("Authorization", "Bearer "+ registerToken)
                .spec(Utils.requestSpecBuilder())
                .when()
                .get(APIResources.GetUserProfileAPI.getResource())
                .then()
                .spec(Utils.responseSpecificationBuilder())
                .extract().response().as(User.class);

        Assert.assertEquals(user.getFirstName(),userFirstName);
    }

    @Test(priority = 2,groups = "functional")
    public void shouldLoginUser() throws IOException {

                createUsersResponseBody=given().header("Authorization","Bearer "+registerToken)
                        .spec(Utils.requestSpecBuilder())
                        .body(dataBuilder.loginUser(email,password))
                        .when()
                        .post(APIResources.LogInUserAPI.getResource())
                        .then()
                        .extract().response().as(CreateUsersResponsePOJO.class);
                loginToken=createUsersResponseBody.getToken();

//        System.out.println(loginToken);
        Assert.assertEquals(user.getFirstName(),userFirstName);
//        return  this;

    }

    @Test(priority = 3,groups = "sanity")
    public void shouldTestUpdateUser() throws IOException {
        String updatedFirstName="Davy";
        String updatedLastName="Davy";

        user=given().
                header("Authorization", "Bearer "+loginToken).
                spec(Utils.requestSpecBuilder())
                .body(dataBuilder.createUsers("Davy", "Jones", email, password))
                .when().patch(APIResources.UpdateUserAPI.getResource())
                .then().spec(Utils.responseSpecificationBuilder())
                .extract().response().as(User.class);
        Assert.assertEquals(user.getFirstName(), updatedFirstName);
        userFirstName=updatedFirstName;
    }


    @Test(priority = 4, groups = "sanity")
    public void shouldLogoutUser() throws IOException {
        Response res=
                given().header("Authorization","Bearer "+loginToken)
                        .spec(Utils.requestSpecBuilder())
                        .when()
                        .post(APIResources.LogOutUserAPI.getResource())
                        .then().extract().response();

        Assert.assertEquals(res.getStatusCode(),200);
    }


    @Test(priority = 5,groups = "sanity")
    public  void  shoudDeleteUsers() throws IOException {

        shouldLoginUser();
        Response res=
                given().
                        header("Authorization","Bearer "+loginToken).
                        spec(Utils.requestSpecBuilder())
                        .body(dataBuilder.loginUser(email,password))
                        .when()
                        .delete(APIResources.DeleteUserAPI.getResource())
                        .then()
                        .extract().response();
        Assert.assertEquals(res.getStatusCode(),200);
    }



}
