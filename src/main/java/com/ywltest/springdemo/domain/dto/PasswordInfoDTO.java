package com.ywltest.springdemo.domain.dto;

import lombok.Data;

import java.util.List;

@Data
public class PasswordInfoDTO {

    private String input;


    private String salt;


    private String algorithm;
}



