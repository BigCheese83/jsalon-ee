package ru.bigcheese.jsalon.ee.web.jsp.tag;

import ru.bigcheese.jsalon.core.Constants;
import ru.bigcheese.jsalon.core.util.CollectionUtils;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;
import java.util.List;

/**
 * Created by BigCheese on 30.05.16.
 */
public class EnvPropsTag extends SimpleTagSupport {
    @Override
    public void doTag() throws JspException, IOException {
        JspWriter out = getJspContext().getOut();
        List<String> props = CollectionUtils.listProperties(Constants.JSALON_ENVIRONMENT_PROPERTIES);
        for (String prop : props) {
            out.println(prop + "<br/>");
        }
    }
}
