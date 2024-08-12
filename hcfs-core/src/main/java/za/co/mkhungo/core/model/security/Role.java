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
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "user_role")
public class Role implements Serializable {

    @Id
    @SequenceGenerator(name = "user_role_id_generator", sequenceName = "user_role_id_sequence", allocationSize = 1,initialValue = 10)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_role_id_generator")
    @Column(name = "user_role_id") private Long userRoleId;
    @Column(name = "role_name") private String roleName;
    @Column(name = "user_description") private String roleDescription;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "role", cascade=CascadeType.ALL, orphanRemoval = true)
    private Set<Privilege> privileges= new HashSet<>();

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinTable(name = "user_group_has_roles", joinColumns = @JoinColumn(name = "user_role_id", referencedColumnName = "user_role_id"),
            inverseJoinColumns = @JoinColumn(name = "user_group_id", referencedColumnName = "user_group_id",foreignKey=@ForeignKey(name = "role_user_group_fk")))
    private UserGroup userGroup;
    public Role (String roleName){super();this.roleName=roleName;}
    public Role (String roleName,String roleDescription){super();this.roleName=roleName;this.roleDescription=roleDescription;}
    public void addPrivilege(Privilege privilege){privileges.add(privilege);privilege.setRole(this);}
    public void removeProduct(Privilege privilege){privileges.remove(privilege);privilege.setRole(null);}
}
