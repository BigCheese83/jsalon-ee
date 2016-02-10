package ru.bigcheese.jsalon.ee.web.jsp.tag;

import ru.bigcheese.jsalon.core.support.SystemInfo;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;

/**
 * Created by BigCheese on 10.02.2016.
 */
public class OsTag extends SimpleTagSupport {
    @Override
    public void doTag() throws JspException, IOException {
        SystemInfo sysInfo = new SystemInfo();
        String os = sysInfo.getOsFamily();
        String ico = "";
        if ("windows".equals(os)) {
            ico = "<i class=\"fa fa-windows\"></i> ";
        } else if ("macos".equals(os)) {
            ico = "<i class=\"fa fa-apple\"></i> ";
        } else if ("linux".equals(os)) {
            ico = "<i class=\"fa fa-linux\"></i> ";
        }
        getJspContext().getOut().print(ico + sysInfo.getOs());
    }
}
