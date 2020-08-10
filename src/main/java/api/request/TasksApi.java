package api.request;

import api.core.BaseApi;
import api.models.Task;
import commons.MappingUtils;
import commons.constants.Endpoints;

public class TasksApi extends BaseApi {

    public Task getTask() {
        return task;
    }

    public void setTask(Object task) {
        this.task = (Task) task;
    }

    private Task task;

    public TasksApi getActiveTaskById(String id) {
        sendGet(Endpoints.TASK_API + "/" + id);
        System.out.println(getResponse().body().asString());
        return this;
    }

    public Task saveTask(){
        setTask(MappingUtils.parseJsonToModel(getJsonAsString(),Task.class));
        return getTask();
    }
}
