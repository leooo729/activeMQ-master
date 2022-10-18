package com.example.jms_demo_2.controller.dto.response;

import com.example.jms_demo_2.model.entity.Mgni;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@XmlRootElement
public class MgniListResponse {
    List<Mgni> mgniList;
}
