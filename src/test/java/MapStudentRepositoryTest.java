import com.nhnacademy.student.Gender;
import com.nhnacademy.student.Student;
import com.nhnacademy.student.repository.MapStudentRepository;

public class MapStudentRepositoryTest {
	public static  MapStudentRepository repository;
	public static void main(String[] args) {
		repository = new MapStudentRepository();

		for (int i=0; i<5; i++) {
			Student student = new Student(i+"", "name"+i, Gender.M, 10+i);
			repository.save(student);
		}

		System.out.println(repository.getStudentById("3").toString());
	}

	private void getStudentsTest(){
		for(Student student : repository.getStudents()){
			System.out.println(student);
		}
	}
}
