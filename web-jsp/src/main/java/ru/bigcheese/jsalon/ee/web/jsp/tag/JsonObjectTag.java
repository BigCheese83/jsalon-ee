package ru.bigcheese.jsalon.ee.web.jsp.tag;

import ru.bigcheese.jsalon.ee.web.jsp.util.JsonUtils;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;

/**
 * Created by BigCheese on 29.01.2016.
 */
public class JsonObjectTag extends SimpleTagSupport {

    private Object object;

    public void setObject(Object object) {
        this.object = object;
    }

    @Override
    public void doTag() throws JspException, IOException {
        if (object != null) {
            // Use object from attribute
            getJspContext().getOut().print(JsonUtils.getGson().toJson(object));
        }
    }
}
