package com.yanchong.blog.Entity;

import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="blogs")
@Component
public class Blog implements Serializable {

    private static final long serialVersionUID = 1L;

    public Blog() {
    }

    private Long id;

    private String smartTitle;

    private String title;

    private String content;

    private Integer view_num=0;

    private Integer comment_num=0;

    private Integer type;

    private Integer isDel=0;

    private Date CreatTime=new Date();

    private Pic Pic;

    private User user;

    private Category category;

    private List<Comments> comments;


    @OneToMany
    @JoinColumn(name = "comment_id")
    public List<Comments> getComments() {
        return comments;
    }

    public void setComments(List<Comments> comments) {
        this.comments = comments;
    }

    @ManyToOne
    @JoinColumn(name="category_id") // 默认也为 department_id
    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @ManyToOne
    @JoinColumn(name="uid") // 默认也为 department_id
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @ManyToOne
    @JoinColumn(name="pic_id") // 默认也为 department_id
    public Pic getPic() {
        return Pic;
    }

    public void setPic(com.yanchong.blog.Entity.Pic pic) {
        Pic = pic;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Column(columnDefinition="TEXT")
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getView_num() {
        return view_num;
    }

    public void setView_num(Integer view_num) {
        this.view_num = view_num;
    }

    public Integer getComment_num() {
        return comment_num;
    }

    public void setComment_num(Integer comment_num) {
        this.comment_num = comment_num;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getSmartTitle() {
        return smartTitle;
    }

    public void setSmartTitle(String smartTitle) {
        this.smartTitle = smartTitle;
    }

    @Column(columnDefinition = "smallint")
    public Integer getIsDel() {
        return isDel;
    }

    public void setIsDel(Integer isDel) {
        this.isDel = isDel;
    }

    public Date getCreatTime() {
        return CreatTime;
    }

    public void setCreatTime(Date creatTime) {
        CreatTime = creatTime;
    }
}
