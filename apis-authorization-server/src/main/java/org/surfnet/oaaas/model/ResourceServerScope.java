package org.surfnet.oaaas.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * Resource scope.
 * User: zhangxiaojie
 * Date: 1/7/14
 * Time: 15:51
 * To change this template use File | Settings | File Templates.
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "ResourceServer_scopes")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
public class ResourceServerScope extends AbstractEntity{
    @Column(unique = true, name = "name")
    @NotNull
    private String name;

    @ManyToOne(optional = false)
    @JoinColumn(name = "resourceserver_id", nullable = false, updatable = false)
    @XmlTransient
    private ResourceServer resourceServer;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ResourceServer getResourceServer() {
        return resourceServer;
    }

    public void setResourceServer(ResourceServer resourceServer) {
        this.resourceServer = resourceServer;
    }
}
