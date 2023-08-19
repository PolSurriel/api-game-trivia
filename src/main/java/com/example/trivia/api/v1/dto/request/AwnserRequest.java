package com.example.trivia.api.v1.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Awnser request
 */
@ApiModel(description = "Awnser request")
@Data
public class AwnserRequest {
    /**
     * Answer ID
     */
    @ApiModelProperty(value = "Answer ID", example = "2")
    @JsonProperty("answerId")
    private Long AnswerId;
}
