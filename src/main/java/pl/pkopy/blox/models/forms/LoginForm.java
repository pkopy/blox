package pl.pkopy.blox.models.forms;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginForm {
    private String user;
    private String password;

    public LoginForm(){

    }

}
