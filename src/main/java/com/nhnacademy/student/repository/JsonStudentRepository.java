package com.nhnacademy.student.repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.nhnacademy.student.Student;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import java.io.*;
import java.nio.charset.StandardCharsets;

@Slf4j
public class JsonStudentRepository implements StudentRepository {
	private final ObjectMapper objectMapper;
	//json file 저장 경로
	private static final String JSON_FILE_PATH="/home/dmswl/Documents/IDEA/student/src/main/resources/json/student.json";

	public JsonStudentRepository(){
		objectMapper = new ObjectMapper();
		//LocalDatetime json 직열화/역직렬화 가능하도록 설정
		// todo 역직렬화에 실패한다면 Student 객체에 기본 생성자가 있는지 확인
		objectMapper.registerModule(new JavaTimeModule());
		// JSON_FILE_PATH 경로에 json 파일이 존재하면 삭제.
		File file = new File(JSON_FILE_PATH);
		if (file.exists()){
			file.delete();
		}
	}

	private synchronized List<Student> readJsonFile(){
		// json 파일이 존재하지 않다면 비어있는 List<Student> 리턴
		List<Student> students;
		File file = new File(JSON_FILE_PATH);
		if (!file.exists()){
			return new ArrayList<>();
		}

		//json read & 역직렬화 ( json string -> Object )
		try(FileInputStream fileInputStream = new FileInputStream(file);
		    InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, StandardCharsets.UTF_8);
		    BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
		) {
			students = objectMapper.readValue(bufferedReader, new TypeReference<List<Student>>() {});
			return  students;
		} catch (JsonProcessingException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

	}

	private synchronized void writeJsonFile(List<Student> studentList){
		// List<Student> 객체를 -> json 파일로 저장 : 직렬화
		File file = new File(JSON_FILE_PATH);

		try(
			FileWriter fileWriter = new FileWriter(file);
			BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
		) {
			objectMapper.writeValue(bufferedWriter,studentList);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void save(Student student) {
		//json String -> Object 형태로 변화 (직렬화)
		List<Student> students = readJsonFile();
		//List에 student 추가
		students.add(student);
		//List<Student>객체를 -> json String 형태로 저장(직렬화)
		writeJsonFile(students);
	}

	@Override
	public void update(Student student) {
		deleteById(student.getId());
		List<Student> students = readJsonFile();
		students.add(student);
		writeJsonFile(students);
	}

	@Override
	public void deleteById(String id) {
		List<Student> students = readJsonFile();
		for(Student s:students) {
			if(id.equals(s.getId())){
				students.remove(s);
				writeJsonFile(students);
				return;
			}
		}
	}

	@Override
	public Student getStudentById(String id) {
		List<Student> students = readJsonFile();
		for(Student s:students) {
			if(id.equals(s.getId())){
				return s;
			}
		}
		return null;
	}

	@Override
	public List<Student> getStudents() {
		List<Student> students = readJsonFile();
		return students;
	}

	@Override
	public boolean existById(String id) {
		List<Student> students = readJsonFile();
		for(Student s:students) {
			if(id.equals(s.getId())){
				return true;
			}
		}
		return false;
	}
}