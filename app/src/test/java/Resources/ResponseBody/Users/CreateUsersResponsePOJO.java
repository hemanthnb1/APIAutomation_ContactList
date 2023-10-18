package Resources.ResponseBody.Users;

public class CreateUsersResponsePOJO {
    private User user;
    private String token;


    public void setUser(User user) {
        this.user = user;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public User getUser() {
        return this.user;
    }

}
