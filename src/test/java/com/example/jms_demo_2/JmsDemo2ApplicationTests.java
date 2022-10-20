package com.example.jms_demo_2;

import com.example.jms_demo_2.controller.TopicProducer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class JmsDemo2ApplicationTests {

	@Autowired
	private TopicProducer topicProducer;
	@Test
	void contextLoads() throws Exception {
		topicProducer.sendMsg("你好");
	}

}
