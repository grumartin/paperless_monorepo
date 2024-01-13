package at.fhtw.swkom.paperless.elasticSearch;

public class SearchDocument {
    private int id;
    private String filename;
    private String content;

    public SearchDocument() {
    }
    public SearchDocument(int id, String filename, String content){
        this.id = id;
        this.filename = filename;
        this.content = content;
    }

    public int getId() {
        return id;
    }


    public String getFilename() {
        return filename;
    }

    public String getContent() {
        return content;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
