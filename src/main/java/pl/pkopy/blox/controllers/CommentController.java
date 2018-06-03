package pl.pkopy.blox.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.pkopy.blox.models.PostEntity;
import pl.pkopy.blox.models.repositories.CommentRepository;
import pl.pkopy.blox.models.services.CommentService;
import pl.pkopy.blox.models.services.LoginService;

@Controller
public class CommentController {
    @Autowired
    CommentRepository commentRepository;
    @Autowired
    LoginService loginService;
    @Autowired
    CommentService commentService;

    @GetMapping("/addComment/{postId}")
    public String addComment(@PathVariable("postId") int postId,
                             Model model) {
        model.addAttribute("login",loginService);
        commentService.setPostId(postId);
        commentService.setAuthor(loginService.getAuthor());

        return "addComment";
    }

    @PostMapping("/addComment")
    public String addCommentPost(@RequestParam("addComment") String addComment) {
        commentService.addComment(addComment);
        return "redirect:/post/" + commentService.getPostId();
    }


}
