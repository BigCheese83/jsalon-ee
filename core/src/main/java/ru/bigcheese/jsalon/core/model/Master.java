package ru.bigcheese.jsalon.core.model;

import java.util.*;

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

    @Override
    public String toString() {
        return "Мастер " + getFullFIO() +
                (getPassport() != null ? " [паспорт " + getPassport().getShortStr() + "]" : "");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Master)) return false;
        if (!super.equals(o)) return false;
        Master master = (Master) o;
        return Objects.equals(busy, master.busy) &&
                Objects.equals(hiringDate, master.hiringDate) &&
                Objects.equals(post, master.post);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), hiringDate, post, busy);
    }

    @Override
    protected List<String> getValidateErrors() {
        List<String> errors = new ArrayList<String>();
        errors.addAll(super.getValidateErrors());
        if (hiringDate == null) {
            errors.add("Укажите дату приема");
        }
        if (post != null) {
            errors.addAll(post.getValidateErrors());
        }
        return Collections.unmodifiableList(errors);
    }
}
