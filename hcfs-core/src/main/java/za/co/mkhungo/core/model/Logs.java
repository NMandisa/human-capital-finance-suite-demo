package za.co.mkhungo.core.model;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;

/**
 * @author Noxolo.Mkhungo
 */

@Data
@Builder
@AllArgsConstructor
@Entity
@Table(name = "logs",schema = "db_hcfs_core")
public class Logs {

    @Id
    @SequenceGenerator(name = "log_id_generator", sequenceName = "sequence_log_id", allocationSize = 1,initialValue = 10)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "log_id_generator")
    @Column(name = "log_id")
    private Long logId;
    @Column(name = "log_level")
    private String logLevel;
    @Column(name = "process_id")
    private String processId;
    @Column(name = "thread_id")
    private String threadId;
    @Column(name = "log_date")
    private Date logDate;

    //[%highlight{${LOG_LEVEL_PATTERN:-%5p}}{FATAL=red,ERROR=red,WARN=yellow,INFO=green,DEBUG=green,TRACE=green}] %d{yyyy-MM-dd HH:mm:ss.SSS} [%style{%pid}{magenta}] [%style{%thread}{bright,blue}] %style{%c{1.1.1.*}}{cyan,bright} - %m%n%ex %throwable{short.methodName}

    public Logs(){
        super();
    }
}
