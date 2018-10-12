package redisdemo.huyva.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redisdemo.huyva.Controller.Request.AddStudentRequest;
import redisdemo.huyva.Controller.Response.MainResponse;
import redisdemo.huyva.Model.Student;
import redisdemo.huyva.queue.MessagePublisher;
import redisdemo.huyva.repository.StudentRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {
    private String code = "0";
    private String msg = "success";
    public static long id =1;

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    MessagePublisher messagePublisher;
    @Override
    public MainResponse addStudent(AddStudentRequest request) {
        Student student = new Student(request);
        StudentServiceImpl.id+=1;
        student.setId(StudentServiceImpl.id);
        studentRepository.save(student);
        messagePublisher.publish("add Student name :"+  request.getName());
        MainResponse response = new MainResponse(code, msg);
        return response;
    }

    @Override
    public MainResponse getStudentById(Long id) {
        Optional<Student> student = studentRepository.findById(id);
        MainResponse response = new MainResponse(code, msg);
        messagePublisher.publish("get Student info by ID :"+  id);
        if (student.isPresent())
            response.setData(student.get());
        return response;
    }

    @Override
    public MainResponse deleteStudentById(Long id) {
        studentRepository.deleteById(id);
        MainResponse response = new MainResponse(code, msg);
        return response;
    }


    @Override
    public MainResponse getAll() {
        Map<Long, Student> studentMap = new HashMap<>();
        Iterable<Student> student = studentRepository.findAll();
        student.forEach(studentInfo ->{
            studentMap.put(studentInfo.getId(), studentInfo );
        });
        MainResponse response = new MainResponse(code, msg);
        response.setData(studentMap);

        return response;

    }

    @Override
    public MainResponse countTotalRecord() {
        Long totalRecord = studentRepository.count();
        MainResponse response = new MainResponse(code, msg);
        response.setData(totalRecord);
        return response;
    }
}
