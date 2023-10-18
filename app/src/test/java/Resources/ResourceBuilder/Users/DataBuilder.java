package Resources.ResourceBuilder.Users;

import Resources.RequestBody.Contacts.CreateContactPOJO;
import Resources.RequestBody.Contacts.UpdateContactPOJO;
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

    public CreateContactPOJO createContact(String firstName, String lastName, String email, String phone){
        CreateContactPOJO createContactPOJO = new CreateContactPOJO();
        createContactPOJO.setFirstName(firstName);
                createContactPOJO.setLastName(lastName);
                createContactPOJO.setEmail(email);
                createContactPOJO.setBirthdate("1970-01-01");
                createContactPOJO.setPhone(phone);
                createContactPOJO.setStreet1("1 Main St.");
                createContactPOJO.setStreet2("Apartment A");
                createContactPOJO.setCity("Anytown");
                createContactPOJO.setStateProvince("KS");
                createContactPOJO.setPostalCode("12345");
                createContactPOJO.setCountry("USA");
                return  createContactPOJO;

    }

    public UpdateContactPOJO updateContact(String name){
        UpdateContactPOJO updateContactPOJO = new UpdateContactPOJO();
        updateContactPOJO.setFirstName(name);
        return updateContactPOJO;
    }


}
