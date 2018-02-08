package com.assignment.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.assignment.models.PlanRequest;
import com.assignment.models.PlanResponse;
import com.assignment.service.PlanGeneraor;

@RestController
@RequestMapping("/generate-plan")
public class PlanController {

	@Autowired
	private PlanGeneraor planGeneraor;
	
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> addPie(@RequestBody PlanRequest planInput) {
    	PlanResponse planResponse = planGeneraor.generate(planInput);
        return new ResponseEntity<>(planResponse, HttpStatus.OK);
    }
}
