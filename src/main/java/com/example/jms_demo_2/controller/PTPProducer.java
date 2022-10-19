package com.example.jms_demo_2.controller;


import com.example.jms_demo_2.controller.dto.request.DepositRequest;
import com.example.jms_demo_2.controller.dto.request.SearchMgniRequest;
import com.example.jms_demo_2.controller.dto.response.MgniListResponse;
import com.example.jms_demo_2.controller.dto.response.StatusResponse;
import com.example.jms_demo_2.model.entity.Mgni;
import com.example.jms_demo_2.service.TransferService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.jms.Queue;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.util.List;

@Validated
@RestController
@RequestMapping("/produce")
public class PTPProducer {
    @Autowired
    private JmsTemplate jmsTemplate;
    @Autowired
    private Queue queue;
    @Autowired
    private TransferService transferService;
    private static ObjectMapper mapper = new ObjectMapper();



    @PostMapping
    public Mgni createMgni(@Valid @RequestBody DepositRequest request) throws Exception {

        Mgni mgni = transferService.createMgni(request);
        jmsTemplate(queue, json(mgni));

        return mgni;
    }

    @GetMapping
    public MgniListResponse getAllMgni() throws Exception {
        MgniListResponse response = transferService.getAllMgni();
        jmsTemplate(queue, json(response));
        return response;
    }
    @GetMapping("/search")
    public List<Mgni> searchTargetMgni(@Valid @RequestBody SearchMgniRequest request) throws Exception {
        List<Mgni> mgniList = transferService.searchTargetMgni(request);
        jmsTemplate(queue, json(mgniList));
        return mgniList;
    }

    @PutMapping("/{id}")
    public Mgni updateMgni(@PathVariable @NotEmpty @Pattern(regexp = "^$|(MGI[0-9]{17})", message = "ID格式請輸入：GMI + 17位數字") String id,
                           @Valid @RequestBody DepositRequest request) throws Exception {
        Mgni mgni = transferService.updateMgni(id, request);
        jmsTemplate(queue, json(mgni));

        return mgni;
    }

    @DeleteMapping("/delete/{id}")
    public StatusResponse deleteMgni(@PathVariable @NotEmpty @Pattern(regexp = "^$|(MGI[0-9]{17})", message = "ID格式請輸入：GMI + 17位數字") String id) throws Exception {
        StatusResponse response = transferService.deleteMgni(id);
        jmsTemplate(queue, json(response));
        return response;
    }


    private static String json(Object object) throws Exception {
        mapper.findAndRegisterModules();
        return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(object);
    }

    private void jmsTemplate(Queue queue, String response) {
        jmsTemplate.convertAndSend(queue, response);
    }

}