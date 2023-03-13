package com.hoaxify.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "t_users")
public class User  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @Size(min = 3, max= 100)
    private String username;

    @Column
    @Size(min = 3, max= 100)
    private String nickname;

    @Column
    @Pattern(regexp ="^(?=.[a-z])(?=.*[A-Z])(?=.*\\d).*$", message = "Lütfen büyük küçük harf içeren bir password yazın")
    @Size(min = 3, max= 100, message = "Password 30 karakterden daha az olamaz")
    private String password;
    @Column
    private String passwordRepeat;

    @ManyToMany
    @JoinTable(name = "t_user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();


}
