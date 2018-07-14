package com.meetingroom.dao;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.meetingroom.BaseTest;
import com.meetingroom.entity.MeetingRoom;

public class MeetingRoomDaoTest extends BaseTest {
	@Autowired
	private MeetingRoomDao mrDao;

	public void insertMeetingRoomTest() {
		MeetingRoom mr = new MeetingRoom();
		mr.setFloor("5");
		mr.setRoom("501");
		mr.setDev("小白板,投影仪");
		mr.setCapacity(15);
		mrDao.insertMeetingRoom(mr);
	}

	public void deleteMeetingRoomTest() {
		mrDao.deleteMeetingRoom(4);
	}

	public void queryAllMeetingRoomTest() {
		System.out.println(mrDao.queryAllMeetingRoom());
	}

	@Test
	public void queryMeetingRoomforFreeTest() {
		//ArrayList<MeetingRoom> meetList = mrDao.queryMeetingRoomforFree("2018-07-07 09:00:00", "2018-07-07 11:00:00");
		//System.out.println(meetList);
	}

	public void queryMeetingRoomByIdTest() {
		System.out.println(mrDao.queryMeetingRoomById(5));
	}
	
	public void updateMeetingRoomTest() {
		MeetingRoom meetingRoom = new MeetingRoom();
		meetingRoom.setMeetingId(5);
		meetingRoom.setDev("啥也没有");
		mrDao.updateMeetingRoom(meetingRoom);
	}
}
