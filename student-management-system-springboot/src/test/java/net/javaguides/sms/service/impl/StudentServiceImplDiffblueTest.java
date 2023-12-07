package net.javaguides.sms.service.impl;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import net.javaguides.sms.entity.Student;
import net.javaguides.sms.repository.StudentRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {StudentServiceImpl.class})
@ExtendWith(SpringExtension.class)
class StudentServiceImplDiffblueTest {
  @MockBean
  private StudentRepository studentRepository;

  @Autowired
  private StudentServiceImpl studentServiceImpl;
  /**
   * Method under test: {@link StudentServiceImpl#getAllStudents()}
   */
  @Test
  void testGetAllStudents() {
    // Arrange
    ArrayList<Student> studentList = new ArrayList<>();
    when(studentRepository.findAll()).thenReturn(studentList);

    // Act
    List<Student> actualAllStudents = studentServiceImpl.getAllStudents();

    // Assert
    assertSame(studentList, actualAllStudents);
    assertTrue(actualAllStudents.isEmpty());
    verify(studentRepository).findAll();
  }

  /**
   * Method under test: {@link StudentServiceImpl#saveStudent(Student)}
   */
  @Test
  void testSaveStudent() {
    // Arrange
    Student student = new Student();
    student.setEmail("jane.doe@example.org");
    student.setFirstName("Jane");
    student.setId(1L);
    student.setLastName("Doe");
    when(studentRepository.save(Mockito.<Student>any())).thenReturn(student);

    Student student2 = new Student();
    student2.setEmail("jane.doe@example.org");
    student2.setFirstName("Jane");
    student2.setId(1L);
    student2.setLastName("Doe");

    // Act and Assert
    assertSame(student, studentServiceImpl.saveStudent(student2));
    verify(studentRepository).save(Mockito.<Student>any());
  }

  /**
   * Method under test: {@link StudentServiceImpl#getStudentById(Integer)}
   */
  @Test
  void testGetStudentById() {
    // Arrange, Act and Assert
    assertNull(studentServiceImpl.getStudentById(1));
  }

  /**
   * Method under test: {@link StudentServiceImpl#getStudentById(Long)}
   */
  @Test
  void testGetStudentById2() {
    // Arrange
    Student student = new Student();
    student.setEmail("jane.doe@example.org");
    student.setFirstName("Jane");
    student.setId(1L);
    student.setLastName("Doe");
    Optional<Student> ofResult = Optional.of(student);
    when(studentRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

    // Act and Assert
    assertSame(student, studentServiceImpl.getStudentById(1L));
    verify(studentRepository).findById(Mockito.<Long>any());
  }

  /**
   * Method under test: {@link StudentServiceImpl#updateStudent(Student)}
   */
  @Test
  void testUpdateStudent() {
    // Arrange
    Student student = new Student();
    student.setEmail("jane.doe@example.org");
    student.setFirstName("Jane");
    student.setId(1L);
    student.setLastName("Doe");
    when(studentRepository.save(Mockito.<Student>any())).thenReturn(student);

    Student student2 = new Student();
    student2.setEmail("jane.doe@example.org");
    student2.setFirstName("Jane");
    student2.setId(1L);
    student2.setLastName("Doe");

    // Act and Assert
    assertSame(student, studentServiceImpl.updateStudent(student2));
    verify(studentRepository).save(Mockito.<Student>any());
  }

  /**
  * Method under test: {@link StudentServiceImpl#deleteStudentById(Long)}
  */
  @Test
  void testDeleteStudentById() {
    // Arrange
    doNothing().when(studentRepository).delById(Mockito.<Long>any());

    // Act
    studentServiceImpl.deleteStudentById(1L);

    // Assert that nothing has changed
    verify(studentRepository).delById(Mockito.<Long>any());
    assertTrue(studentServiceImpl.getAllStudents().isEmpty());
  }
}

