package pl.pkopy.blox.models.services;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import pl.pkopy.blox.models.LoginEntity;
import pl.pkopy.blox.models.forms.LoginForm;
import pl.pkopy.blox.models.repositories.LoginRepository;

import java.lang.instrument.IllegalClassFormatException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Scope(scopeName = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
@Getter
@Setter
public class LoginService {

    @Autowired
    LoginRepository loginRepository;


    private boolean isLogin;
    private boolean isUserExist;
    private boolean isLoginFail;
    private String author;


    public LoginService(){
        isLogin = false;
        isLoginFail = false;
    }

    public void login(LoginForm loginForm){

        LoginEntity loginEntity = loginRepository.findByUser(loginForm.getUser());

        if(loginEntity != null && loginEntity.getPassword().equals(loginForm.getPassword())){
            isLogin = true;
            author = loginEntity.getUser();
        }else {
            isLogin = false;
        }
    }

    public boolean isExist(LoginForm loginForm) {
        Iterable<LoginEntity> users = loginRepository.findAll();

        for(LoginEntity user : users){
            if (user.getUser().equals(loginForm.getUser())){
                isUserExist = true;
                return false;
            }

        }
        return true;
    }

    public void register(LoginForm loginForm) {

        LoginEntity loginEntity = new LoginEntity();
        loginEntity.setUser(loginForm.getUser());
        loginEntity.setPassword(loginForm.getPassword());

        loginRepository.save(loginEntity);






    }
}
