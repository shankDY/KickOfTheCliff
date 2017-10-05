package com.success.kickofthecliff.dto;

/** Ловим данные с сервера **/

import com.fasterxml.jackson.annotation.JsonProperty;


public class KickDTO {

    @JsonProperty("id")//данная анатация говорит, как у нас называются колонки в data base
    private String id;
    @JsonProperty("title")
    private String title;
    @JsonProperty("info")
    private String kickInfo;
    @JsonProperty("photo")
    private String photo;

    //пустой конструктор
    public KickDTO() {
    }



    /*setters and getters*/

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getKickInfo() {
        return kickInfo;
    }

    public void setKickInfo(String kickInfo) {
        this.kickInfo = kickInfo;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
}
