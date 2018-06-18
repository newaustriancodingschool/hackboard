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
  @JsonProperty("id")
  private Long id = null;

  @JsonProperty("applicant")
  private Long applicant = null;

  @JsonProperty("projectId")
  private Long projectId = null;

  @JsonProperty("roleId")
  private Long roleId = null;

  @JsonProperty("applicantFullName")
  private String applicantFullName = null;

  @JsonProperty("roleName")
  private String roleName = null;

  @JsonProperty("roleColor")
  private String roleColor = null;

  @JsonProperty("userGithub")
  private String userGithub = null;

  @JsonProperty("status")
  private Long status = null;

  public ApplicationDto id(Long id) {
    this.id = id;
    return this;
  }

  /**
   * Get id
   * @return id
  **/
  @ApiModelProperty(required = true, value = "")
  @NotNull


  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

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

  public ApplicationDto applicantFullName(String applicantFullName) {
    this.applicantFullName = applicantFullName;
    return this;
  }

  /**
   * Get applicantFullName
   * @return applicantFullName
  **/
  @ApiModelProperty(value = "")


  public String getApplicantFullName() {
    return applicantFullName;
  }

  public void setApplicantFullName(String applicantFullName) {
    this.applicantFullName = applicantFullName;
  }

  public ApplicationDto roleName(String roleName) {
    this.roleName = roleName;
    return this;
  }

  /**
   * Get roleName
   * @return roleName
  **/
  @ApiModelProperty(value = "")


  public String getRoleName() {
    return roleName;
  }

  public void setRoleName(String roleName) {
    this.roleName = roleName;
  }

  public ApplicationDto roleColor(String roleColor) {
    this.roleColor = roleColor;
    return this;
  }

  /**
   * Get roleColor
   * @return roleColor
  **/
  @ApiModelProperty(value = "")


  public String getRoleColor() {
    return roleColor;
  }

  public void setRoleColor(String roleColor) {
    this.roleColor = roleColor;
  }

  public ApplicationDto userGithub(String userGithub) {
    this.userGithub = userGithub;
    return this;
  }

  /**
   * Get userGithub
   * @return userGithub
  **/
  @ApiModelProperty(value = "")


  public String getUserGithub() {
    return userGithub;
  }

  public void setUserGithub(String userGithub) {
    this.userGithub = userGithub;
  }

  public ApplicationDto status(Long status) {
    this.status = status;
    return this;
  }

  /**
   * Get status
   * @return status
  **/
  @ApiModelProperty(required = true, value = "")
  @NotNull


  public Long getStatus() {
    return status;
  }

  public void setStatus(Long status) {
    this.status = status;
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
    return Objects.equals(this.id, applicationDto.id) &&
        Objects.equals(this.applicant, applicationDto.applicant) &&
        Objects.equals(this.projectId, applicationDto.projectId) &&
        Objects.equals(this.roleId, applicationDto.roleId) &&
        Objects.equals(this.applicantFullName, applicationDto.applicantFullName) &&
        Objects.equals(this.roleName, applicationDto.roleName) &&
        Objects.equals(this.roleColor, applicationDto.roleColor) &&
        Objects.equals(this.userGithub, applicationDto.userGithub) &&
        Objects.equals(this.status, applicationDto.status);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, applicant, projectId, roleId, applicantFullName, roleName, roleColor, userGithub, status);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ApplicationDto {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    applicant: ").append(toIndentedString(applicant)).append("\n");
    sb.append("    projectId: ").append(toIndentedString(projectId)).append("\n");
    sb.append("    roleId: ").append(toIndentedString(roleId)).append("\n");
    sb.append("    applicantFullName: ").append(toIndentedString(applicantFullName)).append("\n");
    sb.append("    roleName: ").append(toIndentedString(roleName)).append("\n");
    sb.append("    roleColor: ").append(toIndentedString(roleColor)).append("\n");
    sb.append("    userGithub: ").append(toIndentedString(userGithub)).append("\n");
    sb.append("    status: ").append(toIndentedString(status)).append("\n");
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

