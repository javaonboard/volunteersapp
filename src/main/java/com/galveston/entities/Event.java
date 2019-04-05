package com.galveston.entities;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Event {
    private Long eventId;
    private String category;
    private String name;
    private String level;
    private int point;
    private String date;
    private String time;
    private boolean isConfirmed;

}
