package com.ant.questions.referenceclass;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * <p>
 * 学生类
 * </p>
 *
 * @author Ant
 * @since 2020/8/8 6:46 下午
 */
public class Student {
	private Long id;

	private String name;

	@Autowired
	private ClassRoom classRoom;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ClassRoom getClassRoom() {
		return classRoom;
	}

	public void setClassRoom(ClassRoom classRoom) {
		this.classRoom = classRoom;
	}

	@Override
	public String toString() {
		return "Student{" +
				"id=" + id +
				", name='" + name + '\'' +
				", classRoom.name=" + classRoom.getName() +
				'}';
	}
}
