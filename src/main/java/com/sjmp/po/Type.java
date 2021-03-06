package com.sjmp.po;

import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author SJMP1573
 * @Date 2020/9/28 20:10
 * @Description Type 类
 * @Since version-1.0
 */

@Entity // 对应到数据库生成指定表
@Table(name = "t_type") // 指定表名字
public class Type {

    @Id // 指定主键
    @GeneratedValue // 指定增量方式，默认为 AUTO
    private Long id;
    @NotBlank(message = "分类名称不能为空")//与 TypeController 中 @Valid 一同使用，后端校验数据库数据为空吗？（新增 Type 为空？）
    private String name;

    /*
    * mappedBy 用来标注**拥有**这种关系的字段。
    * 除非关系是单向的，否则是必需的。
    * 那么什么叫拥有关联关系呢，假设是双向一对一的话，
    * 那么拥有关系的这一方有建立、解除和更新与另一方关系的能力。
    * 而另一方没有，只能被动管理。
    * */
    @OneToMany(mappedBy = "type") // Type 被维护 Blog 与 Type 的关系,
    private List<Blog> blogs = new ArrayList<>();

    public Type() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Blog> getBlogs() {
        return blogs;
    }

    public void setBlogs(List<Blog> blogs) {
        this.blogs = blogs;
    }

    @Override
    public String toString() {
        return "Type{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
