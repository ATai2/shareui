package com.ppx.shareui.convertor;

import org.activiti.editor.language.json.converter.BpmnJsonConverter;

public class ShareuiBpmnJsonConvertor extends BpmnJsonConverter {
    static {
        convertersToBpmnMap.put(STENCIL_TASK_USER, ShareuiUserTaskJsonConvertor.class);
    }

}
