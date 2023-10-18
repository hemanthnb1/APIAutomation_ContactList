package Resources;

import com.github.javafaker.Faker;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import java.io.*;
import java.util.Properties;

public class Utils {

    static PrintStream log;

    static {
        try{
            log=new PrintStream(new FileOutputStream("src/test/java/logging.txt"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static String getGlobalValue(String key) throws IOException {
        Properties properties = new Properties();
        FileInputStream file;
        file = new FileInputStream("/Users/testvagrant-1/Codes/contactlist_apiautomation_assignment/app/src/test/java/data.properties");
        properties.load(file);
        return properties.getProperty(key);
    }

    public static RequestSpecification requestSpecBuilder() throws IOException {
        return new RequestSpecBuilder()
                .setBaseUri(getGlobalValue("baseURL"))
                .setContentType(ContentType.JSON)
                .addFilter(RequestLoggingFilter.logRequestTo(log))
                .addFilter(ResponseLoggingFilter.logResponseTo(log))
                .build();
    }

    public static ResponseSpecification responseSpecificationBuilder() {
        return new ResponseSpecBuilder()
                .expectContentType(ContentType.JSON).build();
    }


    public static String getJsonPath(Response response, String key) {
        String resp = response.asString();
        JsonPath jsonPath = new JsonPath(resp);
        return jsonPath.get(key);
    }


    public static String generateEmail(){

        return new Faker().internet().emailAddress();
    }

    public static String generatePassword(){
        return  new Faker().internet().password();
    }

    public  static  String generateFirstname(){
        return  new Faker().name().firstName();
    }

    public  static  String generateLastname(){
        return  new Faker().name().lastName();
    }

    public static  String generatephno(){
        return new Faker().number().digits(10);
    }

}
