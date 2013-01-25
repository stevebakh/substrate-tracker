package substrate.tracker.service;

import substrate.tracker.entity.Project;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * EJB service layer class used to interface with the persistence
 * layer for project data.
 */
@Stateless
public class ProjectService {
    /**
     * Interface with the persistence layer using JPA.
     */
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
     * Update an existing project in the database.
     *
     * @param project The project entity to add to the persistence context
     */
    public void updateProject(final Project project) {
        entityManager.merge(project);
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

    /**
     * Find and delete a project from the database using the
     * provided ID.
     *
     * @param projectId The ID of the Project to delete
     */
    public void deleteProjectById(final Long projectId) {
        entityManager.remove(
                entityManager.find(Project.class, projectId));
    }

    /**
     * Find and paginate all projects.
     *
     * @param limit The maximum number of projects to return
     * @param offset The start position in the list of projects
     * @return A collection of Project entities
     */
    public List<Project> getProjects(final int limit, final int offset) {
        return entityManager.createNamedQuery("allProjects", Project.class)
                .setFirstResult(offset)
                .setMaxResults(limit)
                .getResultList();
    }
}
