package com.samir.has.api.object.person;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 */
public class Manager {

    @JsonProperty("login") private String login;
    @JsonProperty("password") private String password;

}
