package za.co.mkhungo.core.model.security;

import jakarta.persistence.*;
import lombok.*;
import za.co.mkhungo.core.listener.AuditingEntityListener;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Noxolo.Mkhungo
 */
@Getter
@Setter
@RequiredArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "user_group")
public class UserGroup implements Serializable {

    @Id
    @SequenceGenerator(name = "user_group_id_generator", sequenceName = "user_group_id_sequence", allocationSize = 1,initialValue = 10)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_group_id_generator")
    @Column(name = "user_group_id")
    private Long userGroupId;

    @Column(name = "user_group_name")
    private String userGroupName;
    @Column(name = "user_group_description")
    private String userGroupDescription;
    @Column(name = "user_group_email")
    private String userGroupEmail;
    public UserGroup(String userGroupName){
        super();
        this.userGroupName=userGroupName;
    }
    public UserGroup (String userGroupName,String userGroupDescription){
        super();
        this.userGroupName=userGroupName;
        this.userGroupDescription=userGroupDescription;
    }

    @OneToMany(
            mappedBy = "userGroup",
            cascade=CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.EAGER
    )
    private Set<Role> roles = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY, cascade=CascadeType.ALL)
    @JoinTable(name = "user_groups_has_user", joinColumns = @JoinColumn(name = "user_group_id", referencedColumnName = "user_group_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id", referencedColumnName = "user_id",foreignKey=@ForeignKey(name = "user_group_user_fk")))
    private User user;
    public void addRole(Role role){
        roles.add(role);
        role.setUserGroup(this);
    }
    public void removeRole(Role role){
        roles.add(role);
        role.setUserGroup(null);
    }

    public Set<Role> retriveRoles( Set<Role> existingRoles){
        return this.roles = existingRoles;
    }
    public void addRoles(Set<Role> roles){
        roles.addAll(retriveRoles(roles));
        this.setRoles(roles);
    }
    public void removeRoles(HashSet<Role> roles){
        roles.removeAll(retriveRoles(roles));
        this.setRoles(null);
    }
}
