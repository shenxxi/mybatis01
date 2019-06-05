package net.wanho.mapper;

import net.wanho.pojo.Student;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * Created by Administrator on 2019/6/5.
 */
public interface StudentMapper {

    List<Student> getAllStus();

    Student getStuById(Integer id);

    List<Student> getStuByName(String name);

    List<Student> getStuByName2(Student student);

    void addStu(Student student);

    void addStuReturnKey(Student student);

    void addStuReturnKey2(Student student);

    void delStu(Integer id);

    void updateStuById(@Param("stu") Student student, @Param("address") String address, Integer id);

    @Update(" UPDATE student SET name=#{stu.sname},address=#{address} where id=#{stu.id}")
    void updateStuById2(@Param("stu") Student student, @Param("address") String address, Integer id);




}
