package redisdemo.huyva.repository;

import org.springframework.data.repository.CrudRepository;
import redisdemo.huyva.Model.Student;

public interface StudentRepository  extends CrudRepository<Student, Long> {
}
