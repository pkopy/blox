package pl.pkopy.blox.controllers;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.pkopy.blox.models.PostEntity;
import pl.pkopy.blox.models.forms.PostForm;
import pl.pkopy.blox.models.repositories.CategoryRepository;
import pl.pkopy.blox.models.repositories.PostRepository;
import pl.pkopy.blox.models.services.CategoryService;
import pl.pkopy.blox.models.services.LoginService;
import pl.pkopy.blox.models.services.PostService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class MainController {
    @Autowired
    PostRepository postRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    PostService postService;

    @Autowired
    CategoryService categoryService;

    @Autowired
    LoginService loginService;

    @GetMapping("/")

    public String index(Model model){
        model.addAttribute("allPosts", postRepository.findAllByOrderByIdDesc());
        model.addAttribute("login",loginService);



//        return postWithoutOptional.getTitle() + " " +postWithoutOptional.getComments().toString();
        return "index";
    }

    @GetMapping("/addPost")
    public String addPost(Model model){
        model.addAttribute("postForm", new PostForm());
        model.addAttribute("allCategories", categoryRepository.findAll());
        model.addAttribute("login",loginService);

        if(loginService.isLogin()){
            return "addPost";
        }

        return "redirect:/";
    }

    @PostMapping("/addPost")
    public String add(@ModelAttribute PostForm postForm,
                      Model model){
        PostEntity postEntity = new PostEntity();
        model.addAttribute("allCategories", categoryRepository.findAll());
        model.addAttribute("login",loginService);

        postService.addPost(postForm);
        return "redirect:/";
    }

    @GetMapping("/addCategory")
    public String addCategoryGet(Model model){
        model.addAttribute("allCategories", categoryRepository.findAll());
        if(loginService.isLogin()){

        return "addCategory";
        }else{
            return "redirect:/";
        }
    }

    @PostMapping("/addCategory")
    public String addCategory(@RequestParam("addCategory") String addCategory, Model model){
        categoryService.addCategory(addCategory);
        model.addAttribute("login",loginService);

        return "redirect:/";


    }

    @GetMapping("/post/{postId}")
    public String displayPost(@PathVariable("postId") int postId,
                              Model model){
        model.addAttribute("post", postRepository.findById(postId).orElseThrow(IllegalStateException::new));
        model.addAttribute("login",loginService);
//        System.out.println(postRepository.findById(postId));
        return "post";
    }
}
