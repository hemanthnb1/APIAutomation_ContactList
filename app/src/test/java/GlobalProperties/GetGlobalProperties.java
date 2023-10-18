package GlobalProperties;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

public class GetGlobalProperties {

    public static RequestSpecification req;
    public String getGlobalValue(String key) throws IOException {
        Properties properties = new Properties();
        FileInputStream fileInputStream = new FileInputStream("/Users/testvagrant-1/Codes/contactlist_apiautomation_assignment/app/src/test/resources/data.properties");
        properties.load(fileInputStream);
        return properties.getProperty(key);
    }

    public String getJsonPath(Response response, String key){
        String resp = response.asString();
        JsonPath jsonPath = new JsonPath(resp);
        return jsonPath.get(key).toString();
    }

    public RequestSpecification getRequestSpecifications(String resource) throws IOException {
        if(req==null){
            PrintStream printStream = new PrintStream(new FileOutputStream("logFile.txt"));
            req = new RequestSpecBuilder().setBaseUri(getGlobalValue("baseUri")).setContentType(ContentType.JSON).addFilter(RequestLoggingFilter.logRequestTo(printStream))
                    .addFilter(ResponseLoggingFilter.logResponseTo(printStream)).build();
            return req;
        }
        return req;
    }
}
