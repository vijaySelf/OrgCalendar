package com.vijay.OrgCalendar.service;

import com.vijay.OrgCalendar.domain.Employee;
import com.vijay.OrgCalendar.domain.Meeting;
import com.vijay.OrgCalendar.domain.MeetingScheduler;
import com.vijay.OrgCalendar.exception.EmployeeAlreadyPresentException;
import com.vijay.OrgCalendar.exception.NoMeetingsForEmployeeException;
import com.vijay.OrgCalendar.request.EmployeeRequest;
import com.vijay.OrgCalendar.request.MeetingRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MeetingServiceImpl implements MeetingService{
	
	private MeetingScheduler meetingScheduler = createNewMeetingScheduler();
	
	@Override
	public MeetingScheduler createMeetingScheduler() {
		return this.meetingScheduler;
	}
	
	@Override
	public MeetingScheduler addEmployeeToMeeting(EmployeeRequest employeeRequest) {
		
		List<Meeting> meetingList =  meetingScheduler.getMeetingList();
		
		for(Meeting meeting : meetingList){
			if(meeting.getMeetingId().equals(employeeRequest.getMeetingId())){
				
				List<Employee> employeesPresent  = meeting.getAttendees();
				
				for(Employee employee : employeesPresent){
					if(employee.getName().equalsIgnoreCase(employeeRequest.getName())){
						throw new EmployeeAlreadyPresentException(employeeRequest.getName() +" already present in meeting "+employeeRequest.getMeetingId());
					}
				}
				
				Employee employee = new Employee();
				employee.setId(employeeRequest.getEmployeeId());
				employee.setName(employeeRequest.getName());
				meeting.getAttendees().add(employee);
			}
		}
		return meetingScheduler;
	}
	
	@Override
	public MeetingScheduler createMeetingInvite(MeetingRequest meetingRequest) {
		
		Meeting meeting = new Meeting();
		meeting.setMeetingId(meetingRequest.getMeetingId());
		meeting.setAttendees(meetingRequest.getEmployees());
		meeting.setStartTime(meetingRequest.getStartTime());
		meeting.setEndTime(meetingRequest.getEndTime());
		meeting.setMeetingRoomId(meetingRequest.getMeetingRoomId());
		meetingScheduler.getMeetingList().add(meeting);
		return meetingScheduler;
	}
	
	@Override
	public List<Meeting> getMeetingsForEmployee(String employee) {
		List<Meeting> meetings = new ArrayList<>();
		
		List<Meeting> meetingsPresent  = meetingScheduler.getMeetingList();
		
		for(Meeting meeting : meetingsPresent){
			List<Employee> employees = meeting.getAttendees();
			
			for(Employee employee1 : employees){
				if(employee1.getName().equalsIgnoreCase(employee)){
					meetings.add(meeting);
				}
			}
		}
		
		if(meetings.size() == 0){
			throw new NoMeetingsForEmployeeException("There are no meetings for "+employee);
		}
		return meetings;
	}
	
	@Override
	public Integer getMeetingRoomsRequired() {
		
		return meetingScheduler.getMeetingList().size();
	}
	
	public MeetingScheduler createNewMeetingScheduler(){
		MeetingScheduler meetingScheduler = new MeetingScheduler();
		List<Meeting> meetings = new ArrayList<>();
		meetingScheduler.setMeetingList(meetings);
		
		return meetingScheduler;
	}
}
