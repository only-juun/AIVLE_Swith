package swith.backend.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PoseRespondDto {
    @JsonProperty
    private String log;
    @JsonProperty
    private Boolean wifi;
    @JsonProperty
    private Boolean camera;

    public PoseRespondDto(String log, Boolean wifi, Boolean camera) {
        this.log = log;
        this.wifi = wifi;
        this.camera = camera;
    }
}
