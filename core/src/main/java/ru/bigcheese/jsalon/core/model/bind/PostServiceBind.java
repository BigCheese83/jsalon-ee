package ru.bigcheese.jsalon.core.model.bind;

import ru.bigcheese.jsalon.core.model.BaseModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * Created by BigCheese on 18.02.16.
 */
public class PostServiceBind extends BaseModel {

    private Long postId;
    private Long serviceId;
    private String postName;
    private String serviceName;

    public PostServiceBind() {}

    public PostServiceBind(Long id, Long postId, Long serviceId) {
        super(id);
        this.postId = postId;
        this.serviceId = serviceId;
    }

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public Long getServiceId() {
        return serviceId;
    }

    public void setServiceId(Long serviceId) {
        this.serviceId = serviceId;
    }

    public String getPostName() {
        return postName;
    }

    public void setPostName(String postName) {
        this.postName = postName;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PostServiceBind)) return false;
        PostServiceBind that = (PostServiceBind) o;
        return Objects.equals(postId, that.postId) &&
                Objects.equals(serviceId, that.serviceId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(postId, serviceId);
    }

    @Override
    protected List<String> getValidateErrors() {
        List<String> errors = new ArrayList<String>();
        if (postId == null) {
            errors.add("Укажите наименование должности");
        }
        if (serviceId == null) {
            errors.add("Укажите наименование услуги");
        }
        return Collections.unmodifiableList(errors);
    }
}
