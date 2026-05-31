package com.Krsna.UserService.entities;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Rating {

    private String ratingId;

    private String userId;

    private String hotelId;

    private Integer ratings;

    private String feedback;

    private Hotel hotel;

}
