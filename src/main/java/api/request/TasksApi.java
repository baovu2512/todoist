package api.request;

import api.core.BaseApi;
import api.models.Task;
import commons.MappingUtils;
import commons.constants.Endpoints;

import java.util.List;

public class TasksApi extends BaseApi {

    public Task getTask() {
        return task;
    }

    public void setTask(Object task) {
        this.task = (Task) task;
    }

    private Task task;

    public List<Task> getTaskList() {
        return taskList;
    }

    public void setTaskList(List<Task> taskList) {
        this.taskList = taskList;
    }

    private List<Task> taskList;

    public TasksApi getActiveTaskById(String id) {
        sendGet(Endpoints.TASK_API + "/" + id);
        return this;
    }

    public TasksApi getAllActiveTask() {
        sendGet(Endpoints.TASK_API);
        System.out.println(getResponse().statusCode());
        return this;
    }

    public Task saveTask() {
        setTask(MappingUtils.parseJsonToModel(getJsonAsString(), Task.class));
        return getTask();
    }

    public List<Task> saveTaskList() {
        System.out.println("Task \n" + getJsonAsString());
        setTaskList(MappingUtils.parseJsonToModelList(getJsonAsString(), Task[].class));
        return getTaskList();
    }

    public Task andFilterTaskByProjectIdAndContent(String projectId, String taskName) {
        for (Task tTask :
                getTaskList()) {
            if (tTask.getProject_id().equals(projectId) && tTask.getContent().equals(taskName)) return tTask;
        }

        return null;
    }

    public TasksApi reOpenTaskById(String id) {
        sendPost(Endpoints.TASK_API + "/" + id + "/reopen","");
        return this;
    }


}
