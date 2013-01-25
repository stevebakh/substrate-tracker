package substrate.tracker.dto.assembler;

import substrate.tracker.dto.CollectionDTO;
import substrate.tracker.dto.ProjectDTO;
import substrate.tracker.entity.Project;

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
     * Map a list of project entities into a DTO representing
     * a collection of Project DTOs.
     *
     * @param projects A collection of Project entities
     * @return A DTO representing a collection of Project DTOs
     */
    public CollectionDTO<ProjectDTO> assembleDTOs(final List<Project> projects) {
        final CollectionDTO<ProjectDTO> collectionDTO = new CollectionDTO<>();

        for (final Project project : projects) {
            collectionDTO.addItem(assembleDTO(project));
        }

        return collectionDTO;
    }
}
