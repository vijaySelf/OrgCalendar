package com.vijay.OrgCalendar.request;

import com.vijay.OrgCalendar.domain.Employee;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MeetingRequest {

	Integer meetingId;
	
	List<Employee> employees;
	
	Date startTime;
	
	Date endTime;
	
	String location;
	
	Integer meetingRoomId;
	
}
