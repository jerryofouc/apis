package org.surfnet.oaaas.model;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * APIS系统的管理员bean
 * User: zhangxiaojie
 * Date: 1/2/14
 * Time: 17:14
 * To change this template use File | Settings | File Templates.
 */
@SuppressWarnings("serial")
@Entity
@XmlRootElement
@Table(name = "SYSTEM_ADMIN")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class SystemAdminstrator extends AbstractEntity{
    private String name;
    private String password;
    private String email;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
