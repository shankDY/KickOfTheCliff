package com.success.kickofthecliff.dto;

import com.fasterxml.jackson.annotation.JsonProperty;



public class KickWinterDTO {

    @JsonProperty("winter_id")//данная анатация говорит, как у нас называются колонки в data base
    private String winter_id;
    @JsonProperty("winter_title")
    private String winter_title;
    @JsonProperty("winter_info")
    private String winter_kickInfo;
    @JsonProperty("winter_photo")
    private String winter_photo;

    //пустой конструктор
    public KickWinterDTO() {
    }



    /*setters and getters*/

    public String getWinter_id() {
        return winter_id;
    }

    public void setWinter_id(String winter_id) {
        this.winter_id = winter_id;
    }

    public String getWinter_title() {
        return winter_title;
    }

    public void setTitle(String winter_title) {
        this.winter_title = winter_title;
    }

    public String getWinter_kickInfo() {
        return winter_kickInfo;
    }

    public void setWinter_kickInfo(String winter_kickInfo) {
        this.winter_kickInfo = winter_kickInfo;
    }

    public String getWinter_photo() {
        return winter_photo;
    }

    public void setWinter_photo(String winter_photo) {
        this.winter_photo = winter_photo;
    }
}