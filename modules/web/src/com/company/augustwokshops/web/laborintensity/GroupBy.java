package com.company.augustwokshops.web.laborintensity;

import com.haulmont.chile.core.datatypes.impl.EnumClass;

enum GroupBy implements EnumClass<String> {
    EMPLOYEE("EMPLOYEE"),
    DATE("DATE");

    private String id;

    GroupBy(String id) {
        this.id = id;
    }

    @Override
    public String getId() {
        return id;
    }

    public static GroupBy fromId(String id) {
        for (GroupBy grade : GroupBy.values()) {
            if (grade.getId().equals(id))
                return grade;
        }
        return null;
    }
}