package io.refugeescode.hackboard.service.dto;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * ProjectRoleDto
 */
@Validated

public class ProjectRoleDto   {
  @JsonProperty("roleName")
  private String roleName = null;

  @JsonProperty("color")
  private String color = null;

  @JsonProperty("count")
  private Long count = null;

  public ProjectRoleDto roleName(String roleName) {
    this.roleName = roleName;
    return this;
  }

  /**
   * Get roleName
   * @return roleName
  **/
  @ApiModelProperty(required = true, value = "")
  @NotNull


  public String getRoleName() {
    return roleName;
  }

  public void setRoleName(String roleName) {
    this.roleName = roleName;
  }

  public ProjectRoleDto color(String color) {
    this.color = color;
    return this;
  }

  /**
   * Get color
   * @return color
  **/
  @ApiModelProperty(required = true, value = "")
  @NotNull


  public String getColor() {
    return color;
  }

  public void setColor(String color) {
    this.color = color;
  }

  public ProjectRoleDto count(Long count) {
    this.count = count;
    return this;
  }

  /**
   * Get count
   * @return count
  **/
  @ApiModelProperty(value = "")


  public Long getCount() {
    return count;
  }

  public void setCount(Long count) {
    this.count = count;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ProjectRoleDto projectRoleDto = (ProjectRoleDto) o;
    return Objects.equals(this.roleName, projectRoleDto.roleName) &&
        Objects.equals(this.color, projectRoleDto.color) &&
        Objects.equals(this.count, projectRoleDto.count);
  }

  @Override
  public int hashCode() {
    return Objects.hash(roleName, color, count);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ProjectRoleDto {\n");
    
    sb.append("    roleName: ").append(toIndentedString(roleName)).append("\n");
    sb.append("    color: ").append(toIndentedString(color)).append("\n");
    sb.append("    count: ").append(toIndentedString(count)).append("\n");
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

