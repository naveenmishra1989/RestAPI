package com.example.restapidevelopment.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.Size;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("Represent list of Book's Details")
public class Book {
    @NonNull
    @Size(min = 4)
   private Long id;
    String name;
    @Size(min = 3)
    @ApiModelProperty("Author name should be 3 characters ")
    String author;
}
