INSERT INTO instructors (instructor_name, email) VALUES
                                                     ('John Smith', 'john.smith@example.com'),
                                                     ('Emily Johnson', 'emily.johnson@example.com'),
                                                     ('Michael Brown', 'michael.brown@example.com'),
                                                     ('Sophia Davis', 'sophia.davis@example.com'),
                                                     ('Daniel Wilson', 'daniel.wilson@example.com'),
                                                     ('Olivia Martinez', 'olivia.martinez@example.com'),
                                                     ('James Anderson', 'james.anderson@example.com'),
                                                     ('Isabella Taylor', 'isabella.taylor@example.com'),
                                                     ('William Thomas', 'william.thomas@example.com'),
                                                     ('Mia Hernandez', 'mia.hernandez@example.com');

INSERT INTO courses (course_name, description, instructor_id) VALUES
                                                                  ('Java', 'Java courses', 1),
                                                                  ('Web', 'Web development', 2),
                                                                  ('Spring', 'Spring Boot', 3),
                                                                  ('Database', 'Database course', 4),
                                                                  ('Git', 'Learn to use git with github', 5);

INSERT INTO students (student_name, email, phone_number) VALUES
                                                             ('Sok Dara', 'sokdara@gmail.com', '012345678'),
                                                             ('Chantha Vann', 'chantha.vann@gmail.com', '015234567'),
                                                             ('Srey Neang', 'sreyneang@gmail.com', '010987654'),
                                                             ('Vuthy Kim', 'vuthy.kim@gmail.com', '093456789'),
                                                             ('Piseth Chhun', 'piseth.chhun@gmail.com', '097654321'),
                                                             ('Dalin Heng', 'dalin.heng@gmail.com', '088112233'),
                                                             ('Rithy Sok', 'rithy.sok@gmail.com', '071223344'),
                                                             ('Mony Roth', 'mony.roth@gmail.com', '096334455'),
                                                             ('Sophea Ly', 'sophea.ly@gmail.com', '089445566'),
                                                             ('Kosal Phan', 'kosal.phan@gmail.com', '086556677');

INSERT INTO student_course (student_id, course_id) VALUES
                                                       (1, 1),
                                                       (1, 5),
                                                       (2, 7),
                                                       (3, 9),
                                                       (3, 7),
                                                       (4, 2),
                                                       (4, 5),
                                                       (4, 7);

SELECT * FROM student_course sc
                  INNER JOIN courses c
                             ON sc.course_id = c.course_id
WHERE student_id = 3;