import unittest

class TestStudentRegistrationSystem(unittest.TestCase):
    def setUp(self):
        """Set up a new StudentRegistrationSystem for testing."""
        self.system = StudentRegistrationSystem()

    def test_create_student(self):
        """Test creating a new student."""
        result = self.system.create_student(1, "Alice", 20, "Computer Science")
        self.assertTrue(result)
        self.assertIn(1, self.system.students)

    def test_create_student_duplicate(self):
        """Test creating a student with an existing ID."""
        self.system.create_student(1, "Alice", 20, "Computer Science")
        result = self.system.create_student(1, "Bob", 22, "Mathematics")
        self.assertFalse(result)

    def test_read_student(self):
        """Test reading an existing student."""
        self.system.create_student(1, "Alice", 20, "Computer Science")
        student_id = self.system.read_student(1)
        self.assertEqual(student_id, 1)

    def test_read_student_not_found(self):
        """Test reading a non-existing student."""
        output = self.system.read_student(99)
        self.assertIsNone(output)

    def test_read_all_students(self):
        """Test reading all students."""
        self.system.create_student(1, "Alice", 20, "Computer Science")
        self.system.create_student(2, "Bob", 22, "Mathematics")
        students = self.system.read_all_students()
        self.assertEqual(len(students), 2)

    def test_update_student(self):
        """Test updating an existing student."""
        self.system.create_student(1, "Alice", 20, "Computer Science")
        result = self.system.update_student(1, name="Alice Smith", age=21)
        self.assertTrue(result)
        self.assertEqual(self.system.students[1].name, "Alice Smith")
        self.assertEqual(self.system.students[1].age, 21)

    def test_update_student_not_found(self):
        """Test updating a non-existing student."""
        result = self.system.update_student(99, name="Charlie")
        self.assertFalse(result)

    def test_delete_student(self):
        """Test deleting an existing student."""
        self.system.create_student(1, "Alice", 20, "Computer Science")
        result = self.system.delete_student(1)
        self.assertTrue(result)
        self.assertNotIn(1, self.system.students)

    def test_delete_student_not_found(self):
        """Test deleting a non-existing student."""
        result = self.system.delete_student(99)
        self.assertFalse(result)

if __name__ == '__main__':
    unittest.main()
