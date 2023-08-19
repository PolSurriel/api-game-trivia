package com.example.trivia.api.v1.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import lombok.Data;

@ApiModel(description = "Awnser request")
@Data
public class AwnserRequest {
    @JsonProperty("answerId")
    private Long AnswerId;
}
