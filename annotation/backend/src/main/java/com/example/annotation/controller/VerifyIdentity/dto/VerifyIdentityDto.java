package com.example.annotation.controller.VerifyIdentity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VerifyIdentityDto {
    private  String email;  //邮箱

    private  String fpsId;  //

    private  String knowledgeLevel; //

    private  String name;   //姓名

    private  String stream;  //语言

}
