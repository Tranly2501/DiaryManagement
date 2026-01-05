package model;

public class Dinary {
    private int id;
    private String title;
    private String content;
    private String createAt;

    public Dinary() {}

    public Dinary(int id, String title, String content, String createAt) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.createAt = createAt;

    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreateAt() {
        return createAt;
    }

    public void setCreateAt(String createAt) {
        this.createAt = createAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    // lấy content rút gọn để hiện thị preview ngoài danh sách
    public String getPreview() {
        if ( content != null && content.length() >60){
            return content.substring(0,60)+"...."; /// lấy 0->60 kí tự đầu tiên
        }
        return content;
    }
}
