package redisdemo.huyva.Model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import redisdemo.huyva.Controller.Request.AddStudentRequest;

@RedisHash("student")
@Data
@NoArgsConstructor
public class Student {
    @Id
    private Long id;
    private String name;
    private String address;

    public Student(AddStudentRequest request){
        this.setName(request.getName());
        this.setAddress(request.getAddress());
    }
}
