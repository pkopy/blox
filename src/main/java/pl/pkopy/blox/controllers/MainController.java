package pl.pkopy.blox.controllers;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.pkopy.blox.models.PostEntity;
import pl.pkopy.blox.models.forms.PostForm;
import pl.pkopy.blox.models.repositories.CategoryRepository;
import pl.pkopy.blox.models.repositories.PostRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class MainController {
    @Autowired
    PostRepository postRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @GetMapping("/")

    public String index(Model model){
        model.addAttribute("allPosts", postRepository.findAll());



//        return postWithoutOptional.getTitle() + " " +postWithoutOptional.getComments().toString();
        return "index";
    }

    @GetMapping("/addPost")
    public String addPost(Model model){
        model.addAttribute("postForm", new PostForm());
        model.addAttribute("allCategories", categoryRepository.findAll());
        return "addPost";
    }

    @PostMapping("/addPost")
    public String add(@ModelAttribute PostForm postForm,
                      Model model){
        PostEntity postEntity = new PostEntity(postForm);
        model.addAttribute("allCategories", categoryRepository.findAll());
        postRepository.save(postEntity);
        return "index";
    }
}
