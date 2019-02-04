package com.ppx.shareui.controller;

import com.ppx.shareui.service.ShareuiModelService;
import com.ppx.shareui.config.DataSourceConfig;
import lombok.extern.slf4j.Slf4j;
import org.activiti.app.domain.editor.AbstractModel;
import org.activiti.app.model.idm.GroupRepresentation;
import org.activiti.app.model.idm.UserRepresentation;
import org.activiti.app.rest.editor.AbstractModelBpmnResource;
import org.activiti.app.security.SecurityUtils;
import org.activiti.app.service.exception.BaseModelerRestException;
import org.activiti.app.service.exception.InternalServerErrorException;
import org.activiti.bpmn.model.BpmnModel;
import org.activiti.engine.IdentityService;
import org.activiti.engine.identity.Group;
import org.activiti.engine.identity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

/**
 * @author Administrator
 */
@Slf4j
@RestController
@RequestMapping
public class IdmController extends AbstractModelBpmnResource {

    @Autowired
    private DataSourceConfig dataSourceConfig;
    @Autowired
    private IdentityService identityService;
    @Autowired
    private ShareuiModelService shareuiModelService;

    @Override
    @RequestMapping(value = "/models/{processModelId}/bpmn20", method = RequestMethod.GET)
    public void getProcessModelBpmn20Xml(HttpServletResponse response, @PathVariable String processModelId) throws IOException {
        super.getProcessModelBpmn20Xml(response, processModelId);
    }


    @RequestMapping("/authentication")
    @ResponseBody
    public String user(HttpServletRequest request) {
        return "{\"login\":\"shareui@activiti.gov\"}";
    }

    @RequestMapping("/authenticate")
    @ResponseBody
    public String user2(HttpServletRequest request) {
        return "{\"login\":\"shareui@activiti.gov\"}";
    }

    @RequestMapping("/account")
    @ResponseBody
    public String account(HttpServletRequest request) {
        return "{\"id\":\"admin\",\"firstName\":null,\"lastName\":\"Administrator\",\"email\":\"admin\",\"fullName\":\" Administrator\",\"groups\":[{\"id\":\"ROLE_ADMIN\",\"name\":\"Superusers\",\"type\":\"security-role\"}]}";
    }

    @RequestMapping(
            value = {"/profile"},
            method = {RequestMethod.GET},
            produces = {"application/json"}
    )
    public UserRepresentation getProfile() {
        User user = SecurityUtils.getCurrentUserObject();
        UserRepresentation userRepresentation = new UserRepresentation(user);
        List<Group> groups = this.identityService.createGroupQuery().groupMember(user.getId()).list();
        Iterator var4 = groups.iterator();

        while (var4.hasNext()) {
            Group group = (Group) var4.next();
            userRepresentation.getGroups().add(new GroupRepresentation(group));
        }

        return userRepresentation;
    }
//    @RequestMapping("/app/rest/runtime/app-definitions")
//    @ResponseBody
//    public String runtime(HttpServletRequest request){
//        return "{\"size\":3,\"total\":3,\"start\":0,\"data\":[{\"defaultAppId\":\"kickstart\",\"name\":null,\"description\":null,\"modelId\":null,\"theme\":null,\"icon\":null,\"deploymentId\":null,\"deploymentKey\":null,\"tenantId\":null},{\"defaultAppId\":\"tasks\",\"name\":null,\"description\":null,\"modelId\":null,\"theme\":null,\"icon\":null,\"deploymentId\":null,\"deploymentKey\":null,\"tenantId\":null},{\"defaultAppId\":\"identity\",\"name\":null,\"description\":null,\"modelId\":null,\"theme\":null,\"icon\":null,\"deploymentId\":null,\"deploymentKey\":null,\"tenantId\":null}]}";
//    }

    @Override
    protected void generateBpmn20Xml(HttpServletResponse response, AbstractModel model) {
        String name = model.getName().replaceAll(" ", "_");
        response.setHeader("Content-Disposition", "attachment; filename=" + name + ".bpmn20.xml");
        if (model.getModelEditorJson() != null) {
            try {
                ServletOutputStream servletOutputStream = response.getOutputStream();
                response.setContentType("application/xml");

                BpmnModel bpmnModel = shareuiModelService.getBpmnModel(model);
                byte[] xmlBytes = shareuiModelService.getBpmnXML(bpmnModel);
                BufferedInputStream in = new BufferedInputStream(new ByteArrayInputStream(xmlBytes));

                byte[] buffer = new byte[8096];
                while (true) {
                    int count = in.read(buffer);
                    if (count == -1) {
                        break;
                    }
                    servletOutputStream.write(buffer, 0, count);
                }

                // Flush and close stream
                servletOutputStream.flush();
                servletOutputStream.close();

            } catch (BaseModelerRestException e) {
                throw e;

            } catch (Exception e) {
                log.error("Could not generate BPMN 2.0 XML", e);
                throw new InternalServerErrorException("Could not generate BPMN 2.0 xml");
            }
        }
    }

}
