package com.ssafy.ssalgorithm.model.dto;

public class SolvedDto {
    private String id;
    private String no;
    private String name;
    private String level;
    private String tags;
    private String total_time;
    private String solved_date;
    private String user_id;

    public SolvedDto() {};

    public SolvedDto(String id, String no, String name, String level, String tags, String totalTime, String solvedDate, String userId) {
        this.id = id;
        this.no = no;
        this.name = name;
        this.level = level;
        this.tags = tags;
        this.total_time = totalTime;
        this.solved_date = solvedDate;
        this.user_id = userId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getTotalTime() {
        return total_time;
    }

    public void setTotalTime(String totalTime) {
        this.total_time = totalTime;
    }

    public String getSolvedDate() {
        return solved_date;
    }

    public void setSolvedDate(String solvedDate) {
        this.solved_date = solvedDate;
    }

    public String getUserId() {
        return user_id;
    }

    public void setUserId(String userId) {
        this.user_id = userId;
    }
}
