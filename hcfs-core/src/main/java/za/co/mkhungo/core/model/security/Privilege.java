package za.co.mkhungo.core.model.security;

import jakarta.persistence.*;
import lombok.*;
import za.co.mkhungo.core.listener.AuditingEntityListener;
import za.co.mkhungo.core.model.security.enums.Permission;

import java.io.Serializable;

/**
 * @author Noxolo.Mkhungo
 */

@Getter
@Setter
@RequiredArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "user_privilege")
public class Privilege implements Serializable {

    @Id
    @SequenceGenerator(name = "user_privilege_id_generator", sequenceName = "user_privilege_id_sequence", allocationSize = 1,initialValue = 10)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_privilege_id_generator")
    @Column(name = "user_privilege_id")
    private Long userPrivilegeId;
    @Column(name = "user_privilege")
    @Enumerated(EnumType.STRING)
    private Permission userPrivilege;
    @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinTable(name = "privilege_for_role", joinColumns = @JoinColumn(name = "user_privilege_id", referencedColumnName = "user_privilege_id"),
            inverseJoinColumns = @JoinColumn(name = "user_role_id", referencedColumnName = "user_role_id",foreignKey=@ForeignKey(name = "user_privilege_role_fk")
            ))
    private Role role;
    public Privilege(Permission userPrivilege){super();this.userPrivilege=userPrivilege;}


}
