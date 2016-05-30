package ru.bigcheese.jsalon.ee.web.jsp.tag;

import org.apache.commons.lang3.StringUtils;
import ru.bigcheese.jsalon.core.support.SystemInfo;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by BigCheese on 10.02.2016.
 */
public class OsTag extends SimpleTagSupport {

    private static final Map<String, String> map;

    static {
        Map<String, String> m = new HashMap<>();
        m.put("windows", "<i class=\"fa fa-windows\"></i> ");
        m.put("macos", "<i class=\"fa fa-apple\"></i> ");
        m.put("linux", "<i class=\"fa fa-linux\"></i> ");
        map = Collections.unmodifiableMap(m);
    }

    @Override
    public void doTag() throws JspException, IOException {
        SystemInfo sysInfo = new SystemInfo();
        String ico = StringUtils.defaultString(map.get(sysInfo.getOsFamily()), "");
        getJspContext().getOut().print(ico + sysInfo.getOs());
    }
}
