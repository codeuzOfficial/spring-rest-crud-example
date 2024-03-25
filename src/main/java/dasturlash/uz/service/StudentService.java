package dasturlash.uz.service;

import dasturlash.uz.dto.StudentDTO;
import dasturlash.uz.exp.AppBadRequestException;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

@Service
public class StudentService {
    private static List<StudentDTO> studentList = new LinkedList<>();

    public StudentDTO create(StudentDTO student) {
        student.setId(UUID.randomUUID().toString());
        studentList.add(student);
        return student;
    }

    public List<StudentDTO> getAllStudent() {
        return studentList;
    }

    public StudentDTO getById(String id) {
        StudentDTO student = get(id);
        if (student == null) throw new AppBadRequestException("Student not found");
        return student;
    }

    public String updateStudent(StudentDTO student, String id) {
        StudentDTO oldStudent = get(id);
        if (oldStudent == null) throw new AppBadRequestException("Student not found");

        oldStudent.setName(student.getName());
        oldStudent.setSurname(student.getSurname());
        oldStudent.setAge(student.getAge());
        return "Success";
    }

    public Boolean delete(String id) {
        boolean result = studentList.removeIf(student -> student.getId().equals(id));
        return result;
    }

    public StudentDTO get(String id) {
        for (StudentDTO dto : studentList) {
            if (dto.getId().equals(id)) {
                return dto;
            }
        }
        return null;
    }
}
