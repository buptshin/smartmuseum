package com.example.smartmuseum.model;

public class Exhibition {
    private String exhibition_name;  // 展览名称
    private int exhibition_id;  // 展览图片资源
    private String exhibition_state;  // 展览状态
    private int exhibition_passenger_flow;  // 展览客流量
    private int exhibition_like;  // 展览喜欢或不喜欢图片编号（1喜欢，0不喜欢）
    private String exhibition_hall;  // 展览所在展厅
    private double exhibition_time;  // 展厅的停留时间
    private int exhibition_distance;  // 展厅距离你的位置

    public Exhibition() {

    }
    public Exhibition(String name, int id, String state, int passenger_flow, int like, String hall, double time, int distance) {
        this.exhibition_name = name;
        this.exhibition_id = id;
        this.exhibition_state = state;
        this.exhibition_passenger_flow = passenger_flow;
        this.exhibition_like = like;
        this.exhibition_hall = hall;
        this.exhibition_time = time;
        this.exhibition_distance = distance;
    }

    public String getExhibition_name() {
        return exhibition_name;
    }

    public void setExhibition_name(String name) {
        exhibition_name = name;
    }

    public int getExhibition_id() {
        return exhibition_id;
    }

    public void setExhibition_id(int id) {
        exhibition_id = id;
    }

    public String getExhibition_state() {
        return exhibition_state;
    }

    public void setExhibition_state(String state) {
        exhibition_state = state;
    }

    public int getExhibition_passenger_flow() {
        return exhibition_passenger_flow;
    }

    public void setExhibition_passenger_flow(int flow) {
        exhibition_passenger_flow = flow;
    }

    public int getExhibition_like() {
        return exhibition_like;
    }

    public void setExhibition_like(int like) {
        exhibition_like = like;
    }

    public String getExhibition_hall() {
        return exhibition_hall;
    }

    public void setExhibition_hall(String hall) {
        exhibition_hall = hall;
    }

    public double getExhibition_time() {
        return exhibition_time;
    }

    public void setExhibition_time(double time) {
        exhibition_time = time;
    }

    public int getExhibition_distance() {
        return exhibition_distance;
    }

    public void setExhibition_distance(int distance) {
        exhibition_distance = distance;
    }
}
