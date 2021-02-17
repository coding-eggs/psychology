package com.lby.psychology.model.enums;

import lombok.Getter;

@Getter
public enum EnumScaleCategories {

    INTERPERSONAL(10,"人际"),
    STUDY(20,"学习"),
    EMPLOY(30,"就业"),
    EMOTION(40,"情感");


    EnumScaleCategories(Integer id, String name) {
        this.id = id;
        this.name = name;
    }


    public static EnumScaleCategories getEnumById(int id) {
        for(EnumScaleCategories e : EnumScaleCategories.values()) {
            if (e.getId() == id)
                return e;
        }
        return null;
    }

    private final Integer id;

    private final String name;

}
