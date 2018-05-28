package pl.pkopy.blox.models;


import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.pkopy.blox.models.forms.PostForm;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity

@Getter
@Setter

@Table(name = "post")
public class PostEntity {

    @Id
    @GeneratedValue
    private int id;

    private String title;
    private String article;
    private String author;

    @Column(name = "creation_time")
    private LocalDateTime creationTime;

    @Column(name = "sender_ip")
    private String senderIp;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "category_id")
    private CategoryEntity category;

//    private int postId;

    @OneToMany(mappedBy = "post", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<CommentEntity> comments;

    public PostEntity(){

    }
//
//    public PostEntity(PostForm postForm){
//        setArticle(postForm.getArticle());
//        setAuthor(postForm.getAuthor());
//        setTitle(postForm.getTitle());
//        setCategory(postForm.getCategory());
//        setSenderIp(postForm.getSenderIp());
//    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArticle() {
        return article;
    }

    public void setArticle(String article) {
        this.article = article;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public LocalDateTime getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(LocalDateTime creationTime) {
        this.creationTime = creationTime;
    }

    public CategoryEntity getCategory() {
        return category;
    }

    public void setCategory(CategoryEntity category) {
        this.category = category;
    }

    public List<CommentEntity> getComments() {
        return comments;
    }

    public void setComments(List<CommentEntity> comments) {
        this.comments = comments;
    }
}
