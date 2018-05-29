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
 * ApplicationDto
 */
@Validated

public class ApplicationDto   {
  @JsonProperty("applicant")
  private Long applicant = null;

  @JsonProperty("project_id")
  private Long projectId = null;

  @JsonProperty("role_id")
  private Long roleId = null;

  public ApplicationDto applicant(Long applicant) {
    this.applicant = applicant;
    return this;
  }

  /**
   * Get applicant
   * @return applicant
  **/
  @ApiModelProperty(required = true, value = "")
  @NotNull


  public Long getApplicant() {
    return applicant;
  }

  public void setApplicant(Long applicant) {
    this.applicant = applicant;
  }

  public ApplicationDto projectId(Long projectId) {
    this.projectId = projectId;
    return this;
  }

  /**
   * Get projectId
   * @return projectId
  **/
  @ApiModelProperty(required = true, value = "")
  @NotNull


  public Long getProjectId() {
    return projectId;
  }

  public void setProjectId(Long projectId) {
    this.projectId = projectId;
  }

  public ApplicationDto roleId(Long roleId) {
    this.roleId = roleId;
    return this;
  }

  /**
   * Get roleId
   * @return roleId
  **/
  @ApiModelProperty(required = true, value = "")
  @NotNull


  public Long getRoleId() {
    return roleId;
  }

  public void setRoleId(Long roleId) {
    this.roleId = roleId;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ApplicationDto applicationDto = (ApplicationDto) o;
    return Objects.equals(this.applicant, applicationDto.applicant) &&
        Objects.equals(this.projectId, applicationDto.projectId) &&
        Objects.equals(this.roleId, applicationDto.roleId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(applicant, projectId, roleId);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ApplicationDto {\n");
    
    sb.append("    applicant: ").append(toIndentedString(applicant)).append("\n");
    sb.append("    projectId: ").append(toIndentedString(projectId)).append("\n");
    sb.append("    roleId: ").append(toIndentedString(roleId)).append("\n");
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

