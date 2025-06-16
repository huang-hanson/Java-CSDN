package com.fileimport.export.enums;

import lombok.Getter;

@Getter
public enum DepartmentEnum {

    COMPUTER("计算机学院", 1),
    FINANCE("经济学院", 2),
    LAW("法学院", 3),
    ENGLISH("外国语学院", 4),
    MEDICINE("医学院", 5),
    ARTS("艺术学院", 6);

    private String name;
    private Integer code;

    DepartmentEnum(String name, Integer code) {
        this.name = name;
        this.code = code;
    }

    public static DepartmentEnum getEnumByName(String name) {
        for (DepartmentEnum departmentEnum : DepartmentEnum.values()) {
            if (departmentEnum.getName().equals(name)) {
                return departmentEnum;
            }
        }
        return null;
    }

    public static DepartmentEnum getEnumByCode(Integer code) {
        for (DepartmentEnum departmentEnum : DepartmentEnum.values()) {
            if (departmentEnum.getCode().equals(code)) {
                return departmentEnum;
            }
        }
        return null;
    }
}