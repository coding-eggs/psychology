package com.lby.psychology.model.enums;

import lombok.Getter;

import java.io.Serializable;

/**
 * @program: MyBlog
 * @description: 性别枚举
 * @author: Ailuoli
 * @create: 2019-03-28 19:57
 **/

@Getter
public enum EnumSex implements Serializable {

    MAN((short)10,"男"),
    WOMAN((short)20,"女");

    private final Short id;
    private final String name;

    EnumSex(short id, String name) {
        this.id=id;
        this.name = name;
    }


    public static EnumSex getEnumById(int id) {
        for(EnumSex enumSex : EnumSex.values()) {
            if (enumSex.getId() == id)
                return enumSex;
        }
        return null;
    }
}

