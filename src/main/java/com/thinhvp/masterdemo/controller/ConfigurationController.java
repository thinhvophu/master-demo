package com.thinhvp.masterdemo.controller;


import com.thinhvp.masterdemo.dto.Configuration;
import com.thinhvp.masterdemo.dto.RestResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/config")
public class ConfigurationController {

	@GetMapping
	public RestResponse<Configuration> getConfig() {
		Configuration configuration = new Configuration();
		configuration.setAppVersion("App version");
		return new RestResponse<Configuration>().data(configuration);
	}
}
