package com.example.jms_demo_2.controller.dto.response;

import com.example.jms_demo_2.model.entity.Mgni;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class DeleteResponse {
    private String message;
    private Mgni mgni;
}
