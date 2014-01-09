package org.surfnet.oaaas.model;

import org.codehaus.jackson.annotate.JsonProperty;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * ResourceOwnerToScope和AccessToken多对多的中间表
 * User: zhangxiaojie
 * Date: 1/9/14
 * Time: 11:49
 * To change this template use File | Settings | File Templates.
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "RESOURCEOWNERTOSCOPE_TO_ACCESSTOKEN")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
public class ResourceOwnerScopeToAccessToken {
    @Id
    @GeneratedValue
    @JsonProperty
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "ro_to_scope_id", nullable = false)
    @XmlTransient
    private ResourceOwnerToScope resourceOwnerToScope;

    @ManyToOne(optional = false)
    @JoinColumn(name = "access_token_id", nullable = false)
    @XmlTransient
    private AccessToken accessToken;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ResourceOwnerToScope getResourceOwnerToScope() {
        return resourceOwnerToScope;
    }

    public void setResourceOwnerToScope(ResourceOwnerToScope resourceOwnerToScope) {
        this.resourceOwnerToScope = resourceOwnerToScope;
    }

    public AccessToken getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(AccessToken accessToken) {
        this.accessToken = accessToken;
    }
}
