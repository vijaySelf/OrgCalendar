package com.vijay.OrgCalendar.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Meeting {
	
	Integer meetingId;
	
	List<Employee> attendees;
	
	Date startTime;
	
	Date endTime;
	
	Integer meetingRoomId;
	
}
