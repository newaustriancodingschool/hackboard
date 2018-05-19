package io.refugeescode.hackboard.service.dto;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
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

  @JsonProperty("roles")
  @Valid
  private List<String> roles = null;

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

  public ProjectDto roles(List<String> roles) {
    this.roles = roles;
    return this;
  }

  public ProjectDto addRolesItem(String rolesItem) {
    if (this.roles == null) {
      this.roles = new ArrayList<>();
    }
    this.roles.add(rolesItem);
    return this;
  }

  /**
   * Get roles
   * @return roles
  **/
  @ApiModelProperty(value = "")


  public List<String> getRoles() {
    return roles;
  }

  public void setRoles(List<String> roles) {
    this.roles = roles;
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
        Objects.equals(this.roles, projectDto.roles);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, title, description, ownerId, ownerFirstName, ownerLastName, roles);
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
    sb.append("    roles: ").append(toIndentedString(roles)).append("\n");
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

