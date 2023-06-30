package swith.backend.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class PoseRequestDto {
    private int label;
    private String serialNumber;
    private Boolean wifi;
    private Boolean camera;

    public PoseRequestDto(int label, String serialNumber, Boolean wifi, Boolean camera) {
        this.label = label;
        this.serialNumber = serialNumber;
        this.wifi = wifi;
        this.camera = camera;
    }
}
