package io.github.zxh111222.blog;

import java.util.Objects;

public class Blog {
    int id;
    String title;
    String content;

    public Blog(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public Blog(int id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
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

    @Override
    public String toString() {
        return "Blog{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Blog blog = (Blog) o;
        return id == blog.id && Objects.equals(title, blog.title) && Objects.equals(content, blog.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, content);
    }
}