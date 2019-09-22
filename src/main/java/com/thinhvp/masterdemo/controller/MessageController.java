package com.thinhvp.masterdemo.controller;

import com.thinhvp.masterdemo.dto.RestResponse;
import com.thinhvp.masterdemo.dto.SimpleMessage;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api")
public class MessageController {

	@GetMapping(value = "{message}")
	public RestResponse<SimpleMessage> showSimpleMessage(@PathVariable String message) {
		SimpleMessage simpleMessage = new SimpleMessage();
		simpleMessage.setMessage(message);
		return new RestResponse<>(simpleMessage);
	}

	@PostMapping
	public RestResponse<SimpleMessage> postSimpleMessage(@RequestBody SimpleMessage simpleMessage) {
		return new RestResponse<>(simpleMessage);
	}
}
