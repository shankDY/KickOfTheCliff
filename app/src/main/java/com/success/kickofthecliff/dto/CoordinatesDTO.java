package com.success.kickofthecliff.dto;

import com.fasterxml.jackson.annotation.JsonProperty;



public class CoordinatesDTO {
    @JsonProperty("id")//данная анатация говорит, как у нас называются колонки в data base
    private String id;
    @JsonProperty("latitude")
    private String latitude;
    @JsonProperty("longitude")
    private String longitude;
    @JsonProperty("coordinates_id")
    private String coordinates_id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getCoordinates_id() {
        return coordinates_id;
    }

    public void setCoordinates_id(String coordinates_id) {
        this.coordinates_id = coordinates_id;
    }

    //пустой конструктор
    public CoordinatesDTO() {
    }
}
