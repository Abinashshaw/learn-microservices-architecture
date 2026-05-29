package com.Krsna.UserService.entities;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Hotel {
    private String hotelId;

    private String name;

    private String location;

    private String about;
}
