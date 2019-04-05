package com.galveston.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class VolunteerRequest {
    private Long userId;
    private Long eventId;
    private String volunteer;
    private String eventDetails;
    private int point;
}
