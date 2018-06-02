package pl.pkopy.blox.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.pkopy.blox.models.forms.LoginForm;
import pl.pkopy.blox.models.repositories.LoginRepository;
import pl.pkopy.blox.models.services.LoginService;

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
        return "login";
    }

    @PostMapping("/login")

    public String loginPost(@ModelAttribute LoginForm loginForm){
        loginService.login(loginForm);

        if(loginService.isLogin()){
            return "redirect:/";
        }else{
            return "redirect:/login";
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
        return "register";

    }

    @PostMapping("/register")
    public String registerPost(@ModelAttribute LoginForm loginForm,
                               Model model){
        model.addAttribute("login", loginService);
        if(loginService.isExist(loginForm)){
            loginService.register(loginForm);

            return "redirect:/login";
        }else{
            return "register";
        }

    }


}
