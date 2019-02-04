package com.ppx.shareui.convertor;

import com.fasterxml.jackson.databind.JsonNode;
import com.ppx.shareui.ExtensionAttributeUtils;
import org.activiti.bpmn.model.ExtensionAttribute;
import org.activiti.bpmn.model.FlowElement;
import org.activiti.bpmn.model.UserTask;
import org.activiti.editor.language.json.converter.BaseBpmnJsonConverter;
import org.activiti.editor.language.json.converter.UserTaskJsonConverter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Administrator
 */
public class ShareuiUserTaskJsonConvertor extends UserTaskJsonConverter {

    public static final String SHAREUIID = "shareuiid";
    public static final String SHAREUINAME = "shareuiname";

    public static void fillJsonTypes(Map<String, Class<? extends BaseBpmnJsonConverter>> convertersToBpmnMap) {
        convertersToBpmnMap.put(STENCIL_TASK_USER, ShareuiUserTaskJsonConvertor.class);
    }

    @Override
    protected FlowElement convertJsonToElement(JsonNode elementNode, JsonNode modelNode, Map<String, JsonNode> shapeMap) {
        FlowElement flowElement = super.convertJsonToElement(elementNode, modelNode, shapeMap);
        UserTask userTask=null;
        if (flowElement != null) {
            userTask= (UserTask) flowElement;
        }
        String shareuiid = getPropertyValueAsString(SHAREUIID, elementNode);
        String shareuiname = getPropertyValueAsString(SHAREUINAME, elementNode);

        HashMap<String, List<ExtensionAttribute>> attributes = new HashMap<>();
        ExtensionAttribute generateId = ExtensionAttributeUtils.generate(SHAREUIID, shareuiid);
        ExtensionAttribute generateName = ExtensionAttributeUtils.generate(SHAREUINAME, shareuiid);
        List<ExtensionAttribute> vals=new ArrayList<>();
        vals.add(generateId);
        vals.add(generateName);
        attributes.put("shareiu-ext", vals);
        assert userTask != null;
        userTask.setAttributes(attributes);

        return userTask;


    }
}
