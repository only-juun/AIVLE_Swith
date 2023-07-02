package swith.backend.dto;

public class PoseRespondDto {
    private String log;
    private Boolean wifi;
    private Boolean camera;

    public PoseRespondDto(String log, Boolean wifi, Boolean camera) {
        this.log = log;
        this.wifi = wifi;
        this.camera = camera;
    }
}
