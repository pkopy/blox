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

    @GetMapping("/")

    public String index(Model model){
        model.addAttribute("allPosts", postRepository.findAllByOrderByIdDesc());



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
        PostEntity postEntity = new PostEntity();
        model.addAttribute("allCategories", categoryRepository.findAll());
        postService.addPost(postForm);
        return "index";
    }

    @GetMapping("/addCategory")
    public String addCategoryGet(Model model){
        model.addAttribute("allCategories", categoryRepository.findAll());
        return "addCategory";
    }

    @PostMapping("/addCategory")
    public String addCategory(@RequestParam("addCategory") String addCategory, Model model){
        categoryService.addCategory(addCategory);

        return "redirect:/";


    }

    @GetMapping("/post/{postId}")
    public String displayPost(@PathVariable("postId") int postId,
                              Model model){
        model.addAttribute("post", postRepository.findById(postId).orElseThrow(IllegalStateException::new));
//        System.out.println(postRepository.findById(postId));
        return "post";
    }
}
