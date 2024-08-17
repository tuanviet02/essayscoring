/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author admin
 */
public class WriterEssay {
    private int writerEsssayId;
    private User writer;
    private Topic essay;
    private String contentEssay;
    private int limit;
    private String status;
    public WriterEssay() {
    }

    public WriterEssay(int writerEsssayId, User writer, Topic essay, String contentEssay, int limit, String status) {
        this.writerEsssayId = writerEsssayId;
        this.writer = writer;
        this.essay = essay;
        this.contentEssay = contentEssay;
        this.limit = limit;
        this.status = status;
    }

    
    public int getWriterEsssayId() {
        return writerEsssayId;
    }

    public void setWriterEsssayId(int writerEsssayId) {
        this.writerEsssayId = writerEsssayId;
    }

    public User getWriter() {
        return writer;
    }

    public void setWriter(User writer) {
        this.writer = writer;
    }

    public Topic getEssay() {
        return essay;
    }

    public void setEssay(Topic essay) {
        this.essay = essay;
    }

    
    public String getContentEssay() {
        return contentEssay;
    }

    public void setContentEssay(String contentEssay) {
        this.contentEssay = contentEssay;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
}
