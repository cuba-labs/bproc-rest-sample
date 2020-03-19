package com.company.sample.service;

import com.haulmont.addon.bproc.entity.ProcessInstanceData;
import com.haulmont.addon.bproc.entity.TaskData;
import com.haulmont.addon.bproc.form.FormData;
import com.haulmont.addon.bproc.service.BprocFormService;
import com.haulmont.addon.bproc.service.BprocRuntimeService;
import com.haulmont.addon.bproc.service.BprocTaskService;
import com.haulmont.cuba.core.global.UserSessionSource;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;
import java.util.Map;

@Service(ExternalBprocService.NAME)
public class ExternalBprocServiceBean implements ExternalBprocService {

    @Inject
    private BprocTaskService bprocTaskService;

    @Inject
    private BprocRuntimeService bprocRuntimeService;

    @Inject
    private UserSessionSource userSessionSource;

    @Inject
    private BprocFormService bprocFormService;

    @Override
    public ProcessInstanceData startProcessInstanceByKey(String processDefinitionKey, String businessKey, Map<String, Object> processVariables) {
        return bprocRuntimeService.startProcessInstanceByKey(processDefinitionKey, businessKey, processVariables);
    }

    @Override
    public List<TaskData> getUserTasks() {
        return bprocTaskService.createTaskDataQuery()
                .taskAssignee(userSessionSource.getUserSession().getCurrentOrSubstitutedUser())
                .orderByTaskCreateTime().desc()
                .list();
    }

    @Override
    public void completeUserTask(String taskId, String outcome, Map<String, Object> processVariables) {
        TaskData taskData = bprocTaskService.createTaskDataQuery()
                .taskId(taskId)
                .singleResult();
        if (taskData != null) {
            bprocTaskService.completeWithOutcome(taskData, outcome, processVariables);
        } else {
            throw new RuntimeException("User task with id " + taskId + " not found");
        }
    }

    @Override
    public FormData getTaskFormData(String taskId) {
        return bprocFormService.getTaskFormData(taskId);
    }
}