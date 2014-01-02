package org.surfnet.oaaas.model;

import org.codehaus.jackson.annotate.JsonProperty;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * token和rest api的中间表
 * User: zhangxiaojie
 * Date: 12/30/13
 * Time: 21:46
 * To change this template use File | Settings | File Templates.
 */


@SuppressWarnings("serial")
@Entity
@Table(name = "ACCESSTOKEN_TO_ACCESSRESTAPI")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
public class AccessTokenToAccessRestApi {
    @Id
    @GeneratedValue
    @JsonProperty
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "access_rest_api_id", nullable = false, updatable = false)
    @XmlTransient
    private AccessRestApi accessRestApi;

    @ManyToOne(optional = false)
    @JoinColumn(name = "accesstoken_id", nullable = false, updatable = false)
    @XmlTransient
    private AccessToken accessToken;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AccessRestApi getAccessRestApi() {
        return accessRestApi;
    }

    public void setAccessRestApi(AccessRestApi accessRestApi) {
        this.accessRestApi = accessRestApi;
    }

    public AccessToken getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(AccessToken accessToken) {
        this.accessToken = accessToken;
    }
}
