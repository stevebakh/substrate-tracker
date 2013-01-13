package substrate.tracker.service;

import substrate.tracker.entity.Project;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class ProjectService {
    @PersistenceContext
    private EntityManager entityManager;
}
