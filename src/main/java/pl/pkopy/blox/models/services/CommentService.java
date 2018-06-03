package pl.pkopy.blox.models.services;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import pl.pkopy.blox.models.CommentEntity;
import pl.pkopy.blox.models.repositories.CommentRepository;
import pl.pkopy.blox.models.repositories.PostRepository;

@Service
@Scope(scopeName = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
@Getter
@Setter
public class CommentService {

    @Autowired
    PostRepository postRepository;
    @Autowired
    CommentRepository commentRepository;

private int postId;
private String author;

    public CommentService(){

    }

    public void addComment(String text){
        CommentEntity commentEntity = new CommentEntity();
        commentEntity.setPost(postRepository.findById(postId).orElseThrow(IllegalStateException::new));
        commentEntity.setAuthor(author);
        commentEntity.setContent(text);

       commentRepository.save(commentEntity);

    }
}
