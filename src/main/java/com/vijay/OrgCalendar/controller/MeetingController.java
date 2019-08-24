package com.vijay.OrgCalendar.controller;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.vijay.OrgCalendar.exception.EmployeeAlreadyPresentException;
import com.vijay.OrgCalendar.exception.NoMeetingsForEmployeeException;
import com.vijay.OrgCalendar.request.EmployeeRequest;
import com.vijay.OrgCalendar.request.MeetingRequest;
import com.vijay.OrgCalendar.service.MeetingService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/meeting_scheduler/v1/")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class MeetingController {
	
	@Autowired
	MeetingService meetingService;
	
	@GetMapping(path = "/createMeetingScheduler")
	ResponseEntity<?> createMeetingScheduler() {
		return new ResponseEntity<>(meetingService.createMeetingScheduler(), HttpStatus.OK);
	}
	
	@PostMapping(path = "/createMeeting")
	ResponseEntity<?> createMeeting(@RequestBody MeetingRequest meetingRequest) {
		return new ResponseEntity<>(meetingService.createMeetingInvite(meetingRequest), HttpStatus.OK);
	}
	
	@PostMapping(path = "/addEmployee")
	ResponseEntity<?> addEmployee(@RequestBody EmployeeRequest employeeRequest) {
		try {
			return new ResponseEntity<>(meetingService.addEmployeeToMeeting(employeeRequest), HttpStatus.OK);
		} catch(EmployeeAlreadyPresentException e){
			JSONPObject response = new JSONPObject("message", e.getMessage());
			return ResponseEntity.badRequest().body(response);
		}
	}
	
	@GetMapping(path = "/getMeetings")
	ResponseEntity<?> getMeetings(@RequestParam(value = "employee") String employee) {
		try {
			return new ResponseEntity<>(meetingService.getMeetingsForEmployee(employee), HttpStatus.OK);
		} catch(NoMeetingsForEmployeeException e){
			JSONPObject response = new JSONPObject("message", e.getMessage());
			return ResponseEntity.badRequest().body(response);
		}
	}
	
	@GetMapping(path = "/getMeetingRoomCount")
	ResponseEntity<?> getMeetingRoomCount() {
		return new ResponseEntity<>(meetingService.getMeetingRoomsRequired(), HttpStatus.OK);
	}
	
}
