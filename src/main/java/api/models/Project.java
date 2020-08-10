package api.models;

public class Project {
  private Long id;
  private int color;
  private String name;
  private int commentCount;
  private boolean isShared;
  private boolean isInboxProject;
  private boolean isTeamInbox;
  private int parent;
  private int order;

  public Project() {

  }

  public boolean isTeamInbox() {
    return isTeamInbox;
  }

  public void setTeamInbox(boolean isTeamInbox) {
    this.isTeamInbox = isTeamInbox;
  }

  public int getParent() {
    return parent;
  }

  public void setParent(int parent) {
    this.parent = parent;
  }

  public int getOrder() {
    return order;
  }

  public void setOrder(int order) {
    this.order = order;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public int getColor() {
    return color;
  }

  public void setColor(int color) {
    this.color = color;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getCommentCount() {
    return commentCount;
  }

  public void setCommentCount(int commentCount) {
    this.commentCount = commentCount;
  }

  public boolean isShared() {
    return isShared;
  }

  public void setShared(boolean isShared) {
    this.isShared = isShared;
  }

  public boolean isInboxProject() {
    return isInboxProject;
  }

  public void setInboxProject(boolean isInboxProject) {
    this.isInboxProject = isInboxProject;
  }
}
