package com.company.sample.service;

import com.haulmont.addon.bproc.entity.ProcessInstanceData;
import com.haulmont.addon.bproc.entity.TaskData;
import com.haulmont.addon.bproc.form.FormData;

import java.util.List;
import java.util.Map;

public interface ExternalBprocService {
    String NAME = "sample_ExternalBprocService";

    ProcessInstanceData startProcessInstanceByKey(String processDefinitionKey, String businessKey, Map<String, Object> processVariables);

    List<TaskData> getUserTasks();

    void completeUserTask(String taskId, String outcome, Map<String, Object> processVariables);

    FormData getTaskFormData(String taskId);
}