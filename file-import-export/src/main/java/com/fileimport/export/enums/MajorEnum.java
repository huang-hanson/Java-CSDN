package com.fileimport.export.enums;

import lombok.Getter;

@Getter
public enum MajorEnum {

    COMPUTER_SCIENCE_TECHNOLOGY("计算机科学与技术",1),
    SOFTWARE("软件工程",2),
    ECONOMICS("经济学",3),
    LAW("法学",4),
    CLINICAL("临床医学",5),
    MUSICAL_PERFORMANCES("音乐表演",6);

    private String name;
    private Integer code;

    MajorEnum(String name, Integer code) {
        this.name = name;
        this.code = code;
    }
}