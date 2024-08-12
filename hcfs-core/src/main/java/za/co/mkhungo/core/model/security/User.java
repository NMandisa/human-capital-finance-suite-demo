package za.co.mkhungo.core.model.security;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import za.co.mkhungo.core.listener.AuditingEntityListener;

import java.util.*;

/**
 * @author Noxolo.Mkhungo
 */
@Getter
@Setter
@RequiredArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "user")
public class User implements UserDetails {

    @Id
    @SequenceGenerator(name = "user_id_generator", sequenceName = "user_id_sequence", allocationSize = 1,initialValue = 10)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_id_generator")
    @Column(name = "user_id")
    private Long userId;
    @Column(name = "username",length = 25,nullable = false)
    private String username;
    @Column(name = "password",nullable = false)
    private String password;

    @Column(name = "account_expired")
    private Boolean isAccountNonExpired;

    @Column(name = "account_locked")
    private Boolean isAccountNonLocked;

    @Column(name = "credential_expired")
    private Boolean isCredentialsNonExpired;

    @Column(name = "enabled")
    private Boolean isEnabled;

    @OneToMany(mappedBy = "user", cascade=CascadeType.ALL, orphanRemoval = true,fetch = FetchType.EAGER)
    private List<UserGroup> userGroups = new ArrayList<>();
    public User(String username){super();
        this.username=username;
    }
    @Override
    @JoinTable(name = "user_group_privilege",joinColumns =@JoinColumn(name="user_group_id"))
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (UserGroup userGroup : userGroups) {
            authorities.add(new SimpleGrantedAuthority(userGroup.getUserGroupName()));
            for (Role role : userGroup.getRoles()) {
                //ROLE_ADMIN... expected output!
                authorities.add(new SimpleGrantedAuthority("ROLE_" +role.getRoleName()));
                for (Privilege privilege : role.getPrivileges()) {
                    //ADMIN_READ... expected output!
                    authorities.add(new SimpleGrantedAuthority(role.getRoleName()+"_"+privilege.getUserPrivilege()));
                }
            }
        }
        return authorities;
    }
    @Override
    public String getPassword() {
        return password;
    }
    @Override
    public String getUsername() {
        return username;
    }
    @Override
    public boolean isAccountNonLocked() {
        return isAccountNonLocked;
    }
    @Override
    public boolean isCredentialsNonExpired() {
        return isCredentialsNonExpired;
    }
    @Override
    public boolean isEnabled() {return isEnabled;}
    @Override
    public boolean isAccountNonExpired() {return isAccountNonExpired;}
    public boolean isAccountNonLocked(Boolean isAccountNonLocked) {
        return this.isAccountNonLocked=isAccountNonLocked;
    }
    public boolean isAccountNonExpired(Boolean isAccountNonExpired) {
        return this.isAccountNonExpired=isAccountNonExpired;
    }
    public boolean isCredentialsNonExpired(Boolean isCredentialsNonExpired) {
        return this.isCredentialsNonExpired=isCredentialsNonExpired;
    }
    public boolean isEnabled(Boolean isEnabled) {return this.isEnabled=isEnabled;}

    public void addUserGroup(UserGroup userGroup){
        userGroups.add(userGroup);
        userGroup.setUser(this);
    }
    public void removeUserGroup(UserGroup userGroup){
        userGroups.remove(userGroup);
        userGroup.setUser(null);
    }
}
