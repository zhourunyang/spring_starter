package com.zry.mapper;

import com.zry.pojo.Emp;
import com.zry.pojo.EmpQueryParam;
import org.apache.ibatis.annotations.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Mapper
public interface EmpMapper {

    //原始分页查询
    /**
     * 查询总记录数
     * @return
     */
    //@Select("select count(*) from emp e left join dept d on e.dept_id = d.id")
    //Long count();

    /**
     * 分页查询
     * @return
     */
    //@Select("select e.*, d.name deptName from emp e left join dept d on e.dept_id = d.id order by e.update_time desc limit #{index}, #{pageSize}")
    //List<Emp> list(Integer index,Integer pageSize);

    //PageHelper分页查询
    //@Select("select e.*, d.name deptName from emp e left join dept d on e.dept_id = d.id order by e.update_time desc")

    /**
     * 条件分页查询
     * @param name
     * @param gender
     * @param begin
     * @param end
     * @return
     */
     //List<Emp> list(String name, Integer gender, LocalDate begin, LocalDate end);

    /**
     * 封装条件分页查询
     * @param e
     * @return
     */
    List<Emp> list(EmpQueryParam e);

    /**
     * 新增员工基本信息
     * @param emp
     */
    @Options(useGeneratedKeys = true,keyProperty = "id")  //获取到生成的主键   --主键返回
    @Insert("insert into emp(username, name, gender, phone, job, salary, image, entry_date, dept_id, create_time, update_time)" +
            "values (#{username}, #{name}, #{gender}, #{phone}, #{job}, #{salary}, #{image}, #{entryDate}, #{deptId}, #{createTime}, #{updateTime}) ")
    void insert(Emp emp);

    /**
     * 批量删除员工的基本信息
     * @param ids
     */
    void deleteByIds(List<Integer> ids);


    Emp getById(Integer id);

    void updateById(Emp emp);

    @MapKey("pos")
    List<Map<String, Object>> countEmpJobData();

    @MapKey("name")
    List<Map> countEmpGenderDate();

    @Select("select * from emp where username = #{username} and password = #{password}")
    Emp selectByUsernameAndPassword(Emp emp);
}


