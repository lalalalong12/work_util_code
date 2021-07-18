package springdemo.domain.dto;

import lombok.Data;

@Data
public class PasswordInfoDTO {

    private String input;


    private String salt;


    private String algorithm;
}



