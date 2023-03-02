package dev.rashad.springboot.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="Usr")
public class User implements UserDetails {
  @Column(name = "id")
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(name = "username")
  private String username;
  @Column(name = "email")
  private String email;

  @Column(name = "password")
  private String password;

  private String about;
  @Enumerated(EnumType.STRING)
  private Role role;

  @ManyToMany
  @JoinTable(
          name = "Follower_Following",
          joinColumns = {@JoinColumn(name = "channel_id")},
          inverseJoinColumns = {@JoinColumn(name = "follower_id")}
  )
  private List<User> followers = new ArrayList<>();

  @ManyToMany
  @JoinTable(
          name = "Follower_Following",
          joinColumns = {@JoinColumn(name = "follower_id")},
          inverseJoinColumns = {@JoinColumn(name = "channel_id")}
  )
  private List<User> followings = new ArrayList<>();

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return List.of(new SimpleGrantedAuthority(role.name()));
  }

  @Override
  public String getUsername() {
    return email;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }
}
