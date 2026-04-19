package com.learning.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthorResponseDTO {

    private int id;
    private String name;
    private String email;
    private String profession;
    private String nationality;
}
