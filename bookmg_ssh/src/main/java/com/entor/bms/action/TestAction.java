package com.entor.bms.action;

import org.apache.struts2.convention.annotation.Action;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

@Controller
@Scope("prototype")
public class TestAction {

    public String test() {
        return "success";
    }
}
