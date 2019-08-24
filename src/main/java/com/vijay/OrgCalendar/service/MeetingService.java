package com.vijay.OrgCalendar.service;

import com.vijay.OrgCalendar.domain.Meeting;
import com.vijay.OrgCalendar.domain.MeetingScheduler;
import com.vijay.OrgCalendar.request.EmployeeRequest;
import com.vijay.OrgCalendar.request.MeetingRequest;

import java.util.List;

public interface MeetingService {

	MeetingScheduler createMeetingScheduler();
	
	MeetingScheduler addEmployeeToMeeting(EmployeeRequest employeeRequest);
	
	MeetingScheduler createMeetingInvite(MeetingRequest meetingRequest);
	
	List<Meeting> getMeetingsForEmployee(String employee);
	
	Integer getMeetingRoomsRequired();
	
}
