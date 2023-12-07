package net.javaguides.sms.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.util.ArrayList;
import net.javaguides.sms.entity.Student;
import net.javaguides.sms.repository.StudentRepository;
import net.javaguides.sms.service.StudentService;
import net.javaguides.sms.service.impl.StudentServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.ConcurrentModel;
import org.springframework.ui.Model;

@ContextConfiguration(classes = {StudentController.class})
@ExtendWith(SpringExtension.class)
class StudentControllerDiffblueTest {
  @Autowired
  private StudentController studentController;

  @MockBean
  private StudentService studentService;
  /**
  * Method under test: {@link StudentController#createStudentForm(Model)}
  */
  @Test
  void testCreateStudentForm() throws Exception {
    // Arrange
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/students/new");

    // Act and Assert
    MockMvcBuilders.standaloneSetup(studentController)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.model().size(1))
        .andExpect(MockMvcResultMatchers.model().attributeExists("student"))
        .andExpect(MockMvcResultMatchers.view().name("create_student"))
        .andExpect(MockMvcResultMatchers.forwardedUrl("create_student"));
  }

  /**
   * Method under test: {@link StudentController#listStudents(Model)}
   */
  @Test
  void testListStudents() {
    //   Diffblue Cover was unable to write a Spring test,
    //   so wrote a non-Spring test instead.
    //   Reason: R013 No inputs found that don't throw a trivial exception.
    //   Diffblue Cover tried to run the arrange/act section, but the method under
    //   test threw
    //   javax.servlet.ServletException: Circular view path [students]: would dispatch back to the current handler URL [/students] again. Check your ViewResolver setup! (Hint: This may be the result of an unspecified view, due to default view name generation.)
    //       at javax.servlet.http.HttpServlet.service(HttpServlet.java:655)
    //       at javax.servlet.http.HttpServlet.service(HttpServlet.java:764)
    //   See https://diff.blue/R013 to resolve this issue.

    // Arrange
    StudentRepository studentRepository = mock(StudentRepository.class);
    when(studentRepository.findAll()).thenReturn(new ArrayList<>());
    StudentController studentController = new StudentController(new StudentServiceImpl(studentRepository));

    // Act and Assert
    assertEquals("students", studentController.listStudents(new ConcurrentModel()));
    verify(studentRepository).findAll();
  }

  /**
   * Method under test: {@link StudentController#saveStudent(Student)}
   */
  @Test
  void testSaveStudent() throws Exception {
    // Arrange
    Student student = new Student();
    student.setEmail("jane.doe@example.org");
    student.setFirstName("Jane");
    student.setId(1L);
    student.setLastName("Doe");
    when(studentService.saveStudent(Mockito.<Student>any())).thenReturn(student);
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/students");

    // Act and Assert
    MockMvcBuilders.standaloneSetup(studentController)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isFound())
        .andExpect(MockMvcResultMatchers.model().size(1))
        .andExpect(MockMvcResultMatchers.model().attributeExists("student"))
        .andExpect(MockMvcResultMatchers.view().name("redirect:/students"))
        .andExpect(MockMvcResultMatchers.redirectedUrl("/students"));
  }

  /**
   * Method under test: {@link StudentController#updateStudent(Long, Student, Model)}
   */
  @Test
  void testUpdateStudent() throws Exception {
    // Arrange
    Student student = new Student();
    student.setEmail("jane.doe@example.org");
    student.setFirstName("Jane");
    student.setId(1L);
    student.setLastName("Doe");

    Student student2 = new Student();
    student2.setEmail("jane.doe@example.org");
    student2.setFirstName("Jane");
    student2.setId(1L);
    student2.setLastName("Doe");
    when(studentService.updateStudent(Mockito.<Student>any())).thenReturn(student2);
    when(studentService.getStudentById(Mockito.<Long>any())).thenReturn(student);
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/students/{id}", 1L);

    // Act and Assert
    MockMvcBuilders.standaloneSetup(studentController)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isFound())
        .andExpect(MockMvcResultMatchers.model().size(1))
        .andExpect(MockMvcResultMatchers.model().attributeExists("student"))
        .andExpect(MockMvcResultMatchers.view().name("redirect:/students"))
        .andExpect(MockMvcResultMatchers.redirectedUrl("/students"));
  }

  /**
   * Method under test: {@link StudentController#createStudentForm(Model)}
   */
  @Test
  void testCreateStudentForm2() throws Exception {
    // Arrange
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/students/new", "Uri Variables");

    // Act and Assert
    MockMvcBuilders.standaloneSetup(studentController)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.model().size(1))
        .andExpect(MockMvcResultMatchers.model().attributeExists("student"))
        .andExpect(MockMvcResultMatchers.view().name("create_student"))
        .andExpect(MockMvcResultMatchers.forwardedUrl("create_student"));
  }

  /**
   * Method under test: {@link StudentController#deleteStudent(Long)}
   */
  @Test
  void testDeleteStudent() throws Exception {
    // Arrange
    doNothing().when(studentService).deleteStudentById(Mockito.<Long>any());
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/students/{id}", 1L);

    // Act and Assert
    MockMvcBuilders.standaloneSetup(studentController)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isFound())
        .andExpect(MockMvcResultMatchers.model().size(0))
        .andExpect(MockMvcResultMatchers.view().name("redirect:/students"))
        .andExpect(MockMvcResultMatchers.redirectedUrl("/students"));
  }

  /**
   * Method under test: {@link StudentController#deleteStudent(Long)}
   */
  @Test
  void testDeleteStudent2() throws Exception {
    // Arrange
    when(studentService.getAllStudents()).thenReturn(new ArrayList<>());
    doNothing().when(studentService).deleteStudentById(Mockito.<Long>any());
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/students/{id}", "", "Uri Variables");

    // Act and Assert
    MockMvcBuilders.standaloneSetup(studentController)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.model().size(1))
        .andExpect(MockMvcResultMatchers.model().attributeExists("students"))
        .andExpect(MockMvcResultMatchers.view().name("students"))
        .andExpect(MockMvcResultMatchers.forwardedUrl("students"));
  }

  /**
   * Method under test: {@link StudentController#editStudentForm(Long, Model)}
   */
  @Test
  void testEditStudentForm() throws Exception {
    // Arrange
    Student student = new Student();
    student.setEmail("jane.doe@example.org");
    student.setFirstName("Jane");
    student.setId(1L);
    student.setLastName("Doe");
    when(studentService.getStudentById(Mockito.<Long>any())).thenReturn(student);
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/students/edit/{id}", 1L);

    // Act and Assert
    MockMvcBuilders.standaloneSetup(studentController)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.model().size(1))
        .andExpect(MockMvcResultMatchers.model().attributeExists("student"))
        .andExpect(MockMvcResultMatchers.view().name("edit_student"))
        .andExpect(MockMvcResultMatchers.forwardedUrl("edit_student"));
  }
}

