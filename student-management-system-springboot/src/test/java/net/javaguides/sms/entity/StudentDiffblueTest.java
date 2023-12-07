package net.javaguides.sms.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

class StudentDiffblueTest {
  /**
  * Methods under test: 
  * 
  * <ul>
  *   <li>{@link Student#Student()}
  *   <li>{@link Student#setEmail(String)}
  *   <li>{@link Student#setFirstName(String)}
  *   <li>{@link Student#setId(Long)}
  *   <li>{@link Student#setLastName(String)}
  *   <li>{@link Student#getEmail()}
  *   <li>{@link Student#getFirstName()}
  *   <li>{@link Student#getId()}
  *   <li>{@link Student#getLastName()}
  * </ul>
  */
  @Test
  void testConstructor() {
    // Arrange and Act
    Student actualStudent = new Student();
    actualStudent.setEmail("jane.doe@example.org");
    actualStudent.setFirstName("Jane");
    actualStudent.setId(1L);
    actualStudent.setLastName("Doe");
    String actualEmail = actualStudent.getEmail();
    String actualFirstName = actualStudent.getFirstName();
    Long actualId = actualStudent.getId();
    String actualLastName = actualStudent.getLastName();

    // Assert that nothing has changed
    assertEquals("jane.doe@example.org", actualEmail);
    assertEquals("Jane", actualFirstName);
    assertEquals(1L, actualId.longValue());
    assertEquals("Doe", actualLastName);
  }

  /**
   * Methods under test: 
   * 
   * <ul>
   *   <li>{@link Student#Student(String, String, String)}
   *   <li>{@link Student#setEmail(String)}
   *   <li>{@link Student#setFirstName(String)}
   *   <li>{@link Student#setId(Long)}
   *   <li>{@link Student#setLastName(String)}
   *   <li>{@link Student#getEmail()}
   *   <li>{@link Student#getFirstName()}
   *   <li>{@link Student#getId()}
   *   <li>{@link Student#getLastName()}
   * </ul>
   */
  @Test
  void testConstructor2() {
    // Arrange and Act
    Student actualStudent = new Student("Jane", "Doe", "jane.doe@example.org");
    actualStudent.setEmail("jane.doe@example.org");
    actualStudent.setFirstName("Jane");
    actualStudent.setId(1L);
    actualStudent.setLastName("Doe");
    String actualEmail = actualStudent.getEmail();
    String actualFirstName = actualStudent.getFirstName();
    Long actualId = actualStudent.getId();
    String actualLastName = actualStudent.getLastName();

    // Assert that nothing has changed
    assertEquals("jane.doe@example.org", actualEmail);
    assertEquals("Jane", actualFirstName);
    assertEquals(1L, actualId.longValue());
    assertEquals("Doe", actualLastName);
  }
}

