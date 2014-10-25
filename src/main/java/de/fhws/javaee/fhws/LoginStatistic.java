package de.fhws.javaee.fhws;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Temporal;


@Entity
public class LoginStatistic implements Serializable {
    
    @Id
    @GeneratedValue
    private Long id;
    
    
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date loginTime;
    private String ipAddress;
        
    @ManyToOne
    private FHWSUser fhwsUser;
    
    @PrePersist
    public void prePersist() {
        loginTime = new Date();
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public FHWSUser getFhwsUser() {
        return fhwsUser;
    }

    public void setFhwsUser(FHWSUser fhwsUser) {
        this.fhwsUser = fhwsUser;
    }

    
    
    
}
