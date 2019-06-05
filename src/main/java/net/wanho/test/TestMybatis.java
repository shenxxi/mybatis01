package net.wanho.test;

import net.wanho.mapper.StudentMapper;
import net.wanho.pojo.Student;
import net.wanho.util.MybatisUtil;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.util.List;

/**
 * Created by Administrator on 2019/6/5.
 */
public class TestMybatis {
    private SqlSession sqlSession;
    private StudentMapper studentMapper;
    @Before
    public void test(){
       /* InputStream inputStream = TestMybatis.class.getClassLoader().getResourceAsStream("mybatis-config.xml");

        SqlSessionFactory sf = new SqlSessionFactoryBuilder().build(inputStream);

        sqlSession = sf.openSession();*/
        sqlSession = MybatisUtil.getSession();
        studentMapper = sqlSession.getMapper(StudentMapper.class);


    }

    @Test
    public void testAddStu(){
        Student student = new Student(null,"lisi",19,"男","南京");
        sqlSession.update("net.wanho.mapper.StudentMapper.addStu",student);
        sqlSession.commit();

    }

    @Test
    public void delStu(){
        SqlSession session = MybatisUtil.getSession();
        session.update("net.wanho.mapper.StudentMapper.delStu",5);
        session.commit();
        MybatisUtil.closeSession();

    }

    @Test
    public void getAllStus(){
        SqlSession session = MybatisUtil.getSession();
        List<Object> stus = session.selectList("net.wanho.mapper.StudentMapper.getAllStus");
        System.out.println(stus);
        MybatisUtil.closeSession();

    }

    @Test
    public void getStuById(){
       /* SqlSession session = MybatisUtil.getSession();
        Student stu = session.selectOne("net.wanho.mapper.StudentMapper.getStuById", 4);
        System.out.println(stu);*/
        Student stu = studentMapper.getStuById(4);
        System.out.println(stu);
        MybatisUtil.closeSession();

    }

    @Test
    public void getStuByName(){
        //List<Student> stus = studentMapper.getStuByName("%li%");
        List<Student> stus = studentMapper.getStuByName("li");

        System.out.println(stus);
        MybatisUtil.closeSession();

    }

    @Test
    public void getStuByName2(){
        Student student = new Student();
        student.setSname("li");
        List<Student> stus = studentMapper.getStuByName2(student);

        System.out.println(stus);
        MybatisUtil.closeSession();

    }

    @Test
    public void testAddStuReturnKey(){
        Student student = new Student(null,"lisi",19,"男","南京");
        studentMapper.addStuReturnKey(student);
        sqlSession.commit();
        System.out.println(student.getId());

    }

    @Test
    public void testAddStuReturnKey2(){
        Student student = new Student(null,"lisi",19,"男","南京");
        studentMapper.addStuReturnKey2(student);
        sqlSession.commit();
        System.out.println(student.getId());

    }

    @Test
    public void testUpdateStuById(){
        Student student = new Student(8,"xx",19,"男","南京");
        studentMapper.updateStuById(student,"南京",10);
        sqlSession.commit();
        //System.out.println(student.getId());

    }
    @Test
    public void testUpdateStuById2(){
        Student student = new Student(8,"hh",19,"男","南京");
        studentMapper.updateStuById(student,"南京",10);
        sqlSession.commit();
        //System.out.println(student.getId());

    }
}
