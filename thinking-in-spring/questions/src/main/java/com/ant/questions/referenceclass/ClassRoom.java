package com.ant.questions.referenceclass;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;

/**
 * <p>
 * 教室类
 * </p>
 *
 * @author Ant
 * @since 2020/8/8 6:47 下午
 */
public class ClassRoom {
	private String name;

	@Autowired
	private Collection<Student> students;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Collection<Student> getStudents() {
		return students;
	}

	public void setStudents(Collection<Student> students) {
		this.students = students;
	}

	@Override
	public String toString() {
		return "ClassRoom{" +
				"name='" + name + '\'' +
				", students=" + students +
				'}';
	}
}
