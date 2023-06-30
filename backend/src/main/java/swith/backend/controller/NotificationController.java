package swith.backend.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import swith.backend.domain.Pose;
import swith.backend.domain.User;
import swith.backend.dto.PoseRequestDto;
import swith.backend.repository.PoseRepository;
import swith.backend.repository.UserRepository;
import swith.backend.service.NotificationService;

@RestController
@RequestMapping("/notifications")
@RequiredArgsConstructor
public class NotificationController {
    private final NotificationService notificationService;
    private final UserRepository userRepository;
    private final PoseRepository poseRepository;

    @GetMapping(value = "/subscribe/{serialNumber}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public SseEmitter subscribe(
            @PathVariable String serialNumber) {
        User user = userRepository.findBySerialNumber(serialNumber).get();
        Long id = user.getId();

        return notificationService.subscribe(id);
    }

    @PostMapping("/send-data")
    public void sendDataTest(@RequestBody PoseRequestDto poseRequestDto) {
        String serialNumber = poseRequestDto.getSerialNumber();
        int Log = poseRequestDto.getLabel();
        User user = userRepository.findBySerialNumber(serialNumber).get();
        Long id = user.getId();

        Pose pose = Pose.builder()
                .label(poseRequestDto.getLabel())
                .wifi(poseRequestDto.getWifi())
                .camera(poseRequestDto.getCamera())
                .build();
        pose.label_to_string(poseRequestDto.getLabel());
        pose.setUser(user);

        poseRepository.save(pose);

        notificationService.notify(id, poseRequestDto);
    }
}
