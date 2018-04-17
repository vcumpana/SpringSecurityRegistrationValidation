package com.springapp.mvc.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.Collection;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
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

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthDate;

    @NotNull(message = "{user.gender.empty}")
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(unique = true)
    @Email(message = "{user.email.invalid}")
    private String email;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
//    @JoinTable(name = "users_role",
//            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
//            inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")})
    private Collection<Role> roles;

    public void removeRole(RoleName roleName){
        for (Role role: roles){
            if (roleName == role.getRoleName()) {
                roles.remove(role);
                break;
            }
        }
    }

}
