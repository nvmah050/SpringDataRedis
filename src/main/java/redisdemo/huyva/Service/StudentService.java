package redisdemo.huyva.Service;

import redisdemo.huyva.Controller.Request.AddStudentRequest;
import redisdemo.huyva.Controller.Response.MainResponse;

public interface StudentService {
    MainResponse addStudent(AddStudentRequest request);
    MainResponse getStudentById(Long id);
    MainResponse deleteStudentById(Long id);
    MainResponse getAll();
    MainResponse countTotalRecord();
}
