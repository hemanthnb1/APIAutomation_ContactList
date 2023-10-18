package Resources.ResponseBody.Contacts;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class AddContactResponse {
        @JsonProperty("_id")
        private String id;
        @JsonProperty("firstName")
        private String firstName;
        @JsonProperty("lastName")
        private String lastName;
        @JsonProperty("birthdate")
        private String birthdate;
        @JsonProperty("email")
        private String email;
        @JsonProperty("phone")
        private String phone;
        @JsonProperty("street1")
        private String street1;
        @JsonProperty("street2")
        private String street2;
        @JsonProperty("city")
        private String city;
        @JsonProperty("stateProvince")
        private String stateProvince;
        @JsonProperty("postalCode")
        private String postalCode;
        @JsonProperty("country")
        private String country;
        @JsonProperty("owner")
        private String owner;
        @JsonProperty("__v")
        private int version;
        public  String getfirstName() {
            return firstName;
        }

        public  String getId(){
                return id;
        }

        public String getLastName(){
                return  lastName;
        }

    }





















