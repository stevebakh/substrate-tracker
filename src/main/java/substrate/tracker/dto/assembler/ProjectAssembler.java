package substrate.tracker.dto.assembler;

import substrate.tracker.dto.ProjectDTO;
import substrate.tracker.entity.Project;

import java.util.ArrayList;
import java.util.List;

/**
 * Mapper between project entities and DTOs.
 */
public class ProjectAssembler {
    /**
     * Map a project DTO into a project entity.
     *
     * @param projectDTO an instance of a project DTO
     * @return an instance of a project entity
     */
    public Project assembleEntity(final ProjectDTO projectDTO) {
        final Project project = new Project();
        project.setId(projectDTO.getId());
        project.setTitle(projectDTO.getTitle());
        return project;
    }

    /**
     * Map a project entity into a project DTO.
     *
     * @param project an instance of a project entity
     * @return an instance of a project DTO
     */
    public ProjectDTO assembleDTO(final Project project) {
        final ProjectDTO projectDTO = new ProjectDTO();
        projectDTO.setId(project.getId());
        projectDTO.setTitle(project.getTitle());
        return projectDTO;
    }

    /**
     * Map a list of project entities to a list of project DTOs
     *
     * @param projects a list of project entity objects
     * @return a list of project DTO objects
     */
    public List<ProjectDTO> assembleDTOList(final List<Project> projects) {
        final List<ProjectDTO> projectDTOs = new ArrayList<>();

        for (final Project project : projects) {
            projectDTOs.add(assembleDTO(project));
        }

        return projectDTOs;
    }
}
