package com.aTorreNegra.screen;

import java.util.Comparator;

import com.aTorreNegra.model.YSortable;

public class WorldObjectYComparator implements Comparator<YSortable> {

    @Override
    public int compare(YSortable o1, YSortable o2) {
        if (o1.getWorldY() < o2.getWorldY()) {
            return -1;
        } else if (o1.getWorldY() > o2.getWorldY()) {
            return 1;
        } else if (o1.getWorldY() == o2.getWorldY()) {
            if (o1.getSizeY() < o2.getSizeY()) {
                return 1;
            } else {
                return -1;
            }
        }
        return 0;
    }
}
