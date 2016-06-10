package io.tankertux;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

public class TankertuxEvent {
    private String name;
    private Integer level;
    private List<String> ids = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public List<String> getIds() {
        return ids;
    }

    public void setIds(List<String> ids) {
        this.ids = ids;
    }
}
