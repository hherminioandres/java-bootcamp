-- 3. List students and teachers for a given course.
SELECT c.name as 'Course', CONCAT(t.lastname,',',t.firstname) as 'Teacher', CONCAT(s.firstname,',',s.lastname) as 'Student'
FROM COURSE c
INNER JOIN TEACHER t ON (c.teacher = t.matriculation)
INNER JOIN STUDY st ON (c.name = st.course)
INNER JOIN STUDENT s ON (st.student = s.registration)
WHERE c.name = 'Algebra';

-- 4. Percentage of students that passed/failed a given course.
SELECT c.name AS 'Course',
ROUND(SUM(CASE WHEN h.final > 3 THEN 1 ELSE 0 END) / COUNT(h.student), 2)*100 AS 'Passed(%)',
ROUND(SUM(CASE WHEN h.final <= 3 THEN 1 ELSE 0 END) / COUNT(h.student), 2)*100 AS "Failed(%)"
FROM STUDENT s
INNER JOIN HISTORICAL h ON (s.registration = h.student)
INNER JOIN COURSE c ON (c.name = h.course)
WHERE c.name = 'Algebra';

-- 5. For a given teacher, list the timeline for each course that he is assigned to (ordered by date), and the course name.
SELECT CONCAT(t.lastname,', ', t.firstname) AS "Teacher", s.day as "Day", CONCAT(s.begin,' - ',s.end) AS "Time", c.name AS "Course"
FROM TEACHER t
INNER JOIN COURSE c ON (c.teacher = t.matriculation)
INNER JOIN SCHEDULE s ON (s.course = c.name)
WHERE t.matriculation = "mn002"
ORDER BY CASE
          WHEN Day = 'Sunday' THEN 1
          WHEN Day = 'Monday' THEN 2
          WHEN Day = 'Tuesday' THEN 3
          WHEN Day = 'Wednesday' THEN 4
          WHEN Day = 'Thursday' THEN 5
          WHEN Day = 'Friday' THEN 6
          WHEN Day = 'Saturday' THEN 7
     END, s.begin;