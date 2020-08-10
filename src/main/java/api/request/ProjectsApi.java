package api.request;

import api.core.BaseApi;
import api.keys.CommonKey;
import api.models.Project;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import commons.MappingUtils;
import commons.constants.Endpoints;

import java.util.List;

public class ProjectsApi extends BaseApi {
    private final ObjectMapper mapper = new ObjectMapper();


    public List<Project> getProjectList() {
        return projectList;
    }

    public void setProjectList(List<Project> projectList) {
        this.projectList = projectList;
    }

    private List<Project> projectList;

    public ProjectsApi createProjectByApi(String name) {
        JsonNode node = mapper.createObjectNode();

        ((ObjectNode) node).put(CommonKey.NAME, name);
        sendPost(Endpoints.PROJECT_API, node);
        return this;
    }

    public ProjectsApi getAllProjectApi(){
        sendGet(Endpoints.PROJECT_API);
        return this;
    }

    public ProjectsApi saveProjectsList(){
        setProjectList(MappingUtils.parseJsonToModelList(getJsonAsString(),Project[].class));
        return this;
    }

    public Project andFilterProjectByName(String name){
        for (Project prj :
                getProjectList()) {
            if (prj.getName().equals(name))return prj;
        }
        return null;
    }
    public ProjectsApi deleteProjectById(String projectId){
        sendDelete(Endpoints.PROJECT_API+"/"+projectId);
        return this;
    }
    public void deleteAllProject(){
        getAllProjectApi().saveProjectsList();
        for (Project prj :
                getProjectList()) {
            deleteProjectById(String.valueOf(prj.getId())).validateStatusCode(204);
        }
    }
}
