package pl.pkopy.blox.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "login")
public class LoginEntity {
    @Id
    @GeneratedValue
    private int id;
    private String user;
    private String password;

}
