package io.github.zxh111222.blog;

import java.util.Objects;

public class Blog {
    private int id;
    private String title;
    private String content;
    private String type;
    private String cover;

    public Blog() {
    }

    public Blog(String title, String content, String type) {
        this.title = title;
        this.content = content;
        this.type = type;
    }

    public Blog(int id, String title, String content, String type, String cover) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.type = type;
        this.cover = cover;
    }

    public Blog(String title, String content, String type, String cover) {
        this.title = title;
        this.content = content;
        this.type = type;
        this.cover = cover;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    @Override
    public String toString() {
        return "Blog{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", type='" + type + '\'' +
                ", cover='" + cover + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Blog blog = (Blog) o;
        return id == blog.id && Objects.equals(title, blog.title) && Objects.equals(content, blog.content) && Objects.equals(type, blog.type) && Objects.equals(cover, blog.cover);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, content, type, cover);
    }
}