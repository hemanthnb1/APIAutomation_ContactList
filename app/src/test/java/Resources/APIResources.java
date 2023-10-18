package Resources;

public enum APIResources {
    CreateUserAPI("/users"),
    GetUserProfileAPI("/users/me"),
    UpdateUserAPI("/users/me"),
    LogOutUserAPI("/users/logout"),
    LogInUserAPI("/users/login"),
    DeleteUserAPI("/users/me"),
    CreateContactAPI("/contacts"),
    DeleteContactAPI("/contacts"),
    UpdateContactAPI("/contacts"),
    GetContactAPI("/contacts"),
    GetContactListAPI("/contacts");



    public String getResource() {
        return resource;
    }

    private final String resource;

    APIResources(String resource) {
        this.resource = resource;
    }
}
