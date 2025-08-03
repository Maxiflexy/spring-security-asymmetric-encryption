package com.maxiflexy.app.category;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, String> {
    /**
     * Finds a category by its name and user ID.
     *
     * @param name   the name of the category
     * @param userId the ID of the user
     * @return an Optional containing the found Category, or empty if not found
     */
    @Query("""
            SELECT COUNT (c) > 0
            FROM Category c
            WHERE LOWER(c.name) = LOWER(:name)
            AND c.createdBy = :userId OR c.createdBy = 'APP'
            """)
    boolean findByNameAndUser(String name, String userId);

    /**
     * Finds all categories created by a specific user or the application itself.
     *
     * @param userId the ID of the user
     * @return a list of Categories created by the user or the application
     */
    @Query("""
            SELECT c FROM Category c
            WHERE c.createdBy = :userId OR c.createdBy = 'APP'
            """)
    List<Category> findAllByUserId(String userId);

    /**
     * Finds a category by its ID and user ID.
     *
     * @param categoryId the ID of the category
     * @param userId     the ID of the user
     * @return an Optional containing the found Category, or empty if not found
     */
    @Query("""
            SELECT c FROM Category c
            WHERE c.id = :categoryId
            AND (c.createdBy = :userId OR c.createdBy = 'APP')
            """)
    Optional<Category> findByIdAndUserId(String categoryId, String userId);
}