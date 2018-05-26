package pl.pkopy.blox.models;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity

@NoArgsConstructor
@Table(name = "category")
@Getter @Setter
public class CategoryEntity {

    @Id
    @GeneratedValue
    private int id;
    private String name;

    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    List<PostEntity> posts;
}
