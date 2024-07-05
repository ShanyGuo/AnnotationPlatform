package com.example.annotation.controller.bucket.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FileVo {
    private String fileName;
    private String fileContent;
}
