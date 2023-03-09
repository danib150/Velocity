package com.velocitypowered.api.proxy;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize(using = ApiProfileSerializer.class)
public class ApiProfile {
    @JsonProperty("name")
    String name;
    @JsonProperty("id")
    String id;
    @JsonGetter("name")
    public String getName(){
        return name;
    }
    @JsonGetter("id")
    public String getId(){
        return id;
    }
}
