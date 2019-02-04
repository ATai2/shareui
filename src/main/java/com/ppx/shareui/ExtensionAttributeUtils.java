package com.ppx.shareui;

import org.activiti.bpmn.model.ExtensionAttribute;

public class ExtensionAttributeUtils {
    public static ExtensionAttribute generate(String key, String val) {
        ExtensionAttribute ea=new ExtensionAttribute();
        ea.setName(key);
        ea.setNamespace("http://shareiu.com");
        ea.setNamespacePrefix("shareiu");
        ea.setValue(val);
        return ea;
    }
}
