package pl.pkopy.blox.models.forms;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
@Setter
public class LoginForm {
    @NotEmpty
    @Size(min = 6)
    private String user;
    @NotEmpty
    @Size(min = 6)
    private String password;

    public LoginForm(){

    }

}
