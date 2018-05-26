package pl.pkopy.blox.models.forms;


import lombok.Getter;
import lombok.Setter;
import pl.pkopy.blox.models.CategoryEntity;

import java.util.Locale;

@Getter
@Setter
public class PostForm {
    private String title;
    private String article;
    private String author;
    private int category;
    private String senderIp;

    public PostForm(){

    }
}
