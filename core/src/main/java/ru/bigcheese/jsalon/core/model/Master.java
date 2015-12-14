package ru.bigcheese.jsalon.core.model;

import java.util.Date;

/**
 * Created by BigCheese on 31.07.15.
 */
public class Master extends Person {

    private Date hiringDate;
    private Post post;
    private boolean busy;

    public Date getHiringDate() {
        return new Date(hiringDate.getTime());
    }

    public void setHiringDate(Date hiringDate) {
        this.hiringDate = new Date(hiringDate.getTime());
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public boolean isBusy() {
        return busy;
    }

    public void setBusy(boolean busy) {
        this.busy = busy;
    }
}
