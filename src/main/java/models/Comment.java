package models;

import java.util.Objects;

public class Comment {
    private int id;
    private String Content;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    public Comment(String content) {
        Content = content;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comment comment = (Comment) o;
        return id == comment.id &&
                Content.equals(comment.Content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, Content);
    }
}
