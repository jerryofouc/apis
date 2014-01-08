package org.surfnet.oaaas.model;

import org.codehaus.jackson.annotate.JsonProperty;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * ResourceOwner和ResourceServerScope的中间表
 * User: zhangxiaojie
 * Date: 1/8/14
 * Time: 11:18
 * To change this template use File | Settings | File Templates.
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "RESOUREOWNER_TO_SCOPE")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
public class ResourceOwnerToScope {
    @Id
    @GeneratedValue
    @JsonProperty
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "resourceowner_id", nullable = false)
    @XmlTransient
    private ResourceOwner resourceOwner;

    @ManyToOne(optional = false)
    @JoinColumn(name = "scope_id", nullable = false)
    @XmlTransient
    private ResourceServerScope resourceServerScope;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ResourceOwner getResourceOwner() {
        return resourceOwner;
    }

    public void setResourceOwner(ResourceOwner resourceOwner) {
        this.resourceOwner = resourceOwner;
    }

    public ResourceServerScope getResourceServerScope() {
        return resourceServerScope;
    }

    public void setResourceServerScope(ResourceServerScope resourceServerScope) {
        this.resourceServerScope = resourceServerScope;
    }
}
