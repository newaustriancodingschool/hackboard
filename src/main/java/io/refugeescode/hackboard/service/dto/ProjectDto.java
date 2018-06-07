package io.refugeescode.hackboard.service.dto;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.refugeescode.hackboard.service.dto.ApplicationDto;
import io.refugeescode.hackboard.service.dto.ProjectRoleDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * ProjectDto
 */
@Validated

public class ProjectDto   {
  @JsonProperty("id")
  private Long id = null;

  @JsonProperty("title")
  private String title = null;

  @JsonProperty("description")
  private String description = null;

  @JsonProperty("ownerId")
  private Long ownerId = null;

  @JsonProperty("ownerFirstName")
  private String ownerFirstName = null;

  @JsonProperty("ownerLastName")
  private String ownerLastName = null;

  @JsonProperty("ownerLoginName")
  private String ownerLoginName = null;

  @JsonProperty("github")
  private String github = null;

  @JsonProperty("projectStories")
  @Valid
  private List<String> projectStories = null;

  @JsonProperty("projectRole")
  @Valid
  private List<ProjectRoleDto> projectRole = null;

  @JsonProperty("applicationDto")
  @Valid
  private List<ApplicationDto> applicationDto = null;

  public ProjectDto id(Long id) {
    this.id = id;
    return this;
  }

  /**
   * Get id
   * @return id
  **/
  @ApiModelProperty(value = "")


  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public ProjectDto title(String title) {
    this.title = title;
    return this;
  }

  /**
   * Get title
   * @return title
  **/
  @ApiModelProperty(required = true, value = "")
  @NotNull


  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public ProjectDto description(String description) {
    this.description = description;
    return this;
  }

  /**
   * Get description
   * @return description
  **/
  @ApiModelProperty(required = true, value = "")
  @NotNull


  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public ProjectDto ownerId(Long ownerId) {
    this.ownerId = ownerId;
    return this;
  }

  /**
   * Get ownerId
   * @return ownerId
  **/
  @ApiModelProperty(value = "")


  public Long getOwnerId() {
    return ownerId;
  }

  public void setOwnerId(Long ownerId) {
    this.ownerId = ownerId;
  }

  public ProjectDto ownerFirstName(String ownerFirstName) {
    this.ownerFirstName = ownerFirstName;
    return this;
  }

  /**
   * Get ownerFirstName
   * @return ownerFirstName
  **/
  @ApiModelProperty(value = "")


  public String getOwnerFirstName() {
    return ownerFirstName;
  }

  public void setOwnerFirstName(String ownerFirstName) {
    this.ownerFirstName = ownerFirstName;
  }

  public ProjectDto ownerLastName(String ownerLastName) {
    this.ownerLastName = ownerLastName;
    return this;
  }

  /**
   * Get ownerLastName
   * @return ownerLastName
  **/
  @ApiModelProperty(value = "")


  public String getOwnerLastName() {
    return ownerLastName;
  }

  public void setOwnerLastName(String ownerLastName) {
    this.ownerLastName = ownerLastName;
  }

  public ProjectDto ownerLoginName(String ownerLoginName) {
    this.ownerLoginName = ownerLoginName;
    return this;
  }

  /**
   * Get ownerLoginName
   * @return ownerLoginName
  **/
  @ApiModelProperty(value = "")


  public String getOwnerLoginName() {
    return ownerLoginName;
  }

  public void setOwnerLoginName(String ownerLoginName) {
    this.ownerLoginName = ownerLoginName;
  }

  public ProjectDto github(String github) {
    this.github = github;
    return this;
  }

  /**
   * Get github
   * @return github
  **/
  @ApiModelProperty(value = "")


  public String getGithub() {
    return github;
  }

  public void setGithub(String github) {
    this.github = github;
  }

  public ProjectDto projectStories(List<String> projectStories) {
    this.projectStories = projectStories;
    return this;
  }

  public ProjectDto addProjectStoriesItem(String projectStoriesItem) {
    if (this.projectStories == null) {
      this.projectStories = new ArrayList<>();
    }
    this.projectStories.add(projectStoriesItem);
    return this;
  }

  /**
   * Get projectStories
   * @return projectStories
  **/
  @ApiModelProperty(value = "")


  public List<String> getProjectStories() {
    return projectStories;
  }

  public void setProjectStories(List<String> projectStories) {
    this.projectStories = projectStories;
  }

  public ProjectDto projectRole(List<ProjectRoleDto> projectRole) {
    this.projectRole = projectRole;
    return this;
  }

  public ProjectDto addProjectRoleItem(ProjectRoleDto projectRoleItem) {
    if (this.projectRole == null) {
      this.projectRole = new ArrayList<>();
    }
    this.projectRole.add(projectRoleItem);
    return this;
  }

  /**
   * Get projectRole
   * @return projectRole
  **/
  @ApiModelProperty(value = "")

  @Valid

  public List<ProjectRoleDto> getProjectRole() {
    return projectRole;
  }

  public void setProjectRole(List<ProjectRoleDto> projectRole) {
    this.projectRole = projectRole;
  }

  public ProjectDto applicationDto(List<ApplicationDto> applicationDto) {
    this.applicationDto = applicationDto;
    return this;
  }

  public ProjectDto addApplicationDtoItem(ApplicationDto applicationDtoItem) {
    if (this.applicationDto == null) {
      this.applicationDto = new ArrayList<>();
    }
    this.applicationDto.add(applicationDtoItem);
    return this;
  }

  /**
   * Get applicationDto
   * @return applicationDto
  **/
  @ApiModelProperty(value = "")

  @Valid

  public List<ApplicationDto> getApplicationDto() {
    return applicationDto;
  }

  public void setApplicationDto(List<ApplicationDto> applicationDto) {
    this.applicationDto = applicationDto;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ProjectDto projectDto = (ProjectDto) o;
    return Objects.equals(this.id, projectDto.id) &&
        Objects.equals(this.title, projectDto.title) &&
        Objects.equals(this.description, projectDto.description) &&
        Objects.equals(this.ownerId, projectDto.ownerId) &&
        Objects.equals(this.ownerFirstName, projectDto.ownerFirstName) &&
        Objects.equals(this.ownerLastName, projectDto.ownerLastName) &&
        Objects.equals(this.ownerLoginName, projectDto.ownerLoginName) &&
        Objects.equals(this.github, projectDto.github) &&
        Objects.equals(this.projectStories, projectDto.projectStories) &&
        Objects.equals(this.projectRole, projectDto.projectRole) &&
        Objects.equals(this.applicationDto, projectDto.applicationDto);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, title, description, ownerId, ownerFirstName, ownerLastName, ownerLoginName, github, projectStories, projectRole, applicationDto);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ProjectDto {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    title: ").append(toIndentedString(title)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
    sb.append("    ownerId: ").append(toIndentedString(ownerId)).append("\n");
    sb.append("    ownerFirstName: ").append(toIndentedString(ownerFirstName)).append("\n");
    sb.append("    ownerLastName: ").append(toIndentedString(ownerLastName)).append("\n");
    sb.append("    ownerLoginName: ").append(toIndentedString(ownerLoginName)).append("\n");
    sb.append("    github: ").append(toIndentedString(github)).append("\n");
    sb.append("    projectStories: ").append(toIndentedString(projectStories)).append("\n");
    sb.append("    projectRole: ").append(toIndentedString(projectRole)).append("\n");
    sb.append("    applicationDto: ").append(toIndentedString(applicationDto)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

