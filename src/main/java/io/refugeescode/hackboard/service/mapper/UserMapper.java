package io.refugeescode.hackboard.service.mapper;

import io.refugeescode.hackboard.domain.Authority;
import io.refugeescode.hackboard.domain.Tag;
import io.refugeescode.hackboard.domain.User;
import io.refugeescode.hackboard.repository.TagsRepository;
import io.refugeescode.hackboard.service.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Mapper for the entity User and its DTO called UserDTO.
 * <p>
 * Normal mappers are generated using MapStruct, this one is hand-coded as MapStruct
 * support is still in beta, and requires a manual step with an IDE.
 */
@Service
public class UserMapper {

    @Autowired
    private TagsRepository tagsRepository;

    public UserDto userToUserDTO(User user) {
        return new UserDto(user);
    }

    public List<UserDto> usersToUserDTOs(List<User> users) {
        return users.stream()
            .filter(Objects::nonNull)
            .map(this::userToUserDTO)
            .collect(Collectors.toList());
    }

    public User userDTOToUser(UserDto userDto) {
        if (userDto == null) {
            return null;
        } else {
            User user = new User();
            user.setId(userDto.getId());
            user.setLogin(userDto.getLogin());
            user.setFirstName(userDto.getFirstName());
            user.setLastName(userDto.getLastName());
            user.setEmail(userDto.getEmail());
            user.setImageUrl(userDto.getImageUrl());
            user.setActivated(userDto.isActivated());
            user.setLangKey(userDto.getLangKey());
            user.setGithub(userDto.getGithub());
            user.setDescription(userDto.getDescription());
            Set<Authority> authorities = this.authoritiesFromStrings(userDto.getAuthorities());
            if (authorities != null) {
                user.setAuthorities(authorities);
            }
/*

            Set<Tag> tags  =new HashSet<>();
            if (! userDto.getTags().isEmpty()) {
                userDto.getTags().forEach(
                    e -> {
                        Optional<Tag> firstTag = tagsRepository.findAll()
                            .stream()
                            .filter(tag -> tag.getTag().equalsIgnoreCase(e))
                            .findFirst();
                        if (firstTag.isPresent())
                            tags.add(firstTag.get());
                    }
                );
            }
            if (!tags.isEmpty()){
                user.setTags(tags);
            }
*/




  /*          Set<Tag> tags = this.tagsFormsStrings(userDto.getTags());
            if (tags != null) {
                user.setTags(tags);
            }
  */          return user;
        }
    }

    public List<User> userDTOsToUsers(List<UserDto> userDtos) {
        return userDtos.stream()
            .filter(Objects::nonNull)
            .map(this::userDTOToUser)
            .collect(Collectors.toList());
    }

    public User userFromId(Long id) {
        if (id == null) {
            return null;
        }
        User user = new User();
        user.setId(id);
        return user;
    }

    public Set<Authority> authoritiesFromStrings(Set<String> strings) {
        return strings.stream().map(string -> {
            Authority auth = new Authority();
            auth.setName(string);
            return auth;
        }).collect(Collectors.toSet());
    }

    public Set<Tag> tagsFormsStrings(Set<String> strings) {
        return strings.stream()
            .map(e -> {
                Optional<Tag> tag = tagsRepository.findAll()
                    .stream()
                    .filter(currtage -> currtage.getTag().equalsIgnoreCase(e))
                    .findFirst();
                if (tag.isPresent())
                    return tag.get();
                else {
                    Tag tag1 = new Tag();
                    return tag1;
                }

            }).filter(Objects::nonNull)
            .collect(Collectors.toSet());
    }
}
