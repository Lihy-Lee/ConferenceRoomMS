package com.meetingroom.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;

import com.meetingroom.entity.MeetingRoom;

public interface MeetingRoomDao {

	/**
	 * 添加会议室
	 * 
	 * @param meetingRoom
	 * @return
	 */
	public int insertMeetingRoom(MeetingRoom meetingRoom);

	/**
	 * 根据会议室id删除会议室
	 * 
	 * @param mid
	 * @return
	 */
	public int deleteMeetingRoom(Integer meetingId);

	/**
	 * 查看所有的会议室记录
	 * 
	 * @return
	 */
	public ArrayList<MeetingRoom> queryAllMeetingRoom();

	/**
	 * 根据会议开始时间和结束时间,查询数据库中某个时间段空闲的所有会议室
	 * 
	 * @param startTime
	 *            预约会议室开始日期时间
	 * @param endTime
	 *            预约会议室结束日期时间
	 * @return 返回会议室对象
	 */
	// public ArrayList<MeetingRoom> queryMeetingRoomforFree(@Param("startTime")
	// Date startTime,
	// @Param("endTime") Date endTime);

	public ArrayList<MeetingRoom> queryMeetingRoomforFree(@Param("startTime") String startTime,
			@Param("endTime") String endTime);

	/**
	 * 根据会议室id查看会议室信息
	 * 
	 * @param mid
	 * @return
	 */
	public MeetingRoom queryMeetingRoomById(Integer meetingId);

	/**
	 * 修改会议室基本信息
	 * 
	 * @param meetingRoom
	 * @return
	 */
	public int updateMeetingRoom(MeetingRoom meetingRoom);

}
