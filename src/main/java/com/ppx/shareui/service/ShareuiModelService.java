package com.ppx.shareui.service;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.ppx.shareui.convertor.ShareuiBpmnJsonConvertor;
import lombok.extern.slf4j.Slf4j;
import org.activiti.app.domain.editor.AbstractModel;
import org.activiti.app.domain.editor.Model;
import org.activiti.app.service.editor.ModelServiceImpl;
import org.activiti.app.service.exception.InternalServerErrorException;
import org.activiti.bpmn.model.BpmnModel;
import org.activiti.editor.language.json.converter.BpmnJsonConverter;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service("shareuiService")
@Primary
public class ShareuiModelService extends ModelServiceImpl {
  protected BpmnJsonConverter bpmnJsonConverter=new ShareuiBpmnJsonConvertor();
  @Override
  public BpmnModel getBpmnModel(AbstractModel model, Map<String, Model> formMap, Map<String, Model> decisionTableMap) {
    try {
      ObjectNode editorJsonNode = (ObjectNode) objectMapper.readTree(model.getModelEditorJson());
      Map<String, String> formKeyMap = new HashMap<String, String>();
      for (Model formModel : formMap.values()) {
        formKeyMap.put(formModel.getId(), formModel.getKey());
      }

      Map<String, String> decisionTableKeyMap = new HashMap<String, String>();
      for (Model decisionTableModel : decisionTableMap.values()) {
        decisionTableKeyMap.put(decisionTableModel.getId(), decisionTableModel.getKey());
      }

      return bpmnJsonConverter.convertToBpmnModel(editorJsonNode, formKeyMap, decisionTableKeyMap);

    } catch (Exception e) {
      log.error("Could not generate BPMN 2.0 model for " + model.getId(), e);
      throw new InternalServerErrorException("Could not generate BPMN 2.0 model");
    }
  }
}
