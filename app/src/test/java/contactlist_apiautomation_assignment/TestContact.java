package contactlist_apiautomation_assignment;

import Resources.APIResources;
import Resources.ResourceBuilder.Users.DataBuilder;
import Resources.ResponseBody.Contacts.AddContactResponse;
import Resources.Utils;
import com.github.javafaker.Faker;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;

import static io.restassured.RestAssured.given;

public class TestContact {
    DataBuilder dataBuilder;
    String token =null;
    AddContactResponse addContactResponse;
    Faker faker;
    String firstName;
    String lastName;

    @BeforeClass
    void setupdata() throws IOException {
       firstName=Utils.generateFirstname();
       lastName=Utils.generateLastname();
       new TestUser().setup().shouldTestCreateUser();
       token = TestUser.registerToken;
    }

    @Test(priority = 0,groups = "functional")
    public  void shouldAddContact() throws IOException {
        dataBuilder=new DataBuilder();
                addContactResponse=given()
                        .header("Authorization","Bearer "+token)
                        .spec(Utils.requestSpecBuilder())
                        .body(dataBuilder.createContact(firstName,lastName,Utils.generateEmail(),Utils.generatephno()))
                        .when()
                        .post(APIResources.CreateContactAPI.getResource())
                        .then().spec(Utils.responseSpecificationBuilder())
                        .extract().response().as(AddContactResponse.class);

        Assert.assertEquals(addContactResponse.getfirstName(),firstName);
    }


    @Test(priority = 1,groups = "functional")
    public void  shouldGetContact() throws IOException {
        Response res=
                given()
                        .header("Authorization","Bearer "+token)
                        .spec(Utils.requestSpecBuilder())
                        .when()
                        .get(APIResources.GetContactAPI.getResource())
                        .then()
                        .extract().response();

        Assert.assertEquals(res.getStatusCode(),200);

    }

    @Test(priority = 2,groups = "functional")
    public void  shouldGetContactList() throws IOException {
        Response res=
                given()
                        .header("Authorization","Bearer "+token)
                        .spec(Utils.requestSpecBuilder())
                        .when()
                        .get(APIResources.GetContactListAPI.getResource())
                        .then()
                        .extract().response();

        Assert.assertEquals(res.getStatusCode(),200);

    }




    @Test(priority = 3,groups = "sanity")
    public void shouldUpdateContact() throws IOException {

        addContactResponse=given()
                .header("Authorization","Bearer "+token)
                .spec(Utils.requestSpecBuilder())
                .body(dataBuilder.updateContact("Assasin"))
                .when()
                .patch(APIResources.CreateContactAPI.getResource()+"/"+addContactResponse.getId())
                .then()
                .spec(Utils.responseSpecificationBuilder())
                .extract().response().as(AddContactResponse.class);
        Assert.assertEquals(addContactResponse.getfirstName(),"Assasin");
    }

    @Test(priority = 4,groups = "sanity")
    public void shouldUpdateContactWithAllData() throws IOException {

     addContactResponse=given()
                .header("Authorization","Bearer "+token)
                .spec(Utils.requestSpecBuilder())
                .body(dataBuilder.createContact("Assasin","Creed",Utils.generateEmail(),Utils.generatephno()))
                .when()
                .post(APIResources.CreateContactAPI.getResource())
                .then().spec(Utils.responseSpecificationBuilder())
                .extract().response().as(AddContactResponse.class);

        Assert.assertEquals(addContactResponse.getfirstName(),"Assasin");
        Assert.assertEquals(addContactResponse.getLastName(),"Creed");

    }

    @Test(priority = 5,groups = "sanity")
    public  void shouldDeleteContact() throws IOException {
        Response res =given()
                .header("Authorization","Bearer "+token)
                .spec(Utils.requestSpecBuilder())
                .when()
                .delete(APIResources.DeleteContactAPI.getResource()+"/"+addContactResponse.getId())
                .then()
                .extract().response();
        System.out.println(res.getBody().asString());
    }

}
