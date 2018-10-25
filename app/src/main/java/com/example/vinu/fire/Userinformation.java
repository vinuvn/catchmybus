package com.example.vinu.fire;

/**
 * Created by vinu on 10/21/2018.
 */

public class Userinformation {

public String no;
    public String r_id;
    public String start;
    public String stop;
    public String time;
    public String type;

    public Userinformation() {
    }

    public Userinformation(String no, String start, String stop, String time, String type) {
        this.no = no;

        this.start = start;
        this.stop = stop;
        this.time = time;
        this.type = type;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getR_id() {
        return r_id;
    }

    public void setR_id(String r_id) {
        this.r_id = r_id;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getStop() {
        return stop;
    }

    public void setStop(String stop) {
        this.stop = stop;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}

