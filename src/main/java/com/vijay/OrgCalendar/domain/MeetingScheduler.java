package com.vijay.OrgCalendar.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MeetingScheduler {
	
	List<Meeting> meetingList;
}
