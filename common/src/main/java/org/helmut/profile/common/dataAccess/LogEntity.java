package org.helmut.profile.common.dataAccess;

import javax.persistence.*;
import java.util.Objects;

/**
 * Entity class for table T_LOG
 */
@Entity
@Table(name = "T_LOG")
public class LogEntity {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "class")
    private String className;

    private String message;

    @Enumerated(EnumType.STRING)
    @Column(name = "log_type")
    private LogType logType;

    @Column(name = "additional_info")
    private String additionalInfo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LogType getLogType() {
        return logType;
    }

    public void setLogType(LogType logType) {
        this.logType = logType;
    }

    public String getAdditionalInfo() {
        return additionalInfo;
    }

    public void setAdditionalInfo(String additionalInfo) {
        this.additionalInfo = additionalInfo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LogEntity logEntity = (LogEntity) o;
        return Objects.equals(id, logEntity.id) && Objects.equals(className, logEntity.className) && Objects.equals(message, logEntity.message) && logType == logEntity.logType && Objects.equals(additionalInfo, logEntity.additionalInfo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, className, message, logType, additionalInfo);
    }
}
