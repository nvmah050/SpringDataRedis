package redisdemo.huyva.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import redisdemo.huyva.Controller.Request.AddStudentRequest;
import redisdemo.huyva.Controller.Response.MainResponse;
import redisdemo.huyva.Model.Student;
import redisdemo.huyva.Service.StudentService;

@RestController
@RequestMapping("/api/v1")
public class StudentController extends BaseController{
    @Autowired
    StudentService studentService;

    @RequestMapping(value = "/students" , method = RequestMethod.POST
            , produces = MediaType.APPLICATION_JSON_VALUE)
        public ResponseEntity<MainResponse> getStudentByName(@RequestBody AddStudentRequest request){

        return returnSuccess(studentService.addStudent(request));
    }

    @RequestMapping(value = "/students/{id}" , method = RequestMethod.DELETE
            , produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MainResponse> deleteStudentById(@PathVariable("id") Long id){

        return returnSuccess(studentService.deleteStudentById(id));
    }

    @RequestMapping(value = "/students/count" , method = RequestMethod.GET
            , produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MainResponse> countTotalStudents(){

        return returnSuccess(studentService.countTotalRecord());
    }

    @RequestMapping(value = "/students/{id}" , method = RequestMethod.GET
            , produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MainResponse> getStudentById(@PathVariable("id") Long id){

        return returnSuccess(studentService.getStudentById(id));
    }
    @RequestMapping(value = "/students/all" , method = RequestMethod.GET
            , produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MainResponse> getAllStudent(){

        return returnSuccess(studentService.getAll());
    }


}
