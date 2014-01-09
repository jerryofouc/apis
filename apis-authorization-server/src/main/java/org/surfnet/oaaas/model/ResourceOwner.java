package org.surfnet.oaaas.model;

import org.codehaus.jackson.annotate.JsonIgnore;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Set;

/**
 * 这个类代表一个系统中的用户，它拥有ResourceServer Api的访问权限
 * User: zhangxiaojie
 * Date: 12/27/13
 * Time: 16:55
 * To change this template use File | Settings | File Templates.
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "resource_owner")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
public class ResourceOwner extends AbstractEntity{
    @Column(unique = true)
    @NotNull
    private String name;

    @Column(unique = true)
    @NotNull
    private String email;


    @JsonIgnore
    @OneToMany(fetch = FetchType.EAGER, cascade = {CascadeType.ALL}, orphanRemoval = true)
    @JoinColumn(name = "resourceowner_id", nullable = false)
    @Valid
    private Set<AccessToken> accessTokens;

    @JsonIgnore
    @OneToMany(fetch = FetchType.EAGER, cascade = {CascadeType.ALL}, orphanRemoval = true)
    @JoinColumn(name = "resourceowner_id", nullable = false)
    @Valid
    private Set<ResourceOwnerToScope> resourceOwnerToScopes;

    public Set<ResourceOwnerToScope> getResourceOwnerToScopes() {
        return resourceOwnerToScopes;
    }

    public void setResourceOwnerToScopes(Set<ResourceOwnerToScope> resourceOwnerToScopes) {
        this.resourceOwnerToScopes = resourceOwnerToScopes;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<AccessToken> getAccessTokens() {
        return accessTokens;
    }

    public void setAccessTokens(Set<AccessToken> accessTokens) {
        this.accessTokens = accessTokens;
    }
}
