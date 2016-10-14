# backbone-spring-ldap-login

User and login control using Backbone.js and Java Spring LDAP

This app provides functions for user management and login using backbone.js & Java Spring Security with LDAP as an identity provider. It is intended to work as a quickstart for other applications.

UI adopted from the [backbone-webpy-openid](https://github.com/cccarey/backbone-webpy-openid) project. Services adopted from the [backbone-spring-social](https://github.com/cccarey/backbone-spring-social) project.

> **Started:** 2016-10-12  
> **Last Update:** 2016-10-12  
> **Author:** cccarey  

# Notes

## Theme notes

This project uses [Bootstrap](https://getbootstrap.com).

The theme comes from [Bootswatch](https://bootswatch.com).

The social sign-in buttons come from [bootstrap-social on GitHub](https://github.com/lipis/bootstrap-social).
    
# Service API

### /info - GET

Provides basic application information. Sample:

        {
            "version": "v2014228"
        }

### /user - GET

Returns information about the logged in user

        {
            "username": "christian.carey", 
            "firstName": "Christian", 
            "lastName": "Carey", 
            "nickName": "Chris", 
            "email": "....", 
            "active": true, 
            "fullname": "Christian Carey", 
            "password": "", 
            "id": 3
        }

### /user - PUT

Saves user information. See `/user - GET` for request and response payload example.

### /logout - GET

Logs the user out of the application

