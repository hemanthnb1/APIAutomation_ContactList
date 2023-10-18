package Resources.ResourceBuilder.Users;

import Resources.RequestBody.Users.CreateUsersPOJO;
import Resources.RequestBody.Users.LoginUserPojo;

public class DataBuilder {
    public CreateUsersPOJO createUsers(String firstname, String lastName, String email, String password){
        CreateUsersPOJO createUsersPOJO = new CreateUsersPOJO();
        createUsersPOJO.setFirstName(firstname);
        createUsersPOJO.setLastName(lastName);
        createUsersPOJO.setEmail(email);
        createUsersPOJO.setPassword(password);
        return createUsersPOJO;
    }
    public LoginUserPojo loginUser(String email, String password){
        LoginUserPojo loginUserPojo = new LoginUserPojo();
        loginUserPojo.setEmail(email);
        loginUserPojo.setPassword(password);
        return loginUserPojo;
    }


}
