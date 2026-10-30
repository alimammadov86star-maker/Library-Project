package com.example.demo.model.dto.author;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AuthorRequest {
    private Long id;
    @NotBlank
    private String fullname;
    @NotBlank
    private String country;
    @NotBlank
    private String style;

}
