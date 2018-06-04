package pl.pkopy.blox.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import pl.pkopy.blox.models.forms.LoginForm;
import pl.pkopy.blox.models.repositories.LoginRepository;
import pl.pkopy.blox.models.services.LoginService;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
public class LoginController {
    @Autowired
    LoginRepository loginRepository;
    @Autowired
    LoginService loginService;

    @GetMapping("/login")
    public String login(Model model){
        model.addAttribute("allUsers", loginRepository.findAll());
        model.addAttribute("loginForm", new LoginForm());
        model.addAttribute("login", loginService);
        loginService.setLoginFail(false);
        return "login";
    }

    @PostMapping("/login")

    public String loginPost(@ModelAttribute @Valid LoginForm loginForm,
                            BindingResult bindingResult,
                            Model model){
        model.addAttribute("login", loginService);


        loginService.setLoginFail(false);
        if(bindingResult.hasErrors()){
            loginService.setLoginFail(true);

            return "login";
        }
        loginService.login(loginForm);


        if(loginService.isLogin()){
            return "redirect:/";
        }else{
            loginService.setLoginFail(true);
            return "login";
        }

    }

    @GetMapping("/logout")
    public String logout(){

        loginService.setLogin(false);
        return "redirect:/";
    }

    @GetMapping("/register")
    public String register(Model model){

        model.addAttribute("loginForm", new LoginForm());
        model.addAttribute("login", loginService);
        loginService.setLoginFail(false);
        return "register";

    }

    @PostMapping("/register")
    public String registerPost(@ModelAttribute @Valid LoginForm loginForm,
                               BindingResult bindingResult,
                               Model model){

        model.addAttribute("login", loginService);


        loginService.setLoginFail(false);
        if(bindingResult.hasErrors()){
            loginService.setLoginFail(true);
            return "register";
        }
        if(loginService.isExist(loginForm)){
            loginService.register(loginForm);

            return "redirect:/login";
        }else{

            return "register";
        }

    }


}
