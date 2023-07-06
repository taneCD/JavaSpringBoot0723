package com.postgre.SQLdemo.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLOutput;

@RestController
@RequestMapping(path="api/v1/student")
public class StudentControler {
    private final StudentService studentService;

    @Autowired
    public StudentControler(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public String getStudents() throws IOException {
        String str = readText();
        String noviString = "<ul>";
        for(var el : studentService.getStudents()){
            noviString=noviString+"<li>"+ el.getName()+"</li>";

        }
        noviString = noviString + "</ul>";
        return noviString;
    }

    @PostMapping
    public void registerNewStudent(@RequestBody Student student) {
        studentService.addNewStudent(student);
    }

    @DeleteMapping(path = "{studentId}")
    public void deleteStudent(@PathVariable("studentId") Long studentId) {
        studentService.deleteStudent(studentId);
    }

    @PutMapping(path = "{studentId}")
    public void updateStudent(@PathVariable("studentId") Long studentId, @RequestParam(required = false) String name, @RequestParam(required = false) String email) {
        studentService.updateStudent(studentId, name, email);
    }

    public String readText() throws IOException {

        BufferedReader reader = new BufferedReader(new FileReader("src/main/java/com/postgre/SQLdemo/Student/StudentHtml.html"));
        String everything;
        try {
            StringBuilder sb = new StringBuilder();
            String line = reader.readLine();

            while (line != null) {
                sb.append(line);
                sb.append(System.lineSeparator());
                line = reader.readLine();
            }
            everything = sb.toString();
        } finally {
            reader.close();
        }
        //Probaj, java 11
//        String content = Files.readString(Path.of("src/main/java/com/postgre/SQLdemo/Student/StudentHtml.html"));
        return everything;
    }

}
