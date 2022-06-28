package com.bobocode.dao;

import com.bobocode.model.Photo;
import com.bobocode.model.PhotoComment;
import com.bobocode.util.ExerciseNotCompletedException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Transient;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

/**
 * Please note that you should not use auto-commit mode for your implementation.
 */
public class PhotoDaoImpl implements PhotoDao {
    private EntityManagerFactory entityManagerFactory;

    public PhotoDaoImpl(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    @Override
    public void save(Photo photo) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(photo);
        entityManager.getTransaction().commit();
    }

    @Override
    public Photo findById(long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        return entityManager.find(Photo.class, id);
    }

    @Override
    public List<Photo> findAll() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        String jpql = "SELECT p FROM Photo p";
        TypedQuery<Photo> query = entityManager.createQuery(jpql, Photo.class);
        return query.getResultList();
    }

    @Override
    public void remove(Photo photo) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.remove(entityManager.merge(photo));
        entityManager.getTransaction().commit();
    }

    @Override
    public void addComment(long photoId, String comment) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Photo photo = findById(photoId);
        PhotoComment photoComment = new PhotoComment();
        photoComment.setText(comment);
        photo.addComment(photoComment);
        entityManager.persist(entityManager.merge(photo));
        entityManager.getTransaction().commit();
    }
}
