package com.ppx.shareui;

import org.activiti.bpmn.converter.BpmnXMLConverter;
import org.activiti.bpmn.converter.util.InputStreamProvider;
import org.activiti.bpmn.model.*;
import org.activiti.bpmn.model.Process;
import org.activiti.engine.impl.util.io.InputStreamSource;
import org.junit.Test;

import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;

public class XmlTest {
    private static void accept(ExtensionAttribute extensionAttribute) {
        System.out.println(extensionAttribute.getNamespacePrefix());
        System.out.println(extensionAttribute.getName());
        System.out.println(extensionAttribute.getNamespace());
        System.out.println(extensionAttribute.getValue());
    }

    @Test
    public void getExtend(){
        BpmnXMLConverter xmlConverter=new BpmnXMLConverter();
        InputStream resourceAsStream = this.getClass().getResourceAsStream("../../../45.bpmn20.xml");
        InputStreamProvider inputStreamProvider=new InputStreamSource(resourceAsStream);
        BpmnModel bpmnModel = xmlConverter.convertToBpmnModel(inputStreamProvider, true, true);
        Process process = bpmnModel.getProcesses().get(0);
        List<UserTask> flowElementsOfType = process.findFlowElementsOfType(UserTask.class);
        flowElementsOfType.forEach(scriptTask -> {
            System.out.println("====================");
            Map<String, List<ExtensionAttribute>> attributes = scriptTask.getAttributes();
            Set<Map.Entry<String, List<ExtensionAttribute>>> entries = attributes.entrySet();
            for (Map.Entry<String, List<ExtensionAttribute>> entry:
            entries){
                System.out.println("dddddddddddddddddddddddddd");
                String key = entry.getKey();
                System.out.println("key: "+key);
                List<ExtensionAttribute> value = entry.getValue();
                value.forEach(XmlTest::accept);
            }
        });

    }
}
