package com.noc.smsverify.object;

/**
 * Created by dung on 6/7/2017.
 */

public class TinhObj {
    String name;
    String id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TinhObj() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public TinhObj(String name, String id) {
        this.name = name;
        this.id = id;

    }
}
