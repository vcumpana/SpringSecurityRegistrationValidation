package com.springapp.mvc.model;

import com.springapp.mvc.constraints.EmailUniquenessConstraint;
import com.springapp.mvc.constraints.RePasswordConstraint;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Set;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
//@RePasswordConstraint(field = "password",confirmingField = "repeatPassword", message = "{user.repassword.invalid}")
public class User{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    @NotEmpty(message ="{user.name.empty}" )
    private String name;

    @NotEmpty(message ="{user.surname.empty}" )
    private String surname;

    @Size(min = 2, message = "{user.username.invalid}")
    @Column(unique = true)
    private String username;

    @Pattern(regexp = "^(?=.*[A-Z])(?=.*[!@#$&*+=_.])(?=.*[0-9]).(?=\\S+$).{8,}$", message ="{user.password.invalid}" )
    private String password;

    @Transient
    private String repeatPassword;

    private String birthDate;

    @NotNull(message = "{user.gender.empty}")
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(unique = true)
    @Email(message = "{user.email.invalid}")
   // @EmailUniquenessConstraint(message = "{user.email.exists}")
    private String email;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "users_role",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")})
    private Set<Role> roles;

}
