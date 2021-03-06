package pl.pkopy.blox.models.services;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import pl.pkopy.blox.models.CategoryEntity;
import pl.pkopy.blox.models.PostEntity;
import pl.pkopy.blox.models.forms.PostForm;
import pl.pkopy.blox.models.repositories.CategoryRepository;
import pl.pkopy.blox.models.repositories.PostRepository;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.Optional;

@Service
@Scope (scopeName = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
@Getter
@Setter
public class PostService {

    @Autowired
    PostRepository postRepository;

    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    LoginService loginService;

    private HttpServletRequest request;
    private String ip;

    public PostService(){
        request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
                .getRequest();
        ip = request.getRemoteAddr();
    }

    public void addPost(PostForm postForm) {
        Optional<CategoryEntity> categoryEntity = categoryRepository.findById(postForm.getCategory());

        PostEntity postEntity = new PostEntity();
        postEntity.setCategory(categoryEntity.orElseThrow(IllegalStateException::new));
        postEntity.setAuthor(loginService.getAuthor());
        postEntity.setArticle(postForm.getArticle());
        postEntity.setTitle(postForm.getTitle());
        postEntity.setComments(Collections.emptyList());
        postEntity.setSenderIp(ip);

        postRepository.save(postEntity);
    }

    public PostRepository getRepository() {
        return postRepository;
    }

}




