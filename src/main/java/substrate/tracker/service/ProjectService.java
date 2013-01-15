package substrate.tracker.service;

import substrate.tracker.entity.Project;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class ProjectService {
    @PersistenceContext
    private EntityManager entityManager;

    /**
     * Persist a new project to the database.
     *
     * @param project An un-persisted project entity
     * @return the auto-generated ID for the project
     */
    public Long createProject(final Project project) {
        entityManager.persist(project);
        entityManager.flush();
        return project.getId();
    }

    /**
     * Find a project from the database using the provided ID.
     *
     * @param projectId The ID of the Project to find
     * @return A matching Project entity
     */
    public Project findProjectById(final Long projectId) {
        return entityManager.find(Project.class, projectId);
    }
}
