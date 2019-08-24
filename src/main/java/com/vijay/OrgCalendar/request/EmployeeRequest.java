package com.vijay.OrgCalendar.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeRequest {
	
	Integer employeeId;
	
	String name;
	
	Integer meetingId;
}
