/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50614
 Source Host           : localhost
 Source Database       : apis

 Target Server Type    : MySQL
 Target Server Version : 50614
 File Encoding         : utf-8

 Date: 01/14/2014 15:09:55 PM
*/

SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `ACCESSTOKEN_TO_ACCESSRESTAPI`
-- ----------------------------
DROP TABLE IF EXISTS `ACCESSTOKEN_TO_ACCESSRESTAPI`;
CREATE TABLE `ACCESSTOKEN_TO_ACCESSRESTAPI` (
  `id` bigint(20) NOT NULL,
  `access_rest_api_id` bigint(20) DEFAULT NULL,
  `accesstoken_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `accesstoken_id` (`accesstoken_id`),
  KEY `access_rest_api` (`access_rest_api_id`),
  CONSTRAINT `FK_ACCESSTOKEN` FOREIGN KEY (`accesstoken_id`) REFERENCES `accesstoken` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_REST_API` FOREIGN KEY (`access_rest_api_id`) REFERENCES `access_rest_api` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `AccessToken_scopes`
-- ----------------------------
DROP TABLE IF EXISTS `AccessToken_scopes`;
CREATE TABLE `AccessToken_scopes` (
  `ACCESSTOKEN_ID` bigint(20) DEFAULT NULL,
  `element` varchar(255) DEFAULT NULL,
  KEY `I_CCSSCPS_ACCESSTOKEN_ID` (`ACCESSTOKEN_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `AuthorizationRequest_grantedScopes`
-- ----------------------------
DROP TABLE IF EXISTS `AuthorizationRequest_grantedScopes`;
CREATE TABLE `AuthorizationRequest_grantedScopes` (
  `AUTHORIZATIONREQUEST_ID` bigint(20) DEFAULT NULL,
  `element` varchar(255) DEFAULT NULL,
  KEY `I_THRZCPS_AUTHORIZATIONREQUEST_ID` (`AUTHORIZATIONREQUEST_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `AuthorizationRequest_requestedScopes`
-- ----------------------------
DROP TABLE IF EXISTS `AuthorizationRequest_requestedScopes`;
CREATE TABLE `AuthorizationRequest_requestedScopes` (
  `AUTHORIZATIONREQUEST_ID` bigint(20) DEFAULT NULL,
  `element` varchar(255) DEFAULT NULL,
  KEY `I_THRZCPS_AUTHORIZATIONREQUEST_ID1` (`AUTHORIZATIONREQUEST_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `Client_redirectUris`
-- ----------------------------
DROP TABLE IF EXISTS `Client_redirectUris`;
CREATE TABLE `Client_redirectUris` (
  `CLIENT_ID` bigint(20) DEFAULT NULL,
  `element` varchar(255) DEFAULT NULL,
  KEY `I_CLNTTRS_CLIENT_ID` (`CLIENT_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `OPENJPA_SEQUENCE_TABLE`
-- ----------------------------
DROP TABLE IF EXISTS `OPENJPA_SEQUENCE_TABLE`;
CREATE TABLE `OPENJPA_SEQUENCE_TABLE` (
  `ID` tinyint(4) NOT NULL,
  `SEQUENCE_VALUE` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `RESOURCEOWNERTOSCOPE_TO_ACCESSTOKEN`
-- ----------------------------
DROP TABLE IF EXISTS `RESOURCEOWNERTOSCOPE_TO_ACCESSTOKEN`;
CREATE TABLE `RESOURCEOWNERTOSCOPE_TO_ACCESSTOKEN` (
  `id` bigint(20) DEFAULT NULL,
  `ro_to_scope_id` bigint(20) DEFAULT NULL,
  `access_token_id` bigint(20) DEFAULT NULL,
  KEY `access_token_id` (`access_token_id`),
  KEY `ro_to_scope_id` (`ro_to_scope_id`),
  KEY `access_token_id_2` (`access_token_id`),
  KEY `ro_to_scope_id_2` (`ro_to_scope_id`),
  KEY `access_token_id_3` (`access_token_id`),
  KEY `ro_to_scope_id_3` (`ro_to_scope_id`),
  KEY `access_token_id_4` (`access_token_id`),
  KEY `ro_to_scope_id_4` (`ro_to_scope_id`),
  KEY `access_token_id_5` (`access_token_id`),
  KEY `ro_to_scope_id_5` (`ro_to_scope_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `RESOUREOWNER_TO_SCOPE`
-- ----------------------------
DROP TABLE IF EXISTS `RESOUREOWNER_TO_SCOPE`;
CREATE TABLE `RESOUREOWNER_TO_SCOPE` (
  `id` bigint(20) DEFAULT NULL,
  `scope_id` bigint(20) DEFAULT NULL,
  `resourceowner_id` bigint(20) DEFAULT NULL,
  KEY `scope_id` (`scope_id`),
  KEY `resourceowner_id` (`resourceowner_id`),
  KEY `scope_id_2` (`scope_id`),
  KEY `resourceowner_id_2` (`resourceowner_id`),
  KEY `id` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `ResourceServer_scopes`
-- ----------------------------
DROP TABLE IF EXISTS `ResourceServer_scopes`;
CREATE TABLE `ResourceServer_scopes` (
  `id` bigint(20) NOT NULL,
  `RESOURCESERVER_ID` bigint(20) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `creationDate` datetime DEFAULT NULL,
  `modificationDate` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `I_RSRCCPS_RESOURCESERVER_ID` (`RESOURCESERVER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `SYSTEM_ADMIN`
-- ----------------------------
DROP TABLE IF EXISTS `SYSTEM_ADMIN`;
CREATE TABLE `SYSTEM_ADMIN` (
  `id` bigint(20) NOT NULL,
  `creationDate` datetime DEFAULT NULL,
  `modificationDate` datetime DEFAULT NULL,
  `name` varchar(100) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name_index` (`name`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `access_rest_api`
-- ----------------------------
DROP TABLE IF EXISTS `access_rest_api`;
CREATE TABLE `access_rest_api` (
  `id` bigint(20) NOT NULL,
  `modificationDate` datetime DEFAULT NULL,
  `creationDate` datetime DEFAULT NULL,
  `completeUrl` varchar(200) DEFAULT NULL,
  `resourceserver_id` bigint(20) DEFAULT NULL,
  `resourceowner_id` bigint(20) DEFAULT NULL,
  `resourceowner_to_scope_id` bigint(20) DEFAULT NULL,
  `description` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `url_user_idx` (`id`),
  KEY `url_resource_server_idx` (`resourceserver_id`),
  KEY `resourceowner_id` (`resourceowner_id`),
  KEY `resourceowner_to_scope_id` (`resourceowner_to_scope_id`),
  CONSTRAINT `FK_URL_ROS` FOREIGN KEY (`resourceowner_to_scope_id`) REFERENCES `RESOUREOWNER_TO_SCOPE` (`id`),
  CONSTRAINT `FK_URL_RS` FOREIGN KEY (`resourceserver_id`) REFERENCES `resourceserver` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `accesstoken`
-- ----------------------------
DROP TABLE IF EXISTS `accesstoken`;
CREATE TABLE `accesstoken` (
  `id` bigint(20) NOT NULL,
  `creationDate` datetime DEFAULT NULL,
  `modificationDate` datetime DEFAULT NULL,
  `encodedPrincipal` text,
  `expires` bigint(20) DEFAULT NULL,
  `refreshToken` varchar(255) DEFAULT NULL,
  `token` varchar(255) DEFAULT NULL,
  `client_id` bigint(20) NOT NULL,
  `resourceowner_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `U_CCSSTKN_REFRESHTOKEN` (`refreshToken`),
  UNIQUE KEY `U_CCSSTKN_TOKEN` (`token`),
  KEY `I_CCSSTKN_CLIENT` (`client_id`),
  KEY `resourceowner_id` (`resourceowner_id`),
  CONSTRAINT `resource_ownerId` FOREIGN KEY (`resourceowner_id`) REFERENCES `resource_owner` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `authorizationrequest`
-- ----------------------------
DROP TABLE IF EXISTS `authorizationrequest`;
CREATE TABLE `authorizationrequest` (
  `id` bigint(20) NOT NULL,
  `creationDate` datetime DEFAULT NULL,
  `modificationDate` datetime DEFAULT NULL,
  `authState` varchar(255) DEFAULT NULL,
  `authorizationCode` varchar(255) DEFAULT NULL,
  `encodedPrincipal` text,
  `redirectUri` varchar(255) DEFAULT NULL,
  `responseType` varchar(255) DEFAULT NULL,
  `state` varchar(255) DEFAULT NULL,
  `client_id` bigint(20) NOT NULL,
  `resourceowner_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `I_THRZQST_CLIENT` (`client_id`),
  KEY `resourceowner_id` (`resourceowner_id`),
  CONSTRAINT `FK_ACCESS_TOKEN_ID` FOREIGN KEY (`resourceowner_id`) REFERENCES `resource_owner` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `client`
-- ----------------------------
DROP TABLE IF EXISTS `client`;
CREATE TABLE `client` (
  `id` bigint(20) NOT NULL,
  `creationDate` datetime DEFAULT NULL,
  `modificationDate` datetime DEFAULT NULL,
  `clientId` varchar(255) DEFAULT NULL,
  `contactEmail` varchar(255) DEFAULT NULL,
  `contactName` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `expireDuration` bigint(20) DEFAULT NULL,
  `clientName` varchar(255) DEFAULT NULL,
  `allowedImplicitGrant` bit(1) DEFAULT NULL,
  `allowedClientCredentials` bit(1) DEFAULT NULL,
  `secret` varchar(255) DEFAULT NULL,
  `skipConsent` bit(1) DEFAULT NULL,
  `includePrincipal` bit(1) DEFAULT NULL,
  `thumbNailUrl` varchar(255) DEFAULT NULL,
  `useRefreshTokens` bit(1) DEFAULT NULL,
  `resourceserver_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `U_CLIENT_CLIENTID` (`clientId`),
  KEY `I_CLIENT_RESOURCESERVER` (`resourceserver_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `client_attributes`
-- ----------------------------
DROP TABLE IF EXISTS `client_attributes`;
CREATE TABLE `client_attributes` (
  `client_id` bigint(20) DEFAULT NULL,
  `attribute_name` varchar(255) NOT NULL,
  `attribute_value` varchar(255) DEFAULT NULL,
  KEY `I_CLNTBTS_CLIENT_ID` (`client_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `client_scope`
-- ----------------------------
DROP TABLE IF EXISTS `client_scope`;
CREATE TABLE `client_scope` (
  `id` bigint(20) DEFAULT NULL,
  `client_id` bigint(20) DEFAULT NULL,
  `scope_id` bigint(20) DEFAULT NULL,
  KEY `scope_id` (`scope_id`),
  KEY `clientId` (`client_id`),
  KEY `scope_id_2` (`scope_id`),
  KEY `client_id` (`client_id`),
  CONSTRAINT `FK_CLIENT` FOREIGN KEY (`client_id`) REFERENCES `client` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_SCOPE` FOREIGN KEY (`scope_id`) REFERENCES `ResourceServer_scopes` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `resource_owner`
-- ----------------------------
DROP TABLE IF EXISTS `resource_owner`;
CREATE TABLE `resource_owner` (
  `id` bigint(20) NOT NULL,
  `name` varchar(100) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `creationDate` datetime DEFAULT NULL,
  `modificationDate` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `resourceserver`
-- ----------------------------
DROP TABLE IF EXISTS `resourceserver`;
CREATE TABLE `resourceserver` (
  `id` bigint(20) NOT NULL,
  `creationDate` datetime DEFAULT NULL,
  `modificationDate` datetime DEFAULT NULL,
  `contactEmail` varchar(255) DEFAULT NULL,
  `contactName` varchar(255) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `resourceServerKey` varchar(255) DEFAULT NULL,
  `resourceServerName` varchar(255) DEFAULT NULL,
  `owner` varchar(255) DEFAULT NULL,
  `secret` varchar(255) NOT NULL,
  `thumbNailUrl` varchar(255) DEFAULT NULL,
  `serverURL` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `U_RSRCRVR_KEY` (`resourceServerKey`),
  UNIQUE KEY `U_RSRCRVR_OWNER` (`owner`,`resourceServerName`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `schema_version`
-- ----------------------------
DROP TABLE IF EXISTS `schema_version`;
CREATE TABLE `schema_version` (
  `version_rank` int(11) NOT NULL,
  `installed_rank` int(11) NOT NULL,
  `version` varchar(50) NOT NULL,
  `description` varchar(200) NOT NULL,
  `type` varchar(20) NOT NULL,
  `script` varchar(1000) NOT NULL,
  `checksum` int(11) DEFAULT NULL,
  `installed_by` varchar(100) NOT NULL,
  `installed_on` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `execution_time` int(11) NOT NULL,
  `success` tinyint(1) NOT NULL,
  PRIMARY KEY (`version`),
  KEY `schema_version_vr_idx` (`version_rank`),
  KEY `schema_version_ir_idx` (`installed_rank`),
  KEY `schema_version_s_idx` (`success`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

SET FOREIGN_KEY_CHECKS = 1;
